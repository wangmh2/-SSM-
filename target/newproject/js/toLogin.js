/*----------------------表单验证以及提交表单----------------------start*/
$(function () {
    //bootstrap校验
    $('form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            userEmail: {
                message: '邮箱验证失败',
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress: {
                        message: '邮箱地址格式有误'
                    }
                }
            },
            userPassword: {
                message: '密码验证失败',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 12,
                        message: '密码长度必须在6到12位之间'
                    }
                }
            }
        },
        submitHandler: function (validator, form, submitButton) {

            //ajax请求
            $.ajax({
                url: path + "/user/login.do",
                type: "post",
                async: false,
                data: $("#loginForm").serialize(),
                success: function (responseText) {
                    console.log(responseText);
                    if (responseText.message == "验证码错误") {
                        sweetAlert("验证码错误");
                    } else if (responseText.message == "密码错误") {
                        sweetAlert("密码错误");
                    } else if (responseText.message == "账号不存在/没有在邮箱中激活账户") {
                        sweetAlert("账号不存在/账户未激活");
                    } else if (responseText.message == "登陆成功") {
                        window.location.href = path + "/index.html";
                    } else {

                        //出错了也回到首页吧
                        window.location.href = path + "/index.html";
                    }

                    //只要错误了，就设置验证码为空，同时更新验证码
                    $("#inputCaptcha").val("");
                    $("#captcha").attr("src", path + "/user/getGifCode.do?time=" + new Date().getTime());
                    $("#submitButton").removeAttr("disabled");

                },
                error: function (response, ajaxOptions, thrownError) {
                    console.log(response);
                    console.log(ajaxOptions);
                    console.log(thrownError);
                    sweetAlert("系统错误");
                }
            });

            //validator.defaultSubmit();
        }
    });

    //点击图片换一张验证码
    $("#captcha").click(function () {
        $(this).attr("src", path + "/user/getGifCode.do?time=" + new Date().getTime());
    });
});
/*----------------------表单验证以及提交表单----------------------end*/


/*封装错误信息（暂时还没用到）-----------------------------------start*/

/*
var Error = function () {
    return {

        // 初始化各个函数及对象
        init: function () {

        },
        // 显示或者记录错误
        displayError: function (response, ajaxOptions, thrownError) {
            if (response.status == 404) {// 页面没有找到
                pageContent.load($("#hdnContextPath").val() + "/page/404.action");
            } else if (response.status == 401) {// session过期
                SweetAlert.errorSessionExpire();
            } else if (response.status == 507) {// 用户访问次数太频繁
                SweetAlert.error("您的访问次数太频繁, 请过一会再试...");
            } else {//其他错误
                window.location = $("#hdnContextPath").val() + "/page/500.action";
            }
            console.log(thrownError);
        }

    };

}();

jQuery(document).ready(function () {
    Error.init();
});
*/

/*封装错误信息（暂时还没用到）-----------------------------------end*/



