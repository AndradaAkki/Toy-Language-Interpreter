package model.statement;
import model.expression.Expression;
import model.myException.MyException;
import model.state.MyDictionary;
import model.state.ProgramState;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class WriteHeapStatement implements Statement {
    private final String varName;
    private final Expression expression;

    public WriteHeapStatement(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }
    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        var sym = state.symbolTable();
        var heap = state.heap();
        var value = sym.getValue(varName);

        if (!(value instanceof RefValue ref)) {
            throw new RuntimeException("Variable " + varName + " is not of RefType.");
        }
        if (!heap.contains(ref.getAddress())) {
            throw new RuntimeException("Invalid address in heap.");
        }

        Value newValue = expression.evaluate(sym, heap);
        if (!newValue.getType().equals(ref.getLocationType())) {
            throw new RuntimeException("Type mismatch: variable " + varName + " and expression do not match.");
        }
        heap.update(ref.getAddress(), newValue);
        return state;
    }
    @Override
    public String toString() {
        return "wH(" + varName + ", " + expression + ")";
    }

    @Override
    public MyDictionary<String, Type> typecheck(
            MyDictionary<String, Type> typeEnv) throws MyException {

        Type varType = typeEnv.get(varName);
        Type exprType = expression.typecheck(typeEnv);

        if (!(varType instanceof RefType refType))
            throw new MyException("wH target is not RefType");

        if (!refType.getInner().equals(exprType))
            throw new MyException("wH type mismatch");

        return typeEnv;
    }


}
