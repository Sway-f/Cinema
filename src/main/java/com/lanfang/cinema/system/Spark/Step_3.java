package com.lanfang.cinema.system.Spark;

import com.lanfang.cinema.CinemaApplication;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Step_3 {
    public static final Pattern SPACE = Pattern.compile("[\t,]");
    public static void run(Map<String, String> path) throws IOException {

        SparkSession spark = SparkSession
                .builder().master(path.get("spark"))

                .getOrCreate();

        spark.sparkContext()
                .addJar("D:\\study\\idealspace\\cinema\\out\\artifacts\\cinema_jar\\cinema.jar");
        Configuration conf = spark.sparkContext().hadoopConfiguration();
        HdfsDAO hdfs = new HdfsDAO(path.get("HDFS"), conf);
        hdfs.rmr(path.get("Step3Output1"));
        hdfs.rmr(path.get("Step3Output2"));
        JavaRDD<String> lines = spark.read().textFile(path.get("Step2Input")).javaRDD();

        JavaRDD<String> step_1 = spark.read().textFile(path.get("Step3Input1")).javaRDD();

        JavaRDD<String> step_2 = spark.read().textFile(path.get("Step3Input2")).javaRDD();


        JavaRDD<Tuple2<Integer, String>> step_3_1 = step_1.flatMap(
                new FlatMapFunction<String, Tuple2<Integer, String>>() {
                    @Override
                    public Iterator<Tuple2<Integer, String>> call(String s) {
                        String[] tokens = SPACE.split(s);

                        List<Tuple2<Integer, String>> result = new ArrayList<Tuple2<Integer, String>>();
                        for (int i = 1; i < tokens.length; i++) {
                            String[] vector = tokens[i].split(":");
                            int itemID = Integer.parseInt(vector[0].replaceAll("\\(","").replaceAll("\\)",""));
                            String pref = vector[1].replaceAll("\\(","").replaceAll("\\)","");
                            Tuple2<Integer, String> R = new Tuple2<Integer, String>(itemID,tokens[0]+":"+pref);


                            result.add(R);
                        }
                        return result.iterator();
                    }
                });


        JavaRDD<Tuple2<String, Integer>> step_3_2 = step_2.flatMap(
                new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public Iterator<Tuple2<String, Integer>> call(String s) {
                        String[] tokens = SPACE.split(s);

                        List<Tuple2<String, Integer>> result = new ArrayList<Tuple2<String, Integer>>();
                        Tuple2<String, Integer> R = new Tuple2<String, Integer>(tokens[0].replaceAll("\\(","").replaceAll("\\)",""),Integer.parseInt(tokens[1].replaceAll("\\(","").replaceAll("\\)","")));

                        result.add(R);
                        return result.iterator();
                    }
                });
        step_3_2.saveAsTextFile(path.get("Step3Output2"));
        step_3_1.saveAsTextFile(path.get("Step3Output1"));

        spark.stop();

    }
}
