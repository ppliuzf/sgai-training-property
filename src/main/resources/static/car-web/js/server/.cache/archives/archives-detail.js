/*TMODJS:{"version":1,"md5":"73423e3c9224a032f42acd47663c9e82"}*/
template('archives/archives-detail',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,item=$data.item,$out='';$out+='<h4 style=\'font-size:14px;\'>基础信息:</h4> <div class="infos-detail"> <table> <tbody> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车牌号码:</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.ciNumber);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">所属部门:</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.ciDepartment);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">购置日期:</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape($helpers. dateFormat(item.ciBuyDate ,  'yyyy-MM-dd'));
$out+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车辆品牌:</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.ciBrand);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车辆型号:</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.ciModel);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车辆颜色:</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.ciColor);
$out+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车辆排量:</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.ciDisplacement);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">变速箱类型:</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.ciBoxTypeName);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">荷载人数:</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.ciLoadNumber);
$out+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车主(司机)姓名:</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.ciOwnerName);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">车主(司机)手机号:</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.ciOwnerPhone);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">行驶里程数:</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.journeyAmount);
$out+='</td> </tr> </tbody> </table> </div>';
return new String($out);
});