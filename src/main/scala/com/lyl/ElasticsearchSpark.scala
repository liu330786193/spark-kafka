package com.lyl

import org.apache.spark.SparkConf

import scala.tools.nsc.doc.model.Val

class ElasticsearchSpark {

  def main(args: Array[String]): Unit = {
    val path = "cat-agent.log"
    val sparkConf = new SparkConf().setMaster("local").setAppName("JavaLocalFileTest")
    sparkConf.set("es.nodes", "localhost")
    sparkConf.set("es.port", "9200")
    sparkConf.set("es.index.auto.create", "true")

    val numbers = Map("one" -> 1, "two" -> 2, "three" -> 3)

}
