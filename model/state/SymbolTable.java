package model.state;

import model.type.Type;
import model.value.Value;

import java.util.Collection;

public interface SymbolTable {

    // Checks if a variable is defined in the symbol table
    boolean isDefined(String variableName);

    // Retrieves the type of a variable
    Type getType(String variableName);

    // Declares a new variable with its type
    void declareVariable(String variableName, Type type);

    // Updates the value of an existing variable
    void update(String variableName, Value value);

    // Retrieves the value of a variable
    Value getValue(String variableName);
    Collection<Value> getValues();
}
