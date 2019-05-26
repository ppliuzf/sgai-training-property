/*TMODJS:{"version":1,"md5":"0843296162a79c7c2e279f5584424ecf"}*/
template('level-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,idx=$data.idx,$escape=$utils.$escape,$out='';$each(items,function(item,idx){
$out+=' <tr> <td class="text-center">';
$out+=$escape($helpers. fillZero(idx ));
$out+='</td> <td>';
$out+=$escape(item.levelName);
$out+='</td> <td>';
$out+=$escape(item.levelDesc);
$out+='</td> <!-- <td class="text-center act"> <div class="btn-group more"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">更多</button> <ul class="dropdown-menu"> <li><a href="./level-add.html?id=';
$out+=$escape(item.clId);
$out+='">编辑</a></li> <li> ';
if(item.levelType){
$out+=' <a href="#" class="js-del" data-id="';
$out+=$escape(item.clId);
$out+='">删除</a> ';
}
$out+=' </li> </ul> </div> </td> --> <td class="text-center"> <a href="./level-add.html?id=';
$out+=$escape(item.clId);
$out+='">编辑</a> ';
if(item.levelType){
$out+=' <a href="#" class="js-del" data-id="';
$out+=$escape(item.clId);
$out+='">删除</a> ';
}
$out+=' </td> </tr> ';
});
$out+=' ';
return new String($out);
});