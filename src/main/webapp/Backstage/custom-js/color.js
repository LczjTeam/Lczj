/**
 * Created by 14260 on 2018/6/11.
 */
$(document).ready(function(){

    var table = $("#color_table");
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
        url: "../color/list",//注意路径
        data: params,
        dataType: "json",
        success: function (data) {
          //  console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
              //  console.log(JSON.stringify(data[i],null,4));
               // console.log(data[i].color);
               // console.log(data[i].name);
                var itm = data[i];
                table.fnAddData([
                    itm.color,
                    itm.name,
                    '<image style="width: 30px;height:30px;" src="../colors/'+itm.rgb+'"></image>',
                    '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
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

    $("#loading-color").css('display','none');
    /**
     * 添加
     */
    $("#btn_add_save").click(function (e) {
        var formData = new FormData($("#add_color" )[0]);

        if(formData.get("color_add_color")=='' || formData.get("color_add_name")=='' || formData.get("file") == ''){
            swal({
                title: "ID、颜色、图片不能为空！",
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

        var delok = true;
        $.ajax({
            async: false,
            url:'../color/addColor',
            type:'POST',
            dataType: "json",
            contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
            processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
            data:formData,
            success:function (data) {
                if (!data) {
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
        //刷新
        var itm = {};
        itm.color =  ''+$("#color_add_color").val();
        var datas ;
        $.ajax({
            async:false,
            url:'../color/loadByColor',
            type:'POST',
            dataType:"json",
            data:itm,
            success:function (data) {
                console.log(JSON.stringify(data,null,4));

                datas = data;
                table.fnAddData([
                     datas.color,
                     datas.name,
                    '<image style="width: 30px;height:30px;" src="../colors/'+datas.rgb+'"></image>',
                    '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
                ]);
                table.fnDraw();
                $('#color_add_color').val('');
                $('#color_add_name').val('');
                 $('#color_add_modal').modal('hide')
                $('[data-dismiss="fileinput"]').click();

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
            },
            error:function (data) {
                console.log(JSON.stringify(data,null,4));
                swal({
                    title: "刷新失败！",
                    text: "",
                    type: "warning",
                    allowOutsideClick: true,
                    showConfirmButton: true,
                    showCancelButton: false,
                    confirmButtonClass: "btn-success",
                    confirmButtonText: "OK",
                });
            }


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
        var itm = {};
        itm.color =  ''+aData[0];
        var datas ;
        $.ajax({
            async:false,
            url:'../color/loadByColor',
            type:'POST',
            dataType:"json",
            data:itm,
            success:function (data) {
                console.log(JSON.stringify(data,null,4));
                $('#color_edit_color').val(aData[0]);
                $('#color_edit_name').val(aData[1]);
                $("#edit_img").attr('src','../colors/'+data.rgb);
                $('#color_edit_modal').modal('show');
            },
            error:function (data) {
                console.log(JSON.stringify(data,null,4));
                swal({
                    title: "数据获取失败！",
                    text: "",
                    type: "warning",
                    allowOutsideClick: true,
                    showConfirmButton: true,
                    showCancelButton: false,
                    confirmButtonClass: "btn-success",
                    confirmButtonText: "OK",
                });
            }


        });





    });


    $("#btn_edit_save").click(function (e) {
        var nRow =EditRow ;
        var delok = true;
        var formData = new FormData($("#color_edit_form" )[0]);
        if(formData.get("color_edit_color")=='' || formData.get("color_edit_name")=='' || formData.get("file") == ''){
            swal({
                title: "ID、颜色、图片不能为空！",
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
            async:false,
            url:'../color/update',
            type:"POST",
            dataType:"JSON",
            data:formData,
            contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
            processData: false,
            success:function (data) {
                    delok = data;
            },
            error:function (data) {
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


        var itm = {};
        itm.color =  ''+$("#color_edit_color").val();
        $.ajax({
            async:false,
            url:'../color/loadByColor',
            type:'POST',
            dataType:"json",
            data:itm,
            success:function (datas) {
                console.log(JSON.stringify(datas,null,4));
                table.fnUpdate(datas.name, nRow, 1, false);
                table.fnUpdate('<image style="width: 30px;height:30px;" src="../colors/'+datas.rgb+'"></image>', nRow, 2, false);
                table.fnDraw();
                $('#color_edit_color').val('');
                $('#color_edit_name').val('');
                $("#edit_img").attr('src','');
                $('#color_edit_modal').modal('hide');
                $('[data-dismiss="fileinput"]').click();

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
            },
            error:function (data) {
                console.log(JSON.stringify(data,null,4));
                swal({
                    title: "刷新失败！",
                    text: "",
                    type: "warning",
                    allowOutsideClick: true,
                    showConfirmButton: true,
                    showCancelButton: false,
                    confirmButtonClass: "btn-success",
                    confirmButtonText: "OK",
                });
            }


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
            params.color = aData[0];
            $.ajax({
                async: false,
                type: "POST",
                url: "../color/delete",//注意路径
                data: params,
                dataType: "json",
                success: function (data) {
                     delok = data;
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
