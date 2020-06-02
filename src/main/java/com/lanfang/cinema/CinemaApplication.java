package com.lanfang.cinema;


import com.lanfang.cinema.system.Spark.*;
import com.lanfang.cinema.system.controller.WebSocketServerController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@SpringBootApplication
public class CinemaApplication {
    public static final String HDFS = "hdfs://192.168.163.110:8020";
    public static final String Spark = "spark://192.168.163.110:7077";
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Map<String, String> path = new HashMap<String, String>();
        path.put("data_c", "D:/study/idealspace/cinema/src/main/java/logfile/small.csv");
        path.put("spark",Spark);
        path.put("HDFS",HDFS);
        path.put("data",HDFS + "/data");
        path.put("Step1Input", HDFS + "/data/small.csv");
        path.put("Step1Output", path.get("data") + "/step1");
        path.put("Step2Input", path.get("Step1Output"));
        path.put("Step2Output", path.get("data") + "/step2");
        path.put("Step3Input1", path.get("Step1Output"));
        path.put("Step3Output1", path.get("data") + "/step3_1");
        path.put("Step3Input2", path.get("Step2Output"));
        path.put("Step3Output2", path.get("data") + "/step3_2");
        path.put("Step4Input1", path.get("Step3Output1"));
        path.put("Step4Input2", path.get("Step3Output2"));
        path.put("Step4Output", path.get("data") + "/step4");

//        Step_1.run(path);
//        Step_2.run(path);
//        Step_3.run(path);
//        try {
//            Step_4.run(path);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        ConfigurableApplicationContext applicationContext = SpringApplication.run(CinemaApplication.class, args);
        WebSocketServerController.setApplicationContext(applicationContext);
        Step_4.setApplicationContext(applicationContext);

    }



}
