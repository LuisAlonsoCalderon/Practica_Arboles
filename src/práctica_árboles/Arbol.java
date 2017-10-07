/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package práctica_árboles;

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
        int tot = (int)Math.pow(2, altura()+1)-1;
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
    
    public void arrayToArbol(ArrayList<T> a){
        root = arrayToArbol(0, a);
    }
    
    private Nodo arrayToArbol(int p, ArrayList<T> a){
        if (a.get(p) != null){
            Nodo n = new Nodo(a.get(p));
            n.setIzquierda(arrayToArbol(2*p + 1, a));
            n.setDerecha(arrayToArbol(p*2 +2, a));
            return n;
        }
        return null;
    }
    
    public T minimo(){
        return minimo(root);
    }
    
    protected T minimo(Nodo n){
        if (n == null){
            return null;
        } else {
            T a = minimo(n.getIzquierda());
            T b = minimo(n.getDerecha());
            T min = (T)n.getDato();
            min = (a != null) ? a.compareTo(min) < 0 ? a : min : min;
            min = (a != null) ? b.compareTo(min) < 0 ? b : min : min;
            return min;
        }
    }
    
        
    public void remove(T dato){
        root = remove(dato, root);
    }
    
    private Nodo remove(T dato, Nodo n){
        if (n == null) {
            throw new IllegalArgumentException("El elemento no existe");
        }
        if (n.getDato().equals(dato)){
            if (n.getIzquierda() == null && n.getDerecha() == null){
                return null;
            } else if (n.getIzquierda() != null && n.getDerecha() == null){
                return n.getIzquierda();
            } else if (n.getIzquierda() == null && n.getDerecha() != null){
                return n.getDerecha();
            } else {//el caso donde hay que eliminar pero hay 2 hijos
                T min = (T)minimo(n.getDerecha());
                n.setDato(min);
                n.setDerecha(remove(min, n.getDerecha()));
                return n;
            }
        } else {
            if (dato.compareTo(n.getDato()) < 0){
                n.setIzquierda(remove(dato, n.getIzquierda()));
            } else {
                n.setDerecha(remove(dato, n.getDerecha()));
            }
            return n;
        }
    }
    
    protected Nodo<T> root;
}
