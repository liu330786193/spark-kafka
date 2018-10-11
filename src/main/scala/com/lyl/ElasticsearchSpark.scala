package com.lyl

import com.alibaba.fastjson.JSON
import com.tsign.cat.api.trace.TraceSegmentDTO
import org.apache.avro.ipc.specific.Person
import org.apache.spark.{SparkConf, SparkContext}
import org.elasticsearch.spark._
import org.json4s._
import org.json4s.jackson.JsonMethods._

import scala.util.parsing.json.JSONObject

object ElasticsearchSpark {

  def main(args: Array[String]): Unit = {
    case class Person(name:String,age:Int)
    val sparkConf = new SparkConf().setAppName("Custmer_Statistics").setMaster("local[2]")
    sparkConf.set("es.nodes","localhost");
    sparkConf.set("es.port","9200");
    sparkConf.set("es.index.auto.create", "true");
    val sc = new SparkContext(sparkConf)

    //读取文件
//    val log = sc.textFile("cat-agent.log")
    val log = sc.textFile("person.json")

    log.foreach(l => {
//      val position = l.indexOf("{");
//      val trace = l.substring(position - 1 , l.length);
//      println(trace)
      val json = JSON.parseObject(l)
      val tracesegmentId = json.getString("name");
      println(tracesegmentId)
    })
    //过滤Trace

    /*val numbers = Map("one" -> 1, "two" -> 2, "three" -> 3)
    val airports = Map("arrival" -> "Otopeni", "SFO" -> "San Fran")

    sc.makeRDD(Seq(numbers, airports)).saveToEs("spark/docs")*/
  }

}
