var pageCurr=1;
var tableIns;
var pageSize;
var form;
var table;

layui.use(['layer','table','form', 'layedit'], function () {
    table = layui.table;
    form = layui.form;
    form.render();
    //监听表格复选框选择
    table.on('checkbox(userTable)');
    AsyncPost("/user/list",{"pageNum":pageCurr,"pageSize":10},function (data) {
        initTableByData(data.data);
    });
    var $ = layui.$, active = {
        enableUser :function () {
            var checkStatus = table.checkStatus('userTable')
                ,data = checkStatus.data;
            if (data.length > 0){
                changeStatus(data,1);
            }
        },
        disableUser :function () {
            var checkStatus = table.checkStatus('userTable')
                ,data = checkStatus.data;
            if (data.length > 0){
                changeStatus(data,0);
            }
        }
    };
    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    //监听提交
    form.on('submit(search)', function(data){
        getTableByParam();
        return false;
    });
});

function getTableByParam() {
    var username = $("#username").val();
    AsyncPost("/user/list", {"username":username},
        function (data) {
            initTableByData(data.data);
        },
        function () {
            layer.alert("系统错误");
        })
}

function initTableByData(obj) {
    tableIns=table.render({
        elem:"#userTable"
        ,data:obj
        ,page: true
        ,cols: [[
            {type:'checkbox'}
            , {field: 'id', title: 'ID', width:80,sort: true}
            , {field: 'username', title: '登录名'}
            , {field: 'realName', title: '真实姓名'}
            , {field: 'sex', title: '性别',sort: true}
            , {field: 'address', title: '家庭住址',}
            , {field: 'email', title: '邮箱',width:220}
            , {field: 'regDate', title: '注册日期'}
            , {field: 'status', title: '客户状态', fixed: 'right',templet: '#status'}
        ]]
        ,done: function(res, curr, count){
            pageCurr=curr;
        }
    });
}

function changeStatus(data,status) {
    layer.confirm('确定修改用户状态吗？',function () {
        //发起请求
        var a=[];
        for (var i = 0; i < data.length; i++) {
            a.push(data[i].id);
        }
        AsyncPut('/user/changeStatus', {'ids':a.toString(),'status':status}, function (data) {
            layer.closeAll();
            load();
            layer.msg(data.message);
        }, function () {
            layer.msg("保存失败!");
        });
    })
}

//重新加载table
function load(){
    AsyncPost("/user/list",{"pageNum":pageCurr,"pageSize":10},function (data) {
        initTableByData(data.data);
    })
}