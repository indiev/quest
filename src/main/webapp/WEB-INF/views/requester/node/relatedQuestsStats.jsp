<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div class="container-fluid quester-questTotalStat-node">
	<div><h3>발주한 전체 퀘스트 정보 관련 통계</h3></div>
	<div>평균 종합 만족도:4.6/5</div>
	<div>수행한 퀘스트 수:3</div>
	<div>퀘스트에서 습득한 보상 금액:1,500,000 원</div>	
	<div>수행한 분야 순위 차트</div>
	<div>수행한 업무 순위 차트</div>
	<div>수행한 스킬 순위 차트</div>
	
	<ul data-list="areas">
		<li><span data-content="name"></span> : <span data-content="count"></span>번 수행.</li>
	</ul>
	
	<ul data-list="works">
		<li><span data-content="name"></span> : <span data-content="count"></span>번 수행.</li>
	</ul>
	
	<ul data-list="skills">
		<li><span data-content="name"></span> : <span data-content="count"></span>번 수행.</li>
	</ul>
	
</div>
