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
    <script src="js/sweetalert.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/sweetalert.css">
    <script>
        var isonclick = false;
        function check() {
            var phonenumber = document.getElementById("phonenumber").value.trim();
            
            var newpassword = document.getElementById("newpassword").value.trim();
            var newpassword_again = document.getElementById("newpassword_again").value.trim();
            var yanzhengma = document.getElementById("yanzhengma").value.trim();
            if(phonenumber == "" || yanzhengma == "" || newpassword == "" || newpassword_again == ""){
                sweetAlert("请将信息填写完整");
            }else if(newpassword !== newpassword_again){
                sweetAlert("两次输入的密码不一致");
            }else if(isonclick == false){
                sweetAlert("请先请求验证码");
            } else{
                var params = {};
                params.phonenumber = phonenumber;
                params.newpassword = newpassword;
                params.yanzhengma = yanzhengma;
                
                $.ajax({
                    async:false,
                    url:"changepassword",
                    type:"post",
                    data:params,
                    datatype:'json',
                    success: function (data) {
                        if(data.code == "0"){
                            sweetAlert("没有此用户");
                        }else if(data.code == "1"){
                            window.location.href = "login";
                        }else{
                            sweetAlert("验证码错误");
                        }
                    }
                })
            }
        }

        function sendcode() {
            isonclick = true;
            $.ajax({
                url:'sendcode1',
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
        
        #yanzhengmabutton{
            position: absolute;
            top: 130px;
            left: 21px;
            width: 100px;
        }
        
        #yanzhengma{
            position: absolute;
            top: 130px;
            left: 140px;
            width: 133px;
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
                <input type="text" id="phonenumber" class="form-control" placeholder="请输入您的手机号码" name="phonenumber" aria-describedby="basic-addon1">
            </div>
            <div class="input-group d2">
                <button type="button" class="btn btn-info" id="yanzhengmabutton" onclick="sendcode()">发送验证码</button> 
                <input type="text" id="yanzhengma" class="form-control" placeholder="请输入验证码" name="yanzhengma" aria-describedby="basic-addon1">
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
