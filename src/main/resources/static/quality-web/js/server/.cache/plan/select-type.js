/*TMODJS:{"version":13,"md5":"8e67e94bc9a4e1ea23615a8de9d543c5"}*/
template('plan/select-type',function($data,$filename
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