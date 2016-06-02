<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<div>
	협의
	<ul data-list="contract.provisions">
		<li data-content="name" contenteditable='true'></li>
	</ul>
</div>
<div data-content-append="duration" contenteditable="true">기간 : </div>
<div data-content-append="reward" contenteditable="true">금액 : </div>
<div>위약금 관련</div>
<div>퀘스터패널티 : <span data-content-prepend="contract.questerPenalty" contenteditable="true">%</span></div>
<div>리퀘스터패널티 : <span data-content-prepend="contract.requesterPenalty" contenteditable="true">%</span></div>
<div>
	수행해야 할 일(테이블로)
	<ul data-list="requirements">
		<li contenteditable='true'><span data-content="name"></span>|<span data-content="description"></span></li>
	</ul>
</div>
<div>협의 관련 게시판 또는 채팅 ajax로 가져오기. progress에서도 활용</div>
<button type="button" data-value="contract.id" class="btn btn-default" disabled onclick="agree(this.value)">협의완료</button>
<div>총 <span data-content="questers.length"></span>명 중에 <span data-content="contract.agreedUsers.length"></span>명 동의</div>
