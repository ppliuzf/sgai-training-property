/*TMODJS:{"version":1,"md5":"8fbebe4a3040f1b56043c062df7318dd"}*/
template('order/my-reservation',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <tr style="vertical-align:middle"> <td class="text-center">';
$out+=$escape(item.num);
$out+='</td> <td class=\'cli my-reservation\' data-id=';
$out+=$escape(item.riCarId);
$out+='> <a href="details-of-reservation.html?id=';
$out+=$escape(item.id);
$out+='&status=';
$out+=$escape(item.riAuditStatus);
$out+='">';
$out+=$escape(item.riUseStart);
$out+=' </a> </td> <td>';
$out+=$escape(item.createdDt);
$out+='</td> <td>';
$out+=$escape(item.ciNumber);
$out+='</td> <td>';
$out+=$escape(item.ciBrand);
$out+='</td> <td>';
$out+=$escape(item.ciModel);
$out+='</td> <td>';
$out+=$escape(item.ciColor);
$out+='</td> <td>';
$out+=$escape(item.ciBoxTypeName);
$out+='</td> <td>';
$out+=$escape(item.ciDisplacement);
$out+='</td> <td>';
$out+=$escape(item.ciLoadNumber);
$out+='</td> ';
if(item.riAuditStatus === 0){
$out+=' <td>未提交</td> ';
}else if(item.riAuditStatus === 1){
$out+=' <td>已提交</td> ';
}else if(item.riAuditStatus === 2){
$out+=' <td>已通过</td> ';
}else if(item.riAuditStatus === 3){
$out+=' <td>已拒绝</td> ';
}else if(item.riAuditStatus === 4){
$out+=' <td>已归还</td> ';
}else if(item.riAuditStatus === 5){
$out+=' <td>归还中</td> ';
}else{
$out+=' <td>已取消</td> ';
}
$out+=' ';
if(item.riAuditStatus == 1){
$out+=' <td class="text-center"> <a href=\'javascript:;\' class="js-edit1" data-id=';
$out+=$escape(item.id);
$out+='>取消预约</a> </td> ';
}else if(item.riAuditStatus == 2){
$out+=' <td class="text-center"> <a href=\'javascript:;\' class="js-edit2" data-id=';
$out+=$escape(item.id);
$out+=' data-ricarid=';
$out+=$escape(item.riCarId);
$out+=' style=\'font-size:12px;\'>归还车辆</a> </td> ';
}else{
$out+=' <td class="text-center">-</td> ';
}
$out+=' </tr> ';
});
return new String($out);
});