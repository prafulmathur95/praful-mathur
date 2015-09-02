package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassFileInput;
import gnu.bytecode.ClassType;
import gnu.bytecode.ClassTypeWriter;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.functions.MakeList;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.ConsumerWriter;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongArguments;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;

public class PrimProcedure
  extends MethodProc
  implements Inlineable
{
  private static ClassLoader systemClassLoader = PrimProcedure.class.getClassLoader();
  Type[] argTypes;
  Member member;
  gnu.bytecode.Method method;
  char mode;
  int op_code;
  Type retType;
  boolean sideEffectFree;
  LambdaExp source;
  
  public PrimProcedure(int paramInt, ClassType paramClassType, String paramString, Type paramType, Type[] paramArrayOfType)
  {
    this.op_code = paramInt;
    int i;
    if (paramInt == 184)
    {
      i = 8;
      this.method = paramClassType.addMethod(paramString, i, paramArrayOfType, paramType);
      this.retType = paramType;
      this.argTypes = paramArrayOfType;
      if (paramInt != 184) {
        break label70;
      }
    }
    for (;;)
    {
      this.mode = c;
      return;
      i = 0;
      break;
      label70:
      c = 'V';
    }
  }
  
  public PrimProcedure(int paramInt, Type paramType, Type[] paramArrayOfType)
  {
    this.op_code = paramInt;
    this.retType = paramType;
    this.argTypes = paramArrayOfType;
  }
  
  public PrimProcedure(gnu.bytecode.Method paramMethod)
  {
    init(paramMethod);
    if (paramMethod.getName().endsWith("$X")) {}
    for (paramMethod = Type.objectType;; paramMethod = paramMethod.getReturnType())
    {
      this.retType = paramMethod;
      return;
    }
  }
  
  public PrimProcedure(gnu.bytecode.Method paramMethod, char paramChar, Language paramLanguage)
  {
    this.mode = paramChar;
    init(paramMethod);
    Type[] arrayOfType = this.argTypes;
    int j = arrayOfType.length;
    this.argTypes = null;
    int i = j;
    for (;;)
    {
      int k = i - 1;
      if (k < 0) {
        break;
      }
      Type localType1 = arrayOfType[k];
      Type localType2 = paramLanguage.getLangTypeFor(localType1);
      i = k;
      if (localType1 != localType2)
      {
        if (this.argTypes == null)
        {
          this.argTypes = new Type[j];
          System.arraycopy(arrayOfType, 0, this.argTypes, 0, j);
        }
        this.argTypes[k] = localType2;
        i = k;
      }
    }
    if (this.argTypes == null) {
      this.argTypes = arrayOfType;
    }
    if (isConstructor()) {
      this.retType = paramMethod.getDeclaringClass();
    }
    do
    {
      return;
      if (paramMethod.getName().endsWith("$X"))
      {
        this.retType = Type.objectType;
        return;
      }
      this.retType = paramLanguage.getLangTypeFor(paramMethod.getReturnType());
    } while (this.retType != Type.toStringType);
    this.retType = Type.javalangStringType;
  }
  
  public PrimProcedure(gnu.bytecode.Method paramMethod, LambdaExp paramLambdaExp)
  {
    this(paramMethod);
    this.retType = paramLambdaExp.getReturnType();
    this.source = paramLambdaExp;
  }
  
  public PrimProcedure(gnu.bytecode.Method paramMethod, Language paramLanguage)
  {
    this(paramMethod, '\000', paramLanguage);
  }
  
  public PrimProcedure(String paramString1, String paramString2, int paramInt)
  {
    this(ClassType.make(paramString1).getDeclaredMethod(paramString2, paramInt));
  }
  
  public PrimProcedure(java.lang.reflect.Method paramMethod, Language paramLanguage)
  {
    this(((ClassType)paramLanguage.getTypeFor(paramMethod.getDeclaringClass())).getMethod(paramMethod), paramLanguage);
  }
  
  private void compileArgs(Expression[] paramArrayOfExpression, int paramInt, Type paramType, Compilation paramCompilation)
  {
    boolean bool2 = takesVarArgs();
    String str = getName();
    Object localObject3 = null;
    CodeAttr localCodeAttr = paramCompilation.getCode();
    int j;
    int i;
    int i3;
    int m;
    label77:
    int i2;
    int i1;
    int n;
    label155:
    Object localObject1;
    if (paramType == Type.voidType)
    {
      j = 1;
      i = this.argTypes.length - j;
      int k = i;
      if (takesContext()) {
        k = i - 1;
      }
      i3 = paramArrayOfExpression.length - paramInt;
      if ((paramType != null) && (j == 0)) {
        break label406;
      }
      m = 1;
      i2 = 0;
      i1 = 0;
      i = i2;
      boolean bool1 = bool2;
      if (bool2)
      {
        i = i2;
        bool1 = bool2;
        if ((this.method.getModifiers() & 0x80) != 0)
        {
          i = i2;
          bool1 = bool2;
          if (i3 > 0)
          {
            i = i2;
            bool1 = bool2;
            if (this.argTypes.length > 0)
            {
              if (m == 0) {
                break label412;
              }
              n = 0;
              i = i2;
              bool1 = bool2;
              if (i3 == n + k)
              {
                localObject1 = paramArrayOfExpression[(paramArrayOfExpression.length - 1)].getType();
                localObject2 = this.argTypes[(this.argTypes.length - 1)];
                i = i2;
                bool1 = bool2;
                if ((localObject1 instanceof ObjectType))
                {
                  i = i2;
                  bool1 = bool2;
                  if ((localObject2 instanceof ArrayType))
                  {
                    i = i2;
                    bool1 = bool2;
                    if (!(((ArrayType)localObject2).getComponentType() instanceof ArrayType))
                    {
                      i = i1;
                      if (!(localObject1 instanceof ArrayType)) {
                        i = 1;
                      }
                      bool1 = false;
                    }
                  }
                }
              }
            }
          }
        }
      }
      if (!bool1) {
        break label424;
      }
      if (m == 0) {
        break label418;
      }
      n = 1;
      label283:
      n = k - n;
      label290:
      if (this.source != null) {
        break label433;
      }
      localObject2 = null;
      label300:
      localObject1 = localObject2;
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (((Declaration)localObject2).isThisParameter()) {
          localObject1 = ((Declaration)localObject2).nextDecl();
        }
      }
      i1 = 0;
      localObject2 = localObject3;
      localObject3 = localObject1;
      localObject1 = localObject2;
      if (!bool1) {
        break label474;
      }
      localObject1 = localObject2;
      if (i1 != n) {
        break label474;
      }
      localObject1 = this.argTypes[(k - 1 + j)];
      if ((localObject1 != Compilation.scmListType) && (localObject1 != LangObjType.listType)) {
        break label445;
      }
      MakeList.compile(paramArrayOfExpression, paramInt + i1, paramCompilation);
    }
    label406:
    label412:
    label418:
    label424:
    label433:
    label445:
    label474:
    while (i1 >= i3)
    {
      return;
      j = 0;
      break;
      m = 0;
      break label77;
      n = 1;
      break label155;
      n = 0;
      break label283;
      n = paramArrayOfExpression.length - paramInt;
      break label290;
      localObject2 = this.source.firstDecl();
      break label300;
      localCodeAttr.emitPushInt(paramArrayOfExpression.length - paramInt - n);
      localObject1 = ((ArrayType)localObject1).getComponentType();
      localCodeAttr.emitNewArray((Type)localObject1);
    }
    if ((i != 0) && (i1 + 1 == i3))
    {
      i2 = 1;
      label498:
      if (i1 < n) {
        break label741;
      }
      localCodeAttr.emitDup(1);
      localCodeAttr.emitPushInt(i1 - n);
      paramCompilation.usedClass((Type)localObject1);
      if (i2 == 0) {
        break label811;
      }
      localObject2 = Type.objectType;
      label538:
      if (this.source != null) {
        break label818;
      }
    }
    label741:
    label811:
    label818:
    for (Object localObject2 = CheckedTarget.getInstance((Type)localObject2, str, i1 + 1);; localObject2 = CheckedTarget.getInstance((Type)localObject2, this.source, i1))
    {
      paramArrayOfExpression[(paramInt + i1)].compileNotePosition(paramCompilation, (Target)localObject2, paramArrayOfExpression[(paramInt + i1)]);
      if (i2 != 0)
      {
        localObject2 = ((ArrayType)localObject1).getComponentType();
        localCodeAttr.emitDup();
        localCodeAttr.emitInstanceof((Type)localObject1);
        localCodeAttr.emitIfIntNotZero();
        localCodeAttr.emitCheckcast((Type)localObject1);
        localCodeAttr.emitElse();
        localCodeAttr.emitPushInt(1);
        localCodeAttr.emitNewArray((Type)localObject2);
        localCodeAttr.emitDupX();
        localCodeAttr.emitSwap();
        localCodeAttr.emitPushInt(0);
        localCodeAttr.emitSwap();
        ((Type)localObject2).emitCoerceFromObject(localCodeAttr);
        localCodeAttr.emitArrayStore((Type)localObject1);
        localCodeAttr.emitFi();
      }
      if (i1 >= n) {
        localCodeAttr.emitArrayStore((Type)localObject1);
      }
      localObject2 = localObject3;
      if (localObject3 != null) {
        if (m == 0)
        {
          localObject2 = localObject3;
          if (i1 <= 0) {}
        }
        else
        {
          localObject2 = ((Declaration)localObject3).nextDecl();
        }
      }
      i1 += 1;
      localObject3 = localObject2;
      localObject2 = localObject1;
      break;
      i2 = 0;
      break label498;
      if ((localObject3 != null) && ((m != 0) || (i1 > 0))) {
        localObject1 = ((Declaration)localObject3).getType();
      }
      for (;;)
      {
        break;
        if (m != 0) {
          localObject1 = this.argTypes[(i1 + j)];
        } else if (i1 == 0) {
          localObject1 = paramType;
        } else {
          localObject1 = this.argTypes[(i1 - 1)];
        }
      }
      localObject2 = localObject1;
      break label538;
    }
  }
  
  public static void compileInvoke(Compilation paramCompilation, gnu.bytecode.Method paramMethod, Target paramTarget, boolean paramBoolean, int paramInt, Type paramType)
  {
    CodeAttr localCodeAttr = paramCompilation.getCode();
    paramCompilation.usedClass(paramMethod.getDeclaringClass());
    paramCompilation.usedClass(paramMethod.getReturnType());
    if (!takesContext(paramMethod)) {
      localCodeAttr.emitInvokeMethod(paramMethod, paramInt);
    }
    for (;;)
    {
      paramTarget.compileFromStack(paramCompilation, paramType);
      do
      {
        return;
        if ((!(paramTarget instanceof IgnoreTarget)) && ((!(paramTarget instanceof ConsumerTarget)) || (!((ConsumerTarget)paramTarget).isContextTarget()))) {
          break;
        }
        paramType = null;
        localObject = null;
        paramCompilation.loadCallContext();
        if ((paramTarget instanceof IgnoreTarget))
        {
          localObject = Compilation.typeCallContext;
          paramType = ((ClassType)localObject).getDeclaredField("consumer");
          localCodeAttr.pushScope();
          localObject = localCodeAttr.addLocal((Type)localObject);
          localCodeAttr.emitDup();
          localCodeAttr.emitGetField(paramType);
          localCodeAttr.emitStore((Variable)localObject);
          localCodeAttr.emitDup();
          localCodeAttr.emitGetStatic(ClassType.make("gnu.lists.VoidConsumer").getDeclaredField("instance"));
          localCodeAttr.emitPutField(paramType);
        }
        localCodeAttr.emitInvokeMethod(paramMethod, paramInt);
        if (paramBoolean)
        {
          paramCompilation.loadCallContext();
          localCodeAttr.emitInvoke(Compilation.typeCallContext.getDeclaredMethod("runUntilDone", 0));
        }
      } while (!(paramTarget instanceof IgnoreTarget));
      paramCompilation.loadCallContext();
      localCodeAttr.emitLoad((Variable)localObject);
      localCodeAttr.emitPutField(paramType);
      localCodeAttr.popScope();
      return;
      paramCompilation.loadCallContext();
      paramType = Type.objectType;
      localCodeAttr.pushScope();
      Object localObject = localCodeAttr.addLocal(Type.intType);
      paramCompilation.loadCallContext();
      localCodeAttr.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("startFromContext", 0));
      localCodeAttr.emitStore((Variable)localObject);
      localCodeAttr.emitWithCleanupStart();
      localCodeAttr.emitInvokeMethod(paramMethod, paramInt);
      localCodeAttr.emitWithCleanupCatch(null);
      paramCompilation.loadCallContext();
      localCodeAttr.emitLoad((Variable)localObject);
      localCodeAttr.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("cleanupFromContext", 1));
      localCodeAttr.emitWithCleanupDone();
      paramCompilation.loadCallContext();
      localCodeAttr.emitLoad((Variable)localObject);
      localCodeAttr.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("getFromContext", 1));
      localCodeAttr.popScope();
    }
  }
  
  public static void disassemble(Procedure paramProcedure, ClassTypeWriter paramClassTypeWriter)
    throws Exception
  {
    Object localObject1;
    if ((paramProcedure instanceof GenericProc))
    {
      localObject1 = (GenericProc)paramProcedure;
      int j = ((GenericProc)localObject1).getMethodCount();
      paramClassTypeWriter.print("Generic procedure with ");
      paramClassTypeWriter.print(j);
      if (j == 1) {}
      for (paramProcedure = " method.";; paramProcedure = "methods.")
      {
        paramClassTypeWriter.println(paramProcedure);
        int i = 0;
        while (i < j)
        {
          paramProcedure = ((GenericProc)localObject1).getMethod(i);
          if (paramProcedure != null)
          {
            paramClassTypeWriter.println();
            disassemble(paramProcedure, paramClassTypeWriter);
          }
          i += 1;
        }
      }
    }
    ClassType localClassType = null;
    Object localObject3 = paramProcedure.getClass();
    if ((paramProcedure instanceof ModuleMethod))
    {
      localObject2 = ((ModuleMethod)paramProcedure).module.getClass();
      localObject1 = localClassType;
    }
    Object localObject4;
    InputStream localInputStream;
    for (;;)
    {
      localObject3 = ((Class)localObject2).getClassLoader();
      localObject2 = ((Class)localObject2).getName();
      localObject4 = ((String)localObject2).replace('.', '/') + ".class";
      localClassType = new ClassType();
      localInputStream = ((ClassLoader)localObject3).getResourceAsStream((String)localObject4);
      if (localInputStream != null) {
        break;
      }
      throw new RuntimeException("missing resource " + (String)localObject4);
      localObject2 = localObject3;
      localObject1 = localClassType;
      if ((paramProcedure instanceof PrimProcedure))
      {
        localObject4 = ((PrimProcedure)paramProcedure).method;
        localObject2 = localObject3;
        localObject1 = localClassType;
        if (localObject4 != null)
        {
          localObject2 = ((gnu.bytecode.Method)localObject4).getDeclaringClass().getReflectClass();
          localObject1 = ((gnu.bytecode.Method)localObject4).getName();
        }
      }
    }
    new ClassFileInput(localClassType, localInputStream);
    paramClassTypeWriter.setClass(localClassType);
    localObject3 = ((ClassLoader)localObject3).getResource((String)localObject4);
    paramClassTypeWriter.print("In class ");
    paramClassTypeWriter.print((String)localObject2);
    if (localObject3 != null)
    {
      paramClassTypeWriter.print(" at ");
      paramClassTypeWriter.print(localObject3);
    }
    paramClassTypeWriter.println();
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      paramProcedure = paramProcedure.getName();
      if (paramProcedure == null)
      {
        paramClassTypeWriter.println("Anonymous function - unknown method.");
        return;
      }
      localObject2 = Compilation.mangleName(paramProcedure);
    }
    for (paramProcedure = localClassType.getMethods(); paramProcedure != null; paramProcedure = paramProcedure.getNext()) {
      if (paramProcedure.getName().equals(localObject2)) {
        paramClassTypeWriter.printMethod(paramProcedure);
      }
    }
    paramClassTypeWriter.flush();
  }
  
  public static void disassemble(Procedure paramProcedure, Writer paramWriter)
    throws Exception
  {
    disassemble(paramProcedure, new ClassTypeWriter(null, paramWriter, 0));
  }
  
  public static void disassemble$X(Procedure paramProcedure, CallContext paramCallContext)
    throws Exception
  {
    paramCallContext = paramCallContext.consumer;
    if ((paramCallContext instanceof Writer)) {}
    for (paramCallContext = (Writer)paramCallContext;; paramCallContext = new ConsumerWriter(paramCallContext))
    {
      disassemble(paramProcedure, paramCallContext);
      return;
    }
  }
  
  public static PrimProcedure getMethodFor(ClassType paramClassType, String paramString, Declaration paramDeclaration, Type[] paramArrayOfType, Language paramLanguage)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    int m = -1;
    int i = 0;
    if (paramString == null) {
      return null;
    }
    Object localObject1 = localObject3;
    int j;
    int i4;
    int n;
    int i2;
    label221:
    label286:
    int i1;
    int i3;
    try
    {
      String str1 = Compilation.mangleName(paramString);
      localObject1 = localObject3;
      String str2 = str1 + "$V";
      localObject1 = localObject3;
      String str3 = str1 + "$V$X";
      localObject1 = localObject3;
      String str4 = str1 + "$X";
      j = 1;
      localObject1 = localObject3;
      gnu.bytecode.Method localMethod = paramClassType.getDeclaredMethods();
      paramClassType = (ClassType)localObject2;
      localObject1 = paramClassType;
      if (localMethod != null)
      {
        localObject1 = paramClassType;
        if ((localMethod.getModifiers() & 0x9) != 9)
        {
          i4 = j;
          localObject3 = paramClassType;
          n = m;
          i2 = i;
          if (paramDeclaration != null)
          {
            localObject1 = paramClassType;
            if (paramDeclaration.base != null) {
              break label221;
            }
            i2 = i;
            n = m;
            localObject3 = paramClassType;
            i4 = j;
          }
        }
        label379:
        do
        {
          do
          {
            do
            {
              do
              {
                localObject1 = localObject3;
                localMethod = localMethod.getNext();
                j = i4;
                paramClassType = (ClassType)localObject3;
                m = n;
                i = i2;
                break;
                localObject1 = paramClassType;
                localObject2 = localMethod.getName();
                localObject1 = paramClassType;
                if (((String)localObject2).equals(str1)) {
                  break label489;
                }
                localObject1 = paramClassType;
                if (((String)localObject2).equals(str2)) {
                  break label489;
                }
                localObject1 = paramClassType;
                if (((String)localObject2).equals(str4)) {
                  break label489;
                }
                localObject1 = paramClassType;
                if (!((String)localObject2).equals(str3)) {
                  break label379;
                }
                break label489;
                localObject1 = localObject2;
                paramClassType = new PrimProcedure(localMethod, paramLanguage);
                localObject1 = localObject2;
                paramClassType.setName(paramString);
                localObject1 = localObject2;
                i = paramClassType.isApplicable(paramArrayOfType);
                i4 = j;
                localObject3 = localObject2;
                n = i1;
                i2 = i3;
              } while (i < 0);
              i4 = j;
              localObject3 = localObject2;
              n = i1;
              i2 = i3;
            } while (i < i1);
            if (i <= i1) {
              break label445;
            }
            localObject3 = paramClassType;
            break label547;
            i4 = j;
            localObject3 = paramClassType;
            n = m;
            i2 = i;
          } while (j == 0);
          localObject1 = paramClassType;
          if (((String)localObject2).equals("apply")) {
            break label562;
          }
          i4 = j;
          localObject3 = paramClassType;
          n = m;
          i2 = i;
          localObject1 = paramClassType;
        } while (!((String)localObject2).equals("apply$V"));
        break label562;
        label445:
        localObject3 = localObject2;
        if (localObject2 == null) {
          break label547;
        }
        localObject1 = localObject2;
        paramClassType = (PrimProcedure)MethodProc.mostSpecific((MethodProc)localObject2, paramClassType);
        localObject3 = paramClassType;
        if (paramClassType != null) {
          break label547;
        }
        localObject3 = paramClassType;
        if (i1 <= 0) {
          break label547;
        }
        return null;
      }
    }
    catch (SecurityException paramClassType) {}
    return (PrimProcedure)localObject1;
    label489:
    label547:
    label562:
    for (int k = 0;; k = 1)
    {
      localObject2 = paramClassType;
      i1 = m;
      i3 = i;
      if (k != 0) {
        break label286;
      }
      n = 0;
      j = n;
      localObject2 = paramClassType;
      i1 = m;
      i3 = i;
      if (i == 0) {
        break label286;
      }
      localObject2 = null;
      i1 = -1;
      i3 = 0;
      j = n;
      break label286;
      n = i;
      i4 = j;
      i2 = k;
      break;
    }
  }
  
  public static PrimProcedure getMethodFor(ClassType paramClassType, String paramString, Declaration paramDeclaration, Expression[] paramArrayOfExpression, Language paramLanguage)
  {
    int i = paramArrayOfExpression.length;
    Type[] arrayOfType = new Type[i];
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      arrayOfType[i] = paramArrayOfExpression[i].getType();
    }
    return getMethodFor(paramClassType, paramString, paramDeclaration, arrayOfType, paramLanguage);
  }
  
  public static PrimProcedure getMethodFor(Procedure paramProcedure, Declaration paramDeclaration, Type[] paramArrayOfType, Language paramLanguage)
  {
    Object localObject1 = paramProcedure;
    Object localObject2;
    int i;
    int j;
    if ((paramProcedure instanceof GenericProc))
    {
      localObject2 = (GenericProc)paramProcedure;
      localObject1 = ((GenericProc)localObject2).methods;
      paramProcedure = null;
      i = ((GenericProc)localObject2).count;
      do
      {
        j = i - 1;
        if (j < 0) {
          break;
        }
        i = j;
      } while (localObject1[j].isApplicable(paramArrayOfType) < 0);
      if (paramProcedure != null) {
        paramProcedure = null;
      }
    }
    do
    {
      return paramProcedure;
      paramProcedure = localObject1[j];
      i = j;
      break;
      localObject1 = paramProcedure;
      if (paramProcedure == null) {
        return null;
      }
      if (!(localObject1 instanceof PrimProcedure)) {
        break label116;
      }
      localObject2 = (PrimProcedure)localObject1;
      paramProcedure = (Procedure)localObject2;
    } while (((PrimProcedure)localObject2).isApplicable(paramArrayOfType) >= 0);
    label116:
    paramProcedure = getProcedureClass(localObject1);
    if (paramProcedure == null) {
      return null;
    }
    return getMethodFor((ClassType)Type.make(paramProcedure), ((Procedure)localObject1).getName(), paramDeclaration, paramArrayOfType, paramLanguage);
  }
  
  public static PrimProcedure getMethodFor(Procedure paramProcedure, Declaration paramDeclaration, Expression[] paramArrayOfExpression, Language paramLanguage)
  {
    int i = paramArrayOfExpression.length;
    Type[] arrayOfType = new Type[i];
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      arrayOfType[i] = paramArrayOfExpression[i].getType();
    }
    return getMethodFor(paramProcedure, paramDeclaration, arrayOfType, paramLanguage);
  }
  
  public static PrimProcedure getMethodFor(Procedure paramProcedure, Expression[] paramArrayOfExpression)
  {
    return getMethodFor(paramProcedure, null, paramArrayOfExpression, Language.getDefaultLanguage());
  }
  
  public static PrimProcedure getMethodFor(Class paramClass, String paramString, Declaration paramDeclaration, Expression[] paramArrayOfExpression, Language paramLanguage)
  {
    return getMethodFor((ClassType)Type.make(paramClass), paramString, paramDeclaration, paramArrayOfExpression, paramLanguage);
  }
  
  public static Class getProcedureClass(Object paramObject)
  {
    if ((paramObject instanceof ModuleMethod)) {}
    for (paramObject = ((ModuleMethod)paramObject).module.getClass();; paramObject = paramObject.getClass()) {
      try
      {
        ClassLoader localClassLoader1 = ((Class)paramObject).getClassLoader();
        ClassLoader localClassLoader2 = systemClassLoader;
        if (localClassLoader1 != localClassLoader2) {
          break;
        }
        return (Class)paramObject;
      }
      catch (SecurityException paramObject) {}
    }
    return null;
  }
  
  private void init(gnu.bytecode.Method paramMethod)
  {
    this.method = paramMethod;
    if ((paramMethod.getModifiers() & 0x8) != 0) {
      this.op_code = 184;
    }
    for (;;)
    {
      Type[] arrayOfType = paramMethod.getParameterTypes();
      Object localObject = arrayOfType;
      if (isConstructor())
      {
        localObject = arrayOfType;
        if (paramMethod.getDeclaringClass().hasOuterLink())
        {
          int i = arrayOfType.length - 1;
          localObject = new Type[i];
          System.arraycopy(arrayOfType, 1, localObject, 0, i);
        }
      }
      this.argTypes = ((Type[])localObject);
      return;
      localObject = paramMethod.getDeclaringClass();
      if (this.mode == 'P')
      {
        this.op_code = 183;
      }
      else
      {
        this.mode = 'V';
        if ("<init>".equals(paramMethod.getName())) {
          this.op_code = 183;
        } else if ((((ClassType)localObject).getModifiers() & 0x200) != 0) {
          this.op_code = 185;
        } else {
          this.op_code = 182;
        }
      }
    }
  }
  
  public static PrimProcedure makeBuiltinBinary(int paramInt, Type paramType)
  {
    return new PrimProcedure(paramInt, paramType, new Type[] { paramType, paramType });
  }
  
  public static PrimProcedure makeBuiltinUnary(int paramInt, Type paramType)
  {
    return new PrimProcedure(paramInt, paramType, new Type[] { paramType });
  }
  
  public static boolean takesContext(gnu.bytecode.Method paramMethod)
  {
    return paramMethod.getName().endsWith("$X");
  }
  
  public void apply(CallContext paramCallContext)
    throws Throwable
  {
    int k = this.argTypes.length;
    boolean bool = isConstructor();
    int i;
    if ((bool) && (this.method.getDeclaringClass().hasOuterLink())) {
      i = 1;
    }
    for (;;)
    {
      Object localObject1;
      Object localObject2;
      try
      {
        if (this.member != null) {
          break label159;
        }
        localObject1 = this.method.getDeclaringClass().getReflectClass();
        if (i == 0) {
          break label112;
        }
        j = 1;
        localObject2 = new Class[j + k];
        j = k;
      }
      catch (InvocationTargetException paramCallContext)
      {
        throw paramCallContext.getTargetException();
      }
      localObject2[(j + k)] = this.argTypes[k].getReflectClass();
      int j = k;
      break label348;
      i = 0;
      continue;
      label112:
      j = 0;
      continue;
      label159:
      label271:
      label348:
      do
      {
        j = 0;
        break;
        do
        {
          if (i != 0) {
            localObject2[0] = this.method.getDeclaringClass().getOuterLinkType().getReflectClass();
          }
          if (bool)
          {
            this.member = ((Class)localObject1).getConstructor((Class[])localObject2);
            if (!bool) {
              break label271;
            }
            localObject2 = paramCallContext.values;
            localObject1 = localObject2;
            if (i != 0)
            {
              i = localObject2.length + 1;
              localObject1 = new Object[i];
              System.arraycopy(localObject2, 0, localObject1, 1, i - 1);
              localObject1[0] = ((PairClassType)paramCallContext.value1).staticLink;
            }
            localObject1 = ((Constructor)this.member).newInstance((Object[])localObject1);
          }
          for (;;)
          {
            if (takesContext()) {
              return;
            }
            paramCallContext.consumer.writeObject(localObject1);
            return;
            if (this.method == Type.clone_method) {
              break;
            }
            this.member = ((Class)localObject1).getMethod(this.method.getName(), (Class[])localObject2);
            break;
            if (this.method == Type.clone_method)
            {
              localObject2 = paramCallContext.value1;
              localObject1 = localObject2.getClass().getComponentType();
              i = Array.getLength(localObject2);
              localObject1 = Array.newInstance((Class)localObject1, i);
              System.arraycopy(localObject2, 0, localObject1, 0, i);
            }
            else
            {
              localObject1 = this.retType.coerceToObject(((java.lang.reflect.Method)this.member).invoke(paramCallContext.value1, paramCallContext.values));
            }
          }
          k = j - 1;
        } while (k < 0);
      } while (i == 0);
      j = 1;
    }
  }
  
  void compile(Type paramType, ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    ClassType localClassType = null;
    Type localType1 = null;
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    CodeAttr localCodeAttr = paramCompilation.getCode();
    Type localType2 = this.retType;
    int j = 0;
    int i;
    if (isConstructor()) {
      if (this.method == null)
      {
        paramType = localType1;
        if (paramType.hasOuterLink()) {
          ClassExp.loadSuperStaticLink(arrayOfExpression[0], paramType, paramCompilation);
        }
        localType1 = null;
        i = 1;
      }
    }
    for (;;)
    {
      compileArgs(arrayOfExpression, i, localType1, paramCompilation);
      if (this.method != null) {
        break label257;
      }
      localCodeAttr.emitPrimop(opcode(), arrayOfExpression.length, this.retType);
      paramTarget.compileFromStack(paramCompilation, localType2);
      return;
      paramType = this.method.getDeclaringClass();
      break;
      if ((opcode() == 183) && (this.mode == 'P') && ("<init>".equals(this.method.getName())))
      {
        if (this.method == null) {}
        for (;;)
        {
          i = j;
          localType1 = paramType;
          if (!localClassType.hasOuterLink()) {
            break;
          }
          localCodeAttr.emitPushThis();
          localCodeAttr.emitLoad(localCodeAttr.getCurrentScope().getVariable(1));
          localType1 = null;
          i = 1;
          break;
          localClassType = this.method.getDeclaringClass();
        }
      }
      i = j;
      localType1 = paramType;
      if (takesTarget())
      {
        i = j;
        localType1 = paramType;
        if (this.method.getStaticFlag())
        {
          i = 1;
          localType1 = paramType;
        }
      }
    }
    label257:
    compileInvoke(paramCompilation, this.method, paramTarget, paramApplyExp.isTailCall(), this.op_code, localType2);
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget)
  {
    Object localObject3 = paramCompilation.getCode();
    if (this.method == null) {}
    for (Object localObject1 = null;; localObject1 = this.method.getDeclaringClass())
    {
      localObject2 = paramApplyExp.getArgs();
      if (!isConstructor()) {
        break label175;
      }
      if (!paramApplyExp.getFlag(8)) {
        break label161;
      }
      int j = localObject2.length;
      paramCompilation.letStart();
      localObject1 = new Expression[j];
      localObject1[0] = localObject2[0];
      int i = 1;
      while (i < j)
      {
        localObject3 = localObject2[i];
        localObject3 = paramCompilation.letVariable(null, ((Expression)localObject3).getType(), (Expression)localObject3);
        ((Declaration)localObject3).setCanRead(true);
        localObject1[i] = new ReferenceExp((Declaration)localObject3);
        i += 1;
      }
    }
    paramCompilation.letEnter();
    paramCompilation.letDone(new ApplyExp(paramApplyExp.func, (Expression[])localObject1)).compile(paramCompilation, paramTarget);
    return;
    label161:
    ((CodeAttr)localObject3).emitNew((ClassType)localObject1);
    ((CodeAttr)localObject3).emitDup((Type)localObject1);
    label175:
    Object localObject2 = WrongArguments.checkArgCount(this, localObject2.length);
    if (localObject2 != null) {
      paramCompilation.error('e', (String)localObject2);
    }
    if (getStaticFlag()) {
      localObject1 = null;
    }
    compile((Type)localObject1, paramApplyExp, paramCompilation, paramTarget);
  }
  
  public gnu.bytecode.Method getMethod()
  {
    return this.method;
  }
  
  public String getName()
  {
    String str = super.getName();
    if (str != null) {
      return str;
    }
    str = getVerboseName();
    setName(str);
    return str;
  }
  
  public Type getParameterType(int paramInt)
  {
    int i = paramInt;
    if (takesTarget())
    {
      if (paramInt == 0)
      {
        if (isConstructor()) {
          return Type.objectType;
        }
        return this.method.getDeclaringClass();
      }
      i = paramInt - 1;
    }
    paramInt = this.argTypes.length;
    if (i < paramInt - 1) {
      return this.argTypes[i];
    }
    boolean bool = takesVarArgs();
    if ((i < paramInt) && (!bool)) {
      return this.argTypes[i];
    }
    Type localType = this.argTypes[(paramInt - 1)];
    if ((localType instanceof ArrayType)) {
      return ((ArrayType)localType).getComponentType();
    }
    return Type.objectType;
  }
  
  public final Type[] getParameterTypes()
  {
    return this.argTypes;
  }
  
  public Type getReturnType()
  {
    return this.retType;
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression)
  {
    return this.retType;
  }
  
  public final boolean getStaticFlag()
  {
    return (this.method == null) || (this.method.getStaticFlag()) || (isConstructor());
  }
  
  public String getVerboseName()
  {
    StringBuffer localStringBuffer = new StringBuffer(100);
    if (this.method == null)
    {
      localStringBuffer.append("<op ");
      localStringBuffer.append(this.op_code);
      localStringBuffer.append('>');
    }
    for (;;)
    {
      localStringBuffer.append('(');
      int i = 0;
      while (i < this.argTypes.length)
      {
        if (i > 0) {
          localStringBuffer.append(',');
        }
        localStringBuffer.append(this.argTypes[i].getName());
        i += 1;
      }
      localStringBuffer.append(this.method.getDeclaringClass().getName());
      localStringBuffer.append('.');
      localStringBuffer.append(this.method.getName());
    }
    localStringBuffer.append(')');
    return localStringBuffer.toString();
  }
  
  public int isApplicable(Type[] paramArrayOfType)
  {
    int j = super.isApplicable(paramArrayOfType);
    int k = paramArrayOfType.length;
    int i = j;
    if (j == -1)
    {
      i = j;
      if (this.method != null)
      {
        i = j;
        if ((this.method.getModifiers() & 0x80) != 0)
        {
          i = j;
          if (k > 0)
          {
            i = j;
            if ((paramArrayOfType[(k - 1)] instanceof ArrayType))
            {
              Type[] arrayOfType = new Type[k];
              System.arraycopy(paramArrayOfType, 0, arrayOfType, 0, k - 1);
              arrayOfType[(k - 1)] = ((ArrayType)paramArrayOfType[(k - 1)]).getComponentType();
              i = super.isApplicable(arrayOfType);
            }
          }
        }
      }
    }
    return i;
  }
  
  public final boolean isConstructor()
  {
    return (opcode() == 183) && (this.mode != 'P');
  }
  
  public boolean isSideEffectFree()
  {
    return this.sideEffectFree;
  }
  
  public boolean isSpecial()
  {
    return this.mode == 'P';
  }
  
  public int match0(CallContext paramCallContext)
  {
    return matchN(ProcedureN.noArgs, paramCallContext);
  }
  
  public int match1(Object paramObject, CallContext paramCallContext)
  {
    return matchN(new Object[] { paramObject }, paramCallContext);
  }
  
  public int match2(Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    return matchN(new Object[] { paramObject1, paramObject2 }, paramCallContext);
  }
  
  public int match3(Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    return matchN(new Object[] { paramObject1, paramObject2, paramObject3 }, paramCallContext);
  }
  
  public int match4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
  {
    return matchN(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 }, paramCallContext);
  }
  
  public int matchN(Object[] paramArrayOfObject, CallContext paramCallContext)
  {
    int n = paramArrayOfObject.length;
    boolean bool1 = takesVarArgs();
    int m = minArgs();
    if (n < m) {
      return 0xFFF10000 | m;
    }
    if ((!bool1) && (n > m)) {
      return 0xFFF20000 | m;
    }
    int k = this.argTypes.length;
    Object localObject1 = null;
    Object localObject2 = null;
    int i;
    Object[] arrayOfObject;
    int j;
    Object localObject3;
    label166:
    label178:
    Object localObject6;
    Object localObject4;
    label214:
    Object localObject5;
    if ((takesTarget()) || (isConstructor()))
    {
      i = 1;
      boolean bool2 = takesContext();
      arrayOfObject = new Object[k];
      j = k;
      if (bool2)
      {
        j = k - 1;
        arrayOfObject[j] = paramCallContext;
      }
      localObject3 = localObject2;
      if (bool1)
      {
        localObject1 = this.argTypes[(j - 1)];
        if ((localObject1 != Compilation.scmListType) && (localObject1 != LangObjType.listType)) {
          break label267;
        }
        arrayOfObject[(j - 1)] = LList.makeList(paramArrayOfObject, m);
        localObject1 = Type.objectType;
        localObject3 = localObject2;
      }
      if (!isConstructor()) {
        break label307;
      }
      localObject2 = paramArrayOfObject[0];
      j = i;
      if (j >= paramArrayOfObject.length) {
        break label375;
      }
      localObject6 = paramArrayOfObject[j];
      if (j >= m) {
        break label341;
      }
      localObject4 = this.argTypes[(j - i)];
      localObject5 = localObject6;
      if (localObject4 == Type.objectType) {}
    }
    for (;;)
    {
      try
      {
        localObject5 = ((Type)localObject4).coerceFromObject(localObject6);
        if (j >= m) {
          break label357;
        }
        arrayOfObject[(j - i)] = localObject5;
        j += 1;
      }
      catch (ClassCastException paramArrayOfObject)
      {
        label267:
        label307:
        label341:
        return 0xFFF40000 | j + 1;
      }
      i = 0;
      break;
      localObject1 = ((ArrayType)localObject1).getComponentType();
      localObject3 = (Object[])Array.newInstance(((Type)localObject1).getReflectClass(), n - m);
      arrayOfObject[(j - 1)] = localObject3;
      break label166;
      if (i != 0) {
        try
        {
          localObject2 = this.method.getDeclaringClass().coerceFromObject(paramArrayOfObject[0]);
        }
        catch (ClassCastException paramArrayOfObject)
        {
          return -786431;
        }
      }
      localObject2 = null;
      break label178;
      localObject4 = localObject1;
      break label214;
      label357:
      if (localObject3 != null) {
        localObject3[(j - m)] = localObject5;
      }
    }
    label375:
    paramCallContext.value1 = localObject2;
    paramCallContext.values = arrayOfObject;
    paramCallContext.proc = this;
    return 0;
  }
  
  public int numArgs()
  {
    int j = this.argTypes.length;
    int i = j;
    if (takesTarget()) {
      i = j + 1;
    }
    j = i;
    if (takesContext()) {
      j = i - 1;
    }
    if (takesVarArgs()) {
      return j - 1 - 4096;
    }
    return (j << 12) + j;
  }
  
  public final int opcode()
  {
    return this.op_code;
  }
  
  public void print(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.print("#<primitive procedure ");
    paramPrintWriter.print(toString());
    paramPrintWriter.print('>');
  }
  
  public void setReturnType(Type paramType)
  {
    this.retType = paramType;
  }
  
  public void setSideEffectFree()
  {
    this.sideEffectFree = true;
  }
  
  public boolean takesContext()
  {
    return (this.method != null) && (takesContext(this.method));
  }
  
  public boolean takesTarget()
  {
    return this.mode != 0;
  }
  
  public boolean takesVarArgs()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.method != null)
    {
      if ((this.method.getModifiers() & 0x80) == 0) {
        break label29;
      }
      bool1 = true;
    }
    label29:
    String str;
    do
    {
      return bool1;
      str = this.method.getName();
      if (str.endsWith("$V")) {
        break;
      }
      bool1 = bool2;
    } while (!str.endsWith("$V$X"));
    return true;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(100);
    if (this.retType == null) {}
    for (String str = "<unknown>";; str = this.retType.getName())
    {
      localStringBuffer.append(str);
      localStringBuffer.append(' ');
      localStringBuffer.append(getVerboseName());
      return localStringBuffer.toString();
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\PrimProcedure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */