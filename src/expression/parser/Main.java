package expression.parser;

import expression.InExpression;
import expression.exceptions.ExpressionParser;
import expression.exceptions.ParsingException;

public class Main {
    public static void main(String[] args) {
        String test = "((y ** x) ** x)";
        InExpression w = null;
        try {
            w = new ExpressionParser().parse(test);
        } catch (ParsingException e) {
            e.printStackTrace();
        }
        System.out.println(w.evaluate(1, 1, 1));
    }
}
