/*TMODJS:{"version":1,"md5":"2e9f4204500a5e0c05df0ee8cca81a28"}*/
template('archives-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,idx=$data.idx,$escape=$utils.$escape,$out='';$each(items,function(item,idx){
$out+=' <tr> <td class="text-center">';
$out+=$escape($helpers. fillZero(idx ));
$out+='</td> <td><a href="./archives-detail.html?id=';
$out+=$escape(item.prId);
$out+='">';
$out+=$escape(item.prName);
$out+='</a></td> <td>';
$out+=$escape(item.prPhoneFirst);
$out+='</td> <td>';
$out+=$escape(item.ctName);
$out+='</td> <td>';
$out+=$escape(item.clName);
$out+='</td> <!-- <td class="text-center act"> <div class="btn-group more"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">更多</button> <ul class="dropdown-menu"> <li><a href="./archives-add.html?id=';
$out+=$escape(item.prId);
$out+='">编辑</a></li> <li><a href="#" class="js-del" data-id="';
$out+=$escape(item.prId);
$out+='">删除</a></li> </ul> </div> </td> --> <td class="text-center"> <a href="./archives-add.html?id=';
$out+=$escape(item.prId);
$out+='" class="js-edit" data-id="1">编辑</a> <a href="#" class="js-del" data-id="';
$out+=$escape(item.prId);
$out+='">删除</a> </td> </tr> ';
});
return new String($out);
});