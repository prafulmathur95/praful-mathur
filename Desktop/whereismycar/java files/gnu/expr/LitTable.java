package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.lists.FString;
import gnu.mapping.Symbol;
import gnu.mapping.Table2D;
import gnu.mapping.Values;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.IdentityHashMap;
import java.util.regex.Pattern;

public class LitTable
  implements ObjectOutput
{
  static Table2D staticTable = new Table2D(100);
  Compilation comp;
  IdentityHashMap literalTable = new IdentityHashMap(100);
  Literal literalsChain;
  int literalsCount;
  ClassType mainClass;
  int stackPointer;
  Type[] typeStack = new Type[20];
  Object[] valueStack = new Object[20];
  
  public LitTable(Compilation paramCompilation)
  {
    this.comp = paramCompilation;
    this.mainClass = paramCompilation.mainClass;
  }
  
  private void store(Literal paramLiteral, boolean paramBoolean, CodeAttr paramCodeAttr)
  {
    if (paramLiteral.field != null)
    {
      if (!paramBoolean) {
        paramCodeAttr.emitDup(paramLiteral.type);
      }
      paramCodeAttr.emitPutStatic(paramLiteral.field);
    }
    paramLiteral.flags |= 0x8;
  }
  
  public void close() {}
  
  public void emit()
    throws IOException
  {
    for (Literal localLiteral = this.literalsChain; localLiteral != null; localLiteral = localLiteral.next) {
      writeObject(localLiteral.value);
    }
    for (localLiteral = this.literalsChain; localLiteral != null; localLiteral = localLiteral.next) {
      emit(localLiteral, true);
    }
    this.literalTable = null;
    this.literalsCount = 0;
  }
  
  void emit(Literal paramLiteral, boolean paramBoolean)
  {
    CodeAttr localCodeAttr = this.comp.getCode();
    if (paramLiteral.value == null) {
      if (!paramBoolean) {
        localCodeAttr.emitPushNull();
      }
    }
    do
    {
      do
      {
        return;
        if (!(paramLiteral.value instanceof String)) {
          break;
        }
      } while (paramBoolean);
      localCodeAttr.emitPushString(paramLiteral.value.toString());
      return;
      if ((paramLiteral.flags & 0x8) == 0) {
        break;
      }
    } while (paramBoolean);
    localCodeAttr.emitGetStatic(paramLiteral.field);
    return;
    int j;
    Object localObject1;
    int i;
    label129:
    Object localObject2;
    if ((paramLiteral.value instanceof Object[]))
    {
      j = paramLiteral.argValues.length;
      localObject1 = ((ArrayType)paramLiteral.type).getComponentType();
      localCodeAttr.emitPushInt(j);
      localCodeAttr.emitNewArray((Type)localObject1);
      store(paramLiteral, paramBoolean, localCodeAttr);
      i = 0;
      if (i < j)
      {
        localObject2 = (Literal)paramLiteral.argValues[i];
        if (((Literal)localObject2).value != null) {
          break label165;
        }
      }
      for (;;)
      {
        i += 1;
        break label129;
        break;
        label165:
        localCodeAttr.emitDup((Type)localObject1);
        localCodeAttr.emitPushInt(i);
        emit((Literal)localObject2, false);
        localCodeAttr.emitArrayStore((Type)localObject1);
      }
    }
    if ((paramLiteral.type instanceof ArrayType))
    {
      localCodeAttr.emitPushPrimArray(paramLiteral.value, (ArrayType)paramLiteral.type);
      store(paramLiteral, paramBoolean, localCodeAttr);
      return;
    }
    if ((paramLiteral.value instanceof Class))
    {
      localObject1 = (Class)paramLiteral.value;
      if (((Class)localObject1).isPrimitive())
      {
        localObject2 = ((Class)localObject1).getName();
        localObject1 = localObject2;
        if (((String)localObject2).equals("int")) {
          localObject1 = "integer";
        }
        localCodeAttr.emitGetStatic(ClassType.make("java.lang." + Character.toUpperCase(((String)localObject1).charAt(0)) + ((String)localObject1).substring(1)).getDeclaredField("TYPE"));
      }
      for (;;)
      {
        store(paramLiteral, paramBoolean, localCodeAttr);
        return;
        this.comp.loadClassRef((ObjectType)Type.make((Class)localObject1));
      }
    }
    if (((paramLiteral.value instanceof ClassType)) && (!((ClassType)paramLiteral.value).isExisting()))
    {
      this.comp.loadClassRef((ClassType)paramLiteral.value);
      localObject2 = Compilation.typeType.getDeclaredMethod("valueOf", 1);
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = Compilation.typeType.getDeclaredMethod("make", 1);
      }
      localCodeAttr.emitInvokeStatic((Method)localObject1);
      localCodeAttr.emitCheckcast(Compilation.typeClassType);
      store(paramLiteral, paramBoolean, localCodeAttr);
      return;
    }
    ClassType localClassType = (ClassType)paramLiteral.type;
    label459:
    int n;
    int m;
    if ((paramLiteral.flags & 0x4) != 0)
    {
      i = 1;
      localObject1 = null;
      localObject2 = null;
      int k = 0;
      n = 0;
      m = i;
      if (i == 0)
      {
        localObject1 = localObject2;
        if (!(paramLiteral.value instanceof Symbol)) {
          localObject1 = getMethod(localClassType, "valueOf", paramLiteral, true);
        }
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          localObject2 = localObject1;
          if (!(paramLiteral.value instanceof Values))
          {
            localObject1 = "make";
            if ((paramLiteral.value instanceof Pattern)) {
              localObject1 = "compile";
            }
            localObject2 = getMethod(localClassType, (String)localObject1, paramLiteral, true);
          }
        }
        if (localObject2 == null) {
          break label758;
        }
        j = 1;
        label558:
        k = j;
        localObject1 = localObject2;
        m = i;
        if (localObject2 == null)
        {
          m = 1;
          localObject1 = localObject2;
          k = j;
        }
      }
      if (m != 0) {
        localObject1 = getMethod(localClassType, "set", paramLiteral, false);
      }
      if ((localObject1 == null) && (paramLiteral.argTypes.length > 0)) {
        error("no method to construct " + paramLiteral.type);
      }
      if (k == 0) {
        break label790;
      }
      putArgs(paramLiteral, localCodeAttr);
      localCodeAttr.emitInvokeStatic((Method)localObject1);
      label656:
      if ((k == 0) && (!(paramLiteral.value instanceof Values))) {
        break label856;
      }
      localObject2 = null;
      label674:
      if (localObject2 != null)
      {
        localCodeAttr.emitInvokeVirtual((Method)localObject2);
        localClassType.emitCoerceFromObject(localCodeAttr);
      }
      if ((!paramBoolean) || ((m != 0) && (localObject1 != null))) {
        break label870;
      }
    }
    label758:
    label790:
    label856:
    label870:
    for (boolean bool = true;; bool = false)
    {
      store(paramLiteral, bool, localCodeAttr);
      if ((m == 0) || (localObject1 == null)) {
        break;
      }
      if (!paramBoolean) {
        localCodeAttr.emitDup(localClassType);
      }
      putArgs(paramLiteral, localCodeAttr);
      localCodeAttr.emitInvokeVirtual((Method)localObject1);
      return;
      i = 0;
      break label459;
      j = n;
      if (paramLiteral.argTypes.length <= 0) {
        break label558;
      }
      localObject2 = getMethod(localClassType, "<init>", paramLiteral, false);
      j = n;
      break label558;
      if (m != 0)
      {
        localCodeAttr.emitNew(localClassType);
        localCodeAttr.emitDup(localClassType);
        localCodeAttr.emitInvokeSpecial(localClassType.getDeclaredMethod("<init>", 0));
        break label656;
      }
      localCodeAttr.emitNew(localClassType);
      localCodeAttr.emitDup(localClassType);
      putArgs(paramLiteral, localCodeAttr);
      localCodeAttr.emitInvokeSpecial((Method)localObject1);
      break label656;
      localObject2 = localClassType.getDeclaredMethod("readResolve", 0);
      break label674;
    }
  }
  
  void error(String paramString)
  {
    throw new Error(paramString);
  }
  
  public Literal findLiteral(Object paramObject)
  {
    Object localObject1;
    if (paramObject == null) {
      localObject1 = Literal.nullLiteral;
    }
    Object localObject2;
    do
    {
      return (Literal)localObject1;
      localObject2 = (Literal)this.literalTable.get(paramObject);
      localObject1 = localObject2;
    } while (localObject2 != null);
    if (this.comp.immediate) {
      return new Literal(paramObject, this);
    }
    Class localClass = paramObject.getClass();
    Type localType = Type.make(localClass);
    for (;;)
    {
      synchronized (staticTable)
      {
        localObject1 = (Literal)staticTable.get(paramObject, null, null);
        if (localObject1 != null)
        {
          localObject2 = localObject1;
          if (((Literal)localObject1).value == paramObject) {
            break label323;
          }
        }
        localObject2 = localObject1;
        if (!(localType instanceof ClassType)) {
          break label323;
        }
        Object localObject3 = (ClassType)localType;
        localObject2 = localObject1;
        if (staticTable.get(localClass, Boolean.TRUE, null) != null) {
          break label323;
        }
        staticTable.put(localClass, Boolean.TRUE, localClass);
        localObject2 = ((ClassType)localObject3).getFields();
        if (localObject2 != null)
        {
          int i = ((gnu.bytecode.Field)localObject2).getModifiers();
          localObject3 = localObject1;
          if ((i & 0x19) == 25) {}
          try
          {
            localObject5 = ((gnu.bytecode.Field)localObject2).getReflectField().get(null);
            localObject3 = localObject1;
            if (localObject5 != null)
            {
              boolean bool = localClass.isInstance(localObject5);
              if (bool) {
                continue;
              }
              localObject3 = localObject1;
            }
          }
          catch (Throwable localThrowable)
          {
            Object localObject5;
            Literal localLiteral;
            error("caught " + localThrowable + " getting static field " + localObject2);
            localObject4 = localObject1;
            continue;
          }
          localObject2 = ((gnu.bytecode.Field)localObject2).getNext();
          localObject1 = localObject3;
          continue;
          localLiteral = new Literal(localObject5, (gnu.bytecode.Field)localObject2, this);
          staticTable.put(localObject5, null, localLiteral);
          localObject3 = localObject1;
          if (paramObject != localObject5) {
            continue;
          }
          localObject3 = localLiteral;
        }
      }
      localClass = localClass.getSuperclass();
      if (localClass == null)
      {
        localObject2 = localObject1;
        label323:
        if (localObject2 == null) {
          break;
        }
        this.literalTable.put(paramObject, localObject2);
        return (Literal)localObject2;
      }
      Object localObject4 = (ClassType)Type.make(localClass);
    }
    return new Literal(paramObject, localType, this);
  }
  
  public void flush() {}
  
  Method getMethod(ClassType paramClassType, String paramString, Literal paramLiteral, boolean paramBoolean)
  {
    Object localObject7 = paramLiteral.argTypes;
    Object localObject2 = paramClassType.getDeclaredMethods();
    int i1 = localObject7.length;
    Object localObject1 = null;
    long l2 = 0L;
    int k = 0;
    Object localObject3 = null;
    Object localObject4;
    Object localObject6;
    int i;
    int m;
    if (localObject2 != null)
    {
      long l3;
      int n;
      if (!paramString.equals(((Method)localObject2).getName()))
      {
        l3 = l2;
        localObject5 = localObject1;
        localObject4 = localObject3;
        n = k;
      }
      long l1;
      label130:
      label183:
      label216:
      label279:
      label364:
      label370:
      do
      {
        do
        {
          do
          {
            for (;;)
            {
              localObject2 = ((Method)localObject2).getNext();
              k = n;
              localObject3 = localObject4;
              localObject1 = localObject5;
              l2 = l3;
              break;
              n = k;
              localObject4 = localObject3;
              localObject5 = localObject1;
              l3 = l2;
              if (paramBoolean == ((Method)localObject2).getStaticFlag())
              {
                l1 = 0L;
                localObject6 = ((Method)localObject2).getParameterTypes();
                i = 0;
                m = 0;
                if ((i != i1) || (m != localObject6.length)) {
                  break label370;
                }
                if ((localObject1 != null) && ((l2 == 0L) || (l1 != 0L))) {
                  break label183;
                }
                localObject5 = localObject2;
                localObject4 = localObject6;
                n = k;
                l3 = l1;
              }
            }
            n = k;
            localObject4 = localObject3;
            localObject5 = localObject1;
            l3 = l2;
          } while (l1 != 0L);
          i = 0;
          j = 0;
          m = i1;
          n = m - 1;
          m = i;
          k = j;
          int i2;
          if (n >= 0)
          {
            i2 = localObject3[n].compare(localObject6[n]);
            k = j;
            if (i2 != 1)
            {
              k = 1;
              j = 1;
              if (i != 0)
              {
                k = j;
                m = i;
              }
            }
          }
          else
          {
            if (m != 0)
            {
              localObject1 = localObject2;
              localObject3 = localObject6;
            }
            if ((m == 0) || (k == 0)) {
              break label364;
            }
          }
          for (i = 1;; i = 0)
          {
            n = i;
            localObject4 = localObject3;
            localObject5 = localObject1;
            l3 = l2;
            break;
            m = n;
            j = k;
            if (i2 == -1) {
              break label216;
            }
            i2 = 1;
            i = 1;
            m = n;
            j = k;
            if (k == 0) {
              break label216;
            }
            m = i2;
            break label279;
          }
          n = k;
          localObject4 = localObject3;
          localObject5 = localObject1;
          l3 = l2;
        } while (i == i1);
        n = k;
        localObject4 = localObject3;
        localObject5 = localObject1;
        l3 = l2;
      } while (m == localObject6.length);
      Type localType1 = localObject7[i];
      Type localType2 = localObject6[m];
      if (localType1.isSubtype(localType2)) {}
      for (;;)
      {
        i += 1;
        m += 1;
        break label130;
        n = k;
        localObject4 = localObject3;
        localObject5 = localObject1;
        l3 = l2;
        if (!(localType2 instanceof ArrayType)) {
          break;
        }
        n = k;
        localObject4 = localObject3;
        localObject5 = localObject1;
        l3 = l2;
        if (m >= 64) {
          break;
        }
        if (localType1 != Type.intType)
        {
          n = k;
          localObject4 = localObject3;
          localObject5 = localObject1;
          l3 = l2;
          if (localType1 != Type.shortType) {
            break;
          }
        }
        n = ((Number)paramLiteral.argValues[i]).intValue();
        j = n;
        if (n < 0)
        {
          j = n;
          if (paramClassType.getName().equals("gnu.math.IntNum")) {
            j = n + Integer.MIN_VALUE;
          }
        }
        localType1 = ((ArrayType)localType2).getComponentType();
        n = k;
        localObject4 = localObject3;
        localObject5 = localObject1;
        l3 = l2;
        if (j < 0) {
          break;
        }
        n = k;
        localObject4 = localObject3;
        localObject5 = localObject1;
        l3 = l2;
        if (i + j >= i1) {
          break;
        }
        n = j;
        do
        {
          do
          {
            n -= 1;
            if (n < 0) {
              break label737;
            }
            localObject4 = localObject7[(i + n + 1)];
            if (!(localType1 instanceof PrimType)) {
              break;
            }
          } while (localType1.getSignature() == ((Type)localObject4).getSignature());
          n = k;
          localObject4 = localObject3;
          localObject5 = localObject1;
          l3 = l2;
          break;
        } while (((Type)localObject4).isSubtype(localType1));
        n = k;
        localObject4 = localObject3;
        localObject5 = localObject1;
        l3 = l2;
        break;
        label737:
        i += j;
        l1 |= 1 << m;
      }
    }
    if (k != 0) {
      paramString = null;
    }
    do
    {
      return paramString;
      paramString = (String)localObject1;
    } while (l2 == 0L);
    paramString = new Object[localObject3.length];
    localObject2 = new Type[localObject3.length];
    int j = 0;
    k = 0;
    if (j == i1)
    {
      paramLiteral.argValues = paramString;
      paramLiteral.argTypes = ((Type[])localObject2);
      return (Method)localObject1;
    }
    Object localObject5 = localObject3[k];
    if ((1 << k & l2) == 0L)
    {
      paramString[k] = paramLiteral.argValues[j];
      localObject2[k] = paramLiteral.argTypes[j];
    }
    for (;;)
    {
      j += 1;
      k += 1;
      break;
      m = ((Number)paramLiteral.argValues[j]).intValue();
      paramBoolean = paramClassType.getName().equals("gnu.math.IntNum");
      i = m;
      if (paramBoolean) {
        i = m + Integer.MIN_VALUE;
      }
      localObject4 = ((ArrayType)localObject5).getComponentType();
      localObject2[k] = localObject5;
      paramString[k] = Array.newInstance(((Type)localObject4).getReflectClass(), i);
      localObject6 = paramLiteral.argValues;
      if (paramBoolean)
      {
        localObject7 = (int[])paramString[k];
        m = i;
        while (m > 0)
        {
          localObject7[(i - m)] = ((Integer)localObject6[(j + m)]).intValue();
          m -= 1;
        }
      }
      m = i;
      for (;;)
      {
        m -= 1;
        if (m < 0) {
          break;
        }
        Array.set(paramString[k], m, localObject6[(j + 1 + m)]);
      }
      localObject5 = new Literal(paramString[k], (Type)localObject5);
      if ((localObject4 instanceof ObjectType)) {
        ((Literal)localObject5).argValues = ((Object[])paramString[k]);
      }
      paramString[k] = localObject5;
      j += i;
    }
  }
  
  void push(Object paramObject, Type paramType)
  {
    if (this.stackPointer >= this.valueStack.length)
    {
      Object[] arrayOfObject = new Object[this.valueStack.length * 2];
      Type[] arrayOfType = new Type[this.typeStack.length * 2];
      System.arraycopy(this.valueStack, 0, arrayOfObject, 0, this.stackPointer);
      System.arraycopy(this.typeStack, 0, arrayOfType, 0, this.stackPointer);
      this.valueStack = arrayOfObject;
      this.typeStack = arrayOfType;
    }
    this.valueStack[this.stackPointer] = paramObject;
    this.typeStack[this.stackPointer] = paramType;
    this.stackPointer += 1;
  }
  
  void putArgs(Literal paramLiteral, CodeAttr paramCodeAttr)
  {
    paramCodeAttr = paramLiteral.argTypes;
    int j = paramCodeAttr.length;
    int i = 0;
    if (i < j)
    {
      Object localObject = paramLiteral.argValues[i];
      if ((localObject instanceof Literal)) {
        emit((Literal)localObject, false);
      }
      for (;;)
      {
        i += 1;
        break;
        this.comp.compileConstant(localObject, new StackTarget(paramCodeAttr[i]));
      }
    }
  }
  
  public void write(int paramInt)
    throws IOException
  {
    error("cannot handle call to write(int) when externalizing literal");
  }
  
  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    error("cannot handle call to write(byte[]) when externalizing literal");
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    error("cannot handle call to write(byte[],int,int) when externalizing literal");
  }
  
  public void writeBoolean(boolean paramBoolean)
  {
    push(new Boolean(paramBoolean), Type.booleanType);
  }
  
  public void writeByte(int paramInt)
  {
    push(new Byte((byte)paramInt), Type.byteType);
  }
  
  public void writeBytes(String paramString)
    throws IOException
  {
    error("cannot handle call to writeBytes(String) when externalizing literal");
  }
  
  public void writeChar(int paramInt)
  {
    push(new Character((char)paramInt), Type.charType);
  }
  
  public void writeChars(String paramString)
  {
    push(paramString, Type.string_type);
  }
  
  public void writeDouble(double paramDouble)
  {
    push(new Double(paramDouble), Type.doubleType);
  }
  
  public void writeFloat(float paramFloat)
  {
    push(new Float(paramFloat), Type.floatType);
  }
  
  public void writeInt(int paramInt)
  {
    push(new Integer(paramInt), Type.intType);
  }
  
  public void writeLong(long paramLong)
  {
    push(new Long(paramLong), Type.longType);
  }
  
  public void writeObject(Object paramObject)
    throws IOException
  {
    Literal localLiteral = findLiteral(paramObject);
    if ((localLiteral.flags & 0x3) != 0)
    {
      if ((localLiteral.field == null) && (paramObject != null) && (!(paramObject instanceof String))) {
        localLiteral.assign(this);
      }
      if ((localLiteral.flags & 0x2) == 0) {
        localLiteral.flags |= 0x4;
      }
      push(localLiteral, localLiteral.type);
      return;
    }
    localLiteral.flags |= 0x1;
    int j = this.stackPointer;
    label114:
    int i;
    if (((paramObject instanceof FString)) && (((FString)paramObject).size() < 65535))
    {
      push(paramObject.toString(), Type.string_type);
      i = this.stackPointer - j;
      if (i != 0) {
        break label497;
      }
      localLiteral.argValues = Values.noArgs;
      localLiteral.argTypes = Type.typeArray0;
    }
    for (;;)
    {
      localLiteral.flags |= 0x2;
      break;
      if ((paramObject instanceof Externalizable))
      {
        ((Externalizable)paramObject).writeExternal(this);
        break label114;
      }
      if ((paramObject instanceof Object[]))
      {
        paramObject = (Object[])paramObject;
        i = 0;
        while (i < paramObject.length)
        {
          writeObject(paramObject[i]);
          i += 1;
        }
        break label114;
      }
      if ((paramObject == null) || ((paramObject instanceof String)) || ((localLiteral.type instanceof ArrayType))) {
        break label114;
      }
      if ((paramObject instanceof BigInteger))
      {
        writeChars(paramObject.toString());
        break label114;
      }
      if ((paramObject instanceof BigDecimal))
      {
        paramObject = (BigDecimal)paramObject;
        writeObject(((BigDecimal)paramObject).unscaledValue());
        writeInt(((BigDecimal)paramObject).scale());
        break label114;
      }
      if ((paramObject instanceof Integer))
      {
        push(paramObject, Type.intType);
        break label114;
      }
      if ((paramObject instanceof Short))
      {
        push(paramObject, Type.shortType);
        break label114;
      }
      if ((paramObject instanceof Byte))
      {
        push(paramObject, Type.byteType);
        break label114;
      }
      if ((paramObject instanceof Long))
      {
        push(paramObject, Type.longType);
        break label114;
      }
      if ((paramObject instanceof Double))
      {
        push(paramObject, Type.doubleType);
        break label114;
      }
      if ((paramObject instanceof Float))
      {
        push(paramObject, Type.floatType);
        break label114;
      }
      if ((paramObject instanceof Character))
      {
        push(paramObject, Type.charType);
        break label114;
      }
      if ((paramObject instanceof Class))
      {
        push(paramObject, Type.java_lang_Class_type);
        break label114;
      }
      if ((paramObject instanceof Pattern))
      {
        paramObject = (Pattern)paramObject;
        push(((Pattern)paramObject).pattern(), Type.string_type);
        push(Integer.valueOf(((Pattern)paramObject).flags()), Type.intType);
        break label114;
      }
      error(paramObject.getClass().getName() + " does not implement Externalizable");
      break label114;
      label497:
      localLiteral.argValues = new Object[i];
      localLiteral.argTypes = new Type[i];
      System.arraycopy(this.valueStack, j, localLiteral.argValues, 0, i);
      System.arraycopy(this.typeStack, j, localLiteral.argTypes, 0, i);
      this.stackPointer = j;
    }
  }
  
  public void writeShort(int paramInt)
  {
    push(new Short((short)paramInt), Type.shortType);
  }
  
  public void writeUTF(String paramString)
  {
    push(paramString, Type.string_type);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\LitTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */