/**
 * Created by 14260 on 2018/6/11.
 */
$(document).ready(function(){

    var table = $("#face_table");
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
        url: "../face/list",//注意路径
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
                    itm.face,
                    itm.name,
                    '<image style="width: 30px;height:30px;" src="../face/'+itm.photo+'"></image>&nbsp;&nbsp;<image style="width: 30px;height:30px;" src="../face/'+itm.photo.substr(0,itm.photo.lastIndexOf('.jpg'))+'_1.jpg"></image>',
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

    $("#loading-face").css('display','none');

    /**
     * 添加
     */
    $("#btn_add_save").click(function (e) {
        var formData = new FormData($("#add_face" )[0]);

        if(formData.get("face_add_face")=='' || formData.get("face_add_name")=='' || formData.get("file") == ''|| formData.get("file1") == ''){
            swal({
                title: "脸型、名称、图片不能为空！",
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
        console.log(formData.get("face_add_face"),formData.get("face_add_name"));
        $.ajax({
            async: false,
            url:'../face/addFace',
            type:'POST',
            dataType: "json",
            contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
            processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
            data:formData,
            success:function (returndata) {

                    delok = returndata;
            },
            error: function (data) {
                console.log(data);
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

        var itm = {};
        itm.face =  ''+$("#face_add_face").val();
        var datas ;
        $.ajax({
            async:false,
            url:'../face/loadByFace',
            type:'POST',
            dataType:"json",
            data:itm,
            success:function (data) {
                console.log(JSON.stringify(data,null,4));

                datas = data;
                table.fnAddData([
                    datas.face,
                    datas.name,
                    '<image style="width: 30px;height:30px;" src="../face/'+datas.photo+'"></image>&nbsp;&nbsp;<image style="width: 30px;height:30px;" src="../face/'+datas.photo.substr(0,datas.photo.lastIndexOf('.jpg'))+'_1.jpg"></image>',
                    '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
                ]);
                table.fnDraw();
                $('#face_add_face').val('');
                $('#face_add_name').val('');
                $("#add_img").attr('src','');
                $('#file').val('');
                $('#face_add_modal').modal('hide');
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
        // alert(aData[0]);
        var itm = {};
        itm.face =  ''+aData[0];
        var datas ;
        $.ajax({
            async:false,
            url:'../face/loadByFace',
            type:'POST',
            dataType:"json",
            data:itm,
            success:function (data) {
                console.log(JSON.stringify(data,null,4));
                $('#face_edit_face').val(aData[0]);
                $('#face_edit_name').val(aData[1]);
                $("#edit_img").attr('src','../face/'+data.photo);
                $("#edit_img1").attr('src','../face/'+data.photo.substr(0,data.photo.lastIndexOf('.jpg'))+'_1.jpg');
                $('#face_edit_modal').modal('show');
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
        var formData = new FormData($("#face_edit_form" )[0]);
        if(formData.get("face_edit_face")=='' || formData.get("face_edit_name")=='' || formData.get("file") == ''|| formData.get("file1") == ''){
            swal({
                title: "脸型、名称、图片不能为空！",
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
            url:'../face/update',
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
        var itm = {};
        itm.face =  ''+$("#face_edit_face").val();
        $.ajax({
            async:false,
            url:'../face/loadByFace',
            type:'POST',
            dataType:"json",
            data:itm,
            success:function (datas) {
                console.log(JSON.stringify(datas,null,4));
                table.fnUpdate(datas.name, nRow, 1, false);
                table.fnUpdate('<image style="width: 30px;height:30px;" src="../face/'+datas.photo+'"></image>&nbsp;&nbsp;<image style="width: 30px;height:30px;" src="../face/'+datas.photo.substr(0,datas.photo.lastIndexOf('.jpg'))+'_1.jpg"></image>', nRow, 2, false);
                table.fnDraw();
                $('#edit_file').val('');
                $("#edit_img").attr('src','');
                $('[data-dismiss="fileinput"]').click();
                $('#face_edit_modal').modal('hide');


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
            params.face = aData[0];
            $.ajax({
                async: false,
                type: "POST",
                url: "../face/delete",//注意路径
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
