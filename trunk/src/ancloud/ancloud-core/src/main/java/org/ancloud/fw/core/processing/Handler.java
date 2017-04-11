package org.ancloud.fw.core.processing;

public interface Handler<TInput> {
	public abstract void handle(TInput handlePaream);
}
