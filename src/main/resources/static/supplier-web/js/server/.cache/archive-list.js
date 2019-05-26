/*TMODJS:{"version":1,"md5":"ba92397e72a29a50ca2b88fc54114c4d"}*/
template('archive-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <tr> <td class="text-center"> <div class="cont dot " title=""><input type="checkbox" name="choose" class="js-list-select-single" id="';
$out+=$escape(item.id);
$out+='"></div> </td> ';
if(item.index<10){
$out+=' <td class="text-center "><div class="cont dot" title="0';
$out+=$escape(item.index);
$out+='">0';
$out+=$escape(item.index);
$out+='</div></td> ';
}else{
$out+=' <td class="text-center "><div class="cont dot" title="';
$out+=$escape(item.index);
$out+='">';
$out+=$escape(item.index);
$out+='</div></td> ';
}
$out+=' <td> <div class="cont dot" title="';
$out+=$escape(item.no);
$out+='"> <a href="./archive-detail.html?id=';
$out+=$escape(item.id);
$out+='">';
$out+=$escape(item.no);
$out+='</a></div></td> <td> <div class="cont dot HH" title="';
$out+=$escape(item.name);
$out+='">';
$out+=$escape(item.name);
$out+='</div></td> <td> <div class="cont dot" title="';
$out+=$escape(item.typeName);
$out+='">';
$out+=$escape(item.typeName);
$out+='</div></td> <td> <div class="cont dot" title="';
$out+=$escape(item.contentName);
$out+='">';
$out+=$escape(item.contentName);
$out+='</div></td> <!--<td> <div class="cont dot" title="';
$out+=$escape(item.levelName);
$out+='">';
$out+=$escape(item.levelName);
$out+='</div></td>--> ';
if(item.levelId==='1'){
$out+=' <td> <div class="cont dot" title="';
$out+=$escape(item.levelName);
$out+='">未评级</div></td> ';
}else{
$out+=' <td> <div class="cont dot" title="';
$out+=$escape(item.levelName);
$out+='">';
$out+=$escape(item.levelName);
$out+='</div></td> ';
}
$out+=' <td> <div class="cont dot" title="';
$out+=$escape(item.contact);
$out+='">';
$out+=$escape(item.contact);
$out+='</div></td> <td> <div class="cont dot" title="';
$out+=$escape(item.phone);
$out+='">';
$out+=$escape(item.phone);
$out+='</div></td> <td> <div class="cont dot HH" title="';
$out+=$escape(item.address);
$out+='">';
$out+=$escape(item.address);
$out+='</div></td> <td min-width="200">  <!--<input type="checkbox" name="status" data-id="';
$out+=$escape(item.id);
$out+='" ';
$out+=$escape(item.isEnabled === 1 && 'checked');
$out+='>-->  <div class="switch" data-on-="启用" data-off="禁用"> <div class="switch-inner"> <input type="checkbox" type="checkbox" name="status" data-id="';
$out+=$escape(item.id);
$out+='" ';
$out+=$escape(item.isEnabled === 1 && 'checked');
$out+=' style="width:140px; margin-left:-48px;"> </div> </div> </td>     <!--<li><a href="./archive-compile.html?id=';
$out+=$escape(item.id);
$out+='" class="underline js-edit" data-id="">编辑</a></li>--> <!--<li><a class="underline js-del" data-id="';
$out+=$escape(item.id);
$out+='">删除</a></li>-->    <td class="text-center"> <a href="./archive-compile.html?id=';
$out+=$escape(item.id);
$out+='" class="underline js-edit" data-id="">编辑</a> <a href="#" class="underline js-del" data-id="';
$out+=$escape(item.id);
$out+='">删除</a> </td> </tr> ';
});
$out+=' <style> td div{ display: -webkit-box; overflow: hidden; white-space: normal!important; text-overflow: ellipsis; word-wrap: break-word; -webkit-line-clamp: 3; -webkit-box-orient: vertical; } </style>';
return new String($out);
});