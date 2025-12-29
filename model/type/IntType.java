package model.type;

import model.value.IntValue;
import model.value.Value;

public class IntType implements  Type {

    //returns true if the given type is also an IntType
    @Override
    public boolean equals(Object another) {
        return another instanceof IntType;
    }

    //returns the default value for this type : INTEGER -> 0
    @Override
    public Value getDefaultValue() {
        return new IntValue(0);
    }

    //returns the string representation of the type
    @Override
    public String toString() {
        return "int";
    }

}
