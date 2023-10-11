public class GiocoDadi implements Runnable{

    private int Giocatore1Punti;
    private int Giocatore2Punti;

    GiocoDadi() {

        Thread ct = Thread.currentThread();
        ct.setName("Arbitro del gioco");

        Thread g1 = new Thread(this, "Giocatore 1");
        Thread g2 = new Thread(this, "Giocatore 2");

        g1.start();
        g2.start();

        try {
            g1.join();
            g2.join();
        } catch (InterruptedException e) {
            System.out.println("Il padre è stato interrotto.");
        }

        System.out.print("\n");
        if (Giocatore1Punti == Giocatore2Punti) {
            System.out.println("Pareggio.");
        } else if (Giocatore1Punti > Giocatore2Punti) {
            System.out.println("Il Giocatore 1 ha vinto con: " + Giocatore1Punti + " punti.");
            System.out.println("Giocatore 2 ha realizzato: " + Giocatore2Punti + " punti.");
        } else {
            System.out.println("Il Giocatore 2 ha vinto con: " + Giocatore2Punti + " punti.");
            System.out.println("Giocatore 1 ha realizzato: " + Giocatore1Punti + " punti.");
        }

        System.out.println("\nIl padre cessa di vivere");

    }

    @Override
    public void run() {

        double casuale;

        try {

            for (int i=10; i>0; --i) {

                casuale = 1+(int)(Math.random()*6);
                switch (Thread.currentThread().getName()) {
                    case "Giocatore 1" -> Giocatore1Punti += casuale;
                    case "Giocatore 2" -> Giocatore2Punti += casuale;
                }

                Thread.sleep(1000);

            }

        } catch (InterruptedException e) {
            System.out.println("Il figlio è stato terminato.");
        }

        System.out.println("Il figlio cessa di vivere");

    }

    public static void main(String[] args) {
        GiocoDadi g = new GiocoDadi();
    }
}