package com.robin.sparksql

import java.util.Properties

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

object Demo4 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Demo4")
    val sc = new SparkContext(conf)
    val hiveContext = new HiveContext(sc)
    //不指定查询条件
    val url = "jdbc:mysql://192.168.134.50:3306/test?user=root&password=root"
    val prop = new Properties();
    val df = hiveContext.read.jdbc(url,"my_user",prop)
    println("第一种方法输出:"+df.count())
    println("1.------------->"+df.count())
    println("2.------------->"+df.rdd.partitions.size)

    //2.指定数据库字段的范围
    val lowerBound = 1;
    val upperBound = 6;
    val numPartitions = 2;
    val url1 = "jdbc:mysql://192.168.134.50:3306/test?user=root&password=root"
    val prop1 = new Properties()
    val df1 = hiveContext.read.jdbc(url1, "my_user", "id", lowerBound, upperBound, numPartitions, prop1)
    println("第二种方法输出:"+df1.rdd.partitions.size)
    df1.collect().foreach(println)



    val predicates = Array[String]("length(account) >= 3")
    val url2 = "jdbc:mysql://192.168.134.50:3306/test?user=root&password=root"
    val prop2 = new Properties()
    val df2 = hiveContext.read.jdbc(url, "my_user", predicates, prop2)
    println("第三种方法输出："+df2.rdd.partitions.size+","+predicates.length);
    df2.collect().foreach(println)
    df2.registerTempTable("tmp_user")
    hiveContext.sql("select * from tmp_user")
      .write.mode("overwrite")
      .jdbc("jdbc:mysql://192.168.134.50:3306/test?user=root&password=root","tmp_user",prop2)

    println("------------------------------------------------------------------")


    //Spark还提供通过load的方式来读取数据。
    val url3 = "jdbc:mysql://192.168.0.101:3306/test?user=root&password=root"
    val df3 = hiveContext.read.format("jdbc").option("url", url2).option("dbtable", "my_user").load()
    println("第四种方法输出："+df3.rdd.partitions.size);
    df.collect().foreach(println)

    sc.stop()






  }
}
