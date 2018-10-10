package com.lyl;

import org.apache.spark.SparkConf;
import scala.collection.Map;
import scala.tools.nsc.doc.model.Val;


public class JavaLocalFileTest {

    public static void main(String[] args) {
        String path = "cat-agent.log";
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("JavaLocalFileTest");
        /*JavaSparkContext jsc = new JavaSparkContext(sparkConf);

        JavaRDD<String> lines = jsc.textFile(path);

        for (String line : lines.collect()){
            System.out.println(line);
        }
        System.out.println(lines.count());
        System.out.println(lines.first());
        jsc.close();*/

        sparkConf.set("es.nodes", "www.iteblog.com");
        sparkConf.set("es.port", "9200");
        sparkConf.set("es.index.auto.create", "true");



    }

}
