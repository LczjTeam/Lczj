$(function () {

    $.ajax({
        async: false,
        type: "POST",
        url: "../role/list", //注意路径
        data: {},
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data,null,4));

            var str = '';
            for(var i = 0;i < data.length; i++){
                str+='<option value='+data[i].role+' >'+data[i].name+'</option>';
            }

            $('#pre-selected-options').html(str);

            $('#pre-selected-options').multiSelect();
            $('.ms-selectable').prepend("<div style='text-align: left;' ><i class='glyphicon glyphicon-hand-right\n'></i>&nbsp;未拥有</div>");
            $('.ms-selection').prepend("<div style='text-align: left;' ><i class='glyphicon glyphicon-hand-right\n'></i>&nbsp;已拥有</div>");

        },
        error: function (data) {
            console.log(JSON.stringify(data,null,4));
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


    var datas = [];
    $.ajax({
        async: false,
        type: "POST",
        url: "../admin/list",       //注意路径
        data: {},
        dataType: "json",
        success: function (data) {
            console.log(JSON.stringify(data,null,4));

            for(var i = 0;i < data.length; i++){
                var itm = {};
                itm.text = data[i].name;
                itm.icon = "glyphicon glyphicon-certificate";
                itm.href= "#";
                itm.selected= !0;
                datas.push(itm);
            }
        },
        error: function (data) {
            console.log(JSON.stringify(data,null,4));
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


    $("#treeview7").treeview({
        color: "#428bca",
        showBorder: !1,
        nodeIcon: "glyphicon glyphicon-bookmark",
        data: datas,

        onNodeSelected: function (e, o) {
            //节点点击事件
            alert("<p>您单击了 " + o.text + "</p>");
        }
    });

    /**
     * 确认保存
     */
    $('#btn_save_change').click(function(e){

        //获取值
        alert($('#pre-selected-options').val());


        //初始化【设置全部不选】
        var lis2 = $('.ms-list')[0].childNodes;
        for (var i = 0; i < lis2.length; i++) {
            lis2[i].style.display = 'block';
        }


        var lis = $('.ms-list')[1].childNodes;
        for (var i = 0; i < lis.length; i++) {
            lis[i].style.display = 'none';
        }

        var ops = $('#pre-selected-options').find('option');

        for (var i = 0; i < ops.length; i++) {
            ops[i].removeAttribute('selected');
        }

        //设置值
        $('#pre-selected-options').val(['elem_3','elem_2']);
        $('#pre-selected-options').trigger('change');
        $('#pre-selected-options').multiSelect();
    });

});