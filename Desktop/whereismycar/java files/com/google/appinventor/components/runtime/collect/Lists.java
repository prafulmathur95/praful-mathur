package com.google.appinventor.components.runtime.collect;

import java.util.ArrayList;
import java.util.Collections;

public class Lists
{
  public static <E> ArrayList<E> newArrayList()
  {
    return new ArrayList();
  }
  
  public static <E> ArrayList<E> newArrayList(E... paramVarArgs)
  {
    ArrayList localArrayList = new ArrayList(paramVarArgs.length * 110 / 100 + 5);
    Collections.addAll(localArrayList, paramVarArgs);
    return localArrayList;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\collect\Lists.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */