/*TMODJS:{"version":1,"md5":"7c3426f7df23917bfc3a11a82997e0b3"}*/
template('plan/reserve-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,idx=$data.idx,$escape=$utils.$escape,itom=$data.itom,$index=$data.$index,$out='';$each(items,function(item,idx){
$out+=' <tr> <td>';
$out+=$escape(idx + 1);
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
$out+=' <div href="javascript:;}" class="img-text"> <span>';
$out+=$escape(item.rrRoomName);
$out+='</span> </div> </div> </td> <td>';
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
$out+=$escape(item.createEiName);
$out+='</td> <td class="text-center"> <a href="./order-detail.html?id=';
$out+=$escape(item.id);
$out+='">查看</a> </td> </tr> ';
});
$out+=' ';
return new String($out);
});