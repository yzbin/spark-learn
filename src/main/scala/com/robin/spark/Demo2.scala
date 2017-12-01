package com.robin.spark


import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

object Demo2 {

  def main(args: Array[String]): Unit = {
    //配置
    val conf = new SparkConf().setMaster("local[2]").setAppName("Demo2")
    //spark上下文
    val sc = new SparkContext(conf)


    val hiveContext = new HiveContext(sc)

    val df = hiveContext.sql("select * from default.customer")

    df.show()
    df.select("id").show()
    df.count()
  }
}