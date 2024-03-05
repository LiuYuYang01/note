# Docker



## 快速入门

### 卸载旧版

首先如果系统中已经存在旧的Docker，则先卸载：

```Shell
yum remove docker \
    docker-client \
    docker-client-latest \
    docker-common \
    docker-latest \
    docker-latest-logrotate \
    docker-logrotate \
    docker-engine
```



### 配置Docker的yum库

首先要安装一个yum工具

```Bash
yum install -y yum-utils
```

安装成功后，执行命令，配置Docker的yum源：

```Bash
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
```



### 安装Docker

最后，执行命令，安装Docker

```Bash
yum install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```



### 启动和校验

```Bash
# 启动Docker
systemctl start docker

# 停止Docker
systemctl stop docker

# 重启
systemctl restart docker

# 设置开机自启
systemctl enable docker

# 执行docker ps命令，如果不报错，说明安装启动成功
docker ps
```



### 配置镜像加速

这里以阿里云镜像加速为例。



#### 注册阿里云账号

首先访问阿里云网站:

https://www.aliyun.com/

注册一个账号。



#### 开通镜像服务

在首页的产品中，找到阿里云的**容器镜像服务**：

![img](./image/1706623108745-4.png)

点击后进入控制台：

![img](./image/1706623108729-1.png)

首次可能需要选择立刻开通，然后进入控制台。



#### 配置镜像加速

找到**镜像工具**下的**镜像** **加速器**：

![img](./image/1706623108730-2.png)

页面向下滚动，即可找到配置的文档说明：

![img](./image/1706623108730-3.png)

具体命令如下：

```Bash
# 创建目录
mkdir -p /etc/docker

# 复制内容，注意把其中的镜像加速地址改成你自己的
tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://xxxx.mirror.aliyuncs.com"]
}
EOF

# 重新加载配置
systemctl daemon-reload

# 重启Docker
systemctl restart docker
```



**配置Docker自启动**

默认情况下，每次重启虚拟机我们都需要手动启动Docker和Docker中的容器。通过如下命令可以实现开机自启：

```PowerShell
# Docker开机自启
systemctl enable docker

# Docker容器开机自启
docker update --restart=always [容器名/容器id]
```



### 创建容器

#### 部署MySQL

首先，我们利用 `Docker` 来安装一个 `MySQL` 软件，大家可以对比一下之前传统的安装方式，看看哪个效率更高一些。

如果是利用传统方式部署 `MySQL`，大概的步骤有：

- 搜索并下载 `MySQL` 安装包
- 上传至 `Linux` 环境
- 编译和配置环境
- 安装

而使用 `Docker` 安装，仅仅需要一步即可，在命令行输入下面的命令（建议采用CV大法）：

```PowerShell
docker run -d \
  --name mysql \
  -p 3306:3306 \
  -e TZ=Asia/Shanghai \
  -e MYSQL_ROOT_PASSWORD=123 \
  mysql
```

运行效果如图：

![img](./image/1706623711430-13.png)

MySQL 安装完毕！通过任意客户端工具即可连接到 MySQL.



#### 命令解读

利用Docker快速的安装了MySQL，非常的方便，不过我们执行的命令到底是什么意思呢？

```PowerShell
docker run -d \
  --name mysql \
  -p 3306:3306 \
  -e TZ=Asia/Shanghai \
  -e MYSQL_ROOT_PASSWORD=123 \
  mysql
```

**解读：**

- `docker run -d` ：创建并运行一个容器，`-d`则是让容器以后台进程运行
- `--name mysql ` : 给容器起个名字叫`mysql`，你可以叫别的
- `-p 3306:3306` : 设置端口映射。
  - **容器是隔离环境**，外界不可访问。但是可以**将宿主机的端口映射到容器内的端口**，当访问宿主机指定端口时，就是在访问容器内的端口了。
  - 容器内端口往往是由容器内的进程决定，例如MySQL进程默认端口是3306，因此容器内端口一定是3306；而宿主机端口则可以**任意指定**，一般与容器内保持一致。
  - 格式： `-p 宿主机端口:容器内端口`，示例中就是将宿主机的3306映射到容器内的3306端口
- `-e TZ=Asia/Shanghai` : 配置容器内进程运行时的一些参数
  - 格式：`-e KEY=VALUE`，KEY和VALUE都由容器内进程决定
  - 案例中，`TZ=Asia/Shanghai`是设置时区；`MYSQL_ROOT_PASSWORD=123`是设置MySQL默认密码
- `mysql` : 设置**镜像**名称，Docker会根据这个名字搜索并下载镜像
  - 格式：`REPOSITORY:TAG`，例如`mysql:8.0`，其中`REPOSITORY`可以理解为镜像名，`TAG`是版本号
  - 在未指定`TAG`的情况下，默认是最新版本，也就是`mysql:latest`

