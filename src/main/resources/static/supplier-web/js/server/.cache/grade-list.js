/*TMODJS:{"version":1,"md5":"7d5ee02859f6e0a229015fbc39556fef"}*/
template('grade-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,$out='';$each(items,function(item,$index){
$out+=' <tr> ';
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
$out+=' <td> <div class="cont dot HH" title="';
$out+=$escape(item.name);
$out+='">';
$out+=$escape(item.name);
$out+='</div> </td> <td> <div class="cont dot HH" title="';
$out+=$escape(item.description);
$out+='">';
$out+=$escape(item.description);
$out+='</div> </td> <td> <div class="cont dot" title="';
$out+=$escape(item.levelCount);
$out+='">';
$out+=$escape(item.levelCount);
$out+='</div> </td>     <!--<li><a href="./grade-compile.html?id=';
$out+=$escape(item.id);
$out+='" class="underline js-edit" data-id="';
$out+=$escape(item.id);
$out+='">编辑</a></li>--> <!--';
if(item.isDelete !== 3){
$out+='--> <!--<li><a class="underline js-del" data-id="';
$out+=$escape(item.id);
$out+='" data-count="';
$out+=$escape(item.count);
$out+='">删除</a></li>--> <!--';
}
$out+='-->        <!--<li><a href="./grade-compile.html?id=';
$out+=$escape(item.id);
$out+='" class="underline js-edit" data-id="';
$out+=$escape(item.id);
$out+='">编辑</a></li>--> <!--';
if(item.isDelete !== 3){
$out+='--> <!--<li><a class="underline js-del" data-id="';
$out+=$escape(item.id);
$out+='" data-count="';
$out+=$escape(item.levelCount);
$out+='">删除</a></li>--> <!--';
}
$out+='-->    <td class="text-center"> <a href="./grade-compile.html?id=';
$out+=$escape(item.id);
$out+='" class="underline js-edit" data-id="">编辑</a> ';
if(item.isDelete !== 3){
$out+=' <a href="#" class="underline js-del" data-id="';
$out+=$escape(item.id);
$out+='" data-count="';
$out+=$escape(item.count);
$out+='">删除</a> ';
}
$out+=' </td> </tr> ';
});
return new String($out);
});