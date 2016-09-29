#JitPack
本文来自：[书生依旧](https://github.com/ssyijiu)的[Android阅读笔记](https://github.com/ssyijiu/Android-ReadingNotes)，这不是博客，只是笔记，最纯粹的干货，转载请注明出处。     
阅读文章：[JitPack官方文档](https://jitpack.io/docs/ANDROID/)  , [写自己的开源库发布到 JitPack.io](http://icodeyou.com/2015/12/23/2015-12-23-AndroidLibraryJitPack/)  , [用JitPack发布开源库时附加文档和源码](http://www.gcssloop.com/course/jitpack-sources-javadoc/?utm_source=tuicool&utm_medium=referral)  
完成时间：2016/9/18

> 非常简单的几步将你的库发布到JitPack

##1. 创建一个Library
这个Library自然就是你要发布的库。

##2. 配置Gradle
- 在 root gradle 下
    ```
    buildscript {
        repositories {
            jcenter()
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:2.1.0'
            
            // Add this line
            classpath 'com.github.dcendents:android-maven-gradle-plugin:1.3'
        }
    }
    ```
- 在 library gradle 下
    ```
    apply plugin: 'com.github.dcendents.android-maven'
    group='com.github.ssyijiu'  // 将 ssyijiu 改为你的 Github name
    
    // 以下是配置源码和文档
    // 指定编码
    tasks.withType(JavaCompile) {
        options.encoding = "UTF-8"
    }

    // 打包源码
    task sourcesJar(type: Jar) {
        from android.sourceSets.main.java.srcDirs
        classifier = 'sources'
    }
    
    task javadoc(type: Javadoc) {
        failOnError  false
        source = android.sourceSets.main.java.sourceFiles
        classpath += project.files(android.getBootClasspath().join(File.pathSeparator))
        classpath += configurations.compile
    }
    
    // 制作文档(Javadoc)
    task javadocJar(type: Jar, dependsOn: javadoc) {
        classifier = 'javadoc'
        from javadoc.destinationDir
    }
    
    artifacts {
        archives sourcesJar
        archives javadocJar
    }
    ```

##3. 上传 Github
将整个 Project   上传到 Github。 
- 你可以直接使用 Android Studio 的 Share Project  On Github 上传
- 也可以先在 Github 上创建一个仓库， 然后 clone 到本地，将 Project 中的文件复制进去，Commit and Push 
- **注意 : 这里要上传整个 Project 不要只上传你的 Library**

##4. 创建 releases
- 点击 releases
- 点击 Draft a new releases
- 填写 版本号、标题、描述
- 点击 Publish releases

##5. 远程依赖
前四步走完的话，你的库已经发布好了，下面则是如何远程依赖刚刚发布的库。
- 前往[JitPack](https://jitpack.io/)
- 搜索你的 Github name/Project name
- 选择最新版本，Get it

##Demo：[MLog](https://github.com/ssyijiu/MLog)

## 联系作者
- Github : [ssyijiu](https://github.com/ssyijiu)
- E-mail : lxmyijiu@163.com
- WeChat : [ssyijiu11](http://obe5pxv6t.bkt.clouddn.com/weixin.jpg)