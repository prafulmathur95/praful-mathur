package kawa;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ZipLoader;
import gnu.expr.Compilation;
import gnu.expr.CompiledModule;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleManager;
import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import gnu.text.FilePath;
import gnu.text.Lexer;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.URL;

public class Shell
{
  private static Class[] boolClasses;
  public static ThreadLocal currentLoadPath = new ThreadLocal();
  public static Object[] defaultFormatInfo;
  public static Method defaultFormatMethod;
  public static String defaultFormatName;
  static Object[][] formats;
  private static Class[] httpPrinterClasses;
  private static Class[] noClasses = new Class[0];
  private static Object portArg;
  private static Class[] xmlPrinterClasses;
  
  static
  {
    boolClasses = new Class[] { Boolean.TYPE };
    xmlPrinterClasses = new Class[] { OutPort.class, Object.class };
    httpPrinterClasses = new Class[] { OutPort.class };
    portArg = "(port)";
    Object[] arrayOfObject1 = { "scheme", "gnu.kawa.functions.DisplayFormat", "getSchemeFormat", boolClasses, Boolean.FALSE };
    Class[] arrayOfClass1 = boolClasses;
    Boolean localBoolean1 = Boolean.TRUE;
    Object[] arrayOfObject2 = { "elisp", "gnu.kawa.functions.DisplayFormat", "getEmacsLispFormat", boolClasses, Boolean.FALSE };
    Class[] arrayOfClass2 = boolClasses;
    Boolean localBoolean2 = Boolean.TRUE;
    Object[] arrayOfObject3 = { "clisp", "gnu.kawa.functions.DisplayFormat", "getCommonLispFormat", boolClasses, Boolean.FALSE };
    Class[] arrayOfClass3 = boolClasses;
    Boolean localBoolean3 = Boolean.TRUE;
    Object[] arrayOfObject4 = { "commonlisp", "gnu.kawa.functions.DisplayFormat", "getCommonLispFormat", boolClasses, Boolean.FALSE };
    Object[] arrayOfObject5 = { "readable-commonlisp", "gnu.kawa.functions.DisplayFormat", "getCommonLispFormat", boolClasses, Boolean.TRUE };
    Object[] arrayOfObject6 = { "xml", "gnu.xml.XMLPrinter", "make", xmlPrinterClasses, portArg, null };
    Object[] arrayOfObject7 = { "html", "gnu.xml.XMLPrinter", "make", xmlPrinterClasses, portArg, "html" };
    Class[] arrayOfClass4 = xmlPrinterClasses;
    Object localObject1 = portArg;
    Class[] arrayOfClass5 = httpPrinterClasses;
    Object localObject2 = portArg;
    Class[] arrayOfClass6 = noClasses;
    Object[] arrayOfObject8 = { null };
    formats = new Object[][] { arrayOfObject1, { "readable-scheme", "gnu.kawa.functions.DisplayFormat", "getSchemeFormat", arrayOfClass1, localBoolean1 }, arrayOfObject2, { "readable-elisp", "gnu.kawa.functions.DisplayFormat", "getEmacsLispFormat", arrayOfClass2, localBoolean2 }, arrayOfObject3, { "readable-clisp", "gnu.kawa.functions.DisplayFormat", "getCommonLispFormat", arrayOfClass3, localBoolean3 }, arrayOfObject4, arrayOfObject5, arrayOfObject6, arrayOfObject7, { "xhtml", "gnu.xml.XMLPrinter", "make", arrayOfClass4, localObject1, "xhtml" }, { "cgi", "gnu.kawa.xml.HttpPrinter", "make", arrayOfClass5, localObject2 }, { "ignore", "gnu.lists.VoidConsumer", "getInstance", arrayOfClass6 }, arrayOfObject8 };
  }
  
