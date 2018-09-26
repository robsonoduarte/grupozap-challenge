package br.com.grupozap.challenge.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.data.domain.PageRequest.of;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;

import br.com.grupozap.challenge.domain.Immobile;
import br.com.grupozap.challenge.repository.ImmobileRepository;


public class ImmobileServiceTest {


	@InjectMocks
	private ImmobileService service;


	@Mock
	private ImmobileRepository immobileRepository;


	@Mock
	private BoundingBoxGrupoZapService boundingBoxGrupoZapService;


	@Mock
	private ImmobileParameters immobileParameters;


	@Mock
	private Page<Immobile> page;


	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}




	@Test
	public void shouldGetThePropertiesToRentalForPortalZap() {

		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn("zap");
		when(immobileRepository.findAllImobileToRentalForZap(of(1, 20))).thenReturn(page);

		Page<Immobile> pageResult =
			  service.getPropertiesToRental(immobileParameters);

		assertThat(pageResult, equalTo(page));

		verify(immobileParameters).getPage();
		verify(immobileParameters).getPortal();
		verify(immobileRepository).findAllImobileToRentalForZap(of(1, 20));

	}



	@Test
	public void shouldGetThePropertiesToRentalForPortalVivaReal() {

		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn("vivareal");
		when(immobileRepository.findAllImobileToRentalForVivaReal(of(1, 20))).thenReturn(page);

		Page<Immobile> pageResult =
				service.getPropertiesToRental(immobileParameters);

		assertThat(pageResult, equalTo(page));

		verify(immobileParameters).getPage();
		verify(immobileParameters, times(2)).getPortal();
		verify(immobileRepository).findAllImobileToRentalForVivaReal(of(1, 20));

	}


	@Test
	public void shouldReturnOneEmptyPageOImmobileWhenPortalIsNotZapOrVivaReal() {

		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn(null);

		Page<Immobile> pageResult =
				service.getPropertiesToRental(immobileParameters);

		assertThat(pageResult, notNullValue());
		assertThat(pageResult.getContent(), hasSize(0));

		verify(immobileParameters, times(2)).getPortal();
		verify(immobileParameters, times(0)).getPage();
		verify(immobileRepository, times(0)).findAllImobileToRentalForVivaReal(of(1, 20));
		verify(immobileRepository, times(0)).findAllImobileToRentalForZap(of(1, 20));

	}



}