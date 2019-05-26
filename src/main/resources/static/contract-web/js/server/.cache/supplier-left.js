/*TMODJS:{"version":24,"md5":"9ae8b9717c134e0ecfb40ace506dbd48"}*/
template('supplier-left',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <tr> <td class="text-center"> <input type="checkbox" name="choose" id="';
$out+=$escape(item.id);
$out+='" supplier_name="';
$out+=$escape(item.name);
$out+='"> </td> ';
if(item.index<10){
$out+=' <td class="text-center"><div class="cont dot" title="0';
$out+=$escape(item.index);
$out+='">0';
$out+=$escape(item.index);
$out+='</div></td> ';
}else{
$out+=' <td class="text-center"><div class="cont dot" title="';
$out+=$escape(item.index);
$out+='">';
$out+=$escape(item.index);
$out+='</div></td> ';
}
$out+=' <td><div>';
$out+=$escape(item.name);
$out+='</div></td> </tr> ';
});
$out+=' ';
return new String($out);
});