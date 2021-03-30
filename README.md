# 塔科夫市场插件 Tarkov Tools
一个Mirai-Console插件，用于从[Tarkov Market](https://tarkov-market.com)上抓取市场价格数据，并响应指令进行价格信息转发

A mirai-console plugin used to collect data from [Tarkov Market](https://tarkov-market.com) and forwarding result to QQ groups

## 环境
* JRE: 11+
* Mirai-Console: 2.5.0
* MCL

## 构建
Clone并通过IntelliJ IDEA导入本项目，执行buildPlugin任务即可

## 安装
1. 自行构建本项目或在Releases页面下载预构建的版本
2. 将插件放入Mirai-Console的plugins目录
3. 启动Mirai-Console
4. **第一次使用启动后需要在config配置文件中填写[Tarkov Market API](https://tarkov-market.com/dev/api)** KEY
5. 在Mirai-Console中配置命令执行权限

## 使用
目前只有一个主要命令
### /eft search <搜索关键字>（中文关键字）

返回[Tarkov Market](https://tarkov-market.com)上前三个搜索结果的市场价格和最高商人收购价格

## 关于
本项目跟随[Mirai](https://github.com/mamoe/mirai) 使用 `AGPLv3` 开源

码力不强，献丑了。欢迎提Issue，也欢迎各路大佬优化代码提交Pull Request