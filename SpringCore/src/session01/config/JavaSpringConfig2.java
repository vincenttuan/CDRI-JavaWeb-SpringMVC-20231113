package session01.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(value = "session01.bean")
@ComponentScan(basePackages = "session01.bean")
public class JavaSpringConfig2 {
	
}
