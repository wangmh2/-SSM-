//添加评论,
$(function () {
    $("#submitComment").click(function () {

        var $userId = $("#userId").val();

        //判断用户是否登陆了
        if ($userId == "" || $userId.length == 0 || $userId == null) {
            sweetAlert("您需要登陆了以后才能评论");
            return;
        }

        //判断用户是否写了数据
        var $comment = $("#comment_text").val();
        if ($comment == "" || $comment.length == 0) {
            sweetAlert("您没有输入数据");
            return;
        } else {
            $.ajax({
                url: path + "/comment/addComment.do",
                type: "POST",
                data: {
                    userId: $userId,
                    comment: $comment
                },
                success: function (responseText) {
                    if(responseText=="success"){
                        sweetAlert("添加评论成功");
                        window.location.reload();
                    }else {
                        sweetAlert("添加评论失败");
                    }



                },
                error: function () {
                    sweetAlert("获取评论失败！");
                }
            });


        }
    });




});


//查询所有评论，放到页面上展示出来。
$(function () {
    $.ajax({
        url: path + "/comment/queryComment.do",
        type: "POST",
        success: function (responseText) {

            //遍历数组，对返回的JSON作为评论添加到网页上
            for (var i = 0; i < responseText.length; i++) {

                $("#lookComment").append("<div style='background: black; border: #ffffff 1px solid'><p style='color: #c7c7c7'>" + responseText[i].nickName + "  &nbsp;&nbsp;&nbsp;<span style='color: #333333'>" + new Date(responseText[i].createTime).toLocaleString() + "</span><p class='text-right' style='color: #448AFF'>" + responseText[i].order + "</p> </p><span style='color: #c7c7c7'>" + responseText[i].content + " </span></div>");

            }
        },
        error: function () {
            sweetAlert("获取评论失败！");
        }
    });


});


//JS毫秒数转成想要的格式
Date.prototype.toLocaleString = function () {
    return this.getFullYear() + "年" + (this.getMonth() + 1) + "月" + this.getDate() + "日 " + this.getHours() + "点" + this.getMinutes() + "分" + this.getSeconds() + "秒";
};