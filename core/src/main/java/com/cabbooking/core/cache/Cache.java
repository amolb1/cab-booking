package com.cabbooking.core.cache;

import reactor.core.publisher.Flux;

public interface Cache<K,V> {

    void put(K k, V v);

    Flux<V> get(K k);
}
