# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#压缩代码级别
-optimizations !code/simplification/cast,!field/*,!class/merging/* # 混淆时所采用的算法
-optimizationpasses 5  # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合
-allowaccessmodification
-dontpreverify           # 混淆时是否做预校验
-verbose                # 混淆时是否记录日志


# The remainder of this file is identical to the non-optimized version
# of the Proguard configuration file (except that the other file has
# flags to turn off optimization).
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

-dontwarn org.apache.http.**
-dontwarn com.amap.**
-dontwarn com.alibaba.**
-dontwarn com.netease.**
-dontwarn io.netty.**
-dontwarn com.autonavi.amap.**

### keep options
#system default, from android example
#保证android基类不被混淆 # 保持哪些类不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

-keepattributes *Annotation*,InnerClasses
#-keepattributes SourceFile,LineNumberTable

### 3rd party jars
-keep class android.support.** {*;}
-keep class com.amap.** {*;}
-keep class android.webkit.** {*;}
-keep class com.autonavi.amap.** {*;}
#-----------------------------------------------友盟混淆-----------------
-dontshrink
-dontoptimize
-ignorewarning
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**
-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}


-keep class com.facebook.**
-keep class com.facebook.** { *; }
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.handler.**
-keep class com.umeng.socialize.handler.*
-keep class     UMMoreHandler{*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}

-dontwarn twitter4j.**
-keep class twitter4j.** { *; }

-keep class com.tencent.** {*;}
-dontwarn com.tencent.**
-keep public class com.umeng.soexample.R$*{
public static final int *;
}
-keep public class com.umeng.soexample.R$*{
public static final int *;
}
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}

-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}

-keep class com.sina.** {*;}
-dontwarn com.sina.**
-keep class  com.alipay.share.sdk.** {
    *;
}
-keepnames class * implements android.os.Parcelable {
public static final ** CREATOR;
}

-keep class com.linkedin.** { *; }
-keepattributes Signature

#--------------------------------------------------阿里云推送混淆代码
-keepclasseswithmembernames class ** {
    native <methods>;
}
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.taobao.** {*;}
-keep class com.alibaba.** {*;}
-keep class com.alipay.** {*;}
-dontwarn com.taobao.**
-dontwarn com.taobao.tlog.adapter.**
-dontwarn com.alibaba.**
-dontwarn com.alipay.**
-keep class com.ut.** {*;}
-dontwarn com.ut.**
-keep class com.ta.** {*;}
-dontwarn com.ta.**
-keep class anet.**{*;}
-keep class org.android.spdy.**{*;}
-keep class org.android.agoo.**{*;}
-dontwarn anet.**
-dontwarn org.android.spdy.**
-dontwarn org.android.agoo.**

#-------------------------------------------------------------- 腾讯bugly混淆
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
#------------------------------------------------------------------腾讯云分析
-keep class com.tencent.stat.**  {* ;}
-keep class com.tencent.mid.**  {* ;}


#------------------------------------------------------------信鸽推送
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep class com.tencent.android.tpush.**  {* ;}
-keep class com.tencent.mid.**  {* ;}


#----------------------------------------------------------------eventbus相关
# Ensure annotations are kept for runtime use.
-keepattributes *Annotation*

#eventbus官方混淆代码
-keep class org.simple.** { *; }
-keep interface org.simple.** { *; }
-keepclassmembers class * {
    @org.simple.eventbus.Subscriber <methods>;}


#---------------------------------------友盟混淆------------------------
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.*

# Preserve all public classes, and their public and protected fields and
# methods.
-keep public class * {
    public protected *;
}

# Preserve all native method names and the names of their classes.
-keepclasseswithmembernames class * {
    native <methods>;
}

-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}
-keep public class javax.**
-keep public class android.webkit.**

-keep class com.facebook.**
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**

-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}

-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}

-keep public class [your_pkg].R$*{
    public static final int *;
}

-keep public class * implements java.io.Serializable {*;}

# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
# You can comment this out if your library doesn't use serialization.
# If your code contains serializable classes that have to be backward
# compatible, please refer to the manual.
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Preserve all fundamental application classes.
# Preserve all View implementations, their special context constructors, and
# their setters.
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
    }

# Preserve all classes that have special context constructors, and the
# constructors themselves.

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# Preserve all classes that have special context constructors, and the
# constructors themselves.

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# Preserve all possible onClick handlers.

-keepclassmembers class * extends android.content.Context {
   public void *(android.view.View);
   public void *(android.view.MenuItem);
}

# Preserve the special fields of all Parcelable implementations.

-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}

# Preserve static fields of inner classes of R classes that might be accessed
# through introspection.

-keepclassmembers class **.R$* {
  public static <fields>;
}

# Preserve annotated Javascript interface methods.

-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Preserve the required interface from the License Verification Library
# (but don't nag the developer if the library is not used at all).

-keep public interface com.android.vending.licensing.ILicensingService


#不混淆android-async-http
-keep class com.loopj.android.http.**{*;}

 #不混淆org.apache.http.legacy.jar
 -dontwarn android.net.compatibility.**
 -dontwarn android.net.http.**
 -dontwarn com.android.internal.http.multipart.**
 -dontwarn org.apache.commons.**
 -dontwarn org.apache.http.**
 -keep class android.net.compatibility.**{*;}
 -keep class android.net.http.**{*;}
 -keep class com.android.internal.http.multipart.**{*;}
 -keep class org.apache.commons.**{*;}
 -keep class org.apache.http.**{*;}
#处理支付宝提示sslcertificateSocketFactory找不到的问题
 -dontwarn android.net.**
 -keep class android.net.SSLCertificateSocketFactory{*;}

#忽略警告,信鸽sdk升级之后，打包有警告提示
 -dontwarn com.tencent.**


################### for xUtils
-keep public class org.xutils.** {
    public protected *;
}
-keep public interface org.xutils.** {
    public protected *;
}
-keepclassmembers class * extends org.xutils.** {
    public protected *;
}
-keepclassmembers class * extends org.xutils.http.RequestParams {*;}
-keepclassmembers class * {
   void *(android.view.View);
   *** *Click(...);
   *** *Event(...);
}

#--------------------------------------------gson的混淆------------------------------------
##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*
-keep class com.google.**{*;}
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }
-keep class cn.tailorx.protocol.** { *; }





#不混淆android-async-http
-keep class com.loopj.android.http.**{*;}

 #不混淆org.apache.http.legacy.jar
 -dontwarn android.net.compatibility.**
 -dontwarn android.net.http.**
 -dontwarn com.android.internal.http.multipart.**
 -dontwarn org.apache.commons.**
 -dontwarn org.apache.http.**
 -keep class android.net.compatibility.**{*;}
 -keep class android.net.http.**{*;}
 -keep class com.android.internal.http.multipart.**{*;}
 -keep class org.apache.commons.**{*;}
 -keep class org.apache.http.**{*;}
#处理支付宝提示sslcertificateSocketFactory找不到的问题
 -dontwarn android.net.**
 -keep class android.net.SSLCertificateSocketFactory{*;}

#忽略警告,信鸽sdk升级之后，打包有警告提示
 -dontwarn com.tencent.**
 -dontwarn com.tencent.**

-keep class com.alipay.Android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
-keep class com.alipay.mobilesecuritysdk.*
-dontwarn com.alipay.apmobilesecuritysdk.face**
-keep class com.alipay.apmobilesecuritysdk.face.**{*;}
-keep class com.ut.*

-keepattribute Deprecated
-keep  class com.alibaba.fastjson.**{ *;}
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,LocalVariable*Table,*Annotation*,Synthetic,EnclosingMethod

#百度地图混淆
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**
#忽略警告信息
-ignorewarnings


#混淆第三方的一个utils工具包
#git   https://github.com/Blankj/AndroidUtilCode
-keep class com.blankj.utilcode.** { *; }
-keep  class com.blankj.utilcode.** { *; }
-dontwarn com.blankj.utilcode.**
