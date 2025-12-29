package model.state;

import model.myException.MyException;
import model.type.Type;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {

    private final Map<K, V> map;

    public MyDictionary() {
        map = new HashMap<>();
    }

    public MyDictionary(Map<K, V> map) {
        this.map = map;
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public V get(K key) {
        if (!map.containsKey(key))
            throw new MyException("Key not defined: " + key);
        return map.get(key);
    }

    @Override
    public boolean isDefined(K key) {
        return map.containsKey(key);
    }

    @Override
    public MyIDictionary<K, V> copy() {
        return new MyDictionary<>(new HashMap<>(map));
    }

    @Override
    public Map<K, V> getContent() {
        return map;
    }

    @Override
    public MyDictionary<String, Type> deepCopy() {
        MyDictionary<String, Type> copy = new MyDictionary<>();
        for (var entry : map.entrySet()) {
            copy.put((String) entry.getKey(), (Type) entry.getValue());
        }
        return copy;
    }

}
