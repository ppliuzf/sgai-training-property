/*TMODJS:{"version":1,"md5":"e6bf053bc4030abbe6fd6c31d1d1f948"}*/
template('common/modal-pop',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,el=$data.el,size=$data.size,title=$data.title,noIcon=$data.noIcon,$string=$utils.$string,content=$data.content,isCancel=$data.isCancel,cancelModel=$data.cancelModel,cancel=$data.cancel,yesModel=$data.yesModel,sureText=$data.sureText,$out='';$out+='<div class="modal fade" id="';
$out+=$escape(el);
$out+='" role="dialog"> <div class="modal-dialog modal-';
$out+=$escape(size);
$out+='"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button> <h4 class="modal-title" id="exampleModalLabel">';
$out+=$escape(title);
$out+='</h4> </div> <div class="modal-body';
$out+=$escape(noIcon && ' no-icon');
$out+='">';
$out+=$escape(noIcon);
$out+=$string( content);
$out+='</div> <div class="modal-footer"> ';
if(isCancel){
$out+=' <button class="btn btn-default js-model-cancel" data-dismiss="';
$out+=$escape(cancelModel ? 'modal' : '');
$out+='">';
$out+=$escape(cancel);
$out+='</button> ';
}
$out+=' <button class="btn btn-primary js-modal-sure" data-dismiss="';
$out+=$escape(yesModel ? 'modal' : '');
$out+='">';
$out+=$escape(sureText);
$out+='</button> </div> </div> </div> </div>';
return new String($out);
});