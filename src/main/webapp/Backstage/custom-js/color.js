$(document).ready(function(){

    $(".file-box").each(function(){animationHover(this,"pulse")});
    refresh();
    /**
     * 列出颜色
     */
    function refresh() {
        $(".file-box").remove();
        var params={};
        $.ajax ({
            async: false,
            type: "POST",
            url: "../color/list",//注意路径
            data: params,
            dataType: "json",
            success:function (data) {
                var src='';
                for (var i = 0 ; i<data.length;i++){
                    var itm = data[i];
                    src+='<div class="file-box">\n' +
                        '                       <span style="text-align: center" class="color_color" >ID:'+itm.color+'</span>&nbsp;&nbsp;&nbsp;<span>颜色：'+itm.name+'</span>\n' +
                        '                        <div class="file" >\n' +
                        '                            <span class="corner"></span>\n' +
                        '                            <div class="image">\n' +
                        '                                <img alt="image" class="img-responsive" src="'+"../colors/"+itm.rgb+'">\n' +
                        '                            </div>\n' +
                        '                            <a class="file-name edit"  id="'+itm.color+"00"+'" style="margin-left: 21%;"  >\n' +
                        '                                编辑\n' +
                        '                            </a>\n' +
                        '                            <a class="file-name delete " id="'+itm.color+'"  >\n' +
                        '                                删除\n' +
                        '                            </a>\n' +
                        '                        </div>\n' +
                        '                    </div>';
                }
                $("#colorlist").append(src);
            },
            error:function (data) {
                console.log(JSON.stringify(data, null, 4));

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
    }


    $("#loading-color").css('display', 'none');
    /**
     * 编辑
     */

    $(".edit").click(function (e) {

        $('#color_edit_modal').modal('show');
        var itme = {};
        itme.color =  $(this).attr('id');
        itme.color = itme.color/100;
        $.ajax({
            async:false,
            url:'../color/loadColor',
            data:itme,
            dataType:'JSON',
            type:"POST",
            success:function (data) {
                console.log(data);
                var itm = data;
                $("#color_edit_color").val(itm.color);
                $("#color_edit_name").val(itm.name);
                //图片
                $("#edit_ColorImg").attr("src","../colors/"+itm.rgb);

            },
            error:function (data) {
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

        })
    });
    $("#btn_edit_save").click(function (e) {
        var delok = true;
        var formData = new FormData($("#edit_color" )[0]);
        if(formData.get("color_edit")=='' || formData.get("name_edit")=='' || formData.get("colorFile") == ''){
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
            url:'../color/updata',
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
        refresh();
    });
    /**
     * 添加
     */
    $("#btn_add_color").click(function (e) {
        var formData = new FormData($("#add_color" )[0]);
        if(formData.get("color")=='' || formData.get("color_name")=='' || formData.get("file") == ''){
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
            async: false,
            url:'../color/addColor',
            type:'POST',
            dataType: "json",
            contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
            processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
            data:formData,
            success:function (returndata) {
                swal({
                    title: "颜色添加成功！",
                    text: "",
                    type: "success",
                    allowOutsideClick: true,
                    showConfirmButton: true,
                    showCancelButton: false,
                    confirmButtonClass: "btn-success",
                    confirmButtonText: "OK",
                });
                refresh();
            },
            error:function (data) {
                swal({
                    title: "颜色添加失败！",
                    text: "",
                    type: "error",
                    allowOutsideClick: true,
                    showConfirmButton: true,
                    showCancelButton: false,
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "OK",
                });
            }


        })

    });

    /**
     * 删除
     */

    $(".delete").click(function (e) {
        var itmes = {};
        itmes.color = $(this).attr('id');
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
            $.ajax({
                async: false,
                type: "POST",
                url:"../color/delete",
                data: itmes,
                dataType: "json",
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
            refresh();
        });
        /**
         * 删完之后在重新列出颜色
         */
    });
    /**
     * 通过id查询是否存在该id
     */
    $("#color").change(function (e) {
        var itm = {};
        var delok = true;
        itm.color =  $(this).val();
        $.ajax({
            async:false,
            url:'../color/loadByColor',
            type:'POST',
            dataType:"json",
            data:itm,
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
        if (!delok){
            return;
        }
        swal({
            title: "已存在该ID",
            text: "尝试换一个ID",
            type: "warning",
            allowOutsideClick: true,
            showConfirmButton: true,
            showCancelButton: false,
            confirmButtonClass: "btn-success",
            confirmButtonText: "OK",
            confirmButtonColor: "#f7a54a",
        });
    });
});
