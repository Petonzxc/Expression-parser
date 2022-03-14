package expression;

public interface InExpression extends Expression, TripleExpression {
    public int getPriority();
    public boolean mustBracket();
}
