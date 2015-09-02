package gnu.bytecode;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class ClassFileInput
  extends DataInputStream
{
  ClassType ctype;
  InputStream str;
  
  public ClassFileInput(ClassType paramClassType, InputStream paramInputStream)
    throws IOException, ClassFormatError
  {
    super(paramInputStream);
    this.ctype = paramClassType;
    if (!readHeader()) {
      throw new ClassFormatError("invalid magic number");
    }
    paramClassType.constants = readConstants();
    readClassInfo();
    readFields();
    readMethods();
    readAttributes(paramClassType);
  }
  
  public ClassFileInput(InputStream paramInputStream)
    throws IOException
  {
    super(paramInputStream);
  }
  
  public static ClassType readClassType(InputStream paramInputStream)
    throws IOException, ClassFormatError
  {
    ClassType localClassType = new ClassType();
    new ClassFileInput(localClassType, paramInputStream);
    return localClassType;
  }
  
  CpoolClass getClassConstant(int paramInt)
  {
    return (CpoolClass)this.ctype.constants.getForced(paramInt, 7);
  }
  
  public Attribute readAttribute(String paramString, int paramInt, AttrContainer paramAttrContainer)
    throws IOException
  {
    if ((paramString == "SourceFile") && ((paramAttrContainer instanceof ClassType)))
    {
      paramAttrContainer = new SourceFileAttr(readUnsignedShort(), (ClassType)paramAttrContainer);
      return paramAttrContainer;
    }
    int i;
    if ((paramString == "Code") && ((paramAttrContainer instanceof Method)))
    {
      paramString = new CodeAttr((Method)paramAttrContainer);
      paramString.fixup_count = -1;
      paramString.setMaxStack(readUnsignedShort());
      paramString.setMaxLocals(readUnsignedShort());
      paramAttrContainer = new byte[readInt()];
      readFully(paramAttrContainer);
      paramString.setCode(paramAttrContainer);
      i = readUnsignedShort();
      paramInt = 0;
      while (paramInt < i)
      {
        paramString.addHandler(readUnsignedShort(), readUnsignedShort(), readUnsignedShort(), readUnsignedShort());
        paramInt += 1;
      }
      readAttributes(paramString);
      return paramString;
    }
    if ((paramString == "LineNumberTable") && ((paramAttrContainer instanceof CodeAttr)))
    {
      i = readUnsignedShort() * 2;
      paramString = new short[i];
      paramInt = 0;
      while (paramInt < i)
      {
        paramString[paramInt] = readShort();
        paramInt += 1;
      }
      return new LineNumbersAttr(paramString, (CodeAttr)paramAttrContainer);
    }
    Object localObject1;
    if ((paramString == "LocalVariableTable") && ((paramAttrContainer instanceof CodeAttr)))
    {
      paramAttrContainer = (CodeAttr)paramAttrContainer;
      localObject1 = new LocalVarsAttr(paramAttrContainer);
      Object localObject2 = ((LocalVarsAttr)localObject1).getMethod();
      if (((LocalVarsAttr)localObject1).parameter_scope == null) {
        ((LocalVarsAttr)localObject1).parameter_scope = ((Method)localObject2).pushScope();
      }
      paramString = ((LocalVarsAttr)localObject1).parameter_scope;
      if (paramString.end == null) {
        paramString.end = new Label(paramAttrContainer.PC);
      }
      localObject2 = ((Method)localObject2).getConstants();
      int i1 = readUnsignedShort();
      int j = paramString.start.position;
      i = paramString.end.position;
      paramInt = 0;
      for (;;)
      {
        paramAttrContainer = (AttrContainer)localObject1;
        if (paramInt >= i1) {
          break;
        }
        Variable localVariable = new Variable();
        int n = readUnsignedShort();
        int m = n + readUnsignedShort();
        paramAttrContainer = paramString;
        int k;
        if (n == j)
        {
          k = i;
          paramAttrContainer = paramString;
          if (m == i) {}
        }
        else
        {
          for (paramAttrContainer = paramString; (paramAttrContainer.parent != null) && ((n < paramAttrContainer.start.position) || (m > paramAttrContainer.end.position)); paramAttrContainer = paramAttrContainer.parent) {}
          paramString = new Scope(new Label(n), new Label(m));
          paramString.linkChild(paramAttrContainer);
          j = n;
          k = m;
          paramAttrContainer = paramString;
        }
        paramAttrContainer.addVariable(localVariable);
        localVariable.setName(readUnsignedShort(), (ConstantPool)localObject2);
        localVariable.setSignature(readUnsignedShort(), (ConstantPool)localObject2);
        localVariable.offset = readUnsignedShort();
        paramInt += 1;
        i = k;
        paramString = paramAttrContainer;
      }
    }
    if ((paramString == "Signature") && ((paramAttrContainer instanceof Member))) {
      return new SignatureAttr(readUnsignedShort(), (Member)paramAttrContainer);
    }
    if ((paramString == "StackMapTable") && ((paramAttrContainer instanceof CodeAttr)))
    {
      paramString = new byte[paramInt];
      readFully(paramString, 0, paramInt);
      return new StackMapTableAttr(paramString, (CodeAttr)paramAttrContainer);
    }
    if (((paramString == "RuntimeVisibleAnnotations") || (paramString == "RuntimeInvisibleAnnotations")) && (((paramAttrContainer instanceof Field)) || ((paramAttrContainer instanceof Method)) || ((paramAttrContainer instanceof ClassType))))
    {
      localObject1 = new byte[paramInt];
      readFully((byte[])localObject1, 0, paramInt);
      return new RuntimeAnnotationsAttr(paramString, (byte[])localObject1, paramAttrContainer);
    }
    if ((paramString == "ConstantValue") && ((paramAttrContainer instanceof Field))) {
      return new ConstantValueAttr(readUnsignedShort());
    }
    if ((paramString == "InnerClasses") && ((paramAttrContainer instanceof ClassType)))
    {
      i = readUnsignedShort() * 4;
      paramString = new short[i];
      paramInt = 0;
      while (paramInt < i)
      {
        paramString[paramInt] = readShort();
        paramInt += 1;
      }
      return new InnerClassesAttr(paramString, (ClassType)paramAttrContainer);
    }
    if ((paramString == "EnclosingMethod") && ((paramAttrContainer instanceof ClassType))) {
      return new EnclosingMethodAttr(readUnsignedShort(), readUnsignedShort(), (ClassType)paramAttrContainer);
    }
    if ((paramString == "Exceptions") && ((paramAttrContainer instanceof Method)))
    {
      paramString = (Method)paramAttrContainer;
      i = readUnsignedShort();
      paramAttrContainer = new short[i];
      paramInt = 0;
      while (paramInt < i)
      {
        paramAttrContainer[paramInt] = readShort();
        paramInt += 1;
      }
      paramString.setExceptions(paramAttrContainer);
      return paramString.getExceptionAttr();
    }
    if ((paramString == "SourceDebugExtension") && ((paramAttrContainer instanceof ClassType)))
    {
      paramString = new SourceDebugExtAttr((ClassType)paramAttrContainer);
      paramAttrContainer = new byte[paramInt];
      readFully(paramAttrContainer, 0, paramInt);
      paramString.data = paramAttrContainer;
      paramString.dlength = paramInt;
      return paramString;
    }
    paramAttrContainer = new byte[paramInt];
    readFully(paramAttrContainer, 0, paramInt);
    return new MiscAttr(paramString, paramAttrContainer);
  }
  
  public int readAttributes(AttrContainer paramAttrContainer)
    throws IOException
  {
    int j = readUnsignedShort();
    Object localObject1 = paramAttrContainer.getAttributes();
    int i = 0;
    if (i < j)
    {
      Object localObject2 = localObject1;
      label29:
      Attribute localAttribute;
      if (localObject1 != null)
      {
        localObject2 = ((Attribute)localObject1).getNext();
        if (localObject2 == null) {
          localObject2 = localObject1;
        }
      }
      else
      {
        int k = readUnsignedShort();
        localObject1 = (CpoolUtf8)this.ctype.constants.getForced(k, 1);
        int m = readInt();
        ((CpoolUtf8)localObject1).intern();
        localAttribute = readAttribute(((CpoolUtf8)localObject1).string, m, paramAttrContainer);
        localObject1 = localObject2;
        if (localAttribute != null)
        {
          if (localAttribute.getNameIndex() == 0) {
            localAttribute.setNameIndex(k);
          }
          if (localObject2 != null) {
            break label137;
          }
          paramAttrContainer.setAttributes(localAttribute);
        }
      }
      for (;;)
      {
        localObject1 = localAttribute;
        i += 1;
        break;
        localObject1 = localObject2;
        break label29;
        label137:
        if (paramAttrContainer.getAttributes() == localAttribute)
        {
          paramAttrContainer.setAttributes(localAttribute.getNext());
          localAttribute.setNext(null);
        }
        ((Attribute)localObject2).setNext(localAttribute);
      }
    }
    return j;
  }
  
  public void readClassInfo()
    throws IOException
  {
    this.ctype.access_flags = readUnsignedShort();
    this.ctype.thisClassIndex = readUnsignedShort();
    String str1 = getClassConstant(this.ctype.thisClassIndex).name.string;
    this.ctype.this_name = str1.replace('/', '.');
    this.ctype.setSignature("L" + str1 + ";");
    this.ctype.superClassIndex = readUnsignedShort();
    if (this.ctype.superClassIndex == 0) {
      this.ctype.setSuper((ClassType)null);
    }
    for (;;)
    {
      int j = readUnsignedShort();
      if (j <= 0) {
        break;
      }
      this.ctype.interfaces = new ClassType[j];
      this.ctype.interfaceIndexes = new int[j];
      int i = 0;
      while (i < j)
      {
        int k = readUnsignedShort();
        this.ctype.interfaceIndexes[i] = k;
        str1 = ((CpoolClass)this.ctype.constants.getForced(k, 7)).name.string.replace('/', '.');
        this.ctype.interfaces[i] = ClassType.make(str1);
        i += 1;
      }
      str1 = getClassConstant(this.ctype.superClassIndex).name.string;
      this.ctype.setSuper(str1.replace('/', '.'));
    }
  }
  
  public ConstantPool readConstants()
    throws IOException
  {
    return new ConstantPool(this);
  }
  
  public void readFields()
    throws IOException
  {
    int j = readUnsignedShort();
    ConstantPool localConstantPool = this.ctype.constants;
    int i = 0;
    while (i < j)
    {
      int k = readUnsignedShort();
      int m = readUnsignedShort();
      int n = readUnsignedShort();
      Field localField = this.ctype.addField();
      localField.setName(m, localConstantPool);
      localField.setSignature(n, localConstantPool);
      localField.flags = k;
      readAttributes(localField);
      i += 1;
    }
  }
  
  public void readFormatVersion()
    throws IOException
  {
    int i = readUnsignedShort();
    int j = readUnsignedShort();
    this.ctype.classfileFormatVersion = (65536 * j + i);
  }
  
  public boolean readHeader()
    throws IOException
  {
    if (readInt() != -889275714) {
      return false;
    }
    readFormatVersion();
    return true;
  }
  
  public void readMethods()
    throws IOException
  {
    int j = readUnsignedShort();
    int i = 0;
    while (i < j)
    {
      int k = readUnsignedShort();
      int m = readUnsignedShort();
      int n = readUnsignedShort();
      Method localMethod = this.ctype.addMethod(null, k);
      localMethod.setName(m);
      localMethod.setSignature(n);
      readAttributes(localMethod);
      i += 1;
    }
  }
  
  public final void skipAttribute(int paramInt)
    throws IOException
  {
    int i = 0;
    while (i < paramInt)
    {
      int k = (int)skip(paramInt - i);
      int j = k;
      if (k == 0)
      {
        if (read() < 0) {
          throw new EOFException("EOF while reading class files attributes");
        }
        j = 1;
      }
      i += j;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\ClassFileInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */