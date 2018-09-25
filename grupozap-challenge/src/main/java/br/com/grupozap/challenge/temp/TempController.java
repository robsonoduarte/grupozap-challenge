package br.com.grupozap.challenge.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.grupozap.challenge.domain.Immobile;

@RestController
public class TempController {

	@Autowired
	private Repo repo;

/*	@Autowired
	private RepoImpl repoimpl;*/

	@GetMapping("temp")
	public Page<Immobile> get(Temp temp) {
/*		System.out.println(temp.page);
		System.out.println(temp.portal);
		Page<Immobile> page = repo.findAll(PageRequest.of(temp.page, 20));

		Page<Immobile> x = repoimpl.aluguelVivaReal();*/

		return repo.aluguelVivaReal(PageRequest.of(temp.page, 20));
	}





	public static class Temp{
		public String portal;
		public int page;
		public String getPortal() {
			return portal;
		}
		public void setPortal(String portal) {
			this.portal = portal;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}


	}



}
