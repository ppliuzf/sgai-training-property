/*TMODJS:{"version":2,"md5":"fa992e6bc089f2ef8e397065e967eb6f"}*/
template('archives-detail-cars',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,index=$data.index,$escape=$utils.$escape,fillZero=$helpers.fillZero,$out='';$each(items,function(item,index){
$out+=' <tr> <td class="text-center">';
$out+=$escape(index+1 < 10 ? "0" + (index+1) : index+1 | fillZero);
$out+='</td> <td>';
$out+=$escape(item.ciNumber || '--');
$out+='</td> <td>';
$out+=$escape(item.ciBrand || '--');
$out+='</td> <td>';
$out+=$escape(item.ciModel || '--');
$out+='</td> <td>';
$out+=$escape(item.ciColor || '--');
$out+='</td> <td>';
$out+=$escape(item.ciLoadNumber || '--');
$out+='</td> <!-- <td class="text-center act"> <div class="btn-group more"> <button type="button" class="btn btn-primary js-car-del" data-ccid="';
$out+=$escape(item.id);
$out+='">删除</button>   <!-- <li><a href="#" class="js-car-del" data-ccid="';
$out+=$escape(item.id);
$out+='">删除</a></li> -->    </tr> ';
});
return new String($out);
});