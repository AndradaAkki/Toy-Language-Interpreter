package model.state;

import model.myException.ADTException;
import model.type.Type;
import model.value.Value;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapSymbolTable implements  SymbolTable {

    private final Map<String, Value> map = new HashMap<>();

    //checks if a variable is defined in the symbol table
    @Override
    public boolean isDefined(String variableName) {
        return map.containsKey(variableName);
    }

    //retrieves the type of a variable
    @Override
    public Type getType(String variableName) {
        return map.get(variableName).getType();
    }

    //declares a new variable with its type
    @Override
    public void declareVariable(String variableName, Type type) {
        map.put(variableName, type.getDefaultValue());
    }

    //updates the value of an existing variable
    @Override
    public void update(String variableName, Value value) {
        map.put(variableName, value);
    }

    //retrieves the value of a variable
    @Override
    public Value getValue(String variableName) {
        if(!isDefined(variableName)){
            throw new ADTException("Variable " + variableName + " is not defined.");
        }
        return map.get(variableName);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (var entry : map.entrySet()) {
            sb.append(entry.getKey())
                    .append("->")
                    .append(entry.getValue())
                    .append(", ");
        }

        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);  // remove last comma + space
        }

        sb.append("}");
        return sb.toString();
    }


    @Override
    public Collection<Value> getValues() {
        return map.values();
    }

}
