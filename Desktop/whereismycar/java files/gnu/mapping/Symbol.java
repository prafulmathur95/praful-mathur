package gnu.mapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Symbol
  implements EnvironmentKey, Comparable, Externalizable
{
  public static final Symbol FUNCTION = makeUninterned("(function)");
  public static final Symbol PLIST = makeUninterned("(property-list)");
  protected String name;
  Namespace namespace;
  
  public Symbol() {}
  
  public Symbol(Namespace paramNamespace, String paramString)
  {
    this.name = paramString;
    this.namespace = paramNamespace;
  }
  
  public static boolean equals(Symbol paramSymbol1, Symbol paramSymbol2)
  {
    if (paramSymbol1 == paramSymbol2) {}
    do
    {
      return true;
      if ((paramSymbol1 == null) || (paramSymbol2 == null)) {
        return false;
      }
      if (paramSymbol1.name != paramSymbol2.name) {
        break;
      }
      paramSymbol1 = paramSymbol1.namespace;
      paramSymbol2 = paramSymbol2.namespace;
      if ((paramSymbol1 == null) || (paramSymbol2 == null)) {
        break;
      }
    } while (paramSymbol1.name == paramSymbol2.name);
    return false;
    return false;
  }
  
  public static Symbol make(Object paramObject, String paramString)
  {
    if ((paramObject instanceof String)) {}
    for (paramObject = Namespace.valueOf((String)paramObject); (paramObject == null) || (paramString == null); paramObject = (Namespace)paramObject) {
      return makeUninterned(paramString);
    }
    return ((Namespace)paramObject).getSymbol(paramString.intern());
  }
  
  public static Symbol make(String paramString1, String paramString2, String paramString3)
  {
    return Namespace.valueOf(paramString1, paramString3).getSymbol(paramString2.intern());
  }
  
  public static Symbol makeUninterned(String paramString)
  {
    return new Symbol(null, paramString);
  }
  
  public static Symbol makeWithUnknownNamespace(String paramString1, String paramString2)
  {
    return Namespace.makeUnknownNamespace(paramString2).getSymbol(paramString1.intern());
  }
  
  public static Symbol parse(String paramString)
  {
    int i6 = paramString.length();
    int k = -1;
    int i4 = -1;
    int i1 = 0;
    int i5 = 0;
    int m = 0;
    int i = 0;
    int i2 = k;
    int n = i5;
    int j = m;
    int i3 = i4;
    label80:
    String str2;
    if (i < i6)
    {
      i3 = paramString.charAt(i);
      if ((i3 == 58) && (i1 == 0))
      {
        j = i;
        n = i + 1;
        i3 = i4;
        i2 = k;
      }
    }
    else
    {
      if ((i2 < 0) || (i3 <= 0)) {
        break label280;
      }
      str2 = paramString.substring(i2 + 1, i3);
      if (j <= 0) {
        break label275;
      }
    }
    label275:
    for (String str1 = paramString.substring(0, j);; str1 = null)
    {
      return valueOf(paramString.substring(n), str2, str1);
      i2 = i1;
      n = k;
      j = m;
      if (i3 == 123)
      {
        n = k;
        j = m;
        if (k < 0)
        {
          j = i;
          n = i;
        }
        i2 = i1 + 1;
      }
      i1 = i2;
      if (i3 == 125)
      {
        k = i2 - 1;
        if (k == 0)
        {
          i3 = i;
          if ((i < i6) && (paramString.charAt(i + 1) == ':')) {
            i += 2;
          }
          for (;;)
          {
            i2 = n;
            n = i;
            break;
            i += 1;
          }
        }
        i1 = k;
        if (k < 0)
        {
          i = j;
          i2 = n;
          n = i;
          i3 = i4;
          break label80;
        }
      }
      i += 1;
      k = n;
      m = j;
      break;
    }
    label280:
    if (j > 0) {
      return makeWithUnknownNamespace(paramString.substring(n), paramString.substring(0, j));
    }
    return valueOf(paramString);
  }
  
  public static SimpleSymbol valueOf(String paramString)
  {
    return (SimpleSymbol)Namespace.EmptyNamespace.getSymbol(paramString.intern());
  }
  
  public static Symbol valueOf(String paramString, Object paramObject)
  {
    if ((paramObject == null) || (paramObject == Boolean.FALSE)) {
      return makeUninterned(paramString);
    }
    if ((paramObject instanceof Namespace)) {
      paramObject = (Namespace)paramObject;
    }
    for (;;)
    {
      return ((Namespace)paramObject).getSymbol(paramString.intern());
      if (paramObject == Boolean.TRUE) {
        paramObject = Namespace.EmptyNamespace;
      } else {
        paramObject = Namespace.valueOf(((CharSequence)paramObject).toString());
      }
    }
  }
  
  public static Symbol valueOf(String paramString1, String paramString2, String paramString3)
  {
    return Namespace.valueOf(paramString2, paramString3).getSymbol(paramString1.intern());
  }
  
  public int compareTo(Object paramObject)
  {
    paramObject = (Symbol)paramObject;
    if (getNamespaceURI() != ((Symbol)paramObject).getNamespaceURI()) {
      throw new IllegalArgumentException("comparing Symbols in different namespaces");
    }
    return getLocalName().compareTo(((Symbol)paramObject).getLocalName());
  }
  
  public final boolean equals(Object paramObject)
  {
    return ((paramObject instanceof Symbol)) && (equals(this, (Symbol)paramObject));
  }
  
  public final Object getKeyProperty()
  {
    return null;
  }
  
  public final Symbol getKeySymbol()
  {
    return this;
  }
  
  public final String getLocalName()
  {
    return this.name;
  }
  
  public final String getLocalPart()
  {
    return this.name;
  }
  
  public final String getName()
  {
    return this.name;
  }
  
  public final Namespace getNamespace()
  {
    return this.namespace;
  }
  
  public final String getNamespaceURI()
  {
    Namespace localNamespace = getNamespace();
    if (localNamespace == null) {
      return null;
    }
    return localNamespace.getName();
  }
  
  public final String getPrefix()
  {
    Namespace localNamespace = this.namespace;
    if (localNamespace == null) {
      return "";
    }
    return localNamespace.prefix;
  }
  
  public final boolean hasEmptyNamespace()
  {
    Object localObject = getNamespace();
    if (localObject != null) {
      localObject = ((Namespace)localObject).getName();
    }
    return (localObject == null) || (((String)localObject).length() == 0);
  }
  
  public int hashCode()
  {
    if (this.name == null) {
      return 0;
    }
    return this.name.hashCode();
  }
  
  public boolean matches(EnvironmentKey paramEnvironmentKey)
  {
    return (equals(paramEnvironmentKey.getKeySymbol(), this)) && (paramEnvironmentKey.getKeyProperty() == null);
  }
  
  public boolean matches(Symbol paramSymbol, Object paramObject)
  {
    return (equals(paramSymbol, this)) && (paramObject == null);
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.namespace = ((Namespace)paramObjectInput.readObject());
    this.name = ((String)paramObjectInput.readObject());
  }
  
  public Object readResolve()
    throws ObjectStreamException
  {
    if (this.namespace == null) {
      return this;
    }
    return make(this.namespace, getName());
  }
  
  public final void setNamespace(Namespace paramNamespace)
  {
    this.namespace = paramNamespace;
  }
  
  public String toString()
  {
    return toString('P');
  }
  
  public String toString(char paramChar)
  {
    int j = 1;
    Object localObject = getNamespaceURI();
    String str2 = getPrefix();
    int i;
    if ((localObject != null) && (((String)localObject).length() > 0))
    {
      i = 1;
      if ((str2 == null) || (str2.length() <= 0)) {
        break label154;
      }
    }
    for (;;)
    {
      String str1 = getName();
      if (i == 0)
      {
        localObject = str1;
        if (j == 0) {}
      }
      else
      {
        localObject = new StringBuilder();
        if ((j != 0) && ((paramChar != 'U') || (i == 0))) {
          ((StringBuilder)localObject).append(str2);
        }
        if ((i != 0) && ((paramChar != 'P') || (j == 0)))
        {
          ((StringBuilder)localObject).append('{');
          ((StringBuilder)localObject).append(getNamespaceURI());
          ((StringBuilder)localObject).append('}');
        }
        ((StringBuilder)localObject).append(':');
        ((StringBuilder)localObject).append(str1);
        localObject = ((StringBuilder)localObject).toString();
      }
      return (String)localObject;
      i = 0;
      break;
      label154:
      j = 0;
    }
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(getNamespace());
    paramObjectOutput.writeObject(getName());
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\mapping\Symbol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */