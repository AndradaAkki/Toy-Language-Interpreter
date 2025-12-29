package model.value;

import model.type.BoolType;
import model.type.Type;

public class BoolValue implements  Value {
    private final boolean value;

    // constructor
    public BoolValue(boolean value) {
        this.value = value;
    }

    //getter
    public boolean getValue() {
        return value;
    }

    // equals and hashcode
    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}
