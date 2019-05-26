/*TMODJS:{"version":13,"md5":"a82a8bf1b78d396e2c988746e7ef7de1"}*/
template('plan/type',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,itemIndex=$data.itemIndex,$escape=$utils.$escape,$out='';$out+=' ';
$each(items,function(item,itemIndex){
$out+=' <tr> <td>';
$out+=$escape(itemIndex+1);
$out+='</td> <td> <div class="cont dot" title="';
$out+=$escape(item.typeName);
$out+='">';
$out+=$escape(item.typeName);
$out+='</div> </td> <td> ';
if(item.typeCode===0){
$out+=' <div class="cont dot">默认</div> ';
}else{
$out+=' <div class="cont dot">自定义</div> ';
}
$out+=' </td> <td> <div class="cont dot" title="';
$out+=$escape(item.typeDesc);
$out+='">';
$out+=$escape(item.typeDesc);
$out+='</div> </td> <td> <div class="btn-group"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">查看更多 <span class="caret"></span></button> <ul class="dropdown-menu"> <li> <a href="add-type.html?id=';
$out+=$escape(item.id + '&name=' + item.typeName);
$out+='">编辑</a></li> <li>';
if(item.typeCode===1){
$out+='<a class="js-del" data-id="';
$out+=$escape(item.id);
$out+='">删除</a>';
}
$out+='</li> </ul> </div> </td> </tr> ';
});
return new String($out);
});