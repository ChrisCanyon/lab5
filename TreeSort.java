import java.util.Iterator;
import bst.*;
import ki.KeyedItem;
import java.lang.reflect.Array;

public class TreeSort
{
   //convenience method
   public static KeyedItem[] treeSort(KeyedItem[] sort)
   {
     return treeSort(sort, sort.length);
   }

  public static KeyedItem[] treeSort(KeyedItem[] sort, int n){
    //easier to use a KeyedItem array than Comparable
    Class sortComponent = sort.getClass().getComponentType();
    KeyedItem[] sorted;
    try{
      sorted = (KeyedItem[])Array.newInstance(sortComponent, sort.length);
    } catch(Exception e){
      // oopsie poopsie
      System.out.println(e);
      System.out.println("returning null");
      return null;
    }

    if (n > sort.length) {
      n = sort.length;
    } else if (n < 1){
      n = 0;
    }

    //create a new Binary Search Tree
    //a balanced tree ensures logn inserts for nlogn sort
    BinarySearchTree tree = new BinarySearchTree(true, true);

    //need to use the Class class as the actual array type may be a subtype of KeyedItem
    // ??

    // fill up the search tree
    int i;
    for(i = 0; i < n; i++){
      tree.insert(sort[i]);
    }

    //pull sorted stuff out of the tree
    i = 0;
    TreeIterator iter = tree.iterator();
    iter.setInorder();
    while(iter.hasNext()){
      sorted[i] = (KeyedItem)iter.next();
      i++;
    }

    for(i = n; i < sort.length; i++){
      sorted[i] = sort[i];
    }

    return sorted;
   }
}
