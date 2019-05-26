/*TMODJS:{"version":113,"md5":"cf927bda65090f8f9c245cfe0acf4a9a"}*/
template('scheme/det-group-list',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,ditem=$data.ditem,$out='';$each(items,function(item,$index){
$out+=' <table class="table table-hover text-center p-item d';
$out+=$escape(item.id);
$out+=' js-table" data-id="';
$out+=$escape(item.id);
$out+='"> <thead> <tr> <th class="average header">';
$out+=$escape(item.name);
$out+='</th> <th class="average"></th> <th class="average"></th> <th class="average"></th> <th class="average text-center" dd="';
$out+=$escape(item.index);
$out+='" d="';
$out+=$escape(item.nofz);
$out+='"> ';
if(item.name !== '未分组' && item.index !== item.nofz){
$out+='  <span><a class="js-up underline" href="javascripg:;" data-id="d';
$out+=$escape(item.id);
$out+='">上移</a></span> <span><a class="js-down underline js-movedown" href="javascripg:;" data-id="d';
$out+=$escape(item.id);
$out+='">下移</a></span> <span><a class="js-delete-group underline" href="javascripg:;" data-id="';
$out+=$escape(item.id);
$out+='">删除</a></span> ';
}else if(item.name !== '未分组' && item.index === item.nofz){
$out+='  <span><a class="js-up underline" href="javascripg:;" data-id="d';
$out+=$escape(item.id);
$out+='">上移</a></span> <span><a class="underline btn disabled" href="javascripg:;" data-id="d';
$out+=$escape(item.id);
$out+='">下移</a></span> <span><a class="js-delete-group underline" href="javascripg:;" data-id="';
$out+=$escape(item.id);
$out+='">删除</a></span> ';
}else{
$out+=' ';
}
$out+=' </th> </tr> </thead> ';
if(item.list.length > 0){
$out+=' <tbody class="s-sort"> ';
$each(item.list,function(ditem,$index){
$out+=' <tr class="item s-item s';
$out+=$escape(ditem.id);
$out+='" data-id="';
$out+=$escape(ditem.id);
$out+='"> <td class="average">';
$out+=$escape(ditem.name);
$out+='</td> <td class="text-ellipsis text-center" style="width: 300px"> <div class="text-ellipsis text-center" style="width: 300px">';
$out+=$escape(ditem.standard);
$out+='</div> </td> <td class="average">';
$out+=$escape(ditem.answerTypeDesc);
$out+='</td> <td class="average"> <span><a class="js-s-up underline" href="javascripg:;" data-id="s';
$out+=$escape(ditem.id);
$out+='">上移</a></span> <span><a class="js-s-down underline js-movedown" href="javascripg:;" data-id="s';
$out+=$escape(ditem.id);
$out+='">下移</a></span> <span><a class="js-move-to underline" href="javascripg:;" data-id="';
$out+=$escape(ditem.id);
$out+='">移动至</a></span> </td> <td class="average"></td> </tr> ';
});
$out+=' </tbody> ';
}
$out+=' </table> ';
});
return new String($out);
});