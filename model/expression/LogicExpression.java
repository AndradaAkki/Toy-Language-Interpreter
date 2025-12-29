package model.expression;

import model.myException.ExpressionException;
import model.myException.MyException;
import model.state.Heap;
import model.state.SymbolTable;
import model.type.BoolType;
import model.value.BoolValue;
import model.value.Value;
import model.type.Type;

public record LogicExpression(String op, Expression left, Expression right) implements Expression {

    @Override
    public Value evaluate(SymbolTable symTable, Heap heap) throws Exception {
        Value leftValue = left.evaluate(symTable, heap);
        Value rightValue = right.evaluate(symTable, heap);

        if (!leftValue.getType().equals(new BoolType()) || !rightValue.getType().equals(new BoolType())) {
            throw new ExpressionException("Logical operands must be boolean");
        }

        boolean leftBool = ((BoolValue) leftValue).getValue();
        boolean rightBool = ((BoolValue) rightValue).getValue();
        return switch (op) {
            case "and" ->
                new BoolValue(leftBool && rightBool);

            case "or" ->
                new BoolValue(leftBool || rightBool);

            default -> throw new ExpressionException("Invalid logical operator: " + op);
        };
    }

    @Override
    public String toString() {
        return "(" + left + " " + op + " " + right + ")";
    }

    @Override
    public Type typecheck(model.state.MyDictionary<String, Type> typeEnv) throws MyException {
        Type leftType = left.typecheck(typeEnv);
        Type rightType = right.typecheck(typeEnv);

        if (!leftType.equals(new BoolType()))
            throw new ExpressionException("Left operand is not a boolean");
        if (!rightType.equals(new BoolType()))
            throw new ExpressionException("Right operand is not a boolean");

        return new BoolType();
    }

}
