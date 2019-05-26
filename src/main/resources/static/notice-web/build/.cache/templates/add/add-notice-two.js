/*TMODJS:{"version":2,"md5":"f07f53cf8d266f8441a704baaab6905b"}*/
template('templates/add/add-notice-two',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,items=$data.items,$out='';$out+='<div class="form-group"> <label for="exampleInputEmail1">标签</label> <input type="email" class="form-control" id="infoLabel" value="';
$out+=$escape(items.infoLabel);
$out+='" placeholder="请输入标签" maxlength="4"> </div> <div class="form-group clearfix"> <label for="exampleInputEmail1">发布范围</label> <div class="radio"> ';
if(items.infoScopeIsAll == 1){
$out+=' <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="releaseScope" id="releaseScope" checked="checked" value="1" aria-label="..."> 公开，全部可见 </label> <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="releaseScope" id="releaseScope" value="0" aria-label="..."> 部分可见 </label> ';
}else if(items.infoScopeIsAll == 0){
$out+=' <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="releaseScope" id="releaseScope" value="1" aria-label="..."> 公开，全部可见 </label> <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="releaseScope" id="releaseScope" checked="checked" value="0" aria-label="..."> 部分可见 </label> ';
}else{
$out+=' <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="releaseScope" id="releaseScope" value="1" aria-label="..."> 公开，全部可见 </label> <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="releaseScope" id="releaseScope" value="0" aria-label="..."> 部分可见 </label> ';
}
$out+=' </div> </div> <div class="form-group"> ';
if(items.infoScopeIsAll == 0){
$out+=' <div class="js-dept-selector"></div> ';
}else{
$out+=' <div class="js-dept-selector isHide"></div> ';
}
$out+=' </div> <div class="form-group clearfix"> <label for="exampleInputEmail1">类型</label> <div class="radio"> ';
if(items.infoUrgency == 1){
$out+=' <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoUrgency" id="infoUrgency0" value="0" aria-label="..."> 一般 </label> <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoUrgency" id="infoUrgency1" checked="checked" value="1" aria-label="..."> 紧急 </label> ';
}else{
$out+=' <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoUrgency" id="infoUrgency0" checked="checked" value="0" aria-label="..."> 一般 </label> <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoUrgency" id="infoUrgency1" value="1" aria-label="..."> 紧急 </label> ';
}
$out+=' </div> </div> <div id="isExamine" class="clearfix"> <div class="form-group clearfix"> <label for="exampleInputEmail1">需要审核</label> <div class="radio"> ';
if(items.infoApprovalFlag == 1){
$out+=' <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoApprovalFlag" id="infoApprovalFlag" checked="checked" value="1" aria-label="..."> 是 </label> <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoApprovalFlag" id="infoApprovalFlag" value="0" aria-label="..."> 否 </label> ';
}else{
$out+=' <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoApprovalFlag" id="infoApprovalFlag" value="1" aria-label="..."> 是 </label> <label class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <input type="radio" name="infoApprovalFlag" id="infoApprovalFlag" checked="checked" value="0" aria-label="..."> 否 </label> ';
}
$out+=' </div> </div> ';
if(items.infoApprovalFlag == 1){
$out+=' <div class="form-group clearfix js-instant-search" id="selectionAuditor"> ';
}else{
$out+=' <div class="form-group clearfix isHide js-instant-search" id="selectionAuditor"> ';
}
$out+=' <label for="exampleInputEmail1">审核人</label> <div class="js-instant-search"></div> <!-- <input type="text" id="instant-keywords" class="form-control" placeholder="请输入姓名" value="';
$out+=$escape(items.approvalEmpName);
$out+='"> <input type="hidden" id="instant-selected" value="';
$out+=$escape('{"id":'+ items.approvalEmpId +',"title":"'+ items.approvalEmpName +'"}');
$out+='"> <div class="instant-search-content isHide"></div> --> </div> </div>';
return new String($out);
});