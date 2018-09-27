<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/9/15
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        <!--点击验证码图片进行更新-->
        function changeVerifyCode() {
            var time=new Date().getTime();
            document.getElementById("kaptchaImage").src="/kaptcha?d="+time;//为了不让验证码缓存，为了安全起见，需要次次都刷新
        }
        <!--验证登录信息是否填写完整 -->
        function userlogin(){
            var useremail = document.getElementById("useremail").value.trim();
            
            var password = document.getElementById("password").value.trim();
           
            var vcode = document.getElementById("vcode").value.trim();
            
            if(useremail == ""){
                window.alert("请填写登录邮箱");
            }else if (password == "") {
                window.alert("请填写密码");
            }else if (vcode == ""){
                window.alert("请填写验证码");
            }else{
                location.href = "userlogin?useremail="+useremail+"&password="+password+"&vcode="+vcode;
            }
        }
        <!--让返回的错误信息3秒后消失-->
        $(function(){
          $("#message").show().delay(3000).fadeOut();  
        })
    </script>
    <style>
        #container{
            position:absolute;
            top:0px;
            left:0px;
            background-color: #e91d2a;
            width: 100%;
            height: 100%;
           
        }
        
        #login{
            width: 300px;
            height: 350px;
            position: absolute;
            left: 620px;
            top: 150px;
            background-color: white;
        }
        
        .d1{
            position: absolute;
            left: 21px;
            top: 80px;
            width: 260px;
        }
        
        .d2{
            position: absolute;
            left: 21px;
            top: 140px;
            width: 260px;
        }
        
        #logintitle{
            position: absolute;
            top:2px;
            left: 25px;
        }
        
        .form-control{
            position: absolute;
            left: 1px;
            top: 1px;
        }
        
        #vcinput{
            position: absolute;
            left: 21px;
            top: 200px;
            width: 130px;
        }
        
        #vc{
            position: absolute;
            left:165px;
            top:200px;
            
        }
        
        #kaptchaImage{
            height: 36px;
            width: 100px;
        }
        
        
        #loginbutton{
            position: absolute;
            left: 30px;
            top: 270px;
            width:110px;
        }
        
        #resetbutton{
            position: absolute;
            left: 157px;
            top: 270px;
            width: 110px;
        }
        
        #message{
            position: absolute;
            top: 27px;
            left: 160px;
            width: 100px;
            height: 50px;
        }
        
        #message p{
            font-size: 20px;
        }
        
        #forgetpassword{
            position: absolute;
            top: 320px;
            left: 120px;
        }
    </style>
</head>
<body>

    <div id="container">
        <div id="login">
            <div id="logintitle">
                <h2>Sign in</h2>
            </div>
            <div id="message">
                <p>${message}</p>
            </div>
            <div>
                <form action="userlogin" method="post">
                    <div class="input-group d1">
                        <span class="input-group-addon glyphicon glyphicon-user" id="basic-addon1"></span>
                        <input type="text" id="useremail" value="${useremail}" class="form-control" placeholder="useremail" name="useremail" aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group d2">
                        <span class="input-group-addon glyphicon glyphicon-asterisk" id="basic-addon2"></span>
                        <input type="password" id="password" value="${password}" class="form-control" placeholder="password" name="password" aria-describedby="basic-addon1">
                    </div>
                    <div id="vcinput">
                        <input type="text" id="vcode" class="form-control" placeholder="请输入验证码" name="vcode" aria-describedby="basic-addon1">
                    </div>
                    <div id="vc">
                        <img src="kaptcha.jpg" id="kaptchaImage" title="看不清，点击换一张" onclick="changeVerifyCode()">
                    </div>
                    <button type="button" class="btn btn-info" id="loginbutton" onclick="userlogin()">登录</button>
                    <button type="reset" class="btn btn-info" id="resetbutton" value="Reset">重填</button>
                    <div id="forgetpassword">
                        <a href="forgetpassword">忘记密码？</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
