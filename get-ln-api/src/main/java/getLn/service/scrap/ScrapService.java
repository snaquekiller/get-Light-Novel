package getLn.service.scrap;

import org.jsoup.Connection;

/**
 * .
 */
abstract class ScrapService {

    protected Connection addInfo(Connection connect) {
        return connect
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com");
    }

}
