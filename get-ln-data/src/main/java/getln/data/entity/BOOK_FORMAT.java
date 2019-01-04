package getln.data.entity;

/**
 * .
 */
public enum BOOK_FORMAT {
    EPUB("EPUB"),
    MOBI("MOBI");

    private final String book;

    /**
     * @param book
     */
    private BOOK_FORMAT(final String book) {
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
