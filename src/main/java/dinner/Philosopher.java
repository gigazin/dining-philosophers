package dinner;

import java.util.concurrent.Semaphore;

// Classe que representa os filósofos.
public class Philosopher extends Thread {
    private Semaphore leftFork; // Garfo esquerdo.
    private Semaphore rightFork; // Garfo direito.
    private int id; // ID do filósofo.
    private int meals; // Número de refeições.

    // Construtor dos filósofos.
    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork, int meals) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.meals = meals;
    }

    // Método que simula o jantar dos filósofos.
    public void run() {
        try {
            while (meals > 0) {
                think();
                eat();
                meals--;
            }
            if (meals == 0) {
                System.out.println("Filósofo " + id + " terminou suas refeições.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Método privado que simula o pensamento do filósofo.
    private void think() throws InterruptedException {
        try {
            System.out.println("Filósofo " + id + " está pensando.");
            Thread.sleep((long) (Math.random() * 2000)); // Aguarda um intervalo aleatório de até 2 segundos.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Método privado que simula a refeição do filósofo.
    private void eat() throws InterruptedException {
        try {
            leftFork.acquire();
            rightFork.acquire();

            System.out.println("Filósofo " + id + " está comendo.");
            Thread.sleep((long) (Math.random() * 2000)); // Aguarda um intervalo aleatório de até 2 segundos.

            leftFork.release();
            rightFork.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
