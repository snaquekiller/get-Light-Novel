package get.ln.data;

/**
 * .
 */
public enum BOOK_TYPE {
    LIGHT_NOVEL("LIGHT_NOVEL"),
    MANGA("MANGA");

    private final String book;

    /**
     * @param text
     */
    private BOOK_TYPE(final String book) {
        this.book = book;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return book;
    }
}
