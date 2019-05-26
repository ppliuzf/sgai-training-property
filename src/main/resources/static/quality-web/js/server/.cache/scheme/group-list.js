/*TMODJS:{"version":5,"md5":"e0b73f119ad6379b3c308af7156cfeef"}*/
template('scheme/group-list',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <table class="table table-bordered table-hover table-striped add-table text-center"> <thead> <tr> <th>';
$out+=$escape(items.name);
$out+='</th> </tr> </thead> <tbody> ';
$each(items,function(item,$index){
$out+=' ';
});
$out+=' </tbody> </table> <tr> <td>';
$out+=$escape(item.name);
$out+='</td> <td><div class="text-ellipsis text-center">';
$out+=$escape(item.description);
$out+='</div></td> <td>';
$out+=$escape(item.professionalCategory);
$out+='</td> <td>';
$out+=$escape(item.operator);
$out+='</td> <td>';
$out+=$escape($helpers. dateFormat(item.updateTime , 'yyyy-MM-dd hh:mm'));
$out+='</td> <td> <span><a class="underline" data-id="';
$out+=$escape(item.id);
$out+='" href="pages/scheme/set-scheme.html?id=';
$out+=$escape(item.id);
$out+='">配置方案</a></span> <span><a class="js-del underline" href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='">删除</a></span> </td> </tr> ';
});
return new String($out);
});