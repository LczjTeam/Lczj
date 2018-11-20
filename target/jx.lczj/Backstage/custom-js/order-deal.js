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
            url: "../order/listHasPay",//注意路径
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
    $("#loading-deal").css('display', 'none');
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
                $("#order_state").val('已支付');
                $("#good_name").val('名称:'+showdata.mywearVos[0].goodsVo.t_goods.name+'\t(品牌:'+showdata.mywearVos[0].goodsVo.t_brand.name+'       颜色:'+showdata.mywearVos[0].t_color.name+'       脸型:'+showdata.mywearVos[0].t_face.name+'       场景:'+showdata.mywearVos[0].t_occasion.name+')');
                $("#eyeglass_name").val('名称:'+showdata.mywearVos[0].leftEyeglass.eyeglassVo.t_eyeglass.name+'\t(品牌:'+showdata.mywearVos[0].leftEyeglass.eyeglassVo.t_brand.name+'       膜层:'+showdata.mywearVos[0].leftEyeglass.eyeglassVo.t_mask.name+'       设计样式:'+showdata.mywearVos[0].leftEyeglass.eyeglassVo.t_style.name+'       功能:'+showdata.mywearVos[0].leftEyeglass.eyeglassVo.t_efficacy.name+')');
                $("#eye_left").val('类型:'+(showdata.mywearVos[0].leftEyeglass.t_wearglass.wearglass == 0 ? '近视':'远视' )+'       度数:'+showdata.mywearVos[0].leftEyeglass.t_wearglass.degress +'度       散光度数:'+showdata.mywearVos[0].leftEyeglass.t_wearglass.asdegress +'度       散光轴位:'+showdata.mywearVos[0].leftEyeglass.t_wearglass.axis+'度');
                $("#eye_right").val('类型:'+(showdata.mywearVos[0].rightEyeglass.t_wearglass.wearglass == 0 ? '近视':'远视' )+'       度数:'+showdata.mywearVos[0].rightEyeglass.t_wearglass.degress +'度       散光度数:'+showdata.mywearVos[0].rightEyeglass.t_wearglass.asdegress +'度       散光轴位:'+showdata.mywearVos[0].rightEyeglass.t_wearglass.axis+'度');

                //if(showdata.t_order.deal!=null || showdata.t_order.deal!='')   $("#deal_name").val(showdata.t_order.deal);

                //$("#deal_id").val('');
                //$("#deal_id").val(showdata.t_order.dealid);

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
    $("#btn_alter_deal").click(function(e){
        var delok = true;
        var params={};
        params.order = $("#order_code").val();

        if(params.deal =='' || params.title  == '' || params.sort_no  == '' ){
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
            url: "../order/update4",//注意路径
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



});
