/**
 * Created by 14260 on 2018/6/14.
 */
$(document).ready(function () {

    //工具
    $("#btn_add").click(function(e){


        $("#munu_title").val('');
        $("#title").val('');
        $("#url").val('');
        $("#method").val('');
        $("#sort_no").val('');
        $("#css").val('');
        $("#menu_title").text('菜单信息添加');
        $("#munu_title").removeAttr('readonly');

        $("#btn_add_menu").css('visibility','visible');
        $("#btn_alter_menu").css('visibility','hidden');
        $("#parents1").removeAttr('disabled');

    });


    //右侧选择根目录
    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: "../menu/roots",//注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data, null, 4));
            var str ='<option value="0">根目录</option>';
            for (var i = 0; i < data.length; i++) {
                str+='<option value="'+data[i].menu+'"> '+data[i].title+'  </option>'
            }
            $("#parents1").html(str);
        },
        error: function (data) {
            console.log(JSON.stringify(data, null, 4));
            swal({
                title: "数据获取失败！",
                text: "",
                type: "error",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "OK",
            });
        }
    });
    //左侧
    var dataTank = [];//创建容器
    refreshTree();
    function  refreshTree() {

        dataTank = [];
        var params = {};

        $.ajax({
            async: false,
            type: "POST",
            url: "../menu/list",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {

                console.log(JSON.stringify(data, null, 4));
                var oldData = data;//获取Json数据
                for (var i = 0; i < oldData.length; i++) { //提取所需值组成新数组
                    var item = {};
                    var itm = oldData[i];
                    item.id = itm.menu.menu;
                    item.text = itm.menu.title;
                    item.children = [];

                    for (var k = 0; k < itm.menus.length; k++) {
                        var itms = itm.menus[k];
                        var curt = {};
                        curt.id = itms.menu;
                        curt.text = itms.title;
                        curt.icon = 'glyphicon glyphicon-leaf';
                        item.children.push(curt);
                    }
                    dataTank.push(item);
                }
            },
            error: function (data) {
                console.log(JSON.stringify(data, null, 4));
                //alert("数据获取失败 ！");
                swal({
                    title: "数据获取失败！",
                    text: "",
                    type: "error",
                    allowOutsideClick: true,
                    showConfirmButton: true,
                    showCancelButton: false,
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "OK",
                });
            }
        });

    }
    //初始化树
    $('#using_json').data('jstree', false);
    $("#loading-menu").css('display', 'none');
    $("#using_json").jstree({

        'plugins': ["wholerow", "types"],
        'core': {
            "themes": {
                "responsive": false
            },
            'data': dataTank
        },
        "types": {
            "default": {
                "icon": "glyphicon glyphicon-th-large "
            },
            "file": {
                "icon": "fa fa-file "
            }
        }

    });

    $("#btn_add_menu").css('visibility','hidden');
    $("#btn_alter_menu").css('visibility','visible');
    //点击事件
    var curtId = '';
    $('#using_json').on("changed.jstree", function (e, data) {


        $("#menu_title").text('菜单信息修改');
        $("#btn_add_menu").css('visibility','hidden');
        $("#btn_alter_menu").css('visibility','visible');
        $("#munu_title").attr('readonly','readonly');
        var  id= data.selected;
        curtId = id;
        if(id=='') return;
        console.log(id);
        var params={};
        params.menu = ''+id ;
        $.ajax({
            async: false,
            type: "POST",
            url: "../menu/loadById",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                console.log(JSON.stringify(data,null,4));
                var showdata = data;

                $("#munu_title").val(showdata.menu);
                $("#title").val(showdata.title);
                $("#url").val(showdata.url);
                if(showdata.parents=='0'){
                    $("#parents1").attr('disabled','true');
                }else{
                    $("#parents1").removeAttr('disabled');
                }
                $("#parents1").val(showdata.parents);
                $("#method").val(showdata.method);
                $("#sort_no").val(showdata.sort_no);
                $("#css").val(showdata.css);
                $("#visible").val(showdata.visible);

            }, error: function (data) {
                console.log(JSON.stringify(data,null,4));
                //alert("数据获取失败 ！");
                swal({
                    title: "数据获取失败！",
                    text: "",
                    type: "error",
                    allowOutsideClick: true,
                    showConfirmButton: true,
                    showCancelButton: false,
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "OK",
                });
            }
        });
    });

    //刷新树【当data发生改变时】
    function refresh(){
        refreshTree();
        var tree = $('#using_json');
        tree.jstree(true).settings.core.data = dataTank;
        tree.jstree(true).refresh(); //刷新树

    }

    //提交按钮，进行修改
    $("#btn_alter_menu").click(function(e){
        var delok = true;
        var params={};
        params.menu = $("#munu_title").val();
        params.title =$('#title').val();
        params.parents =$('#parents1').val();
        params.url =$('#url').val();
        params.method =$('#method').val();
        params.sort_no =$('#sort_no').val();
        params.visible =$('#visible').val();
        params.css =$('#css').val();

        if(params.menu =='' || params.title  == '' || params.sort_no  == '' ){
            swal({
                title: "编号、名称、序号不能为空！",
                text: "",
                type: "warning",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "OK",
            });
            return;
        }

        $.ajax({
            async: false,
            type: "POST",
            url: "../menu/update",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                if (data) {

                    return;
                } else {
                    delok = false;
                }
            },
            error: function (data) {
                console.log(JSON.stringify(data,null,4));

                delok = false;
            }
        });
        if (!delok) {

            swal({
                title: "保存失败！",
                text: "",
                type: "error",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "OK",
            });
            return;
        }
        refresh();

        swal({
            title: "保存成功！",
            text: "",
            type: "success",
            allowOutsideClick: true,
            showConfirmButton: true,
            showCancelButton: false,
            confirmButtonClass: "btn-success",
            confirmButtonText: "OK",
        });


    });


    //提交按钮，进行添加
    $("#btn_add_menu").click(function(e){
        var delok = true;
        var params={};
        params.menu = $("#munu_title").val();
        params.title =$('#title').val();
        params.parents =$('#parents1').val();
        params.url =$('#url').val();
        params.method =$('#method').val();
        params.sort_no =$('#sort_no').val();
        params.visible =$('#visible').val();
        params.css =$('#css').val();

        if(params.menu =='' || params.title  == '' || params.sort_no  == '' ){
            swal({
                title: "编号、名称、序号不能为空！",
                text: "",
                type: "warning",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "OK",
            });
            return;
        }


        $.ajax({
            async: false,
            type: "POST",
            url: "../menu/add",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                if (data) {

                    return;
                } else {
                    delok = false;
                }
            },
            error: function (data)
            {
                console.log(JSON.stringify(data,null,4));
                delok = false;
            }
        });
        if (!delok) {
            swal({
                title: "添加失败！",
                text: "",
                type: "error",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "OK",
            });
            return;
        }
        refresh();
        $("#munu_title").val('');
        $("#title").val('');
        $("#url").val('');
        $("#method").val('');
        $("#sort_no").val('');
        $("#css").val('');
        swal({
            title: "添加成功！",
            text: "",
            type: "success",
            allowOutsideClick: true,
            showConfirmButton: true,
            showCancelButton: false,
            confirmButtonClass: "btn-success",
            confirmButtonText: "OK",
        });


    });


    /**
     * 删除
     */
    $("#btn_del").click(function(e){

        if(curtId==''){
            swal({
                title: "请先选择要删除的菜单项！",
                text: "",
                type: "warning",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "OK",
            });
            return;
        }

        swal({
            title: "确认删除这一行数据 ?",
            text: "",
            type: "warning",
            allowOutsideClick: true,
            showConfirmButton: true,
            showCancelButton: true,
            confirmButtonClass: "btn-info",
            cancelButtonClass: "btn-danger",
            confirmButtonText: "确认",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function (isConfirm) {
            if (!isConfirm) return;
            var delok = true;
            var params={};
            params.menu = ''+curtId;
            $.ajax({
                async: false,
                type: "POST",
                url: "../menu/delete",//注意路径
                data: params,
                dataType: "json",
                success: function (data) {
                    if (data) {

                        return;
                    } else {
                        delok = false;
                    }
                },
                error: function (data)
                {
                    console.log(JSON.stringify(data,null,4));
                    delok = false;
                }
            });
            if (!delok) {
                swal({
                    title: "删除失败！",
                    text: "",
                    type: "error",
                    allowOutsideClick: true,
                    showConfirmButton: true,
                    showCancelButton: false,
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "OK",
                });
                return;
            }
            refresh();
            curtId = '';
            $("#munu_title").val('');
            $("#title").val('');
            $("#url").val('');
            $("#method").val('');
            $("#sort_no").val('');
            $("#css").val('');
            swal({
                title: "删除成功！",
                text: "",
                type: "success",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-success",
                confirmButtonText: "OK",
            });
        });
    });


});
