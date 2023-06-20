-- 테이블 생성 및 pk설정

CREATE TABLE `google_user` (
    `id`     BIGINT 	 NOT NULL COMMENT '유저 ID',
    `name`   VARCHAR(20) NOT NULL COMMENT '이름',
    `email`  VARCHAR(50) NOT NULL COMMENT '이메일',
    `google` VARCHAR(50) NOT NULL COMMENT '구글 토큰',
    `role`   VARCHAR(10) NOT NULL COMMENT '권한'
);

ALTER TABLE `google_user`
ADD CONSTRAINT `PK_GOOGLE_USER` PRIMARY KEY (`id`);

CREATE TABLE `email_user` (
    `id`       BIGINT 	   NOT NULL COMMENT '유저 ID',
    `name`     VARCHAR(20) NOT NULL COMMENT '이름',
    `email`    VARCHAR(50) NOT NULL COMMENT '이메일',
    `password` VARCHAR(20) NOT NULL COMMENT '비밀번호',
    `role`     VARCHAR(10) NOT NULL COMMENT '권한'
);

ALTER TABLE `email_user`
ADD CONSTRAINT `PK_EMAIL_USER` PRIMARY KEY (`id`);

CREATE TABLE `posts` (
    `id`      BIGINT 		NOT NULL COMMENT '게시물 ID',
    `title`   VARCHAR(20) 	NOT NULL COMMENT '제목',
    `content` VARCHAR(500) 	NOT NULL COMMENT '내용',
    `owner`   BIGINT 		NOT NULL COMMENT '작성자'
);

ALTER TABLE `posts`
ADD CONSTRAINT `PK_posts` PRIMARY KEY (`id`);

CREATE TABLE `comments` (
    `id`             BIGINT 		NOT NULL COMMENT '댓글 ID',
    `text`           VARCHAR(100) 	NOT NULL COMMENT '댓글 내용',
    `created_date`   VARCHAR(100) 	NOT NULL COMMENT '댓글 생성 시간',
    `modified_date`  VARCHAR(100) 	NULL 	 COMMENT '댓글 수정 시간',
    `owner`          BIGINT 		NOT NULL COMMENT '작성자',
    `posts_id`       BIGINT 		NOT NULL COMMENT '게시물 ID'
);

ALTER TABLE `comments`
ADD CONSTRAINT `PK_comments` PRIMARY KEY (`id`);

-- view생성

CREATE VIEW `user` AS
SELECT `id`, `name`, `email`, `password`, `role`
FROM email_user
UNION ALL
SELECT `id`, `name`, `email`, `google` AS `password`, `role`
FROM google_user;