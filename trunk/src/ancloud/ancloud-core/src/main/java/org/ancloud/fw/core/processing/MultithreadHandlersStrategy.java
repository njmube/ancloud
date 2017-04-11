package org.ancloud.fw.core.processing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadHandlersStrategy<TInput> implements ProcessingStrategy<Handlers<TInput>, TInput> {

	@Override
	public void process(Handlers<TInput> processUnit, final TInput input) {
		if (processUnit != null) {
			ExecutorService executor = Executors.newFixedThreadPool(5);
			for(final Handler<TInput> handler:processUnit.getHandlers()){
				executor.execute(new Runnable() {
					@Override
					public void run() {
						handler.handle(input);
					}
				});
			}
			executor.shutdown();
		}
	}
}
