package br.com.grupozap.challenge.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TempLoadData {


	@Autowired
	private TempMongoLoad temp;


	@EventListener
	public void temp(ContextRefreshedEvent event) throws Exception {
		System.out.println("After startup");
		temp.load();
		System.out.println("Done startup");
	}
}