  public static final CompiledModule checkCompiledZip(InputStream paramInputStream, Path paramPath, Environment paramEnvironment, Language paramLanguage)
    throws IOException
  {
    Environment localEnvironment = null;
    do
    {
      try
      {
        paramInputStream.mark(5);
        if ((paramInputStream.read() == 80) && (paramInputStream.read() == 75) && (paramInputStream.read() == 3) && (paramInputStream.read() == 4)) {}
        for (int i = 1;; i = 0)
        {
          paramInputStream.reset();
          if (i != 0) {
            break;
          }
          paramInputStream = localEnvironment;
          return paramInputStream;
        }
        paramInputStream.close();
      }
      catch (IOException paramInputStream)
      {
        return null;
      }
      localEnvironment = Environment.getCurrent();
      paramInputStream = paramPath.toString();
      if (paramEnvironment != localEnvironment) {}
      try
      {
        Environment.setCurrent(paramEnvironment);
        if (!(paramPath instanceof FilePath)) {
          throw new RuntimeException("load: " + paramInputStream + " - not a file path");
        }
      }
      catch (IOException paramPath)
      {
        throw new WrappedException("load: " + paramInputStream + " - " + paramPath.toString(), paramPath);
      }
      finally
      {
        if (paramEnvironment != localEnvironment) {
          Environment.setCurrent(localEnvironment);
        }
      }
      paramPath = ((FilePath)paramPath).toFile();
      if (!paramPath.exists()) {
        throw new RuntimeException("load: " + paramInputStream + " - not found");
      }
      if (!paramPath.canRead()) {
        throw new RuntimeException("load: " + paramInputStream + " - not readable");
      }
      paramPath = CompiledModule.make(new ZipLoader(paramInputStream).loadAllClasses(), paramLanguage);
      paramInputStream = paramPath;
    } while (paramEnvironment == localEnvironment);
    Environment.setCurrent(localEnvironment);
    return paramPath;
  }
  
  static CompiledModule compileSource(InPort paramInPort, Environment paramEnvironment, URL paramURL, Language paramLanguage, SourceMessages paramSourceMessages)
    throws SyntaxException, IOException
  {
    paramInPort = paramLanguage.parse(paramInPort, paramSourceMessages, 1, ModuleManager.getInstance().findWithSourcePath(paramInPort.getName()));
    CallContext.getInstance().values = Values.noArgs;
    paramEnvironment = ModuleExp.evalModule1(paramEnvironment, paramInPort, paramURL, null);
    if ((paramEnvironment == null) || (paramSourceMessages.seenErrors())) {
      return null;
    }
    return new CompiledModule(paramInPort.getModule(), paramEnvironment, paramLanguage);
  }
  
  public static Consumer getOutputConsumer(OutPort paramOutPort)
  {
    Object localObject = defaultFormatInfo;
    if (paramOutPort == null) {
      return VoidConsumer.getInstance();
    }
    if (localObject == null) {
      return Language.getDefaultLanguage().getOutputConsumer(paramOutPort);
    }
    try
    {
      Object[] arrayOfObject = new Object[localObject.length - 4];
      System.arraycopy(localObject, 4, arrayOfObject, 0, arrayOfObject.length);
      int i = arrayOfObject.length;
      for (;;)
      {
        int j = i - 1;
        if (j < 0) {
          break;
        }
        i = j;
        if (arrayOfObject[j] == portArg)
        {
          arrayOfObject[j] = paramOutPort;
          i = j;
        }
      }
      localObject = defaultFormatMethod.invoke(null, arrayOfObject);
    }
    catch (Throwable paramOutPort)
    {
      throw new RuntimeException("cannot get output-format '" + defaultFormatName + "' - caught " + paramOutPort);
    }
    if ((localObject instanceof AbstractFormat))
    {
      paramOutPort.objectFormat = ((AbstractFormat)localObject);
      return paramOutPort;
    }
    paramOutPort = (Consumer)localObject;
    return paramOutPort;
  }
  
  public static void printError(Throwable paramThrowable, SourceMessages paramSourceMessages, OutPort paramOutPort)
  {
    if ((paramThrowable instanceof WrongArguments))
    {
      paramThrowable = (WrongArguments)paramThrowable;
      paramSourceMessages.printAll(paramOutPort, 20);
      if (paramThrowable.usage != null) {
        paramOutPort.println("usage: " + paramThrowable.usage);
      }
      paramThrowable.printStackTrace(paramOutPort);
      return;
    }
    if ((paramThrowable instanceof ClassCastException))
    {
      paramSourceMessages.printAll(paramOutPort, 20);
      paramOutPort.println("Invalid parameter, was: " + paramThrowable.getMessage());
      paramThrowable.printStackTrace(paramOutPort);
      return;
    }
    if ((paramThrowable instanceof SyntaxException))
    {
      SyntaxException localSyntaxException = (SyntaxException)paramThrowable;
      if (localSyntaxException.getMessages() == paramSourceMessages)
      {
        localSyntaxException.printAll(paramOutPort, 20);
        localSyntaxException.clear();
        return;
      }
    }
    paramSourceMessages.printAll(paramOutPort, 20);
    paramThrowable.printStackTrace(paramOutPort);
  }
  
