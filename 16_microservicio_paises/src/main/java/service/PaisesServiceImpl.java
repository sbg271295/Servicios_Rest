package service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import model.Pais;
@Service
public class PaisesServiceImpl implements PaisesService {

	String url="https://restcountries.com/v2/all";
	
	RestClient restClient;
	
	public PaisesServiceImpl(RestClient restClient) {
		this.restClient = restClient;
	}

	@Override
	public List<String> continentes() {
		return Arrays.stream(restClient
				.get()
				.uri(url)
				.retrieve()
				.body(Pais[].class)) //Stream<Pais>
				.map(p->p.getContinente())
				.distinct()
				.toList();
	}

	@Override
	public List<Pais> paisesPorContinente(String continente) {
		return Arrays.stream(restClient
				.get()
				.uri(url)
				.retrieve()
				.body(Pais[].class)) //Stream<Pais>
				.filter(p->p.getContinente().equals(continente))
				.toList();
	}

}
