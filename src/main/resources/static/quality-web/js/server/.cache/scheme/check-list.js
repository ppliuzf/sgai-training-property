/*TMODJS:{"version":1,"md5":"3c358aa6a873877cdb2f5ca83961bd71"}*/
template('scheme/check-list',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <tr> <td>';
$out+=$escape(item.tiName);
$out+='</td> <td><div class="text-ellipsis text-center" style="width:300px;">';
$out+=$escape(item.tiStandard);
$out+='</div></td> <td>';
$out+=$escape(item.pcName);
$out+='</td> <td>';
$out+=$escape(item.tiIsInput === 0 ? '数值' : '单选');
$out+='</td> <td>';
$out+=$escape(item.createEiName);
$out+='</td> <td>';
$out+=$escape($helpers. dateFormat(item.createTime , 'yyyy-MM-dd hh:mm'));
$out+='</td> <td> <span><a class="underline" data-id="';
$out+=$escape(item.tiId);
$out+='" href="pages/check/detail-check.html?id=';
$out+=$escape(item.tiId);
$out+='">查看</a></span> <span><a class="js-del underline" href="javascript:;" data-id="';
$out+=$escape(item.tiId);
$out+='">删除</a></span> </td> </tr> ';
});
$out+=' ';
return new String($out);
});