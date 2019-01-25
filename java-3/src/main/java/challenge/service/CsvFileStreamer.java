package challenge.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.opencsv.bean.CsvToBeanBuilder;

public class CsvFileStreamer<T> {
	
	private String filename;
	private Class<T> beanClass;
	private Logger logger;
	
	public CsvFileStreamer() {}
	
	public CsvFileStreamer(String filename, Class<T> beanClass) {
		this.filename = filename;
		this.beanClass = beanClass;
		
		this.logger = Logger.getLogger(this.getClass().getName());
	}
	
	public Stream<T> getStream() throws FileNotFoundException {
		FileReader reader;
		try {
			reader = this.openFileFromResources();
		} catch (FileNotFoundException e) {
			this.logger.info("File " + this.filename + " not found");
			
			throw e;
		}
		
		return new CsvToBeanBuilder<T>(reader).withType(this.beanClass).build().parse().stream();
	}
	
	private FileReader openFileFromResources() throws FileNotFoundException {
		return new FileReader(getClass().getClassLoader().getResource(this.filename).getFile());
	}
}
