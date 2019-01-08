package getln.data.entity;

/**
 * .
 */
public enum ROLE {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    /**
     * @param role
     */
    private ROLE(final String role) {
        this.role = role;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return role;
    }

}
