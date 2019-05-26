$(function () {
    var id = $.getQueryString('id');
    // var reg=/(^[0-9]{0,2}$)|(^[0-9]{0,2}\.[0-9]{0,2}$)/
    var reg= /^\d\.([1-9]{1,2}|[0-9][1-9])$|^[1-9]\d{0,1}(\.\d{1,2}){0,1}$|^100(\.0{1,2}){0,1}$|0/;
    // 获取数据
    function getDetail(id) {
        $.getData({
            url: '/supplier/getById',
            query: {
                id: id
            },
        }, function (data) {
            if (data) {
                // renderDetail(data);
                $('#headtxt').text('编辑供应商'+'-'+data.name)
                $('.js-main').html(template('archive-add', {
                    item: data
                }));
                renderDetail(data)
            } else {
                $.alert('数据不存在', function () {
                    history.back();
                });
            }
        });
    }
    // 获取类型
    function getType(xxid) {
        $.getData({
            url: '/type/findAllTypeList'
        }, function (data) {
            if (data&& data.length) {
                var _data = [];
                for (var i in data) {
                    _data.push({
                        value: data[i].id,
                        title: data[i].name
                    });
                }
                $('#CL-type').html(template('common/select', {
                    items: _data,
                    xxid:xxid
            }));
            }
        });
    }
    //获取内容类型
    function getService(xxid) {
        $.getData({
            url: '/content/findAllContentList'
        }, function (data) {
            if (data && data.length) {
                var _data = [];
                for (var i in data) {
                    _data.push({
                        value: data[i].id,
                        title: data[i].name
                    });
                }
                $('#FW-type').html(template('common/select', {
                    items: _data,
                    xxid:xxid,
                }));
            }
        });
    }
    // 获取等级
    function getGrade(xxid) {
        $.getData({
            url: '/level/findAllLevelList'
        }, function (data) {
            if (data&& data.length) {
                var _data = [{value:'1',title:"未评级"}];
                for (var i in data) {
                    _data.push({
                        value: data[i].id,
                        title: data[i].name
                    });
                }
                $('#grade').html(template('common/select', {
                    items: _data,
                    xxid:xxid
                }));
            }
        });
    }
    // 渲染详情
    function renderDetail(res) {
        getType(res.typeId)
        getService(res.contentId)
        getGrade(res.levelId)
        $.uploader({
            el: '.js-uploader1',
            url: '/uploadDown/uploadImages',
            maxLength:1,
            tips:'',
            default: res.licenseUrl? [res.licenseUrl] : ''
        });
        $.uploader({
            el: '.js-uploader2',
            url: '/uploadDown/uploadImages',
            maxLength:1,
            tips:'',
            default: res.cardAUrl ? [res.cardAUrl] : ''
        });
        $.uploader({
            el: '.js-uploader3',
            url: '/uploadDown/uploadImages',
            maxLength:1,
            tips:'',
            default: res.cardBUrl ? [res.cardBUrl] : ''
        });
        $.uploadFile({
            el: '.js-uploader-file',
            url:'/uploadDown/uploadFile',
            default: res.fileList&&res.fileList.length ? res.fileList : []
        });
    }
    //收集数据
    function collectData(){
        var rs = {};
        if ($.trim($('#pany').val()) === '' ) {
            $.alert('请输入公司名称', function () {
                $('#pany').select();
            });
            return false;
        }
        if ($.trim($('#pany-tel').val()) !== ''&& !$('#pany-tel').val().match(/^1[0-9]{10}.*/ig)) {
            $.alert('手机号输入格式有误');
            return false;
        }
        if ($('#CL-type').find('option').val() === '' ) {
            $.alert('请选择供应商分类', function () {
                $('#name').select();
            });
            return false;
        }
        if ($('#YY-num').val() === '' ) {
            $.alert('营业执照号不能为空');
            return false;
        }
        if ($('#YY-man').val() === '' ) {
            $.alert('法人信息不能为空');
            return false;
        }
        if ($('#bank').val() === '' ) {
            $.alert('开户银行不能为空');
            return false;
        }
        if ($('#bank-num').val() === '' ) {
            $.alert('银行账号不能为空');
            return false;
        }
        if($.trim($('#pay-text').val()) !== '') {
            var value = $("#pay-text").val();
            if (value >100) {
                $.alert("税率请输入0-100的数字且只能保留两位小数");
                return false
            }else if (!reg.test(value)) {
                // if(parseInt(value)>=100){
                $.alert("税率请输入0-100的数字且只能保留两位小数");
                return false;
                // }
            }
            }

        rs.id = id ? id : '';
        rs.no = $('#idno').val()
        rs.pany = $.trim($('#pany').val());
        // rs.pany_num = $.trim($('#pany-num').val());
        // rs.pany_type = $('#pany-type').find('option').val();
        rs.pany_man = $.trim($('#pany-man').val());
        rs.pany_tel = $.trim($('#pany-tel').val());
        rs.pany_address = $.trim($('#pany-address').val());
        rs.CL_type = $('#CL-type').val();
        rs.FW_type = $('#FW-type').val();
        rs.grade = $('#grade').val();//11111111
        rs.CL_typecon = $('#CL-type').find('option:selected').text();
        rs.FW_typecon = $('#FW-type').find('option:selected').text();
        rs.gradecon = $('#FW-type').find('option:selected').text();//11111111111
        rs.YY_num = $.trim($('#YY-num').val());
        rs.YY_man = $.trim($('#YY-man').val());
        rs.bank = $.trim($('#bank').val());
        rs.bank_num = $.trim($('#bank-num').val());
        if ($('.licenseUrl .upload-item').data('url')) {
            rs.licenseUrl=$('.licenseUrl .upload-item').data('url');
        } else {
            rs.licenseUrl='';
        }
        if ($('.cardAUrl .upload-item').data('url')) {
            rs.cardAUrl=$('.cardAUrl .upload-item').data('url');
        } else {
            rs.cardAUrl='';
        }
        if ($('.cardBUrl .upload-item').data('url')) {
            rs.cardBUrl=$('.cardBUrl .upload-item').data('url');
        } else {
            rs.cardBUrl='';
        }
        var taxpayerType = $('#pay-text').val();
        // rs.cardAUrl=$('.cardAUrl .upload-item').data('url');
        // rs.cardBUrl=$('.cardBUrl .upload-item').data('url');
        rs.pay_text_man = $('#pay-text-man').val();
        rs.pay_text = Number(taxpayerType);
        rs.files = [];
        $('.js-uploader-file .upload-file-items .upload-item').each(function () {
            rs.files.push({
                fileName: $(this).data('name'),
                fileUrl: $(this).data('url'),
            })
        })
        return rs;
    }
    // 保存数据
    function saveData (data) {
        if (id) {
            $.getData({
                url: '/supplier/updateById',
                body: {
                    id:id,
                    address: data.pany_address,
                    bankAccount: data.bank_num,
                    bankName: data.bank,
                    cardAUrl: data.cardAUrl,
                    cardBUrl: data.cardBUrl,
                    contact: data.pany_man,
                    contentId: data.FW_type,
                    contentName: data.FW_typecon,
                    fileList: data.files,
                    legalName: data.YY_man,
                    licenseNo: data.YY_num,
                    licenseUrl: data.licenseUrl,
                    name: data.pany,
                    no:data.no,
                    phone: data.pany_tel,
                    rate:data.pay_text,
                    taxpayerType:data.pay_text_man,
                    typeId: data.CL_type,
                    typeName: data.CL_typecon,
                    levelId:data.grade,
                    // levelName:data.gradecon
                }
            }, function (res) {
                if (res) {
                    $.toast(res.message);
                    if (res.code === '0') {
                        setTimeout(function(){
                            location.href = './archive-list.html';
                        },2000)
                    }
                }
            });
        } else {
            $.getData({
                url: '/supplier/saveSupplier',
                body: {
                    address: data.pany_address,
                    bankAccount: data.bank_num,
                    bankName: data.bank,
                    cardAUrl: data.cardAUrl,
                    cardBUrl: data.cardBUrl,
                    contact: data.pany_man,
                    contentId: data.FW_type,
                    contentName: data.FW_typecon,
                    gysFileList: data.files,
                    legalName: data.YY_man,
                    licenseNo: data.YY_num,
                    licenseUrl: data.licenseUrl,
                    name: data.pany,
                    no: data.YY_num,
                    phone: data.pany_tel,
                    rate:data.pay_text,
                    taxpayerType:data.pay_text_man,
                    typeId: data.CL_type,
                    typeName: data.CL_typecon,
                    levelId:data.grade,
                    // levelName:data.gradecon
                },
            }, function (res) {
                if (res) {
                    $.toast(res.message);
                    if (res.code === '0') {
                        setTimeout(function(){
                            location.href = './archive-list.html';
                        },2000)
                    }
                }
            });
        }
    }
    function init(){
        if (id) {
            getDetail(id)
        } else {
            $('#Headtxt').text('新建供应商');
            getType()
            getService()
            getGrade()
            $('.js-main').html(template('archive-add'));
            $.uploader({
                el: '.js-uploader1',
                url: '/uploadDown/uploadImages',
                maxLength: 1,
                tips:''
            });
            $.uploader({
                el: '.js-uploader2',
                url: '/uploadDown/uploadImages',
                maxLength: 1,
                tips:''
            });
            $.uploader({
                el: '.js-uploader3',
                url: '/uploadDown/uploadImages',
                maxLength: 1,
                tips:''
            });
            $.uploadFile({
                el: '.js-uploader-file',
                onError: function () {
                    $.alert('文件上传失败，请重新上传!');
                }
            });
        }
        $(document).on('click', '.js-save', function () {
            var data = collectData();
            if (data) {
                saveData(data);
            }
        });
        // 取消
        $(document).on('click', '.js-cancel', function () {
            history.back();
        });
        // $("#pay-text").blur(function(){
        //     var value = $("#pay-text").val();
        //     if(!reg.test(value)){
        //         $.alert("请输入大于0的整数或者保留两位小数");
        //     }
        // });
    }
    init()
});
