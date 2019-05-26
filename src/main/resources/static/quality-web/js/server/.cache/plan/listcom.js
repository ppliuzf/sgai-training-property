/*TMODJS:{"version":18,"md5":"46b44c796d2662a6866b300386e64cf4"}*/
template('plan/listcom',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,index=$data.index,$index=$data.$index,$escape=$utils.$escape,$out='';$out+=' ';
$each(items,function(item,index,$index){
$out+=' <button class="link-set-btn" data="';
$out+=$escape(item);
$out+='">';
$out+=$escape(item.title);
$out+='</button> ';
});
$out+=' ';
return new String($out);
});