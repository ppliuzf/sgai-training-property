/*TMODJS:{"version":3,"md5":"797222809a3e374d7cf7cf7ede7262bf"}*/
template('result/side',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,data=$data.data,$out='';$out+='<div class="d-title">基础信息</div> <div class="d-info"> <div class="d-desc">任务名称</div> <div class="d-val">';
$out+=$escape(data.name);
$out+='</div> </div> <div class="d-info"> <div class="d-desc">状态</div> <div class="d-val">';
$out+=$escape(data.status);
$out+='</div> </div> <div class="d-info"> <div class="d-desc">关联对象</div> <div class="d-val">';
$out+=$escape(data.objName);
$out+='</div> </div> <div class="d-info"> <div class="d-desc">执行时间</div> <div class="d-val">';
$out+=$escape($helpers. dateFormat(data.checkTime , 'yyyy-MM-dd hh:mm:ss'));
$out+='</div> </div> <div class="d-info"> <div class="d-desc">成果描述</div> <div class="d-val">';
$out+=$escape(data.resultDesc);
$out+='</div> </div> <div class="d-info"> <div class="d-desc">责任岗位</div> <div class="d-val">';
$out+=$escape(data.createName);
$out+='</div> </div> ';
return new String($out);
});