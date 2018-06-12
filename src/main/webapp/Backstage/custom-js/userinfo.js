/**
 * Created by 14260 on 2018/6/12.
 */

$.validator.setDefaults({
    highlight: function (e) {
        $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
    },
    success: function (e) {
        e.closest(".form-group").removeClass("has-error").addClass("has-success")
    },
    errorElement: "span", errorPlacement: function (e, r) {
        e.appendTo(r.is(":radio") || r.is(":checkbox") ? r.parent().parent().parent() : r.parent())
    },
    errorClass: "help-block m-b-none",
    validClass: "help-block m-b-none"
}),
$().ready(function () {
    $("#commentForm").validate();
    var e = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate(
        {
            rules: {
                admin: "required",
                admin_name: "required",
                username: {
                    required: !0, minlength: 2
                },
                password: {
                    required: !0, minlength: 5
                },
                confirm_password: {
                    required: !0,
                    minlength: 5,
                    equalTo: "#password"
                },
                email: {
                    required: !0,
                    email: !0
                },
                topic: {
                    required: "#newsletter:checked",
                    minlength: 2
                },
                agree: "required"
            },
            messages: {
                admin: e + "请输入你的账号",
                admin_name: e + "请输入您的名称",
                username: {
                    required: e + "请输入您的用户名",
                    minlength: e + "用户名必须两个字符以上"
                },
                password: {
                    required: e + "请输入您的密码", minlength: e + "密码必须5个字符以上"
                }, confirm_password: {
                    required: e + "请再次输入密码"
                    , minlength: e + "密码必须5个字符以上",
                    equalTo: e + "两次输入的密码不一致"
                },
                email: e + "请输入您的E-mail",
                agree: {
                    required: e + "必须同意协议后才能注册", element: "#agree-error"
                }
            }
        }
    ),
    $("#username").focus(function () {
            var e = $("#admin").val(), r = $("#admin_name").val();
            e && r && !this.value && (this.value = e + "." + r)
    }),
    $("#btn_alter_info").click(function () {
        //修改管理员信息及密码
        var delok = true;
        var params={};
        params.admin = $('#admin').val();
        params.name =$('#admin_name').val();
        params.pwd =$('#password').val();
        $.ajax({
            async: false,
            type: "POST",
            url: "../admin/update",//注意路径
            data: params,
            dataType: "json",
            success: function (data) {
                if (data) {
                    return;
                } else {
                    delok = false;
                }
            },
            error: function (data) {
                delok = false;
            }
        });
        if (!delok) {
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




})});