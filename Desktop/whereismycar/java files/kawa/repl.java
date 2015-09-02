package kawa;

import gnu.bytecode.ClassType;
import gnu.expr.ApplicationMainSupport;
import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleManager;
import gnu.lists.FString;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure0or1;
import gnu.mapping.Values;
import gnu.text.Options;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.text.WriterManager;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class repl
  extends Procedure0or1
{
  public static String compilationTopname = null;
  static int defaultParseOptions = 72;
  public static String homeDirectory;
  public static boolean noConsole;
  static Language previousLanguage;
  static boolean shutdownRegistered = WriterManager.instance.registerShutdownHook();
  Language language;
  
  public repl(Language paramLanguage)
  {
    this.language = paramLanguage;
  }
  
  static void bad_option(String paramString)
  {
    System.err.println("kawa: bad option '" + paramString + "'");
    printOptions(System.err);
    System.exit(-1);
  }
  
  static void checkInitFile()
  {
    Object localObject1;
    Object localObject2;
    if (homeDirectory == null)
    {
      localObject1 = null;
      homeDirectory = System.getProperty("user.home");
      if (homeDirectory == null) {
        break label105;
      }
      localObject2 = new FString(homeDirectory);
      if (!"/".equals(System.getProperty("file.separator"))) {
        break label99;
      }
      localObject1 = ".kawarc.scm";
      localObject1 = new File(homeDirectory, (String)localObject1);
    }
    for (;;)
    {
      Environment.getCurrent().put("home-directory", localObject2);
      if ((localObject1 != null) && (((File)localObject1).exists()) && (!Shell.runFileOrClass(((File)localObject1).getPath(), true, 0))) {
        System.exit(-1);
      }
      return;
      label99:
      localObject1 = "kawarc.scm";
      break;
      label105:
      localObject2 = Boolean.FALSE;
    }
  }
  
  public static void compileFiles(String[] paramArrayOfString, int paramInt1, int paramInt2)
  {
    ModuleManager localModuleManager = ModuleManager.getInstance();
    Compilation[] arrayOfCompilation = new Compilation[paramInt2 - paramInt1];
    ModuleInfo[] arrayOfModuleInfo = new ModuleInfo[paramInt2 - paramInt1];
    SourceMessages localSourceMessages = new SourceMessages();
    int i = paramInt1;
    String str;
    Compilation localCompilation;
    Object localObject1;
    while (i < paramInt2)
    {
      str = paramArrayOfString[i];
      getLanguageFromFilenameExtension(str);
      Object localObject2 = Language.getDefaultLanguage();
      localCompilation = null;
      localObject1 = localCompilation;
      try
      {
        Object localObject3 = InPort.openFile(str);
        localObject1 = localCompilation;
        localCompilation = ((Language)localObject2).parse((InPort)localObject3, localSourceMessages, defaultParseOptions);
        localObject1 = localCompilation;
        if (compilationTopname != null)
        {
          localObject1 = localCompilation;
          localObject2 = new ClassType(Compilation.mangleNameIfNeeded(compilationTopname));
          localObject1 = localCompilation;
          localObject3 = localCompilation.getModule();
          localObject1 = localCompilation;
          ((ModuleExp)localObject3).setType((ClassType)localObject2);
          localObject1 = localCompilation;
          ((ModuleExp)localObject3).setName(compilationTopname);
          localObject1 = localCompilation;
          localCompilation.mainClass = ((ClassType)localObject2);
        }
        localObject1 = localCompilation;
        arrayOfModuleInfo[(i - paramInt1)] = localModuleManager.find(localCompilation);
        arrayOfCompilation[(i - paramInt1)] = localCompilation;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        localObject1 = localCompilation;
        System.err.println(localFileNotFoundException);
        localObject1 = localCompilation;
        System.exit(-1);
        i = paramInt1;
        while (i < paramInt2)
        {
          localObject1 = paramArrayOfString[i];
          localCompilation = arrayOfCompilation[(i - paramInt1)];
          try
          {
            System.err.println("(compiling " + (String)localObject1 + " to " + localCompilation.mainClass.getName() + ')');
            arrayOfModuleInfo[(i - paramInt1)].loadByStages(14);
            boolean bool = localSourceMessages.seenErrors();
            localSourceMessages.checkErrors(System.err, 50);
            if (bool) {
              System.exit(-1);
            }
            arrayOfCompilation[(i - paramInt1)] = localCompilation;
            bool = localSourceMessages.seenErrors();
            localSourceMessages.checkErrors(System.err, 50);
            if (bool) {
              System.exit(-1);
            }
          }
          catch (Throwable localThrowable2)
          {
            for (;;)
            {
              internalError(localThrowable2, localThrowable1, localObject1);
            }
          }
          i += 1;
        }
      }
      catch (Throwable localThrowable1)
      {
        for (;;)
        {
          if ((!(localThrowable1 instanceof SyntaxException)) || (((SyntaxException)localThrowable1).getMessages() != localSourceMessages)) {
            internalError(localThrowable1, (Compilation)localObject1, str);
          }
        }
      }
      if (localSourceMessages.seenErrorsOrWarnings())
      {
        System.err.println("(compiling " + str + ')');
        if (localSourceMessages.checkErrors(System.err, 20)) {
          System.exit(1);
        }
      }
      i += 1;
    }
  }
  
  public static void getLanguage()
  {
    if (previousLanguage == null)
    {
      previousLanguage = Language.getInstance(null);
      Language.setDefaults(previousLanguage);
    }
  }
  
  public static void getLanguageFromFilenameExtension(String paramString)
  {
    if (previousLanguage == null)
    {
      previousLanguage = Language.getInstanceFromFilenameExtension(paramString);
      if (previousLanguage != null)
      {
        Language.setDefaults(previousLanguage);
        return;
      }
    }
    getLanguage();
  }
  
  static void internalError(Throwable paramThrowable, Compilation paramCompilation, Object paramObject)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramCompilation != null)
    {
      String str = paramCompilation.getFileName();
      int i = paramCompilation.getLineNumber();
      if ((str != null) && (i > 0))
      {
        localStringBuffer.append(str);
        localStringBuffer.append(':');
        localStringBuffer.append(i);
        localStringBuffer.append(": ");
      }
    }
    localStringBuffer.append("internal error while compiling ");
    localStringBuffer.append(paramObject);
    System.err.println(localStringBuffer.toString());
    paramThrowable.printStackTrace(System.err);
    System.exit(-1);
  }
  
  public static void main(String[] paramArrayOfString)
  {
    for (;;)
    {
      try
      {
        int i = processArgs(paramArrayOfString, 0, paramArrayOfString.length);
        if (i < 0) {
          return;
        }
        if (i < paramArrayOfString.length)
        {
          String str = paramArrayOfString[i];
          getLanguageFromFilenameExtension(str);
          setArgs(paramArrayOfString, i + 1);
          checkInitFile();
          Shell.runFileOrClass(str, false, 0);
          return;
        }
        getLanguage();
        setArgs(paramArrayOfString, i);
        checkInitFile();
        if (shouldUseGuiConsole())
        {
          startGuiConsole();
          continue;
        }
        if (Shell.run(Language.getDefaultLanguage(), Environment.getCurrent())) {
          continue;
        }
      }
      finally
      {
        if (!shutdownRegistered) {
          OutPort.runCleanups();
        }
        ModuleBody.exitDecrement();
      }
      System.exit(-1);
    }
  }
  
  public static void printOption(PrintStream paramPrintStream, String paramString1, String paramString2)
  {
    paramPrintStream.print(" ");
    paramPrintStream.print(paramString1);
    int j = paramString1.length();
    int i = 0;
    while (i < 30 - (j + 1))
    {
      paramPrintStream.print(" ");
      i += 1;
    }
    paramPrintStream.print(" ");
    paramPrintStream.println(paramString2);
  }
  
  public static void printOptions(PrintStream paramPrintStream)
  {
    paramPrintStream.println("Usage: [java kawa.repl | kawa] [options ...]");
    paramPrintStream.println();
    paramPrintStream.println(" Generic options:");
    printOption(paramPrintStream, "--help", "Show help about options");
    printOption(paramPrintStream, "--author", "Show author information");
    printOption(paramPrintStream, "--version", "Show version information");
    paramPrintStream.println();
    paramPrintStream.println(" Options");
    printOption(paramPrintStream, "-e <expr>", "Evaluate expression <expr>");
    printOption(paramPrintStream, "-c <expr>", "Same as -e, but make sure ~/.kawarc.scm is run first");
    printOption(paramPrintStream, "-f <filename>", "File to interpret");
    printOption(paramPrintStream, "-s| --", "Start reading commands interactively from console");
    printOption(paramPrintStream, "-w", "Launch the interpreter in a GUI window");
    printOption(paramPrintStream, "--server <port>", "Start a server accepting telnet connections on <port>");
    printOption(paramPrintStream, "--debug-dump-zip", "Compiled interactive expressions to a zip archive");
    printOption(paramPrintStream, "--debug-print-expr", "Print generated internal expressions");
    printOption(paramPrintStream, "--debug-print-final-expr", "Print expression after any optimizations");
    printOption(paramPrintStream, "--debug-error-prints-stack-trace", "Print stack trace with errors");
    printOption(paramPrintStream, "--debug-warning-prints-stack-trace", "Print stack trace with warnings");
    printOption(paramPrintStream, "--[no-]full-tailcalls", "(Don't) use full tail-calls");
    printOption(paramPrintStream, "-C <filename> ...", "Compile named files to Java class files");
    printOption(paramPrintStream, "--output-format <format>", "Use <format> when printing top-level output");
    printOption(paramPrintStream, "--<language>", "Select source language, one of:");
    Object localObject = Language.getLanguages();
    int i = 0;
    String str;
    while (i < localObject.length)
    {
      paramPrintStream.print("   ");
      str = localObject[i];
      int k = str.length;
      int j = 0;
      while (j < k - 1)
      {
        paramPrintStream.print(str[j] + " ");
        j += 1;
      }
      if (i == 0) {
        paramPrintStream.print("[default]");
      }
      paramPrintStream.println();
      i += 1;
    }
    paramPrintStream.println(" Compilation options, must be specified before -C");
    printOption(paramPrintStream, "-d <dirname>", "Directory to place .class files in");
    printOption(paramPrintStream, "-P <prefix>", "Prefix to prepand to class names");
    printOption(paramPrintStream, "-T <topname>", "name to give to top-level class");
    printOption(paramPrintStream, "--main", "Generate an application, with a main method");
    printOption(paramPrintStream, "--applet", "Generate an applet");
    printOption(paramPrintStream, "--servlet", "Generate a servlet");
    printOption(paramPrintStream, "--module-static", "Top-level definitions are by default static");
    localObject = Compilation.options.keys();
    i = 0;
    while (i < ((ArrayList)localObject).size())
    {
      str = (String)((ArrayList)localObject).get(i);
      printOption(paramPrintStream, "--" + str, Compilation.options.getDoc(str));
      i += 1;
    }
    paramPrintStream.println();
    paramPrintStream.println("For more information go to:  http://www.gnu.org/software/kawa/");
  }
  
  public static int processArgs(String[] paramArrayOfString, int paramInt1, int paramInt2)
  {
    int k = 0;
    int j = paramInt1;
    Object localObject7;
    Object localObject4;
    int i;
    label213:
    Object localObject2;
    Object localObject5;
    Object localObject6;
    if (j < paramInt2)
    {
      localObject7 = paramArrayOfString[j];
      Object localObject1;
      if ((((String)localObject7).equals("-c")) || (((String)localObject7).equals("-e")))
      {
        paramInt1 = j + 1;
        if (paramInt1 == paramInt2) {
          bad_option((String)localObject7);
        }
        getLanguage();
        setArgs(paramArrayOfString, paramInt1 + 1);
        if (((String)localObject7).equals("-c")) {
          checkInitFile();
        }
        localObject4 = Language.getDefaultLanguage();
        localObject1 = new SourceMessages();
        localObject4 = Shell.run((Language)localObject4, Environment.getCurrent(), new CharArrayInPort(paramArrayOfString[paramInt1]), OutPort.outDefault(), null, (SourceMessages)localObject1);
        if (localObject4 != null)
        {
          Shell.printError((Throwable)localObject4, (SourceMessages)localObject1, OutPort.errDefault());
          System.exit(-1);
        }
      }
      for (i = 1;; i = 1)
      {
        j = paramInt1 + 1;
        k = i;
        break;
        if (!((String)localObject7).equals("-f")) {
          break label213;
        }
        paramInt1 = j + 1;
        if (paramInt1 == paramInt2) {
          bad_option((String)localObject7);
        }
        localObject1 = paramArrayOfString[paramInt1];
        getLanguageFromFilenameExtension((String)localObject1);
        setArgs(paramArrayOfString, paramInt1 + 1);
        checkInitFile();
        if (!Shell.runFileOrClass((String)localObject1, true, 0)) {
          System.exit(-1);
        }
      }
      if (((String)localObject7).startsWith("--script"))
      {
        localObject1 = ((String)localObject7).substring(8);
        j += 1;
        k = 0;
        paramInt1 = k;
        i = j;
        if (((String)localObject1).length() > 0) {}
        try
        {
          paramInt1 = Integer.parseInt((String)localObject1);
          i = j;
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            i = paramInt2;
            paramInt1 = k;
          }
        }
        if (i == paramInt2) {
          bad_option((String)localObject7);
        }
        localObject1 = paramArrayOfString[i];
        getLanguageFromFilenameExtension((String)localObject1);
        setArgs(paramArrayOfString, i + 1);
        checkInitFile();
        if (!Shell.runFileOrClass((String)localObject1, true, paramInt1)) {
          System.exit(-1);
        }
        return -1;
      }
      if (((String)localObject7).equals("\\"))
      {
        j += 1;
        if (j == paramInt2) {
          bad_option((String)localObject7);
        }
        localObject4 = paramArrayOfString[j];
        localObject2 = new SourceMessages();
        for (;;)
        {
          try
          {
            localObject5 = new BufferedInputStream(new FileInputStream((String)localObject4));
            paramInt2 = ((InputStream)localObject5).read();
            if (paramInt2 == 35)
            {
              localObject7 = new StringBuffer(100);
              localObject6 = new Vector(10);
              i = 0;
              paramInt1 = i;
              if (paramInt2 != 10)
              {
                paramInt1 = i;
                if (paramInt2 != 13)
                {
                  paramInt1 = i;
                  if (paramInt2 >= 0)
                  {
                    paramInt2 = ((InputStream)localObject5).read();
                    continue;
                  }
                }
              }
              label451:
              i = ((InputStream)localObject5).read();
              if (i >= 0) {
                break label2705;
              }
              System.err.println("unexpected end-of-file processing argument line for: '" + (String)localObject4 + '\'');
              System.exit(-1);
              break label2705;
              label502:
              if (((StringBuffer)localObject7).length() > 0) {
                ((Vector)localObject6).addElement(((StringBuffer)localObject7).toString());
              }
              paramInt1 = ((Vector)localObject6).size();
              if (paramInt1 > 0)
              {
                localObject7 = new String[paramInt1];
                ((Vector)localObject6).copyInto((Object[])localObject7);
                paramInt2 = processArgs((String[])localObject7, 0, paramInt1);
                if ((paramInt2 >= 0) && (paramInt2 < paramInt1)) {
                  System.err.println("" + (paramInt1 - paramInt2) + " unused meta args");
                }
              }
            }
            getLanguageFromFilenameExtension((String)localObject4);
            localObject4 = InPort.openFile((InputStream)localObject5, localObject4);
            setArgs(paramArrayOfString, j + 1);
            checkInitFile();
            paramArrayOfString = OutPort.errDefault();
            localObject4 = Shell.run(Language.getDefaultLanguage(), Environment.getCurrent(), (InPort)localObject4, OutPort.outDefault(), null, (SourceMessages)localObject2);
            ((SourceMessages)localObject2).printAll(paramArrayOfString, 20);
            if (localObject4 != null)
            {
              if (((localObject4 instanceof SyntaxException)) && (((SyntaxException)localObject4).getMessages() == localObject2)) {
                System.exit(1);
              }
              throw ((Throwable)localObject4);
            }
          }
          catch (Throwable paramArrayOfString)
          {
            Shell.printError(paramArrayOfString, (SourceMessages)localObject2, OutPort.errDefault());
            System.exit(1);
          }
          return -1;
          label695:
          if (i != 32)
          {
            paramInt2 = paramInt1;
            if (i != 9) {
              break;
            }
          }
          if (((StringBuffer)localObject7).length() > 0)
          {
            ((Vector)localObject6).addElement(((StringBuffer)localObject7).toString());
            ((StringBuffer)localObject7).setLength(0);
          }
        }
      }
    }
    for (;;)
    {
      label738:
      ((StringBuffer)localObject7).append((char)i);
      paramInt1 = paramInt2;
      break label451;
      label2383:
      label2427:
      label2455:
      label2624:
      label2630:
      label2705:
      do
      {
        paramInt2 = paramInt1;
        if (i != paramInt1) {
          break label738;
        }
        paramInt1 = 0;
        break label451;
        if ((((String)localObject7).equals("-s")) || (((String)localObject7).equals("--")))
        {
          getLanguage();
          setArgs(paramArrayOfString, j + 1);
          checkInitFile();
          Shell.run(Language.getDefaultLanguage(), Environment.getCurrent());
          return -1;
        }
        if (((String)localObject7).equals("-w"))
        {
          paramInt1 = j + 1;
          getLanguage();
          setArgs(paramArrayOfString, paramInt1);
          checkInitFile();
          startGuiConsole();
          i = 1;
          break;
        }
        if (((String)localObject7).equals("-d"))
        {
          paramInt1 = j + 1;
          if (paramInt1 == paramInt2) {
            bad_option((String)localObject7);
          }
          ModuleManager.getInstance().setCompilationDirectory(paramArrayOfString[paramInt1]);
          i = k;
          break;
        }
        if ((((String)localObject7).equals("--target")) || (((String)localObject7).equals("target")))
        {
          paramInt1 = j + 1;
          if (paramInt1 == paramInt2) {
            bad_option((String)localObject7);
          }
          localObject2 = paramArrayOfString[paramInt1];
          if (((String)localObject2).equals("7")) {
            Compilation.defaultClassFileVersion = 3342336;
          }
          if ((((String)localObject2).equals("6")) || (((String)localObject2).equals("1.6")))
          {
            Compilation.defaultClassFileVersion = 3276800;
            i = k;
            break;
          }
          if ((((String)localObject2).equals("5")) || (((String)localObject2).equals("1.5")))
          {
            Compilation.defaultClassFileVersion = 3211264;
            i = k;
            break;
          }
          if (((String)localObject2).equals("1.4"))
          {
            Compilation.defaultClassFileVersion = 3145728;
            i = k;
            break;
          }
          if (((String)localObject2).equals("1.3"))
          {
            Compilation.defaultClassFileVersion = 3080192;
            i = k;
            break;
          }
          if (((String)localObject2).equals("1.2"))
          {
            Compilation.defaultClassFileVersion = 3014656;
            i = k;
            break;
          }
          if (((String)localObject2).equals("1.1"))
          {
            Compilation.defaultClassFileVersion = 2949123;
            i = k;
            break;
          }
          bad_option((String)localObject2);
          i = k;
          break;
        }
        if (((String)localObject7).equals("-P"))
        {
          paramInt1 = j + 1;
          if (paramInt1 == paramInt2) {
            bad_option((String)localObject7);
          }
          Compilation.classPrefixDefault = paramArrayOfString[paramInt1];
          i = k;
          break;
        }
        if (((String)localObject7).equals("-T"))
        {
          paramInt1 = j + 1;
          if (paramInt1 == paramInt2) {
            bad_option((String)localObject7);
          }
          compilationTopname = paramArrayOfString[paramInt1];
          i = k;
          break;
        }
        if (((String)localObject7).equals("-C"))
        {
          paramInt1 = j + 1;
          if (paramInt1 == paramInt2) {
            bad_option((String)localObject7);
          }
          compileFiles(paramArrayOfString, paramInt1, paramInt2);
          return -1;
        }
        if ((((String)localObject7).equals("--output-format")) || (((String)localObject7).equals("--format")))
        {
          paramInt1 = j + 1;
          if (paramInt1 == paramInt2) {
            bad_option((String)localObject7);
          }
          Shell.setDefaultFormat(paramArrayOfString[paramInt1]);
          i = k;
          break;
        }
        if (((String)localObject7).equals("--connect"))
        {
          j += 1;
          if (j == paramInt2) {
            bad_option((String)localObject7);
          }
          if (paramArrayOfString[j].equals("-")) {
            paramInt1 = 0;
          }
          for (;;)
          {
            try
            {
              localObject4 = new Telnet(new Socket(InetAddress.getByName(null), paramInt1), true);
              localObject2 = ((Telnet)localObject4).getInputStream();
              localObject4 = new PrintStream(((Telnet)localObject4).getOutputStream(), true);
              System.setIn((InputStream)localObject2);
              System.setOut((PrintStream)localObject4);
              System.setErr((PrintStream)localObject4);
              i = k;
              paramInt1 = j;
            }
            catch (IOException paramArrayOfString)
            {
              paramArrayOfString.printStackTrace(System.err);
              throw new Error(paramArrayOfString.toString());
            }
            try
            {
              paramInt1 = Integer.parseInt(paramArrayOfString[j]);
            }
            catch (NumberFormatException localNumberFormatException)
            {
              bad_option("--connect port#");
              paramInt1 = -1;
            }
          }
        }
        Object localObject3;
        if (((String)localObject7).equals("--server"))
        {
          getLanguage();
          paramInt1 = j + 1;
          if (paramInt1 == paramInt2) {
            bad_option((String)localObject7);
          }
          if (paramArrayOfString[paramInt1].equals("-")) {
            paramInt1 = 0;
          }
          for (;;)
          {
            try
            {
              paramArrayOfString = new ServerSocket(paramInt1);
              paramInt1 = paramArrayOfString.getLocalPort();
              System.err.println("Listening on port " + paramInt1);
              System.err.print("waiting ... ");
              System.err.flush();
              localObject3 = paramArrayOfString.accept();
              System.err.println("got connection from " + ((Socket)localObject3).getInetAddress() + " port:" + ((Socket)localObject3).getPort());
              TelnetRepl.serve(Language.getDefaultLanguage(), (Socket)localObject3);
              continue;
              try
              {
                paramInt1 = Integer.parseInt(paramArrayOfString[paramInt1]);
              }
              catch (NumberFormatException paramArrayOfString)
              {
                bad_option("--server port#");
                paramInt1 = -1;
              }
            }
            catch (IOException paramArrayOfString)
            {
              throw new Error(paramArrayOfString.toString());
            }
          }
        }
        if (((String)localObject7).equals("--http-auto-handler"))
        {
          paramInt1 = j + 2;
          if (paramInt1 >= paramInt2) {
            bad_option((String)localObject7);
          }
          System.err.println("kawa: HttpServer classes not found");
          System.exit(-1);
          i = k;
          break;
        }
        if (((String)localObject7).equals("--http-start"))
        {
          paramInt1 = j + 1;
          if (paramInt1 >= paramInt2) {
            bad_option("missing httpd port argument");
          }
          System.err.println("kawa: HttpServer classes not found");
          System.exit(-1);
          i = k;
          break;
        }
        if (((String)localObject7).equals("--main"))
        {
          Compilation.generateMainDefault = true;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--applet"))
        {
          defaultParseOptions |= 0x10;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--servlet"))
        {
          defaultParseOptions |= 0x20;
          gnu.kawa.servlet.HttpRequestContext.importServletDefinitions = 2;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--debug-dump-zip"))
        {
          ModuleExp.dumpZipPrefix = "kawa-zip-dump-";
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--debug-print-expr"))
        {
          Compilation.debugPrintExpr = true;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--debug-print-final-expr"))
        {
          Compilation.debugPrintFinalExpr = true;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--debug-error-prints-stack-trace"))
        {
          SourceMessages.debugStackTraceOnError = true;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--debug-warning-prints-stack-trace"))
        {
          SourceMessages.debugStackTraceOnWarning = true;
          i = k;
          paramInt1 = j;
          break;
        }
        if ((((String)localObject7).equals("--module-nonstatic")) || (((String)localObject7).equals("--no-module-static")))
        {
          Compilation.moduleStatic = -1;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--module-static"))
        {
          Compilation.moduleStatic = 1;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--module-static-run"))
        {
          Compilation.moduleStatic = 2;
          i = k;
          paramInt1 = j;
          break;
        }
        if ((((String)localObject7).equals("--no-inline")) || (((String)localObject7).equals("--inline=none")))
        {
          Compilation.inlineOk = false;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--no-console"))
        {
          noConsole = true;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--inline"))
        {
          Compilation.inlineOk = true;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--cps"))
        {
          Compilation.defaultCallConvention = 4;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--full-tailcalls"))
        {
          Compilation.defaultCallConvention = 3;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--no-full-tailcalls"))
        {
          Compilation.defaultCallConvention = 1;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--pedantic"))
        {
          Language.requirePedantic = true;
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--help"))
        {
          printOptions(System.out);
          System.exit(0);
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--author"))
        {
          System.out.println("Per Bothner <per@bothner.com>");
          System.exit(0);
          i = k;
          paramInt1 = j;
          break;
        }
        if (((String)localObject7).equals("--version"))
        {
          System.out.print("Kawa ");
          System.out.print(Version.getVersion());
          System.out.println();
          System.out.println("Copyright (C) 2009 Per Bothner");
          i = 1;
          paramInt1 = j;
          break;
        }
        if ((((String)localObject7).length() > 0) && (((String)localObject7).charAt(0) == '-'))
        {
          localObject4 = localObject7;
          localObject3 = localObject4;
          if (((String)localObject4).length() > 2)
          {
            localObject3 = localObject4;
            if (((String)localObject4).charAt(0) == '-')
            {
              if (((String)localObject4).charAt(1) != '-') {
                break label2427;
              }
              paramInt1 = 2;
              localObject3 = ((String)localObject4).substring(paramInt1);
            }
          }
          localObject4 = Language.getInstance((String)localObject3);
          if (localObject4 != null)
          {
            if (previousLanguage == null) {
              Language.setDefaults((Language)localObject4);
            }
            for (;;)
            {
              previousLanguage = (Language)localObject4;
              i = k;
              paramInt1 = j;
              break;
              paramInt1 = 1;
              break label2383;
              Language.setCurrentLanguage((Language)localObject4);
            }
          }
          paramInt1 = ((String)localObject3).indexOf("=");
          if (paramInt1 < 0)
          {
            localObject4 = null;
            if ((!((String)localObject3).startsWith("no-")) || (((String)localObject3).length() <= 3)) {
              break label2624;
            }
          }
          for (int m = 1;; m = 0)
          {
            localObject6 = localObject3;
            localObject5 = localObject4;
            if (localObject4 == null)
            {
              localObject6 = localObject3;
              localObject5 = localObject4;
              if (m != 0)
              {
                localObject5 = "no";
                localObject6 = ((String)localObject3).substring(3);
              }
            }
            localObject4 = Compilation.options.set((String)localObject6, (String)localObject5);
            i = k;
            paramInt1 = j;
            if (localObject4 == null) {
              break;
            }
            localObject3 = localObject4;
            if (m != 0)
            {
              localObject3 = localObject4;
              if (localObject4 == "unknown option name") {
                localObject3 = "both '--no-' prefix and '=" + (String)localObject5 + "' specified";
              }
            }
            if (localObject3 != "unknown option name") {
              break label2630;
            }
            bad_option((String)localObject7);
            i = k;
            paramInt1 = j;
            break;
            localObject4 = ((String)localObject3).substring(paramInt1 + 1);
            localObject3 = ((String)localObject3).substring(0, paramInt1);
            break label2455;
          }
          System.err.println("kawa: bad option '" + (String)localObject7 + "': " + (String)localObject3);
          System.exit(-1);
          i = k;
          paramInt1 = j;
          break;
        }
        i = k;
        paramInt1 = j;
        if (ApplicationMainSupport.processSetProperty((String)localObject7)) {
          break;
        }
        if (k != 0) {
          return -1;
        }
        return j;
        if (paramInt1 == 0)
        {
          if ((i == 92) || (i == 39) || (i == 34))
          {
            paramInt1 = i;
            break label451;
          }
          if (i == 10) {
            break label502;
          }
          if (i != 13) {
            break label695;
          }
          break label502;
        }
      } while (paramInt1 != 92);
      paramInt2 = 0;
    }
  }
  
  public static void setArgs(String[] paramArrayOfString, int paramInt)
  {
    ApplicationMainSupport.setArgs(paramArrayOfString, paramInt);
  }
  
  public static boolean shouldUseGuiConsole()
  {
    if (noConsole) {}
    for (;;)
    {
      return true;
      try
      {
        Object localObject = Class.forName("java.lang.System").getMethod("console", new Class[0]).invoke(new Object[0], new Object[0]);
        if (localObject == null) {}
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
    return false;
  }
  
  private static void startGuiConsole()
  {
    try
    {
      Class.forName("kawa.GuiConsole").newInstance();
      return;
    }
    catch (Exception localException)
    {
      System.err.println("failed to create Kawa window: " + localException);
      System.exit(-1);
    }
  }
  
  public Object apply0()
  {
    Shell.run(this.language, Environment.getCurrent());
    return Values.empty;
  }
  
  public Object apply1(Object paramObject)
  {
    Shell.run(this.language, (Environment)paramObject);
    return Values.empty;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\repl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */