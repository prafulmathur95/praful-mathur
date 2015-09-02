package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.Language;
import gnu.kawa.reflect.Invoke;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;
import java.lang.reflect.Array;
import java.util.List;

public class ApplyToArgs
  extends ProcedureN
{
  Language language;
  
  public ApplyToArgs(String paramString, Language paramLanguage)
  {
    super(paramString);
    this.language = paramLanguage;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompilationHelpers:validateApplyToArgs");
  }
  
  public Object applyN(Object[] paramArrayOfObject)
    throws Throwable
  {
    Object localObject = paramArrayOfObject[0];
    if ((localObject instanceof Procedure))
    {
      Object[] arrayOfObject = new Object[paramArrayOfObject.length - 1];
      System.arraycopy(paramArrayOfObject, 1, arrayOfObject, 0, arrayOfObject.length);
      return ((Procedure)localObject).applyN(arrayOfObject);
    }
    if (((localObject instanceof Type)) || ((localObject instanceof Class))) {
      return Invoke.make.applyN(paramArrayOfObject);
    }
    if ((localObject instanceof List))
    {
      if (paramArrayOfObject.length != 2) {
        throw new WrongArguments(this, paramArrayOfObject.length);
      }
      int i = ((Number)paramArrayOfObject[1]).intValue();
      return ((List)localObject).get(i);
    }
    if (localObject.getClass().isArray())
    {
      if (paramArrayOfObject.length != 2) {
        throw new WrongArguments(this, paramArrayOfObject.length);
      }
      return Array.get(localObject, ((Number)paramArrayOfObject[1]).intValue());
    }
    throw new WrongType(this, 0, localObject, "procedure");
  }
  
  public void check1(Object paramObject, CallContext paramCallContext)
  {
    if ((paramObject instanceof Procedure))
    {
      ((Procedure)paramObject).check0(paramCallContext);
      return;
    }
    super.check1(paramObject, paramCallContext);
  }
  
  public void check2(Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    if ((paramObject1 instanceof Procedure))
    {
      ((Procedure)paramObject1).check1(paramObject2, paramCallContext);
      return;
    }
    super.check2(paramObject1, paramObject2, paramCallContext);
  }
  
  public void check3(Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    if ((paramObject1 instanceof Procedure))
    {
      ((Procedure)paramObject1).check2(paramObject2, paramObject3, paramCallContext);
      return;
    }
    super.check3(paramObject1, paramObject2, paramObject3, paramCallContext);
  }
  
  public void check4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
  {
    if ((paramObject1 instanceof Procedure))
    {
      ((Procedure)paramObject1).check3(paramObject2, paramObject3, paramObject4, paramCallContext);
      return;
    }
    super.check4(paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
  }
  
  public void checkN(Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    int i = matchN(paramArrayOfObject, paramCallContext);
    if (i != 0)
    {
      ApplyToArgs localApplyToArgs = this;
      Object localObject = localApplyToArgs;
      paramCallContext = paramArrayOfObject;
      if (paramArrayOfObject.length > 0)
      {
        localObject = localApplyToArgs;
        paramCallContext = paramArrayOfObject;
        if ((paramArrayOfObject[0] instanceof Procedure))
        {
          localObject = (Procedure)paramArrayOfObject[0];
          paramCallContext = new Object[paramArrayOfObject.length - 1];
          System.arraycopy(paramArrayOfObject, 1, paramCallContext, 0, paramCallContext.length);
        }
      }
      throw MethodProc.matchFailAsException(i, (Procedure)localObject, paramCallContext);
    }
  }
  
  public int match1(Object paramObject, CallContext paramCallContext)
  {
    if ((paramObject instanceof Procedure)) {
      return ((Procedure)paramObject).match0(paramCallContext);
    }
    return super.match1(paramObject, paramCallContext);
  }
  
  public int match2(Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    if ((paramObject1 instanceof Procedure)) {
      return ((Procedure)paramObject1).match1(paramObject2, paramCallContext);
    }
    return super.match2(paramObject1, paramObject2, paramCallContext);
  }
  
  public int match3(Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    if ((paramObject1 instanceof Procedure)) {
      return ((Procedure)paramObject1).match2(paramObject2, paramObject3, paramCallContext);
    }
    return super.match3(paramObject1, paramObject2, paramObject3, paramCallContext);
  }
  
  public int match4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
  {
    if ((paramObject1 instanceof Procedure)) {
      return ((Procedure)paramObject1).match3(paramObject2, paramObject3, paramObject4, paramCallContext);
    }
    return super.match4(paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
  }
  
  public int matchN(Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    int i = paramArrayOfObject.length;
    if ((i > 0) && ((paramArrayOfObject[0] instanceof Procedure)))
    {
      Procedure localProcedure = (Procedure)paramArrayOfObject[0];
      switch (i)
      {
      default: 
        Object[] arrayOfObject = new Object[i - 1];
        System.arraycopy(paramArrayOfObject, 1, arrayOfObject, 0, i - 1);
        return localProcedure.matchN(arrayOfObject, paramCallContext);
      case 1: 
        return localProcedure.match0(paramCallContext);
      case 2: 
        return localProcedure.match1(paramArrayOfObject[1], paramCallContext);
      case 3: 
        return localProcedure.match2(paramArrayOfObject[1], paramArrayOfObject[2], paramCallContext);
      case 4: 
        return localProcedure.match3(paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramCallContext);
      }
      return localProcedure.match4(paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramArrayOfObject[4], paramCallContext);
    }
    return super.matchN(paramArrayOfObject, paramCallContext);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\functions\ApplyToArgs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */