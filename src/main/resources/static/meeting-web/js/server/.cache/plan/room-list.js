/*TMODJS:{"version":3,"md5":"926e5054244583e9bb8391d9cebc6084"}*/
template('plan/room-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,idx=$data.idx,$escape=$utils.$escape,itom=$data.itom,$index=$data.$index,$out='';$each(items,function(item,idx){
$out+=' <tr> <!-- <td><input type="checkbox" class="js-list-select-single" data-id="';
$out+=$escape(item.rrId);
$out+='"></td> --> <td class="text-center">';
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
$out+=' <label class="img-text" style="font-weight:100"> <span>';
$out+=$escape(item.rrRoomName);
$out+='</span> </label> </div> </td> <td>';
$out+=$escape(item.rrRoomPosition);
$out+='</td> <td>';
$out+=$escape(item.rrRoomPeoples);
$out+='人</td> <td> <div> ';
if(item.deviceDetailVoList.length>0){
$out+=' 已启用：';
$each(item.deviceDetailVoList,function(itom,$index){
$out+=' ';
if(itom.rdsState==0){
$out+=' <a href="javascript:;" class="device-txt" data-rdsId="';
$out+=$escape(itom.rdsId);
$out+='" data-rdsState="';
$out+=$escape(itom.rdsState);
$out+='">';
$out+=$escape(itom.rdRoomDevice);
$out+='</a> ';
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
$out+=' <a href="javascript:;" class="device-txt" data-rdsId="';
$out+=$escape(itom.rdsId);
$out+='" data-rdsState="';
$out+=$escape(itom.rdsState);
$out+='">';
$out+=$escape(itom.rdRoomDevice);
$out+='</a> ';
}
$out+=' ';
});
$out+=' ';
}
$out+=' </div> </td> <td>';
$out+=$escape(item.rrAdminName || '-');
$out+='</td> <td> <!-- <div class="switch"> <div class="switch-inner"> <input type="checkbox" ';
$out+=$escape(item.rrRoomState==1 && 'checked');
$out+=' class="js-status" data-id="';
$out+=$escape(item.rrId);
$out+='"> </div> </div> --> <div class="switch"> <div class="switch-inner"> <input type="checkbox" ';
$out+=$escape(item.rrRoomState==1 && 'checked');
$out+=' class="js-status" data-id="';
$out+=$escape(item.rrId);
$out+='"> </div> </div> </td> <td class="text-center"> <a href="javascript:;" onClick="window.open(\'./editor.html?id=';
$out+=$escape(item.id);
$out+='\',\'_self\')"class="js-editor">编辑</a> <a href="javascript:;" data-id="';
$out+=$escape(item.rrId);
$out+='" data-state="';
$out+=$escape(item.rrRoomState);
$out+='" class="js-del" data-id="1">删除</a> <a href="javascript:;" onClick="window.open(\'./reserve-message.html?id=';
$out+=$escape(item.id);
$out+='\',\'_self\')"class="js-editor">预定信息</a> </td> </tr> ';
});
return new String($out);
});