/*TMODJS:{"version":1,"md5":"b06b7d3634c5d050b2270bd8dba95fdb"}*/
template('archives-detail-event',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,value=$data.value,$out='';$out+='<div class="form-group"> <label for="event-type" class="control-label">事件类型:</label> <div class=""> <select id="event-type" class="form-control js-event-type"> <option value="投诉事件" id="complain" data-codetype="TsCategory">投诉事件</option> <option value="维修事件" id="service" data-codetype="WxCategory">维修事件</option> <option value="安保事件" id="security" data-codetype="AbCategory">安保事件</option> </select> </div> </div> <!-- ';
$each(items,function(item,$index){
$out+=' <option value="';
$out+=$escape(item.value);
$out+='" ';
$out+=$escape(item.value==value && 'selected');
$out+='>';
$out+=$escape(item.title);
$out+='</option> ';
});
$out+=' --> <div class="form-group"> <label for="card-type" class="control-label" id="category">事件类别:</label> <div class="js-card"> <select id="card-complain" class="form-control js-event-type"> </select> </div> </div> <div class="form-group"> <label for="firstname" class="control-label" id="theName">投诉人:</label> <div class=""> <input type="text" class="form-control" id="firstname" placeholder="请输入名字" disabled="disabled" value="';
$out+=$escape(items.name);
$out+='"> </div> </div> <div class="form-group"> <label for="telephone" class="control-label">联系电话:</label> <div class=""> <input type="text" class="form-control" id="telephone" placeholder="请输入电话" disabled="disabled" value="';
$out+=$escape(items.phone);
$out+='"> </div> </div> <div class="form-group"> <label for="adress" class="control-label" id="theAddress">投诉地址：</label> <div class=""> <input type="text" class="form-control" id="adress" placeholder="请输入地址" maxlength="30"> </div> </div> <div class="form-group"> <label for="content" class="control-label" id="theContent">投诉内容：</label> <div class=""> <input type="text" class="form-control" id="content" placeholder="请输入内容" maxlength="170"> </div> </div> <div class="form-group"> <label for="exampleInputFile">上传图片</label> <div class="shangchuan"></div> </div> <div class="form-group"> <label for="telephone" class="control-label">备注：</label> <div class=""> <textarea id="desc" rows="4" class="form-control" maxlength="200"></textarea> <div class="text-right js-count">0/200</div> </div> </div>';
return new String($out);
});