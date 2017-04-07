package org.ancloud.fw.pubsub;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import org.ancloud.fw.core.processing.CompositeHandler;
import org.ancloud.fw.core.processing.MultithreadHandlersStrategy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SimplePublisher<T> extends CompositeHandler<T> implements Publisher<T>, ApplicationContextAware{
	
	public SimplePublisher() {
		super(new MultithreadHandlersStrategy<T>());
	}

	public Class<T> getTClass(){
		return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public void publish(T obj) {
		super.handle(obj);
	}

	@Override
	public void registerSubscriber(Subcriber<T> subcriber) {
		this.addHandler(subcriber);
	}

	@Override
	public void unregisterSubscriber(Subcriber<T> subcriber) {
		this.removeHandler(subcriber);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 Map<String, Subcriber> subcribers = applicationContext.getBeansOfType(Subcriber.class);
		for(Subcriber subcriber:subcribers.values()){
			this.registerSubscriber(subcriber);
		}
	}
}
