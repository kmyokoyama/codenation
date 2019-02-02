package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class QuoteController {

	@Autowired
	private QuoteService service;

	@GetMapping("quote")
	public @ResponseBody Quote getQuote() {
		return service.getQuote();
	}

	@GetMapping("quote/{actor}")
	public @ResponseBody Quote getQuoteByActor(@PathVariable("actor") String actor) {
		return service.getQuoteByActor(actor);
	}

}
