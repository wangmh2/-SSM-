/*添加网站的ajax*/
function addSiteFunc() {
    $.ajax({
        url: path + "/favorites/addSite.do",
        type: "post",
        data: $("#siteForm").serialize(),
        success: function (responseText) {
            console.log(responseText);
            if (responseText == "success") {
                sweetAlert("添加网站成功，请继续添加");
                //将输入框的内容清空
                $("#inputSiteAddr").val("");
                $("#inputSiteName").val("");
            } else if (responseText == "hasWebSiteName") {
                sweetAlert("命名已存在，请换别的命名");
                $("#inputSiteName").val("");
            } else {
                sweetAlert("添加失败");
            }
        },
        error: function () {
            sweetAlert("系统错误了，请重新添加");
        }
    });
}

$(function () {

    //鼠标单击按钮提交
    $("#addSite").click(function () {
        addSiteFunc();

    });

    //回车也能提交
    $("#inputSiteName").keydown(function () {
        if (event.keyCode == 13 && $.trim($("#inputSiteName").val()).length > 0) {
            addSiteFunc();
        }
    });


});


/*下拉框提示、请求后台，返回数据给页面智能提示*/
$(function () {

    //实现搜索输入框的输入提示js类
    function oSearchSuggest(searchFuc) {
        var input = $('#condition');
        var suggestWrap = $('#gov_search_suggest');
        var key = "";
        var init = function () {
            input.bind('keyup', sendKeyWord);
            input.bind('blur', function () {
                setTimeout(hideSuggest, 100);
            })
        }
        var hideSuggest = function () {
            suggestWrap.hide();
        }

        //发送请求，根据关键字到后台查询
        var sendKeyWord = function (event) {
            //键盘选择下拉项
            if (suggestWrap.css('display') == 'block' && event.keyCode == 38 || event.keyCode == 40) {
                var current = suggestWrap.find('li.hover');
                if (event.keyCode == 38) {
                    if (current.length > 0) {
                        var prevLi = current.removeClass('hover').prev();
                        if (prevLi.length > 0) {
                            prevLi.addClass('hover');
                            input.val(prevLi.html());
                        }
                    } else {
                        var last = suggestWrap.find('li:last');
                        last.addClass('hover');
                        input.val(last.html());
                    }

                } else if (event.keyCode == 40) {
                    if (current.length > 0) {
                        var nextLi = current.removeClass('hover').next();
                        if (nextLi.length > 0) {
                            nextLi.addClass('hover');
                            input.val(nextLi.html());
                        }
                    } else {
                        var first = suggestWrap.find('li:first');
                        first.addClass('hover');
                        input.val(first.html());
                    }
                }

                //输入字符
            } else {
                var valText = $.trim(input.val());
                if (valText == '' || valText == key) {
                    return;
                }
                searchFuc(valText);
                key = valText;
            }

        }
        //请求返回后，执行数据展示
        this.dataDisplay = function (data) {
            if (data.length <= 0) {
                suggestWrap.hide();
                return;
            }

            //往搜索框下拉建议显示栏中添加条目并显示
            var li;
            var tmpFrag = document.createDocumentFragment();
            suggestWrap.find('ul').html('');
            for (var i = 0; i < data.length; i++) {
                li = document.createElement('LI');
                li.innerHTML = data[i];
                tmpFrag.appendChild(li);
            }
            suggestWrap.find('ul').append(tmpFrag);
            suggestWrap.show();

            //为下拉选项绑定鼠标事件
            suggestWrap.find('li').hover(function () {
                suggestWrap.find('li').removeClass('hover');
                $(this).addClass('hover');

            }, function () {
                $(this).removeClass('hover');
            }).bind('click', function () {
                input.val(this.innerHTML);
                suggestWrap.hide();
            });
        }
        init();
    }

    //实例化输入提示的JS,参数为进行查询操作时要调用的函数名
    var searchSuggest = new oSearchSuggest(sendKeyWordToBack);

    //这是一个模似函数，实现向后台发送ajax查询请求，并返回一个查询结果数据，传递给前台的JS,再由前台JS来展示数据。本函数由程序员进行修改实现查询的请求

    //参数为一个字符串，是搜索输入框中当前的内容
    <!--搜索数据-->
    function sendKeyWordToBack(keyword) {

        var obj = {
            "condition": $("#condition").val(),
            "userId": $("#userId").val()
        };
        $.ajax({
            type: "POST",
            url: path + "/favorites/querySiteCompletion.do",
            data: obj,
            dataType: "json",
            success: function (data) {

                console.log(data);
                var aData = [];
                for (var i = 0; i < data.length; i++) {
                    //以下为根据输入返回搜索结果的模拟效果代码,实际数据由后台返回
                    if (data[i] != "") {
                        aData.push(data[i]);
                    }
                }
                //将返回的数据传递给实现搜索输入框的输入提示js类
                searchSuggest.dataDisplay(aData);
            }
        });
    }
});



