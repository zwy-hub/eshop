var pageCurr=1;
var tableIns;
var pageSize;
var form;
var table;

//传递参数异步请求数据
function getTableByParam() {
    var id = $("#id").val()*1;
    var username = $("#username").val();
    var status = $("#status").val();
    var timeFrom = $("#timeFrom").val().toString();
    var timeTo = $("#timeTo").val().toString();
    AsyncPost("/order/list", {"id":id,"ui.realName":username,"status":status,
            "orderTimeFrom":timeFrom,"orderTimeTo":timeTo},
        function (data) {
            initTableByData(data.data);
        },
        function () {
            layer.alert("系统错误");
        })
}
//请求到数据，再加载table
function initTableByData(obj){
    tableIns=table.render({
        elem:"#orderTable"
        ,data:obj
        ,page: true
        ,cols: [[
            {field: 'id', title: 'ID', width:80, fixed: 'left',sort: true}
            , {field: 'ui', title: '订单客户',templet: '#ui'}
            , {field: 'status', title: '订单状态'}
            , {field: 'orderTime', title: '订单时间',sort: true}
            , {field: 'orderPrice', title: '订单金额',sort: true}
            , {title: '操作', align:'center', fixed: 'right', width: 200,toolbar: '#barDemo'}
        ]]
        ,done: function(res, curr, count){
            pageCurr=curr;
        }
    });
}
layui.use(['table','form', 'layedit','laydate'], function () {
    form = layui.form;
    table = layui.table;
    var layer = layui.layer
        ,laydate = layui.laydate;
    form.render();
    laydate.render({
        elem: '#timeFrom',
        value:'1990-01-01'
    });
    laydate.render({
        elem: '#timeTo',
        value:'2099-12-31'
    });
    AsyncPost("/order/list",{"pageNum":pageCurr,"pageSize":10},function (data) {
        initTableByData(data.data);
    });
    //监听工具条
    table.on('tool(orderTable)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            //查看明细
            detail(data,data.id);
        } else  if(obj.event === 'del'){
            //删除订单
            del(data,data.id);
        }
    });
    //监听提交
    form.on('submit(search)', function(data){
        getTableByParam();
        return false;
    });
});
function detail(data,id) {
    $("#UName").val(data.ui.realName);
    $("#OStatus").val(data.status);
    $("#OOrderTime").val(data.orderTime);
    $("#OOrderPrice").val(data.orderPrice);

    AsyncPost("/order/detail",{"oid":id},function (data) {
        //生成table表格
        getOrderDetailTable(data.data);
        var obj = $('#setOrderDetail');
        //在这里打开layer
        openLayer('订单详细',true,false,true,['650px','500px'],obj);
    });
}
function getOrderDetailTable(obj) {
    tableIns=table.render({
        elem:"#orderDetailTable"
        ,data:obj
        // ,request:{
        //     pageName: 'curr' //页码的参数名称，默认：page
        //     ,limitName: 'nums' //每页数据量的参数名，默认：limit
        //     ,obj:obj
        // }
        ,cols: [[
            {field: 'pi', title: '商品名称',width:150,templet: '#piName'}
            , {field: 'pi', title: '单价',width:150,templet: '#piPrice'}
            , {field: 'num',width:150, title: '数量'}
            , {field: 'totalPrice', fixed: 'right',width:154, title: '小计'}
        ]]
    });
}
//删除
function del(obj,id) {
    if(null!=id){
        layer.confirm('确定删除订单吗', {
            btn: ['确认','返回'] //按钮
        }, function(){
            AsyncDelete("/order/del",{"id":id},function(res){
                layer.alert(res.message,function(){
                    console.log(res);
                    layer.closeAll();
                    load(obj);
                });
            },function () {
                layer.alert("删除失败,请稍后再试");
            });

        }, function(){
            layer.closeAll();
        });
    }
}
//重新加载table
function load(obj){
    AsyncPost("/order/list",{"pageNum":pageCurr,"pageSize":10},function (data) {
        initTableByData(data.data);
    })
}