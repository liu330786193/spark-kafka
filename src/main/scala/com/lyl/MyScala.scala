package com.lyl

import java.util

import com.timevale.cat.api.jvm.JvmDTO
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object MyScala {

  def main(args: Array[String]): Unit = {
    testKafkaConsumer()
  }

  def testKafkaConsumer() = {
    var brokers = "localhost:9092"
    val topics = "cat-agent-gc"
    // Create context with 2 second batch interval
    val sparkConf = new SparkConf()
      .setAppName("DirectKafkaWordCount")
      .setMaster("local[2]")
      .set("spark.executor.memory","3g")

    val ssc = new StreamingContext(sparkConf, Seconds(2))

    // Create direct kafka stream with brokers and topics
    val topicsSet = topics.split(",").toSet
    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers)
    val messages = KafkaUtils.createDirectStream[JvmDTO, JvmDTO, ProtobufDecoder, ProtobufDecoder](
      ssc, kafkaParams, topicsSet)


    messages.foreachRDD(rdd => {
        println(rdd.count())
        if(rdd.count() != 0){
          rdd.foreach(r => {
            println(r._2.getIp)
          })
        }
      }
    )
    ssc.start()
    ssc.awaitTermination()
  }

  def testKafkaProducer() = {
    /*val sparkConf = new SparkConf().setAppName("DirectKafkaWordCountDemo")
    sparkConf.setMaster("local")
    val ssc = new StreamingContext(sparkConf, Seconds(3))*/
    var brokers = "localhost:9092"
    val topics = "topic-test"
    val messagesPerSec=1 //每秒发送几条信息
    val wordsPerMessage =4 //一条信息包括多少个单词
//    val topicSet = topics.split(",").toSet
    val props = new util.HashMap[String, Object]()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)
    while (true){
      (1 to messagesPerSec.toInt).foreach{mesageNum => {
          val str = (1 to wordsPerMessage.toInt)
            .map(x => scala.util.Random.nextInt(10).toString).mkString(" ")
          val message = new ProducerRecord[String, String](topics, null, str)
          producer.send(message)
          println(message)
        }
        Thread.sleep(3000)
      }
    }
  }

  def test() = {
    val conf = new SparkConf().setAppName("mySpark")
    conf.setMaster("local")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(List(1,2,3,4,5,6)).map(_*3)
    val mappedRDD = rdd.filter(_>10).collect()
    println(rdd.reduce(_+_))
    for (arg <- mappedRDD)
      print(arg + " ")
    println()
    print("math is work")
  }

}
