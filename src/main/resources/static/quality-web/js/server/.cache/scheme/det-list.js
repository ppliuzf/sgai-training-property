/*TMODJS:{"version":53,"md5":"e1d2e3c544ded4b650f30ce15415c4ef"}*/
template('scheme/det-list',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <tr data-id="';
$out+=$escape(item.id);
$out+='"> <td class="text-center"> ';
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
$out+='</td> <td> <div class="text-ellipsis text-center" style="max-width:300px;"> ';
$out+=$escape(item.standard ? item.standard : '无');
$out+=' </div> </td> <td>';
$out+=$escape(item.answerTypeDesc);
$out+='</td> <td>';
$out+=$escape(item.createName);
$out+='</td> <td> ';
if(item.updateTime){
$out+=' ';
$out+=$escape($helpers. dateFormat(item.updateTime , 'yyyy-MM-dd'));
$out+=' ';
}else{
$out+=' -- ';
}
$out+=' </td> <td class="text-center act"> <div class="btn-group more"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">更多 <span class="caret"></span></button> <ul class="dropdown-menu"> <li> <span><a class="js-add-item underline" data-id="';
$out+=$escape(item.id);
$out+='">添加</a></span> </li> </ul> </div> </td> </tr> ';
});
return new String($out);
});