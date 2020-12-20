var pageCurr=1;
var tableIns;
var pageSize;
var form;
var table;

layui.use(['table','form', 'layedit','laydate'], function () {
    form = layui.form;
    table = layui.table;
    var layer = layui.layer
        , laydate = layui.laydate;
    //监听表格复选框选择
    table.on('checkbox(orderDetailTable)');
    if (localStorage.getItem('ARR')){
        loadTable(JSON.parse(localStorage.getItem('ARR')));
    }else{
        loadTable([])
    }
    // localStorage.removeItem('ARR');
    form.render();
    laydate.render({
        elem: '#orderTime',
        format:'yyyy-MM-dd HH:mm:ss'
    });
    var $ = layui.$, active = {
        addOrderDetail: function(){ //添加订单明细
            $("#productCode").val("");
            $("#productNum").val("");
            $("#productName").val("");
            $("#productPrice").val("");
            var obj = $('#addOrderDetail');
            openForm("添加订单明细",true,false,true,['400px','400px'],obj);
        },
        saveOrder: function(){ //保存订单
            //先判断输入框是不是都写了，写了才能弹出框，没写的花就提示还有信息未填写
            var orderUserId = $("#orderUserId").val();
            var orderUsername = $("#orderUsername").val();
            var orderStatus = $("#orderStatus").val();
            var orderTime = $("#orderTime").val();
            var orderPrice = $("#orderPrice").val();
            var x = localStorage.getItem('ARR');
            if (x&&orderUserId !=""&&orderUsername!=""&&
                orderStatus!=""&&orderTime!=""){
                saveOrder(JSON.parse(x),orderUserId,orderStatus,orderTime);
            }
        },
        delOrderDetail: function(){ //删除订单明细
            var checkStatus = table.checkStatus('orderDetailTable')
                ,data = checkStatus.data;
            if (data.length > 0){
                layer.confirm('确定删除订单明细吗？',function (index) {
                    var arr = JSON.parse(localStorage.getItem('ARR'));
                    for (var i= 0;i<data.length;i++){
                        for (var j = 0; j < arr.length; j++) {
                            if (data[i].productCode == arr[j].productCode) {
                                arr.splice(j,1);
                            }
                        }
                    }
                    localStorage.setItem('ARR', JSON.stringify(arr));
                    layer.closeAll();
                    loadTable(arr)
                });
            }
            JSON.stringify(data);
        },
    };
    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    //添加表单明细提交一个商品
    form.on('submit(productSubmit)', function(data){
        if(localStorage.getItem('ARR')){
            array = JSON.parse(localStorage.getItem('ARR'))
        }else{
            var array = [];
        }
        array.push(data.field);
        localStorage.setItem('ARR', JSON.stringify(array));
        var data = JSON.parse(localStorage.getItem('ARR'));
        //orderDetail对象存到localStorage
        //重新加载table
        loadTable(data);
        layer.closeAll();
        return false;
    });
});
//检查是否存在该客户
function existUser(){
    var uid = $("#orderUserId").val();
    AsyncPost("/user/info",{"id":uid},function (data) {
        $("#orderUsername").val(data.realName);
    },function () {
        layer.msg("无此客户编号，请重新输入！");
        $("#orderUsername").val("");
    });
}
//检查是否存在该商品
function existProduct() {
    var pCode = $("#productCode").val();
    if (pCode!=null&&pCode!="")
    AsyncPost("/product/list",{"code":pCode},function (data) {
        var data = data.data;
        if (data.length > 0 &&data[0].status==1){
            $("#productName").val(data[0].name);
            $("#productPrice").val(data[0].price);
        }else{
            layer.msg("无此商品编号或商品已下架，请重新输入！");
            $("#productName").val("");
            $("#productPrice").val("");
        }
    },function () {
    });
}
function loadTable(data){
    tableIns=table.render({
        elem:"#orderDetailTable"
        ,data:data
        ,cols: [[
            {type:'checkbox'}
            , {field: 'productCode', title: '商品编号'}
            , {field: 'productName', title: '商品名称'}
            , {field: 'productNum', title: '数量'}
            , {field: 'productPrice', title: '单价'}
            , {field: 'productAPrice', title: '小计',templet: '#aPrice'}
        ]]
    });
}
function saveOrder(data,id,status,time) {
    layer.confirm('确定保存订单吗？',function () {
        //发起请求
        var a = [];
        var orderP=0;
        for (var i =0;i<data.length;i++){
            orderP += data[i].productNum*data[i].productPrice;
            a.push({'num':data[i].productNum,'piCode':data[i].productCode})
        }
        AsyncPut('/order/save', {'uid':id,'status':status,'orderTime':time,'orderPrice':orderP,'data':JSON.stringify(a)}, function (data) {
            layer.msg("保存成功!");
            localStorage.removeItem('ARR');
            layer.closeAll();
            loadTable([])
        }, function () {
            layer.msg("保存失败!");
        });
    })
}