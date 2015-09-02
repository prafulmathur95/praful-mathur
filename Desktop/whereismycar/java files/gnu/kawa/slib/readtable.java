package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.ReadTable;
import gnu.kawa.lispexpr.ReaderDispatch;
import gnu.kawa.lispexpr.ReaderMacro;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Char;

public class readtable
  extends ModuleBody
{
  public static final readtable $instance;
  static final SimpleSymbol Lit0;
  static final SimpleSymbol Lit1;
  static final SimpleSymbol Lit2;
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6 = (SimpleSymbol)new SimpleSymbol("define-reader-ctor").readResolve();
  public static final ModuleMethod current$Mnreadtable;
  public static final ModuleMethod define$Mnreader$Mnctor;
  public static final ModuleMethod get$Mndispatch$Mnmacro$Mntable;
  public static final ModuleMethod make$Mndispatch$Mnmacro$Mncharacter;
  public static final ModuleMethod readtable$Qu;
  public static final ModuleMethod set$Mndispatch$Mnmacro$Mncharacter;
  public static final ModuleMethod set$Mnmacro$Mncharacter;
  
  static
  {
    Lit5 = (SimpleSymbol)new SimpleSymbol("get-dispatch-macro-table").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("set-dispatch-macro-character").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("make-dispatch-macro-character").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("set-macro-character").readResolve();
    Lit1 = (SimpleSymbol)new SimpleSymbol("readtable?").readResolve();
    Lit0 = (SimpleSymbol)new SimpleSymbol("current-readtable").readResolve();
    $instance = new readtable();
    readtable localreadtable = $instance;
    current$Mnreadtable = new ModuleMethod(localreadtable, 1, Lit0, 0);
    readtable$Qu = new ModuleMethod(localreadtable, 2, Lit1, 4097);
    set$Mnmacro$Mncharacter = new ModuleMethod(localreadtable, 3, Lit2, 16386);
    make$Mndispatch$Mnmacro$Mncharacter = new ModuleMethod(localreadtable, 6, Lit3, 12289);
    set$Mndispatch$Mnmacro$Mncharacter = new ModuleMethod(localreadtable, 9, Lit4, 16387);
    get$Mndispatch$Mnmacro$Mntable = new ModuleMethod(localreadtable, 11, Lit5, 12290);
    define$Mnreader$Mnctor = new ModuleMethod(localreadtable, 13, Lit6, 12290);
    $instance.run();
  }
  
  public readtable()
  {
    ModuleInfo.register(this);
  }
  
  public static ReadTable currentReadtable()
  {
    return ReadTable.getCurrent();
  }
  
  public static void defineReaderCtor(Symbol paramSymbol, Procedure paramProcedure)
  {
    defineReaderCtor(paramSymbol, paramProcedure, currentReadtable());
  }
  
  public static void defineReaderCtor(Symbol paramSymbol, Procedure paramProcedure, ReadTable paramReadTable)
  {
    if (paramSymbol == null) {}
    for (paramSymbol = null;; paramSymbol = paramSymbol.toString())
    {
      paramReadTable.putReaderCtor(paramSymbol, paramProcedure);
      return;
    }
  }
  
  public static Object getDispatchMacroTable(char paramChar1, char paramChar2)
  {
    return getDispatchMacroTable(paramChar1, paramChar2, currentReadtable());
  }
  
  public static Object getDispatchMacroTable(char paramChar1, char paramChar2, ReadTable paramReadTable)
  {
    paramReadTable = paramReadTable.lookup(paramChar1);
    try
    {
      Object localObject = (ReaderDispatch)paramReadTable;
      localObject = ((ReaderDispatch)localObject).lookup(paramChar2);
      paramReadTable = (ReadTable)localObject;
      if (localObject == null) {
        paramReadTable = Boolean.FALSE;
      }
      return paramReadTable;
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "disp-entry", -2, paramReadTable);
    }
  }
  
  public static boolean isReadtable(Object paramObject)
  {
    return paramObject instanceof ReadTable;
  }
  
  public static void makeDispatchMacroCharacter(char paramChar)
  {
    makeDispatchMacroCharacter(paramChar, false);
  }
  
  public static void makeDispatchMacroCharacter(char paramChar, boolean paramBoolean)
  {
    makeDispatchMacroCharacter(paramChar, paramBoolean, currentReadtable());
  }
  
  public static void makeDispatchMacroCharacter(char paramChar, boolean paramBoolean, ReadTable paramReadTable)
  {
    paramReadTable.set(paramChar, new ReaderDispatch(paramBoolean));
  }
  
  public static void setDispatchMacroCharacter(char paramChar1, char paramChar2, Object paramObject)
  {
    setDispatchMacroCharacter(paramChar1, paramChar2, paramObject, currentReadtable());
  }
  
  /* Error */
  public static void setDispatchMacroCharacter(char paramChar1, char paramChar2, Object paramObject, ReadTable paramReadTable)
  {
    // Byte code:
    //   0: aload_3
    //   1: iload_0
    //   2: invokevirtual 131	gnu/kawa/lispexpr/ReadTable:lookup	(I)Lgnu/kawa/lispexpr/ReadTableEntry;
    //   5: astore_3
    //   6: aload_3
    //   7: checkcast 133	gnu/kawa/lispexpr/ReaderDispatch
    //   10: astore 4
    //   12: aload_2
    //   13: checkcast 171	gnu/mapping/Procedure
    //   16: astore_3
    //   17: aload 4
    //   19: iload_1
    //   20: new 173	gnu/kawa/lispexpr/ReaderDispatchMacro
    //   23: dup
    //   24: aload_3
    //   25: invokespecial 176	gnu/kawa/lispexpr/ReaderDispatchMacro:<init>	(Lgnu/mapping/Procedure;)V
    //   28: invokevirtual 177	gnu/kawa/lispexpr/ReaderDispatch:set	(ILjava/lang/Object;)V
    //   31: return
    //   32: astore_2
    //   33: new 142	gnu/mapping/WrongType
    //   36: dup
    //   37: aload_2
    //   38: ldc -77
    //   40: bipush -2
    //   42: aload_3
    //   43: invokespecial 147	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   46: athrow
    //   47: astore_3
    //   48: new 142	gnu/mapping/WrongType
    //   51: dup
    //   52: aload_3
    //   53: ldc -75
    //   55: iconst_1
    //   56: aload_2
    //   57: invokespecial 147	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   60: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	61	0	paramChar1	char
    //   0	61	1	paramChar2	char
    //   0	61	2	paramObject	Object
    //   0	61	3	paramReadTable	ReadTable
    //   10	8	4	localReaderDispatch	ReaderDispatch
    // Exception table:
    //   from	to	target	type
    //   6	12	32	java/lang/ClassCastException
    //   12	17	47	java/lang/ClassCastException
  }
  
  public static void setMacroCharacter(char paramChar, Object paramObject)
  {
    setMacroCharacter(paramChar, paramObject, false);
  }
  
  public static void setMacroCharacter(char paramChar, Object paramObject, boolean paramBoolean)
  {
    setMacroCharacter(paramChar, paramObject, paramBoolean, currentReadtable());
  }
  
  public static void setMacroCharacter(char paramChar, Object paramObject, boolean paramBoolean, ReadTable paramReadTable)
  {
    try
    {
      Procedure localProcedure = (Procedure)paramObject;
      paramReadTable.set(paramChar, new ReaderMacro(localProcedure, paramBoolean));
      return;
    }
    catch (ClassCastException paramReadTable)
    {
      throw new WrongType(paramReadTable, "gnu.kawa.lispexpr.ReaderMacro.<init>(gnu.mapping.Procedure,boolean)", 1, paramObject);
    }
  }
  
  public Object apply0(ModuleMethod paramModuleMethod)
  {
    if (paramModuleMethod.selector == 1) {
      return currentReadtable();
    }
    return super.apply0(paramModuleMethod);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 2: 
      if (isReadtable(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    try
    {
      char c = ((Char)paramObject).charValue();
      makeDispatchMacroCharacter(c);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "make-dispatch-macro-character", 1, paramObject);
    }
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
      c1 = ((Char)paramObject1).charValue();
      setMacroCharacter(c1, paramObject2);
      return Values.empty;
    }
    catch (ClassCastException paramModuleMethod)
    {
      for (;;)
      {
        try
        {
          c1 = ((Char)paramObject1).charValue();
        }
        catch (ClassCastException paramModuleMethod)
        {
          char c1;
          boolean bool;
          char c2;
          throw new WrongType(paramModuleMethod, "make-dispatch-macro-character", 1, paramObject1);
        }
        try
        {
          paramModuleMethod = Boolean.FALSE;
          if (paramObject2 != paramModuleMethod)
          {
            bool = true;
            makeDispatchMacroCharacter(c1, bool);
            return Values.empty;
          }
          bool = false;
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "make-dispatch-macro-character", 2, paramObject2);
        }
      }
      try
      {
        c1 = ((Char)paramObject1).charValue();
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "get-dispatch-macro-table", 1, paramObject1);
      }
      try
      {
        c2 = ((Char)paramObject2).charValue();
        return getDispatchMacroTable(c1, c2);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "get-dispatch-macro-table", 2, paramObject2);
      }
      try
      {
        paramModuleMethod = (Symbol)paramObject1;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "define-reader-ctor", 1, paramObject1);
      }
      try
      {
        paramObject1 = (Procedure)paramObject2;
        defineReaderCtor(paramModuleMethod, (Procedure)paramObject1);
        return Values.empty;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "define-reader-ctor", 2, paramObject2);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "set-macro-character", 1, paramObject1);
    }
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    boolean bool = true;
    switch (paramModuleMethod.selector)
    {
    case 4: 
    case 5: 
    case 7: 
    case 8: 
    case 10: 
    case 12: 
    default: 
      return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
    case 3: 
    case 6: 
      for (;;)
      {
        try
        {
          c1 = ((Char)paramObject1).charValue();
        }
        catch (ClassCastException paramModuleMethod)
        {
          try
          {
            c1 = ((Char)paramObject1).charValue();
          }
          catch (ClassCastException paramModuleMethod)
          {
            char c1;
            char c2;
            throw new WrongType(paramModuleMethod, "set-dispatch-macro-character", 1, paramObject1);
          }
          try
          {
            c2 = ((Char)paramObject2).charValue();
            setDispatchMacroCharacter(c1, c2, paramObject3);
            return Values.empty;
          }
          catch (ClassCastException paramModuleMethod)
          {
            throw new WrongType(paramModuleMethod, "set-dispatch-macro-character", 2, paramObject2);
          }
          try
          {
            c1 = ((Char)paramObject1).charValue();
          }
          catch (ClassCastException paramModuleMethod)
          {
            throw new WrongType(paramModuleMethod, "get-dispatch-macro-table", 1, paramObject1);
          }
          try
          {
            c2 = ((Char)paramObject2).charValue();
          }
          catch (ClassCastException paramModuleMethod)
          {
            throw new WrongType(paramModuleMethod, "get-dispatch-macro-table", 2, paramObject2);
          }
          try
          {
            paramModuleMethod = (ReadTable)paramObject3;
            return getDispatchMacroTable(c1, c2, paramModuleMethod);
          }
          catch (ClassCastException paramModuleMethod)
          {
            throw new WrongType(paramModuleMethod, "get-dispatch-macro-table", 3, paramObject3);
          }
          try
          {
            paramModuleMethod = (Symbol)paramObject1;
          }
          catch (ClassCastException paramModuleMethod)
          {
            throw new WrongType(paramModuleMethod, "define-reader-ctor", 1, paramObject1);
          }
          try
          {
            paramObject1 = (Procedure)paramObject2;
          }
          catch (ClassCastException paramModuleMethod)
          {
            throw new WrongType(paramModuleMethod, "define-reader-ctor", 2, paramObject2);
          }
          try
          {
            paramObject2 = (ReadTable)paramObject3;
            defineReaderCtor(paramModuleMethod, (Procedure)paramObject1, (ReadTable)paramObject2);
            return Values.empty;
          }
          catch (ClassCastException paramModuleMethod)
          {
            throw new WrongType(paramModuleMethod, "define-reader-ctor", 3, paramObject3);
          }
          paramModuleMethod = paramModuleMethod;
          throw new WrongType(paramModuleMethod, "set-macro-character", 1, paramObject1);
        }
        try
        {
          paramModuleMethod = Boolean.FALSE;
          if (paramObject3 != paramModuleMethod)
          {
            bool = true;
            setMacroCharacter(c1, paramObject2, bool);
            return Values.empty;
          }
          bool = false;
          continue;
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "set-macro-character", 3, paramObject3);
        }
        try
        {
          c1 = ((Char)paramObject1).charValue();
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "make-dispatch-macro-character", 1, paramObject1);
        }
        try
        {
          paramModuleMethod = Boolean.FALSE;
          if (paramObject2 == paramModuleMethod) {}
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "make-dispatch-macro-character", 2, paramObject2);
        }
        try
        {
          paramModuleMethod = (ReadTable)paramObject3;
          makeDispatchMacroCharacter(c1, bool, paramModuleMethod);
          return Values.empty;
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "make-dispatch-macro-character", 3, paramObject3);
        }
        bool = false;
      }
    }
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
    case 3: 
      for (;;)
      {
        try
        {
          c1 = ((Char)paramObject1).charValue();
        }
        catch (ClassCastException paramModuleMethod)
        {
          try
          {
            boolean bool;
            c1 = ((Char)paramObject1).charValue();
          }
          catch (ClassCastException paramModuleMethod)
          {
            char c1;
            char c2;
            throw new WrongType(paramModuleMethod, "set-dispatch-macro-character", 1, paramObject1);
          }
          try
          {
            c2 = ((Char)paramObject2).charValue();
          }
          catch (ClassCastException paramModuleMethod)
          {
            throw new WrongType(paramModuleMethod, "set-dispatch-macro-character", 2, paramObject2);
          }
          try
          {
            paramModuleMethod = (ReadTable)paramObject4;
            setDispatchMacroCharacter(c1, c2, paramObject3, paramModuleMethod);
            return Values.empty;
          }
          catch (ClassCastException paramModuleMethod)
          {
            throw new WrongType(paramModuleMethod, "set-dispatch-macro-character", 4, paramObject4);
          }
          paramModuleMethod = paramModuleMethod;
          throw new WrongType(paramModuleMethod, "set-macro-character", 1, paramObject1);
        }
        try
        {
          paramModuleMethod = Boolean.FALSE;
          if (paramObject3 != paramModuleMethod) {
            bool = true;
          }
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "set-macro-character", 3, paramObject3);
        }
        try
        {
          paramModuleMethod = (ReadTable)paramObject4;
          setMacroCharacter(c1, paramObject2, bool, paramModuleMethod);
          return Values.empty;
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "set-macro-character", 4, paramObject4);
        }
        bool = false;
      }
    }
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
  {
    if (paramModuleMethod.selector == 1)
    {
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    }
    return super.match0(paramModuleMethod, paramCallContext);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 6: 
      if ((paramObject instanceof Char))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    }
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
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
            return i;
          } while (!(paramObject1 instanceof Symbol));
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Procedure)) {
            return -786430;
          }
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } while (!(paramObject1 instanceof Char));
        paramCallContext.value1 = paramObject1;
        if ((paramObject2 instanceof Char))
        {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        }
        return -786430;
      } while (!(paramObject1 instanceof Char));
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } while (!(paramObject1 instanceof Char));
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext)
  {
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    case 4: 
    case 5: 
    case 7: 
    case 8: 
    case 10: 
    case 12: 
    default: 
      i = super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
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
              return i;
            } while (!(paramObject1 instanceof Symbol));
            paramCallContext.value1 = paramObject1;
            if (!(paramObject2 instanceof Procedure)) {
              return -786430;
            }
            paramCallContext.value2 = paramObject2;
            if (!(paramObject3 instanceof ReadTable)) {
              return -786429;
            }
            paramCallContext.value3 = paramObject3;
            paramCallContext.proc = paramModuleMethod;
            paramCallContext.pc = 3;
            return 0;
          } while (!(paramObject1 instanceof Char));
          paramCallContext.value1 = paramObject1;
          if ((paramObject2 instanceof Char))
          {
            paramCallContext.value2 = paramObject2;
            if (!(paramObject3 instanceof ReadTable)) {
              return -786429;
            }
          }
          else
          {
            return -786430;
          }
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } while (!(paramObject1 instanceof Char));
        paramCallContext.value1 = paramObject1;
        if ((paramObject2 instanceof Char))
        {
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        }
        return -786430;
      } while (!(paramObject1 instanceof Char));
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      if (!(paramObject3 instanceof ReadTable)) {
        return -786429;
      }
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    } while (!(paramObject1 instanceof Char));
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.value3 = paramObject3;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 3;
    return 0;
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext)
  {
    int i = -786428;
    switch (paramModuleMethod.selector)
    {
    default: 
      i = super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
    }
    label120:
    do
    {
      do
      {
        return i;
        if (!(paramObject1 instanceof Char)) {
          break;
        }
        paramCallContext.value1 = paramObject1;
        if (!(paramObject2 instanceof Char)) {
          break label120;
        }
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
      } while (!(paramObject4 instanceof ReadTable));
      paramCallContext.value4 = paramObject4;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 4;
      return 0;
      return -786431;
      return -786430;
      if (!(paramObject1 instanceof Char)) {
        break;
      }
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
    } while (!(paramObject4 instanceof ReadTable));
    paramCallContext.value4 = paramObject4;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 4;
    return 0;
    return -786431;
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\slib\readtable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */