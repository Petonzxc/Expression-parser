package expression.exceptions;

public class BaseParser {
    private static final char END = '\0';
    private final CharSource source;
    private char ch = 0xffff;

    protected BaseParser(final CharSource source) {
        this.source = source;
        take();
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected int getPos() {
        return source.getId() + 1;
    }

    protected boolean test(final String s) {
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != source.takeVal(source.getId() + i)) {
                return false;
            }
        }
        return true;
    }

    protected boolean take(final String s) {
        for(int i = 0; i < s.length(); i++) {
            take(s.charAt(i));
        }
        return true;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected void expect(final char expected) {
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected void expect(final String value) {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected void getInt(StringBuilder sb) {
        if(test('0')) {
            sb.append(take());
        } else {
            while (between('0', '9')) {
                sb.append(take());
            }
        }
    }

    protected boolean eof() {
        return take(END);
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected boolean isWhitespace() {
        return Character.isWhitespace(ch);
    }


    protected void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }
}
