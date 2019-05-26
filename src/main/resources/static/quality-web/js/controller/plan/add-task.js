$(function () {
    var periodId, periodName, rangId, // 阶段id、阶段名称、范畴Id
        recordId = $.getQueryString('id'),
        recordName = $.getQueryString('name'),
        typeSelect = []; // 选的的关联对象
    // 获取任务阶段
    function getTaskPeriod() {
    $.getDataPlan({
        url: '/pc/period/periodList',
        query: {
        recordId: recordId
        }
    }, function (data) {
        if (data && data.length) {
        var _data = [];
        for (var i in data) {
            _data.push({
            title: data[i].periodName,
            value: data[i].id
            });
        }
        periodId = _data[0].recordId;
        periodName = _data[0].recordName;
        renderTaskPeriod(_data);
        }
    });
    }
    // 获取专业范畴
    function getTaskRang() {
        $.getDataPlan({
            url: '/task/getAllCategory'
        }, function (data) {
                if (data && data.length) {
                var _data = [];
                var defaultData = {
                    title: '请选择',
                    value: ''
                };
                _data.push(defaultData);
                for (var i in data) {
                    _data.push({
                    title: data[i].pcName,
                    value: data[i].id
                    });
                }
                renderTaskRang(_data);
            }
        });
    }
    // 获取检验方案
    function getTaskPlan() {
        rangId = $('.js-range').find("option:selected").val();
        $.getDataPlan({
            url: '/task/getPlanList',
            query: {
             categoryId: rangId
            }
        }, function (data) {
                if (data && data.length) {
                var _data = [];
                for (var i in data) {
                    _data.push({
                    title: data[i].name,
                    value: data[i].id
                    });
                }
                renderTaskPlan(_data);
            }
        });
    }
    // 获取配置项
    function getTaskSet() {
        $.getDataPlan({
            url: '/task/getCategoryTypeByCategoryId',
            query: {
             categoryId: rangId
            }
        }, function (data) {
                var _data = [];
                if (data && data.length) {
                    for (var i in data) {
                        _data.push({
                        title: data[i].asName,
                        value: data[i].asId
                        });
                    }
                } else {
                    _data.push({
                        title: '暂无数据',
                        value: ''
                    });
                }
                renderTaskSet(_data);
        });
    }
    // 渲染任务阶段
    function renderTaskPeriod(data) {
      $('.js-step').html(template('plan/select', {
        id: 'period',
        items: data
      }));
    }
    // 渲染专业范畴
    function renderTaskRang(data) {
      $('.js-range').html(template('plan/select', {
        id: 'rang',
        items: data
      }));
    }
    // 渲染检验方案
    function renderTaskPlan(data) {
      $('.js-plan').html(template('plan/select', {
        id: 'plan',
        items: data
      }));
    }
    // 渲染配置对象
    function renderTaskSet(data) {
        $('.link-set').html(template('plan/listcom', {
            items: data
        }));
    }
    // 初始化
    function init(data) {
        getTaskPeriod(); // 获取计划
        getTaskRang(); // 获取专业范畴
        $('.js-addtask-left').html(template('plan/addtask-left'));
        $('.js-addtask-topcom').html(template('plan/addtask-top'));
        $('.js-addtask-btmcom').html(template('plan/addtask-btm'));
        $('.js-addtask-car').html(template('task-detail'));
        $('.js-num').html(template('plan/number'));
        // 富文本计数器
        $.counter({
            el: '#taskDesc', // 文本框 id，默认 #textarea
            count: '.counttask', // 计数器 id，默认 .js-count
            max: 300 // 输入最大长度值，默认 200
        });
        $.counter({
            el: '#approveDesc', // 文本框 id，默认 #textarea
            count: '.countapprove', // 计数器 id，默认 .js-count
            max: 300 // 输入最大长度值，默认 200
        });
        // 组织树
        $.dept({
                name:'task1',
                el: '.js-dept0-selector',
                type: 'emp'
          });
        $.dept({
                name:'task2',
                el: '.js-dept0-selector1',
                type: 'emp'
          });
        $.instantSearch({
          url: '/orgTree/searchEmpInfoByName'
      });
        $.uploader({
        url: '/uploadDown/uploadImages',
        imageGroup: 'mm',
      });
        // 日历
        $('#dateStart,#dateEnd').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            minView: 'year',
            autoclose: true
        });

        // 取消
        $(document).on('click', '.btn-cancel', function () {
          window.history.back();
        });

        $(document).on('click', '.input-group-addon', function(){
            $(this).prev().val('');
        })
    }

    // 计划切换
    $(document).on('change', '.js-step', function () { // 任务阶段
            getTaskPlan();
        });
    // 专业范畴切换
    $(document).on('change', '.js-range', function () {
        if ($('#range').val()) {
            $('.type-plan').show(); // 显示方案
            $('.link-con').show(); // 显示关联对象
            getTaskPlan();
            getTaskSet();
        }else{
            // 隐藏方案和关联对象
            $('.type-plan').hide();
            $('.link-con').hide();
        }
      });
    // 配置框显示隐藏
    $(document).on('click', '.js-set', function (e) {
        e.stopPropagation();
        $('.link-set').toggle('display');
        getTaskSet();
    });
    // 隐藏配置框
    $(document).on('click', 'body', function () {
        $('.link-set').css('display', 'none');
    });
    // 配置类型
    $(document).on('click', '.link-set', function (e) {
        e.stopPropagation();
        // 选中的关联对象
        var currentSelect = e.target.textContent,
            url = '/task/getSpaceData'; // 房产
            switch (currentSelect) {
                case '物料档案':
                    url = '/task/getMaterielData';
                    break;
                case '房产档案':
                    url = '/task/getSpaceData';
                    break;
                case '设备档案':
                    url = '/task/getDevicesData';
                break;
                case '暂无数据':
                    url = '';
                    break;
            }
            if (!url) {
                return
            }
            $.getDataPlan({
                url
            }, function (data) {
                data.forEach(function (item) {
                   item.typeId = item.id;
                   item.typeName = item.name;
                });
                // 点击配置对象，弹框
                $('#setItem').modal('show');
                renderUl(data);
            });
        // 渲染弹框对象
        function renderUl(data) {
            $('#ul-type').html(template('common/ul', {items: data}));
                // 渲染被选中的
                var typeItemArr = $('.type-item');
                for (var i = 0; i < typeItemArr.length; i++) {
                    typeSelect.forEach(function (item) {
                        if(typeItemArr[i].dataset.id === item.asId) {
                            $(typeItemArr[i]).addClass('type-selected');
                        }
                    });
                }
            }
    });
    // 选择关联类型
    $(document).on('click', '.type-item', function () {
        if ($(this).hasClass('type-selected')) {
            $(this).removeClass('type-selected');
        } else {
            $(this).addClass('type-selected');
        }
        var selectedArr = $('.type-selected');
        typeSelect = [];
        for (var i = 0; i < selectedArr.length; i++) {
            var item = {
                asId: selectedArr[i].dataset.id,
                asName: selectedArr[i].dataset.name
            };
            typeSelect.push(item);
        }
    });
    // 确定关联对象
    $(document).on('click', '.js-sure', function () {
        // 渲染选中的对象
        $('.link-set-word').html(template('plan/setting-word', {
            items: typeSelect
        }));
        $('#setItem').modal('hide');
    });




    // 是否需要审核
    $(document).on('click', '.js-checkbox', function () {
        if ($(this).is(':checked')){
            $('.approve-con').show();
        } else {
            $('.approve-con').hide();
        }
    });

    // 收集公共部分数据
    function collectData() {
        var params = {};
            // 计划名称
        params.recordId = recordId;
        params.recordName = recordName;
        // 阶段名称
        params.periodId = $('.js-step').find("option:selected").val();
        params.periodName = $('.js-step').find("option:selected").text();
        if (!params.periodId) {
            $.alert('请选择任务阶段！');
            return false;
        }
        // 专业范畴
        var typeRange = $(".js-range").find("option:selected").val();
        if (!typeRange) {
            $.alert('请选择专业范畴！');
            return false;
        }
        // 检验方案
        var typePlan = $(".js-plan").find("option:selected").val();
        if (!typePlan) {
            $.alert('请选择检验方案！');
            return false;
        }
        params.planId = typePlan;
        // 关联对象
        var relationData = [],
            relationName = [];
        typeSelect.forEach(function (item) {
            relationData.push(item.asId);
            relationName.push(item.asName);
        });
        if (relationData.length === 0) {
            $.alert('请配置关联对象！');
            return false;
        } else {
            params.relationName = relationName.join(',');
        }

        // 任务名称
        params.taskName = $.trim($('#taskName').val());
        if (!params.taskName) {
            $.alert('请输入任务名称！');
            return;
        }
        // 执行时间
        params.taskBeginTime = $.getTimeStamp($('#dateStart').val());
        params.taskEndTime = $.getTimeStamp($('#dateEnd').val());
        if (!params.taskBeginTime || !params.taskEndTime) {
            $.alert('请选择执行时间！');
            return;
        } else if (params.taskBeginTime > params.taskEndTime) {
            $.alert('结束时间不能小于开始时间！');
            return;
        }
        // 添加任务名称 = 专业范畴名称 + 名称 + 时间

        params.associatedObject = {
            typeRange, // 范畴
            typePlan,// 方案
            relationData // 关联对象
        };
        params.associatedObject = JSON.stringify(params.associatedObject);

        // 是否需要审核
        // params.taskNeedAppr = $('#needApproval').is(':checked') ? 1 : 0;
        // if (params.taskNeedAppr && $('.js-task2-all').val()=='') {
        //     msgCom('请选择审核人！', 2000);
        //     return false;
        // }
        params.taskNeedAppr = 0;

        // 获取责任人 sl有数据后放开责任人
        if ($('.js-task1-all').val()=='') {
            msgCom('请选择责任人！', 2000);
            return false;
        }
        params.dutyPersonIdList = [];
        var participantMember = $.parseJSON($('.js-task1-all').val());
        if (participantMember) {
            for (let p = 0;p < participantMember.length;p++) {
                params.dutyPersonIdList.push(participantMember[p].id);
            }
        }
        // 审核人
        params.approverList = [];
        if($('.js-task2-all').val()){
            var approverMember = $.parseJSON($('.js-task2-all').val());
        }
         if (params.taskNeedAppr && approverMember) {
            for (let p = 0;p < approverMember.length;p++) {
                params.approverList.push(approverMember[p].id);
            }
            params.taskApprRequire = $.trim($('#approveDesc').val()); //审核要求
        }
        //图片
        params.imgList = imgCom();
        // 任务类型 1执行类 2检验类
        params.taskType = 2;
        //任务说明
        params.taskDesc = $.trim($('#taskDesc').val());
      return params;
    }
    // 保存
    $(document).on('click', '.btn-submit', function () {
        var body = collectData();
          if (body) {
              // 调用计划的保存方法
              // $.getDataPlan({
              //     url: '/task/taskSave',

              // 调用品质的保存方法
                  $.getData({
                  url: '/taskApp/taskQualitySave',
                  body
              }, function (data) {
                  msgToast('提交成功', 2000);
                  setTimeout(() => {
                      window.history.back();
                  }, 2000);
              });
          }
    });
    // 收集图片
    function imgCom () {
      var imgs = [];
        $('.upload-item').each(function (params) {
           var obj={
            imgUrl: $(this).data('url'),
            isDefault: 1
           };
            imgs.push(obj);
        });
        return imgs;
    }
    function msgToast (val, time) {
        $.msg(val, {
           time: time
        }, function() {
            // 成功
        });
    }
    function msgCom (value, time) {
      $.alert(value, function() {
                // $('#name').select();
            });
            return false;
    }
    init();
});
