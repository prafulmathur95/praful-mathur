package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.ExceptionsAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Filter;
import gnu.bytecode.LocalVarsAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import java.util.Set;
import java.util.Vector;

public class LambdaExp
  extends ScopeExp
{
  public static final int ATTEMPT_INLINE = 4096;
  static final int CANNOT_INLINE = 32;
  static final int CAN_CALL = 4;
  static final int CAN_READ = 2;
  static final int CLASS_METHOD = 64;
  static final int DEFAULT_CAPTURES_ARG = 512;
  static final int IMPORTS_LEX_VARS = 8;
  static final int INLINE_ONLY = 8192;
  static final int METHODS_COMPILED = 128;
  static final int NEEDS_STATIC_LINK = 16;
  protected static final int NEXT_AVAIL_FLAG = 16384;
  public static final int NO_FIELD = 256;
  public static final int OVERLOADABLE_FIELD = 2048;
  public static final int SEQUENCE_RESULT = 1024;
  static Method searchForKeywordMethod3;
  static Method searchForKeywordMethod4;
  static final ApplyExp unknownContinuation = new ApplyExp((Expression)null, null);
  Vector applyMethods;
  Variable argsArray;
  public Expression body;
  Declaration capturedVars;
  Variable closureEnv;
  public Field closureEnvField;
  public Expression[] defaultArgs;
  private Declaration firstArgsArrayArg;
  public LambdaExp firstChild;
  Variable heapFrame;
  Initializer initChain;
  public LambdaExp inlineHome;
  public Keyword[] keywords;
  public int max_args;
  public int min_args;
  public Declaration nameDecl;
  public LambdaExp nextSibling;
  Method[] primBodyMethods;
  Method[] primMethods;
  Object[] properties;
  public Expression returnContinuation;
  public Type returnType;
  int selectorValue;
  public Field staticLinkField;
  Set<LambdaExp> tailCallers;
  Procedure thisValue;
  Variable thisVariable;
  Expression[] throwsSpecification;
  ClassType type = Compilation.typeProcedure;
  
  public LambdaExp() {}
  
  public LambdaExp(int paramInt)
  {
    this.min_args = paramInt;
    this.max_args = paramInt;
  }
  
  public LambdaExp(Expression paramExpression)
  {
    this.body = paramExpression;
  }
  
  final void addApplyMethod(Compilation paramCompilation, Field paramField)
  {
    LambdaExp localLambdaExp2 = this;
    LambdaExp localLambdaExp1 = localLambdaExp2;
    if (paramField != null)
    {
      localLambdaExp1 = localLambdaExp2;
      if (paramField.getStaticFlag()) {
        paramField = paramCompilation.getModule();
      }
    }
    for (;;)
    {
      if (paramField.applyMethods == null) {
        paramField.applyMethods = new Vector();
      }
      paramField.applyMethods.addElement(this);
      return;
      do
      {
        localLambdaExp2 = localLambdaExp1.outerLambda();
        if ((localLambdaExp2 instanceof ModuleExp)) {
          break;
        }
        localLambdaExp1 = localLambdaExp2;
      } while (localLambdaExp2.heapFrame == null);
      paramField = localLambdaExp2;
      if (!localLambdaExp2.getHeapFrameType().getSuperclass().isSubtype(Compilation.typeModuleBody)) {
        paramField = paramCompilation.getModule();
      }
    }
  }
  
  void addMethodFor(ClassType paramClassType, Compilation paramCompilation, ObjectType paramObjectType)
  {
    Object localObject1 = getName();
    LambdaExp localLambdaExp = outerLambda();
    int i2;
    int n;
    label32:
    int i3;
    label46:
    int i4;
    label70:
    Method[] arrayOfMethod;
    int i1;
    int j;
    label124:
    StringBuffer localStringBuffer;
    int m;
    label144:
    int k;
    label237:
    int i5;
    label271:
    label294:
    Object localObject2;
    Object localObject3;
    label370:
    Object localObject4;
    label393:
    label405:
    int i6;
    int i9;
    if (this.keywords == null)
    {
      i2 = 0;
      if (this.defaultArgs != null) {
        break label604;
      }
      n = 0;
      if ((this.flags & 0x200) == 0) {
        break label617;
      }
      i3 = 0;
      if ((this.max_args >= 0) && (this.min_args + i3 >= this.max_args)) {
        break label624;
      }
      i4 = 1;
      arrayOfMethod = new Method[i3 + 1];
      this.primBodyMethods = arrayOfMethod;
      if (this.primMethods == null) {
        this.primMethods = arrayOfMethod;
      }
      i1 = 0;
      if ((this.nameDecl == null) || (!this.nameDecl.getFlag(4096L))) {
        break label630;
      }
      j = 0;
      localStringBuffer = new StringBuffer(60);
      if (j == 0) {
        break label824;
      }
      m = 8;
      k = m;
      if (this.nameDecl != null)
      {
        if (!this.nameDecl.needsExternalAccess()) {
          break label830;
        }
        k = m | 0x1;
      }
      if (((!localLambdaExp.isModuleBody()) && (!(localLambdaExp instanceof ClassExp))) || (localObject1 == null))
      {
        localStringBuffer.append("lambda");
        m = paramCompilation.method_counter + 1;
        paramCompilation.method_counter = m;
        localStringBuffer.append(m);
      }
      if (i1 != 67) {
        break label881;
      }
      localStringBuffer.append("<clinit>");
      if (getFlag(1024)) {
        localStringBuffer.append("$C");
      }
      if ((getCallConvention() < 2) || (i1 != 0)) {
        break label902;
      }
      i5 = 1;
      m = k;
      if (i1 != 0)
      {
        if (j == 0) {
          break label908;
        }
        m = (k & 0xFFFFFFFD) + 1;
      }
      if (!paramClassType.isInterface())
      {
        j = m;
        if (!isAbstract()) {}
      }
      else
      {
        j = m | 0x400;
      }
      if ((isClassMethod()) && ((localLambdaExp instanceof ClassExp)) && (this.min_args == this.max_args))
      {
        localObject2 = null;
        k = 0;
        localObject3 = firstDecl();
        if (localObject3 != null) {
          break label919;
        }
        if (this.returnType == null) {
          break label976;
        }
      }
      if ((!getFlag(1024)) && (getCallConvention() < 2)) {
        break label1125;
      }
      localObject4 = Type.voidType;
      if ((paramObjectType == null) || (paramObjectType == paramClassType)) {
        break label1137;
      }
      k = 1;
      i6 = 0;
      m = i6;
      if (getCallConvention() >= 2)
      {
        m = i6;
        if (i1 == 0) {
          m = 1;
        }
      }
      i9 = localStringBuffer.length();
      i1 = 0;
    }
    for (;;)
    {
      if (i1 > i3) {
        return;
      }
      localStringBuffer.setLength(i9);
      int i8 = this.min_args + i1;
      i6 = i8;
      int i7 = i6;
      if (i1 == i3)
      {
        i7 = i6;
        if (i4 != 0) {
          i7 = i6 + 1;
        }
      }
      Type[] arrayOfType = new Type[k + i7 + m];
      if (k > 0) {
        arrayOfType[0] = paramObjectType;
      }
      localObject2 = firstDecl();
      localObject1 = localObject2;
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (((Declaration)localObject2).isThisParameter()) {
          localObject1 = ((Declaration)localObject2).nextDecl();
        }
      }
      i6 = 0;
      for (;;)
      {
        if (i6 < i8)
        {
          arrayOfType[(k + i6)] = ((Declaration)localObject1).getType().getImplementationType();
          localObject1 = ((Declaration)localObject1).nextDecl();
          i6 += 1;
          continue;
          i2 = this.keywords.length;
          break;
          label604:
          n = this.defaultArgs.length - i2;
          break label32;
          label617:
          i3 = n;
          break label46;
          label624:
          i4 = 0;
          break label70;
          label630:
          if ((this.nameDecl != null) && (this.nameDecl.getFlag(2048L)))
          {
            j = 1;
            break label124;
          }
          if (isClassMethod())
          {
            if ((localLambdaExp instanceof ClassExp))
            {
              localObject2 = (ClassExp)localLambdaExp;
              if ((((ClassExp)localObject2).isMakingClassPair()) && (paramObjectType != null)) {}
              for (j = 1;; j = 0)
              {
                if (this != ((ClassExp)localObject2).initMethod) {
                  break label715;
                }
                i1 = 73;
                break;
              }
              label715:
              if (this != ((ClassExp)localObject2).clinitMethod) {
                break label124;
              }
              i1 = 67;
              j = 1;
              break label124;
            }
            j = 0;
            break label124;
          }
          if ((this.thisVariable != null) || (paramObjectType == paramClassType))
          {
            j = 0;
            break label124;
          }
          if ((this.nameDecl != null) && ((this.nameDecl.context instanceof ModuleExp)))
          {
            localObject2 = (ModuleExp)this.nameDecl.context;
            if ((((ModuleExp)localObject2).getSuperType() == null) && (((ModuleExp)localObject2).getInterfaces() == null)) {}
            for (j = 1;; j = 0) {
              break;
            }
          }
          j = 1;
          break label124;
          label824:
          m = 0;
          break label144;
          label830:
          if (this.nameDecl.isPrivate()) {}
          for (int i = 0;; i = 1)
          {
            k = i;
            if (isClassMethod()) {
              k = this.nameDecl.getAccessFlags(i);
            }
            k = m | k;
            break;
          }
          label881:
          if (getSymbol() == null) {
            break label237;
          }
          localStringBuffer.append(Compilation.mangleName((String)localObject1));
          break label237;
          label902:
          i5 = 0;
          break label271;
          label908:
          m = (k & 0x2) + 2;
          break label294;
          label919:
          if (((Declaration)localObject3).isThisParameter())
          {
            m = k - 1;
            localObject1 = localObject2;
          }
          label937:
          label976:
          label1068:
          do
          {
            do
            {
              localObject3 = ((Declaration)localObject3).nextDecl();
              k = m + 1;
              localObject2 = localObject1;
              break;
              m = k;
              localObject1 = localObject2;
            } while (((Declaration)localObject3).getFlag(8192L));
            localObject1 = localObject2;
            if (localObject2 == null) {
              localObject1 = paramClassType.getMethods(new Filter()
              {
                public boolean select(Object paramAnonymousObject)
                {
                  paramAnonymousObject = (Method)paramAnonymousObject;
                  if (!((Method)paramAnonymousObject).getName().equals(this.val$mangled)) {}
                  while (((Method)paramAnonymousObject).getParameterTypes().length != LambdaExp.this.min_args) {
                    return false;
                  }
                  return true;
                }
              }, 2);
            }
            localObject4 = null;
            m = localObject1.length;
            do
            {
              m -= 1;
              if (m < 0) {
                break;
              }
              localObject2 = localObject1[m];
              if (localObject3 == null) {}
              for (localObject2 = ((Method)localObject2).getReturnType();; localObject2 = localObject2.getParameterTypes()[k])
              {
                if (localObject4 != null) {
                  break label1068;
                }
                localObject4 = localObject2;
                break;
              }
            } while (localObject2 == localObject4);
            m = k;
          } while (localObject3 != null);
          break label370;
          if (localObject4 != null)
          {
            if (localObject3 == null) {
              break label1116;
            }
            ((Declaration)localObject3).setType((Type)localObject4);
          }
          for (;;)
          {
            m = k;
            if (localObject3 != null) {
              break label937;
            }
            break;
            label1116:
            setCoercedReturnType((Type)localObject4);
          }
          label1125:
          localObject4 = getReturnType().getImplementationType();
          break label393;
          label1137:
          k = 0;
          break label405;
        }
      }
      if (m != 0) {
        arrayOfType[(arrayOfType.length - 1)] = Compilation.typeCallContext;
      }
      i6 = j;
      Object localObject5;
      if (i8 < i7)
      {
        localObject3 = ((Declaration)localObject1).getType();
        localObject5 = ((Type)localObject3).getName();
        if ((paramClassType.getClassfileVersion() >= 3211264) && ((localObject3 instanceof ArrayType)))
        {
          j |= 0x80;
          if ((i2 <= 0) && (i3 >= n))
          {
            localObject2 = localObject3;
            if (!"gnu.lists.LList".equals(localObject5))
            {
              localObject2 = localObject3;
              if ((localObject3 instanceof ArrayType)) {}
            }
          }
          else
          {
            localObject2 = Compilation.objArrayType;
            this.argsArray = new Variable("argsArray", Compilation.objArrayType);
            this.argsArray.setParameter(true);
          }
          this.firstArgsArrayArg = ((Declaration)localObject1);
          i7 = arrayOfType.length;
          if (i5 == 0) {
            break label1447;
          }
          i6 = 2;
          label1298:
          arrayOfType[(i7 - i6)] = localObject2;
          i6 = j;
        }
      }
      else
      {
        if (i5 != 0) {
          localStringBuffer.append("$X");
        }
        if ((!(localLambdaExp instanceof ClassExp)) && ((!(localLambdaExp instanceof ModuleExp)) || (!((ModuleExp)localLambdaExp).getFlag(131072)))) {
          break label1453;
        }
      }
      label1447:
      label1453:
      for (j = 1;; j = 0)
      {
        localObject1 = localStringBuffer.toString();
        i7 = 0;
        i8 = localStringBuffer.length();
        for (;;)
        {
          localObject2 = paramClassType;
          if (localObject2 == null) {
            break label1464;
          }
          if (((ClassType)localObject2).getDeclaredMethod((String)localObject1, arrayOfType) == null) {
            break;
          }
          localStringBuffer.setLength(i8);
          localStringBuffer.append('$');
          i7 += 1;
          localStringBuffer.append(i7);
          localObject1 = localStringBuffer.toString();
        }
        localStringBuffer.append("$V");
        break;
        i6 = 1;
        break label1298;
      }
      label1464:
      ClassType[] arrayOfClassType;
      label1517:
      Expression localExpression;
      if (j != 0)
      {
        localObject5 = paramClassType.addMethod((String)localObject1, arrayOfType, (Type)localObject4, i6);
        arrayOfMethod[i1] = localObject5;
        if ((this.throwsSpecification == null) || (this.throwsSpecification.length <= 0)) {
          break label1870;
        }
        i7 = this.throwsSpecification.length;
        arrayOfClassType = new ClassType[i7];
        j = 0;
        if (j >= i7) {
          break label1856;
        }
        localObject3 = null;
        localObject2 = null;
        localExpression = this.throwsSpecification[j];
        arrayOfType = null;
        if (!(localExpression instanceof ReferenceExp)) {
          break label1732;
        }
        localObject3 = (ReferenceExp)localExpression;
        localObject1 = ((ReferenceExp)localObject3).getBinding();
        if (localObject1 == null) {
          break label1703;
        }
        localObject3 = ((Declaration)localObject1).getValue();
        if (!(localObject3 instanceof ClassExp)) {
          break label1668;
        }
        localObject2 = ((ClassExp)localObject3).getCompiledClassType(paramCompilation);
        localObject1 = arrayOfType;
      }
      for (;;)
      {
        localObject3 = localObject1;
        if (localObject2 == null)
        {
          localObject3 = localObject1;
          if (localObject1 == null) {
            localObject3 = "invalid throws specification";
          }
        }
        if (localObject3 != null)
        {
          paramCompilation.error('e', (String)localObject3, localExpression);
          localObject2 = Type.javalangThrowableType;
        }
        arrayOfClassType[j] = localObject2;
        j += 1;
        break label1517;
        localObject2 = ((ClassType)localObject2).getSuperclass();
        break;
        label1668:
        localObject1 = "throws specification " + ((Declaration)localObject1).getName() + " has non-class lexical binding";
        continue;
        label1703:
        localObject1 = "unknown class " + ((ReferenceExp)localObject3).getName();
        continue;
        label1732:
        localObject1 = arrayOfType;
        if ((localExpression instanceof QuoteExp))
        {
          localObject2 = ((QuoteExp)localExpression).getValue();
          localObject1 = localObject2;
          if ((localObject2 instanceof Class)) {
            localObject1 = Type.make((Class)localObject2);
          }
          if ((localObject1 instanceof ClassType)) {
            localObject3 = (ClassType)localObject1;
          }
          localObject2 = localObject3;
          localObject1 = arrayOfType;
          if (localObject3 != null)
          {
            localObject2 = localObject3;
            localObject1 = arrayOfType;
            if (!((ClassType)localObject3).isSubtype(Type.javalangThrowableType))
            {
              localObject1 = ((ClassType)localObject3).getName() + " does not extend Throwable";
              localObject2 = localObject3;
            }
          }
        }
      }
      label1856:
      new ExceptionsAttr((Method)localObject5).setExceptions(arrayOfClassType);
      label1870:
      i1 += 1;
      j = i6;
    }
  }
  
  void addMethodFor(Compilation paramCompilation, ObjectType paramObjectType)
  {
    for (Object localObject = this; (localObject != null) && (!(localObject instanceof ClassExp)); localObject = ((ScopeExp)localObject).outer) {}
    if (localObject != null) {}
    for (localObject = ((ClassExp)localObject).instanceType;; localObject = getOwningLambda().getHeapFrameType())
    {
      addMethodFor((ClassType)localObject, paramCompilation, paramObjectType);
      return;
    }
  }
  
  public void allocChildClasses(Compilation paramCompilation)
  {
    Object localObject1 = getMainMethod();
    if ((localObject1 != null) && (!((Method)localObject1).getStaticFlag())) {
      declareThis(((Method)localObject1).getDeclaringClass());
    }
    localObject1 = firstDecl();
    if ((localObject1 == this.firstArgsArrayArg) && (this.argsArray != null)) {
      getVarScope().addVariable(this.argsArray);
    }
    if ((!getInlineOnly()) && (getCallConvention() >= 2))
    {
      if (this.firstArgsArrayArg != null) {
        break label120;
      }
      if (localObject1 != null) {}
    }
    for (;;)
    {
      getVarScope().addVariable(null, Compilation.typeCallContext, "$ctx").setParameter(true);
      label120:
      do
      {
        do
        {
          if (localObject1 != null) {
            break label152;
          }
          declareClosureEnv();
          allocFrame(paramCompilation);
          allocChildMethods(paramCompilation);
          return;
          if (this.argsArray == null) {
            break;
          }
        } while (localObject1 != this.firstArgsArrayArg);
        break;
      } while (localObject1 != this.firstArgsArrayArg.nextDecl());
    }
    label152:
    if ((((Declaration)localObject1).var != null) || ((getInlineOnly()) && (((Declaration)localObject1).ignorable()))) {}
    for (;;)
    {
      localObject1 = ((Declaration)localObject1).nextDecl();
      break;
      if ((((Declaration)localObject1).isSimple()) && (!((Declaration)localObject1).isIndirectBinding()))
      {
        ((Declaration)localObject1).allocateVariable(null);
      }
      else
      {
        Object localObject2 = Compilation.mangleName(((Declaration)localObject1).getName()).intern();
        Type localType = ((Declaration)localObject1).getType().getImplementationType();
        localObject2 = getVarScope().addVariable(null, localType, (String)localObject2);
        ((Declaration)localObject1).var = ((Variable)localObject2);
        ((Variable)localObject2).setParameter(true);
      }
    }
  }
  
  void allocChildMethods(Compilation paramCompilation)
  {
    LambdaExp localLambdaExp = this.firstChild;
    while (localLambdaExp != null)
    {
      if ((!localLambdaExp.isClassGenerated()) && (!localLambdaExp.getInlineOnly()) && (localLambdaExp.nameDecl != null)) {
        localLambdaExp.allocMethod(this, paramCompilation);
      }
      if ((localLambdaExp instanceof ClassExp))
      {
        ClassExp localClassExp = (ClassExp)localLambdaExp;
        if (localClassExp.getNeedsClosureEnv())
        {
          if ((!(this instanceof ModuleExp)) && (!(this instanceof ClassExp))) {
            break label109;
          }
          localObject = (ClassType)getType();
          localObject = localClassExp.instanceType.setOuterLink((ClassType)localObject);
          localClassExp.staticLinkField = ((Field)localObject);
          localClassExp.closureEnvField = ((Field)localObject);
        }
      }
      localLambdaExp = localLambdaExp.nextSibling;
      continue;
      label109:
      if (this.heapFrame != null) {}
      for (Object localObject = this.heapFrame;; localObject = this.closureEnv)
      {
        localObject = (ClassType)((Variable)localObject).getType();
        break;
      }
    }
  }
  
  Field allocFieldFor(Compilation paramCompilation)
  {
    if ((this.nameDecl != null) && (this.nameDecl.field != null))
    {
      paramCompilation = this.nameDecl.field;
      return paramCompilation;
    }
    boolean bool = getNeedsClosureEnv();
    ClassType localClassType;
    label47:
    Object localObject;
    String str;
    label59:
    int i;
    int j;
    if (bool)
    {
      localClassType = getOwningLambda().getHeapFrameType();
      localObject = getName();
      if (localObject != null) {
        break label311;
      }
      str = "lambda";
      i = 16;
      if ((this.nameDecl == null) || (!(this.nameDecl.context instanceof ModuleExp))) {
        break label325;
      }
      bool = this.nameDecl.needsExternalAccess();
      localObject = str;
      if (bool) {
        localObject = "$Prvt$" + str;
      }
      if (this.nameDecl.getFlag(2048L))
      {
        j = 0x10 | 0x8;
        i = j;
        if (!((ModuleExp)this.nameDecl.context).isStatic()) {
          i = j & 0xFFFFFFEF;
        }
      }
      if ((this.nameDecl.isPrivate()) && (!bool))
      {
        j = i;
        if (!paramCompilation.immediate) {}
      }
      else
      {
        j = i | 0x1;
      }
      i = j;
      paramCompilation = (Compilation)localObject;
      if ((this.flags & 0x800) != 0)
      {
        if (this.min_args != this.max_args) {
          break label319;
        }
        i = this.min_args;
      }
    }
    for (;;)
    {
      label233:
      paramCompilation = (String)localObject + '$' + i;
      if (localClassType.getDeclaredField(paramCompilation) == null)
      {
        i = j;
        for (;;)
        {
          localObject = localClassType.addField(paramCompilation, Compilation.typeModuleMethod, i);
          paramCompilation = (Compilation)localObject;
          if (this.nameDecl == null) {
            break;
          }
          this.nameDecl.field = ((Field)localObject);
          return (Field)localObject;
          localClassType = paramCompilation.mainClass;
          break label47;
          label311:
          str = Compilation.mangleNameIfNeeded((String)localObject);
          break label59;
          label319:
          i = 1;
          break label233;
          label325:
          localObject = new StringBuilder().append(str).append("$Fn");
          j = paramCompilation.localFieldIndex + 1;
          paramCompilation.localFieldIndex = j;
          localObject = j;
          paramCompilation = (Compilation)localObject;
          if (!bool)
          {
            i = 0x10 | 0x8;
            paramCompilation = (Compilation)localObject;
          }
        }
      }
      i += 1;
    }
  }
  
  public void allocFrame(Compilation paramCompilation)
  {
    if (this.heapFrame != null) {
      if ((!(this instanceof ModuleExp)) && (!(this instanceof ClassExp))) {
        break label36;
      }
    }
    label36:
    ClassType localClassType;
    for (paramCompilation = getCompiledClassType(paramCompilation);; paramCompilation = localClassType)
    {
      this.heapFrame.setType(paramCompilation);
      return;
      localClassType = new ClassType(paramCompilation.generateClassName("frame"));
      localClassType.setSuper(paramCompilation.getModuleType());
      paramCompilation.addClass(localClassType);
    }
  }
  
  void allocMethod(LambdaExp paramLambdaExp, Compilation paramCompilation)
  {
    if (!getNeedsClosureEnv()) {
      paramLambdaExp = null;
    }
    for (;;)
    {
      addMethodFor(paramCompilation, paramLambdaExp);
      return;
      if (((paramLambdaExp instanceof ClassExp)) || ((paramLambdaExp instanceof ModuleExp)))
      {
        paramLambdaExp = paramLambdaExp.getCompiledClassType(paramCompilation);
      }
      else
      {
        while (paramLambdaExp.heapFrame == null) {
          paramLambdaExp = paramLambdaExp.outerLambda();
        }
        paramLambdaExp = (ClassType)paramLambdaExp.heapFrame.getType();
      }
    }
  }
  
  void allocParameters(Compilation paramCompilation)
  {
    paramCompilation = paramCompilation.getCode();
    paramCompilation.locals.enterScope(getVarScope());
    int i = getLineNumber();
    if (i > 0) {
      paramCompilation.putLineNumber(getFileName(), i);
    }
    if (this.heapFrame != null) {
      this.heapFrame.allocateLocal(paramCompilation);
    }
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    setIndexes();
    paramCallContext.writeValue(new Closure(this, paramCallContext));
  }
  
  public void capture(Declaration paramDeclaration)
  {
    if (paramDeclaration.isSimple())
    {
      if ((this.capturedVars == null) && (!paramDeclaration.isStatic()) && (!(this instanceof ModuleExp)) && (!(this instanceof ClassExp))) {
        this.heapFrame = new Variable("heapFrame");
      }
      paramDeclaration.setSimple(false);
      if (!paramDeclaration.isPublic())
      {
        paramDeclaration.nextCapturedVar = this.capturedVars;
        this.capturedVars = paramDeclaration;
      }
    }
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    if ((paramTarget instanceof IgnoreTarget)) {
      return;
    }
    CodeAttr localCodeAttr = paramCompilation.getCode();
    Object localObject = outerLambda();
    ClassType localClassType = Compilation.typeModuleMethod;
    if (((this.flags & 0x100) != 0) || ((paramCompilation.immediate) && ((localObject instanceof ModuleExp))))
    {
      if (this.primMethods == null) {
        allocMethod(outerLambda(), paramCompilation);
      }
      compileAsMethod(paramCompilation);
      addApplyMethod(paramCompilation, null);
      ProcInitializer.emitLoadModuleMethod(this, paramCompilation);
    }
    Field localField;
    for (;;)
    {
      paramTarget.compileFromStack(paramCompilation, localClassType);
      return;
      localField = compileSetField(paramCompilation);
      if (!localField.getStaticFlag()) {
        break;
      }
      localCodeAttr.emitGetStatic(localField);
    }
    localObject = paramCompilation.curLambda;
    if (((LambdaExp)localObject).heapFrame != null) {}
    for (localObject = ((LambdaExp)localObject).heapFrame;; localObject = ((LambdaExp)localObject).closureEnv)
    {
      localCodeAttr.emitLoad((Variable)localObject);
      localCodeAttr.emitGetField(localField);
      break;
    }
  }
  
  void compileAsMethod(Compilation paramCompilation)
  {
    if (((this.flags & 0x80) != 0) || (isAbstract())) {}
    do
    {
      return;
      this.flags |= 0x80;
    } while (this.primMethods == null);
    Method localMethod = paramCompilation.method;
    LambdaExp localLambdaExp = paramCompilation.curLambda;
    paramCompilation.curLambda = this;
    boolean bool = this.primMethods[0].getStaticFlag();
    int i1 = this.primMethods.length - 1;
    Type localType = restArgType();
    Object localObject3 = null;
    Object localObject2;
    int i;
    Object localObject1;
    if (i1 > 0)
    {
      localObject2 = new long[this.min_args + i1];
      i = 0;
      localObject1 = firstDecl();
      for (;;)
      {
        localObject3 = localObject2;
        if (i >= this.min_args + i1) {
          break;
        }
        localObject2[i] = ((Declaration)localObject1).flags;
        localObject1 = ((Declaration)localObject1).nextDecl();
        i += 1;
      }
    }
    if (getCallConvention() >= 2) {}
    CodeAttr localCodeAttr;
    int k;
    for (int j = 1;; j = 0)
    {
      i = 0;
      if (i > i1) {
        break label679;
      }
      paramCompilation.method = this.primMethods[i];
      if (i >= i1) {
        break label586;
      }
      localCodeAttr = paramCompilation.method.startCode();
      k = i + 1;
      while ((k < i1) && ((this.defaultArgs[k] instanceof QuoteExp))) {
        k += 1;
      }
    }
    if ((k == i1) && (localType != null)) {}
    Variable localVariable;
    int n;
    for (int m = 1;; m = 0)
    {
      localVariable = paramCompilation.callContextVar;
      localObject2 = localCodeAttr.getArg(0);
      localObject1 = localObject2;
      if (!bool)
      {
        localCodeAttr.emitPushThis();
        if (getNeedsClosureEnv()) {
          this.closureEnv = ((Variable)localObject2);
        }
        localObject1 = localCodeAttr.getArg(1);
      }
      localObject2 = firstDecl();
      n = 0;
      while (n < this.min_args + i)
      {
        ((Declaration)localObject2).flags |= 0x40;
        ((Declaration)localObject2).var = ((Variable)localObject1);
        localCodeAttr.emitLoad((Variable)localObject1);
        localObject1 = ((Variable)localObject1).nextVar();
        n += 1;
        localObject2 = ((Declaration)localObject2).nextDecl();
      }
    }
    if (j != 0) {}
    for (Object localObject4 = localObject1;; localObject4 = null)
    {
      paramCompilation.callContextVar = ((Variable)localObject4);
      n = i;
      while (n < k)
      {
        localObject4 = StackTarget.getInstance(((Declaration)localObject2).getType());
        this.defaultArgs[n].compile(paramCompilation, (Target)localObject4);
        n += 1;
        localObject2 = ((Declaration)localObject2).nextDecl();
      }
    }
    if (m != 0)
    {
      localObject2 = localType.getName();
      if ("gnu.lists.LList".equals(localObject2))
      {
        localObject2 = new QuoteExp(LList.Empty);
        label459:
        ((Expression)localObject2).compile(paramCompilation, localType);
      }
    }
    else
    {
      if (j != 0) {
        localCodeAttr.emitLoad((Variable)localObject1);
      }
      if (!bool) {
        break label571;
      }
      localCodeAttr.emitInvokeStatic(this.primMethods[k]);
      label494:
      localCodeAttr.emitReturn();
      this.closureEnv = null;
      paramCompilation.callContextVar = localVariable;
    }
    for (;;)
    {
      i += 1;
      break;
      if ("java.lang.Object[]".equals(localObject2))
      {
        localObject2 = new QuoteExp(Values.noArgs);
        break label459;
      }
      throw new Error("unimplemented #!rest type " + (String)localObject2);
      label571:
      localCodeAttr.emitInvokeVirtual(this.primMethods[k]);
      break label494;
      label586:
      if (localObject3 != null)
      {
        k = 0;
        localObject1 = firstDecl();
        while (k < this.min_args + i1)
        {
          ((Declaration)localObject1).flags = localObject3[k];
          ((Declaration)localObject1).var = null;
          localObject1 = ((Declaration)localObject1).nextDecl();
          k += 1;
        }
      }
      paramCompilation.method.initCode();
      allocChildClasses(paramCompilation);
      allocParameters(paramCompilation);
      enterFunction(paramCompilation);
      compileBody(paramCompilation);
      compileEnd(paramCompilation);
      generateApplyMethods(paramCompilation);
    }
    label679:
    paramCompilation.method = localMethod;
    paramCompilation.curLambda = localLambdaExp;
  }
  
  public void compileBody(Compilation paramCompilation)
  {
    Variable localVariable = paramCompilation.callContextVar;
    paramCompilation.callContextVar = null;
    if (getCallConvention() >= 2)
    {
      localObject1 = getVarScope().lookup("$ctx");
      if ((localObject1 != null) && (((Variable)localObject1).getType() == Compilation.typeCallContext)) {
        paramCompilation.callContextVar = ((Variable)localObject1);
      }
    }
    for (Object localObject1 = ConsumerTarget.makeContextTarget(paramCompilation);; localObject1 = Target.pushValue(getReturnType()))
    {
      Expression localExpression = this.body;
      Object localObject2 = this;
      if (this.body.getLineNumber() > 0) {
        localObject2 = this.body;
      }
      localExpression.compileWithPosition(paramCompilation, (Target)localObject1, (Expression)localObject2);
      paramCompilation.callContextVar = localVariable;
      return;
    }
  }
  
  public void compileEnd(Compilation paramCompilation)
  {
    Object localObject = paramCompilation.getCode();
    if (!getInlineOnly())
    {
      if ((paramCompilation.method.reachableHere()) && ((Compilation.defaultCallConvention < 3) || (isModuleBody()) || (isClassMethod()) || (isHandlingTailCalls()))) {
        ((CodeAttr)localObject).emitReturn();
      }
      popScope((CodeAttr)localObject);
      ((CodeAttr)localObject).popScope();
    }
    for (localObject = this.firstChild; localObject != null; localObject = ((LambdaExp)localObject).nextSibling) {
      if ((!((LambdaExp)localObject).getCanRead()) && (!((LambdaExp)localObject).getInlineOnly())) {
        ((LambdaExp)localObject).compileAsMethod(paramCompilation);
      }
    }
    if (this.heapFrame != null) {
      paramCompilation.generateConstructor(this);
    }
  }
  
  public Field compileSetField(Compilation paramCompilation)
  {
    if (this.primMethods == null) {
      allocMethod(outerLambda(), paramCompilation);
    }
    Field localField = allocFieldFor(paramCompilation);
    if (paramCompilation.usingCPStyle()) {
      compile(paramCompilation, Type.objectType);
    }
    for (;;)
    {
      return new ProcInitializer(this, paramCompilation, localField).field;
      compileAsMethod(paramCompilation);
      addApplyMethod(paramCompilation, localField);
    }
  }
  
  public Variable declareClosureEnv()
  {
    Object localObject2;
    Object localObject1;
    if ((this.closureEnv == null) && (getNeedsClosureEnv()))
    {
      localObject2 = outerLambda();
      localObject1 = localObject2;
      if ((localObject2 instanceof ClassExp)) {
        localObject1 = ((LambdaExp)localObject2).outerLambda();
      }
      if (((LambdaExp)localObject1).heapFrame == null) {
        break label82;
      }
      localObject2 = ((LambdaExp)localObject1).heapFrame;
      if ((!isClassMethod()) || ("*init*".equals(getName()))) {
        break label90;
      }
      this.closureEnv = declareThis(this.type);
    }
    for (;;)
    {
      return this.closureEnv;
      label82:
      localObject2 = ((LambdaExp)localObject1).closureEnv;
      break;
      label90:
      if ((((LambdaExp)localObject1).heapFrame == null) && (!((LambdaExp)localObject1).getNeedsStaticLink()) && (!(localObject1 instanceof ModuleExp)))
      {
        this.closureEnv = null;
      }
      else if ((!isClassGenerated()) && (!getInlineOnly()))
      {
        localObject1 = getMainMethod();
        boolean bool = "*init*".equals(getName());
        if ((!((Method)localObject1).getStaticFlag()) && (!bool))
        {
          this.closureEnv = declareThis(((Method)localObject1).getDeclaringClass());
        }
        else
        {
          this.closureEnv = new Variable("closureEnv", localObject1.getParameterTypes()[0]);
          if (bool) {}
          for (localObject1 = declareThis(((Method)localObject1).getDeclaringClass());; localObject1 = null)
          {
            getVarScope().addVariableAfter((Variable)localObject1, this.closureEnv);
            this.closureEnv.setParameter(true);
            break;
          }
        }
      }
      else if (inlinedIn((LambdaExp)localObject1))
      {
        this.closureEnv = ((Variable)localObject2);
      }
      else
      {
        this.closureEnv = new Variable("closureEnv", ((Variable)localObject2).getType());
        getVarScope().addVariable(this.closureEnv);
      }
    }
  }
  
  public Variable declareThis(ClassType paramClassType)
  {
    if (this.thisVariable == null)
    {
      this.thisVariable = new Variable("this");
      getVarScope().addVariableAfter(null, this.thisVariable);
      this.thisVariable.setParameter(true);
    }
    if (this.thisVariable.getType() == null) {
      this.thisVariable.setType(paramClassType);
    }
    if ((this.decls != null) && (this.decls.isThisParameter())) {
      this.decls.var = this.thisVariable;
    }
    return this.thisVariable;
  }
  
  void enterFunction(Compilation paramCompilation)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    getVarScope().noteStartFunction(localCodeAttr);
    if ((this.closureEnv != null) && (!this.closureEnv.isParameter()) && (!paramCompilation.usingCPStyle()))
    {
      if (!getInlineOnly())
      {
        localCodeAttr.emitPushThis();
        localObject2 = this.closureEnvField;
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = outerLambda().closureEnvField;
        }
        localCodeAttr.emitGetField((Field)localObject1);
        localCodeAttr.emitStore(this.closureEnv);
      }
    }
    else
    {
      if (paramCompilation.usingCPStyle()) {
        break label187;
      }
      if (this.heapFrame != null) {
        break label163;
      }
      localObject1 = currentModule().getCompiledClassType(paramCompilation);
      label108:
      localObject2 = this.capturedVars;
      label113:
      if (localObject2 == null) {
        break label187;
      }
      if (((Declaration)localObject2).field == null) {
        break label177;
      }
    }
    for (;;)
    {
      localObject2 = ((Declaration)localObject2).nextCapturedVar;
      break label113;
      if (inlinedIn(outerLambda())) {
        break;
      }
      outerLambda().loadHeapFrame(paramCompilation);
      localCodeAttr.emitStore(this.closureEnv);
      break;
      label163:
      localObject1 = (ClassType)this.heapFrame.getType();
      break label108;
      label177:
      ((Declaration)localObject2).makeField((ClassType)localObject1, paramCompilation, null);
    }
    label187:
    if ((this.heapFrame != null) && (!paramCompilation.usingCPStyle()))
    {
      localObject1 = (ClassType)this.heapFrame.getType();
      if ((this.closureEnv != null) && (!(this instanceof ModuleExp))) {
        this.staticLinkField = ((ClassType)localObject1).addField("staticLink", this.closureEnv.getType());
      }
      if ((!(this instanceof ModuleExp)) && (!(this instanceof ClassExp)))
      {
        ((ClassType)localObject1).setEnclosingMember(paramCompilation.method);
        localCodeAttr.emitNew((ClassType)localObject1);
        localCodeAttr.emitDup((Type)localObject1);
        localCodeAttr.emitInvokeSpecial(Compilation.getConstructor((ClassType)localObject1, this));
        if (this.staticLinkField != null)
        {
          localCodeAttr.emitDup((Type)localObject1);
          localCodeAttr.emitLoad(this.closureEnv);
          localCodeAttr.emitPutField(this.staticLinkField);
        }
        localCodeAttr.emitStore(this.heapFrame);
      }
    }
    Object localObject1 = this.argsArray;
    Object localObject2 = localObject1;
    if (this.min_args == this.max_args)
    {
      localObject2 = localObject1;
      if (this.primMethods == null)
      {
        localObject2 = localObject1;
        if (getCallConvention() < 2) {
          localObject2 = null;
        }
      }
    }
    int k = 0;
    if (this.keywords == null)
    {
      i = 0;
      if (this.defaultArgs != null) {
        break label408;
      }
    }
    label408:
    for (int i1 = 0;; i1 = this.defaultArgs.length - i)
    {
      if (!(this instanceof ModuleExp)) {
        break label421;
      }
      return;
      i = this.keywords.length;
      break;
    }
    label421:
    int i2 = -1;
    int i3 = 0;
    getMainMethod();
    Variable localVariable = paramCompilation.callContextVar;
    Declaration localDeclaration = firstDecl();
    int i = 0;
    int j = 0;
    label465:
    int m;
    int n;
    label519:
    Type localType;
    if (localDeclaration != null) {
      if (getCallConvention() < 2)
      {
        localObject1 = null;
        paramCompilation.callContextVar = ((Variable)localObject1);
        m = i3;
        n = i2;
        if (localDeclaration == this.firstArgsArrayArg)
        {
          m = i3;
          n = i2;
          if (localObject2 != null)
          {
            if (this.primMethods == null) {
              break label690;
            }
            n = k;
            m = n - this.min_args;
          }
        }
        if ((n < 0) && (localDeclaration.isSimple()) && (!localDeclaration.isIndirectBinding())) {
          break label1205;
        }
        localType = localDeclaration.getType();
        if (n < 0) {
          break label699;
        }
        localObject1 = Type.objectType;
        label556:
        if (!localDeclaration.isSimple()) {
          localDeclaration.loadOwningObject(null, paramCompilation);
        }
        if (n >= 0) {
          break label705;
        }
        localCodeAttr.emitLoad(localDeclaration.getVariable());
        label586:
        if (localType != localObject1) {
          CheckedTarget.emitCheckedCoerce(paramCompilation, this, k + 1, localType);
        }
        if (localDeclaration.isIndirectBinding()) {
          localDeclaration.pushIndirectBinding(paramCompilation);
        }
        if (!localDeclaration.isSimple()) {
          break label1185;
        }
        localObject1 = localDeclaration.getVariable();
        if (localDeclaration.isIndirectBinding()) {
          ((Variable)localObject1).setType(Compilation.typeLocation);
        }
        localCodeAttr.emitStore((Variable)localObject1);
      }
    }
    label690:
    label699:
    label705:
    label1185:
    label1205:
    for (;;)
    {
      k += 1;
      localDeclaration = localDeclaration.nextDecl();
      i3 = m;
      i2 = n;
      break;
      localObject1 = getVarScope().lookup("$ctx");
      break label465;
      n = 0;
      m = 0;
      break label519;
      localObject1 = localType;
      break label556;
      if (k < this.min_args)
      {
        localCodeAttr.emitLoad((Variable)localObject2);
        localCodeAttr.emitPushInt(k);
        localCodeAttr.emitArrayLoad(Type.objectType);
        break label586;
      }
      if (k < this.min_args + i1)
      {
        localCodeAttr.emitPushInt(k - n);
        localCodeAttr.emitLoad((Variable)localObject2);
        localCodeAttr.emitArrayLength();
        localCodeAttr.emitIfIntLt();
        localCodeAttr.emitLoad((Variable)localObject2);
        localCodeAttr.emitPushInt(k - n);
        localCodeAttr.emitArrayLoad();
        localCodeAttr.emitElse();
        localObject3 = this.defaultArgs;
        i2 = j + 1;
        localObject3[(m + j)].compile(paramCompilation, localType);
        localCodeAttr.emitFi();
        j = i2;
        break label586;
      }
      if ((this.max_args < 0) && (k == this.min_args + i1))
      {
        localCodeAttr.emitLoad((Variable)localObject2);
        localCodeAttr.emitPushInt(k - n);
        localCodeAttr.emitInvokeStatic(Compilation.makeListMethod);
        localObject1 = Compilation.scmListType;
        break label586;
      }
      localCodeAttr.emitLoad((Variable)localObject2);
      localCodeAttr.emitPushInt(this.min_args + i1 - n);
      Object localObject3 = this.keywords;
      i2 = i + 1;
      paramCompilation.compileConstant(localObject3[i]);
      localObject3 = this.defaultArgs;
      i3 = j + 1;
      localObject3 = localObject3[(m + j)];
      ArrayType localArrayType;
      PrimType localPrimType;
      ClassType localClassType1;
      ClassType localClassType2;
      ClassType localClassType3;
      if ((localObject3 instanceof QuoteExp))
      {
        if (searchForKeywordMethod4 == null)
        {
          localArrayType = Compilation.objArrayType;
          localPrimType = Type.intType;
          localClassType1 = Type.objectType;
          localClassType2 = Type.objectType;
          localClassType3 = Compilation.scmKeywordType;
          ClassType localClassType4 = Type.objectType;
          searchForKeywordMethod4 = localClassType3.addMethod("searchForKeyword", new Type[] { localArrayType, localPrimType, localClassType1, localClassType2 }, localClassType4, 9);
        }
        ((Expression)localObject3).compile(paramCompilation, localType);
        localCodeAttr.emitInvokeStatic(searchForKeywordMethod4);
        i = i2;
        j = i3;
        break label586;
      }
      if (searchForKeywordMethod3 == null)
      {
        localArrayType = Compilation.objArrayType;
        localPrimType = Type.intType;
        localClassType1 = Type.objectType;
        localClassType2 = Compilation.scmKeywordType;
        localClassType3 = Type.objectType;
        searchForKeywordMethod3 = localClassType2.addMethod("searchForKeyword", new Type[] { localArrayType, localPrimType, localClassType1 }, localClassType3, 9);
      }
      localCodeAttr.emitInvokeStatic(searchForKeywordMethod3);
      localCodeAttr.emitDup(1);
      paramCompilation.compileConstant(Special.dfault);
      localCodeAttr.emitIfEq();
      localCodeAttr.emitPop(1);
      ((Expression)localObject3).compile(paramCompilation, localType);
      localCodeAttr.emitFi();
      i = i2;
      j = i3;
      break label586;
      localCodeAttr.emitPutField(localDeclaration.field);
      continue;
      paramCompilation.callContextVar = localVariable;
      return;
    }
  }
  
  Object evalDefaultArg(int paramInt, CallContext paramCallContext)
  {
    try
    {
      paramCallContext = this.defaultArgs[paramInt].eval(paramCallContext);
      return paramCallContext;
    }
    catch (Throwable paramCallContext)
    {
      throw new WrappedException("error evaluating default argument", paramCallContext);
    }
  }
  
  public void generateApplyMethods(Compilation paramCompilation)
  {
    paramCompilation.generateMatchMethods(this);
    if (Compilation.defaultCallConvention >= 2)
    {
      paramCompilation.generateApplyMethodsWithContext(this);
      return;
    }
    paramCompilation.generateApplyMethodsWithoutContext(this);
  }
  
  Declaration getArg(int paramInt)
  {
    for (Declaration localDeclaration = firstDecl();; localDeclaration = localDeclaration.nextDecl())
    {
      if (localDeclaration == null) {
        throw new Error("internal error - getArg");
      }
      if (paramInt == 0) {
        return localDeclaration;
      }
      paramInt -= 1;
    }
  }
  
  public int getCallConvention()
  {
    int i = 2;
    if (isModuleBody())
    {
      if (Compilation.defaultCallConvention >= 2) {
        i = Compilation.defaultCallConvention;
      }
      return i;
    }
    if (isClassMethod()) {
      return 1;
    }
    if (Compilation.defaultCallConvention != 0) {
      return Compilation.defaultCallConvention;
    }
    return 1;
  }
  
  public LambdaExp getCaller()
  {
    return this.inlineHome;
  }
  
  public final boolean getCanCall()
  {
    return (this.flags & 0x4) != 0;
  }
  
  public final boolean getCanRead()
  {
    return (this.flags & 0x2) != 0;
  }
  
  public ClassType getClassType()
  {
    return this.type;
  }
  
  protected ClassType getCompiledClassType(Compilation paramCompilation)
  {
    if (this.type == Compilation.typeProcedure) {
      throw new Error("internal error: getCompiledClassType");
    }
    return this.type;
  }
  
  protected final String getExpClassName()
  {
    String str2 = getClass().getName();
    int i = str2.lastIndexOf('.');
    String str1 = str2;
    if (i >= 0) {
      str1 = str2.substring(i + 1);
    }
    return str1;
  }
  
  public ClassType getHeapFrameType()
  {
    if (((this instanceof ModuleExp)) || ((this instanceof ClassExp))) {
      return (ClassType)getType();
    }
    return (ClassType)this.heapFrame.getType();
  }
  
  public final boolean getImportsLexVars()
  {
    return (this.flags & 0x8) != 0;
  }
  
  public final boolean getInlineOnly()
  {
    return (this.flags & 0x2000) != 0;
  }
  
  public final Method getMainMethod()
  {
    Method[] arrayOfMethod = this.primBodyMethods;
    if (arrayOfMethod == null) {
      return null;
    }
    return arrayOfMethod[(arrayOfMethod.length - 1)];
  }
  
  public final Method getMethod(int paramInt)
  {
    if ((this.primMethods == null) || ((this.max_args >= 0) && (paramInt > this.max_args))) {}
    do
    {
      return null;
      paramInt -= this.min_args;
    } while (paramInt < 0);
    int i = this.primMethods.length;
    Method[] arrayOfMethod = this.primMethods;
    if (paramInt < i) {}
    for (;;)
    {
      return arrayOfMethod[paramInt];
      paramInt = i - 1;
    }
  }
  
  public final boolean getNeedsClosureEnv()
  {
    return (this.flags & 0x18) != 0;
  }
  
  public final boolean getNeedsStaticLink()
  {
    return (this.flags & 0x10) != 0;
  }
  
  public LambdaExp getOwningLambda()
  {
    for (ScopeExp localScopeExp = this.outer;; localScopeExp = localScopeExp.outer)
    {
      if (localScopeExp == null) {
        return null;
      }
      if (((localScopeExp instanceof ModuleExp)) || (((localScopeExp instanceof ClassExp)) && (getNeedsClosureEnv())) || (((localScopeExp instanceof LambdaExp)) && (((LambdaExp)localScopeExp).heapFrame != null))) {
        return (LambdaExp)localScopeExp;
      }
    }
  }
  
  public Object getProperty(Object paramObject1, Object paramObject2)
  {
    Object localObject = paramObject2;
    if (this.properties != null)
    {
      int i = this.properties.length;
      int j;
      do
      {
        j = i - 2;
        localObject = paramObject2;
        if (j < 0) {
          break;
        }
        i = j;
      } while (this.properties[j] != paramObject1);
      localObject = this.properties[(j + 1)];
    }
    return localObject;
  }
  
  public final Type getReturnType()
  {
    if (this.returnType == null)
    {
      this.returnType = Type.objectType;
      if ((this.body != null) && (!isAbstract())) {
        this.returnType = this.body.getType();
      }
    }
    return this.returnType;
  }
  
  int getSelectorValue(Compilation paramCompilation)
  {
    int j = this.selectorValue;
    int i = j;
    if (j == 0)
    {
      i = paramCompilation.maxSelectorValue;
      paramCompilation.maxSelectorValue = (this.primMethods.length + i);
      i += 1;
      this.selectorValue = i;
    }
    return i;
  }
  
  public Type getType()
  {
    return this.type;
  }
  
  public int incomingArgs()
  {
    if ((this.min_args == this.max_args) && (this.max_args <= 4) && (this.max_args > 0)) {
      return this.max_args;
    }
    return 1;
  }
  
  boolean inlinedIn(LambdaExp paramLambdaExp)
  {
    for (LambdaExp localLambdaExp = this; localLambdaExp.getInlineOnly(); localLambdaExp = localLambdaExp.getCaller()) {
      if (localLambdaExp == paramLambdaExp) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isAbstract()
  {
    return this.body == QuoteExp.abstractExp;
  }
  
  public final boolean isClassGenerated()
  {
    return (isModuleBody()) || ((this instanceof ClassExp));
  }
  
  public final boolean isClassMethod()
  {
    return (this.flags & 0x40) != 0;
  }
  
  public final boolean isHandlingTailCalls()
  {
    return (isModuleBody()) || ((Compilation.defaultCallConvention >= 3) && (!isClassMethod()));
  }
  
  public final boolean isModuleBody()
  {
    return this instanceof ModuleExp;
  }
  
  public void loadHeapFrame(Compilation paramCompilation)
  {
    for (LambdaExp localLambdaExp = paramCompilation.curLambda; (localLambdaExp != this) && (localLambdaExp.getInlineOnly()); localLambdaExp = localLambdaExp.getCaller()) {}
    CodeAttr localCodeAttr = paramCompilation.getCode();
    if ((localLambdaExp.heapFrame != null) && (this == localLambdaExp)) {
      localCodeAttr.emitLoad(localLambdaExp.heapFrame);
    }
    for (;;)
    {
      return;
      if (localLambdaExp.closureEnv != null)
      {
        localCodeAttr.emitLoad(localLambdaExp.closureEnv);
        paramCompilation = (ClassType)localLambdaExp.closureEnv.getType();
      }
      while (localLambdaExp != this)
      {
        Field localField = localLambdaExp.staticLinkField;
        Object localObject = paramCompilation;
        if (localField != null)
        {
          localObject = paramCompilation;
          if (localField.getDeclaringClass() == paramCompilation)
          {
            localCodeAttr.emitGetField(localField);
            localObject = (ClassType)localField.getType();
          }
        }
        localLambdaExp = localLambdaExp.outerLambda();
        paramCompilation = (Compilation)localObject;
        continue;
        localCodeAttr.emitPushThis();
        paramCompilation = paramCompilation.curClass;
      }
    }
  }
  
  protected boolean mustCompile()
  {
    if ((this.keywords != null) && (this.keywords.length > 0)) {
      return true;
    }
    if (this.defaultArgs != null)
    {
      int i = this.defaultArgs.length;
      Expression localExpression;
      do
      {
        int j;
        do
        {
          j = i - 1;
          if (j < 0) {
            break;
          }
          localExpression = this.defaultArgs[j];
          i = j;
        } while (localExpression == null);
        i = j;
      } while ((localExpression instanceof QuoteExp));
      return true;
    }
    return false;
  }
  
  public LambdaExp outerLambda()
  {
    if (this.outer == null) {
      return null;
    }
    return this.outer.currentLambda();
  }
  
  public LambdaExp outerLambdaNotInline()
  {
    Object localObject = this;
    LambdaExp localLambdaExp;
    do
    {
      ScopeExp localScopeExp;
      do
      {
        localScopeExp = ((ScopeExp)localObject).outer;
        if (localScopeExp == null) {
          break;
        }
        localObject = localScopeExp;
      } while (!(localScopeExp instanceof LambdaExp));
      localLambdaExp = (LambdaExp)localScopeExp;
      localObject = localScopeExp;
    } while (localLambdaExp.getInlineOnly());
    return localLambdaExp;
    return null;
  }
  
  public void print(OutPort paramOutPort)
  {
    paramOutPort.startLogicalBlock("(Lambda/", ")", 2);
    Object localObject1 = getSymbol();
    if (localObject1 != null)
    {
      paramOutPort.print(localObject1);
      paramOutPort.print('/');
    }
    paramOutPort.print(this.id);
    paramOutPort.print('/');
    paramOutPort.print("fl:");
    paramOutPort.print(Integer.toHexString(this.flags));
    paramOutPort.writeSpaceFill();
    printLineColumn(paramOutPort);
    paramOutPort.startLogicalBlock("(", false, ")");
    Object localObject2 = null;
    int j = 0;
    int i;
    int k;
    label109:
    Declaration localDeclaration;
    if (this.keywords == null)
    {
      i = 0;
      if (this.defaultArgs != null) {
        break label287;
      }
      k = 0;
      localDeclaration = firstDecl();
      if ((localDeclaration == null) || (!localDeclaration.isThisParameter())) {
        break label399;
      }
      j = -1;
      i = 0;
    }
    for (;;)
    {
      label131:
      if (localDeclaration != null) {
        if (j < this.min_args)
        {
          localObject1 = null;
          label146:
          if (localDeclaration != firstDecl()) {
            paramOutPort.writeSpaceFill();
          }
          if (localObject1 != localObject2)
          {
            paramOutPort.print(localObject1);
            paramOutPort.writeSpaceFill();
          }
          localObject2 = null;
          if ((localObject1 != Special.optional) && (localObject1 != Special.key)) {
            break label396;
          }
          localObject2 = this.defaultArgs;
          int m = i + 1;
          localObject2 = localObject2[i];
          i = m;
        }
      }
      label287:
      label396:
      for (;;)
      {
        if (localObject2 != null) {
          paramOutPort.print('(');
        }
        localDeclaration.printInfo(paramOutPort);
        if ((localObject2 != null) && (localObject2 != QuoteExp.falseExp))
        {
          paramOutPort.print(' ');
          ((Expression)localObject2).print(paramOutPort);
          paramOutPort.print(')');
        }
        j += 1;
        localDeclaration = localDeclaration.nextDecl();
        localObject2 = localObject1;
        break label131;
        i = this.keywords.length;
        break;
        k = this.defaultArgs.length - i;
        break label109;
        if (j < this.min_args + k)
        {
          localObject1 = Special.optional;
          break label146;
        }
        if ((this.max_args < 0) && (j == this.min_args + k))
        {
          localObject1 = Special.rest;
          break label146;
        }
        localObject1 = Special.key;
        break label146;
        paramOutPort.endLogicalBlock(")");
        paramOutPort.writeSpaceLinear();
        if (this.body == null) {
          paramOutPort.print("<null body>");
        }
        for (;;)
        {
          paramOutPort.endLogicalBlock(")");
          return;
          this.body.print(paramOutPort);
        }
      }
      label399:
      i = 0;
    }
  }
  
  public final Type restArgType()
  {
    if (this.min_args == this.max_args) {}
    do
    {
      return null;
      if (this.primMethods == null) {
        throw new Error("internal error - restArgType");
      }
      localObject = this.primMethods;
    } while ((this.max_args >= 0) && (localObject.length > this.max_args - this.min_args));
    Object localObject = localObject[(localObject.length - 1)];
    Type[] arrayOfType = ((Method)localObject).getParameterTypes();
    int j = arrayOfType.length - 1;
    int i = j;
    if (((Method)localObject).getName().endsWith("$X")) {
      i = j - 1;
    }
    return arrayOfType[i];
  }
  
  void setCallersNeedStaticLink()
  {
    LambdaExp localLambdaExp2 = outerLambda();
    for (ApplyExp localApplyExp = this.nameDecl.firstCall; localApplyExp != null; localApplyExp = localApplyExp.nextCall) {
      for (LambdaExp localLambdaExp1 = localApplyExp.context; (localLambdaExp1 != localLambdaExp2) && (!(localLambdaExp1 instanceof ModuleExp)); localLambdaExp1 = localLambdaExp1.outerLambda()) {
        localLambdaExp1.setNeedsStaticLink();
      }
    }
  }
  
  public final void setCanCall(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x4;
      return;
    }
    this.flags &= 0xFFFFFFFB;
  }
  
  public final void setCanRead(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x2;
      return;
    }
    this.flags &= 0xFFFFFFFD;
  }
  
  public final void setClassMethod(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x40;
      return;
    }
    this.flags &= 0xFFFFFFBF;
  }
  
  public final void setCoercedReturnType(Type paramType)
  {
    this.returnType = paramType;
    if ((paramType != null) && (paramType != Type.objectType) && (paramType != Type.voidType) && (this.body != QuoteExp.abstractExp))
    {
      Expression localExpression = this.body;
      this.body = Compilation.makeCoercion(localExpression, paramType);
      this.body.setLine(localExpression);
    }
  }
  
  public final void setCoercedReturnValue(Expression paramExpression, Language paramLanguage)
  {
    if (!isAbstract())
    {
      Expression localExpression = this.body;
      this.body = Compilation.makeCoercion(localExpression, paramExpression);
      this.body.setLine(localExpression);
    }
    paramExpression = paramLanguage.getTypeFor(paramExpression);
    if (paramExpression != null) {
      setReturnType(paramExpression);
    }
  }
  
  public void setExceptions(Expression[] paramArrayOfExpression)
  {
    this.throwsSpecification = paramArrayOfExpression;
  }
  
  public final void setImportsLexVars()
  {
    int i = this.flags;
    this.flags |= 0x8;
    if (((i & 0x8) == 0) && (this.nameDecl != null)) {
      setCallersNeedStaticLink();
    }
  }
  
  public final void setImportsLexVars(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x8;
      return;
    }
    this.flags &= 0xFFFFFFF7;
  }
  
  public final void setInlineOnly(boolean paramBoolean)
  {
    setFlag(paramBoolean, 8192);
  }
  
  public final void setNeedsStaticLink()
  {
    int i = this.flags;
    this.flags |= 0x10;
    if (((i & 0x10) == 0) && (this.nameDecl != null)) {
      setCallersNeedStaticLink();
    }
  }
  
  public final void setNeedsStaticLink(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.flags |= 0x10;
      return;
    }
    this.flags &= 0xFFFFFFEF;
  }
  
  public void setProperty(Object paramObject1, Object paramObject2)
  {
    try
    {
      this.properties = PropertySet.setProperty(this.properties, paramObject1, paramObject2);
      return;
    }
    finally
    {
      paramObject1 = finally;
      throw ((Throwable)paramObject1);
    }
  }
  
  public final void setReturnType(Type paramType)
  {
    this.returnType = paramType;
  }
  
  public void setType(ClassType paramClassType)
  {
    this.type = paramClassType;
  }
  
  public boolean side_effects()
  {
    return false;
  }
  
  public String toString()
  {
    String str2 = getExpClassName() + ':' + getSymbol() + '/' + this.id + '/';
    int j = getLineNumber();
    int i = j;
    if (j <= 0)
    {
      i = j;
      if (this.body != null) {
        i = this.body.getLineNumber();
      }
    }
    String str1 = str2;
    if (i > 0) {
      str1 = str2 + "l:" + i;
    }
    return str1;
  }
  
  public Expression validateApply(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Declaration paramDeclaration)
  {
    paramDeclaration = paramApplyExp.getArgs();
    if ((this.flags & 0x1000) != 0)
    {
      paramDeclaration = InlineCalls.inlineCall(this, paramDeclaration, true);
      if (paramDeclaration != null) {
        paramType = paramInlineCalls.visit(paramDeclaration, paramType);
      }
    }
    int i;
    do
    {
      int j;
      do
      {
        do
        {
          do
          {
            return paramType;
            paramApplyExp.visitArgs(paramInlineCalls);
            i = paramApplyExp.args.length;
            paramType = WrongArguments.checkArgCount(getName(), this.min_args, this.max_args, i);
            if (paramType != null) {
              return paramInlineCalls.noteError(paramType);
            }
            j = getCallConvention();
            paramType = paramApplyExp;
          } while (!paramInlineCalls.getCompilation().inlineOk(this));
          paramType = paramApplyExp;
        } while (!isClassMethod());
        if (j <= 2) {
          break;
        }
        paramType = paramApplyExp;
      } while (j != 3);
      paramDeclaration = getMethod(i);
      paramType = paramApplyExp;
    } while (paramDeclaration == null);
    boolean bool = this.nameDecl.isStatic();
    if ((!bool) && ((this.outer instanceof ClassExp)) && (((ClassExp)this.outer).isMakingClassPair())) {}
    paramDeclaration = new PrimProcedure(paramDeclaration, this);
    if (bool) {
      paramInlineCalls = paramApplyExp.args;
    }
    for (;;)
    {
      return new ApplyExp(paramDeclaration, paramInlineCalls).setLine(paramApplyExp);
      Declaration localDeclaration;
      for (paramType = paramInlineCalls.getCurrentLambda();; paramType = paramType.outerLambda())
      {
        if (paramType == null) {
          return paramInlineCalls.noteError("internal error: missing " + this);
        }
        if (paramType.outer == this.outer)
        {
          localDeclaration = paramType.firstDecl();
          if ((localDeclaration != null) && (localDeclaration.isThisParameter())) {
            break;
          }
          return paramInlineCalls.noteError("calling non-static method " + getName() + " from static method " + paramType.getName());
        }
      }
      i = paramApplyExp.getArgCount();
      paramInlineCalls = new Expression[i + 1];
      System.arraycopy(paramApplyExp.getArgs(), 0, paramInlineCalls, 1, i);
      paramInlineCalls[0] = new ThisExp(localDeclaration);
    }
  }
  
  public final boolean variable_args()
  {
    return this.max_args < 0;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    localCompilation = paramExpVisitor.getCompilation();
    if (localCompilation == null) {
      localLambdaExp = null;
    }
    for (;;)
    {
      try
      {
        paramExpVisitor = paramExpVisitor.visitLambdaExp(this, paramD);
        return paramExpVisitor;
      }
      finally
      {
        if (localCompilation == null) {
          continue;
        }
        localCompilation.curLambda = localLambdaExp;
      }
      localLambdaExp = localCompilation.curLambda;
      localCompilation.curLambda = this;
    }
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    visitChildrenOnly(paramExpVisitor, paramD);
    visitProperties(paramExpVisitor, paramD);
  }
  
  protected final <R, D> void visitChildrenOnly(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    LambdaExp localLambdaExp = paramExpVisitor.currentLambda;
    paramExpVisitor.currentLambda = this;
    try
    {
      this.throwsSpecification = paramExpVisitor.visitExps(this.throwsSpecification, paramD);
      paramExpVisitor.visitDefaultArgs(this, paramD);
      if ((paramExpVisitor.exitValue == null) && (this.body != null)) {
        this.body = paramExpVisitor.update(this.body, paramExpVisitor.visit(this.body, paramD));
      }
      return;
    }
    finally
    {
      paramExpVisitor.currentLambda = localLambdaExp;
    }
  }
  
  protected final <R, D> void visitProperties(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    if (this.properties != null)
    {
      int j = this.properties.length;
      int i = 1;
      while (i < j)
      {
        Object localObject = this.properties[i];
        if ((localObject instanceof Expression)) {
          this.properties[i] = paramExpVisitor.visitAndUpdate((Expression)localObject, paramD);
        }
        i += 2;
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\LambdaExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */