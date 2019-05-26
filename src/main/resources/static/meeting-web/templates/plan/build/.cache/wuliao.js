/*TMODJS:{"version":1,"md5":"f34b04657d9bfc94ac41999e7d11ae16"}*/
template('wuliao',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <tr class="materiel-item" data-name="';
$out+=$escape(item.maName);
$out+='" data-id="';
$out+=$escape(item.maId);
$out+='" data-num="';
$out+=$escape(item.maCount);
$out+='" data-type="';
$out+=$escape(item.maTypeName);
$out+='" data-typeId="';
$out+=$escape(item.maTypeId);
$out+='" data-code="';
$out+=$escape(item.maCode);
$out+='"> <td>';
$out+=$escape(item.maName);
$out+='</td> <!--<td><input class="form-control" type="text" value="';
$out+=$escape(item.maCount);
$out+='" maxlength="5" placeholder="请输入物料数" style="width: 100%;"></td>--> <!--<td class="js-maCount" contenteditable="true">';
$out+=$escape(item.maCount);
$out+='</td>--> <td style="text-align: center"><a class="js-del" data-id="';
$out+=$escape(item.maId);
$out+='">删除</a></td> </tr> ';
});
return new String($out);
});