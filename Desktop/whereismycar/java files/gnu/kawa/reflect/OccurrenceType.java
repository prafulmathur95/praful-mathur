package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.lists.AbstractSequence;
import gnu.lists.ItemPredicate;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class OccurrenceType
  extends ObjectType
  implements Externalizable, TypeValue
{
  public static final Type emptySequenceType = getInstance(SingletonType.instance, 0, 0);
  static final Method isInstanceMethod = typeOccurrenceType.getDeclaredMethod("isInstance", 1);
  public static final ClassType typeOccurrenceType = ClassType.make("gnu.kawa.reflect.OccurrenceType");
  Type base;
  int maxOccurs;
  int minOccurs;
  
  public OccurrenceType(Type paramType, int paramInt1, int paramInt2)
  {
    this.base = paramType;
    this.minOccurs = paramInt1;
    this.maxOccurs = paramInt2;
  }
  
  public static Type getInstance(Type paramType, int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 1) && (paramInt2 == 1)) {
      return paramType;
    }
    if ((paramInt1 == 0) && (paramInt2 < 0) && ((paramType == SingletonType.instance) || (paramType == Type.pointer_type))) {
      return Type.pointer_type;
    }
    return new OccurrenceType(paramType, paramInt1, paramInt2);
  }
  
  public static char itemCountCode(Type paramType)
  {
    int j = itemCountRange(paramType);
    int i = j & 0xFFF;
    j >>= 12;
    if (j == 0) {
      return '0';
    }
    if (i == 0)
    {
      if (j == 1) {
        return '?';
      }
      return '*';
    }
    if ((i == 1) && (j == 1)) {
      return '1';
    }
    return '+';
  }
  
  public static boolean itemCountIsOne(Type paramType)
  {
    return itemCountRange(paramType) == 4097;
  }
  
  public static boolean itemCountIsZeroOrOne(Type paramType)
  {
    return itemCountRange(paramType) >> 13 == 0;
  }
  
  public static int itemCountRange(Type paramType)
  {
    int i = 0;
    if ((paramType instanceof SingletonType)) {}
    label168:
    do
    {
      return 4097;
      if ((paramType instanceof OccurrenceType))
      {
        paramType = (OccurrenceType)paramType;
        int j = paramType.minOccurs();
        int k = paramType.maxOccurs();
        int i1 = itemCountRange(paramType.getBase());
        if (((j == 1) && (k == 1)) || (i1 == 0)) {
          return i1;
        }
        i = k;
        if (k > 1048575) {
          i = -1;
        }
        if (i == 0) {
          return 0;
        }
        int n = i1 >> 12;
        k = i;
        int m = j;
        if (i1 != 4097)
        {
          k = j;
          if (j > 4095) {
            k = 4095;
          }
          k *= (i1 & 0xFFF);
          j = k;
          if (k > 4095) {
            j = 4095;
          }
          if ((i >= 0) && (n >= 0)) {
            break label168;
          }
          i = -1;
        }
        for (;;)
        {
          k = i;
          m = j;
          if (i > 1048575)
          {
            k = -1;
            m = j;
          }
          return k << 12 | m;
          i *= n;
        }
      }
      if ((paramType instanceof PrimType))
      {
        if (paramType.isVoid()) {}
        for (;;)
        {
          return i;
          i = 4097;
        }
      }
    } while (((paramType instanceof ArrayType)) || (((paramType instanceof ObjectType)) && (paramType.compare(Compilation.typeValues) == -3)));
    return 61440;
  }
  
  public static Type itemPrimeType(Type paramType)
  {
    while ((paramType instanceof OccurrenceType)) {
      paramType = ((OccurrenceType)paramType).getBase();
    }
    if (itemCountIsOne(paramType)) {
      return paramType;
    }
    return SingletonType.instance;
  }
  
  public Object coerceFromObject(Object paramObject)
  {
    if ((paramObject instanceof Values)) {}
    while ((this.minOccurs > 1) || (this.maxOccurs == 0))
    {
      localObject = paramObject;
      if (isInstance(paramObject)) {
        break;
      }
      throw new ClassCastException();
    }
    Object localObject = this.base.coerceFromObject(paramObject);
    return localObject;
  }
  
  public int compare(Type paramType)
  {
    if ((paramType instanceof OccurrenceType))
    {
      paramType = (OccurrenceType)paramType;
      if ((this.minOccurs == paramType.minOccurs) && (this.maxOccurs == paramType.maxOccurs)) {
        return this.base.compare(paramType.getBase());
      }
    }
    return -2;
  }
  
  public Expression convertValue(Expression paramExpression)
  {
    return null;
  }
  
  public void emitIsInstance(Variable paramVariable, Compilation paramCompilation, Target paramTarget)
  {
    InstanceOf.emitIsInstance(this, paramVariable, paramCompilation, paramTarget);
  }
  
  public void emitTestIf(Variable paramVariable, Declaration paramDeclaration, Compilation paramCompilation)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    if (paramVariable != null) {
      localCodeAttr.emitLoad(paramVariable);
    }
    if (paramDeclaration != null)
    {
      localCodeAttr.emitDup();
      paramDeclaration.compileStore(paramCompilation);
    }
    paramCompilation.compileConstant(this);
    localCodeAttr.emitSwap();
    localCodeAttr.emitInvokeVirtual(isInstanceMethod);
    localCodeAttr.emitIfIntNotZero();
  }
  
  public Type getBase()
  {
    return this.base;
  }
  
  public Procedure getConstructor()
  {
    return null;
  }
  
  public Type getImplementationType()
  {
    return Type.pointer_type;
  }
  
  public boolean isInstance(Object paramObject)
  {
    boolean bool4 = true;
    boolean bool2 = true;
    boolean bool3 = false;
    int i;
    int j;
    int k;
    Object localObject;
    boolean bool1;
    if ((paramObject instanceof Values))
    {
      paramObject = (Values)paramObject;
      i = ((Values)paramObject).startPos();
      j = 0;
      int m = 0;
      k = i;
      if ((this.base instanceof ItemPredicate))
      {
        localObject = (ItemPredicate)this.base;
        j = i;
        i = m;
        bool4 = ((ItemPredicate)localObject).isInstancePos((AbstractSequence)paramObject, j);
        j = ((Values)paramObject).nextPos(j);
        if (j == 0) {
          if (i >= this.minOccurs)
          {
            bool1 = bool2;
            if (this.maxOccurs >= 0)
            {
              if (i > this.maxOccurs) {
                break label117;
              }
              bool1 = bool2;
            }
          }
        }
      }
    }
    label117:
    label194:
    do
    {
      do
      {
        do
        {
          for (;;)
          {
            return bool1;
            bool1 = false;
          }
          bool1 = bool3;
        } while (!bool4);
        i += 1;
        break;
        do
        {
          j += 1;
          k = ((Values)paramObject).nextPos(k);
          if (k == 0)
          {
            if (j >= this.minOccurs)
            {
              bool1 = bool4;
              if (this.maxOccurs >= 0) {
                if (j > this.maxOccurs) {
                  break label194;
                }
              }
            }
            for (bool1 = bool4;; bool1 = false) {
              return bool1;
            }
          }
          localObject = ((Values)paramObject).getPosPrevious(k);
        } while (this.base.isInstance(localObject));
        return false;
        bool1 = bool3;
      } while (this.minOccurs > 1);
      bool1 = bool3;
    } while (this.maxOccurs == 0);
    return this.base.isInstance(paramObject);
  }
  
  public int maxOccurs()
  {
    return this.maxOccurs;
  }
  
  public int minOccurs()
  {
    return this.minOccurs;
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.base = ((Type)paramObjectInput.readObject());
    this.minOccurs = paramObjectInput.readInt();
    this.maxOccurs = paramObjectInput.readInt();
  }
  
  public String toString()
  {
    String str = this.base.toString();
    int i;
    StringBuffer localStringBuffer;
    if ((str == null) || (str.indexOf(' ') >= 0))
    {
      i = 1;
      localStringBuffer = new StringBuffer();
      if (i != 0) {
        localStringBuffer.append('(');
      }
      localStringBuffer.append(str);
      if (i != 0) {
        localStringBuffer.append(')');
      }
      if ((this.minOccurs != 1) || (this.maxOccurs != 1)) {
        break label85;
      }
    }
    for (;;)
    {
      return localStringBuffer.toString();
      i = 0;
      break;
      label85:
      if ((this.minOccurs == 0) && (this.maxOccurs == 1))
      {
        localStringBuffer.append('?');
      }
      else if ((this.minOccurs == 1) && (this.maxOccurs == -1))
      {
        localStringBuffer.append('+');
      }
      else
      {
        if ((this.minOccurs != 0) || (this.maxOccurs != -1)) {
          break label161;
        }
        localStringBuffer.append('*');
      }
    }
    label161:
    localStringBuffer.append('{');
    localStringBuffer.append(this.minOccurs);
    localStringBuffer.append(',');
    if (this.maxOccurs >= 0) {
      localStringBuffer.append(this.maxOccurs);
    }
    for (;;)
    {
      localStringBuffer.append('}');
      break;
      localStringBuffer.append('*');
    }
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.base);
    paramObjectOutput.writeInt(this.minOccurs);
    paramObjectOutput.writeInt(this.maxOccurs);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\OccurrenceType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */