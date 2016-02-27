INSERT INTO customer(firstname, lastname, signupdate) values ('Juergen','Hoeller', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Mark','Fisher', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Rod','Johnson', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('David','Syer', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Gunnar','Hillert', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Dave','McCrory', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Josh','Long', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Patrick','Chanezon', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Andy','Piper', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Eric','Bottard', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Chris','Richardson', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Raja','Rao', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Rajdeep','Dua', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Monica','Wilkinson', NOW() );
INSERT INTO customer(firstname, lastname, signupdate) values ('Mark','Pollack', NOW() );

INSERT INTO Server(name, host, description, createDate, modifyDate) values ('sever1','110.45.144.55', '설명123', NOW(), NOW());
INSERT INTO Server(name, host, description, createDate, modifyDate) values ('sever2','2.1.1.1', '설명2123', NOW(), NOW());
INSERT INTO Server(name, host, description, createDate, modifyDate) values ('sever3','1.3.1.1', '설명3123', NOW(), NOW());
INSERT INTO Server(name, host, description, createDate, modifyDate) values ('배포 서버','10.222.223.56', '배포 처리를 하는 서버 입니다.', NOW(), NOW());
INSERT INTO Server(name, host, description, createDate, modifyDate) values ('스테이지 서버','10.222.223.55', '배포하기 이전에 테스트를 하는 서버입니다.', NOW(), NOW());

INSERT INTO Project(name, description, repositoryURL, repositoryPath, repositoryType, groupId, artifactId, createDate, modifyDate) values ('프로젝트1','이 프로젝트는 12345', '110.45.144.55', '/deploy', 'SVN', 'com.gabia.deployment', 'deployment', NOW(), NOW());
INSERT INTO Project(name, description, repositoryURL, repositoryPath, repositoryType, groupId, artifactId, createDate, modifyDate) values ('도메인.com','도메인 웹페이지 서비스입니다.', 'svn.gabia.com', 'domain.gabia.com', 'SVN', 'domain.gabia.com', 'www',NOW(), NOW());
INSERT INTO Project(name, description, repositoryURL, repositoryPath, repositoryType, groupId, artifactId, createDate, modifyDate) values ('프로젝트3','이 프로젝트는 312345', 'http://3.1.12.3/a', '/a1df', 'SVN', 'com.gabia.hosting', 'www',NOW(), NOW());
INSERT INTO Project(name, description, repositoryURL, repositoryPath, repositoryType, groupId, artifactId, createDate, modifyDate) values ('프로젝트4','이 프로젝트는 412345', 'svn://4.1.12.3/a', '/ad2f', 'SVN', 'com.gabia.hosting', 'www2',NOW(), NOW());
INSERT INTO Project(name, description, repositoryURL, repositoryPath, repositoryType, groupId, artifactId, createDate, modifyDate) values ('프로젝트5','이 프로젝트는 512345', 'svn://5.1.12.3/a', '/ad3f', 'SVN', 'com.gabia.www', 'www',NOW(), NOW());

INSERT INTO ProjectServer(projectId, serverId, name, description, type, path, createDate, modifyDate) values(1, 1, 'a', 'abc', 'R', '/home/deploy2', now(), now());
INSERT INTO ProjectServer(projectId, serverId, name, description, type, path, createDate, modifyDate) values(1, 2, 'b', 'abc', 'T', '/b', now(), now());
INSERT INTO ProjectServer(projectId, serverId, name, description, type, path, createDate, modifyDate) values(2, 4, '도메인 배포서버', '도메인1111', 'R', '/home/domain', now(), now());
INSERT INTO ProjectServer(projectId, serverId, name, description, type, path, createDate, modifyDate) values(2, 4, '도메인2 배포서버', '도메인2222', 'R', '/home/domain2', now(), now());
INSERT INTO ProjectServer(projectId, serverId, name, description, type, path, createDate, modifyDate) values(2, 5, '도메인 테스트서버', '도메인 ttttt', 'T', '/home/domain', now(), now());
INSERT INTO ProjectServer(projectId, serverId, name, description, type, path, createDate, modifyDate) values(2, 5, '도메인2 테스트서버', '도메인2 tttt', 'T', '/home/domain2', now(), now());
INSERT INTO ProjectServer(projectId, serverId, name, description, type, path, createDate, modifyDate) values(2, 1, '123123', '도메인2 tttt', 'T', '/home/domain2', now(), now());

INSERT INTO Version(groupId, artifactId, versionId, copyRevision, name, author, state, createDate, modifyDate) values('com.gabia.deployment', 'deployment', '1.0', 1, '1.0', 'ehnawh', 'R', now(), now());
INSERT INTO Version(groupId, artifactId, versionId, copyRevision, name, author, state, createDate, modifyDate) values('com.gabia.deployment', 'deployment', '1.1', 4, '1.1', 'ehnawh', 'T', now(), now());
INSERT INTO Version(groupId, artifactId, versionId, copyRevision, name, author, state, createDate, modifyDate) values('com.gabia.deployment', 'deployment', '1.2', 5, '1.2', 'ehnawh', 'S', now(), now());
INSERT INTO Version(groupId, artifactId, versionId, copyRevision, name, author, state, createDate, modifyDate) values('com.gabia.deployment', 'deployment', '1.6', 44, '1.6', 'ehnawh', 'R', now(), now());
INSERT INTO Version(groupId, artifactId, versionId, copyRevision, name, author, state, createDate, modifyDate) values('domain.gabia.com', 'www', '1.0', 3, '1.0', 'ehnawh', 'B', now(), now());
INSERT INTO Version(groupId, artifactId, versionId, copyRevision, name, author, state, createDate, modifyDate) values('domain.gabia.com', 'www', '1.1', 4, '1.1', 'ehnawh', 'R', now(), now());
INSERT INTO Version(groupId, artifactId, versionId, copyRevision, name, author, state, createDate, modifyDate) values('domain.gabia.com', 'www', '2.0', 1223, '2.0', 'lhh', 'S', now(), now());

INSERT INTO History(versionId, projectServerId, type, result, runLog, errorLog, createDate, modifyDate) values(1, 1, 'D', 'S', 'abcd', 'err', now(), now());
INSERT INTO History(versionId, projectServerId, type, result, runLog, errorLog, createDate, modifyDate) values(1, 1, 'B', 'F', 'abcd', 'err', now(), now());