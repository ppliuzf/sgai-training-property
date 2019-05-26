/*TMODJS:{"version":3,"md5":"c0e6879e5970f87789703655f61b3df7"}*/
template('plan/meeting-location-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <tr> <td>';
$out+=$escape(item.rpPositionName);
$out+='</td> <td>';
$out+=$escape(item.rpPositionDesc);
$out+='</td> <td> <a href="#" class="js-del" data-id="';
$out+=$escape(item.rpId);
$out+='">删除</a> </td> </tr> ';
});
return new String($out);
});