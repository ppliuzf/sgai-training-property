$(function () {
    var infoId = $.getQueryString('infoId'),
        imgID,
        wuliaoData = [],
        MaterielData = [];
    // 获取今天的时间
    function p(s) {
        return s < 10 ? '0' + s : s;
    }
    var myDate = new Date();
    //获取当前年
    var year = myDate.getFullYear();
    //获取当前月
    var month = myDate.getMonth() + 1;
    //获取当前日
    var date = myDate.getDate();
    var myTime = year + '-' + p(month) + "-" + p(date);

    function init() {
        getContent();
        changeTitle();
        $(document).on('click', '.js-select-materiel', function () {
            collectMateriel();
        });
        $(document).on('click', '#save', function (e) {
            submit(infoId);
        });
        // 取消

        $(document).on('click', '#cancle', function (e) {
            history.go(-1);
        });
        $(document).on('click', '.js-del', function () {
            var $this = $(this);
            $.pop({
                content: '确认删除当前物料？',
                size: 'sm',
                yes: function () {
                    $this.parents('.materiel-item').remove();
                    for (var i = 0; i < MaterielData.length; i++) {
                        if (MaterielData[i].id == $this.parent().parent().attr('data-mattypeid')) {

                            MaterielData[i].isChecked = false;
                            break;
                        }
                    }
                    if ($('.materiel-item').length <= 0) {
                        $('.form-inline').removeClass('show').addClass('hidden');
                    }
                }
            });
        });

    }
    init();
    // 清空日期选择
    $(document).on('click', '.js-date-clean', function () {
        $(this).prev().val('');
    });
    // 获取列表
    function getContent() {
        // 如果是二次编辑的数据
        if (infoId) {
            $.getData({
                url: 'suppliesOperation/suppliesDetil',
                query: {
                    id: infoId
                }
            }, function (data) {
                if (data) {
                    imgID = data.imgID;
                    // 引入模板
                    renderCont(data);
                    // 上传图片
                    $.uploader({
                        el: '.shangchuan',
                        default: data.imgUrl ? data.imgUrl.split(",") : [],
                        url: '/uploadDown/uploadImages',
                        maxLength: 5,
                        tips: '注：最多上传5张图片，<br />推荐尺寸1080*1080，图片大小不超过10M'
                    });
                    // 时间插件
                    $('.form_date1').datetimepicker({
                        language: 'zh-CN',
                        weekStart: 1,
                        todayBtn: 1,
                        autoclose: 1,
                        todayHighlight: 1,
                        startView: 2,
                        minView: 2,
                        forceParse: 0,
                        startDate: myTime,
                        format: "yyyy-mm-dd"
                    });
                }
                if (data.suppliesDetails && data.suppliesDetails.length > 0) {
                    wuliaoData = data.suppliesDetails;
                    $('.wuliao .form-inline').addClass('show').removeClass('hidden');
                    renderWuLiao(data.suppliesDetails);
                }
                $.counter({
                    el: '#applyReason'
                });
            });
            $.getData({
                url: 'common/listMdmMatInfo',
                query: {
                    pageNo: 1,
                    pageSize: 1000

                }
            }, function (data) {
                MaterielData = data;

                for (var i = 0; i < wuliaoData.length; i++) {

                    for (var j = 0; j < MaterielData.length; j++) {
                        if (wuliaoData[i].matTypeId == MaterielData[j].id) {
                            MaterielData[j].isChecked = wuliaoData[i].isChecked = true;
                            break;
                        }
                    }
                }
            });




        } else {
            // 引入模板
            renderCont({});
            $.counter({
                el: '#applyReason'
            });
        }

        $.uploader({
            el: '.shangchuan',
            url: '/uploadDown/uploadImages',
            maxLength: 5,
            tips: '注：最多上传5张图片，<br />推荐尺寸1080*1080，图片大小不超过10M'
        });
        // 时间插件
        $('.form_date1').datetimepicker({
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0,
            startDate: myTime,
            format: "yyyy-mm-dd"
        });

    }
    function renderCont(data) {
        $('.stepOne').html(template('add/material-apply-add', {
            items: data
        }));
    }

    //获取物料列表
    function collectMateriel() {
        if (!MaterielData.length > 0) {
            $.getData({
                url: 'common/listMdmMatInfo',
                query: {
                    pageNo: 1,
                    pageSize: 1000

                }
            }, function (data) {
                MaterielData = data;
                for (var i = 0; i < wuliaoData.length; i++) {
                    for (var j = 0; j < MaterielData.length; j++) {
                        if (wuliaoData[i].matTypeId == MaterielData[j].id) {
                            MaterielData[j].isChecked = wuliaoData[i].isChecked = true;
                            break;
                        }
                    }
                }
                renderMaterielList(MaterielData);
            });
        } else {
            renderMaterielList(MaterielData);
        }

    }
    // 渲染物料弹窗
    function renderMaterielList() {
        if (MaterielData.length) {
            $.pop({
                content: template('plan/material-apply-add', { items: MaterielData }),
                title: "选择物料",
                noIcon: true,
                yes: function () {
                    //点击确定时判断弹出卡是能是否有选择
                    if ($("input[type='checkbox']").is(':checked')) {
                        var selectedMaArr = collectSelectMa();
                        if (selectedMaArr.length) {
                            $('.wuliao .form-inline').addClass('show').removeClass('hidden');
                            renderWuLiao(selectedMaArr);
                        } else {
                            $('.wuliao .form-inline').addClass('show').removeClass('hidden');
                        }
                    } else {
                        $('.modal-dialog').hide();  //没有选择直接 return false
                        return false;
                    }

                }
            });
        } else {
            $.alert('没有物料，请先添加物料');
        }
    }
    function renderWuLiao(param) {
        $('.js-wuliao-list').html(template('plan/wuliao', { items: param }));
    }
    function collectSelectMa() {
        var suppliesDetails = [];
        $('.js-materiel-list input').each(function (i) {
            var $this = $(this);

            if ($this.is(':checked') && $this.attr('data-isChecked') == "false") {
                suppliesDetails.push({
                    applyCount: $this.attr('data-applycount'),
                    matName: $this.attr('data-matname'),
                    matSpec: $this.attr('data-matspec'),
                    matTypeCode: $this.attr('data-mattypecode'),
                    matTypeName: $this.attr('data-mattypename'),
                    matTypeId: $this.attr('data-mattypeid')
                });
                console.log($this.attr('data-mattypeid'))
                MaterielData[i].isChecked = true;
            } else if (!$this.is(':checked') && $this.attr('data-isChecked') == "true") {

                MaterielData[i].isChecked = false;
                $('.materiel-item').each(function () {
                    var $$this = $(this);
                    if ($$this.attr('data-mattypeid') == $this.attr('data-mattypeid')) {
                        $$this.remove();
                    }
                })
                // for(var i=0;i<suppliesDetails.length;i++){
                //     if($this.attr('data-id') == suppliesDetails[i].id){
                //         suppliesDetails.splice(i, 1);
                //         break;
                //     }
                // }
            }
        });
        suppliesDetails = collectMaterielVos().concat(suppliesDetails);
        return suppliesDetails;
    }
    // 收集物料数据
    function collectMaterielVos() {
        var suppliesDetails = [];
        $('.materiel-item').each(function () {
            var $this = $(this);
            suppliesDetails.push({
                applyCount: $this.find("input").val(),
                matName: $this.attr('data-matname'),
                matSpec: $this.attr('data-matspec'),
                matTypeCode: $this.attr('data-mattypecode'),
                matTypeName: $this.attr('data-mattypename'),
                id: $this.attr('data-id'),
                matTypeId: $this.attr('data-mattypeid')
            });
        });
        return suppliesDetails;
    }
    // 根据url参数修改页面标题
    function changeTitle() {
        if (infoId) {
            $(".js-title h4").html("编辑申请");
        } else {
            $(".js-title h4").html("新建申请");
        }
    }
    // 收集新建申请数据
    function getData() {
        var param = {};
        param.applyNo = $("#infoOrgId").val(); //申请编号
        param.applyReason = $("#applyReason").val(); // 申请原因
        param.supplyDate = $("#supplyTime").val(); //领取时间
        param.suppliesDetails = collectMaterielVos();  //物料明细
        param.imgUrl = '';
        $('.upload-item-inner').each(function () { param.imgUrl += this.href + ',' }); //拼接href
        if (param.imgUrl.length > 0) param.imgUrl = param.imgUrl.substr(0, param.imgUrl.length - 1);  // 去除最后一个逗号
        if (param.supplyDate == '') {
            $.toast('请选择物料申请日期', { timer: 2000 });
            return false;
        }
        if (param.applyReason == '') {
            $.toast('申请理由不能为空', { timer: 2000 });
            return false;
        }
        if (!param.suppliesDetails.length > 0) {
            $.toast('必须添加物料', { timer: 2000 });
            return false;
        }
        var reg = /\D/;
        for (var i = 0; i < param.suppliesDetails.length; i++) {

            if (reg.test(param.suppliesDetails[i].applyCount) || param.suppliesDetails[i].applyCount > 99999 || param.suppliesDetails[i].applyCount <= 0) {
                $.toast('物料数量为1~99999的正整数', { timer: 2000 });
                return false;
            }
        }

        return param;
    }
    // 点击保存
    function submit(infoId) {
        var param = getData();
        if (param === false) {
            return false;
        } else {
            if (infoId) {
                param.id = infoId;
                param.imgID = imgID;
                $.getData({
                    url: 'suppliesOperation/updateSuppliesInvoices',
                    query: {},
                    body: param
                }, function (data) {
                    if (data) {
                        window.history.go(-1);
                    }
                });
            }
            else {
                $.getData({
                    url: 'suppliesOperation/addSuppliesInvoices',
                    query: {},
                    body: param
                }, function (data) {
                    if (data) {
                        window.history.go(-1);
                    }
                });
            }

        }
    }
});
