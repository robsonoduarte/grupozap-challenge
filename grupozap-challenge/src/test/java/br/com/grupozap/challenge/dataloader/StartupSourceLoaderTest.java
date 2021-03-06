package br.com.grupozap.challenge.dataloader;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.event.ContextRefreshedEvent;

public class StartupSourceLoaderTest {


	@InjectMocks
	private StartupSourceLoader soucerLoader;


	@Mock
	private MongoSourceLoader mongoSourceLoader;


	@Mock
	private ContextRefreshedEvent event;



	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}


	@Test
	public void shouldLoadTheSouceInStartupOfSpringBoot() throws Exception {
		soucerLoader.load(event);
		verify(mongoSourceLoader).loadSourceInMongo();
	}

}
