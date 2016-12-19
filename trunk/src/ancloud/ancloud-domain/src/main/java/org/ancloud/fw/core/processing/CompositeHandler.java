package org.ancloud.fw.core.processing;

import java.util.ArrayList;
import java.util.List;

public class CompositeHandler<T> implements Handlers<T>,Handler<T>{

	List<Handler<T>> handlers = new ArrayList<Handler<T>>();

	ProcessingStrategy<Handlers<T>,T> processingStrategy;
	
	public CompositeHandler(ProcessingStrategy<Handlers<T>,T> processingStrategy){
		this.processingStrategy = processingStrategy;
	}
	
	@Override
	public void handle(T handlePaream) {
		if(processingStrategy!=null){
			processingStrategy.process(this,handlePaream);
		}
	}
	
	public void addHandler(Handler<T> handler){
		if(handler!=null){
			this.handlers.add(handler);
		}
	}
	
	public List<Handler<T>> getHandlers() {
		return handlers;
	}
}
