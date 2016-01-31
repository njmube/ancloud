package org.bluebird.fw.core.processing;

import java.util.List;

public interface Handlers<TInput> {
	public List<Handler<TInput>> getHandlers();
}
