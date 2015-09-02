package kawa.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Macro
  extends Syntax
  implements Printable, Externalizable
{
  private ScopeExp capturedScope;
  public Object expander;
  private boolean hygienic = true;
  Object instance;
  
  public Macro() {}
  
  public Macro(Object paramObject)
  {
    super(paramObject);
  }
  
  public Macro(Object paramObject, Procedure paramProcedure)
  {
    super(paramObject);
    this.expander = new QuoteExp(paramProcedure);
  }
  
  public Macro(Macro paramMacro)
  {
    this.name = paramMacro.name;
    this.expander = paramMacro.expander;
    this.hygienic = paramMacro.hygienic;
  }
  
  public static Macro make(Declaration paramDeclaration)
  {
    Macro localMacro = new Macro(paramDeclaration.getSymbol());
    paramDeclaration.setSyntax();
    localMacro.capturedScope = paramDeclaration.context;
    return localMacro;
  }
  
  public static Macro make(Object paramObject, Procedure paramProcedure)
  {
    return new Macro(paramObject, paramProcedure);
  }
  
  public static Macro make(Object paramObject1, Procedure paramProcedure, Object paramObject2)
  {
    paramObject1 = new Macro(paramObject1, paramProcedure);
    ((Macro)paramObject1).instance = paramObject2;
    return (Macro)paramObject1;
  }
  
  public static Macro makeNonHygienic(Object paramObject, Procedure paramProcedure)
  {
    paramObject = new Macro(paramObject, paramProcedure);
    ((Macro)paramObject).hygienic = false;
    return (Macro)paramObject;
  }
  
  public static Macro makeNonHygienic(Object paramObject1, Procedure paramProcedure, Object paramObject2)
  {
    paramObject1 = new Macro(paramObject1, paramProcedure);
    ((Macro)paramObject1).hygienic = false;
    ((Macro)paramObject1).instance = paramObject2;
    return (Macro)paramObject1;
  }
  
  /* Error */
  public Object expand(Object paramObject, Translator paramTranslator)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 31	kawa/lang/Macro:expander	Ljava/lang/Object;
    //   4: astore 4
    //   6: aload 4
    //   8: instanceof 65
    //   11: ifeq +65 -> 76
    //   14: aload 4
    //   16: instanceof 67
    //   19: ifne +57 -> 76
    //   22: aload 4
    //   24: checkcast 65	gnu/mapping/Procedure
    //   27: astore_3
    //   28: aload_0
    //   29: getfield 21	kawa/lang/Macro:hygienic	Z
    //   32: ifne +255 -> 287
    //   35: aload_1
    //   36: aload_2
    //   37: invokestatic 72	kawa/lang/Quote:quote	(Ljava/lang/Object;Lkawa/lang/Translator;)Ljava/lang/Object;
    //   40: astore_1
    //   41: aload_1
    //   42: invokestatic 78	kawa/lang/Translator:listLength	(Ljava/lang/Object;)I
    //   45: istore 7
    //   47: iload 7
    //   49: ifgt +131 -> 180
    //   52: aload_2
    //   53: new 80	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   60: ldc 83
    //   62: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: aload_0
    //   66: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   69: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   72: invokevirtual 98	kawa/lang/Translator:syntaxError	(Ljava/lang/String;)Lgnu/expr/Expression;
    //   75: areturn
    //   76: aload 4
    //   78: astore_3
    //   79: aload 4
    //   81: instanceof 67
    //   84: ifne +32 -> 116
    //   87: aload_2
    //   88: getfield 102	kawa/lang/Translator:currentMacroDefinition	Lkawa/lang/Macro;
    //   91: astore 5
    //   93: aload_2
    //   94: aload_0
    //   95: putfield 102	kawa/lang/Translator:currentMacroDefinition	Lkawa/lang/Macro;
    //   98: aload_2
    //   99: aload 4
    //   101: invokevirtual 106	kawa/lang/Translator:rewrite	(Ljava/lang/Object;)Lgnu/expr/Expression;
    //   104: astore_3
    //   105: aload_0
    //   106: aload_3
    //   107: putfield 31	kawa/lang/Macro:expander	Ljava/lang/Object;
    //   110: aload_2
    //   111: aload 5
    //   113: putfield 102	kawa/lang/Translator:currentMacroDefinition	Lkawa/lang/Macro;
    //   116: aload_3
    //   117: checkcast 67	gnu/expr/Expression
    //   120: aload_2
    //   121: invokevirtual 110	kawa/lang/Translator:getGlobalEnvironment	()Lgnu/mapping/Environment;
    //   124: invokevirtual 114	gnu/expr/Expression:eval	(Lgnu/mapping/Environment;)Ljava/lang/Object;
    //   127: checkcast 65	gnu/mapping/Procedure
    //   130: astore_3
    //   131: goto -103 -> 28
    //   134: astore_1
    //   135: aload_2
    //   136: aload 5
    //   138: putfield 102	kawa/lang/Translator:currentMacroDefinition	Lkawa/lang/Macro;
    //   141: aload_1
    //   142: athrow
    //   143: astore_1
    //   144: aload_2
    //   145: new 80	java/lang/StringBuilder
    //   148: dup
    //   149: invokespecial 81	java/lang/StringBuilder:<init>	()V
    //   152: ldc 116
    //   154: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: aload_0
    //   158: invokevirtual 119	kawa/lang/Macro:getName	()Ljava/lang/String;
    //   161: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: ldc 121
    //   166: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: aload_1
    //   170: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   173: invokevirtual 94	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   176: invokevirtual 98	kawa/lang/Translator:syntaxError	(Ljava/lang/String;)Lgnu/expr/Expression;
    //   179: areturn
    //   180: iload 7
    //   182: iconst_1
    //   183: isub
    //   184: anewarray 123	java/lang/Object
    //   187: astore 4
    //   189: iconst_0
    //   190: istore 6
    //   192: iload 6
    //   194: iload 7
    //   196: if_icmpge +38 -> 234
    //   199: aload_1
    //   200: checkcast 125	gnu/lists/Pair
    //   203: astore_1
    //   204: iload 6
    //   206: ifle +14 -> 220
    //   209: aload 4
    //   211: iload 6
    //   213: iconst_1
    //   214: isub
    //   215: aload_1
    //   216: invokevirtual 128	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   219: aastore
    //   220: aload_1
    //   221: invokevirtual 131	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   224: astore_1
    //   225: iload 6
    //   227: iconst_1
    //   228: iadd
    //   229: istore 6
    //   231: goto -39 -> 192
    //   234: aload_3
    //   235: aload 4
    //   237: invokevirtual 135	gnu/mapping/Procedure:applyN	([Ljava/lang/Object;)Ljava/lang/Object;
    //   240: astore_3
    //   241: aload_1
    //   242: instanceof 137
    //   245: ifeq +51 -> 296
    //   248: aload_3
    //   249: instanceof 125
    //   252: ifeq +44 -> 296
    //   255: aload_3
    //   256: instanceof 137
    //   259: ifne +37 -> 296
    //   262: aload_3
    //   263: checkcast 125	gnu/lists/Pair
    //   266: astore_3
    //   267: new 137	gnu/lists/PairWithPosition
    //   270: dup
    //   271: aload_1
    //   272: checkcast 137	gnu/lists/PairWithPosition
    //   275: aload_3
    //   276: invokevirtual 128	gnu/lists/Pair:getCar	()Ljava/lang/Object;
    //   279: aload_3
    //   280: invokevirtual 131	gnu/lists/Pair:getCdr	()Ljava/lang/Object;
    //   283: invokespecial 140	gnu/lists/PairWithPosition:<init>	(Lgnu/text/SourceLocator;Ljava/lang/Object;Ljava/lang/Object;)V
    //   286: areturn
    //   287: aload_3
    //   288: aload_1
    //   289: invokevirtual 144	gnu/mapping/Procedure:apply1	(Ljava/lang/Object;)Ljava/lang/Object;
    //   292: astore_3
    //   293: goto -52 -> 241
    //   296: aload_3
    //   297: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	298	0	this	Macro
    //   0	298	1	paramObject	Object
    //   0	298	2	paramTranslator	Translator
    //   27	270	3	localObject1	Object
    //   4	232	4	localObject2	Object
    //   91	46	5	localMacro	Macro
    //   190	40	6	i	int
    //   45	152	7	j	int
    // Exception table:
    //   from	to	target	type
    //   98	110	134	finally
    //   0	28	143	java/lang/Throwable
    //   28	47	143	java/lang/Throwable
    //   52	76	143	java/lang/Throwable
    //   79	98	143	java/lang/Throwable
    //   110	116	143	java/lang/Throwable
    //   116	131	143	java/lang/Throwable
    //   135	143	143	java/lang/Throwable
    //   180	189	143	java/lang/Throwable
    //   199	204	143	java/lang/Throwable
    //   209	220	143	java/lang/Throwable
    //   220	225	143	java/lang/Throwable
    //   234	241	143	java/lang/Throwable
    //   241	287	143	java/lang/Throwable
    //   287	293	143	java/lang/Throwable
  }
  
  public ScopeExp getCapturedScope()
  {
    if (this.capturedScope == null)
    {
      if (!(this.instance instanceof ModuleExp)) {
        break label33;
      }
      this.capturedScope = ((ModuleExp)this.instance);
    }
    for (;;)
    {
      return this.capturedScope;
      label33:
      if (this.instance != null) {
        this.capturedScope = ModuleInfo.findFromInstance(this.instance).getModuleExp();
      }
    }
  }
  
  public final boolean isHygienic()
  {
    return this.hygienic;
  }
  
  public void print(Consumer paramConsumer)
  {
    paramConsumer.write("#<macro ");
    paramConsumer.write(getName());
    paramConsumer.write(62);
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    setName((String)paramObjectInput.readObject());
    this.expander = new QuoteExp(paramObjectInput.readObject());
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    return paramTranslator.rewrite(expand(paramPair, paramTranslator));
  }
  
  public Expression rewriteForm(Object paramObject, Translator paramTranslator)
  {
    return paramTranslator.rewrite(expand(paramObject, paramTranslator));
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    String str = paramTranslator.getFileName();
    int i = paramTranslator.getLineNumber();
    int j = paramTranslator.getColumnNumber();
    Syntax localSyntax = paramTranslator.currentSyntax;
    try
    {
      paramTranslator.setLine(paramPair);
      paramTranslator.currentSyntax = this;
      paramTranslator.scanForm(expand(paramPair, paramTranslator), paramScopeExp);
      return;
    }
    finally
    {
      paramTranslator.setLine(str, i, j);
      paramTranslator.currentSyntax = localSyntax;
    }
  }
  
  public void setCapturedScope(ScopeExp paramScopeExp)
  {
    this.capturedScope = paramScopeExp;
  }
  
  public final void setHygienic(boolean paramBoolean)
  {
    this.hygienic = paramBoolean;
  }
  
  public String toString()
  {
    return "#<macro " + getName() + '>';
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(getName());
    paramObjectOutput.writeObject(((QuoteExp)this.expander).getValue());
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\Macro.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */