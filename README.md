# 暖爪宠物店

Spring Boot + MyBatis-Plus + MySQL 的宠物店展示与预约示例项目。

## 运行

先配置数据库环境变量：

```powershell
$env:PET_SHOP_DB_URL="jdbc:mysql://mysql6.sqlpub.com:3311/tbsdb202605?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true"
$env:PET_SHOP_DB_USERNAME="你的数据库账号"
$env:PET_SHOP_DB_PASSWORD="你的数据库密码"
```

启动服务：

```powershell
mvn spring-boot:run
```

访问：

```text
http://localhost:8080
```

预约表单会提交到 `/api/bookings`，并通过 MyBatis-Plus 写入 MySQL 的 `bookings` 表。
