/**
 * Created by 14260 on 2018/6/12.
 */
$().ready(function () {
    $("#btn_alter_headpic").click(function(){
        var formData = new FormData($("#headpic_form" )[0]);
        //alert(formData);
        var ok = false;
        $.ajax({
            url: '../files/uploaduserhead' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                console.log(JSON.stringify(returndata,null,4));
                //window.location.href = "index.jsp";
                swal({
                    title: "头像修改成功！",
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
                //alert("login failed");
                console.log(JSON.stringify(data,null,4));
                swal({
                    title: "头像修改失败！",
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

});