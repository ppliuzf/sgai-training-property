(function ($) {
    var $_GET = (function () {
        var url = window.document.location.href.toString();
        var u = url.split("?");
        if (typeof (u[1]) == "string") {
            u = u[1].split("&");
            var get = {};
            for (var i in u) {
                var j = u[i].split("=");
                get[j[0]] = j[1];
            }
            return get;
        }
        else {
            return {};
        }
    })();
    $('input').attr("autocomplete", "off");
    if ($_GET['token']) {
        localStorage.setItem('token', $_GET['token']);
    }
    // localStorage.setItem('token', 'eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1Mjk4ODg4MDQsInN1YiI6IntcInVzZXJUeXBlXCI6XCJJXCIsXCJjb21Db2RlXCI6XCJzazAwMVwiLFwidXNlck5hbWVcIjpcInNrdXNlcjEwXCIsXCJ1c2VySWRcIjpcInNrdXNlcjEwXCJ9IiwiZXhwIjoxNTI5OTMyMDA0fQ.WCh1VkgUcdbdbSecY3fRW4pWHWjvEpl6kf4tc6NlO_U');
    var Loading = function () {
        this.show();
    };
    Loading.prototype = {
        show: function () {
            var loading = $('<div id="loading"></div>');
            $('body').append(loading);
        },
        hide: function () {
            $('#loading').remove();
        }
    };
    var loading = function () {
        return new Loading();
    };
    $.loading = loading;

    // 全、反选11
    var SelectAll = function (config) {
        var settings = {
            selectAll: '.js-list-select-all',
            selectSingle: '.js-list-select-single',
            deleteBtn: '.js-del-multi',
            disabledClass: 'disabled'
        };
        this.opt = $.extend(settings, config);
        this.init();
    };
    SelectAll.prototype = {
        suiteChecked: function () {
            var checkedLength = $(this.opt.selectSingle + ':checked').length;
            $(this.opt.deleteBtn)[checkedLength ? 'removeClass' : 'addClass']('disabled');
            $(this.opt.selectAll).prop('checked', checkedLength >= this.listLength);
        },
        reset: function () {
            $(this.opt.deleteBtn).addClass(this.opt.disabledClass);
            $(this.opt.selectAll + ',' + this.opt.selectSingle).prop('checked', false);
        },
        init: function () {
            this.listLength = $(this.opt.selectSingle).length;
            var self = this;
            $(this.opt.selectAll).off('change').on('change', function () {
                $(self.opt.selectSingle).prop('checked', $(this).is(':checked'));
                self.suiteChecked();
            });
            $(this.opt.selectSingle).on('change', function () {
                $(this).is(':checked') || $(self.opt.selectAll).prop('checked', false);
                self.suiteChecked();
            });
        }
    };
    var selectAll = function () {
        return new SelectAll();
    };
    $.selectAll = selectAll;
    $.extend({
        /**
         * 获取数据（默认post）
         */
        getData: function (config, callback, error) {
            var setting = {
                showLoading: true,
                url: '',
                headers: {
                    'Accept': '*/*',
                    'Content-Type': 'application/json;charset=UTF-8',
                    'accessToken': 'PC_' + localStorage.getItem('token') || $.getCookie($.getCookie(projectName + '-code'))
                },
                type: 'post',
                query: '',
                body: null,
                async: true,
                crossDomain: true,
                success: function (data) {
                    if (data.code === '0') {
                        callback(data.data);
                    } else {
                        error ? error(data.message) : $.alert(data.message);
                    }
                },
                error: function () {
                    error ? error() : $.alert('服务器连接失败，请稍候再试');
                },
                complete: function () {
                    opt.showLoading && loading.hide();
                }
            };
            var opt = $.extend(setting, config);
            if (opt.showLoading) {
                var loading = $.loading();
            }
            if (!opt.url) {
                $.alert('参数配置有误，请检查');
            }
            if (opt.type === 'post' && opt.body) {
                opt.data = JSON.stringify(opt.body);
            }
            opt.url = (opt.url.indexOf('://') > -1 ? opt.url : serverUrl + opt.url) + '?' + $.param(opt.query || {});
            $.ajax(opt);
        },
        /**
         * 获取cookie
         */
        getCookie: function (key) {
            var cookie = document.cookie.split('; ');
            for (var i = 0; i < cookie.length; i++) {
                var _cookie = cookie[i].split('=');
                if (key === _cookie[0]) {
                    return unescape(_cookie[1]);
                }
            }
            return '';
        },
        /**
         * 设置cookie
         */
        setCookie: function (key, value, expires, path) {
            var cookie = key + '=' + escape(value);
            if (expires) {
                var date = new Date();
                date.setTime(date.getTime() + expires * 3600 * 1000);
                cookie += '; expires=' + date.toGMTString();
            }
            document.cookie = cookie + ';path=' + (path || '/');
        },
        /**
         * 获取地址栏参数
         */
        getQueryString: function (name) {
            var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)'),
                res = location.search.substr(1).match(reg);
            return res ? unescape(res[2]) : '';
        },
        /**
         * 转换时间戳
         */
        getTimeStamp: function (date, type) {
            var rs = new Date(date.replace(/-/g, '/')).getTime();
            if (type === 'end') {
                rs += 86399999; // 24小时的毫秒数
            }
            return date ? rs : '';
        },
        /**
         * 时间戳转时间
         */
        formatTime: function (time, format) {
            if (!time) {
                return '';
            }
            if (typeof time === 'string') {
                time = time.toString().replace(/-/g, '/'); // ios下new Date(2016-10-31)报错:invalid date...要改为: 2016/10/31样式
            }
            if (typeof time === 'number' && (time.toString().length) === 10) {
                time = parseInt(time + '000');
            }
            // 过去
            var stamp = new Date(time),
                cur = new Date(),
                year = stamp.getFullYear(),
                month = (stamp.getMonth() + 1) > 9 ? (stamp.getMonth() + 1) : '0' + (stamp.getMonth() + 1),
                day = stamp.getDate() > 9 ? stamp.getDate() : '0' + stamp.getDate(),
                hour = stamp.getHours() > 9 ? stamp.getHours() : '0' + stamp.getHours(),
                minute = stamp.getMinutes() > 9 ? stamp.getMinutes() : '0' + stamp.getMinutes(),
                sec = stamp.getSeconds() > 9 ? stamp.getSeconds() : '0' + stamp.getSeconds(),
                ms = stamp.getMilliseconds() < 100 ? '0' + (stamp.getMilliseconds() < 10 ? '0' + stamp.getMilliseconds() : stamp.getMilliseconds()) : stamp.getMilliseconds(),
                weeks = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                // weeks = ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                week = weeks[stamp.getDay()];
            if (format) {
                format = format.replace('yyyy', year);
                format = format.replace('MM', month);
                format = format.replace('dd', day);
                format = format.replace('hh', hour);
                format = format.replace('mm', minute);
                format = format.replace('ss', sec);
                format = format.replace('ms', ms);

                if (year === cur.getFullYear() && stamp.getMonth() === cur.getMonth() && stamp.getDate() === cur.getDate()) {
                    // week = '今天';
                }
                format = format.replace('week', week);
            } else {
                format = year + '-' + month + '-' + day + ' ' + hour + ':' + minute;
            }

            return format;
        },
        /**
         * 计数器
         */
        counter: function (config) {
            var settings = {
                el: '#textarea',
                count: '.js-count',
                max: 200
            },
                opt = $.extend(settings, config);
            $(opt.count).text($(opt.el).val().length + '/' + opt.max);
            $(opt.el).on('keydown keyup keypress input', function () {
                $(opt.count).text($(opt.el).val().length + '/' + opt.max);
            });
        },
        counterG: function (config) {
            var settings = {
                el: '#textarea',
                count: '.js-count',
                max: 200
            },
                opt = $.extend(settings, config);
            $(opt.count).text($(opt.el).val().length + '/' + opt.max);
            $(opt.el).on('keydown keyup keypress input', function () {
                $(this).siblings(opt.count).text($(this).val().length + '/' + opt.max);
            });
        },
        /**
         * msg
         */
        msg: function (content, config, callback) {
            var $msg = $('<div id="msg"><span><em>' + content + '</em></span></div>');
            $('body').append($msg);
            setTimeout(function () {
                $msg.remove();
                typeof (config) === 'function' ? config() : callback && callback();
            }, config ? (config.time || 1000) : 1000);
        },
        pop: function (config) {
            var settings = {
                    el: 'modal-pop',
                    ide: "#modal-pop",
                    title: '提示',
                    content: '',
                    sureText: '确认',
                    cancel:'取消',
                    height: "auto",
                    size: 'md', // sm, md, lg
                    isCancel: true,
                    noIcon: false,
                    cancelModel: true,
                    yesModel: true,
                    success: function () {
                    },
                    yes: function () {
                    },
                    cancels: function () {
                    }
                },
                opt = $.extend(settings, config);
            $('body').append(template('common/modal-pop', {
                el: opt.el,
                height: opt.height,
                title: opt.title,
                content: opt.content,
                sureText: opt.sureText,
                isCancel: opt.isCancel,
                size: opt.size,
                noIcon: opt.noIcon,
                cancel: opt.cancel,
                cancelModel: opt.cancelModel,
                yesModel: opt.yesModel
            }));
            opt.success();
            $(opt.ide).modal('show');
            $(opt.ide).on('hidden.bs.modal', function () {
                $(this).remove();
            });
            $('.js-modal-sure').on('click', opt.yes);
            $('.js-model-cancel').on('click', opt.cancels);
        },
        // pop: function (config) {
        //     var settings = {
        //         title: '提示',
        //         content: '',
        //         sureText: '确认',
        //         size: 'md',// sm, md, lg
        //         isCancel: true,
        //         noIcon: false,
        //         success: function() {},
        //         yes: function() {}
        //     },
        //         opt = $.extend(settings, config);
        //     $('body').append(template('common/modal-pop', {
        //         title: opt.title,
        //         content: opt.content,
        //         sureText: opt.sureText,
        //         isCancel: opt.isCancel,
        //         size: opt.size,
        //         noIcon: opt.noIcon
        //     }));
        //     opt.success();
        //     $('#modal-pop').modal('show');
        //     $('#modal-pop').on('hidden.bs.modal', function () {
        //         $(this).remove();
        //     });
        //     $('.js-modal-sure').on('click', opt.yes);
        // },
        alert: function (content, sure) {
            this.pop({
                size: 'sm',
                content: content,
                isCancel: false,
                yes: sure
            });
        },
        confirm: function (content, sure) {
            this.pop({
                size: 'sm',
                content: content,
                yes: sure
            });
        },
        renderPage: function (config) {
            var settings = {
                elem: '.pages',
                limit: 10,
                count: 0,
                curr: 1,
                groups: 5,
                jump: function () { }
            },
                opt = $.extend(settings, config);
            var pageTotal = Math.ceil(opt.count / opt.limit),
                pageNow = opt.curr;
            setBtns(true);
            $(opt.elem).off('click').on('click', '.item', function () {
                if ($(this).hasClass('active')) {
                    return;
                }
                pageNow = +$(this).text();
                setBtns();
            });
            $(opt.elem).on('click', '.first, .last', function () {
                if ($(this).hasClass('disabled')) {
                    return;
                }
                if ($(this).hasClass('first')) {
                    pageNow = 1;
                } else {
                    pageNow = pageTotal;
                }
                setBtns();
            });

            function setBtns(isFirst) {
                isFirst || opt.jump(pageNow);
                var pageNums = [],
                    area = Math.floor(opt.groups / 2);
                for (var i = -area; i <= area; i++) {
                    var _pageNum = pageNow + i;
                    if (_pageNum < 1) {
                        area += 1;
                        continue;
                    }
                    if (_pageNum > pageTotal) {
                        pageNums = [];
                        var startNum = pageTotal - opt.groups | 1;
                        for (var j = startNum < 1 ? 1 : startNum; j <= pageTotal; j++) {
                            pageNums.push(j);
                        }
                        break;
                    } else {
                        pageNums.push(_pageNum);
                    }
                }
                $(opt.elem).html(template('common/pages', {
                    items: pageNums,
                    count: opt.count,
                    pageSize: pageTotal
                }));
                setBtnStyle();
            }

            function setBtnStyle() {
                $(opt.elem).find('.first')[pageNow < 2 ? 'addClass' : 'removeClass']('disabled');
                $(opt.elem).find('.last')[pageNow >= pageTotal ? 'addClass' : 'removeClass']('disabled');
                $(opt.elem).find('[data-page=' + pageNow + ']').addClass('active').siblings().removeClass('active');
            }
        },
        weekSelector: function (config) {
            var settings = {
                el: '.week-selector',
                fillZero: false,
                curr: '',
                onselect: function () { }
            },
                opt = $.extend(settings, config);
            var today = opt.curr ? new Date(opt.curr.replace(/-/g, '/')) : new Date(),
                timeStamp = today.getTime(),
                flapIndex = 0,
                tempDay = [];

            function getPrev(n) {
                return timeStamp - 86400000 * n;
            }

            function getNext(n) {
                return firstDay + 86400000 * n;
            }

            function formatNumber(n) {
                if (opt.fillZero) {
                    return n < 10 ? '0' + n : n;
                } else {
                    return n;
                }
            }

            function flap(isPrev) {
                if (flapIndex > 7 || flapIndex < 0) {
                    flapIndex = 0;
                    return;
                }
                var index = isPrev ? 7 - flapIndex++ : flapIndex++,
                    el = $('li', opt.el).eq(index);
                el.addClass('flap');
                setTimeout(function () {
                    flap(isPrev);
                }, 50);
                setTimeout(function () {
                    el[selected[index] === selectedDate ? 'addClass' : 'removeClass']('current').find('.day').text(tempDay[index]);
                }, 250);
                setTimeout(function () {
                    el.removeClass('flap');
                }, 500);
            }
            var weekName = ['日', '一', '二', '三', '四', '五', '六'],
                currWeek = today.getDay(),
                firstDay = currWeek - 1 < 0 ? getPrev(6) : getPrev(currWeek - 1),
                items = [],
                selected = [],
                selectedDate = today.getTime();
            for (var j = 0; j < 7; j++) {
                var d = new Date(getNext(j));
                items.push({
                    day: formatNumber(d.getDate()),
                    week: weekName[d.getDay()]
                });
                selected.push(d.getTime());
            }
            $(opt.el).html(template('common/week-selector', {
                year: today.getFullYear(),
                month: formatNumber(today.getMonth() + 1),
                day: formatNumber(today.getDate()),
                items: items
            }));
            $('.prev, .next', opt.el).off('click').on('click', function () {
                firstDay += ($(this).hasClass('prev') ? -7 : 7) * 24 * 60 * 60 * 1000;
                tempDay = [];
                selected = [];
                for (var i = 0; i < 7; i++) {
                    var d = new Date(getNext(i));
                    tempDay.push(formatNumber(d.getDate()));
                    selected.push(d.getTime());
                }
                flap($(this).hasClass('prev'));
                var title = new Date(selected[3]);
                $('.title', opt.el).text(title.getFullYear() + '年' + formatNumber(title.getMonth() + 1) + '月');
                return false;
            });
            $('li', opt.el).off('click').on('click', function () {
                $(this).addClass('current').siblings().removeClass('current');
                selectedDate = selected[$(this).index()];
                opt.onselect(selectedDate);
            });
        },
        /**
         * 上传
         */
        uploader: function (config) {
            var setting = {
                url: '/uploadDown/uploadImages',
                el: '.js-uploader',
                default: [], // 默认图
                maxLength: 3, // 最大上传个数
                maxSize: 10240, // 最大大小，单位：kb
                maxSizeText: '您上传的图片大于10M，请重新选择！',
                tips: '注：最多上传{{maxLength}}张图片，<br>　　推荐尺寸1080*1080，图片大小不超过10M',
                imageGroup: 'default', // 图片分组，用于放大显示
                loadingClass: 'loading',
                isSetDefault: false, // 是否设置默认图
                type: 'image/jpg,image/jpeg,image/png,image/gif,image/bmp', // 上传的类型
                typeText: '您上传的图片类型不正确，请重新选择！',
                isInsertImage: true, // 上传完是否插入图片（可能上传的是文件）
                onDelete: function () { },
                onInsert: function () { },
                beforeUpload: function () { },
                afterUpload: function () { },
                onError: function () { }
            };
            var opt = $.extend(setting, config);
            $(opt.el).each(function () {
                var $this = $(this);
                // 渲染上传区域
                $this.html(template('common/uploader', {
                    tips: opt.tips.replace('{{maxLength}}', opt.maxLength),
                    type: opt.type
                }));
                $this.off();
                // 点击上传按钮
                $this.on('click', '.upload-btn', function () {
                    $('#upload', $this).click();
                });
                // 触发上传
                $this.on('change', '#upload', function () {
                    if ($(this).val() !== '') {
                        beforeUpload();
                    }
                });
                // 删除上传的图片
                $this.on('click', '.upload-close', function () {
                    var $item = $(this).parent();
                    $item.removeClass('show');
                    getImageCount();
                    setTimeout(function () {
                        $item.remove();
                        if (opt.isSetDefault) {
                            $('.upload-items', $this).find('.upload-item:first').addClass('is-default').siblings().removeClass('is-default');
                        }
                        opt.onDelete();
                    }, 1000);
                });
                // 有默认值时
                if (opt.default.length) {
                    for (var i in opt.default) {
                        insertImage(opt.default[i]);
                    }
                }
                var uploadLoading;
                // 传图前
                function beforeUpload() {
                    var file = $('#upload', $this)[0].files[0],
                        fd = new FormData();
                    fd.append('file', file);
                    if (!opt.type.split('/')[0].indexOf(file.type)) {
                        $.alert(opt.typeText);
                        return;
                    }
                    if (opt.maxSize * 1024 < file.size) {
                        $.alert(opt.maxSizeText);
                        return;
                    }
                    $('.upload-btn', $this).addClass(opt.loadingClass);
                    uploadLoading = $.loading();
                    opt.beforeUpload();
                    inUpload(fd);
                }
                // 传图中
                function inUpload(fd) {
                    $.ajax({
                        url: serverUrl + opt.url,
                        type: 'post',
                        headers: {
                            accessToken: localStorage.getItem("token")
                        },
                        data: fd,
                        cache: false,
                        contentType: false,
                        processData: false,
                        success: function (data) {
                            if (data.code === '0') {
                                if (data.data && opt.isInsertImage) {
                                    insertImage(data.data);
                                }
                                opt.afterUpload(data.data);
                            } else {
                                opt.onError(data.message);
                            }
                        },
                        error: function () {
                            opt.onError();
                        },
                        complete: function () {
                            $('.upload-btn', $this).removeClass(opt.loadingClass);
                            uploadLoading.hide();
                            $('#upload', $this).val('');
                        }
                    });
                }
                // 插入上传的图片
                function insertImage(url) {
                    var cont = template('common/uploader-item', {
                        url: url,
                        isSetDefault: opt.isSetDefault,
                        imageGroup: opt.imageGroup
                    });
                    $('.upload-items', $this).append(cont).find('.upload-item:last').addClass('show');
                    setTimeout(function () {
                        opt.onInsert();
                        getImageCount();
                    }, 20);
                    var img = new Image();
                    img.src = url;
                    img.onload = function () {
                        setTimeout(function () {
                            $('a[href="' + url + '"]', $this).parent().addClass('loaded');
                            if (opt.isSetDefault) {
                                $('.upload-items', $this).find('.upload-item:first').addClass('is-default').siblings().removeClass('is-default');
                            }
                        }, 600);
                    };
                }
                // 判断图片个数
                function getImageCount() {
                    $('.upload-btn', $this)[$('.upload-items', $this).find('.upload-item.show').length >= opt.maxLength ? 'addClass' : 'removeClass']('hide');
                }
            });
        },
        /**
         * 组织树选择
         */
        dept: function (config) {
            var setting = {
                el: '',
                name: '',
                type: 'all',
                placeholder: '',
                onlyDept: false,
                default: [], // 默认值，组织树callback的所有值
                deptUrl: '/orgTree/getSgaiOrgTreeById',
                searchUrl: '/orgTree/searchSgaiOrgTree'
            },
                opt = $.extend(setting, config);
            var items = '', emp = [], dept = [];
            if (opt.default.length) {
                for (var i in opt.default) {
                    items += '<span class="js-multi-select-item item" data-id="'+ opt.default[i].id +'">'+ opt.default[i].title +'<i class="js-multi-select-del del"></i></span>';
                    if (opt.default[i].isDept) {
                        dept.push(opt.default[i].id);
                    } else {
                        emp.push(opt.default[i].id);
                    }
                }
            }
            $(opt.el).html(template('common/dept-selector',{
                name: opt.name,
                value: items || opt.placeholder,
                emp: emp.join(','),
                dept: dept.join(','),
                all: opt.default.length ? JSON.stringify(opt.default) : ''
            }));
            var $selector = $('.js-'+ opt.name +'-selector', opt.el),
                $clean = $('.js-'+ opt.name +'-clean', opt.el),
                $dept = $('.js-'+ opt.name +'-dept', opt.el),
                $emp = $('.js-'+ opt.name +'-emp', opt.el),
                $all = $('.js-'+ opt.name +'-all', opt.el);

            if (emp.length || dept.length) {
                $clean.show();
            }
            // 载入组织树所需文件
            function loadDeptTree(callback) {
                $.ajax({
                    url: './js/server/deptTree.js',
                    dataType: 'script',
                    success: function () {
                        callback();
                    },
                    error: function () {
                        $.alert('组织树载入失败，请稍后再试');
                    }
                });
            }
            // 初始化组织树
            function initDeptTree() {
                deptTree.init({
                    type: opt.type,
                    deptUrl: opt.deptUrl,
                    searchUrl: opt.searchUrl,
                    selected: $all.val(),
                    callBack: function (data) {
                        renderDeptSelected(data);
                    }
                });
            }
            // 渲染选中的发布范围
            function renderDeptSelected(data) {
                $clean[data.complete.length ? 'show' : 'hide']();
                $selector.html(template('common/dept-tree-selected', {
                    items: data.complete
                }));
                $dept.val(data.deptId.join(','));
                $emp.val(data.empId.join(','));
                $all.val(data.complete.length ? JSON.stringify(data.complete) : '');
            }
            // 清空选中
            function cleanSelected() {
                $clean.hide();
                $selector.html('');
                $dept.val('');
                $emp.val('');
                $all.val('');
            }
            // 删除选中的
            function delDeptSelected(id) {
                // 全删
                if (!id) {
                    cleanSelected();
                    return;
                }
                // 单个删除
                var v = JSON.parse($all.val()), emp = $emp.val().split(','), dept = $dept.val().split(',');
                for (var i in v) {
                    if (+id === +v[i].id) {
                        v.splice(i, 1);
                        emp.splice(i, 1);
                        dept.splice(i, 1);
                        break;
                    }
                }
                if (v.length) {
                    $all.val(JSON.stringify(v));
                    $emp.val(emp.join(','));
                    $dept.val(dept.join(','));
                } else {
                    cleanSelected();
                }
            }
            // 点击弹出组织树
            $(document).on('click', '.js-'+ opt.name +'-selector, .js-'+ opt.name +'-search', function () {
                if (!window.deptTree) {
                    loadDeptTree(function () {
                        initDeptTree();
                    });
                    return;
                }
                initDeptTree();
            });
            // 全删选中的组织或人员
            $(document).on('click', '.js-'+ opt.name +'-clean', function () {
                delDeptSelected();
                return false;
            });
            // 单个删除选中的组织或人员
            $(document).on('click', '.js-multi-select-del', function () {
                $(this).parent().remove();
                delDeptSelected($(this).parent().data('id'));
                return false;
            });
            // 选中的项点击无效
            $(document).on('click', '.js-multi-select-item', function () {
                return false;
            });
        },
        /**
         * 即时搜索
         */
        instantSearch: function (config) {
            var setting = {
                el: '.js-instant-search',
                content: '.instant-search-content',
                searchInput: '#instant-keywords',
                selectedInput: '#instant-selected',
                loadingClass: 'loading',
                placeholder: '请输入姓名',
                default: {},
                url: '/orgTree /searchSgaiEmpInfoByName',
                delay: 500
            },
                opt = $.extend(setting, config);
            if (!opt.url) {
                $.alert('参数配置有误，请检查');
                return;
            }
            $(opt.el).each(function () {
                $(this).html(template('common/instant-search', {
                    placeholder: opt.placeholder,
                    approvalEmpName: opt.default.approvalEmpName,
                    approvalEmpId: opt.default.approvalEmpId
                }));
                var $this = $(this),
                    $inp = $(opt.searchInput, $this),
                    $val = $(opt.selectedInput, $this),
                    $content = $(opt.content, $this),
                    isInput = false, // 是否输入上屏了
                    inputT,
                    inputV = ''; // 防止输入中文不上屏却触发了事件
                $inp.off('keyup keydown keypress input').on('keyup keydown keypress input', function () {
                    $inp.removeClass(opt.loadingClass);
                    var v = $.trim($inp.val());
                    if (inputV === v) {
                        return;
                    }
                    inputV = v;
                    if ('' === v) {
                        $content.removeClass('show');
                        return;
                    }
                    clearTimeout(inputT);
                    inputT = setTimeout(function () {
                        if ('' === $.trim($inp.val())) {
                            return;
                        }
                        $inp.addClass(opt.loadingClass);
                        $.getData({
                            url: opt.url,
                            showLoading: false,
                            query: {
                                keyword: v
                            }
                        }, function (data) {
                            if (data) {
                                var _data = data;
                                _data.keywords = v;
                                $content.html(template('common/instant-search-dept', _data));
                                setTimeout(function () {
                                    $content.addClass('show');
                                }, 10);
                            } else {
                                $content.removeClass('show');
                            }
                            $inp.removeClass('loading');
                        }, function (data) {
                            $.alert(data);
                            $inp.removeClass('loading');
                        });
                    }, opt.delay);
                });
                $inp.off('blur').on('blur', function () {
                    if (!isInput) {
                        $val.val('');
                        $inp.val('');
                        return;
                    }
                    setTimeout(function () {
                        $inp.val($val.val() ? JSON.parse($val.val()).title : '');
                    }, 100);
                });
                // 上屏
                $content.off('click').on('click', 'li', function () {
                    $val.val(JSON.stringify({
                        id: $(this).prop('id'),
                        title: $(this).find('.user').text()
                    }));
                    isInput = true;
                    $inp.val($(this).find('.user').text()).focus();
                });
                $(document).off('mouseup').on('mouseup', function () {
                    $content.removeClass('show');
                    $(".instant-search-content").removeClass('show');
                });
            });
        },
        /**
         * 气泡提示
         */
        bubble: function (config) {
            var settings = {
                el: $('body'),
                msg: '请设置消息内容',
                cancel: function () { },
                ok: function () { }
            };
            var opt = $.extend(settings, config);
            $('#bubble').remove();
            $('body').append(template('common/bubble', {
                text: opt.msg
            }));
            var $bubble = $('#bubble');
            $bubble.css({
                left: opt.el.offset().left - $bubble.width() + 30,
                top: opt.el.offset().top - $bubble.height() - 10
            });
            $('.btn-cancel, .btn-ok', $bubble).one('click', function () {
                $(this).hasClass('btn-ok') ? opt.ok() : opt.cancel();
                $bubble.remove();
                return false;
            });
            $('body').one('click', function () {
                $('#bubble').remove();
            });
            $(window).one('resize', function () {
                $('#bubble').remove();
            });
        },
        /**
         * toast提示
         */
        toast: function (msg, config, callback) {
            var setting = {
                type: 'default', // success, error
                timer: 1000
            };
            var opt = $.extend(setting, config);
            var $toast = $('<div id="toast"></div>');
            $toast.append('<div class="toast-inner"><i class="icon-' + opt.type + '"></i><p>' + msg + '</p></div>').appendTo('body');
            setTimeout(function () {
                $toast.remove();
                typeof (config) === 'function' ? config() : callback && callback();
            }, opt.timer);
        }
    });
})(jQuery);
