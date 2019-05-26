/*TMODJS:{"version":35,"md5":"5938890bdd586d173418f607c667f43e"}*/
template('major/img-list',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <li> <img src="';
$out+=$escape(item.url);
$out+='" alt=""> <span class="glyphicon glyphicon-ok icon-right"></span> </li> ';
});
return new String($out);
});