/**
 * Created by 14260 on 2018/6/12.
 */
$(document).ready(function() {

    $("#btn_login").click(function(){
        $("#loding-login").css('display','block');
        var int = setInterval(function(e){

            var params={};
            params.admin =$("#admin").val();
            params.pwd =$("#pwd").val();

            if(params.admin =='' || params.pwd == ''){
                $("#loding-login").css('display','none');

                swal({
                    title: "账号和密码不能为空！",
                    text: "",
                    type: "warning",
                    allowOutsideClick: true,
                    showConfirmButton: true,
                    showCancelButton: false,
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "OK",
                });
                clearInterval(int);
                return ;
            }

            $.ajax({
                async: false,
                type: "POST",
                url: "../admin/login",//注意路径
                data: params,
                dataType: "json",
                success: function (data) {
                    //console.log(JSON.stringify(data,null,4));
                    if(data.t_admin == null){
                        swal({
                            title: "账号不存在或无效！",
                            text: "",
                            type: "warning",
                            allowOutsideClick: true,
                            showConfirmButton: true,
                            showCancelButton: false,
                            confirmButtonClass: "btn-danger",
                            confirmButtonText: "OK",
                        });
                        $("#loding-login").css('display','none');
                        clearInterval(int);
                    }else if (data.menuVos==null){
                        swal({
                            title: "密码错误！",
                            text: "",
                            type: "error",
                            allowOutsideClick: true,
                            showConfirmButton: true,
                            showCancelButton: false,
                            confirmButtonClass: "btn-danger",
                            confirmButtonText: "OK",
                        });
                        $("#loding-login").css('display','none');
                        clearInterval(int);
                    }else{
                        window.location.href="index.jsp";
                    }
                },
                error: function (data) {
                    //console.log(JSON.stringify(data,null,4));
                    swal({
                        title: "登陆出错！",
                        text: "",
                        type: "error",
                        allowOutsideClick: true,
                        showConfirmButton: true,
                        showCancelButton: false,
                        confirmButtonClass: "btn-danger",
                        confirmButtonText: "OK",
                    });
                    $("#loding-login").css('display','none');
                    clearInterval(int);
                }
            });



        },1000);

    });



});
