/*TMODJS:{"version":49,"md5":"5376d5a68031a218008a1e52b42b9ab5"}*/
template('check/check-list',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
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
$out+=$escape(item.tiName);
$out+='</td> <td><div class="text-ellipsis" style="width:400px;">';
$out+=$escape(item.tiStandard ? item.tiStandard : '无');
$out+='</div></td> <td>';
$out+=$escape(item.pcName);
$out+='</td> <td>';
$out+=$escape(item.tiIsInput === 0 ? '数值' : '单选');
$out+='</td> <td>';
$out+=$escape(item.createEiName);
$out+='</td> <td>';
$out+=$escape($helpers. dateFormat(item.createTime , 'yyyy-MM-dd hh:mm'));
$out+='</td> <td class="text-center act"> <div class="btn-group more"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">更多 <span class="caret"></span></button> <ul class="dropdown-menu"> <li> <span><a class="underline" data-id="';
$out+=$escape(item.tiId);
$out+='" href="pages/check/detail-check.html?id=';
$out+=$escape(item.tiId);
$out+='">查看</a></span> </li> <li> <span><a class="js-del underline" href="javascript:;" data-id="';
$out+=$escape(item.tiId);
$out+='">删除</a></span> </li> </ul> </div> </td> </tr> ';
});
$out+=' ';
return new String($out);
});