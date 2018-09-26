//点击左侧链接、右侧显示页面
$(function () {
    $("#leftNavBar li a ").click(function () {

        //当前样式
        $(this).parent().addClass("active").siblings().removeClass();

        var attr = $(this).attr("name");
        if (attr == "queryMemo") {
            $("#queryMemo").show().siblings().hide();
        } else if (attr == "addMemo") {

            //获取用户的邮箱
            var email = $("#userEmail").val();

            //设置输入框的值
            $("#inputEmail").val(email);

            $("#addMemo").show().siblings().hide();
        }
    });
});


//查询所有备忘录的信息，并显示在页面上
$(function () {
    var userId = $("#userId").val();
    $.ajax({
        url: path + "/memo/queryAllMemo.do",
        type: "POST",
        data: {userId: userId},
        success: function (responseText) {

            if (responseText == "" || responseText == null || responseText.length == 0) {
                $("#allMemo").html("您还没有任何的备忘录数据");
            } else {

                //遍历数组，对返回的JSON作为备忘录添加到网页上
                for (var i = 0; i < responseText.length; i++) {
                    var memoId = responseText[i].memoId;
                    $("#allMemo").append("<tr><td>" + responseText[i].nickName + "</td><td>" + new Date(responseText[i].editTime).toLocaleString() + "</td><td>" + responseText[i].email + "</td><td>" + responseText[i].content + "</td><td>" + new Date(responseText[i].sendTime).toLocaleString() + "</td><td>" + responseText[i].state + "</td><td><button class='btn btn-primary' onclick='updCurrentMemo(this)' id=" + memoId + "  data-toggle='modal' data-target='#myModal'>修改</button>&nbsp;&nbsp;<a href='" + path + "/memo/deleteMemo.do?memoId=" + memoId + "'>删除</a></td>");


                    //如果是已发送状态的，那么设置不可用
                    if (responseText[i].state == "已发送") {
                        $("#" + memoId + "").attr("disabled", "true");

                    }

                }
            }
        },
        error: function () {
            sweetAlert("获取备忘录数据失败！");
        }
    });
});


//更新当前的备忘录数据
function updCurrentMemo(currentMemo) {


    var id = $(currentMemo).attr("id");

    //获取内容
    var content = $(currentMemo).parent().siblings().eq(3).html();

    //获取发送时间
    var sendTime = $(currentMemo).parent().siblings().eq(4).html();

    $("#memoContentManager").empty();

    //将其添加到模态框中
    $("#memoContentManager").append("<tr><td><input type='text' id='memoContent' class='form-control' value='" + content + "'></td><td><input type='text' id='sendTime' value='" + sendTime + "'  readonly  class='form-control form_datetime '></td><input type='hidden' value='" + id + "' id='memoId'></tr>");


    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        autoclose: true,//自动关闭
        minView: 0,//最精准的时间选择为日期0-分 1-时 2-日 3-月
        weekStart: 0,
        startDate: new Date()

    });


}


//JS毫秒数转成想要的格式
Date.prototype.toLocaleString = function () {
    return this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() + " " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();
};

//修改备忘录提交表单
$("#updateMemo").click(function () {

    var memoContent = $("#memoContent").val();
    var sendTime = $("#sendTime").val();
    var memoId = $("#memoId").val();
    var userId = $("#userId").val();


    $.ajax({
        url: path + "/memo/updateMemo.do",
        type: "post",
        data: {memoId: memoId, memoContent: memoContent, sendTime: sendTime, userId: userId},

        success: function (responseText) {
            if (responseText == "success") {
                sweetAlert("修改成功");
            } else {
                sweetAlert("修改失败");
            }

            //隐藏模态框并刷新页面
            $('#myModal').modal('hide');
            window.location.reload();
        },
        error: function () {
            sweetAlert("系统错误了");
        }
    });


});


//时间选择器
$("#datetimepicker").datetimepicker({
    format: 'yyyy-mm-dd hh:ii:ss',
    autoclose: true,//自动关闭
    minView: 0,//最精准的时间选择为日期0-分 1-时 2-日 3-月
    weekStart: 0,
    startDate: new Date()

});


