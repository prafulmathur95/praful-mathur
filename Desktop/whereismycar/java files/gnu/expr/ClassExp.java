package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.OutPort;
import gnu.text.SourceLocator;
import java.util.Hashtable;
import java.util.Vector;

public class ClassExp
  extends LambdaExp
{
  public static final int CLASS_SPECIFIED = 65536;
  public static final int HAS_SUBCLASS = 131072;
  public static final int INTERFACE_SPECIFIED = 32768;
  public static final int IS_ABSTRACT = 16384;
  public String classNameSpecifier;
  public LambdaExp clinitMethod;
  boolean explicitInit;
  public LambdaExp initMethod;
  ClassType instanceType;
  boolean partsDeclared;
  boolean simple;
  public int superClassIndex = -1;
  public Expression[] supers;
  
  public ClassExp() {}
  
  public ClassExp(boolean paramBoolean)
  {
    this.simple = paramBoolean;
    ClassType localClassType = new ClassType();
    this.type = localClassType;
    this.instanceType = localClassType;
  }
  
  static void getImplMethods(ClassType paramClassType, String paramString, Type[] paramArrayOfType, Vector paramVector)
  {
    Object localObject;
    int i;
    if ((paramClassType instanceof PairClassType))
    {
      localObject = ((PairClassType)paramClassType).instanceType;
      Type[] arrayOfType = new Type[paramArrayOfType.length + 1];
      arrayOfType[0] = paramClassType;
      System.arraycopy(paramArrayOfType, 0, arrayOfType, 1, paramArrayOfType.length);
      localObject = ((ClassType)localObject).getDeclaredMethod(paramString, arrayOfType);
      if (localObject == null) {
        break label151;
      }
      i = paramVector.size();
      if ((i == 0) || (!paramVector.elementAt(i - 1).equals(localObject))) {
        paramVector.addElement(localObject);
      }
    }
    for (;;)
    {
      return;
      if (paramClassType.isInterface())
      {
        try
        {
          localObject = paramClassType.getReflectClass();
          if (localObject == null) {
            continue;
          }
          localObject = (ClassType)Type.make(Class.forName(paramClassType.getName() + "$class", false, ((Class)localObject).getClassLoader()));
        }
        catch (Throwable paramClassType)
        {
          return;
        }
        label151:
        paramClassType = paramClassType.getInterfaces();
        i = 0;
        while (i < paramClassType.length)
        {
          getImplMethods(paramClassType[i], paramString, paramArrayOfType, paramVector);
          i += 1;
        }
      }
    }
  }
  
  static void invokeDefaultSuperConstructor(ClassType paramClassType, Compilation paramCompilation, LambdaExp paramLambdaExp)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    Method localMethod = paramClassType.getDeclaredMethod("<init>", 0);
    if (localMethod == null)
    {
      paramCompilation.error('e', "super class does not have a default constructor");
      return;
    }
    localCodeAttr.emitPushThis();
    if ((paramClassType.hasOuterLink()) && ((paramLambdaExp instanceof ClassExp)))
    {
      paramLambdaExp = (ClassExp)paramLambdaExp;
      loadSuperStaticLink(paramLambdaExp.supers[paramLambdaExp.superClassIndex], paramClassType, paramCompilation);
    }
    localCodeAttr.emitInvokeSpecial(localMethod);
  }
  
  static void loadSuperStaticLink(Expression paramExpression, ClassType paramClassType, Compilation paramCompilation)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    paramExpression.compile(paramCompilation, Target.pushValue(Compilation.typeClassType));
    localCodeAttr.emitInvokeStatic(ClassType.make("gnu.expr.PairClassType").getDeclaredMethod("extractStaticLink", 1));
    localCodeAttr.emitCheckcast(paramClassType.getOuterLinkType());
  }
  
  public static String slotToMethodName(String paramString1, String paramString2)
  {
    String str = paramString2;
    if (!Compilation.isValidJavaName(paramString2)) {
      str = Compilation.mangleName(paramString2, false);
    }
    int i = str.length();
    paramString2 = new StringBuffer(i + 3);
    paramString2.append(paramString1);
    if (i > 0)
    {
      paramString2.append(Character.toTitleCase(str.charAt(0)));
      paramString2.append(str.substring(1));
    }
    return paramString2.toString();
  }
  
  private static void usedSuperClasses(ClassType paramClassType, Compilation paramCompilation)
  {
    paramCompilation.usedClass(paramClassType.getSuperclass());
    paramClassType = paramClassType.getInterfaces();
    if (paramClassType != null)
    {
      int i = paramClassType.length;
      for (;;)
      {
        i -= 1;
        if (i < 0) {
          break;
        }
        paramCompilation.usedClass(paramClassType[i]);
      }
    }
  }
  
  public Declaration addMethod(LambdaExp paramLambdaExp, Object paramObject)
  {
    Declaration localDeclaration = addDeclaration(paramObject, Compilation.typeProcedure);
    paramLambdaExp.outer = this;
    paramLambdaExp.setClassMethod(true);
    localDeclaration.noteValue(paramLambdaExp);
    localDeclaration.setFlag(1048576L);
    localDeclaration.setProcedureDecl(true);
    paramLambdaExp.setSymbol(paramObject);
    return localDeclaration;
  }
  
  public void compile(Compilation paramCompilation, Target paramTarget)
  {
    if ((paramTarget instanceof IgnoreTarget)) {
      return;
    }
    compileMembers(paramCompilation);
    compilePushClass(paramCompilation, paramTarget);
  }
  
  public ClassType compileMembers(Compilation paramCompilation)
  {
    ClassType localClassType1 = paramCompilation.curClass;
    Method localMethod = paramCompilation.method;
    ClassType localClassType2;
    Object localObject4;
    LambdaExp localLambdaExp;
    Object localObject7;
    Object localObject8;
    int j;
    Object localObject5;
    Object localObject9;
    for (;;)
    {
      try
      {
        localClassType2 = getCompiledClassType(paramCompilation);
        paramCompilation.curClass = localClassType2;
        localObject4 = outerLambda();
        localObject3 = null;
        if ((localObject4 instanceof ClassExp))
        {
          localObject1 = ((LambdaExp)localObject4).type;
          if (localObject1 != null)
          {
            localClassType2.setEnclosingMember((Member)localObject1);
            if ((localObject1 instanceof ClassType)) {
              ((ClassType)localObject1).addMemberClass(localClassType2);
            }
          }
          if (this.instanceType != localClassType2)
          {
            this.instanceType.setEnclosingMember(this.type);
            this.type.addMemberClass(this.instanceType);
          }
          usedSuperClasses(this.type, paramCompilation);
          if (this.type != this.instanceType) {
            usedSuperClasses(this.instanceType, paramCompilation);
          }
          localObject1 = getFileName();
          if (localObject1 != null) {
            localClassType2.setSourceFile((String)localObject1);
          }
          localLambdaExp = paramCompilation.curLambda;
          paramCompilation.curLambda = this;
          allocFrame(paramCompilation);
          localObject3 = this.firstChild;
          if (localObject3 == null) {
            break;
          }
          if (((LambdaExp)localObject3).isAbstract())
          {
            localObject3 = ((LambdaExp)localObject3).nextSibling;
            continue;
          }
        }
        else
        {
          if ((localObject4 != null) && (!(localObject4 instanceof ModuleExp)))
          {
            localObject1 = localMethod;
            continue;
          }
          localObject1 = localObject3;
          if (!(localObject4 instanceof ModuleExp)) {
            continue;
          }
          localObject1 = localObject3;
          if (this.type.getName().indexOf('$') <= 0) {
            continue;
          }
          localObject1 = ((LambdaExp)localObject4).type;
          continue;
        }
        localObject6 = paramCompilation.method;
        localObject7 = paramCompilation.curLambda;
        localObject8 = paramCompilation.getFileName();
        i = paramCompilation.getLineNumber();
        j = paramCompilation.getColumnNumber();
        paramCompilation.setLine((Expression)localObject3);
        paramCompilation.method = ((LambdaExp)localObject3).getMainMethod();
        Object localObject1 = ((LambdaExp)localObject3).nameDecl;
        if ((localObject1 == null) || (!((Declaration)localObject1).getFlag(2048L))) {
          ((LambdaExp)localObject3).declareThis(paramCompilation.curClass);
        }
        paramCompilation.curClass = this.instanceType;
        paramCompilation.curLambda = ((LambdaExp)localObject3);
        paramCompilation.method.initCode();
        ((LambdaExp)localObject3).allocChildClasses(paramCompilation);
        ((LambdaExp)localObject3).allocParameters(paramCompilation);
        if (!"*init*".equals(((LambdaExp)localObject3).getName())) {
          break label707;
        }
        localObject1 = paramCompilation.getCode();
        if (this.staticLinkField != null)
        {
          ((CodeAttr)localObject1).emitPushThis();
          ((CodeAttr)localObject1).emitLoad(((CodeAttr)localObject1).getCurrentScope().getVariable(1));
          ((CodeAttr)localObject1).emitPutField(this.staticLinkField);
        }
        localObject1 = ((LambdaExp)localObject3).body;
        if ((localObject1 instanceof BeginExp))
        {
          localObject1 = (BeginExp)localObject1;
          if (((BeginExp)localObject1).length == 0)
          {
            localObject1 = null;
            continue;
          }
          localObject1 = localObject1.exps[0];
          continue;
        }
        localObject5 = null;
        localObject4 = localObject5;
        if ((localObject1 instanceof ApplyExp))
        {
          localObject9 = ((ApplyExp)localObject1).func;
          localObject4 = localObject5;
          if ((localObject9 instanceof QuoteExp))
          {
            localObject9 = ((QuoteExp)localObject9).getValue();
            localObject4 = localObject5;
            if ((localObject9 instanceof PrimProcedure))
            {
              localObject9 = (PrimProcedure)localObject9;
              localObject4 = localObject5;
              if (((PrimProcedure)localObject9).isSpecial())
              {
                localObject4 = localObject5;
                if ("<init>".equals(((PrimProcedure)localObject9).method.getName())) {
                  localObject4 = ((PrimProcedure)localObject9).method.getDeclaringClass();
                }
              }
            }
          }
        }
        localObject5 = this.instanceType.getSuperclass();
        if (localObject4 != null)
        {
          ((Expression)localObject1).compileWithPosition(paramCompilation, Target.Ignore);
          if ((localObject4 != this.instanceType) && (localObject4 != localObject5)) {
            paramCompilation.error('e', "call to <init> for not this or super class");
          }
          ((LambdaExp)localObject3).enterFunction(paramCompilation);
          if (localObject4 != this.instanceType) {
            paramCompilation.callInitMethods(getCompiledClassType(paramCompilation), new Vector(10));
          }
          if (localObject4 == null) {
            break label699;
          }
          Expression.compileButFirst(((LambdaExp)localObject3).body, paramCompilation);
          ((LambdaExp)localObject3).compileEnd(paramCompilation);
          ((LambdaExp)localObject3).generateApplyMethods(paramCompilation);
          paramCompilation.method = ((Method)localObject6);
          paramCompilation.curClass = localClassType2;
          paramCompilation.curLambda = ((LambdaExp)localObject7);
          paramCompilation.setLine((String)localObject8, i, j);
          continue;
        }
        if (localObject5 == null) {
          continue;
        }
      }
      finally
      {
        paramCompilation.curClass = localClassType1;
        paramCompilation.method = localMethod;
      }
      invokeDefaultSuperConstructor((ClassType)localObject5, paramCompilation, this);
      continue;
      label699:
      ((LambdaExp)localObject3).compileBody(paramCompilation);
      continue;
      label707:
      ((LambdaExp)localObject3).enterFunction(paramCompilation);
      ((LambdaExp)localObject3).compileBody(paramCompilation);
    }
    if ((!this.explicitInit) && (!this.instanceType.isInterface())) {
      paramCompilation.generateConstructor(this.instanceType, this);
    }
    while (isAbstract())
    {
      arrayOfMethod = null;
      i = 0;
      break label1253;
      if (j >= i) {
        break label1227;
      }
      localObject4 = arrayOfMethod[j];
      localObject7 = ((Method)localObject4).getName();
      localObject8 = ((Method)localObject4).getParameterTypes();
      localObject5 = ((Method)localObject4).getReturnType();
      localObject3 = this.instanceType.getMethod((String)localObject7, (Type[])localObject8);
      if ((localObject3 == null) || (((Method)localObject3).isAbstract())) {
        break label857;
      }
      break label1259;
      if (this.initChain != null) {
        this.initChain.reportError("unimplemented: explicit constructor cannot initialize ", paramCompilation);
      }
    }
    Method[] arrayOfMethod = this.type.getAbstractMethods();
    int i = arrayOfMethod.length;
    break label1253;
    label857:
    if ((((String)localObject7).length() > 3) && (((String)localObject7).charAt(2) == 't') && (((String)localObject7).charAt(1) == 'e'))
    {
      int k = ((String)localObject7).charAt(0);
      if ((k == 103) || (k == 115))
      {
        if ((k == 115) && (((Type)localObject5).isVoid()) && (localObject8.length == 1))
        {
          localObject4 = localObject8[0];
          localObject9 = Character.toLowerCase(((String)localObject7).charAt(3)) + ((String)localObject7).substring(4);
          localObject6 = this.instanceType.getField((String)localObject9);
          localObject3 = localObject6;
          if (localObject6 == null) {
            localObject3 = this.instanceType.addField((String)localObject9, (Type)localObject4, 1);
          }
          localObject4 = this.instanceType.addMethod((String)localObject7, 1, (Type[])localObject8, (Type)localObject5).startCode();
          ((CodeAttr)localObject4).emitPushThis();
          if (k != 103) {
            break label1068;
          }
          ((CodeAttr)localObject4).emitGetField((Field)localObject3);
        }
        for (;;)
        {
          ((CodeAttr)localObject4).emitReturn();
          break label1259;
          if ((k != 103) || (localObject8.length != 0)) {
            break label1259;
          }
          localObject4 = localObject5;
          break;
          label1068:
          ((CodeAttr)localObject4).emitLoad(((CodeAttr)localObject4).getArg(1));
          ((CodeAttr)localObject4).emitPutField((Field)localObject3);
        }
      }
    }
    Object localObject6 = new Vector();
    getImplMethods(this.type, (String)localObject7, (Type[])localObject8, (Vector)localObject6);
    if (((Vector)localObject6).size() != 1) {
      if (((Vector)localObject6).size() != 0) {
        break label1268;
      }
    }
    label1227:
    label1253:
    label1259:
    label1268:
    for (Object localObject3 = "missing implementation for ";; localObject3 = "ambiguous implementation for ")
    {
      paramCompilation.error('e', (String)localObject3 + localObject4);
      break label1259;
      localObject4 = this.instanceType.addMethod((String)localObject7, 1, (Type[])localObject8, (Type)localObject5).startCode();
      for (localObject3 = ((CodeAttr)localObject4).getCurrentScope().firstVar(); localObject3 != null; localObject3 = ((Variable)localObject3).nextVar()) {
        ((CodeAttr)localObject4).emitLoad((Variable)localObject3);
      }
      ((CodeAttr)localObject4).emitInvokeStatic((Method)((Vector)localObject6).elementAt(0));
      ((CodeAttr)localObject4).emitReturn();
      break label1259;
      generateApplyMethods(paramCompilation);
      paramCompilation.curLambda = localLambdaExp;
      paramCompilation.curClass = localClassType1;
      paramCompilation.method = localMethod;
      return localClassType2;
      j = 0;
      break;
      j += 1;
      break;
    }
  }
  
  public void compilePushClass(Compilation paramCompilation, Target paramTarget)
  {
    ClassType localClassType1 = this.type;
    CodeAttr localCodeAttr = paramCompilation.getCode();
    paramCompilation.loadClassRef(localClassType1);
    boolean bool = getNeedsClosureEnv();
    if ((isSimple()) && (!bool)) {
      return;
    }
    int i;
    if ((isMakingClassPair()) || (bool)) {
      if (localClassType1 == this.instanceType)
      {
        localCodeAttr.emitDup(this.instanceType);
        localClassType1 = ClassType.make("gnu.expr.PairClassType");
        if (!bool) {
          break label156;
        }
        i = 3;
      }
    }
    Type[] arrayOfType;
    for (;;)
    {
      arrayOfType = new Type[i];
      int j = i;
      if (bool)
      {
        getOwningLambda().loadHeapFrame(paramCompilation);
        j = i - 1;
        arrayOfType[j] = Type.pointer_type;
      }
      ClassType localClassType2 = ClassType.make("java.lang.Class");
      for (;;)
      {
        j -= 1;
        if (j < 0) {
          break;
        }
        arrayOfType[j] = localClassType2;
      }
      paramCompilation.loadClassRef(this.instanceType);
      break;
      label156:
      i = 2;
      continue;
      localClassType1 = ClassType.make("gnu.bytecode.Type");
      i = 1;
    }
    localCodeAttr.emitInvokeStatic(localClassType1.addMethod("make", arrayOfType, localClassType1, 9));
    paramTarget.compileFromStack(paramCompilation, localClassType1);
  }
  
  public Field compileSetField(Compilation paramCompilation)
  {
    return new ClassInitializer(this, paramCompilation).field;
  }
  
  public void declareParts(Compilation paramCompilation)
  {
    if (this.partsDeclared) {}
    label166:
    do
    {
      return;
      this.partsDeclared = true;
      Hashtable localHashtable = new Hashtable();
      Object localObject1 = firstDecl();
      if (localObject1 != null)
      {
        int i;
        Object localObject2;
        Object localObject3;
        if (((Declaration)localObject1).getCanRead())
        {
          int j = ((Declaration)localObject1).getAccessFlags((short)1);
          i = j;
          if (((Declaration)localObject1).getFlag(2048L)) {
            i = j | 0x8;
          }
          if (!isMakingClassPair()) {
            break label166;
          }
          i |= 0x400;
          localObject2 = ((Declaration)localObject1).getType().getImplementationType();
          this.type.addMethod(slotToMethodName("get", ((Declaration)localObject1).getName()), i, Type.typeArray0, (Type)localObject2);
          localObject3 = this.type;
          String str = slotToMethodName("set", ((Declaration)localObject1).getName());
          PrimType localPrimType = Type.voidType;
          ((ClassType)localObject3).addMethod(str, i, new Type[] { localObject2 }, localPrimType);
        }
        for (;;)
        {
          localObject1 = ((Declaration)localObject1).nextDecl();
          break;
          localObject2 = Compilation.mangleNameIfNeeded(((Declaration)localObject1).getName());
          ((Declaration)localObject1).field = this.instanceType.addField((String)localObject2, ((Declaration)localObject1).getType(), i);
          ((Declaration)localObject1).setSimple(false);
          localObject3 = (Declaration)localHashtable.get(localObject2);
          if (localObject3 != null) {
            duplicateDeclarationError((Declaration)localObject3, (Declaration)localObject1, paramCompilation);
          }
          localHashtable.put(localObject2, localObject1);
        }
      }
      for (localObject1 = this.firstChild; localObject1 != null; localObject1 = ((LambdaExp)localObject1).nextSibling)
      {
        if (((LambdaExp)localObject1).isAbstract()) {
          setFlag(16384);
        }
        if ("*init*".equals(((LambdaExp)localObject1).getName()))
        {
          this.explicitInit = true;
          if (((LambdaExp)localObject1).isAbstract()) {
            paramCompilation.error('e', "*init* method cannot be abstract", (SourceLocator)localObject1);
          }
          if ((this.type instanceof PairClassType)) {
            paramCompilation.error('e', "'*init*' methods only supported for simple classes");
          }
        }
        ((LambdaExp)localObject1).outer = this;
        if (((localObject1 != this.initMethod) && (localObject1 != this.clinitMethod) && (((LambdaExp)localObject1).nameDecl != null) && (!((LambdaExp)localObject1).nameDecl.getFlag(2048L))) || (!isMakingClassPair())) {
          ((LambdaExp)localObject1).addMethodFor(this.type, paramCompilation, null);
        }
        if (isMakingClassPair()) {
          ((LambdaExp)localObject1).addMethodFor(this.instanceType, paramCompilation, this.type);
        }
      }
      if ((!this.explicitInit) && (!this.instanceType.isInterface())) {
        Compilation.getConstructor(this.instanceType, this);
      }
      if (isAbstract()) {
        this.instanceType.setModifiers(this.instanceType.getModifiers() | 0x400);
      }
    } while (this.nameDecl == null);
    this.instanceType.setModifiers(this.instanceType.getModifiers() & 0xFFFFFFFE | this.nameDecl.getAccessFlags((short)1));
  }
  
  public ClassType getClassType()
  {
    return this.type;
  }
  
  protected ClassType getCompiledClassType(Compilation paramCompilation)
  {
    return this.type;
  }
  
  public Type getType()
  {
    if (this.simple) {
      return Compilation.typeClass;
    }
    return Compilation.typeClassType;
  }
  
  public final boolean isAbstract()
  {
    return getFlag(16384);
  }
  
  public boolean isMakingClassPair()
  {
    return this.type != this.instanceType;
  }
  
  public boolean isSimple()
  {
    return this.simple;
  }
  
  protected boolean mustCompile()
  {
    return true;
  }
  
  public void print(OutPort paramOutPort)
  {
    paramOutPort.startLogicalBlock("(" + getExpClassName() + "/", ")", 2);
    Object localObject = getSymbol();
    if (localObject != null)
    {
      paramOutPort.print(localObject);
      paramOutPort.print('/');
    }
    paramOutPort.print(this.id);
    paramOutPort.print("/fl:");
    paramOutPort.print(Integer.toHexString(this.flags));
    if (this.supers.length > 0)
    {
      paramOutPort.writeSpaceFill();
      paramOutPort.startLogicalBlock("supers:", "", 2);
      i = 0;
      while (i < this.supers.length)
      {
        this.supers[i].print(paramOutPort);
        paramOutPort.writeSpaceFill();
        i += 1;
      }
      paramOutPort.endLogicalBlock("");
    }
    paramOutPort.print('(');
    int i = 0;
    if (this.keywords == null) {}
    for (;;)
    {
      for (localObject = firstDecl(); localObject != null; localObject = ((Declaration)localObject).nextDecl())
      {
        if (i > 0) {
          paramOutPort.print(' ');
        }
        ((Declaration)localObject).printInfo(paramOutPort);
        i += 1;
      }
      int j = this.keywords.length;
    }
    paramOutPort.print(") ");
    for (localObject = this.firstChild; localObject != null; localObject = ((LambdaExp)localObject).nextSibling)
    {
      paramOutPort.writeBreakLinear();
      ((LambdaExp)localObject).print(paramOutPort);
    }
    if (this.body != null)
    {
      paramOutPort.writeBreakLinear();
      this.body.print(paramOutPort);
    }
    paramOutPort.endLogicalBlock(")");
  }
  
  public void setSimple(boolean paramBoolean)
  {
    this.simple = paramBoolean;
  }
  
  public void setTypes(Compilation paramCompilation)
  {
    Object localObject1;
    int k;
    label25:
    Object localObject2;
    if (this.supers == null)
    {
      j = 0;
      localObject3 = new ClassType[j];
      localObject1 = null;
      k = 0;
      i = 0;
      if (k >= j) {
        break label224;
      }
      localObject2 = Language.getDefaultLanguage().getTypeFor(this.supers[k]);
      if ((localObject2 instanceof ClassType)) {
        break label92;
      }
      paramCompilation.setLine(this.supers[k]);
      paramCompilation.error('e', "invalid super type");
    }
    for (;;)
    {
      k += 1;
      break label25;
      j = this.supers.length;
      break;
      label92:
      localObject2 = (ClassType)localObject2;
      try
      {
        m = ((ClassType)localObject2).getModifiers();
        if ((m & 0x200) == 0)
        {
          if (i < k) {
            paramCompilation.error('e', "duplicate superclass for " + this);
          }
          localObject1 = localObject2;
          this.superClassIndex = k;
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        for (;;)
        {
          int n = 0;
          m = n;
          if (paramCompilation != null)
          {
            paramCompilation.error('e', "unknown super-type " + ((ClassType)localObject2).getName());
            m = n;
          }
        }
        int m = i + 1;
        localObject3[i] = localObject2;
        i = m;
      }
    }
    label224:
    if ((localObject1 != null) && ((this.flags & 0x8000) != 0)) {
      paramCompilation.error('e', "cannot be interface since has superclass");
    }
    if ((!this.simple) && (localObject1 == null) && ((this.flags & 0x10000) == 0) && ((getFlag(131072)) || ((this.nameDecl != null) && (this.nameDecl.isPublic()))))
    {
      localObject2 = new PairClassType();
      this.type = ((ClassType)localObject2);
      ((PairClassType)localObject2).setInterface(true);
      ((PairClassType)localObject2).instanceType = this.instanceType;
      localObject2 = this.type;
      this.instanceType.setSuper(Type.pointer_type);
      this.instanceType.setInterfaces(new ClassType[] { localObject2 });
      ClassType localClassType = this.type;
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = Type.pointer_type;
      }
      localClassType.setSuper((ClassType)localObject2);
      if (i != j) {
        break label567;
      }
      localObject1 = localObject3;
      label382:
      this.type.setInterfaces((ClassType[])localObject1);
      if (this.type.getName() == null)
      {
        if (this.classNameSpecifier == null) {
          break label586;
        }
        localObject1 = this.classNameSpecifier;
        label412:
        if (localObject1 != null) {
          break label666;
        }
        localObject2 = new StringBuffer(100);
        paramCompilation.getModule().classFor(paramCompilation);
        ((StringBuffer)localObject2).append(paramCompilation.mainClass.getName());
        ((StringBuffer)localObject2).append('$');
        j = ((StringBuffer)localObject2).length();
        i = 0;
        label463:
        ((StringBuffer)localObject2).append(i);
        localObject1 = ((StringBuffer)localObject2).toString();
        if (paramCompilation.findNamedClass((String)localObject1) != null) {
          break label651;
        }
      }
    }
    for (;;)
    {
      this.type.setName((String)localObject1);
      paramCompilation.addClass(this.type);
      if (isMakingClassPair())
      {
        this.instanceType.setName(this.type.getName() + "$class");
        paramCompilation.addClass(this.instanceType);
      }
      return;
      if (!getFlag(32768)) {
        break;
      }
      this.instanceType.setInterface(true);
      break;
      label567:
      localObject1 = new ClassType[i];
      System.arraycopy(localObject3, 0, localObject1, 0, i);
      break label382;
      label586:
      localObject2 = getName();
      localObject1 = localObject2;
      if (localObject2 == null) {
        break label412;
      }
      i = ((String)localObject2).length();
      localObject1 = localObject2;
      if (i <= 2) {
        break label412;
      }
      localObject1 = localObject2;
      if (((String)localObject2).charAt(0) != '<') {
        break label412;
      }
      localObject1 = localObject2;
      if (((String)localObject2).charAt(i - 1) != '>') {
        break label412;
      }
      localObject1 = ((String)localObject2).substring(1, i - 1);
      break label412;
      label651:
      ((StringBuffer)localObject2).setLength(j);
      i += 1;
      break label463;
      label666:
      if ((isSimple()) && (!(this instanceof ObjectExp))) {
        break label689;
      }
      localObject1 = paramCompilation.generateClassName((String)localObject1);
    }
    label689:
    int i = 0;
    Object localObject3 = new StringBuffer(100);
    label703:
    int j = ((String)localObject1).indexOf('.', i);
    if (j < 0)
    {
      if (i != 0) {
        break label885;
      }
      if (paramCompilation.mainClass != null) {
        break label843;
      }
      localObject2 = null;
      label732:
      if (localObject2 != null) {
        break label854;
      }
      j = -1;
      label739:
      if (j <= 0) {
        break label865;
      }
      ((StringBuffer)localObject3).append(((String)localObject2).substring(0, j + 1));
    }
    for (;;)
    {
      if (i < ((String)localObject1).length()) {
        ((StringBuffer)localObject3).append(Compilation.mangleNameIfNeeded(((String)localObject1).substring(i)));
      }
      localObject1 = ((StringBuffer)localObject3).toString();
      break;
      ((StringBuffer)localObject3).append(Compilation.mangleNameIfNeeded(((String)localObject1).substring(i, j)));
      j += 1;
      i = j;
      if (j >= ((String)localObject1).length()) {
        break label703;
      }
      ((StringBuffer)localObject3).append('.');
      i = j;
      break label703;
      label843:
      localObject2 = paramCompilation.mainClass.getName();
      break label732;
      label854:
      j = ((String)localObject2).lastIndexOf('.');
      break label739;
      label865:
      if (paramCompilation.classPrefix != null)
      {
        ((StringBuffer)localObject3).append(paramCompilation.classPrefix);
        continue;
        label885:
        if ((i == 1) && (i < ((String)localObject1).length()))
        {
          ((StringBuffer)localObject3).setLength(0);
          ((StringBuffer)localObject3).append(paramCompilation.mainClass.getName());
          ((StringBuffer)localObject3).append('$');
        }
      }
    }
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    Compilation localCompilation = paramExpVisitor.getCompilation();
    if (localCompilation == null) {
      return (R)paramExpVisitor.visitClassExp(this, paramD);
    }
    ClassType localClassType = localCompilation.curClass;
    try
    {
      localCompilation.curClass = this.type;
      paramExpVisitor = paramExpVisitor.visitClassExp(this, paramD);
      return paramExpVisitor;
    }
    finally
    {
      localCompilation.curClass = localClassType;
    }
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    LambdaExp localLambdaExp2 = paramExpVisitor.currentLambda;
    paramExpVisitor.currentLambda = this;
    this.supers = paramExpVisitor.visitExps(this.supers, this.supers.length, paramD);
    try
    {
      for (LambdaExp localLambdaExp1 = this.firstChild; (localLambdaExp1 != null) && (paramExpVisitor.exitValue == null); localLambdaExp1 = localLambdaExp1.nextSibling)
      {
        if (this.instanceType != null)
        {
          Declaration localDeclaration = localLambdaExp1.firstDecl();
          if ((localDeclaration != null) && (localDeclaration.isThisParameter())) {
            localDeclaration.setType(this.type);
          }
        }
        paramExpVisitor.visitLambdaExp(localLambdaExp1, paramD);
      }
      return;
    }
    finally
    {
      paramExpVisitor.currentLambda = localLambdaExp2;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ClassExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */