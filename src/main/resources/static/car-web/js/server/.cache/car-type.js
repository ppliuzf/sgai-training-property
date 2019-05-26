/*TMODJS:{"version":1,"md5":"4afae9571462763ff8bd254f4b5b8e09"}*/
template('car-type',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,itemIndex=$data.itemIndex,$escape=$utils.$escape,$out='';$each(items,function(item,itemIndex){
$out+=' <tr> <td class=\'text-center\'>';
$out+=$escape(item.num);
$out+='</td> <td>';
$out+=$escape(item.ctName);
$out+='</td> <td>';
$out+=$escape(item.ctDesc);
$out+='</td> <td> ';
if(item.ctStatus===0){
$out+=' <span>默认</span> ';
}else{
$out+=' <span>自定义</span> ';
}
$out+=' </td> <td class=\'text-center\'> ';
if(item.ctStatus===0){
$out+=' <span style="color:#dedede;">—</span> ';
}else{
$out+=' <a href="javascript:;">删除</a> ';
}
$out+=' </td> </tr> ';
});
$out+=' ';
return new String($out);
});