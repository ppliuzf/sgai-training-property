/*TMODJS:{"version":1,"md5":"869c667d94d086788337aa381a1a64a8"}*/
template('type-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,idx=$data.idx,$escape=$utils.$escape,$out='';$each(items,function(item,idx){
$out+=' <tr> <td class="text-center">';
$out+=$escape($helpers. fillZero(idx ));
$out+='</td> <td>';
$out+=$escape(item.typeName);
$out+='</td> <td>';
$out+=$escape(item.typeDesc);
$out+='</td> <!-- <td class="text-center act"> <div class="btn-group more"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">更多</button> <ul class="dropdown-menu"> <li><a href="./type-add.html?id=';
$out+=$escape(item.ctId);
$out+='">编辑</a></li> <li> ';
if(item.typeStatus === 1){
$out+=' <a href="#" data-id="';
$out+=$escape(item.ctId);
$out+='" class="js-del">删除</a> ';
}
$out+=' </li> </ul> </div> </td> --> <td class="text-center"> <a href="./type-add.html?id=';
$out+=$escape(item.ctId);
$out+='" class="js-edit" data-id="1">编辑</a> ';
if(item.typeStatus === 1){
$out+=' <a href="#" data-id="';
$out+=$escape(item.ctId);
$out+='" class="js-del">删除</a> ';
}
$out+=' </td> </tr> ';
});
return new String($out);
});