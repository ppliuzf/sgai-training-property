/*TMODJS:{"version":54,"md5":"ce7667a9d97a2aed4e2f037f9217f47d"}*/
template('contract-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,idx=$data.idx,$escape=$utils.$escape,$out='';$each(items,function(item,idx){
$out+=' <tr> <td class="text-center"> ';
if(item.status === 1){
$out+=' <input type="checkbox" class="js-list-select-single" data-id="';
$out+=$escape(item.id);
$out+='" data-status="';
$out+=$escape(item.status);
$out+='" name="choose"> ';
}else{
$out+=' <input type="checkbox" class="js-list-select-single" data-id="';
$out+=$escape(item.id);
$out+='" data-status="';
$out+=$escape(item.status);
$out+='" disabled > ';
}
$out+=' </td> <td class="text-center">';
$out+=$escape(idx + 1 < 10 ? '0'+(idx + 1) : idx + 1);
$out+='</td> <td><a href="./contract-detail.html?id=';
$out+=$escape(item.id);
$out+='">';
$out+=$escape(item.no);
$out+='</a></td> <td><div>';
$out+=$escape(item.name);
$out+='</div></td> <td><div>';
$out+=$escape(item.typeName);
$out+='</div></td> <td>';
$out+=$escape($helpers. dateFormat(item.singDate , 'yyyy-MM-dd'));
$out+='</td> <td>';
$out+=$escape(item.creater);
$out+='</td> <td><div>';
$out+=$escape(item.ownerName);
$out+='</div></td> <td><div>';
$out+=$escape(item.secondPartyName);
$out+='</div></td> <td>';
$out+=$escape($helpers. dateFormat(item.effectiveDate , 'yyyy-MM-dd'));
$out+='</td> <td>';
$out+=$escape(item.amount);
$out+='万</td> <td>';
$out+=$escape(item.status !== 1 ? '已签约': '未签约');
$out+='</td>      <!--<a href="./contract-add.html?id=';
$out+=$escape(item.id);
$out+='" data-id="';
$out+=$escape(item.id);
$out+='" class="js-edit">编辑</a>-->  <!--';
if(item.status === 1){
$out+='-->  <!--<a href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='" class="js-del">删除</a>-->  <!--';
}
$out+='-->    <td class="text-center" style="white-space:nowrap"> <a href="./contract-add.html?id=';
$out+=$escape(item.id);
$out+='" data-id="';
$out+=$escape(item.id);
$out+='" class="js-edit">编辑</a> ';
if(item.status === 1){
$out+=' <a href="javascript:;" data-id="';
$out+=$escape(item.id);
$out+='" class="js-del">删除</a> ';
}
$out+=' </td> </tr> ';
});
$out+=' <style> td div{ display: -webkit-box; overflow: hidden; white-space: normal!important; text-overflow: ellipsis; word-wrap: break-word; -webkit-line-clamp: 3; -webkit-box-orient: vertical; } </style>';
return new String($out);
});