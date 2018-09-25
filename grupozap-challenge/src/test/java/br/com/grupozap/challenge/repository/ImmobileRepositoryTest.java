package br.com.grupozap.challenge.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.grupozap.challenge.domain.Immobile;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ImmobileRepositoryTest {

	@Autowired
	private ImmobileRepository repository;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}




	@Test
	public void shouldFindAllImobileThatIsEligibleToRentalForVivaReal() {

		Page<Immobile> pageResult =
				repository.findAllImobileToRentalForVivaReal(PageRequest.of(0, 20));


		assertThat(pageResult.getContent(), not(empty()));


	    //Um imóvel apenas é elegível pro portal Vivareal:
	    //Quando for aluguel e no máximo o valor for de R$ 4.000,00.
		//valor do condomínio não pode ser maior/igual que 30% do valor do aluguel.
		// Ele tem lat e lon diferente a 0.

		pageResult.getContent().forEach( immobile -> {
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLat(), not(0.0));
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLon(), not(0.0));
			assertThat(immobile.getPricingInfos().getBusinessType(), equalTo("RENTAL"));
			assertThat(immobile.getPricingInfos().getRentalTotalPrice(), lessThanOrEqualTo(4000l));
			assertThat(immobile.getPricingInfos().getMonthlyCondoFee(), lessThan((int) (immobile.getPricingInfos().getRentalTotalPrice() * 0.3)));
		});

	}


	@Test
	public void shouldFindAllImobileThatIsEligibleToRentalForVivaRealAndReturnInPaginationMode() {

		Page<Immobile> pageResult =
				repository.findAllImobileToRentalForVivaReal(PageRequest.of(0, 20));

		assertThat(pageResult.getContent(), hasSize(20));
		assertThat(pageResult.getPageable().getPageNumber(), equalTo(0));
		assertThat(pageResult.getPageable().getPageSize(), equalTo(20));
		assertThat(pageResult.getTotalElements(), equalTo(514L));

		pageResult =
				repository.findAllImobileToRentalForVivaReal(PageRequest.of(1, 20));

		assertThat(pageResult.getContent(), hasSize(20));
		assertThat(pageResult.getPageable().getPageNumber(), equalTo(1));
		assertThat(pageResult.getPageable().getPageSize(), equalTo(20));
		assertThat(pageResult.getTotalElements(), equalTo(514L));

	}






}
