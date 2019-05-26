/*TMODJS:{"version":13,"md5":"cfe736fa2dea6a5b637b8f94ec43bad2"}*/
template('plan/paSelect',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,id=$data.id,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$out='';$out+='<select id="';
$out+=$escape(id);
$out+='" lay-filter="';
$out+=$escape(id);
$out+='" style="width: 60%; height: 30px"> ';
$each(items,function(item,$index){
$out+=' <option value="';
$out+=$escape(item.periodName);
$out+='" data-paId="';
$out+=$escape(item.id);
$out+='">';
$out+=$escape(item.periodName);
$out+='</option> ';
});
$out+=' </select>';
return new String($out);
});