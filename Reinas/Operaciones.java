/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;

/**
 * 
 * Clase <code> Operaciones</code> con un método público que 
 * se utiliza para la implementación de la Interfaz Gráfica <code> Reinas</code>
 * por medio de la colección Conjunto de la clase <code>ConjuntoArreglo</code>
 *
 * @author Karen Arteaga Mendoza 190161, Uxia Vázquez Fernández 190940
 * @since 2020-05
 */

public class Operaciones {
    /**
     * conjuntos en los que se guardan los valores
     * de los métodos
     * 
     */
    private ConjuntoArreglo<Integer> col, suma, resta;
    /**
     * arreglo tipo Integer que represeta una 8-tupla
     */
    private Integer []sol;
    /**
     * matriz tipo Integer en la que se guardan las 8-tuplas válidas
     */
    private Integer [][] arregloSol;
    /**
     * entero que lleva la cuenta del número de 8-tuplas válidas
     */
    int sig;
    /**
     * variable entero que representa la fila en la que se encuentra la primera 
     * reina
     */
    int fila;
            
    /**   
     * Constructor
     * llama al método <code> verificar()</code> con la variable cl antes de inicializar 
     * atributos
     * @param cl Indica el valor de la columna en la que se encuentra la reina 1
     * @param fl Indica el valor de la fila en la que se encuentra la reina 1
     */
    public Operaciones(Integer cl, Integer fl){
        verificar(cl);
        verificar(fl);
        col= new ConjuntoArreglo<>();
        suma= new ConjuntoArreglo<>();
        resta= new ConjuntoArreglo<>();
        sol= new Integer[8];
        arregloSol= new Integer[92][92];
        sig=0;
        sol[fl]= cl;
        col.add(cl);
        suma.add(cl+fl);
        resta.add(fl-cl);
        fila= fl;
        
    } 
    /**
     * 
     * método privado que calcula las 8-tuplas que se guardan en <code> arregloSol</code>
     * llama al método privado <code> buscarReinas</code>
     * 
     * @return la matriz de las 8-tuplas válidas
     */
    public Integer[][] calcular(){       
        buscarReinas(1,fila, col, suma, resta);
        /**for(int j=0; j<sig; j++){
            for (int i=0; i<8; i++)
                System.out.print(arregloSol[j][i]);
            System.out.println();
          } */
        System.out.println("numero de soluciones: "+ sig);
        return arregloSol;    
    }
    /**
     * método privado recursivo que busca las 8-tuplas válidas simulando
     * de un algoritmo de búsqueda a profundidad
     * llama al método privado <code> llenarMat()</code>
     * 
     * @param k entero que indica los vectores k-prometedores existentes
     * @param c Conjunto que contiene las columnas no válidas
     * @param s Conjunto que contine las diagonales a 135° no válidas
     * @param r Conjunto que contiene las diagonales 45° no válidas
     */
    private void buscarReinas(int k, int fil, ConjuntoArreglo<Integer> c, ConjuntoArreglo<Integer> s, ConjuntoArreglo<Integer> r){                     
        fil=siguiente(fil);
        if (k==8)
            llenarMat(sol);
        else{           
            for (int i=0; i<=7; i++){
                if (!c.contains(i)&& !s.contains(fil+i) && !r.contains(fil-i)){
                    sol[fil]=i;
                    c.add(i);
                    s.add(fil+i);
                    r.add(fil-i); 
                    buscarReinas(k+1,fil, c, s, r);        
                    c.remove(i);
                    s.remove(fil+i);
                    r.remove(fil-i); 
                }
            }  
        }      
    }
    /**
     * método privado que modifica la matriz <code> arregloSol</code>
     * inicializa una variable tipo <code> Random</code> de la clase 
     * <code> java.util.Random</code>
     * 
     * @param arr arreglo de enteros que representa una 8-tupla válida
     * @see Random
     */
    private void llenarMat(Integer[] arr){
        for (int j=0; j<8; j++)
            arregloSol[sig][j]=arr[j];
        sig++;                  
    }
    /**
     * Unico método público de la Clase 
     * llama al método calcula() tipo <code> Integer[][]</code> y elige dentro 
     * de él un vector aleatorio
     * Importa la clase Random de 
     * 
     * @return Integer[] solución aleatoria las posibles soluciones a la 
     * columna dada
     */
    public Integer[] obtenerColumnas(){
        Integer[][] matriz= calcular();
        Random ran= new Random();
        int num= ran.nextInt(sig);
        Integer[] res= new Integer[8];
        System.arraycopy(matriz[num], 0, res, 0, 8);       
        return res;
    }
    /**
     * método privado que revisa la validez de la variable ingresada 
     * 
     * @param a entero a verificar
     * @throws IndexOutOfBoundsException si la variable no está dentro 
     * de los parámetros
     */
   
    private static void verificar(int a){
       if (a<0||a>7)
           throw new IndexOutOfBoundsException();
    }
    /**
     * 
     * @return int numero de soluciones posibles
     */
    public int getCuantas(){
        return sig;
    }
    /**
     * método privado que calcula la fila en la que se ecuentra la siguiente reina
     * @param a entero que representa la fila anterior
     * @return int
     */
    private int siguiente(int a){         
        if(a==7)
            a=0;
        else
            a++;  
        return a;
    }
}