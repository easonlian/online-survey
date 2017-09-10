create table answer (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `questionnaire_id` int(4) unsigned not null default 0 comment '问卷id',
    `data` varchar(4096) not null default '' comment '答案数据',
    `user` varchar(60) not null default '' comment '受访人姓名',
    `tel` varchar(60) not null default '' comment '受访人电话',
    `create_time` TIMESTAMP not null default CURRENT_TIMESTAMP comment '创建日期',
    `update_time` TIMESTAMP not null default CURRENT_TIMESTAMP comment '最后更新时间',
    PRIMARY KEY (`id`)
);