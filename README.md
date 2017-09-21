# myComment
仿大众点评后台管理及前端完整系统

### 系统整体功能需求
![image](https://github.com/ZJX-09/imageForREADME/raw/master/myComentForGithubReadMe/requiredment.png)
### 数据库设计
数据库设计主要分为2大部分（省略了具体属性，体现实体间关系）

#### 第一部分（以订单为中心的实体关系）

![image](https://github.com/ZJX-09/imageForREADME/raw/master/myComentForGithubReadMe/E-R1.png)

#### 第二部分（以用户组为中心的实体关系）
![image](https://github.com/ZJX-09/imageForREADME/raw/master/myComentForGithubReadMe/E-R2.png)

### 系统运行效果图

#### 前端

* 前端首页

![image](https://github.com/ZJX-09/imageForREADME/raw/master/myComentForGithubReadMe/front_index.png)

说明：分为三个部分

1. 商户搜索（可以根据城市、类型、关键字进行搜索）
2. 首页广告
3. 猜你喜欢（商户推荐列表）

* 点击美食进行类型搜索

![image](https://github.com/ZJX-09/imageForREADME/raw/master/myComentForGithubReadMe/front_c_search.png)

说明：可以通过商户的搜索模块去进行类型的搜索，搜索出商户列表

* 点击商户查看商户详情

![image](https://github.com/ZJX-09/imageForREADME/raw/master/myComentForGithubReadMe/front_detail.png)

说明：可以查看商户的星级、人均价格和用户对该商户的点评列表，以及收藏和买单功能。

* 会员登录个人中心

![image](https://github.com/ZJX-09/imageForREADME/raw/master/myComentForGithubReadMe/front_center.png)

说明：会员登录之后，可以进行买单，然后可以查看自己的订单列表

* 会员点评

![image](https://github.com/ZJX-09/imageForREADME/raw/master/myComentForGithubReadMe/front_dianping.png)

说明：会员可以对商户进行点评功能，包括评论的星级以及文字。

#### 后台

分为三种用户角色：系统管理员、普通管理员、业务员

* 超级管理员界面

![image](https://github.com/ZJX-09/imageForREADME/raw/master/myComentForGithubReadMe/back_system.png)

说明：超级管理员拥有最高的权限，所以显示所有的菜单。

1. 点击用户显示分配的用户组，点击用户组，显示相应已经分配的菜单和动作
2. 可以对用户进行分配用户组，以及可以对用户组进行分配菜单和动作
3. 可以分别对用户、用户组、菜单和动作右击进行管理

* 普通管理员界面

![image](https://github.com/ZJX-09/imageForREADME/raw/master/myComentForGithubReadMe/back_system2.png)

说明：普通管理员拥有对广告、商户的管理，以及对订单、评论、报表查看的功能

* 业务员界面

![image](https://github.com/ZJX-09/imageForREADME/raw/master/myComentForGithubReadMe/back_system3.png)

说明：业务员拥有对广告、商户进行浏览、查询的功能，但没有管理的功能，以及对订单、评论、报表查看的功能

* 报表统计功能

![image](https://github.com/ZJX-09/imageForREADME/raw/master/myComentForGithubReadMe/back_report.png)

说明：按类别和时间间隔（1小时）分类统计出当前系统前一天的订单数量分布

















