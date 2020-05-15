#!/bin/bash

## 打包时间戳
date=`date +%Y%m%d%H%M%S`

## 查询的关键字
keyword=$1
if [ -z $1 ]; then
    keyword=vue
fi
echo "搜索文件的关键字为：$keyword"

## 存放打包文件的目录
targetPath=$2

## 默认目录
if [ -z $targetPath ];then
    targetPath=/eship/deploy
fi

## 加上时间戳
targetPath=$targetPath/vue/$date

echo 打包文件目录路径：$targetPath


# 打包
echo 开始出包.......................................
echo 开始编译，打包..................................
sh 05_run-vues.sh prod $keyword

cd ../
for file in $(ls | grep -E $keyword)
do
  if [ -d "$file" ];then
    if [[ $file = *"nuxt"* ]];then
        echo "####################################################"
        echo "## nuxt 打包..."
        echo 开始处理$file
        cd $file
        currentPath=$(pwd)
        echo 开始复制包到目标目录:$currentPath/$file

        mkdir -p $currentPath/$file

        cp -r $currentPath/.nuxt $currentPath/$file/.nuxt
        cp $currentPath/nuxt.config.js $currentPath/$file/
        cp $currentPath/package.json $currentPath/$file/
        cp -r $currentPath/static $currentPath/$file/static

        zipFileName=$file-$date.tar.gz
        echo 开始压缩.......................................
        tar cf $zipFileName $file
        echo 压缩完成.......................................


        echo 复制文件到出包目录..............................
        mkdir -p $targetPath
        mv $zipFileName $targetPath/

        echo 开始清理临时文件...............................
        rm -rf $currentPath/$file

        ## 返回主目录
        cd ../


    else
        echo "####################################################"
        echo 开始处理$file

        cd $file
        currentPath=$(pwd)
        echo 开始复制包到目标目录:$currentPath/$file
        if [ -d "$currentPath/dist" ];then
          echo "$currentPath/dist"
          cp -r $currentPath/dist $currentPath/$file
        fi


        zipFileName=$file-$date.tar.gz
        echo 开始压缩.......................................
        tar cf $zipFileName $file
        echo 压缩完成.......................................


        echo 复制文件到出包目录..............................
        mkdir -p $targetPath
        mv $zipFileName $targetPath/

        echo 开始清理临时文件...............................
        rm -rf $currentPath/$file

        ## 返回主目录
        cd ../
    fi
    
  fi
done

echo 打包完成,文件目录路径：$targetPath
