/*TMODJS:{"version":2,"md5":"4ad93a9c344597eb3e58b702c076ab93"}*/
template('plan/order-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,idx=$data.idx,$escape=$utils.$escape,itom=$data.itom,$index=$data.$index,$out='';$each(items,function(item,idx){
$out+=' <tr> <td class="text-center">';
$out+=$escape(idx+1 < 10 ? "0" + (idx+1) : idx+1);
$out+='</td> <td> <div class="img-text-info"> ';
if(item.roomImg){
$out+=' <a href="';
$out+=$escape(item.roomImg);
$out+='" data-lightbox="item-';
$out+=$escape(idx);
$out+='"> <img class="default-image" src="';
$out+=$escape(item.roomImg);
$out+='" onerror="this.src=\'./images/default_image.png\'"> </a> ';
}else{
$out+=' <a> <img src="./images/default_image.png" class="default-image"> </a> ';
}
$out+=' <a href="./order-detail.html?id=';
$out+=$escape(item.id);
$out+='" class="img-text"> <span>';
$out+=$escape(item.rrRoomName);
$out+='</span> </a> </div> </td> <td>';
$out+=$escape(item.rrRoomPosition);
$out+='</td> <td>';
$out+=$escape(item.rrRoomPeoples);
$out+=' 人</td> <td> <div> ';
if(item.deviceDetailVoList.length>0){
$out+=' 已启用：';
$each(item.deviceDetailVoList,function(itom,$index){
$out+=' ';
if(itom.rdsState==0){
$out+=' <span href="javascript:;" class="device-txt" data-rdsId="';
$out+=$escape(itom.rdsId);
$out+='" data-rdsState="';
$out+=$escape(itom.rdsState);
$out+='">';
$out+=$escape(itom.rdRoomDevice);
$out+='</span> ';
}
$out+=' ';
});
$out+=' ';
}
$out+=' </div> <div> ';
if(item.deviceDetailVoList.length>0){
$out+='已禁用：';
$each(item.deviceDetailVoList,function(itom,$index){
if(itom.rdsState!=0){
$out+=' <span href="javascript:;" class="device-txt" data-rdsId="';
$out+=$escape(itom.rdsId);
$out+='" data-rdsState="';
$out+=$escape(itom.rdsState);
$out+='">';
$out+=$escape(itom.rdRoomDevice);
$out+='</span> ';
}
$out+=' ';
});
$out+=' ';
}
$out+=' </div> </td> <td>';
$out+=$escape(item.miMtTimeSeg);
$out+='</td> <td>';
$out+=$escape($helpers. formatStatus(item.miStatus ));
$out+='</td> <!-- <td> ';
if(item.miStatus === 1 || item.miStatus === 2){
$out+=' <div class="btn-group"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">查看更多 <span class="caret"></span> </button> <ul class="dropdown-menu"> ';
if(item.miStatus === 1){
$out+=' <li> <a href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='" data-date="';
$out+=$escape(item.miMtDate);
$out+='" data-time="';
$out+=$escape(item.time);
$out+='" data-roomid="';
$out+=$escape(item.rrId);
$out+='" class="js-edit">编辑</a> </li> <li> <a href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='" class="js-cancel">取消</a> </li> ';
}else if(item.miStatus === 2){
$out+=' <li> <a href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='" class="js-end">结束</a> </li> ';
}
$out+=' </ul> </div> ';
}
$out+=' </td> --> <td class="text-center"> ';
if(item.miStatus === 1 || item.miStatus === 2){
$out+=' ';
if(item.miStatus === 2){
$out+=' <a href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='" data-date="';
$out+=$escape(item.miMtDate);
$out+='" data-time="';
$out+=$escape(item.time);
$out+='" data-roomid="';
$out+=$escape(item.rrId);
$out+='" class="js-edit">编辑</a> <a href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='" class="js-cancel" >取消</a> ';
}else if(item.miStatus === 1){
$out+=' <a href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='" class="js-end">结束</a> ';
}
$out+=' ';
}
$out+=' </td> </tr> ';
});
$out+=' ';
return new String($out);
});