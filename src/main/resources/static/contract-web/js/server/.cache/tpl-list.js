/*TMODJS:{"version":24,"md5":"b06606bc01b32b31a3b7f66007a12a4b"}*/
template('tpl-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,idx=$data.idx,$escape=$utils.$escape,$out='';$each(items,function(item,idx){
$out+=' <tr> <td class="text-center">';
$out+=$escape(idx + 1 < 10 ? '0'+(idx + 1) : idx + 1);
$out+='</td> <td>';
$out+=$escape(item.no);
$out+='</td> <td>';
$out+=$escape(item.name);
$out+='</td> <td>';
$out+=$escape(item.typeName);
$out+='</td> <td>';
$out+=$escape(item.createDate);
$out+='</td> <td>';
$out+=$escape(item.uploadBy);
$out+='</td> <td>';
$out+=$escape(item.description);
$out+='</td>       <!--<a class="js-download" data-name="';
$out+=$escape(item.fileName);
$out+='" data-url="';
$out+=$escape(item.url);
$out+='">下载模板</a>-->   <!--<a href="./tpl-add.html?id=';
$out+=$escape(item.id);
$out+='" class="js-edit">编辑</a>-->  <!--';
if(item.isDelete !== 3){
$out+='-->  <!--<a href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='" class="js-del">删除</a>-->  <!--';
}
$out+='-->    <td class="text-center" style="white-space:nowrap"> <a href="javascript:;" class="js-download" data-name="';
$out+=$escape(item.fileName);
$out+='" data-url="';
$out+=$escape(item.url);
$out+='">下载模板</a> <a href="./tpl-add.html?id=';
$out+=$escape(item.id);
$out+='" class="js-edit">编辑</a> ';
if(item.isDelete !== 3){
$out+=' <a href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='" class="js-del">删除</a> ';
}
$out+=' </td> </tr> ';
});
return new String($out);
});