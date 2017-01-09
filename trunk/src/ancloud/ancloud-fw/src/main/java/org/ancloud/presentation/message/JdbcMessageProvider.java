package org.ancloud.presentation.message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.ancloud.fw.presentation.util.LocaleUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.util.Assert;

public class JdbcMessageProvider implements MessageProvider {

	protected static final String QUERY_INSERT_MESSAGE = "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?)";
	protected static final String QUERY_DELETE_MESSAGES = "DELETE FROM %s WHERE %s = ?";
	protected static final String QUERY_SELECT_BASENAMES = "SELECT DISTINCT %s from %s";
	protected static final String QUERY_SELECT_MESSAGES = "SELECT %s,%s,%s,%s,%s FROM %s";// WHERE (COALESCE(%s, '')|| '_' || COALESCE(%s, '')) = ?";

	private JdbcTemplate template;

	private String languageColumn = "language";
	private String countryColumn = "country";
	private String variantColumn = "variant";
	private String keyColumn = "messageKey";
	private String messageColumn = "message";
	private String tableName = "message";
	private String basenameColumn = "basename";
	private String delimiter = "";

	private final MessageExtractor extractor = new MessageExtractor();
	

	public Messages getMessages(String baseName) {
		String query = String.format(getSelectMessagesQuery(),
				addDelimiter(languageColumn), addDelimiter(countryColumn),
				addDelimiter(variantColumn), addDelimiter(keyColumn),
				addDelimiter(messageColumn), addDelimiter(tableName),
				addDelimiter(languageColumn), addDelimiter(countryColumn));

		return template.query(query, new Object[] {}, extractor);
	}

	public void setMessages(String basename, Messages messages) {

		deleteMessages(basename);

		String query = String.format(getInsertMessageQuery(),
				addDelimiter(tableName), addDelimiter(languageColumn),
				addDelimiter(countryColumn), addDelimiter(variantColumn),
				addDelimiter(keyColumn), addDelimiter(messageColumn));

		for (Locale locale : messages.getLocales()) {

			insert(query, basename, locale.getLanguage(), locale.getCountry(),
					locale.getVariant(), messages.getMessages(locale));

		}

	}

	private void insert(String query, final String basename,
			final String language, final String country, final String variant,
			final Map<String, String> messages) {

		final Iterator<Map.Entry<String, String>> messagesIterator = messages
				.entrySet().iterator();

		template.batchUpdate(query, new BatchPreparedStatementSetter() {

			public void setValues(PreparedStatement ps, int i)
					throws SQLException {

				Map.Entry<String, String> entry = messagesIterator.next();
				ps.setString(1, basename);
				ps.setString(2, language);
				ps.setString(3, country);
				ps.setString(4, variant);
				ps.setString(5, entry.getKey());
				ps.setString(6, entry.getValue());

			}

			public int getBatchSize() {

				return messages.size();
			}
		});

	}

	public List<String> getAvailableBaseNames() {
		List<String> basenames = template.queryForList(String.format(
				getSelectBasenamesQuery(), addDelimiter(basenameColumn),
				addDelimiter(tableName)), String.class);
		return basenames;
	}

	private void deleteMessages(final String basename) {
	}

	/**
	 * Returns the query used to select messages of a basename
	 * 
	 * @return the query
	 */
	protected String getSelectMessagesQuery() {

		return QUERY_SELECT_MESSAGES;
	}

	/**
	 * Returns the query used for selecting available basenames.
	 * 
	 * @return the query
	 */
	protected String getSelectBasenamesQuery() {

		return QUERY_SELECT_BASENAMES;
	}

	/**
	 * Returns the Query-Template used to insert a Message
	 * 
	 * @return the query
	 */
	protected String getInsertMessageQuery() {

		return QUERY_INSERT_MESSAGE;
	}

	/**
	 * Returns the query to delete Messages
	 * 
	 * @return the query
	 */
	protected String getDeleteMessagesQuery() {

		return QUERY_DELETE_MESSAGES;
	}

	/**
	 * Method that "wraps" a field-name (or table-name) into the delimiter.
	 * 
	 * @param name
	 *            the name of the field/table
	 * @return the wrapped field/table
	 */
	protected String addDelimiter(String name) {

		return String.format("%s%s%s", delimiter, name, delimiter);
	}

	/**
	 * Returns the delimiter used within queries to delimit table- and
	 * column-names
	 * 
	 * @return the delimiter
	 */
	public String getDelimiter() {

		return delimiter;
	}

