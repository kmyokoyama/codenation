package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	private ScriptRepository repository;

	@Override
	public Quote getQuote() {
		Iterable<Script> results = this.repository.findAll();
		
		List<Script> resultsList = new ArrayList<>();
		
		results.iterator().forEachRemaining(resultsList::add);
		
		long numberOfRows = resultsList.size();
		
		if (numberOfRows == 0) {
			return new Quote();
		}
		
		long randomId = this.generateRandom(1, numberOfRows);
		
		Script resultScript = resultsList.get((int) randomId);
		
		return new Quote((int) resultScript.getId(), resultScript.getActor(), resultScript.getDetail());
	}

	@Override
	public Quote getQuoteByActor(String actor) {
		Iterable<Script> results = this.repository.findByActor(actor);

		List<Script> resultsList = new ArrayList<>();
		
		results.iterator().forEachRemaining(resultsList::add);
		
		long numberOfRows = resultsList.size();
		
		if (numberOfRows == 0) {
			return new Quote();
		}
		
		long randomId = this.generateRandom(1, numberOfRows);
		
		Script resultScript = resultsList.get((int) randomId);
		
		return new Quote((int) resultScript.getId(), resultScript.getActor(), resultScript.getDetail());
	}

	private long generateRandom(long min, long max) {
		return (Math.abs(new Random().nextLong()) % (max-min+1)) + min;
	}
}
