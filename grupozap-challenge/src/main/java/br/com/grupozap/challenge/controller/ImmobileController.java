package br.com.grupozap.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupozap.challenge.domain.Immobile;
import br.com.grupozap.challenge.service.ImmobileParameters;
import br.com.grupozap.challenge.service.ImmobileService;

@RestController
public class ImmobileController {

	@Autowired
	private ImmobileService immobileService;


	@GetMapping("/properties/sale")
	public Page<Immobile> getPropertiesToSale(RequestParameters parameters){
		return immobileService.getPropertiesToSale(immobileParameters(parameters));
	}








	private ImmobileParameters immobileParameters(RequestParameters parameters) {
		return ImmobileParameters.builder()
				.page(parameters.getPage())
				.portal(parameters.getPortal())
				.build();
	}




}
