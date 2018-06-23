/**
 * Created by 14260 on 2018/6/11.
 */
$(document).ready(function(){

    var table = $("#occasion_table");
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
        url: "../occasion/list",//注意路径
        data: params,
        dataType: "json",
        success: function (data) {
           console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
              //  console.log(JSON.stringify(data[i],null,4));
               // console.log(data[i].occasion);
               // console.log(data[i].name);

                var itm = data[i];
                table.fnAddData([
                    itm.occasion,
                    itm.name,
                    itm.photo,
                    '<image style="width: 30px;height:30px;" src="../occasion/'+itm.occasion+'_0.png"></image>',
                    '<image style="width: 30px;height:30px;" src="../occasion/'+itm.occasion+'_1.png"></image>',
                    '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
                ]);
            }
        },
        error: function (data) {
            console.log(JSON.stringify(data,null,4));
            alert("数据获取失败 ！");
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

    $("#loading-occasion").css('display','none');
    /**
     * 添加
     */
    $("#btn_add_save").click(function(e){

        var formData = new FormData($("#add_occasion" )[0]);

        if(formData.get("occasion_add_occasion")=='' || formData.get("occasion_add_name")=='' || formData.get("occasion_add_photo") == ''){
            swal({
                title: "场景、名字、英文名字不能为空！",
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
            url:'../occasion/addoccasion',
            type:'POST',
            dataType: "json",
            contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
            processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
            data:formData,
            success:function (returndata) {
                if (data) {
                    delok =true;
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
            $('#occasion_add_occasion').val(),
            $('#occasion_add_name').val(),
            $('#occasion_add_photo ').val(),
            '<image style="width: 30px;height:30px;" src="../occasion/'+$('#occasion_add_occasion').val()+'_0.png"></image>',
            '<image style="width: 30px;height:30px;" src="../occasion/'+$('#occasion_add_occasion').val()+'_1.png"></image>',
            '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
        ]);
        table.fnDraw();
        $('#occasion_add_occasion').val('');
        $('#occasion_add_name').val('');
        $('#occasion_add_photo ').val('');
        $('#file1').val('');
        $('#file2').val('');
        $('#occasion_add_modal').modal('hide')
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


        /*var delok = true;
        var params={};
        params.occasion = $('#occasion_add_occasion').val();
        params.name =$('#occasion_add_name').val();
        params.photo = $('#occasion_add_photo').val();
        params.file1 = $('#file').val();
        if(params.occasion =='' || params.name == '' || params.photo == '' || params.file1==''){
            swal({
                title: "角色、名称、英文名、彩色图不能为空！",
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
            url: "../occasion/add",//注意路径
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
            $('#occasion_add_occasion').val(),
            $('#occasion_add_name').val(),
            $('#occasion_add_photo ').val(),
            $('#file').val(),
            '<a class="edit"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" ><i class="fa fa-trash"></i>&nbsp;删除</a>'
        ]);
        table.fnDraw();
        $('#occasion_add_occasion').val('');
        $('#occasion_add_name').val('');
        $('#occasion_add_photo ').val();
        $('#file').val();
        $('#occasion_add_modal').modal('hide')
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

        */






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
        itm.occasion =  ''+aData[0];
        var datas ;
        $.ajax({
            async:false,
            url:'../occasion/loadByoccasion',
            type:'POST',
            dataType:"json",
            data:itm,
            success:function (data) {
                console.log(JSON.stringify(data,null,4));
                $('#occasion_edit_occasion').val(aData[0]);
                $('#occasion_edit_name').val(aData[1]);
                $("#edit_img1").attr('src','../occasions/'+data.occasion+'_0.png');
                $("#edit_img2").attr('src','../occasions/'+data.occasion+'_1.png');
                $('#occasion_edit_modal').modal('show');
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
        var formData = new FormData($("#occasion_edit_form" )[0]);
        if(formData.get("occasion_edit_occasion")=='' || formData.get("occasion_edit_name")=='' || formData.get("file1") == ''|| formData.get("file2") == ''){
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
            url:'../occasion/update',
            type:"POST",
            dataType:"JSON",
            data:formData,
            contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
            processData: false,
            success:function (data) {
                if (data) {

                    return;
                } else {
                    delok = false;
                }
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
        itm.occasion =  ''+$("#occasion_edit_occasion").val();
        $.ajax({
            async:false,
            url:'../occasion/loadByoccasion',
            type:'POST',
            dataType:"json",
            data:itm,
            success:function (datas) {
                console.log(JSON.stringify(datas,null,4));
                table.fnUpdate(datas.name, nRow, 1, false);
                table.fnUpdate(datas.photo,nRow, 2,false);
                table.fnDraw();
                $('#edit_file1').val('');
                $("#edit_img1").attr('src','');
                $('#edit_file2').val('');
                $("#edit_img2").attr('src','');
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
            params.occasion = aData[0];
            $.ajax({
                async: false,
                type: "POST",
                url: "../occasion/delete",//注意路径
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
