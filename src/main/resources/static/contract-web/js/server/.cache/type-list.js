/*TMODJS:{"version":28,"md5":"f58fcc153b62c1926a66f47921df383e"}*/
template('type-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,idx=$data.idx,$escape=$utils.$escape,$out='';$each(items,function(item,idx){
$out+=' <tr> <td class="text-center">';
$out+=$escape(idx + 1 < 10 ? '0'+(idx + 1) : idx + 1);
$out+='</td> <td>';
$out+=$escape(item.typeName);
$out+='</td> <td>';
$out+=$escape(item.typeDescription);
$out+='</td> <td>';
$out+=$escape(item.total);
$out+='</td> <td>';
$out+=$escape(item.limitValue);
$out+='万</td>       <!--<a href="./type-add.html?id=';
$out+=$escape(item.id);
$out+='" class="js-edit">编辑</a>-->  <!--';
if(item.isDelete !== 3){
$out+='-->  <!--<a href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='" class="js-del">删除</a>-->  <!--';
}
$out+='-->    <td class="text-center" style="white-space:nowrap"> <a href="./type-add.html?id=';
$out+=$escape(item.id);
$out+='" class="js-edit">编辑</a> ';
if(item.isDelete !== 3){
$out+=' <a href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='" class="js-del">删除</a> ';
}
$out+=' </td> </tr> ';
});
return new String($out);
});