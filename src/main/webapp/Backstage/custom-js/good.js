/**
 * Created by 14260 on 2018/6/11.
 */
$(document).ready(function(){

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
    $("#loading-age").css('display','none');

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
    $("#loading-age").css('display','none');


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
    $("#loading-age").css('display','none');


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
    $("#loading-age").css('display','none');


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
    $("#loading-age").css('display','none');



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
    $("#loading-age").css('display','none');


});
