package expression;

public abstract class BinaryOperation implements InExpression {
    private final InExpression left;
    private final InExpression right;

    public BinaryOperation(InExpression left, InExpression right) {
        this.left = left;
        this.right = right;
    }

    public InExpression getLeftExpression() {
        return left;
    }

    public InExpression getRightExpression() {
        return right;
    }

    public abstract int getPriority();

    public abstract int getAssociativity();

    public abstract int calc(int x, int y);

    public int evaluate(int val) {
        return calc(left.evaluate(val), right.evaluate(val));
    }

    public int evaluate(int x, int y, int z) {
        return calc(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    public abstract String getOperation();

    @Override
    public abstract boolean mustBracket();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() == this.getClass()) {
            final BinaryOperation cur = (BinaryOperation) obj;
            return this.getLeftExpression().equals(cur.getLeftExpression())
                    && this.getRightExpression().equals(cur.getRightExpression());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ((left.hashCode() * 1399) + right.hashCode()) * 1399 + getClass().hashCode();
    }

    @Override
    public String toString() {
        return '(' + left.toString() + ' ' + getOperation() + ' ' + right.toString() + ')';
    }

    public String toMiniString(final int priority, final int associativity) {
        StringBuilder sb = new StringBuilder();
        if (priority <= left.getPriority()) {
            sb.append(left.toMiniString());
        } else {
            sb.append("(").append(left.toMiniString()).append(")");
        }
        sb.append(" ").append(getOperation()).append(" ");
        if (priority > right.getPriority()) {
            sb.append("(").append(right.toMiniString()).append(")");
        }
        if (priority < right.getPriority()) {
            sb.append(right.toMiniString());
        }
        if (priority == right.getPriority()) {
            if (right.mustBracket() || associativity == 1) {
                sb.append("(").append(right.toMiniString()).append(")");
            } else {
                sb.append(right.toMiniString());
            }
        }
        return sb.toString();
    }
}
