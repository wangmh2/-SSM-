<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/9/19
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户界面</title>
    <link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css"/>

    <!--Jquery-->
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>

    <!--背景CSS-->
    <link rel="stylesheet" media="screen" href="css/background.css">

    <link rel="stylesheet" href="css/bootstrap.css"/>

    <!--导航条CSS-->
    <link rel="stylesheet" href="css/navbar.css"/>


    <script src="js/sweetalert.min.js"></script>

    <link rel="stylesheet" type="text/css" href="css/sweetalert.css">

    <!--使用JS获取项目根路径-->
    <script>
        var path = "";
        $(function () {
            var strFullPath = window.document.location.href;
            var strPath = window.document.location.pathname;
            var pos = strFullPath.indexOf(strPath);
            var prePath = strFullPath.substring(0, pos);
            var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
            path = prePath ;
        });
    </script>

    <!--清除所有默认样式-->
    <link rel="stylesheet" href="css/cleanDefault.css"/>

    <!--获取天气预报-->
    <script>
        $(function () {
            /*先获取位置、再获取天气预报信息*/
            $.ajax({
                url: "http://restapi.amap.com/v3/ip?key=2fee96173203217eda5f6d469180ffd0",
                type: "get",
                success: function (responseText1) {
                    $.ajax({
                        url: "https://free-api.heweather.com/s6/weather/forecast?key=145b0dbb7bd04b8a942f12f22256ccb9&location=" + responseText1.city,
                        type: "get",
                        success: function (responseText2) {

                            $(".jumbotron h1").html(responseText1.city + "近三日天气");

                            var jsonObj = responseText2.HeWeather6[0].daily_forecast;
                            for (var i = 0; i < jsonObj.length-4; i++) {


                                var date = jsonObj[i].date;
                                var weather = jsonObj[i].cond_txt_d;
                                var low = jsonObj[i].tmp_min;
                                var hight = jsonObj[i].tmp_max;

                                $(".jumbotron").append("<p>" + date + "：" + weather + "，温度：" + low + "～" + hight + "℃</p>");

                            }
                        },
                        error: function () {
                            sweetAlert("获取天气失败...")
                        }
                    });

                },
                error: function () {
                    sweetAlert("定位失败...")
                }
            });
        });
        
        function tishi(){
            sweetAlert("此功能还未开发完全");
        }
        
    </script>

    <!--天气预报样式-->
    <style>
        .jumbotron {
            text-align: center;
            padding-top: 115px;
            padding-bottom: 30px;
            margin-bottom: 30px;
            color: #c7c7c7;
            background-color: none;
        }
        .jumbotron h1 {
            font-size:70px;
            color: inherit;
            margin-bottom: inherit;
        }
        .jumbotron p{
            font-size: 25px;
        }
        
        #useremail{
            position: absolute;
            top: 10px;
        }

    </style>
</head>
<body>


<!-- 导航条被嵌套在背景中 -->
<div id="particles-js">
    <!--导航条-->
    <nav>
        
        <div onclick="tishi()">
            
                <div>
                    <h1>在线弹幕聊天</h1>
                    <span></span>
                </div>
                <div>
                    <h1>在线弹幕聊天</h1>
                    <span></span>
                </div>
            </a>
        </div>
        <div onclick="tishi()">
           
                <div>
                    <h1>备忘录</h1>
                    <span></span>
                </div>
                <div>
                    <h1>备忘录</h1>
                    <span></span>
                </div>
            </a>
        </div>
        <div>
            <a href="message">
                <div>
                    <h1>留言板</h1>
                    <span></span>
                </div>
                <div>
                    <h1>留言板</h1>
                    <span></span>
                </div>
            </a>
        </div>
        
        <div>
            <a href="homepage.html">
                <div>
                    <h1>退出登录</h1>
                    <span></span>
                </div>
                <div>
                    <h1>退出登录</h1>
                    <span></span>
                </div>
            </a>
        </div>
        <div>
            
        </div>
        <div id="useremail">
            <p>尊敬的用户<%=session.getAttribute("username")%><%=session.getAttribute("time")%>
        </div>
    </nav>
    

    <!--天气预报-->
    <div class="jumbotron text-center" style="position: absolute;width: 100%" id="weather">
        <h1></h1>

    </div>
</div>


<!--导航条JS-->
<script src="js/narbar.js"></script>

<!-- 背景JS -->
<script src="js/background/particles.js"></script>
<script src="js/background/app.js"></script>

</body>

</html>
