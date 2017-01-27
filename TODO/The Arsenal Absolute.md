# The Arsenal Absolute

### 第一章，搭建高效的开发环境

#### MAC 快捷键

Commond + ~：切换同一个应用的不同窗口

Commond + W：关闭当前窗口，如果一个应用打开了多个窗口，使用 Commond + Q

Commond + N：新建窗口

截屏：Commond + Shift + 4 自由截屏，Command + Shift + 3 截取整个屏幕，Commond + Shift + 4 +  空格 截取当前窗口

光标：Commond + Left/Right 行首行尾，Option + Left/Right 按单词移动，Commond + Up/Down 页首页尾

Commond + Delete：删除行

#### HomeBrew

brew search name : 搜索相关软件

brew install name：安装软件

brew uninstall name：卸载软件

brew info name：查看软件的信息

brew list : 列出安装的软件

使用 Homebrew Cask 在 brew 后添加 cask 命令

### 第二章 Git

初始化一个Git仓库，使用git init命令。

添加文件到Git仓库，分两步：

第一步，使用命令git add <file>，注意，可反复多次使用，添加多个文件；

第二步，使用命令git commit，完成。 

要随时掌握工作区的状态，使用git status命令。

如果git status告诉你有文件被修改过，用git diff可以查看修改内容。

HEAD指向的版本就是当前版本，HEAD^ 指向上一个版本，HEAD^^ 指向上上个版本，HEAD~100 指向上100个版本，因此，Git允许我们在版本的历史之间穿梭，使用命令git reset --hard commit_id。

穿梭前，用git log可以查看提交历史，以便确定要回退到哪个版本。

要重返未来，用git reflog查看命令历史，以便确定要回到未来的哪个版本。

git add命令实际上就是把要提交的所有修改放到暂存区（Stage），然后，执行git commit就可以一次性把暂存区的所有修改提交到分支。

如果不add到暂存区，那就不会加入到commit中。

场景1：当你改乱了工作区某个文件的内容，想直接丢弃工作区的修改时，用命令git checkout -- file。

场景2：当你不但改乱了工作区某个文件的内容，还添加到了暂存区时，想丢弃修改，分两步，第一步用命令git reset HEAD file，就回到了场景1，第二步按场景1操作。

git checkout其实是用版本库里的版本替换工作区的版本，无论工作区是修改还是删除，都可以“一键还原”。

命令git rm用于删除一个文件。如果一个文件已经被提交到版本库，那么你永远不用担心误删，但是要小心，你只能恢复文件到最新版本，你会丢失**最近一次提交后你修改的内容**。

创建SSH Key。在用户主目录下，看看有没有.ssh目录，如果有，再看看这个目录下有没有id_rsa和id_rsa.pub这两个文件，如果已经有了，可直接跳到下一步。如果没有，打开Shell（Windows下打开Git Bash），创建SSH Key：

$ ssh-keygen -t rsa -C "youremail@example.com"

你需要把邮件地址换成你自己的邮件地址，然后一路回车，使用默认值即可。

要关联一个远程库，使用命令git remote add origin git@server-name:path/repo-name.git；

关联后，使用命令git push -u origin master第一次推送master分支的所有内容；

此后，每次本地提交后，只要有必要，就可以使用命令git push origin master推送最新修改。

实际上，Git支持多种协议，默认的git://使用ssh，但也可以使用https等其他协议。

使用https除了速度慢以外，还有个最大的麻烦是每次推送都必须输入口令，但是在某些只开放http端口的公司内部就无法使用ssh协议而只能用https。

## 第三章 Android Studio

#### 快捷键

Commit + Shift + A 搜索快捷键

Commit + E 最近浏览文件    Commit + Shift + E 最近编辑文件

Commit + Option + Left/Right 回退／前进最近浏览位置

Option + Shift + Up/Down 上／下移动行

Option + F7 查找方法、字端、资源等的调用位置

Commit + B/左键 快速进入一个方法定义

Commit + Option + B  快速进入一个方法的实现

Commit + p 查看一个方法等定义

F1 查看文档

Commit + Shift + Option + F8 设置单次断点

Run -> View Breakpoints 断点控制，设置异常断点、日志断点等

Control + G 多行选择

Option + 鼠标拖动  多行编辑

Option + Shift + 左键 多光标选择

Debug 时 Option + 左键 查看表达式或变量的值



