/*TMODJS:{"version":37,"md5":"faff43303d85bbb86f0b9349eb4efb42"}*/
template('result/detail-list',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,itemIndex=$data.itemIndex,$escape=$utils.$escape,$out='';$each(items,function(item,itemIndex){
$out+=' <tr> <td class="text-center"> ';
if(item.num<10){
$out+=' 0';
$out+=$escape(item.num);
$out+=' ';
}else{
$out+=' ';
$out+=$escape(item.num);
$out+=' ';
}
$out+=' </td> <td>';
$out+=$escape(item.name);
$out+='</td> <td>';
$out+=$escape(item.groupName);
$out+='</td> <td>';
$out+=$escape(item.answerType);
$out+='</td> <td>';
$out+=$escape(item.checkName);
$out+='</td> <td>';
$out+=$escape($helpers. dateFormat(item.checkTime , 'yyyy-MM-dd hh:mm:ss'));
$out+='</td> <td>';
$out+=$escape(item.status);
$out+='</td> <td>';
$out+=$escape(item.result);
$out+='</td>  <!--<div class="text-ellipsis text-center" style="width: 100px">';
$out+=$escape(item.remark);
$out+='</div>-->  </tr> ';
});
$out+=' ';
return new String($out);
});