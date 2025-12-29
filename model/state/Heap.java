package model.state;

import model.value.Value;

import java.util.Map;

public interface Heap {
    int allocate(Value value);
    Value get(int address) throws Exception;
    void update(int address, Value value) throws Exception;
    boolean contains(int address);
    Map<Integer, Value> getContent();
    void setContent(Map<Integer, Value> newHeap);
}
