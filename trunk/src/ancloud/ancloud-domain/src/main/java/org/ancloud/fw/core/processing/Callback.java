package org.ancloud.fw.core.processing;

public interface Callback<T> {
	public void call(T callbackParam);
}
