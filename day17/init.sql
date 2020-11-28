CREATE TABLE `t_dictionary`  (
  `t_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键(自增)',
  `t_col_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称(逗号隔开)',
  `t_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `t_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典描述',
  `t_table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典表明',
  `t_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `t_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否启用(1:启用,0:禁用)',
  `t_order` int(11) NULL DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`t_id`) USING BTREE
) ;
CREATE TABLE `t_dictionary_detail`  (
  `t_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `t_did` bigint(20) NULL DEFAULT NULL COMMENT '关联t_dictionary',
  `col1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `col2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `col3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `col4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `col5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `t_state` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `t_create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;
