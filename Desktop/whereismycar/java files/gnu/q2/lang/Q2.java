package gnu.q2.lang;

import gnu.expr.ApplyExp;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.kawa.functions.AppendValues;
import gnu.kawa.lispexpr.ReadTable;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.xml.XMLPrinter;
import java.io.Writer;
import kawa.standard.Scheme;

public class Q2
  extends Scheme
{
  static final Object emptyForm = new FString();
  static Q2 instance;
  
  public Q2()
  {
    instance = this;
    ModuleBody.setMainPrintValues(true);
  }
  
  public static int compareIndentation(int paramInt1, int paramInt2)
  {
    int i = paramInt1 >>> 16;
    int j = paramInt1 >>> 16;
    paramInt1 &= 0xFF;
    paramInt2 &= 0xFF;
    if (i == j) {
      return paramInt1 - paramInt2;
    }
    if (((i < j) && (paramInt1 <= paramInt2)) || ((i > j) && (paramInt1 >= paramInt2))) {
      return (i - j) * 8;
    }
    return Integer.MIN_VALUE;
  }
  
  public static Q2 getQ2Instance()
  {
    if (instance == null) {
      new Q2();
    }
    return instance;
  }
  
  public static void registerEnvironment()
  {
    Language.setDefaults(new Q2());
  }
  
  public ReadTable createReadTable()
  {
    ReadTable localReadTable = ReadTable.createInitial();
    localReadTable.set(40, new Q2ReaderParens());
    localReadTable.setFinalColonIsKeyword(true);
    return localReadTable;
  }
  
  public Lexer getLexer(InPort paramInPort, SourceMessages paramSourceMessages)
  {
    gnu.expr.Compilation.defaultCallConvention = 2;
    return new Q2Read(paramInPort, paramSourceMessages);
  }
  
  public Consumer getOutputConsumer(Writer paramWriter)
  {
    return new XMLPrinter(paramWriter, false);
  }
  
  public Procedure getPrompter()
  {
    return new Prompter();
  }
  
  public Expression makeApply(Expression paramExpression, Expression[] paramArrayOfExpression)
  {
    Expression[] arrayOfExpression = new Expression[paramArrayOfExpression.length + 1];
    arrayOfExpression[0] = paramExpression;
    System.arraycopy(paramArrayOfExpression, 0, arrayOfExpression, 1, paramArrayOfExpression.length);
    return new ApplyExp(Q2Apply.q2Apply, arrayOfExpression);
  }
  
  public Expression makeBody(Expression[] paramArrayOfExpression)
  {
    return new ApplyExp(AppendValues.appendValues, paramArrayOfExpression);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\q2\lang\Q2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */