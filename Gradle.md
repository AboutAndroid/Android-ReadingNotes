#Gradle
本文来自：[书生依旧](https://github.com/ssyijiu)的[Android阅读笔记](https://github.com/ssyijiu/Android-ReadingNotes)，这不是博客，只是笔记，最纯粹的干货。     
阅读文章：   
[给 Android 初学者的 Gradle 知识普及](http://stormzhang.com/android/2016/07/02/gradle-for-android-beginners/)   
[Android 开发你需要了解的 Gradle 配置](http://stormzhang.com/android/2016/07/15/android-gradle-config/)
http://kaywu.github.io/2016/04/24/Gradle/
[从Eclipse到AndroidStudio（四）Gradle基本配置](http://www.jianshu.com/p/cd8fe9b16369)
http://www.androidchina.net/2155.html   
http://blog.csdn.net/innost/article/details/48228651

##一.什么是Gradle
新一代自动化构建工具（Ant、Maven都是构建工具），用于对项目进行编译、运行、签名、打包、依赖管理等一系列功能的集合，一个独立的项目与AS、Android无关。    
使用Groovy来进行描述，简洁灵活。     
为了支持Gradle在AS上谷歌开发了一个插件：Android Gradle Plugin，在项目根目录下的build.gradle文件中有这么一行用来依赖该插件的代码：classpath 'com.android.tools.build:gradle:2.1.0' 这个版本号是gradle插件，是谷歌定的，和Gradle官方没有关系。 

##二.Gradle Wrapper
为了解决不同项目Gradle版本不一致的问题，谷歌推出的Gradle包装，每个Android项目中都会配置一个指定版本的Gradle，通过Gradle Wrapper每个项目可以使用不同版本的Gradle来构建。

##三.AS中的Gradle配置文件
> 未完待续 

1、app下的build.gradle：整个项目中最主要的gradle配置文件
```
apply plugin: 'com.android.application'
// 使用com.android.application插件来构建Android的主程序，相当于Eclipse中的ADT插件。
// apply plugin: 'com.android.library'：如果这个模块是一个Library的话，应该引入的插件叫做'com.android.library'
android {
    // 编译SDK版本
    compileSdkVersion 23   
    // build tools 版本
    buildToolsVersion "24.0.0"

    defaultConfig {
        // 应用包名
        applicationId "com.ssyijiu.alertdialog"
        // 最小支持的SDK版本
        minSdkVersion 15
        // 目标SDK版本
        targetSdkVersion 23
        // 版本号
        versionCode 1
        // 版本名称
        versionName "1.0"
    }

    // 编译类型    
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

// 依赖管理标签
dependencies {
    // 编译libs目录下的所有jar包，用什么jar包直接放在libs目录下就可以了
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    
    // 去远程仓库（一般是jcenter 和 maven 仓库）下载butterknife:5.1.1并编译
    // com.jakewharton远程项目的路径，butterknife远程项目名称，5.1.1版本号
    // 版本号写成 5.1.+ 表明使用5.1版本分支下的最新版本，5.+表明5版本分支下的最新版本
    // 写成compile 'com.jakewharton:butterknife:+' 表示使用这个项目的最新版本
    // 不建议compile 'com.jakewharton:butterknife:+'写法，每次都要联网检查该项目有没有最新版本
    compile 'com.jakewharton:butterknife:5.1.1'
    
    // 编译本地library库
    compile project(':library')
}
```
2、Project下的build.gradle： 
```
// buildscript用来声明Gradle的配置
buildscript {
    // Gradle工具本身默认从jcenter仓库下载
    repositories {
        jcenter()
    }
    dependencies {
        // 声明了 android gradle plugin 的版本
        classpath 'com.android.tools.build:gradle:2.1.0'
    }
}

// 声明项目中所有Moudle的通用配置，这里规定所有模块中要用到的jar包也都从jcente仓库中获取 
// 在allprojects标签下配置的好处是你不需要再在每个模块下的build.gradle中单独配置了
allprojects {
    repositories {
        jcenter()
    }
}
task clean(type: Delete) {
    delete rootProject.buildDir
}
```
整个项目的 gradle 基础配置文件，声明了 android gradle plugin 的版本。

3、settings.gradle： 
```
include ':app',':library'
```
列出了项目中各个Moudle的名称，我这里有一个app和一个叫做library的库。  

4、gradle目录下的gradle-wrapper.properties：  
```
#Mon Dec 28 10:00:20 PST 2015
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-2.10-all.zip
```
声明了 gradle 的目录与下载路径以及当前项目使用的 gradle 版本。

##四.正确的导入AS开源项目
打开各个Moudle下的build.gradle（包括library），检查ompileSdkVersion 和 buildToolsVersion是否和本地版本一直，不一致的话修改版本或者下载该版本。 

检查 gradle-wrapper.properties，Google 有些时候要求不同的 AS 支持不同的 gradle 版本。比如 AS 1.0 的时候要求必须使用 gradle 1.x 的版本，等到 AS 2.0 的时候，Google 不支持 gradle1.x 的版本，这个时候你必须手动更新下 android gradle plugin 的版本。

##五.Gradle命令
```
gradlew -v：查看当前项目的gradle版本。 
gradlew clean ：清除Project/app目录下的build文件夹（如果没有清除哪个？如果有多个Moudle会怎么样？） 
gradlew build：检查依赖并编译打包（debug、release 环境的包都打出来） 
gradlew assembleDebug ：编译并打Debug包 
gradlew assembleRelease ：编译并打Release的包 
```
**注意：第一次上面执行命令行，那么会出现一个下载的提示，紧接着会打印一个个的点。** 
    ![](./_image/2016-07-25 12-16-03.jpg) 

##六.总结
关键是理解下面三者之间的关系：
- Gradle：自动化构建工具，用于对项目进行编译、运行、签名、打包、依赖管理等一系列功能的集合，一个独立的项目与AS、Android无关。
- Android Gradle Plugin：谷歌为了支持Gradle在AS上使用开发的插件，它的版本号有谷歌规定与Gradle版本无关。
- Gradle Wrapper：通过Gradle Wrapper每个Android项目中都会配置一个指定版本的Gradle，通过Gradle Wrapper每个项目可以使用不同版本的Gradle来构建。

##联系作者
Email：ssyijiu@126.com   
微信：SSyijiu11


