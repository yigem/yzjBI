### 基于OpenAI的数据可视化平台

#### 项目成果

http://39.101.74.250:8088

账户：yzj123
密码：123456789

**登录页面**

![image](https://github.com/yigem/yzjBI/blob/main/tupian/denglu.png) 

**注册页面**

![image](https://github.com/yigem/yzjBI/blob/main/tupian/zhuce.png) 

**功能列表**

![image](https://github.com/yigem/yzjBI/blob/main/tupian/gongnengliebiao.png)

**智能分析（同步）**

![image](https://github.com/yigem/yzjBI/blob/main/tupian/tongbu.png) 

**因为智能分析（异步）是用的线程池实现的，结果和MQ一样，但是最大区别是线程池没有持久化的功能。**

**智能分析（MQ）**

![image](https://github.com/yigem/yzjBI/blob/main/tupian/mq.png)

**智能分析（MQ）生成中**

![image](https://github.com/yigem/yzjBI/blob/main/tupian/wodetubiao.png)

**智能分析（MQ）结果**

![image](https://github.com/yigem/yzjBI/blob/main/tupian/wodetubiaojieguo.png)

#### 新增功能

**形势与政策**

![image](https://github.com/yigem/yzjBI/blob/main/tupian/xingshizhengce.png)

**形势与政策分析结果**

![image](https://github.com/yigem/yzjBI/blob/main/tupian/xszcjieguo.png)

**AI模型**

![image](https://github.com/yigem/yzjBI/blob/main/tupian/ai.png)

**AI模型分析结果**

![image](https://github.com/yigem/yzjBI/blob/main/tupian/aijieguo.png) 

#### 项目介绍

商业智能BI：数据可视化，报表可视化系统

##### 传统BI平台：

https://chartcube.alipay.com/

1.需要人工上传数据

2.需要人工拖选分析要用到的数据行和列（数据分析师）

3.需要人工选择图表类型（数据分析师）

4.生成图表并保存配置

##### 智能BI（数据可视化）平台：

区别于传统的 BI，用户只需要导入最原始的数据集，输入想要进行分析的目标（比如帮我分析一下网站的增长趋势），就能利用 AI 自动生成一个符合要求的图表以及结论。

![image](https://github.com/yigem/yzjBI/blob/main/tupian/tongbu.png) 

**优点：**让不会数据分析的同学也能通过输入目标快速完成数据分析，节约人力成本，会用到 AI 接口。

#### 需求分析：

1.智能分析：用户输入目标和原始数据，可以自动生成图表和分析结论

2.图表管理

3.图表生成的异步化（消息队列）

4.对接 AI 能力

#### 架构图

##### 基础流程：

![image](https://github.com/yigem/yzjBI/blob/main/tupian/jichuliucheng.png) 

##### 优化流程（异步化）：

![image](https://github.com/yigem/yzjBI/blob/main/tupian/youhuahou.png) 

#### 技术栈

##### 前端

1.React + Ant Design Pro

2.可视化开发库（Echarts）

3.umi openapi 代码生成（自动生成后端调用代码）

##### 后端

1.SpringBoot

2.MySQL数据库

3.MyBatis Plus 框架

**4.消息队列（RabbitMQ）**

**5.AI 能力（Open AI 接口开发）**

6.Excel 的上传和数据的解析（Easy Excel）

7.Swagger + Knife4j 项目接口文档

8.Hutool 工具库

#### 一.前后端项目初始化

#### 二.智能分析业务开发

##### 业务流程

1.用户输入

- 分析目标
- 上传原始数据（excel）
- 控制图表：比如图表类型，图表名称等

2.后端校验

- 检验用户的输入是否合法（比如长度）
- 成本控制（次数统计和校验，鉴权等）

3.把处理后的数据输入给 AI 模型（调用 AI 接口），让 AI 模型给我们提供图表信息和结论文本

4.图表信息（是一段 json 配置，是一段代码），结论文本在前端进行展示

**原始数据压缩**

AI 接口普遍都要输入字数限制，尽可能压缩数据，能够允许多传一些数据

如何向 AI 提词？

① 持续输入，持续优化

如：第一次问：
我给你三行数据，请帮我分析网站的增长趋势，数据如下：
第一行：日期：1 号，用户数：10 人
第二行：日期：2 号，用户数：20 人
第三行：日期：3 号，用户数：30 人

AI 答： 根据给出的数据，我们可以看出这个网站的用户数在三天内呈现快速增长的趋势。具体来说，该网站用户数从第一天的10人增长到第二天的20人，增加了10人，增长率为100%；相比之下，第三天用户数从第二天的20人增长到第三天的30人，增加了同样的10人，增长率降至50%。如果这种增长趋势持续下去，我们可以预期该网站在未来几天内还会继续吸引更多用户。当然，若要得出更加准确的结论，需要更多数据的支持。

② 数据压缩（内容压缩，比如把很长的内容提取关键词，也可以让 AI 来做）

如：我给你三行数据，请帮我分析网站的增长趋势，数据如下：
表头：日期，用户数
1 号, 10
2 号, 20
3 号, 30

AI 回答： 根据给出的数据，我们可以计算出每日用户增长量：
●第一天增长了 10 个新用户；
●第二天增长了 20 - 10 = 10 个新用户；
●第三天增长了 30 - 20 = 10 个新用户。
可以看出，每天的用户增长量都相等，呈现一个线性增长趋势。如果这个网站在未来的时间里能够保持现在的增长速度和策略不变，预计每过一天会增加10个用户。但需要注意的是，这个预测是基于目前的数据情况，实际情况会受到许多因素的影响，不能完全准确预测未来用户增长。

**使用 AI 来压缩：**
问：我给你三行数据，请帮我用最少的字数压缩这段数据
第一行：日期：1 号，用户数：10 人 第二行：日期：2 号，用户数：20 人 第三行：日期：3 号，用户数：30 人
 答：日期1-3号，用户数分别为10/20/30人。

**使用 csv 对 excel 文件的数据进行提取和压缩**

开源库：https://easyexcel.opensource.alibaba.com/docs/current/



**调用 AI**

输入：系统预设（提前告诉他职责，功能，回复格式要求）+ 分析目标 + 压缩后的数据

最简单的系统预设：你是一个数据分析师，接下来我会给你我的分析目标和原始数据，请告诉我分析结论。

AI 提词技巧③： 在系统层面做预设效果一般来说，会比直接拼接在用户消息里效果更好一些。

![image](https://github.com/yigem/yzjBI/blob/main/tupian/diaoyongai.png) 

#### 三.智能分析业务流程开发

##### AI 生成内容方法

**生成结论**

> 示例问法：
>
> 你是一个数据分析师，请帮我分析网站用户的增长趋势
>
> 原始数据如下：
>
> 日期,用户数
>
> 1号,10
>
> 2号,20
>
> 3号,30

**生成图表**

AI 无法直接生成现成的图表，但是AI可以生成图表代码 => 可以把代码利用前端的组件库（Echarts）在网页进行展示

echarts 支持在线调试：https://echarts.apache.org/examples/zh/editor.html?c=line-simple

**AI 提问技巧**

如果想要让 AI 更好地理解我们的输入，给我们预期的，精确格式的输出，我们就需要严格控制提问词

① 使用系统预设

![image](https://github.com/yigem/yzjBI/blob/main/tupian/aiyushe.png) 

② 控制输入格式（便于 AI 精确地理解我们的需求）

> 比如：你是一个数据分析师和前端开发专家，接下来我会按照以下固定格式给你提供内容：
>
> 分析需求：
>
> {数据分析的需求或者目标}
>
> 原始数据：
>
> {csv格式的原始数据，用,作为分隔符}
>
> 请根据以上内容，帮我生成数据分析结论和可视化图表代码

③ 控制输出格式（便于 AI 返回的内容能够更加方便地为我们所用）

> 比如：你是一个数据分析师和前端开发专家，接下来我会按照以下固定格式给你提供内容：
>
> 分析需求：
>
> {数据分析的需求或者目标}
>
> 原始数据：
>
> {csv格式的原始数据，用,作为分隔符}
>
> 请根据这两部分内容，按照以下指定格式生成内容（此外不要输出任何多余的开头、结尾、注释）
>
> 【【【【【
>
> {前端 Echarts V5 的 option 配置对象js代码，合理地将数据进行可视化，不要生成任何多余的内容，比如注释}
>
> 【【【【【
>
> {明确的数据分析结论、越详细越好，不要生成多余的注释}

##### 智能接口实现

后端流程

1.构造用户请求（用户消息，csv数据，图表类型）

2.调用 AI，得到 AI 响应结果

3.从 AI 响应结果中，取出需要的信息

4.保存图表到数据库

##### 开发图表管理功能（增删改查）

##### 系统优化

现在的网站足够安全嘛？

① 如果用户上传一个超大的文件怎么办？（校验文件的大小）

② 如果用户用科技疯狂点击提交，怎么办？

③ 如果 AI 的生成太慢（比如需要一分钟），又有很多用户要同时生成，给系统造成了压力，怎么兼顾用户体验和系统的可用性？

**只要涉及到用户自主上传的操作，一定要校验文件（图像）**

校验的维度：

- 文件的大小
- 文件的后缀

##### 数据存储

现状：我们把每个图表的原始数据全部存放在同一个数据表（chart表）的字段里

出现的问题：

- 如果用户上传的原始数据量很大，图表数日益增多，查询Chart表就会很慢
- 对于 可视化平台，用户是有查看原始数据，对原始数据进行简单查询的需求的。现在如果把所有数据存放在一个字段（列）中，查询时，只能取出这个列的所有内容。

解决方案 => 分库分表：

把**每个图表对应的原始数据单独保存为一个新的数据表**，而不是都存放在一个字段里

![image](https://github.com/yigem/yzjBI/blob/main/tupian/xinshujuku.png) 

优点：

- 存储时，能够分开存储，互不影响（也能增加安全性）
- 查询时，可以使用各种sql语句灵活取出需要的字段，查询性能更快

##### 限流

现在的问题：使用系统是需要消耗成本的，用户有可能疯狂刷量，让你破产。

解决问题：

1. 控制成本 => 限制用户调用总次数
2. 用户在短时间内疯狂使用，导致服务器资源被占满，其他用户无法使用 => 限流

限流阈值多大合适？参考正常用户的使用，比如限制单个用户在每秒只能使用 1 次等。

**限流的实现**

① 本地限流（单机限流）

每个服务器单独限流，一般适用于单体项目，就是你的**项目只有一个服务器**

② 分布式限流（多机限流）（本项目使用分布式限流，主要是练习使用）

如果你的项目有多个服务器，比如微服务，那么建议使用分布式限流。

- 把用户的使用频率等数据放到一个集中的存储进行统计，比如 Redis，这样无论用户的请求落到了哪台服务器上，都以集中的数据存储内的数据为准（**Redisson -** 是一个操作 Redis 的工具库）
- 在网关集中进行限流和统计（Spring Cloud GateWay）（本项目没有使用）

####  系统问题分析

问题场景：调用的服务处理能力有限，或者接口的处理（或返回）时长较长时，就应该考虑异步化了

① 用户等待时间有点长（因为要等 AI 生成）

② 业务服务器可能会有很多请求在处理，导致系统资源紧张，严重时导致服务器宕机或者无法处理新的请求

③ 调用的第三方服务（AI 能力）的处理能力有限的，比如每 3 秒只能处理 1 个请求，会导致 AI 处理不过来，严重时 AI 可能会对咱们的后台系统拒绝服务。

##### 异步化

同步：一件事情做完，再做另外一件事情。

异步：不用等一件事做完，就可以做另外一件事情。等第一件事完成时，可以收到一个通知，**通知**你这件事做好了，你可以再进行后续处理。

##### 业务流程分析

**标准异步化的业务流程**

- 当用户要进行耗时很长的操作时，点击提交后，不需要在界面等，而是应该把这个**任务保存到数据库中记录下来。**
- 用户要执行新任务时：
  - 任务提交成功：
    - 如果我们的程序还有多余的空闲线程，可以立刻去做这个任务
    - 如果我们的程序的线程都在繁忙，无法继续处理，那就放到等待队列里
  - 任务提交失败：比如我们的程序所有线程都在忙，**任务队列满了**
    - 拒绝掉这个任务，再也不去执行。
    - 通过保存到数据库中的记录来看到提交失败的任务，并且在程序闲的时候，可以把任务从数据库中捞到程序里，再去执行。
  - 我们的程序（线程）从任务队列中取出任务依次执行，每完成一件事情要修改一下任务的状态
  - 用户可以查询任务的执行状态，或者在任务执行成功或失败时能得到通知，来优化体验
  - 如果我们要执行的任务非常复杂，包含很多环节，在每一个小任务完成时，要在程序（数据库中）记录一下任务的执行状态（进度）

**我们系统的业务流程**

1. 用户点击智能分析页的提交按钮时，先把图表立刻保存到数据库中（作为一个任务）
2. 用户可以在图表管理页面查看所有图表（已生成的，生成中的，生成失败）的信息和状态

**优化流程（异步化）：**

![image](https://github.com/yigem/yzjBI/blob/main/tupian/yibuhua.png) 

**问题：**

1. 任务 队列的最大容量应该设置为多少？
2. 程序怎么从任务队列中取出任务去执行？这个任务队列的流程怎么实现？怎么保证程序最多同时执行多少个任务？

**需要用到线程池**

为什么需要线程池？

1. 线程的管理比较复杂（比如什么时候新增线程，什么时候减少空闲线程）
2. 任务存取比较复杂（什么时候接收任务，什么时候拒接任务，怎么保证大家不抢到同一个任务）

**线程池的作用：帮助你轻松管理线程、协调任务的执行过程。**

#### 四.分析系统现状不足

> 单机系统的问题

已经经过了同步到异步的改造？

现状：目前的异步是通过 **本地** 的线程池实现的

① 无法集中限制，只能单机限制

假如 AI 服务限制只能有 2 个用户同时使用，单个线程池可以限制最大核心线程数为 2 来实现

假设系统用量增大，改为分布式，多台服务器，每个服务器都要有 2 个线程，就有可能有 2N 个线程，超过了 AI 服务的限制。

**解决方案：**在一个集中的地方去管理下发任务（比如集中存储当前正在执行的任务数）

② 任务由于是放在内存中执行的，可能会丢失

虽然可以人工从数据库捞出来再重试，但是其实需要额外开发（比如定时任务），这种重试的场景是非常典型的，其实是不需要我们开发者过于关心，或者自己实现的。

解决方案：把任务放在一个可以持久化存储的硬盘

③ 优化：如果你的系统功能越来越多，长耗时任务越来越多，系统会越来越复杂的（比如要开多个线程池，资源可能会出现项目抢占）。

服务拆分（应用解耦）：其实我们可以把长耗时，消耗很多的任务把它单独抽成一个程序，不要影响主业务。

解决方案：可以有一个中间人，让中间人帮我们去连接两个系统（比如核心系统和智能生成业务）

##### 消息队列的模型

![image](https://github.com/yigem/yzjBI/blob/main/tupian/xiaoxiduiliemoxing.png) 

##### 为什么要用消息队列？

① 异步处理

生产者发送完消息之后，可以继续去忙别的，消费者想什么时候消费都可以，不会产生阻塞

② 削峰填谷

先把用户的请求放到消息队列中，消费者（实际执行操作的应用）可以按照自己的需求，慢慢去取。

原来：12 点时来了10万个请求，原本情况下，10万个请求都在系统内部立即处理，很快系统压力过大就宕机了。

现在：把这 10 万个请求放到消息队列中，处理系统以自己的恒定速率（比如每秒 1 个）慢慢执行，从而保护系统，稳定处理。

##### 分布式消息队列的优势

① 数据持久化：它可以把消息集中存储到硬盘里，服务器重启就不会丢失。

② 可扩展性：可以根据需求，随时增加（或减少）节点，继续保持稳定的服务

③ 应用解耦：可以连接各个不同语言，框架开发的系统，让这些系统能够灵活传输读取数据。

##### 消息队列的缺点

① 要给系统引入额外的中间件，系统会更复杂、额外维护中间件、额外的费用（部署）成本
② 消息队列：消息丢失、顺序性、重复消费、数据的一致性（分布式系统就要考虑），也可以叫分布式场景下需要考虑的问题

**主流分布式消息队列选型**（本项目用 **rabbitmq** ）

官方网站：[https://www.rabbitmq.com](https://www.rabbitmq.com/getstarted.html)

#### RabbitMQ项目实战

**选择客户端**

怎么在项目中使用 RabbitMQ？

① 使用官方的客户端

优点：兼容性好，换语言成本低，比较灵活

缺点：太灵活，要自己去处理一些事情。比如要自己维护管理连接，很麻烦。

② 使用封装好的客户端，比如Spring Boot RabbitMQ Starter

优点：简单易用，直接配置直接用，更方便地去管理连接

缺点：封装的太好了，没学过的话反而不知道怎么用。不够灵活，被框架限制。

**因为项目是 Spring Boot 项目，所以使用 Spring Boot RabbitMQ Starter**

**https://spring.io/guides/gs/messaging-rabbitmq/**

##### 基础实战

① 引入依赖
注意，使用的版本一定要和你的 springboot 版本一致！！！！！！！

```java
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-amqp -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
    <version>2.7.2</version>
</dependency>

```

② 在 yml 中引入配置：

```java
spring:
    rabbitmq:
        host: localhost
        port: 5672
        password: guest
        username: guest
```

③ 创建交换机和队列

```java
/**
 * 用于创建测试程序用到的交换机和队列（只用在程序启动前执行一次）
 */
public class MqInitMain {

    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            String EXCHANGE_NAME = "code_exchange";
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            // 创建队列，随机分配一个队列名称
            String queueName = "code_queue";
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, EXCHANGE_NAME, "my_routingKey");
        } catch (Exception e) {

        }

    }
}
```

④ 生产者代码

```java
@Component
public class MyMessageProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchange, String routingKey, String message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}
```

⑤ 消费者代码

```java
@Component
@Slf4j
public class MyMessageConsumer {

    // 指定程序监听的消息队列和确认机制
    @SneakyThrows
    @RabbitListener(queues = {"code_queue"}, ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        log.info("receiveMessage message = {}", message);
        channel.basicAck(deliveryTag, false);
    }

}
```

⑥ 单元测试执行

```java
@SpringBootTest
class MyMessageProducerTest {

    @Resource
    private MyMessageProducer myMessageProducer;

    @Test
    void sendMessage() {
        myMessageProducer.sendMessage("code_exchange", "my_routingKey", "你好呀");
    }
}
```

##### BI 项目改造

以前是把任务提交到线程池，然后在线程池提交中编写处理程序的代码，线程池内排队。

如果程序中断了，任务就没了，就丢了。

**改造后的流程：**

1. 把任务提交改为向队列发送消息
2. 写一个专门的接收消息的程序，处理任务
3. 如果程序中断了，消息未被确认，还会重发
4. 现在，消息全部集中发到消息队列中，你可以部署多个后端，都从同一个地方取任务，从而实现了分布式负载均衡。

**实现步骤**

1. 创建交换机和队列
2. 将线程池中的执行代码移到消费者类中
3. 根据消费者的需求来确认消息的格式（chartId）
4. 将提交线程池改造为发送消息到队列

**验证**

验证发现，如果程序中断了，没有ack，也没有nack（服务中断，没有任何响应），那么这条消息会被重新放到消息队列中，从而实现了每个任务都会执行。











































