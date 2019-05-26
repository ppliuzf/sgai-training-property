$(function() {

    //定义变量
    var params = {}, // 查询条件
        selectDay = getDay(); // 选择的日期(初始值当天)

    params.riUseEnd = new Date().getTime(); // 获取当前时间的车辆

    // 获取当前日期
    function getDay(val) {
        var time = val ? new Date(val) : new Date(),
            day = time.getFullYear() + "-" + (time.getMonth() + 1) + "-" + time.getDate() + " ";
        return day;
    }
    //获取数据
    function getList(pageNum,isFirst,params) {
        $.getData({
            url: '/carUserRelationInfo/getReservableCarInfoList',
            body: params,
            query:{
                pageNum:pageNum,
                pageSize:20
            }
        }, function(data) {
            isFirst && $.renderPage({
                count: data.count,
                limit: 20,
                jump: function(num) {
                    getList(num, false, params);
                }
            })
            if (data.list.length && data.list) {
                renderList(data.list)
            } else {
                renderEmpty();
            }
        });
    }
    // 获取车辆类型
    function getType() {
        $.getData({
            url: '/gearBoxType/gearBoxTypeList',
        }, function(data) {
            renderType(data)
        });
    }
    //渲染类型
    function renderType(data) {
        $('#bsx').html(template('order/sel-type', { items: data }))
    }
    //渲染列表
    function renderList(data) {
        $('.js-main').html(template('order/orders', { items: data }))
    }
    // 渲染空
    function renderEmpty() {
        $('.js-main').html(template('order/order-empty'));
    }

    // 日期选择器回调函数
    $.weekSelector({
        onselect: function(val) {
            selectDay = getDay(val);
            getList(1,true,params);
            setSelectTime();
        }
    });

    // 设置预约时间范围
    function setSelectTime() {
        var times = $.initTimeList(),
            canSelectTime = [], // 可以选择的时间点
            point = 0,          // 截取时间的节点
            today = new Date(); // 当前时间
        if (new Date(selectDay) > today) {
            // 如果不是当天，以后的时间不筛选；
            point = 0;
        } else {
            // 如果是当天的，过去时间筛选去除；
            point = today.getHours() + 1;
        }
        for (var i = point; i < times.length; i++) {
            canSelectTime.push({
                title: times[i].startTime,
                value: i
            });
        }
        // 渲染时间
        $('#start-time').html(template('common/select', {
            items: canSelectTime
        }));
    }

    function init() {
        getType();
        getList(1,true,params);
        setSelectTime();

        // 搜索
        $(document).on('click', '#oeder-chose', function() {
            if ($('#num').val().indexOf("-") !== -1 || $('#num').val().indexOf(".") !== -1) {
                $.alert('请输入正确的荷载人数');
                return false
            } else {
                if($('#num').val() === 0){
                    $('#num').val('');
                }
                if( $('#bsx option:selected').val() === "0" ){
                    $('#bsx option:selected').val('')
                }else{
                    var ciBoxTypeId = $('#bsx option:selected').val()
                }
                // 当前选的时间
                var currTime = selectDay + ' ' +  $('#start-time').val() + ':00';
                params.riUseEnd = new Date(currTime).getTime();
                params.ciLoadNumber = $('#num').val();
                params.ciDisplacement = $('#pl').val();
                params.ciBoxTypeId = ciBoxTypeId;
                getList(1,true,params)
            }
        });
        //预约
        $(document).on('click', '#order-cl', function() {
            let marsk = '<div class="orderMarsk" style="width:100%;height:100%;background:#000;position:fixed;top:0;left:0;opacity:0.2;"></div>';
            $('body').append(marsk)
            $('.orderMarsk').click(function() {
                $(this).remove()
            })
            var id = $(this).data('id');
            $.bubble({
                el: $(this),
                msg:'你确定预约此车辆吗？',
                ok: function () {
                    var selectedTime = selectDay + ' ' +  $('#start-time').val() + ':00';
                    sessionStorage.setItem('selectedTime', selectedTime );
                    location.href = './pages/order/new-reservation.html?id=' + id;
                    $('.orderMarsk').remove()
                },
                cancel: function () {
                    $('.orderMarsk').remove()
                    // $.toast('您已取消');
                }
            });
            var $bubble = $('#bubble');
            $bubble.css({
                left: $(this).offset().left - $bubble.width() + 194,
                top: $(this).offset().top - $bubble.height() - 10
            });

        });
        

        //审核跳转
        $(document).on('click', '#sh', function(){
            window.location.href = './pages/order/my-review/my-review.html'
        })
        //预约跳转
        $(document).on('click', '#yy', function(){
            window.location.href = "./pages/order/my-reservation/my-reservation.html"
        })
        
    }
    init();
});