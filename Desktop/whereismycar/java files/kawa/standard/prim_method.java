package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Expression;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.ListPat;
import kawa.lang.Pattern;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class prim_method
  extends Syntax
{
  public static final prim_method interface_method;
  public static final prim_method op1;
  private static Pattern pattern3 = new ListPat(3);
  private static Pattern pattern4 = new ListPat(4);
  public static final prim_method static_method;
  public static final prim_method virtual_method = new prim_method(182);
  int op_code;
  
  static
  {
    virtual_method.setName("primitive-virtual-method");
    static_method = new prim_method(184);
    static_method.setName("primitive-static-method");
    interface_method = new prim_method(185);
    interface_method.setName("primitive-interface-method");
    op1 = new prim_method();
    op1.setName("primitive-op1");
  }
  
  public prim_method() {}
  
  public prim_method(int paramInt)
  {
    this.op_code = paramInt;
  }
  
  int opcode()
  {
    return this.op_code;
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator)
  {
    Object[] arrayOfObject = new Object[4];
    if (this.op_code == 0)
    {
      if (!pattern3.match(paramObject, arrayOfObject, 1)) {}
    }
    else {
      while (pattern4.match(paramObject, arrayOfObject, 0))
      {
        if ((arrayOfObject[3] instanceof LList)) {
          break;
        }
        return paramTranslator.syntaxError("missing/invalid parameter list in " + getName());
      }
    }
    return paramTranslator.syntaxError("wrong number of arguments to " + getName() + "(opcode:" + this.op_code + ")");
    Object localObject1 = (LList)arrayOfObject[3];
    int j = ((LList)localObject1).size();
    Type[] arrayOfType = new Type[j];
    int i = 0;
    while (i < j)
    {
      localObject1 = (Pair)localObject1;
      arrayOfType[i] = paramTranslator.exp2Type((Pair)localObject1);
      localObject1 = (LList)((Pair)localObject1).getCdr();
      i += 1;
    }
    Type localType = paramTranslator.exp2Type(new Pair(arrayOfObject[2], null));
    if (this.op_code == 0) {
      paramObject = new PrimProcedure(((Number)arrayOfObject[1]).intValue(), localType, arrayOfType);
    }
    for (;;)
    {
      return new QuoteExp(paramObject);
      Object localObject2 = null;
      paramObject = paramTranslator.exp2Type((Pair)paramObject);
      localObject1 = paramObject;
      if (paramObject != null) {
        localObject1 = ((Type)paramObject).getImplementationType();
      }
      paramObject = localObject2;
      try
      {
        localObject1 = (ClassType)localObject1;
        paramObject = localObject1;
        ((ClassType)localObject1).getReflectClass();
        paramObject = localObject1;
        if ((arrayOfObject[1] instanceof Pair))
        {
          paramTranslator = (Pair)arrayOfObject[1];
          if (paramTranslator.getCar() == "quote") {
            arrayOfObject[1] = ((Pair)paramTranslator.getCdr()).getCar();
          }
        }
        paramObject = new PrimProcedure(this.op_code, (ClassType)paramObject, arrayOfObject[1].toString(), localType, arrayOfType);
      }
      catch (Exception localException)
      {
        if (paramObject != null) {
          break label403;
        }
      }
    }
    char c = 'e';
    for (;;)
    {
      paramTranslator.error(c, "unknown class: " + arrayOfObject[0]);
      break;
      label403:
      c = 'w';
      ((ClassType)paramObject).setExisting(false);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\prim_method.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */