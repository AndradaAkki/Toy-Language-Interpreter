package model.expression;

import model.myException.ExpressionException;
import model.myException.MyException;
import model.state.Heap;
import model.state.MyDictionary;
import model.state.SymbolTable;
import model.type.BoolType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;
import model.type.Type;

public record RelationalExpression(String op, Expression left, Expression right) implements Expression {
    @Override
    public Value evaluate(SymbolTable symTable, Heap heap) throws Exception {
        var leftValue = left.evaluate(symTable, heap);
        var rightValue = right.evaluate(symTable, heap);

        if (!leftValue.getType().equals(new IntType()) || !rightValue.getType().equals(new IntType()))
            throw new ExpressionException("Relational operands must be integers");

        int n1 = ((IntValue) leftValue).getValue();
        int n2 = ((IntValue) rightValue).getValue();
        return switch (op) {
            case "<" -> new BoolValue(n1 < n2);
            case "<=" -> new BoolValue(n1 <= n2);
            case "==" -> new BoolValue(n1 == n2);
            case "!=" -> new BoolValue(n1 != n2);
            case ">" -> new BoolValue(n1 > n2);
            case ">=" -> new BoolValue(n1 >= n2);
            default -> throw new ExpressionException("Invalid relational operator: " + op);
        };
    }
    @Override
    public String toString() {
        return left + " " + op + " " + right;
    }
    @Override
    public Type typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type t1 = left.typecheck(typeEnv);
        Type t2 = right.typecheck(typeEnv);

        if (!t1.equals(new IntType()) || !t2.equals(new IntType()))
            throw new MyException("Relational operands must be integers");

        return new BoolType();
    }

}
