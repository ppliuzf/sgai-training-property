/*TMODJS:{"version":2,"md5":"d04d7112f3af80e7d3661fad472d7191"}*/
template('archives-detail-events',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,index=$data.index,$escape=$utils.$escape,fillZero=$helpers.fillZero,$out='';$each(items,function(item,index){
$out+=' <tr> <td class="text-center">';
$out+=$escape(index+1 < 10 ? "0" + (index+1) : index+1 | fillZero);
$out+='</td> <td>';
$out+=$escape(item.code || '--');
$out+='</td> <td>';
$out+=$escape(item.category || '--');
$out+='</td> <td>';
$out+=$escape(item.type == 'TS'? '投诉事件': item.type == 'AB'? '安保事件': item.type == 'WX'? '维修事件': '--');
$out+='</td> <td>';
$out+=$escape(item.person || '--');
$out+='</td> <td>';
$out+=$escape(item.telphone || '--');
$out+='</td> <td>';
$out+=$escape(item.time || '--');
$out+='</td> <td>';
$out+=$escape(item.type == 'TS' ? (item.states == "0" ? "待指派" : item.states == "1" ? "待处理" : item.states == 2 ? "待回访" : item.states == 3 ? "已完成" : "已终止") : (item.states == "0" ? "待核实" : item.states == "1" ? "待指派" : item.states == 2 ? "待处理" : item.states == 3 ? "已完成" : "已终止"));
$out+='</td> <td class="text-center"> <a href="#" class="js-event-check" data-code="';
$out+=$escape(item.code);
$out+='" data-codetype="';
$out+=$escape(item.codeType);
$out+='">查看进度</a> <a href="#" class="js-event-operate" data-code="';
$out+=$escape(item.code);
$out+='" data-states="';
$out+=$escape(item.states);
$out+='" data-type="';
$out+=$escape(item.type);
$out+='">';
$out+=$escape(item.type == 'TS' ? (item.states == "0" ? "指派" : item.states == "1" ? "处理" : item.states == 2 ? "回访" : "") : (item.states == "0" ? "核实" : item.states == "1" ? "指派" : item.states == 2 ? "处理" : ""));
$out+='</a> <a href="#" class="js-event-del co-red" data-code="';
$out+=$escape(item.code);
$out+='" data-states="';
$out+=$escape(item.states);
$out+='" data-type="';
$out+=$escape(item.type);
$out+='">';
$out+=$escape(item.states == "3" ? "" : item.states == "4" ? "" : "终止");
$out+='</a> </td> </tr> ';
});
$out+=' <style> .co-red { color: red; } </style>';
return new String($out);
});