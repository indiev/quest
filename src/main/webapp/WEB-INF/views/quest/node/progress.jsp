<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
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

<button type="button" class="btn btn-default" disabled>업무완료</button>
<button type="button" class="btn btn-default" disabled>완료승인</button> - 평가