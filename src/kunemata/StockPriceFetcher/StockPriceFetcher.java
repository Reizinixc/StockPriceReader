package kunemata.StockPriceFetcher;

import java.util.Observable;

import kunemata.StockPriceReader.StockPriceReaderIF;

public class StockPriceFetcher extends Observable {
	
	private StockPriceReaderIF stockPriceReader;
	
	public StockPriceFetcher(StockPriceReaderIF stockPriceReader) {
		this.stockPriceReader = stockPriceReader;
	}
	
	public double getStockPrice() throws Exception {
		return stockPriceReader.getStockPrice();
	}
	
	public String getLastedUpdated() {
		return stockPriceReader.getLastUpdated();
	}
	
	@Override
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}
}
