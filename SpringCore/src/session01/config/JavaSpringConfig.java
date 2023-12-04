package session01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import session01.bean.Hello;
import session01.bean.Lotto;

// JavaSpringConfig 是一個 Spring 配置檔, 來管理所定義的物件
@Configuration
public class JavaSpringConfig {
	
	@Bean(name = "hello") // bean 資源名稱
	@Scope("singleton") // 物件範圍(生命週期). 預設 singleton(單一物件-共用), prototype(多物件-每次調用都新建一個) 
	public Hello getHello() {
		Hello hello = new Hello();
		return hello;
	}
	
	@Bean(name = "lotto")
	@Scope("prototype")
	public Lotto getLotto() {
		Lotto lotto = new Lotto();
		return lotto;
	}
	
}
