package org.ancloud.domain.message;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.de.GermanStemFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.AnalyzerDefs;
import org.hibernate.search.annotations.ClassBridge;
import org.hibernate.search.annotations.ClassBridges;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

@Entity
@Table(name = "message",
		uniqueConstraints=@UniqueConstraint(columnNames={"messageKey","language","country"})
)
@Embeddable
@Indexed
//@AnalyzerDefs({
//		@AnalyzerDef(name = "en", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
//				@TokenFilterDef(factory = LowerCaseFilterFactory.class),
//				@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
//						@Parameter(name = "language", value = "English") }) }),
//		@AnalyzerDef(name = "de", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
//				@TokenFilterDef(factory = LowerCaseFilterFactory.class),
//				@TokenFilterDef(factory = GermanStemFilterFactory.class) }),
//		@AnalyzerDef(name = "und", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
//				@TokenFilterDef(factory = LowerCaseFilterFactory.class) }) })
//@ClassBridges({
//		@ClassBridge(name = "year", index = Index.YES, analyze = Analyze.NO, store = Store.NO, impl = Message.class) })
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Field(store = Store.NO)
	private String messageKey;

	@Field(store = Store.NO)
	private String language;

	@Field(store = Store.NO)
	private String country;

	@Field(store = Store.NO)
	private String basename;

	private String variant;

	@Field(store = Store.NO)
	private String message;

	public Message() {
	}

	public String getKey() {
		return messageKey;
	}

	public void setKey(String key) {
		this.messageKey = key;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBasename() {
		return basename;
	}

	public void setBasename(String basename) {
		this.basename = basename;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
