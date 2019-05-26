/*TMODJS:{"version":50,"md5":"8fb4043ffad0007140fdd863d4c3bbb5"}*/
template('scheme/main-scheme',function($data,$filename
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
$out+=' </td> <td> <div class="cont dot" title="';
$out+=$escape(item.name);
$out+='"> <a href="../check/detail-check.html?id=';
$out+=$escape(item.htiId);
$out+='" class="underline js-view" title="';
$out+=$escape(item.name);
$out+='">';
$out+=$escape(item.name);
$out+='</a> </div> </td> <td> <div class="cont dot">';
$out+=$escape(item.groupName);
$out+='</div> </td> <td> <div class="text-ellipsis text-center" style="max-width: 300px">';
$out+=$escape(item.standard ? item.standard : '无');
$out+='</div> </td> <td> <div class="cont dot" >';
$out+=$escape(item.answerTypeDesc);
$out+='</div> </td> <td> <div class="cont dot">';
$out+=$escape(item.createName);
$out+='</div> </td> <td> <div class="cont dot">';
$out+=$escape($helpers. dateFormat(item.createTime ,  'yyyy-MM-dd'));
$out+='</div> </td> <td class="text-center act"> <div class="btn-group more"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">更多 <span class="caret"></span></button> <ul class="dropdown-menu"> <li> <span><a class="js-cancel-relate underline" data-id="';
$out+=$escape(item.id);
$out+='">取消关联</a></span> </li> </ul> </div> </td> </tr> ';
});
return new String($out);
});