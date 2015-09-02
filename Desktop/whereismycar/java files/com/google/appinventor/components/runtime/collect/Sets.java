package com.google.appinventor.components.runtime.collect;

import java.util.Collections;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class Sets
{
  public static <K> HashSet<K> newHashSet()
  {
    return new HashSet();
  }
  
  public static <E> HashSet<E> newHashSet(E... paramVarArgs)
  {
    HashSet localHashSet = new HashSet(paramVarArgs.length * 4 / 3 + 1);
    Collections.addAll(localHashSet, paramVarArgs);
    return localHashSet;
  }
  
  public static <E> SortedSet<E> newSortedSet(E... paramVarArgs)
  {
    TreeSet localTreeSet = new TreeSet();
    Collections.addAll(localTreeSet, paramVarArgs);
    return localTreeSet;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\collect\Sets.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */