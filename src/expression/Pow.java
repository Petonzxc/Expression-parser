package expression;

public class Pow extends BinaryOperation {
    public Pow(InExpression left, InExpression right) {
        super(left, right);
    }

    @Override
    public boolean mustBracket() {
        return false;
    }

    @Override
    public String getOperation() {
        return "**";
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
    public int calc(int left, int right) {
        int ans = 1;
        while(right > 0) {
            ans *= left;
            right--;
        }
        return ans;
    }

    public String toMiniString() {
        return toMiniString(2, 1);
    }
}
