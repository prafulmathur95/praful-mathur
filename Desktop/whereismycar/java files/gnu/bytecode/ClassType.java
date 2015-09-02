package gnu.bytecode;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.Externalizable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ClassType
  extends ObjectType
  implements AttrContainer, Externalizable, Member
{
  public static final int JDK_1_1_VERSION = 2949123;
  public static final int JDK_1_2_VERSION = 3014656;
  public static final int JDK_1_3_VERSION = 3080192;
  public static final int JDK_1_4_VERSION = 3145728;
  public static final int JDK_1_5_VERSION = 3211264;
  public static final int JDK_1_6_VERSION = 3276800;
  public static final int JDK_1_7_VERSION = 3342336;
  public static final ClassType[] noClasses = new ClassType[0];
  int Code_name_index;
  int ConstantValue_name_index;
  int LineNumberTable_name_index;
  int LocalVariableTable_name_index;
  int access_flags;
  Attribute attributes;
  int classfileFormatVersion = 2949123;
  ConstantPool constants;
  public Method constructor;
  boolean emitDebugInfo = true;
  Member enclosingMember;
  Field fields;
  int fields_count;
  ClassType firstInnerClass;
  int[] interfaceIndexes;
  ClassType[] interfaces;
  Field last_field;
  Method last_method;
  Method methods;
  int methods_count;
  ClassType nextInnerClass;
  SourceDebugExtAttr sourceDbgExt;
  ClassType superClass;
  int superClassIndex = -1;
  int thisClassIndex;
  
  public ClassType() {}
  
  public ClassType(String paramString)
  {
    setName(paramString);
  }
  
  public static ClassType make(String paramString)
  {
    return (ClassType)Type.getType(paramString);
  }
  
  public static ClassType make(String paramString, ClassType paramClassType)
  {
    paramString = make(paramString);
    if (paramString.superClass == null) {
      paramString.setSuper(paramClassType);
    }
    return paramString;
  }
  
  public static byte[] to_utf8(String paramString)
  {
    Object localObject;
    if (paramString == null) {
      localObject = null;
    }
    int m;
    int i;
    int j;
    int k;
    byte[] arrayOfByte;
    do
    {
      return (byte[])localObject;
      m = paramString.length();
      i = 0;
      j = 0;
      if (j < m)
      {
        k = paramString.charAt(j);
        if ((k > 0) && (k <= 127)) {
          i += 1;
        }
        for (;;)
        {
          j += 1;
          break;
          if (k <= 2047) {
            i += 2;
          } else {
            i += 3;
          }
        }
      }
      arrayOfByte = new byte[i];
      j = 0;
      i = 0;
      localObject = arrayOfByte;
    } while (j >= m);
    int n = paramString.charAt(j);
    if ((n > 0) && (n <= 127))
    {
      k = i + 1;
      arrayOfByte[i] = ((byte)n);
      i = k;
    }
    for (;;)
    {
      j += 1;
      break;
      if (n <= 2047)
      {
        k = i + 1;
        arrayOfByte[i] = ((byte)(n >> 6 & 0x1F | 0xC0));
        arrayOfByte[k] = ((byte)(n >> 0 & 0x3F | 0x80));
        i = k + 1;
      }
      else
      {
        k = i + 1;
        arrayOfByte[i] = ((byte)(n >> 12 & 0xF | 0xE0));
        int i1 = k + 1;
        arrayOfByte[k] = ((byte)(n >> 6 & 0x3F | 0x80));
        i = i1 + 1;
        arrayOfByte[i1] = ((byte)(n >> 0 & 0x3F | 0x80));
      }
    }
  }
  
  void addEnclosingMember()
  {
    for (;;)
    {
      Object localObject;
      try
      {
        int i = this.flags;
        if ((i & 0x18) != 16) {
          return;
        }
        localObject = getReflectClass();
        this.flags |= 0x8;
        Class localClass1 = ((Class)localObject).getEnclosingClass();
        if (localClass1 == null) {
          continue;
        }
        if (((Class)localObject).isMemberClass()) {
          break label100;
        }
        java.lang.reflect.Method localMethod = ((Class)localObject).getEnclosingMethod();
        if (localMethod != null)
        {
          this.enclosingMember = addMethod(localMethod);
          continue;
        }
        localObject = ((Class)localObject).getEnclosingConstructor();
      }
      finally {}
      if (localObject != null) {
        this.enclosingMember = addMethod((Constructor)localObject);
      } else {
        label100:
        this.enclosingMember = ((ClassType)Type.make(localClass2));
      }
    }
  }
  
  public Field addField()
  {
    return new Field(this);
  }
  
  public Field addField(String paramString)
  {
    Field localField = new Field(this);
    localField.setName(paramString);
    return localField;
  }
  
  public final Field addField(String paramString, Type paramType)
  {
    Field localField = new Field(this);
    localField.setName(paramString);
    localField.setType(paramType);
    return localField;
  }
  
  public final Field addField(String paramString, Type paramType, int paramInt)
  {
    paramString = addField(paramString, paramType);
    paramString.flags = paramInt;
    return paramString;
  }
  
  /* Error */
  public void addFields()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 119	gnu/bytecode/ClassType:getReflectClass	()Ljava/lang/Class;
    //   6: astore_2
    //   7: aload_2
    //   8: invokevirtual 172	java/lang/Class:getDeclaredFields	()[Ljava/lang/reflect/Field;
    //   11: astore_1
    //   12: aload_1
    //   13: arraylength
    //   14: istore 4
    //   16: iconst_0
    //   17: istore_3
    //   18: iload_3
    //   19: iload 4
    //   21: if_icmpge +66 -> 87
    //   24: aload_1
    //   25: iload_3
    //   26: aaload
    //   27: astore_2
    //   28: ldc -82
    //   30: aload_2
    //   31: invokevirtual 180	java/lang/reflect/Field:getName	()Ljava/lang/String;
    //   34: invokevirtual 184	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   37: ifeq +14 -> 51
    //   40: aload_0
    //   41: aload_0
    //   42: getfield 115	gnu/bytecode/ClassType:flags	I
    //   45: bipush 32
    //   47: ior
    //   48: putfield 115	gnu/bytecode/ClassType:flags	I
    //   51: aload_0
    //   52: aload_2
    //   53: invokevirtual 180	java/lang/reflect/Field:getName	()Ljava/lang/String;
    //   56: aload_2
    //   57: invokevirtual 186	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   60: invokestatic 148	gnu/bytecode/Type:make	(Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   63: aload_2
    //   64: invokevirtual 189	java/lang/reflect/Field:getModifiers	()I
    //   67: invokevirtual 191	gnu/bytecode/ClassType:addField	(Ljava/lang/String;Lgnu/bytecode/Type;I)Lgnu/bytecode/Field;
    //   70: pop
    //   71: iload_3
    //   72: iconst_1
    //   73: iadd
    //   74: istore_3
    //   75: goto -57 -> 18
    //   78: astore_1
    //   79: aload_2
    //   80: invokevirtual 194	java/lang/Class:getFields	()[Ljava/lang/reflect/Field;
    //   83: astore_1
    //   84: goto -72 -> 12
    //   87: aload_0
    //   88: aload_0
    //   89: getfield 115	gnu/bytecode/ClassType:flags	I
    //   92: iconst_1
    //   93: ior
    //   94: putfield 115	gnu/bytecode/ClassType:flags	I
    //   97: aload_0
    //   98: monitorexit
    //   99: return
    //   100: astore_1
    //   101: aload_0
    //   102: monitorexit
    //   103: aload_1
    //   104: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	105	0	this	ClassType
    //   11	14	1	arrayOfField1	java.lang.reflect.Field[]
    //   78	1	1	localSecurityException	SecurityException
    //   83	1	1	arrayOfField2	java.lang.reflect.Field[]
    //   100	4	1	localObject	Object
    //   6	74	2	localClass	Class
    //   17	58	3	i	int
    //   14	8	4	j	int
    // Exception table:
    //   from	to	target	type
    //   7	12	78	java/lang/SecurityException
    //   2	7	100	finally
    //   7	12	100	finally
    //   12	16	100	finally
    //   28	51	100	finally
    //   51	71	100	finally
    //   79	84	100	finally
    //   87	97	100	finally
  }
  
  public void addInterface(ClassType paramClassType)
  {
    int i;
    if ((this.interfaces == null) || (this.interfaces.length == 0)) {
      i = 0;
    }
    ClassType[] arrayOfClassType;
    for (this.interfaces = new ClassType[1];; this.interfaces = arrayOfClassType)
    {
      this.interfaces[i] = paramClassType;
      return;
      i = this.interfaces.length;
      int j = i;
      int k;
      do
      {
        k = j - 1;
        if (k < 0) {
          break;
        }
        j = k;
      } while (this.interfaces[k] != paramClassType);
      return;
      arrayOfClassType = new ClassType[i + 1];
      System.arraycopy(this.interfaces, 0, arrayOfClassType, 0, i);
    }
  }
  
  public void addMemberClass(ClassType paramClassType)
  {
    Object localObject = null;
    for (ClassType localClassType = this.firstInnerClass; localClassType != null; localClassType = localClassType.nextInnerClass)
    {
      if (localClassType == paramClassType) {
        return;
      }
      localObject = localClassType;
    }
    if (localObject == null)
    {
      this.firstInnerClass = paramClassType;
      return;
    }
    ((ClassType)localObject).nextInnerClass = paramClassType;
  }
  
  /* Error */
  public void addMemberClasses()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 115	gnu/bytecode/ClassType:flags	I
    //   6: istore_2
    //   7: iload_2
    //   8: bipush 20
    //   10: iand
    //   11: bipush 16
    //   13: if_icmpeq +6 -> 19
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: aload_0
    //   20: invokevirtual 119	gnu/bytecode/ClassType:getReflectClass	()Ljava/lang/Class;
    //   23: astore_1
    //   24: aload_0
    //   25: aload_0
    //   26: getfield 115	gnu/bytecode/ClassType:flags	I
    //   29: iconst_4
    //   30: ior
    //   31: putfield 115	gnu/bytecode/ClassType:flags	I
    //   34: aload_1
    //   35: invokevirtual 213	java/lang/Class:getClasses	()[Ljava/lang/Class;
    //   38: astore_1
    //   39: aload_1
    //   40: arraylength
    //   41: istore_3
    //   42: iload_3
    //   43: ifle -27 -> 16
    //   46: iconst_0
    //   47: istore_2
    //   48: iload_2
    //   49: iload_3
    //   50: if_icmpge -34 -> 16
    //   53: aload_0
    //   54: aload_1
    //   55: iload_2
    //   56: aaload
    //   57: invokestatic 148	gnu/bytecode/Type:make	(Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   60: checkcast 2	gnu/bytecode/ClassType
    //   63: invokevirtual 215	gnu/bytecode/ClassType:addMemberClass	(Lgnu/bytecode/ClassType;)V
    //   66: iload_2
    //   67: iconst_1
    //   68: iadd
    //   69: istore_2
    //   70: goto -22 -> 48
    //   73: astore_1
    //   74: aload_0
    //   75: monitorexit
    //   76: aload_1
    //   77: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	78	0	this	ClassType
    //   23	32	1	localObject1	Object
    //   73	4	1	localObject2	Object
    //   6	64	2	i	int
    //   41	10	3	j	int
    // Exception table:
    //   from	to	target	type
    //   2	7	73	finally
    //   19	42	73	finally
    //   53	66	73	finally
  }
  
  Method addMethod()
  {
    return new Method(this, 0);
  }
  
  public Method addMethod(String paramString)
  {
    return addMethod(paramString, 0);
  }
  
  public Method addMethod(String paramString, int paramInt)
  {
    Method localMethod = new Method(this, paramInt);
    localMethod.setName(paramString);
    return localMethod;
  }
  
  /* Error */
  public Method addMethod(String paramString, int paramInt, Type[] paramArrayOfType, Type paramType)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_3
    //   5: invokevirtual 231	gnu/bytecode/ClassType:getDeclaredMethod	(Ljava/lang/String;[Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   8: astore 5
    //   10: aload 5
    //   12: ifnull +38 -> 50
    //   15: aload 4
    //   17: aload 5
    //   19: invokevirtual 235	gnu/bytecode/Method:getReturnType	()Lgnu/bytecode/Type;
    //   22: invokevirtual 238	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   25: ifeq +25 -> 50
    //   28: aload 5
    //   30: getfield 240	gnu/bytecode/Method:access_flags	I
    //   33: istore 6
    //   35: iload 6
    //   37: iload_2
    //   38: iand
    //   39: iload_2
    //   40: if_icmpne +10 -> 50
    //   43: aload 5
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: areturn
    //   50: aload_0
    //   51: aload_1
    //   52: iload_2
    //   53: invokevirtual 225	gnu/bytecode/ClassType:addMethod	(Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   56: astore_1
    //   57: aload_1
    //   58: aload_3
    //   59: putfield 244	gnu/bytecode/Method:arg_types	[Lgnu/bytecode/Type;
    //   62: aload_1
    //   63: aload 4
    //   65: putfield 248	gnu/bytecode/Method:return_type	Lgnu/bytecode/Type;
    //   68: goto -22 -> 46
    //   71: astore_1
    //   72: aload_0
    //   73: monitorexit
    //   74: aload_1
    //   75: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	76	0	this	ClassType
    //   0	76	1	paramString	String
    //   0	76	2	paramInt	int
    //   0	76	3	paramArrayOfType	Type[]
    //   0	76	4	paramType	Type
    //   8	36	5	localMethod	Method
    //   33	6	6	i	int
    // Exception table:
    //   from	to	target	type
    //   2	10	71	finally
    //   15	35	71	finally
    //   50	68	71	finally
  }
  
  public Method addMethod(String paramString1, String paramString2, int paramInt)
  {
    paramString1 = addMethod(paramString1, paramInt);
    paramString1.setSignature(paramString2);
    return paramString1;
  }
  
  public Method addMethod(String paramString, Type[] paramArrayOfType, Type paramType, int paramInt)
  {
    return addMethod(paramString, paramInt, paramArrayOfType, paramType);
  }
  
  public Method addMethod(Constructor paramConstructor)
  {
    Class[] arrayOfClass = paramConstructor.getParameterTypes();
    int j = paramConstructor.getModifiers();
    int i = arrayOfClass.length;
    paramConstructor = new Type[i];
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      paramConstructor[i] = Type.make(arrayOfClass[i]);
    }
    return addMethod("<init>", j, paramConstructor, Type.voidType);
  }
  
  public Method addMethod(java.lang.reflect.Method paramMethod)
  {
    int j = paramMethod.getModifiers();
    Object localObject = paramMethod.getParameterTypes();
    int i = localObject.length;
    Type[] arrayOfType = new Type[i];
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      arrayOfType[i] = Type.make(localObject[i]);
    }
    localObject = Type.make(paramMethod.getReturnType());
    return addMethod(paramMethod.getName(), j, arrayOfType, (Type)localObject);
  }
  
  public void addMethods(Class paramClass)
  {
    int i;
    try
    {
      this.flags |= 0x2;
      int j;
      java.lang.reflect.Method localMethod;
      Object localObject;
      try
      {
        java.lang.reflect.Method[] arrayOfMethod = paramClass.getDeclaredMethods();
        j = arrayOfMethod.length;
        i = 0;
        if (i < j)
        {
          localMethod = arrayOfMethod[i];
          if (localMethod.getDeclaringClass().equals(paramClass)) {}
        }
      }
      catch (SecurityException localSecurityException1)
      {
        for (;;)
        {
          localObject = paramClass.getMethods();
        }
        addMethod(localMethod);
      }
      try
      {
        localObject = paramClass.getDeclaredConstructors();
        j = localObject.length;
        i = 0;
        if (i < j)
        {
          localMethod = localObject[i];
          if (localMethod.getDeclaringClass().equals(paramClass)) {}
        }
      }
      catch (SecurityException localSecurityException2)
      {
        for (;;)
        {
          arrayOfConstructor = paramClass.getConstructors();
        }
        addMethod(localMethod);
      }
    }
    finally {}
    for (;;)
    {
      Constructor[] arrayOfConstructor;
      return;
      i += 1;
      break;
      i += 1;
    }
  }
  
  public final void addModifiers(int paramInt)
  {
    this.access_flags |= paramInt;
  }
  
  public Method checkSingleAbstractMethod()
  {
    Method[] arrayOfMethod = getAbstractMethods();
    int j = arrayOfMethod.length;
    Object localObject1 = null;
    int i = 0;
    Object localObject2 = localObject1;
    if (i < j)
    {
      localObject2 = arrayOfMethod[i];
      Method localMethod = getMethod(((Method)localObject2).getName(), ((Method)localObject2).getParameterTypes());
      if ((localMethod == null) || (localMethod.isAbstract())) {}
    }
    for (;;)
    {
      i += 1;
      break;
      if (localObject1 != null)
      {
        localObject2 = null;
        return (Method)localObject2;
      }
      localObject1 = localObject2;
    }
  }
  
  public void cleanupAfterCompilation()
  {
    for (Method localMethod = this.methods; localMethod != null; localMethod = localMethod.getNext()) {
      localMethod.cleanupAfterCompilation();
    }
    this.constants = null;
    this.attributes = null;
    this.sourceDbgExt = null;
  }
  
  public int compare(Type paramType)
  {
    int i = -1;
    if (paramType == nullType) {}
    do
    {
      do
      {
        do
        {
          return 1;
          if (!(paramType instanceof ClassType)) {
            return swappedCompareResult(paramType.compare(this));
          }
          String str = getName();
          if ((str != null) && (str.equals(paramType.getName()))) {
            return 0;
          }
          paramType = (ClassType)paramType;
          if (isSubclass(paramType)) {
            return -1;
          }
        } while (paramType.isSubclass(this));
        if (this == toStringType)
        {
          if (paramType == Type.javalangObjectType) {}
          for (;;)
          {
            return i;
            i = 1;
          }
        }
        if (paramType != toStringType) {
          break;
        }
      } while (this == Type.javalangObjectType);
      return -1;
      if (isInterface())
      {
        if (paramType == Type.javalangObjectType) {}
        for (;;)
        {
          return i;
          i = -2;
        }
      }
      if (!paramType.isInterface()) {
        break;
      }
    } while (this == Type.javalangObjectType);
    return -2;
    return -3;
  }
  
  public final int countMethods(Filter paramFilter, int paramInt)
  {
    Vector localVector = new Vector();
    getMethods(paramFilter, paramInt, localVector);
    return localVector.size();
  }
  
  public void doFixups()
  {
    if (this.constants == null) {
      this.constants = new ConstantPool();
    }
    if (this.thisClassIndex == 0) {
      this.thisClassIndex = this.constants.addClass(this).index;
    }
    if (this.superClass == this) {
      setSuper((ClassType)null);
    }
    if (this.superClassIndex < 0) {
      if (this.superClass != null) {
        break label151;
      }
    }
    label151:
    for (int i = 0;; i = this.constants.addClass(this.superClass).index)
    {
      this.superClassIndex = i;
      if ((this.interfaces == null) || (this.interfaceIndexes != null)) {
        break;
      }
      int j = this.interfaces.length;
      this.interfaceIndexes = new int[j];
      i = 0;
      while (i < j)
      {
        this.interfaceIndexes[i] = this.constants.addClass(this.interfaces[i]).index;
        i += 1;
      }
    }
    for (Object localObject1 = this.fields; localObject1 != null; localObject1 = ((Field)localObject1).next) {
      ((Field)localObject1).assign_constants(this);
    }
    for (localObject1 = this.methods; localObject1 != null; localObject1 = ((Method)localObject1).next) {
      ((Method)localObject1).assignConstants();
    }
    Object localObject2;
    if ((this.enclosingMember instanceof Method))
    {
      localObject2 = EnclosingMethodAttr.getFirstEnclosingMethod(getAttributes());
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = new EnclosingMethodAttr(this);
      }
      ((EnclosingMethodAttr)localObject1).method = ((Method)this.enclosingMember);
    }
    for (;;)
    {
      for (localObject1 = this.firstInnerClass; localObject1 != null; localObject1 = ((ClassType)localObject1).nextInnerClass) {
        this.constants.addClass((ObjectType)localObject1);
      }
      if ((this.enclosingMember instanceof ClassType)) {
        this.constants.addClass((ClassType)this.enclosingMember);
      }
    }
    localObject1 = InnerClassesAttr.getFirstInnerClasses(getAttributes());
    if (localObject1 != null) {
      ((InnerClassesAttr)localObject1).setSkipped(true);
    }
    Attribute.assignConstants(this, this);
    i = 1;
    if (i <= this.constants.count)
    {
      localObject2 = this.constants.pool[i];
      if (!(localObject2 instanceof CpoolClass)) {
        localObject2 = localObject1;
      }
      for (;;)
      {
        i += 1;
        localObject1 = localObject2;
        break;
        CpoolClass localCpoolClass = (CpoolClass)localObject2;
        localObject2 = localObject1;
        if ((localCpoolClass.clas instanceof ClassType))
        {
          localObject2 = localObject1;
          if (((ClassType)localCpoolClass.clas).getEnclosingMember() != null)
          {
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = new InnerClassesAttr(this);
            }
            ((InnerClassesAttr)localObject2).addClass(localCpoolClass, this);
          }
        }
      }
    }
    if (localObject1 != null)
    {
      ((InnerClassesAttr)localObject1).setSkipped(false);
      ((InnerClassesAttr)localObject1).assignConstants(this);
    }
  }
  
  public Method[] getAbstractMethods()
  {
    return getMethods(AbstractMethodFilter.instance, 2);
  }
  
  public final Attribute getAttributes()
  {
    return this.attributes;
  }
  
  public short getClassfileMajorVersion()
  {
    return (short)(this.classfileFormatVersion >> 16);
  }
  
  public short getClassfileMinorVersion()
  {
    return (short)(this.classfileFormatVersion & 0xFFFF);
  }
  
  public int getClassfileVersion()
  {
    return this.classfileFormatVersion;
  }
  
  public final CpoolEntry getConstant(int paramInt)
  {
    if ((this.constants == null) || (this.constants.pool == null) || (paramInt > this.constants.count)) {
      return null;
    }
    return this.constants.pool[paramInt];
  }
  
  public final ConstantPool getConstants()
  {
    return this.constants;
  }
  
  public ClassType getDeclaredClass(String paramString)
  {
    addMemberClasses();
    for (ClassType localClassType = this.firstInnerClass; localClassType != null; localClassType = localClassType.nextInnerClass) {
      if (paramString.equals(localClassType.getSimpleName())) {
        return localClassType;
      }
    }
    return null;
  }
  
  public Field getDeclaredField(String paramString)
  {
    for (Field localField = getFields(); localField != null; localField = localField.next) {
      if (paramString.equals(localField.name)) {
        return localField;
      }
    }
    return null;
  }
  
  public Method getDeclaredMethod(String paramString, int paramInt)
  {
    Object localObject1 = null;
    for (;;)
    {
      try
      {
        if (("<init>".equals(paramString)) && (hasOuterLink()))
        {
          i = 1;
          localMethod = getDeclaredMethods();
          if (localMethod == null) {
            break;
          }
          localObject2 = localObject1;
          if (!paramString.equals(localMethod.getName())) {
            break label143;
          }
          localObject2 = localObject1;
          if (paramInt + i != localMethod.getParameterTypes().length) {
            break label143;
          }
          if (localObject1 == null) {
            break label140;
          }
          throw new Error("ambiguous call to getDeclaredMethod(\"" + paramString + "\", " + paramInt + ")\n - " + localObject1 + "\n - " + localMethod);
        }
      }
      finally {}
      int i = 0;
      continue;
      label140:
      Object localObject2 = localMethod;
      label143:
      Method localMethod = localMethod.next;
      localObject1 = localObject2;
    }
    return (Method)localObject1;
  }
  
  public Method getDeclaredMethod(String paramString, Type[] paramArrayOfType)
  {
    int i;
    Method localMethod;
    if (("<init>".equals(paramString)) && (hasOuterLink()))
    {
      i = 1;
      localMethod = getDeclaredMethods();
      label25:
      if (localMethod == null) {
        break label166;
      }
      if (paramString.equals(localMethod.getName())) {
        break label54;
      }
    }
    label54:
    int k;
    do
    {
      Type[] arrayOfType;
      int j;
      do
      {
        localMethod = localMethod.next;
        break label25;
        i = 0;
        break;
        arrayOfType = localMethod.getParameterTypes();
        if ((paramArrayOfType == null) || ((paramArrayOfType == arrayOfType) && (i == 0))) {
          return localMethod;
        }
        j = paramArrayOfType.length;
      } while (j != arrayOfType.length - i);
      Type localType1;
      Type localType2;
      do
      {
        do
        {
          do
          {
            k = j - 1;
            if (k < 0) {
              break;
            }
            localType1 = arrayOfType[(k + i)];
            localType2 = paramArrayOfType[k];
            j = k;
          } while (localType1 == localType2);
          j = k;
        } while (localType2 == null);
        j = k;
      } while (localType1.getSignature().equals(localType2.getSignature()));
    } while (k >= 0);
    return localMethod;
    label166:
    return null;
  }
  
  public final Method getDeclaredMethods()
  {
    try
    {
      if ((this.flags & 0x12) == 16) {
        addMethods(getReflectClass());
      }
      Method localMethod = this.methods;
      return localMethod;
    }
    finally {}
  }
  
  public ClassType getDeclaringClass()
  {
    addEnclosingMember();
    if ((this.enclosingMember instanceof ClassType)) {
      return (ClassType)this.enclosingMember;
    }
    return null;
  }
  
  public Member getEnclosingMember()
  {
    addEnclosingMember();
    return this.enclosingMember;
  }
  
  public Field getField(String paramString)
  {
    return getField(paramString, 1);
  }
  
  public Field getField(String paramString, int paramInt)
  {
    Object localObject1 = this;
    label111:
    for (;;)
    {
      try
      {
        Object localObject2 = ((ClassType)localObject1).getDeclaredField(paramString);
        int i;
        if (localObject2 != null) {
          if (paramInt != -1)
          {
            i = ((Field)localObject2).getModifiers();
            if ((i & paramInt) == 0) {}
          }
          else
          {
            paramString = (String)localObject2;
            return paramString;
          }
        }
        ClassType[] arrayOfClassType = ((ClassType)localObject1).getInterfaces();
        if (arrayOfClassType != null)
        {
          i = 0;
          if (i < arrayOfClassType.length)
          {
            localObject2 = arrayOfClassType[i].getField(paramString, paramInt);
            if (localObject2 == null) {
              break label111;
            }
            paramString = (String)localObject2;
            continue;
          }
        }
        localObject2 = ((ClassType)localObject1).getSuperclass();
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          paramString = null;
          continue;
          i += 1;
        }
      }
      finally {}
    }
  }
  
  public final int getFieldCount()
  {
    return this.fields_count;
  }
  
  public final Field getFields()
  {
    try
    {
      if ((this.flags & 0x11) == 16) {
        addFields();
      }
      Field localField = this.fields;
      return localField;
    }
    finally {}
  }
  
  public ClassType[] getInterfaces()
  {
    try
    {
      if ((this.interfaces == null) && ((this.flags & 0x10) != 0) && (getReflectClass() != null))
      {
        Class[] arrayOfClass = this.reflectClass.getInterfaces();
        int j = arrayOfClass.length;
        if (j == 0) {}
        for (arrayOfClassType = noClasses;; arrayOfClassType = new ClassType[j])
        {
          this.interfaces = arrayOfClassType;
          int i = 0;
          while (i < j)
          {
            this.interfaces[i] = ((ClassType)Type.make(arrayOfClass[i]));
            i += 1;
          }
        }
      }
      ClassType[] arrayOfClassType = this.interfaces;
      return arrayOfClassType;
    }
    finally {}
  }
  
  public Method[] getMatchingMethods(String paramString, Type[] paramArrayOfType, int paramInt)
  {
    int i = 0;
    Vector localVector = new Vector(10);
    Method localMethod = this.methods;
    if (localMethod != null)
    {
      int j;
      if (!paramString.equals(localMethod.getName())) {
        j = i;
      }
      for (;;)
      {
        localMethod = localMethod.getNext();
        i = j;
        break;
        j = i;
        if ((paramInt & 0x8) == (localMethod.access_flags & 0x8))
        {
          j = i;
          if ((paramInt & 0x1) <= (localMethod.access_flags & 0x1))
          {
            j = i;
            if (localMethod.arg_types.length == paramArrayOfType.length)
            {
              j = i + 1;
              localVector.addElement(localMethod);
            }
          }
        }
      }
    }
    paramString = new Method[i];
    localVector.copyInto(paramString);
    return paramString;
  }
  
  /* Error */
  public Method getMethod(String paramString, Type[] paramArrayOfType)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: astore_3
    //   4: aload_3
    //   5: aload_1
    //   6: aload_2
    //   7: invokevirtual 231	gnu/bytecode/ClassType:getDeclaredMethod	(Ljava/lang/String;[Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   10: astore 4
    //   12: aload 4
    //   14: ifnull +10 -> 24
    //   17: aload 4
    //   19: astore_3
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_3
    //   23: areturn
    //   24: aload_3
    //   25: invokevirtual 526	gnu/bytecode/ClassType:getSuperclass	()Lgnu/bytecode/ClassType;
    //   28: astore 4
    //   30: aload 4
    //   32: astore_3
    //   33: aload 4
    //   35: ifnonnull -31 -> 4
    //   38: aload_0
    //   39: astore 4
    //   41: aload 4
    //   43: invokevirtual 523	gnu/bytecode/ClassType:getInterfaces	()[Lgnu/bytecode/ClassType;
    //   46: astore 6
    //   48: aload 6
    //   50: ifnull +43 -> 93
    //   53: iconst_0
    //   54: istore 7
    //   56: iload 7
    //   58: aload 6
    //   60: arraylength
    //   61: if_icmpge +32 -> 93
    //   64: aload 6
    //   66: iload 7
    //   68: aaload
    //   69: aload_1
    //   70: aload_2
    //   71: invokevirtual 231	gnu/bytecode/ClassType:getDeclaredMethod	(Ljava/lang/String;[Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   74: astore 5
    //   76: aload 5
    //   78: astore_3
    //   79: aload 5
    //   81: ifnonnull -61 -> 20
    //   84: iload 7
    //   86: iconst_1
    //   87: iadd
    //   88: istore 7
    //   90: goto -34 -> 56
    //   93: aload 4
    //   95: invokevirtual 526	gnu/bytecode/ClassType:getSuperclass	()Lgnu/bytecode/ClassType;
    //   98: astore_3
    //   99: aload_3
    //   100: astore 4
    //   102: aload_3
    //   103: ifnonnull -62 -> 41
    //   106: aconst_null
    //   107: astore_3
    //   108: goto -88 -> 20
    //   111: astore_1
    //   112: aload_0
    //   113: monitorexit
    //   114: aload_1
    //   115: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	116	0	this	ClassType
    //   0	116	1	paramString	String
    //   0	116	2	paramArrayOfType	Type[]
    //   3	105	3	localObject1	Object
    //   10	91	4	localObject2	Object
    //   74	6	5	localMethod	Method
    //   46	19	6	arrayOfClassType	ClassType[]
    //   54	35	7	i	int
    // Exception table:
    //   from	to	target	type
    //   4	12	111	finally
    //   24	30	111	finally
    //   41	48	111	finally
    //   56	76	111	finally
    //   93	99	111	finally
  }
  
  public Method getMethod(java.lang.reflect.Method paramMethod)
  {
    String str = paramMethod.getName();
    Class[] arrayOfClass = paramMethod.getParameterTypes();
    Type[] arrayOfType = new Type[arrayOfClass.length];
    int i = arrayOfClass.length;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      arrayOfType[i] = Type.make(arrayOfClass[i]);
    }
    return addMethod(str, paramMethod.getModifiers(), arrayOfType, Type.make(paramMethod.getReturnType()));
  }
  
  public final int getMethodCount()
  {
    return this.methods_count;
  }
  
  public int getMethods(Filter paramFilter, int paramInt, List<Method> paramList)
  {
    int i = 0;
    Object localObject1 = null;
    ClassType localClassType = this;
    for (;;)
    {
      int j = i;
      Object localObject2;
      if (localClassType != null)
      {
        String str = localClassType.getPackageName();
        localObject2 = localClassType.getDeclaredMethods();
        if (localObject2 != null)
        {
          if (localClassType != this)
          {
            j = ((Method)localObject2).getModifiers();
            if ((j & 0x2) != 0) {
              j = i;
            }
          }
          for (;;)
          {
            localObject2 = ((Method)localObject2).getNext();
            i = j;
            break;
            if ((j & 0x5) == 0)
            {
              j = i;
              if (!str.equals(localObject1)) {}
            }
            else
            {
              j = i;
              if (paramFilter.select(localObject2))
              {
                if (paramList != null) {
                  paramList.add(localObject2);
                }
                j = i + 1;
              }
            }
          }
        }
        localObject1 = str;
        if (paramInt == 0) {
          j = i;
        }
      }
      else
      {
        return j;
      }
      j = i;
      if (paramInt > 1)
      {
        localObject2 = localClassType.getInterfaces();
        j = i;
        if (localObject2 != null)
        {
          int k = 0;
          for (;;)
          {
            j = i;
            if (k >= localObject2.length) {
              break;
            }
            i += localObject2[k].getMethods(paramFilter, paramInt, paramList);
            k += 1;
          }
        }
      }
      localClassType = localClassType.getSuperclass();
      i = j;
    }
  }
  
  public int getMethods(Filter paramFilter, int paramInt1, Method[] paramArrayOfMethod, int paramInt2)
  {
    Vector localVector = new Vector();
    getMethods(paramFilter, paramInt1, localVector);
    int i = localVector.size();
    paramInt1 = 0;
    while (paramInt1 < i)
    {
      paramArrayOfMethod[(paramInt2 + paramInt1)] = ((Method)localVector.elementAt(paramInt1));
      paramInt1 += 1;
    }
    return i;
  }
  
  public final Method getMethods()
  {
    return this.methods;
  }
  
  public Method[] getMethods(Filter paramFilter, int paramInt)
  {
    Vector localVector = new Vector();
    getMethods(paramFilter, paramInt, localVector);
    int i = localVector.size();
    paramFilter = new Method[i];
    paramInt = 0;
    while (paramInt < i)
    {
      paramFilter[paramInt] = ((Method)localVector.elementAt(paramInt));
      paramInt += 1;
    }
    return paramFilter;
  }
  
  public Method[] getMethods(Filter paramFilter, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0) {
      return getMethods(paramFilter, i);
    }
  }
  
  public final int getModifiers()
  {
    try
    {
      if ((this.access_flags == 0) && ((this.flags & 0x10) != 0) && (getReflectClass() != null)) {
        this.access_flags = this.reflectClass.getModifiers();
      }
      int i = this.access_flags;
      return i;
    }
    finally {}
  }
  
  public ClassType getOuterLinkType()
  {
    if (!hasOuterLink()) {
      return null;
    }
    return (ClassType)getDeclaredField("this$0").getType();
  }
  
  public String getPackageName()
  {
    String str = getName();
    int i = str.lastIndexOf('.');
    if (i < 0) {
      return "";
    }
    return str.substring(0, i);
  }
  
  /* Error */
  public String getSimpleName()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 115	gnu/bytecode/ClassType:flags	I
    //   6: bipush 16
    //   8: iand
    //   9: ifeq +25 -> 34
    //   12: aload_0
    //   13: invokevirtual 119	gnu/bytecode/ClassType:getReflectClass	()Ljava/lang/Class;
    //   16: astore_1
    //   17: aload_1
    //   18: ifnull +16 -> 34
    //   21: aload_0
    //   22: getfield 535	gnu/bytecode/ClassType:reflectClass	Ljava/lang/Class;
    //   25: invokevirtual 590	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   28: astore_2
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_2
    //   32: areturn
    //   33: astore_1
    //   34: aload_0
    //   35: invokevirtual 338	gnu/bytecode/ClassType:getName	()Ljava/lang/String;
    //   38: astore_2
    //   39: aload_2
    //   40: bipush 46
    //   42: invokevirtual 581	java/lang/String:lastIndexOf	(I)I
    //   45: istore_3
    //   46: aload_2
    //   47: astore_1
    //   48: iload_3
    //   49: ifle +11 -> 60
    //   52: aload_2
    //   53: iload_3
    //   54: iconst_1
    //   55: iadd
    //   56: invokevirtual 593	java/lang/String:substring	(I)Ljava/lang/String;
    //   59: astore_1
    //   60: aload_1
    //   61: bipush 36
    //   63: invokevirtual 581	java/lang/String:lastIndexOf	(I)I
    //   66: istore_3
    //   67: aload_1
    //   68: astore_2
    //   69: iload_3
    //   70: iflt -41 -> 29
    //   73: aload_1
    //   74: invokevirtual 107	java/lang/String:length	()I
    //   77: istore 4
    //   79: iload_3
    //   80: iconst_1
    //   81: iadd
    //   82: istore_3
    //   83: iload_3
    //   84: iload 4
    //   86: if_icmpge +31 -> 117
    //   89: aload_1
    //   90: iload_3
    //   91: invokevirtual 111	java/lang/String:charAt	(I)C
    //   94: istore 5
    //   96: iload 5
    //   98: bipush 48
    //   100: if_icmplt +17 -> 117
    //   103: iload 5
    //   105: bipush 57
    //   107: if_icmpgt +10 -> 117
    //   110: iload_3
    //   111: iconst_1
    //   112: iadd
    //   113: istore_3
    //   114: goto -31 -> 83
    //   117: aload_1
    //   118: iload_3
    //   119: invokevirtual 593	java/lang/String:substring	(I)Ljava/lang/String;
    //   122: astore_2
    //   123: goto -94 -> 29
    //   126: astore_1
    //   127: aload_0
    //   128: monitorexit
    //   129: aload_1
    //   130: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	131	0	this	ClassType
    //   16	2	1	localClass	Class
    //   33	1	1	localThrowable	Throwable
    //   47	71	1	localObject1	Object
    //   126	4	1	localObject2	Object
    //   28	95	2	localObject3	Object
    //   45	74	3	i	int
    //   77	10	4	j	int
    //   94	14	5	k	int
    // Exception table:
    //   from	to	target	type
    //   21	29	33	java/lang/Throwable
    //   2	17	126	finally
    //   21	29	126	finally
    //   34	46	126	finally
    //   52	60	126	finally
    //   60	67	126	finally
    //   73	79	126	finally
    //   89	96	126	finally
    //   117	123	126	finally
  }
  
  public final boolean getStaticFlag()
  {
    return (getModifiers() & 0x8) != 0;
  }
  
  public ClassType getSuperclass()
  {
    try
    {
      if ((this.superClass == null) && (!isInterface()) && (!"java.lang.Object".equals(getName())) && ((this.flags & 0x10) != 0) && (getReflectClass() != null)) {
        this.superClass = ((ClassType)make(this.reflectClass.getSuperclass()));
      }
      ClassType localClassType = this.superClass;
      return localClassType;
    }
    finally {}
  }
  
  public final boolean hasOuterLink()
  {
    getFields();
    return (this.flags & 0x20) != 0;
  }
  
  public final boolean implementsInterface(ClassType paramClassType)
  {
    if (this == paramClassType) {}
    do
    {
      return true;
      localObject = getSuperclass();
    } while ((localObject != null) && (((ClassType)localObject).implementsInterface(paramClassType)));
    Object localObject = getInterfaces();
    if (localObject != null)
    {
      int i = localObject.length;
      int j;
      do
      {
        j = i - 1;
        if (j < 0) {
          break;
        }
        i = j;
      } while (!localObject[j].implementsInterface(paramClassType));
      return true;
    }
    return false;
  }
  
  public boolean isAccessible(ClassType paramClassType, ObjectType paramObjectType, int paramInt)
  {
    int i = paramClassType.getModifiers();
    if (((paramInt & 0x1) != 0) && ((i & 0x1) != 0)) {}
    label126:
    label132:
    label134:
    do
    {
      for (;;)
      {
        return true;
        String str1 = getName();
        String str2 = paramClassType.getName();
        if (!str1.equals(str2))
        {
          if ((paramInt & 0x2) != 0) {
            return false;
          }
          int j = str1.lastIndexOf('.');
          if (j >= 0)
          {
            str1 = str1.substring(0, j);
            j = str2.lastIndexOf('.');
            if (j < 0) {
              break label126;
            }
          }
          for (str2 = str2.substring(0, j);; str2 = "")
          {
            if (str1.equals(str2)) {
              break label132;
            }
            if ((i & 0x1) != 0) {
              break label134;
            }
            return false;
            str1 = "";
            break;
          }
        }
      }
    } while (((paramInt & 0x4) != 0) && (isSubclass(paramClassType)) && ((!(paramObjectType instanceof ClassType)) || (((ClassType)paramObjectType).isSubclass(this))));
    return false;
  }
  
  public boolean isAccessible(Member paramMember, ObjectType paramObjectType)
  {
    if (paramMember.getStaticFlag()) {
      paramObjectType = null;
    }
    return isAccessible(paramMember.getDeclaringClass(), paramObjectType, paramMember.getModifiers());
  }
  
  public final boolean isInterface()
  {
    return (getModifiers() & 0x200) != 0;
  }
  
  public final boolean isSubclass(ClassType paramClassType)
  {
    boolean bool2 = true;
    boolean bool1;
    if (paramClassType.isInterface()) {
      bool1 = implementsInterface(paramClassType);
    }
    do
    {
      do
      {
        return bool1;
        if (this != toStringType) {
          break;
        }
        bool1 = bool2;
      } while (paramClassType == javalangStringType);
      if (this != javalangStringType) {
        break;
      }
      bool1 = bool2;
    } while (paramClassType == toStringType);
    for (ClassType localClassType = this;; localClassType = localClassType.getSuperclass())
    {
      if (localClassType == null) {
        break label74;
      }
      bool1 = bool2;
      if (localClassType == paramClassType) {
        break;
      }
    }
    label74:
    return false;
  }
  
  public final boolean isSubclass(String paramString)
  {
    Object localObject = this;
    ClassType localClassType;
    do
    {
      if (paramString.equals(((ClassType)localObject).getName())) {
        return true;
      }
      localClassType = ((ClassType)localObject).getSuperclass();
      localObject = localClassType;
    } while (localClassType != null);
    return false;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    setName(paramObjectInput.readUTF());
    this.flags |= 0x10;
  }
  
  public Object readResolve()
    throws ObjectStreamException
  {
    String str = getName();
    synchronized (mapNameToType)
    {
      Type localType = (Type)???.get(str);
      if (localType != null) {
        return localType;
      }
      ???.put(str, this);
      return this;
    }
  }
  
  public final void setAttributes(Attribute paramAttribute)
  {
    this.attributes = paramAttribute;
  }
  
  public void setClassfileVersion(int paramInt)
  {
    this.classfileFormatVersion = paramInt;
  }
  
  public void setClassfileVersion(int paramInt1, int paramInt2)
  {
    this.classfileFormatVersion = ((paramInt1 & 0xFFFF) * 65536 + paramInt2 * 65535);
  }
  
  public void setClassfileVersionJava5()
  {
    setClassfileVersion(3211264);
  }
  
  public void setEnclosingMember(Member paramMember)
  {
    this.enclosingMember = paramMember;
  }
  
  public final void setInterface(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.access_flags |= 0x600;
      return;
    }
    this.access_flags &= 0xFDFF;
  }
  
  public void setInterfaces(ClassType[] paramArrayOfClassType)
  {
    this.interfaces = paramArrayOfClassType;
  }
  
  public final void setModifiers(int paramInt)
  {
    this.access_flags = paramInt;
  }
  
  public void setName(String paramString)
  {
    this.this_name = paramString;
    setSignature("L" + paramString.replace('.', '/') + ";");
  }
  
  public final Field setOuterLink(ClassType paramClassType)
  {
    if ((this.flags & 0x10) != 0) {
      throw new Error("setOuterLink called for existing class " + getName());
    }
    Object localObject1 = getDeclaredField("this$0");
    if (localObject1 == null)
    {
      Field localField = addField("this$0", paramClassType);
      this.flags |= 0x20;
      for (localObject1 = this.methods;; localObject1 = ((Method)localObject1).getNext())
      {
        localObject2 = localField;
        if (localObject1 == null) {
          break;
        }
        if ("<init>".equals(((Method)localObject1).getName()))
        {
          if (((Method)localObject1).code != null) {
            throw new Error("setOuterLink called when " + localObject1 + " has code");
          }
          localObject2 = ((Method)localObject1).arg_types;
          Type[] arrayOfType = new Type[localObject2.length + 1];
          System.arraycopy(localObject2, 0, arrayOfType, 1, localObject2.length);
          arrayOfType[0] = paramClassType;
          ((Method)localObject1).arg_types = arrayOfType;
          ((Method)localObject1).signature = null;
        }
      }
    }
    Object localObject2 = localObject1;
    if (!paramClassType.equals(((Field)localObject1).getType())) {
      throw new Error("inconsistent setOuterLink call for " + getName());
    }
    return (Field)localObject2;
  }
  
  public void setSourceFile(String paramString)
  {
    if (this.sourceDbgExt != null)
    {
      this.sourceDbgExt.addFile(paramString);
      if (this.sourceDbgExt.fileCount > 1) {
        return;
      }
    }
    String str = SourceFileAttr.fixSourceFile(paramString);
    int i = str.lastIndexOf('/');
    paramString = str;
    if (i >= 0) {
      paramString = str.substring(i + 1);
    }
    SourceFileAttr.setSourceFile(this, paramString);
  }
  
  public void setStratum(String paramString)
  {
    if (this.sourceDbgExt == null) {
      this.sourceDbgExt = new SourceDebugExtAttr(this);
    }
    this.sourceDbgExt.addStratum(paramString);
  }
  
  public void setSuper(ClassType paramClassType)
  {
    this.superClass = paramClassType;
  }
  
  public void setSuper(String paramString)
  {
    if (paramString == null) {}
    for (paramString = Type.pointer_type;; paramString = make(paramString))
    {
      setSuper(paramString);
      return;
    }
  }
  
  public String toString()
  {
    return "ClassType " + getName();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeUTF(getName());
  }
  
  public byte[] writeToArray()
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(500);
    try
    {
      writeToStream(localByteArrayOutputStream);
      return localByteArrayOutputStream.toByteArray();
    }
    catch (IOException localIOException)
    {
      throw new InternalError(localIOException.toString());
    }
  }
  
  public void writeToFile()
    throws IOException
  {
    writeToFile(this.this_name.replace('.', File.separatorChar) + ".class");
  }
  
  public void writeToFile(String paramString)
    throws IOException
  {
    paramString = new BufferedOutputStream(new FileOutputStream(paramString));
    writeToStream(paramString);
    paramString.close();
  }
  
  public void writeToStream(OutputStream paramOutputStream)
    throws IOException
  {
    DataOutputStream localDataOutputStream = new DataOutputStream(paramOutputStream);
    doFixups();
    localDataOutputStream.writeInt(-889275714);
    localDataOutputStream.writeShort(getClassfileMinorVersion());
    localDataOutputStream.writeShort(getClassfileMajorVersion());
    if (this.constants == null)
    {
      localDataOutputStream.writeShort(1);
      localDataOutputStream.writeShort(this.access_flags);
      localDataOutputStream.writeShort(this.thisClassIndex);
      localDataOutputStream.writeShort(this.superClassIndex);
      if (this.interfaceIndexes != null) {
        break label126;
      }
      localDataOutputStream.writeShort(0);
    }
    for (;;)
    {
      localDataOutputStream.writeShort(this.fields_count);
      for (paramOutputStream = this.fields; paramOutputStream != null; paramOutputStream = paramOutputStream.next) {
        paramOutputStream.write(localDataOutputStream, this);
      }
      this.constants.write(localDataOutputStream);
      break;
      label126:
      int j = this.interfaceIndexes.length;
      localDataOutputStream.writeShort(j);
      int i = 0;
      while (i < j)
      {
        localDataOutputStream.writeShort(this.interfaceIndexes[i]);
        i += 1;
      }
    }
    localDataOutputStream.writeShort(this.methods_count);
    for (paramOutputStream = this.methods; paramOutputStream != null; paramOutputStream = paramOutputStream.next) {
      paramOutputStream.write(localDataOutputStream, this);
    }
    Attribute.writeAll(this, localDataOutputStream);
    this.flags |= 0x3;
  }
  
  static class AbstractMethodFilter
    implements Filter
  {
    public static final AbstractMethodFilter instance = new AbstractMethodFilter();
    
    public boolean select(Object paramObject)
    {
      return ((Method)paramObject).isAbstract();
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\ClassType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */