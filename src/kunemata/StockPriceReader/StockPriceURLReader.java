package kunemata.StockPriceReader;

import java.io.IOException;

import kunemata.Exception.CannotReadDataException;

import org.apache.xerces.impl.io.MalformedByteSequenceException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class StockPriceURLReader implements StockPriceReaderIF {

	private static final String XPATH_STOCK_PRICE_EXPR = "/html/body/table/tbody/tr/td/table/tbody/tr/td[2]/table[2]/tbody/tr[3]/td[2]/table/tbody/tr[3]/td[2]/table/tbody/tr/td/table[2]/tbody/tr[1]/td[2]/table[1]/tbody/tr[8]/td[2]/font/strong/text()";
	private static final String XPATH_LAST_UPDATED_EXPR = "/html/body/table/tbody/tr/td/table/tbody/tr/td[2]/table[2]/tbody/tr[3]/td[2]/table/tbody/tr[3]/td[2]/table/tbody/tr/td/table[2]/tbody/tr[1]/td[2]/table[1]/tbody/tr[1]/td/text()[1]";

	private String basePath;
	private String stockName;

	private HtmlPage currentPage;

	public StockPriceURLReader(String basePath, String stockName) {
		this.basePath = basePath;
		this.stockName = stockName;
	}

	public void fetchStockPrice() throws IOException {
		WebClient client = new WebClient();
		client.setCssEnabled(false);
		client.setJavaScriptEnabled(false);

		try {
			currentPage = client.getPage(basePath + stockName);
		} catch (FailingHttpStatusCodeException | MalformedByteSequenceException e) {
			throw new CannotReadDataException();
		}
	}

	@Override
	public double getStockPrice() throws Exception {
		fetchStockPrice();

		DomText stockPriceDomText = currentPage
				.getFirstByXPath(XPATH_STOCK_PRICE_EXPR);
		return Double.parseDouble(stockPriceDomText.asText());
	}

	@Override
	public String getLastUpdated() {
		if (currentPage == null)
			throw new CannotReadDataException();
		else {
			DomText lastUpdatedDomText = currentPage.getFirstByXPath(XPATH_LAST_UPDATED_EXPR);
			return lastUpdatedDomText.asText();
		}
	}

}
