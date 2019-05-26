$(function () {
    function renderList(data) {
        $('.js-join').html(template('plan/type-list', { 
            items: data 
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 4
        }));
    }
    // 获取列表
    function getList(pageCurr = 1, isFirst = true) {
        $.getData({
            url: '/roomResource/getRoomTypeList',
            query: {
                pageNo: pageCurr, //传入的是第几页
                pageSize: 10  //我需要的数据是几条
            }
        }, function (data) {
            console.log(data)
            if (data) {
                renderList(data.list);
                let total = data.count;  //后台返回的数据总条数
                isFirst && $.renderPage({
                    count: total, //数据总条数
                    curr: pageCurr, //展示的是第几页
                    jump: function (num) {
                        currPage = num;
                        getList(currPage);
                    }
                });
            }

        });
    }
    //删除会议室类型
    function delType(rtId, deleteNum) {
        if (deleteNum == 1) {
            $.msg("数据有关联，无法删除");
            return;
        } else {
            $.getData({
                url: '/roomResource/deleteRoomType',
                query: {
                    rtId
                }
            }, function (data) {
                if (data) {
                    $.toast("删除成功");
                    getList();
                }
            });
        }
    }
    function init() {
        //获取列表
        getList();
        //点击删除
        $(document).on('click', '.js-del', function () {
            let rtId = $(this).data('id'), deleteNum = $(this).data('delete');
            console.log(deleteNum)
            // $.confirm('确定删除当前类型？', function () {
            //     delType(rtId, deleteNum);
            // });
            $.bubble({
                el: $(this),
                msg: '确定删除当前类型？',
                ok: function () {
                    delType(rtId, deleteNum);
                },
                cancel: function () {
                    $.toast('您已取消');
                }
            });
        });
    }
    init();
});