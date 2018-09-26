//设置bootstrap模态框居中
$(function () {
    // dom加载完毕
    var $m_btn = $('#modalBtn');
    var $modal = $('#myModal');
    $m_btn.on('click', function () {
        $modal.modal({backdrop: 'static'});
    });

    // 测试 bootstrap 居中
    $modal.on('show.bs.modal', function () {
        var $this = $(this);
        var $modal_dialog = $this.find('.modal-dialog');
        // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
        $this.css('display', 'block');
        $modal_dialog.css({'margin-top': Math.max(0, ($(window).height() - $modal_dialog.height()) / 2)});
    });

});
