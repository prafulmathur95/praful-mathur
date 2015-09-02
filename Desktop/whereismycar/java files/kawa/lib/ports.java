package kawa.lib;

import gnu.bytecode.ClassType;
import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.lists.EofClass;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.InPort;
import gnu.mapping.LocationProc;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.LineBufferedReader;
import gnu.text.Path;
import gnu.text.SyntaxException;
import java.io.Writer;
import kawa.standard.Scheme;
import kawa.standard.char_ready_p;
import kawa.standard.read_line;

public class ports
  extends ModuleBody
{
  public static final ports $instance;
  static final SimpleSymbol Lit0;
  static final ClassType Lit1;
  static final SimpleSymbol Lit10;
  static final SimpleSymbol Lit11;
  static final SimpleSymbol Lit12;
  static final SimpleSymbol Lit13;
  static final SimpleSymbol Lit14;
  static final SimpleSymbol Lit15;
  static final SimpleSymbol Lit16;
  static final SimpleSymbol Lit17;
  static final SimpleSymbol Lit18;
  static final SimpleSymbol Lit19;
  static final SimpleSymbol Lit2;
  static final SimpleSymbol Lit20;
  static final SimpleSymbol Lit21;
  static final SimpleSymbol Lit22;
  static final SimpleSymbol Lit23;
  static final SimpleSymbol Lit24;
  static final SimpleSymbol Lit25;
  static final SimpleSymbol Lit26;
  static final SimpleSymbol Lit27;
  static final SimpleSymbol Lit28;
  static final SimpleSymbol Lit29;
  static final ClassType Lit3;
  static final SimpleSymbol Lit30;
  static final SimpleSymbol Lit31;
  static final SimpleSymbol Lit32;
  static final SimpleSymbol Lit33;
  static final SimpleSymbol Lit34;
  static final SimpleSymbol Lit35;
  static final SimpleSymbol Lit36;
  static final SimpleSymbol Lit37;
  static final SimpleSymbol Lit38;
  static final SimpleSymbol Lit39;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit40;
  static final SimpleSymbol Lit41;
  static final SimpleSymbol Lit42;
  static final SimpleSymbol Lit43;
  static final SimpleSymbol Lit44;
  static final SimpleSymbol Lit45 = (SimpleSymbol)new SimpleSymbol("transcript-off").readResolve();
  static final Keyword Lit5;
  static final IntNum Lit6;
  static final Char Lit7;
  static final Char Lit8;
  static final SimpleSymbol Lit9;
  public static final ModuleMethod call$Mnwith$Mninput$Mnfile;
  public static final ModuleMethod call$Mnwith$Mninput$Mnstring;
  public static final ModuleMethod call$Mnwith$Mnoutput$Mnfile;
  public static final ModuleMethod call$Mnwith$Mnoutput$Mnstring;
  public static final ModuleMethod char$Mnready$Qu;
  public static final ModuleMethod close$Mninput$Mnport;
  public static final ModuleMethod close$Mnoutput$Mnport;
  public static final LocationProc current$Mnerror$Mnport;
  public static final LocationProc current$Mninput$Mnport;
  public static final LocationProc current$Mnoutput$Mnport;
  public static final ModuleMethod default$Mnprompter;
  public static final ModuleMethod display;
  public static final ModuleMethod eof$Mnobject$Qu;
  public static final ModuleMethod force$Mnoutput;
  public static final ModuleMethod get$Mnoutput$Mnstring;
  public static final ModuleMethod input$Mnport$Mncolumn$Mnnumber;
  public static final GenericProc input$Mnport$Mnline$Mnnumber;
  static final ModuleMethod input$Mnport$Mnline$Mnnumber$Fn5;
  public static final GenericProc input$Mnport$Mnprompter;
  static final ModuleMethod input$Mnport$Mnprompter$Fn6;
  public static final ModuleMethod input$Mnport$Mnread$Mnstate;
  public static final ModuleMethod input$Mnport$Qu;
  static final ModuleMethod lambda$Fn1;
  static final ModuleMethod lambda$Fn2;
  static final ModuleMethod lambda$Fn3;
  public static final ModuleMethod newline;
  public static final ModuleMethod open$Mninput$Mnfile;
  public static final ModuleMethod open$Mninput$Mnstring;
  public static final ModuleMethod open$Mnoutput$Mnfile;
  public static final ModuleMethod open$Mnoutput$Mnstring;
  public static final ModuleMethod output$Mnport$Qu;
  public static final ModuleMethod port$Mncolumn;
  public static final GenericProc port$Mnline;
  static final ModuleMethod port$Mnline$Fn4;
  public static final ModuleMethod read;
  public static final ModuleMethod read$Mnline;
  public static final ModuleMethod set$Mninput$Mnport$Mnline$Mnnumber$Ex;
  public static final ModuleMethod set$Mninput$Mnport$Mnprompter$Ex;
  public static final ModuleMethod set$Mnport$Mnline$Ex;
  public static final ModuleMethod transcript$Mnoff;
  public static final ModuleMethod transcript$Mnon;
  public static final ModuleMethod with$Mninput$Mnfrom$Mnfile;
  public static final ModuleMethod with$Mnoutput$Mnto$Mnfile;
  public static final ModuleMethod write;
  public static final ModuleMethod write$Mnchar;
  
  static
  {
    Lit44 = (SimpleSymbol)new SimpleSymbol("transcript-on").readResolve();
    Lit43 = (SimpleSymbol)new SimpleSymbol("read-line").readResolve();
    Lit42 = (SimpleSymbol)new SimpleSymbol("read").readResolve();
    Lit41 = (SimpleSymbol)new SimpleSymbol("close-output-port").readResolve();
    Lit40 = (SimpleSymbol)new SimpleSymbol("close-input-port").readResolve();
    Lit39 = (SimpleSymbol)new SimpleSymbol("input-port-prompter").readResolve();
    Lit38 = (SimpleSymbol)new SimpleSymbol("set-input-port-prompter!").readResolve();
    Lit37 = (SimpleSymbol)new SimpleSymbol("default-prompter").readResolve();
    Lit36 = (SimpleSymbol)new SimpleSymbol("input-port-column-number").readResolve();
    Lit35 = (SimpleSymbol)new SimpleSymbol("port-column").readResolve();
    Lit34 = (SimpleSymbol)new SimpleSymbol("input-port-line-number").readResolve();
    Lit33 = (SimpleSymbol)new SimpleSymbol("set-input-port-line-number!").readResolve();
    Lit32 = (SimpleSymbol)new SimpleSymbol("port-line").readResolve();
    Lit31 = (SimpleSymbol)new SimpleSymbol("set-port-line!").readResolve();
    Lit30 = (SimpleSymbol)new SimpleSymbol("input-port-read-state").readResolve();
    Lit29 = (SimpleSymbol)new SimpleSymbol("display").readResolve();
    Lit28 = (SimpleSymbol)new SimpleSymbol("write").readResolve();
    Lit27 = (SimpleSymbol)new SimpleSymbol("char-ready?").readResolve();
    Lit26 = (SimpleSymbol)new SimpleSymbol("eof-object?").readResolve();
    Lit25 = (SimpleSymbol)new SimpleSymbol("newline").readResolve();
    Lit24 = (SimpleSymbol)new SimpleSymbol("force-output").readResolve();
    Lit23 = (SimpleSymbol)new SimpleSymbol("call-with-output-string").readResolve();
    Lit22 = (SimpleSymbol)new SimpleSymbol("call-with-input-string").readResolve();
    Lit21 = (SimpleSymbol)new SimpleSymbol("get-output-string").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("open-output-string").readResolve();
    Lit19 = (SimpleSymbol)new SimpleSymbol("open-input-string").readResolve();
    Lit18 = (SimpleSymbol)new SimpleSymbol("write-char").readResolve();
    Lit17 = (SimpleSymbol)new SimpleSymbol("output-port?").readResolve();
    Lit16 = (SimpleSymbol)new SimpleSymbol("input-port?").readResolve();
    Lit15 = (SimpleSymbol)new SimpleSymbol("with-output-to-file").readResolve();
    Lit14 = (SimpleSymbol)new SimpleSymbol("with-input-from-file").readResolve();
    Lit13 = (SimpleSymbol)new SimpleSymbol("call-with-output-file").readResolve();
    Lit12 = (SimpleSymbol)new SimpleSymbol("call-with-input-file").readResolve();
    Lit11 = (SimpleSymbol)new SimpleSymbol("open-output-file").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("open-input-file").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("trim").readResolve();
    Lit8 = Char.make(32);
    Lit7 = Char.make(10);
    Lit6 = IntNum.make(1);
    Lit5 = Keyword.make("setter");
    Lit4 = (SimpleSymbol)new SimpleSymbol("current-error-port").readResolve();
    Lit3 = ClassType.make("gnu.mapping.OutPort");
    Lit2 = (SimpleSymbol)new SimpleSymbol("current-output-port").readResolve();
    Lit1 = ClassType.make("gnu.mapping.InPort");
    Lit0 = (SimpleSymbol)new SimpleSymbol("current-input-port").readResolve();
    $instance = new ports();
    ports localports = $instance;
    open$Mninput$Mnfile = new ModuleMethod(localports, 1, Lit10, 4097);
    open$Mnoutput$Mnfile = new ModuleMethod(localports, 2, Lit11, 4097);
    call$Mnwith$Mninput$Mnfile = new ModuleMethod(localports, 3, Lit12, 8194);
    call$Mnwith$Mnoutput$Mnfile = new ModuleMethod(localports, 4, Lit13, 8194);
    with$Mninput$Mnfrom$Mnfile = new ModuleMethod(localports, 5, Lit14, 8194);
    with$Mnoutput$Mnto$Mnfile = new ModuleMethod(localports, 6, Lit15, 8194);
    input$Mnport$Qu = new ModuleMethod(localports, 7, Lit16, 4097);
    output$Mnport$Qu = new ModuleMethod(localports, 8, Lit17, 4097);
    lambda$Fn1 = new ModuleMethod(localports, 9, null, 4097);
    lambda$Fn2 = new ModuleMethod(localports, 10, null, 4097);
    lambda$Fn3 = new ModuleMethod(localports, 11, null, 4097);
    write$Mnchar = new ModuleMethod(localports, 12, Lit18, 8193);
    open$Mninput$Mnstring = new ModuleMethod(localports, 14, Lit19, 4097);
    open$Mnoutput$Mnstring = new ModuleMethod(localports, 15, Lit20, 0);
    get$Mnoutput$Mnstring = new ModuleMethod(localports, 16, Lit21, 4097);
    call$Mnwith$Mninput$Mnstring = new ModuleMethod(localports, 17, Lit22, 8194);
    call$Mnwith$Mnoutput$Mnstring = new ModuleMethod(localports, 18, Lit23, 4097);
    force$Mnoutput = new ModuleMethod(localports, 19, Lit24, 4096);
    newline = new ModuleMethod(localports, 21, Lit25, 4096);
    eof$Mnobject$Qu = new ModuleMethod(localports, 23, Lit26, 4097);
    char$Mnready$Qu = new ModuleMethod(localports, 24, Lit27, 4096);
    write = new ModuleMethod(localports, 26, Lit28, 8193);
    display = new ModuleMethod(localports, 28, Lit29, 8193);
    input$Mnport$Mnread$Mnstate = new ModuleMethod(localports, 30, Lit30, 4097);
    set$Mnport$Mnline$Ex = new ModuleMethod(localports, 31, Lit31, 8194);
    port$Mnline$Fn4 = new ModuleMethod(localports, 32, Lit32, 4097);
    set$Mninput$Mnport$Mnline$Mnnumber$Ex = new ModuleMethod(localports, 33, Lit33, 8194);
    input$Mnport$Mnline$Mnnumber$Fn5 = new ModuleMethod(localports, 34, Lit34, 4097);
    port$Mncolumn = new ModuleMethod(localports, 35, Lit35, 4097);
    input$Mnport$Mncolumn$Mnnumber = new ModuleMethod(localports, 36, Lit36, 4097);
    default$Mnprompter = new ModuleMethod(localports, 37, Lit37, 4097);
    set$Mninput$Mnport$Mnprompter$Ex = new ModuleMethod(localports, 38, Lit38, 8194);
    input$Mnport$Mnprompter$Fn6 = new ModuleMethod(localports, 39, Lit39, 4097);
    close$Mninput$Mnport = new ModuleMethod(localports, 40, Lit40, 4097);
    close$Mnoutput$Mnport = new ModuleMethod(localports, 41, Lit41, 4097);
    read = new ModuleMethod(localports, 42, Lit42, 4096);
    read$Mnline = new ModuleMethod(localports, 44, Lit43, 8192);
    transcript$Mnon = new ModuleMethod(localports, 47, Lit44, 4097);
    transcript$Mnoff = new ModuleMethod(localports, 48, Lit45, 0);
    $instance.run();
  }
  
  public ports()
  {
    ModuleInfo.register(this);
  }
  
  public static Object callWithInputFile(Path paramPath, Procedure paramProcedure)
  {
    paramPath = openInputFile(paramPath);
    try
    {
      paramProcedure = paramProcedure.apply1(paramPath);
      return paramProcedure;
    }
    finally
    {
      closeInputPort(paramPath);
    }
  }
  
  public static Object callWithInputString(CharSequence paramCharSequence, Procedure paramProcedure)
  {
    if (paramCharSequence == null) {}
    for (paramCharSequence = null;; paramCharSequence = paramCharSequence.toString())
    {
      paramCharSequence = new CharArrayInPort(paramCharSequence);
      paramProcedure = paramProcedure.apply1(paramCharSequence);
      closeInputPort(paramCharSequence);
      return paramProcedure;
    }
  }
  
  public static Object callWithOutputFile(Path paramPath, Procedure paramProcedure)
  {
    paramPath = openOutputFile(paramPath);
    try
    {
      paramProcedure = paramProcedure.apply1(paramPath);
      return paramProcedure;
    }
    finally
    {
      closeOutputPort(paramPath);
    }
  }
  
  public static FString callWithOutputString(Procedure paramProcedure)
  {
    CharArrayOutPort localCharArrayOutPort = new CharArrayOutPort();
    paramProcedure.apply1(localCharArrayOutPort);
    paramProcedure = localCharArrayOutPort.toCharArray();
    localCharArrayOutPort.close();
    return new FString(paramProcedure);
  }
  
  public static Object closeInputPort(InPort paramInPort)
  {
    paramInPort.close();
    return Values.empty;
  }
  
  public static Object closeOutputPort(OutPort paramOutPort)
  {
    paramOutPort.close();
    return Values.empty;
  }
  
  public static Object defaultPrompter(Object paramObject)
  {
    int i = inputPortReadState(paramObject);
    if (characters.isChar$Eq(Char.make(i), Lit7)) {
      return "";
    }
    if (characters.isChar$Eq(Char.make(i), Lit8)) {}
    for (Object localObject = "#|kawa:";; localObject = strings.stringAppend(new Object[] { "#|", strings.makeString(1, Char.make(i)), "---:" }))
    {
      paramObject = input$Mnport$Mnline$Mnnumber.apply1(paramObject);
      try
      {
        Number localNumber = (Number)paramObject;
        return strings.stringAppend(new Object[] { localObject, numbers.number$To$String(localNumber), "|# " });
      }
      catch (ClassCastException localClassCastException)
      {
        throw new WrongType(localClassCastException, "number->string", 0, paramObject);
      }
    }
  }
  
  public static void display(Object paramObject)
  {
    display(paramObject, current$Mnoutput$Mnport.apply0());
  }
  
  public static void display(Object paramObject1, Object paramObject2)
  {
    AbstractFormat localAbstractFormat = Scheme.displayFormat;
    try
    {
      Consumer localConsumer = (Consumer)paramObject2;
      localAbstractFormat.format(paramObject1, localConsumer);
      return;
    }
    catch (ClassCastException paramObject1)
    {
      throw new WrongType((ClassCastException)paramObject1, "gnu.lists.AbstractFormat.format(java.lang.Object,gnu.lists.Consumer)", 3, paramObject2);
    }
  }
  
  public static void forceOutput()
  {
    forceOutput(current$Mnoutput$Mnport.apply0());
  }
  
  public static void forceOutput(Object paramObject)
  {
    try
    {
      Writer localWriter = (Writer)paramObject;
      localWriter.flush();
      return;
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "java.io.Writer.flush()", 1, paramObject);
    }
  }
  
  public static FString getOutputString(CharArrayOutPort paramCharArrayOutPort)
  {
    return new FString(paramCharArrayOutPort.toCharArray());
  }
  
  public static int inputPortColumnNumber(Object paramObject)
  {
    return portColumn(paramObject) + 1;
  }
  
  public static Object inputPortLineNumber(LineBufferedReader paramLineBufferedReader)
  {
    return AddOp.$Pl.apply2(Lit6, port$Mnline.apply1(paramLineBufferedReader));
  }
  
  public static Procedure inputPortPrompter(TtyInPort paramTtyInPort)
  {
    return paramTtyInPort.getPrompter();
  }
  
  public static char inputPortReadState(Object paramObject)
  {
    try
    {
      InPort localInPort = (InPort)paramObject;
      return localInPort.getReadState();
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "gnu.mapping.InPort.getReadState()", 1, paramObject);
    }
  }
  
  public static boolean isCharReady()
  {
    return isCharReady(current$Mninput$Mnport.apply0());
  }
  
  public static boolean isCharReady(Object paramObject)
  {
    return char_ready_p.ready(paramObject);
  }
  
  public static boolean isEofObject(Object paramObject)
  {
    return paramObject == EofClass.eofValue;
  }
  
  public static boolean isInputPort(Object paramObject)
  {
    return paramObject instanceof InPort;
  }
  
  public static boolean isOutputPort(Object paramObject)
  {
    return paramObject instanceof OutPort;
  }
  
  static Object lambda1(Object paramObject)
  {
    try
    {
      InPort localInPort = (InPort)paramObject;
      return localInPort;
    }
    catch (ClassCastException localClassCastException)
    {
      paramObject = WrongType.make(localClassCastException, current$Mninput$Mnport, 1, paramObject);
      ((WrongType)paramObject).expectedType = Lit1;
      throw ((Throwable)paramObject);
    }
  }
  
  static Object lambda2(Object paramObject)
  {
    try
    {
      OutPort localOutPort = (OutPort)paramObject;
      return localOutPort;
    }
    catch (ClassCastException localClassCastException)
    {
      paramObject = WrongType.make(localClassCastException, current$Mnoutput$Mnport, 1, paramObject);
      ((WrongType)paramObject).expectedType = Lit3;
      throw ((Throwable)paramObject);
    }
  }
  
  static Object lambda3(Object paramObject)
  {
    try
    {
      OutPort localOutPort = (OutPort)paramObject;
      return localOutPort;
    }
    catch (ClassCastException localClassCastException)
    {
      paramObject = WrongType.make(localClassCastException, current$Mnerror$Mnport, 1, paramObject);
      ((WrongType)paramObject).expectedType = Lit3;
      throw ((Throwable)paramObject);
    }
  }
  
  public static void newline()
  {
    newline(current$Mnoutput$Mnport.apply0());
  }
  
  public static void newline(Object paramObject)
  {
    try
    {
      OutPort localOutPort = (OutPort)paramObject;
      localOutPort.println();
      return;
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "gnu.mapping.OutPort.println()", 1, paramObject);
    }
  }
  
  public static InPort openInputFile(Path paramPath)
  {
    return InPort.openFile(paramPath);
  }
  
  public static InPort openInputString(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {}
    for (paramCharSequence = null;; paramCharSequence = paramCharSequence.toString()) {
      return new CharArrayInPort(paramCharSequence);
    }
  }
  
  public static OutPort openOutputFile(Path paramPath)
  {
    return OutPort.openFile(paramPath);
  }
  
  public static CharArrayOutPort openOutputString()
  {
    return new CharArrayOutPort();
  }
  
  public static int portColumn(Object paramObject)
  {
    try
    {
      LineBufferedReader localLineBufferedReader = (LineBufferedReader)paramObject;
      return localLineBufferedReader.getColumnNumber();
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "gnu.text.LineBufferedReader.getColumnNumber()", 1, paramObject);
    }
  }
  
  public static int portLine(LineBufferedReader paramLineBufferedReader)
  {
    return paramLineBufferedReader.getLineNumber();
  }
  
  public static Object read()
  {
    return read((InPort)current$Mninput$Mnport.apply0());
  }
  
  public static Object read(InPort paramInPort)
  {
    paramInPort = new LispReader(paramInPort);
    Object localObject;
    try
    {
      localObject = paramInPort.readObject();
      if (paramInPort.seenErrors()) {
        throw ((Throwable)new SyntaxException(paramInPort.getMessages()));
      }
    }
    catch (SyntaxException paramInPort)
    {
      paramInPort.setHeader("syntax error in read:");
      throw ((Throwable)paramInPort);
    }
    return localObject;
  }
  
  public static Object readLine()
  {
    return readLine((LineBufferedReader)current$Mninput$Mnport.apply0(), Lit9);
  }
  
  public static Object readLine(LineBufferedReader paramLineBufferedReader)
  {
    return readLine(paramLineBufferedReader, Lit9);
  }
  
  public static Object readLine(LineBufferedReader paramLineBufferedReader, Symbol paramSymbol)
  {
    if (paramSymbol == null) {}
    for (paramSymbol = null;; paramSymbol = paramSymbol.toString()) {
      return read_line.apply(paramLineBufferedReader, paramSymbol);
    }
  }
  
  public static void setInputPortLineNumber$Ex(Object paramObject1, Object paramObject2)
  {
    setPortLine$Ex(paramObject1, AddOp.$Mn.apply2(paramObject2, Lit6));
  }
  
  public static void setInputPortPrompter$Ex(TtyInPort paramTtyInPort, Procedure paramProcedure)
  {
    paramTtyInPort.setPrompter(paramProcedure);
  }
  
  /* Error */
  public static void setPortLine$Ex(Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aload_0
    //   1: checkcast 649	gnu/text/LineBufferedReader
    //   4: astore_2
    //   5: aload_1
    //   6: checkcast 496	java/lang/Number
    //   9: invokevirtual 713	java/lang/Number:intValue	()I
    //   12: istore_3
    //   13: aload_2
    //   14: iload_3
    //   15: invokevirtual 717	gnu/text/LineBufferedReader:setLineNumber	(I)V
    //   18: return
    //   19: astore_1
    //   20: new 520	gnu/mapping/WrongType
    //   23: dup
    //   24: aload_1
    //   25: ldc_w 719
    //   28: iconst_1
    //   29: aload_0
    //   30: invokespecial 525	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   33: athrow
    //   34: astore_0
    //   35: new 520	gnu/mapping/WrongType
    //   38: dup
    //   39: aload_0
    //   40: ldc_w 719
    //   43: iconst_2
    //   44: aload_1
    //   45: invokespecial 525	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   48: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	49	0	paramObject1	Object
    //   0	49	1	paramObject2	Object
    //   4	10	2	localLineBufferedReader	LineBufferedReader
    //   12	3	3	i	int
    // Exception table:
    //   from	to	target	type
    //   0	5	19	java/lang/ClassCastException
    //   5	13	34	java/lang/ClassCastException
  }
  
  public static void transcriptOff() {}
  
  public static void transcriptOn(Object paramObject)
  {
    OutPort.setLogFile(paramObject.toString());
  }
  
  public static Object withInputFromFile(Path paramPath, Procedure paramProcedure)
  {
    paramPath = InPort.openFile(paramPath);
    InPort localInPort = InPort.inDefault();
    try
    {
      InPort.setInDefault(paramPath);
      paramProcedure = paramProcedure.apply0();
      return paramProcedure;
    }
    finally
    {
      InPort.setInDefault(localInPort);
      paramPath.close();
    }
  }
  
  public static Object withOutputToFile(Path paramPath, Procedure paramProcedure)
  {
    paramPath = OutPort.openFile(paramPath);
    OutPort localOutPort = OutPort.outDefault();
    try
    {
      OutPort.setOutDefault(paramPath);
      paramProcedure = paramProcedure.apply0();
      return paramProcedure;
    }
    finally
    {
      OutPort.setOutDefault(localOutPort);
      paramPath.close();
    }
  }
  
  public static void write(Object paramObject)
  {
    write(paramObject, current$Mnoutput$Mnport.apply0());
  }
  
  public static void write(Object paramObject1, Object paramObject2)
  {
    AbstractFormat localAbstractFormat = Scheme.writeFormat;
    try
    {
      Consumer localConsumer = (Consumer)paramObject2;
      localAbstractFormat.format(paramObject1, localConsumer);
      return;
    }
    catch (ClassCastException paramObject1)
    {
      throw new WrongType((ClassCastException)paramObject1, "gnu.lists.AbstractFormat.format(java.lang.Object,gnu.lists.Consumer)", 3, paramObject2);
    }
  }
  
  public static void writeChar(Object paramObject)
  {
    writeChar(paramObject, OutPort.outDefault());
  }
  
  public static void writeChar(Object paramObject, OutPort paramOutPort)
  {
    try
    {
      Char localChar = (Char)paramObject;
      Char.print(characters.char$To$Integer(localChar), paramOutPort);
      return;
    }
    catch (ClassCastException paramOutPort)
    {
      throw new WrongType(paramOutPort, "char->integer", 1, paramObject);
    }
  }
  
  public Object apply0(ModuleMethod paramModuleMethod)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply0(paramModuleMethod);
    case 15: 
      return openOutputString();
    case 19: 
      forceOutput();
      return Values.empty;
    case 21: 
      newline();
      return Values.empty;
    case 24: 
      if (isCharReady()) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    case 42: 
      return read();
    case 44: 
      return readLine();
    }
    transcriptOff();
    return Values.empty;
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 13: 
    case 15: 
    case 17: 
    case 20: 
    case 22: 
    case 25: 
    case 27: 
    case 29: 
    case 31: 
    case 33: 
    case 38: 
    case 43: 
    case 45: 
    case 46: 
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    }
    try
    {
      paramModuleMethod = Path.valueOf(paramObject);
      return openInputFile(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "open-input-file", 1, paramObject);
    }
    try
    {
      paramModuleMethod = Path.valueOf(paramObject);
      return openOutputFile(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "open-output-file", 1, paramObject);
    }
    if (isInputPort(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    if (isOutputPort(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    return lambda1(paramObject);
    return lambda2(paramObject);
    return lambda3(paramObject);
    writeChar(paramObject);
    return Values.empty;
    try
    {
      paramModuleMethod = (CharSequence)paramObject;
      return openInputString(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "open-input-string", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (CharArrayOutPort)paramObject;
      return getOutputString(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "get-output-string", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (Procedure)paramObject;
      return callWithOutputString(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "call-with-output-string", 1, paramObject);
    }
    forceOutput(paramObject);
    return Values.empty;
    newline(paramObject);
    return Values.empty;
    if (isEofObject(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    if (isCharReady(paramObject)) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
    write(paramObject);
    return Values.empty;
    display(paramObject);
    return Values.empty;
    return Char.make(inputPortReadState(paramObject));
    try
    {
      paramModuleMethod = (LineBufferedReader)paramObject;
      return Integer.valueOf(portLine(paramModuleMethod));
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "port-line", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (LineBufferedReader)paramObject;
      return inputPortLineNumber(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "input-port-line-number", 1, paramObject);
    }
    return Integer.valueOf(portColumn(paramObject));
    return Integer.valueOf(inputPortColumnNumber(paramObject));
    return defaultPrompter(paramObject);
    try
    {
      paramModuleMethod = (TtyInPort)paramObject;
      return inputPortPrompter(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "input-port-prompter", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (InPort)paramObject;
      return closeInputPort(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "close-input-port", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (OutPort)paramObject;
      return closeOutputPort(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "close-output-port", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (InPort)paramObject;
      return read(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "read", 1, paramObject);
    }
    try
    {
      paramModuleMethod = (LineBufferedReader)paramObject;
      return readLine(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "read-line", 1, paramObject);
    }
    transcriptOn(paramObject);
    return Values.empty;
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply2(paramModuleMethod, paramObject1, paramObject2);
    }
    try
    {
      paramModuleMethod = Path.valueOf(paramObject1);
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (TtyInPort)paramObject1;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "set-input-port-prompter!", 1, paramObject1);
      }
      try
      {
        paramObject1 = (Procedure)paramObject2;
        setInputPortPrompter$Ex(paramModuleMethod, (Procedure)paramObject1);
        return Values.empty;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "set-input-port-prompter!", 2, paramObject2);
      }
      try
      {
        paramModuleMethod = (LineBufferedReader)paramObject1;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "read-line", 1, paramObject1);
      }
      try
      {
        paramObject1 = (Symbol)paramObject2;
        return readLine(paramModuleMethod, (Symbol)paramObject1);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "read-line", 2, paramObject2);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "call-with-input-file", 1, paramObject1);
    }
    try
    {
      paramObject1 = (Procedure)paramObject2;
      return callWithInputFile(paramModuleMethod, (Procedure)paramObject1);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "call-with-input-file", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = Path.valueOf(paramObject1);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "call-with-output-file", 1, paramObject1);
    }
    try
    {
      paramObject1 = (Procedure)paramObject2;
      return callWithOutputFile(paramModuleMethod, (Procedure)paramObject1);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "call-with-output-file", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = Path.valueOf(paramObject1);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "with-input-from-file", 1, paramObject1);
    }
    try
    {
      paramObject1 = (Procedure)paramObject2;
      return withInputFromFile(paramModuleMethod, (Procedure)paramObject1);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "with-input-from-file", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = Path.valueOf(paramObject1);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "with-output-to-file", 1, paramObject1);
    }
    try
    {
      paramObject1 = (Procedure)paramObject2;
      return withOutputToFile(paramModuleMethod, (Procedure)paramObject1);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "with-output-to-file", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = (OutPort)paramObject2;
      writeChar(paramObject1, paramModuleMethod);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "write-char", 2, paramObject2);
    }
    try
    {
      paramModuleMethod = (CharSequence)paramObject1;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "call-with-input-string", 1, paramObject1);
    }
    try
    {
      paramObject1 = (Procedure)paramObject2;
      return callWithInputString(paramModuleMethod, (Procedure)paramObject1);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "call-with-input-string", 2, paramObject2);
    }
    write(paramObject1, paramObject2);
    return Values.empty;
    display(paramObject1, paramObject2);
    return Values.empty;
    setPortLine$Ex(paramObject1, paramObject2);
    return Values.empty;
    setInputPortLineNumber$Ex(paramObject1, paramObject2);
    return Values.empty;
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match0(paramModuleMethod, paramCallContext);
    case 48: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 44: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 42: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 24: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 21: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 19: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    }
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 0;
    return 0;
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 13: 
    case 15: 
    case 17: 
    case 20: 
    case 22: 
    case 25: 
    case 27: 
    case 29: 
    case 31: 
    case 33: 
    case 38: 
    case 43: 
    case 45: 
    case 46: 
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 47: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 44: 
      if (!(paramObject instanceof LineBufferedReader)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 42: 
      if (!(paramObject instanceof InPort)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 41: 
      if (!(paramObject instanceof OutPort)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 40: 
      if (!(paramObject instanceof InPort)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 39: 
      if (!(paramObject instanceof TtyInPort)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 37: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 36: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 35: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 34: 
      if (!(paramObject instanceof LineBufferedReader)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 32: 
      if (!(paramObject instanceof LineBufferedReader)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 30: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 28: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 26: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 24: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 23: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 21: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 19: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 18: 
      if (!(paramObject instanceof Procedure)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 16: 
      if (!(paramObject instanceof CharArrayOutPort)) {
        return -786431;
      }
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 14: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 12: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 11: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 10: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 9: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 8: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 7: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 2: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    }
    if (Path.coerceToPathOrNull(paramObject) != null)
    {
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    }
    return -786431;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    default: 
      i = super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  return i;
                } while (!(paramObject1 instanceof LineBufferedReader));
                paramCallContext.value1 = paramObject1;
                if (!(paramObject2 instanceof Symbol)) {
                  return -786430;
                }
                paramCallContext.value2 = paramObject2;
                paramCallContext.proc = paramModuleMethod;
                paramCallContext.pc = 2;
                return 0;
              } while (!(paramObject1 instanceof TtyInPort));
              paramCallContext.value1 = paramObject1;
              if (!(paramObject2 instanceof Procedure)) {
                return -786430;
              }
              paramCallContext.value2 = paramObject2;
              paramCallContext.proc = paramModuleMethod;
              paramCallContext.pc = 2;
              return 0;
              paramCallContext.value1 = paramObject1;
              paramCallContext.value2 = paramObject2;
              paramCallContext.proc = paramModuleMethod;
              paramCallContext.pc = 2;
              return 0;
              paramCallContext.value1 = paramObject1;
              paramCallContext.value2 = paramObject2;
              paramCallContext.proc = paramModuleMethod;
              paramCallContext.pc = 2;
              return 0;
              paramCallContext.value1 = paramObject1;
              paramCallContext.value2 = paramObject2;
              paramCallContext.proc = paramModuleMethod;
              paramCallContext.pc = 2;
              return 0;
              paramCallContext.value1 = paramObject1;
              paramCallContext.value2 = paramObject2;
              paramCallContext.proc = paramModuleMethod;
              paramCallContext.pc = 2;
              return 0;
            } while (!(paramObject1 instanceof CharSequence));
            paramCallContext.value1 = paramObject1;
            if (!(paramObject2 instanceof Procedure)) {
              return -786430;
            }
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
            paramCallContext.value1 = paramObject1;
            if (!(paramObject2 instanceof OutPort)) {
              return -786430;
            }
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } while (Path.coerceToPathOrNull(paramObject1) == null);
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Procedure)) {
            return -786430;
          }
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } while (Path.coerceToPathOrNull(paramObject1) == null);
        paramCallContext.value1 = paramObject1;
        if (!(paramObject2 instanceof Procedure)) {
          return -786430;
        }
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      } while (Path.coerceToPathOrNull(paramObject1) == null);
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Procedure)) {
        return -786430;
      }
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } while (Path.coerceToPathOrNull(paramObject1) == null);
    paramCallContext.value1 = paramObject1;
    if (!(paramObject2 instanceof Procedure)) {
      return -786430;
    }
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
    current$Mninput$Mnport = LocationProc.makeNamed(Lit0, InPort.inLocation);
    current$Mninput$Mnport.pushConverter(lambda$Fn1);
    current$Mnoutput$Mnport = LocationProc.makeNamed(Lit2, OutPort.outLocation);
    current$Mnoutput$Mnport.pushConverter(lambda$Fn2);
    current$Mnerror$Mnport = LocationProc.makeNamed(Lit4, OutPort.errLocation);
    current$Mnerror$Mnport.pushConverter(lambda$Fn3);
    port$Mnline = new GenericProc("port-line");
    paramCallContext = port$Mnline;
    Keyword localKeyword = Lit5;
    ModuleMethod localModuleMethod1 = set$Mnport$Mnline$Ex;
    ModuleMethod localModuleMethod2 = port$Mnline$Fn4;
    paramCallContext.setProperties(new Object[] { localKeyword, localModuleMethod1, port$Mnline$Fn4 });
    input$Mnport$Mnline$Mnnumber = new GenericProc("input-port-line-number");
    paramCallContext = input$Mnport$Mnline$Mnnumber;
    localKeyword = Lit5;
    localModuleMethod1 = set$Mninput$Mnport$Mnline$Mnnumber$Ex;
    localModuleMethod2 = input$Mnport$Mnline$Mnnumber$Fn5;
    paramCallContext.setProperties(new Object[] { localKeyword, localModuleMethod1, input$Mnport$Mnline$Mnnumber$Fn5 });
    input$Mnport$Mnprompter = new GenericProc("input-port-prompter");
    paramCallContext = input$Mnport$Mnprompter;
    localKeyword = Lit5;
    localModuleMethod1 = set$Mninput$Mnport$Mnprompter$Ex;
    localModuleMethod2 = input$Mnport$Mnprompter$Fn6;
    paramCallContext.setProperties(new Object[] { localKeyword, localModuleMethod1, input$Mnport$Mnprompter$Fn6 });
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\ports.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */