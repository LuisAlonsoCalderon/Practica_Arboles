/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package práctica_árboles;

/**
 *
 * @author luis
 */
public class Nodo <T extends Comparable> {

    public Nodo(Nodo derecha, Nodo izquierda, T dato) {
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.dato = dato;
    }

    public Nodo(T dato) {
        this(null, null, dato);
    }

    public T getDato() {
        return dato;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }
    
    private Nodo derecha;
    private Nodo izquierda;
    private T dato;
}
