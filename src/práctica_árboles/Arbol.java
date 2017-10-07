/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package práctica_árboles;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 * @param <T>
 */
public class Arbol<T extends Comparable> {

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
            int a = size(n.getIzquierda());
            int b = size(n.getDerecha());
            return 1 + a + b;
        }
    }
    
    public int altura(){ // o profundidad?
        return altura(root);
    }
    
    private int altura(Nodo n){
        if (n == null){
            return 0;
        } else {
            int a = altura(n.getIzquierda());
            int b = altura(n.getDerecha());
            return 1 + Math.max(a, b);
        }
    }
    
    public boolean contains(T elemento){
        return contains(elemento, root);
    }
    
    private boolean contains(T elemento, Nodo n){
        if (n == null){
            return false;
        }
        boolean contiene = elemento.equals(n.getDato());
        return contiene || contains(elemento, n.getIzquierda()) || contains(elemento, n.getDerecha());
    }
    
    public int nodosPorNivel(int n){
        return nodosPorNivel(n, root);
    }
    
    private int nodosPorNivel(int n, Nodo act){
        if (n < 0 || act == null){
            return 0;
        } else if (n == 0){
            return 1;
        } else {
            return nodosPorNivel(n-1, act.getIzquierda()) + nodosPorNivel(n-1, act.getDerecha());
        }
    }
    
    public void nivelMasPoblado(){
        List<T>[] lis = new List[altura()];
        nivelMasPoblado(root, lis, 0);
        int tamMay = 0;
        List<T> mayor = null;
        for(List<T> l : lis){
            if (l.size() > tamMay){
                mayor = l;
                tamMay = l.size();
            }
        }
        System.out.println(mayor);
    }
    
    private void nivelMasPoblado(Nodo n, List<T>[] l, int nivel){
        if (n != null){
            if (l[nivel] == null) {
                l[nivel] = new ArrayList<>();
            }
            l[nivel].add((T)n.getDato());
            nivelMasPoblado(n.getIzquierda(), l, nivel+1);
            nivelMasPoblado(n.getDerecha(), l, nivel+1);
        }
    }
    
    public ArrayList<T> toArray(){ //Retorna un ArrayList en vez de un Array ya que en java no se pueden hacer arreglos con tipos de dato genéricos
        int tot = (int)Math.pow(2, altura())-1;
        ArrayList<T> arr = new ArrayList(tot); //simular arreglo genérico
        for (int i = 0 ; i < tot ; i++ ){
            arr.add(null);
        }
        toArray(root, 0, arr);
        
        return arr;
    }
    
    private void toArray(Nodo n, int p, ArrayList<T> a){
        if (n != null){
            a.set(p ,(T)n.getDato());
            toArray(n.getIzquierda(), 2*p+1, a);
            toArray(n.getDerecha(), 2*p+2, a);
        }
    }
    
    public void parseArbol(ArrayList<T> a){
        root = parseArbol(0, a);
    }
    
    private Nodo parseArbol(int p, ArrayList<T> a){
        if (a.get(p) != null){
            Nodo n = new Nodo(a.get(p));
            n.setIzquierda(parseArbol(2*p + 1, a));
            n.setDerecha(parseArbol(p*2 +2, a));
            return n;
        }
        return null;
    }
    
    protected Nodo<T> root;
}
