package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class BaseUnit
  extends NamedUnit
  implements Externalizable
{
  static int base_count = 0;
  private static final String unitName = "(name)";
  String dimension;
  int index;
  
  public BaseUnit()
  {
    this.name = "(name)";
    this.index = Integer.MAX_VALUE;
    this.dims = Dimensions.Empty;
  }
  
  public BaseUnit(String paramString)
  {
    this.name = paramString;
    init();
  }
  
  public BaseUnit(String paramString1, String paramString2)
  {
    this.name = paramString1;
    this.dimension = paramString2;
    init();
  }
  
  public static int compare(BaseUnit paramBaseUnit1, BaseUnit paramBaseUnit2)
  {
    int i = paramBaseUnit1.name.compareTo(paramBaseUnit2.name);
    if (i != 0) {
      return i;
    }
    paramBaseUnit1 = paramBaseUnit1.dimension;
    paramBaseUnit2 = paramBaseUnit2.dimension;
    if (paramBaseUnit1 == paramBaseUnit2) {
      return 0;
    }
    if (paramBaseUnit1 == null) {
      return -1;
    }
    if (paramBaseUnit2 == null) {
      return 1;
    }
    return paramBaseUnit1.compareTo(paramBaseUnit2);
  }
  
  public static BaseUnit lookup(String paramString1, String paramString2)
  {
    String str = paramString1.intern();
    if ((str == "(name)") && (paramString2 == null))
    {
      paramString1 = Unit.Empty;
      return paramString1;
    }
    int i = str.hashCode();
    int j = table.length;
    for (NamedUnit localNamedUnit = table[((0x7FFFFFFF & i) % j)];; localNamedUnit = localNamedUnit.chain)
    {
      if (localNamedUnit == null) {
        break label92;
      }
      if ((localNamedUnit.name == str) && ((localNamedUnit instanceof BaseUnit)))
      {
        BaseUnit localBaseUnit = (BaseUnit)localNamedUnit;
        paramString1 = localBaseUnit;
        if (localBaseUnit.dimension == paramString2) {
          break;
        }
      }
    }
    label92:
    return null;
  }
  
  public static BaseUnit make(String paramString1, String paramString2)
  {
    BaseUnit localBaseUnit2 = lookup(paramString1, paramString2);
    BaseUnit localBaseUnit1 = localBaseUnit2;
    if (localBaseUnit2 == null) {
      localBaseUnit1 = new BaseUnit(paramString1, paramString2);
    }
    return localBaseUnit1;
  }
  
  public String getDimension()
  {
    return this.dimension;
  }
  
  public int hashCode()
  {
    return this.name.hashCode();
  }
  
  protected void init()
  {
    this.base = this;
    this.scale = 1.0D;
    this.dims = new Dimensions(this);
    super.init();
    int i = base_count;
    base_count = i + 1;
    this.index = i;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.name = paramObjectInput.readUTF();
    this.dimension = ((String)paramObjectInput.readObject());
  }
  
  public Object readResolve()
    throws ObjectStreamException
  {
    BaseUnit localBaseUnit = lookup(this.name, this.dimension);
    if (localBaseUnit != null) {
      return localBaseUnit;
    }
    init();
    return this;
  }
  
  public Unit unit()
  {
    return this;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeUTF(this.name);
    paramObjectOutput.writeObject(this.dimension);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\BaseUnit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */