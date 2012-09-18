package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.regex.Pattern;

import kunemata.Exception.CannotReadDataException;
import kunemata.StockPriceReader.StockPriceURLReader;

import org.junit.Before;
import org.junit.Test;

public class TestStockPriceURLReader {
	
	private StockPriceURLReader stockPriceURLReader;
	
	@Before
	public void setUp() throws IOException {
		stockPriceURLReader = new StockPriceURLReader("file:///D:/kunemata/workspace/lab13/web/", "PTT.html");
		stockPriceURLReader.fetchStockPrice();
	}

	@Test
	public void testGetStockPrice() throws Exception {
		String stockPrice = Double.toString(stockPriceURLReader.getStockPrice());
		assertEquals(true, Pattern.matches("\\d+\\.\\d+", stockPrice));
	}
	
	@Test
	public void testGetLastUpdated() {
		assertEquals(true, Pattern.matches("Last Update \\d{1,2} [a-zA-Z]{3} \\d{4} \\d{2}:\\d{2}:\\d{2}", stockPriceURLReader.getLastUpdated()));
	}
	
	@Test(expected = CannotReadDataException.class)
	public void testGetLastUpdatedBeforeFetch() {
		StockPriceURLReader priceURLReader = stockPriceURLReader = new StockPriceURLReader("file:///D:/kunemata/workspace/lab13/web/", "CPALL.html");
		String s = priceURLReader.getLastUpdated();
	}

}
