/*TMODJS:{"version":1,"md5":"464efa66e40bb36d2a60ae68ccbb20ea"}*/
template('modal-pop',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,size=$data.size,title=$data.title,$string=$utils.$string,content=$data.content,isCancel=$data.isCancel,$out='';$out+='<div class="modal fade" id="modal-pop" role="dialog"> <div class="modal-dialog modal-';
$out+=$escape(size);
$out+='"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> <h4 class="modal-title" id="exampleModalLabel">';
$out+=$escape(title);
$out+='</h4> </div> <div class="modal-body">';
$out+=$string( content);
$out+='</div> <div class="modal-footer"> <a class="btn btn-primary js-modal-sure" data-dismiss="modal">确定</a> ';
if(isCancel){
$out+=' <a class="btn btn-default" data-dismiss="modal">取消</a> ';
}
$out+=' </div> </div> </div> </div>';
return new String($out);
});