package expression.exceptions;

public interface CharSource {
    boolean hasNext();
    char next();
    char takeVal(int id);
    int getId();
    IllegalArgumentException error(final String message);
}
