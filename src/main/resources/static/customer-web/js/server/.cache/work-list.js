/*TMODJS:{"version":1,"md5":"d4beab20fdfed2bfc7f9e09757094167"}*/
template('work-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$out+='<table class="table table-bordered table-hover table-striped"> <thead> <tr> <th class="text-center">费用名称</th> <th width="80">单价(元)</th> <th>单位</th> <th>描述</th> <th width="92" class="text-center">操作</th> </tr> </thead> <tbody class="js-list"> ';
$each(items,function(item,$index){
$out+=' <tr data-id="';
$out+=$escape(item.id);
$out+='"> <td>';
$out+=$escape(item.wdCostName);
$out+='</td> <td>';
$out+=$escape(item.wdPrice);
$out+='</td> <td>';
$out+=$escape(item.wdUnit);
$out+='</td> <td>';
$out+=$escape(item.wdDesc);
$out+='</td> <!-- <td class="text-center act"> <div class="btn-group more"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">更多</button> <ul class="dropdown-menu"> <li> <a href="./work-add.html?id=';
$out+=$escape(item.id);
$out+='">编辑</a> </li> <li> <a href="#" class="js-del" data-id="';
$out+=$escape(item.id);
$out+='">删除</a> </li> </ul> </div> </td> --> <td class="text-center"> <a href="./work-add.html?id=';
$out+=$escape(item.id);
$out+='">编辑</a> <a href="#" class="js-del" data-id="';
$out+=$escape(item.id);
$out+='">删除</a> </td> </tr> ';
});
$out+=' </tbody> </table> <div class="pages"></div>';
return new String($out);
});