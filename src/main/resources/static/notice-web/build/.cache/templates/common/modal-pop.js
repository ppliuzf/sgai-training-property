/*TMODJS:{"version":2,"md5":"a6f4cbe2a7b40e8e97908608c10356ab"}*/
template('templates/common/modal-pop',function($data,$filename
/**/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,size=$data.size,title=$data.title,noIcon=$data.noIcon,$string=$utils.$string,content=$data.content,isCancel=$data.isCancel,sureText=$data.sureText,$out='';$out+='<div class="modal fade" id="modal-pop" role="dialog"> <div class="modal-dialog modal-';
$out+=$escape(size);
$out+='"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> <h4 class="modal-title" id="exampleModalLabel">';
$out+=$escape(title);
$out+='</h4> </div> <div class="modal-body';
$out+=$escape(noIcon && ' no-icon');
$out+='">';
$out+=$escape(noIcon);
$out+=$string( content);
$out+='</div> <div class="modal-footer"> ';
if(isCancel){
$out+=' <a class="btn btn-default" data-dismiss="modal">取消</a> ';
}
$out+=' <a class="btn btn-primary js-modal-sure" data-dismiss="modal">';
$out+=$escape(sureText);
$out+='</a> </div> </div> </div> </div> ';
return new String($out);
});