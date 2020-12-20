layui.use(['element','jquery'], function(){
    var element = layui.element;
    var $ = layui.jquery;
    //定义事件
    $("a").click(function () {
        var url = $(this).data("url");
        if (url!=null)
            $("#content").load(url);
    })
});