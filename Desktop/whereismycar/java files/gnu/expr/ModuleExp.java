package gnu.expr;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ClassType;
import gnu.kawa.reflect.ClassMemberLocation;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.Externalizable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.URL;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ModuleExp
  extends LambdaExp
  implements Externalizable
{
  public static final int EXPORT_SPECIFIED = 16384;
  public static final int IMMEDIATE = 1048576;
  public static final int LAZY_DECLARATIONS = 524288;
  public static final int NONSTATIC_SPECIFIED = 65536;
  public static final int STATIC_RUN_SPECIFIED = 262144;
  public static final int STATIC_SPECIFIED = 32768;
  public static final int SUPERTYPE_SPECIFIED = 131072;
  public static boolean alwaysCompile = compilerAvailable;
  public static boolean compilerAvailable = true;
  public static String dumpZipPrefix;
  public static int interactiveCounter;
  static int lastZipCounter;
  public static boolean neverCompile = false;
  ModuleInfo info;
  ClassType[] interfaces;
  ClassType superType;
  
  public static final boolean evalModule(Environment paramEnvironment, CallContext paramCallContext, Compilation paramCompilation, URL paramURL, OutPort paramOutPort)
    throws Throwable
  {
    ModuleExp localModuleExp = paramCompilation.getModule();
    Language localLanguage = paramCompilation.getLanguage();
    paramCompilation = evalModule1(paramEnvironment, paramCompilation, paramURL, paramOutPort);
    if (paramCompilation == null) {
      return false;
    }
    evalModule2(paramEnvironment, paramCallContext, localLanguage, localModuleExp, paramCompilation);
    return true;
  }
  
  public static final Object evalModule1(Environment paramEnvironment, Compilation paramCompilation, URL paramURL, OutPort paramOutPort)
    throws SyntaxException
  {
    ModuleExp localModuleExp = paramCompilation.getModule();
    localModuleExp.info = paramCompilation.minfo;
    Environment localEnvironment = Environment.setSaveCurrent(paramEnvironment);
    Compilation localCompilation = Compilation.setSaveCurrent(paramCompilation);
    SourceMessages localSourceMessages = paramCompilation.getMessages();
    ClassLoader localClassLoader = null;
    Object localObject2 = null;
    Object localObject3 = null;
    if ((alwaysCompile) && (neverCompile)) {
      throw new RuntimeException("alwaysCompile and neverCompile are both true!");
    }
    if (neverCompile) {
      paramCompilation.mustCompile = false;
    }
    paramEnvironment = localClassLoader;
    Object localObject1 = localObject3;
    try
    {
      paramCompilation.process(6);
      paramEnvironment = localClassLoader;
      localObject1 = localObject3;
      paramCompilation.minfo.loadByStages(8);
      boolean bool;
      if (paramOutPort != null)
      {
        paramEnvironment = localClassLoader;
        localObject1 = localObject3;
        bool = localSourceMessages.checkErrors(paramOutPort, 20);
        if (!bool) {
          break label171;
        }
        Environment.restoreCurrent(localEnvironment);
        Compilation.restoreCurrent(localCompilation);
        if (0 != 0) {
          throw new NullPointerException();
        }
        paramEnvironment = null;
      }
      label171:
      label313:
      Class localClass;
      label482:
      label499:
      do
      {
        do
        {
          do
          {
            return paramEnvironment;
            paramEnvironment = localClassLoader;
            localObject1 = localObject3;
            if (localSourceMessages.seenErrors()) {
              break;
            }
            paramEnvironment = localClassLoader;
            localObject1 = localObject3;
            if (paramCompilation.mustCompile) {
              break label313;
            }
            paramEnvironment = localClassLoader;
            localObject1 = localObject3;
            if ((Compilation.debugPrintFinalExpr) && (paramOutPort != null))
            {
              paramEnvironment = localClassLoader;
              localObject1 = localObject3;
              paramOutPort.println("[Evaluating final module \"" + localModuleExp.getName() + "\":");
              paramEnvironment = localClassLoader;
              localObject1 = localObject3;
              localModuleExp.print(paramOutPort);
              paramEnvironment = localClassLoader;
              localObject1 = localObject3;
              paramOutPort.println(']');
              paramEnvironment = localClassLoader;
              localObject1 = localObject3;
              paramOutPort.flush();
            }
            paramEnvironment = localClassLoader;
            localObject1 = localObject3;
            paramCompilation = Boolean.TRUE;
            Environment.restoreCurrent(localEnvironment);
            Compilation.restoreCurrent(localCompilation);
            paramEnvironment = paramCompilation;
          } while (0 == 0);
          throw new NullPointerException();
          paramEnvironment = localClassLoader;
          localObject1 = localObject3;
          localClass = evalToClass(paramCompilation, paramURL);
          if (localClass == null)
          {
            Environment.restoreCurrent(localEnvironment);
            Compilation.restoreCurrent(localCompilation);
            if (0 != 0) {
              throw new NullPointerException();
            }
            return null;
          }
          paramCompilation = (Compilation)localObject2;
          paramEnvironment = localClassLoader;
          localObject1 = localObject3;
          try
          {
            paramURL = Thread.currentThread();
            paramCompilation = (Compilation)localObject2;
            paramEnvironment = localClassLoader;
            localObject1 = paramURL;
            localClassLoader = paramURL.getContextClassLoader();
            paramCompilation = localClassLoader;
            paramEnvironment = localClassLoader;
            localObject1 = paramURL;
            paramURL.setContextClassLoader(localClass.getClassLoader());
            paramCompilation = localClassLoader;
          }
          catch (Throwable paramEnvironment)
          {
            do
            {
              for (;;)
              {
                paramURL = null;
              }
              paramEnvironment = paramCompilation;
              localObject1 = paramURL;
              bool = localSourceMessages.seenErrors();
            } while (bool);
            Environment.restoreCurrent(localEnvironment);
            Compilation.restoreCurrent(localCompilation);
            paramEnvironment = localClass;
          }
          paramEnvironment = paramCompilation;
          localObject1 = paramURL;
          localModuleExp.body = null;
          paramEnvironment = paramCompilation;
          localObject1 = paramURL;
          localModuleExp.thisVariable = null;
          if (paramOutPort == null) {
            break label482;
          }
          paramEnvironment = paramCompilation;
          localObject1 = paramURL;
          if (!localSourceMessages.checkErrors(paramOutPort, 20)) {
            break label499;
          }
          paramOutPort = Boolean.valueOf(false);
          Environment.restoreCurrent(localEnvironment);
          Compilation.restoreCurrent(localCompilation);
          paramEnvironment = paramOutPort;
        } while (paramURL == null);
        paramURL.setContextClassLoader(paramCompilation);
        return paramOutPort;
      } while (paramURL == null);
      paramURL.setContextClassLoader(paramCompilation);
      return localClass;
    }
    finally
    {
      Environment.restoreCurrent(localEnvironment);
      Compilation.restoreCurrent(localCompilation);
      if (localObject1 != null) {
        ((Thread)localObject1).setContextClassLoader(paramEnvironment);
      }
    }
  }
  
  public static final void evalModule2(Environment paramEnvironment, CallContext paramCallContext, Language paramLanguage, ModuleExp paramModuleExp, Object paramObject)
    throws Throwable
  {
    Environment localEnvironment = Environment.setSaveCurrent(paramEnvironment);
    Object localObject1;
    label146:
    for (;;)
    {
      try
      {
        if (paramObject == Boolean.TRUE)
        {
          paramModuleExp.body.apply(paramCallContext);
          paramCallContext.runUntilDone();
          return;
        }
        localObject1 = paramObject;
        if ((paramObject instanceof Class)) {
          localObject1 = ModuleContext.getContext().findInstance((Class)paramObject);
        }
        if ((localObject1 instanceof Runnable))
        {
          if (!(localObject1 instanceof ModuleBody)) {
            break label146;
          }
          paramObject = (ModuleBody)localObject1;
          if (!((ModuleBody)paramObject).runDone)
          {
            ((ModuleBody)paramObject).runDone = true;
            ((ModuleBody)paramObject).run(paramCallContext);
          }
        }
        if (paramModuleExp != null) {
          break;
        }
        ClassMemberLocation.defineAll(localObject1, paramLanguage, paramEnvironment);
        continue;
        ((Runnable)localObject1).run();
      }
      finally
      {
        Environment.restoreCurrent(localEnvironment);
        if (0 != 0) {
          throw new NullPointerException();
        }
      }
    }
    paramModuleExp = paramModuleExp.firstDecl();
    label164:
    if (paramModuleExp != null)
    {
      paramObject = paramModuleExp.getSymbol();
      if ((!paramModuleExp.isPrivate()) && (paramObject != null)) {
        break label194;
      }
    }
    for (;;)
    {
      paramModuleExp = paramModuleExp.nextDecl();
      break label164;
      break;
      label194:
      localObject1 = paramModuleExp.field;
      label215:
      Object localObject3;
      Expression localExpression;
      if ((paramObject instanceof Symbol))
      {
        paramObject = (Symbol)paramObject;
        localObject3 = paramLanguage.getEnvPropertyFor(paramModuleExp);
        localExpression = paramModuleExp.getValue();
        if ((paramModuleExp.field.getModifiers() & 0x10) == 0) {
          break label390;
        }
        if ((!(localExpression instanceof QuoteExp)) || (localExpression == QuoteExp.undefined_exp)) {
          break label310;
        }
        localObject1 = ((QuoteExp)localExpression).getValue();
      }
      for (;;)
      {
        if (!paramModuleExp.isIndirectBinding()) {
          break label377;
        }
        paramEnvironment.addLocation((Symbol)paramObject, localObject3, (Location)localObject1);
        break;
        paramObject = Symbol.make("", paramObject.toString().intern());
        break label215;
        label310:
        Object localObject2 = paramModuleExp.field.getReflectField().get(null);
        if (!paramModuleExp.isIndirectBinding())
        {
          paramModuleExp.setValue(QuoteExp.getInstance(localObject2));
          localObject1 = localObject2;
        }
        else if (paramModuleExp.isAlias())
        {
          localObject1 = localObject2;
          if ((localExpression instanceof ReferenceExp)) {}
        }
        else
        {
          paramModuleExp.setValue(null);
          localObject1 = localObject2;
        }
      }
      label377:
      paramEnvironment.define((Symbol)paramObject, localObject3, localObject1);
      continue;
      label390:
      localObject1 = new StaticFieldLocation(((gnu.bytecode.Field)localObject1).getDeclaringClass(), ((gnu.bytecode.Field)localObject1).getName());
      ((StaticFieldLocation)localObject1).setDeclaration(paramModuleExp);
      paramEnvironment.addLocation((Symbol)paramObject, localObject3, (Location)localObject1);
      paramModuleExp.setValue(null);
    }
  }
  
  public static Class evalToClass(Compilation paramCompilation, URL paramURL)
    throws SyntaxException
  {
    paramCompilation.getModule();
    SourceMessages localSourceMessages = paramCompilation.getMessages();
    Object localObject3;
    Object localObject1;
    int i;
    Object localObject2;
    try
    {
      paramCompilation.minfo.loadByStages(12);
      if (localSourceMessages.seenErrors()) {
        return null;
      }
      localObject3 = paramCompilation.loader;
      localObject1 = paramURL;
      if (paramURL == null) {
        localObject1 = Path.currentPath().toURL();
      }
      ((ArrayClassLoader)localObject3).setResourceContext((URL)localObject1);
      paramURL = null;
      if (dumpZipPrefix == null) {
        break label509;
      }
      paramURL = new StringBuffer(dumpZipPrefix);
      lastZipCounter += 1;
      if (interactiveCounter > lastZipCounter) {
        lastZipCounter = interactiveCounter;
      }
      paramURL.append(lastZipCounter);
      paramURL.append(".zip");
      paramURL = new ZipOutputStream(new FileOutputStream(paramURL.toString()));
    }
    catch (IOException paramCompilation)
    {
      Object localObject4;
      throw new WrappedException("I/O error in lambda eval", paramCompilation);
      localObject3 = paramCompilation.minfo;
      ((ModuleInfo)localObject3).setModuleClass(paramURL);
      paramCompilation.cleanupAfterCompilation();
      j = ((ModuleInfo)localObject3).numDependencies;
      i = 0;
      while (i < j)
      {
        localObject4 = localObject3.dependencies[i];
        localObject2 = ((ModuleInfo)localObject4).getModuleClassRaw();
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = evalToClass(((ModuleInfo)localObject4).comp, null);
        }
        paramCompilation.loader.addClass((Class)localObject1);
        i += 1;
      }
    }
    catch (ClassNotFoundException paramCompilation)
    {
      throw new WrappedException("class not found in lambda eval", paramCompilation);
    }
    catch (Throwable paramURL)
    {
      label268:
      paramCompilation.getMessages().error('f', "internal compile error - caught " + paramURL, paramURL);
      throw new SyntaxException(localSourceMessages);
    }
    if (i < paramCompilation.numClasses)
    {
      localObject1 = paramCompilation.classes[i];
      localObject2 = ((ClassType)localObject1).getName();
      localObject1 = ((ClassType)localObject1).writeToArray();
      ((ArrayClassLoader)localObject3).addClass((String)localObject2, (byte[])localObject1);
      if (paramURL != null)
      {
        localObject2 = new ZipEntry(((String)localObject2).replace('.', '/') + ".class");
        ((ZipEntry)localObject2).setSize(localObject1.length);
        localObject4 = new CRC32();
        ((CRC32)localObject4).update((byte[])localObject1);
        ((ZipEntry)localObject2).setCrc(((CRC32)localObject4).getValue());
        ((ZipEntry)localObject2).setMethod(0);
        paramURL.putNextEntry((ZipEntry)localObject2);
        paramURL.write((byte[])localObject1);
      }
    }
    else
    {
      if (paramURL == null) {
        break label524;
      }
      paramURL.close();
      break label524;
      while ((((ArrayClassLoader)localObject1).getParent() instanceof ArrayClassLoader)) {
        localObject1 = (ArrayClassLoader)((ArrayClassLoader)localObject1).getParent();
      }
    }
    for (;;)
    {
      if (i < paramCompilation.numClasses)
      {
        localObject2 = paramCompilation.classes[i];
        localObject4 = ((ArrayClassLoader)localObject3).loadClass(((ClassType)localObject2).getName());
        ((ClassType)localObject2).setReflectClass((Class)localObject4);
        ((ClassType)localObject2).setExisting(true);
        if (i == 0)
        {
          localObject2 = localObject4;
        }
        else
        {
          localObject2 = paramURL;
          if (localObject1 != localObject3)
          {
            ((ArrayClassLoader)localObject1).addClass((Class)localObject4);
            localObject2 = paramURL;
          }
        }
      }
      else
      {
        int j;
        return paramURL;
        label509:
        i = 0;
        break;
        i += 1;
        break;
        label524:
        paramURL = null;
        localObject1 = localObject3;
        break label268;
        i = 0;
        continue;
      }
      i += 1;
      paramURL = (URL)localObject2;
    }
  }
  
  public static void mustAlwaysCompile()
  {
    alwaysCompile = true;
    neverCompile = false;
  }
  
  public static void mustNeverCompile()
  {
    alwaysCompile = false;
    neverCompile = true;
    compilerAvailable = false;
  }
  
  public void allocChildClasses(Compilation paramCompilation)
  {
    declareClosureEnv();
    if (!paramCompilation.usingCPStyle()) {
      return;
    }
    allocFrame(paramCompilation);
  }
  
  void allocFields(Compilation paramCompilation)
  {
    Declaration localDeclaration = firstDecl();
    if (localDeclaration != null)
    {
      if (((localDeclaration.isSimple()) && (!localDeclaration.isPublic())) || (localDeclaration.field != null)) {}
      for (;;)
      {
        localDeclaration = localDeclaration.nextDecl();
        break;
        if ((localDeclaration.getFlag(65536L)) && (localDeclaration.getFlag(6L))) {
          localDeclaration.makeField(paramCompilation, null);
        }
      }
    }
    localDeclaration = firstDecl();
    if (localDeclaration != null)
    {
      if (localDeclaration.field != null) {}
      Expression localExpression2;
      for (;;)
      {
        localDeclaration = localDeclaration.nextDecl();
        break;
        localExpression2 = localDeclaration.getValue();
        if (((!localDeclaration.isSimple()) || (localDeclaration.isPublic()) || (localDeclaration.isNamespaceDecl()) || ((localDeclaration.getFlag(16384L)) && (localDeclaration.getFlag(6L)))) && (!localDeclaration.getFlag(65536L)))
        {
          if ((!(localExpression2 instanceof LambdaExp)) || ((localExpression2 instanceof ModuleExp)) || ((localExpression2 instanceof ClassExp))) {
            break label185;
          }
          ((LambdaExp)localExpression2).allocFieldFor(paramCompilation);
        }
      }
      label185:
      Expression localExpression1 = localExpression2;
      if (!localDeclaration.shouldEarlyInit()) {
        if (!localDeclaration.isAlias()) {
          break label214;
        }
      }
      label214:
      for (localExpression1 = localExpression2;; localExpression1 = null)
      {
        localDeclaration.makeField(paramCompilation, localExpression1);
        break;
      }
    }
  }
  
  public ClassType classFor(Compilation paramCompilation)
  {
    Object localObject2;
    if ((this.type != null) && (this.type != Compilation.typeProcedure)) {
      localObject2 = this.type;
    }
    Object localObject1;
    Object localObject3;
    label44:
    label334:
    label366:
    label392:
    do
    {
      return (ClassType)localObject2;
      localObject2 = getFileName();
      localObject1 = getName();
      localObject3 = null;
      if (localObject1 != null)
      {
        localObject2 = localObject3;
        if (getName() == null) {
          setName((String)localObject1);
        }
        localObject3 = Compilation.mangleNameIfNeeded((String)localObject1);
        if ((paramCompilation.classPrefix.length() != 0) || (localObject2 == null) || (((Path)localObject2).isAbsolute())) {
          break label366;
        }
        localObject1 = ((Path)localObject2).getParent();
        if (localObject1 == null) {
          break label366;
        }
        localObject1 = localObject1.toString();
        if ((((String)localObject1).length() <= 0) || (((String)localObject1).indexOf("..") >= 0)) {
          break label366;
        }
        localObject2 = ((String)localObject1).replaceAll(System.getProperty("file.separator"), "/");
        localObject1 = localObject2;
        if (((String)localObject2).startsWith("./")) {
          localObject1 = ((String)localObject2).substring(2);
        }
        if (!((String)localObject1).equals(".")) {
          break label334;
        }
        localObject1 = localObject3;
      }
      for (;;)
      {
        localObject3 = new ClassType((String)localObject1);
        setType((ClassType)localObject3);
        localObject2 = localObject3;
        if (paramCompilation.mainLambda != this) {
          break;
        }
        if (paramCompilation.mainClass != null) {
          break label392;
        }
        paramCompilation.mainClass = ((ClassType)localObject3);
        return (ClassType)localObject3;
        if (localObject2 == null)
        {
          str = getName();
          localObject1 = str;
          localObject2 = localObject3;
          if (str != null) {
            break label44;
          }
          localObject1 = "$unnamed_input_file$";
          localObject2 = localObject3;
          break label44;
        }
        if ((this.filename.equals("-")) || (this.filename.equals("/dev/stdin")))
        {
          str = getName();
          localObject1 = str;
          localObject2 = localObject3;
          if (str != null) {
            break label44;
          }
          localObject1 = "$stdin$";
          localObject2 = localObject3;
          break label44;
        }
        localObject3 = Path.valueOf(localObject2);
        String str = ((Path)localObject3).getLast();
        int i = str.lastIndexOf('.');
        localObject1 = str;
        localObject2 = localObject3;
        if (i <= 0) {
          break label44;
        }
        localObject1 = str.substring(0, i);
        localObject2 = localObject3;
        break label44;
        localObject1 = Compilation.mangleURI((String)localObject1) + "." + (String)localObject3;
        continue;
        localObject1 = paramCompilation.classPrefix + (String)localObject3;
      }
      localObject2 = localObject3;
    } while (((String)localObject1).equals(paramCompilation.mainClass.getName()));
    paramCompilation.error('e', "inconsistent main class name: " + (String)localObject1 + " - old name: " + paramCompilation.mainClass.getName());
    return (ClassType)localObject3;
  }
  
  public Declaration firstDecl()
  {
    try
    {
      if (getFlag(524288)) {
        this.info.setupModuleExp();
      }
      return this.decls;
    }
    finally {}
  }
  
  public final ClassType[] getInterfaces()
  {
    return this.interfaces;
  }
  
  public String getNamespaceUri()
  {
    return this.info.uri;
  }
  
  public final ClassType getSuperType()
  {
    return this.superType;
  }
  
  public final boolean isStatic()
  {
    return (getFlag(32768)) || (((Compilation.moduleStatic >= 0) || (getFlag(1048576))) && (!getFlag(131072)) && (!getFlag(65536)));
  }
  
  public void print(OutPort paramOutPort)
  {
    paramOutPort.startLogicalBlock("(Module/", ")", 2);
    Object localObject = getSymbol();
    if (localObject != null)
    {
      paramOutPort.print(localObject);
      paramOutPort.print('/');
    }
    paramOutPort.print(this.id);
    paramOutPort.print('/');
    paramOutPort.writeSpaceFill();
    paramOutPort.startLogicalBlock("(", false, ")");
    localObject = firstDecl();
    if (localObject != null)
    {
      paramOutPort.print("Declarations:");
      while (localObject != null)
      {
        paramOutPort.writeSpaceFill();
        ((Declaration)localObject).printInfo(paramOutPort);
        localObject = ((Declaration)localObject).nextDecl();
      }
    }
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
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    paramObjectInput = paramObjectInput.readObject();
    if ((paramObjectInput instanceof ClassType))
    {
      this.type = ((ClassType)paramObjectInput);
      setName(this.type.getName());
    }
    for (;;)
    {
      this.flags |= 0x80000;
      return;
      setName((String)paramObjectInput);
    }
  }
  
  public final void setInterfaces(ClassType[] paramArrayOfClassType)
  {
    this.interfaces = paramArrayOfClassType;
  }
  
  public final void setSuperType(ClassType paramClassType)
  {
    this.superType = paramClassType;
  }
  
  public boolean staticInitRun()
  {
    return (isStatic()) && ((getFlag(262144)) || (Compilation.moduleStatic == 2));
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD)
  {
    return (R)paramExpVisitor.visitModuleExp(this, paramD);
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    String str1 = null;
    if ((this.type != null) && (this.type != Compilation.typeProcedure) && (!this.type.isExisting()))
    {
      paramObjectOutput.writeObject(this.type);
      return;
    }
    if (0 == 0) {
      str1 = getName();
    }
    String str2 = str1;
    if (str1 == null) {
      str2 = getFileName();
    }
    paramObjectOutput.writeObject(str2);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\expr\ModuleExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */