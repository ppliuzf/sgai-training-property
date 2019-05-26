/*TMODJS:{"version":1,"md5":"e08266c3ec0122f5868422976a12c11b"}*/
template('common/week-selector',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,year=$data.year,month=$data.month,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,day=$data.day,$out='';$out+='<div class="week-selector"> <div class="title text-center">';
$out+=$escape(year);
$out+='年';
$out+=$escape(month);
$out+='月</div> <div class="selector"> <ul class="clearfix"> ';
$each(items,function(item,$index){
$out+=' <li class="';
$out+=$escape(item.day === day && 'current today');
$out+='"> <div class="item"> <p class="day">';
$out+=$escape(item.day);
$out+='日</p> <p class="week">周';
$out+=$escape(item.week);
$out+='</p> </div> </li> ';
});
$out+=' </ul> </div> <a class="prev"><i class="glyphicon glyphicon-menu-left"></i></a> <a class="next"><i class="glyphicon glyphicon-menu-right"></i></a> </div>';
return new String($out);
});