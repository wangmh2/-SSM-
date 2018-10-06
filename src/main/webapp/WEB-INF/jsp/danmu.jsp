<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/10/6
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>弹幕</title>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js"></script>
    <link rel="stylesheet" type="text/css" href="css/danmu.css">
</head>
<body>
<!-- 主屏幕 -->
<div class="screen">
    <!-- 播放器 -->
    <div id="a1" class="player"></div>
    <div class="inputArea">
        <div class="inputAndButton">
            <input type="text" class="inputText"/>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" class="textSubmit" value="发表"/>
        </div>
    </div>
</div>

<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="ckplayer/ckplayer.js"></script>
<script type="text/javascript" src="js/ckplayer_init.js" ></script>
</body>
</html>
