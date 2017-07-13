package org.ancloud.fw.core.processing;

import java.util.List;

public interface Handlers<T> extends Iterable<Handler<T>>{
	public List<Handler<T>> getHandlers();
}
