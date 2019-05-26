$(function () {
    var id = $.getQueryString('id');
    var sid = $.getQueryString('sid');
    var currPage = 1;
    //获取详情
    function getInfo(id) {
        $.getData({
            url: '/supplier/getById',
            query: {
                id: id
            },
        }, function (data) {
            if (data) {
                var arr =[];
                arr.push(data.licenseUrl);
                arr.push(data.cardAUrl);
                arr.push(data.cardBUrl);
                data.arr = arr
                $('#headTXT').text('供应商详情'+'-'+ data.name);
                $('.js-detail').html(template('archive-detail', {item: data}));
                getcontance(id)
            } else {
                $.alert("获取数据失败!")
            }
        });
    }
    //获取合同
    function getcontance(id) {
        $.getData({
            url: '/htContract/htContractPageList',
            query: {
                id: id,
                pageNum: currPage,
                pageSize: 10
            },
        }, function (data) {
            $.renderPage({
                count: data.count,
                curr: currPage,
                jump: function (n) {
                    currPage = n;
                    getcontance(id);
                }
            });
            if (data.list && data.list.length) {
                $('.js-list').html(template('contanct-list', {
                    items: data.list,
                    sid : sid
                }));
            } else {
                renderEmpty()
            }
        });
    }
    // 渲染数据为空
    function renderEmpty(text) {
        $('.js-list').html(template('common/record-empty', {
            colspan:7,
            text: '暂无数据' || text
        }));
    }
    function init() {
        getInfo(id)
        $(document).on('click', '.js-ToFile', function () {
            var fileUrl = $(this).data('url');
            var fileName = $(this).data('name');
            window.location.href = serverUrl+'/uploadDown/downLoadFile?filePath=http://192.168.144.246:20001'+fileUrl+'&filename='+fileName+'';
        })
        $(document).on('click', '.btn-back', function () {
            window.history.back();
        })

    }

    init();
});