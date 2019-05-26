/*TMODJS:{"version":1,"md5":"cdb3fea0e73a2f083d2ccfc72767a4ef"}*/
template('order/already-passed',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,riAuditStatus=$data.riAuditStatus,$escape=$utils.$escape,ciNumber=$data.ciNumber,ciDepartment=$data.ciDepartment,ciBuyDate=$data.ciBuyDate,ciEngineNumber=$data.ciEngineNumber,ciBrand=$data.ciBrand,ciModel=$data.ciModel,ciColor=$data.ciColor,ciDisplacement=$data.ciDisplacement,ciBoxTypeName=$data.ciBoxTypeName,ciLoadNumber=$data.ciLoadNumber,ciOwnerName=$data.ciOwnerName,ciOwnerPhone=$data.ciOwnerPhone,riUserName=$data.riUserName,riUseStart=$data.riUseStart,createdDtLong=$data.createdDtLong,riUsePurpose=$data.riUsePurpose,$out='';$out+='<div class="row header"> <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8"> <h4 style=\'margin:0px;\'> ';
if(riAuditStatus === 0){
$out+=' <span>无需审核-未提交</span> ';
}else if(riAuditStatus === 1){
$out+=' <span>待审核-已提交</span> ';
}else if(riAuditStatus === 2){
$out+=' <span>审核结果-已通过</span> ';
}else if(riAuditStatus === 3){
$out+=' <span>审核结果-已拒绝</span> ';
}else{
$out+=' <span>无需审核-已归还</span> ';
}
$out+=' </h4> </div> <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right"> <button class="btn btn-default" id=\'fh\' style=\'margin-top:12px;\'>返回</button> </div> </div> <h4 style=\'font-size:14px;\' class=\'blod\'>车辆信息</h4> <div class="infos-detail"> <table> <tbody> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车牌号码：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciNumber);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">所属部门：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciDepartment);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">购置日期：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape($helpers. dateFormat(ciBuyDate ,  'yyyy-MM-dd' ));
$out+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">发动机号：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciEngineNumber);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车辆品牌：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciBrand);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车辆型号：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciModel);
$out+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车辆颜色：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciColor);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车辆排量：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciDisplacement);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">变速箱类型：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciBoxTypeName);
$out+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">荷载人数：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciLoadNumber);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车主(司机)姓名：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciOwnerName);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车主(司机)手机号：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciOwnerPhone);
$out+='</td> </tr> </tbody> </table> </div> <hr> <h4 style="font-size:14px;" class=\'blod\'>预约信息</h4> <div class="infos-detail"> <table> <tbody> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">申请人：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(riUserName);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">所属部门：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciDepartment);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">预约时间：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(riUseStart);
$out+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">申请时间：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(createdDtLong);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center"></td> <td class="col-lg-3 col-md-3 col-sm-3 value"></td> <td class="col-lg-1 col-md-1 col-sm-1 text-center"></td> <td class="col-lg-3 col-md-3 col-sm-3 value"></td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">申请理由：</td> <td class="col-lg-3 col-md-3 col-sm-3 value" colspan=\'5\'>';
$out+=$escape(riUsePurpose);
$out+='</td> </tr> </tbody> </table> </div> ';
return new String($out);
});