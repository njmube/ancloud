package org.ancloud.fw.core.processing;

import java.util.List;

public interface Handlers<TInput> {
	public List<Handler<TInput>> getHandlers();
}
