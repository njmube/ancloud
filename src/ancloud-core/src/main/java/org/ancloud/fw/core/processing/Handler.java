package org.ancloud.fw.core.processing;

public interface Handler<T> {
	public abstract void handle(T param);
}
