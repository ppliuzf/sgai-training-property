$(function () {
    var infoId = $.getQueryString('infoId');
    var name = null;
    var currPage = 1;
    // 渲染select
    function getCangKu() {
        $.getData({
            url: '/Inventories/findAllBhByivtNo',
            query: {
                ivtNo: infoId
            }
        }, function (data) {
            for (var m = 0; m < data.length; m++) {
                $('#detailCangKu').append('<option value="' + data[m].whId + '">' + data[m].whName + '</option>')
            }
        })
    }
    // 获取上内容 
    function getContent() {
        $.getData({
            url: '/Inventories/detailNew',
            query: {
                ivtNo: infoId
            }
        }, function (data) {
            if (data) {
                
                name = data.whName;
                if (data.ivtPiJson) {
                    ivtPiJson = data.ivtPiJson;
                    position = JSON.parse(ivtPiJson).title;
                    data.position = position;
                } else {
                    data.position = '无';
                }
                if (data.matStat == 1) {
                    $('#status').html('未开始');
                    $('#act').removeClass('hidden');
                    $('#edit').removeClass('hidden');
                    $('#delete').removeClass('hidden');
                } else if (data.matStat == 2) {
                    $('#act').removeClass('hidden');
                    $('#status').html('盘点中');
                } else {
                    $('#status').html('已完成');
                }
                renderMain(data);
            }
        });
    }
    // 获取table
    function table(pageNum) {
        $.getData({
            url: '/Inventories/excuteInventMatPage',
            query: {
                whId: $('#detailCangKu').find('option:selected').val() ? $('#detailCangKu').find('option:selected').val() : '',
                ivtNo: infoId,
                pageNum: pageNum || 1,
                pageSize: 10
            }
        }, function (data) {
            if (data && data.list && data.list.length) {
                $('.pages').css('display', 'block');
                total = data.count;
                $.renderPage({
                    count: total,
                    curr: currPage,
                    jump: function (n) {
                        table(n);
                        currPage = n;
                    }
                });
                for (var i = 0; i < data.list.length; i++) {
                    data.list[i].itemName = name;
                    if (!data.list[i].matRealNum) {
                        data.list[i].matRealNum = 0;
                    }
                    data.list[i].matDiffNum = data.list[i].matNum - data.list[i].matRealNum;
                }
                renderTable(data.list);
            } else {
                $('.pages').css('display', 'none');
                renderEmpty();
            }
        });
    }
    // 上模板
    function renderMain(data) {
        console.log(JSON.stringify(data));
        if (data) {
            $('.js-main').html(template('check/check-detail', {
                items: data
            }));
        }
    }
    // 渲染table模板
    function renderTable(data) {
        $('.js-list').html(template('check/check-detail-list', {
            items: data
        }));
    }

    // 渲染table无数据模板
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 7
        }));
    }
    // 删除
    function delItem() {
        $.getData({
            url: '/Inventories/del',
            query: {
                ivtNo: infoId
            }
        }, function (data) {
            if (data) {
                $.msg('操作成功', {
                    time: 2000
                });
                location.href = './check-list.html';
            }
        });
    }
    // 数据
    function sd() {
        var rs = {};
        rs.ivtNo = infoId;
        return rs;
    }
    // 点击执行前先执行这个
    function actMain() {
        var data = sd();
        $.getData({
            url: '/Inventories/begin',
            query: data
        }, function () {
            console.log('@@@@');
        });
    }
    function init() {
        getCangKu();
        getContent();
        setTimeout(() => {
            table();
        }, 500);
        // 返回
        $(document).on('click', '#back', function () {
            window.history.go(-1);
        });
        // 执行
        $(document).on('click', '#act', function () {
            actMain();
            window.location.href = './check-act.html?infoId=' + infoId + '&state=' + $.getQueryString('state');
        });
        // 编辑
        $(document).on('click', '#edit', function () {
            window.location.href = './check-add.html?infoId=' + infoId;
        });
        // 删除
        $(document).on('click', '#delete', function () {
            var $this = $(this);
            $.pop({
                title: '提示',
                content: '确认要删除该盘点吗？',
                btn: ['确认', '取消'],
                size: 'sm',
                yes: function () {
                    delItem();
                }
            });
        });
        // 
        $(document).on('change', '#detailCangKu', function () {
            table();
        })
        // 分页
        $.renderPage({ count: 100 })
    }
    init();
});
