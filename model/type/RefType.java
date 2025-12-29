package model.type;

import model.value.RefValue;
import model.value.Value;

public class RefType implements Type{
    private final Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return inner;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof RefType) {
            return  inner.equals(((RefType) other).inner);
        }
        return  false;
    }
    @Override
    public Value getDefaultValue() {
        return new RefValue(0, inner);
    }

    @Override
    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }
}
