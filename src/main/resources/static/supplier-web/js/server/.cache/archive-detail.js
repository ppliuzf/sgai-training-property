/*TMODJS:{"version":6,"md5":"8129df9d3c97b1c9a2d30f9342d4633a"}*/
template('archive-detail',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,item=$data.item,$each=$utils.$each,i=$data.i,$index=$data.$index,file=$data.file,$out='';$out+='<div class="col-xs-12 col-sm-11 col-md-9 col-lg-7 " > <div class="infos-detail"> <div class="form-group"> <label for="information">基础信息：</label> </div> <div class="form-group"> <table> <tbody> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">公司名称</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.name);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">编号</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.no);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">供应商分类</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.typeName);
$out+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">供应商等级</td> ';
if(item.levelId==='1'){
$out+=' <td class="col-lg-3 col-md-3 col-sm-3 value">未评级</td> ';
}else{
$out+=' <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.levelName);
$out+='</td> ';
}
$out+=' <td class="col-lg-1 col-md-1 col-sm-1 text-center">联系人</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.contact);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">联系电话</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.phone);
$out+='</td> </tr> <tr> <td class="text-center">地址</td> <td class="value" colspan="5">';
$out+=$escape(item.address);
$out+='</td> </tr> <tr> <td class="text-center">服务内容</td> <td class="value">';
$out+=$escape(item.contentName);
$out+='</td> <td class="text-center"></td> <td class="value"></td> <td class="text-center"></td> <td class="value"></td> </tr> </tbody> </table> </div> <div class="form-group"> <label for="information">营业执照信息：</label> </div> <div class="form-group"> <table> <tbody> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">营业执照号</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.licenseNo);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">法人信息</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.legalName);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">一般纳税人类别</td> ';
if(item.taxpayerType===1){
$out+=' <td class="col-lg-3 col-md-3 col-sm-3 value">一般纳税人</td> ';
}else if(item.taxpayerType===2){
$out+=' <td class="col-lg-3 col-md-3 col-sm-3 value">小规模纳税人</td> ';
}
$out+=' </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">税率</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.rate);
$out+='%</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">开户银行</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.bankName);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">银行账号</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(item.bankAccount);
$out+='</td> </tr> </tbody> </table> </div> </div> </div> <div class="col-xs-12 col-sm-11 col-md-9 col-lg-7"> <div class="form-group "> <label for="photos">相关证件照：</label> </div> <div class="form-group"> <ul class="list-unstyled photoss photodd">  <!--';
if(item.licenseUrl){
$out+='--> <!--<a href="';
$out+=$escape(item.licenseUrl);
$out+='" data-lightbox="cover+1" target="_blank">--> <!--<img src="';
$out+=$escape(item.licenseUrl);
$out+='"/>-->  <!--';
}else{
$out+='-->  <!--';
}
$out+='-->   <!--';
if(item.cardAUrl){
$out+='--> <!--<a href="';
$out+=$escape(item.cardAUrl);
$out+='" data-lightbox="cover+2" target="_blank">--> <!--<img src="';
$out+=$escape(item.cardAUrl);
$out+='"/>-->  <!--';
}else{
$out+='-->  <!--';
}
$out+='-->   <!--';
if(item.cardBUrl){
$out+='--> <!--<a href="';
$out+=$escape(item.cardBUrl);
$out+='" data-lightbox="cover+3" target="_blank">--> <!--<img src="';
$out+=$escape(item.cardBUrl);
$out+='"/>-->  <!--';
}else{
$out+='-->  <!--';
}
$out+='-->  ';
$each(item.arr,function(i,$index){
$out+=' <li> ';
if(i){
$out+=' <a href="';
$out+=$escape(i);
$out+='" target="_blank" data-lightbox="cover+';
$out+=$escape(item.id);
$out+='"> <img src="';
$out+=$escape(i);
$out+='" alt="" class="clickImg"> </a> ';
}else{
$out+=' <a href="images/no-uploder.png" target="_blank" data-lightbox="cover+';
$out+=$escape(item.id);
$out+='"> <img src="images/no-uploder.png" alt="" class="clickImg"> </a> ';
}
$out+=' </li> ';
});
$out+=' </ul> <ul class="list-unstyled photoss"> <li> <div>营业执照正面</div> </li> <li> <div>法人身份证正面</div> </li> <li> <div>法人身份证反面</div> </li> </ul> </div> </div> <div class="col-xs-12 col-sm-11 col-md-9 col-lg-7"> <div class="form-group "> <label for="files">相关附件：</label> </div> <div class="form-group"> <ul class="list-unstyled wenjian"> ';
$each(item.fileList,function(file,$index){
$out+=' <li> <a href="#" class=\'underline js-ToFile\' data-name="';
$out+=$escape(file.fileName);
$out+='" data-url="';
$out+=$escape(file.fileUrl);
$out+='">';
$out+=$escape(file.fileName);
$out+='</a> </li> ';
});
$out+=' </ul> </div> </div> <div class="col-xs-12 col-sm-11 col-md-9 col-lg-7"> <div class="form-group "> <label for="contract">关联合同：</label> </div> <table class="table table-bordered table-hover"> <thead> <tr> <th class="text-center" width="60">序号</th> <th>合同编号</th> <th>合同名称</th> <th>合同类型</th> <th>创建时间</th> <th>创建人</th> <th>合同状态</th> </tr> </thead> <tbody class="js-list"> </tbody> </table> <nav class="text-right pages"></nav> </div>';
return new String($out);
});