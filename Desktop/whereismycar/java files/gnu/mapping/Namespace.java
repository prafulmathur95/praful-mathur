package gnu.mapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Hashtable;

public class Namespace
  implements Externalizable, HasNamedParts
{
  public static final Namespace EmptyNamespace = valueOf("");
  protected static final Hashtable nsTable = new Hashtable(50);
  int log2Size;
  private int mask;
  String name;
  int num_bindings;
  protected String prefix = "";
  protected SymbolRef[] table;
  
  protected Namespace()
  {
    this(64);
  }
  
  protected Namespace(int paramInt)
  {
    for (this.log2Size = 4; paramInt > 1 << this.log2Size; this.log2Size += 1) {}
    paramInt = 1 << this.log2Size;
    this.table = new SymbolRef[paramInt];
    this.mask = (paramInt - 1);
  }
  
  public static Namespace create()
  {
    return new Namespace(64);
  }
  
  public static Namespace create(int paramInt)
  {
    return new Namespace(paramInt);
  }
  
  public static Namespace getDefault()
  {
    return EmptyNamespace;
  }
  
  public static Symbol getDefaultSymbol(String paramString)
  {
    return EmptyNamespace.getSymbol(paramString);
  }
  
  public static Namespace makeUnknownNamespace(String paramString)
  {
    if ((paramString == null) || (paramString == "")) {}
    for (String str = "";; str = "http://kawa.gnu.org/unknown-namespace/" + paramString) {
      return valueOf(str, paramString);
    }
  }
  
  public static Namespace valueOf()
  {
    return EmptyNamespace;
  }
  
  public static Namespace valueOf(String arg0)
  {
    String str = ???;
    if (??? == null) {
      str = "";
    }
    synchronized (nsTable)
    {
      Namespace localNamespace = (Namespace)nsTable.get(str);
      if (localNamespace != null) {
        return localNamespace;
      }
      localNamespace = new Namespace();
      localNamespace.setName(str.intern());
      nsTable.put(str, localNamespace);
      return localNamespace;
    }
  }
  
  public static Namespace valueOf(String paramString, SimpleSymbol paramSimpleSymbol)
  {
    if (paramSimpleSymbol == null) {}
    for (paramSimpleSymbol = null;; paramSimpleSymbol = paramSimpleSymbol.getName()) {
      return valueOf(paramString, paramSimpleSymbol);
    }
  }
  
  public static Namespace valueOf(String paramString1, String paramString2)
  {
    if ((paramString2 == null) || (paramString2.length() == 0)) {
      return valueOf(paramString1);
    }
    String str = paramString2 + " -> " + paramString1;
    synchronized (nsTable)
    {
      localObject = nsTable.get(str);
      if ((localObject instanceof Namespace))
      {
        paramString1 = (Namespace)localObject;
        return paramString1;
      }
    }
    Object localObject = new Namespace();
    ((Namespace)localObject).setName(paramString1.intern());
    ((Namespace)localObject).prefix = paramString2.intern();
    nsTable.put(str, localObject);
    return (Namespace)localObject;
  }
  
  public Symbol add(Symbol paramSymbol, int paramInt)
  {
    paramInt &= this.mask;
    SymbolRef localSymbolRef = new SymbolRef(paramSymbol, this);
    paramSymbol.namespace = this;
    localSymbolRef.next = this.table[paramInt];
    this.table[paramInt] = localSymbolRef;
    this.num_bindings += 1;
    if (this.num_bindings >= this.table.length) {
      rehash();
    }
    return paramSymbol;
  }
  
  public Object get(String paramString)
  {
    return Environment.getCurrent().get(getSymbol(paramString));
  }
  
  public final String getName()
  {
    return this.name;
  }
  
  public final String getPrefix()
  {
    return this.prefix;
  }
  
  public Symbol getSymbol(String paramString)
  {
    return lookup(paramString, paramString.hashCode(), true);
  }
  
  public boolean isConstant(String paramString)
  {
    return false;
  }
  
  public Symbol lookup(String paramString)
  {
    return lookup(paramString, paramString.hashCode(), false);
  }
  
  public Symbol lookup(String paramString, int paramInt, boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        Symbol localSymbol = lookupInternal(paramString, paramInt);
        if (localSymbol != null) {
          return localSymbol;
        }
        if (!paramBoolean) {
          break;
        }
        if (this == EmptyNamespace)
        {
          paramString = new SimpleSymbol(paramString);
          paramString = add(paramString, paramInt);
          return paramString;
        }
      }
      finally {}
      paramString = new Symbol(this, paramString);
    }
    return null;
  }
  
  protected final Symbol lookupInternal(String paramString, int paramInt)
  {
    paramInt &= this.mask;
    Object localObject2 = null;
    Object localObject1 = this.table[paramInt];
    if (localObject1 != null)
    {
      SymbolRef localSymbolRef = ((SymbolRef)localObject1).next;
      Symbol localSymbol = ((SymbolRef)localObject1).getSymbol();
      if (localSymbol == null) {
        if (localObject2 == null)
        {
          this.table[paramInt] = localSymbolRef;
          label51:
          this.num_bindings -= 1;
        }
      }
      for (;;)
      {
        localObject1 = localSymbolRef;
        break;
        ((SymbolRef)localObject2).next = localSymbolRef;
        break label51;
        if (localSymbol.getLocalPart().equals(paramString)) {
          return localSymbol;
        }
        localObject2 = localObject1;
      }
    }
    return null;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.name = ((String)paramObjectInput.readObject()).intern();
    this.prefix = ((String)paramObjectInput.readObject());
  }
  
  public Object readResolve()
    throws ObjectStreamException
  {
    String str = getName();
    if (str != null)
    {
      if ((this.prefix == null) || (this.prefix.length() == 0)) {}
      for (;;)
      {
        Namespace localNamespace = (Namespace)nsTable.get(str);
        if (localNamespace == null) {
          break;
        }
        return localNamespace;
        str = this.prefix + " -> " + str;
      }
      nsTable.put(str, this);
    }
    return this;
  }
  
  protected void rehash()
  {
    int j = this.table.length;
    int i = j * 2;
    int n = i - 1;
    int k = 0;
    SymbolRef[] arrayOfSymbolRef1 = this.table;
    SymbolRef[] arrayOfSymbolRef2 = new SymbolRef[i];
    int m = j - 1;
    if (m >= 0)
    {
      Object localObject = arrayOfSymbolRef1[m];
      for (i = k;; i = j)
      {
        k = i;
        j = m;
        if (localObject == null) {
          break;
        }
        SymbolRef localSymbolRef = ((SymbolRef)localObject).next;
        Symbol localSymbol = ((SymbolRef)localObject).getSymbol();
        j = i;
        if (localSymbol != null)
        {
          k = localSymbol.getName().hashCode() & n;
          j = i + 1;
          ((SymbolRef)localObject).next = arrayOfSymbolRef2[k];
          arrayOfSymbolRef2[k] = localObject;
        }
        localObject = localSymbolRef;
      }
    }
    this.table = arrayOfSymbolRef2;
    this.log2Size += 1;
    this.mask = n;
    this.num_bindings = k;
  }
  
  public boolean remove(Symbol paramSymbol)
  {
    for (;;)
    {
      SymbolRef localSymbolRef;
      try
      {
        int i = paramSymbol.getLocalPart().hashCode() & this.mask;
        Object localObject2 = null;
        localObject1 = this.table[i];
        if (localObject1 == null) {
          break;
        }
        localSymbolRef = ((SymbolRef)localObject1).next;
        Symbol localSymbol = ((SymbolRef)localObject1).getSymbol();
        if ((localSymbol == null) || (localSymbol == paramSymbol))
        {
          if (localObject2 == null)
          {
            this.table[i] = localSymbolRef;
            this.num_bindings -= 1;
            if (localSymbol != null) {
              return true;
            }
          }
          else
          {
            ((SymbolRef)localObject2).next = localSymbolRef;
            continue;
          }
        }
        else {
          localObject2 = localObject1;
        }
      }
      finally {}
      Object localObject1 = localSymbolRef;
    }
    return false;
  }
  
  public final void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("#,(namespace \"");
    localStringBuilder.append(this.name);
    localStringBuilder.append('"');
    if ((this.prefix != null) && (this.prefix != ""))
    {
      localStringBuilder.append(' ');
      localStringBuilder.append(this.prefix);
    }
    localStringBuilder.append(')');
    return localStringBuilder.toString();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(getName());
    paramObjectOutput.writeObject(this.prefix);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\Namespace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */