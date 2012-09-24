import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import kunemata.StockPriceFetcher.StockPriceFetcherTask;

public class StockPriceFetcherConsole implements Observer {

   private StockPriceFetcherTask fetcherTask;
   private Timer timer;

   public StockPriceFetcherConsole(StockPriceFetcherTask fetcherTask) {
      this.fetcherTask = fetcherTask;
      this.timer = new Timer();
      fetcherTask.getFetcher().addObserver(this);
   }

   public void run() {
      timer.schedule(fetcherTask, fetcherTask.getPeriod(), fetcherTask.getDelay());
   }

   @Override
   public void update(Observable o, Object arg) {
      Map<String , Object> outputMap = (Map<String, Object>) arg;
      Exception e = (Exception) outputMap.get("exception");
      if (e != null) {
         System.err.println(e);
         timer.cancel();
         System.exit(1);
      } else {
         System.out.println(outputMap.get("lastUpdated") + " " + outputMap.get("price"));
      }
   }
}
