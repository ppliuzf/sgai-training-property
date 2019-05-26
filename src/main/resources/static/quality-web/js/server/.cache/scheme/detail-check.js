/*TMODJS:{"version":1,"md5":"352aa8705c1a6009df7234447ed8b825"}*/
template('scheme/detail-check',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,items=$data.items,$each=$utils.$each,item=$data.item,$index=$data.$index,$out='';$out+='<div class="row header"> <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8"> <h4>';
$out+=$escape(items.tiName);
$out+='</h4> </div> <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right"> <button type="button" class="btn btn-default btn-back js-check-back">返回</button> </div> </div> <h4>基础信息:</h4> <div class="row"> <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> <div class="row"> <div class="col-sm-4 col-md-3"> <dl> <dt>检验项名称:</dt> <dd>';
$out+=$escape(items.tiName);
$out+='</dd> </dl> </div> <div class="col-sm-4 col-md-3"> <dl> <dt>专业范畴:</dt> <dd>';
$out+=$escape(items.pcName);
$out+='</dd> </dl> </div> <div class="col-sm-4 col-md-3"> <dl> <dt>创建人:</dt> <dd>';
$out+=$escape(items.createEiName);
$out+='</dd> </dl> </div> <div class="col-sm-4 col-md-3"> <dl> <dt>创建时间:</dt> <dd>';
$out+=$escape($helpers. dateFormat(items.createTime ,  'yyyy-MM-dd hh:mm'));
$out+='</dd> </dl> </div> </div> </div> </div> <h4>检验标准:</h4> <div class="row"> <div class="col-sm-12 col-md-12">';
$out+=$escape(items.tiStandard);
$out+='</div> </div> <div class="row" style="padding:10px 0;"> ';
$each(items.enclosures,function(item,$index){
$out+=' <div class="col-sm-1 col-md-1"> <a href="';
$out+=$escape(item.riImgUrl);
$out+='" data-lightbox="fuck"><img src="';
$out+=$escape(item.riImgUrl);
$out+='" width="80"></a> </div> ';
});
$out+=' </div> <h4>答案类型: <span style="font-size: 14px; color: #000;font-weight: bold;">';
$out+=$escape(items.tiIsInput === 0 ? '数值' : '单选');
$out+='</span></h4> ';
if(items.tiIsInput===0){
$out+=' <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> <div class="row"> <div class="col-sm-4 col-md-3"> <dl> <dt>正常值下限:</dt> <dd>';
$out+=$escape(items.tiMin);
$out+='</dd> </dl> </div> <div class="col-sm-4 col-md-3"> <dl> <dt>正常值上限:</dt> <dd>';
$out+=$escape(items.tiMax);
$out+='</dd> </dl> </div> <div class="col-sm-4 col-md-3"> <dl> <dt>数值单位:</dt> <dd>';
$out+=$escape(items.tiUnit);
$out+='</dd> </dl> </div> </div> </div> ';
}else{
$out+=' <ul class="answer-type"> ';
$each(items.newOptions,function(item,$index){
$out+=' <li> ';
if(item.isOk){
$out+=' <i class="icon-ok"></i> ';
}else{
$out+=' <i class="icon-error"></i> ';
}
$out+=' ';
$out+=$escape(item.value);
$out+=' </li> ';
});
$out+=' </ul> ';
}
$out+=' <h4>缺陷整改:</h4> <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> <div class="row"> <div class="col-sm-4 col-md-3"> <dl> <dt>责任岗位:</dt> <dd>';
$out+=$escape(items.tiPostName);
$out+='</dd> </dl> </div> <div class="col-sm-4 col-md-3"> <dl> <dt>完工期限:</dt> <dd> ';
if(items.tiLimitTime ){
$out+=' <span>';
$out+=$escape(items.tiLimitTime);
$out+=$escape(items.tiFinshUnit === 1 ? '天' : items.tiFinshUnit === 2 ? '小时' : '分钟');
$out+='</span> ';
}
$out+=' </dd> </dl> </div> <div class="col-sm-4 col-md-3"> <dl> <dt>整改要求:</dt> <dd>';
$out+=$escape(items.tiRectificationRequirements);
$out+='</dd> </dl> </div> </div> </div>';
return new String($out);
});