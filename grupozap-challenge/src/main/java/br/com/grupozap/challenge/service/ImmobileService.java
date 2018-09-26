package br.com.grupozap.challenge.service;

import static org.apache.commons.lang3.StringUtils.upperCase;
import static org.springframework.data.domain.PageRequest.of;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.grupozap.challenge.domain.Immobile;
import br.com.grupozap.challenge.repository.ImmobileRepository;

@Service
public class ImmobileService {

	private static final int PAGE_SIZE = 20;
	private static final String PORTAL_ZAP = "ZAP";
	private static final String PORTAL_VIVALREAL = "VIVAREAL";


	@Autowired
	private ImmobileRepository immobileRepository;



	public Page<Immobile> getPropertiesToRental(ImmobileParameters parameters) {

		if(PORTAL_ZAP.equals(upperCase(parameters.getPortal()))) {
			return immobileRepository.findAllImobileToRentalForZap(pageable(parameters));
		}

		if(PORTAL_VIVALREAL.equals(upperCase(parameters.getPortal()))) {
			return immobileRepository.findAllImobileToRentalForVivaReal(pageable(parameters));
		}

		return Page.empty();
	}


















	private Pageable pageable(ImmobileParameters parameters) {
		return of(parameters.getPage(), PAGE_SIZE);
	}




}
