# 点触验证码
## Android Studio接入方式：
### 1、libs添加aar：
在android studio项目的libs添加`TouClick-V1.0.0.aar`，
### 2、build.gradle添加配置
依赖的jar包:
```javascript
repositories {
    flatDir {
        dirs 'libs'
    }
}

dependencies {
	......
    //加载libs下的jar文件
    compile fileTree(include: ['*.jar'], dir: 'libs')
    //加载libs下的TouClick-1.0.0.aar文件
    compile(name:'TouClick-V1.0.0', ext:'aar')

    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'com.google.code.gson:gson:2.6.2'
	......
}
```
### 3、权限配置
所需权限:
```java
<!-- 点触验证所需权限-->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<!-- 点触验证所需权限结束-->
```
### 4、项目使用:
#### （1）在需要使用验证码的页面的XML中添加相应控件:
A、**图标选择型:**根据文字选择出符合要求的图片:
```xml
 <com.touclick.android.ui.ImgTxtButton
	android:id="@+id/imgtxtbutton"
	android:layout_width="280dp"
	android:layout_height="45dp"
	android:background="@drawable/touclick_capcha_btn_click_normal"
	android:textColor="#fff"
	android:textSize="15sp"
	img:checkbg="@drawable/touclick_capcha_btn_click_normal"
	img:checkerrortxt="@string/check_fail"
	img:checkokbg="@drawable/touclick_capcha_btn_click_normal"
	img:checkoktxt="@string/check_success"
	img:checktxt="@string/click_check"
	img:checktype="14" />
```
B、**图文点击型：**根据文字，按顺序选中图片相应的文字：
```xml
<com.touclick.android.ui.ImgTxtButton
	android:id="@+id/imgtxtbutton"
	android:layout_width="280dp"
	android:layout_height="45dp"
	android:background="@drawable/touclick_capcha_btn_click_normal"
	android:textColor="#fff"
	android:textSize="15sp"
	img:checkbg="@drawable/touclick_capcha_btn_click_normal"
	img:checkerrortxt="@string/check_fail"
	img:checkokbg="@drawable/touclick_capcha_btn_click_normal"
	img:checkoktxt="@string/check_success"
	img:checktxt="@string/click_check"
	img:checktype="13" />
```
其中，UI各属性的含义如下：

| 属性  |  含义 |
| ------------ | ------------ |
| checkbg  |  背景图 |
| checkerrortxt  | 验证失败显示的文字内容  |
| checkokbg  | 验证成功时的背景图  |
| checkoktxt  | 验证成功显示的文字内容  |
| checktxt  | 正常显示的内容  |
| checktype  | 验证类型：13:图文点击型；14:图标选择型  |


（2）在引用此`UI`的`Activity`中，获取`Imgtxtbutton`对象，并且`Imgtxtbutton`对象设置监听，传入公钥和监听对象：
例如：
```java
import com.touclick.android.api.TCListener;
import com.touclick.android.ui.ImgTxtButton;

//自定义控件ImgTxtButton
mImgButton = (ImgTxtButton) findViewById(R.id.imgtxtbutton);
mImgButton.setTCListener(publicKey, new TCListener() {

      @Override
      public void onSuccess(int status, String token, String checkAddress, String sid) {
           //用户验证成功的回调。开发者需要将传进来的参数发送到服务器SDK
		   System.out.println("状态：" + status + ";token:" + token + ";checkAddress:" + checkAddress + ";sid:" + sid);
      }

      @Override
      public void onFailture(int status, String msg, String sid) {
	  	   //用户点击验证码错误
           Toast.makeText(getBaseContext(), "状态：" + status + ";msg:" + msg + ";sid:" + sid, Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onClose(int status, String msg) {
	       //用户点击关闭验证码触发该方法
           Toast.makeText(getBaseContext(), "状态：" + status + ";msg:" + msg,Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onError(int status, Object msg) {
	      //验证码逻辑出现错误，例如：网络超时
          Toast.makeText(getBaseContext(), "状态：" + status + ";msg:" + msg,Toast.LENGTH_SHORT).show();
      }
});
//弹窗是否能够获取焦点（默认为true，点击空白处关闭弹窗）
mImgButton.setFocusable(false);
//弹窗是否可以触摸（默认为true，点击空白处关闭弹窗）
mImgButton.setTouchable(false);
```

## Eclipse 接入返回式
## 方式一 引用项目方式

### 1、TouClick项目导入：
在Eclipse的Package Explore栏目的空白区，右键选择Import

```flow
st=>start: Import
e=>end: 导入项目成功
op1=>operation: Existing Project into Workspace
op2=>operation: 选择示例代码中的Eclipse／WuyiTouClick-sdk项目
op3=>operation: 导入成功

st->op1->op2->op3->e
```
### 2、App引用TouClick项目:

```flow
st=>start: 选中自己App项目
e=>end: 引用成功
op1=>operation: 选中Properties
op2=>operation: 选中Andorid
op3=>operation: 在右侧的Library栏中点击Add
op4=>operation: 选中TouClick项目

st->op1->op2->op3->op4->e
```
完成上述操作，既可在项目中正常使用验证码。
### 3、项目使用：
参考Android Studio中的**《4、项目使用》**

## 方式二 资源添加
### 1、资源导入：
将添加资源文件中的Eclipse下的资源数据拷贝到相应的文件目录下
### 2、权限配置：
在清单文件中添加验证码所需权限：
```java
<!-- 点触验证所需权限-->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<!-- 点触验证所需权限结束-->
```
### 3、项目使用：
参考Android Studio中的**《4、项目使用》**