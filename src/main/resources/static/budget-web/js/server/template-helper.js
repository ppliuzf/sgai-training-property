//过滤html
function removeHTMLTag(str) {
	str = str.replace(/<\/?[^>]*>/g, ''); //去除HTML tag
	str = str.replace(/[ | ]*\n/g, '\n'); //去除行尾空白
	//str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
	str = str.replace(/ /ig, ''); //去掉
	return str;
}
//时间戳格式化
template.helper('dateFormat', function(date, format) {
	if (date) {
		date = new Date(parseInt(date));
		var map = {
			'M': date.getMonth() + 1, //月
			'd': date.getDate(), //日
			'h': date.getHours(), //时
			'm': date.getMinutes(), //分
			's': date.getSeconds(), //秒
			'q': Math.floor((date.getMonth() + 3) / 3), //季度
			'S': date.getMilliseconds(), //毫秒
			'D': date.getDay()
		};
		format = format.replace(/([yMdhmsqSD])+/g, function(all, t) {
			var v = map[t];
			if (v !== undefined) {
				if (t == 'D') {
					switch (v) {
						case 1:
							v = '一';
							break;
						case 2:
							v = '二';
							break;
						case 3:
							v = '三';
							break;
						case 4:
							v = '四';
							break;
						case 5:
							v = '五';
							break;
						case 6:
							v = '六';
							break;
						case 0:
							v = '日';
							break;
					}
				} else {
					if (all.length > 1) {
						v = '0' + v;
						v = v.substr(v.length - 2);
					}
				}
				return v;
			} else if (t === 'y') {
				return (date.getFullYear() + '').substr(4 - all.length);
			}
			return all;
		});
		return format;
	}
	return '';
});
//图片转换大小
template.helper('formatImgUrl', function(url, size){
	var _url = url.split(/.png|.jpg|.jpeg|.bmp/), _size = size || 100;
	return _url[0]+'_'+ _size +'_'+ _size +'_80_1.png';
});
// 首个图片获取
template.helper('getFirstImg', function (cont, char) {
	return cont.split(char || ' | ')[0];
});