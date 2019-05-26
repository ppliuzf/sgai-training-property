/*TMODJS:{"version":1,"md5":"e53da2c4b6e6631269b844cc0803ae1c"}*/
template('select-op',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items.data,function(item,$index){
$out+=' <option value="';
$out+=$escape(item.rtName);
$out+='" ';
$out+=$escape(item.id==items.rtId && 'selected');
$out+=' data-id="';
$out+=$escape(item.id);
$out+='">';
$out+=$escape(item.rtName);
$out+='</option> ';
});
return new String($out);
});