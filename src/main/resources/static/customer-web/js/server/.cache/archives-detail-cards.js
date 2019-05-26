/*TMODJS:{"version":2,"md5":"f8373ca6f30452114d746e4c1c6a9afe"}*/
template('archives-detail-cards',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,index=$data.index,$escape=$utils.$escape,fillZero=$helpers.fillZero,$out='';$each(items,function(item,index){
$out+=' <tr> <td class="text-center">';
$out+=$escape(index+1 < 10 ? "0" + (index+1) : index+1 | fillZero);
$out+='</td> <td>';
$out+=$escape(item.ccCertificateName);
$out+='</td> <td>';
$out+=$escape(item.ccCertificateNo);
$out+='</td> <!-- <td class="text-center act"> <div class="btn-group more"> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">更多</button> <ul class="dropdown-menu"> <li><a href="#" class="js-card-edit" data-ccnid="';
$out+=$escape(item.ccnId);
$out+='" data-ccid="';
$out+=$escape(item.ccId);
$out+='" data-number="';
$out+=$escape(item.ccCertificateNo);
$out+='">编辑</a></li> <li><a href="#" class="js-card-del" data-ccid="';
$out+=$escape(item.ccId);
$out+='">删除</a></li> </ul> </div> </td> --> <td class="text-center"> <a href="#" class="js-card-edit" data-ccnid="';
$out+=$escape(item.ccnId);
$out+='" data-ccid="';
$out+=$escape(item.ccId);
$out+='" data-number="';
$out+=$escape(item.ccCertificateNo);
$out+='">编辑</a> <a href="#" class="js-card-del" data-ccid="';
$out+=$escape(item.ccId);
$out+='">删除</a> </td> </tr> ';
});
return new String($out);
});