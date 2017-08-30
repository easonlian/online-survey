create table question (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `section_id` int(10) unsigned not null default 0 comment '段落id',
    `sequence` int(4) unsigned not null default 0 comment '题目序号',
    `desc` varchar(60) not null default '' comment '题目描述',
    `type` int(2) unsigned not null default 0 comment '题目类型',
    `create_time` TIMESTAMP not null default CURRENT_TIMESTAMP comment '创建日期',
    `update_time` TIMESTAMP not null default CURRENT_TIMESTAMP comment '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_section_id_sequence` (`section_id`, `sequence`)
);