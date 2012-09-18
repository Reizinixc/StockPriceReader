package test;

import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import kunemata.StockPriceFetcher.StockPriceFetcher;
import kunemata.StockPriceFetcher.StockPriceFetcherTask;
import kunemata.StockPriceReader.StockPriceURLReader;

import org.junit.Before;
import org.junit.Test;

public class TestStockPriceFetcherTask {
	
	private StockPriceFetcherTask stockPriceFetcherTask;
	private StockPriceFetcher fetcher;
	
	@Before
	public void setUp() {
		fetcher = new StockPriceFetcher(new StockPriceURLReader("http://nilapat.cs.sci.ku.ac.th/~usa/418471/SET/quote.php?symbol=", "PPT"));
		stockPriceFetcherTask = new StockPriceFetcherTask(fetcher);
	}

	@Test
	public void testInitialization() {
		StockPriceFetcher fetcher = new StockPriceFetcher(new StockPriceURLReader("http://nilapat.cs.sci.ku.ac.th/~usa/418471/SET/quote.php?symbol=", "PPT"));
		StockPriceFetcherTask fetcherTask = new StockPriceFetcherTask(fetcher);
	}
	
	@Test
	public void testTimerTask() {
		Observer o = new Observer() {
			
			@Override
			public void update(Observable o, Object arg) {
				System.out.println(arg);
			}
		}; 
		
		fetcher.addObserver(o);
		
		Timer t = new Timer();
		t.schedule(stockPriceFetcherTask, 1000);
	}

}
