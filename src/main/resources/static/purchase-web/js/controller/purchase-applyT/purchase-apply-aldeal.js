$(function () {
    var currPage = 1;
    var infoId = $.getQueryString('id'), status = $.getQueryString('status'), listAn = [];
    function everWuLiao(id1, id2) {
        var param = {};
        param.taskId = id1;
        param.matTypeId = id2;
        return param;
    }
    // 获取物料的供应商
    function getGongYingShang(id1, id2, name, number, guige, xinghao) {
        var dataM = everWuLiao(id1, id2);

        $.getData({
            url: '/listOrDetail/taskMatDetail',
            query: dataM
        }, function (data) {
            if (data && data.list) {
                listAn = data.list;
                for (var i = 0; i < listAn.length; i++) {
                    listAn[i].name = name;
                    listAn[i].number = number;
                    listAn[i].guige = guige;
                    listAn[i].xinghao = xinghao;
                }
                console.log(JSON.stringify(listAn));
            }
        })
    }
    // 获取未处理列表
    function getListN(pageNum, isStatus) {
        $.getData({
            url: '/listOrDetail/planTaskDetail',
            query: {
                id: infoId
            }
        }, function (data) {
            if (data) {
                $.renderPage({
                    count: data.count,
                    curr: currPage,
                    jump: function (n) {
                        currPage = n;
                        getListN(n);
                    }
                });
                renderDetailUp(data);
                if (data.planDetailList) {
                    matrList = data.planDetailList;
                    $('.pages').css('display', 'block');
                    if (status == 1) { // 已处理
                        renderDetailDownY(data.planDetailList);
                    } else if(status == 2) { // 未处理
                        renderDetailDown(data.planDetailList);
                    } else {
                        renderUndefine(data.planDetailList);
                    }
                } else {
                    $('.pages').css('display', 'none');
                    renderEmpty();
                }
            }
        });
    }

    $(document).on('click', '.js-xq', function () {
        var dataId = $(this).attr('data-id');
        $.getData({
            url: '/listOrDetail/detailCompList',
            query: {
                detailId: dataId
            }
        }, function (data) {
            $.pop({
                title: '采购详情',
                content: template('purchase-applyT/materialC', {
                    items: data
                }),
                size: 'lg',
                isCancel: true,
                yes: function () {

                }
            });
        });

    });
    // 渲染up
    function renderDetailUp(data) {
        if (data.taskDept) {
            data.taskDept = JSON.parse(data.taskDept)[0].title;
        }
        $('.js-main-up').html(template('purchase-applyT/purchase-apply-detail-alup', {
            items: data
        }));
    }

    // 渲染down--未处理
    function renderDetailDown(data) {
        $('#untreated').removeClass('hide');
        $('#untreatedList').html(template('purchase-applyT/purchase-apply-detail-downa', {
            items: data
        }));
    }
    // 点击已处理的操作
    function renderUndefine(data){
        $('#undeMy').removeClass('hide');
        $('#undeList').html(template('purchase-applyT/render-undefine', {
            items: data
        }));
    }
    // 渲染down--已处理
    function renderDetailDownY(data) {
        $('#processed').removeClass('hide');
        $('#processedList').html(template('purchase-applyT/purchase-apply-detail-downa-y', {
            items: data
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-main-down').html(template('common/record-empty', {
            colspan: 10
        }));
    }
    // 根据状态修改标题及按钮
    function changeTitle() {
        if (status == 2) {
            $('.js-title h4').text("采购申请详情-未处理");
            $('#submit').show();
        } else {
            $('.js-title h4').text("采购申请详情-已处理");
            $('#submit').hide();
        }
    }
    $(document).on('click', '.js-detail-my', function () {
        var id1 = $(this).parent().attr('data-id');
        var id2 = $(this).parent().attr('mattypeid');
        var name = $(this).attr('name');
        var number = $(this).attr('number');
        var guige = $(this).attr('guige');
        var xinghao = $(this).attr('xinghao');
        getGongYingShang(id1, id2, name, number, guige, xinghao);
        setTimeout(function () {
            $.pop({
                title: '采购详情',
                content: template('purchase-applyT/materialB', {
                    items: listAn
                }),
                size: 'lg',
                noIcon: true,
                isCancel: false,
                yes: function () {
                    console.log('true');
                }
            });
        }, 500);
    });
    function init() {
        // 点击返回
        $(document).on('click', '.js-back', function () {
            window.history.go(-1);
        });
        // 点击处理
        $(document).on('click', '#submit', function () {
            location.href = "./purchase-apply-deal.html?id=" + infoId;
        });
        if (status == 1) {
            document.title = '采购申请详情--已处理';
            getListN();
        } else {
            document.title = '采购申请详情--未处理';
            getListN();
        }
        changeTitle();
    }
    init();
});
