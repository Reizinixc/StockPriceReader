import java.util.Scanner;

import kunemata.StockPriceFetcher.StockPriceFetcher;
import kunemata.StockPriceFetcher.StockPriceFetcherTask;
import kunemata.StockPriceReader.StockPriceURLReader;


public class Main {
   
   private String stockName;

   public void run() {
      StockPriceFetcherTask fetcherTask = new StockPriceFetcherTask(new StockPriceFetcher(new StockPriceURLReader("http://nilapat.cs.sci.ku.ac.th/~usa/418471/SET/quote.php?symbol=", stockName)), 0, 5000);
      StockPriceFetcherConsole console = new StockPriceFetcherConsole(fetcherTask);
      console.run();
   }
   
   public Main(String stockName) {
      this.stockName = stockName;
   }
   
   public static void main(String[] args) {
      new Main(new Scanner(System.in).nextLine()).run();
   }
}
