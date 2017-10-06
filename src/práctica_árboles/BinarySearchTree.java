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
    
    public boolean esBST(){
        return esBST(super.root);
    }
    
    private boolean esBST(Nodo n){ // se puede hacer en menos lineas de codigo
        if (n == null){
            return true;
        } else {
            boolean izq = true;
            boolean der = true;
            if (n.getIzquierda() != null){
                if (n.getIzquierda().getDato().compareTo(n.getDato()) < 0){
                    izq = esBST(n.getIzquierda());
                } else {
                    izq = false;
                }
            }
            if (n.getDerecha() != null){
                if (n.getDerecha().getDato().compareTo(n.getDato()) >= 0){
                    der = esBST(n.getDerecha());
                } else {
                    der = false;
                }
            }
            return izq && der;
        }
    }
}
