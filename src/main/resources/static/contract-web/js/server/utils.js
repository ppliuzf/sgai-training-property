(function ($) {
    var $_GET = (function () {
        var url = window.document.location.href.toString();
        var u = url.split("?");
        if (typeof (u[1]) ==  "string") {
            u = u[1].split("&");
            var get = {};
            for (var i  in u) {
                var j = u[i].split("=");
                get[j[0]] = j[1];
            }
            return get;
        }
        else {
            return {};
        }
    })();
    if ($_GET['token']) {
        localStorage.setItem('token', $_GET['token']);
    }
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
    // 全、反选
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
                    //'accessToken':'PC_eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MzE3MjQ2OTgsInN1YiI6IntcImNvbU5hbWVcIjpcIueZvem5remhueebrue7hFwiLFwidXNlclR5cGVcIjpcIklcIixcImNvbUNvZGVcIjpcImJhaWx1XCIsXCJ1c2VyTmFtZVwiOlwi5byg5L-K5riFXCIsXCJlbUNvZGVcIjpcInpoYW5nanVucWluXCIsXCJ1c2VySWRcIjpcInVzZXIwMVwiLFwiZGVwdENvZGVcIjpcImhvdXRhaVwifSIsImV4cCI6MTUzMTc2Nzg5OH0.mLddf_rD7E3Llt0KElBsVqWkICLL8KDESZJAwMSRWqQ'
                    'accessToken': 'PC_' + localStorage.getItem('token')
                },
                type: 'post',
                query: '',
                body: null,
                async: true,
                crossDomain: true,
                success: function (data) {
                    if (data.code === '0') {
                        callback(data.data, data);
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
            // opt.url = serverUrl + opt.url + '?' + $.param(opt.query || {});
            opt.url = (opt.url.indexOf('://') > -1 ? opt.url : serverUrl + opt.url) +'?'+ $.param(opt.query || {});
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
                    title: '提示',
                    content: '',
                    sureText: '确认',
                    size: 'md',// sm, md, lg
                    isCancel: true,
                    noIcon: false,
                    success: function () { },
                    yes: function () { }
                },
                opt = $.extend(settings, config);
            $('body').append(template('common/modal-pop', {
                title: opt.title,
                content: opt.content,
                sureText: opt.sureText,
                isCancel: opt.isCancel,
                size: opt.size,
                noIcon: opt.noIcon
            }));
            opt.success();
            $('#modal-pop').modal('show');
            $('#modal-pop').on('hidden.bs.modal', function () {
                $(this).remove();
            });
            $('.js-modal-sure').on('click', opt.yes);
        },
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
            var pageTotal = Math.ceil(opt.count / opt.limit), pageNow = opt.curr;
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
                onDelete: function() {},
                onInsert: function() {},
                beforeUpload: function() {},
                afterUpload: function() {},
                onError: function() {}
            };
            var opt = $.extend(setting, config);
            $(opt.el).each(function() {
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
                    setTimeout(function() {
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
                        url:url,
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
                            $('a[href="'+ url +'"]', $this).parent().addClass('loaded');
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
        uploadFile: function (config) {
            var setting = {
                url: '/uploadDown/uploadFile',
                el: '',
                fileName: '',
                default: [],
                type: '',
                title: '',
                btnTips: '',
                tips: '',
                maxLength: 100,
                isRequire: false,
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
                $this.html(template('common/uploaderFile', {
                    tips: opt.tips,
                    title: opt.title,
                    btnTips: opt.btnTips,
                    isRequire: opt.isRequire,
                    maxLength: opt.maxLength
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
                // 删除上传的附件
                $this.on('click', '.upload-close', function () {
                    var $item = $(this).parent();
                    $item.removeClass('show');
                    setTimeout(function () {
                        $item.remove();
                        opt.onDelete($item);
                    }, 20);
                    if (opt.maxLength !== 1) {
                        $('.upload-btn_wrap', $this)[$('.upload-file-items', $this).find('.upload-item').length >= opt.maxLength ? 'addClass' : 'removeClass']('hide');
                    } else {
                        $('.upload-btn_wrap', $this).removeClass('hide');
                    }
                });
                // 有默认值时
                if (opt.default.length) {
                    for (var i in opt.default) {
                        insertFile(opt.default[i]);
                    }
                }
                // 上传前
                var uploadLoading;
                function beforeUpload() {
                    $('.js-save').attr('disabled', "disabled");
                    var file = $('#upload', $this)[0].files[0],
                        fd = new FormData();
                    fd.append('file', file);
                    opt.fileName = file.name;
                    uploadLoading = $.loading();
                    opt.beforeUpload();
                    inUpload(fd);
                }
                // 上传中
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
                            $('.js-save').removeAttr("disabled");
                            if (data.code === '0' || data.code === '-1') {
                                if (data.data) {
                                    insertFile({ fileName: opt.fileName, fileUrl: data.data});
                                } else {
                                    $.alert('文件上传失败，请重新上传！');
                                }
                                opt.afterUpload();
                            } else {
                                opt.onError(data.data);
                            }
                        },
                        error: function () {
                            $('.js-save').removeAttr("disabled");
                            opt.onError();
                        },
                        complete: function () {
                            uploadLoading.hide();
                            $('#upload', $this).val('');
                        }
                    });
                }
                // 插入上传的图片
                function insertFile(fileInfo) {
                    var cont = template('common/uploadFileItem', fileInfo);
                    $('.upload-file-items', $this).append(cont);
                    setTimeout(function () {
                        getFileCount();
                        opt.onInsert();
                        $('.upload-file-items', $this).find('.upload-item').addClass('show')
                    }, 20);
                }
                // 判断个数
                function getFileCount() {
                    $('.upload-btn_wrap', $this)[$('.upload-file-items', $this).find('.upload-item').length >= opt.maxLength ? 'addClass' : 'removeClass']('hide');
                }
            });
        },
        /**
         * 气泡提示
         */
        bubble: function (config) {
            var settings = {
                el: $('body'),
                msg: '请设置消息内容',
                cancel: function() {},
                ok: function() {}
            };
            var opt = $.extend(settings, config);
            $('#bubble').remove();
            $('body').append(template('common/bubble',{
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
        toast: function (msg, config) {
            var setting = {
                type: 'default', // success, error
                timer: 1000
            };
            var opt = $.extend(setting, config);
            var $toast = $('<div id="toast"></div>');
            $toast.append('<div class="toast-inner"><i class="icon-'+ opt.type +'"></i><p>'+ msg +'</p></div>').appendTo('body');
            setTimeout(function () {
                $toast.remove();
            }, opt.timer);
        }
    });
})(jQuery);
