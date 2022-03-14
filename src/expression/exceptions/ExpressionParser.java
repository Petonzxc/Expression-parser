package expression.exceptions;

import expression.*;

public final class ExpressionParser implements TripleParser {
    public InExpression parse(final String source) throws ParsingException {
        return parse(new StringSource(source));
    }

    public InExpression parse(final CharSource source) throws ParsingException {
        return new ParserExpression(source).parseExp();
    }

    private static class ParserExpression extends BaseParser {
        public ParserExpression(final CharSource source) {
            super(source);
        }

        public InExpression parseExp() throws ParsingException {
            final InExpression result = parseShifts();
            if (eof()) {
                return result;
            }
            throw new ParsingException("End of Expression expected on pos: " + getPos());
        }

        private InExpression parseShifts() throws ParsingException {
            InExpression expression = parsePlusOrMinus();
            while (!eof()) {
                skipWhitespace();
                if (take('<')) {
                    if(!take('<')) {
                       throw new ParsingException("Unknown command on pos: " + getPos());
                    }
                    expression = new LeftShift(expression, parsePlusOrMinus());
                } else if (take('>')) {
                    if(!take('>')) {
                        throw new ParsingException("Unknown command on pos: " + getPos());
                    }
                    if (take('>')) {
                        expression = new ArithmeticShift(expression, parsePlusOrMinus());
                    } else {
                        expression = new RightShift(expression, parsePlusOrMinus());
                    }
                } else {
                    break;
                }
            }
            return expression;
        }

        private InExpression parsePlusOrMinus() throws ParsingException {
            InExpression expression = parseMulOrDiv();
            while (!eof()) {
                skipWhitespace();
                if (take('+')) {
                    expression = new CheckedAdd(expression, parseMulOrDiv());
                } else if (take('-')) {
                    expression = new CheckedSubtract(expression, parseMulOrDiv());
                } else {
                    break;
                }
            }
            return expression;
        }

        private InExpression parseMulOrDiv() throws ParsingException {
            InExpression expression = parsePowOrLog();
            while (!eof()) {
                skipWhitespace();
                if (!test("**") && take('*')) {
                    expression = new CheckedMultiply(expression, parsePowOrLog());
                } else if (!test("//") && take('/')) {
                    expression = new CheckedDivide(expression, parsePowOrLog());
                } else {
                    break;
                }
            }
            return expression;
        }

        private InExpression parsePowOrLog() throws ParsingException {
            InExpression expression = parseValueOrConstOrUnary();
            while (!eof()) {
                skipWhitespace();
                if (test("**")) {
                    take("**");
                    expression = new CheckedPow(expression, parseValueOrConstOrUnary());
                } else if (test("//")) {
                    take("//");
                    expression = new CheckedLog(expression, parseValueOrConstOrUnary());
                } else {
                    break;
                }
            }
            return expression;
        }

        private InExpression parseValueOrConstOrUnary() throws ParsingException {
            while (!eof()) {
                skipWhitespace();
                if(take('a')) {
                    if(!take("bs")) {
                        throw new ParsingException("Unknown command on pos: " + getPos());
                    }
                    if(!isWhitespace() && !test('(')) {
                        throw new ParsingException("Incorrect abs expression on pos: " + getPos());
                    }
                    return new CheckedAbs(parseValueOrConstOrUnary());
                } else if (take('(')) {
                    return parseBracket();
                } else if (test('x') || test('y') || test('z')) {
                    return parseVariable();
                } else if (between('0', '9')) {
                    return parseConst(false);
                } else if (take('-')) {
                    if (between('0', '9')) {
                        return parseConst(true);
                    } else {
                        return new CheckedNegate(parseValueOrConstOrUnary());
                    }
                } else {
                    throw new ParsingException("Illegal argument on pos: " + getPos()
                            + ", expect expression, value, const or unary");
                }
            }
            throw new ParsingException("No  right argument on pos: " + getPos());
        }

        private InExpression parseBracket() throws ParsingException {
            InExpression expression = parseShifts();
            skipWhitespace();
            if(!take(')')) {
                throw new ParsingException("No end bracket on pos: " + getPos());
            }
            return expression;
        }

        private InExpression parseConst(boolean minus) {
            final StringBuilder sb = new StringBuilder();
            if (minus) {
                sb.append('-');
            }
            getInt(sb);
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (NumberFormatException e) {
                throw new ParsingException("Invalid Const on pos: " + getPos());
            }
        }

        private InExpression parseVariable() {
            final String variable = Character.toString(take());
            skipWhitespace();
            return new Variable(variable);
        }
    }
}
