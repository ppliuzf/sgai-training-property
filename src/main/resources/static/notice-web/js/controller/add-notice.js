
$(function () {

    $(document).off('click');

    // 定义ckeditor用到的公共参数
    var editorxs;
    var config = {};

    // 页面加载时的公共方法（如果是编辑页面，获取请求数据的内容）
    function init() {
        getContent();
    }
    init();

    // 获取列表
    function getContent() {
        // 如果是二次编辑的数据
        if ($.getQueryString('infoId')) {
            // var data = {};
            // data.infoTitle = '标题';
            // data.infoSummary = '摘要';
            // data.infoCover = '封皮';
            // data.infoTimePublish = '1';
            // data.publishTime = '2017-12-30 12:30';
            // data.infoContent = "<ul>\n\t<li>\n\t<h2 style=\"font-style:italic;\"><s><em><strong>正文内容部分显示成粗体文字的方式</strong></em></s></h2>\n\t</li>\n</ul>\n";

            // data.infoLabel  = '标签';
            // data.infoScopeIsAll = '1';
            // data.infoUrgency = '0';
            // data.infoApprovalFlag = '1';
            // data.approvalEmpId = '147568';
            // data.approvalEmpName = '金魏巍';
            $.getData({
                url: '/noticeOp/getDetail4Edit',
                query: {
                    id: $.getQueryString('infoId')
                }
            }, function(data) {
                if (data) {
                    // 引入模板
                    data.visibilityScopeArr = [
                        {name:'APP', id: 'appPublic', checked: 0},
                        {name:'PC', id: 'pcPublic', checked: 0},
                        {name:'投屏', id: 'tpPublic', checked: 0},

                    ]
                    if(data.visibilityScope) {
                        data.visibilityScopeArr.forEach(function(item){
                            if (data.visibilityScope.indexOf(item.name) >-1) {
                                item.checked = 1;
                            }
                        });
                    }

                    console.dir(data.visibilityScopeArr);
                    renderCont(data);
                    // 调用公共方法
                    loadVendor(JSON.parse(data.infoContent));

                    // 上传图片
                    $.uploader({
                        el: '.shangchuan',
                        default: data.infoCover?[data.infoCover]: [],
                        url: '/uploadDown/uploadImages',
                        maxLength: 1,
                        tips: '注：最多上传1张图片，<br />推荐尺寸1080*1080，图片大小不超过10M'
                    });

                    // 组织树
                    $.dept({
                        el: '.js-dept-selector',
                        type: 'all',
                        default: 0 === data.infoScopeIsAll ? JSON.parse(data.infoScopeObject) : []
                    });
                    data.deptSearch = {
                        approvalEmpName:data.approvalEmpName,
                        approvalEmpId:data.approvalEmpId
                    };
                    $.instantSearch({
                        default : data.deptSearch // 默认选中项的名称和Id（新增时不需要，用于二次编辑回显）
                        // url: '/orgTree/searchEmpInfoByName'
                    });
                }
            });

        } else {
            // 引入模板
            renderCont({});
            // 调用公共方法
            loadVendor('');

            $.uploader({
                el: '.shangchuan',
                url: '/uploadDown/uploadImages',
                maxLength: 1,
                tips: '注：最多上传1张图片，<br />推荐尺寸1080*1080，图片大小不超过10M'
            });
            // 组织树
            $.dept({
                el: '.js-dept-selector',
                type: 'all'
            });

            $.instantSearch({
                placeholder: '请输入姓名'
            });
        }

    }
    // 页面加载成功后的公共方法
    function loadVendor(infoContent) {
        // html编辑器
        var html = infoContent;
        editorxs = CKEDITOR.appendTo( 'infoContent', config, html );
        // CKEDITOR.tools.callFunction("0", "\/userfiles\/files\/Public%20Folder\/icon_clear(4).png", "\u6587\u4ef6\u4e0e\u73b0\u6709\u7684\u91cd\u540d\uff0c\u65b0\u4e0a\u4f20\u7684\u6587\u4ef6\u6539\u540d\u4e3a \"icon_clear(4).png\"\u3002");
        // debugger;
        // CKEDITOR.tools.callFunction(1,'http://114.115.140.117:9099/userfiles/files/2914c58bbb8f40d485a13b02f9bf0491.png','')
        // 摘要记数
        $.counter({
            el:'#infoSummary',
            count: '.conttask'
        });

        // 审核人选择
        // $.kmInstantSearch({
        //     searchUrl: '/orgTree/searchEmpInfoByName'
        // });

        // 时间插件
        $('.form_date').datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            language: 'zh-CN',
            weekStart: 1,
            todayBtn:  1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0
        });
    }
    $(document).on('click', '.js-date-clean', function () {
        $(this).prev().val('');
    });
    // 渲染数据
    function renderCont(data) {
        $('.stepOne').html(template('add/add-notice-one', {
            items: data
        }));

        $('.stepTwo').html(template('add/add-notice-two', {
            items: data
        }));
    }
    // 点击预约发布
    $(document).on('click', '#infoTimePublish', function(e){
        if($('#infoTimePublish').is(':checked')){
            $("#releaseTime").show();
            $('#isExamine').hide();
        } else {
            $("#releaseTime").hide();
            $('#isExamine').show();
        }
    });

    $(document).on('click', '#releaseScope', function(e){

        if($('#releaseScope').is(':checked') === false){
            $(".js-dept-selector").show();
        } else {
            $(".js-dept-selector").hide();
        }
    });


    // 点击需要审核
    $(document).on('click', '#infoApprovalFlag', function(e){
        if($('#infoApprovalFlag').is(':checked')){
            $("#selectionAuditor").show();
        } else {
            $("#selectionAuditor").hide();
        }
    });

    // 点击预览
    $(document).on('click', '#preview', function(e){
        var infoTitle = $('#infoTitle').val(),
            infoSummary = $('#infoSummary').val();
        if(infoTitle != '' && infoSummary != ''){
            var previewCont = {};
            previewCont.title = $.trim($('#infoTitle').val());
            previewCont.summary = $.trim($('#infoSummary').val()).replace(/</g, '&lt;').replace(/\r|\n|\r\n|\\r|\\n|\\r\\n/ig, '<br>').replace(/\s/g, '&nbsp;');
            previewCont.content = editorxs.getData();
            $.pop({
                title: '预览',
                content: template('add/preview', {
                    items: previewCont
                }),
                isCancel: false,
                noIcon:true
            });
            // 正文
            $('.article').html(previewCont.content);
        } else {
            $.pop({
                title: '提示',
                content: '请先填写相关信息',
                size:'sm',
                isCancel: false
            });
        }

    });
    var isSubmit = true;
    function infoValidate() {
        if ( $.trim($('#infoTitle').val()) === ''){ //没写标题
            $.alert('请输入标题');
            isSubmit = false;
            return false;
        } else {
            isSubmit = true;
        }
        if ( $.trim($('#infoSummary').val()) === ''){ //没写摘要
            $.alert('请输入摘要');
            isSubmit = false;
            return false;
        } else {
            isSubmit = true;
        }
        if ($('#infoTimePublish').is(':checked') == 1) { // 选择了预约发布，需要填写发布时间
            if ( $.trim($('#publishTime').val()) === ''){ //没写摘要
                $.alert('请选择发布时间');
                isSubmit = false;
                return false;
            } else {
                isSubmit = true;
            }
        }
        var publicType = false;
        $('#publicType input').each(function(){
            var selfType = $(this);
            if (selfType.is(':checked')) {
                publicType = true;
                return false;
            } else {
                publicType = false;
            }
        });
        if (publicType == false) {
            $.alert('至少选择一项发布到范围');
            isSubmit = false;
            return false;
        }
        if (editorxs.getData() === ''){ //没写正文
            $.alert('请输入正文');
            isSubmit = false;
            return false;
        } else {
            isSubmit = true;
        }
    }

    var isSubmitTwo = true;
    function infoValidateStepTwo() {
        if ($('input:radio[name="releaseScope"]:checked').val() == undefined) { // 发布范围
            $.alert('请选择发布范围');
            isSubmitTwo = false;
            return false;
        } else {
            if($('input:radio[name="releaseScope"]:checked').val() == 0){
                if ($('.js--all').val() == '') {
                    $.alert('请选择发布范围的人员或部门');
                    isSubmitTwo = false;
                    return false;
                }
            };
            isSubmitTwo = true;
        }

        if ($('#infoApprovalFlag').is(':checked')) { // 需要审核
            if ( $.trim($('#instant-keywords').val()) === ''){ //没写审核人
                $.alert('请输入审核人姓名');
                isSubmitTwo = false;
                return false;
            } else {
                isSubmitTwo = true;
            }
        }
    }
    // 点击下一步
    $(document).on('click', '#nextStep', function(e){
        //调用验证的公共方法
        infoValidate();
        if (isSubmit === false) {
            return false;
        } else {
            $('#stepTwoCont').removeClass('isHide');
            $('#stepOneCont').addClass('isHide');
        }
        // 获取编辑器的内容
        // console.log('获取编辑器的内容'+ editorxs.getData());
    });
    // 点击上一步
    $(document).on('click', '#beforeStep', function(e){
        $('#stepOneCont').removeClass('isHide');
        $('#stepTwoCont').addClass('isHide');
    });
    // 点击确认发布
    $(document).on('click', '#releaseInfo', function(e){
        var self = $(this);
        if (self.hasClass('green-btn')) {
            return false;
        } else {
            submit(1);
            location.href = './post-list.html';
        }

    });
    // 点击发布并新增
    $(document).on('click', '#releaseInfoAddInfo', function(e){
        var self = $(this);
        if (self.hasClass('green-btn')) {
            return false;
        } else {
            submit(2);
        }

    });
    // 计算选中的总人数
    function calcNumber(params) {
        var rs = 0;
        for (var i in params) {
            if (params[i].isDept) {
                rs += params[i].empNum;
            } else {
                rs += 1;
            }
        }
        return rs;
    }
    // 发布的公共方法
    function submit(status) {
        //调用验证的公共方法
        infoValidateStepTwo();

        if (isSubmitTwo === false) {
            return false;
        } else {
            $('#releaseInfo, #releaseInfoAddInfo').addClass('green-btn');
            var params = {}
            params.infoTitle = $.trim($('#infoTitle').val()); // 标题
            params.infoSummary = $.trim($('#infoSummary').val()); // 摘要
            // params.infoCover = $('.upload-item-inner').prop('href'); // 封皮
            params.infoCover = '';
            params.infoTimePublish = $('#infoTimePublish').is(':checked') ? 1 : 0; // 是否预约发布
            var visibilityScope = ''
            $('#publicType input').each(function(){
                var selfType = $(this);
                if (selfType.is(':checked')) {
                    visibilityScope = visibilityScope + selfType.attr('value') + '/';
                }
            });
            params.visibilityScope = visibilityScope.substr(0,visibilityScope.length-1);
            params.infoContent = JSON.stringify(editorxs.getData()); // 正文
            params.infoLabel = $.trim($('#infoLabel').val()); // 标签
            // params.infoPicture = ''; // 正文第一张图片
            // params.infoReceiptFlag = '0'; // 是否需要回执
            params.infoScopeIsAll = $('#releaseScope').is(':checked') ? 1 : 0; // 发布范围（全部1，部分0）
            params.infoUrgency = $("input[name='infoUrgency']:checked").val(); // 类型（一般0，紧急1）

            // alert(params.infoUrgency);
            // params.approvalEmpId = '147568';
            // params.approvalEmpName = '金魏巍';
            // params.infoScope = [];
            // params.infoScopeDeparts = [];
            // params.infoScopeEmpNum = '0';
            // params.infoScopeObject = '';

            // 发布范围部分可见
            if (params.infoScopeIsAll === 0) {
                params.infoScopeDeparts = $('.js--dept').val() ? $('.js--dept').val().split(',') : [];
                params.infoScope = $('.js--emp').val() ? $('.js--emp').val().split(',') : [];
                params.infoScopeObject = $('.js--all').val();
                params.infoScopeEmpNum = calcNumber(JSON.parse(params.infoScopeObject));
            }

            if ($.getQueryString('infoId')) { //如果是二次编辑
                params.id = $.getQueryString('infoId');
            } else {
                params.id = '';
            }
            // alert(params.infoTimePublish);
            if (params.infoTimePublish === 1) { // 选择了预约发布，才提交发布时间，才提交审核人
                params.publishTime = $.getTimeStamp($("#publishTime").val()); // 发布时间

            } else {
                params.infoApprovalFlag = $('#infoApprovalFlag').is(':checked') ? 1 : 0; // 是否选择了审核人
                // alert(params.infoApprovalFlag);
                if (params.infoApprovalFlag === 1) {
                    var selected;
                    if  ($('#instant-selected').val()) {
                        selected = JSON.parse($('#instant-selected').val());
                        console.log(selected);
                    } else {
                        selected = '';
                    }
                    if (typeof(selected) === 'number' || selected === '' ) {
                        params.approvalEmpId = selected;
                        params.approvalEmpName = $('#instant-keywords').val();
                    } else {
                        params.approvalEmpId = selected.id;
                        params.approvalEmpName = selected.title;
                    }
                }
            }
            console.log('提交的数据');
            console.log(params);
            var postUrl = '';
            if ($.getQueryString('infoId')) { //如果是二次编辑
                postUrl = '/noticeOp/update';
            } else {
                postUrl = '/noticeOp/submit';
            }
            $.getData({
                url: postUrl,
                query: {},
                body: params
            }, function (data) {
                if (data) {
                    $('#releaseInfo, #releaseInfoAddInfo').removeClass('green-btn');
                    if (status == 1) { // 直接确定
                        $.toast('操作成功', {
                            type:"success",
                            timer: 2000
                        });
                        window.history.go(-1);
                    } else if (status == 2) { // 确定并新增
                        $.toast('操作成功', {
                            type:"success",
                            timer: 2000
                        });
                        window.location.href='add-notice.html';
                    }
                }
            });
        }
    }
});
