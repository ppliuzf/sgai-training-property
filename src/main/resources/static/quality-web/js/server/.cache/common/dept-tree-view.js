/*TMODJS:{"version":37,"md5":"aff2b54bbef05f40c8d52d0c35c76f86"}*/
template('common/dept-tree-view',' <div class="dept-selector clearfix"> <div class="selector pull-left"> <div class="search"> <i class="glyphicon glyphicon-search"></i> <input type="text" name="" id="" class="form-control js-dept-search" placeholder="搜索人员" maxlength="15">  </div> <div class="path js-dept-path"> <div class="item"> <a href="javascript:" class="root" data-id="">通讯录</a> </div> </div> <div class="js-dept-select-all select-all"> <label><input type="checkbox" class="select-all-inp"> 全选</label> </div> <div class="content"> <ul class="js-dept-tree"></ul> <ul class="js-dept-search-result"></ul> </div> <div class="js-dept-cover cover"></div> </div> <div class="shower pull-right"> <div class="shower-content"> <div class="header">已选择</div> <ul class="js-dept-shower"></ul> </div> </div> </div>');