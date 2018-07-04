/**
 * Created by 14260 on 2018/6/11.
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


        $("#add_good").val('');
        $("#add_name").val('');
        $("#add_models").val('');
        $("#add_width").val('');
        $("#add_height").val('');
        $("#add_length").val('');
        $("#add_max_width").val('');
        $("#add_price").val('');
        $("#add_space").val('');
        $("#atts_list").html('');

        $("#good_title").text('眼镜信息添加');


        $("#page_good_list").css('display','none');
        $("#page_good_add").css('display','block');
    });

    $("#btn-back-a").click(function(){
        $("#page_good_list").css('display','block');
        $("#page_good_add").css('display','none');
    });


    /**
     *初始化
     */
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../good/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
           console.log(JSON.stringify(data,null,4));

            for(var i = 0 ; i < data.length ; i++) {
                var datas = data[i];
                console.log(JSON.stringify(datas,null,4));
                var categoryStr = '';
                for( var j = 0 ;j < datas.t_categories.length ; j++){
                    var itm =  datas.t_categories[j];
                    categoryStr+='&nbsp;&nbsp;&nbsp;'+itm.name;
                }

                //alert(categoryStr);


                var colorStr = '';
                for( var j = 0 ;j < datas.t_colors.length ; j++){
                    var itm =  datas.t_colors[j];
                    colorStr+='&nbsp;&nbsp;&nbsp;<image  style="width: 20px;height: 20px;" src="../colors/'+ itm.rgb.trim()+'" ></image>'+itm.name
                }

                //alert(colorStr);


                var faceStr = '';
                for( var j = 0 ;j < datas.t_faces.length ; j++){
                    var itm =  datas.t_faces[j];
                    faceStr +='&nbsp;&nbsp;&nbsp;<image  style="width: 30px;height: 30px;" src="../face/'+ itm.photo+'"></image>'+itm.name
                }


                //alert(faceStr);
                var occasionStr = '';
                for( var j = 0 ;j < datas.t_occasions.length ; j++){
                    var itm =  datas.t_occasions[j];
                    occasionStr +='&nbsp;&nbsp;&nbsp;'+itm.name
                }


                //alert(occasionStr);
                var ageStr = '';
                for( var j = 0 ;j < datas.t_agesections.length ; j++){
                    var itm =  datas.t_agesections[j];
                    ageStr  +='&nbsp;&nbsp;&nbsp;'+itm.name+'('+itm.minage+'-'+itm.maxage+')'
                }

                //alert(ageStr);


                var attachStr = '';
                for( var j = 0 ;j < datas.t_attachments.length ; j++){
                    var itm =  datas.t_attachments[j];
                    attachStr +='&nbsp;&nbsp;&nbsp;<image style="width: 45px;height: 45px;" src="../goods/'+ itm.path+'"></image>'
                }


                //alert(attachStr);
                var str1 = '<tr>' +
                    '<td>' + datas.t_goods.name + '</td>' +
                    '<td>' + datas.t_goods.models + '</td>' +
                    '<td>' + datas.t_brand.name + '</td>' +
                    '<td>' + categoryStr+ '</td>' +
                    '<td>' + (datas.t_goods.suitable_sex == 0 ? '通用':(datas.t_goods.suitable_sex == 1 ? "男":"女"))+ '</td>' +
                    '<td>' + datas.t_goods.price + '</td>' +
                    '<td>&nbsp;&nbsp;' + datas.t_goods.goods + '</td>' +
                    '<td>' + '&nbsp;&nbsp;镜面宽:'+ datas.t_goods.width + 'mm&nbsp;&nbsp;镜面高:'+ datas.t_goods.height+'mm&nbsp;&nbsp;鼻尖距:'+ datas.t_goods.space
                    + 'mm&nbsp;&nbsp;镜腿长:'+ datas.t_goods.length+ 'mm&nbsp;&nbsp;镜总宽:'+ datas.t_goods.max_width+ 'mm</td>' +
                    '<td>' + colorStr + '</td>' +
                    '<td>' + ageStr + '</td>' +
                    '<td>' + faceStr + '</td>' +
                    '<td>' + occasionStr + '</td>' +
                    '<td>' + attachStr + '</td>' +
                    '<td><a class="edit"  id="' + datas.t_goods.goods + '"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="' + datas.t_goods.goods + '" ><i class="fa fa-trash"></i>&nbsp;删除</a></td> ' +
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
     * 类别
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
                str+='<option value="'+itm.category+'">&nbsp;&nbsp;&nbsp;&nbsp;'+itm.name+'</option>';
            }

            $("#add_category").html(str);
            $("#edit_category").html(str);
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
     * 品牌
     */
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../brand/list",       //注意路径
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
            $("#edit_brand").html(str);
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
     * 颜色
     */
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../color/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            var str = '';
            //console.log(JSON.stringify(data,null,4));
            str+='<option value="'+data[0].color+ '" selected="selected">'
                + '&nbsp;&nbsp;&nbsp;&nbsp;' + data[0].name+'</option>';
            for(var i = 1;i < data.length;i++){
                var itm = data[i];
                str+='<option value="'+itm.color+ '">' +
                    '&nbsp;&nbsp;&nbsp;&nbsp;' + itm.name + '</option>';
            }
            $("#add_color").html(str);
            $("#edit_color").html(str);
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
     * 使用年龄段
     */
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../age/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            var str = '';
            //console.log(JSON.stringify(data,null,4));
            str+='<option value="'+data[0].agesection+ '" selected="selected">' +
                '&nbsp;&nbsp;&nbsp;&nbsp;'
                +data[0].name+'('+ parseInt(data[0].minage)+ '-' + parseInt(data[0].maxage)+')</option>';
            for(var i = 1;i < data.length;i++){
                var itm = data[i];
                str+='<option value="'+itm.agesection+ '">' +
                    '&nbsp;&nbsp;&nbsp;&nbsp;'
                    +itm.name+'('+ parseInt(itm.minage)+ '-' + parseInt(itm.maxage)+')</option>';
            }
            $("#add_agesection").html(str);
            $("#edit_agesection").html(str);
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
            str+='<option value="'+data[0].face+ '" selected="selected">' +
                '&nbsp;&nbsp;&nbsp;&nbsp;' +data[0].name+ '</option>';
            for(var i = 1;i < data.length;i++){
                var itm = data[i];
                str+='<option value="'+itm.face+ '">' +
                    '&nbsp;&nbsp;&nbsp;&nbsp;' +itm.name+ '</option>';
            }
            $("#add_face").html(str);
            $("#edit_face").html(str);
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
     * 使用场景
     */
    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../occasion/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            var str = '';
            //console.log(JSON.stringify(data,null,4));
            str+='<option value="'+data[0].occasion+ '" selected="selected">' +
                '&nbsp;&nbsp;&nbsp;&nbsp;' +data[0].name+ '</option>';
            for(var i = 1;i < data.length;i++){
                var itm = data[i];
                str+='<option value="'+itm.occasion+ '">' +
                    '&nbsp;&nbsp;&nbsp;&nbsp;' +itm.name+ '</option>';
            }
            $("#add_occasion").html(str);
            $("#edit_occasion").html(str);
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



   $("#loading-goods").css('display','none');

    /**
     * 添加
     */
    $("#btn_add_save").click(function (e) {

        var formData = new FormData($("#goods_add")[0]);
        formData.append('category', $("#add_category").val());
        formData.append('colors', $("#add_color").val());
        formData.append('faces', $("#add_face").val());
        formData.append('occasions', $("#add_occasion").val());
        formData.append('agesections', $("#add_agesection").val());


        if (formData.get("good") == '' || formData.get("good_name") == '' || formData.get("models") == ''
            || formData.get("width") == ''  || formData.get("height") == ''  || formData.get("length") == ''
            || formData.get("max_width") == ''  || formData.get("space") == ''  || formData.get("price") == '') {
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


        if($("#good_title").text()=='眼镜信息添加') {
            //眼镜信息添加
            var delok = true;
            $.ajax({
                async: false,
                url: '../good/add',
                type: 'POST',
                dataType: "json",
                contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
                processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
                data: formData,
                success: function (datas) {

                    console.log(JSON.stringify(datas, null, 4))

                    console.log(JSON.stringify(datas, null, 4));
                    var categoryStr = '';
                    for( var j = 0 ;j < datas.t_categories.length ; j++){
                        var itm =  datas.t_categories[j];
                        categoryStr+='&nbsp;&nbsp;&nbsp;'+itm.name;
                    }

                    //alert(categoryStr);


                    var colorStr = '';
                    for (var j = 0; j < datas.t_colors.length; j++) {
                        var itm = datas.t_colors[j];
                        colorStr += '&nbsp;&nbsp;&nbsp;<image  style="width: 20px;height: 20px;" src="../colors/' + itm.rgb.trim() + '" ></image>' + itm.name
                    }

                    //alert(colorStr);


                    var faceStr = '';
                    for (var j = 0; j < datas.t_faces.length; j++) {
                        var itm = datas.t_faces[j];
                        faceStr += '&nbsp;&nbsp;&nbsp;<image  style="width: 30px;height: 30px;" src="../face/' + itm.photo + '"></image>' + itm.name
                    }


                    //alert(faceStr);
                    var occasionStr = '';
                    for (var j = 0; j < datas.t_occasions.length; j++) {
                        var itm = datas.t_occasions[j];
                        occasionStr += '&nbsp;&nbsp;&nbsp;' + itm.name
                    }


                    //alert(occasionStr);
                    var ageStr = '';
                    for (var j = 0; j < datas.t_agesections.length; j++) {
                        var itm = datas.t_agesections[j];
                        ageStr += '&nbsp;&nbsp;&nbsp;' + itm.name + '(' + itm.minage + '-' + itm.maxage + ')'
                    }

                    //alert(ageStr);


                    var attachStr = '';
                    for (var j = 0; j < datas.t_attachments.length; j++) {
                        var itm = datas.t_attachments[j];
                        attachStr += '&nbsp;&nbsp;&nbsp;<image style="width: 45px;height: 45px;" src="../goods/' + itm.path + '"></image>'
                    }


                    //alert(attachStr);
                    var str1 = '<tr>' +
                        '<td>' + datas.t_goods.name + '</td>' +
                        '<td>' + datas.t_goods.models + '</td>' +
                        '<td>' + datas.t_brand.name + '</td>' +
                        '<td>' + categoryStr + '</td>' +
                        '<td>' + (datas.t_goods.suitable_sex == 0 ? '通用' : (datas.t_goods.suitable_sex == 1 ? "男" : "女")) + '</td>' +
                        '<td>' + datas.t_goods.price + '</td>' +
                        '<td>&nbsp;&nbsp;' + datas.t_goods.goods + '</td>' +
                        '<td>' + '&nbsp;&nbsp;镜面宽:' + datas.t_goods.width + 'mm&nbsp;&nbsp;镜面高:' + datas.t_goods.height + 'mm&nbsp;&nbsp;鼻尖距:' + datas.t_goods.space
                        + 'mm&nbsp;&nbsp;镜腿长:' + datas.t_goods.length + 'mm&nbsp;&nbsp;镜总宽:' + datas.t_goods.max_width + 'mm</td>' +
                        '<td>' + colorStr + '</td>' +
                        '<td>' + ageStr + '</td>' +
                        '<td>' + faceStr + '</td>' +
                        '<td>' + occasionStr + '</td>' +
                        '<td>' + attachStr + '</td>' +
                        '<td><a class="edit"  id="' + datas.t_goods.goods + '"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="' + datas.t_goods.goods + '" ><i class="fa fa-trash"></i>&nbsp;删除</a></td> ' +
                        '</tr>';

                    console.log(str1)
                    $("#tbd").prepend(str1).trigger('footable_redraw');
                    setEvents();

                    $("#page_good_list").css('display', 'block');
                    $("#page_good_add").css('display', 'none');
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
            //眼镜信息添加
            var delok = true;
            $.ajax({
                async: false,
                url: '../good/update',
                type: 'POST',
                dataType: "json",
                contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
                processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
                data: formData,
                success: function (datas) {
                    console.log(JSON.stringify(datas, null, 4));

                    var  nRow = EditRow;
                    if (nRow.nextSibling != null && nRow.nextSibling.innerHTML != null && nRow.nextSibling.innerHTML.indexOf('footable-row-detail-cell') != -1) {
                        nRow.nextSibling.remove();
                    }
                    nRow.remove();

                    var categoryStr = '';
                    for( var j = 0 ;j < datas.t_categories.length ; j++){
                        var itm =  datas.t_categories[j];
                        categoryStr+='&nbsp;&nbsp;&nbsp;'+itm.name;
                    }

                    //alert(categoryStr);
                    var colorStr = '';
                    for (var j = 0; j < datas.t_colors.length; j++) {
                        var itm = datas.t_colors[j];
                        colorStr += '&nbsp;&nbsp;&nbsp;<image  style="width: 20px;height: 20px;" src="../colors/' + itm.rgb.trim() + '" ></image>' + itm.name
                    }

                    //alert(colorStr);


                    var faceStr = '';
                    for (var j = 0; j < datas.t_faces.length; j++) {
                        var itm = datas.t_faces[j];
                        faceStr += '&nbsp;&nbsp;&nbsp;<image  style="width: 30px;height: 30px;" src="../face/' + itm.photo + '"></image>' + itm.name
                    }


                    //alert(faceStr);
                    var occasionStr = '';
                    for (var j = 0; j < datas.t_occasions.length; j++) {
                        var itm = datas.t_occasions[j];
                        occasionStr += '&nbsp;&nbsp;&nbsp;' + itm.name
                    }


                    //alert(occasionStr);
                    var ageStr = '';
                    for (var j = 0; j < datas.t_agesections.length; j++) {
                        var itm = datas.t_agesections[j];
                        ageStr += '&nbsp;&nbsp;&nbsp;' + itm.name + '(' + itm.minage + '-' + itm.maxage + ')'
                    }

                    //alert(ageStr);


                    var attachStr = '';
                    for (var j = 0; j < datas.t_attachments.length; j++) {
                        var itm = datas.t_attachments[j];
                        attachStr += '&nbsp;&nbsp;&nbsp;<image style="width: 45px;height: 45px;" src="../goods/' + itm.path + '"></image>'
                    }
                    //alert(attachStr);
                    var str1 = '<tr>' +
                        '<td>' + datas.t_goods.name + '</td>' +
                        '<td>' + datas.t_goods.models + '</td>' +
                        '<td>' + datas.t_brand.name + '</td>' +
                        '<td>' + categoryStr + '</td>' +
                        '<td>' + (datas.t_goods.suitable_sex == 0 ? '通用' : (datas.t_goods.suitable_sex == 1 ? "男" : "女")) + '</td>' +
                        '<td>' + datas.t_goods.price + '</td>' +
                        '<td>&nbsp;&nbsp;' + datas.t_goods.goods + '</td>' +
                        '<td>' + '&nbsp;&nbsp;镜面宽:' + datas.t_goods.width + 'mm&nbsp;&nbsp;镜面高:' + datas.t_goods.height + 'mm&nbsp;&nbsp;鼻尖距:' + datas.t_goods.space
                        + 'mm&nbsp;&nbsp;镜腿长:' + datas.t_goods.length + 'mm&nbsp;&nbsp;镜总宽:' + datas.t_goods.max_width + 'mm</td>' +
                        '<td>' + colorStr + '</td>' +
                        '<td>' + ageStr + '</td>' +
                        '<td>' + faceStr + '</td>' +
                        '<td>' + occasionStr + '</td>' +
                        '<td>' + attachStr + '</td>' +
                        '<td><a class="edit"  id="' + datas.t_goods.goods + '"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="' + datas.t_goods.goods + '" ><i class="fa fa-trash"></i>&nbsp;删除</a></td> ' +
                        '</tr>';

                    console.log(str1)
                    $("#tbd").prepend(str1).trigger('footable_redraw');
                    setEvents();

                    $("#page_good_list").css('display', 'block');
                    $("#page_good_add").css('display', 'none');
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
                    url: "../good/delete",//注意路径
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
            var id = $(this).attr('id');
            /*alert(id);
             alert(nRow.innerHTML);*/
            $("#div-alter").css("display",'block');
            $("#div-list").css("display",'none');

            params = {};
            params.code = id ;
            $.ajax({
                async: false,
                type: "POST",
                url: "../good/loadById",//注意路径
                data: params,
                dataType: "json",
                success: function (datas) {

                    console.log(JSON.stringify(datas, null, 4));
                    $("#add_good").val(datas.t_goods.goods);
                    $("#add_name").val(datas.t_goods.name);
                    $("#add_brand").val(datas.t_goods.brand);
                    $("#add_suitable_sex").val(datas.t_goods.suitable_sex);
                    $("#add_models").val(datas.t_goods.models);
                    $("#add_width").val(datas.t_goods.width);
                    $("#add_height").val(datas.t_goods.height);
                    $("#add_length").val(datas.t_goods.length);
                    $("#add_max_width").val(datas.t_goods.max_width);
                    $("#add_price").val(datas.t_goods.price);
                    $("#add_space").val(datas.t_goods.space);

                    var categoryStr = [];
                    for( var j = 0 ;j < datas.t_categories.length ; j++){
                        var itm =  datas.t_categories[j];
                        categoryStr.push(itm.category);
                    }
                    $("#add_category").val(categoryStr);
                    $("#add_category").multiselect('refresh');

                    var colorStr = [];
                    for( var j = 0 ;j < datas.t_colors.length ; j++){
                        var itm =  datas.t_colors[j];
                        colorStr.push(itm.color);
                    }
                    $("#add_color").val(colorStr);
                    $("#add_color").multiselect('refresh');


                    //alert(colorStr);


                    var faceStr = [];
                    for( var j = 0 ;j < datas.t_faces.length ; j++){
                        var itm =  datas.t_faces[j];
                        faceStr.push(itm.face);
                    }
                    $("#add_face").val(faceStr) ;
                    $("#add_face").multiselect('refresh');




                    //alert(faceStr);
                    var occasionStr = [];
                    for( var j = 0 ;j < datas.t_occasions.length ; j++){
                        var itm =  datas.t_occasions[j];
                        occasionStr.push(itm.occasion);
                    }
                    $("#add_occasion").val(occasionStr);
                    $("#add_occasion").multiselect('refresh');



                    //alert(occasionStr);
                    var ageStr = [];
                    for( var j = 0 ;j < datas.t_agesections.length ; j++){
                        var itm =  datas.t_agesections[j];
                        ageStr.push(itm.agesection);
                    }
                    $("#add_agesection").val(ageStr);
                    $("#add_agesection").multiselect('refresh');


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

                    $("#good_title").text('眼镜信息修改');

                    $("#page_good_list").css('display','none');
                    $("#page_good_add").css('display','block');

                },error:function(err){
                    console.log(JSON.stringify(err, null, 4))
                }});
        });
    }


});
