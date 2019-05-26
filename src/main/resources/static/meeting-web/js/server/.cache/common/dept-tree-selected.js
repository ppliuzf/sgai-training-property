/*TMODJS:{"version":1,"md5":"e4e4c77244d94886189150e7b33aa7b9"}*/
template('common/dept-tree-selected',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <span class="js-multi-select-item item" data-id="';
$out+=$escape(item.id);
$out+='">';
$out+=$escape(item.title);
$out+='</span> ';
});
$out+=' ';
return new String($out);
});