package br.com.grupozap.challenge.temp;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.grupozap.challenge.domain.Immobile;

@Repository
public interface Repo extends PagingAndSortingRepository<Immobile, String> {

}
