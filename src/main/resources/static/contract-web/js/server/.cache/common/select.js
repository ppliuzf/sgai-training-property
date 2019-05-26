/*TMODJS:{"version":22,"md5":"86a26c6b3a234e7287d7c8d6499c8652"}*/
template('common/select',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,eid=$data.eid,$out='';$each(items,function(item,$index){
$out+=' <option value="';
$out+=$escape(item.value);
$out+='" ';
$out+=$escape(item.value == eid && 'selected');
$out+=' data-limit=';
$out+=$escape(item.limit);
$out+='>';
$out+=$escape(item.title);
$out+='</option> ';
});
return new String($out);
});