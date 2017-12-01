package com.robin.spark


import org.apache.log4j.Logger
import org.apache.spark.{SparkConf, SparkContext}
import math.random

object Demo3 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
        .setAppName("myApplication")
        .setMaster("local[2]")  //local：本地单线程，local[K]：本地多线程（指定K个内核）
    //初始化上下文
    val sc = new SparkContext(sparkConf)
    //读取数据
    val rdd = sc.textFile("c:\\\\README.md")


    //统计行数
//    val count1 = rdd.count()
//    System.out.println("文本的行数:"+count1)
//
//    //统计出包含spark的行数
//    // 过滤 + 统计
//    val count2 = rdd.filter(line => line.contains("spark")).count()
//    System.out.print("包含spark的行数:" + count2)
//
//    //该数据的第一行
//    val firstline = rdd.first()
//    System.out.print("文本中的第一行是:"+firstline)
//
//    //需求:打印出所有包含spark的行？
//    val linewithspark = rdd.filter(line => line.contains("spark"))
//
//    linewithspark.foreach(s => println(s))


//    reduce(binary_function)
//    reduce将RDD中元素前两个传给输入函数，产生一个新的return值，
//    新产生的return值与RDD中下一个元素（第三个元素）组成两个元素，再被传给输入函数，直到最后只有一个值为止。


    //文本中字符串最长为多少
//    val result = rdd.map(line => line.split(" ").size).reduce((a, b) => if (a > b) a else  b)
//    println(result)


    //上面可以用java代码简化
//    val max_size = rdd.map(line => line.split(" ").size).reduce((a, b) => math.max(a, b))
//    println("最大字符串的大小为:"+max_size)

    //单词统计
    val wordCounts = rdd.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a,b) => a+b)
//    println(wordCounts.foreach(s => println(s)))
    println(wordCounts.count())
  }
}
