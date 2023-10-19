package analyzer;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.ArrayList;
import java.util.List;

public class Analyzer implements Runnable {


    long[] entradas = {1,2,3,4,5,6,7,8,9,10,12,14,16,18,20,22,24,26,28,30,35,40,45,50,60,70,80,90,100,300,500,700,1000,2000,4000,6000,10000,20000,40000,60000,80000,100000,150000,200000,300000,500000,650000,10000000,30000000,50000000,800000000,90000000,
            100000000,500000000,999999999}; //Entradas para el programa


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

   private List<Double> tiempos_algoritmo(int numintentos, long tiempoEjecucion,Algorithm algoritmoActual){
       List<Double> tiempos = new ArrayList<>(); //Lista para guardar tiempos

       Chronometer tiempo_calculo = new Chronometer(); //Cronometro para vigilar el tiempo de ejecucion



        double tl1 = Double.MAX_VALUE; //variable para guardar tiempo minimo

       int i = 0;

        tiempo_calculo.start();
        while((i < entradas.length) && (tiempo_calculo.getElapsedTime() < tiempoEjecucion)){ //While para cada N

            int j = 0;

            while((j<numintentos) && (tiempo_calculo.getElapsedTime() < tiempoEjecucion)){ //While para cada intento de N
                Chronometer tiempoIntento = new Chronometer();

                tiempoIntento.start();
                algoritmoActual.init(entradas[i]);  //Ejecutamos el algoritmo
                tiempoIntento.stop();


                if(tl1 > tiempoIntento.getElapsedTime()*(10^10)) tl1 = tiempoIntento.getElapsedTime()*10^10; // Si ha habido un tiempo menor, es el que se guarda
                j++;
            }

                if(tiempo_calculo.getElapsedTime() < tiempoEjecucion){
                    tiempos.add(tl1);
                }
            i++;
        }
        tiempo_calculo.stop();
        return  tiempos;
    }





    public  String findComplexityOf(Algorithm algorithm, long maxExecutionTime) {



          List<Double> tiempos; // Lista para guardar los tiempos de cada iteracion

          int numintentos = 300;


          tiempos = tiempos_algoritmo(numintentos,maxExecutionTime/2,algorithm); //Calculamos los tiempos de las iteraciones, como tiempo maximo vamos cambiando los valores hasta dar exactitud


        for(int i = 0;i < tiempos.size();i++){
            System.out.println("---------" + tiempos.get(i) + "-----------");
        }

        return complexity;
    }

}
