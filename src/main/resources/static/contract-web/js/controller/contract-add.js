$(function () {
    var id = $.getQueryString('id');
    id ? $('#Headtxt').text('编辑合同') : $('#Headtxt').text('新建合同');
    var currPage = 1;
    var arrcount = [];
    var ids = [];
    // 获取类型
    function getType(eid) {
        $.getData({
            url: '/type/findList'
        }, function (data) {
            if (data && data.length) {
                var _data = [];
                for (var i in data) {
                    _data.push({
                        value: data[i].id,
                        title: data[i].typeName,
                        limit: data[i].limitValue
                    });
                }
                $('#type').html(template('common/select', {
                    items: _data,
                    eid: eid
                }));
            }
        });
    }
    // 收集数据
    function collectImage() {
        var rs = [];
        $('.upload-item', '.js-uploader-image').each(function () {
            rs.push($(this).data('url'));
        });
        // rs = rs.join(' | ');
        return rs;
    }
    function collectData() {
        collectServiceAll();
        var rs = {};
        if ($.trim($('#name').val()) === '') {
            $.alert('请输入合同名称', function () {
                $('#name').select();
            });
            return false;
        }
        if ($.trim($('#total').val()) !== '') {
            if ($.trim($('#total').val()) > $('#type').find('option:selected').data('limit')) {
                $.alert('合同总额不能大于当前合同类型的合同规约值', function () {
                    $('#total').select();
                });
                return false;
            }
        } else {
            $.alert('请输入合同总额', function () {
                $('#total').select();
            });
            return false;

        }
        var reg = /(^[1-9]{1}[0-9]*$)|(^[0-9]*\.[0-9]{2}$)/;
        if (!reg.test($.trim($('#total').val()))) {
            $.alert('合同总额请输入两位小数', function () {
                $('#total').select();
            });
            return false;
        }
        if ($.trim($('#tel').val()) !== '' && !$('#tel').val().match(/^1[0-9]{10}.*/ig)) {
            $.alert('手机号输入格式有误');
            return false;
        }
        if ($.trim($('#signDate').val()) !== '' && $.trim($('#sureDate').val()) !== '' && $.getTimeStamp($('#signDate').val()) > $.getTimeStamp($('#sureDate').val())) {
            $.alert('生效时间不能小于签订时间');
            return false;
        }
        // if(ids.join(',') === ''){
        //     $.alert('请选择供应商');
        //     return false;
        // }
        rs.id = id;
        rs.supplierIds  = ids.join(',');
        rs.name = $.trim($('#name').val());
        rs.typeId = $.trim($('#type').val());
        rs.typeName = $('#type').find('option:selected').text();
        console.log($('#type').find('option:selected').data('limit'));
        rs.amount = +$.trim($('#total').val());
        rs.status = +$.trim($('#status').val());
        rs.ownerName = $.trim($('#aName').val());
        rs.secondPartyName = $.trim($('#bName').val());
        rs.singDate = $.getTimeStamp($('#signDate').val());
        rs.effectiveDate = $.getTimeStamp($('#sureDate').val());
        rs.creater = $.trim($('#addName').val());
        rs.phone = $.trim($('#tel').val());
        rs.description = $.trim($('#desc').val());
        rs.images = collectImage();
        rs.files = [];
        var fileElementArr = ['copy', 'agree', 'file'];
        for (var i = 0, len = fileElementArr.length; i < len; i++) {
            $('.js-uploader-' + fileElementArr[i] +' .upload-file-items .upload-item').each(function () {
                rs.files.push({
                    mark: i+1,
                    fileName: $(this).data('name'),
                    fileUrl: $(this).data('url'),
                    id: $(this).data('id') ? $(this).data('id') : ''
                })
            })
            if (fileElementArr[i] ==='copy' && !rs.files.length) {
                $.alert('请上传合同副本');
                return false;
            }
        }
        return rs;
    }
    // 保存数据
    function saveData(params) {
        $.getData({
            url: id ? '/contract/updateById' : '/contract/save',
            body: params
        }, function (res) {
            var codeFun = {
                '0': function () {
                    $.toast(id ? '编辑成功' : '保存成功');
                    setTimeout(function () {
                        location.href = './archives-list.html';
                    },2000)
                },
                '-1': function () {
                    $.alert('保存失败')
                },
                '3': function () {
                    $.alert(res.message);
                }
            }
            codeFun[res.code]();
        });
    }
    // 获取详情
    function getDetail() {
        $.getData({
            url: '/contract/getById',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                renderDetail(data);
            } else {
                $.alert('数据不存在', function () {
                    history.back();
                });
            }
        });
    }
    function ClearNullArr(arr) {
        for (var i = 0, len = arr.length; i < len; i++) {
            if (!arr[i] || arr[i] == '' || arr[i] === undefined) {
                arr.splice(i, 1);
                len--;
                i--;
            }
        }
        return arr;
    }
    // 渲染详情
    function renderDetail(resDetail) {
        $('.js-main').html(template('contract-add', resDetail));
        getType(resDetail.typeId);
        var suppliers = []
        for(var i =0;i<resDetail.suppliers.length;i++){
            var supplierss={
                supplier_id : resDetail.suppliers[i].id,
                supplier_name : resDetail.suppliers[i].name
            }
            suppliers.push(supplierss)
        }
        supplierSelected2(suppliers)
        var copyArr = [], agreeArr = [], fileArr = [];
        if (resDetail.files.length) {
            for (var i = 0, len = resDetail.files.length; i< len;i++) {
                switch (resDetail.files[i].mark) {
                    case 1:
                        copyArr.push(resDetail.files[i])
                        break;
                    case 2:
                        agreeArr.push(resDetail.files[i])
                        break;
                    case 3:
                        fileArr.push(resDetail.files[i])
                        break;
                }
            }
            // console.log(copyArr, agreeArr, fileArr);
        }
        $.counter({
            el: '#desc'
        });
        $.uploader({
            el: '.js-uploader-image',
            maxLength: 3,
            imageGroup: 'contract',
            default: resDetail.images && ClearNullArr(resDetail.images).length ? ClearNullArr(resDetail.images) : []
        });
        $.uploadFile({
            el: '.js-uploader-copy',
            title: '合同副本:',
            isRequire: true,
            btnTips: '请选择上传合同',
            default: copyArr
        });
        $.uploadFile({
            el: '.js-uploader-agree',
            title: '补充协议:',
            btnTips: '请选择上传补充协议',
            default: agreeArr
        });
        $.uploadFile({
            el: '.js-uploader-file',
            title: '其他附件:',
            btnTips: '请选择上传附件',
            tips: '注：仅限pdf、word、excel、ppt格式，大小不超过50M',
            default:  fileArr
        });
        $('#sureDate').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            minView: 'year',
            endDate: '2050-01-01',
            autoclose: true
        });
        $('#signDate').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            minView: 'year',
            endDate: '2050-01-01',
            autoclose: true
        });
    }
    //供应商弹窗
    function getsupplierTT(items){
        $.pop({
            title: '供应商选择',
            content: template('supplier-list', {
                items:items
            }),
            size: 'lg',
            noIcon: true,
            yes: function () {
                var text = supplierShow()
                supplierSelected2(text)
            }
        });
    }
    //获取供应商
    function getSuppliers() {
        $.getData({
            url: '/contract/supplierPageList',
            body: {
                name: $('#supplier-name').val()
            },
            query: {
                pageNum: currPage,
                pageSize: 10
            },
        }, function (data) {
            $.renderPage({
                count: data.count,
                curr: currPage,
                jump: function(n) {
                    currPage = n;
                    getSuppliers();
                }
            });
            if (data.list && data.list.length) {
                for ( var i = 0; i < data.list.length; i++) {
                    data.list[i].index = i + 1;
                }
                getsupplierlist(data.list)
            } else {
                renderEmpty();
            }
        });
        arrcount = supplierShow()
    }
    //渲染供应商数据
    function getsupplierlist(items) {
        $('.js-s-list').html(template('supplier-left', {
            items:items
        }));
        getServiceShow()
    }
    //第一次填充供应商
    function supplierSelected(data) {
        var c = unique2(data)
        var ch = document.getElementsByName("choose");
        var suppliers ={}
        for ( var i = 0; i < ch.length; i++) {
           if(!ch[i].checked){
             var suppliers ={
                   supplier_id :ch[i].id,
                   supplier_name :ch[i].getAttribute('supplier_name')
               };
           }
            for (var j = 0; j < c.length; j++) {
                if(c[j].supplier_id === suppliers.supplier_id){
                     c.splice(j,1)
                }
            }
        }
        $('.content-right').html(template('supplier-right', {
            items:c
        }));
        var checkNum = 0
        var ch = document.getElementsByName("choose");
        var chooseLength = ch.length;
        for ( var i = 0; i < ch.length; i++) {
            if (ch[i].checked) {
                checkNum++
            }
        }
        if (checkNum === chooseLength) {
            document.getElementsByName("allChecked")[0].checked = true;
        } else {
            document.getElementsByName("allChecked")[0].checked = false;
        }
    }
    //第二次填充供应商
    function supplierSelected2(data) {
        $('.js-suppliers').html(template('supplier-content', {
            items: data,
        }));
    }
    //选择的服务内容
    function supplierShow(){
        var lis = $('.list-right li')
        var texts = [];
        for(var i=0;i<lis.length;i++){
            var sers ={
                supplier_id:lis[i].dataset.id,
                supplier_name:lis[i].innerText
            }
            texts.push(sers)
        }
        return texts
    }
    //收集已选的服务内容
    function collectServiceAll(){
        ids.splice(0,ids.length)
        var rs = [];
        $('.js-suppliers .js-del-device').each(function () {
            rs.push({
                supplier_id: $(this).data('id'),
                supplier_name: $(this).text()
            });
            ids.push($(this).data('id'))
        });
        return rs;
    }
    //选择供应商的回显
    function getServiceShow(){
        //收集回显数据
        var list = collectServiceAll();
        var c = list.concat(arrcount)
        var checkNum = 0;
        var chooseLength = 0
        if(c.length){
            var ch = document.getElementsByName("choose");
            chooseLength = ch.length;
            for ( var i = 0; i < ch.length; i++) {
                if(ch[i].checked){
                    checkNum++
                }
                var supplier_name2 = ch[i].getAttribute('supplier_name')
                for (var j = 0; j < c.length; j++) {
                    var supplier_name1 = c[j].supplier_name
                    if(supplier_name2 === supplier_name1){
                        ch[i].checked = true
                    }
                }
            }
            supplierSelected(c);
        }
    }
    //渲染无数据
    function renderEmpty() {
        $('.js-s-list').html(template('common/record-empty', {
            colspan: 3,
            text: '暂无数据,请添加供应商'
        }));
    }
    //数组去重
    function unique2(arr1){
        var kv = {}
        for (var i = 0; i < arr1.length;) {
            if (kv[arr1[i].supplier_id + ',' + arr1[i].supplier_name]) {
                arr1.splice(i, 1);
            }
            else {
                kv[arr1[i].supplier_id + ',' + arr1[i].supplier_name] = true;
                i++;
            }
        }
        return arr1
    }
    function init() {
        $('.js-main').html(template('contract-add'));
        if (id) {
            getDetail();
        } else {
            getType();
            $.counter({
                el: '#desc'
            });
            $('#sureDate').datetimepicker({
                language: 'zh-CN',
                autoclose: true,
                startView: 2,
                minView: 2
            });
            $('#signDate').datetimepicker({
                language: 'zh-CN',
                autoclose: true,
                startView: 2,
                minView: 2
            });
            $.uploader({
                el: '.js-uploader-image',
                imageGroup: 'contract',
                maxLength: 3,
            });
            $.uploadFile({
                el: '.js-uploader-copy',
                title: '合同副本:',
                isRequire: true,
                btnTips: '请选择上传合同'
            });
            $.uploadFile({
                el: '.js-uploader-agree',
                title: '补充协议:',
                btnTips: '请选择上传补充协议'
            });
            $.uploadFile({
                el: '.js-uploader-file',
                title: '其他附件:',
                btnTips: '请选择上传附件',
                tips:'注：仅限pdf、word、excel、ppt格式，大小不超过50M'
            });
        }
        // 保存
        $(document).on('click', '.js-save', function () {
            var data = collectData();
            if (data) {
                saveData(data);
            }
        });
        // 取消
        $(document).on('click', '.js-cancel', function () {
            location.href = './archives-list.html';
        });
        $(document).on('click', '.js-date-clean', function () {
            $(this).prev().val('');
        });
        $(document).on('click', '#Suppliers .serch', function () {
            getsupplierTT();//开弹窗
            getSuppliers();//掉数据
        });
        // 搜索
        $(document).on('click', '.js-supplier-search', function() {
            currPage = 1; // 搜索的时候记得重置当前页号为１
            getSuppliers();

        });
        // 重置
        $(document).on('click', '.js-supplier-reset', function() {
            $('#supplier-name').val('')
        });
        //弹窗单选
        $(document).on('click', '[name=choose]', function() {
            // console.log(arrcount)
            var checkNum = 0;
            var chooseLength = 0;
            var ch = document.getElementsByName("choose");
            chooseLength = ch.length;
            var arr = []
            var suppliers ={
                supplier_id : '',
                supplier_name : ''
            };
            for ( var i = 0; i < ch.length; i++) {
                if(ch[i].checked) {
                    suppliers ={
                        supplier_id :ch[i].id,
                        supplier_name :ch[i].getAttribute('supplier_name')
                    };
                    arr.push(suppliers)
                    checkNum++;
                }
            }
            var c = arr.concat(arrcount)
            supplierSelected(c);
            if (checkNum === chooseLength&& checkNum!==0) {
                document.getElementsByName("allChecked")[0].checked = true;
            } else {
                document.getElementsByName("allChecked")[0].checked = false;
            }
        });
        // 弹窗全选
        $(document).on('click', '.js-supplier-all', function() {
            // console.log(arrcount)
            var ch = document.getElementsByName("choose");
            var arr = []
            var suppliers ={
                supplier_id : '',
                supplier_name : ''
            };
            if(document.getElementsByName("allChecked")[0].checked == true)
            {
                for(var i = 0;i < ch.length;i++)
                {
                    ch[i].checked=true;
                }
            } else {
                for(var i = 0;i < ch.length;i++)
                {
                    ch[i].checked = false;
                }
            }
            for ( var i = 0; i < ch.length; i++) {
                if(ch[i].checked) {
                    suppliers ={
                        supplier_id :ch[i].id,
                        supplier_name :ch[i].getAttribute('supplier_name')
                    };
                    arr.push(suppliers)
                }
            }
            var c = arr.concat(arrcount)
            supplierSelected(c);
        });
        //删除
        $(document).on('click', '.dels', function() {
            var ch = document.getElementsByName("choose");
            for ( var i = 0; i < ch.length; i++) {
                if(ch[i].checked) {
                    var supplier_name = ch[i].getAttribute('supplier_name');
                    if(supplier_name === $(this).parent().text()){
                        ch[i].checked = false;
                        if(document.getElementsByName("allChecked")[0].checked === true){
                            document.getElementsByName("allChecked")[0].checked = false
                        }
                    }
                }
            }
            for( var i = 0; i < arrcount.length; i++){
                if(arrcount[i].supplier_name===$(this).parent().text()){
                    arrcount.splice(i,1)
                }
            }
            $(this).parent().remove();
        });
        $(document).on('click', '.del', function () {
            $(this).parent().remove();
        })
    }
    init();
});
