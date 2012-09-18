package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.regex.Pattern;

import kunemata.Exception.CannotReadDataException;
import kunemata.StockPriceFetcher.StockPriceFetcher;
import kunemata.StockPriceReader.StockPriceURLReader;

import org.junit.Before;
import org.junit.Test;

public class TestStockPriceFetcher {
	
	private StockPriceFetcher stockPriceFetcher;
	
	@Before
	public void setUp() throws IOException {
		StockPriceURLReader stockPriceReader = new StockPriceURLReader("http://nilapat.cs.sci.ku.ac.th/~usa/418471/SET/quote.php?symbol=", "PTT");
		stockPriceReader.fetchStockPrice();
		stockPriceFetcher = new StockPriceFetcher(stockPriceReader);
	}

	@Test
	public void testGetStockPrice() throws Exception {
		assertEquals(true, Pattern.matches("\\d+(\\.\\d+){0,1}", Double.toString(stockPriceFetcher.getStockPrice())));
	}
	
	@Test
	public void testGetLastUpdated() {
		assertEquals(true, Pattern.matches("Last Update \\d{1,2} [a-zA-Z]{3} \\d{4} \\d{2}:\\d{2}:\\d{2}", stockPriceFetcher.getLastedUpdated()));
	}
	
	@Test(expected = CannotReadDataException.class)
	public void testGetStockPriceBeforeFetch() {
		StockPriceFetcher fetcher = new StockPriceFetcher(new StockPriceURLReader("http://nilapat.cs.sci.ku.ac.th/~usa/418471/SET/quote.php?symbol=", "PTT"));
		fetcher.getLastedUpdated();
	}
}
