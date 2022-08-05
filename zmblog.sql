/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50733
Source Host           : localhost:3306
Source Database       : zmblog

Target Server Type    : MYSQL
Target Server Version : 50733
File Encoding         : 65001

Date: 2022-08-06 00:00:18
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
INSERT INTO `hibernate_sequence` VALUES ('217');
INSERT INTO `hibernate_sequence` VALUES ('217');
INSERT INTO `hibernate_sequence` VALUES ('217');
INSERT INTO `hibernate_sequence` VALUES ('217');
INSERT INTO `hibernate_sequence` VALUES ('217');

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
INSERT INTO `t_comment` VALUES ('199', '/img/avatar.png', '1', '2022-04-30 13:28:26', '123@qq.com', '1', '174', null, '');
INSERT INTO `t_comment` VALUES ('200', 'https://pic.qqtn.com/up/2017-11/2017112216362233724.jpg', '2', '2022-04-30 13:30:52', '1456133139@qq.com', 'Zm-Mmm', '174', null, '');
INSERT INTO `t_comment` VALUES ('202', '/img/avatar.png', 'gg', '2022-06-29 20:37:31', '123@wq', '13', '174', '200', '');

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
INSERT INTO `t_detail` VALUES ('94', '', '', '```java\r\npackage com.util;\r\n\r\nimport java.security.MessageDigest;\r\nimport java.security.NoSuchAlgorithmException;\r\n\r\npublic class MessageDigestAlgorithm {\r\n\r\n    public static String code(String str){\r\n        try {\r\n            MessageDigest md = MessageDigest.getInstance(\"MessageDigestAlgorithm\");\r\n            md.update(str.getBytes());\r\n            byte[]byteDigest = md.digest();\r\n            int i;\r\n            StringBuffer buf = new StringBuffer(\"\");\r\n            for (int offset = 0; offset < byteDigest.length; offset++) {\r\n                i = byteDigest[offset];\r\n                if (i < 0)\r\n                    i += 256;\r\n                if (i < 16)\r\n                    buf.append(\"0\");\r\n                buf.append(Integer.toHexString(i));\r\n            }\r\n            //32位加密\r\n            return buf.toString();\r\n            // 16位的加密\r\n            //return buf.toString().substring(8, 24);\r\n        } catch (NoSuchAlgorithmException e) {\r\n            e.printStackTrace();\r\n            return null;\r\n        }\r\n    }\r\n}\r\n\r\n```', '2020-07-07 14:30:28', 'https://p0.ssl.img.360kuai.com/t01d316080036698774.webp', '原创', '', '', '', 'MD5加密源码', '2020-07-10 17:58:37', '54', '140', '1', '在开发过程中，避免不了要涉及到数据加密，比如用户账号密码的加密，用户敏感数据的加密，涉及到的加密算法种类繁多，作为拿来主义的开发者时间精力有限，能够清楚其中主流的加密算法和用途，就已经足够了。');
INSERT INTO `t_detail` VALUES ('127', '', '', '```java\r\npackage com.util;\r\n\r\nimport org.commonmark.Extension;\r\nimport org.commonmark.ext.gfm.tables.TableBlock;\r\nimport org.commonmark.ext.gfm.tables.TablesExtension;\r\nimport org.commonmark.ext.heading.anchor.HeadingAnchorExtension;\r\nimport org.commonmark.node.Link;\r\nimport org.commonmark.node.Node;\r\nimport org.commonmark.parser.Parser;\r\nimport org.commonmark.renderer.html.AttributeProvider;\r\nimport org.commonmark.renderer.html.AttributeProviderContext;\r\nimport org.commonmark.renderer.html.AttributeProviderFactory;\r\nimport org.commonmark.renderer.html.HtmlRenderer;\r\n\r\nimport java.util.*;\r\n\r\npublic class Markdown {\r\n\r\n    /**\r\n     * markdown格式转换成HTML格式\r\n     * @param markdown\r\n     * @return\r\n     */\r\n    public static String markdownToHtml(String markdown) {\r\n        Parser parser = Parser.builder().build();\r\n        Node document = parser.parse(markdown);\r\n        HtmlRenderer renderer = HtmlRenderer.builder().build();\r\n        return renderer.render(document);\r\n    }\r\n\r\n    /**\r\n     * 增加扩展[标题锚点，表格生成]\r\n     * Markdown转换成HTML\r\n     * @param markdown\r\n     * @return\r\n     */\r\n    public static String markdownToHtmlExtensions(String markdown) {\r\n        //h标题生成id\r\n        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());\r\n        //转换table的HTML\r\n        List<Extension> tableExtension = Arrays.asList(TablesExtension.create());\r\n        Parser parser = Parser.builder()\r\n                .extensions(tableExtension)\r\n                .build();\r\n        Node document = parser.parse(markdown);\r\n        HtmlRenderer renderer = HtmlRenderer.builder()\r\n                .extensions(headingAnchorExtensions)\r\n                .extensions(tableExtension)\r\n                .attributeProviderFactory(new AttributeProviderFactory() {\r\n                    public AttributeProvider create(AttributeProviderContext context) {\r\n                        return new CustomAttributeProvider();\r\n                    }\r\n                })\r\n                .build();\r\n        return renderer.render(document);\r\n    }\r\n\r\n    /**\r\n     * 处理标签的属性\r\n     */\r\n    static class CustomAttributeProvider implements AttributeProvider {\r\n        @Override\r\n        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {\r\n            //改变a标签的target属性为_blank\r\n            if (node instanceof Link) {\r\n                attributes.put(\"target\", \"_blank\");\r\n            }\r\n            if (node instanceof TableBlock) {\r\n                attributes.put(\"class\", \"ui celled table\");\r\n            }\r\n        }\r\n    }\r\n}\r\n```', '2020-07-08 16:19:51', 'https://pic1.zhimg.com/80/v2-07153671b36b2d0e8b8ade5163f3fb00_720w.png', '转载', '', '', '', 'MarkDown格式转换', '2020-07-09 23:06:16', '80', '140', '1', 'Markdown是一种可以使用普通文本编辑器编写的标记语言，通过简单的标记语法，它可以使普通文本内容具有一定的格式。\r\n\r\nMarkdown具有一系列衍生版本，用于扩展Markdown的功能(如表格、脚注、内嵌HTML等等)');
INSERT INTO `t_detail` VALUES ('174', '', '', '### 1. 长度限制\r\n```javascript\r\n<script language=\"javascript\">\r\n	function test() {\r\n		if (document.a.b.value.length > 50) {\r\n			alert(\"不能超过50个字符！\");\r\n			document.a.b.focus();\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 2. 只能是汉字\r\n```javascript\r\n<script language=\"javascript\">\r\n	function isChinese(obj) {\r\n		var reg = /^[\\u0391-\\uFFE5]+$/;\r\n		if (obj != \"\" && !reg.test(obj)) {\r\n			alert(\'必须输入中文！\');\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 3. 只能是英文字母\r\n```javascript\r\n<script type=\"text/javascript\">\r\n	//验证只能是字母\r\n	function checkZm(zm) {\r\n		var zmReg = /^[a-zA-Z]*$/;\r\n		if (zm != \"\" && !zmReg.test(zm)) {\r\n			alert(\"只能是英文字母！\");\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 4. 只能是数字\r\n```javascript\r\n<script language=javascript>\r\n	//验证只能为数字 \r\n	function checkNumber(obj) {\r\n		var reg = /^[0-9]+$/;\r\n		if (obj != \"\" && !reg.test(obj)) {\r\n			alert(\'只能输入数字！\');\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 5. 只能是英文字母和数字\r\n```javascript\r\n<script type=\"text/javascript\">\r\n	//验证只能是字母和数字 \r\n	function checkZmOrNum(zmnum) {\r\n		var zmnumReg = /^[0-9a-zA-Z]*$/;\r\n		if (zmnum != \"\" && !zmnumReg.test(zmnum)) {\r\n			alert(\"只能输入是字母或者数字,请重新输入\");\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 6. 检验时间大小(与当前时间比较)\r\n```javascript\r\n<script type=\"text/javascript\">\r\n	//检验时间大小(与当前时间比较) \r\n	function checkDate(obj) {\r\n		var obj_value = obj.replace(/-/g, \"/\"); //替换字符，变成标准格式(检验格式为：\'2009-12-10\') \r\n		// var obj_value=obj.replace(\"-\",\"/\");//替换字符，变成标准格式(检验格式为：\'2010-12-10 11:12\') \r\n		var date1 = new Date(Date.parse(obj_value));\r\n		var date2 = new Date(); //取今天的日期 \r\n		if (date1 > date2) {\r\n			alert(\"不能大于当前时间！\");\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 7. 屏蔽关键字(这里屏蔽\\*\\*\\*和\\*\\*\\*\\*)\r\n```javascript\r\n<script type=\"text/javascript\">\r\n	function test(obj) {\r\n		if ((obj.indexOf(\"***\") == 0) || (obj.indexOf(\"****\") == 0)) {\r\n			alert(\"屏蔽关键字(这里屏蔽***和****)！\");\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 8. 两次输入密码是否相同\r\n```javascript\r\n<script type=\"text/javascript\">\r\n	function check() {\r\n		with(document.all) {\r\n			if (input1.value != input2.value) {\r\n				alert(\"密码不一致\")\r\n				input1.value = \"\";\r\n				input2.value = \"\";\r\n			} else {\r\n				alert(\"密码一致\");\r\n				document.forms[0].submit();\r\n			}\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 9. 表单项不能为空\r\n```javascript\r\n<script language=\"javascript\">\r\n	function CheckForm(obj) {\r\n		if (obj.length == 0) {\r\n			alert(\"姓名不能为空!\");\r\n			return false;\r\n		}\r\n		return true;\r\n		alert(\"姓名不能为空!\");\r\n	}\r\n</script>\r\n```\r\n### 10. 邮箱验证\r\n```javascript\r\n<script language=\"javascript\">\r\n	function test(obj) {\r\n		//对电子邮件的验证\r\n		var myreg = /^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$/;\r\n		if (!myreg.test(obj)) {\r\n			alert(\'请输入有效的邮箱！\');\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 11. 验证手机号\r\n```javascript\r\n<script type=\"text/javascript\">\r\n	function validatemobile(mobile) {\r\n		if (mobile.length == 0) {\r\n			alert(\'手机号码不能为空！\');\r\n			return false;\r\n		}\r\n		if (mobile.length != 11) {\r\n			alert(\'请输入有效的手机号码，需是11位！\');\r\n			return false;\r\n		}\r\n\r\n		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$/;\r\n		if (!myreg.test(mobile)) {\r\n			alert(\'请输入有效的手机号码！\');\r\n			return false;\r\n		}\r\n	}\r\n</script>\r\n```\r\n### 12. 验证身份证号码（需是有效身份证）\r\n```javascript\r\n		<script type=\"text/javascript\">\r\n			// 构造函数，变量为15位或者18位的身份证号码\r\n			function clsIDCard(CardNo) {\r\n				this.Valid = false;\r\n				this.ID15 = \'\';\r\n				this.ID18 = \'\';\r\n				this.Local = \'\';\r\n				if (CardNo != null) this.SetCardNo(CardNo);\r\n			}\r\n\r\n			// 设置身份证号码，15位或者18位\r\n			clsIDCard.prototype.SetCardNo = function(CardNo) {\r\n				this.ID15 = \'\';\r\n				this.ID18 = \'\';\r\n				this.Local = \'\';\r\n				CardNo = CardNo.replace(\" \", \"\");\r\n				var strCardNo;\r\n				if (CardNo.length == 18) {\r\n					pattern = /^\\d{17}(\\d|x|X)$/;\r\n					if (pattern.exec(CardNo) == null) return;\r\n					strCardNo = CardNo.toUpperCase();\r\n				} else {\r\n					pattern = /^\\d{15}$/;\r\n					if (pattern.exec(CardNo) == null) return;\r\n					strCardNo = CardNo.substr(0, 6) + \'19\' + CardNo.substr(6, 9)\r\n					strCardNo += this.GetVCode(strCardNo);\r\n				}\r\n				this.Valid = this.CheckValid(strCardNo);\r\n			}\r\n\r\n			// 校验身份证有效性\r\n			clsIDCard.prototype.IsValid = function() {\r\n				return this.Valid;\r\n			}\r\n\r\n			// 返回生日字符串，格式如下，1981-10-10\r\n			clsIDCard.prototype.GetBirthDate = function() {\r\n				var BirthDate = \'\';\r\n				if (this.Valid) BirthDate = this.GetBirthYear() + \'-\' + this.GetBirthMonth() + \'-\' + this.GetBirthDay();\r\n				return BirthDate;\r\n			}\r\n\r\n			// 返回生日中的年，格式如下，1981\r\n			clsIDCard.prototype.GetBirthYear = function() {\r\n				var BirthYear = \'\';\r\n				if (this.Valid) BirthYear = this.ID18.substr(6, 4);\r\n				return BirthYear;\r\n			}\r\n\r\n			// 返回生日中的月，格式如下，10\r\n			clsIDCard.prototype.GetBirthMonth = function() {\r\n				var BirthMonth = \'\';\r\n				if (this.Valid) BirthMonth = this.ID18.substr(10, 2);\r\n				if (BirthMonth.charAt(0) == \'0\') BirthMonth = BirthMonth.charAt(1);\r\n				return BirthMonth;\r\n			}\r\n\r\n			// 返回生日中的日，格式如下，10\r\n			clsIDCard.prototype.GetBirthDay = function() {\r\n				var BirthDay = \'\';\r\n				if (this.Valid) BirthDay = this.ID18.substr(12, 2);\r\n				return BirthDay;\r\n			}\r\n\r\n			// 返回性别，1：男，0：女\r\n			clsIDCard.prototype.GetSex = function() {\r\n				var Sex = \'\';\r\n				if (this.Valid) Sex = this.ID18.charAt(16) % 2;\r\n				return Sex;\r\n			}\r\n\r\n			// 返回15位身份证号码\r\n			clsIDCard.prototype.Get15 = function() {\r\n				var ID15 = \'\';\r\n				if (this.Valid) ID15 = this.ID15;\r\n				return ID15;\r\n			}\r\n\r\n			// 返回18位身份证号码\r\n			clsIDCard.prototype.Get18 = function() {\r\n				var ID18 = \'\';\r\n				if (this.Valid) ID18 = this.ID18;\r\n				return ID18;\r\n			}\r\n\r\n			// 返回所在省，例如：上海市、浙江省\r\n			clsIDCard.prototype.GetLocal = function() {\r\n				var Local = \'\';\r\n				if (this.Valid) Local = this.Local;\r\n				return Local;\r\n			}\r\n\r\n			clsIDCard.prototype.GetVCode = function(CardNo17) {\r\n				var Wi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);\r\n				var Ai = new Array(\'1\', \'0\', \'X\', \'9\', \'8\', \'7\', \'6\', \'5\', \'4\', \'3\', \'2\');\r\n				var cardNoSum = 0;\r\n				for (var i = 0; i < CardNo17.length; i++) cardNoSum += CardNo17.charAt(i) * Wi[i];\r\n				var seq = cardNoSum % 11;\r\n				return Ai[seq];\r\n			}\r\n\r\n			clsIDCard.prototype.CheckValid = function(CardNo18) {\r\n				if (this.GetVCode(CardNo18.substr(0, 17)) != CardNo18.charAt(17)) return false;\r\n				if (!this.IsDate(CardNo18.substr(6, 8))) return false;\r\n				var aCity = {\r\n					11: \"北京\",\r\n					12: \"天津\",\r\n					13: \"河北\",\r\n					14: \"山西\",\r\n					15: \"内蒙古\",\r\n					21: \"辽宁\",\r\n					22: \"吉林\",\r\n					23: \"黑龙江 \",\r\n					31: \"上海\",\r\n					32: \"江苏\",\r\n					33: \"浙江\",\r\n					34: \"安徽\",\r\n					35: \"福建\",\r\n					36: \"江西\",\r\n					37: \"山东\",\r\n					41: \"河南\",\r\n					42: \"湖北 \",\r\n					43: \"湖南\",\r\n					44: \"广东\",\r\n					45: \"广西\",\r\n					46: \"海南\",\r\n					50: \"重庆\",\r\n					51: \"四川\",\r\n					52: \"贵州\",\r\n					53: \"云南\",\r\n					54: \"西藏 \",\r\n					61: \"陕西\",\r\n					62: \"甘肃\",\r\n					63: \"青海\",\r\n					64: \"宁夏\",\r\n					65: \"新疆\",\r\n					71: \"台湾\",\r\n					81: \"香港\",\r\n					82: \"澳门\",\r\n					91: \"国外\"\r\n				};\r\n				if (aCity[parseInt(CardNo18.substr(0, 2))] == null) return false;\r\n				this.ID18 = CardNo18;\r\n				this.ID15 = CardNo18.substr(0, 6) + CardNo18.substr(8, 9);\r\n				this.Local = aCity[parseInt(CardNo18.substr(0, 2))];\r\n				return true;\r\n			}\r\n\r\n			clsIDCard.prototype.IsDate = function(strDate) {\r\n				var r = strDate.match(/^(\\d{1,4})(\\d{1,2})(\\d{1,2})$/);\r\n				if (r == null) return false;\r\n				var d = new Date(r[1], r[2] - 1, r[3]);\r\n				return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[2] && d.getDate() == r[3]);\r\n			}\r\n\r\n\r\n			function valiIdCard(idCard) {\r\n				var checkFlag = new clsIDCard(idCard);\r\n				if (!checkFlag.IsValid()) {\r\n					alert(\"输入的身份证号无效,请输入真实的身份证号！\");\r\n					document.getElementByIdx(\"idCard\").focus();\r\n					return false;\r\n				} else {\r\n					alert(\"是有效身份证！\");\r\n				}\r\n			}\r\n		</script>\r\n```\r\n#### 转载自:[https://blog.csdn.net/qq_27628085/article/details/81198962](https://blog.csdn.net/qq_27628085/article/details/81198962)', '2020-07-07 16:04:49', 'https://p.ssl.qhimg.com/t01b16aca138cd5d16f.jpg?size=720x400', '转载', '', '', '', 'JS表单验证-12个常用的JS表单验证', '2022-04-30 13:33:27', '102', '141', '1', '12种常用JS表单验证，部分使用正则表达式。包含常用邮箱验证、身份证验证、手机号验证、密码组合验证等等');
INSERT INTO `t_detail` VALUES ('176', '', '', '## 获取IP地址\r\n在开发工作中,我们常常需要获取客户端的IP\r\n### 1、nginx配置\r\n为了拿到真实的IP地址,避免代理等错误源,需要在nginx配置\r\n```nginx\r\nlocation / {\r\n            # 指向当前的某个服务端口(根据服务单独java打包jar开启对应的服务)\r\n            proxy_pass   http://127.0.0.1:端口号;\r\n​\r\n            # 这里配置java获取客户端真实的ip地址(避免因为nginx反向代理造成ip 127.0.0.1等其他错误现象)\r\n            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;\r\n            set $Real $http_x_forwarded_for;\r\n            if ( $Real ~ (\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+),(.*) ){\r\n                set $Real $1.$2.$3.$4;\r\n            }\r\n            proxy_set_header X-Real-Ip $Real;\r\n        }\r\n```\r\n### 2、java API 获取\r\n```java\r\n//获取ip地址\r\n    @GetMapping(value = \"/getIp\")\r\n    @ResponseBody\r\n    private String getIpAddr(HttpServletRequest request) {\r\n        String ip = request.getHeader(\"x-forwarded-for\");\r\n        System.out.println(\"x-forwarded-for ip: \" + ip);\r\n        if (ip != null && ip.length() != 0 && !\"unknown\".equalsIgnoreCase(ip)) {\r\n            // 多次反向代理后会有多个ip值，第一个ip才是真实ip\r\n            if (ip.indexOf(\",\") != -1) {\r\n                ip = ip.split(\",\")[0];\r\n            }\r\n        }\r\n        if (ip == null || ip.length() == 0 || \"unknown\".equalsIgnoreCase(ip)) {\r\n            ip = request.getHeader(\"Proxy-Client-IP\");\r\n            System.out.println(\"Proxy-Client-IP ip: \" + ip);\r\n        }\r\n        if (ip == null || ip.length() == 0 || \"unknown\".equalsIgnoreCase(ip)) {\r\n            ip = request.getHeader(\"WL-Proxy-Client-IP\");\r\n            System.out.println(\"WL-Proxy-Client-IP ip: \" + ip);\r\n        }\r\n        if (ip == null || ip.length() == 0 || \"unknown\".equalsIgnoreCase(ip)) {\r\n            ip = request.getHeader(\"HTTP_CLIENT_IP\");\r\n            System.out.println(\"HTTP_CLIENT_IP ip: \" + ip);\r\n        }\r\n        if (ip == null || ip.length() == 0 || \"unknown\".equalsIgnoreCase(ip)) {\r\n            ip = request.getHeader(\"HTTP_X_FORWARDED_FOR\");\r\n            System.out.println(\"HTTP_X_FORWARDED_FOR ip: \" + ip);\r\n        }\r\n        if (ip == null || ip.length() == 0 || \"unknown\".equalsIgnoreCase(ip)) {\r\n            ip = request.getHeader(\"X-Real-IP\");\r\n            System.out.println(\"X-Real-IP ip: \" + ip);\r\n        }\r\n        if (ip == null || ip.length() == 0 || \"unknown\".equalsIgnoreCase(ip)) {\r\n            ip = request.getRemoteAddr();\r\n            System.out.println(\"getRemoteAddr ip: \" + ip);\r\n        }\r\n        System.out.println(\"获取客户端ip: \" + ip);\r\n        return ip;\r\n    }\r\n```\r\n#### 原文地址:[https://dsx2016.com/?p=226](https://dsx2016.com/?p=226 \"https://dsx2016.com/?p=226\")', '2020-07-10 21:35:13', 'https://bpic.588ku.com//element_origin_min_pic/19/04/04/b9f7a185292b99fc476991b35c57ab07.jpg', '转载', '', '', '', 'Java获取访客IP地址', '2020-07-10 21:37:27', '26', '140', '1', '在开发工作中,我们常常需要获取客户端的IP，本文提供两种获取客户端真实IP的方法。');
INSERT INTO `t_detail` VALUES ('206', '', '', '# Git学习笔记\r\n\r\n## 1、用户信息\r\n\r\n```bash\r\n# 设置用户信息\r\ngit config --global user.name \"zm\"\r\ngit config --global user.email \"1456133139@qq.com\"\r\n# 查看用户信息\r\ngit config --global user.name\r\ngit config --global user.email\r\n```\r\n\r\n------------\r\n\r\n\r\n## 2、仓库管理\r\n\r\n### 2.1 初始化git本地仓库\r\n\r\n```bash\r\ngit init\r\n```\r\n\r\n------------\r\n\r\n\r\n### 2.2 本地仓库管理\r\n\r\n```bash\r\ngit add 		# 工作区 -> 暂存区\r\ngit commit 		# 暂存区 -> 本地仓库\r\ngit status		# 查看状态\r\ngit log 		# 查看提交记录\r\n```\r\n\r\n> **一般使用 git add .**\r\n\r\n------------\r\n\r\n\r\n### 2.3 版本回退\r\n\r\n```bash\r\ngit reset --hard [commitID]\r\ngit reflog                    # 查看已经删除的提交记录\r\n```\r\n\r\n> **可以通过.gitignore配置文件设置不需要提交的文件**\r\n\r\n------------\r\n\r\n## 3、分支管理\r\n\r\n```bash\r\ngit branch						# 查看分支\r\ngit branch -d [branchID]		# 删除分支\r\ngit branch [branchID]			# 创建分支\r\ngit checkout					# 切换分支\r\ngit checkout -b [branchID]		# 创建并切换分支\r\ngit merge [branchID]			# 合并分支\r\n```\r\n\r\n> **git branch -D [branchID] 可以强制删除分支**\r\n\r\n**<-** *两个分支对相同文件的修改可能会存在冲突，这时候需要手动解决冲突，重新add、commit* **->**\r\n\r\n------------\r\n\r\n## 4、常见的不同环境下分支作用\r\n\r\n\r\n- **master** (生产)分支\r\n	- 上线分支，develop版本更新结束merge到master并上线\r\n- **develop** (开发)分支\r\n	- 从开发分支branch功能分支进行开发，结束后merge到devlop分支\r\n- **hotfix** (DEBUG)分支\r\n	- 从master中拉取代码并修改，然后merge到master和develop分支\r\n\r\n\r\n------------\r\n\r\n## 5、配置SSH公钥\r\n\r\n```bash\r\nssh-keygen -t rsa 		# 生成SSH公钥\r\ncat ~/.ssh/id_rsa.pub	# 查看当前公钥\r\nssh -T git@gitee.com	# 查看连接是否成功\r\n\r\n```\r\n> **生成公钥之后不断回车即可(如果公钥已经存在，则会自动覆盖)**\r\n\r\n\r\n------------\r\n\r\n## 6、远程仓库管理\r\n\r\n```bash\r\ngit push [远程仓库名] [本地分支名]:[远端分支名]\r\n# 如果远端分支名和本地分支名相同，则可以只写本地分支\r\n# git push origin master\r\n\r\ngit push -f 	# 表示强制覆盖\r\ngit push --set-upstream		# 推送到远端的同时并建立和远端的关联关系\r\n# 如果当前分支已经和远端分支关联，则可以省略分支名和远端名\r\n\r\ngit branch -vv		# 查看本地分支与远端分支的关联关系\r\n\r\ngit clone [仓库路径] [本地目录]		# 本地目录可以省略\r\n\r\ngit fetch		# 抓取：将远端仓库里的更新都抓取到本地，不进行合并\r\n				# 如果不指定远端名称和分支名称，则抓取所有分支\r\n\r\ngit pull		# 拉取：将远端仓库里的更新拉到本地并自动合并，等同于fetch+merge\r\n				# 如果不指定远端名称和分支名称，则抓取所有并更新当前分支\r\n```\r\n\r\n------------\r\n\r\n\r\n\r\n## 7、.gitignore通用配置文件\r\n\r\n```bash\r\n# Compiled class file\r\n*.class\r\n\r\n# Log file\r\n*.log\r\n\r\n# BlueJ files\r\n*.ctxt\r\n\r\n# Mobile Tools for Java (J2ME)\r\n.mtj.tmp/\r\n\r\n# Package Files #\r\n*.jar\r\n*.war\r\n*.nar\r\n*.ear\r\n*.zip\r\n*.tar.gz\r\n*.rar\r\n\r\n# virtual machine crash logs, see http://www.java.com/en/download/help/error_hotspot.xml\r\nhs_err_pid*\r\n\r\n.mvn/\r\n.idea/\r\nlog/\r\ntarget/\r\n*.iml\r\n\r\n```\r\n\r\n------------\r\n\r\n## 8、IDEA集成Git\r\n\r\n### 8.1 查看版本信息\r\n\r\n> File -> Settings -> Version Control -> Git -> Path to Git executable -> Test\r\n\r\n### 8.2 设置IDEA Terminal集成Bash\r\n\r\n> File -> Settings -> Tools -> Terminal -> Application Settings -> Shell path -> X:\\Git\\bin\\bash.exe\r\n', '2022-08-04 21:38:45', 'https://fc1tn.baidu.com/it/u=232235860,1551268367&fm=202&mola=new&crop=v1', '原创', '', '', '', 'Git学习笔记', '2022-08-05 16:45:05', '16', '140', '1', 'Git学习笔记');
INSERT INTO `t_detail` VALUES ('214', '', '', '1', '2022-08-05 16:47:32', 'https://img.cmsblogs.cn/2021/03/20210319173514370.jpg', '原创', '', '', '', 'Swagger学习笔记', '2022-08-05 16:49:54', '3', '140', '1', 'Swagger学习笔记');
INSERT INTO `t_detail` VALUES ('216', '', '', '# 1、MySQL常用命令与简单查询\r\n\r\n## 1.1 命令行\r\n```SQL\r\nshow databases;		# 查看数据库中的表\r\nuse [表名]\r\n\r\nmysql -u root -p	# 连接数据库\r\ndesc [数据库名称]	# 查看数据库类型\r\n\r\ncreate database bjpowernode; # 创建数据库\r\nshow tables;		# 查看表\r\n\r\nselect version(); # 查看MySQL版本号\r\n```\r\n\r\n## 1.2 简单查询\r\n\r\n```SQL\r\nselect * from dept;				# 查看所有表数据\r\n\r\ndesc [表名]	# 查看表结构\r\n\r\n```\r\n\r\n------------\r\n\r\n\r\n**select * 的缺点**\r\n\r\n1. 效率低\r\n2. 可读性差\r\n3. 实际开发不建议\r\n\r\n## 1.3 别名\r\n\r\n```SQL\r\nselect [表1],[表2] as [别名] from dept;\r\n# as可以省略\r\n# 当别名中有空格，用单引号\'\'隔开\r\n# 也可以加双引号，但是建议用\'单引号\'括起来\r\n```\r\n\r\n## 1.4 字段中的数学表达式\r\n\r\n```SQL\r\nselect salary*12 from employee\r\n```\r\n\r\n# 2、条件查询\r\n\r\n```SQL\r\nselect\r\n	字段1、字段2、字段3...\r\nfrom\r\n	表名\r\nwhere\r\n	条件;\r\n\r\n```\r\n\r\n- = 等于\r\n```SQL\r\n# 查询薪资等于800的员工姓名和编号\r\nselect empno,ename from emp where sal = 800;\r\n```\r\n\r\n- <>或!= 不等于\r\n```SQL\r\n# 查询薪资不等于800的员工姓名和编号\r\nselect empno,ename from emp where sal != 800;\r\n```\r\n\r\n- < 小于\r\n```SQL\r\n# 查询薪资小于2000的员工姓名和编号\r\nmysql> select empno,ename,sal from emp where sal < 2000;\r\n```\r\n\r\n- <= 小于等于\r\n```SQL\r\n# 查询薪资小于等于3000的员工姓名和编号\r\nselect empno,ename,sal from emp where sal <= 3000;\r\n```\r\n\r\n- \\> 大于\r\n```SQL\r\n# 查询薪资大于3000的员工姓名和编号\r\nselect empno,ename,sal from emp where sal > 3000;\r\n```\r\n\r\n- \\>= 大于等于\r\n```SQL\r\n# 查询薪资大于等于3000的员工姓名和编号\r\nselect empno,ename,sal from emp where sal >= 3000;\r\n```\r\n\r\n- between … and …. 两个值之间, 等同于 >= and <=\r\n```SQL\r\n# 查询薪资在2450和3000之间的员工信息,包括2450和3000\r\n	select empno,ename,sal from emp where sal >= 2450 and sal <= 3000;\r\n	select\r\n		empno,ename,sal\r\n	from\r\n		emp\r\n	where\r\n		sal between 2450 and 3000;\r\n```\r\n\r\n> **between...and...左边的值一定比右边小且为闭区间**\r\n\r\n- 查询null结果要用is不能用=\r\n\r\n- is的反义词为is not\r\n\r\n```SQL\r\n# 查询哪些员工的津贴/补助为null\r\nselect empno,ename,sal,comm from emp where comm is null;\r\n```\r\n\r\n> **&&条件在数据库中用and**\r\n\r\n> **and的优先级比or高 **\r\n\r\n#### in关键字\r\n\r\n```SQL\r\n# 查询工作岗位是MANAGER和SALESMAN的员工\r\nselect empno,ename,job from emp where job = \'MANAGER\' or job = \'SALESMAN\';\r\nselect empno,ename,job from emp where job in(\'MANAGER\', \'SALESMAN\');\r\n```\r\n> 注意：in不是一个区间。in后面跟的是具体的值\r\n\r\n\r\n# 3、模糊查询\r\n\r\n#### like支持%和_匹配\r\n\r\n```SQL\r\n# %0% 找出名字中含有O的\r\nselect ename from emp where ename like \'%O%\';\r\n\r\n# %T 找出名字以T结尾的\r\nselect ename from emp where ename like \'%T\'\r\n\r\n# K% 找出名字以K开始的\r\nselect ename from emp where ename like \'K%\';\r\n\r\n# _A% 找出第二个字每是A的\r\nselect ename from emp where ename like \'_A%\';\r\n\r\n# __R% 找出第三个字母是R的\r\nselect ename from emp where ename like \'__R%\';\r\n\r\n```\r\n\r\n> \\为转义字符适用于查询的数据跟想要的数据冲突\r\n\r\n\r\n# 4、排序\r\n\r\n```SQL\r\nselect xxx from emp order by xxx; # xxx为想要排序的字段\r\n```\r\n\r\n> 默认为升序(从上至下，由小及大)\r\n\r\n```SQL\r\nselect xxx from emp order by xxx desc; # 降序\r\n```\r\n\r\n```SQL\r\nselect xxx from emp order by xxx asc; # 升序\r\n```\r\n\r\n## 4.2 多字段排序\r\n\r\n```SQL\r\nselect\r\n	ename,sal\r\nfrom\r\n	emp\r\norder by\r\n	sal asc, ename asc;\r\n\r\n```\r\n\r\n> **只有sal相等的时候，才会考虑启用ename排序**\r\n\r\n\r\n# 5、单行处理函数\r\n\r\n> 特点：一行一行处理，每一行输出一个结果\r\n\r\n```SQL\r\nlower() 	# 转小写\r\nupper() 	# 转大写\r\nsubstr() 	# 取子串 格式:substr(被截取的字符串,起始下标,截取长度)\r\nlength() 	# 取长度\r\ntrim()		# 去空格\r\nconcat(a,b)	# 将b字符串拼到a字符串后面\r\n```\r\n> **substr起始下标为1**\r\n\r\n```SQL\r\nround(小数，保留的小数位) 	# 四舍五入\r\nrand() 		#生成随机数 ，且随机数都为1到0之间的小数\r\n```\r\n\r\n## 5.2 NULL\r\n\r\n#### 在数据库中，有NULL参与的数学运算最终值总是NULL\r\n\r\n> **注意：NULL只要参与运算，最终结果一定是NULL。为了避免这个现象，需要使用ifnull函数**\r\n\r\n```SQL\r\n# 补助为NULL的时候，将补助当做0\r\nselect ename, (sal + ifnull(comm, 0)) * 12 as yearsal from emp;\r\n```\r\n\r\n# 6、多行处理函数(分组函数)\r\n\r\n## 6.1 分组函数必须先进行分组，然后才能用\r\n\r\n- **求和 sum()**\r\n\r\n- **求平均数 avg()**\r\n\r\n- **求最大值 max()**\r\n\r\n- **求最小值 min(0)**\r\n\r\n- **计数 count()		统计该字段下所有不为null的元素个数**\r\n\r\n> **分组函数自动处理NULL，不需要提前对NULL进行处理**\r\n\r\n- count(具体字段)：表示统计该字段下所有不为NULL的元素的总数\r\n\r\n- count(*)：统计表当中的总行数\r\n\r\n## 6.2 分组查询\r\n\r\n\r\n```SQL\r\nselect\r\n	...\r\nfrom\r\n	...\r\nwhere\r\n	...\r\ngroup by\r\n	...\r\norder by\r\n	...;\r\n```\r\n**执行顺序:**\r\n\r\n1. from\r\n\r\n2. where\r\n\r\n3. group by\r\n\r\n4. select\r\n\r\n5. order by\r\n\r\n\r\n> 由于where执行时group by分组还未执行,所以分组函数不能直接运用在where后面\r\n\r\n> select后面可以接分组函数,因为执行select的时候分组函数group by已经执行了\r\n\r\n**一旦在语句后加入group by,select后不能加入尚未分组的字段**\r\n\r\n> **group by 后面可以跟多个字段进行联合分组**\r\n\r\n```SQL\r\n# 找出每个工作岗位的工资和\r\n# 实现思路:按照工作岗位分组，然后对工资求和\r\nselect\r\n	job,sum(sal)\r\nfrom\r\n	emp\r\ngroup by\r\n	job;\r\n\r\n# 找出每个部门的最高薪资\r\n# 实现思路:按照部门编号分组，求每一组的最大值\r\nselect\r\n	deptno,max(sal)\r\nfrom\r\n	emp\r\ngroup by\r\n	deptno;\r\n\r\n# 找出“每个部门，不同工作岗位”的最高薪资\r\n# 实现思路:两个字段联合成1个字段看\r\nselect\r\n	deptno, job, max(sal)\r\nfrom\r\n	emp\r\ngroup by\r\n	deptno, job;\r\n```\r\n\r\n\r\n## 6.3 having语句\r\n\r\n- 可以对分完组之后的数据进一步进行过滤\r\n\r\n- 不能单独使用，不能代替where\r\n\r\n- 能使用where过滤掉的尽量用where\r\n\r\n```SQL\r\n# 找出每个部门最高薪资，要求显示最高薪资大于3000的\r\nselect\r\n	deptno,max(sal)\r\nfrom\r\n	emp\r\ngroup by\r\n	deptno\r\nhaving\r\n	max(sal) > 3000;\r\n\r\n# 以上的sql语句执行效率低\r\n# 优化策略：where和having，优先选择where，where实在完成不了了，再选择having\r\n\r\nselect\r\n	deptno,max(sal)\r\nfrom\r\n	emp\r\nwhere\r\n	sal > 3000\r\ngroup by\r\n	deptno;\r\n```\r\n\r\n## 6.4 练习\r\n\r\n```SQL\r\n# 找出每个岗位的平均薪资，要求显示平均薪资大于1500的，除MANAGER岗位之外，按照平均薪资降序排。\r\nselect\r\n	job, avg(sal) as avgsal\r\nfrom\r\n	emp\r\nwhere\r\n	job <> \'MANAGER\'\r\ngroup by\r\n	job\r\nhaving\r\n	avg(sal) > 1500\r\norder by\r\n	avgsal desc;\r\n```', '2022-08-05 22:14:10', 'https://t9.baidu.com/it/u=2278076917,2400225170&fm=218&app=126&size=f242,150&n=0&f=JPEG&fmt=auto?s=45B20C728DB0598011E975C10200F0B0&sec=1659805200&t=853bf4261e8948d8328d771d3ac39d86', '原创', '', '', '', 'MySQL学习笔记', '2022-08-05 23:30:51', '14', '142', '1', 'MySQL学习笔记');

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
INSERT INTO `t_detail_tags` VALUES ('206', '205');
INSERT INTO `t_detail_tags` VALUES ('206', '204');
INSERT INTO `t_detail_tags` VALUES ('214', '213');
INSERT INTO `t_detail_tags` VALUES ('214', '212');
INSERT INTO `t_detail_tags` VALUES ('214', '158');
INSERT INTO `t_detail_tags` VALUES ('216', '215');

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
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_leaving_message
-- ----------------------------
INSERT INTO `t_leaving_message` VALUES ('196', '123123', '1456133139@qq.com', '啊', '2021-03-22 16:07:02');
INSERT INTO `t_leaving_message` VALUES ('201', '1', '123@qq.com', '1', '2022-04-30 13:42:58');
INSERT INTO `t_leaving_message` VALUES ('203', '老八', '123@123', '老八秘制小汉堡', '2022-06-29 20:44:37');
INSERT INTO `t_leaving_message` VALUES ('208', '233', '13123@113', '2333', '2022-08-04 22:16:30');
INSERT INTO `t_leaving_message` VALUES ('209', '1', '1@1', '1', '2022-08-04 22:16:40');
INSERT INTO `t_leaving_message` VALUES ('210', '嗨害嗨', '1@1', '来啦', '2022-08-04 22:17:03');
INSERT INTO `t_leaving_message` VALUES ('211', '蔡徐坤', '1@1', '你干嘛', '2022-08-04 22:17:38');

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
INSERT INTO `t_tag` VALUES ('204', 'Git');
INSERT INTO `t_tag` VALUES ('205', '版本控制工具');
INSERT INTO `t_tag` VALUES ('212', 'Java');
INSERT INTO `t_tag` VALUES ('213', 'Swagger');
INSERT INTO `t_tag` VALUES ('215', 'MySQL');

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
