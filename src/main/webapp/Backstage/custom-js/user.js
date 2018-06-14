/**
 * Created by 14260 on 2018/6/11.
 */
$(document).ready(function(){

    var table = $("#user_table");
    table.dataTable({
        "columnDefs": [{ // set default column settings
            'orderable': false,
            'targets': [3]
        }, {
            "searchable": false,
            "targets": [3]
        }],
        "order": [
            [0, "asc"]
        ]
    });
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../admin/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                console.log(JSON.stringify(data[i],null,4));
                console.log(data[i].admin);
                console.log(data[i].name)
                console.log(data[i].isvalid);
                var itm = data[i];
                table.fnAddData([
                    itm.admin,
                    itm.name,
                    itm.isvalid == '1' ? '有效':'无效',
                    '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                    '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'+
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                    '<a class="pwd" ><i class="fa fa-edit"></i>&nbsp;密码重置</a>'
                ]);
            }
        },
        error: function (data) {
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
    $("#loading-user").css('display','none');


    /**
     * 添加
     */
    $("#btn_add_save").click(function(e){
        var delok = true;
        var params={};
        params.admin = $('#user_add_user').val();
        params.name =$('#user_add_name').val();
        params.isvalid = '1';
        $.ajax({
            async: false,
            type: "POST",
            url: "../admin/add",//注意路径
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
        table.fnAddData([
            $('#user_add_user').val(),
            $('#user_add_name').val(),
            '有效',
            '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
        ]);
        table.fnDraw();
        $('#user_add_user').val('');
        $('#user_add_name').val('');
        $('#user_add_modal').modal('hide')
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
     * 编辑
     */
    var  EditRow = -1;
    table.on('click', '.edit', function (e) {
        e.preventDefault();

        var nRow = $(this).parents('tr')[0];
        EditRow = nRow;
        var aData = table.fnGetData(nRow);
        //alert(aData[0]);
        $('#user_edit_user').val(aData[0]);
        $('#user_edit_name').val(aData[1]);
        console.log((aData[2]=='有效'? '1':'0'));
        $('#user_edit_isvalid').val((aData[2]=='有效'? '1':'0'));
        $('#user_edit_modal').modal('show')
    });


    $("#btn_edit_save").click(function(e){
        var nRow = EditRow;
        var delok = true;
        var params={};
        var validValue = $('#user_edit_isvalid').val();
        params.admin = $('#user_edit_user').val();
        params.name =$('#user_edit_name').val();
        params.isvalid = validValue;
        $.ajax({
            async: false,
            type: "POST",
            url: "../admin/update1",//注意路径
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

        table.fnUpdate($('#user_edit_user').val(), nRow, 0, false);
        table.fnUpdate($('#user_edit_name').val(), nRow, 1, false);
        table.fnUpdate(validValue == 1 ? '有效':'无效', nRow, 2, false);
        table.fnDraw();
        $('#user_edit_user').val('');
        $('#user_edit_name').val('');
        validValue == 1 ? '有效':'无效';
        $('#user_edit_modal').modal('hide')
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


    /**
     * 删除
     */
    table.on('click', '.delete', function (e) {
        e.preventDefault();
        var nRow = $(this).parents('tr')[0];
        var aData = table.fnGetData(nRow);
        //alert("delete:"+aData[0]);

        console.log(aData[0]);
        console.log(nRow);

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
            params.admin = aData[0];
            $.ajax({
                async: false,
                type: "POST",
                url: "../admin/delete",//注意路径
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
                    delok = false;
                }
            });
            if (!delok){
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
            table.fnDeleteRow(nRow);
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


    /**
     * 密码重置
     */

    table.on('click', '.pwd', function (e) {
        e.preventDefault();

        var nRow = $(this).parents('tr')[0];
        var aData = table.fnGetData(nRow);

        swal({
            title: "确认重置密码为123456 ?",
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
            params.admin = aData[0];
            $.ajax({
                async: false,
                type: "POST",
                url: "../admin/resetpwd",//注意路径
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
                    delok = false;
                }
            });

            if (!delok){
                swal({
                    title: "密码重置失败！",
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
            swal({
                title: "密码重置成功！",
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


    $("#btn_edit_save").click(function(e){
        var nRow = EditRow;
        var delok = true;
        var params={};
        var validValue = $('#user_edit_isvalid').val();
        params.admin = $('#user_edit_user').val();
        params.name =$('#user_edit_name').val();
        params.isvalid = validValue;
        $.ajax({
            async: false,
            type: "POST",
            url: "../admin/update1",//注意路径
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

        table.fnUpdate($('#user_edit_user').val(), nRow, 0, false);
        table.fnUpdate($('#user_edit_name').val(), nRow, 1, false);
        table.fnUpdate(validValue == 1 ? '有效':'无效', nRow, 2, false);
        table.fnDraw();
        $('#user_edit_user').val('');
        $('#user_edit_name').val('');
        validValue == 1 ? '有效':'无效';
        $('#user_edit_modal').modal('hide')
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
});
