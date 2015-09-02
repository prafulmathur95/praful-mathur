package gnu.bytecode;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.List;

public class ArrayType
  extends ObjectType
  implements Externalizable
{
  public Type elements;
  
  public ArrayType(Type paramType)
  {
    this(paramType, paramType.getName() + "[]");
  }
  
  ArrayType(Type paramType, String paramString)
  {
    this.this_name = paramString;
    this.elements = paramType;
  }
  
  public static ArrayType make(Type paramType)
  {
    ArrayType localArrayType2 = paramType.array_type;
    ArrayType localArrayType1 = localArrayType2;
    if (localArrayType2 == null)
    {
      localArrayType1 = new ArrayType(paramType, paramType.getName() + "[]");
      paramType.array_type = localArrayType1;
    }
    return localArrayType1;
  }
  
  static ArrayType make(String paramString)
  {
    Type localType = Type.getType(paramString.substring(0, paramString.length() - 2));
    ArrayType localArrayType2 = localType.array_type;
    ArrayType localArrayType1 = localArrayType2;
    if (localArrayType2 == null)
    {
      localArrayType1 = new ArrayType(localType, paramString);
      localType.array_type = localArrayType1;
    }
    return localArrayType1;
  }
  
  public int compare(Type paramType)
  {
    if (paramType == nullType) {
      return 1;
    }
    if ((paramType instanceof ArrayType)) {
      return this.elements.compare(((ArrayType)paramType).elements);
    }
    if ((paramType.getName().equals("java.lang.Object")) || (paramType == toStringType)) {
      return -1;
    }
    return -3;
  }
  
  public Type getComponentType()
  {
    return this.elements;
  }
  
  public Type getImplementationType()
  {
    Type localType = this.elements.getImplementationType();
    if (this.elements == localType) {
      return this;
    }
    return make(localType);
  }
  
  public String getInternalName()
  {
    return getSignature();
  }
  
  public int getMethods(Filter paramFilter, int paramInt, List<Method> paramList)
  {
    int i = 0;
    if (paramInt > 0)
    {
      int j = Type.objectType.getMethods(paramFilter, 0, paramList);
      i = j;
      if (paramInt > 1)
      {
        i = j;
        if (paramFilter.select(Type.clone_method))
        {
          if (paramList != null) {
            paramList.add(Type.clone_method);
          }
          i = j + 1;
        }
      }
    }
    return i;
  }
  
  public Class getReflectClass()
  {
    try
    {
      if (this.reflectClass == null) {
        this.reflectClass = Class.forName(getInternalName().replace('/', '.'), false, this.elements.getReflectClass().getClassLoader());
      }
      this.flags |= 0x10;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      while ((this.flags & 0x10) == 0) {}
      RuntimeException localRuntimeException = new RuntimeException("no such array class: " + getName());
      localRuntimeException.initCause(localClassNotFoundException);
      throw localRuntimeException;
    }
    return this.reflectClass;
  }
  
  public String getSignature()
  {
    if (this.signature == null) {
      setSignature("[" + this.elements.getSignature());
    }
    return this.signature;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.elements = ((Type)paramObjectInput.readObject());
  }
  
  public Object readResolve()
    throws ObjectStreamException
  {
    ArrayType localArrayType = this.elements.array_type;
    if (localArrayType != null) {
      return localArrayType;
    }
    this.elements.array_type = this;
    return this;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.elements);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\ArrayType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */