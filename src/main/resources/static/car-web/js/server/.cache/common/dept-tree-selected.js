/*TMODJS:{"version":1,"md5":"708a2d3683bbde76d81f5b048f4dcc56"}*/
template('common/dept-tree-selected',function($data,$filename
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