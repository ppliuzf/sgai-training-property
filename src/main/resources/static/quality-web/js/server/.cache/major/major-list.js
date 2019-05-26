/*TMODJS:{"version":52,"md5":"66612aa6f6f27f8e505172b4e8afa78d"}*/
template('major/major-list',function($data,$filename
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
$out+=' </td> <td> ';
if(item.pcIcon){
$out+=' <img src="';
$out+=$escape(item.pcIcon);
$out+='" width="32" height="32"> ';
}else{
$out+=' <img src="../../images/default_image.png" width="32" height="32"> ';
}
$out+=' </td> <td>';
$out+=$escape(item.pcName);
$out+='</td> <td>';
$out+=$escape(item.asType);
$out+='</td> <td> <div class="text-ellipsis" style="width: 600px">';
$out+=$escape(item.pcDesc);
$out+='</div> </td> <td class="text-center act"> <div class="btn-group more"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">更多 <span class="caret"></span></button> <ul class="dropdown-menu"> <li> <span><a class="js-edit" data-id="';
$out+=$escape(item.pcId);
$out+='" href="pages/major/edit-major.html?id=';
$out+=$escape(item.pcId);
$out+='">编辑</a></span> </li> <li> ';
if(item.isDefault){
$out+=' <span><a class="disabled" data-id="';
$out+=$escape(item.pcId);
$out+='">删除</a></span> ';
}else{
$out+=' <span><a class="js-del underline" href="javascript:;" data-id="';
$out+=$escape(item.pcId);
$out+='">删除</a></span> ';
}
$out+=' </li> </ul> </div> </td> </tr> ';
});
return new String($out);
});