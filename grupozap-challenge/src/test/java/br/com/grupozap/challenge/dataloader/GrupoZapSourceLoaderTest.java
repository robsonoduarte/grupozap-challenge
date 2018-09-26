package br.com.grupozap.challenge.dataloader;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import br.com.grupozap.challenge.dataloader.GrupoZapSourceLoader;

public class GrupoZapSourceLoaderTest {


	@InjectMocks
	private GrupoZapSourceLoader sourceLoader;


	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void shouldLoadTheFileSourceOfGrupoZapAndReturnOneListOfString() throws Exception {
		List<String> source = sourceLoader.loadLines();
		assertThat(source, hasSize(10000));
	}

}
