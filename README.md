# drive-cloud 微服务系统
mvn  clean package -D maven.test.skip=true
## 项目介绍
**drive-cloud**是一款微服务系统，采用前后端分离模式， 后台采用Spring cloud Alibaba作为微服务框架，基于OAuth2 的RBAC权限管理，集成Sentinel从流量控制、熔断降级、系统负载等多个维度保护服务的稳定性，采用Nacos作为注册中心和配置中心。

作者：郭正洋 框架问题联系 15116101123  1078823721

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
- mysql5.0
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

业务服务Application
```

### 项目结构说明
```


|----drive-cloud
    
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
		
		|----drive-cloud-admin               老系统模块
            |----drive-cloud-admin-api       老系统服务API
            |----drive-cloud-admin-service   老系统服务
		|----drive-cloud-marketing             活动营销模块
            |----drive-cloud-marketing-api       活动营销模块API
            |----drive-cloud-marketing-service   活动营销模块服务
		|----drive-cloud-basics             基础模块
            |----drive-cloud-basics-api       基础模块API
            |----drive-cloud-basics-service   基础模块服务
=
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

分布式配置中心：（所有项目的配置文件都在nacos上面）
内网：http://125.0.8.191:6648/nacos
生产：http://47.108.95.60:6688/nacos


## 验证框架使用




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




Google的规范里，单行可以不加大括号：Short conditional statements may be written on one line if this enhances readability. You may use this only when the line is brief and the statement does not use the else clause.if (x == kFoo) return new Foo();
if (x == kBar) return new Bar();
......In general, curly braces are not required for single-line statements, but they are allowed if you like them; conditional or loop statements with complex conditions or statements may be more readable with curly braces. Some projects require that an if must always always have an accompanying brace.if (condition)
  DoSomething();  // 2 space indent.

if (condition) {
  DoSomething();  // 2 space indent.
}

Optional.ofNullable(driveSchoolService.getById(item.getSchoolId())).ifPresent(u ->{item.setSchoolName(u.getSchoolName())});


Optional.ofNullable(coachInfoVo).orElseThrow(()-> new BizException("取指错误"));

方法	描述
of	把指定的值封装为Optional对象，如果指定的值为null，则抛出NullPointerException
empty	创建一个空的Optional对象
ofNullable	把指定的值封装为Optional对象，如果指定的值为null，则创建一个空的Optional对象
get	如果创建的Optional中有值存在，则返回此值，否则抛出NoSuchElementException
orElse	如果创建的Optional中有值存在，则返回此值，否则返回一个默认值
orElseGet	如果创建的Optional中有值存在，则返回此值，否则返回一个由Supplier接口生成的值
orElseThrow	如果创建的Optional中有值存在，则返回此值，否则抛出一个由指定的Supplier接口生成的异常
filter	如果创建的Optional中的值满足filter中的条件，则返回包含该值的Optional对象，否则返回一个空的Optional对象
map	如果创建的Optional中的值存在，对该值执行提供的Function函数调用
flagMap	如果创建的Optional中的值存在，就对该值执行提供的Function函数调用，返回一个Optional类型的值，否则就返回一个空的Optional对象
isPresent	如果创建的Optional中的值存在，返回true，否则返回false
ifPresent	如果创建的Optional中的值存在，则执行该方法的调用，否则什么也不做


