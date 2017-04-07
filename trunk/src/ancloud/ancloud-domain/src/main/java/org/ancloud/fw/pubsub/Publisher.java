package org.ancloud.fw.pubsub;

public interface Publisher<T> {
	public void registerSubscriber(Subcriber<T> subcriber);
	public void unregisterSubscriber(Subcriber<T> subcriber);
	public void publish(T obj);
}
