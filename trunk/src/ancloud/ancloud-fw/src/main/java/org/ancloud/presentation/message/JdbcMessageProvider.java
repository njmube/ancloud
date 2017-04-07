package org.ancloud.presentation.message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import javax.sql.DataSource;

import org.ancloud.fw.presentation.util.LocaleUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.util.Assert;

public class JdbcMessageProvider implements MessageProvider {

	protected static final String QUERY_SELECT_MESSAGES = "SELECT %s,%s,%s,%s,%s FROM %s";

	private JdbcTemplate template;

	private String languageColumn = "language";
	private String countryColumn = "country";
	private String variantColumn = "variant";
	private String keyColumn = "messageKey";
	private String messageColumn = "message";
	private String tableName = "message";

	private final MessageExtractor extractor = new MessageExtractor();

	public JdbcMessageProvider(DataSource dataSource){
		Assert.notNull(dataSource);
		this.template = new JdbcTemplate(dataSource);
	}
	
	public Messages getMessages() {
		String query = String.format(QUERY_SELECT_MESSAGES, languageColumn,
				countryColumn, variantColumn, keyColumn,
				messageColumn, tableName, languageColumn,
				countryColumn);

		return template.query(query, new Object[] {}, extractor);
	}

	class MessageExtractor implements ResultSetExtractor<Messages> {
		public Messages extractData(ResultSet rs) throws SQLException, DataAccessException {

			Messages messages = new Messages();

			while (rs.next()) {
				String language = rs.getString(languageColumn);
				String country = rs.getString(countryColumn);
				String variant = rs.getString(variantColumn);
				String key = rs.getString(keyColumn);
				String message = rs.getString(messageColumn);

				Locale locale = LocaleUtils.toLocale(language, country, variant);
				messages.addMessage(locale.toString(), key, message);
			}

			return messages;
		}
	}
}
