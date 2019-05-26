/*TMODJS:{"version":1,"md5":"8fa4d00f7a0266f278caabe7cdcd3853"}*/
template('common/select',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,xxid=$data.xxid,$out='';$each(items,function(item,$index){
$out+=' <option value="';
$out+=$escape(item.value);
$out+='" ';
$out+=$escape(item.value == xxid && 'selected');
$out+='>';
$out+=$escape(item.title);
$out+='</option> ';
});
return new String($out);
});