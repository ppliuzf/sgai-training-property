/*TMODJS:{"version":1,"md5":"696f072c8ef1115cc1f930b6d346198f"}*/
template('common/work-empty',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,colspan=$data.colspan,text=$data.text,$out='';$out+=' <table class="table table-bordered table-hover table-striped"> <thead> <tr> <th class="text-center">费用名称</th> <th>单价(元)</th> <th>单位</th> <th>描述</th> <th width="92" class="text-center">操作</th> </tr> </thead> <tbody class="js-list"> <tr> <td colspan="';
$out+=$escape(colspan);
$out+='" class="text-center">';
$out+=$escape(text || '暂无记录');
$out+='</td> </tr> </tbody> </table>';
return new String($out);
});