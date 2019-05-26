/*TMODJS:{"version":2,"md5":"bf606b9913a83e79327111a1447de551"}*/
template('templates/common/pages',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,count=$data.count,pageSize=$data.pageSize,items=$data.items,$each=$utils.$each,item=$data.item,$index=$data.$index,$out='';$out+='<ul class="pagination"> <li class="count"><span>共 ';
$out+=$escape(count);
$out+=' 条，';
$out+=$escape(pageSize || 1);
$out+=' 页</span></li> <li class="first"><a href="javascript:">&laquo;</a></li> ';
if(items.length){
$out+=' ';
$each(items,function(item,$index){
$out+=' <li class="item" data-page="';
$out+=$escape(item);
$out+='"><a href="javascript:">';
$out+=$escape(item);
$out+='</a></li> ';
});
$out+=' ';
}else{
$out+=' <li class="item active"><a href="javascript:">1</a></li> ';
}
$out+=' <li class="last"><a href="javascript:">&raquo;</a></li> </ul>';
return new String($out);
});