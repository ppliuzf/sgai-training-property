/*TMODJS:{"version":1,"md5":"1c83300cf1d3cefba256ccf17181d97b"}*/
template('common/select-op',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,value=$data.value,$out='';$each(items,function(item,$index){
$out+=' <option value="';
$out+=$escape(item.rtName);
$out+='" ';
$out+=$escape(item.rtName==value && 'selected');
$out+=' data-id="';
$out+=$escape(item.id);
$out+='">';
$out+=$escape(item.rtName);
$out+='</option> ';
});
return new String($out);
});