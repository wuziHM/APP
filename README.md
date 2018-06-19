# APP简介
#### 集合了多种效果，和基础知识的一个应用

* java_lib 中主要集合了javaSE的一些基础知识，以及一些常用的设计模式demo
* app的module中是一些安卓的基础知识，特效的demo。包括四大组件的一些总结，一些动画效果的实现，一些框架的使用实例。
* library_swipe是一个滑动刷新的库
* base-adapter-library是鸿洋的封装的adapter一个库，挺好用的，有listView的adapter，RecycleView的adapter。

#### 下面是一些界面的截图和说明总结：
### 首页
![首页](./screenshot/main.jpg)

**首页分为4个部分，基础知识，进阶特效、高级、实例**

#### 基础部分
1. 基本控件:recyclerView的使用、adapter局部更新、PopupWindow使用、Spinner使用等等
2. 动画：使用属性动画实现位移、旋转、透明等效果，以及一些组合效果。还有插值器的使用。
3. Drawable：使用drawable、TransitionDrawable实现图片不断循环播放的效果，关键代码如下：


	
#### 实例
![实例](./screenshot/four_fragment.jpg)
实例目前只做了几个关于图片的东西。
1.图图大全是用的百度api上的接口一个图片的查看器。其中用到SmartTabLayout、SwipeToLoadLayout、RecyclerViewPager、PhotoView等开源控件。


