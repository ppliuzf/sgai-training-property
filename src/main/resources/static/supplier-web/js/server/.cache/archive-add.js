/*TMODJS:{"version":1,"md5":"825ed593e97e06755efd219b12952e1d"}*/
template('archive-add',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,item=$data.item,$out='';$out+='<div class="col-xs-12 col-sm-11 col-md-9 col-lg-7"> <div class="form-group"> <label for="information">基础信息：</label> </div> <div class="form-group"> <label for="pany"><span class="require"></span>公司名称：</label> <input type="text" class="form-control" id="pany" maxlength="50" placeholder="" value="';
$out+=$escape(item &&item.name);
$out+='"> </div> ';
if(item&&item.no){
$out+=' <div class="form-group"> <label for="idno"><span class="require"></span>编号：</label> <input type="text" class="form-control" value="';
$out+=$escape(item&&item.no);
$out+='" disabled="disabled" id="idno"> </div> ';
}
$out+='            <div class="row form-group"> <div class="col-lg-6"> <label for="pany-man">联系人：</label> <input type="text" class="form-control " id="pany-man" maxlength="20" placeholder="" value="';
$out+=$escape(item &&item.contact);
$out+='"> </div> <div class=" col-lg-6"> <label for="pany-tel">联系电话：</label> <input type="text" class="form-control " id="pany-tel" maxlength="20" placeholder="" value="';
$out+=$escape(item &&item.phone);
$out+='"> </div> </div> <div class="form-group"> <label for="pany-address">地址：</label> <input type="text" class="form-control" id="pany-address" maxlength="100" placeholder="" value="';
$out+=$escape(item &&item.address);
$out+='"> </div> <div class="row form-group"> <div class=" col-lg-6"> <label for="CL-type"><span class="require"></span>供应商分类：</label> <select id="CL-type" class="form-control" > </select> </div> <div class=" col-lg-6"> <label for="CL-type"><span class="require"></span>供应商等级：</label> <select id="grade" class="form-control" > </select> </div> </div> <div class="form-group"> <label for="FW-type">供应商服务内容：</label> <select id="FW-type" class="form-control" > </select> </div> </div> <div class="col-xs-12 col-sm-11 col-md-9 col-lg-7 "> <div class="form-group"> <label for="YY-information">营业执照信息：</label> </div> <div class="row form-group"> <div class="col-lg-6"> <label for="YY-num"><span class="require"></span>营业执照号：</label> <input type="text" class="form-control " id="YY-num" maxlength="100" placeholder="" value="';
$out+=$escape(item &&item.licenseNo);
$out+='"> </div> <div class=" col-lg-6"> <label for="YY-man"><span class="require"></span>法人信息：</label> <input type="text" class="form-control " id="YY-man" maxlength="20" placeholder="" value="';
$out+=$escape(item &&item.legalName);
$out+='"> </div> </div> <div class="form-group"> <label for="bank"><span class="require"></span>开户银行：</label> <input type="text" class="form-control" id="bank" maxlength="50" placeholder="" value="';
$out+=$escape(item &&item.bankName);
$out+='"> </div> <div class="form-group"> <label for="bank-num"><span class="require"></span>银行账号：</label> <input type="text" class="form-control" id="bank-num" maxlength="20" placeholder="" value="';
$out+=$escape(item &&item.bankAccount);
$out+='"> </div> <div class="row form-group"> <div class="col-lg-6"> <label for="pay-text-man">一般纳税人类别：</label> <select id="pay-text-man" class="form-control" > <option value ="1" ';
$out+=$escape(item && item.taxpayerType === 1 && 'selected');
$out+='>一般纳税人</option> <option value="2" ';
$out+=$escape(item && item.taxpayerType === 2 && 'selected');
$out+='>小规模纳税人</option> </select> </div> <div class=" col-lg-6 " id="rate"> <label for="pay-text">税率:</label> ';
if(item && item.rate !== null){
$out+=' <input type="text" class="form-control" id="pay-text" maxlength="10" placeholder="" value="';
$out+=$escape(item && item.rate);
$out+='"> ';
}else{
$out+=' <input type="text" class="form-control" id="pay-text" maxlength="10" placeholder="" value="" > ';
}
$out+=' <label class="lblrate">%</label> </div> </div> <div class="form-group" id="upload"> <div> <label for="upload">相关证件照</label> </div> <div class="cent licenseUrl"> <div class="js-uploader1"></div> <p class="help-block">营业执照正面</p> </div> <div class="cent cardAUrl"> <div class="js-uploader2"></div> <p class="help-block">法人身份证正面</p> </div> <div class="cent cardBUrl"> <div class="js-uploader3"></div> <p class="help-block">法人身份证反面</p> </div> <p class="help-block">注：仅限jpg、png、bmp格式，大小不超过10M</p> </div> <div class="form-group clearfix"> <label for="upload-file">相关附件</label> <div class="js-uploader-file"></div> <p class="help-block">注：仅限pdf、word、excel、txt、ppt格式，大小不超过50M</p> </div> </div> <div class="col-xs-12 col-sm-11 col-md-9 col-lg-7 footer"> <button class="btn btn-primary js-save">保存</button> <button class="btn btn-default js-cancel">取消</button> </div>';
return new String($out);
});