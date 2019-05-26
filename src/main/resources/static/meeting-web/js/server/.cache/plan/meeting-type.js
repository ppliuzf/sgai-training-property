/*TMODJS:{"version":2,"md5":"a5f608513150b9705dd0967eb5de8589"}*/
template('plan/meeting-type',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <tr class="row"> <td class="col-xs-2 col-sm-2 col-md-2 col-lg-2">';
$out+=$escape(item.rtName);
$out+='</td> <td class="col-xs-6 col-sm-6 col-md-6 col-lg-6">';
$out+=$escape(item.rtTypeDesc);
$out+='</td> <td class="col-xs-2 col-sm-2 col-md-2 col-lg-2">';
$out+=$escape(item.rtTypeName);
$out+='</td> <td class="col-xs-2 col-sm-2 col-md-2 col-lg-2">-</td> </tr> ';
});
return new String($out);
});