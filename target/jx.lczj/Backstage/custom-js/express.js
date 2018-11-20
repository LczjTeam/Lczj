/**
 * Created by 14260 on 2018/6/14.
 */
$(document).ready(function () {


    //左侧
    var dataTank = [];//创建容器
    refreshTree();
    function  refreshTree() {

        dataTank = [];
        var params = {};
        $.ajax({
            async: false,
            type: "POST",
            url: "../order/listHasCreate",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {

              //  console.log(JSON.stringify(data, null, 4));
                var oldData = data;//获取Json数据
                for (var i = 0; i < oldData.length; i++) { //提取所需值组成新数组
                    var item = {};
                    var itm = oldData[i];
                    item.id = itm.order;
                    item.text = itm.order;
                    item.children = [];
                    dataTank.push(item);
                }
            },
            error: function (data) {
                //console.log(JSON.stringify(data, null, 4));
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
    //初始化树
    $('#using_json').data('jstree', false);
    $("#loading-express").css('display', 'none');
    $("#using_json").jstree({

        'plugins': ["wholerow", "types"],
        'core': {
            "themes": {
                "responsive": false
            },
            'data': dataTank
        },
        "types": {
            "default": {
                "icon": "glyphicon glyphicon-th-large "
            },
            "file": {
                "icon": "fa fa-file "
            }
        }

    });



    function  loadByOrder(id){
        var params={};
        params.order = ''+id ;
        $.ajax({
            async: false,
            type: "POST",
            url: "../orders/loadById",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                console.log(JSON.stringify(data,null,4));
                var showdata = data;

                $("#order_code").val(showdata.t_order.order);
                $("#customer_name").val(showdata.t_customer.name);
                $("#address_name").val(showdata.t_address.consignee);
                $("#address_text").val(showdata.t_address.provincename+showdata.t_address.cityname+showdata.t_address.countyname+showdata.t_address.street);
                $("#order_state").val('已加工');

                if(showdata.t_order.express!=null || showdata.t_order.express!='')   $("#express_name").val(showdata.t_order.express);

                $("#express_id").val('');
                $("#express_id").val(showdata.t_order.expressid);

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
    //点击事件
    var curtId = '';
    $('#using_json').on("changed.jstree", function (e, data) {

        var  id= data.selected;
        curtId = id;
        if(id=='') return;
       // console.log(id);
        loadByOrder(id);

    });

    //提交按钮，进行修改
    $("#btn_alter_express").click(function(e){
        var delok = true;
        var params={};
        params.order = $("#order_code").val();
        params.express =$('#express_name').val();
        params.expressid =$('#express_id').val();

        if(params.express =='' || params.title  == '' || params.sort_no  == '' ){
            swal({
                title: "编号、名称、序号不能为空！",
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
            type: "POST",
            url: "../order/update2",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                delok = data;
            },
            error: function (data) {
              //  console.log(JSON.stringify(data,null,4));

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


    });


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
        loadByOrder(id);
    })





});
