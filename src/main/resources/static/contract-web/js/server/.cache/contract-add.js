/*TMODJS:{"version":27,"md5":"36069bb0399a94e7606ab7300ea7383b"}*/
template('contract-add',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,name=$data.name,amount=$data.amount,status=$data.status,ownerName=$data.ownerName,secondPartyName=$data.secondPartyName,singDate=$data.singDate,effectiveDate=$data.effectiveDate,creater=$data.creater,phone=$data.phone,description=$data.description,$out='';$out+='<div class="form-group clearfix"> <div class="col-xs-12"> <label for="name"><span class="require"></span>合同名称</label> <input type="text" id="name" class="form-control" data-max="50" maxlength="50" value="';
$out+=$escape(name);
$out+='"> </div> </div> <div class="form-group clearfix"> <div class="col-xs-12"> <label for="type">合同类型</label> <select class="form-control" id="type"> </select> </div> </div> <div class="form-group clearfix"> <div class="col-xs-12 spe"> <label for="total"><span class="require"></span>合同总额</label> <input type="number" id="total" class="form-control" value="';
$out+=$escape(amount);
$out+='"> </div> </div> <div class="form-group clearfix"> <div class="col-xs-12"> <label for="status"><span class="require"></span>合同状态</label> <select class="form-control" id="status"> <option value="1" ';
$out+=$escape(status === 1 && 'selected');
$out+='>未签约</option> <option value="2" ';
$out+=$escape(status === 2 && 'selected');
$out+='>已签约</option> </select> </div> </div> <div class="form-group clearfix"> <div class="col-xs-12"> <label for="Suppliers"></span>供应商</label> <div id="Suppliers"> <div class="js-suppliers"></div> <i class="serch"></i> </div> </div> </div> <div class="form-group clearfix"> <div class="col-xs-12"> <label for="aName">甲方名称</label> <input type="text" id="aName" class="form-control" data-max="50" maxlength="50" value="';
$out+=$escape(ownerName);
$out+='"> </div> </div> <div class="form-group clearfix"> <div class="col-xs-12"> <label for="bName">乙方名称</label> <input type="text" id="bName" class="form-control" data-max="50" maxlength="50" value="';
$out+=$escape(secondPartyName);
$out+='"> </div> </div> <div class="form-group col-xs-12 col-sm-6 col-md-6 has-feedback"> <label for="signDate">签订日期</label> <div class="input-group"> <input type="text" name="" id="signDate" class="form-control" readonly value="';
$out+=$escape($helpers. dateFormat(singDate , 'yyyy-MM-dd'));
$out+='" data-date-format="yyyy-mm-dd"> <span class="input-group-addon js-date-clean"> <span class="glyphicon glyphicon-remove"></span> </span> </div> </div> <div class="form-group col-xs-12 col-sm-6 col-md-6 has-feedback"> <label for="sureDate">生效日期</label> <div class="input-group"> <input type="text" name="" id="sureDate" class="form-control" readonly value="';
$out+=$escape($helpers. dateFormat(effectiveDate , 'yyyy-MM-dd'));
$out+='" data-date-format="yyyy-mm-dd"> <span class="input-group-addon js-date-clean"> <span class="glyphicon glyphicon-remove"></span> </span> </div> </div> <div class="form-group clearfix"> <div class="col-xs-12 col-sm-6 col-md-6"> <label for="addName">创建人</label> <input type="text" id="addName" class="form-control" data-max="20" maxlength="20" value="';
$out+=$escape(creater);
$out+='"> </div> <div class="col-xs-12 col-sm-6 col-md-6"> <label for="tel">手机号</label> <input type="text" id="tel" class="form-control" data-max="11" maxlength="11" value="';
$out+=$escape(phone);
$out+='"> </div> </div> <div class="form-group clearfix desc-box"> <div class="col-xs-12"> <label for="desc">描述</label> <textarea id="desc" rows="6" class="form-control" maxlength="200">';
$out+=$escape(description);
$out+='</textarea> <div class="text-right js-count" style="right: 30px;">0/200</div> </div> </div> <div class="form-group clearfix"> <div class="col-xs-12"> <label>上传图片：</label> <div class="js-uploader-image"></div> </div> </div> <div class="form-group clearfix"> <div class="col-xs-12"> <div class="js-uploader-copy"></div> </div> </div> <div class="form-group clearfix"> <div class="col-xs-12"> <div class="js-uploader-agree"></div> </div> </div> <div class="form-group clearfix"> <div class="col-xs-12"> <div class="js-uploader-file"></div> </div> </div> <div class="form-group"> <div class="col-xs-12"> <button class="btn btn-primary js-save">保存</button> <button class="btn btn-default js-cancel">取消</button> </div> </div> <style> #Suppliers{ min-height: 32px; box-shadow: none; border: 1px solid #d9d9d9; color: #666666; padding: 0 25px 0 15px; border-radius: 4px; position: relative; } .serch { background: url(\'images/search.png\') no-repeat; position: absolute; width: 30px; top: 7px; bottom: 0; right: -8px; cursor: pointer; } </style>';
return new String($out);
});