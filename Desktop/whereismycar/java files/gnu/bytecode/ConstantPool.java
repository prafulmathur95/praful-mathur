package gnu.bytecode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ConstantPool
{
  public static final byte CLASS = 7;
  public static final byte DOUBLE = 6;
  public static final byte FIELDREF = 9;
  public static final byte FLOAT = 4;
  public static final byte INTEGER = 3;
  public static final byte INTERFACE_METHODREF = 11;
  public static final byte LONG = 5;
  public static final byte METHODREF = 10;
  public static final byte NAME_AND_TYPE = 12;
  public static final byte STRING = 8;
  public static final byte UTF8 = 1;
  int count;
  CpoolEntry[] hashTab;
  boolean locked;
  CpoolEntry[] pool;
  
  public ConstantPool() {}
  
  public ConstantPool(DataInputStream paramDataInputStream)
    throws IOException
  {
    this.count = (paramDataInputStream.readUnsignedShort() - 1);
    this.pool = new CpoolEntry[this.count + 1];
    int i = 1;
    if (i <= this.count)
    {
      int k = paramDataInputStream.readByte();
      Object localObject = getForced(i, k);
      int j = i;
      switch (k)
      {
      default: 
        j = i;
      }
      for (;;)
      {
        i = j + 1;
        break;
        ((CpoolUtf8)localObject).string = paramDataInputStream.readUTF();
        j = i;
        continue;
        ((CpoolValue1)localObject).value = paramDataInputStream.readInt();
        j = i;
        continue;
        ((CpoolValue2)localObject).value = paramDataInputStream.readLong();
        j = i + 1;
        continue;
        ((CpoolClass)localObject).name = ((CpoolUtf8)getForced(paramDataInputStream.readUnsignedShort(), 1));
        j = i;
        continue;
        ((CpoolString)localObject).str = ((CpoolUtf8)getForced(paramDataInputStream.readUnsignedShort(), 1));
        j = i;
        continue;
        localObject = (CpoolRef)localObject;
        ((CpoolRef)localObject).clas = getForcedClass(paramDataInputStream.readUnsignedShort());
        ((CpoolRef)localObject).nameAndType = ((CpoolNameAndType)getForced(paramDataInputStream.readUnsignedShort(), 12));
        j = i;
        continue;
        localObject = (CpoolNameAndType)localObject;
        ((CpoolNameAndType)localObject).name = ((CpoolUtf8)getForced(paramDataInputStream.readUnsignedShort(), 1));
        ((CpoolNameAndType)localObject).type = ((CpoolUtf8)getForced(paramDataInputStream.readUnsignedShort(), 1));
        j = i;
      }
    }
  }
  
  public CpoolClass addClass(CpoolUtf8 paramCpoolUtf8)
  {
    int i = CpoolClass.hashCode(paramCpoolUtf8);
    if (this.hashTab == null) {
      rehash();
    }
    int j = this.hashTab.length;
    for (CpoolEntry localCpoolEntry = this.hashTab[((0x7FFFFFFF & i) % j)]; localCpoolEntry != null; localCpoolEntry = localCpoolEntry.next) {
      if ((i == localCpoolEntry.hash) && ((localCpoolEntry instanceof CpoolClass)))
      {
        CpoolClass localCpoolClass = (CpoolClass)localCpoolEntry;
        if (localCpoolClass.name == paramCpoolUtf8) {
          return localCpoolClass;
        }
      }
    }
    return new CpoolClass(this, i, paramCpoolUtf8);
  }
  
  public CpoolClass addClass(ObjectType paramObjectType)
  {
    CpoolClass localCpoolClass = addClass(addUtf8(paramObjectType.getInternalName()));
    localCpoolClass.clas = paramObjectType;
    return localCpoolClass;
  }
  
  public CpoolValue2 addDouble(double paramDouble)
  {
    return addValue2(6, Double.doubleToLongBits(paramDouble));
  }
  
  public CpoolRef addFieldRef(Field paramField)
  {
    return addRef(9, addClass(paramField.owner), addNameAndType(paramField));
  }
  
  public CpoolValue1 addFloat(float paramFloat)
  {
    return addValue1(4, Float.floatToIntBits(paramFloat));
  }
  
  public CpoolValue1 addInt(int paramInt)
  {
    return addValue1(3, paramInt);
  }
  
  public CpoolValue2 addLong(long paramLong)
  {
    return addValue2(5, paramLong);
  }
  
  public CpoolRef addMethodRef(Method paramMethod)
  {
    CpoolClass localCpoolClass = addClass(paramMethod.classfile);
    if ((paramMethod.getDeclaringClass().getModifiers() & 0x200) == 0) {}
    for (int i = 10;; i = 11) {
      return addRef(i, localCpoolClass, addNameAndType(paramMethod));
    }
  }
  
  public CpoolNameAndType addNameAndType(CpoolUtf8 paramCpoolUtf81, CpoolUtf8 paramCpoolUtf82)
  {
    int i = CpoolNameAndType.hashCode(paramCpoolUtf81, paramCpoolUtf82);
    if (this.hashTab == null) {
      rehash();
    }
    int j = this.hashTab.length;
    for (CpoolEntry localCpoolEntry = this.hashTab[((0x7FFFFFFF & i) % j)]; localCpoolEntry != null; localCpoolEntry = localCpoolEntry.next) {
      if ((i == localCpoolEntry.hash) && ((localCpoolEntry instanceof CpoolNameAndType)) && (((CpoolNameAndType)localCpoolEntry).name == paramCpoolUtf81) && (((CpoolNameAndType)localCpoolEntry).type == paramCpoolUtf82)) {
        return (CpoolNameAndType)localCpoolEntry;
      }
    }
    return new CpoolNameAndType(this, i, paramCpoolUtf81, paramCpoolUtf82);
  }
  
  public CpoolNameAndType addNameAndType(Field paramField)
  {
    return addNameAndType(addUtf8(paramField.getName()), addUtf8(paramField.getSignature()));
  }
  
  public CpoolNameAndType addNameAndType(Method paramMethod)
  {
    return addNameAndType(addUtf8(paramMethod.getName()), addUtf8(paramMethod.getSignature()));
  }
  
  public CpoolRef addRef(int paramInt, CpoolClass paramCpoolClass, CpoolNameAndType paramCpoolNameAndType)
  {
    int i = CpoolRef.hashCode(paramCpoolClass, paramCpoolNameAndType);
    if (this.hashTab == null) {
      rehash();
    }
    int j = this.hashTab.length;
    for (CpoolEntry localCpoolEntry = this.hashTab[((0x7FFFFFFF & i) % j)]; localCpoolEntry != null; localCpoolEntry = localCpoolEntry.next) {
      if ((i == localCpoolEntry.hash) && ((localCpoolEntry instanceof CpoolRef)))
      {
        CpoolRef localCpoolRef = (CpoolRef)localCpoolEntry;
        if ((localCpoolRef.tag == paramInt) && (localCpoolRef.clas == paramCpoolClass) && (localCpoolRef.nameAndType == paramCpoolNameAndType)) {
          return localCpoolRef;
        }
      }
    }
    return new CpoolRef(this, i, paramInt, paramCpoolClass, paramCpoolNameAndType);
  }
  
  public CpoolString addString(CpoolUtf8 paramCpoolUtf8)
  {
    int i = CpoolString.hashCode(paramCpoolUtf8);
    if (this.hashTab == null) {
      rehash();
    }
    int j = this.hashTab.length;
    for (CpoolEntry localCpoolEntry = this.hashTab[((0x7FFFFFFF & i) % j)]; localCpoolEntry != null; localCpoolEntry = localCpoolEntry.next) {
      if ((i == localCpoolEntry.hash) && ((localCpoolEntry instanceof CpoolString)))
      {
        CpoolString localCpoolString = (CpoolString)localCpoolEntry;
        if (localCpoolString.str == paramCpoolUtf8) {
          return localCpoolString;
        }
      }
    }
    return new CpoolString(this, i, paramCpoolUtf8);
  }
  
  public final CpoolString addString(String paramString)
  {
    return addString(addUtf8(paramString));
  }
  
  public CpoolUtf8 addUtf8(String paramString)
  {
    String str = paramString.intern();
    int i = str.hashCode();
    if (this.hashTab == null) {
      rehash();
    }
    int j = this.hashTab.length;
    for (paramString = this.hashTab[((0x7FFFFFFF & i) % j)]; paramString != null; paramString = paramString.next) {
      if ((i == paramString.hash) && ((paramString instanceof CpoolUtf8)))
      {
        CpoolUtf8 localCpoolUtf8 = (CpoolUtf8)paramString;
        if (localCpoolUtf8.string == str) {
          return localCpoolUtf8;
        }
      }
    }
    if (this.locked) {
      throw new Error("adding new Utf8 entry to locked contant pool: " + str);
    }
    return new CpoolUtf8(this, i, str);
  }
  
  CpoolValue1 addValue1(int paramInt1, int paramInt2)
  {
    int i = CpoolValue1.hashCode(paramInt2);
    if (this.hashTab == null) {
      rehash();
    }
    int j = this.hashTab.length;
    for (CpoolEntry localCpoolEntry = this.hashTab[((0x7FFFFFFF & i) % j)]; localCpoolEntry != null; localCpoolEntry = localCpoolEntry.next) {
      if ((i == localCpoolEntry.hash) && ((localCpoolEntry instanceof CpoolValue1)))
      {
        CpoolValue1 localCpoolValue1 = (CpoolValue1)localCpoolEntry;
        if ((localCpoolValue1.tag == paramInt1) && (localCpoolValue1.value == paramInt2)) {
          return localCpoolValue1;
        }
      }
    }
    return new CpoolValue1(this, paramInt1, i, paramInt2);
  }
  
  CpoolValue2 addValue2(int paramInt, long paramLong)
  {
    int i = CpoolValue2.hashCode(paramLong);
    if (this.hashTab == null) {
      rehash();
    }
    int j = this.hashTab.length;
    for (CpoolEntry localCpoolEntry = this.hashTab[((0x7FFFFFFF & i) % j)]; localCpoolEntry != null; localCpoolEntry = localCpoolEntry.next) {
      if ((i == localCpoolEntry.hash) && ((localCpoolEntry instanceof CpoolValue2)))
      {
        CpoolValue2 localCpoolValue2 = (CpoolValue2)localCpoolEntry;
        if ((localCpoolValue2.tag == paramInt) && (localCpoolValue2.value == paramLong)) {
          return localCpoolValue2;
        }
      }
    }
    return new CpoolValue2(this, paramInt, i, paramLong);
  }
  
  public final int getCount()
  {
    return this.count;
  }
  
  CpoolEntry getForced(int paramInt1, int paramInt2)
  {
    paramInt1 &= 0xFFFF;
    CpoolEntry localCpoolEntry = this.pool[paramInt1];
    Object localObject;
    if (localCpoolEntry == null)
    {
      if (this.locked) {
        throw new Error("adding new entry to locked contant pool");
      }
      localObject = localCpoolEntry;
      switch (paramInt2)
      {
      default: 
        localObject = localCpoolEntry;
      case 2: 
        this.pool[paramInt1] = localObject;
        ((CpoolEntry)localObject).index = paramInt1;
      }
    }
    do
    {
      return (CpoolEntry)localObject;
      localObject = new CpoolUtf8();
      break;
      localObject = new CpoolValue1(paramInt2);
      break;
      localObject = new CpoolValue2(paramInt2);
      break;
      localObject = new CpoolClass();
      break;
      localObject = new CpoolString();
      break;
      localObject = new CpoolRef(paramInt2);
      break;
      localObject = new CpoolNameAndType();
      break;
      localObject = localCpoolEntry;
    } while (localCpoolEntry.getTag() == paramInt2);
    throw new ClassFormatError("conflicting constant pool tags at " + paramInt1);
  }
  
  CpoolClass getForcedClass(int paramInt)
  {
    return (CpoolClass)getForced(paramInt, 7);
  }
  
  public final CpoolEntry getPoolEntry(int paramInt)
  {
    return this.pool[paramInt];
  }
  
  void rehash()
  {
    int j;
    CpoolEntry localCpoolEntry;
    if ((this.hashTab == null) && (this.count > 0))
    {
      i = this.pool.length;
      for (;;)
      {
        j = i - 1;
        if (j < 0) {
          break;
        }
        localCpoolEntry = this.pool[j];
        i = j;
        if (localCpoolEntry != null)
        {
          localCpoolEntry.hashCode();
          i = j;
        }
      }
    }
    if (this.count < 5) {}
    for (int i = 101;; i = this.count * 2)
    {
      this.hashTab = new CpoolEntry[i];
      if (this.pool == null) {
        break;
      }
      i = this.pool.length;
      for (;;)
      {
        j = i - 1;
        if (j < 0) {
          break;
        }
        localCpoolEntry = this.pool[j];
        i = j;
        if (localCpoolEntry != null)
        {
          localCpoolEntry.add_hashed(this);
          i = j;
        }
      }
    }
  }
  
  void write(DataOutputStream paramDataOutputStream)
    throws IOException
  {
    paramDataOutputStream.writeShort(this.count + 1);
    int i = 1;
    while (i <= this.count)
    {
      CpoolEntry localCpoolEntry = this.pool[i];
      if (localCpoolEntry != null) {
        localCpoolEntry.write(paramDataOutputStream);
      }
      i += 1;
    }
    this.locked = true;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\ConstantPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */