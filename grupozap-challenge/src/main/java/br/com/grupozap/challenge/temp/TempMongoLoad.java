package br.com.grupozap.challenge.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.grupozap.challenge.domain.Immobile;

@Component
public class TempMongoLoad {

	@Autowired
	private TempLoadFile temp;


	@Autowired
	private TempoRepo tempoRepo;


	@Autowired
	private Repo repo;


	@SuppressWarnings("deprecation")
	public void load() throws Exception {



		ObjectMapper objectMapper = new ObjectMapper();

		temp().forEach( json -> {

			Immobile immobile;
			try {
				immobile = objectMapper.readValue(json, Immobile.class);
				System.out.println(immobile);
				repo.save(immobile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		long count = repo.count();

		System.out.println(count);
	}








	private List<String> temp(){
		List<String> doc =
			      new BufferedReader(new InputStreamReader(TempLoadData.class.getResourceAsStream("/temp/source"),
			          StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
		return doc;
	}







}
