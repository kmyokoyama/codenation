package challenge.service;

import java.util.Date;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class PlayerBean {

	@CsvBindByName(column = "nationality")
	private String nationality;
	
	@CsvBindByName(column = "club")
	private String club;
	
	@CsvBindByName(column = "full_name")
	private String fullName;
	
	@CsvBindByName(column = "eur_release_clause")
	private Double eurReleaseClause;
	
	@CsvBindByName(column = "eur_wage")
	private Double eurWage;
	
	@CsvBindByName(column = "birth_date")
	@CsvDate("yyyy-MM-dd")
	private Date birthDate;
	
	@CsvBindByName(column = "age")
	private Integer age;
	
	public PlayerBean() {}

	public String getNationality() {
		return nationality;
	}

	public String getClub() {
		return club;
	}

	public String getFullName() {
		return fullName;
	}

	public Double getEurReleaseClause() {
		return eurReleaseClause;
	}

	public Double getEurWage() {
		return eurWage;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Integer getAge() {
		return age;
	}
}
