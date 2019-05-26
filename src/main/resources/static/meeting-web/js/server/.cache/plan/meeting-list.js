/*TMODJS:{"version":2,"md5":"9f0af4a7b449cd10dfe2b253c51ef639"}*/
template('plan/meeting-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <tr> <td>';
$out+=$escape(item.rrRoomName);
$out+='</td> <td>';
$out+=$escape(item.rrRoomPosition);
$out+='</td> <td>';
$out+=$escape(item.rrRoomPeoples);
$out+=' 人</td> <td>';
$out+=$escape(item.rdRoomDevice);
$out+='</td> <td>';
$out+=$escape(item.miMtTimeSeg);
$out+='</td> ';
if(item.miStatus == 1){
$out+=' <td>未开始</td> ';
}else if(item.miStatus == 2){
$out+=' <td>进行中</td> ';
}else if(item.miStatus == 3){
$out+=' <td>已结束</td> ';
}else if(item.miStatus == 4){
$out+=' <td>已逾期</td> ';
}else if(item.miStatus == 5){
$out+=' <td>已取消</td> ';
}
$out+=' <td> <a href="#">编辑</a> <a href="#">取消</a> </td> </tr> ';
});
return new String($out);
});