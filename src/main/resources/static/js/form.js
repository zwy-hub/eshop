$(function(){
    layui.use(['form' ,'layer','tree'], function() {
        var layer = layui.layer;
        var tree = layui.tree;
    })
})
/*打开form表单*/
function openForm(title,fixed,resize,shadeClose,area,obj) {
    layer.open({
        type:1,
        title: title,
        fixed:fixed,
        resize :resize,
        shadeClose: shadeClose,
        area: area,
        content:obj,
        success:function(layero){
            //此处，会弹出一个遮罩层（还不知道原因）所以先把他hide掉(我暂时没找到更好的方法)。
            var mask = $(".layui-layer-shade");
            mask.hide();
        },
        end:function(){
            obj.css("display",'none');
            layer.closeAll();
        }
    });
}
function openLayer(title,fixed,resize,shadeClose,area,obj) {
    layer.open({
        type:1,
        title: title,
        fixed:fixed,
        resize :resize,
        shadeClose: shadeClose,
        area: area,
        content:obj,
        success:function(layero){
            //此处，会弹出一个遮罩层（还不知道原因）所以先把他hide掉(我暂时没找到更好的方法)。
            var mask = $(".layui-layer-shade");
            mask.hide();
        },
        end:function(){
            obj.css("display",'none');
            layer.closeAll();
        }
    });
}