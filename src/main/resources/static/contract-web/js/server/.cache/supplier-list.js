/*TMODJS:{"version":62,"md5":"beccec0716aa8f0a98e1fdf4647c0772"}*/
template('supplier-list','<div class="supplierss"> <div class="supplier-left"> <div class="supplier-pop"> <div class="filter"> <div class="form-inline"> <div class="form-group"> <label for="supplier-name">供应商名称</label> <input type="text" name="" id="supplier-name" class="form-control"> </div> <div class="form-group"> <button type="submit" class="btn btn-primary js-supplier-search">查询</button> <button type="submit" class="btn btn-primary js-supplier-reset">重置</button> </div> </div> </div> <table class="table table-bordered table-hover table-list"> <thead> <tr> <th class="text-center" width="48"><input type="checkbox" class="js-supplier-all" name="allChecked"></th> <th class="text-center" width="60">序号</th> <th class="text-center">供应商</th> </tr> </thead> <tbody class="js-s-list"> </tbody> </table> <nav class="text-right pages"></nav> </div> </div> <div class="supplier-right"> <div class="content-header">已选择</div> <div class="content-right"> </div> </div> </div> <style> .supplierss{ display: flex; margin: 5px 0px -40px 0px; } .supplier-left{ width: 70%; } .supplier-right { margin-left: 20px; width: 30%; border: 1px solid #eaedf1; max-height:510px; margin-bottom: 30px; overflow: auto; } .content-header{ height: 34px; line-height: 34px; padding-left: 20px; border-bottom: 1px solid #eaedf1; } </style>');