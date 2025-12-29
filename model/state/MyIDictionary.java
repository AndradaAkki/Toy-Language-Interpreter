package model.state;

import model.type.Type;

import java.util.Map;

public interface MyIDictionary<K, V> {
    void put(K key, V value);
    V get(K key);
    boolean isDefined(K key);
    MyIDictionary<K, V> copy();
    Map<K, V> getContent();
    MyDictionary<String, Type> deepCopy();

}