  public static Throwable run(Language paramLanguage, Environment paramEnvironment, InPort paramInPort, Consumer paramConsumer, OutPort paramOutPort, URL paramURL, SourceMessages paramSourceMessages)
  {
    Language localLanguage = Language.setSaveCurrent(paramLanguage);
    Lexer localLexer = paramLanguage.getLexer(paramInPort, paramSourceMessages);
    boolean bool1;
    if (paramOutPort != null) {
      bool1 = true;
    }
    for (;;)
    {
      localLexer.setInteractive(bool1);
      CallContext localCallContext = CallContext.getInstance();
      Consumer localConsumer = null;
      if (paramConsumer != null)
      {
        localConsumer = localCallContext.consumer;
        localCallContext.consumer = paramConsumer;
      }
      try
      {
        Object localObject1 = Thread.currentThread();
        Object localObject2 = ((Thread)localObject1).getContextClassLoader();
        if (!(localObject2 instanceof ArrayClassLoader)) {
          ((Thread)localObject1).setContextClassLoader(new ArrayClassLoader((ClassLoader)localObject2));
        }
        for (;;)
        {
          try
          {
            localObject1 = paramLanguage.parse(localLexer, 7, null);
            if (bool1)
            {
              bool2 = paramSourceMessages.checkErrors(paramOutPort, 20);
              if (localObject1 != null) {
                continue;
              }
              return null;
              bool1 = false;
              break;
            }
            if (paramSourceMessages.seenErrors()) {
              throw new SyntaxException(paramSourceMessages);
            }
          }
          catch (Throwable localThrowable)
          {
            boolean bool2;
            if (!bool1)
            {
              return localThrowable;
              bool2 = false;
              continue;
              if (bool2) {
                continue;
              }
              localObject2 = localThrowable.getModule();
              StringBuilder localStringBuilder = new StringBuilder().append("atInteractiveLevel$");
              i = ModuleExp.interactiveCounter + 1;
              ModuleExp.interactiveCounter = i;
              ((ModuleExp)localObject2).setName(i);
              i = paramInPort.read();
              if ((i < 0) || (i == 13) || (i == 10))
              {
                if (!ModuleExp.evalModule(paramEnvironment, localCallContext, localThrowable, paramURL, paramOutPort)) {
                  continue;
                }
                if (!(paramConsumer instanceof Writer)) {
                  break label363;
                }
                ((Writer)paramConsumer).flush();
                break label363;
              }
              if ((i == 32) || (i == 9)) {
                continue;
              }
              paramInPort.unread();
              continue;
            }
          }
          finally
          {
            if (paramConsumer != null) {
              localCallContext.consumer = localConsumer;
            }
            Language.restoreCurrent(localLanguage);
          }
          printError(localThrowable, paramSourceMessages, paramOutPort);
        }
      }
      catch (SecurityException localSecurityException)
      {
        for (;;)
        {
          int i;
          continue;
          label363:
          if (i >= 0) {}
        }
      }
    }
  }
  
  public static Throwable run(Language paramLanguage, Environment paramEnvironment, InPort paramInPort, OutPort paramOutPort1, OutPort paramOutPort2, SourceMessages paramSourceMessages)
  {
    AbstractFormat localAbstractFormat = null;
    if (paramOutPort1 != null) {
      localAbstractFormat = paramOutPort1.objectFormat;
    }
    Consumer localConsumer = getOutputConsumer(paramOutPort1);
    try
    {
      paramLanguage = run(paramLanguage, paramEnvironment, paramInPort, localConsumer, paramOutPort2, null, paramSourceMessages);
      return paramLanguage;
    }
    finally
    {
      if (paramOutPort1 != null) {
        paramOutPort1.objectFormat = localAbstractFormat;
      }
    }
  }
  
