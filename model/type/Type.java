package model.type;

import model.value.Value;

public interface Type {
    //checks if this type is equal to another type
    boolean equals(Object another);


    //returns the default value for this type : INTEGER -> 0, BOOLEAN -> false, STRING -
    Value getDefaultValue();
}
