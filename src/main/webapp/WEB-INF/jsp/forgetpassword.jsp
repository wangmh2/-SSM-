<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/9/20
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script>
        function check() {
            var useremail = document.getElementById("useremail").value.trim();
            var password_protection = document.getElementById("password_protection").value.trim();
            var newpassword = document.getElementById("newpassword").value.trim();
            var newpassword_again = document.getElementById("newpassword_again").value.trim();
            if(useremail == "" || password_protection == "" || newpassword == "" || newpassword_again == ""){
                alert("请将信息填写完整");
            }else if(newpassword !== newpassword_again){
                alert("两次输入的密码不一致");
            }else{
                location.href = "changepassword?useremail="+useremail+"&newpassword="+newpassword+"&password_protection="+password_protection;
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
        
        #changepassord{
            width: 300px;
            height: 350px;
            position: absolute;
            left: 620px;
            top: 150px;
            background-color: white;
        }
        
        #changetitle{
            position: absolute;
            top:2px;
            left: 25px; 
        }
        
        .d1{
            position: absolute;
            top: 80px;
            left: 21px;
            width: 250px;
        }
        
        .d2{
            position: absolute;
            top: 130px;
            left: 21px;
            width: 250px;
        }
        
        .d3 {
            position: absolute;
            top: 180px;
            left: 21px;
            width: 250px;
        }
        
        .d4{
            position: absolute;
            top: 230px;
            left: 21px;
            width: 250px;
        }
        
        .form-control {
            position: absolute;
            left: 1px;
            top: 1px;
        }
        
        #quedingbutton {
            position: absolute;
            left: 50px;
            top: 280px;
        }
        
        #resetbutton{
            position: absolute;
            left: 160px;
            top: 280px;
        }
        
        #message{
            position: absolute;
            top: 27px;
            left: 140px;
            width: 120px;
            height: 50px;
        }
    </style>
</head>
<body>
    <div id="container">
        <div id="changepassord">
            <div id="changetitle">
                <h3>更改密码</h3>
            </div>
            <div id="message">
                <p>${message}</p>
            </div>
            <div class="input-group d1">
                <span class="input-group-addon glyphicon glyphicon-user" id="basic-addon1"></span>
                <input type="text" id="useremail" class="form-control" placeholder="请输入您的邮箱" name="useremail" aria-describedby="basic-addon1">
            </div>
            <div class="input-group d2">
                <span class="input-group-addon glyphicon glyphicon-user" id="basic-addon2"></span>
                <input type="text" id="password_protection" class="form-control" placeholder="请输入您的密保号" name="password_protection" aria-describedby="basic-addon1">
            </div>
            <div class="input-group d3">
                <span class="input-group-addon glyphicon glyphicon-user" id="basic-addon3"></span>
                <input type="password" id="newpassword" class="form-control" placeholder="请输入您的新密码" name="newpassword" aria-describedby="basic-addon1">
            </div>
            <div class="input-group d4">
                <span class="input-group-addon glyphicon glyphicon-user" id="basic-addon4"></span>
                <input type="password" id="newpassword_again" class="form-control" placeholder="请再次输入您的新密码" name="newpassword_again" aria-describedby="basic-addon1">
            </div>
            <button type="button" class="btn btn-info" id="quedingbutton" onclick="check()">确定更改</button>
            <button type="reset" class="btn btn-info" id="resetbutton">重新填写</button>
        </div>
    </div>
</body>
</html>
