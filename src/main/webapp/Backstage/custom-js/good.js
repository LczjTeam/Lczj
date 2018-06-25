/**
 * Created by 14260 on 2018/6/11.
 */
$(document).ready(function(){


    /**
     *
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
                    '<td>' + datas.t_category.name + '</td>' +
                    '<td>' + (datas.t_goods.suitable_sex == 0 ? '通用':(datas.t_goods.suitable_sex == 1 ? "男":"女"))+ '</td>' +
                    '<td>' + datas.t_goods.goods + '</td>' +
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
                $("#tbd").prepend(str1);
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

    $(".footable").footable();


    /**
     * 添加类别
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
     * 添加品牌
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
     * 添加颜色
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
     * 添加使用年龄段
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
     * 添加脸型
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
     * 添加使用场景
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


    /**
     * 添加
     */
    $("#btn_add_save").click(function (e) {
        var formData = new FormData($("#goods_add")[0]);
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

                console.log(JSON.stringify(datas,null,4))

               /* var str = '<tr>'+
                    '<td>'+datas.t_news.title+'</td>'+
                    '<td>'+formatDateTime(datas.t_news.issue_date)+'</td>'+
                    '<td>'+datas.t_news.code+'</td>'+
                    '<td>'+datas.t_admin.name+' </td>'+
                    (( datas.t_news.photo== null || datas.t_news.photo =='') ?  '<td>无</td>': ('<td><image style="width: 50px;height:50px;" src="../stories/' + datas.t_news.photo +'"></image></td>'))+
                    '<td>'+datas.t_news.keyword+' </td>'+
                    '<td>'+(datas.t_news.publish==0 ? '否':'是')+' </td>'+
                    '<td>'+(datas.t_news.top==0 ? '否':'是')+' </td>'+
                    '<td><a class="edit"  id="'+datas.t_news.code+'"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="'+datas.t_news.code+'" ><i class="fa fa-trash"></i>&nbsp;删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="preview"  id="'+datas.t_news.filename+'" ><i class="fa fa-chrome"></i>&nbsp;浏览</a></td>'+
                    '</tr>';

                $("#tbd").prepend(str).trigger('footable_redraw');

                $('#add_keyword').val('');
                $('#add_title').val('');
                $('#add_photo').val('');
                $('#add_content').code('');

                $("#div-add").css("display",'none');
                $("#div-list").css("display",'block');
                setEvents();
                swal({
                    title: "添加成功！",
                    text: "",
                    type: "success",
                    allowOutsideClick: true,
                    showConfirmButton: true,
                    showCancelButton: false,
                    confirmButtonClass: "btn-success",
                    confirmButtonText: "OK",
                });*/
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

    });



});
