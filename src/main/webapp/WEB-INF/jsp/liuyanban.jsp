<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/9/21
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>留言板</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="js/sweetalert.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/sweetalert.css">
    
    <style>
        #container{
            position:absolute;
            top:0px;
            left:0px;
            background-color: #e91d2a;
            width: 100%;
            height: 100%;
        }
        
        #liuyanban{
            position: absolute;
            top: 30px;
            left: 460px;
            width: 650px;
            height: 610px;
            background-color: white;
        }
        
        #liuyanbantitle{
            position: absolute;
            left: 25px;
        }
        
        #table1{
            position: absolute;
            left: 5px;
            top: 60px;
            width: 640px;
            height: 500px;
        }
        
        #table2{
            width: 100%;
            height: 100%;
        }
        
        #anniu{
            position: absolute;
            left: 190px;
            top: 500px;
        }
        
        #amessage{
            position: absolute;
            left: 540px;
            top: 660px;
            width: 400px;
        }
        
        #tucaobutton{
            position: absolute;
            left: 960px;
            top: 660px;
            width: 80px;
        }
        
        #dangqianye{
            position: absolute;
            left: 5px;
            top: 510px;
        }
        
        #fanhui{
            position: absolute;
            left: 500px;
            top: 510px;
        }
    </style>
</head>
<body>  
    <div id="container">
        <div id="liuyanban">
            <div id="liuyanbantitle">
                <h2>留言板</h2>
            </div>
            <div id="table1">
                <table class="table table-hover"  id="table2">
                    <tr style="height: 50px;">
                        <th style="text-align: center;vertical-align: middle;">用户名</th>
                        <th style="text-align: center;vertical-align: middle;">他/她的留言</th>
                        <th style="text-align: center;vertical-align: middle;">留言时间</th>
                    </tr>
                    <c:forEach items="${message1}" var="message">
                        <tr>
                            <td style="text-align: center;vertical-align: middle;">${message.username}</td>
                            <td style="text-align: center;width: 350px;vertical-align: middle;">${message.info}</td>
                            <td style="text-align: center;vertical-align: middle;">${message.createtime}</td>
                        </tr>
                    </c:forEach>
                </table>
                <div id="dangqianye">
                    <p>当前是第<%=session.getAttribute("dangqianpageno")%>页</p>
                </div>
                <div id="anniu">
                    <button class="btn btn-info" id="lastbutton" onclick="last()">上一页</button>
                    <button class="btn" id="btn1" onclick="one()">1</button>
                    <span id="sufPoint">...</span>
                    <button class="btn " id="btn7" onclick="thelast()"><%=session.getAttribute("pagenum")%></button>
                    <button class="btn btn-info" id="nextbutton" onclick="next()">下一页</button>
                    
                </div>
                <div id="fanhui">
                    <a href="userpage">返回用户界面</a>
                </div>
            </div>
        </div>
    </div>
    <input type="text" id="amessage" class="form-control" placeholder="我也要吐槽这个破网站(请不要超过64个字)" name="tucao" aria-describedby="basic-addon1" />
    <button type="button" class="btn btn-info" id="tucaobutton" onclick="addmessage()">吐槽</button>
</body>
<script type="text/javascript">
    function addmessage(){
        var info = document.getElementById("amessage").value.trim();
        var username = "<%=session.getAttribute("username")%>";
        if(info == ""){
            sweetAlert("朋友您确定不吐槽一下我的这个破网站吗");
        }else{
            var params = {};
            params.username = username;
            params.info = info;
            $.ajax({
                async:false,
                url:"addmessage",
                type:"post",
                data:params,
                datatype:'json',
                success: function (data) {
                    if(data.code == "0"){
                        location.reload();
                        sweetAlert("留言成功");
                        
                    }else{
                        sweetAlert("留言失败");
                    }
                    
                }
            })
        }
    }
    
    function last() {
        var dangqianpageno = "<%=session.getAttribute("dangqianpageno")%>";
        if(dangqianpageno == 1){
            sweetAlert("前面没有了哦！");
        }else{
            var pageno = parseInt(dangqianpageno) - parseInt(1);
            location.href = "changemessage?pageno="+pageno;
        }
        
    }
    
    function next() {
        var dangqianpageno = "<%=session.getAttribute("dangqianpageno")%>";
        var pagenum = "<%=session.getAttribute("pagenum")%>";
        if(dangqianpageno == pagenum){
            sweetAlert("后面没有了哦！");
        }else{
            var pageno = parseInt(dangqianpageno) + parseInt(1);
            location.href = "changemessage?pageno="+pageno;
        }
    }
    
    function one(){
        location.href = "changemessage?pageno="+1;
    }
    
    function thelast() {
        var pageno = "<%=session.getAttribute("pagenum")%>";
        location.href = "changemessage?pageno="+pageno;
    }
</script>
</html>
