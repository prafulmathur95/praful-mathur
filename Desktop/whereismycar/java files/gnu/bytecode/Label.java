package gnu.bytecode;

import java.util.ArrayList;
import java.util.Iterator;

public class Label
{
  int first_fixup;
  Type[] localTypes;
  boolean needsStackMapEntry;
  int position;
  Type[] stackTypes;
  private Object[] typeChangeListeners;
  
  public Label()
  {
    this(-1);
  }
  
  public Label(int paramInt)
  {
    this.position = paramInt;
  }
  
  public Label(CodeAttr paramCodeAttr)
  {
    this(-1);
  }
  
  private void mergeLocalType(int paramInt, Type paramType)
  {
    Type localType = this.localTypes[paramInt];
    paramType = mergeTypes(localType, paramType);
    this.localTypes[paramInt] = paramType;
    if (paramType != localType) {
      notifyTypeChangeListeners(paramInt, paramType);
    }
  }
  
  private void notifyTypeChangeListeners(int paramInt, Type paramType)
  {
    Object[] arrayOfObject = this.typeChangeListeners;
    if ((arrayOfObject == null) || (arrayOfObject.length <= paramInt)) {}
    Object localObject;
    do
    {
      return;
      localObject = arrayOfObject[paramInt];
    } while (localObject == null);
    if ((localObject instanceof Label)) {
      ((Label)localObject).mergeLocalType(paramInt, paramType);
    }
    while (paramType == null)
    {
      arrayOfObject[paramInt] = null;
      return;
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        ((Label)((Iterator)localObject).next()).mergeLocalType(paramInt, paramType);
      }
    }
  }
  
  void addTypeChangeListener(int paramInt, Label paramLabel)
  {
    Object localObject2 = this.typeChangeListeners;
    if (localObject2 == null)
    {
      localObject1 = new Object[paramInt + 10];
      this.typeChangeListeners = ((Object[])localObject1);
    }
    Object localObject3;
    for (;;)
    {
      localObject3 = localObject1[paramInt];
      if (localObject3 != null) {
        break;
      }
      localObject1[paramInt] = paramLabel;
      return;
      localObject1 = localObject2;
      if (localObject2.length <= paramInt)
      {
        localObject1 = new Object[paramInt + 10];
        System.arraycopy(this.typeChangeListeners, 0, localObject1, 0, this.typeChangeListeners.length);
        this.typeChangeListeners = ((Object[])localObject1);
      }
    }
    if ((localObject3 instanceof Label))
    {
      localObject2 = new ArrayList();
      ((ArrayList)localObject2).add((Label)localObject3);
      localObject1[paramInt] = localObject2;
    }
    for (Object localObject1 = localObject2;; localObject1 = (ArrayList)localObject3)
    {
      ((ArrayList)localObject1).add(paramLabel);
      return;
    }
  }
  
  void addTypeChangeListeners(CodeAttr paramCodeAttr)
  {
    if ((paramCodeAttr.local_types != null) && (paramCodeAttr.previousLabel != null))
    {
      int j = paramCodeAttr.local_types.length;
      int i = 0;
      while (i < j)
      {
        if ((paramCodeAttr.local_types[i] != null) && ((paramCodeAttr.varsSetInCurrentBlock == null) || (paramCodeAttr.varsSetInCurrentBlock.length <= i) || (paramCodeAttr.varsSetInCurrentBlock[i] == 0))) {
          paramCodeAttr.previousLabel.addTypeChangeListener(i, this);
        }
        i += 1;
      }
    }
  }
  
  public void define(CodeAttr paramCodeAttr)
  {
    if (paramCodeAttr.reachableHere()) {
      setTypes(paramCodeAttr);
    }
    while (this.localTypes == null)
    {
      paramCodeAttr.previousLabel = this;
      paramCodeAttr.varsSetInCurrentBlock = null;
      defineRaw(paramCodeAttr);
      if (this.localTypes != null) {
        paramCodeAttr.setTypes(this);
      }
      paramCodeAttr.setReachable(true);
      return;
    }
    int i = this.localTypes.length;
    for (;;)
    {
      int j = i - 1;
      if (j < 0) {
        break;
      }
      i = j;
      if (this.localTypes[j] != null) {
        if (paramCodeAttr.locals.used != null)
        {
          i = j;
          if (paramCodeAttr.locals.used[j] != null) {}
        }
        else
        {
          this.localTypes[j] = null;
          i = j;
        }
      }
    }
  }
  
  public void defineRaw(CodeAttr paramCodeAttr)
  {
    if (this.position >= 0) {
      throw new Error("label definition more than once");
    }
    this.position = paramCodeAttr.PC;
    this.first_fixup = paramCodeAttr.fixup_count;
    if (this.first_fixup >= 0) {
      paramCodeAttr.fixupAdd(1, this);
    }
  }
  
  public final boolean defined()
  {
    return this.position >= 0;
  }
  
  Type mergeTypes(Type paramType1, Type paramType2)
  {
    if (paramType1 instanceof PrimType != paramType2 instanceof PrimType) {
      return null;
    }
    return Type.lowestCommonSuperType(paramType1, paramType2);
  }
  
  public void setTypes(CodeAttr paramCodeAttr)
  {
    addTypeChangeListeners(paramCodeAttr);
    if ((this.stackTypes != null) && (paramCodeAttr.SP != this.stackTypes.length)) {
      throw new InternalError();
    }
    Type[] arrayOfType = paramCodeAttr.local_types;
    if (paramCodeAttr.local_types == null) {}
    for (int i = 0;; i = paramCodeAttr.local_types.length)
    {
      setTypes(arrayOfType, i, paramCodeAttr.stack_types, paramCodeAttr.SP);
      return;
    }
  }
  
  public void setTypes(Label paramLabel)
  {
    setTypes(paramLabel.localTypes, paramLabel.localTypes.length, paramLabel.stackTypes, paramLabel.stackTypes.length);
  }
  
  void setTypes(Type[] paramArrayOfType1, int paramInt1, Type[] paramArrayOfType2, int paramInt2)
  {
    if ((paramInt1 <= 0) || (paramArrayOfType1[(paramInt1 - 1)] != null))
    {
      if (this.stackTypes != null) {
        break label94;
      }
      if (paramInt2 != 0) {
        break label50;
      }
      this.stackTypes = Type.typeArray0;
      label31:
      if (paramInt1 != 0) {
        break label74;
      }
      this.localTypes = Type.typeArray0;
    }
    for (;;)
    {
      return;
      paramInt1 -= 1;
      break;
      label50:
      this.stackTypes = new Type[paramInt2];
      System.arraycopy(paramArrayOfType2, 0, this.stackTypes, 0, paramInt2);
      break label31;
      label74:
      this.localTypes = new Type[paramInt1];
      System.arraycopy(paramArrayOfType1, 0, this.localTypes, 0, paramInt1);
      return;
      label94:
      if (paramInt2 != this.stackTypes.length) {
        throw new InternalError("inconsistent stack length");
      }
      int i = 0;
      while (i < paramInt2)
      {
        this.stackTypes[i] = mergeTypes(this.stackTypes[i], paramArrayOfType2[i]);
        i += 1;
      }
      if (paramInt1 < this.localTypes.length) {}
      for (paramInt2 = paramInt1;; paramInt2 = this.localTypes.length)
      {
        i = 0;
        while (i < paramInt2)
        {
          mergeLocalType(i, paramArrayOfType1[i]);
          i += 1;
        }
      }
      while (paramInt1 < this.localTypes.length)
      {
        this.localTypes[paramInt1] = null;
        paramInt1 += 1;
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\Label.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */