package com.lemon.spark.core.wc

import org.apache.spark.rdd.RDD
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

    //TODO 执行业务操作

    //1. 读取文件，获取一行一行的数据
    //  hello world
    val lines: RDD[String] = sc.textFile("datas")

    //2.将一行数据进行拆分，形成一个一个的单词(分词)
    //  "hello world" => hello,world,hello,world
    val words: RDD[String] = lines.flatMap(_.split(" "))

    //3.将数据根据单词进行分组，便于统计
    // (hello,hello,hello),(world,world)
    val wordGroip: RDD[(String, Iterable[String])] = words.groupBy(words => words)

    //4.对分组后的数据进行转换
    //  (hello,hello,hello),(world,world)
    //  (hello,3),(world,2)
    val wordToCount = wordGroip.map {
      case (word, list) => {
        (word, list.size)
      }
    }

    //5.将转换结果采集到控制台打印出来
    val array: Array[(String, Int)] = wordToCount.collect()
    array.foreach(println)

    //TODO 关闭连接
    sc.stop()
  }
}
