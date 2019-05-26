/*TMODJS:{"version":35,"md5":"2f644747853555ead7ab4b6fb7b05a9d"}*/
template('common/dept-tree-selected',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <span class="js-multi-select-item item" data-id="';
$out+=$escape(item.id);
$out+='">';
$out+=$escape(item.title);
$out+='<i class="js-multi-select-del del"></i></span> ';
});
return new String($out);
});