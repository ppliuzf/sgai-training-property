/*TMODJS:{"version":1,"md5":"3c815fd8f919558d7f6346f0e818cd38"}*/
template('box-type',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,itemIndex=$data.itemIndex,$escape=$utils.$escape,$out='';$each(items,function(item,itemIndex){
$out+=' <tr> <td class=\'text-center\'>';
$out+=$escape(item.num);
$out+='</td> <td>';
$out+=$escape(item.btName);
$out+='</td> <td>';
$out+=$escape(item.btDesc);
$out+='</td> <td> ';
if(item.btStatus===0){
$out+=' <span>默认</span> ';
}else{
$out+=' <span>自定义</span> ';
}
$out+=' </td> <td class=\'text-center\'> ';
if(item.btStatus===0){
$out+=' <span style="color:#dedede;">—</span> ';
}else{
$out+=' <a href="javascript:;" data-id=';
$out+=$escape(item.id);
$out+='>删除</a> ';
}
$out+=' </td> </tr> ';
});
$out+=' ';
return new String($out);
});