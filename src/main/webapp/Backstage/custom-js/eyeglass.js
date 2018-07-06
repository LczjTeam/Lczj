/**
 * Created by 14260 on 2018/7/05.
 */
$(document).ready(function(){

    $(".footable").footable();

    var initJs = false;

    function dynamicLoadJs(url, callback) {
        var head = document.getElementsByTagName('head')[0];
        var script = document.createElement('script');
        script.type = 'text/javascript';
        script.src = url;
        if(typeof(callback)=='function'){
            script.onload = script.onreadystatechange = function () {
                if (!this.readyState || this.readyState === "loaded" || this.readyState === "complete"){
                    callback();
                    script.onload = script.onreadystatechange = null;
                }
            };
        }
        head.appendChild(script);
    }

    $("#btn-add-a").click(function(){

        if(!initJs){
            dynamicLoadJs('js/demo/webuploader-demo.min.js',function(e){console.log('加载成功')});
            initJs = true;
        }

        $("#eyeglass").removeAttr('readonly');

        $("#eyeglass").val('');
        $("#add_name").val('');
        $("#add_refraction").val("");
        $("#add_price").val("");
        $("#eyeglass_title").text('镜片信息添加');


        $("#page_eyeglass_list").css('display','none');
        $("#page_eyeglass_add").css('display','block');
    });

    $("#btn-back-a").click(function(){
        $("#page_eyeglass_list").css('display','block');
        $("#page_eyeglass_add").css('display','none');
    });


    /**
     *初始化
     */
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../eyeglass/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data,null,4));

            for(var i = 0 ; i < data.length ; i++) {
                var datas = data[i];
                console.log(JSON.stringify(datas,null,4));
                var attachStr = '';
                for( var j = 0 ;j < datas.t_attachments.length ; j++){
                    var itm =  datas.t_attachments[j];
                    attachStr +='&nbsp;&nbsp;&nbsp;<image style="width: 45px;height: 45px;" src="../goods/'+ itm.path+'"></image>'
                }


                //alert(attachStr);
                var str1 = '<tr>' +
                    '<td>' + datas.t_eyeglass.name + '</td>' +
                    '<td>' + datas.t_brand.name + '</td>' +
                    '<td>' + datas.t_mask.name + '</td>' +
                    '<td>' + datas.t_eyeglass.refraction + '</td>' +
                    '<td>' + datas.t_eyeglass.price + '</td>' +
                    '<td>' + datas.t_eyeglass.eyeglass + '</td>' +
                    '<td>' + datas.t_category.name + '</td>' +
                    '<td>' + datas.t_efficacy.name + '</td>' +
                    '<td>' + datas.t_style.name + '</td>' +
                    '<td>' + attachStr + '</td>' +
                    '<td><a class="edit"  id="' + datas.t_eyeglass.eyeglass+ '"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="' + datas.t_eyeglass.eyeglass + '" ><i class="fa fa-trash"></i>&nbsp;删除</a></td> ' +
                    '</tr>';

                console.log(str1)
                $("#tbd").prepend(str1).trigger('footable_redraw');

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



    /**
     * 镜片材质
     */
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../category/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            var str = '';
            //console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                var itm = data[i];
                str+='<option value="'+itm.category+'">'+itm.name+'</option>';
            }

            $("#add_category").html(str);

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
     * 镜片功能
     */
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../efficacy/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            var str = '';
            //console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                var itm = data[i];
                str+='<option value="'+itm.efficacy+'">'+itm.name+'</option>';
            }

            $("#add_efficacy").html(str);

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
     * 镜片品牌
     */
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../brand/list1",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            var str = '';
            //console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                var itm = data[i];
                str+='<option value="'+itm.brand+'">'+itm.name+'</option>';
            }

            $("#add_brand").html(str);

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
     * 镜片膜层
     */
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../mask/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            var str = '';
            //console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                var itm = data[i];
                str+='<option value="'+itm.mask+'">'+itm.name+'</option>';
            }

            $("#add_mask").html(str);

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
     * 镜片设计样式
     */
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../style/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            var str = '';
            //console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                var itm = data[i];
                str+='<option value="'+itm.style+'">'+itm.name+'</option>';
            }

            $("#add_style").html(str);

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



    $("#loading-eyeglass").css('display','none');

    /**
     * 添加/修改
     */
    $("#btn_add_save").click(function (e) {

        var formData1 = new FormData($("#eyeglass_add")[0]);

       if (formData1.get("eyeglass") ==null ||
           formData1.get("price")==null ||
           formData1.get("category")==null||
           formData1.get("efficacy")==null||
           formData1.get("brand") ==null||
           formData1.get("mask")==null||
           formData1.get("add_style")==null||
           formData1.get("refraction")==null||
           formData1.get("add_name")==null||
           formData1.get("fileName")==null
       ) {
            swal({
                title: "输入框不能为空！",
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


        if($("#eyeglass_title").text()=='镜片信息添加') {
            //眼镜信息添加
            var delok = true;
            $.ajax({
                async: false,
                url: '../eyeglass/add',
                type: 'POST',
                dataType: "json",
                contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
                processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
                data: formData1,
                success: function (datas) {

                    console.log(JSON.stringify(datas, null, 4));
                    var attachStr = '';
                    for( var j = 0 ;j < datas.t_attachments.length ; j++){
                        var itm =  datas.t_attachments[j];
                        attachStr +='&nbsp;&nbsp;&nbsp;<image style="width: 45px;height: 45px;" src="../goods/'+ itm.path+'"></image>'
                    }


                    //alert(attachStr);
                    var str1 = '<tr>' +
                        '<td>' + datas.t_eyeglass.name + '</td>' +
                        '<td>' + datas.t_brand.name + '</td>' +
                        '<td>' + datas.t_mask.name + '</td>' +
                        '<td>' + datas.t_eyeglass.refraction + '</td>' +
                        '<td>' + datas.t_eyeglass.price + '</td>' +
                        '<td>' + datas.t_eyeglass.eyeglass + '</td>' +
                        '<td>' + datas.t_category.name + '</td>' +
                        '<td>' + datas.t_efficacy.name + '</td>' +
                        '<td>' + datas.t_style.name + '</td>' +
                        '<td>' + attachStr + '</td>' +
                        '<td><a class="edit"  id="' + datas.t_eyeglass.eyeglass+ '"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="' + datas.t_eyeglass.eyeglass + '" ><i class="fa fa-trash"></i>&nbsp;删除</a></td> ' +
                        '</tr>';

                    setEvents();

                    console.log(str1)
                    $("#tbd").prepend(str1).trigger('footable_redraw');
                    $("#eyeglass").val('');
                    $("#add_name").val('');
                    $("#add_refraction").val("");
                    $("#add_price").val("");
                    $("#page_eyeglass_list").css('display', 'block');
                    $("#page_eyeglass_add").css('display', 'none');
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
                    return;

                },
                error: function (data) {
                    console.log(data)
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
        }else{
            //眼镜信息修改
            var delok = true;
            $.ajax({
                async: false,
                url: '../eyeglass/update',
                type: 'POST',
                dataType: "json",
                contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
                processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
                data: formData1,
                success: function (datas) {
                    console.log(JSON.stringify(datas, null, 4));

                    var  nRow = EditRow;
                    if (nRow.nextSibling != null && nRow.nextSibling.innerHTML != null && nRow.nextSibling.innerHTML.indexOf('footable-row-detail-cell') != -1) {
                        nRow.nextSibling.remove();
                    }
                    nRow.remove();

                    //alert(ageStr);

                    var attachStr = '';
                    for (var j = 0; j < datas.t_attachments.length; j++) {
                        var itm = datas.t_attachments[j];
                        attachStr += '&nbsp;&nbsp;&nbsp;<image style="width: 45px;height: 45px;" src="../goods/' + itm.path + '"></image>'
                    }

                    //alert(attachStr);
                    var str1 = '<tr>' +
                        '<td>' + datas.t_eyeglass.name + '</td>' +
                        '<td>' + datas.t_brand.name + '</td>' +
                        '<td>' + datas.t_mask.name + '</td>' +
                        '<td>' + datas.t_eyeglass.refraction + '</td>' +
                        '<td>' + datas.t_eyeglass.price + '</td>' +
                        '<td>' + datas.t_eyeglass.eyeglass + '</td>' +
                        '<td>' + datas.t_category.name + '</td>' +
                        '<td>' + datas.t_efficacy.name + '</td>' +
                        '<td>' + datas.t_style.name + '</td>' +
                        '<td>' + attachStr + '</td>' +
                        '<td><a class="edit"  id="' + datas.t_eyeglass.eyeglass+ '"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="' + datas.t_eyeglass.eyeglass + '" ><i class="fa fa-trash"></i>&nbsp;删除</a></td> ' +
                        '</tr>';


                    console.log(str1)
                    $("#tbd").prepend(str1).trigger('footable_redraw');
                    setEvents();

                    $("#page_eyeglass_list").css('display', 'block');
                    $("#page_eyeglass_add").css('display', 'none');
                    swal({
                        title: "修改成功！",
                        text: "",
                        type: "success",
                        allowOutsideClick: true,
                        showConfirmButton: true,
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: "OK",
                    });
                    return;

                },
                error: function (data) {
                    console.log(data)
                    swal({
                        title: "修改失败！",
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
        }

    });

    var EditRow = '-1';
    setEvents();


    function setEvents() {
        $(".delete").unbind("click");
        $(".edit").unbind("click");
        /**
         * 删除
         */
        $(".delete").click(function (e) {

            var nRow = $(this).parents('tr')[0];
            var id = $(this).attr('id');


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
                var params = {};
                params.code = id;
                $.ajax({
                    async: false,
                    type: "POST",
                    url: "../eyeglass/delete",//注意路径
                    data: params,
                    dataType: "json",
                    success: function (data) {
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
                if (nRow.nextSibling != null && nRow.nextSibling.innerHTML != null && nRow.nextSibling.innerHTML.indexOf('footable-row-detail-cell') != -1) {
                    nRow.nextSibling.remove();
                }
                nRow.remove();
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


        /**
         * 编辑
         */
        $(".edit").click(function (e) {

            if(!initJs){
                dynamicLoadJs('js/demo/webuploader-demo.min.js',function(e){console.log('加载成功')});
                initJs = true;
            }
            var nRow = $(this).parents('tr')[0];
            EditRow = nRow;
            var id = $(this).attr('id')+'';
            /*alert(id);
             alert(nRow.innerHTML);*/
            $("#div-alter").css("display",'block');
            $("#div-list").css("display",'none');

            params = {};
            params.code = id ;
            $.ajax({
                async: false,
                type: "POST",
                url: "../eyeglass/loadById",//注意路径
                data: params,
                dataType: "json",
                success: function (datas) {

                    console.log(JSON.stringify(datas, null, 4));

                    $("#add_eyeglass").val(datas.t_eyeglass.eyeglass);
                    $("#add_name").val(datas.t_category.name);
                    $("#add_brand").val(datas.t_brand.brand);
                    $("#add_category").val(datas.t_category.category);
                    $("#add_efficacy").val(datas.t_efficacy.efficacy);
                    $("#add_mask").val(datas.t_mask.mask);
                    $("#add_style").val(datas.t_style.style);
                    $("#add_refraction").val(datas.t_eyeglass.refraction);
                    $("#add_price").val(datas.t_eyeglass.price);

                    //alert(ageStr);

                    $("#atts_list").html('');
                    var attachStr = '';
                    for( var j = 0 ;j < datas.t_attachments.length ; j++){
                        var itm =  datas.t_attachments[j];
                        var response = itm.attachment;
                        var str  = '<div class="col-sm-12" id="'+response+'">'+
                            '<div class="col-sm-8">'+
                            '<input name="fileName" value="'+response+'" readonly style="background-color: white;border: none;outline: none;" class="form-control" type="hidden">'+
                            '<input  value="'+response+'.png" readonly style="background-color: white;border: none;outline: none;" class="form-control" type="text">'+
                            '</div>'+
                            '<div class="col-sm-4" style="padding-top: 3px;" >'+
                            '<a  style="float: right;"    class="atts_deletes" ><i class="fa fa-times-circle"></i></a>'+
                            '</div>' +
                            '</div>';

                        $("#atts_list").append(str);
                    }


                    $(".atts_deletes").unbind('click');

                    $(".atts_deletes").click(function(e){
                        var divs = $(this).parents('div')[0].parentNode;
                        //alert(divs.id);

                        var params = {};
                        params.code = divs.id+'';
                        $.ajax({
                            async: false,
                            type: "POST",
                            url: "../good/deleteAttach",//注意路径
                            data: params,
                            dataType: "json",
                            success: function (datas) {
                                if(datas){
                                    $('#'+divs.id).remove();
                                }else{
                                    swal({
                                        title: "移除失败！",
                                        text: "",
                                        type: "error",
                                        allowOutsideClick: true,
                                        showConfirmButton: true,
                                        showCancelButton: false,
                                        confirmButtonClass: "btn-danger",
                                        confirmButtonText: "OK",
                                    });
                                }

                            },error:function(err){
                                console.log(JSON.stringify(err, null, 4));
                                swal({
                                    title: "移除失败！",
                                    text: "",
                                    type: "error",
                                    allowOutsideClick: true,
                                    showConfirmButton: true,
                                    showCancelButton: false,
                                    confirmButtonClass: "btn-danger",
                                    confirmButtonText: "OK",
                                });
                            }});
                    });

                    $("#eyeglass_title").text('镜片信息修改');
                    $("#eyeglass").attr('readonly','readonly');
                    $("#page_eyeglass_list").css('display','none');
                    $("#page_eyeglass_add").css('display','block');

                },error:function(err){
                    console.log(JSON.stringify(err, null, 4))
                }});
        });
    }


});