/*搜索数据，按回车和单击搜索都行*/

$(function () {
    //按回车后搜索数据
    $("#condition").keydown(function () {

        var $condition = $("#condition").val();
        if (event.keyCode == 13 && $.trim($condition).length > 0) {

            searchSite();
        }
    });
    //点击搜索按钮搜索数据
    $("#search").click(function () {
        //得到输入框的数据
        var $condition = $("#condition").val();

        if ($.trim($condition).length > 0) {
            searchSite();
        }
    });
});

//搜索数据的ajax
function searchSite() {
    //得到输入框的数据
    var $condition = $("#condition").val();
    var $userId = $("#userId").val();
    $.ajax({
        url: path + "/favorites/querySiteByCondition.do",
        type: "post",
        data: {condition: $condition, userId: $userId},
        success: function (responseText) {
            if (responseText.length == 0) {
                sweetAlert("您没有添加该网站");
                return;
            }

            //返回的是一个集合，我们遍历集合，
            for (var index in responseText) {
                var jsonObj = responseText[index];

                //获取JSON对象
                var data = eval('(' + jsonObj + ')');
                if (data.webSiteName == $condition) {
                    window.location.href = data.webSiteAddr;
                    $("#condition").val("");
                    return;
                }
            }
            sweetAlert("您没有添加该网站");
            return;
        },
        error: function () {
            sweetAlert("系统错误了，请重新添加");
        }
    });
}



/*根据Id查询索引记录*/

$(function () {
    $("#queryById").click(function () {
        var currentPage = $("#currentPage").val();
        if (currentPage == null || currentPage == "") {
            currentPage = 1;
        }
        $.ajax({
            url: path + "/favorites/querySiteById.do",
            type: "post",
            data: {userId: $("#userId").val(), currentPage: currentPage},

            success: function (responseText) {

                //返回的是一个Map集合，我们遍历Map集合，
                for (var index in responseText) {

                    //获取Map的value，index就代表Map的key
                    var jsonObj = responseText[index]

                    if (index.length > 16) {

                        //获取JSON对象
                        var data = eval("(" + jsonObj + ")");

                        $("#manageSiteContent").before("<tr><td><input type='text' value=" + data.webSiteAddr + " /></td><td><input type='text' value=" + data.webSiteName + " /></td><td><a href=" + path + "/favorites/deleteSiteById.do?indexId=" + index + ">删除</a></td></tr>");

                    } else {
                        if (index == "currentPage") {
                            $("#currentPage").val(jsonObj);
                        } else if (index == "totalPageCount") {
                            $("#totalPageCount").val(jsonObj);
                        } else {
                            $("#totalRecordCount").val(jsonObj);
                        }
                    }
                }
                createPage($("#totalPageCount").val());
            },
            error: function () {
                sweetAlert("系统错误了");
            }
        });
    });
});



/*当页面加载，就定位光标到输入框中，点击取消按钮时刷新页面*/
$(function () {
    $("#condition").focus();


    $("button[name='cancelOperate']").click(function () {
        window.location.reload();
    });
});



/*分页*/

//生成分页样式
function createPage(totalPageCount) {
    for (var i = 1; i <= totalPageCount; i++) {
        $(".pagination").append("<li><a href='#' onclick='queryPage(" + i + ")'>" + i + "</a></li>");
    }
}

//异步查询分页数据
function queryPage(currentPage) {
    //把原有的数据删除掉
    $("#manageSiteTr").siblings().empty();

    $.ajax({
        url: path + "/favorites/querySiteById.do",
        type: "post",
        data: {userId: $("#userId").val(), currentPage: currentPage},

        success: function (responseText) {

            //返回的是一个Map集合，我们遍历Map集合，
            for (var index in responseText) {

                //获取Map的value，index就代表Map的key
                var jsonObj = responseText[index]

                if (index.length > 16) {

                    //获取JSON对象
                    var data = eval("(" + jsonObj + ")");

                    $("#manageSiteContent").before("<tr><td><input type='text' value=" + data.webSiteAddr + " /></td><td><input type='text' value=" + data.webSiteName + " /></td><td><a href='${request.contextPath}/favorites/deleteSiteById.do?indexId=" + index + "'>删除</a></td></tr>");

                } else {
                    if (index == "currentPage") {
                        $("#currentPage").val(jsonObj);
                    } else if (index == "totalPageCount") {
                        $("#totalPageCount").val(jsonObj);
                    } else {
                        $("#totalRecordCount").val(jsonObj);
                    }
                }
            }

        },
        error: function () {
            sweetAlert("系统错误了");
        }
    });
}







