package com.robin.spark

<<<<<<< Updated upstream
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

object Demo2 {
  def main(args: Array[String]): Unit = {
    //配置
    val conf=new SparkConf().setMaster("local[2]").setAppName("Demo2")
    //spark上下文
    val sc=new SparkContext(conf)


    val hiveContext=new HiveContext(sc)

    val df = hiveContext.sql("select * from default.customer")

    df.show()
    df.select("id").show()
    df.count()
  }

=======
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Demo2 {
  def main(args: Array[String]): Unit = {
    //配置文件的设置
    val conf=new SparkConf().setAppName("name").setMaster("local[2]")

     val ssc =new StreamingContext(conf,Seconds(1))


  }
>>>>>>> Stashed changes
}
