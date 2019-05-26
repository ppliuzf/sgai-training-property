$(function (){
    var id = $.getQueryString('id');
    var dataList = [];
    // 获取科目列表
    function getList(){
        $.getData({
			url: '/template/getRelationToItems',
			query: {
				templateId: id
			}
		}, function(data) {
            renderSelect(data);
		});
    }
    // 编辑-渲染输入框
    function renderDetail(list, attr, index){
        if(list && list.length !==0){
            var attrList = [];
            var valString = '';
            for(var key = 0; key < list.length; key++){
                attrList[key] = {};
                attrList[key].itemId = list[key].id;
                attrList[key].itemName = list[key].itemName;
                valString = valString + ',' + list[key].itemName;
            }
            attrList = JSON.stringify(attrList);
            $(attr).eq(index).attr('cost-list', attrList);
            valString = valString.slice(1);
            $(attr).eq(index).val(valString);
        }
    }
    // 渲染页面
    function renderSelect(data){
        $('.js-relate-data').html(template('common/tpl-relation-data', {
			items: data
        }));
        // debugger;
        for(var i = 0; i < data.length; i++){
            renderDetail(data[i].itemList, '[sub-index]', i);
            if(data[i].childSubjectList && data[i].childSubjectList.length > 0){
                for(var j = 0; j < data[i].childSubjectList.length; j++){
                    renderDetail(data[i].childSubjectList[j].itemList, '[sub-'+ i + '-secondIndex]', j);
                    if(data[i].childSubjectList[j].childSubjectList && data[i].childSubjectList[j].childSubjectList.length){
                        for(var k = 0; k < data[i].childSubjectList[j].childSubjectList.length; k++){
                            renderDetail(data[i].childSubjectList[j].childSubjectList[k].itemList, '[sub-'+ i + '-' + j +'-thirdIndex]', k);
                        }
                    }
                }
            }
        }
    }
    // 弹窗渲染
    function popWinShow(index){
        $.pop({
            title: '请选择费项',
            btn: ['确定', '取消'],
            area: ['800px', '800px'],
            noIcon: true,
            // content: '请输入拒绝理由：',
            yes: function(){
                var valString = '';
                var costList = [];
                var count = 0;
                for(var i = 0; i < $('.check-input').length; i++){
                    if($('.check-input')[i].checked){
                        costList[count] = {};
                        costList[count].itemId = $('.check-input')[i].value;
                        costList[count].itemName = $('.check-input')[i].name;
                        count++;
                        valString = valString + ',' + $('.check-input')[i].name;
                        
                    }
                }
                valString = valString.slice(1);
                $('.cost-input')[index].value = valString;
                costList = JSON.stringify(costList);
                $('.cost-input').eq(index).attr('cost-list', costList);
                $('.cost-input').attr("disabled",false);
            }
        });
        $('.modal-body').html(template('common/tpl-relation-list', {
            items: dataList
        }));
        if($('.cost-input').eq(index).attr('cost-list')){
            var checkList = $('.cost-input').eq(index).attr('cost-list');
            checkList = JSON.parse(checkList);
            for(var i = 0; i <dataList.length; i++){
                for(var j = 0; j < checkList.length; j++){
                    if(dataList[i].id === checkList[j].itemId){
                        $('.check-input').eq(i).attr('checked', 'true');
                    }
                }
            }
            console.log(checkList);
        }
        
    }
    // 获取费项列表
    function getCostList(){
        $.getData({
			url:  '/expensesItem/getList',
			query: {},
		}, function (data) {
            for(var i = 0; i <data.length; i++){
                data[i].checked = false;
            }
            dataList = data;
		});
    }
    // 收集数据
    function collectData(){
        var rs = {};
        rs.templateId = id;
        rs.subjectItems = [];
        var count = 0;
        for(var i = 0; i < $('.cost-input').length; i++){
            if($('.cost-input')[i].value){
                rs.subjectItems[count] = {};
                rs.subjectItems[count].subId = $('.cost-input').eq(i).attr('sub-id');
                rs.subjectItems[count].subCode = $('.cost-input').eq(i).attr('sub-code');
                rs.subjectItems[count].subName = $('.cost-input').eq(i).attr('sub-name');
                rs.subjectItems[count].itemList = [];
                rs.subjectItems[count].itemList = JSON.parse($('.cost-input').eq(i).attr('cost-list'));
                count++;
            }
        }
        return rs;
    }
    // 保存数据
    function saveData (data) {
        $.getData({
            url: '/template/saveRelationData',
            body: data
        }, function (data) {
            // window.history.back();
            location.href = './template-list.html';
        });
    }
    function init(){
        getList();
        getCostList();
        $(document).on('click', '.identify', function (){
            if($(this).hasClass('glyphicon-plus')){
                $(this).removeClass('glyphicon-plus');
                $(this).addClass('glyphicon-minus');
                $(this).parent().parent().nextAll().show();
            }else {
                $(this).removeClass('glyphicon-minus');
                $(this).addClass('glyphicon-plus');
                $(this).parent().parent().nextAll().hide();
            }
        });
        // 点击输入框弹出费项列表
        $(document).on('focus', '.cost-input', function (){
            var index = $('.cost-input').index(this);
            $('.cost-input').attr("disabled",true);
            popWinShow(index);
        });
        // 点击保存
        $(document).on('click', '.js-save', function (){
            var data = collectData();
            if (data) {
                saveData(data);
            }
        });
        // 取消
		$(document).on('click', '.js-cancel', function () {
			history.back();
        });
        $(document).on('click', '.js-modal-cancel,.close,#modal-pop', function () {
			$('.cost-input').attr("disabled",false);
        });
        
    }
    init();
});