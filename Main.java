import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
      Clock c = new Clock();
      c.addMs(System.currentTimeMillis());
      c.addHour(1); // UTC+1

      while (true) {
        System.out.print("\033[H\033[2J"); // Clear escapecode
        System.out.println(c.getDateString());

        TimeUnit.SECONDS.sleep(1);
        c.addSec(1);
      }

    }
}
