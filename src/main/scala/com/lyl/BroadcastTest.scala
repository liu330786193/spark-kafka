package com.lyl

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.Set
import scala.io.Source

object BroadcastTest {
  def main(args: Array[String]): Unit = {
    val sources = Source.fromFile("C:\\Users\\lyl\\Desktop\\README.txt")
    val dict = Set[String]()

    for (line <- sources.getLines()) {
      dict += line
    }
    val sparkConf = new SparkConf().setAppName("BroadcastTest")
    val ctx = new SparkContext(sparkConf)
    val broadcastVar = ctx.broadcast(dict)


  }
}
