# zmblogs博客网站

#### 介绍

- 项目在线网址 www.zmblogs.cn

- 当前发布版本 **v2.6.9**

- 当前项目版本架构为**一体式架构**，前端使用**Thymeleaf模板引擎**开发，集成在**Spring Boot**中。

- 项目部署仅需要完成**Spring Boot**部署即可使用

- v3.0版本**前后端分离架构**项目正在开发中

#### 项目特点

- **分布式Session**管理

- 多用户**权限管理**

- 页面在线**二维码**生成

- 自定义页面**滚动条**

- **Editor.md**编辑器**粘贴**和**拖拽**图片上传

- **Docker**部署配置相关服务器组件

- **Nginx负载均衡**及安全策略配置

- 使用**华为云OBS对象存储**服务保存博客图片

- **Redis**实现**用户签到**功能，包含连续签到，总签到次数统计

- **Redis**缓存分类、标签、归档、留言、统计页数据

- **定时任务**持久化缓存数据

- 前端**响应式布局**，适应常见分辨率

#### 项目架构

- Spring Boot
    - Spring
    - SpringMVC
    - Hibernate
        - JPA
    - Mybatis
        - alibaba druid
    - Thymeleaf
        - SemanticUI
    - Docker
        - Nginx
        - MySQL
        - Redis
    - HuaweiCloud-OBS


#### 安装教程

1. 修改主配置文件**application.properties**中的配置文件环境为**spring.profiles.active=dev**

2. 删除配置文件中的**jasypt加密**内容，替换为本地端口、用户名、密码

3. sql文件在src/main/resources/mapper/sql中，数据库名默认**zmblog**

4. 项目使用**redis**缓存，请确保本地缓存开启，相关配置在**dev**环境配置文件中

5. 网站后台登录默认管理账号**用户名:admin 密码:admin**

#### 参与贡献

1.  Zm-Mmm 邮箱: zm-mmm@foxmail.com QQ: 1456133139