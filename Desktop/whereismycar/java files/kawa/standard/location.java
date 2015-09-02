package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ReferenceExp;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.reflect.Invoke;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Location;
import gnu.mapping.LocationProc;
import gnu.mapping.ProcLocation;
import gnu.mapping.Procedure;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class location
  extends Syntax
{
  public static final location location = new location();
  private static ClassType thisType = ClassType.make("kawa.standard.location");
  
  static
  {
    location.setName("location");
  }
  
  public static Procedure makeLocationProc(Location paramLocation)
  {
    return new LocationProc(paramLocation);
  }
  
  public static Location makeProcLocation$V(Procedure paramProcedure, Object[] paramArrayOfObject)
  {
    int i = paramArrayOfObject.length;
    if (((paramProcedure instanceof ApplyToArgs)) && (i > 0) && ((paramArrayOfObject[0] instanceof Procedure)))
    {
      paramProcedure = (Procedure)paramArrayOfObject[0];
      if (((paramProcedure instanceof LocationProc)) && (i == 1)) {
        return ((LocationProc)paramProcedure).getLocation();
      }
      Object[] arrayOfObject = new Object[i - 1];
      System.arraycopy(paramArrayOfObject, 1, arrayOfObject, 0, arrayOfObject.length);
      return new ProcLocation(paramProcedure, arrayOfObject);
    }
    if (((paramProcedure instanceof LocationProc)) && (i == 0)) {
      return ((LocationProc)paramProcedure).getLocation();
    }
    return new ProcLocation(paramProcedure, paramArrayOfObject);
  }
  
  public static Expression rewrite(Expression paramExpression, Translator paramTranslator)
  {
    if ((paramExpression instanceof ReferenceExp))
    {
      paramExpression = (ReferenceExp)paramExpression;
      paramExpression.setDontDereference(true);
      Declaration localDeclaration = paramExpression.getBinding();
      if (localDeclaration != null)
      {
        localDeclaration.maybeIndirectBinding(paramTranslator);
        paramTranslator = Declaration.followAliases(localDeclaration);
        paramTranslator.setCanRead(true);
        paramTranslator.setCanWrite(true);
      }
      return paramExpression;
    }
    if ((paramExpression instanceof ApplyExp))
    {
      paramExpression = (ApplyExp)paramExpression;
      paramTranslator = new Expression[paramExpression.getArgs().length + 1];
      paramTranslator[0] = paramExpression.getFunction();
      System.arraycopy(paramExpression.getArgs(), 0, paramTranslator, 1, paramTranslator.length - 1);
      return Invoke.makeInvokeStatic(thisType, "makeProcLocation", paramTranslator);
    }
    return paramTranslator.syntaxError("invalid argument to location");
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator)
  {
    if (!(paramObject instanceof Pair)) {
      return paramTranslator.syntaxError("missing argument to location");
    }
    paramObject = (Pair)paramObject;
    if (((Pair)paramObject).getCdr() != LList.Empty) {
      return paramTranslator.syntaxError("extra arguments to location");
    }
    location locallocation = location;
    paramObject = rewrite(paramTranslator.rewrite(((Pair)paramObject).getCar()), paramTranslator);
    return Invoke.makeInvokeStatic(thisType, "makeLocationProc", new Expression[] { paramObject });
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\location.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */