/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package práctica_árboles;

/**
 *
 * @author luis
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable> extends Arbol {

    public BinarySearchTree() {
        super();
    }
    
    public void add(T dato){
        root = add(dato, super.root);
    }
    
    private Nodo add(T dato, Nodo n){
        if (n == null){
            return new Nodo(dato);
        } else {
            if (dato.compareTo(n.getDato()) < 0){
                n.setIzquierda(add(dato, n.getIzquierda()));
            } else {
                n.setDerecha(add(dato, n.getDerecha()));
            }
            return n;
        }
    }
}
