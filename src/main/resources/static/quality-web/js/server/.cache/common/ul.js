/*TMODJS:{"version":31,"md5":"ad9ce00ae230fe558100bcd6f53bdcdd"}*/
template('common/ul',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <li data-id="';
$out+=$escape(item.typeId);
$out+='" data-name="';
$out+=$escape(item.typeName);
$out+='" class="type-item">';
$out+=$escape(item.typeName);
$out+='</li> ';
});
return new String($out);
});