/*TMODJS:{"version":1,"md5":"2e73766abd63c02739988c165ef3d186"}*/
template('archives-add-card',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,$index=$data.$index,$escape=$utils.$escape,id=$data.id,options=$data.options,opt=$data.opt,$out='';$each(items,function(item,$index){
$out+=' <div class="box"> <a href="#" class="js-card-remove hide"><i class="glyphicon glyphicon-remove-circle"></i></a> <div class="form-group clearfix"> <label for="card-';
$out+=$escape(id);
$out+='">证件类型</label> <select name="" id="card-';
$out+=$escape(id);
$out+='" class="card form-control"> ';
$each(options,function(opt,$index){
$out+=' <option value="';
$out+=$escape(opt.value);
$out+='" ';
$out+=$escape(opt.value == item.ccnId && 'selected');
$out+='>';
$out+=$escape(opt.title);
$out+='</option> ';
});
$out+=' </select> </div> <div class="form-group clearfix"> <label for="card-num-';
$out+=$escape(id);
$out+='">证件号</label> <input type="text" id="card-num-';
$out+=$escape(id);
$out+='" class="card-num form-control" maxlength="100" value="';
$out+=$escape(item.ccCertificateNo);
$out+='"> </div> </div> ';
});
return new String($out);
});