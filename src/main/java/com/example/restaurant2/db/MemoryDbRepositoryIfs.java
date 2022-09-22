package com.example.restaurant2.db;

import java.util.List;
import java.util.Optional;

/*
<T>	Type
<E>	Element
<K>	Key
<V>	Value
<N>	Number
*/
public interface MemoryDbRepositoryIfs<T> {
    Optional<T> findById(int index);
    T save(T entity);
    void deleteById(int index);
    List<T> listAll();
}
