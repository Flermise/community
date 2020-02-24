##  社区论坛项目

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