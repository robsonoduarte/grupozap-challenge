package br.com.grupozap.challenge.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.grupozap.challenge.domain.Immobile;

public interface ImmobileRepository extends MongoRepository<Immobile, String> {

	@Query("{ $and: [{\"pricingInfos.businessType\" : \"RENTAL\"},{ \"pricingInfos.rentalTotalPrice\": { $lte: 4000 } }, { $where: \"this.pricingInfos.monthlyCondoFee  <= this.pricingInfos.rentalTotalPrice * 0.3\" }, {\"address.geoLocation.precision\": {$ne : \"NO_GEOCODE\"}} ] }")
	Page<Immobile> findAllImobileToRentalForVivaReal(Pageable pageable);

}
