package model.state;

import model.value.Value;

import java.util.Map;

public class MapHeap implements Heap{
    private final Map<Integer, Value> heap = new java.util.HashMap<>();
    private int nextFreeAddress = 1;

    @Override
    public int allocate(Value value) {
        heap.put(nextFreeAddress, value);
        return  nextFreeAddress++;
    }

    @Override
    public Value get(int address) {
        return heap.get(address);
    }

    @Override
    public void update(int address, Value value) {
        heap.put(address, value);
    }

    @Override
    public boolean contains(int address) {
        return heap.containsKey(address);
    }
    @Override
    public Map<Integer, Value> getContent() {
        return heap;
    }
    @Override
    public void setContent(Map<Integer, Value> newHeap) {
        heap.clear();
        heap.putAll(newHeap);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (var entry : heap.entrySet()) {
            sb.append(entry.getKey())
                    .append("->")
                    .append(entry.getValue())
                    .append(", ");
        }

        if (sb.length() > 1)
            sb.setLength(sb.length() - 2); // remove last comma

        sb.append("}");
        return sb.toString();
    }


}
