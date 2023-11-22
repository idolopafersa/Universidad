/*
El Valor de una torreta estará determinado por su DPS * Rango/Coste,
por eso si una torreta tiene Un gran DPS pero es muy cara o poco rango
 no tendrá tanto valor
*/

package net.agsh.towerdefense.strats;

import net.agsh.towerdefense.Tower;

import java.util.*;

public class TowerBuyer {



    private static class TowerInfo{
        int index;
        float value;

        public TowerInfo(int index, float value){
            this.index = index;
            this.value = value;
        }


    }


    public static float getTowerValue(Tower tower){
        return (tower.getDamage()/tower.getCooldown())*(tower.getRange()/tower.getCost());
    }

    public static ArrayList<Integer> buyTowers(ArrayList<Tower> towers, float money) {
        // This is just a (bad) example. Replace ALL of this with your own code.
        // The ArrayList<Integer> returned is a list of the indices of the towers you want to buy.
        // For example, if you want to buy the first and third towers, return [0, 2].
        // The selected towers must be affordable, and the total cost must be less than or equal to money.
        // The indices should be given in the order that the towers are given in the original ArrayList<Tower> towers.

        // Create an ArrayList<Integer> to store the indices of the towers you want to buy.
        HashMap<Tower, Float> TablaMemoria = new HashMap<>();
        ArrayList<TowerInfo> towerInfos = new ArrayList<>();

        for (int i = 0; i< towers.size();i++){
            Tower tower = towers.get(i);
            float value = 0.0f;
            boolean ValorYaCalculado = false;

            for (Map.Entry<Tower, Float> EntryMemoria : TablaMemoria.entrySet()){  //Comparamos cada Entry del TreeMap
                if (tower.sameTypeAs(EntryMemoria.getKey())){  //Si encontramos una torre del mismo tipo, cogemos su valor y terminamos
                    value = EntryMemoria.getValue();
                    ValorYaCalculado = true;

                    break;
                }
            }
            if(!ValorYaCalculado){ //El Valor de Tipo de Torre no ha sido Calculado, por lo que calculamos su valor, y lo añadimos al TreeMap
                value = getTowerValue(tower);
                TablaMemoria.put(tower,getTowerValue(tower));
            }
            towerInfos.add(new TowerInfo(i,value));  //Añadimos al Array de iformacion de las torretas, la nueva informacion

            //Ahora vamos a Crear una Java Collection para ordenar el Array en funcion del valor en orden Descendente

            Collections.sort(towerInfos, Comparator.comparingDouble(info -> -info.value));

        }

        //Ahora que tenemos el Array Ordenado, vamos a seleccionar las torretas
        ArrayList<Integer> selected = new ArrayList<>();
        float actualMoney = 0.0f;

        for(TowerInfo Actual : towerInfos){

            if(actualMoney+towers.get(Actual.index).getCost() + actualMoney <= money){
                selected.add(Actual.index);
                actualMoney += towers.get(Actual.index).getCost();
            }

        }




        return selected;
    }
}
