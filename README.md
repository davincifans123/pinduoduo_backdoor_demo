# 拼多多app中提权和获取数据操作的复现DEMO，含源码和演示视频

本项目结合前人的脱壳及分析经验，利用从拼多多app中提取的恶意代码，现实获取用户手机中登录的微博账号功能。演示了拼多多app如何利用漏洞攻击用户手机，取得高权限，从而获取用户数据等操作。  
致敬对抗网络黑恶势力的安全研究前辈，抨击拼多多毫无底线的行为。  

- DEMO 源代码：src目录
- DEMO apk文件：拼多多恶意代码DEMO.apk
- DEMO 演示视频：拼多多恶意代码DEMO演示视频.mov
- 参考文档：https://github.com/davinci01010/pinduoduo_backdoor_x

DEMO的说明：  
- DEMO效果：点击获取微博ID，默认权限下app无权限读取该数据，提示无权限。无权限条件下则启动StartAnyWhere获取权限，演示获取权限动作，再次点击获取微博ID，取得微博ID。
- 适配说明：DEMO支持安卓13以下版本OPPO、VIVO 手机，条件有限，仅验证部分手机。需适配不同手机是因为，StartAnyWhere 提权需通过一个可以系统应用做跳板获取权限（绕过android exported Content Provider特性，文档：https://developer.android.com/guide/topics/manifest/provider-element?hl=zh-cn#gprmns  ），目前DEMO里只写了OV厂的，其他厂商的适配自行抄拼多多的代码来改DEMO，似乎除了华为，其他的手机厂商通杀。 
- 提权代码说明：DEMO中演示的提权代码（代码路径：src/main/app/src/main/java/com/tech/pinduoduo_backdoor/IntentWrapper.java），来自拼多多恶意代码中的“CommonAlivePullStartUP”,该提权代码使用了安卓系统通用组件“android.os.WorkSource”的bundle 反序列化漏洞(CVE-2023-20983)，3月份Google发布补丁修复该漏洞。拼多多app中有多个提权代码，可自行替换相关代码。  
<img width="1303" alt="提权" src="https://user-images.githubusercontent.com/128487541/226639024-572e93d0-3d42-455f-8f3b-802e444f77e9.png">

- 获取微博ID代码： src/main/app/src/main/java/com/tech/pinduoduo_backdoor/idCollectors/WeiboNameCollector.java，原代码位置如下。  
<img width="1276" alt="alive_strategy_biz_plugin" src="https://user-images.githubusercontent.com/128487541/226658238-bb6c36f8-62c3-4dce-a43b-b9bd8f7c3a93.png">

本人安卓开发技术太菜，DEMO和演示视频做的比较粗糙，抛砖引玉，欢迎交流。    
 
 
## 听说大家都爱听八卦 
#### 第一个故事：
最近Google将拼多多app识别为恶意软件，封了拼多多的Google账号，在Google Play下架拼多多app，还自动删除用户手机中的拼多多，还好拼多多和TEMU的Google账号是分离的，TEMU保住了一命。实际上，早在一年前，国内手机厂商们就陆续发现拼多多app的问题，某些手机厂商还在其应用市场下架拼多多app长达数月。   
国内手机厂商们是怎么发现拼多多app攻击用户手机的？因拼多多app在用户手机中肆无忌惮的用漏洞操作 系统文件及应用，删除及篡改关键系统文件，导致许多用户手机不断崩溃重启，无法开机，用户找手机厂商售后，投诉手机质量问题，拼多多app的恶意行为也被厂商发现。 
 

#### 第二个故事：
拼多多在社区团购业务（也就是多多买菜）的百团大战中大获全胜，市场份额第一，这里有个很有意思的故事。一年前，拼多多在北方某县城，和其他公司的买菜业务打得难舍难分，拼多多的百人神秘团队祭出奇招（ppd rocket），通过拼多多app中的神奇代码，识别到该地区用户使用其他公司的社区团购应用，则强行消耗手机的cpu，让手机卡顿，用户难以正常使用其他公司的社区团购，使拼多多在该地区获得用户“广泛好评”。  
更奇葩的是，拼多多app还修改了手机系统统计的耗电量数据，避免自己做的手脚被普通用户发现，还能栽赃到其他应用身上，修改耗电量统计数据的代码，在其他大牛公开的拼多多恶意代码中还能看到。
