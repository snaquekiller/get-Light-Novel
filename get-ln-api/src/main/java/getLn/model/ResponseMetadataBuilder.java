package getLn.model;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * The Class ResponseMetadataBuilder.
 */
public class ResponseMetadataBuilder {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseMetadataBuilder.class);

    /** The metadata. */
    private final ResponseMetadata metadata;

    /** The default instant. */
    private Instant defaultInstant = Instant.now();

    /** The permit switch. */
    private boolean permitSwitch;

    /**
     * Instantiates a new response metadata builder.
     */
    public ResponseMetadataBuilder() {
        metadata = new ResponseMetadata();
    }

    /**
     * Parses the UTC date time.
     *
     * @param dateTime the date time
     * @param maxDate  the max date
     * @return the instant
     */
    private static Instant parseUTCDateTime(final String dateTime, final Instant maxDate) {
        Instant instant = null;
        try {
            instant = Instant.parse(dateTime);
            if (instant.isAfter(Instant.now())) {
                LOGGER.info("End date '{}' is in the future -> defaulted to {} ", dateTime, maxDate.toString());
                instant = maxDate;
            }
        } catch (final DateTimeParseException dtpe) {
            LOGGER.error("Cannot parse end date '" + dateTime + "' -> defaulted to now", dtpe);
            instant = maxDate;
        }

        return instant;
    }


    public ResponseMetadataBuilder parse(Page page) {
        page(page.getNumber());
        total(page.getTotalElements());
        limit(page.getSize());
        sort(page.getSort().toString());
        return this;
    }

    /**
     * Total.
     *
     * @param total the total
     * @return the response metadata builder
     */
    public ResponseMetadataBuilder total(final long total) {
        metadata.setTotal(total);
        return this;
    }

    /**
     * Limit.
     *
     * @param limit the limit
     * @return the response metadata builder
     */
    public ResponseMetadataBuilder limit(final int limit) {
        metadata.setLimit(limit);
        return this;
    }

    /**
     * Page.
     *
     * @param page the page
     * @return the response metadata builder
     */
    public ResponseMetadataBuilder page(final int page) {
        metadata.setPage(page + 1);
        return this;
    }

    /**
     * pageRequest.
     *
     * @param pageRequest the page request
     * @return the response metadata builder
     */
    public ResponseMetadataBuilder pageRequest(final PageRequest pageRequest) {
        if (pageRequest != null) {
            page(pageRequest.getPageNumber());
            limit(pageRequest.getPageSize());
            if (pageRequest.getSort() != null && pageRequest.getSort().iterator().hasNext()) {
                final Order first = pageRequest.getSort().iterator().next();
                sort(first.getProperty());
                if (Direction.ASC.equals(first.getDirection())) {
                    order("asc");
                } else {
                    order("desc");
                }
            }
        }
        return this;
    }

    /**
     * Date to utc.
     *
     * @param date the date
     * @return the string
     */
    private String dateToUtc(final Date date) {
        return date == null ? null : Instant.ofEpochMilli(date.getTime()).toString();
    }


    /**
     * Page.
     *
     * @param order the order
     * @return the response metadata builder
     */
    public ResponseMetadataBuilder order(final String order) {
        metadata.setOrder(order);
        return this;
    }

    /**
     * Sort.
     *
     * @param sort the sort
     * @return the response metadata builder
     */
    public ResponseMetadataBuilder sort(final String sort) {
        metadata.setSort(sort);
        return this;
    }

    /**
     * Search.
     *
     * @param search the search
     * @return the response metadata builder
     */
    public ResponseMetadataBuilder search(final String search) {
        metadata.setSearch(search);
        return this;
    }

    /**
     * Start date.
     *
     * @param startDate the start date
     * @return the response metadata builder
     */
    public ResponseMetadataBuilder startDate(final String startDate) {
        metadata.setStartDate(startDate);
        return this;
    }

    /**
     * End date.
     *
     * @param endDate the end date
     * @return the response metadata builder
     */
    public ResponseMetadataBuilder endDate(final String endDate) {
        metadata.setEndDate(endDate);
        return this;
    }

    /**
     * On error use.
     *
     * @param defaultvalue the defaultvalue
     * @return the response metadata builder
     */
    public ResponseMetadataBuilder onErrorUse(final Instant defaultvalue) {
        defaultInstant = defaultvalue;
        return this;
    }

    /**
     * Switch date if before.
     *
     * @param permitSwitch the permit switch
     * @return the response metadata builder
     */
    public ResponseMetadataBuilder switchDateIfBefore(final boolean permitSwitch) {
        this.permitSwitch = permitSwitch;
        return this;
    }

    /**
     * Builds the.
     *
     * @return the response metadata
     */
    public ResponseMetadata build() {
        if (metadata.getStartDate() != null) {
            final Instant instant = parseUTCDateTime(metadata.getStartDate(), defaultInstant);
            metadata.setStartDate(instant == null ? null : instant.toString());
        }
        if (metadata.getEndDate() != null) {
            final Instant instant = parseUTCDateTime(metadata.getEndDate(), defaultInstant);
            metadata.setEndDate(instant == null ? null : instant.toString());
        }
        if (permitSwitch) {
            final Instant start = parseUTCDateTime(metadata.getStartDate(), defaultInstant);
            final Instant end = parseUTCDateTime(metadata.getEndDate(), defaultInstant);
            if (start != null && end != null && start.isAfter(end)) {
                metadata.setStartDate(end.toString());
                metadata.setEndDate(start.toString());
            }
        }
        return metadata;
    }

}
