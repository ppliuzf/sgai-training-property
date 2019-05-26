/*TMODJS:{"version":29,"md5":"d6d17327e34803c3a1579d1c53634ac2"}*/
template('supplier-right',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$out+='<ul class="list-right"> ';
$each(items,function(item,$index){
$out+=' <li data-id="';
$out+=$escape(item.supplier_id);
$out+='">';
$out+=$escape(item.supplier_name);
$out+='<i class="dels"></i></li> ';
});
$out+=' </ul> <style> .list-right{ margin: 0; padding: 0; } .list-right li{ position: relative; list-style: none; background: #F8F9FC; } .list-right li:nth-of-type(odd){ background: #FFFFFF; } .dels{ background: url(\'images/close.png\')no-repeat darkgray; background-size: 10px 10px; width: 10px; height: 10px; display: inline-block; cursor: pointer; transition: 0.5s; position: absolute; right: 8px; top: 5px; border-radius: 50%; } .dels:hover{ transform: rotate(90deg); } </style>';
return new String($out);
});