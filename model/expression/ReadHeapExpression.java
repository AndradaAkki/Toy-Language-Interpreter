package model.expression;
import model.myException.MyException;
import model.state.Heap;
import model.state.MyDictionary;
import model.state.SymbolTable;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;


public class ReadHeapExpression implements Expression {

    private final Expression expression;

    public ReadHeapExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value evaluate(SymbolTable symTable, Heap heap) throws Exception {
        Value v = expression.evaluate(symTable, heap);

        if (!(v instanceof RefValue ref))
            throw new RuntimeException("Not a RefValue");

        if (!heap.contains(ref.getAddress()))
            throw new RuntimeException("Invalid address");

        return heap.get(ref.getAddress());
    }

    @Override
    public String toString() {
        return "rH(" + expression + ")";
    }
    @Override
    public Type typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type t = expression.typecheck(typeEnv);
        if (!(t instanceof RefType refType))
            throw new MyException("rH argument is not a RefType");

        return refType.getInner();
    }

}
