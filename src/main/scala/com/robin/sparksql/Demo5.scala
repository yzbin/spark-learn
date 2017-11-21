package com.robin.sparksql

import java.util.Properties

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

object Demo5 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Demo5")
    val sc = new SparkContext(conf)
    val hivecontext = new HiveContext(sc)

    val readConnProperties4 = new Properties()
    readConnProperties4.put("driver", "com.mysql.jdbc.Driver")
    readConnProperties4.put("user", "root")
    readConnProperties4.put("password", "root")
    readConnProperties4.put("fetchsize", "3")

    val jdbcDF4 = hivecontext.read.jdbc(
      "jdbc:mysql://192.168.134.50:3306",
      "(select * from test.my_user where len(amount)>=3) t",  // 注意括号和表别名，必须得有，这里可以过滤数据
      readConnProperties4)
    jdbcDF4.registerTempTable("tmp_my_user")
    hivecontext.sql("select * from tmp_my_user");



  }
}
