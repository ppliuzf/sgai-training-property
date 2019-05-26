/*TMODJS:{"version":12,"md5":"ac150c20d897a1b1abd0c1fa7f51241e"}*/
template('plan/select',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$out+='<select> ';
$each(items,function(item,$index){
$out+=' <option value="';
$out+=$escape(item.value);
$out+='">';
$out+=$escape(item.title);
$out+='</option> ';
});
$out+=' </select>';
return new String($out);
});