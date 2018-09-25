package br.com.grupozap.challenge.temp;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import br.com.grupozap.challenge.domain.Immobile;

/*@Repository*/
public class RepoImpl implements RepoCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Page<Immobile> aluguelZap() {

		PageRequest z = PageRequest.of(0, 20);

		Query y =
				query(where("pricingInfos.businessType").is("RENTAL").and("pricingInfos.rentalTotalPrice").gte(3500l).and("address.geoLocation.precision").ne("NO_GEOCODE")).with(z);

		List<Immobile> find = mongoTemplate.find(y, Immobile.class);

		return PageableExecutionUtils.getPage(find, z, () -> mongoTemplate.count(y, Immobile.class));
	}


	/*@Override*/
	public Page<Immobile> vendaZap() {

		PageRequest z = PageRequest.of(0, 20);

		Query y =
				query(where("pricingInfos.businessType").is("SALE").and("pricingInfos.price").gte(600000l).and("address.geoLocation.precision").ne("NO_GEOCODE").and("usableAreas").gt(3500l)).with(z);

		List<Immobile> find = mongoTemplate.find(y, Immobile.class);

		return PageableExecutionUtils.getPage(find, z, () -> mongoTemplate.count(y, Immobile.class));
	}



	/*@Override*/
	public Page<Immobile> aluguelVivaReal() {

		PageRequest z = PageRequest.of(0, 20);

		//.and("pricingInfos.monthlyCondoFee").lt("pricingInfos.rentalTotalPrice - (pricingInfos.rentalTotalPrice * 0.30)")
		Query y =
				query(where("pricingInfos.businessType").is("RENTAL").and("pricingInfos.rentalTotalPrice").lte(4000).and("address.geoLocation.precision").ne("NO_GEOCODE")).with(z);

		List<Immobile> find = mongoTemplate.find(y, Immobile.class);






		return PageableExecutionUtils.getPage(find, z, () -> mongoTemplate.count(y, Immobile.class));
	}

}
