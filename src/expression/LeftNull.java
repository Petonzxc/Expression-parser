package expression;

public class LeftNull extends AbstractUnary {
    public LeftNull(InExpression expression) {
        super(expression);
    }

    @Override
    public int calc(int x) {
        int result = 0;
        for(int i = 0; i < 32; i++) {
            if((x & (1 << i)) == 0){
                result++;
            } else {
                break;
            }
        }
        return result;
    }

    @Override
    public String getOperation() {
        return "t0";
    }

    @Override
    public String toMiniString() {
        return super.toMiniString();
    }
}
