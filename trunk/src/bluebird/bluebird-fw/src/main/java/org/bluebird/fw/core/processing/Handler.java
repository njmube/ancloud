package org.bluebird.fw.core.processing;

public interface Handler<TInput> {
	public abstract void handle(TInput handlePaream);
}
