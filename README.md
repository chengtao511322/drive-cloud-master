# drive-cloud 微服务系统

## 项目介绍
**drive-cloud**是一款微服务系统，采用前后端分离模式， 后台采用Spring cloud Alibaba作为微服务框架，基于OAuth2 的RBAC权限管理，集成Sentinel从流量控制、熔断降级、系统负载等多个维度保护服务的稳定性，采用Nacos作为注册中心和配置中心。

## 演示地址
暂无，需要自己clone到本地运行。

## 技术选型
- Spring cloud Alibaba
- Spring Security oauth2
- Nacos
- Sentinel
- Mybatis Plus
- minio
- knife4j
- lombok
- mapstruct
- redis
- lombok

### 功能介绍
- 用户管理: 提供系统用户的管理
- 角色管理: 分配角色的菜单权限，可根据部门设置角色的数据权限
- 菜单管理: 配置多级菜单，结合前端的动态路由管理
- 部门管理: 管理公司部门、子部门
- 岗位管理: 管理各个部门的职位
- 字典管理: 维护一些常用固定的数据，如：状态、性别
- 终端管理: oauth2的终端管理
- 登录日志: 记录系统用户的登录日志
- 操作日志: 记录系统用户的操作日志
- 接口文档: 可在线调试接口，方便开发测试
- 代码生成: 采用AutoGenerator代码生成器，减少重复代码开发

## 本地运行
```

# 环境安装
1.Nacos
2.Sentinel
3.Minio
4.redis
修改配置文件中各组件的连接地址

# 启动运行项目
请按下面顺序启动顺序
1.driveCloudGatewayApplication
2.driveloudAuthApplication
3.driveCloudSystemApplication
4.driveCloudMintorApplication
5.driveCloudOssApplication
```

### 项目结构说明
```
drive-cloud
    |----drive-cloud-auth                     认证模块
    |----drive-cloud-common
        |----drive-cloud-common-core          通用核心模块
        |----drive-cloud-common-data          数据模块
        |----drive-cloud-common-datascope     数据权限
        |----drive-cloud-common-log           日志处理
        |----drive-cloud-common-redis         缓存模块
        |----drive-cloud-common-security      资源服务器
        |----drive-cloud-common-swagger       接口文档
    |----drive-cloud-gateway                  网关模块
    |----drive-cloud-modules
        |----drive-cloud-generate             代码生成
        |----drive-cloud-monitor              日志模块
            |----drive-cloud-monitor-api      日志服务API
            |----drive-cloud-monitor-service  日志服务
        |----drive-cloud-oss                  对象存储服务模块
            |----drive-cloud-oss-api          对象存储API
            |----drive-cloud-oss-service      对象存储服务
        |----drive-cloud-system               系统模块
            |----drive-cloud-system-api       系统服务API
            |----drive-cloud-system-service   系统服务
```





| 配置参数   | 说明 | 备注 |
| :- | :- | :-  |
| baseProjectPath       | 生成文件所在项目路径     |     |
| srcJavaPath           | 文件存放路径     |     |
| basePackage           | 基础包路径     |     |
| authorName            | 代码作者名称    |     |
| driverName            | 数据库驱动     |     |
| url                   | 数据库连接地址     |     |
| username              | 数据库用户名     |     |
| password              | 数据库密码     |     |
| fileOverride          | 是否覆盖文件     |     |
| prefix                | 表前缀名称     |     |
| modulesName           | 模块名称     |     |
| tables                | 要生成的表名     |     |

注意: 代码生成是直接对数据库表直接读取，没有对字段进行细化区分，生成的前端代码与实际业务需求不符，需要自己手动修改相应页面显示数据与表单类型，来实现自己的实际业务需求。


## 演示图




//被注释的元素，值必须是一个字符串，不能为null，且调用trim()后，长度必须大于0
@NotBlank(message = "")

//被注释的元素，值不能为null,但可以为"空"，用于基本数据类型的非空校验上，而且被其标注的字段可以使用 @size、@Max、@Min 等对字段数值进行大小的控制
@NotNull(message = "")

//被注释的的元素，值不能为null，且长度必须大于0，一般用在集合类上面
@NotEmpty(message = "")

//被注释的元素必须符合指定的正则表达式。
@Pattern(regexp = "", message = "")

//被注释的元素的大小必须在指定的范围内。
@Size(min =, max =)

//被注释的元素，值必须是一个数字，且值必须大于等于指定的最小值
@Min(value = long以内的值, message = "")

//被注释的元素，值必须是一个数字，且值必须小于等于指定的最大值
@Max(value = long以内的值, message = "")

//被注释的元素，值必须是一个数字，其值必须大于等于指定的最小值
@DecimalMin(value = 可以是小数, message = "")

//被注释的元素，值必须是一个数字，其值必须小于等于指定的最大值
@DecimalMax(value = 可以是小数, message = "")

//被注释的元素，值必须为null
@Null(message = "")

//被注释的元素必须是一个数字，其值必须在可接受的范围内
@Digits(integer =, fraction =)

//被注释的元素，值必须为true
@AssertTrue(message = "")

//被注释的元素，值必须为false
@AssertFalse(message = "")

//被注释的元素必须是一个过去的日期
@Past(message = "")

//被注释的元素必须是一个将来的日期
@Future(message = "")

//被注释的元素必须是电子邮件地址
@Email(regexp = "", message = "")
//被注释的元素必须在合适的范围内
@Range(min =, max =, message = "")

//被注释的字符串的大小必须在指定的范围内
@Length(min =, max =, message = "")




# 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程
# 不要设置过大，如果过大，启动项目会报错：打开文件数过多

server.undertow.io-threads=16

# 阻塞任务线程池, 当执行类似servlet请求阻塞IO操作, undertow会从这个线程池中取得线程
# 它的值设置取决于系统线程执行任务的阻塞系数，默认值是IO线程数*8

server.undertow.worker-threads=256

# 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理
# 每块buffer的空间大小,越小的空间被利用越充分，不要设置太大，以免影响其他应用，合适即可

server.undertow.buffer-size=1024

# 每个区分配的buffer数量 , 所以pool的大小是buffer-size * buffers-per-region

server.undertow.buffers-per-region=1024

# 是否分配的直接内存(NIO直接分配的堆外内存)

server.undertow.direct-buffers=true