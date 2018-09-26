
/*---------------表单验证------------------start*/
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
            }
        },

        //这个方法只有0.45版本才能有效。
        //邮箱去做后台唯一性校验
        submitHandler: function (validator, form, submitButton) {
            var $email = $("#userEmail").val();
            $.ajax({
                url: path + "/user/validateEmail.do",
                type: "post",
                async: false,
                data: {
                    "userEmail": $email
                },
                success: function (responseText) {
                    if (responseText == "hasEmail") {
                        validator.defaultSubmit();
                        sweetAlert("请到您指定的邮箱完成重置密码操作");
                    } else {
                        sweetAlert("您的邮箱并没有注册过，请注册");
                    }
                },
                error: function () {
                    sweetAlert("系统错误！");
                }
            });
        }
    });
});


/*---------------表单验证------------------end*/
