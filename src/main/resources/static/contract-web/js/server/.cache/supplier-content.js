/*TMODJS:{"version":27,"md5":"06988cd85323801abbe362e3ab370d2f"}*/
template('supplier-content',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <span class="js-del-device fcol" data-id="';
$out+=$escape(item.supplier_id);
$out+='">';
$out+=$escape(item.supplier_name);
$out+='<i class="del"></i></span> ';
});
$out+=' <style> .fcol{ color:black; } .del{ background: url(\'images/close.png\')no-repeat darkgray; background-size: 10px 10px; width: 10px; height: 10px; display: inline-block; margin-left: 4px; cursor: pointer; transition: 0.5s; border-radius: 50%; } .del:hover{ transform: rotate(90deg); } </style> ';
return new String($out);
});