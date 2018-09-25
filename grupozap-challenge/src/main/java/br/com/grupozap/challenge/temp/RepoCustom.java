package br.com.grupozap.challenge.temp;

import org.springframework.data.domain.Page;

import br.com.grupozap.challenge.domain.Immobile;

public interface RepoCustom {

	Page<Immobile> aluguelZap();
}
