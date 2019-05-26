/*TMODJS:{"version":54,"md5":"851deba5c98411161a2f1107962bb962"}*/
template('scheme/scheme-list',function($data,$filename
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
$out+=$escape(item.name);
$out+='</td> <td><div class="text-ellipsis" style="max-width: 400px">';
$out+=$escape(item.description);
$out+='</div></td> <td>';
$out+=$escape(item.professionalCategory);
$out+='</td> <td>';
$out+=$escape(item.operator);
$out+='</td> <td>';
$out+=$escape($helpers. dateFormat(item.updateTime , 'yyyy-MM-dd hh:mm'));
$out+='</td> <td class="text-center act"> <div class="btn-group more"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">更多<span class="caret"></span></button> <ul class="dropdown-menu"> <li> <span><a class="underline" data-id="';
$out+=$escape(item.id);
$out+='" href="pages/scheme/set-scheme.html?id=';
$out+=$escape(item.id);
$out+='">配置方案</a></span> </li> <li> <span><a class="js-del underline" href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='">删除</a></span> </li> </ul> </div> </td> </tr> ';
});
$out+=' ';
return new String($out);
});