//获取用户的数据、设置到页面的hidden上
$(function () {
    $.ajax({
        url: path + "/user/getUser.do",
        type: "post",
        async: false,
        data: {},
        success: function (responseText) {

            if (responseText.user != null) {
                $("#loginDiv div h1").html("<font color='red'>" + responseText.user.userNickname + "</font>");
                $("#loginDiv").attr("href", "");

                $("#registerDiv").attr("href", path + "/logout.action");
                $("#registerDiv div h1").html("注销");

                //页面获取用户的数据
                $("#userNickname").val(responseText.user.userNickname);
                $("#userId").val(responseText.user.userId);


                $("#userEmail").val(responseText.user.userEmail);
            }
        },
        error: function () {


            alert("系统错误");
        }
    })
});
