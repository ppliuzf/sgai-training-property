/*TMODJS:{"version":1,"md5":"d9a61209b9e305bd5ce33bda3845a923"}*/
template('archives-import-record',function($data,$filename
/*``*/) {
'use strict';var $utils=this,$helpers=$utils.$helpers,$each=$utils.$each,items=$data.items,item=$data.item,index=$data.index,$out='';$each(items,function(item,index){
$out+=' <tr> <th>序号</th> <th>导入时间</th> <th>导入人姓名</th> <th>文件名称</th> <th>导入状态</th> <th>导入结果</th> <th>操作</th> </tr> ';
});
return new String($out);
});