/*TMODJS:{"version":1,"md5":"596812e37cb304c8be449e539e2c7fe0"}*/
template('order/orders',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,i=$data.i,$index=$data.$index,$escape=$utils.$escape,$out='';$out+='<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style=\'margin:0px; padding:0;\'> ';
$each(items,function(i,$index){
$out+=' <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3"> <div class="panel panel-primary"> <div class="panel-heading text-center" style="width:100%">';
$out+=$escape(i.ciNumber);
$out+='</div> <div class="panel-body"> <p>所属部门：';
$out+=$escape(i.ciDepartment);
$out+='</p> <p>车辆品牌：';
$out+=$escape(i.ciBrand);
$out+='</p> <p>车辆型号：';
$out+=$escape(i.ciModel);
$out+='</p> <p>车辆颜色：';
$out+=$escape(i.ciColor);
$out+='</p> <p>车辆排量：';
$out+=$escape(i.ciDisplacement);
$out+='</p> <p>荷载人数：';
$out+=$escape(i.ciLoadNumber);
$out+='</p> <button type="button" class="btn btn-primary" id=\'order-cl\' data-id=';
$out+=$escape(i.id);
$out+=' style="width:100%">预约</button> </div> </div> </div> ';
});
$out+=' </div>';
return new String($out);
});