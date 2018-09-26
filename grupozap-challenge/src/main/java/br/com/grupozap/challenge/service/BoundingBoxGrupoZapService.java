package br.com.grupozap.challenge.service;

import org.springframework.stereotype.Service;

import br.com.grupozap.challenge.domain.Location;


@Service
public class BoundingBoxGrupoZapService {


	private static final double MIN_LON = -46.693419;
	private static final double MIN_LAT = -23.568704;
	private static final double MAX_LON = -46.641146;
	private static final double MAX_LAT = -23.546686;



	public boolean isBoundingbox(Location location) {

		if(location == null) {
			throw new IllegalArgumentException("location is null");
		}

		return MIN_LAT <= location.getLat()
				&& MAX_LAT >= location.getLat()
				&& MIN_LON <= location.getLon()
				&& MAX_LON >= location.getLon();
	}


}
