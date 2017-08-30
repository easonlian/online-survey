create table questionnaire (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `title` varchar(50) not null default '' comment '标题',
    `quarter` int(2) not null default 0 comment '季度',
    `year` int(5) not null default 0 comment '年度',
    `serial_num` varchar(50) not null default '' comment '表号',
    `office` varchar(50) not null default '' comment '制表单位',
    `doc_num` varchar(50) not null default '' comment '文号',
    `desc` varchar(200) not null default '' comment '说明json',
    `create_time` TIMESTAMP not null default CURRENT_TIMESTAMP comment '创建日期',
    `update_time` TIMESTAMP not null default CURRENT_TIMESTAMP comment '最后更新时间',
    PRIMARY KEY (`id`)
);