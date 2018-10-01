/*使用goEasy的推送功能及生成弹幕*/
var goEasy = new GoEasy({
    appkey: 'BC-f584732e29df466ab9c4efc6fc3d47e7'
});

//按回车的时候会推送
$("#inputText").keydown(function () {
    if (event.keyCode == 13) {
        goEasy.publish({
            channel: 'demo_channel',
            message: $("#userNickname").val() + "：" + $("#inputText").val()
        });
        //推送完清空输入框
        $("#inputText").val("");
    }
});

//单击按钮的时候会推送
$("#btn").click(function () {
    goEasy.publish({
        channel: 'demo_channel',
        message: $("#userNickname").val() + "：" + $("#inputText").val()
    });
});

//接收数据、数据形式是弹幕
goEasy.subscribe({
    channel: 'demo_channel',
    onMessage: function (message) {
        var txt = message.content;
        var item = {
            img: path + '/imgs/favicon.ico', //图片
            info: txt, //文字
            /*href: 'www.baidu.com',*/ //链接
            close: true, //显示关闭按钮
            speed: 15, //延迟,单位秒,默认8
            bottom: 500, //距离底部高度,单位px,默认随机
            color: '#fff', //字体颜色颜色,默认白色
            old_ie_color: '#fff' //ie低版兼容色,不能与网页背景相同,默认黑色
        };
        $('body').barrager(item);
    }
});

