<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<h3>협의</h3>
<div data-content-append="duration" data-model="quest" data-value="id" data-column="duration" contenteditable="true">기간 : </div>
<div data-content-append="reward" data-model="quest" data-value="id" data-column="reward" contenteditable="true">금액 : </div>
<div>위약금 관련</div>
<div>퀘스터패널티 : <span data-content="contract.questerPenalty" data-model="contract" data-value="contract.id" data-column="questerPenalty" contenteditable="true"></span>%</div>
<div>리퀘스터패널티 : <span data-content="contract.requesterPenalty" data-model="contract" data-value="contract.id" data-column="requesterPenalty" contenteditable="true"></span>%</div>
<hr>
<div>
	<p>조항</p>
	<div data-list="contract.provisions" class="list-group">
		<a data-content="name" data-model="provision" data-value="id" data-column="name" href="#" class="list-group-item" contenteditable="true"></a>
	</div>
</div>
<hr>
<div>
	<p>요구사항</p>
	<div data-list="requirements" class="list-group">
		<a href="#" class="list-group-item">
			<h5 data-content="name" data-model="requirement" data-value="id" data-column="name" contenteditable="true"></h5>
			<p data-content="description" data-model="requirement" data-value="id" data-column="description" contenteditable="true"></p>
		</a>
	</div>
</div>
<div>
	참여한 퀘스터들
	<ul data-list="questers">
		<li data-content="name"></span>
	</ul>
</div>
<div>협의 관련 게시판 또는 채팅 ajax로 가져오기. progress에서도 활용</div>
<button type="button" data-value="contract.id" class="btn btn-default" disabled onclick="agree(this.value)">협의완료</button>
<div>총 <span data-content="questers.length"></span>명 중에 <span data-content="contract.agreedUsers.length"></span>명 동의</div>