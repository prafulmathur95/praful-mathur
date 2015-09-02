package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.GenericProc;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.lists.FString;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import java.util.Vector;

public class ClassMethods
  extends Procedure2
{
  public static final ClassMethods classMethods = new ClassMethods();
  
  static
  {
    classMethods.setName("class-methods");
  }
  
  public static MethodProc apply(ObjectType paramObjectType, String paramString, char paramChar, Language paramLanguage)
  {
    PrimProcedure[] arrayOfPrimProcedure = getMethods(paramObjectType, paramString, paramChar, null, paramLanguage);
    Object localObject1 = null;
    paramLanguage = null;
    int i = 0;
    while (i < arrayOfPrimProcedure.length)
    {
      PrimProcedure localPrimProcedure = arrayOfPrimProcedure[i];
      Object localObject2 = localObject1;
      if (paramLanguage != null)
      {
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          localObject2 = new GenericProc();
          ((GenericProc)localObject2).add(paramLanguage);
        }
      }
      paramLanguage = localPrimProcedure;
      if (localObject2 != null) {
        ((GenericProc)localObject2).add(paramLanguage);
      }
      i += 1;
      localObject1 = localObject2;
    }
    if (localObject1 != null)
    {
      ((GenericProc)localObject1).setName(paramObjectType.getName() + "." + paramString);
      return (MethodProc)localObject1;
    }
    return paramLanguage;
  }
  
  public static MethodProc apply(Procedure paramProcedure, Object paramObject1, Object paramObject2)
  {
    Object localObject = paramObject1;
    if ((paramObject1 instanceof Class)) {
      localObject = Type.make((Class)paramObject1);
    }
    if ((localObject instanceof ClassType)) {
      paramObject1 = (ClassType)localObject;
    }
    while (((paramObject2 instanceof String)) || ((paramObject2 instanceof FString)) || ((paramObject2 instanceof Symbol)))
    {
      paramObject2 = paramObject2.toString();
      paramProcedure = (Procedure)paramObject2;
      if (!"<init>".equals(paramObject2)) {
        paramProcedure = Compilation.mangleName((String)paramObject2);
      }
      paramObject2 = apply((ObjectType)paramObject1, paramProcedure, '\000', Language.getDefaultLanguage());
      if (paramObject2 != null) {
        return paramObject2;
      }
      throw new RuntimeException("no applicable method named `" + paramProcedure + "' in " + ((ClassType)paramObject1).getName());
      if (((localObject instanceof String)) || ((localObject instanceof FString)) || ((localObject instanceof Symbol))) {
        paramObject1 = ClassType.make(localObject.toString());
      } else {
        throw new WrongType(paramProcedure, 0, null);
      }
    }
    throw new WrongType(paramProcedure, 1, null);
    return (MethodProc)paramObject2;
  }
  
  static String checkName(Expression paramExpression)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if ((paramExpression instanceof QuoteExp))
    {
      paramExpression = ((QuoteExp)paramExpression).getValue();
      if ((!(paramExpression instanceof FString)) && (!(paramExpression instanceof String))) {
        break label40;
      }
      localObject1 = paramExpression.toString();
    }
    label40:
    do
    {
      return (String)localObject1;
      localObject1 = localObject2;
    } while (!(paramExpression instanceof Symbol));
    return ((Symbol)paramExpression).getName();
  }
  
  static String checkName(Expression paramExpression, boolean paramBoolean)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if ((paramExpression instanceof QuoteExp))
    {
      paramExpression = ((QuoteExp)paramExpression).getValue();
      if ((!(paramExpression instanceof FString)) && (!(paramExpression instanceof String))) {
        break label49;
      }
    }
    for (paramExpression = paramExpression.toString(); Compilation.isValidJavaName(paramExpression); paramExpression = ((Symbol)paramExpression).getName())
    {
      localObject1 = paramExpression;
      label49:
      do
      {
        return (String)localObject1;
        localObject1 = localObject2;
      } while (!(paramExpression instanceof Symbol));
    }
    return Compilation.mangleName(paramExpression, paramBoolean);
  }
  
  public static PrimProcedure[] getMethods(ObjectType paramObjectType, String paramString, char paramChar, ClassType paramClassType, Language paramLanguage)
  {
    Object localObject = paramObjectType;
    if (paramObjectType == Type.tostring_type) {
      localObject = Type.string_type;
    }
    int j;
    if (paramChar == 'P')
    {
      paramObjectType = null;
      paramObjectType = new MethodFilter(paramString, 0, 0, paramClassType, paramObjectType);
      if ((paramChar != 'P') && (!"<init>".equals(paramString))) {
        break label244;
      }
      j = 1;
      label54:
      paramClassType = new Vector();
      if (j == 0) {
        break label250;
      }
      i = 0;
      label70:
      ((ObjectType)localObject).getMethods(paramObjectType, i, paramClassType);
      if ((j == 0) && ((!(localObject instanceof ClassType)) || (((ClassType)localObject).isInterface()))) {
        Type.pointer_type.getMethods(paramObjectType, 0, paramClassType);
      }
      if (j == 0) {
        break label256;
      }
    }
    PrimProcedure[] arrayOfPrimProcedure;
    label244:
    label250:
    label256:
    for (int i = paramClassType.size();; i = removeRedundantMethods(paramClassType))
    {
      arrayOfPrimProcedure = new PrimProcedure[i];
      int m = 0;
      int k = i;
      i = m;
      for (;;)
      {
        k -= 1;
        if (k < 0) {
          break;
        }
        paramString = (Method)paramClassType.elementAt(k);
        paramObjectType = paramString;
        if (j == 0)
        {
          paramObjectType = paramString;
          if (paramString.getDeclaringClass() != localObject)
          {
            Type localType = ((ObjectType)localObject).getImplementationType();
            paramObjectType = paramString;
            if ((localType instanceof ClassType)) {
              paramObjectType = new Method(paramString, (ClassType)localType);
            }
          }
        }
        arrayOfPrimProcedure[i] = new PrimProcedure(paramObjectType, paramChar, paramLanguage);
        i += 1;
      }
      paramObjectType = (ObjectType)localObject;
      break;
      j = 0;
      break label54;
      i = 2;
      break label70;
    }
    return arrayOfPrimProcedure;
  }
  
  private static int removeRedundantMethods(Vector paramVector)
  {
    int i = paramVector.size();
    int j = 1;
    while (j < i)
    {
      Method localMethod1 = (Method)paramVector.elementAt(j);
      ClassType localClassType = localMethod1.getDeclaringClass();
      Type[] arrayOfType1 = localMethod1.getParameterTypes();
      int n = arrayOfType1.length;
      int k = 0;
      if (k < j)
      {
        Method localMethod2 = (Method)paramVector.elementAt(k);
        Type[] arrayOfType2 = localMethod2.getParameterTypes();
        if (n != arrayOfType2.length) {}
        int i1;
        do
        {
          k += 1;
          break;
          int m = n;
          do
          {
            i1 = m - 1;
            if (i1 < 0) {
              break;
            }
            m = i1;
          } while (arrayOfType1[i1] == arrayOfType2[i1]);
        } while (i1 >= 0);
        if (localClassType.isSubtype(localMethod2.getDeclaringClass())) {
          paramVector.setElementAt(localMethod1, k);
        }
        paramVector.setElementAt(paramVector.elementAt(i - 1), j);
        i -= 1;
      }
      else
      {
        j += 1;
      }
    }
    return i;
  }
  
  public static int selectApplicable(PrimProcedure[] paramArrayOfPrimProcedure, int paramInt)
  {
    int i = paramArrayOfPrimProcedure.length;
    int m = 0;
    int n = 0;
    int j = 0;
    int k = 0;
    while (k < i)
    {
      int i1 = paramArrayOfPrimProcedure[k].numArgs();
      int i2 = Procedure.minArgs(i1);
      int i3 = Procedure.maxArgs(i1);
      i1 = 0;
      if (paramInt < i2) {
        n += 1;
      }
      for (;;)
      {
        if (i1 == 0) {
          break label105;
        }
        j += 1;
        k += 1;
        break;
        if ((paramInt > i3) && (i3 >= 0)) {
          m += 1;
        } else {
          i1 = 1;
        }
      }
      label105:
      PrimProcedure localPrimProcedure = paramArrayOfPrimProcedure[(i - 1)];
      paramArrayOfPrimProcedure[(i - 1)] = paramArrayOfPrimProcedure[k];
      paramArrayOfPrimProcedure[k] = localPrimProcedure;
      i -= 1;
    }
    if (j > 0) {
      return j;
    }
    if (n > 0) {
      return -983040;
    }
    if (m > 0) {
      return -917504;
    }
    return 0;
  }
  
  public static long selectApplicable(PrimProcedure[] paramArrayOfPrimProcedure, Type[] paramArrayOfType)
  {
    int k = paramArrayOfPrimProcedure.length;
    int m = 0;
    int j = 0;
    int i = 0;
    while (i < k)
    {
      int n = paramArrayOfPrimProcedure[i].isApplicable(paramArrayOfType);
      PrimProcedure localPrimProcedure;
      if (n < 0)
      {
        localPrimProcedure = paramArrayOfPrimProcedure[(k - 1)];
        paramArrayOfPrimProcedure[(k - 1)] = paramArrayOfPrimProcedure[i];
        paramArrayOfPrimProcedure[i] = localPrimProcedure;
        k -= 1;
      }
      else if (n > 0)
      {
        localPrimProcedure = paramArrayOfPrimProcedure[m];
        paramArrayOfPrimProcedure[m] = paramArrayOfPrimProcedure[i];
        paramArrayOfPrimProcedure[i] = localPrimProcedure;
        m += 1;
        i += 1;
      }
      else
      {
        j += 1;
        i += 1;
      }
    }
    return (m << 32) + j;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2)
  {
    return apply(this, paramObject1, paramObject2);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\ClassMethods.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */