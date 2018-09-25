package br.com.grupozap.challenge.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

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



	// TESTES PARA FIND DE IMOVEIS LEGIVEIS PARA O VIVAREAL

	@Test
	public void shouldFindAllImobileThatIsEligibleToRentalForVivaReal() {

		Page<Immobile> pageResult =
				repository.findAllImobileToRentalForVivaReal(PageRequest.of(0, 20));


		assertThat(pageResult.getContent(), not(empty()));


	    //Um imóvel apenas é elegível pro portal Vivareal:
	    //Quando for aluguel e no máximo o valor for de R$ 4.000,00.
		//valor do condomínio não pode ser maior/igual que 30% do valor do aluguel.
		// Ele tem lat e lon diferente de 0.

		pageResult.getContent().forEach( immobile -> {
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLat(), not(0.0));
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLon(), not(0.0));
			assertThat(immobile.getPricingInfos().getBusinessType(), equalTo("RENTAL"));
			assertThat(immobile.getPricingInfos().getRentalTotalPrice(), lessThanOrEqualTo(4000l));
			assertThat(immobile.getPricingInfos().getMonthlyCondoFee(), lessThan((int) (immobile.getPricingInfos().getRentalTotalPrice() * 0.3)));
		});

	}


	@Test
	public void shouldFindAllImobileThatIsEligibleToSaleForVivaReal() {

		Page<Immobile> pageResult =
				repository.findAllImobileToSaleForVivaReal(PageRequest.of(0, 20));

		assertThat(pageResult.getContent(), not(empty()));

		//Um imóvel apenas é elegível pro portal Vivareal:
		// Quando for venda e no máximo o valor for de R$ 700.000,00.
		// Ele tem lat e lon diferente de 0.

		pageResult.getContent().forEach( immobile -> {
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLat(), not(0.0));
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLon(), not(0.0));
			assertThat(immobile.getPricingInfos().getBusinessType(), equalTo("SALE"));
			assertThat(immobile.getPricingInfos().getRentalTotalPrice(), lessThanOrEqualTo(700000l));
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


	@Test
	public void shouldFindAllImobileThatIsEligibleToSAleForVivaRealAndReturnInPaginationMode() {

		Page<Immobile> pageResult =
				repository.findAllImobileToSaleForVivaReal(PageRequest.of(0, 20));

		assertThat(pageResult.getContent(), hasSize(20));
		assertThat(pageResult.getPageable().getPageNumber(), equalTo(0));
		assertThat(pageResult.getPageable().getPageSize(), equalTo(20));
		assertThat(pageResult.getTotalElements(), equalTo(4528l));

		pageResult =
				repository.findAllImobileToSaleForVivaReal(PageRequest.of(1, 20));

		assertThat(pageResult.getContent(), hasSize(20));
		assertThat(pageResult.getPageable().getPageNumber(), equalTo(1));
		assertThat(pageResult.getPageable().getPageSize(), equalTo(20));
		assertThat(pageResult.getTotalElements(), equalTo(4528l)); // TODO: remover ou adicionar comentário que esse número não pode mudar no arquivo...

	}




	// TESTES PARA FIND DE IMOVEIS LEGIVEIS PARA O ZAP

	@Test
	public void shouldFindAllImobileThatIsEligibleToRentalForVivaZap() {

		Page<Immobile> pageResult =
				repository.findAllImobileToRentalForZap(PageRequest.of(0, 20));


		assertThat(pageResult.getContent(), not(empty()));

	    //Um imóvel apenas é elegível pro portal Vivareal:
	    //Quando for aluguel e no mínimo o valor for de R$ 3.500,00.
		// Ele tem lat e lon diferente de 0.

		pageResult.getContent().forEach( immobile -> {
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLat(), not(0.0));
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLon(), not(0.0));
			assertThat(immobile.getPricingInfos().getBusinessType(), equalTo("RENTAL"));
			assertThat(immobile.getPricingInfos().getRentalTotalPrice(), greaterThanOrEqualTo(3500l));
		});

	}



	@Test
	public void shouldFindAllImobileThatIsEligibleToRentalForZapAndReturnInPaginationMode() {

		Page<Immobile> pageResult =
				repository.findAllImobileToRentalForZap(PageRequest.of(0, 20));

		assertThat(pageResult.getContent(), hasSize(20));
		assertThat(pageResult.getPageable().getPageNumber(), equalTo(0));
		assertThat(pageResult.getPageable().getPageSize(), equalTo(20));
		assertThat(pageResult.getTotalElements(), equalTo(933L));

		pageResult =
				repository.findAllImobileToRentalForZap(PageRequest.of(1, 20));

		assertThat(pageResult.getContent(), hasSize(20));
		assertThat(pageResult.getPageable().getPageNumber(), equalTo(1));
		assertThat(pageResult.getPageable().getPageSize(), equalTo(20));
		assertThat(pageResult.getTotalElements(), equalTo(933L));

	}



}
