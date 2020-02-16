# raffleSite

南邮校科协的寒假项目，算是前端与java组一半一半？前端上传excel表格，后端解析后导入到mysql数据库中，随机从数据库中取出数据放到刮刮卡中。

## 数据库配置

登录mysql数据库后使用以下命令配置数据库。

```mysql
CREATE DATABASE raffle;
use raffle;
CREATE TABLE user(
    'id' INT UNSIGNED AUTO_INCREMENT,
    'sid' VARCHAR(9) NOT NULL,
    'name' VARCHAR(30) NOT NULL,
    PRIMARY LEY('id')
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```



## 本地安装使用

下载release中的jar包，在命令行中使用` java -jar xxx.jar`运行，需要安装java。

运行后到浏览器中访问` localhost:9091`使用。

<img src="./page0.png" >



<img src="./page1.png"/>

已优化移动端显示，亦可在移动端使用。

<img src="./mobile.png"/>



