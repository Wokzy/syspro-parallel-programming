import java.util.ArrayList;

class Task13 {
    static int x = 0;
    static int y = 0;
    static int z = 0;

    private static boolean ENABLED_LOGGING = false;
    private static ArrayList<String> trace = new ArrayList<>();
    private static synchronized void log(String msg) {
        if (!ENABLED_LOGGING) {
            return;
        }
        trace.add(msg);
    }

    private static synchronized String trace() {
        if (!ENABLED_LOGGING) {
            return "";
        }
        String result = "";
        for (var s : trace) {
            result += s + " -> ";
        }
        result += "END";
        return result;
    }

    static class A extends Thread {
      public void run() {
        int a_x = x;   log("A.1");
        int a_z = z;   log("A.2");
        y = a_x + a_z; log("A.3");
      }
    }

    static class B extends Thread {
      public void run() {
        int b_x = x; log("B.1");
        x = b_x + 1; log("B.2");
        int b_z = z; log("B.3");
        z = b_z + 1; log("B.4");
      }
    }

    static class C extends Thread {
      public void run() {
        int c_y = y;    log("C.1");
        if (c_y == 2) { log("C.2");
          int c_x = x;  log("C.3");
          x = c_x - 1;  log("C.4");
        }
      }
    }

    public static void main(String... args) {
        if (args.length > 0) {
            ENABLED_LOGGING = Integer.parseInt(args[0]) != 0;
        }

        Thread a = new A();
        Thread b = new B();
        Thread c = new C();

        a.start();
        b.start();
        c.start();

        try {
            a.join();
        } catch (InterruptedException e) {}

        try {
            b.join();
        } catch (InterruptedException e) {}

        try {
            c.join();
        } catch (InterruptedException e) {}

        System.out.printf("x = %2d, y = %2d, z = %2d | %s\n", x, y, z, trace());
    }
}
