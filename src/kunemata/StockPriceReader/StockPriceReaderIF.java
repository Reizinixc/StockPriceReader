package kunemata.StockPriceReader;

/**
 * <code>StockPriceReaderIF</code>
 * 
 * @author kunemata
 * @version 1.0
 * @since 1.0
 */
public interface StockPriceReaderIF {
	double getStockPrice() throws Exception;
	String getLastUpdated() throws Exception;
}
