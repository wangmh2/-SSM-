//导航条的JS
$("nav>div").css("left", function (i) {
        return i * 220;
    }
);
$("nav>div").click(function () {
    var href = $(this).children().eq(0).attr("href");
    $(window).attr("location", href);
});
