var pageCurr=1;
var tableIns;
var pageSize;
var form;
var table;
var tree;

layui.use(['element','layer','table','form', 'tree', 'layedit'], function () {
    table = layui.table;
    form = layui.form;
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    form.render();
    tree = layui.tree;
    let height = document.documentElement.clientHeight - 160;

    loadTable();

    table.on('toolbar(adminTable)',function (obj) {
        switch (obj.event) {
            case 'add':
                $("#id").val("");
                $("#name").val("");
                $("input[name='name']").removeAttr("readonly");
                loadMenuTree([]);
                layer.msg("请在右边表单进行编辑并保存");
                break;
            case 'search':
                let adminName = $("#adminName").val();
                let query = {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , done: function (res, curr, count) {
                        //完成后重置where，解决下一次请求携带旧数据
                        // this.where = {};
                    }
                };
                if (!adminName) {
                    adminName = "";
                }
                //设定异步数据接口的额外参数
                query.where = {name: adminName};
                tableIns.reload(query);
                $("#adminName").val(adminName);
                break;
        }
    });
    table.on('tool(adminTable)',function (obj) {
        let data = obj.data;
        if (obj.event === 'edit'){
            // console.log(data);
            $("#id").val(data.id);
            $("#name").val(data.name);
            $("input[name='name']").attr("readonly","readonly");
            form.render();
            loadMenuTree(data.fs);
        } else if (obj.event === 'del'){
            layer.msg("edit"+data.id);
            layer.confirm('确认删除吗？', function (index) {
                //向服务端发送删除指令
                $.ajax({
                    url: '/admin/del/'+ data.id,
                    type: 'DELETE',
                    success: function(result) {
                        // Do something with the result
                        tableIns.reload();
                        layer.msg(result.message);
                        layer.close(index);
                    }
                });
            });
        }
    });
});

//保存表单
function adminFormSave() {
    var id = $("#id").val()*1;
    var name = $("#name").val();
    //存放分配的权限id
    let menuIdList = [];
    if (tree.getChecked('adminMenuTree')[0]){
        for (let check of tree.getChecked('adminMenuTree')[0].children) {
            menuIdList.push(check.id);
            for (let i=0; i < check.children.length; i++){
                menuIdList.push(check.children[i].id);
            }
        }
    }
    AsyncPost("/admin/save",{"id":id,"name":name,"functions":menuIdList.toString()},
        function (data) {
        layer.msg(data.message);
        loadTable();
        //需要重新加载table
    },function () {
        layer.msg("操作失败，请稍后再试！");
    });
}

//菜单树
function loadMenuTree(arr) {
    let treeData;
    $.ajax({
        url: '/admin/tree',
        type: 'post',
        success: function(data) {
            //全菜单
            treeData = treeUtil.updateKeyForLayuiTree(data);
            //再根据传递的obj勾选相应的菜单功能
            treeData = treeUtil.checkedForLayuiTree(treeData,arr);
            tree.render({
                elem: '#adminMenuTree'
                , id: 'adminMenuTree'
                , data: [{
                    title: '系统菜单根节点'
                    , id: 0
                    , spread: true
                    , children: treeData
                }]
                , showCheckbox: true
            });
        }
    })
}

//重置密码
function resetPassword() {
    let id = $("#id").val();
    if (id === "") {
        layer.msg("新增用户无需点这个按钮，如需重置请先选择需要重置的用户！", {icon: 2, time: 2000}, function () {
        });
        return;
    }
    layer.confirm('确认重置该用户的密码吗？', function (index) {
        $.ajax({
            url: '/admin/resetPassword/'+ id,
            type: 'put',
            success: function(result) {
                layer.msg("密码重置成功，请尽快通知用户登录系统修改密码！", {icon: 1, time: 2000}, function () {});
                layer.close(index);
            }
        });
    });
}

//菜单工具
treeUtil = {
    updateKeyForLayuiTree: function (data) {
        let newArray = [];
        for (let i = 0; i < data.length; i++) {//放父节点进树里面
            let obj = {};
            if (data[i].parentId === 0) {
                obj.id =  data[i].id;
                obj.title = data[i].name;
                obj.spread=true;
                obj.href = data[i].url;
                obj.children = [];
                newArray.push(obj);
            }
        }
        for (let i = 0; i < data.length; i++) {//再把子节点添加到父节点下面
            let obj = {};
            if (data[i].parentId > 0) {
                obj.id =  data[i].id;
                obj.title = data[i].name;
                obj.href = data[i].url;
                for (let j = 0; j < newArray.length; j++){
                    if (data[i].parentId===newArray[j].id){
                        newArray[j].children.push(obj);
                    }
                }
            }
        }
        return newArray;
    },
    checkedForLayuiTree: function (data,adminData) {
        for (let i = 0; i < adminData.length; i++) {
            if (adminData[i].parentId > 0) {
                for (let j = 0; j < data.length; j++) {
                    for (let k = 0; k < data[j].children.length; k++) {
                        if (adminData[i].id === data[j].children[k].id)
                            data[j].children[k].checked = true;
                    }
                }
            }
        }
        return data;
    }
};

//加载table
function loadTable() {
    tableIns= table.render({
        elem: '#adminTable'
        , url:'/admin/list'
        , method: 'POST'
        , toolbar: '#adminTableToolbarDemo'
        , title: '管理员列表'
        , cols: [[
            {field: 'id', title: 'ID', hide: true}
            , {field: 'name', title: '登录名'}
            , {field: 'pwd', title: '密码'}
            , {fixed: 'right', title: '操作', toolbar: '#adminTableBarDemo'}
        ]]
        , defaultToolbar: ['', '', '']
        , page: true
    });
}
