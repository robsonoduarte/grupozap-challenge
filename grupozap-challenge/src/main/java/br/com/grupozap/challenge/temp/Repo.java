package br.com.grupozap.challenge.temp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.grupozap.challenge.domain.Immobile;

@Repository
public interface Repo extends PagingAndSortingRepository<Immobile, String>{


	@Query("{ $and: [{\"pricingInfos.businessType\" : \"RENTAL\"},{ \"pricingInfos.rentalTotalPrice\": { $lte: 4000 } }, { $where: \"this.pricingInfos.monthlyCondoFee  <= this.pricingInfos.rentalTotalPrice * 0.3\" }, {\"address.geoLocation.precision\": {$ne : \"NO_GEOCODE\"}} ] }")
	Page<Immobile> aluguelVivaReal(Pageable pageable);


}
