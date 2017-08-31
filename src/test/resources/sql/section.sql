create table section (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `questionnaire_id` int(10) unsigned not null default 0 comment '问卷id',
    `sequence` int(4) unsigned not null default 0 comment '段落序号',
    `name` varchar(60) not null default '' comment '段落标题',
    `create_time` TIMESTAMP not null default CURRENT_TIMESTAMP comment '创建日期',
    `update_time` TIMESTAMP not null default CURRENT_TIMESTAMP comment '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_questionnaire_id_sequence` (`questionnaire_id`, `sequence`)
);