package ai.jiffy.temporal.cadence.jiffycadence;

import ai.jiffy.temporal.cadence.jiffycadence.config.CadenceWorker;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JiffyCadenceApplication {

	private static Logger logger = Workflow.getLogger(JiffyCadenceApplication.class);

	@Autowired
	private static CadenceWorker cadenceWorker;

	public static void main(String[] args) {
		SpringApplication.run(JiffyCadenceApplication.class, args);
	}

}
