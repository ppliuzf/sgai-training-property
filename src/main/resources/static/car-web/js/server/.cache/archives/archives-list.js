/*TMODJS:{"version":1,"md5":"765716dd25c470d0a31795fd76084f85"}*/
template('archives/archives-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,itemIndex=$data.itemIndex,$escape=$utils.$escape,$out='';$each(items,function(item,itemIndex){
$out+=' <tr> <td class=\'text-center\'>';
$out+=$escape(item.num);
$out+='</td> <td> <a href="pages/archives/archives-detail.html?id=';
$out+=$escape(item.id);
$out+='"> <!--';
if(item.imgUrl){
$out+='--> <!--<span class="arch-mar"><img src="';
$out+=$escape(item.imgUrl);
$out+='" alt="" width="50" height="50"></span>--> <!--';
}else{
$out+='-->  <!--';
}
$out+='--> ';
$out+=$escape(item.ciNumber);
$out+=' </a> </td> <td>';
$out+=$escape(item.ciDepartment);
$out+='</td> <td>';
$out+=$escape(item.ciTypeName);
$out+='</td> <td>';
$out+=$escape(item.ciBrand);
$out+='</td> <td>';
$out+=$escape(item.ciModel);
$out+='</td> <td>';
$out+=$escape(item.ciColor);
$out+='</td> <td>';
$out+=$escape(item.ciBoxTypeName);
$out+='</td> <td>';
$out+=$escape(item.ciLoadNumber);
$out+='</td> <td> <!--';
if(item.ciStatus===0 ){
$out+='--> <!--<button type="button" class="btn btn-sm btn-success btn-on" data-id="';
$out+=$escape(item.id);
$out+='">启用</button>--> <!--';
}else{
$out+='--> <!--<button type="button" class="btn btn-sm btn-warning btn-off" data-id="';
$out+=$escape(item.id);
$out+='">停用</button>--> <!--';
}
$out+='--> <div class="switch"> <div class="switch-inner"> <input type="checkbox" ';
$out+=$escape(item.ciStatus === 0 && 'checked');
$out+=' class="js-status" data-id="';
$out+=$escape(item.id);
$out+='" > </div> </div> </td> <td class=\'text-center\'> <a href="pages/archives/archives-edit.html?id=';
$out+=$escape(item.id);
$out+='" class="js-edit" data-id="';
$out+=$escape(item.id);
$out+='">编辑</a> <a href="javascript:;" class="js-del" data-id=';
$out+=$escape(item.id);
$out+='>删除</a> </td> </tr> ';
});
return new String($out);
});