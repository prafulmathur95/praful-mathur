package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class InnerClassesAttr
  extends Attribute
{
  int count;
  short[] data;
  
  public InnerClassesAttr(ClassType paramClassType)
  {
    super("InnerClasses");
    addToFrontOf(paramClassType);
  }
  
  public InnerClassesAttr(short[] paramArrayOfShort, ClassType paramClassType)
  {
    this(paramClassType);
    this.count = ((short)(paramArrayOfShort.length >> 2));
    this.data = paramArrayOfShort;
  }
  
  public static InnerClassesAttr getFirstInnerClasses(Attribute paramAttribute)
  {
    for (;;)
    {
      if ((paramAttribute == null) || ((paramAttribute instanceof InnerClassesAttr))) {
        return (InnerClassesAttr)paramAttribute;
      }
      paramAttribute = paramAttribute.next;
    }
  }
  
  void addClass(CpoolClass paramCpoolClass, ClassType paramClassType)
  {
    int i = 0;
    int j = this.count;
    this.count = (j + 1);
    int k = j * 4;
    Object localObject1;
    Object localObject2;
    if (this.data == null)
    {
      this.data = new short[16];
      paramClassType = paramClassType.constants;
      localObject1 = (ClassType)paramCpoolClass.getClassType();
      localObject2 = ((ClassType)localObject1).getSimpleName();
      if ((localObject2 != null) && (((String)localObject2).length() != 0)) {
        break label185;
      }
      j = 0;
      label74:
      this.data[k] = ((short)paramCpoolClass.index);
      paramCpoolClass = ((ClassType)localObject1).getDeclaringClass();
      localObject2 = this.data;
      if (paramCpoolClass != null) {
        break label199;
      }
    }
    for (;;)
    {
      localObject2[(k + 1)] = i;
      this.data[(k + 2)] = ((short)j);
      j = ((ClassType)localObject1).getModifiers();
      this.data[(k + 3)] = ((short)(j & 0xFFFFFFDF));
      return;
      if (k < this.data.length) {
        break;
      }
      localObject1 = new short[k * 2];
      System.arraycopy(this.data, 0, localObject1, 0, k);
      this.data = ((short[])localObject1);
      break;
      label185:
      j = paramClassType.addUtf8((String)localObject2).index;
      break label74;
      label199:
      i = (short)paramClassType.addClass(paramCpoolClass).index;
    }
  }
  
  public void assignConstants(ClassType paramClassType)
  {
    super.assignConstants(paramClassType);
  }
  
  public int getLength()
  {
    return this.count * 8 + 2;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter)
  {
    ClassType localClassType = (ClassType)this.container;
    ConstantPool localConstantPool;
    int i;
    label63:
    int j;
    label79:
    CpoolClass localCpoolClass;
    label91:
    Object localObject2;
    label117:
    int k;
    label140:
    Object localObject1;
    if (this.data == null)
    {
      localConstantPool = null;
      paramClassTypeWriter.print("Attribute \"");
      paramClassTypeWriter.print(getName());
      paramClassTypeWriter.print("\", length:");
      paramClassTypeWriter.print(getLength());
      paramClassTypeWriter.print(", count: ");
      paramClassTypeWriter.println(this.count);
      i = 0;
      if (i >= this.count) {
        return;
      }
      if (localConstantPool != null) {
        break label319;
      }
      j = 0;
      if ((localConstantPool != null) && (j != 0)) {
        break label336;
      }
      localCpoolClass = null;
      if ((localCpoolClass == null) || (!(localCpoolClass.clas instanceof ClassType))) {
        break label347;
      }
      localObject2 = (ClassType)localCpoolClass.clas;
      paramClassTypeWriter.print(' ');
      if ((j != 0) || (localObject2 == null)) {
        break label353;
      }
      k = ((ClassType)localObject2).getModifiers();
      paramClassTypeWriter.print(Access.toString(k, 'I'));
      paramClassTypeWriter.print(' ');
      if ((j != 0) || (localObject2 == null)) {
        break label372;
      }
      localObject1 = ((ClassType)localObject2).getSimpleName();
      label173:
      paramClassTypeWriter.print((String)localObject1);
      paramClassTypeWriter.print(" = ");
      if (localCpoolClass == null) {
        break label429;
      }
      localObject1 = localCpoolClass.getClassName();
      label195:
      paramClassTypeWriter.print((String)localObject1);
      paramClassTypeWriter.print("; ");
      if ((j != 0) || (localObject2 == null)) {
        break label453;
      }
      localObject2 = ((ClassType)localObject2).getName();
      j = ((String)localObject2).lastIndexOf('.');
      localObject1 = localObject2;
      if (j > 0) {
        localObject1 = ((String)localObject2).substring(j + 1);
      }
      j = ((String)localObject1).lastIndexOf('$') + 1;
      if (j >= ((String)localObject1).length()) {
        break label435;
      }
      j = ((String)localObject1).charAt(j);
      if ((j < 48) || (j > 57)) {
        break label435;
      }
      paramClassTypeWriter.print("not a member");
    }
    for (;;)
    {
      paramClassTypeWriter.println();
      i += 1;
      break label63;
      localConstantPool = localClassType.getConstants();
      break;
      label319:
      j = this.data[(i * 4)] & 0xFFFF;
      break label79;
      label336:
      localCpoolClass = localConstantPool.getForcedClass(j);
      break label91;
      label347:
      localObject2 = null;
      break label117;
      label353:
      k = this.data[(i * 4 + 3)] & 0xFFFF;
      break label140;
      label372:
      k = this.data[(i * 4 + 2)] & 0xFFFF;
      if ((localConstantPool == null) || (k == 0))
      {
        localObject1 = "(Anonymous)";
        break label173;
      }
      paramClassTypeWriter.printOptionalIndex(k);
      localObject1 = ((CpoolUtf8)localConstantPool.getForced(k, 1)).string;
      break label173;
      label429:
      localObject1 = "(Unknown)";
      break label195;
      label435:
      paramClassTypeWriter.print("member of ");
      paramClassTypeWriter.print(localClassType.getName());
      continue;
      label453:
      j = this.data[(i * 4 + 1)] & 0xFFFF;
      if (j == 0)
      {
        paramClassTypeWriter.print("not a member");
      }
      else
      {
        paramClassTypeWriter.print("member of ");
        paramClassTypeWriter.print(((CpoolClass)localConstantPool.getForced(j, 7)).getStringName());
      }
    }
  }
  
  public void write(DataOutputStream paramDataOutputStream)
    throws IOException
  {
    paramDataOutputStream.writeShort(this.count);
    int i = 0;
    while (i < this.count)
    {
      paramDataOutputStream.writeShort(this.data[(i * 4)]);
      paramDataOutputStream.writeShort(this.data[(i * 4 + 1)]);
      paramDataOutputStream.writeShort(this.data[(i * 4 + 2)]);
      paramDataOutputStream.writeShort(this.data[(i * 4 + 3)]);
      i += 1;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\InnerClassesAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */