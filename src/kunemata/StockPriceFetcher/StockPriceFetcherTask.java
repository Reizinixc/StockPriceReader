package kunemata.StockPriceFetcher;

import java.util.HashMap;
import java.util.TimerTask;

public class StockPriceFetcherTask extends TimerTask {

	private StockPriceFetcher fetcher;
   private long period;
   private long delay;

	public StockPriceFetcherTask(StockPriceFetcher fetcher, long period, long delay) {
		this.fetcher = fetcher;
      this.period = period;
      this.delay = delay;
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
	
	public StockPriceFetcher getFetcher() {
      return fetcher;
   }
	
	public long getDelay() {
      return delay;
   }
	
	public long getPeriod() {
      return period;
   }
	
	public void setDelay(long delay) {
      this.delay = delay;
   }
	
	public void setPeriod(long period) {
      this.period = period;
   }
}
