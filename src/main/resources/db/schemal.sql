CREATE TABLE IF NOT EXISTS `user` (
    `id` INT(11)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` VARCHAR(255) NOT NULL DEFAULT '',
    `phone` VARCHAR(255) NOT NULL DEFAULT '',
    `password_hash` VARCHAR(100) DEFAULT '',
    `created_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `deleted` INT(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段',
    PRIMARY KEY (id),
    KEY ix_user_name (`name`),
    KEY ix_user_phone (`phone`)
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `wechat_identity` (
    `id` INT(11)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` INT(11) NOT NULL DEFAULT 0 COMMENT 'user id',
    `union_id` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '开放平台id',
    `nick_name` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '微信昵称',
    `avatar_url` VARCHAR(2083) NOT NULL DEFAULT '' COMMENT '头像地址',
    `gender` INT(1) NOT NULL DEFAULT 0 COMMENT '性别数字',
    `country` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '国家',
    `province` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '省',
    `language` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '语言',
    `created_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `deleted` INT(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段',
    PRIMARY KEY (id),
    KEY ix_wx_identity_user_id (`user_id`),
    KEY ix_wx_union_id (`union_id`),
    KEY ix_wx_nick_name (`nick_name`)
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `customer` (
    `id` INT(11)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` INT(11) NOT NULL DEFAULT 0 COMMENT 'user id',
    `created_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `updated_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `deleted` INT(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段',
    PRIMARY KEY (id),
    KEY ix_customer_user_id (`user_id`)
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `brand_manager` (
    `id` INT(11)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` INT NOT NULL  DEFAULT 0 COMMENT '管理员userId',
    `brand_id` INT NOT NULL  DEFAULT 0 COMMENT '品牌Id',
    `created_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    PRIMARY KEY (id),
    KEY ix_brand_manager_user_id (`user_id`),
    KEY ix_brand_manager_brand_id (`brand_id`)
    ) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `store_employee` (
    `id` INT(11)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` INT NOT NULL  DEFAULT 0 COMMENT '管理员userId',
    `store_id` INT NOT NULL  DEFAULT 0 COMMENT '门店id',
    `role` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '员工角色',
    `created_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    PRIMARY KEY (id),
    KEY ix_store_employee_store_id(`store_id`),
    KEY ix_store_employee_user_id(`user_id`),
    KEY ix_store_employee_roll(`role`)
    ) ENGINE=InnoDB;

-- time-zone issue reference
-- https://blog.csdn.net/CHS007chs/article/details/81348291