镜像的名称不是随意的，而是要到 [Docker镜像仓库](https://hub.docker.com/) 中寻找

镜像运行时的配置也不是随意的，要参考镜像的帮助文档，这些在镜像仓库或者软件的官方网站中都能找到。

如果我们要安装其它软件，也可以到镜像cang'k中寻找对应的镜像名称和版本，阅读相关配置即可。



## 镜像

### 下载镜像

```shell
# 下载最新版本
docker pull mysql

# 下载指定版本
docker pull mysql:8.0.27
```

如果镜像后面不加版本号，则为 `letest` 表示最新版本



### 构建镜像

```shell
docker build -t 镜像名称 .

# 如果Dockerfile在上一级目录中
docker build -t 镜像名称 -f ../Dockerfile .
```

 `-t` 表示指定镜像名称

`-f` 表示指定 `Dockerfile` 路径，如果在当前目录中可以省略 `-f`



### 删除镜像

```
docker rmi 镜像名称
```



### 保存镜像

将镜像保存到本地：方便备份、离线使用、共享和节省下载时间

```shell
# docker save -o 镜像包名 镜像名称
docker save -o mysql.tar mysql

# 也可以将镜像保存到指定位置
docker save -o /root/images/mysql.tar mysql
```



### 导入镜像

```shell
# docker load --input 镜像名
docker load --input mysql.tar
```



### 查看镜像

查看宿主机中下载的所有镜像

```shell
docker images
```



### 提交镜像

用于将镜像推送到 `Docker` 官方仓库中

```shell
docker push
```



## 容器

### 运行并创建容器

```shell
# docker run -d --name 容器名称 -p 宿主机端口:容器端口 镜像名称
docker run -d --name nginx -p 80:80 nginx
```



### 查看运行中的容器

```
docker ps
```



### 启动容器

```shell
docker start 容器名称
```



### 停止镜像

```shell
docker stop 容器名称
```



### 重启容器

```shell
docker restart 容器名称
```



### 删除容器

```shell
docker rm 容器名称或ID
```



### 进入容器

```shell
docker exec -it <容器ID或容器名称> bash
```



### 查看容器日志

```shell
docker logs 容器名称
```



### 查看容器信息

```
docker container inspect 容器名称
```



不过一般使用以下命令查看容器的详细信息，因为该方法不仅可以查看容器的，并且还能查看镜像的详细信息

```shell
docker inspect 容器名称
```

如果容器与镜像重名，那么默认优先查看的是容器详细信息



## 网络

在 Docker 中使用网络是为了实现容器之间的通信和与外部网络的连接。网络在 Docker 中的应用场景非常广泛，比如容器间通信、多主机通信、与外部网络的连接



默认情况下每个容器都有一个虚拟的网络IP，但值并不固定与某一个容器绑定，如果我们在开发时写死某个IP，而在部署时很可能MySQL容器的IP会发生变化，从而导致连接失败。所以可以借助 `Docker` 网络来解决这个问题，只要在一个网络中，多个容器之间就能互相访问。下面是网络的常用命令



### 创建网络

```shell
docker network create 网络名称
```



### 删除网络

```shell
docker network rm 网络名称
```



### 清除网络

清除所有未使用的网络

```shell
docker network prune
```



### 连接网络

```shell
docker network connect 网络名称 容器名称

# 也可以通过--alias给网络取个别名
docker network connect 网络名称 容器名称 --alias 网络别名
```



### 断开网络

```shell
docker network disconnect 网络名称 容器名称
```



### 查看网络信息

```shell
docker network inspect 网络名称
```



### 查看所有网络

```shell
docker network ls
```



### 多容器互相访问

只要多个容器在同一个网络中就能实现互相访问，比如 `MySQL` 与 `Thrive` 容器的网络都为 `lyy` ，那么他们都可以连接 `lyy` 这个网络

```python
# 直接通过虚拟机IP连接
SQLALCHEMY_DATABASE_URI = 'mysql://root:123@192.168.92.140:3306/thrive'

# 通过lyy网络的IP连接
SQLALCHEMY_DATABASE_URI = 'mysql://root:123@172.18.0.1:3306/thrive'

# 通过容器的IP连接
SQLALCHEMY_DATABASE_URI = 'mysql://root:123@172.18.0.2:3306/thrive'

# 通过容器名连接（推荐）
SQLALCHEMY_DATABASE_URI = 'mysql://root:123@Mysql:3306/thrive'
```

上面四种方式我们更推荐使用容器名连接，因为这样的话我们不需要知道网络的IP多少，不管他怎么变，都是这个容器的IP



## 数据卷

**为什么要使用数据卷？**

使用数据卷可以实现容器和宿主机之间的数据共享和持久化，并且方便在宿主机上修改容器中的数据，提高开发和部署的便利性。



**典型应用场景**

1. 修改容器内的数据比较麻烦，需要先进入到容器才能修改，这时可以使用数据卷，将容器中的数据映射到宿主机，从而实现数据绑定，这样不管修改了容器内的数据还是映射后的宿主机数据，彼此都会双向修改
2. 当容器被删除后，数据自然就没有了，如果我们想要实现数据持久化，也可以使用数据卷，这样当容器删除后数据仍然保留



### 创建数据卷

创建的数据卷默认存放位置在：`/var/lib/docker/volumes/`

```shell
docker volume create 数据卷名称
```



在创建容器时候使用这个数据卷

```shell
# docker run -d --name 容器名称 -p 5000:5000 数据卷名称:容器内需要映射的路径 镜像名称
docker run -d --name thrive -p 5000:5000 thrive:/thrive thrive
```



其实在创建容器时候给他指定 `-v 数据卷名称` 能够自动创建数据卷，推荐使用这种方式

```shell
docker run -d --name thrive -p 5000:5000 -v thrive:/thrive thrive
```



### 删除数据卷

```shell
docker volume rm 数据卷名称
```



### 清除数据卷

通过该命令能够删除所有未使用的数据卷

```shell
# 默认情况下执行该命令会有交互，需要输入y才能清除
docker volume prune

# 在后面加上-f可以直接清除
docker volume prune -f
```



### 查看数据卷信息

```she
docker volume inspect 数据卷名称
```



### 查看所有数据卷

```
docker volume ls
```



### 本地数据卷



## Dockerfile

`Dockerfile` 是用于构建 `Docker` 镜像的文本文件。它包含了一系列指令和配置，通过这些指令可以自动化地构建、配置和部署 `Docker` 容器。



下面是 `Dockerfile `的一些常见指令和使用方法：

1. FROM：指定基础镜像，例如：`FROM ubuntu:latest`。
2. RUN：在镜像中执行命令。例如：`RUN apt-get update && apt-get install -y python`。
3. COPY：将文件从宿主机复制到镜像中。例如：`COPY app.py /app/`。
4. WORKDIR：设置工作目录。例如：`WORKDIR /app/`。
5. EXPOSE：声明容器运行时的监听端口。例如：`EXPOSE 80`。
6. CMD：指定容器启动时执行的命令。通常用于设置应用程序的启动命令。例如：`CMD [“python”, “app.py”]`。



我们可以选择指定系统镜像如何一步一步来，不过比较麻烦，需要配置很多东西。也可以选择直接使用别人打包好的镜像，这样就可以省去很多配置

![image-20240206182755857](./image/image-20240206182755857.png)



### Java

```shell
# 设置基础镜像
FROM openjdk:11.0-jre-buster

# 设置工作目录
WORKDIR /thrive

# 将jar包复制到工作目录中并拷贝给app.jar
COPY student.jar /thrive/app.jar

# 暴露容器端口号，不写表示所有
EXPOSE 5555

# 创建容器成功做的事情,等价于：java -jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```



### Python

```shell
# 设置基础镜像
FROM python:3.10

# 设置工作目录
WORKDIR /thrive

# 将当前目录中所有文件复制到指定目录中
COPY . /thrive

# 构建镜像时做的事情：下载相关依赖
RUN pip3 install -r requirements.txt -i https://mirrors.bfsu.edu.cn/pypi/web/simple/

# 暴露容器端口号
EXPOSE 5000

# 在容器创建成功后做的事情，相当于执行：python3 app.py
CMD ["python3", "app.py"]
```



### 前端



## 项目部署



## Portainer

Portainer 是一个开源的 Docker 可视化管理工具，它为用户提供了一种简单、直观的方式来管理和监控 Docker 容器、镜像和数据卷。



安装也非常简单，首先下载 `Portainer` 的 `Docker` 镜像；

```shell
docker pull portainer/portainer
```



接下来使用如下命令运行 `Portainer` 容器；

```shell
docker run -p 9000:9000 -p 8000:8000 --name portainer \
--restart=always \
-v /var/run/docker.sock:/var/run/docker.sock \
-v /mydata/portainer/data:/data \
-d portainer/portainer
```



假设我的虚拟机IP为：`192.168.92.141`，那么可以通过如下地址访问

```
http://192.168.92.141:9000/
```

第一次进入需要设置管理员的账号密码

设置完毕后进入到如下页面就证明OK了

![image-20240206202305353](./image/image-20240206202305353.png)



## 扩展

### 命令别名

如果一段命令过长，可以给这个命令起个别名，方便我们使用：

修改 `/root/.bashrc` 文件

```shell
# User specific aliases and functions

alias rm='rm -i'
alias cp='cp -i'
alias mv='mv -i'
# 核心
alias dps='docker ps --format "table {{.ID}}\t{{.Image}}\t{{.Ports}}\t{{.Status}}\t{{.Names}}"'
alias dis='docker images'

# Source global definitions
if [ -f /etc/bashrc ]; then
        . /etc/bashrc
fi
```



最后一定要注意 执行这个命令使别名生效

```PowerShell
source /root/.bashrc
```

