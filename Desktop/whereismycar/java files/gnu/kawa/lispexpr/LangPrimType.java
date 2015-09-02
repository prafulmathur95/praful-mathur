package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.reflect.InstanceOf;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.text.Char;

public class LangPrimType
  extends PrimType
  implements TypeValue
{
  public static final PrimType byteType = Type.byteType;
  public static final LangPrimType charType = new LangPrimType(Type.charType);
  public static final PrimType doubleType;
  public static final PrimType floatType;
  public static final PrimType intType;
  public static final PrimType longType;
  public static final PrimType shortType = Type.shortType;
  public static final LangPrimType voidType = new LangPrimType(Type.voidType);
  PrimType implementationType;
  Language language;
  
  static
  {
    intType = Type.intType;
    longType = Type.longType;
    floatType = Type.floatType;
    doubleType = Type.doubleType;
  }
  
  public LangPrimType(PrimType paramPrimType)
  {
    super(paramPrimType);
    this.implementationType = paramPrimType;
  }
  
  public LangPrimType(PrimType paramPrimType, Language paramLanguage)
  {
    super(paramPrimType);
    this.language = paramLanguage;
    this.implementationType = paramPrimType;
  }
  
  public LangPrimType(String paramString1, String paramString2, int paramInt, Class paramClass)
  {
    super(paramString1, paramString2, paramInt, paramClass);
  }
  
  public LangPrimType(String paramString1, String paramString2, int paramInt, Class paramClass, Language paramLanguage)
  {
    this(paramString1, paramString2, paramInt, paramClass);
    this.implementationType = Type.signatureToPrimitive(paramString2.charAt(0));
    this.language = paramLanguage;
  }
  
  public char charValue(Object paramObject)
  {
    if ((paramObject instanceof Character)) {
      return ((Character)paramObject).charValue();
    }
    return ((Char)paramObject).charValue();
  }
  
  public Object coerceFromObject(Object paramObject)
  {
    if (paramObject.getClass() == this.reflectClass) {
      return paramObject;
    }
    switch (getSignature().charAt(0))
    {
    default: 
      return super.coerceFromObject(paramObject);
    case 'Z': 
      if (this.language.isTrue(paramObject)) {}
      for (paramObject = Boolean.TRUE;; paramObject = Boolean.FALSE) {
        return paramObject;
      }
    case 'C': 
      return new Character(((Char)paramObject).charValue());
    }
    return Values.empty;
  }
  
  public Object coerceToObject(Object paramObject)
  {
    Object localObject;
    switch (getSignature().charAt(0))
    {
    default: 
      localObject = super.coerceToObject(paramObject);
    case 'Z': 
    case 'C': 
      do
      {
        return localObject;
        return this.language.booleanObject(((Boolean)paramObject).booleanValue());
        localObject = paramObject;
      } while ((paramObject instanceof Char));
      return Char.make(((Character)paramObject).charValue());
    }
    return Values.empty;
  }
  
  public int compare(Type paramType)
  {
    int i = getSignature().charAt(0);
    if ((paramType instanceof PrimType))
    {
      int j = paramType.getSignature().charAt(0);
      if (i == j) {
        return 0;
      }
      if (i == 86) {
        return 1;
      }
      if ((j == 86) || (j == 90)) {
        return -1;
      }
    }
    if ((i == 86) || (i == 90)) {
      return 1;
    }
    if ((i == 67) && (paramType.getName().equals("gnu.text.Char"))) {
      return -1;
    }
    if ((paramType instanceof LangObjType)) {
      return swappedCompareResult(paramType.compare(this));
    }
    return super.compare(paramType);
  }
  
  public Expression convertValue(Expression paramExpression)
  {
    return null;
  }
  
  public void emitCoerceFromObject(CodeAttr paramCodeAttr)
  {
    switch (getSignature().charAt(0))
    {
    default: 
      super.emitCoerceFromObject(paramCodeAttr);
      return;
    case 'Z': 
      this.language.emitCoerceToBoolean(paramCodeAttr);
      return;
    }
    ClassType localClassType = ClassType.make("gnu.text.Char");
    Method localMethod = localClassType.getDeclaredMethod("charValue", 0);
    paramCodeAttr.emitCheckcast(localClassType);
    paramCodeAttr.emitInvokeVirtual(localMethod);
  }
  
  public void emitCoerceToObject(CodeAttr paramCodeAttr)
  {
    switch (getSignature().charAt(0))
    {
    default: 
      super.emitCoerceToObject(paramCodeAttr);
    }
    for (;;)
    {
      if (0 != 0) {
        paramCodeAttr.emitInvokeStatic(ClassType.make(null).getDeclaredMethod("make", new Type[] { null }));
      }
      return;
      paramCodeAttr.emitIfIntNotZero();
      this.language.emitPushBoolean(true, paramCodeAttr);
      paramCodeAttr.emitElse();
      this.language.emitPushBoolean(false, paramCodeAttr);
      paramCodeAttr.emitFi();
      continue;
      paramCodeAttr.emitInvokeStatic(ClassType.make("gnu.text.Char").getDeclaredMethod("make", 1));
    }
  }
  
  public void emitIsInstance(CodeAttr paramCodeAttr)
  {
    switch (getSignature().charAt(0))
    {
    default: 
      super.emitIsInstance(paramCodeAttr);
      return;
    case 'Z': 
      paramCodeAttr.emitPop(1);
      paramCodeAttr.emitPushInt(1);
      return;
    }
    paramCodeAttr.emitInstanceof(ClassType.make("gnu.text.Char"));
  }
  
  public void emitIsInstance(Variable paramVariable, Compilation paramCompilation, Target paramTarget)
  {
    InstanceOf.emitIsInstance(this, paramVariable, paramCompilation, paramTarget);
  }
  
  public void emitTestIf(Variable paramVariable, Declaration paramDeclaration, Compilation paramCompilation)
  {
    getSignature().charAt(0);
    CodeAttr localCodeAttr = paramCompilation.getCode();
    if (paramVariable != null) {
      localCodeAttr.emitLoad(paramVariable);
    }
    if (paramDeclaration != null)
    {
      localCodeAttr.emitDup();
      paramDeclaration.compileStore(paramCompilation);
    }
    emitIsInstance(localCodeAttr);
    localCodeAttr.emitIfIntNotZero();
  }
  
  public Procedure getConstructor()
  {
    return null;
  }
  
  public Type getImplementationType()
  {
    return this.implementationType;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\LangPrimType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */