package expression.exceptions;

import expression.exceptions.AbstractArithmeticalException;

public class OverflowException extends AbstractArithmeticalException {
    public OverflowException(String message) {
        super(message);
    }

    public static boolean IsOverflow(final int left, final int right, final int res) {
        return (left != 0 && res / left != right) || (right != 0 && res / right != left);
    }
}
