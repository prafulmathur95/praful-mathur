package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class Field
  extends Location
  implements AttrContainer, Member
{
  Attribute attributes;
  int flags;
  Field next;
  ClassType owner;
  java.lang.reflect.Field rfield;
  String sourceName;
  
  public Field(ClassType paramClassType)
  {
    if (paramClassType.last_field == null) {
      paramClassType.fields = this;
    }
    for (;;)
    {
      paramClassType.last_field = this;
      paramClassType.fields_count += 1;
      this.owner = paramClassType;
      return;
      paramClassType.last_field.next = this;
    }
  }
  
  public static Field searchField(Field paramField, String paramString)
  {
    while (paramField != null)
    {
      if (paramField.getSourceName() == paramString) {
        return paramField;
      }
      paramField = paramField.next;
    }
    return null;
  }
  
  void assign_constants(ClassType paramClassType)
  {
    ConstantPool localConstantPool = paramClassType.constants;
    if ((this.name_index == 0) && (this.name != null)) {
      this.name_index = localConstantPool.addUtf8(this.name).index;
    }
    if ((this.signature_index == 0) && (this.type != null)) {
      this.signature_index = localConstantPool.addUtf8(this.type.getSignature()).index;
    }
    Attribute.assignConstants(this, paramClassType);
  }
  
  public final Attribute getAttributes()
  {
    return this.attributes;
  }
  
  public final ClassType getDeclaringClass()
  {
    return this.owner;
  }
  
  public final int getFlags()
  {
    return this.flags;
  }
  
  public final int getModifiers()
  {
    return this.flags;
  }
  
  public final Field getNext()
  {
    return this.next;
  }
  
  public java.lang.reflect.Field getReflectField()
    throws NoSuchFieldException
  {
    try
    {
      if (this.rfield == null) {
        this.rfield = this.owner.getReflectClass().getDeclaredField(getName());
      }
      java.lang.reflect.Field localField = this.rfield;
      return localField;
    }
    finally {}
  }
  
  public String getSourceName()
  {
    if (this.sourceName == null) {
      this.sourceName = getName().intern();
    }
    return this.sourceName;
  }
  
  public final boolean getStaticFlag()
  {
    return (this.flags & 0x8) != 0;
  }
  
  public final void setAttributes(Attribute paramAttribute)
  {
    this.attributes = paramAttribute;
  }
  
  public final void setConstantValue(Object paramObject, ClassType paramClassType)
  {
    int i = 0;
    ConstantPool localConstantPool2 = paramClassType.constants;
    ConstantPool localConstantPool1 = localConstantPool2;
    if (localConstantPool2 == null)
    {
      localConstantPool1 = new ConstantPool();
      paramClassType.constants = localConstantPool1;
    }
    switch (getType().getSignature().charAt(0))
    {
    default: 
      paramObject = localConstantPool1.addString(paramObject.toString());
    }
    for (;;)
    {
      new ConstantValueAttr(((CpoolEntry)paramObject).getIndex()).addToFrontOf(this);
      return;
      if (PrimType.booleanValue(paramObject)) {
        i = 1;
      }
      paramObject = localConstantPool1.addInt(i);
      continue;
      if ((paramObject instanceof Character))
      {
        paramObject = localConstantPool1.addInt(((Character)paramObject).charValue());
      }
      else
      {
        paramObject = localConstantPool1.addInt(((Number)paramObject).intValue());
        continue;
        paramObject = localConstantPool1.addLong(((Number)paramObject).longValue());
        continue;
        paramObject = localConstantPool1.addFloat(((Number)paramObject).floatValue());
        continue;
        paramObject = localConstantPool1.addDouble(((Number)paramObject).doubleValue());
      }
    }
  }
  
  public void setSourceName(String paramString)
  {
    this.sourceName = paramString;
  }
  
  public final void setStaticFlag(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x8;
      return;
    }
    this.flags ^= 0xFFFFFFF7;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(100);
    localStringBuffer.append("Field:");
    localStringBuffer.append(getDeclaringClass().getName());
    localStringBuffer.append('.');
    localStringBuffer.append(this.name);
    return localStringBuffer.toString();
  }
  
  void write(DataOutputStream paramDataOutputStream, ClassType paramClassType)
    throws IOException
  {
    paramDataOutputStream.writeShort(this.flags);
    paramDataOutputStream.writeShort(this.name_index);
    paramDataOutputStream.writeShort(this.signature_index);
    Attribute.writeAll(this, paramDataOutputStream);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\Field.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */