/*TMODJS:{"version":1,"md5":"cf4a07450d10f6dbd6ef468e8e7e94df"}*/
template('archives/car-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$out+='<option value="0">全部</option> ';
$each(items,function(item,$index){
$out+=' <option value=';
$out+=$escape(item.id);
$out+='>';
$out+=$escape(item.btName);
$out+='</option> ';
});
return new String($out);
});