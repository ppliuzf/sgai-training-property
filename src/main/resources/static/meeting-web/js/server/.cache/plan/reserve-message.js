/*TMODJS:{"version":1,"md5":"3fa6a7433786f25cc82582bceed362ba"}*/
template('plan/reserve-message',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <tr> <td>';
$out+=$escape(item.miMtTimeSeg);
$out+='</td> <td>';
$out+=$escape(item.createEiName);
$out+='</td> <td>';
$out+=$escape(item.miMtSubject);
$out+='</td> <td>';
$out+=$escape(item.miMtContent);
$out+='</td> </tr> ';
});
return new String($out);
});