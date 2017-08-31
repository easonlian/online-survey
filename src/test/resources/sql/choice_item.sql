create table choice_item (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `question_id` int(10) unsigned not null default 0 comment '题目id',
    `sequence` int(4) unsigned not null default 0 comment '题目序号',
    `desc` varchar(60) not null default '' comment '题目描述',
    `create_time` TIMESTAMP not null default CURRENT_TIMESTAMP comment '创建日期',
    `update_time` TIMESTAMP not null default CURRENT_TIMESTAMP comment '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_question_id_sequence` (`question_id`, `sequence`)
);