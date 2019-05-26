/*TMODJS:{"version":1,"md5":"b347ac8ce55f3b6fc8e2483417cdba39"}*/
template('questionnaire-quest',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,surveyMainVo=$data.surveyMainVo,$each=$utils.$each,surveyQuestionDetailVoList=$data.surveyQuestionDetailVoList,item=$data.item,$index=$data.$index,smCount=$data.smCount,sqNo=$data.sqNo,idem=$data.idem,$out='';$out+='<dl class="show-quest"> <dt> <h3 class="wrap-word">';
$out+=$escape(surveyMainVo.smName);
$out+='</h3> <div class="meta">结束时间：';
$out+=$escape(surveyMainVo.smEndTime);
$out+='</div> </dt> ';
$each(surveyQuestionDetailVoList,function(item,$index){
$out+=' <dd class="detail-box"> <div class="item"> <h4 style="font-size: 16px;" class="wrap-word" data-cou="';
$out+=$escape(smCount);
$out+='" data-sq="';
$out+=$escape(sqNo);
$out+='" data-sqtype="';
$out+=$escape(item.sqType);
$out+='"> 第';
$out+=$escape(item.sqNo);
$out+='题：';
$out+=$escape(item.sqTopic);
$out+=' </h4> </div> ';
if(item.sqType==0){
$out+=' ';
$each(item.saContentList,function(idem,$index){
$out+='  <div class="checkbox"> <label> <input type="radio" class="answerZ" value="';
$out+=$escape(idem);
$out+='" name="';
$out+=$escape(item.sqNo);
$out+='" data-name="';
$out+=$escape(item.sqNo);
$out+='" flag=t rue data-sqid="';
$out+=$escape(item.sqId);
$out+='" data-sqtype="';
$out+=$escape(item.sqType);
$out+='"> <span class="Zanswer">';
$out+=$escape(idem);
$out+='</span> </label> </div> ';
});
$out+=' ';
}else if(item.sqType==1){
$out+=' ';
$each(item.saContentList,function(idem,$index){
$out+='  <div class="checkbox"> <label style="padding-left: 40px"> <input type="checkbox" class="answerO" value="';
$out+=$escape(idem);
$out+='" name="';
$out+=$escape(item.sqNo);
$out+='" data-name="';
$out+=$escape(item.sqNo);
$out+='" flag=t rue data-sqid="';
$out+=$escape(item.sqId);
$out+='" data-sqtype="';
$out+=$escape(item.sqType);
$out+='"> <span class="Oanswer">';
$out+=$escape(idem);
$out+='</span> </label> </div> ';
});
}else{
$out+=' <div class="form-group">  <textarea id="desc" rows="4" class="form-control answerText" maxlength="200" data-name="';
$out+=$escape(item.sqNo);
$out+='" data-sqid="';
$out+=$escape(item.sqId);
$out+='" data-sqtype="';
$out+=$escape(item.sqType);
$out+='"></textarea> <div class="text-right js-count">0/200</div> </div> ';
}
$out+=' </dd> ';
});
$out+=' <dd> <div class="form-group"> <button class="btn btn-primary js-save">下一步</button> <button class="btn btn-default js-close">取消</button> </div> </dd> </dl> <dl class="hide-query"> <div class="row clearfix"> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="name"> <span class="require"></span>问卷人姓名</label> <input type="text" name="userName" id="userName" maxlength="20" class="form-control"> </div> </div> <div class="form-group"> <div class="row clearfix"> <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6"> <label for="Mobile"> <span class="require"></span>问卷人手机</label> <input type="number" name="userPhone" maxlength="11" id="userPhone" class="form-control" oninput="if(value.length>11)value=value.slice(0,11)"> </div> </div> </div> <div class="form-group"> <button class="btn btn-primary js-saves trims" role="button">保存</button> <button class="btn btn-primary js-Entry trims" role="button">保存并录入下一条</button> <button class="btn btn-default js-cancel">上一步</button> </div> </dl> <style> /* .answerZ,.answerO{ margin-left: 50%; } */ input { position: relative; *top: 1px; margin: 0 4px 0 0; *margin: 0 0 0 -4px; } .red-query { color: red; } .hide-query { display: none; } .show-query { display: block; } .meta { color: #666; } .detail-box { padding-top: 10px; } .wrap-word { word-wrap: break-word; } .tip-txt { font-size: 14px; } .q-list { list-style: none; margin: 0; padding: 0; } .q-list { padding: 0 0 10px 15px; } .q-list li { padding-bottom: 10px; } .modal-body,.Zanswer,.Oanswer{ overflow-y: auto; word-wrap: break-word; word-break: normal; } </style>';
return new String($out);
});