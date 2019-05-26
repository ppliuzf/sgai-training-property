/*TMODJS:{"version":1,"md5":"bc24b9c3bd1fa71756ead0e9c75d5d94"}*/
template('order/my-review',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$out+=' ';
$each(items,function(item,$index){
$out+=' <tr> <td class=\'text-center\'>';
$out+=$escape(item.num);
$out+='</td> <td class=\'pro\'> <a href="already-passed.html?id=';
$out+=$escape(item.id + '&riAuditStatus='+ item.riAuditStatus +'&createdDtLong='+ item.createdDtLong);
$out+='"> ';
$out+=$escape(item.ciNumber);
$out+='</a> </td> <td>';
$out+=$escape(item.ciDepartment);
$out+='</td> <td>';
$out+=$escape(item.riUseStart);
$out+='</td> <td>';
$out+=$escape(item.riUserName);
$out+='</td> <td>';
$out+=$escape(item.updatedDt);
$out+='</td> <td> <div style="overflow:hidden; text-overflow:ellipsis; white-space:nowrap; width:250px;">';
$out+=$escape(item.riUsePurpose);
$out+=' </div> </td> <td> ';
if(item.riAuditStatus === 0){
$out+=' <span>未提交</span> ';
}else if(item.riAuditStatus === 1){
$out+=' <span>未审核</span> ';
}else if(item.riAuditStatus === 2){
$out+=' <span>已通过</span> ';
}else if(item.riAuditStatus === 3){
$out+=' <span>已拒绝</span> ';
}else if(item.riAuditStatus === 5){
$out+=' <span>归还中</span> ';
}else{
$out+=' <span>已归还</span> ';
}
$out+=' </td> <td class=\'text-center\'> ';
if(item.riAuditStatus == 1){
$out+=' <a href=\'javascript:;\' class="js-edit1" data-id=';
$out+=$escape(item.id);
$out+='-';
$out+=$escape(item.riAuditStatus);
$out+='-';
$out+=$escape(item.riAuditName1);
$out+='-';
$out+=$escape(item.riAuditName);
$out+='>通过</a> <a href="javascript:;" class="js-edit2" data-id=';
$out+=$escape(item.id);
$out+='-';
$out+=$escape(item.riAuditStatus);
$out+='-';
$out+=$escape(item.riAuditName1);
$out+='-';
$out+=$escape(item.riAuditName);
$out+='>拒绝</a> ';
}else{
$out+=' <div>-</div> ';
}
$out+=' </td> </tr> ';
});
return new String($out);
});