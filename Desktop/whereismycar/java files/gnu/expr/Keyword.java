package gnu.expr;

import gnu.lists.Consumer;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Keyword
  extends Symbol
  implements Printable, Externalizable
{
  public static final Namespace keywordNamespace = ;
  
  static
  {
    keywordNamespace.setName("(keywords)");
  }
  
  public Keyword() {}
  
  public Keyword(Namespace paramNamespace, String paramString)
  {
    super(paramNamespace, paramString);
  }
  
  private Keyword(String paramString)
  {
    super(keywordNamespace, paramString);
  }
  
  public static boolean isKeyword(Object paramObject)
  {
    return paramObject instanceof Keyword;
  }
  
  public static Keyword make(String paramString)
  {
    int i = paramString.hashCode();
    Keyword localKeyword2 = (Keyword)keywordNamespace.lookup(paramString, i, false);
    Keyword localKeyword1 = localKeyword2;
    if (localKeyword2 == null)
    {
      localKeyword1 = new Keyword(paramString);
      keywordNamespace.add(localKeyword1, i);
    }
    return localKeyword1;
  }
  
  public static Object searchForKeyword(Object[] paramArrayOfObject, int paramInt, Object paramObject)
  {
    while (paramInt < paramArrayOfObject.length)
    {
      if (paramArrayOfObject[paramInt] == paramObject) {
        return paramArrayOfObject[(paramInt + 1)];
      }
      paramInt += 2;
    }
    return Special.dfault;
  }
  
  public static Object searchForKeyword(Object[] paramArrayOfObject, int paramInt, Object paramObject1, Object paramObject2)
  {
    for (;;)
    {
      Object localObject = paramObject2;
      if (paramInt < paramArrayOfObject.length)
      {
        if (paramArrayOfObject[paramInt] == paramObject1) {
          localObject = paramArrayOfObject[(paramInt + 1)];
        }
      }
      else {
        return localObject;
      }
      paramInt += 2;
    }
  }
  
  public Symbol asSymbol()
  {
    return Namespace.EmptyNamespace.getSymbol(getName());
  }
  
  public void print(Consumer paramConsumer)
  {
    Symbols.print(getName(), paramConsumer);
    paramConsumer.write(58);
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.name = ((String)paramObjectInput.readObject());
  }
  
  public Object readResolve()
    throws ObjectStreamException
  {
    return make(keywordNamespace, getName());
  }
  
  public final String toString()
  {
    return getName() + ':';
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(getName());
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\Keyword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */