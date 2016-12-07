package org.ancloud.fw.core.processing;

public interface ProcessingStrategy<T,TInput> {
	public void process(T processUnit,TInput input);
}
