/**
 * Created by 14260 on 2018/6/14.
 */
$(document).ready(function () {

    $("#loading-express").css('display', 'none');

    function  loadByOrder(id){
        var params={};
        params.order = ''+id ;
        $.ajax({
            async: false,
            type: "POST",
            url: "../express/loadByOrder",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                console.log(JSON.stringify(data,null,4));

                $("#loading-express").css('display', 'none');
                var str = '';
                if(data.object == null){
                    $('#expresslist').html('');
                    $('#express-info').html('');
                    $('#express-warning').html('该订单暂无物流信息！');
                    return;
                }
                $('#express-warning').html('');
                for(var i = 0 ; i < data.object.Traces.length;i++) {

                     str += '<div class="timeline-item " style="margin:10px auto;width: 70%;">' +
                        '<div class="col-sm-3 ">' +
                        '<small class="text-navy">' + data.object.Traces[i].AcceptTime + '</small>' +
                        '</div>' +
                        '<div class="col-sm-9  no-top-border">' +
                        '<p class="m-b-xs"><strong>' + data.object.Traces[i].AcceptStation + '</strong></p>' +
                        '</div>' +
                        '</div>'
                }

                $('#expresslist').html(str);


                var ss =    '<div class="timeline-item " style="margin:10px auto;width: 70%;">'+
                                '<div class="col-sm-3 ">'+
                                '快递公司：'+
                                '</div>'+
                                '<div class="col-sm-9  no-top-border">'+
                                    '<p class="m-b-xs"><strong id="expressname">'+data.express+'</strong></p>'+
                                '</div>'+
                            '</div>'+
                            '<div class="timeline-item " style="margin:10px auto;width: 70%;">'+
                                '<div class="col-sm-3 ">'+
                                '快递编号：'+
                                '</div>'+
                                '<div class="col-sm-9  no-top-border">'+
                                    '<p class="m-b-xs"><strong id="expressid">'+data.expressid+'</strong></p>'+
                                '</div>'+
                            '</div>'+
                            '<div class="timeline-item " style="margin:10px auto;width: 70%;">'+
                                '<div class="col-sm-3 ">'+
                                '快递状态：'+
                                '</div>'+
                                '<div class="col-sm-9  no-top-border">'+
                                    '<p class="m-b-xs"><strong id="express-state">'+(data.object.State == 2 ?'在途中': (data.object.State == 3 ? '已签收':'问题件' )) +'</strong></p>'+
                                '</div>'+
                            '</div>';
                $('#express-info').html(ss);

            }, error: function (data) {
                // console.log(JSON.stringify(data,null,4));
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
    }



    $("#btn_search").click(function(){
        var id = $("#order_code").val();
        if(id==''){
            swal({
                title: "订单编号不能为空！",
                text: "",
                type: "warning",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-success",
                confirmButtonText: "OK",
            });
            return;
        }
        $("#loading-express").css('display', 'block');

        loadByOrder(id);
    })





});
