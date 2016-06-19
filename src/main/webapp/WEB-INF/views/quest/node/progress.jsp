<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<style type="text/css">
/* Pie Chart */
.progress-pie-chart {
  width:200px;
  height: 200px;
  border-radius: 50%;
  background-color: #E5E5E5;
  position: relative;
}
.progress-pie-chart.gt-50 {
  background-color: #81CE97;
}

.ppc-progress {
  content: "";
  position: absolute;
  border-radius: 50%;
  left: calc(50% - 100px);
  top: calc(50% - 100px);
  width: 200px;
  height: 200px;
  clip: rect(0, 200px, 200px, 100px);
}
.ppc-progress .ppc-progress-fill {
  content: "";
  position: absolute;
  border-radius: 50%;
  left: calc(50% - 100px);
  top: calc(50% - 100px);
  width: 200px;
  height: 200px;
  clip: rect(0, 100px, 200px, 0);
  background: #81CE97;
  transform: rotate(60deg);
}
.gt-50 .ppc-progress {
  clip: rect(0, 100px, 200px, 0);
}
.gt-50 .ppc-progress .ppc-progress-fill {
  clip: rect(0, 200px, 200px, 100px);
  background: #E5E5E5;
}

.ppc-percents {
  content: "";
  position: absolute;
  border-radius: 50%;
  left: calc(50% - 173.91304px/2);
  top: calc(50% - 173.91304px/2);
  width: 173.91304px;
  height: 173.91304px;
  background: #fff;
  text-align: center;
  display: table;
}
.ppc-percents span {
  display: block;
  font-size: 2.6em;
  font-weight: bold;
  color: #81CE97;
}

.pcc-percents-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.progress-pie-chart {
  margin: 50px auto 0;
}
</style>
<div class="progress-pie-chart" data-percent="60"><!--Pie Chart -->
	<div class="ppc-progress">
		<div class="ppc-progress-fill"></div>
	</div>
	<div class="ppc-percents">
		<div class="pcc-percents-wrapper">
			<span>%</span>
		</div>
	</div>
</div><!--End Chart -->
<div class="progress">
	<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">60%</div>
</div>
<div>
	수행해야 할 일(테이블로). 체크박스
	<ul data-list="requirements">
		<li data-content-prepend="name" data-content-append="description"> / </li>
	</ul>
</div>
<div>
	업무 채팅 - 내용. 답변 형식. 이미지 첨부도 가능하게. 메신저처럼 이용가능하면 좋을듯. 알림도 감<br>
	업무 게시판 - 공지, 중요한 사항 올리기
</div>

<div class="issue-list"></div>

<button type="button" class="btn btn-default" disabled>업무완료</button>
<button type="button" class="btn btn-default" disabled>완료승인</button> - 평가

<script type="text/javascript">
$(function() {
	$("div.issue-list").load("/issue/list");

	var $ppc = $('.progress-pie-chart'),
		percent = parseInt($ppc.data('percent')),
		deg = 360*percent/100;
	if (percent > 50) {
		$ppc.addClass('gt-50');
	}
	$('.ppc-progress-fill').css('transform','rotate('+ deg +'deg)');
	$('.ppc-percents span').html(percent+'%');
});
</script>