/**
 * Created by 14260 on 2018/6/11.
 */
$(document).ready(function(){

    var table = $("#model_table");
    table.dataTable({
        "columnDefs": [{ // set default column settings
            'orderable': false,
            'targets': [6]
        }, {
            "searchable": false,
            "targets": [6]
        }],
        "order": [
            [0, "asc"]
        ]
    });
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../model/list",//注意路径
        data: params,
        dataType: "json",
        success: function (data) {
           console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                var itm = data[i];
                table.fnAddData([
                    itm.t_model.model,
                    itm.t_model.name,
                    '<image style="width: 30px;height:30px;" src="../models/'+itm.t_model.photo+'"></image>',
                    itm.t_model.age,
                    itm.t_model.sex==0?'女':'男',
                    '<image style="width: 30px;height:30px;" src="../face/'+itm.t_face.photo+'"></image>',
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

    $("#loading-model").css('display','none');


    /**
     * 脸型
     */
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../face/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            var str = '';
            console.log(JSON.stringify(data,null,4));

            for(var i = 0;i < data.length;i++){
                var itm = data[i];
                if(i==0){
                    str+='<option value="'+itm.face+ '" selected="selected"> ' + itm.name+ '</option>';
                }else {
                    str += '<option value="' + itm.face + '">' + itm.name + '</option>';
                }
            }
            $("#model_add_face").html(str);
            $('#model_edit_face').html(str);
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



    /**
     * 添加
     */
    $("#btn_add_save").click(function (e) {
        var formData = new FormData($("#add_model" )[0]);

        if(formData.get("model")=='' || formData.get("name")==''|| formData.get("age")=='' || formData.get("file") == ''){
            swal({
                title: "ID、名称、年龄、图片不能为空！",
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
            url:'../model/add',
            type:'POST',
            dataType: "json",
            contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
            processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
            data:formData,
            success:function (itm) {
                if (itm!=null){

                    if (itm.info!=null && itm.info.indexOf("withGlasses")==-1){
                        swal({
                            title: itm.info,
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
                        itm.t_model.model,
                        itm.t_model.name,
                        '<image style="width: 30px;height:30px;" src="../models/'+itm.t_model.photo+'"></image>',
                        itm.t_model.age,
                        itm.t_model.sex==0?'女':'男',
                        '<image style="width: 30px;height:30px;" src="../face/'+itm.t_face.photo+'"></image>',
                        '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
                    ]);
                    $('#model_add_age').val('');
                    $('#model_add_model').val('');
                    $('#model_add_name').val('');
                    $('#file').val('');
                    $('#model_src').attr('src','');
                    $('#model_add_modal').modal('hide')


                    window.open ('../' + itm.info, 'newwindow', 'height=600, width=350, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');

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

                }else{
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
        itm.model =  ''+aData[0];
        var datas ;
        $.ajax({
            async:false,
            url:'../model/loadByModel',
            type:'POST',
            dataType:"json",
            data:itm,
            success:function (data) {
                console.log(JSON.stringify(data,null,4));
                $('#model_edit_model').val(data.model);
                $('#model_edit_name').val(data.name);
                $('#model_edit_sex').val(data.sex.trim());
                $('#model_edit_age').val(data.age);
                $('#model_edit_face').val(data.face);
                $("#edit_img").attr('src','../models/'+data.photo);
                $('#model_edit_modal').modal('show');
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
        var formData = new FormData($("#model_edit_form" )[0]);
        if(formData.get("model")=='' || formData.get("name")==''|| formData.get("age")=='' || formData.get("file") == ''){
            swal({
                title: "ID、名称、年龄、图片不能为空！",
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
            url:'../model/update',
            type:"POST",
            dataType:"JSON",
            data:formData,
            contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
            processData: false,
            success:function (data) {
                //alert(data.info)

                if(data.info!=null && data.info.indexOf("withGlasses")==-1){
                    swal({
                        title: data.info,
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

                table.fnUpdate(data.t_model.name, nRow, 1, false);
                table.fnUpdate('<image style="width: 30px;height:30px;" src="../models/'+data.t_model.photo+'"></image>', nRow, 2, false);
                table.fnUpdate(data.t_model.age, nRow,3, false);
                table.fnUpdate(data.t_model.name, nRow, 4, false);
                table.fnUpdate('<image style="width: 30px;height:30px;" src="../face/'+data.t_face.photo+'"></image>', nRow, 5, false);
                //table.fnDraw();
                $('#model_edit_age').val('');
                $('#model_edit_model').val('');
                $('#model_edit_name').val('');
                $('#file').val('');
                $('#model_src').attr('src','');
                $('#model_edit_modal').modal('hide')
                window.open ('../' + data.info, 'newwindow', 'height=600, width=350, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');

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
            params.model =parseInt(aData[0]);
            $.ajax({
                async: false,
                type: "POST",
                url: "../model/delete",//注意路径
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
