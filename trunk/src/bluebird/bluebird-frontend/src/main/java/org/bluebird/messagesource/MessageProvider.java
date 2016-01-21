package org.bluebird.messagesource;

import java.util.Collection;

public abstract interface MessageProvider {
	public abstract Messages getMessages(String basename);

	public abstract Collection<String> getAvailableBaseNames();
}