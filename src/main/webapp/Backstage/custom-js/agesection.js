/**
 * Created by 14260 on 2018/6/11.
 */
$(document).ready(function(){

    var table = $("#age_table");
    table.dataTable({
        "columnDefs": [{ // set default column settings
            'orderable': false,
            'targets': [4]
        }, {
            "searchable": false,
            "targets": [4]
        }],
        "order": [
            [0, "asc"]
        ]
    });
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../age/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var itm = data[i];
                table.fnAddData([
                    itm.agesection,
                    itm.name,
                    itm.minage,
                    itm.maxage,
                    '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
                    '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                    '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
                ]);
            }
        },
        error: function (data) {
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
    $("#loading-age").css('display','none');


    /**
     * 添加
     */
    $("#btn_add_save").click(function(e){
        var delok = true;
        var params={};
        params.agesection = $('#age_add_age').val();
        params.name = $('#age_add_name').val();
        params.minage = parseInt($('#age_add_min').val());
        params.maxage = parseInt($('#age_add_max').val());

        if(params.agesection == '' || params.name == '' || params.minage == '' ||
            params.maxage == ''|| params.minage >= params.maxage){
            swal({
                title: "账号不能为空，且最大年龄的值应大于最小年龄的值！",
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
            url: "../age/add",          //注意路径
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
            $('#age_add_age').val(),
            $('#age_add_name').val(),
            $('#age_add_min').val(),
            $('#age_add_max').val(),
            '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
            '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
        ]);

        console.log($('#age_add_age').val());
        console.log($('#age_add_name').val());
        console.log($('#age_add_min').val());
        console.log($('#age_add_max').val());

        table.fnDraw();
        $('#age_add_age').val();
        $('#age_add_name').val();
        $('#age_add_min').val();
        $('#age_add_max').val();
        $('#age_add_modal').modal('hide')
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

        $('#age_edit_age').val(aData[0]);
        $('#age_edit_name').val(aData[1]);
        $('#age_edit_min').val(aData[2]);
        $('#age_edit_max').val(aData[3]);
        $('#age_edit_modal').modal('show')
    });


    $("#btn_edit_save").click(function(e){
        var nRow = EditRow;
        var delok = true;
        var params={};
        params.agesection = $('#age_edit_age').val();
        params.name =$('#age_edit_name').val();
        params.minage = parseInt($('#age_add_min').val());
        params.maxage = parseInt($('#age_add_max').val());

        console.log(params.agesection);
        console.log(params.name);
        console.log(params.minage);
        console.log(params.maxage);

        if(params.name == '' || params.minage=='' || params.maxage=='' || params.minage >= params.maxage){
            swal({
                title: "名称，年龄不能为空,且最大年龄的值应大于最小年龄的值！",
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
            url: "../age/update",       //注意路径
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

        table.fnUpdate($('#age_edit_age').val(), nRow, 0, false);
        table.fnUpdate($('#age_edit_name').val(), nRow, 1, false);
        table.fnUpdate($('#age_edit_min').val(), nRow, 2, false);
        table.fnUpdate($('#age_edit_max').val(), nRow, 3, false);
        table.fnDraw();
        $('#age_edit_age').val();
        $('#age_edit_name').val();
        $('#age_edit_min').val();
        $('#age_edit_max').val();
        $('#age_edit_modal').modal('hide')
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
            params.agesection = aData[0];

            $.ajax({
                async: false,
                type: "POST",
                url: "../age/delete",      //注意路径
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