  public static boolean run(Language paramLanguage, Environment paramEnvironment)
  {
    InPort localInPort = InPort.inDefault();
    SourceMessages localSourceMessages = new SourceMessages();
    if ((localInPort instanceof TtyInPort))
    {
      localObject = paramLanguage.getPrompter();
      if (localObject != null) {
        ((TtyInPort)localInPort).setPrompter((Procedure)localObject);
      }
    }
    for (Object localObject = OutPort.errDefault();; localObject = null)
    {
      paramLanguage = run(paramLanguage, paramEnvironment, localInPort, OutPort.outDefault(), (OutPort)localObject, localSourceMessages);
      if (paramLanguage != null) {
        break;
      }
      return true;
    }
    printError(paramLanguage, localSourceMessages, OutPort.errDefault());
    return false;
  }
  
  public static boolean run(Language paramLanguage, Environment paramEnvironment, InPort paramInPort, Consumer paramConsumer, OutPort paramOutPort, URL paramURL)
  {
    SourceMessages localSourceMessages = new SourceMessages();
    paramLanguage = run(paramLanguage, paramEnvironment, paramInPort, paramConsumer, paramOutPort, paramURL, localSourceMessages);
    if (paramLanguage != null) {
      printError(paramLanguage, localSourceMessages, paramOutPort);
    }
    return paramLanguage == null;
  }
  
  public static final boolean runFile(InputStream paramInputStream, Path paramPath, Environment paramEnvironment, boolean paramBoolean, int paramInt)
    throws Throwable
  {
    Object localObject1 = paramInputStream;
    if (!(paramInputStream instanceof BufferedInputStream)) {
      localObject1 = new BufferedInputStream(paramInputStream);
    }
    Language localLanguage = Language.getDefaultLanguage();
    Path localPath = (Path)currentLoadPath.get();
    try
    {
      currentLoadPath.set(paramPath);
      paramInputStream = checkCompiledZip((InputStream)localObject1, paramPath, paramEnvironment, localLanguage);
      localObject2 = paramInputStream;
      if (paramInputStream != null) {
        break label222;
      }
      localObject1 = InPort.openFile((InputStream)localObject1, paramPath);
      for (;;)
      {
        paramInt -= 1;
        if (paramInt < 0) {
          break;
        }
        ((InPort)localObject1).skipRestOfLine();
      }
      try
      {
        localObject2 = new SourceMessages();
        localURL = paramPath.toURL();
        if (!paramBoolean) {
          break label170;
        }
        if (ModuleBody.getMainPrintValues())
        {
          paramPath = getOutputConsumer(OutPort.outDefault());
          paramPath = run(localLanguage, paramEnvironment, (InPort)localObject1, paramPath, null, localURL, (SourceMessages)localObject2);
          if (paramPath == null) {
            break label214;
          }
          throw paramPath;
        }
      }
      finally
      {
        ((InPort)localObject1).close();
      }
    }
    finally
    {
      currentLoadPath.set(localPath);
    }
    URL localURL;
    for (;;)
    {
      paramPath = new VoidConsumer();
    }
    label170:
    paramPath = compileSource((InPort)localObject1, paramEnvironment, localURL, localLanguage, (SourceMessages)localObject2);
    ((SourceMessages)localObject2).printAll(OutPort.errDefault(), 20);
    paramInputStream = paramPath;
    if (paramPath == null)
    {
      ((InPort)localObject1).close();
      currentLoadPath.set(localPath);
      return false;
    }
    label214:
    ((InPort)localObject1).close();
    Object localObject2 = paramInputStream;
    label222:
    if (localObject2 != null) {
      ((CompiledModule)localObject2).evalModule(paramEnvironment, OutPort.outDefault());
    }
    currentLoadPath.set(localPath);
    return true;
  }
  
