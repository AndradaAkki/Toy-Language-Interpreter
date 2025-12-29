package model.expression;

import model.myException.ExpressionException;
import model.myException.MyException;
import model.state.Heap;
import model.state.MyDictionary;
import model.state.SymbolTable;
import model.type.IntType;
import model.value.IntValue;
import model.value.Value;
import model.type.Type;

public record ArithmeticExpression(char op, Expression left, Expression right) implements Expression{

    @Override
    public Value evaluate(SymbolTable symTable, Heap heap) throws Exception {
        Value leftValue = left.evaluate(symTable, heap);
        Value rightValue = right.evaluate(symTable, heap);
        if (!(leftValue.getType().equals(rightValue.getType()))) {
            throw new ExpressionException("Type mismatch between left and right expressions");
        }
        int leftInt = ((IntValue)leftValue).getValue();
        int rightInt = ((IntValue)rightValue).getValue();

        return switch (op) {
            case '+' -> new IntValue(leftInt + rightInt);
            case '-' -> new  IntValue(leftInt - rightInt);
            case '*' -> new  IntValue(leftInt * rightInt);
            case '/' -> {
                if (rightInt == 0) {
                    throw new ExpressionException("Division by zero");
                }
                yield new IntValue(leftInt / rightInt);
            }
            default -> throw new ExpressionException("Invalid operator: " + op);
        };
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + op + " " + right.toString() + ")";
    }
    @Override
    public Type typecheck(MyDictionary<String, Type> typeEnv) throws MyException {
        Type t1 = left.typecheck(typeEnv);
        Type t2 = right.typecheck(typeEnv);

        if (!t1.equals(new IntType()))
            throw new MyException("First operand is not int");
        if (!t2.equals(new IntType()))
            throw new MyException("Second operand is not int");

        return new IntType();
    }

}
