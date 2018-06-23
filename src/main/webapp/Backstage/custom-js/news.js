/**
 * Created by 14260 on 2018/6/23.
 */

$(document).ready(function() {
    $(".summernote").summernote({
        lang: "zh-CN",
        height: 300
    });


    $("#btn_add").click(function(){
        $("#div-add").css("display",'block');
        $("#div-list").css("display",'none');
    });

    $("#btn-add-bakck").click(function(){
        $("#div-add").css("display",'none');
        $("#div-list").css("display",'block');
    });

    $("#btn-alter-bakck").click(function(){
        $("#div-alter").css("display",'none');
        $("#div-list").css("display",'block');
    });


    var params={};
    $.ajax({
        async: false,
        type: "POST",
        url: "../news/list1",//注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            //  console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                console.log(JSON.stringify(data[i],null,4));
                var str = '<tr>'+
                        '<td>'+data[i].t_news.title+'</td>'+
                        '<td>'+formatDateTime(data[i].t_news.issue_date)+'</td>'+
                        '<td>'+data[i].t_news.code+'</td>'+
                        '<td>'+data[i].t_admin.name+' </td>'+
                        (( data[i].t_news.photo== null || data[i].t_news.photo =='') ?  '<td>无</td>': ('<td><image style="width: 50px;height:50px;" src="../stories/' + data[i].t_news.photo +'"></image></td>'))+
                        '<td>'+data[i].t_news.keyword+' </td>'+
                        '<td>'+(data[i].t_news.publish==0 ? '是':'否')+' </td>'+
                        '<td>'+(data[i].t_news.top==0 ? '是':'否')+' </td>'+
                        '<td><a class="edit"  id="'+data[i].t_news.code+'"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="'+data[i].t_news.code+'" ><i class="fa fa-trash"></i>&nbsp;删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="preview"  id="'+data[i].t_news.filename+'" ><i class="fa fa-chrome"></i>&nbsp;浏览</a></td>'+
                        '</tr>';

                $("#tbd").append(str);


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
    $(".footable").footable();
    $("#loading-news").css('display','none');


    /**
     * 时间格式化
     * @param inputTime
     * @returns {string}
     */
    function formatDateTime(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    };



    /**
     * 添加
     */
    $("#btn_add_save").click(function (e) {
        var formData = new FormData($("#stories_add")[0]);
        formData.append('content', $("#add_content").code());

        console.log($("#add_content").code());

        if (formData.get("title") == '' || formData.get("keyword") == '' ||  $("#add_content").code()=='') {
            swal({
                title: "标题、关键字、内容不能为空！",
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
            url: '../news/add',
            type: 'POST',
            dataType: "json",
            contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
            processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
            data: formData,
            success: function (datas) {

                console.log(JSON.stringify(datas,null,4))

                var str = '<tr>'+
                    '<td>'+datas.t_news.title+'</td>'+
                    '<td>'+formatDateTime(datas.t_news.issue_date)+'</td>'+
                    '<td>'+datas.t_news.code+'</td>'+
                    '<td>'+datas.t_admin.name+' </td>'+
                    (( datas.t_news.photo== null || datas.t_news.photo =='') ?  '<td>无</td>': ('<td><image style="width: 50px;height:50px;" src="../stories/' + datas.t_news.photo +'"></image></td>'))+
                    '<td>'+datas.t_news.keyword+' </td>'+
                    '<td>'+(datas.t_news.publish==0 ? '是':'否')+' </td>'+
                    '<td>'+(datas.t_news.top==0 ? '是':'否')+' </td>'+
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
                });
            },
            error: function (data) {
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

    /**
     * 修改
     */
    $("#btn_edit_save").click(function (e) {
        var formData = new FormData($("#stories_edit")[0]);
        formData.append('content', $("#edit_content").code());

        console.log($("#edit_content").code());

        if (formData.get("title") == '' || formData.get("keyword") == '' ||  $("#edit_content").code()=='') {
            swal({
                title: "标题、关键字、内容不能为空！",
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
            url: '../news/update',
            type: 'POST',
            dataType: "json",
            contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置(详见：#1)
            processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post，详见：#2)
            data: formData,
            success: function (datas) {

                console.log(JSON.stringify(datas,null,4))
                var  nRow = EditRow;
                if (nRow.nextSibling != null && nRow.nextSibling.innerHTML != null && nRow.nextSibling.innerHTML.indexOf('footable-row-detail-cell') != -1) {
                    nRow.nextSibling.remove();
                }
                nRow.remove();

                var str = '<tr>'+
                    '<td>'+datas.t_news.title+'</td>'+
                    '<td>'+formatDateTime(datas.t_news.issue_date)+'</td>'+
                    '<td>'+datas.t_news.code+'</td>'+
                    '<td>'+datas.t_admin.name+' </td>'+
                    (( datas.t_news.photo== null || datas.t_news.photo =='') ?  '<td>无</td>': ('<td><image style="width: 50px;height:50px;" src="../stories/' + datas.t_news.photo +'"></image></td>'))+
                    '<td>'+datas.t_news.keyword+' </td>'+
                    '<td>'+(datas.t_news.publish==0 ? '是':'否')+' </td>'+
                    '<td>'+(datas.t_news.top==0 ? '是':'否')+' </td>'+
                    '<td><a class="edit"  id="'+datas.t_news.code+'"  ><i class="fa fa-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="delete" id="'+datas.t_news.code+'" ><i class="fa fa-trash"></i>&nbsp;删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="preview"  id="'+datas.t_news.filename+'" ><i class="fa fa-chrome"></i>&nbsp;浏览</a></td>'+
                    '</tr>';

                $("#tbd").prepend(str).trigger('footable_redraw');

                $('#add_keyword').val('');
                $('#add_title').val('');
                $('#add_photo').val('');
                $('#add_content').code('');

                $("#div-alter").css("display",'none');
                $("#div-list").css("display",'block');

                setEvents();
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
            },
            error: function (data) {
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

    });

    var EditRow = '-1';

    setEvents();
    function setEvents(){

        $(".delete").unbind("click");
        $(".preview").unbind("click");
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
                    url: "../news/delete1",//注意路径
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
         * 预览
         */
        $(".preview").click(function (e) {
            var id = $(this).attr('id');
            window.open('../stories/' + id, '_blank');
            return;
        });


        /**
         * 编辑
         */
        $(".edit").click(function (e) {
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
                url: "../news/loadById",//注意路径
                data: params,
                dataType: "json",
                success: function (datas) {

                    console.log(JSON.stringify(datas, null, 4));
                    $('#edit_code').val(datas.t_news.code);
                    $('#edit_title').val(datas.t_news.title);
                    $('#edit_keyword').val(datas.t_news.keyword);
                    $('#edit_top').val(datas.t_news.top=='0'? '是':'否');
                    $('#edit_publish').val(datas.t_news.publish=='0'? '是':'否');
                    $('#edit_photo_name').val(datas.t_news.photo);



                    var url = '../stories/'+datas.t_news.code+'-content.html';
                    $.ajax({
                        url:url,
                        type:"GET",
                        dataType:"html",
                        success:function(result){
                            console.log(result);
                            $('#edit_content').code(result);
                        }
                    });


                },error:function(err){
                    console.log(JSON.stringify(err, null, 4))
                }});
        });
    }
});

