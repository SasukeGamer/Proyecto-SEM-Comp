/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Collection;

/**
 * Implementación de la interfaz Conjunto (Set) utilizando 
 * como estructura de datos interna arreglos 
 * @author Karteagame 190161
 * @param <E> Indica el tipo de datos de los elementos de la colección
 */
public class ConjuntoArreglo<E> extends ColeccionArregloAbstracta<E> implements Conjunto<E> {
    /**
     * Constructor
     * @param tam Indica el tamaño del conjunto
     * @param fac Indica el factor de crecimiento del conjunto
     */
    public ConjuntoArreglo(int tam, float fac){
        super(tam, fac);
    }
    /**
     * Constructor
     * @param tam Indica el tamaño del conjunto
     */
    public ConjuntoArreglo(int tam){
        super(tam);
    }
    /** se llama por default a super() por ser vacio **/
    public ConjuntoArreglo(){       
    }
    /**
     * Agrega un elemento nuevo al conjunto
     * @param e Indica el elemento que se quiere añadir al conjunto
     * @return <code>true</code> si el elemento no era parte del conjunto anteriormente
     * y se agregó de manera correcta
     */
    @Override
    public boolean add(E e){
        boolean res= false;
        if (!contains(e)){
            if (arreglo.length-1== ultimo)
                incrementaArreglo();
            arreglo[++ultimo]=e;
            res= true;
        }
        return res;
    }
    /**
     * Elimina un elemento específico del conjunto 
     * @param o Indica el objeto que se desea eliminar
     * @return true si se encontró y se eliminó correctamente
     */
    @Override
    public boolean remove(Object o) {
        if (contains(o)){
            int index=0;
            for (int i=0; i<=ultimo; i++){
                if (arreglo[i].equals(o)){
                    index=i;
                    break;
                }
            }
            for(int i=index; i<ultimo; i++){
                arreglo[i]=arreglo[i+1];
            }
            arreglo[ultimo]=null;
            ultimo--;
            return true;
        }
        return false;          
    }
    /**
     * Elimina del conjunto todos los elementos que se encuentran en una colección determinada
     * @param c Indica la colección en la que se encuentran los elemento
     * @return true si se lograron eliminar todos los elementos de manera correcta
     */

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c==null)
            return true;
        if (c.isEmpty())
            return true;
        Object[] col= c.toArray();
        int tamIn= size();
        for (Object o: col){
            remove(o);
        }
        return size()==tamIn-c.size();               
   }
    /**
     * 
     * @param c Indica la colección que se quiere retener
     * @return false
     * @throws UnsupportedOperationException
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Método que crea un nuevo conjunto con la unión del conjunto acual y otro 
     * @param c Indica el conjunto con el que se quiere evaluar la unión 
     * @return {@code ConjuntoArreglo<E>} nuevo conjuto que contiene los elementos de la union
     * 
     */
    public ConjuntoArreglo<E> unionConjunto(ConjuntoArreglo<E> c){
        ConjuntoArreglo<E> nuevo= new ConjuntoArreglo<>();
        nuevo.addAll(this);
        nuevo.addAll(c);
        return nuevo;
    }
    /**
     * Agrega todos los elementos de una colección al conjunto
     * @param c Indica la colección
     * @return true si se agregaron todos los elementos exitosamente
     */
    protected boolean addAll(ConjuntoArreglo<E> c){
        boolean res= false;
        for (int i=0; i<c.size(); i++){
            res= false;
            if ( add((E) c.toArray()[i]))
               res= true;
       }
        return res;
    }

    /**
     * Metodo que regresa la intersección del conjunto con otro
     * @param c Indica el conjunto con el que se quiere evaluar la intersección
     * @return {@code ConjuntoArreglo<E>} nuevo conjuto que contiene los elementos de la intersección
     */
    public ConjuntoArreglo<E> interseccion(ConjuntoArreglo<E> c){
        ConjuntoArreglo<E> nuevo= new ConjuntoArreglo();
        for(E obj: c){
            if (this.contains(obj))
                nuevo.add(obj);
        }
        return nuevo;
    }
    
}
