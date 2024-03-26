package dinner;

import java.util.concurrent.Semaphore;

// Classe que representa o jantar dos filósofos
public class DiningPhilosophers {
    public static final int NUM_PHILOSOPHERS = 5; // Número de filósofos
    public static final int MEALS_PER_PHILOSOPHER = 10; // Número de refeições para cada filósofo

    public static void main(String[] args) {
        Semaphore[] forks = new Semaphore[NUM_PHILOSOPHERS]; // Garfos.
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS]; // Filósofos.

        // Inicializa os garfos.
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Semaphore(1);
        }

        // Inicializa os filósofos.
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            Semaphore leftFork = forks[i]; // Atribui o garfo da esquerda.
            Semaphore rightFork = forks[(i + 1) % NUM_PHILOSOPHERS]; // Atribui o garfo da direita.
            philosophers[i] = new Philosopher(i, leftFork, rightFork, MEALS_PER_PHILOSOPHER); // Cria o filósofo.
            philosophers[i].start(); // Inicia o filósofo.
        }
    }
}
