package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.SourceLocator;

public class Declaration
  implements SourceLocator
{
  static final int CAN_CALL = 4;
  static final int CAN_READ = 2;
  static final int CAN_WRITE = 8;
  public static final long CLASS_ACCESS_FLAGS = 25820135424L;
  public static final int EARLY_INIT = 536870912;
  public static final long ENUM_ACCESS = 8589934592L;
  public static final int EXPORT_SPECIFIED = 1024;
  public static final int EXTERNAL_ACCESS = 524288;
  public static final long FIELD_ACCESS_FLAGS = 32463912960L;
  public static final int FIELD_OR_METHOD = 1048576;
  public static final long FINAL_ACCESS = 17179869184L;
  static final int INDIRECT_BINDING = 1;
  public static final int IS_ALIAS = 256;
  public static final int IS_CONSTANT = 16384;
  public static final int IS_DYNAMIC = 268435456;
  static final int IS_FLUID = 16;
  public static final int IS_IMPORTED = 131072;
  public static final int IS_NAMESPACE_PREFIX = 2097152;
  static final int IS_SIMPLE = 64;
  public static final int IS_SINGLE_VALUE = 262144;
  public static final int IS_SYNTAX = 32768;
  public static final int IS_UNKNOWN = 65536;
  public static final long METHOD_ACCESS_FLAGS = 17431527424L;
  public static final int MODULE_REFERENCE = 1073741824;
  public static final int NONSTATIC_SPECIFIED = 4096;
  public static final int NOT_DEFINING = 512;
  public static final int PACKAGE_ACCESS = 134217728;
  static final int PRIVATE = 32;
  public static final int PRIVATE_ACCESS = 16777216;
  public static final String PRIVATE_PREFIX = "$Prvt$";
  public static final int PRIVATE_SPECIFIED = 16777216;
  static final int PROCEDURE = 128;
  public static final int PROTECTED_ACCESS = 33554432;
  public static final int PUBLIC_ACCESS = 67108864;
  public static final int STATIC_SPECIFIED = 2048;
  public static final long TRANSIENT_ACCESS = 4294967296L;
  public static final int TYPE_SPECIFIED = 8192;
  static final String UNKNOWN_PREFIX = "loc$";
  public static final long VOLATILE_ACCESS = 2147483648L;
  static int counter;
  public Declaration base;
  public ScopeExp context;
  int evalIndex;
  public gnu.bytecode.Field field;
  String filename;
  public ApplyExp firstCall;
  protected long flags;
  protected int id;
  Method makeLocationMethod;
  Declaration next;
  Declaration nextCapturedVar;
  int position;
  Object symbol;
  protected Type type;
  protected Expression typeExp;
  protected Expression value;
  Variable var;
  
  protected Declaration()
  {
    int i = counter + 1;
    counter = i;
    this.id = i;
    this.value = QuoteExp.undefined_exp;
    this.flags = 64L;
    this.makeLocationMethod = null;
  }
  
  public Declaration(Variable paramVariable)
  {
    this(paramVariable.getName(), paramVariable.getType());
    this.var = paramVariable;
  }
  
  public Declaration(Object paramObject)
  {
    int i = counter + 1;
    counter = i;
    this.id = i;
    this.value = QuoteExp.undefined_exp;
    this.flags = 64L;
    this.makeLocationMethod = null;
    setName(paramObject);
  }
  
  public Declaration(Object paramObject, gnu.bytecode.Field paramField)
  {
    this(paramObject, paramField.getType());
    this.field = paramField;
    setSimple(false);
  }
  
  public Declaration(Object paramObject, Type paramType)
  {
    int i = counter + 1;
    counter = i;
    this.id = i;
    this.value = QuoteExp.undefined_exp;
    this.flags = 64L;
    this.makeLocationMethod = null;
    setName(paramObject);
    setType(paramType);
  }
  
  public static Declaration followAliases(Declaration paramDeclaration)
  {
    for (;;)
    {
      Object localObject;
      if ((paramDeclaration != null) && (paramDeclaration.isAlias()))
      {
        localObject = paramDeclaration.getValue();
        if ((localObject instanceof ReferenceExp)) {
          break label25;
        }
      }
      label25:
      do
      {
        return paramDeclaration;
        localObject = ((ReferenceExp)localObject).binding;
      } while (localObject == null);
      paramDeclaration = (Declaration)localObject;
    }
  }
  
  public static Declaration getDeclaration(Named paramNamed)
  {
    return getDeclaration(paramNamed, paramNamed.getName());
  }
  
  public static Declaration getDeclaration(Object paramObject, String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      Class localClass = PrimProcedure.getProcedureClass(paramObject);
      localObject1 = localObject2;
      if (localClass != null) {
        localObject1 = ((ClassType)Type.make(localClass)).getDeclaredField(Compilation.mangleNameIfNeeded(paramString));
      }
    }
    if (localObject1 != null)
    {
      int i = ((gnu.bytecode.Field)localObject1).getModifiers();
      if ((i & 0x8) != 0)
      {
        paramString = new Declaration(paramString, (gnu.bytecode.Field)localObject1);
        paramString.noteValue(new QuoteExp(paramObject));
        if ((i & 0x10) != 0) {
          paramString.setFlag(16384L);
        }
        return paramString;
      }
    }
    return null;
  }
  
  public static Declaration getDeclarationFromStatic(String paramString1, String paramString2)
  {
    paramString1 = new Declaration(paramString2, ClassType.make(paramString1).getDeclaredField(paramString2));
    paramString1.setFlag(18432L);
    return paramString1;
  }
  
  public static Declaration getDeclarationValueFromStatic(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      Object localObject = Class.forName(paramString1).getDeclaredField(paramString2).get(null);
      paramString1 = new Declaration(paramString3, ClassType.make(paramString1).getDeclaredField(paramString2));
      paramString1.noteValue(new QuoteExp(localObject));
      paramString1.setFlag(18432L);
      return paramString1;
    }
    catch (Exception paramString1)
    {
      throw new WrappedException(paramString1);
    }
  }
  
  public static final boolean isUnknown(Declaration paramDeclaration)
  {
    return (paramDeclaration == null) || (paramDeclaration.getFlag(65536L));
  }
  
  public final Variable allocateVariable(CodeAttr paramCodeAttr)
  {
    String str;
    if ((!isSimple()) || (this.var == null))
    {
      str = null;
      if (this.symbol != null) {
        str = Compilation.mangleNameIfNeeded(getName());
      }
      if ((!isAlias()) || (!(getValue() instanceof ReferenceExp))) {
        break label77;
      }
      paramCodeAttr = followAliases(this);
      if (paramCodeAttr != null) {
        break label69;
      }
    }
    label69:
    for (paramCodeAttr = null;; paramCodeAttr = paramCodeAttr.var)
    {
      this.var = paramCodeAttr;
      return this.var;
    }
    label77:
    if (isIndirectBinding()) {}
    for (Object localObject = Compilation.typeLocation;; localObject = getType().getImplementationType())
    {
      this.var = this.context.getVarScope().addVariable(paramCodeAttr, (Type)localObject, str);
      break;
    }
  }
  
  public void compileStore(Compilation paramCompilation)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    if (isSimple())
    {
      localCodeAttr.emitStore(getVariable());
      return;
    }
    if (!this.field.getStaticFlag())
    {
      loadOwningObject(null, paramCompilation);
      localCodeAttr.emitSwap();
      localCodeAttr.emitPutField(this.field);
      return;
    }
    localCodeAttr.emitPutStatic(this.field);
  }
  
  public short getAccessFlags(short paramShort)
  {
    short s;
    if (getFlag(251658240L))
    {
      paramShort = 0;
      if (getFlag(16777216L)) {
        paramShort = (short)2;
      }
      s = paramShort;
      if (getFlag(33554432L)) {
        s = (short)(paramShort | 0x4);
      }
      paramShort = s;
      if (getFlag(67108864L)) {
        paramShort = (short)(s | 0x1);
      }
    }
    for (;;)
    {
      s = paramShort;
      if (getFlag(2147483648L)) {
        s = (short)(paramShort | 0x40);
      }
      paramShort = s;
      if (getFlag(4294967296L)) {
        paramShort = (short)(s | 0x80);
      }
      s = paramShort;
      if (getFlag(8589934592L)) {
        s = (short)(paramShort | 0x4000);
      }
      paramShort = s;
      if (getFlag(17179869184L)) {
        paramShort = (short)(s | 0x10);
      }
      return paramShort;
    }
  }
  
  public final boolean getCanCall()
  {
    return (this.flags & 0x4) != 0L;
  }
  
  public final boolean getCanRead()
  {
    return (this.flags & 0x2) != 0L;
  }
  
  public final boolean getCanWrite()
  {
    return (this.flags & 0x8) != 0L;
  }
  
  public int getCode()
  {
    return this.id;
  }
  
  public final int getColumnNumber()
  {
    int j = this.position & 0xFFF;
    int i = j;
    if (j == 0) {
      i = -1;
    }
    return i;
  }
  
  public final Object getConstantValue()
  {
    Expression localExpression = getValue();
    if ((!(localExpression instanceof QuoteExp)) || (localExpression == QuoteExp.undefined_exp)) {
      return null;
    }
    return ((QuoteExp)localExpression).getValue();
  }
  
  public final ScopeExp getContext()
  {
    return this.context;
  }
  
  public final String getFileName()
  {
    return this.filename;
  }
  
  public final boolean getFlag(long paramLong)
  {
    return (this.flags & paramLong) != 0L;
  }
  
  public final int getLineNumber()
  {
    int j = this.position >> 12;
    int i = j;
    if (j == 0) {
      i = -1;
    }
    return i;
  }
  
  public final String getName()
  {
    if (this.symbol == null) {
      return null;
    }
    if ((this.symbol instanceof Symbol)) {
      return ((Symbol)this.symbol).getName();
    }
    return this.symbol.toString();
  }
  
  public String getPublicId()
  {
    return null;
  }
  
  public final Object getSymbol()
  {
    return this.symbol;
  }
  
  public String getSystemId()
  {
    return this.filename;
  }
  
  public final Type getType()
  {
    if (this.type == null) {
      setType(Type.objectType);
    }
    return this.type;
  }
  
  public final Expression getTypeExp()
  {
    if (this.typeExp == null) {
      setType(Type.objectType);
    }
    return this.typeExp;
  }
  
  public final Expression getValue()
  {
    if ((this.value != QuoteExp.undefined_exp) || ((this.field != null) && ((this.field.getModifiers() & 0x18) == 24) && (!isIndirectBinding()))) {}
    try
    {
      this.value = new QuoteExp(this.field.getReflectField().get(null));
      for (;;)
      {
        return this.value;
        if (((this.value instanceof QuoteExp)) && (getFlag(8192L)) && (this.value.getType() != this.type)) {
          try
          {
            Object localObject = ((QuoteExp)this.value).getValue();
            Type localType = getType();
            this.value = new QuoteExp(localType.coerceFromObject(localObject), localType);
          }
          catch (Throwable localThrowable1) {}
        }
      }
    }
    catch (Throwable localThrowable2)
    {
      for (;;) {}
    }
  }
  
  public Variable getVariable()
  {
    return this.var;
  }
  
  public final boolean hasConstantValue()
  {
    Expression localExpression = getValue();
    return ((localExpression instanceof QuoteExp)) && (localExpression != QuoteExp.undefined_exp);
  }
  
  public boolean ignorable()
  {
    if ((getCanRead()) || (isPublic())) {}
    Object localObject;
    do
    {
      do
      {
        do
        {
          return false;
        } while ((getCanWrite()) && (getFlag(65536L)));
        if (!getCanCall()) {
          return true;
        }
        localObject = getValue();
      } while ((localObject == null) || (!(localObject instanceof LambdaExp)));
      localObject = (LambdaExp)localObject;
    } while ((((LambdaExp)localObject).isHandlingTailCalls()) && (!((LambdaExp)localObject).getInlineOnly()));
    return true;
  }
  
  public final boolean isAlias()
  {
    return (this.flags & 0x100) != 0L;
  }
  
  public boolean isCompiletimeConstant()
  {
    return (getFlag(16384L)) && (hasConstantValue());
  }
  
  public final boolean isFluid()
  {
    return (this.flags & 0x10) != 0L;
  }
  
  public final boolean isIndirectBinding()
  {
    return (this.flags & 1L) != 0L;
  }
  
  public final boolean isLexical()
  {
    return (this.flags & 0x10010010) == 0L;
  }
  
  public final boolean isNamespaceDecl()
  {
    return (this.flags & 0x200000) != 0L;
  }
  
  public final boolean isPrivate()
  {
    return (this.flags & 0x20) != 0L;
  }
  
  public final boolean isProcedureDecl()
  {
    return (this.flags & 0x80) != 0L;
  }
  
  public final boolean isPublic()
  {
    return ((this.context instanceof ModuleExp)) && ((this.flags & 0x20) == 0L);
  }
  
  public final boolean isSimple()
  {
    return (this.flags & 0x40) != 0L;
  }
  
  public boolean isStableSourceLocation()
  {
    return true;
  }
  
  public boolean isStatic()
  {
    boolean bool2 = true;
    boolean bool1;
    if (this.field != null) {
      bool1 = this.field.getStaticFlag();
    }
    LambdaExp localLambdaExp;
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (getFlag(2048L));
        bool1 = bool2;
      } while (isCompiletimeConstant());
      if (getFlag(4096L)) {
        return false;
      }
      localLambdaExp = this.context.currentLambda();
      if (!(localLambdaExp instanceof ModuleExp)) {
        break;
      }
      bool1 = bool2;
    } while (((ModuleExp)localLambdaExp).isStatic());
    return false;
  }
  
  public final boolean isThisParameter()
  {
    return this.symbol == ThisExp.THIS_NAME;
  }
  
  public void load(AccessExp paramAccessExp, int paramInt, Compilation paramCompilation, Target paramTarget)
  {
    if ((paramTarget instanceof IgnoreTarget)) {
      return;
    }
    if (paramAccessExp == null) {}
    Object localObject2;
    for (Object localObject1 = null; (isAlias()) && ((this.value instanceof ReferenceExp)); localObject1 = paramAccessExp.contextDecl())
    {
      localObject2 = (ReferenceExp)this.value;
      localObject3 = ((ReferenceExp)localObject2).binding;
      if ((localObject3 == null) || (((paramInt & 0x2) != 0) && (!((Declaration)localObject3).isIndirectBinding())) || ((localObject1 != null) && (((Declaration)localObject3).needsContext()))) {
        break;
      }
      ((Declaration)localObject3).load((AccessExp)localObject2, paramInt, paramCompilation, paramTarget);
      return;
    }
    if ((isFluid()) && ((this.context instanceof FluidLetExp)))
    {
      this.base.load(paramAccessExp, paramInt, paramCompilation, paramTarget);
      return;
    }
    CodeAttr localCodeAttr = paramCompilation.getCode();
    Object localObject3 = getType();
    boolean bool;
    if ((!isIndirectBinding()) && ((paramInt & 0x2) != 0))
    {
      if (this.field == null) {
        throw new Error("internal error: cannot take location of " + this);
      }
      bool = paramCompilation.immediate;
      if (this.field.getStaticFlag())
      {
        paramAccessExp = ClassType.make("gnu.kawa.reflect.StaticFieldLocation");
        if (bool)
        {
          paramInt = 1;
          localObject1 = paramAccessExp.getDeclaredMethod("make", paramInt);
          if (!bool) {
            break label309;
          }
          paramCompilation.compileConstant(this);
          label242:
          localCodeAttr.emitInvokeStatic((Method)localObject1);
          localObject1 = paramAccessExp;
        }
      }
    }
    label309:
    label392:
    do
    {
      do
      {
        paramTarget.compileFromStack(paramCompilation, (Type)localObject1);
        return;
        paramInt = 2;
        break;
        paramAccessExp = ClassType.make("gnu.kawa.reflect.FieldLocation");
        if (bool) {}
        for (paramInt = 2;; paramInt = 3)
        {
          localObject2 = paramAccessExp.getDeclaredMethod("make", paramInt);
          loadOwningObject((Declaration)localObject1, paramCompilation);
          localObject1 = localObject2;
          break;
        }
        paramCompilation.compileConstant(this.field.getDeclaringClass().getName());
        paramCompilation.compileConstant(this.field.getName());
        break label242;
        if (this.field == null) {
          break label636;
        }
        paramCompilation.usedClass(this.field.getDeclaringClass());
        paramCompilation.usedClass(this.field.getType());
        if (this.field.getStaticFlag()) {
          break label624;
        }
        loadOwningObject((Declaration)localObject1, paramCompilation);
        localCodeAttr.emitGetField(this.field);
        localObject1 = localObject3;
      } while (!isIndirectBinding());
      localObject1 = localObject3;
    } while ((paramInt & 0x2) != 0);
    Label localLabel;
    if (paramAccessExp != null)
    {
      localObject1 = paramAccessExp.getFileName();
      if (localObject1 != null)
      {
        int i = paramAccessExp.getLineNumber();
        if (i > 0)
        {
          localObject2 = ClassType.make("gnu.mapping.UnboundLocationException");
          bool = localCodeAttr.isInTry();
          int j = paramAccessExp.getColumnNumber();
          paramAccessExp = new Label(localCodeAttr);
          paramAccessExp.define(localCodeAttr);
          localCodeAttr.emitInvokeVirtual(Compilation.getLocationMethod);
          localObject3 = new Label(localCodeAttr);
          ((Label)localObject3).define(localCodeAttr);
          localLabel = new Label(localCodeAttr);
          localLabel.setTypes(localCodeAttr);
          if (bool)
          {
            localCodeAttr.emitGoto(localLabel);
            label532:
            paramInt = 0;
            if (!bool) {
              paramInt = localCodeAttr.beginFragment(localLabel);
            }
            localCodeAttr.addHandler(paramAccessExp, (Label)localObject3, (ClassType)localObject2);
            localCodeAttr.emitDup((Type)localObject2);
            localCodeAttr.emitPushString((String)localObject1);
            localCodeAttr.emitPushInt(i);
            localCodeAttr.emitPushInt(j);
            localCodeAttr.emitInvokeVirtual(((ClassType)localObject2).getDeclaredMethod("setLine", 3));
            localCodeAttr.emitThrow();
            if (!bool) {
              break label958;
            }
            localLabel.define(localCodeAttr);
          }
        }
      }
    }
    for (;;)
    {
      localObject1 = Type.pointer_type;
      break;
      label624:
      localCodeAttr.emitGetStatic(this.field);
      break label392;
      label636:
      if ((isIndirectBinding()) && (paramCompilation.immediate) && (getVariable() == null))
      {
        Environment localEnvironment = Environment.getCurrent();
        if ((this.symbol instanceof Symbol)) {}
        for (localObject1 = (Symbol)this.symbol;; localObject1 = localEnvironment.getSymbol(this.symbol.toString()))
        {
          localLabel = null;
          localObject2 = localLabel;
          if (isProcedureDecl())
          {
            localObject2 = localLabel;
            if (paramCompilation.getLanguage().hasSeparateFunctionNamespace()) {
              localObject2 = EnvironmentKey.FUNCTION;
            }
          }
          paramCompilation.compileConstant(localEnvironment.getLocation((Symbol)localObject1, localObject2), Target.pushValue(Compilation.typeLocation));
          break;
        }
      }
      if (paramCompilation.immediate)
      {
        localObject1 = getConstantValue();
        if (localObject1 != null)
        {
          paramCompilation.compileConstant(localObject1, paramTarget);
          return;
        }
      }
      if ((this.value != QuoteExp.undefined_exp) && (ignorable()) && ((!(this.value instanceof LambdaExp)) || (!(((LambdaExp)this.value).outer instanceof ModuleExp))))
      {
        this.value.compile(paramCompilation, paramTarget);
        return;
      }
      localObject2 = getVariable();
      if (((this.context instanceof ClassExp)) && (localObject2 == null) && (!getFlag(128L)))
      {
        localObject1 = (ClassExp)this.context;
        if (((ClassExp)localObject1).isMakingClassPair())
        {
          localObject2 = ClassExp.slotToMethodName("get", getName());
          localObject2 = ((ClassExp)localObject1).type.getDeclaredMethod((String)localObject2, 0);
          ((ClassExp)localObject1).loadHeapFrame(paramCompilation);
          localCodeAttr.emitInvoke((Method)localObject2);
          break label392;
        }
      }
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = allocateVariable(localCodeAttr);
      }
      localCodeAttr.emitLoad((Variable)localObject1);
      break label392;
      localCodeAttr.setUnreachable();
      break label532;
      label958:
      localCodeAttr.endFragment(paramInt);
      continue;
      localCodeAttr.emitInvokeVirtual(Compilation.getLocationMethod);
    }
  }
  
  void loadOwningObject(Declaration paramDeclaration, Compilation paramCompilation)
  {
    Declaration localDeclaration = paramDeclaration;
    if (paramDeclaration == null) {
      localDeclaration = this.base;
    }
    if (localDeclaration != null)
    {
      localDeclaration.load(null, 0, paramCompilation, Target.pushObject);
      return;
    }
    getContext().currentLambda().loadHeapFrame(paramCompilation);
  }
  
  public void makeField(ClassType paramClassType, Compilation paramCompilation, Expression paramExpression)
  {
    boolean bool1 = needsExternalAccess();
    int j = 0;
    boolean bool2 = getFlag(16384L);
    boolean bool3 = getFlag(8192L);
    if ((paramCompilation.immediate) && ((this.context instanceof ModuleExp)) && (!bool2) && (!bool3)) {
      setIndirectBinding(true);
    }
    if ((isPublic()) || (bool1) || (paramCompilation.immediate)) {
      j = 0x0 | 0x1;
    }
    int i;
    if ((!isStatic()) && ((!getFlag(268501008L)) || (!isIndirectBinding()) || (isAlias())))
    {
      i = j;
      if ((paramExpression instanceof ClassExp))
      {
        i = j;
        if (((LambdaExp)paramExpression).getNeedsClosureEnv()) {}
      }
    }
    else
    {
      i = j | 0x8;
    }
    if (!isIndirectBinding())
    {
      j = i;
      if (!bool2) {
        break label231;
      }
      if (!shouldEarlyInit())
      {
        j = i;
        if (!(this.context instanceof ModuleExp)) {
          break label231;
        }
        j = i;
        if (!((ModuleExp)this.context).staticInitRun()) {
          break label231;
        }
      }
    }
    if (!(this.context instanceof ClassExp))
    {
      j = i;
      if (!(this.context instanceof ModuleExp)) {}
    }
    else
    {
      j = i | 0x10;
    }
    label231:
    Object localObject1 = getType().getImplementationType();
    Object localObject3 = localObject1;
    if (isIndirectBinding())
    {
      localObject3 = localObject1;
      if (!((Type)localObject1).isSubtype(Compilation.typeLocation)) {
        localObject3 = Compilation.typeLocation;
      }
    }
    if (!ignorable())
    {
      localObject1 = getName();
      Object localObject2;
      if (localObject1 == null) {
        localObject2 = "$unnamed$0";
      }
      for (i = "$unnamed$0".length() - 2;; i = ((String)localObject2).length())
      {
        int k = 0;
        while (paramClassType.getDeclaredField((String)localObject2) != null)
        {
          localObject1 = new StringBuilder().append(((String)localObject2).substring(0, i)).append('$');
          k += 1;
          localObject2 = k;
        }
        localObject2 = Compilation.mangleNameIfNeeded((String)localObject1);
        localObject1 = localObject2;
        if (getFlag(65536L)) {
          localObject1 = "loc$" + (String)localObject2;
        }
        localObject2 = localObject1;
        if (bool1)
        {
          localObject2 = localObject1;
          if (!getFlag(1073741824L)) {
            localObject2 = "$Prvt$" + (String)localObject1;
          }
        }
      }
      this.field = paramClassType.addField((String)localObject2, (Type)localObject3, j);
      if ((paramExpression instanceof QuoteExp))
      {
        localObject1 = ((QuoteExp)paramExpression).getValue();
        if ((!this.field.getStaticFlag()) || (!localObject1.getClass().getName().equals(((Type)localObject3).getName()))) {
          break label580;
        }
        paramClassType = paramCompilation.litTable.findLiteral(localObject1);
        if (paramClassType.field == null) {
          paramClassType.assign(this.field, paramCompilation.litTable);
        }
      }
    }
    label580:
    while ((!(localObject3 instanceof PrimType)) && (!"java.lang.String".equals(((Type)localObject3).getName())))
    {
      if ((!shouldEarlyInit()) && ((isIndirectBinding()) || ((paramExpression != null) && (!(paramExpression instanceof ClassExp))))) {
        BindingInitializer.create(this, paramExpression, paramCompilation);
      }
      return;
    }
    paramCompilation = (Compilation)localObject1;
    if ((localObject1 instanceof Char)) {
      paramCompilation = IntNum.make(((Char)localObject1).intValue());
    }
    this.field.setConstantValue(paramCompilation, paramClassType);
  }
  
  public void makeField(Compilation paramCompilation, Expression paramExpression)
  {
    setSimple(false);
    makeField(paramCompilation.mainClass, paramCompilation, paramExpression);
  }
  
  Location makeIndirectLocationFor()
  {
    if ((this.symbol instanceof Symbol)) {}
    for (Symbol localSymbol = (Symbol)this.symbol;; localSymbol = Namespace.EmptyNamespace.getSymbol(this.symbol.toString().intern())) {
      return Location.make(localSymbol);
    }
  }
  
  public void maybeIndirectBinding(Compilation paramCompilation)
  {
    if (((isLexical()) && (!(this.context instanceof ModuleExp))) || (this.context == paramCompilation.mainLambda)) {
      setIndirectBinding(true);
    }
  }
  
  public final boolean needsContext()
  {
    return (this.base == null) && (this.field != null) && (!this.field.getStaticFlag());
  }
  
  public final boolean needsExternalAccess()
  {
    return ((this.flags & 0x80020) == 524320L) || ((this.flags & 0x200020) == 2097184L);
  }
  
  public boolean needsInit()
  {
    return (!ignorable()) && ((this.value != QuoteExp.nullExp) || (this.base == null));
  }
  
  public final Declaration nextDecl()
  {
    return this.next;
  }
  
  public void noteValue(Expression paramExpression)
  {
    if (this.value == QuoteExp.undefined_exp)
    {
      if ((paramExpression instanceof LambdaExp)) {
        ((LambdaExp)paramExpression).nameDecl = this;
      }
      this.value = paramExpression;
    }
    while (this.value == paramExpression) {
      return;
    }
    if ((this.value instanceof LambdaExp)) {
      ((LambdaExp)this.value).nameDecl = null;
    }
    this.value = null;
  }
  
  public void printInfo(OutPort paramOutPort)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    printInfo(localStringBuffer);
    paramOutPort.print(localStringBuffer.toString());
  }
  
  public void printInfo(StringBuffer paramStringBuffer)
  {
    paramStringBuffer.append(this.symbol);
    paramStringBuffer.append('/');
    paramStringBuffer.append(this.id);
    paramStringBuffer.append("/fl:");
    paramStringBuffer.append(Long.toHexString(this.flags));
    if (ignorable()) {
      paramStringBuffer.append("(ignorable)");
    }
    Expression localExpression = this.typeExp;
    Type localType = getType();
    if ((localExpression != null) && (!(localExpression instanceof QuoteExp)))
    {
      paramStringBuffer.append("::");
      paramStringBuffer.append(localExpression);
    }
    for (;;)
    {
      if (this.base != null)
      {
        paramStringBuffer.append("(base:#");
        paramStringBuffer.append(this.base.id);
        paramStringBuffer.append(')');
      }
      return;
      if ((this.type != null) && (localType != Type.pointer_type))
      {
        paramStringBuffer.append("::");
        paramStringBuffer.append(localType.getName());
      }
    }
  }
  
  public void pushIndirectBinding(Compilation paramCompilation)
  {
    paramCompilation = paramCompilation.getCode();
    paramCompilation.emitPushString(getName());
    if (this.makeLocationMethod == null)
    {
      ClassType localClassType1 = Type.pointer_type;
      ClassType localClassType2 = Type.string_type;
      ClassType localClassType3 = Compilation.typeLocation;
      ClassType localClassType4 = Compilation.typeLocation;
      this.makeLocationMethod = localClassType3.addMethod("make", new Type[] { localClassType1, localClassType2 }, localClassType4, 9);
    }
    paramCompilation.emitInvokeStatic(this.makeLocationMethod);
  }
  
  public final void setAlias(boolean paramBoolean)
  {
    setFlag(paramBoolean, 256L);
  }
  
  public final void setCanCall()
  {
    setFlag(true, 4L);
    if (this.base != null) {
      this.base.setCanRead();
    }
  }
  
  public final void setCanCall(boolean paramBoolean)
  {
    setFlag(paramBoolean, 4L);
  }
  
  public final void setCanRead()
  {
    setFlag(true, 2L);
    if (this.base != null) {
      this.base.setCanRead();
    }
  }
  
  public final void setCanRead(boolean paramBoolean)
  {
    setFlag(paramBoolean, 2L);
  }
  
  public final void setCanWrite()
  {
    this.flags |= 0x8;
    if (this.base != null) {
      this.base.setCanRead();
    }
  }
  
  public final void setCanWrite(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x8;
      return;
    }
    this.flags &= 0xFFFFFFFFFFFFFFF7;
  }
  
  public void setCode(int paramInt)
  {
    if (paramInt >= 0) {
      throw new Error("code must be negative");
    }
    this.id = paramInt;
  }
  
  public final void setFile(String paramString)
  {
    this.filename = paramString;
  }
  
  public final void setFlag(long paramLong)
  {
    this.flags |= paramLong;
  }
  
  public final void setFlag(boolean paramBoolean, long paramLong)
  {
    if (paramBoolean)
    {
      this.flags |= paramLong;
      return;
    }
    this.flags &= (0xFFFFFFFFFFFFFFFF ^ paramLong);
  }
  
  public final void setFluid(boolean paramBoolean)
  {
    setFlag(paramBoolean, 16L);
  }
  
  public final void setIndirectBinding(boolean paramBoolean)
  {
    setFlag(paramBoolean, 1L);
  }
  
  public final void setLine(int paramInt)
  {
    setLine(paramInt, 0);
  }
  
  public final void setLine(int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (paramInt1 < 0) {
      i = 0;
    }
    paramInt1 = paramInt2;
    if (paramInt2 < 0) {
      paramInt1 = 0;
    }
    this.position = ((i << 12) + paramInt1);
  }
  
  public final void setLocation(SourceLocator paramSourceLocator)
  {
    this.filename = paramSourceLocator.getFileName();
    setLine(paramSourceLocator.getLineNumber(), paramSourceLocator.getColumnNumber());
  }
  
  public final void setName(Object paramObject)
  {
    this.symbol = paramObject;
  }
  
  public final void setNext(Declaration paramDeclaration)
  {
    this.next = paramDeclaration;
  }
  
  public final void setPrivate(boolean paramBoolean)
  {
    setFlag(paramBoolean, 32L);
  }
  
  public final void setProcedureDecl(boolean paramBoolean)
  {
    setFlag(paramBoolean, 128L);
  }
  
  public final void setSimple(boolean paramBoolean)
  {
    setFlag(paramBoolean, 64L);
    if ((this.var != null) && (!this.var.isParameter())) {
      this.var.setSimple(paramBoolean);
    }
  }
  
  public final void setSymbol(Object paramObject)
  {
    this.symbol = paramObject;
  }
  
  public final void setSyntax()
  {
    setSimple(false);
    setFlag(536920064L);
  }
  
  public final void setType(Type paramType)
  {
    this.type = paramType;
    if (this.var != null) {
      this.var.setType(paramType);
    }
    this.typeExp = QuoteExp.getInstance(paramType);
  }
  
  public final void setTypeExp(Expression paramExpression)
  {
    this.typeExp = paramExpression;
    if ((paramExpression instanceof TypeValue)) {}
    for (paramExpression = ((TypeValue)paramExpression).getImplementationType();; paramExpression = Language.getDefaultLanguage().getTypeFor(paramExpression, false))
    {
      Object localObject = paramExpression;
      if (paramExpression == null) {
        localObject = Type.pointer_type;
      }
      this.type = ((Type)localObject);
      if (this.var != null) {
        this.var.setType((Type)localObject);
      }
      return;
    }
  }
  
  public final void setValue(Expression paramExpression)
  {
    this.value = paramExpression;
  }
  
  boolean shouldEarlyInit()
  {
    return (getFlag(536870912L)) || (isCompiletimeConstant());
  }
  
  public String toString()
  {
    return "Declaration[" + this.symbol + '/' + this.id + ']';
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\Declaration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */