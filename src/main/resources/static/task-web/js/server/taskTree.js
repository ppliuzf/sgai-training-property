/**
 * 组织树操作
 * Author: Kevin
 * selected --> 选中的项目，用于点击后渲染已选择项，来源于callBack返回的complete
 * type --> 类型。emp：只能搜索和选择员工；dept：只能搜索和选择部门；all：可以搜索和选择所有类型
 * callBack --> 返回选中的数据，具体参考:47
 */
var taskDeptTree = {
    init: function(config){
        this.opt = $.extend({
            selected: '',
            type: 'all', //emp, dept
            deptUrl: '', // 列表接口地址
            searchUrl: '', // 搜索接口地址
            // deptId: 0, // 可能进来不是0，所以给个这个参数
            isDisabled: true, // 是否有默认选中的，置灰
            size: [660, 558],
            resize: false,
            choiseLineId: '',
            callBack: function(){}
        }, config);
        var self = this, typeName = self.opt.type === 'all' ? '部门/人员' : self.opt.type === 'emp' ? '人员' : '部门';
        $.pop({
            title: '选择',
            area: [self.opt.size[0] +'px', self.opt.size[1]+ 'px'],
            resize: self.opt.resize,
            content: template('plan/task-tree-view', {
                typeName: typeName
            }),
            success: function(){
                self.renderSelected();
                self.act();
            },
            yes: function(index){
                var title = [],
                    empId = [],
                    nodeId = [],
                    deptId = [],
                    complete = [];
                $('.js-dept-shower li').each(function(){
                    var $this = $(this);
                    $this.data('group') ? deptId.push($this.data('id')) : empId.push($this.data('id'));
                    title.push($this.find('.title').text());
                    complete.push({
                        isDept: $this.data('group') ? true : false,
                        id: $this.data('id'),
                        title: $this.find('.title').text(),
                        avatar: $this.data('avatar'),
                        empNum: $this.data('number')
                    });
                });
                var callBackContent = {
                    empId: empId,
                    deptId: deptId,
                    title: title,
                    complete: complete
                };
                //配置项
                $('.link-set-word').html('');
                $('.link-set-word').html(template('plan/setting-word', {
                    id: 'rang',
                    items: complete
                  }));
                self.opt.callBack(callBackContent);
            }
        });
    },
    act: function(){
        var self = this;
        $('.dept-selector').off('click');
        $('.js-dept-search').off('input keyup');

        //根据左侧生成选中内容
        function getSelectValue($this){
            if($('input', $this).is(':checked') && !$('.js-dept-shower').find('[data-id='+ $this.data('id') +']').length){
                var _item = $('<li><i class="glyphicon glyphicon-'+ ($this.data('group') ? 'folder' : 'user') +'"></i> <span class="title">'+ $this.find('.title').text() +'</span><i class="glyphicon glyphicon-remove"></i></li>');
                _item.attr({
                    'data-id': $this.data('id'),
                    'data-avatar': $this.data('avatar'),
                    'data-number': $this.data('number')
                }); //data-id直接写到元素上append不出来，很奇怪的问题，测试了变量的方式、获取的方式都没问题，唯独这里有问题
                if($this.data('group')){
                    _item.attr('data-group', $this.data('group'));
                    if($('.js-dept-shower').find('[data-group]').length){
                        _item.insertAfter($('.js-dept-shower').find('[data-group]').last());
                    }else{
                        $('.js-dept-shower').prepend(_item);
                    }
                }else{
                    $('.js-dept-shower').append(_item);
                }
            }
            if(!$('input', $this).is(':checked')){
                $('.js-dept-shower').find('[data-id='+ $this.data('id') +']').remove();
            }
        }
        //根据选中内容对左侧进行勾选
        function getSelectedItems(){
            $('.js-dept-shower li').each(function(){
                $('.js-dept-tree, .js-dept-search-result').find('[data-id='+ $(this).data('id') +']').find('input').prop('checked', true).parent().find('.js-dept-inside').addClass('disabled');
            });
            isSelectAll();
        }
        //搜索
        function getDeptDataBySearch(keywords){
            $.getData({
                url: self.opt.searchUrl || '/orgDept/searchNode',
                query: {
                    keyword: keywords,
                    queryType: self.opt.type == 'dept' ? 0 : self.opt.type == 'emp' ? 1 : 2
                }
            }, function(data){
                renderDeptTree(data, true);
            });
        }
        //获取数据
        function getDeptData(config){
            $('.js-dept-cover').show();
            var query = config || {};
            // query.isIncludeEmp = self.opt.type === 'dept' ? 0 : 1;
            // query.id = '';
            $.getData({
                url: self.opt.deptUrl || '/task/getSpaceData',
                query: query
            }, function(data){
                var _data = [];
                for (var i in data) {
                    _data.push({
                    nodeName: data[i].name,
                    nodeId: data[i].id
                    });
                }
                renderDeptTree(_data);
            });
        }
        //是否是全选
        function isSelectAll(){
            var isSelectAll = true;
            var $el = $('.js-dept-tree').is(':visible') ? $('.js-dept-tree') : $('.js-dept-search-result');
            if(!$('input:not(:disabled)', $el).length){
                isSelectAll = false;
            }else{
                $('li', $el).each(function(){
                    if(!$('input', this).is(':checked') && !$('input', this).is(':disabled')){
                        isSelectAll = false;
                        return false;
                    }
                });
            }
            $('.js-dept-select-all input').prop('checked', isSelectAll ? true : false);
        }
        //渲染组织树列表
        function renderDeptTree(data, isSearch){
            var html = template('plan/dept-tree-list', {
                items: data,
                isSearch: isSearch,
                type: self.opt.type,
                isDisabled: self.opt.isDisabled
            });
            isSearch ? $('.js-dept-search-result').html(html).show().siblings().hide() : $('.js-dept-tree').html(html).show().siblings().hide();
            $('.js-dept-cover').hide();
            setTimeout(function () {
                $('.dept-selector .content').css({
                    top: 80
                });
            }, 200);
            getSelectedItems();
        }
        //全选/反选
        $('.dept-selector .select-all-inp').on('change', function(){
            var $el = $('.js-dept-tree').is(':visible') ? $('.js-dept-tree') : $('.js-dept-search-result'),
                $this = $(this);
            $('input', $el).each(function(){
                if($(this).is(':disabled')){
                    return;
                }
                if($this.is(':checked')){
                    $(this).prop('checked', true).parent().find('.js-dept-inside').addClass('disabled');
                }else{
                    $(this).prop('checked', false).parent().find('.js-dept-inside').removeClass('disabled');
                }
            });
            $('li', $el).each(function(){
                getSelectValue($(this));
            });
        });
        //单个选择
        $('.dept-selector').on('click', '.clicker', function(){
            var $par = $(this).parent(), $inp = $par.find('input');
            if($inp.is(':disabled')){
                return false;
            }
            if($inp.is(':checked')){
                $inp.prop('checked', false);
                $par.find('.js-dept-inside').removeClass('disabled');
            }else{
                $inp.prop('checked', true);
                $par.find('.js-dept-inside').addClass('disabled');
            }
            getSelectValue($par);
            isSelectAll();
            return false;
        });
        //下级
        $('.dept-selector').on('click', '.js-dept-inside', function(){
            if($(this).hasClass('disabled')){
                return false;
            }
            $('.js-dept-path').find('.item').last().find('a').removeClass('disabled').addClass('underline');
            $('.js-dept-path').append('<div class="item"><i class="glyphicon glyphicon-menu-right"></i><a class="disabled" data-id="'+ $(this).parent().data('id') +'">'+ $(this).parent().find('.title').text() +'</a></div>');
            getDeptData({
                // deptId: $(this).parent().data('id') // sl
            });
            return false;
        });
        //location点击
        $('.dept-selector').on('click', '.js-dept-path a', function(){
            var $this = $(this);
            if(!$this.hasClass('disabled')){
                $this.parent().nextAll('.item').remove();
                if(!$this.hasClass('root')){
                    $this.removeClass('underline').addClass('disabled');
                }
                getDeptData({
                    // deptId: $this.data('id') || self.opt.deptId // sl
                });
                return false;
            }
        });
        //右侧已选删除
        $('.dept-selector').on('click', '.glyphicon-remove', function(){
            var $par = $(this).parent();
            var $el = $('.js-dept-tree').is(':visible') ? $('.js-dept-tree') : $('.js-dept-search-result');
            $el.find('[data-id='+ $par.data('id') +']').find('input').prop('checked', false).parent().find('.js-dept-inside').removeClass('disabled');
            isSelectAll();
            $par.remove();
            return false;
        });
        //搜索
        var deptSerchT;
        $('.js-dept-search').on('input keyup', function(){
            clearTimeout(deptSerchT);
            var _val = $.trim($(this).val());
            if(_val !== ''){
                $('.js-dept-cover, .js-dept-search-cancel').show();
                deptSerchT = setTimeout(function() {
                    getDeptDataBySearch(_val);
                }, 300);
            }else{
                $('.js-dept-cover, .js-dept-search-cancel').hide();
                $('.js-dept-tree').show().siblings().hide();
                getSelectedItems();
                isSelectAll();
            }
        });
        //搜索叉掉
        $('.dept-selector').on('click', '.js-dept-search-cancel', function(){
            $('.js-dept-search').val('');
            $('.js-dept-cover, .js-dept-search-cancel').hide();
            $('.js-dept-tree').show().siblings().hide();
            getSelectedItems();
            isSelectAll();
        });
        getDeptData({
            // deptId: self.opt.deptId
        });
    },
    renderSelected: function(){
        var _selected = this.opt.selected ? JSON.parse(this.opt.selected) : '',
            cont = '';
        for(var i = 0, len = _selected.length; i < len; i++) {
            cont += '<li data-number="'+ _selected[i].empNum +'" data-avatar="'+ _selected[i].avatar +'" data-id="'+ _selected[i].id +'"'+ (_selected[i].isDept && ' data-group="1"') +'><i class="glyphicon glyphicon-'+ (_selected[i].isDept ? 'folder' : 'user') +'"></i> <span class="title">'+ _selected[i].title +'</span><i class="glyphicon glyphicon-remove"></i></li>';
        }
        $('.js-dept-shower').html(cont);
    }
    // 新加
};
