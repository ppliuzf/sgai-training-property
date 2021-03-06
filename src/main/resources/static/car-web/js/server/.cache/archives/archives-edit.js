/*TMODJS:{"version":1,"md5":"3e3f3e5a7d1beb62916abd90e0491e3d"}*/
template('archives/archives-edit',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,item=$data.item,$out='';$out+='<div class="form-group clearfix"> <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> <label for="ciNumber"><span class="require"></span>车牌号码</label> <input type="text" id="ciNumber" class="form-control" value="';
$out+=$escape(item.ciNumber);
$out+='" name="ciNumber" maxlength="10"> </div> </div> <div class="form-group clearfix" id="partment-box"> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">   <!--<select id="ciDepartment" class="form-control" data-id="';
$out+=$escape(item.ciDepartmentId);
$out+='"></select>--> <label for="ciDepartment"><span class="require"></span>所属部门</label> <div class="js-dept-selector" id="ciDepartment"></div> </div> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="ciNum"><span class="require"></span>行驶里程数</label> <input type="text" id="ciNum" class="form-control" maxlength="7" name="ciNum" value="';
$out+=$escape(item.journeyAmount);
$out+='"> </div> </div> <div class="form-group clearfix"> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="ciBuyDate">购置日期</label> <input type="text" id="ciBuyDate" class="form-control" value="';
$out+=$escape($helpers. dateFormat(item.ciBuyDate ,  'yyyy-MM-dd'));
$out+='" /> </div> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="e-num">发动机号码</label> <input type="text" id="e-num" class="form-control" value="';
$out+=$escape(item.ciEngineNumber);
$out+='" name="ciEngineNumber" maxlength="50"/> </div> </div> <div class="form-group clearfix"> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="ciBrand">车辆品牌</label> <input type="text" id="ciBrand" class="form-control" value="';
$out+=$escape(item.ciBrand);
$out+='" maxlength="20" name="ciBrand"> </div> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="ciModel">车辆型号</label> <input type="text" id="ciModel" class="form-control" value="';
$out+=$escape(item.ciModel);
$out+='" maxlength="20" name="ciModel"> </div> </div> <div class="form-group clearfix"> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="ciColor">车辆颜色</label> <input type="text" id="ciColor" class="form-control" value="';
$out+=$escape(item.ciColor);
$out+='" name="ciColor" maxlength="10"> </div> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="ciDisplacement">车辆排量</label> <input type="text" id="ciDisplacement" class="form-control" value="';
$out+=$escape(item.ciDisplacement);
$out+='" name="ciDisplacement" maxlength="10"> </div> </div> <div class="form-group clearfix"> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="ciBoxType">变速箱类型</label> <select class="form-control" id="ciBoxType" name="ciBoxTypeName" data-id="';
$out+=$escape(item.ciBoxTypeId);
$out+='"> </select> </div> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" id="ciLoad-box"> <label for="ciLoadNumber">荷载人数</label> <input type="text" id="ciLoadNumber" class="form-control" value="';
$out+=$escape(item.ciLoadNumber);
$out+='" name="ciLoadNumber" maxlength="4"> </div> </div> <div class="form-group clearfix"> <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> <input type="text" id="ciTypeId" hidden value="2" name="ciTypeId"> <label for="ciTypeName">车辆类型</label> <select class="form-control" id="ciTypeName" name="ciTypeName" data-id="';
$out+=$escape(item.ciTypeId);
$out+='"> </select> </div> </div> <div class="form-group clearfix" id="phone-box"> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="ciOwnerName">车主(司机)姓名</label> <input type="text" id="ciOwnerName" class="form-control" value="';
$out+=$escape(item.ciOwnerName);
$out+='" name="ciOwnerName" maxlength="10"> </div> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" > <label for="ciOwnerPhone">车主(司机)手机号</label> <input type="text" id="ciOwnerPhone" class="form-control" value="';
$out+=$escape(item.ciOwnerPhone);
$out+='" name="ciOwnerPhone" maxlength="11"> </div> </div> <div class="form-group clearfix"> <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> <label for="ciRemark">备注信息</label> <div class="textarea"> <textarea id="ciRemark" rows="4" class="form-control js-textarea" name="ciRemark" maxlength="200">';
$out+=$escape(item.ciRemark);
$out+='</textarea> <div class="border"></div> <div class="number js-count text-right"></div> </div> </div> </div> <div class="form-group clearfix"> <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> <label for="ciImage">上传图片</label> <div class="js-uploader" id="ciImage"></div> </div> </div> <div class="btn-box"> <a class="btn btn-primary js-save">保存</a> <a class="btn btn-default js-cancel">取消</a> </div>';
return new String($out);
});