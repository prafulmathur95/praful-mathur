package gnu.kawa.lispexpr;

import gnu.bytecode.Type;
import gnu.expr.Language;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.util.RangeTable;
import gnu.mapping.Environment;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.ThreadLocation;

public class ReadTable
  extends RangeTable
{
  public static final int CONSTITUENT = 2;
  public static final int ILLEGAL = 0;
  public static final int MULTIPLE_ESCAPE = 4;
  public static final int NON_TERMINATING_MACRO = 6;
  public static final int SINGLE_ESCAPE = 3;
  public static final int TERMINATING_MACRO = 5;
  public static final int WHITESPACE = 1;
  static final ThreadLocation current = new ThreadLocation("read-table");
  public static int defaultBracketMode = -1;
  Environment ctorTable = null;
  protected boolean finalColonIsKeyword;
  protected boolean hexEscapeAfterBackslash = true;
  protected boolean initialColonIsKeyword;
  public char postfixLookupOperator = 65535;
  
  public static ReadTable createInitial()
  {
    ReadTable localReadTable = new ReadTable();
    localReadTable.initialize();
    return localReadTable;
  }
  
  public static ReadTable getCurrent()
  {
    ReadTable localReadTable = (ReadTable)current.get(null);
    Object localObject = localReadTable;
    if (localReadTable == null)
    {
      localObject = Language.getDefaultLanguage();
      if (!(localObject instanceof LispLanguage)) {
        break label45;
      }
    }
    label45:
    for (localObject = ((LispLanguage)localObject).defaultReadTable;; localObject = createInitial())
    {
      current.set(localObject);
      return (ReadTable)localObject;
    }
  }
  
  public static void setCurrent(ReadTable paramReadTable)
  {
    current.set(paramReadTable);
  }
  
  public Object getReaderCtor(String paramString)
  {
    try
    {
      initCtorTable();
      paramString = this.ctorTable.get(paramString, null);
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  void initCtorTable()
  {
    if (this.ctorTable == null) {
      this.ctorTable = Environment.make();
    }
  }
  
  public void initialize()
  {
    ReadTableEntry localReadTableEntry = ReadTableEntry.getWhitespaceInstance();
    set(32, localReadTableEntry);
    set(9, localReadTableEntry);
    set(10, localReadTableEntry);
    set(13, localReadTableEntry);
    set(12, localReadTableEntry);
    set(124, ReadTableEntry.getMultipleEscapeInstance());
    set(92, ReadTableEntry.getSingleEscapeInstance());
    set(48, 57, ReadTableEntry.getDigitInstance());
    localReadTableEntry = ReadTableEntry.getConstituentInstance();
    set(97, 122, localReadTableEntry);
    set(65, 90, localReadTableEntry);
    set(33, localReadTableEntry);
    set(36, localReadTableEntry);
    set(37, localReadTableEntry);
    set(38, localReadTableEntry);
    set(42, localReadTableEntry);
    set(43, localReadTableEntry);
    set(45, localReadTableEntry);
    set(46, localReadTableEntry);
    set(47, localReadTableEntry);
    set(61, localReadTableEntry);
    set(62, localReadTableEntry);
    set(63, localReadTableEntry);
    set(64, localReadTableEntry);
    set(94, localReadTableEntry);
    set(95, localReadTableEntry);
    set(123, ReadTableEntry.brace);
    set(126, localReadTableEntry);
    set(127, localReadTableEntry);
    set(8, localReadTableEntry);
    set(58, new ReaderColon());
    set(34, new ReaderString());
    set(35, ReaderDispatch.create(this));
    set(59, ReaderIgnoreRestOfLine.getInstance());
    set(40, ReaderParens.getInstance('(', ')'));
    set(39, new ReaderQuote(makeSymbol("quote")));
    set(96, new ReaderQuote(makeSymbol("quasiquote")));
    set(44, new ReaderQuote(makeSymbol("unquote"), '@', makeSymbol("unquote-splicing")));
    setBracketMode();
  }
  
  public ReadTableEntry lookup(int paramInt)
  {
    Object localObject2 = (ReadTableEntry)lookup(paramInt, null);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = localObject2;
      if (paramInt >= 0)
      {
        localObject1 = localObject2;
        if (paramInt < 65536)
        {
          if (!Character.isDigit((char)paramInt)) {
            break label80;
          }
          localObject1 = (ReadTableEntry)lookup(48, null);
        }
      }
    }
    for (;;)
    {
      localObject2 = localObject1;
      if (localObject1 == null)
      {
        localObject2 = localObject1;
        if (paramInt >= 128) {
          localObject2 = ReadTableEntry.getConstituentInstance();
        }
      }
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = ReadTableEntry.getIllegalInstance();
      }
      return (ReadTableEntry)localObject1;
      label80:
      if (Character.isLowerCase((char)paramInt))
      {
        localObject1 = (ReadTableEntry)lookup(97, null);
      }
      else if (Character.isLetter((char)paramInt))
      {
        localObject1 = (ReadTableEntry)lookup(65, null);
      }
      else
      {
        localObject1 = localObject2;
        if (Character.isWhitespace((char)paramInt)) {
          localObject1 = (ReadTableEntry)lookup(32, null);
        }
      }
    }
  }
  
  protected Object makeSymbol(String paramString)
  {
    return Namespace.EmptyNamespace.getSymbol(paramString.intern());
  }
  
  public void putReaderCtor(String paramString, Type paramType)
  {
    try
    {
      initCtorTable();
      this.ctorTable.put(paramString, paramType);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void putReaderCtor(String paramString, Procedure paramProcedure)
  {
    try
    {
      initCtorTable();
      this.ctorTable.put(paramString, paramProcedure);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void putReaderCtorFld(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      initCtorTable();
      paramString1 = this.ctorTable.getSymbol(paramString1);
      StaticFieldLocation.define(this.ctorTable, paramString1, null, paramString2, paramString3);
      return;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  public void setBracketMode()
  {
    setBracketMode(defaultBracketMode);
  }
  
  public void setBracketMode(int paramInt)
  {
    if (paramInt <= 0)
    {
      ReadTableEntry localReadTableEntry = ReadTableEntry.getConstituentInstance();
      set(60, localReadTableEntry);
      if (paramInt < 0)
      {
        set(91, localReadTableEntry);
        set(93, localReadTableEntry);
      }
    }
    for (;;)
    {
      if (paramInt >= 0)
      {
        set(91, ReaderParens.getInstance('[', ']'));
        remove(93);
      }
      return;
      set(60, new ReaderTypespec());
    }
  }
  
  public void setFinalColonIsKeyword(boolean paramBoolean)
  {
    this.finalColonIsKeyword = paramBoolean;
  }
  
  public void setInitialColonIsKeyword(boolean paramBoolean)
  {
    this.initialColonIsKeyword = paramBoolean;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\ReadTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */