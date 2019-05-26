/*TMODJS:{"version":6,"md5":"bc34aad525cdef1b704046fbf96bd5dd"}*/
template('plan/reserve-meeting-room',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <tr> <td> ';
$out+=$escape(item.rrRoomName);
$out+=' </td> <td>';
$out+=$escape(item.rrRoomPosition);
$out+='</td> <td>';
$out+=$escape(item.rrRoomPeoples);
$out+='</td> <td>';
$out+=$escape(item.rdRoomDevice);
$out+='</td> <td><a href="#" class="js-order" data-id="';
$out+=$escape(item.id);
$out+='">预订</a></td> </tr> ';
});
return new String($out);
});