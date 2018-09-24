package br.com.grupozap.challenge.temp;

import java.net.URISyntaxException;

import org.springframework.stereotype.Component;

@Component
public class TempLoadFile {

	public String path() throws URISyntaxException {
		return TempLoadData.class.getResource("/temp/temp").toURI().getPath();
	}
}
