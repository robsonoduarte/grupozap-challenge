package br.com.grupozap.challenge.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TempoRepo {

	@Autowired
	private MongoTemplate mongoTemplate;


	public void temp() {

	}

}
