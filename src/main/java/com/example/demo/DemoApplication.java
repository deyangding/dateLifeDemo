package com.example.demo;

import com.example.demo.export.Export;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.util.StringUtils;

import java.io.IOException;

@SpringBootApplication
@EnableCaching
public class DemoApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);
//		String argqqqs = new Gson().toJson(args);
//		System.out.println("#########################"+ argqqqs+ ":#"+args.length);
//		Export export = new Export();
//		if (args == null || args.length==0) {
//			System.out.println("######################### fa");
//			export.writeWithoutHead(false);;
//		} else {
//			System.out.println("######################### true");
//			export.writeWithoutHead(true);
//		}
	}
}
