CREATE TABLE `job_task_param` (
  `param_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '参数ID',
  `task_id` bigint(20) NOT NULL COMMENT '任务ID',
  `param_name` varchar(255) NOT NULL COMMENT '参数名称',
  `param_value` varchar(255) NOT NULL COMMENT '参数值',
  `OBJECT_VERSION_NUMBER` decimal(20,0) DEFAULT '1',
  `REQUEST_ID` bigint(20) DEFAULT '-1',
  `PROGRAM_ID` bigint(20) DEFAULT '-1',
  `CREATED_BY` bigint(20) DEFAULT '-1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` bigint(20) DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` bigint(20) DEFAULT '-1',
  PRIMARY KEY (`param_id`,`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10264 DEFAULT CHARSET=utf8 COMMENT='任务参数表'