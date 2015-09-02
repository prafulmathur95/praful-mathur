package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class ConstantValueAttr
  extends Attribute
{
  Object value;
  int value_index;
  
  public ConstantValueAttr(int paramInt)
  {
    super("ConstantValue");
    this.value_index = paramInt;
  }
  
  public ConstantValueAttr(Object paramObject)
  {
    super("ConstantValue");
    this.value = paramObject;
  }
  
  public void assignConstants(ClassType paramClassType)
  {
    super.assignConstants(paramClassType);
    ConstantPool localConstantPool;
    if (this.value_index == 0)
    {
      localConstantPool = paramClassType.getConstants();
      paramClassType = null;
      if (!(this.value instanceof String)) {
        break label50;
      }
      paramClassType = localConstantPool.addString((String)this.value);
    }
    for (;;)
    {
      this.value_index = paramClassType.getIndex();
      return;
      label50:
      if ((this.value instanceof Integer)) {
        paramClassType = localConstantPool.addInt(((Integer)this.value).intValue());
      } else if ((this.value instanceof Long)) {
        paramClassType = localConstantPool.addLong(((Long)this.value).longValue());
      } else if ((this.value instanceof Float)) {
        paramClassType = localConstantPool.addFloat(((Float)this.value).floatValue());
      } else if ((this.value instanceof Long)) {
        paramClassType = localConstantPool.addDouble(((Double)this.value).doubleValue());
      }
    }
  }
  
  public final int getLength()
  {
    return 2;
  }
  
  public Object getValue(ConstantPool paramConstantPool)
  {
    if (this.value != null) {
      return this.value;
    }
    paramConstantPool = paramConstantPool.getPoolEntry(this.value_index);
    switch (paramConstantPool.getTag())
    {
    }
    for (;;)
    {
      return this.value;
      this.value = ((CpoolString)paramConstantPool).getString().getString();
      continue;
      this.value = new Integer(((CpoolValue1)paramConstantPool).value);
      continue;
      this.value = new Long(((CpoolValue2)paramConstantPool).value);
      continue;
      this.value = new Float(Float.intBitsToFloat(((CpoolValue1)paramConstantPool).value));
      continue;
      this.value = new Double(Double.longBitsToDouble(((CpoolValue2)paramConstantPool).value));
    }
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter)
  {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", value: ");
    Object localObject;
    if (this.value_index == 0)
    {
      localObject = getValue(paramClassTypeWriter.ctype.constants);
      if ((localObject instanceof String)) {
        paramClassTypeWriter.printQuotedString((String)localObject);
      }
    }
    for (;;)
    {
      paramClassTypeWriter.println();
      return;
      paramClassTypeWriter.print(localObject);
      continue;
      paramClassTypeWriter.printOptionalIndex(this.value_index);
      paramClassTypeWriter.ctype.constants.getPoolEntry(this.value_index).print(paramClassTypeWriter, 1);
    }
  }
  
  public void write(DataOutputStream paramDataOutputStream)
    throws IOException
  {
    paramDataOutputStream.writeShort(this.value_index);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\ConstantValueAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */