package model.value;

import model.type.IntType;
import model.type.Type;

public class IntValue implements Value{
    private final int value;

    //constructor
    public IntValue(int value) {
        this.value = value;
    }

    //getter
    public int getValue() {
        return value;
    }

    //equals and hashcode
    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

}
