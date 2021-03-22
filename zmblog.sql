/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50733
Source Host           : localhost:3306
Source Database       : zmblog

Target Server Type    : MYSQL
Target Server Version : 50733
File Encoding         : 65001

Date: 2021-03-22 16:12:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('199');
INSERT INTO `hibernate_sequence` VALUES ('199');
INSERT INTO `hibernate_sequence` VALUES ('199');
INSERT INTO `hibernate_sequence` VALUES ('199');
INSERT INTO `hibernate_sequence` VALUES ('199');

-- ----------------------------
-- Table structure for `t_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL,
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
INSERT INTO `t_comment` VALUES ('183', '/img/avatar.png', '1', '2021-03-22 14:31:04', '1456133139@qq.com', '1', '174', null, '');
INSERT INTO `t_comment` VALUES ('184', '/img/avatar.png', '?', '2021-03-22 14:31:29', '1456133139@qq.com', '1', '174', '183', '');
INSERT INTO `t_comment` VALUES ('185', '/img/avatar.png', '?', '2021-03-22 14:31:30', '1456133139@qq.com', '1', '174', '184', '');
INSERT INTO `t_comment` VALUES ('186', '/img/avatar.png', '?', '2021-03-22 14:31:35', '1456133139@qq.com', '123', '174', '185', '');
INSERT INTO `t_comment` VALUES ('187', '/img/avatar.png', '123123123', '2021-03-22 14:31:43', '1456133139@qq.com', '10086', '174', '183', '');
INSERT INTO `t_comment` VALUES ('188', '/img/avatar.png', '110', '2021-03-22 14:31:47', '1456133139@qq.com', '110', '174', '187', '');
INSERT INTO `t_comment` VALUES ('189', 'https://pic.qqtn.com/up/2017-11/2017112216362233724.jpg', 'hjh', '2021-03-22 14:36:33', '1456133139@qq.com', 'Zm-Mmm', '174', null, '');
INSERT INTO `t_comment` VALUES ('190', 'https://pic.qqtn.com/up/2017-11/2017112216362233724.jpg', 'abab', '2021-03-22 14:37:49', '1456133139@qq.com', 'Zm-Mmm', '174', '183', '');
INSERT INTO `t_comment` VALUES ('191', '/img/avatar.png', '111111111111111111111111111', '2021-03-22 14:49:55', '1456133139@qq.com', '我不懂', '174', null, '');
INSERT INTO `t_comment` VALUES ('192', '/img/avatar.png', '？', '2021-03-22 14:50:06', '1456133139@qq.com', '我不懂', '174', '189', '');
INSERT INTO `t_comment` VALUES ('193', '/img/avatar.png', '66666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666', '2021-03-22 15:57:31', '1941565808@qq.com', '王八蛋', '174', null, '');
INSERT INTO `t_comment` VALUES ('194', '/img/avatar.png', '你在咋', '2021-03-22 15:59:38', 'jianer2021@126.com', '毛', '174', '190', '');
INSERT INTO `t_comment` VALUES ('195', '/img/avatar.png', '1', '2021-03-22 16:03:46', '1456133139@qq.com', '1', '176', null, '');

-- ----------------------------
-- Table structure for `t_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_detail`;
CREATE TABLE `t_detail` (
  `id` bigint(20) NOT NULL,
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_detail
-- ----------------------------
INSERT INTO `t_detail` VALUES ('94', '', '', '```java\r\npackage com.util;\r\n\r\nimport java.security.MessageDigest;\r\nimport java.security.NoSuchAlgorithmException;\r\n\r\npublic class MessageDigestAlgorithm {\r\n\r\n    public static String code(String str){\r\n        try {\r\n            MessageDigest md = MessageDigest.getInstance(\"MessageDigestAlgorithm\");\r\n            md.update(str.getBytes());\r\n            byte[]byteDigest = md.digest();\r\n            int i;\r\n            StringBuffer buf = new StringBuffer(\"\");\r\n            for (int offset = 0; offset < byteDigest.length; offset++) {\r\n                i = byteDigest[offset];\r\n                if (i < 0)\r\n                    i += 256;\r\n                if (i < 16)\r\n                    buf.append(\"0\");\r\n                buf.append(Integer.toHexString(i));\r\n            }\r\n            //32位加密\r\n            return buf.toString();\r\n            // 16位的加密\r\n            //return buf.toString().substring(8, 24);\r\n        } catch (NoSuchAlgorithmException e) {\r\n            e.printStackTrace();\r\n            return null;\r\n        }\r\n    }\r\n}\r\n\r\n```', '2020-07-07 14:30:28', 'https://p0.ssl.img.360kuai.com/t01d316080036698774.webp', '原创', '', '', '', 'MD5加密源码', '2020-07-10 17:58:37', '53', '140', '1', '在开发过程中，避免不了要涉及到数据加密，比如用户账号密码的加密，用户敏感数据的加密，涉及到的加密算法种类繁多，作为拿来主义的开发者时间精力有限，能够清楚其中主流的加密算法和用途，就已经足够了。');
INSERT INTO `t_detail` VALUES ('127', '', '', '```java\r\npackage com.util;\r\n\r\nimport org.commonmark.Extension;\r\nimport org.commonmark.ext.gfm.tables.TableBlock;\r\nimport org.commonmark.ext.gfm.tables.TablesExtension;\r\nimport org.commonmark.ext.heading.anchor.HeadingAnchorExtension;\r\nimport org.commonmark.node.Link;\r\nimport org.commonmark.node.Node;\r\nimport org.commonmark.parser.Parser;\r\nimport org.commonmark.renderer.html.AttributeProvider;\r\nimport org.commonmark.renderer.html.AttributeProviderContext;\r\nimport org.commonmark.renderer.html.AttributeProviderFactory;\r\nimport org.commonmark.renderer.html.HtmlRenderer;\r\n\r\nimport java.util.*;\r\n\r\npublic class Markdown {\r\n\r\n    /**\r\n     * markdown格式转换成HTML格式\r\n     * @param markdown\r\n     * @return\r\n     */\r\n    public static String markdownToHtml(String markdown) {\r\n        Parser parser = Parser.builder().build();\r\n        Node document = parser.parse(markdown);\r\n        HtmlRenderer renderer = HtmlRenderer.builder().build();\r\n        return renderer.render(document);\r\n    }\r\n\r\n    /**\r\n     * 增加扩展[标题锚点，表格生成]\r\n     * Markdown转换成HTML\r\n     * @param markdown\r\n     * @return\r\n     */\r\n    public static String markdownToHtmlExtensions(String markdown) {\r\n        //h标题生成id\r\n        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());\r\n        //转换table的HTML\r\n        List<Extension> tableExtension = Arrays.asList(TablesExtension.create());\r\n        Parser parser = Parser.builder()\r\n                .extensions(tableExtension)\r\n                .build();\r\n        Node document = parser.parse(markdown);\r\n        HtmlRenderer renderer = HtmlRenderer.builder()\r\n                .extensions(headingAnchorExtensions)\r\n                .extensions(tableExtension)\r\n                .attributeProviderFactory(new AttributeProviderFactory() {\r\n                    public AttributeProvider create(AttributeProviderContext context) {\r\n                        return new CustomAttributeProvider();\r\n                    }\r\n                })\r\n                .build();\r\n        return renderer.render(document);\r\n    }\r\n\r\n    /**\r\n     * 处理标签的属性\r\n     */\r\n    static class CustomAttributeProvider implements AttributeProvider {\r\n        @Override\r\n        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {\r\n            //改变a标签的target属性为_blank\r\n            if (node instanceof Link) {\r\n                attributes.put(\"target\", \"_blank\");\r\n            }\r\n            if (node instanceof TableBlock) {\r\n                attributes.put(\"class\", \"ui celled table\");\r\n            }\r\n        }\r\n    }\r\n}\r\n```', '2020-07-08 16:19:51', 'https://pic1.zhimg.com/80/v2-07153671b36b2d0e8b8ade5163f3fb00_720w.png', '转载', '', '', '', 'MarkDown格式转换', '2020-07-09 23:06:16', '78', '140', '1', 'Markdown是一种可以使用普通文本编辑器编写的标记语言，通过简单的标记语法，它可以使普通文本内容具有一定的格式。\r\n\r\nMarkdown具有一系列衍生版本，用于扩展Markdown的功能(如表格、脚注、内嵌HTML等等)');
INSERT INTO `t_detail` VALUES ('174', '', '', '### 1. 长度限制\r\n```javascript\r\n<script language=\"javascript\">\r\n	function test() {\r\n		if (document.a.b.value.length > 50) {\r\n			alert(\"不能超过50个字符！\");\r\n			document.a.b.focus();\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 2. 只能是汉字\r\n```javascript\r\n<script language=\"javascript\">\r\n	function isChinese(obj) {\r\n		var reg = /^[\\u0391-\\uFFE5]+$/;\r\n		if (obj != \"\" && !reg.test(obj)) {\r\n			alert(\'必须输入中文！\');\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 3. 只能是英文字母\r\n```javascript\r\n<script type=\"text/javascript\">\r\n	//验证只能是字母\r\n	function checkZm(zm) {\r\n		var zmReg = /^[a-zA-Z]*$/;\r\n		if (zm != \"\" && !zmReg.test(zm)) {\r\n			alert(\"只能是英文字母！\");\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 4. 只能是数字\r\n```javascript\r\n<script language=javascript>\r\n	//验证只能为数字 \r\n	function checkNumber(obj) {\r\n		var reg = /^[0-9]+$/;\r\n		if (obj != \"\" && !reg.test(obj)) {\r\n			alert(\'只能输入数字！\');\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 5. 只能是英文字母和数字\r\n```javascript\r\n<script type=\"text/javascript\">\r\n	//验证只能是字母和数字 \r\n	function checkZmOrNum(zmnum) {\r\n		var zmnumReg = /^[0-9a-zA-Z]*$/;\r\n		if (zmnum != \"\" && !zmnumReg.test(zmnum)) {\r\n			alert(\"只能输入是字母或者数字,请重新输入\");\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 6. 检验时间大小(与当前时间比较)\r\n```javascript\r\n<script type=\"text/javascript\">\r\n	//检验时间大小(与当前时间比较) \r\n	function checkDate(obj) {\r\n		var obj_value = obj.replace(/-/g, \"/\"); //替换字符，变成标准格式(检验格式为：\'2009-12-10\') \r\n		// var obj_value=obj.replace(\"-\",\"/\");//替换字符，变成标准格式(检验格式为：\'2010-12-10 11:12\') \r\n		var date1 = new Date(Date.parse(obj_value));\r\n		var date2 = new Date(); //取今天的日期 \r\n		if (date1 > date2) {\r\n			alert(\"不能大于当前时间！\");\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 7. 屏蔽关键字(这里屏蔽\\*\\*\\*和\\*\\*\\*\\*)\r\n```javascript\r\n<script type=\"text/javascript\">\r\n	function test(obj) {\r\n		if ((obj.indexOf(\"***\") == 0) || (obj.indexOf(\"****\") == 0)) {\r\n			alert(\"屏蔽关键字(这里屏蔽***和****)！\");\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 8. 两次输入密码是否相同\r\n```javascript\r\n<script type=\"text/javascript\">\r\n	function check() {\r\n		with(document.all) {\r\n			if (input1.value != input2.value) {\r\n				alert(\"密码不一致\")\r\n				input1.value = \"\";\r\n				input2.value = \"\";\r\n			} else {\r\n				alert(\"密码一致\");\r\n				document.forms[0].submit();\r\n			}\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 9. 表单项不能为空\r\n```javascript\r\n<script language=\"javascript\">\r\n	function CheckForm(obj) {\r\n		if (obj.length == 0) {\r\n			alert(\"姓名不能为空!\");\r\n			return false;\r\n		}\r\n		return true;\r\n		alert(\"姓名不能为空!\");\r\n	}\r\n</script>\r\n```\r\n### 10. 邮箱验证\r\n```javascript\r\n<script language=\"javascript\">\r\n	function test(obj) {\r\n		//对电子邮件的验证\r\n		var myreg = /^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$/;\r\n		if (!myreg.test(obj)) {\r\n			alert(\'请输入有效的邮箱！\');\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 11. 验证手机号\r\n```javascript\r\n<script type=\"text/javascript\">\r\n	function validatemobile(mobile) {\r\n		if (mobile.length == 0) {\r\n			alert(\'手机号码不能为空！\');\r\n			return false;\r\n		}\r\n		if (mobile.length != 11) {\r\n			alert(\'请输入有效的手机号码，需是11位！\');\r\n			return false;\r\n		}\r\n\r\n		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$/;\r\n		if (!myreg.test(mobile)) {\r\n			alert(\'请输入有效的手机号码！\');\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 12. 验证身份证号码（需是有效身份证）\r\n```javascript\r\n		<script type=\"text/javascript\">\r\n			// 构造函数，变量为15位或者18位的身份证号码\r\n			function clsIDCard(CardNo) {\r\n				this.Valid = false;\r\n				this.ID15 = \'\';\r\n				this.ID18 = \'\';\r\n				this.Local = \'\';\r\n				if (CardNo != null) this.SetCardNo(CardNo);\r\n			}\r\n\r\n			// 设置身份证号码，15位或者18位\r\n			clsIDCard.prototype.SetCardNo = function(CardNo) {\r\n				this.ID15 = \'\';\r\n				this.ID18 = \'\';\r\n				this.Local = \'\';\r\n				CardNo = CardNo.replace(\" \", \"\");\r\n				var strCardNo;\r\n				if (CardNo.length == 18) {\r\n					pattern = /^\\d{17}(\\d|x|X)$/;\r\n					if (pattern.exec(CardNo) == null) return;\r\n					strCardNo = CardNo.toUpperCase();\r\n				} else {\r\n					pattern = /^\\d{15}$/;\r\n					if (pattern.exec(CardNo) == null) return;\r\n					strCardNo = CardNo.substr(0, 6) + \'19\' + CardNo.substr(6, 9)\r\n					strCardNo += this.GetVCode(strCardNo);\r\n				}\r\n				this.Valid = this.CheckValid(strCardNo);\r\n			}\r\n\r\n			// 校验身份证有效性\r\n			clsIDCard.prototype.IsValid = function() {\r\n				return this.Valid;\r\n			}\r\n\r\n			// 返回生日字符串，格式如下，1981-10-10\r\n			clsIDCard.prototype.GetBirthDate = function() {\r\n				var BirthDate = \'\';\r\n				if (this.Valid) BirthDate = this.GetBirthYear() + \'-\' + this.GetBirthMonth() + \'-\' + this.GetBirthDay();\r\n				return BirthDate;\r\n			}\r\n\r\n			// 返回生日中的年，格式如下，1981\r\n			clsIDCard.prototype.GetBirthYear = function() {\r\n				var BirthYear = \'\';\r\n				if (this.Valid) BirthYear = this.ID18.substr(6, 4);\r\n				return BirthYear;\r\n			}\r\n\r\n			// 返回生日中的月，格式如下，10\r\n			clsIDCard.prototype.GetBirthMonth = function() {\r\n				var BirthMonth = \'\';\r\n				if (this.Valid) BirthMonth = this.ID18.substr(10, 2);\r\n				if (BirthMonth.charAt(0) == \'0\') BirthMonth = BirthMonth.charAt(1);\r\n				return BirthMonth;\r\n			}\r\n\r\n			// 返回生日中的日，格式如下，10\r\n			clsIDCard.prototype.GetBirthDay = function() {\r\n				var BirthDay = \'\';\r\n				if (this.Valid) BirthDay = this.ID18.substr(12, 2);\r\n				return BirthDay;\r\n			}\r\n\r\n			// 返回性别，1：男，0：女\r\n			clsIDCard.prototype.GetSex = function() {\r\n				var Sex = \'\';\r\n				if (this.Valid) Sex = this.ID18.charAt(16) % 2;\r\n				return Sex;\r\n			}\r\n\r\n			// 返回15位身份证号码\r\n			clsIDCard.prototype.Get15 = function() {\r\n				var ID15 = \'\';\r\n				if (this.Valid) ID15 = this.ID15;\r\n				return ID15;\r\n			}\r\n\r\n			// 返回18位身份证号码\r\n			clsIDCard.prototype.Get18 = function() {\r\n				var ID18 = \'\';\r\n				if (this.Valid) ID18 = this.ID18;\r\n				return ID18;\r\n			}\r\n\r\n			// 返回所在省，例如：上海市、浙江省\r\n			clsIDCard.prototype.GetLocal = function() {\r\n				var Local = \'\';\r\n				if (this.Valid) Local = this.Local;\r\n				return Local;\r\n			}\r\n\r\n			clsIDCard.prototype.GetVCode = function(CardNo17) {\r\n				var Wi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);\r\n				var Ai = new Array(\'1\', \'0\', \'X\', \'9\', \'8\', \'7\', \'6\', \'5\', \'4\', \'3\', \'2\');\r\n				var cardNoSum = 0;\r\n				for (var i = 0; i < CardNo17.length; i++) cardNoSum += CardNo17.charAt(i) * Wi[i];\r\n				var seq = cardNoSum % 11;\r\n				return Ai[seq];\r\n			}\r\n\r\n			clsIDCard.prototype.CheckValid = function(CardNo18) {\r\n				if (this.GetVCode(CardNo18.substr(0, 17)) != CardNo18.charAt(17)) return false;\r\n				if (!this.IsDate(CardNo18.substr(6, 8))) return false;\r\n				var aCity = {\r\n					11: \"北京\",\r\n					12: \"天津\",\r\n					13: \"河北\",\r\n					14: \"山西\",\r\n					15: \"内蒙古\",\r\n					21: \"辽宁\",\r\n					22: \"吉林\",\r\n					23: \"黑龙江 \",\r\n					31: \"上海\",\r\n					32: \"江苏\",\r\n					33: \"浙江\",\r\n					34: \"安徽\",\r\n					35: \"福建\",\r\n					36: \"江西\",\r\n					37: \"山东\",\r\n					41: \"河南\",\r\n					42: \"湖北 \",\r\n					43: \"湖南\",\r\n					44: \"广东\",\r\n					45: \"广西\",\r\n					46: \"海南\",\r\n					50: \"重庆\",\r\n					51: \"四川\",\r\n					52: \"贵州\",\r\n					53: \"云南\",\r\n					54: \"西藏 \",\r\n					61: \"陕西\",\r\n					62: \"甘肃\",\r\n					63: \"青海\",\r\n					64: \"宁夏\",\r\n					65: \"新疆\",\r\n					71: \"台湾\",\r\n					81: \"香港\",\r\n					82: \"澳门\",\r\n					91: \"国外\"\r\n				};\r\n				if (aCity[parseInt(CardNo18.substr(0, 2))] == null) return false;\r\n				this.ID18 = CardNo18;\r\n				this.ID15 = CardNo18.substr(0, 6) + CardNo18.substr(8, 9);\r\n				this.Local = aCity[parseInt(CardNo18.substr(0, 2))];\r\n				return true;\r\n			}\r\n\r\n			clsIDCard.prototype.IsDate = function(strDate) {\r\n				var r = strDate.match(/^(\\d{1,4})(\\d{1,2})(\\d{1,2})$/);\r\n				if (r == null) return false;\r\n				var d = new Date(r[1], r[2] - 1, r[3]);\r\n				return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[2] && d.getDate() == r[3]);\r\n			}\r\n\r\n\r\n			function valiIdCard(idCard) {\r\n				var checkFlag = new clsIDCard(idCard);\r\n				if (!checkFlag.IsValid()) {\r\n					alert(\"输入的身份证号无效,请输入真实的身份证号！\");\r\n					document.getElementByIdx(\"idCard\").focus();\r\n					return false;\r\n				} else {\r\n					alert(\"是有效身份证！\");\r\n				}\r\n			}\r\n		</script>\r\n```\r\n#### 转载自:[https://blog.csdn.net/qq_27628085/article/details/81198962](https://blog.csdn.net/qq_27628085/article/details/81198962)', '2020-07-07 16:04:49', 'https://img.mukewang.com/5b0f543d00010eb708000450.png', '转载', '', '', '', 'JS表单验证-12个常用的JS表单验证', '2020-07-15 14:08:08', '83', '141', '1', '12种常用JS表单验证，部分使用正则表达式。包含常用邮箱验证、身份证验证、手机号验证、密码组合验证等等');
INSERT INTO `t_detail` VALUES ('176', '', '', '## 获取IP地址\r\n在开发工作中,我们常常需要获取客户端的IP\r\n### 1、nginx配置\r\n为了拿到真实的IP地址,避免代理等错误源,需要在nginx配置\r\n```nginx\r\nlocation / {\r\n            # 指向当前的某个服务端口(根据服务单独java打包jar开启对应的服务)\r\n            proxy_pass   http://127.0.0.1:端口号;\r\n​\r\n            # 这里配置java获取客户端真实的ip地址(避免因为nginx反向代理造成ip 127.0.0.1等其他错误现象)\r\n            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;\r\n            set $Real $http_x_forwarded_for;\r\n            if ( $Real ~ (\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+),(.*) ){\r\n                set $Real $1.$2.$3.$4;\r\n            }\r\n            proxy_set_header X-Real-Ip $Real;\r\n        }\r\n```\r\n### 2、java API 获取\r\n```java\r\n//获取ip地址\r\n    @GetMapping(value = \"/getIp\")\r\n    @ResponseBody\r\n    private String getIpAddr(HttpServletRequest request) {\r\n        String ip = request.getHeader(\"x-forwarded-for\");\r\n        System.out.println(\"x-forwarded-for ip: \" + ip);\r\n        if (ip != null && ip.length() != 0 && !\"unknown\".equalsIgnoreCase(ip)) {\r\n            // 多次反向代理后会有多个ip值，第一个ip才是真实ip\r\n            if (ip.indexOf(\",\") != -1) {\r\n                ip = ip.split(\",\")[0];\r\n            }\r\n        }\r\n        if (ip == null || ip.length() == 0 || \"unknown\".equalsIgnoreCase(ip)) {\r\n            ip = request.getHeader(\"Proxy-Client-IP\");\r\n            System.out.println(\"Proxy-Client-IP ip: \" + ip);\r\n        }\r\n        if (ip == null || ip.length() == 0 || \"unknown\".equalsIgnoreCase(ip)) {\r\n            ip = request.getHeader(\"WL-Proxy-Client-IP\");\r\n            System.out.println(\"WL-Proxy-Client-IP ip: \" + ip);\r\n        }\r\n        if (ip == null || ip.length() == 0 || \"unknown\".equalsIgnoreCase(ip)) {\r\n            ip = request.getHeader(\"HTTP_CLIENT_IP\");\r\n            System.out.println(\"HTTP_CLIENT_IP ip: \" + ip);\r\n        }\r\n        if (ip == null || ip.length() == 0 || \"unknown\".equalsIgnoreCase(ip)) {\r\n            ip = request.getHeader(\"HTTP_X_FORWARDED_FOR\");\r\n            System.out.println(\"HTTP_X_FORWARDED_FOR ip: \" + ip);\r\n        }\r\n        if (ip == null || ip.length() == 0 || \"unknown\".equalsIgnoreCase(ip)) {\r\n            ip = request.getHeader(\"X-Real-IP\");\r\n            System.out.println(\"X-Real-IP ip: \" + ip);\r\n        }\r\n        if (ip == null || ip.length() == 0 || \"unknown\".equalsIgnoreCase(ip)) {\r\n            ip = request.getRemoteAddr();\r\n            System.out.println(\"getRemoteAddr ip: \" + ip);\r\n        }\r\n        System.out.println(\"获取客户端ip: \" + ip);\r\n        return ip;\r\n    }\r\n```\r\n#### 原文地址:[https://dsx2016.com/?p=226](https://dsx2016.com/?p=226 \"https://dsx2016.com/?p=226\")', '2020-07-10 21:35:13', 'https://bpic.588ku.com//element_origin_min_pic/19/04/04/b9f7a185292b99fc476991b35c57ab07.jpg', '转载', '', '', '', 'Java获取访客IP地址', '2020-07-10 21:37:27', '22', '140', '1', '在开发工作中,我们常常需要获取客户端的IP，本文提供两种获取客户端真实IP的方法。');

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
INSERT INTO `t_detail_tags` VALUES ('84', '54');
INSERT INTO `t_detail_tags` VALUES ('87', '53');
INSERT INTO `t_detail_tags` VALUES ('90', '54');
INSERT INTO `t_detail_tags` VALUES ('91', '54');
INSERT INTO `t_detail_tags` VALUES ('93', '54');
INSERT INTO `t_detail_tags` VALUES ('176', '178');
INSERT INTO `t_detail_tags` VALUES ('176', '177');
INSERT INTO `t_detail_tags` VALUES ('174', '175');
INSERT INTO `t_detail_tags` VALUES ('174', '173');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_leaving_message
-- ----------------------------
INSERT INTO `t_leaving_message` VALUES ('180', '测试', '1456133139@qq.com', '23333333333333333333333333333333333333333333', '2020-07-11 21:51:20');
INSERT INTO `t_leaving_message` VALUES ('181', '啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊', '2323123123123@q', '啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊', '2020-07-11 22:12:10');
INSERT INTO `t_leaving_message` VALUES ('182', '啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊', '213123123!@q', 'sasd', '2020-07-11 22:12:25');
INSERT INTO `t_leaving_message` VALUES ('196', '123123', '1456133139@qq.com', '啊', '2021-03-22 16:07:02');
INSERT INTO `t_leaving_message` VALUES ('197', '好家伙', '1941565808@qq.com', '好家伙', '2021-03-22 16:09:37');
INSERT INTO `t_leaving_message` VALUES ('198', '111111111111111111111111111111', '1456133139@qq.com', '11111111111111111111111', '2021-03-22 16:09:52');

-- ----------------------------
-- Table structure for `t_tag`
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES ('158', 'Spring Boot');
INSERT INTO `t_tag` VALUES ('173', '正则表达式');
INSERT INTO `t_tag` VALUES ('175', 'JavaScript');
INSERT INTO `t_tag` VALUES ('177', 'Nginx');
INSERT INTO `t_tag` VALUES ('178', '常用API');

-- ----------------------------
-- Table structure for `t_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('140', '后端开发');
INSERT INTO `t_type` VALUES ('141', '前端开发');
INSERT INTO `t_type` VALUES ('142', '数据库相关');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'https://pic.qqtn.com/up/2017-11/2017112216362233724.jpg', '2020-07-05 20:04:34', '1456133139@qq.com', 'Zm-Mmm', '5f93f983524def3dca464469d2cf9f3e', '1', '2020-07-05 20:05:13', '1456133139');
