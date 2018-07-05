/**
 * Created by 25778 on 2018/6/18.
 */
$(document).ready(function(){

    var table = $("#brand_table");
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
        url: "../brand/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                console.log(JSON.stringify(data[i],null,4));
                console.log(data[i].brand);
                console.log(data[i].name);
                console.log(data[i].company);
                console.log(data[i].type);
                var itm = data[i];
                table.fnAddData([
                    itm.brand,
                    itm.name,
                    itm.company,
                    typeIntToStr(itm.type),
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
    $("#loading-brand").css('display','none');

    //type,int类型转string类型
    function typeIntToStr(int_type) {
        if (int_type==0){
            return '通用品牌'
        }
        else if(int_type==1){
            return '镜框品牌'
        }
        else if (int_type==2){
            return '镜片品牌'
        }
    }

    function typeStrToInt(str_type) {
        if (str_type=='通用品牌'){
            return 0;
        }
        else if(str_type=='镜框品牌'){
            return 1;
        }
        else if (str_type=='镜片品牌'){
            return 2;
        }
    }

    /**
     * 添加
     */
    $("#btn_add_save").click(function(e){
        var delok = true;
        var params={};
        params.brand = $('#brand_add_brand').val();
        params.name =$('#brand_add_name').val();
        params.company =$('#brand_add_company').val();
        params.type = $('#brand_add_type').val();
        if(params.brand == ''|| params.name == ''|| params.company == ''){
            swal({
                title: "编号、名称和制造商不能为空！",
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
            url: "../brand/add",//注意路径
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
            $('#brand_add_brand').val(),
            $('#brand_add_name').val(),
            $('#brand_add_company').val(),
            typeIntToStr($('#brand_add_type').val()),
            // $('#brand_add_company').option($('#brand_add_company').selectedIndex).text();
            '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>' +
            '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
            '<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
        ]);
        table.fnDraw();
        $('#brand_add_brand').val('');
        $('#brand_add_name').val('');
        $('#brand_add_company').val('');
        typeIntToStr($('#brand_add_type').val()),
        // $('#brand_add_company').option($('#brand_add_company').selectedIndex).text();

        $('#brand_add_modal').modal('hide')
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

        $('#brand_edit_brand').val(aData[0]);
        $('#brand_edit_name').val(aData[1]);
        $('#brand_edit_company').val(aData[2]);
        $('#brand_edit_type').val(typeStrToInt(aData[3])),
        $('#brand_edit_modal').modal('show')
    });


    $("#btn_edit_save").click(function(e){
        var nRow = EditRow;
        var delok = true;
        var params={};
        params.brand = $('#brand_edit_brand').val();
        params.name =$('#brand_edit_name').val();
        params.company = $('#brand_edit_company').val();
        params.type = $('#brand_edit_type').val();


        if(params.brand == ''||params.name == ''||params.company == '' ){
            swal({
                title: "品牌编号、名称和制造商不能为空！",
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
            url: "../brand/update",//注意路径
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

        table.fnUpdate($('#brand_edit_brand').val(), nRow, 0, false);
        table.fnUpdate($('#brand_edit_name').val(), nRow, 1, false);
        table.fnUpdate($('#brand_edit_company').val(), nRow, 2, false);
        table.fnUpdate(typeIntToStr($('#brand_edit_type').val()), nRow, 3, false);
        table.fnDraw();
        $('#brand_edit_brand').val('');
        $('#brand_edit_name').val('');
        $('#brand_edit_company').val('');
        typeIntToStr($('#brand_edit_type').val());
        $('#brand_edit_modal').modal('hide')
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
            params.brand = aData[0];
            $.ajax({
                async: false,
                type: "POST",
                url: "../brand/delete",//注意路径
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
