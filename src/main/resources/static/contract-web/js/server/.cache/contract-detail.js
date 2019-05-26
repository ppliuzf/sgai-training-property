/*TMODJS:{"version":50,"md5":"ca69725e3f13737559af2483e4d1fb20"}*/
template('contract-detail',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$escape=$utils.$escape,name=$data.name,no=$data.no,typeName=$data.typeName,amount=$data.amount,singDate=$data.singDate,ownerName=$data.ownerName,secondPartyName=$data.secondPartyName,effectiveDate=$data.effectiveDate,creater=$data.creater,phone=$data.phone,description=$data.description,$each=$utils.$each,suppliers=$data.suppliers,supplier=$data.supplier,$index=$data.$index,images=$data.images,item=$data.item,files=$data.files,$out='';$out+='<div class="col-xs-12"> <div class="infos-detail"> <div class="form-group"> <table> <tbody> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">合同名称</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(name);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">合同编号</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(no);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">合同类型</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(typeName);
$out+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">合同总额</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(amount);
$out+='万</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">签订日期</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape($helpers. dateFormat(singDate , 'yyyy-MM-dd'));
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">甲方名称</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(ownerName);
$out+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">乙方名称</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(secondPartyName);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">生效日期</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape($helpers. dateFormat(effectiveDate , 'yyyy-MM-dd'));
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center">创建人</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(creater);
$out+='</td> </tr> <tr> <td class="col-lg-1 col-md-1 col-sm-1 text-center">手机号</td> <td class="col-lg-3 col-md-3 col-sm-3 value">';
$out+=$escape(phone);
$out+='</td> <td class="col-lg-1 col-md-1 col-sm-1 text-center"></td> <td class="col-lg-3 col-md-3 col-sm-3 value"></td> <td class="col-lg-1 col-md-1 col-sm-1 text-center"></td> <td class="col-lg-3 col-md-3 col-sm-3 value"></td> </tr> <tr> <td class="text-center">描述</td> <td class="value" colspan="5">';
$out+=$escape(description);
$out+='</td> </tr> <tr> <td class="text-center">供应商</td> <td class="value" colspan="5"> ';
$each(suppliers,function(supplier,$index){
$out+=' ';
if(suppliers.length){
$out+=' ';
if(supplier === suppliers[suppliers.length-1]){
$out+=' <span class="detail-title desc">';
$out+=$escape(supplier.name);
$out+='</span> ';
}else{
$out+=' <span class="detail-title desc">';
$out+=$escape(supplier.name);
$out+=' , </span> ';
}
$out+=' ';
}
$out+=' ';
});
$out+=' </td> </tr> </tbody> </table> </div> </div> </div> <div class="col-xs-12"> <div class="form-group"> <label>相关图片:</label> </div> ';
$each(images,function(item,$index){
$out+=' <div class="di-box"> ';
if(item){
$out+=' <a href="';
$out+=$escape(item);
$out+='" data-lightbox="img"> <img src="';
$out+=$escape(item);
$out+='" alt=""> </a> ';
}else{
$out+=' <img src="images/no-uploder.png" alt=""> ';
}
$out+=' </div> ';
});
$out+=' </div> <div class="col-xs-12">  <div class="form-group"> <label>相关附件:</label> </div>  <div class="form-group"> <label>合同副本:</label> </div> ';
$each(files,function(item,$index){
if(item.mark === 1){
$out+=' <div class="file-box"> <a href="#" class=\'underline js-ToFile\' data-name="';
$out+=$escape(item.fileName);
$out+='" data-url="';
$out+=$escape(item.fileUrl);
$out+='">';
$out+=$escape(item.fileName);
$out+='</a> </div> ';
}
$out+=' ';
});
$out+='  <div class="form-group"> <label>补充协议:</label> </div> ';
$each(files,function(item,$index){
$out+=' ';
if(item.mark === 2){
$out+=' <div class="file-box"> <a href="#" class=\'underline js-ToFile\' data-name="';
$out+=$escape(item.fileName);
$out+='" data-url="';
$out+=$escape(item.fileUrl);
$out+='">';
$out+=$escape(item.fileName);
$out+='</a> </div> ';
}
$out+=' ';
});
$out+='  <div class="form-group"> <label>其他附件:</label> </div> ';
$each(files,function(item,$index){
$out+=' ';
if(item.mark === 3){
$out+=' <div class="file-box"> <a href="#" class=\'underline js-ToFile\' data-name="';
$out+=$escape(item.fileName);
$out+='" data-url="';
$out+=$escape(item.fileUrl);
$out+='">';
$out+=$escape(item.fileName);
$out+='</a> </div> ';
}
$out+=' ';
});
$out+=' </div> <style> .desc{ max-width: 1135px; } .di-box { float: left; width: 84px; height: 84px; margin-right: 20px; text-align: center; margin-bottom: 30px; } .di-box img { width: 100%; height: 100%; } .di-box a{ display: block; width: 84px; height: 84px; } .di-box a img { width: 100%; } .WW{ font-weight: 700; } </style>';
return new String($out);
});