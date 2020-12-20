var pageCurr=1;
var tableIns;
var pageSize;
var form;
var table;

//传递参数异步请求数据
function getTableByParam() {
    var code = $("#code").val();
    var name = $("#name").val();
    var tid = $("#tid").val();
    var brand = $("#brand").val();
    var priceFrom = $("#priceFrom").val()*1;
    var priceTo = $("#priceTo").val()*1;
    AsyncPost("/product/list", {"code":code,"name":name,"type.id":tid,
            "brand":brand,"priceFrom":priceFrom,"priceTo":priceTo},
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
        elem:"#productTable"
        ,data:obj
        ,page: true
        ,cols: [[
            {field: 'id', title: 'ID', width:80, fixed: 'left',sort: true}
            , {field: 'code', title: '商品编号',sort: true}
            , {field: 'name', title: '商品名称'}
            , {field: 'type', title: '商品类型',sort: true,templet: '#type'}
            , {field: 'status', title: '商品状态',templet: '#status'}
            , {field: 'brand', title: '品牌'}
            , {field: 'price', title: '价格'}
            , {field: 'num', title: '库存',sort: true}
            , {field: 'intro', title: '商品描述'}
            , {title: '操作', align:'center', fixed: 'right', width: 200,toolbar: '#barDemo'}
        ]]
        ,done: function(res, curr, count){
            pageCurr=curr;
        }
    });
}

layui.use(['table','upload'], function () {
    table = layui.table;
    upload = layui.upload;
    form = layui.form;//select、单选、复选等依赖form
    form.render();
    var uploadInst = upload.render({
        elem:'#test1'
        ,
    });
    AsyncPost("/product/list",{"pageNum":pageCurr,"pageSize":10},function (data) {
        initTableByData(data.data);
    });
    //监听工具条
    table.on('tool(productTable)', function(obj){
        var data = obj.data;
        if(obj.event === 'edit'){
            //编辑
            edit(data);
        } else  if(obj.event === 'del'){
            //删除
            del(data,data.id);
        }
    });
    //监听提交
    form.on('submit(search)', function(data){
        getTableByParam();
        return false;
    });
    form.on('submit(productSubmit)', function(data){
        formSubmit(data);
        return false;
    });
});

//提交表单
function formSubmit(obj){
    // $("#productForm").serialize(),
    var id = $("#id").val();
    if (id!="") id= $("#id").val()*1; else id=0;
    var code = $("#productCode").val();
    var name = $("#productName").val();
    var tid = $("#productType").val();
    var brand = $("#productBrand").val();
    var pic = $("#productPic").val();
    var num = $("#productNum").val()*1;
    var price = $("#productPrice").val();
    var intro = $("#productIntro").val();
    var status = $("#productStatus").val()*1;

    var type;
    type = $("#id").val()==""?"post":"put";

    AsyncAjax(type,"/product/set",{"id":id,"code":code,"name":name,"type.id":tid,
            "brand":brand,"pic":pic,"num":num,"price":price,"intro":intro,"status":status},
        function (data) {
        if (data.code == 200) {
            layer.alert(data.message,function(){
                layer.closeAll();
                load(obj);
            });
        } else {
            layer.alert(data.message);
        }
    }, function (data) {
        layer.alert("插入失败");
    });
}
//新增
function add() {
    edit(null,"新增");
}
//打开编辑框
function edit(data,title){
    if(data == null){
        $("#id").val("");
        $("#productStatus").val("2");
        $("#productType").val("0");
        $("#productName").val("");
        $("#productCode").val("");
        $("#productBrand").val("");
        $("#productNum").val("");
        $("#productPrice").val("");
        $("#productIntro").val("");
        $("#productPic").val("");
    }else{
        //回显数据
        $("#id").val(data.id);
        $("#productStatus").val(data.status);
        $("#productType").val(data.type.id);
        $("#productName").val(data.name);
        $("#productCode").val(data.code);
        $("#productBrand").val(data.brand);
        $("#productNum").val(data.num);
        $("#productPrice").val(data.price);
        $("#productIntro").val(data.intro);
        $("#productPic").val(data.pic);
    }
    form.render();
    var obj = $('#setProduct');
    openForm(title,true,false,true,['400px','500px'],obj);
}
//删除
function del(obj,id) {
    if(null!=id){
        layer.confirm('商品可能与订单关联，因此无法强制强制删除，是否要下架该商品？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            AsyncDelete("/product/del",{"id":id},function(res){
                layer.alert(res.message,function(){
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
    AsyncPost("/product/list",{"pageNum":pageCurr,"pageSize":10},function (data) {
        initTableByData(data.data);
    })
}