package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;

public class RecordConstructor
  extends ProcedureN
{
  gnu.bytecode.Field[] fields;
  ClassType type;
  
  public RecordConstructor(ClassType paramClassType)
  {
    init(paramClassType);
  }
  
  public RecordConstructor(ClassType paramClassType, Object paramObject)
  {
    this.type = paramClassType;
    if (paramObject == null)
    {
      init(paramClassType);
      return;
    }
    int j = LList.listLength(paramObject, false);
    this.fields = new gnu.bytecode.Field[j];
    gnu.bytecode.Field localField = paramClassType.getFields();
    int i = 0;
    label43:
    Pair localPair;
    String str;
    if (i < j)
    {
      localPair = (Pair)paramObject;
      str = localPair.getCar().toString();
    }
    for (paramObject = localField;; paramObject = ((gnu.bytecode.Field)paramObject).getNext())
    {
      if (paramObject == null) {
        throw new RuntimeException("no such field " + str + " in " + paramClassType.getName());
      }
      if (((gnu.bytecode.Field)paramObject).getSourceName() == str)
      {
        this.fields[i] = paramObject;
        paramObject = localPair.getCdr();
        i += 1;
        break label43;
        break;
      }
    }
  }
  
  public RecordConstructor(ClassType paramClassType, gnu.bytecode.Field[] paramArrayOfField)
  {
    this.type = paramClassType;
    this.fields = paramArrayOfField;
  }
  
  public RecordConstructor(Class paramClass)
  {
    init((ClassType)Type.make(paramClass));
  }
  
  public RecordConstructor(Class paramClass, Object paramObject)
  {
    this((ClassType)Type.make(paramClass), paramObject);
  }
  
  public RecordConstructor(Class paramClass, gnu.bytecode.Field[] paramArrayOfField)
  {
    this((ClassType)Type.make(paramClass), paramArrayOfField);
  }
  
  private void init(ClassType paramClassType)
  {
    this.type = paramClassType;
    paramClassType = paramClassType.getFields();
    int i = 0;
    Object localObject = paramClassType;
    int j;
    while (localObject != null)
    {
      j = i;
      if ((((gnu.bytecode.Field)localObject).getModifiers() & 0x9) == 1) {
        j = i + 1;
      }
      localObject = ((gnu.bytecode.Field)localObject).getNext();
      i = j;
    }
    this.fields = new gnu.bytecode.Field[i];
    i = 0;
    if (paramClassType != null)
    {
      if ((paramClassType.getModifiers() & 0x9) != 1) {
        break label99;
      }
      localObject = this.fields;
      j = i + 1;
      localObject[i] = paramClassType;
      i = j;
    }
    label99:
    for (;;)
    {
      paramClassType = paramClassType.getNext();
      break;
      return;
    }
  }
  
  public Object applyN(Object[] paramArrayOfObject)
  {
    Object localObject;
    try
    {
      localObject = this.type.getReflectClass().newInstance();
      if (paramArrayOfObject.length != this.fields.length) {
        throw new WrongArguments(this, paramArrayOfObject.length);
      }
    }
    catch (InstantiationException paramArrayOfObject)
    {
      throw new GenericError(paramArrayOfObject.toString());
    }
    catch (IllegalAccessException paramArrayOfObject)
    {
      throw new GenericError(paramArrayOfObject.toString());
    }
    int i = 0;
    while (i < paramArrayOfObject.length)
    {
      gnu.bytecode.Field localField = this.fields[i];
      try
      {
        localField.getReflectField().set(localObject, paramArrayOfObject[i]);
        i += 1;
      }
      catch (Exception paramArrayOfObject)
      {
        throw new WrappedException("illegal access for field " + localField.getName(), paramArrayOfObject);
      }
    }
    return localObject;
  }
  
  public String getName()
  {
    return this.type.getName() + " constructor";
  }
  
  public int numArgs()
  {
    int i = this.fields.length;
    return i << 12 | i;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\RecordConstructor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */