# Covid19-Real-time-dynamic

> **Involved: java crawler, mysql storage, Javascripte visualization, etc. .**

<br></br>

# 效果图

![](https://img-blog.csdnimg.cn/20210111094314770.png)


<br></br>

# 介绍/报告   


![](https://pic.imgdb.cn/item/60c23bf8844ef46bb21dc006.jpg)




<br></br>

# 简介

## 实现方式

**后端**：Mysql + Java

<p></p>

**前端**：Javascript + HTML + CSS 

<p></p>

**系统服务**：shell

<br></br>

## 开发环境
**系统** ：Ubuntu 18.04.5 LTS (Bionic Beaver)

<p></p>

**编辑器**：Vim & Vscode

<p></p>

**Java环境**：JDK15   

<p></p>

**JDBC驱动**：mysql-connector-java-5.1.39

<p></p>

**Mysql环境**：Mysql8.0

<br></br>

## 部署环境
**系统** ：Centos7.4

<p></p>

**编辑器**：Vim 

<p></p>

**Java环境**：JDK8

<p></p>

**Mysql环境**：Mysql5.1

<br></br>

# 需求分析


## 用户角度分析
>疫情数据准确

>疫情数据实时性高

>能直观看到现在疫情的情况

>能多角度的学习到疫情的现状


## 技术角度分析
> 疫情数据需要来源

>要有精确且有效数据

>数据需要存储到安全的地方

>存储的数据需要能被高效获取

>有效数据需要再分类成独立数据集

>数据集需要制作图表进行可视化

<br></br>

# 架构设计
## 总结构设计
<font color=#999AAA >  整体分成四个模块

![](https://img-blog.csdnimg.cn/img_convert/942dd64127bec94e95aeac5b969ba6f2.png)
**monitor模块**: 负责监听疫情动态数据，实现数据实时更新,保证数据的实时性，降低系统资源利用率

**Data mining模块**: 负责抓取所有与疫情相关的数据 并 挖掘出有用的数据，实现数据的“无中生有”，保证数据的准确性与有效性，降低数据冗余

**Database模块**： 负责存储Data mining模块生成的数据，实现数据的存储，保证数据的安全性、高可用性，降低数据的不稳定性

**Data visuallzation模块**: 负责将存储在Databases模块的有效数据 变成直观的数据，实现数据可视化，保证数据的可读性，降低某些数据被当作多余的可能

`monitor模块与其他模块区别存储/使用`

`monitor模块负责其它模块整体的使用及调度 `

<br></br>

## 数据挖掘模块设计
<font color=#999AAA >  Data mining模块分成三个功能区

![](https://img-blog.csdnimg.cn/20210109234148779.png#pic_center)

**Data collection**:负责数据的收集，收集疫情相关的数据

**Data cleaning**: 负责数据的清洗，清洗出有效数据

**Data storage**: 负责数据的存储，将数据存储到数据库

<br></br>

## 数据库模型设计
![](https://img-blog.csdnimg.cn/2021010923513676.png)
对Data mining模块存储的数据进行简单分类


<br></br>

## 数据可视化模块设计
<font color=#999AAA >  Data visualization模块分成三个功能区

![](https://img-blog.csdnimg.cn/20210109235324882.png#pic_center)

**Data Extraction**:负责数据的提取，从数据库中提取出数据，并进行简单处理

**Data Classification**:负责数据的分类处理，这是对疫情数据最后的一步处理

**Generate Webpage code**: 负责将分类过的数据，并生成web页面

<br></br>

## 监听模块设计
<font color=#999AAA >  Data mining模块分成两个组件，三个功能区

![](https://img-blog.csdnimg.cn/20210109235839153.png)

**data collection**: 负责数据的收集

**Data monitoring**: 负责监听data collectiong收集的数据，如果监听到数据发生变化，立即作出反应

**Process monitoring**: 负责监听Data monitoring组件，如果监听到该组件有所变化，立即执行整个项目，将上述架构实现

<br></br>

# 具体实现 
## 项目文件一览

使用的语言和文件分布、代码工程量：
![](https://img-blog.csdnimg.cn/20210110124437893.png)

文件目录：
![](https://img-blog.csdnimg.cn/20210110001040297.png)
**所有文件将会被打包成:**
	
	两个jar包:
	> COVID19.jar            文件:  index.java  目录：Data_mining  DATA_visualization
	> Data_monitoring.jar     文件：Data_monitoring.java 
	
	一套Linux Shell脚本:
	> Main: Monitor_process.sh      分支文件： runcovid.sh   COVID19UPDATE.sh
	
	一个Web访问目录:
	> 目下分支两个目录   */Log    */Run     
	> 包含已/待生成的Web文件： Form_theme.js   index.html     */Log/诸多日志文件
	> >
	> > index.html 文件为COVID19.jar将生成的文件   内包含已编写的两个远程Javascript文件，协助可视化的制作


​		



## 数据挖掘模块
文件目录:
![](https://img-blog.csdnimg.cn/20210110002545393.png#pic_center)
>`共分成五个类`

<br></br>

### mining_transfer类

**作用**：资源池 

**功能**：存储数据Data_mining模块中所有需要被使用的数据，并对外提供接口


<font color=#999AAA >  代码：

![](https://img-blog.csdnimg.cn/20210110003242499.png)

<br></br>

### Scraping类
**作用**：数据爬虫

**功能**：爬取网页JSON数据，并进行简单处理，将JSON数据变成一段连续的字符串，之后将这一次处理生成的数据记录下来

`因为疫情数据不能出现一点差错，所以仍和一次对数据的处理都需要存储为日志文件，提高可维护、可控性`

<font color=#999AAA >  包的使用：

![](https://img-blog.csdnimg.cn/20210110005337597.png)
<font color=#999AAA >  重要变量：

![](https://img-blog.csdnimg.cn/20210110005405809.png)
<font color=#999AAA >  简单正则处理去除特殊字符：

![](https://img-blog.csdnimg.cn/20210110115102661.png)


<font color=#999AAA > 构造方法 & 爬取JSON数据：

![](https://img-blog.csdnimg.cn/20210110005451434.png)


<font color=#999AAA > 生成日志文件：

![](https://img-blog.csdnimg.cn/20210110005536869.png)
<br></br>

### Cleaning 类
**作用**：数据清洗

**功能**：按数据的不同特征，清洗出有用数据数据

<font color=#999AAA > 包的使用：

![](https://img-blog.csdnimg.cn/20210110120009455.png)
<font color=#999AAA > 依旧需要将每一次处理都记录下来，方便后期维护：

![](https://img-blog.csdnimg.cn/20210110120320166.png)
<font color=#999AAA > 自编的一个正则处理的模板，能大大提高代码的整洁性，和程序的运行速度

![](https://img-blog.csdnimg.cn/20210110120611689.png)
<font color=#999AAA > 只需要拟定好需要的正则规则使用模版即可

示范：
![](https://img-blog.csdnimg.cn/20210110121045776.png)
这一段能将国内所有地区，包括其名称、累计确诊、治愈、死亡人数都找出

再例如：
![](https://img-blog.csdnimg.cn/20210110121310163.png)
这一段能把国内整体的一个疫情数据都找出
最后就是构造方法了：
![](https://img-blog.csdnimg.cn/20210110121551733.png)
<br></br>

### Submission类

**作用**：数据存储

**功能**：将需要存储的数据存储到Mysql数据库中，并对Mysql数据库进行简单管理

<font color=#999AAA > 包的使用：

![](https://img-blog.csdnimg.cn/20210110122030298.png)
<font color=#999AAA > 重要的变量，包含Mysql不同版本的连接方式，以及用户名和密码：

![](https://img-blog.csdnimg.cn/20210110122257372.png)
<font color=#999AAA >使用JDBC，编写sq语言l，对数据库表进行管理

![](https://img-blog.csdnimg.cn/20210110122406818.png)
<font color=#999AAA > 动态生成会变化部分的sql代码，使用sql语言将数据插入数据库中（仅示范部分）

![](https://img-blog.csdnimg.cn/20210110122540912.png)
<font color=#999AAA >构造方法，连接数据库，调度其它方法

![](https://img-blog.csdnimg.cn/20210110122927772.png)


<br></br>

### Run_mining类

**作用**：资源调度

**功能**：调度Data_mining模块中所有资源，包括类的使用、数据的传递

<font color=#999AAA >  代码：

![](https://img-blog.csdnimg.cn/20210110004056271.png)

<br></br>



### 小结

如果，数据挖掘模块刚爬取到的JSON数据是这样：
![](https://img-blog.csdnimg.cn/20210110123230375.png)
那么，数据挖掘模块完整使用后，在Mysql数据库中的数据为：
![](https://img-blog.csdnimg.cn/20210110123503312.png)


<br></br>

## 数据可视化模块
文件目录：
![](https://img-blog.csdnimg.cn/20210110125255217.png#pic_center)

>`共分成五个类`

<br></br>

### visualization_transfer类

**作用**：资源池 

**功能**：存储数据Data_visualization模块中所有需要被使用的数据，并对外提供接口

<font color=#999AAA >  代码：

![](https://img-blog.csdnimg.cn/20210110124859214.png)
<br></br>

### Extraction类

**作用**：数据提取

**功能**：提取出Mysql数据库中需要的数据，存储到资源池中
`和Data_mining模块的Submissionl类的许多相似功能不再赘述`

<font color=#999AAA > 包的使用：

![](https://img-blog.csdnimg.cn/20210110125129716.png)
<font color=#999AAA > 对Mysql表进行最大限制，并提取其表名：

![](https://img-blog.csdnimg.cn/20210110125902478.png)
<font color=#999AAA > 根据表名，提取出其存储的数据：

![](https://img-blog.csdnimg.cn/20210110125959512.png)

<br></br>

### Generation_Javascript类

**作用**：Javascript Code生成

**功能**：根据已经设计好的数据可视化代码，生成其可能会发生变化的Javascript代码
`为了不偏移以Java实现的核心思想，仅介绍核心功能`

<font color=#999AAA >生成疫情在地图上的地区分布的主要数据，包括地区名称，确诊人数等关键数据
 (仅为范例)：

![](https://img-blog.csdnimg.cn/20210110130832695.png)
### Generation_HTML类

**作用**：HTML Code生成 与 插入

**功能**：根据已经设计好的数据可视化代码，结合Generation_Javascript类生成的Javascript代码，生成其可能会发生变化的HTML代码，再将其存储为一个可被访问的web页面
`为了不偏移以Java实现的核心思想，仅介绍核心功能`

<font color=#999AAA >将可被浏览器所编译的Javascript数据，疫情地区分布图、新增人数地区分布图等。。。
 (仅为范例)：

 ![](https://img-blog.csdnimg.cn/20210110131453887.png)
<font color=#999AAA >生成Web文件：

![](https://img-blog.csdnimg.cn/20210110131532789.png)
<br></br>

### Run_visualization类

**作用**：Data_visualization模块资源调度

**功能**：调度所有资源、包括资源池中的数据分配、类的使用、数据的传递

<font color=#999AAA > 代码：

![](https://img-blog.csdnimg.cn/20210110132006897.png)

<br></br>

### 小结
如果，数据可视化模块从数据库中读取到的数据长这样：
![](https://img-blog.csdnimg.cn/20210110131712749.png)
那么数据可视化模块，可能生成的Web页面会是这样：
![](https://img-blog.csdnimg.cn/20210110132411277.png)


<br></br>

## index类
**index.java位于工程文件的根目录**

![](https://img-blog.csdnimg.cn/20210111165442670.png#pic_center)

**作用**：Data_visualization和Data_mining模块的资源调度

**功能**：调度所有资源、包括资源池中的数据分配、类的使用、数据的传递

<font color=#999AAA > 代码：

![](https://img-blog.csdnimg.cn/20210111165524110.png)


<br></br>


## 监听模块
文件目录：
![](https://img-blog.csdnimg.cn/20210110132648521.png#pic_center)


>`只是为了方便展示而存放于一个目录，实际使用中应置于项目根目录`
>`共分成一个Java文件，三个Linux Shell脚本`

<br></br>

### Java  Data_monitoring类
**作用**：监控疫情数据动态

**功能**：利用线程休眠的方式让该程序，24小时不间断监控疫情数据，如果发生了变化，立马返回异常给系统

<font color=#999AAA > 包的使用：

![](https://img-blog.csdnimg.cn/20210110133504815.png)
<font color=#999AAA > 关键变量与线程方法：

![](https://img-blog.csdnimg.cn/20210110133717901.png)
<font color=#999AAA > 线程内进行数据爬虫和对比：

![](https://img-blog.csdnimg.cn/20210110133958595.png)
遗憾：
> 在编写Data_monitoring类时有一个极大的遗憾
> 本打算分四个线程，三个监听线程负责多方位捕捉多方面的疫情数据，这样可以提高容错率
> 其中另一线程负责做数据匹配，如果疫情数据发生更新让该线程作出反映
> 这样才能实现真正的24小时，不间断监听
> 但奈何服务器在我的博客和我的"玩弄"之下，已没有多少资源来让其“霸占”，所以只好作罢。

<br></br>


### Monitor_process Shell
**作用**：监控 Data_monitoring类 

**功能**：利用进程监听的方式来判断Data_monitoring类 的反映，如果出现异常，立即作出处理

<font color=#999AAA > 线程监听：

![](https://img-blog.csdnimg.cn/20210110135030513.png)

`注：此时的Java  Data_monitoring类已被打包成Data_monitoring jar`
`注：脚本中的一切都要按照部署环境来编写和理解`


<br></br>

# 总结
<p></p>

## 项目优缺点分析

### 缺点分析
>数据挖掘模块，数据收集来源单一，爬取的都是二手信息，没有挖掘出更多有效数据

>数据可视化模块，没有分析出更多有用的信息

>数据监听模块shell脚本，受技术能力限制，有部分冗余命令

### 优点分析

>数据可视化模块，通过事前编写好Javascript/Css/HTML代码，让Java来根据一定算法生成生成Web文件，速度极快，可扩展性高

>项目整体达到了轻量化的标准，整个项目文件仅1M，部署简单

>项目模块化设计，后期维护更方便，版本迭代更方便


<br></br>

## 技术总结
>**JAVA涉及的知识点：**
>
>>I/O流、多线程、正则运算、JDBC、网络爬虫、数据清洗的实现、简单算法编写、代码设计模式

> **数据库涉及的知识点：**
>
> >Mysql的使用；SQL的DDL、DML、DCL

>**前端涉及的知识点：**
>
>>HTML、CSS布局设计；Javascript动态数据可视化；echart的二次开发及使用；

>**Linux涉及的知识点：**
>
>>VIM使用；编写Shell脚本(包括系统进程监听、Linux命令执行)；Web平台部署



<br></br>

# 项目展示
已经不提供服务的第一代版本：
![](https://img-blog.csdnimg.cn/20210111094314770.png)