package kunemata.StockPriceFetcher;

import java.util.HashMap;
import java.util.TimerTask;

public class StockPriceFetcherTask extends TimerTask {

	private StockPriceFetcher fetcher;

	public StockPriceFetcherTask(StockPriceFetcher fetcher) {
		this.fetcher = fetcher;
	}

	@Override
	public void run() {
		HashMap<String, Object> outputMap = new HashMap<String, Object>();
		
		try {
			outputMap.put("price", fetcher.getStockPrice());
			outputMap.put("lastUpdated", fetcher.getLastedUpdated());
		} catch (Exception e) {
			outputMap.put("exception", e);
		}
		
		fetcher.notifyObservers(outputMap);
	}

}
