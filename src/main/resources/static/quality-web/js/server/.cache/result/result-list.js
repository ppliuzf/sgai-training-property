/*TMODJS:{"version":43,"md5":"e755645dd5b67b5a6636503579d9e87d"}*/
template('result/result-list',function($data,$filename
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
$out+=' </td> <td><a class="underline" data-id="';
$out+=$escape(item.ttId);
$out+='" href="pages/result/result-detail.html?id=';
$out+=$escape(item.ttId);
$out+='">';
$out+=$escape(item.ttName);
$out+='</a></td> <td><div class="text-ellipsis">';
$out+=$escape(item.pcName);
$out+='</div></td> <td>';
$out+=$escape(item.createName);
$out+='</td> <td>';
$out+=$escape($helpers. dateFormat(item.createTime , 'yyyy-MM-dd hh:mm'));
$out+='</td> <td>';
$out+=$escape(item.ttSubmitName);
$out+='</td> <td>';
$out+=$escape($helpers. dateFormat(item.ttSubmitTime , 'yyyy-MM-dd hh:mm'));
$out+='</td> <td class="text-center act">';
$out+=$escape(item.ttStatus);
$out+='</td> </tr> ';
});
$out+=' ';
return new String($out);
});