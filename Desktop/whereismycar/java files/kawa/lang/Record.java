package kawa.lang;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrappedException;
import java.io.PrintWriter;
import java.util.Vector;

public class Record
{
  static java.lang.reflect.Field getField(Class paramClass, String paramString)
    throws NoSuchFieldException
  {
    paramClass = ((ClassType)Type.make(paramClass)).getFields();
    if (paramClass != null)
    {
      if ((paramClass.getModifiers() & 0x9) != 1) {}
      while (!paramClass.getSourceName().equals(paramString))
      {
        paramClass = paramClass.getNext();
        break;
      }
      return paramClass.getReflectField();
    }
    throw new NoSuchFieldException();
  }
  
  public static boolean isRecord(Object paramObject)
  {
    return paramObject instanceof Record;
  }
  
  public static ClassType makeRecordType(String paramString, LList paramLList)
  {
    Object localObject2 = ClassType.make("kawa.lang.Record");
    String str = Compilation.mangleNameIfNeeded(paramString);
    ClassType localClassType = new ClassType(str);
    localClassType.setSuper((ClassType)localObject2);
    localClassType.setModifiers(33);
    Object localObject1 = localClassType.addMethod("<init>", Type.typeArray0, Type.voidType, 1);
    localObject2 = ((ClassType)localObject2).addMethod("<init>", Type.typeArray0, Type.voidType, 1);
    localObject1 = ((Method)localObject1).startCode();
    ((CodeAttr)localObject1).emitPushThis();
    ((CodeAttr)localObject1).emitInvokeSpecial((Method)localObject2);
    ((CodeAttr)localObject1).emitReturn();
    localObject1 = paramLList;
    if (!paramString.equals(str))
    {
      localObject1 = localClassType.addMethod("getTypeName", Type.typeArray0, Compilation.typeString, 1).startCode();
      ((CodeAttr)localObject1).emitPushString(paramString);
      ((CodeAttr)localObject1).emitReturn();
    }
    for (localObject1 = paramLList; localObject1 != LList.Empty; localObject1 = (LList)paramString.getCdr())
    {
      paramString = (Pair)localObject1;
      paramLList = paramString.getCar().toString();
      localClassType.addField(Compilation.mangleNameIfNeeded(paramLList), Type.pointer_type, 1).setSourceName(paramLList.intern());
    }
    paramString = localClassType.writeToArray();
    paramString = new ArrayClassLoader(new String[] { str }, new byte[][] { paramString });
    try
    {
      Type.registerTypeForClass(paramString.loadClass(str), localClassType);
      return localClassType;
    }
    catch (ClassNotFoundException paramString)
    {
      throw new InternalError(paramString.toString());
    }
  }
  
  public static Object set1(Object paramObject1, String paramString, Object paramObject2)
  {
    Class localClass = paramObject1.getClass();
    try
    {
      java.lang.reflect.Field localField = getField(localClass, paramString);
      Object localObject = localField.get(paramObject1);
      localField.set(paramObject1, paramObject2);
      return localObject;
    }
    catch (NoSuchFieldException paramObject1)
    {
      throw new GenericError("no such field " + paramString + " in " + localClass.getName());
    }
    catch (IllegalAccessException paramObject1)
    {
      throw new GenericError("illegal access for field " + paramString);
    }
  }
  
  public static LList typeFieldNames(ClassType paramClassType)
  {
    return typeFieldNames(paramClassType.getReflectClass());
  }
  
  public static LList typeFieldNames(Class paramClass)
  {
    LList localLList = LList.Empty;
    paramClass = ((ClassType)Type.make(paramClass)).getFields();
    Vector localVector = new Vector(100);
    while (paramClass != null)
    {
      if ((paramClass.getModifiers() & 0x9) == 1) {
        localVector.addElement(SimpleSymbol.valueOf(paramClass.getSourceName()));
      }
      paramClass = paramClass.getNext();
    }
    int i = localVector.size();
    for (paramClass = localLList;; paramClass = new Pair(localVector.elementAt(i), paramClass))
    {
      i -= 1;
      if (i < 0) {
        break;
      }
    }
    return paramClass;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    Object localObject1 = getClass();
    if ((paramObject == null) || (paramObject.getClass() != localObject1)) {
      return false;
    }
    localObject1 = ((ClassType)Type.make((Class)localObject1)).getFields();
    label37:
    if (localObject1 != null) {
      if ((((gnu.bytecode.Field)localObject1).getModifiers() & 0x9) == 1) {
        break label60;
      }
    }
    for (;;)
    {
      localObject1 = ((gnu.bytecode.Field)localObject1).getNext();
      break label37;
      break;
      try
      {
        label60:
        Object localObject3 = ((gnu.bytecode.Field)localObject1).getReflectField();
        Object localObject2 = ((java.lang.reflect.Field)localObject3).get(this);
        localObject3 = ((java.lang.reflect.Field)localObject3).get(paramObject);
        if (localObject2.equals(localObject3)) {
          continue;
        }
        return false;
      }
      catch (Exception paramObject)
      {
        throw new WrappedException((Throwable)paramObject);
      }
    }
  }
  
  public Object get(String paramString, Object paramObject)
  {
    paramObject = getClass();
    try
    {
      Object localObject = getField((Class)paramObject, paramString).get(this);
      return localObject;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      throw new GenericError("no such field " + paramString + " in " + ((Class)paramObject).getName());
    }
    catch (IllegalAccessException paramObject)
    {
      throw new GenericError("illegal access for field " + paramString);
    }
  }
  
  public String getTypeName()
  {
    return getClass().getName();
  }
  
  public int hashCode()
  {
    java.lang.reflect.Field[] arrayOfField = getClass().getFields();
    int j = 12345;
    int i = 0;
    while (i < arrayOfField.length)
    {
      Object localObject = arrayOfField[i];
      try
      {
        localObject = ((java.lang.reflect.Field)localObject).get(this);
        k = j;
        if (localObject != null) {
          k = j ^ localObject.hashCode();
        }
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        for (;;)
        {
          int k = j;
        }
      }
      i += 1;
      j = k;
    }
    return j;
  }
  
  public void print(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.print(toString());
  }
  
  public Object put(String paramString, Object paramObject)
  {
    return set1(this, paramString, paramObject);
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(200);
    localStringBuffer.append("#<");
    localStringBuffer.append(getTypeName());
    gnu.bytecode.Field localField = ((ClassType)Type.make(getClass())).getFields();
    if (localField != null)
    {
      if ((localField.getModifiers() & 0x9) != 1) {}
      for (;;)
      {
        localField = localField.getNext();
        break;
        try
        {
          Object localObject = localField.getReflectField().get(this);
          localStringBuffer.append(' ');
          localStringBuffer.append(localField.getSourceName());
          localStringBuffer.append(": ");
          localStringBuffer.append(localObject);
        }
        catch (Exception localException)
        {
          for (;;)
          {
            String str = "#<illegal-access>";
          }
        }
      }
    }
    localStringBuffer.append(">");
    return localStringBuffer.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\Record.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */