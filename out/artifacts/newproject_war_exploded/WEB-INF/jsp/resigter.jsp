
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>用户注册</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery.js"></script>
        <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
        <script src="js/sweetalert.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/sweetalert.css">
        <script>
            var isonclick = false;
            
            function userresigter() {
                var phonenumber = document.getElementById("phonenumber").value.trim();
                var password = document.getElementById("password").value.trim();
                var username = document.getElementById("username").value.trim();
                var password_again = document.getElementById("password_again").value.trim();
                var yanzhengma = document.getElementById("yanzhengma").value.trim();
                
                if(username == "" || password == "" || password_again == "" || phonenumber == ""){
                    sweetAlert("请将信息填写完整");
                }else if(password != password_again){
                    sweetAlert("两次输入的密码不同");
                }else if(yanzhengma == ""){
                    sweetAlert("请输入您的短信验证码！");
                }else if(isonclick == false){
                    sweetAlert("请先请求手机验证码！");
                }else{
                    var params = {};
                    params.phonenumber = phonenumber;
                    params.password = password;
                    params.username = username;
                    params.yanzhengma = yanzhengma;
                    <!--async 设置为 false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行 -->
                    $.ajax({
                        async:false,
                        url:"userresigter",
                        type:"post",
                        data: params,
                        datatype: 'json',
                        success: function (data) {
                            if(data.code == "0"){
                                window.location.href = "login";
                                sweetAlert("注册成功！");
                            }else if(data.code == "2"){
                                sweetAlert("该手机号码已经被注册");
                            }else{
                                sweetAlert("手机验证码错误");
                            }
                        }
                    });
                }
            }

            <!--让返回的错误信息3秒后消失-->
            $(function(){
                $("#message").show().delay(3000).fadeOut();
            })
            
            function sendcode() {
                isonclick = true;
               $.ajax({
                   url:'sendcode',
                   type:"get",
                   data: "phonenumber="+document.getElementById("phonenumber").value.trim(),
                   success: function (data) {
                       sweetAlert("您的验证码已经发送，请注意查收");
                   }
               })
            }
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
            
            #resigter{
                position: absolute;
                top: 150px;
                left: 620px;
                width: 300px;
                height: 400px;
                background-color: white;
            }
            
            #resigtertitle{
                position: absolute;
                top:0px;
                left: 20px;
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
                top: 130px;
                width: 260px;
            }
            
            .d3{
                position: absolute;
                left: 21px;
                top: 180px;
                width: 260px;
            }
            
            .d4{
                position: absolute;
                left: 21px;
                top: 230px;
                width: 260px;
            }

            .form-control{
                position: absolute;
                left: 1px;
                top: 1px;
            }
            
            #yanzhenmabutton{
                position: absolute;
                left: 21px;
                top: 280px;
            }
            
            #yanzhengma{
                position: absolute;
                left: 160px;
                top: 280px;
                width: 124px;
            }
            
            #resigterbutton{
                position: absolute;
                left: 21px;
                top: 330px;
            }
            
            #resetbutton{
                position: absolute;
                left: 170px;
                top: 330px;
            }
            
            #link{
                position: absolute;
                left: 86px;
                top: 376px;
            }
            
            #message{
                position: absolute;
                left: 160px;
                top: 33px;
            }
        </style>
    </head>
    <body>
        <div id="container">
            
            <div id="resigter">
                <div id="resigtertitle">
                    <h2>Sign up</h2>
                </div>
                <div id="message">
                    <p>${message}</p>
                </div>
                <div>
                    <form>
                        <div class="input-group d1">
                            <span class="input-group-addon glyphicon glyphicon-user" id="basic-addon1"></span>
                            <input type="text" id="phonenumber" class="form-control" placeholder="请输入您的手机号码" name="phonenumber" aria-describedby="basic-addon1">
                        </div>
                        <div class="input-group d2">
                            <span class="input-group-addon glyphicon glyphicon-asterisk" id="basic-addon2"></span>
                            <input type="password" id="password" class="form-control" placeholder="请输入密码" name="password" aria-describedby="basic-addon1">
                        </div>
                        <div class="input-group d3">
                            <span class="input-group-addon glyphicon glyphicon-asterisk" id="basic-addon3"></span>
                            <input type="password" id="password_again" class="form-control" placeholder="请再次输入密码" name="password_again" aria-describedby="basic-addon1">
                        </div>
                            <div class="input-group d4">
                                <span class="input-group-addon glyphicon glyphicon-cog" id="basic-addon4"></span>
                                <input type="text" id="username" class="form-control" placeholder="请输入您的用户名" name="username" aria-describedby="basic-addon1">
                            </div>
                        <button type="button" class="btn btn-info" id="yanzhenmabutton" title="用于进行用户身份认证" onclick="sendcode()">点击发送验证码</button>
                        <input type="text" id="yanzhengma" placeholder="请填写验证码"  class="form-control"  aria-describedby="basic-addon1">
                        <button type="button" class="btn btn-info" id="resigterbutton" onclick="userresigter()">点击进行注册</button>
                        <button type="reset" class="btn btn-info" id="resetbutton">重新填写</button>
                        <div id="link">
                            <a href="homepage.html">点击此处返回首页</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