	/**
	 * Sets the delimiter used within queries to delimit table- and column-names
	 * (defaults to `). Must not be null.
	 * 
	 * @param delimiter
	 *            the delimiter to use
	 */
	public void setDelimiter(String delimiter) {

		Assert.notNull(delimiter);
		this.delimiter = delimiter;
	}

	/**
	 * Returns the name of the column holding the information about the basename
	 * (string-type)
	 * 
	 * @return the name of the basename-column
	 */

	/**
	 * Returns the name of the table containing the messages
	 * 
	 * @return the name of the table containing the messages
	 */
	public String getTableName() {

		return tableName;
	}

	/**
	 * Sets the name of the table containing the messages
	 * 
	 * @param tableName
	 *            the name of the table containing the messages
	 */
	public void setTableName(String tableName) {

		Assert.notNull(tableName);
		this.tableName = tableName;
	}

	/**
	 * Sets the {@link DataSource} where connections can be created to the
	 * database containing the table with messages
	 * 
	 * @param dataSource
	 *            the {@link DataSource} to set
	 */
	public void setDataSource(DataSource dataSource) {

		Assert.notNull(dataSource);
		this.template = new JdbcTemplate(dataSource);
	}

	/**
	 * Returns the name of the column holding the information about the language
	 * (string-type)
	 * 
	 * @return the name of the column holding the information about the language
	 *         (string-type)
	 */
	public String getLanguageColumn() {

		return languageColumn;
	}

	/**
	 * Sets the name of the column holding the information about the language
	 * (string-type)
	 * 
	 * @param languageColumn
	 *            the name of the language-column
	 */
	public void setLanguageColumn(String languageColumn) {

		Assert.notNull(languageColumn);

		this.languageColumn = languageColumn;
	}

	/**
	 * Returns the name of the column holding the information about the country
	 * (string-type)
	 * 
	 * @return the name of the column holding the information about the country
	 *         (string-type)
	 */
	public String getCountryColumn() {

		return countryColumn;
	}

	/**
	 * Sets the name of the column holding the information about the country
	 * (string-type)
	 * 
	 * @param countryColumn
	 *            the name of the country-column
	 */
	public void setCountryColumn(String countryColumn) {

		Assert.notNull(countryColumn);

		this.countryColumn = countryColumn;
	}

	/**
	 * Returns the name of the column holding the information about the variant
	 * (string-type)
	 * 
	 * @return the name of the column holding the information about the variant
	 *         (string-type)
	 */
	public String getVariantColumn() {

		return variantColumn;
	}

	/**
	 * Sets the name of the column holding the information about the variant
	 * (string-type)
	 * 
	 * @param variantColumn
	 *            the name of the variant-column
	 */
	public void setVariantColumn(String variantColumn) {

		Assert.notNull(variantColumn);
		this.variantColumn = variantColumn;
	}

	/**
	 * Returns the name of the column holding the information about the key
	 * (string-type)
	 * 
	 * @return the name of the column holding the information about the key
	 *         (string-type)
	 */
	public String getKeyColumn() {

		return keyColumn;
	}

	/**
	 * Sets the name of the column holding the information about the key aka the
	 * name of the message-code (string-type)
	 * 
	 * @param keyColumn
	 *            the name of the key-column
	 */
	public void setKeyColumn(String keyColumn) {

		Assert.notNull(keyColumn);

		this.keyColumn = keyColumn;
	}

	/**
	 * Returns the name of the column holding the information about the message
	 * (string-type)
	 * 
	 * @return the name of the column holding the information about the message
	 *         (string-type)
	 */
	public String getMessageColumn() {

		return messageColumn;
	}

	/**
	 * Sets the name of the column holding the information about the
	 * message-value aka the message itself (string-type)
	 * 
	 * @param messageColumn
	 *            the name of the message-column
	 */
	public void setMessageColumn(String messageColumn) {

		Assert.notNull(messageColumn);
		this.messageColumn = messageColumn;
	}

	/**
	 * Helper that extracts messages from a resultset
	 **/
	class MessageExtractor implements ResultSetExtractor<Messages> {
		public Messages extractData(ResultSet rs) throws SQLException,
				DataAccessException {

			Messages messages = new Messages();

			while (rs.next()) {
				String language = rs.getString(languageColumn);
				String country = rs.getString(countryColumn);
				String variant = rs.getString(variantColumn);
				String key = rs.getString(keyColumn);
				String message = rs.getString(messageColumn);

				Locale locale = LocaleUtils
						.toLocale(language, country, variant);
				messages.addMessage(locale, key, message);
			}

			return messages;
		}
	}
}
