/*TMODJS:{"version":1,"md5":"d6f9d350cbb4f8f1effa29040c147ea7"}*/
template('dept-tree-selected',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <span class="js-multi-select-item item" data-id="';
$out+=$escape(item.id);
$out+='">';
$out+=$escape(item.title);
$out+='<i class="js-multi-select-del del"></i></span> ';
});
return new String($out);
});