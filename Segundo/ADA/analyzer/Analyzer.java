package analyzer;

public class Analyzer implements Runnable {
    Algorithm algorithm;
    long maxExecutionTime;
    String complexity = null;

    public Analyzer(Algorithm algorithm, long maxExecutionTime) {
        this.algorithm = algorithm;
        this.maxExecutionTime = maxExecutionTime;
    }

    public String getComplexity() {
        return complexity;
    }

    @Override
    public void run() {
        complexity = findComplexityOf(algorithm, maxExecutionTime);
    }

    static String findComplexityOf(Algorithm algorithm, long maxExecutionTime) {
        Chronometer timer = new Chronometer();
        timer.pause();
        // Mientras el tiempo sea menor, calculamos la complejidad
        while(timer.getElapsedTime() < maxExecutionTime) {
            System.out.println("Calculando complejidad del algoritmo: " + algorithm.getName());
            algorithm.init(10);
            timer.resume();
            

        }


        Chronometer chrono = new Chronometer();
        chrono.pause();
        int n = 10;
        algorithm.init(n);
        chrono.resume();
        algorithm.run();
        long time = chrono.getElapsedTime();
        String complexity = "1";
        if(time > 0.1) {
            complexity = "log(n)";
        }
        return complexity;
    }
}
