/*TMODJS:{"version":1,"md5":"b58b2060ab07966bc5357f62b0b0f6dd"}*/
template('device-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,ids=$data.ids,$out='';$out+='<div class="pop-device-list"> ';
$each(items,function(item,$index){
$out+=' <div class="form-inline" data-id="';
$out+=$escape(item.id);
$out+='"> <div class="form-group js-itemid" data-id="';
$out+=$escape(item.id);
$out+='" data-name="';
$out+=$escape(item.rdRoomDevice);
$out+='" data-status="0"> <input class=\'check-device\' type="checkbox" ';
$out+=$escape(ids.indexOf(item.id)> -1 && 'checked');
$out+='>&nbsp;';
$out+=$escape(item.rdRoomDevice);
$out+=' </div> <!-- <div class=" form-group switch"> <input type="checkbox" checked class="checkout" data-id="';
$out+=$escape(item.id);
$out+='"> </div> --> <div class="form-group"> ';
if(item.rrState === 1){
$out+=' <a class="js-device-remove"> <i class="glyphicon glyphicon-remove"></i> </a>';
}
$out+=' </div> </div> ';
});
$out+=' </div> <div class="pop-device-custom"> <div class="js-device-user form-inline"></div> <a class="js-device-add"> <i class="glyphicon glyphicon-plus"></i> 自定义添加</a> </div> ';
return new String($out);
});