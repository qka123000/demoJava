# zookeeper是什么

## 工作机制

- 基于观察者模式的分布式服务管理框架,负责存储大家都关心的数据

- 接收观察者的注册,公共数据发生变化,zk通知已经注册的观察者做出相应的反映,从而实现类似主从的管理模式;

## zookeeper特点

- 一个领导者(leader),多个跟随者(follower)组成的server集群;

- leader负责更新系统状态

- follower负责接收客户端请求和返回结果,集群中有半数节点以上存活(等于半数也不能工作)zookeeper就能正常工作,所以部署zookeeper最好是奇数台server;

- 每个server保存一份相同的副本,client无论连接哪个server都是一样的

- 数据更新具有原子性,一次数据要么成功要么全都失败

## zookeeper的数据结构

- 数据结构为属性结构,每个树结点成为一个znode
- 每个znode默认能存1M数据;
- 每个znode都可以通过其路径唯一标识

## 应用场景

- 统一命名服务:类似域名和ip对应关系,客户端请求域名由zookeeper分发请求到域名下的ip主机进行处理
- 统一配置管理:配置文件修改后快速同步到各个节点,可以交由zookeeper实现,配置信息写入znode,节点监听znode
- 统一集群管理:zookeeper可监听节点的实时状态
- 服务器动态节点上下线:
- 软负载均衡:

## 官网

zookeeper.apache.org

# 本地模式安装

## 安装前准备

- 传tar包

```sh
## 用rz命名上传tar包到/data/home/installs
[root@localhost114 installs]# rz
rz waiting to receive.
Starting zmodem transfer.  Press Ctrl+C to cancel.
Transferring zookeeper-3.4.12.tar.gz...
  100%   35808 KB    11936 KB/sec    00:00:03       0 Errors  
```

- 解压

```sh
[root@localhost114 installs]# tar -xf zookeeper-3.4.12.tar.gz 
[root@localhost114 installs]# ls
zookeeper-3.4.12  zookeeper-3.4.12.tar.gz
```



## 修改配置

- 重命名配置文件zoo_simple.cfg > zoo.cfg

```sh
[root@localhost114 conf]# mv zoo_sample.cfg zoo.cfg
[root@localhost114 conf]# 
```

- 修改dataDir

```sh
## 新建data目录
[root@localhost114 zookeeper-3.4.12]# mkdir data
[root@localhost114 zookeeper-3.4.12]# cd data
[root@localhost114 data]# pwd
/data/installs/zookeeper-3.4.12/data

## 修改配置文件
[root@localhost114 conf]# vi zoo.cfg
# the directory where the snapshot is stored.
# do not use /tmp for storage, /tmp here is just
# example sakes.
#dataDir=/tmp/zookeeper
dataDir=/data/installs/zookeeper-3.4.12/data
```



## 操作zookeeper

- 启动zk server

```sh
## zk安装目录,bin下执行 ./zkServer.sh start
[root@localhost114 bin]# ./zkServer.sh start
ZooKeeper JMX enabled by default
Using config: /data/installs/zookeeper-3.4.12/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
[root@localhost114 bin]# 
```

- jps查看进程
- 启动客户端命令行

```sh

```



- 退出客户端
- 停止zk server

## zk参数

- tickTime=2000 : 通信心跳数,server
- initLimit=10 : LF初始化通信时间
- syncLimit = 5 : LF同步通信时限;
- dataDir 数据文件目录
- clientPort = 2181 客户端连接端口;

## zk选举机制

- zk配置文件中没有指定master,但是zk工作时会有一个节点临时担任leader
- zk选举过程,首先服务器1启动,投自己一票,一共五台机器,只有一票发现不够半数继续等待,第二台机器启动,先投自己一票,第一台机器把票也投给第二台,但是发现还是不够半数,则这两台都等待,第三台机器启动,投自己一票整个无法启动,第二台机器把这两票都投给第三台,第三台过半,zk可以工作,leader选举完成,第四台机器启动发现有leader则作为follower工作
- zk的leader总是中间那台机器;

## zk节点类型

- 短暂节点:客户端和服务端断开,节点删除,则为短暂节点
- 持久节点:客户端和服务端断开,节点不删除,则为持久节点;

分布式安装和部署

- 集群规划:在hdp001,hdp002,hdp003 三个节点部署三个zk server
- 配置参数

