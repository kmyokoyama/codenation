package challenge;

import static java.util.Comparator.comparing;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import challenge.service.CsvFileStreamer;
import challenge.service.PlayerBean;

public class Main {
	
	private static String FILEPATH = "data.csv";

	// Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
	public int q1() {
		CsvFileStreamer<PlayerBean> streamer = this.getStreamer();
		
		int result = 0;
		
		try {
			result = (int) streamer.getStream()
					.map(PlayerBean::getNationality)
					.distinct()
					.count();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// Quantos clubes (coluna `club`) diferentes existem no arquivo?
	// Obs: Existem jogadores sem clube.
	public int q2() {
		CsvFileStreamer<PlayerBean> streamer = this.getStreamer();
		
		int result = 0;
		
		try {
			result = (int) streamer.getStream()
					.map(PlayerBean::getClub)
					.filter(c -> !c.isEmpty())
					.distinct()
					.count();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// Liste o primeiro nome (coluna `full_name`) dos 20 primeiros jogadores.
	public List<String> q3() {
		CsvFileStreamer<PlayerBean> streamer = this.getStreamer();
		
		List<String> result = new ArrayList<>();
		
		try {
			result = streamer.getStream()
					.limit(20)
					.map(PlayerBean::getFullName)
					.collect(Collectors.toList());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
	// (utilize as colunas `full_name` e `eur_release_clause`)
	public List<String> q4() {
		CsvFileStreamer<PlayerBean> streamer = this.getStreamer();
		
		List<String> result = new ArrayList<>();
		
		try {
			result = streamer.getStream()
					.filter(p -> Objects.nonNull(p.getEurReleaseClause()))
					.sorted(
						comparing(PlayerBean::getEurReleaseClause)
						.reversed()
					)
					.limit(10)
					.map(PlayerBean::getFullName)
					.collect(Collectors.toList());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
	// (utilize as colunas `full_name` e `birth_date`)
	public List<String> q5() {
		CsvFileStreamer<PlayerBean> streamer = this.getStreamer();
		
		List<String> result = new ArrayList<>();
		
		try {
			result = streamer.getStream()
					.sorted(
						comparing(PlayerBean::getBirthDate)
						.thenComparing(PlayerBean::getEurWage)
					)
					.limit(10)
					.map(PlayerBean::getFullName)
					.collect(Collectors.toList());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(result);
		
		return result;
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
	// (utilize a coluna `age`)
	public Map<Integer, Integer> q6() {
		CsvFileStreamer<PlayerBean> streamer = this.getStreamer();
		
		Map<Integer, Integer> result = new HashMap<>();
		
		try {
			result = streamer.getStream()
					.collect(
						Collectors.groupingBy(
							PlayerBean::getAge,
							Collectors.counting()
						)
					)
					.entrySet()
					.stream()
					.collect(
						Collectors.toMap(
							e -> e.getKey(),
							e -> e.getValue().intValue()
						)
					);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private CsvFileStreamer<PlayerBean> getStreamer() {
		return new CsvFileStreamer<>(FILEPATH, PlayerBean.class);
	}
}
