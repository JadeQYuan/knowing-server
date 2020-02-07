-- MySQL Workbench Synchronization
-- Generated: 2020-01-19 18:42
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Jade

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `knowing`.`blog_article`
    ADD COLUMN `special_id` BIGINT(20) NULL DEFAULT NULL COMMENT '专栏' AFTER `content`,
    ADD COLUMN `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' AFTER `special_id`,
    ADD COLUMN `create_user_id` BIGINT(20) NOT NULL COMMENT '创建用户' AFTER `create_time`,
    CHANGE COLUMN `title` `title` VARCHAR(30) NOT NULL COMMENT '标题' ,
    CHANGE COLUMN `content` `content` LONGTEXT NOT NULL COMMENT '内容' ,
COMMENT = '文章' ;

ALTER TABLE `knowing`.`blog_article_tag`
    ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
    ADD PRIMARY KEY (`id`),
COMMENT = '文章关联标签' ;

ALTER TABLE `knowing`.`blog_note`
    ADD COLUMN `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' AFTER `content`,
    ADD COLUMN `create_user_id` BIGINT(20) NOT NULL COMMENT '创建用户' AFTER `create_time`,
    CHANGE COLUMN `title` `title` VARCHAR(30) NOT NULL COMMENT '标题' ,
    CHANGE COLUMN `content` `content` VARCHAR(5000) NOT NULL COMMENT '内容' ,
COMMENT = '笔记' ;

ALTER TABLE `knowing`.`blog_tag`
    ADD COLUMN `category_id` BIGINT(20) NOT NULL COMMENT '分类id' AFTER `id`,
    ADD COLUMN `icon_url` VARCHAR(64) NULL DEFAULT NULL COMMENT '图标地址' AFTER `intro`,
    ADD COLUMN `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' AFTER `icon_url`,
    ADD COLUMN `create_user_id` BIGINT(20) NOT NULL COMMENT '创建用户' AFTER `create_time`,
    CHANGE COLUMN `name` `name` VARCHAR(15) NOT NULL COMMENT '名称' ,
    CHANGE COLUMN `description` `intro` VARCHAR(200) NOT NULL COMMENT '介绍' ,
    ADD INDEX `fk_blog_tag_blog_tag_category_idx` (`category_id` ASC),
COMMENT = '标签' ;

ALTER TABLE `knowing`.`blog_tag_category`
    DROP COLUMN `description`,
    ADD COLUMN `shared` TINYINT(1) NOT NULL COMMENT '是否公开' AFTER `name`,
    ADD COLUMN `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' AFTER `shared`,
    ADD COLUMN `create_user_id` BIGINT(20) NOT NULL COMMENT '创建用户' AFTER `create_time`,
    CHANGE COLUMN `name` `name` VARCHAR(32) NOT NULL COMMENT '名称' ,
COMMENT = '标签分类' ;

ALTER TABLE `knowing`.`sys_user`
    ADD COLUMN `email` VARCHAR(64) NULL DEFAULT NULL COMMENT '邮箱地址' AFTER `create_time`,
    ADD COLUMN `roles` VARCHAR(64) NOT NULL AFTER `email`,
    CHANGE COLUMN `nickname` `nickname` VARCHAR(20) NOT NULL COMMENT '昵称' ,
    CHANGE COLUMN `avatar_url` `avatar_url` VARCHAR(128) NOT NULL COMMENT '头像地址' ,
    CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
COMMENT = '用户' ;

ALTER TABLE `knowing`.`sys_user_oauth`
    CHANGE COLUMN `user_id` `user_id` BIGINT(20) NOT NULL COMMENT '用户' ,
    CHANGE COLUMN `platform` `platform` VARCHAR(20) NOT NULL COMMENT '平台' ,
    CHANGE COLUMN `open_id` `open_id` VARCHAR(64) NOT NULL COMMENT '第三方平台id' ,
COMMENT = '用户认证' ;

CREATE TABLE IF NOT EXISTS `knowing`.`blog_special` (
                                                        `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                        `name` VARCHAR(64) NOT NULL COMMENT '名称',
                                                        `intro` VARCHAR(255) NOT NULL COMMENT '介绍',
                                                        `shared` TINYINT(1) NOT NULL COMMENT '是否公开',
                                                        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                        `create_user_id` BIGINT(20) NOT NULL COMMENT '创建用户',
                                                        PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
COMMENT = '专栏';

ALTER TABLE `knowing`.`blog_tag`
    ADD CONSTRAINT `fk_blog_tag_blog_tag_category`
        FOREIGN KEY (`category_id`)
            REFERENCES `knowing`.`blog_tag_category` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
