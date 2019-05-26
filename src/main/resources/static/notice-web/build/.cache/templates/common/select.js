/*TMODJS:{"version":2,"md5":"8ceec532c5a9497ec0121c108d5f4a48"}*/
template('templates/common/select',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,value=$data.value,$out='';$each(items,function(item,$index){
$out+=' <option value="';
$out+=$escape(item.value);
$out+='" ';
$out+=$escape(item.value == value && 'selected');
$out+='>';
$out+=$escape(item.title);
$out+='</option> ';
});
return new String($out);
});