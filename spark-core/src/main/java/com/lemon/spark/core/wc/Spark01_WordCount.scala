package com.lemon.spark.core.wc

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by lemoon on 2021/11/25 11:09 PM
  */
object Spark01_WordCount {
  def main(args: Array[String]): Unit = {
    //Application
    //Spark框架
    //TODO 建立和Spark框架的连接
    //JDBC:Connection
    val sparconf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparconf)

    //TODO 关闭连接
    sc.stop()
  }
}
