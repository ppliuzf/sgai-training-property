/*TMODJS:{"version":35,"md5":"1c2de38219e958e571842adb5829d1bd"}*/
template('result/detail-side',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,items=$data.items,$out='';$out+='<h4>基础信息</h4> <dl class="item"> <dt>任务名称</dt> <dd class="cont">';
$out+=$escape(items.name);
$out+='</dd> </dl> <dl class="item"> <dt>状态</dt> <dd class="cont">';
$out+=$escape(items.status);
$out+='</dd> </dl> <dl class="item"> <dt>关联对象</dt> <dd class="cont">';
$out+=$escape(items.objName);
$out+='</dd> </dl> <dl class="item"> <dt>执行时间</dt> <dd class="cont">';
$out+=$escape($helpers. dateFormat(items.checkTime , 'yyyy-MM-dd hh:mm:ss'));
$out+='</dd> </dl> <dl class="item"> <dt>成果描述</dt> <dd class="cont">';
$out+=$escape(items.resultDesc);
$out+='</dd> </dl> <dl class="item"> <dt>责任岗位</dt> <dd class="cont">';
$out+=$escape(items.createName);
$out+='</dd> </dl> ';
return new String($out);
});