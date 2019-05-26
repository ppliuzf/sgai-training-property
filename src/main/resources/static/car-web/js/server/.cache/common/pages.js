/*TMODJS:{"version":1,"md5":"255af1eaaf630a984c9aa9aa30027ca2"}*/
template('common/pages',function($data,$filename
/*``*/) {
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