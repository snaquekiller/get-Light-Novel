package getln.data.entity;

/**
 * .
 */
public enum Status {
    IN_PROGRESS("IN_PROGRESS"),
    FAILED("FAILED"),
    RE_TRY("RE_TRY"),
    AVAILABLE("AVAILABLE");

    private final String status;

    /**
     * @param status
     */
    private Status(final String status) {
        this.status = status;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return status;
    }
}
