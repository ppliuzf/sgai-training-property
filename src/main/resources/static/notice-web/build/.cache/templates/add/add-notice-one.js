/*TMODJS:{"version":2,"md5":"9817ae7af50530a4dc45453a87ed7047"}*/
template('templates/add/add-notice-one',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,items=$data.items,$each=$utils.$each,item=$data.item,index=$data.index,$out='';$out+='<div class="form-group"> <label for="exampleInputEmail1"><span class="require"></span>标题</label> <input type="text" class="form-control" id="infoTitle" value="';
$out+=$escape(items.infoTitle);
$out+='" placeholder="请输入标题" maxlength="50"> </div> <div class="form-group"> <label for="exampleInputEmail1"><span class="require"></span>摘要</label> <textarea type="email" rows="4" class="form-control" id="infoSummary" placeholder="请输入摘要" maxlength="200">';
$out+=$escape(items.infoSummary);
$out+='</textarea> <div class="conttask text-right"></div> </div>  <div class="form-group"> <div class="checkbox"> <label> ';
if(items.infoTimePublish == 1){
$out+=' <input type="checkbox" id="infoTimePublish" checked="checked" name="infoTimePublish" > 预约发布 ';
}else{
$out+=' <input type="checkbox" id="infoTimePublish" name="infoTimePublish" > 预约发布 ';
}
$out+=' </label> </div> ';
if(items.infoTimePublish == 1){
$out+=' <div class="" id="releaseTime"> ';
}else{
$out+=' <div class="isHide" id="releaseTime"> ';
}
$out+=' <label class="control-label">发布时间:</label> <div class="input-group"> <input id="publishTime" data-date-format="yyyy-MM-dd HH:ii" format="yyyy-MM-dd HH:ii" class="form-control form_date" size="16" type="text" id="infoTime" value="';
$out+=$escape($helpers. dateFormat(items.publishTime , 'yyyy-MM-dd hh:mm'));
$out+='" readonly> <span class="input-group-addon js-date-clean"><span class="glyphicon glyphicon-remove"></span></span> </div> </div> </div> <div class="form-group"> <label>发布到：</label> <label id="publicType"> ';
if(items.visibilityScopeArr){
$out+=' ';
$each(items.visibilityScopeArr,function(item,index){
$out+=' ';
if(item.checked == 1){
$out+=' <input type="checkbox" id="';
$out+=$escape(item.id);
$out+='" checked="checked" name="" value = \'';
$out+=$escape(item.name);
$out+='\' > ';
$out+=$escape(item.name);
$out+='&nbsp;&nbsp; ';
}else{
$out+=' <input type="checkbox" id="';
$out+=$escape(item.id);
$out+='" name="" value = \'';
$out+=$escape(item.name);
$out+='\' > ';
$out+=$escape(item.name);
$out+='&nbsp;&nbsp; ';
}
$out+=' ';
});
$out+=' ';
}else{
$out+=' <input type="checkbox" id="appPublic" checked="checked" value = \'APP\' > APP&nbsp;&nbsp; <input type="checkbox" id="pcPublic" checked="checked" value = \'PC\' > PC&nbsp;&nbsp; <input type="checkbox" id="pcPublic" value=\'投屏\'> 投屏 ';
}
$out+=' </label> </div> <div class="form-group"> <label for="exampleInputEmail1"><span class="require"></span>正文</label> <div id=\'infoContent\' name="infoContent"></div> </div> <div class="previewContent"> </div>';
return new String($out);
});