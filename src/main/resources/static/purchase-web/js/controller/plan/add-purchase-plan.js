$(function () {
    function init() {
        $(document).on('click', '#save', function(e){
            submit(1); // 保存
        });
        $(document).on('click', '#saveUpdate', function(e){
            submit(2); // 保存并关联任务
        });
         // 点击取消
         $(document).on('click', '#cancle', function(e){
            history.go(-1);
        }); 
        if ($.getQueryString('id')) { // 二次编辑
            
            $.getData({
                url: '/listOrDetail/getPlanDetail',
                query: {
                    id: $.getQueryString('id')
                }
            }, function(data) {
                if (data) {
                    // 引入模板
                    renderCont(data);
                    $.counter({
                        el:'#planDescription',
                        count: '.js-count'
                    });
                    // 组织树
                    $.dept({
                        el: '.js-dept-selector',
                        type: 'dept',
                        name: 'plan',
                        defalutLen: '1',
                        // default: 0 === data.infoScopeIsAll ? JSON.parse(data.planDept) : []
                        default: JSON.parse(data.planDept)
                    });
                }
            });

        } else {
            // 引入模板
            renderCont({});
            
            $.counter({
                el: '#planDescription '
            });
            // 组织树
            $.dept({
                el: '.js-dept-selector',
                type: 'dept',
                name: 'plan',
                defalutLen: '1'
                // ,name: 'dept'
            });
        }
        
    }
    init();
    
    // 引入模板公共方法
    function renderCont(data) {
        $('.stepOne').html(template('plan/add-purchase-plan', {
            items: data
        }));
    }
    
    // 公共验证方法
    var isSubmit = true;
    function planValidate() {

        if ( $.trim($('#planName').val()) === ''){ //计划名称
            $.toast('请输入计划名称',{timer:2000});
            isSubmit = false;
            return false;
        } else {
            isSubmit = true;
        }

        if ( $.trim($('#planType option:selected').val()) === ''){ //计划类型
            $.toast('请选择计划类型',{timer:2000});
            isSubmit = false;
            return false;
        } else {
            isSubmit = true;
        }
        if ( $.trim($('.js-plan-all').val()) === ''){ //计划部门
            $.toast('请选择部门',{timer:2000});
            isSubmit = false;
            return false;
        } else {
            isSubmit = true;
        }
    }
    // 点击保存
    function submit(type) {
        //调用验证的公共方法
        planValidate();
        if (isSubmit === false) {
            return false;
        } else {
            var param = {};
            if ($.getQueryString('id')) { // 二次编辑
                param.id = $.getQueryString('id');
            }
            param.planName = $.trim($("#planName").val()); // 计划名称
            param.planType = $('#planType option:selected') .val(); // 计划类型
            param.planDescribe = $.trim($("#planDescription").val()); // 摘要
            param.planDept = $('.js-plan-all').val(); // 计划部门
            param.planDeptId = $('.js-plan-dept').val();
            param.planStat = ''
            
            $.getData({
                url: '/order/saveOrUpdatePlan',
                query: {},
                body: param
            }, function (data) {
                if (data) {
                    if (type === 1) { // 保存
                        // window.history.go(-1);
                        window.location.href = 'purchase-plan-list.html';
                    } else { // 保存并关联任务
                        window.location.href = 'purchase-paln-detail.html?id='+data+'&status=dtj';
                    }
                }
            });
        }
    }
});
