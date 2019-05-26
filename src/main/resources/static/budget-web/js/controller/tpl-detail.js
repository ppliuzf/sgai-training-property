$(function (){
    var id = $.getQueryString('id');
    // 获取模板数据
    function getTplData(){
        $.getData({
			url: '/template/getById',
			query: {
				id: id
			}
		}, function(data) {
            console.log(JSON.parse(data.content));
            renderSelectBasic(data);
		});
    }
    // 渲染基础信息
	function renderSelectBasic(data) {
		$('.js-detail-top').html(template('common/tpl-detail-top', {
			item: data
        }));
        var dataTop = JSON.parse(data.content).list
        $('.js-detail-mid').html(template('common/tpl-detail-mid', {
			item: dataTop
		}));
    }
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
            $(attr).eq(index).text(valString);
            // $(attr).eq(index).text('aaa');
            console.log($(attr).eq(index));
        }else {
            $(attr).eq(index).parent().hide();

        }
    }
    // 渲染页面
    function renderSelect(data){
        $('.js-detail-mid-second').html(template('common/tpl-detail-midsecond', {
			items: data
        }));
        for(var i = 0; i < data.length; i++){
            renderDetail(data[i].itemList, '[sub-index]', i);
            if(data[i].childSubjectList && data[i].childSubjectList.length > 0){
                for(var j = 0; j < data[i].childSubjectList.length; j++){
                    var attr = '[sub-'+ i + '-secondIndex]';
                    renderDetail(data[i].childSubjectList[j].itemList, '[sub-'+ i + '-secondIndex]', j);
                    if(data[i].childSubjectList[j].childSubjectList && data[i].childSubjectList[j].childSubjectList.length){
                        for(var k = 0; k < data[i].childSubjectList[j].childSubjectList.length; k++){
                            renderDetail(data[i].childSubjectList[j].childSubjectList[k].itemList, '[sub-'+ i + '-' + j +'-thirdIndex]', k);
                        }
                    }
                }
            }
        }
        var isExit = false;
        for(var i = 0; i < data.length; i++){
            if(data[i].itemList.length > 0){
                isExit = true;
            }
            if(data[i].childSubjectList && data[i].childSubjectList.length > 0){
                for(var j = 0; j < data[i].childSubjectList.length; j++){
                    if(data[i].childSubjectList[j].itemList.length > 0){
                        isExit = true;
                    }
                    if(data[i].childSubjectList[j].childSubjectList && data[i].childSubjectList[j].childSubjectList.length){
                        for(var k = 0; k < data[i].childSubjectList[j].childSubjectList.length; k++){
                            if(data[i].childSubjectList[j].childSubjectList[k].itemList.length > 0){
                                isExit = true;
                            }
                        }
                    }
                }
            }
        }
        if(!isExit){
            $('.exit').hide();
        }
    }
    function init(){
        getTplData();
        getList();
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
        $(document).on('click', '.js-return', function (){
            window.history.back();
        });
    }
    init();
});