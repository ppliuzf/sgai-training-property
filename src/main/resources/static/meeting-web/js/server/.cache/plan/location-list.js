/*TMODJS:{"version":1,"md5":"ea7cfb1b78da716708c61702055a2552"}*/
template('plan/location-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,idx=$data.idx,$escape=$utils.$escape,$out='';$each(items,function(item,idx){
$out+=' <tr> <!-- <td><input type="checkbox" data-id="';
$out+=$escape(item.rpId);
$out+='" class="js-list-select-single"></td> --> <td>';
$out+=$escape(idx + 1);
$out+='</td> <td>';
$out+=$escape(item.rpPositionName);
$out+='</td> <td>';
$out+=$escape(item.rpPositionDesc);
$out+='</td> <td class="text-center">  <!-- <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">查看更多 <span class="caret"></span></button> <ul class="dropdown-menu"> <li><a href="javascript:;" class="js-del" data-id="';
$out+=$escape(item.rpId);
$out+='">删除</a></li> </ul> -->  <a href="javascript:;" class="js-del" data-id="';
$out+=$escape(item.rpId);
$out+='" class="js-del" data-id="1">删除</a> </td> </tr> ';
});
return new String($out);
});