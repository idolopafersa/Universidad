package analyzer;

import javax.annotation.processing.SupportedSourceVersion;

public class Analyzer implements Runnable {

    long n1 = 1;
    long n2 = 1;

    Algorithm algorithm;
    long maxExecutionTime;
    public String complexity;

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

    private void  incremento(Double ratio, long newn1, long newn2){


        if(newn1<20){
            newn1= newn1+newn1;
        }else if(ratio <1.5){
            newn1=newn1*100;
        }else if(ratio < 3){
            newn1=newn1*10000;
        }
        newn2 = newn2*2;

        n2 = newn2;
        n1 = newn1;
    }

    public  String findComplexityOf(Algorithm algorithm, long maxExecutionTime) {
        double tl1 = 999999999999999999999.0;
        double tl2 = 999999999999999999999.0;



        Chronometer chrono = new Chronometer();


        int n = 0;


        double ratio = 0.0;

        Chronometer algortimTimer = new Chronometer();
        Chronometer algortimTimer2 = new Chronometer();
        chrono.start();
        while ((n < 5) && (chrono.getElapsedTime() <= maxExecutionTime)) {

            System.out.println("Iteracion " + n + " Algoritmo " + algorithm.getName() + "Con un ratio actual de " + ratio);

            algortimTimer.start();
            algorithm.init(n1);
            algortimTimer.pause();

            tl1 = Math.min(tl1,algortimTimer.getElapsedTime());



            algortimTimer2.start();
            algorithm.init(n2);
            algortimTimer2.pause();

            tl2 = Math.min(tl2,algortimTimer2.getElapsedTime());

            ratio =(tl1)/(tl2);
            incremento(ratio,n1,n2);






            n++;
        }

        System.out.println("------------------"+ ratio + "----------------");
        if( ratio < 1.5){
            complexity = "1";
        }else{
            complexity = "log(n)";
        }


        chrono.stop();
        return complexity;
    }

}
