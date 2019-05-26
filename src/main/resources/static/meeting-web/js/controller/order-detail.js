$(function () {
    var ids = $.getQueryString('id');
    if (!ids) {
        $.alert('数据不存在', function () {
            history.back();
        });
        return;
    }

    function collectDispatch() {
        var arr = [];
        $('.deptVos-item input').each(function () {
            var $this = $(this);
            if ($this.is(':checked')) {
                if ($this.data('id')) {
                    arr.push($this.data('id'));
                }
            }
        });
        return {
            'miId': ids,
            'eiIds': arr.join(',')
        };
    }

    function getFormatTime(param) {
        if ((typeof param).toUpperCase() === 'STRING') {
            param = param.split(',');
        }
        var len = param.length;
        var startInd = param[0];
        var endInd = param[len - 1];
        return timeArr[startInd].startTime + '-' + timeArr[endInd].endTime;
    }
    // 获取列表
    function getDetail() {
        $.getData({
            url: '/meetings/getMeetingDetail',
            query: {
                id: ids
            }
        }, function (data) {
            if (data) {
                data.miMtDate = $.formatTime(data.miMtDate, 'yyyy-MM-dd');
                data.miMtTimeSeg = getFormatTime(data.miMtTimeSeg);
                renderContent(data);
                // $("#modal-pop").modal("hide");
                $(".modal-backdrop").remove();
            }else {
                //$("#modal-pop").modal("hide");
                $(".modal-backdrop").remove();
            }
        });
    }

    function setDeptVos(params) {
        $.getData({
            url: '/meetings/send',
            query: params
        }, function (data) {
            if (data) {
                $.toast('派遣成功', {
                    type: 'success'
                });
                getDetail();
            } else {
                $.toast('派遣失败',{
                    type:'error'
                });
                //$("#modal-pop").modal("hide");
                $(".modal-backdrop").remove();
            }
        });
    }
    // 渲染数据
    function renderContent(data) {
        $('.js-content').html(template('plan/order-detail', data));
    }
    // 渲染派遣
    function renderDept(data) {
        // console.log(data)
        $.pop({
            title: '派遣',
            noIcon: true,
            content: template('plan/deptVosList', {
                items: data
            }),
            yes: function () {
                setDeptVos(collectDispatch());
            }
        });
    }

    function getDeptVos(id) {
        $.getData({
            url: '/meetings/getEmpInfosByDeptId',
            query: {
                deptId: id
            }
        }, function (data) {
            var person = [];
            var list = [];
            var flag = true;
            $(".has-send").each(function () {  
                person.push($(this).text())
            })
            for (var i = 0; i < data.length; i++) {
                flag = true
                for (var j = 0; j < person.length; j++) {
                    if(data[i].lastname.localeCompare(person[j]) == '0') {
                        flag = false;
                        break
                    }
                }
                if (flag) {
                    list.push(data[i])
                }
            }
            if (data.length) {
                for (var i in data) {
                    data[i].value = data[i].eiId;
                    data[i].title = data[i].userName;
                }
                // console.log(list)
                renderDept(list);
            }
        });
    }

    function init() {
        timeArr = $.initTimeList();
        getDetail();
        // 返回
        $(document).on('click', '.js-back', function () {
            window.history.go(-1);
        });
        // 派遣
        $(document).on('click', '.js-dispatch', function () {
            $("#modal-pop").modal("hide");
            var $this = $(this);
            getDeptVos($this.data('id'));
        });
        // 派遣中的全选
        $(document).on('change', '.js-checked-all input', function () {
            var $this = $(this);
            $('.deptVos-item').each(function () {
                var self = $(this);
                if ($this.is(':checked')) {
                    self.find('input').prop('checked', true);
                } else {
                    self.find('input').prop('checked', false);
                }
            })
        });
        // renderContent();
    }
    init();
});