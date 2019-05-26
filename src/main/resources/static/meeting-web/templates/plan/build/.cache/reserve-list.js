/*TMODJS:{"version":1,"md5":"241d70ca03b8dc5911caf7210a08ef79"}*/
template('reserve-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,idx=$data.idx,$escape=$utils.$escape,itom=$data.itom,$index=$data.$index,$out='';$each(items,function(item,idx){
$out+=' <tr> <td>';
$out+=$escape(idx + 1);
$out+='</td> <td> <div class="img-text-info"> ';
if(item.roomImg){
$out+=' <a href="';
$out+=$escape(item.roomImg);
$out+='" target="_blank" data-lightbox="item-';
$out+=$escape(idx);
$out+='"> <img class="default-image" src="';
$out+=$escape(item.roomImg);
$out+='" onerror="this.src=\'./images/default_image.png\'"> </a> ';
}else{
$out+=' <a> <img src="./images/default_image.png" class="default-image"> </a> ';
}
$out+=' <span class="img-text">';
$out+=$escape(item.rrRoomName);
$out+='</span> </div> </td> <td>';
$out+=$escape(item.rrRoomPosition);
$out+='</td> <td>';
$out+=$escape(item.rrRoomPeoples);
$out+='人</td> <td> <div> ';
if(item.deviceDetailVoList.length>0){
$out+=' 已启用：';
$each(item.deviceDetailVoList,function(itom,$index){
$out+=' ';
if(itom.rdsState==0){
$out+=' <span class="device-txt" data-rdsId="';
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
$out+=' <span class="device-txt" data-rdsId="';
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
$out+=' </div> </td> <td class="text-center"> <a href="javascript:;" class="js-order" data-id="';
$out+=$escape(item.id);
$out+='">预定</a> </td> <td class="text-center"> <a href="javascript:;" class="js-order" data-id="';
$out+=$escape(item.id);
$out+='">预定</a> </td> </td> <td class="text-center"> <a href="javascript:;" class="js-order" data-id="';
$out+=$escape(item.id);
$out+='">预定</a> </td> </tr> ';
});
return new String($out);
});