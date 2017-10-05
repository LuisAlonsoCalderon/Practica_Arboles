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
public class Arbol<T> {

    public Arbol() {
        root = null;
    }
    
    public int size(){
        return size(root);
    }
    
    private int size(Nodo n){
        if (n == null){
            return 0;
        } else {
            return 1 + size(n.getIzquierda()) + size(n.getDerecha());
        }
    }
    
    protected Nodo<T> root;
}
