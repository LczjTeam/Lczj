/**
 * Created by 25778 on 2018/7/15.
 */
$(document).ready(function(){

    var table = $("#shops_table");
    table.dataTable({
        "columnDefs": [{ // set default column settings
            'orderable': false,
            'targets': [7]
        }, {
            "searchable": false,
            "targets": [7]
        }],
        "order": [
            [0, "asc"]
        ]
    });
    var params={};

    $.ajax({
        async: false,
        type: "POST",
        url: "../shops/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                // console.log(JSON.stringify(data[i],null,4));
                // console.log(data[i].shop);
                // console.log(data[i].name);
                // console.log(data[i].address);
                // console.log(data[i].phone);
                // console.log(data[i].pos_x);
                // console.log(data[i].pos_y);
                // console.log(data[i].orders);
                var itm = data[i];
                table.fnAddData([
                    itm.shop,
                    itm.name,
                    itm.address,
                    itm.phone,
                    itm.pos_x,
                    itm.pos_y,
                    itm.orders,
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
    $("#loading-shops").css('display','none');

    /**
     * 添加
     */
    $("#btn_add_save").click(function(e){
        var delok = true;
        var params={};

        params.shop = $('#shops_add_shops').val();
        params.name =$('#shops_add_name').val();
        params.address =$('#shops_add_address').val();
        params.phone = $('#shops_add_phone').val();
        params.pos_x = $('#shops_add_pos_x').val();
        params.pos_y = $('#shops_add_pos_y').val();
        params.orders = $('#shops_add_orders').val();

        console.log($('#shops_add_shops').val());
        console.log($('#shops_add_name').val());
        console.log($('#shops_add_address').val());
        console.log($('#shops_add_phone').val());
        console.log($('#shops_add_pos_x').val());
        console.log($('#shops_add_pos_y').val());
        console.log($('#shops_add_orders').val());


        if(params.shop == ''|| params.name == ''|| params.address == '' || params.phone=='' || params.pos_x==''||params.pos_y==''||params.orders==''){
            swal({
                title: "门店信息不能有空值！",
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
            url: "../shops/add",//注意路径
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
            $('#shops_add_shops').val(),
            $('#shops_add_name').val(),
            $('#shops_add_address').val(),
            $('#shops_add_phone').val(),
            $('#shops_add_pos_x').val(),
            $('#shops_add_pos_y').val(),
            $('#shops_add_orders').val(),

            '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
            '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
        ]);

        table.fnDraw();
        $('#shops_add_shops').val(),
        $('#shops_add_name').val(),
        $('#shops_add_address').val(),
        $('#shops_add_phone').val(),
        $('#shops_add_pos_x').val(),
        $('#shops_add_pos_y').val(),
        $('#shops_add_orders').val(),
        // $('#shops_add_company').option($('#shops_add_company').selectedIndex).text();

        $('#shops_add_modal').modal('hide')
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

        $('#shops_edit_shops').val(aData[0]);
        $('#shops_edit_name').val(aData[1]);
        $('#shops_edit_address').val(aData[2]);
        $('#shops_edit_phone').val(aData[3]);
        $('#shops_edit_pos_x').val(aData[4]);
        $('#shops_edit_pos_y').val(aData[5]);
        $('#shops_edit_orders').val(aData[6]);

        $('#shops_edit_modal').modal('show')
    });


    $("#btn_edit_save").click(function(e){
        var nRow = EditRow;
        var delok = true;
        var params={};

        params.shop = $('#shops_edit_shops').val();
        params.name =$('#shops_edit_name').val();
        params.address =$('#shops_edit_address').val();
        params.phone = $('#shops_edit_phone').val();
        params.pos_x = $('#shops_edit_pos_x').val();
        params.pos_y = $('#shops_edit_pos_y').val();
        params.orders = $('#shops_edit_orders').val();

        console.log(JSON.stringify(params,null,4));

        if(params.name == ''|| params.address == '' || params.phone=='' || params.pos_x==''||params.pos_y==''||params.orders==''){
            swal({
                title: "门店信息不能有空值！",
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
            url: "../shops/update",//注意路径
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

        table.fnUpdate($('#shops_edit_shops').val(), nRow, 0, false);
        table.fnUpdate($('#shops_edit_name').val(), nRow, 1, false);
        table.fnUpdate($('#shops_edit_address').val(), nRow, 2, false);
        table.fnUpdate($('#shops_edit_phone').val(), nRow, 3, false);
        table.fnUpdate($('#shops_edit_pos_x').val(), nRow, 4, false);
        table.fnUpdate($('#shops_edit_pos_y').val(), nRow, 5, false);
        table.fnUpdate($('#shops_edit_orders').val(), nRow, 6, false);

        table.fnDraw();

        $('#shops_edit_shops').val(),
        $('#shops_edit_name').val(),
        $('#shops_edit_address').val(),
        $('#shops_edit_phone').val(),
        $('#shops_edit_pos_x').val(),
        $('#shops_edit_pos_y').val(),
        $('#shops_edit_orders').val(),

        $('#shops_edit_modal').modal('hide')
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
            params.shop = aData[0];
            $.ajax({
                async: false,
                type: "POST",
                url: "../shops/delete",//注意路径
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

