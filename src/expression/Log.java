package expression;

public class Log extends BinaryOperation {
    public Log(InExpression left, InExpression right) {
        super(left, right);
    }

    @Override
    public boolean mustBracket() {
        return false;
    }

    @Override
    public String getOperation() {
        return "//";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public int getAssociativity() {
        return 1;
    }

    @Override
    public int calc(final int right, final int left) {
        int ans = 0;
        int cur = left;
        while(cur >= right) {
            cur /= right;
            ans++;
        }
        return ans;
    }

    public String toMiniString() {
        return toMiniString(2, 1);
    }
}
