/*TMODJS:{"version":14,"md5":"81edbb1a5b0bc9840b624b052965deee"}*/
template('plan/list-data',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,itemIndex=$data.itemIndex,$escape=$utils.$escape,$out='';$each(items,function(item,itemIndex){
$out+=' <tr> <td>';
$out+=$escape(item.num);
$out+='</td> <td> <div class="cont"> <!--<a href="detail.html?id=';
$out+=$escape(item.id + '&name=' + item.recordName);
$out+='" title="';
$out+=$escape(item.recordName);
$out+='" class="underline dot name">';
$out+=$escape(item.recordName);
$out+='</a>--> <a href="./pages/plan/detail.html?id=';
$out+=$escape(item.id + '&name=' + item.recordName);
$out+='" title="';
$out+=$escape(item.recordName);
$out+='" class="underline dot name">';
$out+=$escape(item.recordName);
$out+='</a> </div> </td> <td><div class="cont dot" title="';
$out+=$escape(item.raDutyEiEmpName);
$out+='">';
$out+=$escape(item.typeName);
$out+='</div></td> <td><div class="cont dot" title="';
$out+=$escape($helpers. dateFormat(item.createTime , 'yyyy-MM-dd'));
$out+='">';
$out+=$escape($helpers. dateFormat(item.createTime , 'yyyy-MM-dd'));
$out+='</div></td> <td><div class="cont dot" title="';
$out+=$escape(item.raCreatorEiEmpName);
$out+='">';
$out+=$escape(item.creatorEiEmpName);
$out+='</div></td> </tr> ';
});
return new String($out);
});