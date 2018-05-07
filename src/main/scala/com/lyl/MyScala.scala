package com.lyl

import java.util

import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object MyScala {

  def main(args: Array[String]): Unit = {
    testKafkaProducer()
  }

  def testKafkaConsumer(args: Array[String]) = {
    if (args.length < 2) {
      System.err.println(s"""
                            |Usage: DirectKafkaWordCount <brokers> <topics>
                            |  <brokers> is a list of one or more Kafka brokers
                            |  <topics> is a list of one or more kafka topics to consume from
                            |
        """.stripMargin)
      System.exit(1)
    }

    val Array(brokers, topics) = args

    val sparkConf = new SparkConf().setAppName("DirectKafkaWorkCount")
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    val topicSet = topics.split(",").toSet
    val props = new util.HashMap[String, Object]()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    val messages = KafkaUtils.createDirectStream[String, String](ssc, props, topicSet)
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