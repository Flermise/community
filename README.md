##  社区论坛项目
## 参考地址
[示例论坛](http://112.126.67.195)
## 快速运行
1. 安装必备工具  
JDK，Maven
2. 克隆代码到本地  
3. 运行命令创建数据库脚本
```sh
mvn flyway:migrate
```
4. 运行打包命令
```sh
mvn package
```
5. 运行项目(以后台方式 指定配置运行)  
```sh
nohup  java -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar /null &>/dev/null &

```
## 资料
[SpringWeb](https://spring.io/guides/gs/serving-web-content/)

## 工具
[FLS项目](https://github.com/Flermise/community)  
[Flyway](https://flywaydb.org/)

## 脚本
```sql
create table USER
(
    ID           INTEGER auto_increment,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);
```

```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
