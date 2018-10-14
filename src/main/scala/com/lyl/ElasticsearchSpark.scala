package com.lyl

import com.alibaba.fastjson.JSON
import org.apache.spark.{SparkConf, SparkContext}
import org.elasticsearch.spark._
import trace.Person

import scala.collection.mutable.ListBuffer

object ElasticsearchSpark {

  def main(args: Array[String]): Unit = {
//    case class Person(name:String,age:Int)
    val sparkConf = new SparkConf().setAppName("Custmer_Statistics").setMaster("local[2]")
    sparkConf.set("es.nodes","localhost")
    sparkConf.set("es.port","9200")
    sparkConf.set("es.index.auto.create", "true")
    sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(sparkConf)

    //读取文件
//    val log = sc.textFile("cat-agent.log")
    val log = sc.textFile("person.json")
    var list = ListBuffer[String]()
    list.append("111")
    log.foreach(p => {
//      val person = JSON.parseObject(p, classOf[Person])
      list.append(p)
      println(list)
    })
    println(list.toList)
//    sc.makeRDD(Seq(p)).saveToEs("spark/docs")

    /*log.foreach(p => {
//      val person = JSON.parseObject(p, classOf[Person]);
      val position = p.indexOf("{");
      val trace = p.substring(position , p.length);
      println(trace)
      val startTime = System.currentTimeMillis();
      val defaultTraceSegment = JSON.parseObject(trace, classOf[DefaultTraceSegment]);
      sc.makeRDD(Seq(defaultTraceSegment)).saveToEs("spark/trace/" + defaultTraceSegment.traceSegmentId)
    })*/


//    val mapper = new ObjectMapper()
//    mapper.registerModule(DefaultScalaModule)
//    val log = "{\"status\":200,\"http_user_agent\":\"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36\",\"request_method\":\"get\",\"timestamp\":\"2016-11-13T06:59:26.315Z\",\"http_url\":\"http://www.donews.com/it/201611/2942496.shtm\",\"http_x_forwarded_for\":\"221.0.90.218\",\"event\":\"load\",\"is_new\":1,\"http_referer\":\"https://www.baidu.com/link?url=btPCeZwsYyyONB-oYL7szsox4qjvynFPzoeZlocefakSUxZxj0gEOXoG9dIivWg7BqsooA1tMfZpS-FYdl5Gva&wd=&eqid=c3101a590000f2980000000258280f0d\",\"cookie\":\"441de3213207ac61f58be158e80cc194\",\"page_id\":\"23788cd5e93c8c870472bd1bd40f6c40\",\"short_cookie\":\"2cb494930448e4abf37577dd28c5947f\",\"appkey\":\"app_website\"}"

//    val obj = mapper.readValue(log, classOf[SdkBean])

//    println(obj.toString)

//    val traceSegment = JSON.parseObject(log, DefaultTraceSegment.class)
    /*log.foreach(t => {
      val position = t.indexOf("{");
      val trace = t.substring(position , t.length);
      println(trace)
      val traceSegment = parse(t).extract[DefaultTraceSegment]
      println(traceSegment)
    })*/
    /*log.foreach(l => {
      val position = l.indexOf("{");
      val trace = l.substring(position , l.length);
      println(trace)
      val json = JSON.parseObject(trace)
      val tracesegmentId = json.getString("traceSegmentId");
      println(tracesegmentId)
      val applicationId = json.getString("applicationId");
      println(applicationId)
      val ip = json.getString("ip");
      println(ip)
      val applicationInstanceId = json.getString("applicationInstanceId");
      println(applicationInstanceId)

      val spansJsonArray = JSON.parseArray(json.getString("spans"));
      spansJsonArray
      println()


    })*/
    //过滤Trace

    /*val numbers = Map("one" -> 1, "two" -> 2, "three" -> 3)
    val airports = Map("arrival" -> "Otopeni", "SFO" -> "San Fran")

    sc.makeRDD(Seq(numbers, airports)).saveToEs("spark/docs")*/
  }



}
