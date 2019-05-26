/*TMODJS:{"version":6,"md5":"029f886be6ac4761e1cd69bf32f5bfcd"}*/
template('plan/type-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,items=$data.items,$each=$utils.$each,item=$data.item,idx=$data.idx,$escape=$utils.$escape,$out='';$out+='<table class="table table-bordered table-hover table-striped table-list"> <thead> <tr> <th width=\'60\' class="text-center">序号</th> <th >类型名称</th> <th >描述</th>  <th class=" text-center" width=\'90\'>操作</th> </tr> </thead> <tbody class="js-list"> ';
if(items.length>0){
$out+=' ';
$each(items,function(item,idx){
$out+=' <tr> <td style="text-align:center;" >';
$out+=$escape(idx+1 < 10 ? "0" + (idx+1) : idx+1);
$out+='</td> <td >';
$out+=$escape(item.rtName);
$out+='</td> <td >';
$out+=$escape(item.rtTypeDesc);
$out+='</td> <!-- <td class="col-xs-2 col-sm-2 col-md-2 col-lg-2">';
$out+=$escape(item.rtTypeName);
$out+='</td> --> <td class="text-center"> <!-- <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-2 col-lg-offset-2"> <div class="btn-group"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">查看更多 <span class="caret"></span> </button> <ul class="dropdown-menu"> <li> <a href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='" data-delete="';
$out+=$escape(item.mayDelete);
$out+='" class="js-del">删除</a> </li> <li> <a href="./type-list-add.html?id=';
$out+=$escape(item.id);
$out+='" data-id="';
$out+=$escape(item.id);
$out+=' " data-state="';
$out+=$escape(item.rrRoomState);
$out+='">编辑</a> </li> </ul> </div> </div> --> <a href="./type-list-add.html?id=';
$out+=$escape(item.id);
$out+='" data-id="';
$out+=$escape(item.id);
$out+=' " data-state="';
$out+=$escape(item.rrRoomState);
$out+='" class="js-edit" data-id="1" >编辑</a> <a href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='" data-delete="';
$out+=$escape(item.mayDelete);
$out+='" class="js-del" class="js-del" data-id="1">删除</a> </td> </tr> ';
});
$out+=' ';
}else{
$out+=' <tr> <td colspan="5" style="text-align: center">暂无数据</td> </tr> ';
}
$out+=' </tbody> </table> ';
if(items.length>0){
$out+=' <div class="pages" style="float:right;"></div>';
}else{
}
$out+=' ';
return new String($out);
});