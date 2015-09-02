package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.kawa.functions.GetNamedPart;
import gnu.mapping.Namespace;
import gnu.mapping.WrappedException;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Hashtable;

public class ClassNamespace
  extends Namespace
  implements Externalizable
{
  ClassType ctype;
  
  public ClassNamespace() {}
  
  public ClassNamespace(ClassType paramClassType)
  {
    setName("class:" + paramClassType.getName());
    this.ctype = paramClassType;
  }
  
  public static ClassNamespace getInstance(String paramString, ClassType paramClassType)
  {
    synchronized (nsTable)
    {
      Object localObject = nsTable.get(paramString);
      if ((localObject instanceof ClassNamespace))
      {
        paramString = (ClassNamespace)localObject;
        return paramString;
      }
      paramClassType = new ClassNamespace(paramClassType);
      nsTable.put(paramString, paramClassType);
      return paramClassType;
    }
  }
  
  public Object get(String paramString)
  {
    try
    {
      paramString = GetNamedPart.getTypePart(this.ctype, paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      throw WrappedException.wrapIfNeeded(paramString);
    }
  }
  
  public ClassType getClassType()
  {
    return this.ctype;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.ctype = ((ClassType)paramObjectInput.readObject());
    setName("class:" + this.ctype.getName());
  }
  
  public Object readResolve()
    throws ObjectStreamException
  {
    String str = getName();
    if (str != null)
    {
      Namespace localNamespace = (Namespace)nsTable.get(str);
      if ((localNamespace instanceof ClassNamespace)) {
        return localNamespace;
      }
      nsTable.put(str, this);
    }
    return this;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.ctype);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\ClassNamespace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */