<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/9/14
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>首页</title>

    <link rel="stylesheet" type="text/css" href="verify/css/verify.css">
    <script type="text/javascript" src="verify/js/jquery.min.js"></script>
    <script type="text/javascript" src="verify/js/verify.min.js"></script>
    <script>
        $('#mpanel1').codeVerify({

            //常规验证码type=1， 运算验证码type=2
            type : 1,

            //验证码宽度
            width : '400px',

            //验证码高度
            height : '50px',

            //......更多参数设置请查阅文档

            //提交按钮的id名称
            btnId : 'check-btn',

            //验证成功以后的回调
            success : function() {
                alert('验证匹配！');
            }

        });
    </script>

   
</head>
<body>
<div id="mpanel1" >
</div>

<!--确定的按钮，id名称在初始化时使用-->
<button id="check-btn" class="verify-btn">确定</button>
</body>
</html>
