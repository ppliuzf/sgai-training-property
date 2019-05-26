$(function () {
    var infoId = $.getQueryString('infoId');
    var day1, ivtExecEmpName, ivtExecEmpId, ivtNo, ivtPiJson, ivtPlanBegin, ivtPlanEnd, ivtTitle;
    var matStat, whId, whName, matList = [], position, aa = null;
    var currPage = 1, cangKuList = [], realNumber = null, diffNumber = null;
    // 渲染select  获取整个仓库
    function getCangKu() {
        $.getData({
            url: '/Inventories/findAllBhByivtNo',
            query: {
                ivtNo: infoId
            }
        }, function (data) {
            for (var m = 0; m < data.length; m++) {
                $('#actCangKu').append('<option value = "' + data[m].whId + '">' + data[m].whName + '</option>')
            }
        })
    }
    function checkNumber(theObj) {
        var reg = /^[0-9]+$/;
        if (reg.test(theObj)) {
            return true;
        }
        return false;
    }
    $(document).on('keyup', '.matRealNum', function () {
        if (!checkNumber($(this).val())) {
            $(this).val('0');
        }
    });
    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S": this.getMilliseconds()
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;

    };
    function getTime() {
        var date = new Date(), day;
        day = date.toLocaleDateString().split('/');
        if (day[1] < 10) {
            day[1] = "0" + day[1];
        }
        day1 = day.join('-');
        $('.form_date2').datetimepicker({
            autoclose: 1,
            startDate: day1,
            format: "yyyy-MM-dd hh:ii"

        });
        $('.form_date3').datetimepicker({
            autoclose: 1,
            startDate: day1,
            format: "yyyy-MM-dd hh:ii"
        });
        $(document).on('click', '.js-date-clean', function () {
            $(this).prev().val('');
        });
    }
    // 清空日期选择
    $(document).on('click', '.js-date-clean', function () {
        $(this).prev().val('');
    });
    // 模糊搜索
    $.instantSearch({
        url: '/orgTree/searchEmpInfoByName',
        placeholder: '请输入盘点执行人'
    });
    // 基础详情
    function getContent() {
        $.getData({
            url: '/Inventories/detailNew',
            query: {
                ivtNo: infoId
            }
        }, function (data) {
            if (data) {
                if (data.ivtExecBegin) {
                    $('#startTime').val(new Date(data.ivtExecBegin).Format("yyyy-MM-dd"));
                } else {
                    $('#startTime').val('');
                }
                if (data.ivtExecEnd) {
                    $('#endTime').val(new Date(data.ivtExecEnd).Format("yyyy-MM-dd"));
                } else {
                    $('#endTime').val('');
                }
                ivtExecEmpId = data.ivtExecEmpId;
                ivtExecEmpName = data.ivtExecEmpName;
                ivtNo = data.ivtNo;
                ivtPlanBegin = $.trim(new Date(data.ivtPlanBegin).Format("yyyy-MM-dd")) + 'T00:00:00';
                ivtPlanEnd = $.trim(new Date(data.ivtPlanEnd).Format("yyyy-MM-dd")) + 'T00:00:00';
                ivtTitle = data.ivtTitle;
                matStat = data.matStat;
                whId = data.whId;
                whName = data.whName;
                // ivtPiJson":"{\"id\":\"17845\",\"title\":\"雷黎01\"}"
                if (data.ivtPiJson) {
                    ivtPiJson = data.ivtPiJson;
                    position = JSON.parse(ivtPiJson).title;
                    data.position = position;
                } else {
                    data.position = '无';
                }

                $('#instant-keywords').val(data.ivtExecEmpName);
                $('#chayi').val(data.ivtDiffReason);
                $.counter({
                    el: '#chayi'
                });
                $('#kui').html(data.lessCount);
                $('#ying').html(data.moreCount);
                renderMain(data);
            }
        });
    }
    // 翻页获取table
    function tableJump(n){
        $.getData({
            url: '/Inventories/findMatListByonBlur',
            query: {
                ivtNo: infoId,
                whId: $('#actCangKu').find('option:selected').val() ? $('#actCangKu').find('option:selected').val() : '',
                pageNum: n,
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
                        tableJump(n);
                        currPage = n;
                    }
                });
                for (var m = 0; m < data.list.length; m++) {
                    if (!data.list[m].matDiffNum) {
                        data.list[m].matDiffNum = data.list[m].matNum - data.list[m].matRealNum;
                    }
                }
                renderTable(data.list);
                difMain();
            }else {
                $('.pages').css('display', 'none');
                renderEmpty();
            }
        })
    };
    // 获取table
    function table(pageNum) {
        $.getData({
            url: '/Inventories/findMatListByivtNo',
            query: {
                ivtNo: infoId,
                whId: $('#actCangKu').find('option:selected').val() ? $('#actCangKu').find('option:selected').val() : '',
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
                        tableJump(n);
                        currPage = n;
                    }
                });
                for (var m = 0; m < data.list.length; m++) {
                    if (!data.list[m].matDiffNum) {
                        data.list[m].matDiffNum = data.list[m].matNum - data.list[m].matRealNum;
                    }
                }
                renderTable(data.list);
                difMain();
            } else {
                $('.pages').css('display', 'none');
                renderEmpty();
            }
        });
    }
    // 实时更新列表
    function upData(){
        $.getData({
            url: '/Inventories/excuteSynchroMatList',
            query: {
                ivtNo: infoId
            }
        }, function (data) {
            console.log(data);
        })
    };
    function upEdit(diffNumber, realNumber, matTypeId, whId){
        $.getData({
            url: '/Inventories/updateWarehousRecord',
            body: {
                matDiffNum: diffNumber,
                matRealNum: realNumber,
                matTypeId: matTypeId,
                orderNumber: infoId,
                whId: whId
            }
        }, function (data) {
            console.log(data);
        }) 
    }
    // 计算插值
    function difMain() {
        $('.js-list tr').each(function () {
            var that = $(this);
            that.find('td').find('input').on('change', function () {
                if (Number($(this).val()) < 0) {
                    $(this).val("0");
                }
                var mm = Number($(this).parent().prev().html());
                var nn = Number($(this).val());
                realNumber = nn;
                var ss = mm - nn;
                diffNumber = ss;
                $(this).parent().next().html(ss);
                matTypeId = $(this).parents('.main').attr('mattypeid');
                whId = $(this).parents('.main').attr('whid');
                upEdit(diffNumber, realNumber, matTypeId, whId)
                if (ss < 0) {
                    $(this).parent().prev().css('color', 'red');
                    $(this).css('color', 'red');
                    $(this).parent().next().css('color', 'red');
                } else {
                    $(this).parent().prev().css('color', '#000');
                    $(this).css('color', '#000');
                    $(this).parent().next().css('color', '#000');
                }
            });
            that.find('td').find('input').on('keyup', function () {
                if (Number($(this).val()) < 0) {
                    $(this).val("0");
                }
                var mm = Number($(this).parent().prev().html());
                var nn = Number($(this).val());
                realNumber = nn;
                var ss = mm - nn;
                diffNumber = ss;
                $(this).parent().next().html(ss);
                matTypeId = $(this).parents('.main').attr('mattypeid');
                whId = $(this).parents('.main').attr('whid');
                upEdit(diffNumber, realNumber, matTypeId, whId)
                if (ss < 0) {
                    $(this).parent().prev().css('color', 'red');
                    $(this).css('color', 'red');
                    $(this).parent().next().css('color', 'red');
                } else {
                    $(this).parent().prev().css('color', '#000');
                    $(this).css('color', '#000');
                    $(this).parent().next().css('color', '#000');
                }
            });
        });
    }

    // 渲染上模板
    function renderMain(data) {
        $('.js-main').html(template('check/check-act', {
            items: data
        }));
    }
    // 渲染table模板
    function renderTable(data) {
        $('.js-list').html(template('check/check-act-list', {
            items: data,
            state: $.getQueryString('state')
        }));
    }
    // 渲染table无数据模板
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 7
        }));
    }
    $(document).on('focus', '#number', function () {
        $(this).val('');
    });

    // 保存的数据
    function submitData() {
        aa = 0;
        matList = [];
        var param = {};
        // param.id = infoId;
        param.ivtDiffReason = $.trim($('#chayi').val());//差异原因
        if ($.trim($('#startTime').val())) {
            // param.ivtExecBegin = $.trim($('#startTime').val()) + 'T00:00:00'; //盘点开始时间
            var aachen = $('#startTime').val().split(' ')[0];
            var bbchen = $('#startTime').val().split(' ')[1]+':00';
            param.ivtExecBegin = aachen + 'T' + bbchen;
        }
        if ($.trim($('#endTime').val())) {
            // param.ivtExecEnd = $.trim($('#endTime').val()) + 'T00:00:00'; //盘点结束时间
            var aachen1 = $('#endTime').val().split(' ')[0];
            var bbchen1 = $('#endTime').val().split(' ')[1]+':00';
            param.ivtExecEnd = aachen1 + 'T' + bbchen1;
        }
        if ($('#instant-selected').val()) {
            var name = JSON.parse($('#instant-selected').val());
            param.ivtExecEmpId = name.id;
            param.ivtExecEmpName = name.title; //执行人
        }
        param.ivtNo = infoId;
        // if (ivtPiJson) {
        //     param.ivtPiJson = ivtPiJson;
        // }
        param.matStat = matStat;
        // param.ivtPlanBegin = ivtPlanBegin;
        // param.ivtPlanEnd = ivtPlanEnd;
        // param.ivtTitle = ivtTitle;
        $('.js-list tr').each(function (index, item) {
            var self = $(this);
        //     var objList = {};
        //     objList.id = self.attr("data-id");
        //     objList.ivtId = infoId;
        //     objList.matDiffNum = Number(self.find(".difNum").html()); //差异量
        //     objList.matName = self.find(".matName").html(); // 物料名称
        //     objList.matNum = Number(self.find('.matNum').html()); // 库存数量
        //     objList.matRealNum = Number(self.find('.matRealNum').val()); // 实存数量
        //     objList.matSpec = self.attr('matspec'); // 物料规格
        //     objList.matTypeCode = self.attr("mattypecode"); // 物料型号
        //     objList.whId = whId;
        //     objList.matTypeId = self.attr('mattypeid');
            if (!self.find('.matRealNum').val()) {
                aa = 1;
            }
        //     matList.push(objList);
        });
        // param.matParamList = matList;
        // param.matStat = Number(matStat);
        // param.whId = whId;
        // param.whName = whName;
        // if ($('.js-list').find('tr').eq(0).find('td').html() == '暂无记录') {
        //     $.toast('无物料信息', {
        //         timer: 2000
        //     });
        //     return false;
        // }
        if (aa == 1) {
            $.toast('请输入实存数量的值', {
                timer: 2000
            });
            return;
        }
        return param;
    }
    // 保存
    function submit() {
        upData();
        var qq = $.getTimeStamp($.trim($('#startTime').val()));
        var ww = $.getTimeStamp($.trim($('#endTime').val(), 'end'));
        if (qq && ww && qq > ww) {
            $.toast('盘点开始时间大于盘点结束时间', {
                timer: 2000
            });
            return;
        }
        var dataMain = submitData();
        if (!dataMain) {
            return false;
        }
        $.getData({
            url: '/Inventories/excuteUpdateInventories',
            body: dataMain
        }, function (data) {
            if (data) {
                location.href = './check-list.html';
            }
        });
    }
    function init() {
        getCangKu();
        getTime();
        getContent();
        table();
        $.counter({
            el: '#chayi'
        });
        // 保存
        $(document).on('click', '#checkSave', function () {
            submit();
        });
        // 取消
        $(document).on('click', '#checkCancle', function () {
            window.history.go(-1);
        });
        $(document).on('change', '#actCangKu', function(){
            tableJump(currPage);
        })
        // 分页
        $.renderPage({ count: 100 })
    }
    init();
});
