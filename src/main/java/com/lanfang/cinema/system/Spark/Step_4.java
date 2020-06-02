package com.lanfang.cinema.system.Spark;

import com.lanfang.cinema.CinemaApplication;
import com.lanfang.cinema.system.Service.ReService;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import scala.Tuple2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@Component
public class Step_4 {
    public static final Pattern SPACE = Pattern.compile("[\t,]");
    private static ApplicationContext applicationContext;
    private ReService reService;

    public static void run(Map<String, String> path) throws Exception {
        SparkSession spark = SparkSession
                .builder().master(path.get("spark"))

                .getOrCreate();

        spark.sparkContext()
                .addJar("D:\\study\\idealspace\\cinema\\out\\artifacts\\cinema_jar\\cinema.jar");
        Configuration conf = spark.sparkContext().hadoopConfiguration();
        HdfsDAO hdfs = new HdfsDAO(path.get("HDFS"), conf);
        hdfs.rmr(path.get("Step4Output"));



        JavaRDD<String> step_3_1 = spark.read().textFile(path.get("Step4Input1")).javaRDD();

        JavaRDD<String> step_3_2 = spark.read().textFile(path.get("Step4Input2")).javaRDD();


        JavaPairRDD<String, String> step_4_1 = step_3_2.mapToPair(
                new PairFunction<String, String, String>() {

                    @Override
                    public Tuple2<String, String> call(String s) {
                        String[] tokens = SPACE.split(s);
                        String[] v1 = tokens[0].replaceAll("\\(","").replaceAll("\\)","").split(":");
                        String itemID1 = v1[0];
                        String itemID2 = v1[1];
                        String num = tokens[1];
                        String k = itemID1;
                        String v = "A:" + itemID2 + "," + num;

                        return new Tuple2<String, String>( k, v);
                    }
                });


        JavaPairRDD<String, String> step_4_2 = step_3_1.mapToPair(
                new PairFunction<String, String, String>() {

                    @Override
                    public Tuple2<String, String> call(String s) {
                        String[] tokens = SPACE.split(s);
                        String[] v2 = tokens[1].replaceAll("\\(","").replaceAll("\\)","").split(":");
                        String itemID = tokens[0].replaceAll("\\(","").replaceAll("\\)","");
                        String userID = v2[0];
                        String pref = v2[1];

                        String k = itemID;
                        String v = "B:" + userID + "," + pref;

                        return new Tuple2<String, String>( k, v);
                    }
                });

        JavaPairRDD<String, String> step_4_3 = step_4_1.join(step_4_2).mapToPair(
                new PairFunction<Tuple2<String, Tuple2<String, String>>, String, String>() {

                    @Override
                    public Tuple2<String, String> call(Tuple2<String, Tuple2<String, String>> stringTuple2Tuple2) throws Exception {
                        String[] tokens = SPACE.split(stringTuple2Tuple2._2.toString().replaceAll("\\(","").replaceAll("\\)",""));
                        String[] v1 = tokens[0].split(":");
                        String item =v1[1];
                        String[] v2 =tokens[2].split(":");
                        String user =v2[1];

                        int num =Integer.parseInt(tokens[1]);
                        double pref =Double.parseDouble(tokens[3]);

                        double r =num*pref;

                        String k =user +":"+item;
                        String v = String.valueOf(r);

                        return new Tuple2<String, String>( k, v);
                    }


                });

        JavaPairRDD<String, String> out_4 = step_4_3.reduceByKey(
                new Function2<String, String, String>() {
                    @Override
                    public String call(String v1, String v2) throws Exception {
                        double re =Double.parseDouble(v1)+Double.parseDouble(v2);
                        return String.valueOf(re);
                    }

                });


        List<Tuple2<String, String>> output = out_4.collect();
        Step_4 s =new Step_4();
        for (Tuple2<?,?> tuple : output) {
            System.out.println(tuple);
            s.save(Long.valueOf(tuple._1.toString().split(":")[0])
                    ,Long.valueOf(tuple._1.toString().split(":")[1])
                    ,Double.parseDouble(tuple._2.toString()));
        }
        spark.stop();
    }

    public static void setApplicationContext(ApplicationContext context) {
        Step_4.applicationContext = context;
    }

    public void save(Long user_id,Long movie_id,Double score)throws Exception{
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet rs = null;

        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://127.0.0.1:3306/cinema?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
            String user = "root";
            String password = "16210220312";
            connection = DriverManager.getConnection(url, user, password);
            // 获取statement，preparedStatement
            String sql = "insert into re (user_id,movie_id,score)values(?,?,?)";
            prepareStatement = connection.prepareStatement(sql);
            // 执行插入
            prepareStatement.setLong(1, user_id);
            prepareStatement.setLong(2, movie_id);
            prepareStatement.setDouble(3, score);
            prepareStatement.executeUpdate();
            connection.close();

        } finally {
            // 关闭连接，释放资源
            if (rs != null) {
                rs.close();
            }
            if (prepareStatement != null) {
                prepareStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


}