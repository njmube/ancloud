package org.bluebird.presentation.message;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bluebird.presentation.util.LocaleUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.util.Assert;

public class InitializableMessageSource extends AbstractMessageSource implements InitializingBean {
	protected Map<Locale, List<String>> resolvingPath;
	protected Map<String, Map<String, MessageFormat>> messages = new HashMap<String, Map<String, MessageFormat>>();
	protected Locale defaultLocale;
	protected MessageProvider messageProvider;
	protected boolean autoInitialization;
	private ArrayList<String> basenames;
	private boolean basenameRestriction;
	private Boolean returnUnresolvedCode = true;

	public InitializableMessageSource() {
		this.resolvingPath = new HashMap<Locale, List<String>>();
	}

	private void addMessage(String basename, Locale locale, String code,MessageFormat messageFormat) {
		String localeString = basename + "_" + (locale != null ? locale.toString() : "");
		Map<String, MessageFormat> codeMap = messages.get(localeString);

		if (codeMap == null) {
			codeMap = new HashMap<String, MessageFormat>();
			messages.put(localeString, codeMap);
		}

		codeMap.put(code, messageFormat);
	}

	public void afterPropertiesSet() throws Exception {
		if (this.autoInitialization){
			initialize();
		}
	}

	public Locale getDefaultLocale() {
		return this.defaultLocale;
	}

	private List<String> getPath(Locale locale) {
		List<String> paths = resolvingPath.get(locale);
		if (paths == null) {
			paths = new ArrayList<String>();

			List<Locale> localePath = LocaleUtils.getPath(locale,
					getDefaultLocale());
			for (Locale loc : localePath) {
				if (loc == null) {
					paths.add("_");
				} else {

					String language = LocaleUtils.getLanguage(loc);
					String country = LocaleUtils.getCountry(loc);
					String variant = LocaleUtils.getVariant(loc);
					if (!variant.isEmpty()) {
						paths.add(String.format("_%s_%s_%s", language, country,
								variant));
					} else if (!country.isEmpty()) {
						paths.add(String.format("_%s_%s", language, country));
					} else if (!language.isEmpty()) {
						paths.add(String.format("_%s", language));
					}
				}

			}

			resolvingPath.put(locale, paths);
		}
		return paths;
	}

	public Boolean getReturnUnresolvedCode() {

		return this.returnUnresolvedCode;
	}

	public void initialize() {

		// reset the path-cache (default-locale could have been changed)
		resolvingPath = new HashMap<Locale, List<String>>();

		if (!basenameRestriction) {
			basenames = new ArrayList<String>();
			basenames.addAll(messageProvider.getAvailableBaseNames());
		}

		messages = new HashMap<String, Map<String, MessageFormat>>();

		for (String basename : basenames) {
			initialize(basename);
		}
	}

	public void initialize(String basename) {
		Messages messagesForBasename = messageProvider.getMessages(basename);

		for (Locale locale : messagesForBasename.getLocales()) {
			Map<String, String> codeToMessage = messagesForBasename
					.getMessages(locale);

			for (String code : codeToMessage.keySet()) {
				try {
					addMessage(
							basename,
							locale,
							code,
							createMessageFormat(codeToMessage.get(code), locale));
				} catch (RuntimeException e) {
					throw new RuntimeException(
							String.format(
									"Error processing Message code=%s locale=%s basename=%s, %s",
									code, locale, basename, e.getMessage()), e);
				}
			}
		}

	}

	protected MessageFormat resolveCode(String code, Locale locale) {
		for (String basename : basenames) {
			List<String> paths = getPath(locale);
			for (String loc : paths) {
				Map<String, MessageFormat> formatMap = messages.get(basename
						+ loc);
				if (formatMap != null) {
					MessageFormat format = formatMap.get(code);
					if (format != null) {
						if (format.getLocale() == null) {
							format.setLocale(defaultLocale);
						}
						return format;
					}
				}
			}
		}

		if (getReturnUnresolvedCode()) {
			return createMessageFormat(code, locale);
		} else {
			return null;
		}
	}

	public void setAutoInitialize(boolean autoInitialize) {
		this.autoInitialization = autoInitialize;
	}

	public void setBasename(String basename) {
		basenameRestriction = true;
		this.basenames = new ArrayList<String>();
		basenames.add(basename);
	}

	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public void setMessageProvider(MessageProvider messageProvider) {
		Assert.notNull(messageProvider);
		this.messageProvider = messageProvider;
	}

	public void setReturnUnresolvedCode(Boolean returnUnresolvedCode) {
		this.returnUnresolvedCode = returnUnresolvedCode;
	}
	
	public void setAutoInitialization(boolean autoInitialization) {
		this.autoInitialization = autoInitialization;
	}
}