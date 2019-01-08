package getLn.model;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.joda.time.Interval;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

/**
 * Represents a collection metadata (i.e. pagination).
 */
@JsonInclude(Include.ALWAYS)
public class ResponseMetadata {

    /**
     * The total elements count.
     */
    private Long total;

    /**
     * The page.
     */
    private Integer page;

    /**
     * The limit.
     */
    private Integer limit;

    /**
     * The start date.
     */
    private String startDate;

    /**
     * The end date.
     */
    private String endDate;

    /**
     * The sort.
     */
    private String sort;

    /**
     * The order.
     */
    private String order;

    /**
     * The search.
     */
    private String search;

    /**
     * Instantiates a new response metadata.
     */
    public ResponseMetadata() {

    }

    public ResponseMetadata(Page page) {
        this.total = page.getTotalElements();
        this.page = page.getNumber() + 1;
        this.limit = page.getSize();
    }

    /**
     * Constructor.
     *
     * @param total The total elements count.
     * @param page The page.
     * @param limit The limit.
     */
    public ResponseMetadata(final long total, final int page, final int limit) {
        this.total = total;
        this.page = page;
        this.limit = limit;
    }

    /**
     * Constructor.
     */
    public ResponseMetadata(final ResponseMetadata responseMetadata) {
        this.total = responseMetadata.total;
        this.page = responseMetadata.page;
        this.limit = responseMetadata.limit;
        this.startDate = responseMetadata.startDate;
        this.endDate = responseMetadata.endDate;
        this.sort = responseMetadata.sort;
        this.search = responseMetadata.search;
        this.order = responseMetadata.order;
    }

    /**
     * Returns the limit.
     *
     * @return The limit.
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Sets new The limit..
     *
     * @param limit New value of The limit..
     */
    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    /**
     * Returns the page.
     *
     * @return The page.
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Sets new The page..
     *
     * @param page New value of The page..
     */
    public void setPage(final Integer page) {
        this.page = page;
    }

    /**
     * Returns the offset.
     *
     * @return The offset.
     */
    public Integer getOffset() {
        if (page != null && limit != null) {
            return page * limit;
        } else {
            return 0;
        }
    }

    /**
     * Returns the total elements count.
     *
     * @return The total elements count.
     */
    public Long getTotal() {
        return total;
    }

    /**
     * Sets new The total elements count..
     *
     * @param total New value of The total elements count..
     */
    public void setTotal(final Long total) {
        this.total = total;
    }

    /**
     * Gets The limit..
     *
     * @return Value of The limit..
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets new The limit..
     *
     * @param endDate New value of The limit..
     */
    public void setEndDate(final String endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets The limit..
     *
     * @return Value of The limit..
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets new The limit..
     *
     * @param startDate New value of The limit..
     */
    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets The limit..
     *
     * @return Value of The limit..
     */
    public String getSort() {
        return sort;
    }

    /**
     * Sets new The limit..
     *
     * @param sort New value of The limit..
     */
    public void setSort(final String sort) {
        this.sort = sort;
    }

    /**
     * Gets The limit..
     *
     * @return Value of The limit..
     */
    public String getOrder() {
        return order;
    }

    /**
     * Sets new The limit..
     *
     * @param order New value of The limit..
     */
    public void setOrder(final String order) {
        this.order = order;
    }

    /**
     * Gets the search.
     *
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * Sets the search.
     *
     * @param search the new search
     */
    public void setSearch(final String search) {
        this.search = search;
    }

    /**
     * Gets the interval.
     *
     * @return the interval
     */
    @JsonIgnore
    public Interval getInterval() {
        final long start = Instant.parse(startDate).toEpochMilli();
        final long end = Instant.parse(endDate).toEpochMilli();
        return new Interval(start, end);
    }

    /**
     * Gets the paging information.
     *
     * @return the paging information
     */
    @JsonIgnore
    public Pageable getPagingInformation() {
        // TODO : we always sort paginate page. check if this is the good
        // behavior.
        final PageRequest result = new PageRequest(getPage() - 1, // page is defaulted
                // to 1 if < 1. ->
                // see
                // PaginateController.java
                getLimit(), Direction.fromString(getOrder()), getSort());
        return result;
    }

}
