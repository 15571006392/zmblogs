/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : zmblog

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2022-10-11 16:50:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_about`
-- ----------------------------
DROP TABLE IF EXISTS `t_about`;
CREATE TABLE `t_about` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `all_count` int(11) DEFAULT NULL,
  `register_user_count` int(11) DEFAULT NULL,
  `online_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_about
-- ----------------------------

-- ----------------------------
-- Table structure for `t_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `detail_id` bigint(20) DEFAULT NULL,
  `parent_comment_id` bigint(20) DEFAULT NULL,
  `admin_comment` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh6wr3psgnncfdi62idek9lj12` (`detail_id`),
  KEY `FK4jj284r3pb7japogvo6h72q95` (`parent_comment_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `t_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_detail`;
CREATE TABLE `t_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NOT NULL,
  `comment` bit(1) NOT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT NULL,
  `first_picture` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj0k9bo8cpd3hys3ka2ane9wis` (`type_id`),
  KEY `FKbuwn6fdbwphkgnuw9x9podllq` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_detail
-- ----------------------------
INSERT INTO `t_detail` VALUES ('1', '', '', '# v2.6.8 更新日志\r\n\r\n> **v2.6.1 以前的更新日志均在Git仓库提交统计中**\r\n\r\n# 更新内容\r\n\r\n## 功能更新\r\n\r\n1. 重构首页热门分类、标签、推荐博客dao层代码，使用Mybatis实现\r\n2. **使用Redis缓存热门分类、标签、推荐博客**\r\n    1. 分类新增修改删除时清空分类缓存\r\n    2. 标签新增修改删除时清空标签缓存\r\n    3. 博客新增修改删除时清空全部缓存\r\n    4. 全站博客修改删除时清空全部缓存\r\n\r\n3. **使用Redis缓存所有分类、标签**\r\n    1. 分类新增修改删除时清空分类缓存\r\n    2. 标签新增修改删除时清空标签缓存\r\n    3. 博客新增修改删除时清空分类、标签缓存\r\n    4. 全站博客修改删除时清空分类、标签缓存\r\n\r\n4. 完善后端代码注释\r\n5. 修复Redis缓存中Types和Tags属性blogCount序列化后存在重复属性BUG\r\n6. 删除RedisConfig中Spring Boot自带的缓存\r\n7. 新增后台博客编辑页、用户信息修改页权限校验\r\n8. 新增博客详情页草稿状态校验\r\n9. 修复注册用户未发布博客查询不到该用户BUG\r\n10. **使用Redis缓存博客归档页所有博客**\r\n    1. 博客新增修改删除时清空归档页博客缓存\r\n    2. 全站博客新增修改删除时清空归档页博客缓存\r\n\r\n11. **使用Redis缓存留言页最近留言**\r\n    1. 发布新留言时清空留言缓存\r\n    2. 删除留言时清空留言缓存\r\n\r\n12. 重构留言页dao层代码，用Mybatis实现\r\n12. **新增统计页dao、service、entity代码**\r\n13. 注册用户数量查询从用户信息mapper中迁移到博客网站统计页mapper\r\n14. **新增数据库表t_about，新增定时任务持久化博客网站统计页redis数据到mysql**\r\n15. 使用Redis缓存博客网站统计页信息 \r\n16. **新增Redis缓存工具类**\r\n17. 删除线程池工具类\r\n\r\n## 页面优化\r\n\r\n1. 修复留言页页脚位置显示错误\r\n\r\n2. 引入jquery.scrollTo.min.js、jquery-3.6.1.min.js、semantic.min.js、css到项目\r\n\r\n3. 移除前端页面cdn资源引入\r\n\r\n4. 修改谷歌字体库为fonts.loli.net\r\n\r\n5. 修改错误页页脚位置\r\n\r\n# 项目架构\r\n\r\n- Spring Boot\r\n    - Spring\r\n    - SpringMVC\r\n    - Hibernate\r\n        - JPA\r\n    - Mybatis\r\n        - alibaba druid\r\n    - Thymeleaf\r\n        - SemanticUI\r\n    - Docker\r\n        - Nginx\r\n        - MySQL\r\n        - Redis\r\n    - HuaweiCloud-OBS\r\n\r\n# 参与贡献\r\n\r\n1.  Zm_Mmm\r\n\r\n> [开源地址](https://gitee.com/zm_mmm/blog \"开源地址\")', '2022-10-11 16:22:00', 'https://img1.baidu.com/it/u=2578056587,3507178520&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=256', '原创', '', '', '', '博客网站v2.6.8更新日志', '2022-10-11 16:33:17', '8', '1', '1', 'v2.6.8 更新日志');

-- ----------------------------
-- Table structure for `t_detail_tags`
-- ----------------------------
DROP TABLE IF EXISTS `t_detail_tags`;
CREATE TABLE `t_detail_tags` (
  `details_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  KEY `FKdjn4j7v88jq7m8htbhiaarfni` (`tags_id`),
  KEY `FK4gctocyknrewcjs5vgvlgscpr` (`details_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_detail_tags
-- ----------------------------
INSERT INTO `t_detail_tags` VALUES ('1', '1');

-- ----------------------------
-- Table structure for `t_leaving_message`
-- ----------------------------
DROP TABLE IF EXISTS `t_leaving_message`;
CREATE TABLE `t_leaving_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `message` longtext,
  `ct` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=269 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_leaving_message
-- ----------------------------
INSERT INTO `t_leaving_message` VALUES ('267', 'admin', '10086@163.com', 'Test', '2022-10-11 16:36:42', 'https://img2.baidu.com/it/u=2327794482,132256549&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500');
INSERT INTO `t_leaving_message` VALUES ('268', '老八', '110@qq.com', '嗨害嗨', '2022-10-11 16:37:20', '/img/avatar.png');

-- ----------------------------
-- Table structure for `t_tag`
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES ('1', '开源项目更新日志');

-- ----------------------------
-- Table structure for `t_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('1', '开源项目');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'https://img2.baidu.com/it/u=2327794482,132256549&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500', '2022-10-11 16:06:38', '10086@163.com', 'admin', '21232f297a57a5a743894a0e4a801fc3', '2', '2022-10-11 16:29:27', 'admin');
