package org.ancloud.fw.core.processing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeHandler<T> implements Handlers<T>,Handler<T>{

	Object mutex = new Object();
			
	List<Handler<T>> handlers = new ArrayList<Handler<T>>();
	
	@Override
	public synchronized Iterator<Handler<T>> iterator() {
		return handlers.iterator();
	}

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
	
	public synchronized void addHandler(Handler<T> handler){
		if(handler!=null){
			this.handlers.add(handler);
		}
	}
	public synchronized void removeHandler(Handler<T> handler) {
		if(handlers.contains(handler)){
			handlers.remove(handler);
		}
	}
	public List<Handler<T>> getHandlers() {
		return handlers;
	}
	public void changeStrategy(ProcessingStrategy<Handlers<T>,T> processingStrategy){
		this.processingStrategy = processingStrategy;
	}

	
}
