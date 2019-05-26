/*TMODJS:{"version":1,"md5":"ddc9727d6d150294ce2f2f572c2da0a7"}*/
template('editor',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,rrRoomName=$data.rrRoomName,rrRoomPosition=$data.rrRoomPosition,rrRoomType=$data.rrRoomType,rrRoomPeoples=$data.rrRoomPeoples,deviceDetailVoList=$data.deviceDetailVoList,$each=$utils.$each,item=$data.item,$index=$data.$index,rrRoomDesc=$data.rrRoomDesc,$out='';$out+='<div> <div class="col-xs-12 col-sm-11 col-md-9 col-lg-7"> <div class="form-group"> <label for="room-name"> <span class="require"></span>会议室名称：</label> <input type="text" class="form-control" id="room-name" maxlength="20" placeholder="请输入会议室名称" value="';
$out+=$escape(rrRoomName);
$out+='"> </div> <div class="form-group"> <label for="room-location"> <span class="require"></span>位置：</label> <input type="text" readonly="readonly" class="form-control js-room-location js-posi" id="room-location" maxlength="20" placeholder="请选择位置" value="';
$out+=$escape(rrRoomPosition);
$out+='"> </div> <div class="form-group"> <label for="room-type"> <span class="require"></span>会议类型：</label> <select id="room-type" class="form-control js-room-type" required="required" > <!-- <option selected="selected">';
$out+=$escape(rrRoomType);
$out+='</option> --> </select> </div> <div class="form-group"> <div class="form-inline"> <label for="num"> <span class="require"></span>可容纳：</label> <input type="text" min="0" class="form-control lengthinp" id="num" maxlength="10" placeholder="请输入可容纳人数" onkeyup="this.value=this.value.replace(/\\D/g,\'\')" onafterpaste="this.value=this.value.replace(/\\D/g,\'\')" value="';
$out+=$escape(rrRoomPeoples);
$out+='">&nbsp;&nbsp;人 </div> </div> <div class="form-group"> <label>设备：</label> <div class="js-room-device" > <div class="multi-selector"> <div class="inner"> ';
if(deviceDetailVoList.length>0){
$out+=' ';
$each(deviceDetailVoList,function(item,$index){
$out+=' <span data-id="';
$out+=$escape(item.id);
$out+='"> ';
$out+=$escape(item.rdRoomDevice);
$out+=' <i class="del"></i> </span> ';
});
$out+=' ';
}
$out+=' </div> </div> </div> </div> <div class="form-group room-text"> <label for="room-text">描述:</label> <textarea name="" id="room-text" rows="4" class="form-control" placeholder="会议室描述不能超过200字" maxlength="200" >';
$out+=$escape(rrRoomDesc);
$out+='</textarea> <div class="text-right js-count">0/200</div> </div> <div class="form-group"> <label>上传图片：</label> <div class="js-uploader" id="js-uploader"></div> </div> <div class="form-group select-admin"> <label> <span class="require"></span>设置会议室管理员：</label> <div class="cont js-instant-search js-approval-manager instant-search"> <input type="text" id="instant-keywords" class="layui-input"> <input type="hidden" id="instant-selected"> <div class="instant-search-content approval-name"></div> </div> </div> <button type="button" class="btn btn-primary js-save">保存</button> <button type="button" class="btn btn-default js-cancel">取消</button> </div> <div class="tree"> <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3" style=" max-height: 600px; overflow: auto;"> <form class="tree-form" role="form" id="tree-rxmj" novalidate> <ul id="tt" class="easyui-tree"></ul> </form> </div> </div> </div>';
return new String($out);
});