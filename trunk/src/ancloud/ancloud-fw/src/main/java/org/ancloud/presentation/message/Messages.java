package org.ancloud.presentation.message;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class Messages {
	private Map<Locale, Map<String, String>> messages;

	public Messages(Map<Locale, Map<String, String>> messages) {
		this.messages = messages;
	}

	public Messages() {
		this.messages = new HashMap<Locale, Map<String, String>>();
	}

	public Set<Locale> getLocales() {
		return this.messages.keySet();
	}

	public Map<String, String> getMessages(Locale locale) {
		return this.messages.get(locale);
	}

	public void setMessages(Locale locale, Map<String, String> map) {
		this.messages.put(locale, map);
	}

	public void addMessage(Locale locale, String key, String message) {
		Map<String, String> keyToMessage = this.messages.get(locale);
		if (keyToMessage == null) {
			keyToMessage = new HashMap<String, String>();
			this.messages.put(locale, keyToMessage);
		}
		keyToMessage.put(key, message);
	}

	public void removeMessage(Locale locale, String key) {
		Map<String, String> keyToMessage = this.messages.get(locale);
		if (keyToMessage == null) {
			return;
		}
		keyToMessage.remove(key);
	}

	public String getMessage(Locale locale, String key) {
		Map<String, String> localeMessages = this.messages.get(locale);
		if (localeMessages == null) {
			return null;
		}
		return ((String) localeMessages.get(key));
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (Locale locale : getLocales()) {
			String localeString = (locale == null) ? "default (base)" : locale
					.toString();
			buffer.append(String.format("%d messages for locale %s\n",
					new Object[] { Integer.valueOf(getMessages(locale).size()),
							localeString }));
		}
		return buffer.toString();
	}

	public String getFullInfo() {
		StringBuffer buffer = new StringBuffer();

		for (Locale locale : getLocales()) {
			String localeString = (locale == null) ? "default (base)" : locale
					.toString();
			buffer.append("Messages for locale " + localeString + "\n");

			buffer.append("====================================================================================\n");

			for (Map.Entry<String, String> msg : getMessages(locale).entrySet()) {
				String value = (String) msg.getValue();
				if (value.contains("\n")) {
					value = value.replace("\n", "\n  ");
				}
				buffer.append(String.format("%s=%s\n",
						new Object[] { msg.getKey(), value }));
			}

			buffer.append("\n\n");
		}

		return buffer.toString();
	}

	public boolean hasMessage(Locale locale, String key) {
		return (getMessage(locale, key) != null);
	}
}