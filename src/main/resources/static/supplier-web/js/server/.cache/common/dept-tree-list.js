/*TMODJS:{"version":1,"md5":"b9d80d429243f8d37d661604b14ebcd2"}*/
template('common/dept-tree-list',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,items=$data.items,$each=$utils.$each,item=$data.item,$index=$data.$index,isDisabled=$data.isDisabled,$escape=$utils.$escape,type=$data.type,isSearch=$data.isSearch,$out='';$out+=' ';
if(items && items.length){
$out+=' ';
$each(items,function(item,$index){
$out+=' ';
if(item.disable && item.disable === 'Y' && isDisabled){
$out+=' <div class="item"> ';
if(item.nodeType == '0'){
$out+=' <i class="icon icon-checkbox-disabled"></i> ';
}else{
$out+=' <i class="glyphicon glyphicon-user"></i> ';
}
$out+=' <span class="title">';
$out+=$escape(item.nodeName);
$out+='</span> </div> ';
}else{
$out+=' <li data-number="';
$out+=$escape(item.empNum);
$out+='" data-avatar="';
$out+=$escape(item.avatar);
$out+='" data-id="';
$out+=$escape(item.nodeId);
$out+='" ';
$out+=$escape(item.nodeType == '0' && ' data-group="1"');
$out+='> <input type="checkbox"';
if(item.nodeType == '0' && type === 'emp'){
$out+=' disabled';
}
$out+='> ';
if(item.nodeType == '0'){
$out+=' <i class="icon icon-folder"></i> ';
}else{
$out+=' <i class="glyphicon glyphicon-user"></i> ';
}
$out+=' <span class="title" data-number="';
$out+=$escape(item.empNum);
$out+='">';
$out+=$escape(item.nodeName);
$out+='</span> ';
if(item.nodeType == '0' && item.empNum > 0 && !isSearch){
$out+=' <a href="javascript:" class="inside js-dept-inside"><i class="glyphicon glyphicon-menu-right"></i></a> ';
}
$out+=' <div class="clicker"></div> </li> ';
}
$out+=' ';
});
$out+=' ';
}else{
$out+=' <li class="no-record">无记录</li> ';
}
return new String($out);
});