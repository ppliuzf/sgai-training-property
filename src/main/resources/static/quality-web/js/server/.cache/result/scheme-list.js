/*TMODJS:{"version":1,"md5":"accdc78323e33cf7eb2d43f59955e059"}*/
template('result/scheme-list',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <tr> <td>';
$out+=$escape(item.name);
$out+='</td> <td><div class="text-ellipsis text-center">';
$out+=$escape(item.description);
$out+='</div></td> <td>';
$out+=$escape(item.professionalCategory);
$out+='</td> <td>';
$out+=$escape(item.operator);
$out+='</td> <td>';
$out+=$escape($helpers. dateFormat(item.updateTime , 'yyyy-MM-dd hh:mm'));
$out+='</td> <td> <span><a class="underline" data-id="';
$out+=$escape(item.id);
$out+='" href="pages/check/detail-check.html?id=';
$out+=$escape(item.id);
$out+='">配置方案</a></span> <span><a class="js-del underline" href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='">删除</a></span> </td> </tr> ';
});
$out+=' ';
return new String($out);
});