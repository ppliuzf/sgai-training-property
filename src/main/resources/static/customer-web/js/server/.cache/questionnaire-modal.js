/*TMODJS:{"version":1,"md5":"c9c2073fa29e6a0aa9b5ebd2e2b00bef"}*/
template('questionnaire-modal',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,list=$data.list,$escape=$utils.$escape,saContent=$data.saContent,scale=$data.scale,count=$data.count,$each=$utils.$each,item=$data.item,itemIndex=$data.itemIndex,boxTxt=$data.boxTxt,$out='';$out+=' ';
if(list){
$out+=' <div style="width: 100%;word-wrap: break-word;word-break: normal; ">';
$out+=$escape(saContent);
$out+='</div> <div class="row"> <div class=" col-xs-10 col-sm-9 col-md-6 col-lg-6"> <div class="progress"> <div class="progress-bar progress-bar-color" role="progressbar" aria-valuenow="';
$out+=$escape(scale);
$out+='" aria-valuemin="0" aria-valuemax="100" style="width:';
$out+=$escape(scale);
$out+='"> <span class="sr-only ">(';
$out+=$escape(scale);
$out+=')</span> </div> </div> </div> <div class="col-xs-1 col-sm-2 col-md-3 col-lg-3 "> <span>';
$out+=$escape(count);
$out+='票</span> </div> <div class="col-xs-1 col-sm-2 col-md-3 col-lg-3 "> <span>(';
$out+=$escape(scale);
$out+=')</span> </div> </div> <table class="table table-bordered table-hover"> <thead> <tr> <th class="text-center">序号</th> <th>ID</th> <th>IP地址</th> <th>姓名</th> <th>电话</th> <th>投票时间</th> </tr> </thead> <tbody class="js-list" id="select-detail"> ';
$each(list,function(item,itemIndex){
$out+=' <tr> <td class="text-center">';
$out+=$escape(itemIndex+1);
$out+='</td> <td>';
$out+=$escape(item.userId);
$out+='</td> <td class="wrap-word">';
$out+=$escape(item.ip);
$out+='</td> <td>';
$out+=$escape(item.userName);
$out+='</td> <td>';
$out+=$escape(item.userPhone);
$out+='</td> <td>';
$out+=$escape(item.saAnswerTime);
$out+='</td> </tr> ';
});
$out+=' </tbody> </table> <nav class="text-right pages"></nav> ';
}else{
$out+=' <div style="width: 100%;word-wrap: break-word;word-break: normal;">';
$out+=$escape(boxTxt);
$out+='</div>';
}
$out+=' <style> .progress-bar-color { background-image: -webkit-linear-gradient(top, #6EA9E2 0%, #6EA9E2 100%); background-image: -o-linear-gradient(top, #6EA9E2 0%, #6EA9E2 100%); background-image: -webkit-gradient(linear, left top, left bottom, from(#6EA9E2), to(#6EA9E2)); background-image: linear-gradient(to bottom, #6EA9E2 0%, #6EA9E2 100%); filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=\'#6EA9E2\', endColorstr=\'#6EA9E2\', GradientType=0); background-repeat: repeat-x; } </style>';
return new String($out);
});