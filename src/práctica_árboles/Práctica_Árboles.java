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
public class Práctica_Árboles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(3);
        bst.add(5);
        bst.add(1);
        bst.add(12);
        bst.add(0);
        System.out.println(bst.size());
    }
    
}
