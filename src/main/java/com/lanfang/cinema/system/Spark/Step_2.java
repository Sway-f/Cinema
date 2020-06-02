package com.lanfang.cinema.system.Spark;

import com.lanfang.cinema.CinemaApplication;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Step_2 {
    public static final Pattern SPACE = Pattern.compile("[\t,]");
    public static void run(Map<String, String> path) throws IOException {

        SparkSession spark = SparkSession
                .builder().master(path.get("spark"))

                .getOrCreate();

        spark.sparkContext()
                .addJar("D:\\study\\idealspace\\cinema\\out\\artifacts\\cinema_jar\\cinema.jar");
        Configuration conf = spark.sparkContext().hadoopConfiguration();
        HdfsDAO hdfs = new HdfsDAO(path.get("HDFS"), conf);
        hdfs.rmr(path.get("Step2Output"));

        JavaRDD<String> lines = spark.read().textFile(path.get("Step2Input")).javaRDD();




        JavaRDD<String> one = lines.flatMap(
                new FlatMapFunction<String, String>() {
                    @Override
                    public Iterator<String> call(String s) {
                        String[] tokens = SPACE.split(s);
                        List<String> list = new ArrayList<String>();
                        for (int i = 1; i < tokens.length; i++) {
                            String itemID = tokens[i].split(":")[0];

                            for (int j = 1; j < tokens.length; j++) {
                                String itemID2 = tokens[j].split(":")[0];
                                list.add( itemID + ":" + itemID2);
                            }
                        }
                        return list.iterator();
                    }
                });

        JavaPairRDD<String, Integer> step_2 = one.mapToPair(
                new PairFunction<String, String, Integer>() {

                    @Override
                    public Tuple2<String, Integer> call(String s) {
                        return new Tuple2<String, Integer>(s, 1);
                    }
                });

        JavaPairRDD<String, Integer> out_2 = step_2.reduceByKey(
                new Function2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer i1, Integer i2) {
                        return i1 + i2;
                    }
                });

        out_2.saveAsTextFile(path.get("Step2Output"));
//        List<Tuple2<String, Integer>> output = out_2.collect();
//        for (Tuple2<?,?> tuple : output) {
//            System.out.println(tuple);
//        }
        spark.stop();
    }
}