  /* Error */
  public static boolean runFileOrClass(String paramString, boolean paramBoolean, int paramInt)
  {
    // Byte code:
    //   0: invokestatic 271	gnu/expr/Language:getDefaultLanguage	()Lgnu/expr/Language;
    //   3: astore 5
    //   5: aload_0
    //   6: ldc_w 499
    //   9: invokevirtual 505	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   12: ifeq +29 -> 41
    //   15: ldc_w 507
    //   18: invokestatic 511	gnu/text/Path:valueOf	(Ljava/lang/Object;)Lgnu/text/Path;
    //   21: astore 4
    //   23: getstatic 515	java/lang/System:in	Ljava/io/InputStream;
    //   26: astore_3
    //   27: aload_3
    //   28: aload 4
    //   30: invokestatic 134	gnu/mapping/Environment:getCurrent	()Lgnu/mapping/Environment;
    //   33: iload_1
    //   34: iload_2
    //   35: invokestatic 517	kawa/Shell:runFile	(Ljava/io/InputStream;Lgnu/text/Path;Lgnu/mapping/Environment;ZI)Z
    //   38: istore_1
    //   39: iload_1
    //   40: ireturn
    //   41: aload_0
    //   42: invokestatic 511	gnu/text/Path:valueOf	(Ljava/lang/Object;)Lgnu/text/Path;
    //   45: astore 4
    //   47: aload 4
    //   49: invokevirtual 521	gnu/text/Path:openInputStream	()Ljava/io/InputStream;
    //   52: astore_3
    //   53: goto -26 -> 27
    //   56: astore_3
    //   57: aload_3
    //   58: getstatic 525	java/lang/System:err	Ljava/io/PrintStream;
    //   61: invokevirtual 528	java/lang/Throwable:printStackTrace	(Ljava/io/PrintStream;)V
    //   64: iconst_0
    //   65: ireturn
    //   66: astore_3
    //   67: aload_0
    //   68: invokestatic 532	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   71: astore_0
    //   72: aload_0
    //   73: aload 5
    //   75: invokestatic 198	gnu/expr/CompiledModule:make	(Ljava/lang/Class;Lgnu/expr/Language;)Lgnu/expr/CompiledModule;
    //   78: invokestatic 134	gnu/mapping/Environment:getCurrent	()Lgnu/mapping/Environment;
    //   81: invokestatic 450	gnu/mapping/OutPort:outDefault	()Lgnu/mapping/OutPort;
    //   84: invokevirtual 495	gnu/expr/CompiledModule:evalModule	(Lgnu/mapping/Environment;Lgnu/mapping/OutPort;)V
    //   87: iconst_1
    //   88: ireturn
    //   89: astore_0
    //   90: getstatic 525	java/lang/System:err	Ljava/io/PrintStream;
    //   93: new 148	java/lang/StringBuilder
    //   96: dup
    //   97: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   100: ldc_w 534
    //   103: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: aload_3
    //   107: invokevirtual 332	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   110: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: invokevirtual 537	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   119: iconst_0
    //   120: ireturn
    //   121: astore_0
    //   122: aload_0
    //   123: invokevirtual 539	java/lang/Throwable:printStackTrace	()V
    //   126: iconst_0
    //   127: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	128	0	paramString	String
    //   0	128	1	paramBoolean	boolean
    //   0	128	2	paramInt	int
    //   26	27	3	localInputStream	InputStream
    //   56	2	3	localThrowable1	Throwable
    //   66	41	3	localThrowable2	Throwable
    //   21	27	4	localPath	Path
    //   3	71	5	localLanguage	Language
    // Exception table:
    //   from	to	target	type
    //   27	39	56	java/lang/Throwable
    //   5	27	66	java/lang/Throwable
    //   41	53	66	java/lang/Throwable
    //   57	64	66	java/lang/Throwable
    //   67	72	89	java/lang/Throwable
    //   72	87	121	java/lang/Throwable
  }
  
  public static void setDefaultFormat(String paramString)
  {
    paramString = paramString.intern();
    defaultFormatName = paramString;
    int i = 0;
    Object[] arrayOfObject = formats[i];
    Object localObject = arrayOfObject[0];
    if (localObject == null)
    {
      System.err.println("kawa: unknown output format '" + paramString + "'");
      System.exit(-1);
    }
    while (localObject != paramString)
    {
      i += 1;
      break;
    }
    defaultFormatInfo = arrayOfObject;
    try
    {
      defaultFormatMethod = Class.forName((String)arrayOfObject[1]).getMethod((String)arrayOfObject[2], (Class[])arrayOfObject[3]);
      if (!defaultFormatInfo[1].equals("gnu.lists.VoidConsumer")) {
        ModuleBody.setMainPrintValues(true);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        System.err.println("kawa:  caught " + localThrowable + " while looking for format '" + paramString + "'");
        System.exit(-1);
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\Shell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */