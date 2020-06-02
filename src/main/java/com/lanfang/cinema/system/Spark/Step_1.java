package com.lanfang.cinema.system.Spark;

import com.lanfang.cinema.CinemaApplication;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Step_1 {
    public static final Pattern SPACE = Pattern.compile("[\t,]");
    public static void run(Map<String, String> path) throws IOException {

        SparkSession spark = SparkSession
                .builder().master(path.get("spark"))

                .getOrCreate();

        spark.sparkContext()
                .addJar("D:\\study\\idealspace\\cinema\\out\\artifacts\\cinema_jar\\cinema.jar");
        Configuration conf = spark.sparkContext().hadoopConfiguration();
        HdfsDAO hdfs = new HdfsDAO(path.get("HDFS"), conf);
        hdfs.copyFile(path.get("data_c"),path.get("data"));
        hdfs.rmr(path.get("Step1Output"));

        JavaRDD<String> lines = spark.read().textFile(path.get("Step1Input")).javaRDD();


        /**
         * map 键值对 ，类似于MR的map方法
         * pairFunction<T,K,V>: T:输入类型；K,V：输出键值对
         * 表示输入类型为T,生成的key-value对中的key类型为k,value类型为v,对本例,T=String, K=String, V=Integer(计数)
         * 需要重写call方法实现转换
         */

        JavaPairRDD<Integer, String> step_1 = lines.mapToPair(
                new PairFunction<String, Integer, String>() {

                    //scala.Tuple2<K,V> call(T t)
                    //Tuple2为scala中的一个对象,call方法的输入参数为T,即输入一个单词s,新的Tuple2对象的key为这个单词,计数为1
                    @Override
                    public Tuple2<Integer, String> call(String s) {
                        String[] tokens = SPACE.split(s);
                        int userID = Integer.parseInt(tokens[0]);
                        String itemID = tokens[1];
                        String pref = tokens[2];
                        return new Tuple2<Integer, String>(userID, itemID+":"+pref);
                    }
                });


        JavaPairRDD<Integer, String> out_1 = step_1.reduceByKey(
                new Function2<String, String, String>() {
                    @Override
                    public String call(String v1, String v2) throws Exception {
                        return v1+","+v2;
                    }

                });



        out_1.saveAsTextFile(path.get("Step1Output"));

//        List<Tuple2<Integer, String>> output = out_1.collect();
//        for (Tuple2<?,?> tuple : output) {
//            System.out.println(tuple._1() + ": " + tuple._2());
//        }
        spark.stop();

    }
}
