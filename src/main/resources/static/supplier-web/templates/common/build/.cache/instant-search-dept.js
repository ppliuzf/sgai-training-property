/*TMODJS:{"version":1,"md5":"7619cc3b0eed1be4e0047b736946fcc9"}*/
template('instant-search-dept',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,list=$data.list,item=$data.item,i=$data.i,$escape=$utils.$escape,total=$data.total,$out='';$out+='<div class="inner"> <ul> ';
$each(list,function(item,i){
$out+=' ';
if(i < 5){
$out+=' <li data-id="';
$out+=$escape(item.eiId);
$out+='" data-no="';
$out+=$escape(item.eiEmpNo);
$out+='"> <div class="user dot">';
$out+=$escape(item.eiEmpName);
$out+='</div> <div class="dept dot">';
$out+=$escape(item.pathDeptName);
$out+='</div> </li> ';
}
$out+=' ';
});
$out+=' </ul> ';
if(total > 5){
$out+=' <div class="more">合计';
$out+=$escape(total);
$out+='条搜索结果，请输入更完整的信息</div> ';
}
$out+=' </div>';
return new String($out);
});