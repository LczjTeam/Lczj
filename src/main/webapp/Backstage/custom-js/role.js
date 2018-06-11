/**
 * Created by 14260 on 2018/6/11.
 */
$(document).ready(function(){

    var table = $("#role_table");
    table.dataTable({
        "columnDefs": [{ // set default column settings
            'orderable': false,
            'targets': [2]
        }, {
            "searchable": false,
            "targets": [2]
        }],
        "order": [
            [0, "asc"]
        ]
    });
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../role/list",//注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                console.log(JSON.stringify(data[i],null,4));
                console.log(data[i].role);
                console.log(data[i].name);
                var itm = data[i];
                table.fnAddData([
                    itm.role,
                    itm.name,
                    '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
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

    /**
     * 添加
     */
    $("#btn_add_save").click(function(e){
        var delok = true;
        var params={};
        params.role = $('#role_add_role').val();
        params.name =$('#role_add_name').val();
        $.ajax({
            async: false,
            type: "POST",
            url: "../role/add",//注意路径
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
            $('#role_add_role').val(),
            $('#role_add_name').val(),
            '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
        ]);
        table.fnDraw();
        $('#role_add_role').val('');
        $('#role_add_name').val('');
        $('#role_add_modal').modal('hide')
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
        $('#role_edit_role').val(aData[0]);
        $('#role_edit_name').val(aData[1]);
        $('#role_edit_modal').modal('show')

    });


    $("#btn_edit_save").click(function(e){
        var nRow = EditRow;
        var delok = true;
        var params={};
        params.role = $('#role_edit_role').val();
        params.name =$('#role_edit_name').val();
        $.ajax({
            async: false,
            type: "POST",
            url: "../role/update",//注意路径
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
        table.fnUpdate($('#role_edit_role').val(), nRow, 0, false);
        table.fnUpdate($('#role_edit_name').val(), nRow, 1, false);
        table.fnDraw();
        $('#role_edit_role').val('');
        $('#role_edit_name').val('');
        $('#role_edit_modal').modal('hide')
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
            params.role = aData[0];
            $.ajax({
                async: false,
                type: "POST",
                url: "../role/delete",//注意路径
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
});