java -Xmx3550m -Xms3550m -Xmn2g -Xss128k
-Xmx3550m：设置JVM最大可用内存为3550M。
-Xms3550m：设置JVM促使内存为3550m。此值可以设置与-Xmx相同，以避免每次垃圾回收完成后JVM重新分配内存。
-Xmn2g：设置年轻代大小为2G。整个JVM内存大小=年轻代大小 + 年老代大小 + 持久代大小。持久代一般固定大小为64m，所以增大年轻代后，将会减小年老代大小。此值对系统性能影响较大，Sun官方推荐配置为整个堆的3/8。
-Xss128k：设置每个线程的堆栈大小。JDK5.0以后每个线程堆栈大小为1M，以前每个线程堆栈大小为256K。更具应用的线程所需内存大小进行调整。在相同物理内存下，减小这个值能生成更多的线程。但是操作系统对一个进程内的线程数还是有限制的，不能无限生成，经验值在3000~5000左右。
java -Xmx3550m -Xms3550m -Xss128k -XX:NewRatio=4 -XX:SurvivorRatio=4 -XX:MaxPermSize=16m -XX:MaxTenuringThreshold=0
-XX:NewRatio=4:设置年轻代（包括Eden和两个Survivor区）与年老代的比值（除去持久代）。设置为4，则年轻代与年老代所占比值为1：4，年轻代占整个堆栈的1/5
-XX:SurvivorRatio=4：设置年轻代中Eden区与Survivor区的大小比值。设置为4，则两个Survivor区与一个Eden区的比值为2:4，一个Survivor区占整个年轻代的1/6
-XX:MaxPermSize=16m:设置持久代大小为16m。
-XX:MaxTenuringThreshold=0：设置垃圾最大年龄。如果设置为0的话，则年轻代对象不经过Survivor区，直接进入年老代。对于年老代比较多的应用，可以提高效率。如果将此值设置为一个较大值，则年轻代对象会在Survivor区进行多次复制，这样可以增加对象再年轻代的存活时间，增加在年轻代即被回收的概论。



### 分布式事务使用
项目对应的配置文件加入
事务管理器地址
本地Tm:125.0.8.191:8070  对应的管理后台 http://125.0.8.191:7970/
生产Tm：172.24.86.62:8070  对应的管理后台 http://47.108.95.60:7970
 
 txManagerPassword = codingapi

tx-lcn:
  client:
    manager-address: 125.0.8.191:8070
  logger:
    enabled: true

项目启动类加上注解
/**TC开启分布式事务注解**/
@EnableDistributedTransaction

在对应产生分布式事务方法上面加入

@LcnTransaction //分布式事务注解

具体方法参考 drive-cloud-admin 项目 里面的 AccountController -increaseAmount方法


# 分布式锁使用
该项目采用redis实现分布式锁，需要注意锁续命问题 以及，如果宕机后锁释放，后续改为zookerper实现分布式锁
// 上锁
RedisLock.tryGetDistributedLock()
// 解锁
RedisLock.releaseDistributedLock()

##Redis 分布式锁的缺点
其实上⾯那种⽅案最⼤的问题，就是如果你对某个 redis master 实例，写⼊了 myLock 这种锁 
key 的 value，此时会异步复制给对应的 master slave 实例。 
但是这个过程中⼀旦发⽣ redis master 宕机，主备切换，redis slave 变为了 redis master。 
接着就会导致，客户端 2 来尝试加锁的时候，在新的 redis master 上完成了加锁，⽽客户端 1 
也以为⾃⼰成功加了锁。 
此时就会导致多个客户端对⼀个分布式锁完成了加锁。 
这时系统在业务语义上⼀定会出现问题，导致各种脏数据的产⽣。 
所以这个就是 redis cluster，或者是 redis master-slave 架构的主从异步复制导致的 redis 分布式 
锁的最⼤缺陷：在 redis master 实例宕机的时候，可能导致多个客户端同时完成加锁。

 

### 手写Lock锁使用
DriveLock driveLock = new DriveLock();
// 上锁
driveLock.lock();
// 解锁
driveLock.unlock();

案例参考 
DriveThreadLock 类中的使用

#幂等性解决方案

对于每个请求必须有⼀个唯⼀的标识，举个栗⼦：订单⽀付请求，肯定得包含订单 id，⼀
个订单 id 最多⽀付⼀次，对吧。
每次处理完请求之后，必须有⼀个记录标识这个请求处理过了。
常⻅的⽅案是在 mysql 中 记录个状态啥的（Mysql乐观锁），
⽐如⽀付之前记录⼀条这个订单的⽀付流⽔。
每次接收请求需要进⾏判断，判断之前是否处理过。
⽐如说，如果有⼀个订单已经⽀付 了，就已经有了⼀条⽀付流⽔，
那么如果重复发送这个请求，则此时先插⼊⽀付流⽔， 
orderId 已经存在了，
唯⼀键约束⽣效，报错插⼊不进去的。然后你就不⽤再扣款了。

