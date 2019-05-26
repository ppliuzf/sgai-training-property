/*TMODJS:{"version":1,"md5":"072cb50dcd2d3c883fb807257e8a41a1"}*/
template('archives-add',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,prName=$data.prName,prPhoneFirst=$data.prPhoneFirst,prPhoneSecond=$data.prPhoneSecond,prEmail=$data.prEmail,prSex=$data.prSex,prBirth=$data.prBirth,customCardInfoVos=$data.customCardInfoVos,$out='';$out+='<div class="form-group clearfix"> <div class="row clearfix"> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="name"> <span class="require"></span>姓名</label> <input type="text" id="name" class="form-control" maxlength="20" value="';
$out+=$escape(prName);
$out+='"> </div> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="reach"> <span class="require"></span>部门</label>  <input type="text" readonly="readonly" class="form-control js-room-location js-posi" id="room-location" maxlength="20" placeholder="请选择部门"> </div> </div> </div> </div> <div class="form-group "> <div class="row clearfix"> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="tel"> <span class="require"></span>手机号</label> <input type="text" id="tel" class="form-control" maxlength="11" value="';
$out+=$escape(prPhoneFirst);
$out+='"> </div> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="tel1">手机号1</label> <input type="text" id="tel1" class="form-control" maxlength="11" value="';
$out+=$escape(prPhoneSecond);
$out+='"> </div> </div> </div> <div class="form-group clearfix"> <label for="mail">邮箱</label> <input type="text" id="mail" class="form-control" maxlength="50" value="';
$out+=$escape(prEmail);
$out+='"> </div> <div class="form-group"> <div class="row clearfix"> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="sex"> <span class="require"></span>性别</label> <select name="" id="sex" class="form-control"> <option value="1" ';
$out+=$escape(prSex==1 && 'selected');
$out+='>男</option> <option value="2" ';
$out+=$escape(prSex==2 && 'selected');
$out+='>女</option> </select> </div> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="birth"> <span class="require"></span>出生日期</label> <input type="text" name="" id="birth" class="form-control" readonly value="';
$out+=$escape($helpers. dateFormat(prBirth , 'yyyy-MM-dd'));
$out+='"> </div> </div> </div> <div class="form-group"> <div class="row clearfix"> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="type"> <span class="require"></span>类型</label> <select name="" id="type" class="form-control"></select> </div> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="level"> <span class="require"></span>级别</label> <select name="" id="level" class="form-control"></select> </div> </div> </div> <div class="js-card"></div> <div class="text-right"> <i class="glyphicon glyphicon-plus-sign"></i> <a href="#" class="js-card-add">添加';
if((customCardInfoVos && customCardInfoVos.length) || !prName){
$out+='多个';
}
$out+='证件号</a> </div> <div class="form-group"> <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> <button class="btn btn-primary js-save">保存</button> <button class="btn btn-default js-cancel">取消</button> </div> </div>';
return new String($out);
});