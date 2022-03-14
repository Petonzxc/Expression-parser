package expression;

public class Const implements InExpression {
    private final Number val;

    public Const(int val) {
        this.val = val;
    }

    public int getPriority() {
        return 3;
    }

    @Override
    public boolean mustBracket() {
        return false;
    }

    @Override
    public int evaluate(int val) {
        return (int) this.val;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) this.val;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Const) {
            final Const cur = (Const) obj;
            return val.equals(cur.val);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public String toMiniString() {
        return val.toString();
    }

    @Override
    public int hashCode() {
        return val.hashCode();
    }
}
