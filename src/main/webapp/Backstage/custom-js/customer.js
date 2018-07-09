/**
 * Created by 14260 on 2018/6/11.
 */
$(document).ready(function() {

    var table = $("#customer_table");
    table.dataTable({
        "columnDefs": [{ // set default column settings
            'orderable': false,
            'targets': [5]
        }, {
            "searchable": false,
            "targets": [5]
        }],
        "order": [
            [0, "asc"]
        ]
    });
/*    freash();
    function freash() {*/


    var params = {};
    $.ajax({
        async: false,
        type: "POST",
        url: "../customer/list",       //注意路径
        data: params,
        dataType: "json",
        success: function (data) {
            // console.log(JSON.stringify(data,null,4));
            for (var i = 0; i < data.length; i++) {
                  console.log(JSON.stringify(data[i],null,4));
                // console.log(data[i].admin);
                //  console.log(data[i].name)
                //  console.log(data[i].isvalid);
                var itm = data[i];
                table.fnAddData([
                    itm.name,
                    itm.phone,
                    itm.sex == '1' ? '男' : '女',
                    itm.birthday,
                    '<image style="width: 20px;height:20px;" src="../customer/' + itm.face + '"></image>',
                    '<a class="pwd" ><i class="fa fa-edit"></i>&nbsp;密码重置</a>'
                ]);
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
 /*   }*/

    $("#loading-customer").css('display','none');



    /**
     * 密码重置
     */

    table.on('click', '.pwd', function (e) {
        e.preventDefault();

        var nRow = $(this).parents('tr')[0];
        var aData = table.fnGetData(nRow);
        swal({
            title: "确认重置密码为123456 ?",
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
            params.vip = aData[0]+'';
            $.ajax({
                async: false,
                type: "POST",
                url: "../customer/resetpwd",//注意路径
                data: params,
                dataType: "json",
                success: function (data) {
                    if (data) {

                        delok = true;
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
                    title: "密码重置失败！",
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
                title: "密码重置成功！",
                text: "",
                type: "success",
                allowOutsideClick: true,
                showConfirmButton: true,
                showCancelButton: false,
                confirmButtonClass: "btn-success",
                confirmButtonText: "OK",
            });
/*        freash();*/

        });

    });

});
