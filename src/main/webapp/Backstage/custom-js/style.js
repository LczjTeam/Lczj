/**
 * Created by 25778 on 2018/6/18.
 */
$(document).ready(function(){

    var table = $("#style_table");
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
        url: "../style/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                console.log(JSON.stringify(data[i],null,3));
                console.log(data[i].style);
                console.log(data[i].name)
                var itm = data[i];
                table.fnAddData([
                    itm.style,
                    itm.name,
                    '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                    '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
                ]);
            }
        },
        error: function (data) {
            //  console.log(JSON.stringify(data,null,4));
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
    $("#loading-style").css('display','none');


    /**
     * 添加
     */
    $("#btn_add_save").click(function(e){
        var delok = true;
        var params={};
        params.style = $('#style_add_style').val();
        params.name =$('#style_add_name').val();
        console.log(JSON.stringify(params,null,2));
        if(params.style == ''|| params.name == ''){
            swal({
                title: "编号、名称不能为空！",
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
            url: "../style/add",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                console.log(JSON.stringify(data,null,2));
                delok = data;
            },
            error: function (data) {
                console.log(JSON.stringify(data,null,2));
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
            $('#style_add_style').val(),
            $('#style_add_name').val(),
            '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
            '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
        ]);
        table.fnDraw();
        $('#style_add_style').val('');
        $('#style_add_name').val('');
        $('#style_add_modal').modal('hide')
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

        $('#style_edit_style').val(aData[0]);
        $('#style_edit_name').val(aData[1]);
        $('#style_edit_modal').modal('show')
    });


    $("#btn_edit_save").click(function(e){
        var nRow = EditRow;
        var delok = true;
        var params={};
        params.style = $('#style_edit_style').val();
        params.name =$('#style_edit_name').val();

        console.log(JSON.stringify(params,null,2));
        if(params.style == ''||params.name == ''){

            swal({
                title: "设计样式编号、名称不能为空！",
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
            url: "../style/update",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                console.log(JSON.stringify(data,null,2));
                delok = data;
            },
            error: function (data) {
                delok = false;
            }
        });
        if (!delok) {
            console.log(JSON.stringify(data,null,2));
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

        table.fnUpdate($('#style_edit_style').val(), nRow, 0, false);
        table.fnUpdate($('#style_edit_name').val(), nRow, 1, false);
        table.fnDraw();
        $('#style_edit_style').val('');
        $('#style_edit_name').val('');
        $('#style_edit_modal').modal('hide')
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
            params.style = aData[0];
            $.ajax({
                async: false,
                type: "POST",
                url: "../style/delete",//注意路径
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
                    title: "删除失败",
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
