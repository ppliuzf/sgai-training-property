$(function () {
    var currPage = 1;
    var infoId = $.getQueryString('id');
    var gysList = [], optHtml = null, count = 0;
    // 物料
    var matrList = [];
    var mainDataList = [];
    function supplier() {
        $.getData({
            url: '/common/getComList'
        }, function (data) {
            gysList = data;
        });
    }
    // 获取列表
    function getList() {
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
                        getList(n);
                    }
                });
                renderDetailUp(data);
                if (data.planDetailList) {
                    matrList = data.planDetailList;
                    $('.pages').css('display', 'block');
                    renderDetailDown(data.planDetailList);
                    for (var i = 0; i < data.planDetailList.length; i++) {
                        mainDataList[i] = JSON.parse(window.sessionStorage.getItem($('#materielList tr').eq(i).data('id')));
                    }
                } else {
                    $('.pages').css('display', 'none');
                    renderEmpty();
                }
            }
        });
    }

    // 渲染up
    function renderDetailUp(data) {
        if (data.taskDept) {
            data.taskDept = JSON.parse(data.taskDept)[0].title;
        }
        $('.js-main-up').html(template('purchase-applyT/purchase-apply-detail-up', {
            items: data
        }));
    }
    // 渲染down
    function renderDetailDown(data) {
        $('.js-main-down').html(template('purchase-applyT/purchase-apply-detail-down', {
            items: data
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-main-down').html(template('common/record-empty', {
            colspan: 10
        }));
    }

    function saveData() {
        var count = [];
        var param = [], n = 0;
        for (var i = 0; i < mainDataList.length; i++) {
            if (mainDataList[i] && mainDataList[i].length > 0) {
                count[i] = 0;
                for (var j = 0; j < mainDataList[i].length; j++) {
                    count[i] += parseInt(mainDataList[i][j].buyCount);
                }
                if (count[i] > mainDataList[i][0].applyCount) {
                    $.toast("第" + (i + 1) + "条物料分配总数大于所需数量", { timer: 2000 });
                    return false;
                }
            }
            else {
                $.toast("请分配第" + (i + 1) + "条物料", { timer: 2000 });
                return false;
            }

        }
        for (var i = 0; i < mainDataList.length; i++) {
            for (var j = 0; j < mainDataList[i].length; j++) {
                param[n] = mainDataList[i][j];
                n++;
            }
        }
        $.getData({
            url: '/order/complateTask',
            body: param
        }, function (data) {
            console.log(data);
            location.href = './purchase-apply-list.html'
        });
    }

    function change() {
        $('#tjgys').html('').append('<div class="tjgys form-inline"><div class="form-group dispM"><label for="suplList" class="control-label">选择供应商 </label>' +
            '<select id="suplList" class="comList form-control" required="required">' + optHtml + '</select></div>' +
            '<div class="form-group dispM">     ' +
            ' <label for="" class="control-label">数量&nbsp;</label>' +
            '<input id="number" class="numbers form-control" value="1" min="1" type="number"/>' +
            '</div>' +
            '<div class="form-group dispM"><button type="button" class="suplLi1 btn btn-primary btn-xs" id="add">添加</button></div>' +
            '</div >');
        var idMy = $('#woliaoList option:selected').attr('data-id');
        if (window.sessionStorage.getItem(idMy)) {
            var data = JSON.parse(window.sessionStorage.getItem(idMy));
            $('#tjgys').html('');
            $('#aaa').html(optHtml);
            console.log(JSON.stringify(data));
            for (var a = 0; a < data.length; a++) {
                $('#aaa').find('option').each(function () {
                    if (data[a].supplyComName == $(this).val()) {
                        $(this).attr('selected', 'selected');
                        $(this).siblings().removeAttr('selected');
                    }
                })
                var optHtml1 = null;
                optHtml1 = $('#aaa').html();
                console.log(optHtml1);
                $('#tjgys').append('<div class="tjgys form-inline"><div class="form-group"><label for="suplList" class="control-label">选择供应商 </label>' +
                    '<select class="comList form-control" id="suplList">' + optHtml1 + '</select></div>' +
                    '<div class="form-group">  ' +
                    '<label for="" class="control-label">数量&nbsp;</label>' +
                    '<input id="number" class="numbers form-control" value="' + data[a].buyCount + '" min="1" type="number"/>' +
                    '</div>' +
                    '<div class="form-group dispM"><button type="button" class="suplLi1 btn btn-primary btn-xs" id="add">添加</button></div>' +
                    '</div >');

            }
            $('#tjgys button:gt(0)').text('删除').attr('id', 'delete');
            $('#tjgys div:gt(0)').css('margin-top', '6px');
        }
    }

    function init() {
        // 删除
        $(document).on('click', '#delete', function () {
            $(this).parent().parent().remove();
        });
        // 
        $(document).on('change', '#woliaoList', function () {
            setTimeout(change, 300);
        })
        // 点击采购
        $(document).on('click', '#purchase', function () {
            $('#tjgys').html('').append('<div class="tjgys form-inline"><div class="form-group dispM"><label for="suplList" class="control-label">选择供应商 </label>' +
                '<select id="suplList" class="comList form-control" required="required"></select></div>' +
                '<div class="form-group dispM">     ' +
                ' <label for="" class="control-label">数量&nbsp;</label>' +
                '<input id="number" class="numbers form-control" value="1" min="1" type="number"/>' +
                '</div>' +
                '<div class="form-group dispM"><button type="button" class="suplLi1 btn btn-primary btn-xs" id="add">添加</button></div>' +
                '</div >');
            setTimeout(change, 500);
            $.pop({
                title: '采购物料',
                content: template('purchase-applyT/material', {
                    items: matrList
                }),
                size: 'lg',
                isCancel: true,
                noIcon: true,
                yes: function () {
                    mainDataList[$('#woliaoList option:selected').index()] = [];
                    $('#tjgys>div').each(function () {
                        var obj = {};
                        obj.supplyComId = $(this).find('.comList option:selected').attr('id');
                        obj.matTypeId = $(this).parent().prev().find('#woliaoList option:selected').attr('mattypeid');
                        obj.supplyComName = $(this).find('.comList option:selected').attr('name');
                        obj.buyCount = $(this).find('input').val();
                        obj.taskId = infoId;
                        obj.applyCount = $(this).parent().prev().find('#shuliang').text();
                        obj.detailId = $(this).parent().prev().find('#woliaoList option:selected').attr('data-id');
                        obj.matName = $(this).parent().prev().find('#woliaoList option:selected').html();
                        obj.matTypeCode = $(this).parent().prev().find('#xinghao').text();
                        obj.matSpec = $(this).parent().prev().find('#guige').text();
                        mainDataList[$('#woliaoList option:selected').index()].push(obj);
                    });
                    window.sessionStorage.setItem(matrList[$('#woliaoList option:selected').index()].id, JSON.stringify(mainDataList[$('#woliaoList option:selected').index()]));
                }
            });
            // 已经处理过的change事件
            $("#guige").text(matrList[0].matSpec);
            $("#shuliang").text(matrList[0].applyCount);
            $("#xinghao").text(matrList[0].matTypeCode);
            // 选择物料
            $(document).on('change', '#woliaoList', function () {
                $('#tjgys').html('').append('<div class="tjgys form-inline"><div class="form-group"><label for="suplList" class="control-label">选择供应商 </label>' +
                    '<select class="comList form-control" id="suplList">' + optHtml + '</select></div>' +
                    ' <div class="form-group">  ' +
                    '  <label for="" class="control-label">数量&nbsp;</label>' +
                    '<input id="number" class="numbers form-control" value="1" min="1" type="number"/>' +
                    '</div>' +
                    '<div class="form-group dispM"><button type="button" class="suplLi1 btn btn-primary btn-xs" id="add">添加</button><div class="form-group dispM">' +
                    '</div >');
                $("#guige").text('');
                $("#shuliang").text('');
                $("#xinghao").text('');
                $("#guige").text(matrList[$(this).find("option:selected").val()].matSpec);
                $("#shuliang").text(matrList[$(this).find("option:selected").val()].applyCount);
                $("#xinghao").text(matrList[$(this).find("option:selected").val()].matTypeCode);
            });
            // 渲染供应商
            for (var m = 0; m < gysList.length; m++) {
                $('#suplList').append('<option id="' + gysList[m].id + '" name="' + gysList[m].comName + '">' + gysList[m].comName + '</option>');
            }
            optHtml = $('#tjgys .tjgys').eq(0).find('.comList').html();
            // 添加
            $(document).off('click', '#add').on('click', '#add', function () {
                if ($('#tjgys').html() == '') {
                    $('#tjgys').html('');
                } else {
                    var html = '<div class="tjgys form-inline"><div class="form-group"><label for="suplList" class="control-label">选择供应商 </label>' +
                        '<select class="comList form-control" id="suplList">' + optHtml + '</select></div>' +
                        ' <div class="form-group">  ' +
                        '  <label for="" class="control-label">数量&nbsp;</label>' +
                        '<input id="number" class="numbers form-control" value="1" min="1" type="number"/>' +
                        '</div>' +
                        '<div class="form-group"><button type="button" class="suplLi1 btn btn-primary btn-xs" id="add">添加</button></div>' +
                        '</div >';
                    $('#tjgys').append(html);
                    $('#tjgys button:gt(0)').text('删除').attr('id', 'delete');
                    $('#tjgys div:gt(0)').css('margin-top', '6px');

                    // 数量
                    $(document).off('keyup', '.numbers').on('keyup', '.numbers', function (e) {
                        if ($('.numbers').eq($(e.target).parents('.tjgys').index()).val() !== '' && $('.numbers').eq($(e.target).parents('.tjgys').index()).val() < 1) {
                            $('.numbers').eq($(e.target).parents('.tjgys').index()).val('1');
                        }
                    });

                }
            });
        });
        // 点击采购详情
        $(document).on('click', '.js-detail', function () {
            var id = $(this).attr('data-id');
            if (!JSON.parse(window.sessionStorage.getItem(id))) {
                $.alert("请先分配该条物料的供应商");
                return false;
            }
            $.pop({
                title: '采购详情',
                content: template('purchase-applyT/materialC', {
                    items: JSON.parse(window.sessionStorage.getItem(id))
                }),
                size: 'lg',
                isCancel: false,
                noIcon: true,
                yes: function () {

                }
            });
        });
        // 点击提交
        $(document).on('click', '#save', function () {
            saveData();
            // window.location.href = './purchase-apply-list.html';
        });
        // 点击取消
        $(document).on('click', '#cancle', function () {
            history.go(-1);
        });
        // 数量
        $(document).off('keyup', '.numbers').on('keyup', '.numbers', function (e) {
            if ($('.numbers').eq($(e.target).parents('.tjgys').index()).val() !== '' && $('.numbers').eq($(e.target).parents('.tjgys').index()).val() < 1) {
                $('.numbers').eq($(e.target).parents('.tjgys').index()).val('1');
            }
        });
        getList();
        supplier();
    }
    init();
});

