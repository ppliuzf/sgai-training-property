/*TMODJS:{"version":1,"md5":"f96d1326876d398355938774a85c9cc1"}*/
template('order/new-reservation',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,ciNumber=$data.ciNumber,ciDepartment=$data.ciDepartment,ciBoxTypeName=$data.ciBoxTypeName,ciLoadNumber=$data.ciLoadNumber,ciBrand=$data.ciBrand,ciModel=$data.ciModel,ciColor=$data.ciColor,ciDisplacement=$data.ciDisplacement,remarks=$data.remarks,createdDt=$data.createdDt,$out='';$out+='<h4>基础信息</h4> <div class="infos-detail"> <table> <tbody> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车牌号码：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciNumber);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">所属部门：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciDepartment);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">变速箱类型：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciBoxTypeName);
$out+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">荷载人数：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciLoadNumber);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车辆品牌：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciBrand);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车辆型号：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciModel);
$out+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车辆颜色：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciColor);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车辆排量：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ciDisplacement);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">预约日期：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(remarks);
$out+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">预约时间：</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(createdDt);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center"></td> <td class="col-lg-3 col-md-3 col-sm-3 value"></td> <td class="col-lg-1 col-md-1 col-sm-1 text-center"></td> <td class="col-lg-3 col-md-3 col-sm-3 value"></td> </tr> </tbody> </table> </div>';
return new String($out);
});