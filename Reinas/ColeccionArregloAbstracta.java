/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase generica abstracta en la que se implementan 
 * estructuras de datos y metodos comunes para todas las implementaciones
 * de colecciones basadas en arreglos
 * 
 * @author Karen Arteaga 190161
 * @param <E> Indica el tipo de datos de los elementos de la colección
 * 
 */
abstract public class ColeccionArregloAbstracta<E> implements Collection<E>{
    /**
     * Contiene los elementos guardados en este conjunto
     */
    protected E[] arreglo;
    /**
     * Indica el lugar donde se gurarda el ultimo elemento válido
     */
    protected int ultimo;
    /**
     * Indica el factor de crecimiento para incrementar el tamaño del arreglo
     */
    private float fc;
    /**
     * Constructor
     * 
     * @param tam Indica el tamaño inicial del arreglo interno
     * @param factorCre Indica el factor de crecimiento utilizado
     * para incrementar el arreglo
     */
    public ColeccionArregloAbstracta(int tam, float factorCre){
      if (factorCre>1)
          fc=factorCre;
      else
          throw new IllegalArgumentException("Argumento de factor "
                  + "de crecimiento invalido");
      if (tam<0)
          tam=-tam;
      arreglo=(E[]) new Object[tam];
      ultimo=-1;
           
    }
    /**
     * Constructor
     * 
     * @param tam  Indica el tamaño inicial del arreglo interno
     */
    public ColeccionArregloAbstracta(int tam){
        this(tam, 2);
    }
    /**
     * Constructor 
     */
    public ColeccionArregloAbstracta(){
        this(10, 2);
    }
    /**
     * Obtiene el factor de crecimiento usado por el método
     * (@code incrementaArreglo)
     * 
     * @return El factor de crecimiento usado por el método
     * (@code incrementaArreglo)
     */
    public float getFactorCre(){
        return fc;
    }
    
    //Interfaz Collection
   /**
    * Indica el tamaño de la colección
    * @return la cantidad de elementos de la colección
    */
    @Override
    public int size(){
        return ultimo+1;
    }
    /**
     * Indica si la colección es vacía
     * @return {@code true} si no hay elementos 
     */    
    @Override
    public boolean isEmpty(){
        return ultimo==-1;
    }
    /**
     * Indica si la colección contiene o no un objeto determinado
     * @param o Indica el objeto que se busca
     * @return {@code true} si se encontró dentro de la colección
     */
    @Override
    public boolean contains(Object o){
        for (int i=0; i<=ultimo; ++i){
            Object elemento=arreglo[i];           
            if (o==null){
                if (elemento==o)
                    return true; 
            }else{
                if (o.equals(elemento))
                return true;
            }
        }
        return false;
    }
    /**
     * Indica si fue posible añadir todos los elementos de una colección determinada a la colección
     * @param c Indica la colección
     * @return {@code true} si se agregaron exitosamente
     */
    @Override
    public boolean addAll(Collection<? extends E> c){
        boolean res= false;
        for (E obj: c){
            if (add(obj))
                res=true;
        }
       return res;
    }
    /**
     * Crea un iterador de la colección
     * @return {@code Iterator<E>} de la colección
     */
    @Override
    public Iterator<E> iterator(){
        return new Iterator<E>(){
            private int i=ultimo;
            @Override
            public boolean hasNext(){
                return i>0;
            }
            @Override
            public E next(){
                if (i<0)
                    throw new NoSuchElementException("La pila no tiene"
                            + "mas elementos para iterar");
                return arreglo[i--];
            }
        };
    }
    /**
     * Crea un arreglo con los elementos de la colección
     * @return nuevo arreglo {@code Object[]} 
     */
    @Override 
    public Object[] toArray() { 
        return Arrays.copyOf(arreglo, ultimo + 1); 
    } 
    /**
     * Guarda los elementos de la colección en un arreglo determinado
     * @param <T> Tipo de dato
     * @param a Arreglo en el que se guardarán los elementos
     * @return arreglo {@code T[]} 
     */
    @Override 
    public <T> T[] toArray(T[] a) { 
        if (a.length < ultimo + 1) 
            a = Arrays.copyOf(a, ultimo + 1);          
        int i = 0; 
        for (E objeto : this) 
            a[i++] = (T) objeto;        
        for (int j = i; j < a.length; ++j) 
            a[j] = null; 
        return a; 
    } 
    /**
     * Indica si todos los elementos de una colección se encuentran en otra
     * @param c Indica la colección que se busca
     * @return true si se encontraron
     */
    @Override 
    public boolean containsAll(Collection<?> c) { 
        if (c == null) 
            return false; 
        if (c.size()==0) 
            return true; 
        return c.stream().noneMatch((obj) -> (!contains((E) obj)));     
    } 
    /**
     * Vuelve a todos los elementos de la coleción {@code null}
     */
    @Override 
    public void clear() { 
        Arrays.fill(arreglo, null); 
        ultimo = -1; 
    } 
    /**
     * Incrementa el arreglo dependiendo del factor de crecimiento
     */
    protected void incrementaArreglo() {          
        arreglo = Arrays.copyOf( arreglo,  (int) (arreglo.length * fc) );         
    } 
    /**
     * Imprime todos los elementos de la colección
     */
    public void imprimir(){
        if (ultimo!=-1){
            for (int i=0; i<=ultimo; i++)
                System.out.print(arreglo[i]+"-");
            System.out.println();
        }        
    }
    
    
    
}
