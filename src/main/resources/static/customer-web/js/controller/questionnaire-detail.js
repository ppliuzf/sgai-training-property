$(function () {
	var id = $.getQueryString('id');

	function getDetail() {
		$.getData({
			url: '/survey/getSurveyDetail',
			query: {
				id: id,
			}
		}, function (data) {
			if (data) {
				data.surveyQuestionVOs.forEach(function (item) {
					if (!item.soContent) return;
					var newContent = item.soContent.split('@@');
					var len = newContent.length;
					newContent.splice(len - 1, 1);
					item.newContent = newContent;
				});
				renderDetail(data);
			}
		});
	}

	// 渲染详情
	function renderDetail(data) {
		$('.js-detail').html(template('questionnaire-detail', data));
	}


	function init() {
		getDetail();
		$(document).on('click', '.btn-go', function () {
			window.history.back();
		})
	}

	init();
});