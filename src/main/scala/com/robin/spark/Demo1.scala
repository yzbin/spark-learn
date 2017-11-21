package com.robin.spark

import org.apache.spark._
import org.apache.spark.streaming._
object Demo1 {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("name").setMaster("local[2]")
    val ssc=new StreamingContext(conf,Seconds(5))

    //监听端口，捕捉信息
    val streamline=ssc.socketTextStream("192.168.134.50", 9999)

    //数据处理
    val words = streamline.flatMap(_.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)

    //控制台输出
    wordCounts.print()
    //启动StreamingContext
    ssc.start()
    //等待终端（线程阻塞）
    ssc.awaitTermination()
    ssc.stop()
  }

}
