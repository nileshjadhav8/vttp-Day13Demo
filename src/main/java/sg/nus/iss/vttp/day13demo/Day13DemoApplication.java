package sg.nus.iss.vttp.day13demo;

import java.util.List;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.nus.iss.vttp.day13demo.utility.Utility;;

@SpringBootApplication
public class Day13DemoApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(Day13DemoApplication.class);

		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List<String> opsVal = appArgs.getOptionValues("dataDir");

		System.out.println(opsVal);


		if(opsVal != null){
			//create dir
			Utility.createDir(opsVal.get(0));
		}else{
			//terminate program
			System.out.println("no data dir provided..");
			System.exit(1);
		}
		app.run(args);
		//SpringApplication.run(Day13DemoApplication.class, args);
	}

	

}
