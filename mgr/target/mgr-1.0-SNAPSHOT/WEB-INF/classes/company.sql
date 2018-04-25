
/*
1.	登录管理
2.	员工信息管理
3.	权限管理
4.	薪资管理
5.	考核管理
6.	员工培训管理
7.	部门和职位管理
8.	后勤管理（统计生活生产用品使用、库存情况）
9.	事程进度管理
10.	利润统计
company_profit.html
 */
CREATE TABLE `te_profit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `principal` NUMERIC(20,2) NOT NULL COMMENT '本金',
  `type` int(4) DEFAULT '1' COMMENT '收入支出类型，1：收入，2：支出',
  `income` NUMERIC(20,2) DEFAULT null comment '收支',
  `profit` NUMERIC(20,2) DEFAULT null comment '利润',
  `all_income` NUMERIC(20,2) DEFAULT null comment '总收入',
  `all_expense` NUMERIC(20,2) DEFAULT null comment '总支出',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;