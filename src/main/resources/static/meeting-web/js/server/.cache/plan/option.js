/*TMODJS:{"version":1,"md5":"f6174a7d2118fab7afb245620b8cda6d"}*/
template('plan/option',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,idx=$data.idx,$escape=$utils.$escape,$out='';$each(items,function(item,idx){
$out+=' <option value="';
$out+=$escape(idx);
$out+='">';
$out+=$escape(item);
$out+='</option> ';
});
return new String($out);
});