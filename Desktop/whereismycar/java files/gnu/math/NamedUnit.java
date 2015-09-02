package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class NamedUnit
  extends Unit
  implements Externalizable
{
  Unit base;
  NamedUnit chain;
  String name;
  double scale;
  
  public NamedUnit() {}
  
  public NamedUnit(String paramString, double paramDouble, Unit paramUnit)
  {
    this.name = paramString;
    this.base = paramUnit;
    this.scale = paramDouble;
    init();
  }
  
  public NamedUnit(String paramString, DQuantity paramDQuantity)
  {
    this.name = paramString.intern();
    this.scale = paramDQuantity.factor;
    this.base = paramDQuantity.unt;
    init();
  }
  
  public static NamedUnit lookup(String paramString)
  {
    String str = paramString.intern();
    int i = str.hashCode();
    int j = table.length;
    for (paramString = table[((0x7FFFFFFF & i) % j)]; paramString != null; paramString = paramString.chain) {
      if (paramString.name == str) {
        return paramString;
      }
    }
    return null;
  }
  
  public static NamedUnit lookup(String paramString, double paramDouble, Unit paramUnit)
  {
    String str = paramString.intern();
    int i = str.hashCode();
    int j = table.length;
    for (paramString = table[((0x7FFFFFFF & i) % j)]; paramString != null; paramString = paramString.chain) {
      if ((paramString.name == str) && (paramString.scale == paramDouble) && (paramString.base == paramUnit)) {
        return paramString;
      }
    }
    return null;
  }
  
  public static NamedUnit make(String paramString, double paramDouble, Unit paramUnit)
  {
    NamedUnit localNamedUnit2 = lookup(paramString, paramDouble, paramUnit);
    NamedUnit localNamedUnit1 = localNamedUnit2;
    if (localNamedUnit2 == null) {
      localNamedUnit1 = new NamedUnit(paramString, paramDouble, paramUnit);
    }
    return localNamedUnit1;
  }
  
  public static NamedUnit make(String paramString, Quantity paramQuantity)
  {
    if ((paramQuantity instanceof DQuantity)) {}
    for (double d = ((DQuantity)paramQuantity).factor;; d = paramQuantity.re().doubleValue())
    {
      Unit localUnit = paramQuantity.unit();
      NamedUnit localNamedUnit = lookup(paramString, d, localUnit);
      paramQuantity = localNamedUnit;
      if (localNamedUnit == null) {
        paramQuantity = new NamedUnit(paramString, d, localUnit);
      }
      return paramQuantity;
      if (paramQuantity.imValue() != 0.0D) {
        throw new ArithmeticException("defining " + paramString + " using complex value");
      }
    }
  }
  
  public String getName()
  {
    return this.name;
  }
  
  protected void init()
  {
    this.factor = (this.scale * this.base.factor);
    this.dims = this.base.dims;
    this.name = this.name.intern();
    int i = (0x7FFFFFFF & this.name.hashCode()) % table.length;
    this.chain = table[i];
    table[i] = this;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.name = paramObjectInput.readUTF();
    this.scale = paramObjectInput.readDouble();
    this.base = ((Unit)paramObjectInput.readObject());
  }
  
  public Object readResolve()
    throws ObjectStreamException
  {
    NamedUnit localNamedUnit = lookup(this.name, this.scale, this.base);
    if (localNamedUnit != null) {
      return localNamedUnit;
    }
    init();
    return this;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeUTF(this.name);
    paramObjectOutput.writeDouble(this.scale);
    paramObjectOutput.writeObject(this.base);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\math\NamedUnit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */