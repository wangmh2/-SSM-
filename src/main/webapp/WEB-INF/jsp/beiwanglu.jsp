<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/10/5
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>备忘录</title>
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
        
        #beiwanglu{
            position: absolute;
            top: 30px;
            left: 460px;
            width: 650px;
            height: 610px;
            background-color: white;
        }
        
        #plus{
            position: absolute;
            top: 550px;
            left: 500px;
        }

        #table1{
            position: absolute;
            left: 5px;
            top: 60px;
            width: 640px;
            height: 500px;
        }
        
        #div1{
            position: absolute;
            display: none;
            left: 220px;
            top: 250px;
           
            width:250px;
            height:150px;
            text-align: center;
            background-color: gray;
        }
        
        #beiwangtime{
            position: absolute;
            left: 30px;
            top: 20px;
            width: 200px;
        }
        
        #address{
            position: absolute;
            left: 30px;
            top: 70px;
            width: 200px;
        }
        #quedingbutton{
            position: absolute;
            left: 20px;
            top: 110px;
            width: 100px;
        }
        
        #guanbibutton{
            position: absolute;
            left: 140px;
            top: 110px;
            width: 100px;
        }
    </style>
</head>
<body>
    <div id="container">
        <div id="beiwanglu">
            <div>
                <div id="table1">
                    <table class="table table-hover"  id="table2">
                        <tr style="height: 50px;">
                            <th style="text-align: center;vertical-align: middle;">用户名</th>
                            <th style="text-align: center;vertical-align: middle;">时间</th>
                            <th style="text-align: center;vertical-align: middle;">地点</th>
                        </tr>
                        <c:forEach items="${beiwang}" var="beiwang">
                            <tr>
                                <td style="text-align: center;vertical-align: middle;">${beiwang.username}</td>
                                <td style="text-align: center;width: 350px;vertical-align: middle;">${beiwang.beiwangtime}</td>
                                <td style="text-align: center;vertical-align: middle;">${beiwang.address}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div id="plus">
                    <button type="button" class="btn btn-info" id="plusbutton" onclick="addbeiwanglu()">添加备忘事项</button>
                </div>
                <div id="div1">
                    <input type="text" id="beiwangtime" placeholder="时间"  class="form-control"  name="beiwangtime" aria-describedby="basic-addon1">
                    <input type="text" id="address" placeholder="地点"  class="form-control"  name="address" aria-describedby="basic-addon1">
                    <button type="button" class="btn btn-info" id="quedingbutton" onclick="queding()">确定添加</button>
                    <button type="button" class="btn btn-info" id="guanbibutton" onclick="guanbi()">关闭</button>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    function addbeiwanglu() {
        document.getElementById("div1").style.display = "block";
    }
    
    function guanbi(){
        document.getElementById("div1").style.display = "none";
    }
    
    function queding() {
        var beiwangtime = document.getElementById("beiwangtime").value.trim();
        var address = document.getElementById("address").value.trim();
        if(beiwangtime == "" || address == ""){
            sweetAlert("请将信息填写完整");
        }else{
            params = {};
            params.beiwangtime = beiwangtime;
            params.address = address;
            $.ajax({
                url:"addbeiwang",
                type:"post",
                data:params,
                datatype:'json'
                success: function (data) {
                    if(data.code == "0"){
                        sweetAlert("备忘成功，我们将在时间发生一个小时前给你发送提醒短信");
                    }else{
                        sweetAlert("备忘失败");
                    }
                }
            })
        }
    }
</script>
</html>
