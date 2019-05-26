/*TMODJS:{"version":1,"md5":"0b956ced3a01dc945d8de0af9e48ce87"}*/
template('common/select',function($data,$filename
/*``*/) {
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