CREATE TABLE `t_wx_user_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid` varchar(55) DEFAULT NULL COMMENT '用户唯一标识',
  `third_session_key` varchar(40) DEFAULT '0' COMMENT '用户redis当中的key',
  `unionid` varchar(55) DEFAULT NULL COMMENT '用户在开放平台的唯一标识符(在法大大小程序里面的唯一标识)',
  `name` varchar(15) DEFAULT NULL COMMENT '用户姓名',
  `is_bind_mobile` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否绑定手机号(0否1是)',
  `mobile` varchar(15) DEFAULT NULL COMMENT '用户登录手机号码',
  `login_number` int(11) NOT NULL DEFAULT '0' COMMENT '登录次数',
  `login_time` datetime DEFAULT NULL COMMENT '最近一次登陆时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='微信小程序登录信息表';

CREATE TABLE `lg_product_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_name` varchar(32) DEFAULT NULL COMMENT '商品名称',
	product_id VARCHAR(32) DEFAULT NULL COMMENT '商品ID',
	product_number VARCHAR(32) DEFAULT NULL COMMENT '商品编号',
  `category_id` BIGINT(11) DEFAULT NULL COMMENT '商品分类ID',
  `click_num` int(11) NOT NULL DEFAULT '0' COMMENT '商品点击数',
	base_collect_num int(11) NOT NULL DEFAULT '100' COMMENT '收藏基数',
  `inventory_num` int(11) NOT NULL DEFAULT '0' COMMENT '商品库存数量',
  `market_price` DOUBLE(10,2) DEFAULT NULL COMMENT '市场价',
  `sale_price` DOUBLE(10,2) DEFAULT NULL COMMENT '售价',
  `introduce` varchar(55) DEFAULT NULL COMMENT '简介',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `small_pic` VARCHAR(50) DEFAULT NULL COMMENT '缩略图',
  `original_pic` VARCHAR(50) DEFAULT NULL COMMENT '原图',
  `big_pic` VARCHAR(50) DEFAULT NULL COMMENT '大图',
  `is_putaway` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '是否上架（0否1是）',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品信息';

CREATE TABLE `lg_product_category_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_name` varchar(32) DEFAULT NULL COMMENT '分类名称',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `parent_id` bigint(11) DEFAULT NULL COMMENT '父类 ID',
  `sort_id` int(5) NOT NULL DEFAULT '0' COMMENT '排序',
  `is_show` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '是否导航显示（0否1是）',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品分类信息';

CREATE TABLE `lg_product_car` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `open_id` varchar(55) DEFAULT NULL COMMENT '用户的openId',
  `product_id` VARCHAR(32) DEFAULT NULL COMMENT '商品ID',
  `product_name` VARCHAR(32) DEFAULT NULL COMMENT '商品名称',
  `amount` int(5) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `unit_price` DOUBLE(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品购买单价',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品购物车';

CREATE TABLE `lg_product_collect` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `open_id` varchar(55) DEFAULT NULL COMMENT '用户的openId',
  `product_id` VARCHAR(32) DEFAULT NULL COMMENT '商品ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商品收藏';
