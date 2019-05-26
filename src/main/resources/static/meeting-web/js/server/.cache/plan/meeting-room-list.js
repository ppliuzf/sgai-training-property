/*TMODJS:{"version":8,"md5":"9b8360d7e063f3cd14ae21eb28868535"}*/
template('plan/meeting-room-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <tr> <td>';
$out+=$escape(item.rrRoomName);
$out+='</td> <td>';
$out+=$escape(item.rrRoomPosition);
$out+='</td> <td>';
$out+=$escape(item.rrRoomPeoples);
$out+='</td> <td>';
$out+=$escape(item.rdRoomDevice);
$out+='</td> <td>管理员</td> <td> <div class="switch"> <input type="checkbox" ';
$out+=$escape(item.rrRoomState == 1 && 'checked');
$out+=' class="js-status" data-id="';
$out+=$escape(item.rrId);
$out+='"> </div> </td> <td><a href="#" data-id="';
$out+=$escape(item.rrId);
$out+='" data-state="';
$out+=$escape(item.rrRoomState);
$out+='" class="js-del">删除</a></td> </tr> ';
});
return new String($out);
});