DROP TABLE IF EXISTS `chat_room`;

CREATE TABLE `chat_room` (
	`no`	BIGINT	NOT NULL    AUTO_INCREMENT PRIMARY KEY	COMMENT 'PK',
	`id`	VARCHAR(128)	NOT NULL	COMMENT 'UK',
	`name`	VARCHAR(100)	NOT NULL,
	`session_id`	VARCHAR(128)	NOT NULL,
	`user_count`	BIGINT	NOT NULL	DEFAULT 0,
	`created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '등록일자',
	`updated_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '수정일자'
);

