package challenge;

import org.springframework.stereotype.Service;

@Service
public interface QuoteService {
	Quote getQuote();
	Quote getQuoteByActor(String actor);
}
