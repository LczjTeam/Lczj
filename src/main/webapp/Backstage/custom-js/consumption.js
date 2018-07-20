/**
 * Created by xiaoyi on 2018/7/19.
 */
$(document).ready(function(){

    var table = $("#consumption_table");

    table.dataTable({
        "order": [
            [0, "asc"]
        ]
    });
    $("#consumption_table_wrapper").find('.row')[0].remove();
    /**
     * 查询
     */
    $("#btn_consumption").click(function(e){
        var params={};
        params.customer = $('#customer').val();
        params.time = document.getElementById("searchDateRange").innerText;

        console.log($('#customer').val());
        console.log(document.getElementById("searchDateRange").innerText);

        if(params.customer == ''){
            swal({
                title: "用户名不能为空！",
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
        table.dataTable().fnClearTable();

        $.ajax({
            async: false,
            type: "POST",
            url: "../orders/searchByTime",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                console.log(JSON.stringify(data,null,4));
                for (var i = 0; i < data.length; i++) {
                    console.log(JSON.stringify(data[i],null,4));
                    console.log(data[i].order);
                    console.log(data[i].sure_time);
                    console.log(data[i].address);
                    console.log(data[i].expressid);
                    console.log(data[i].state);
                    console.log(data[i].totalfee);
                    console.log(data[i].voucher);

                    var itm = data[i];
                    table.fnAddData([
                        itm.order,
                        itm.sure_time,
                        itm.address,
                        itm.expressid,
                        itm.state,
                        itm.totalfee,
                        itm.voucher
                    ]);
                }
            },
            error: function (data) {
                swal({
                    title: "查询失败！",
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

    });
    $("#loading-consumption").css('display','none');

});
