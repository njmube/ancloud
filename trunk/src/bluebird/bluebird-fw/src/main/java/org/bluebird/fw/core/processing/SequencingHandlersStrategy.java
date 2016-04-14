package org.bluebird.fw.core.processing;

public class SequencingHandlersStrategy<TInput> implements ProcessingStrategy<Handlers<TInput>,TInput> {

	@Override
	public void process(Handlers<TInput> processUnit,TInput input ) {
		if(processUnit !=null){
			for(Handler<TInput> handler:processUnit.getHandlers()){
				handler.handle(input);
			}
		}
	}
}
