package gnu.ecmascript;

import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.LambdaExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.SetExp;
import gnu.lists.Sequence;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;
import kawa.standard.Scheme;

public class Parser
{
  public static final Expression[] emptyArgs = new Expression[0];
  static Expression emptyStatement = new QuoteExp(Values.empty);
  public static Expression eofExpr = new QuoteExp(Sequence.eofValue);
  public int errors;
  Lexer lexer;
  InPort port;
  Object previous_token;
  Object token;
  
  public Parser(InPort paramInPort)
  {
    this.port = paramInPort;
    this.lexer = new Lexer(paramInPort);
  }
  
  public static void main(String[] paramArrayOfString)
  {
    new Scheme();
    paramArrayOfString = InPort.inDefault();
    if ((paramArrayOfString instanceof TtyInPort))
    {
      localObject1 = new Prompter();
      ((TtyInPort)paramArrayOfString).setPrompter((Procedure)localObject1);
    }
    paramArrayOfString = new Parser(paramArrayOfString);
    Object localObject1 = OutPort.outDefault();
    try
    {
      for (;;)
      {
        Object localObject2 = paramArrayOfString.parseStatement();
        if (localObject2 == eofExpr) {
          return;
        }
        ((OutPort)localObject1).print("[Expression: ");
        ((Expression)localObject2).print((OutPort)localObject1);
        ((OutPort)localObject1).println("]");
        localObject2 = ((Expression)localObject2).eval(Environment.user());
        ((OutPort)localObject1).print("result: ");
        ((OutPort)localObject1).print(localObject2);
        ((OutPort)localObject1).println();
      }
      return;
    }
    catch (Throwable paramArrayOfString)
    {
      System.err.println("caught exception:" + paramArrayOfString);
      paramArrayOfString.printStackTrace(System.err);
    }
  }
  
  public Expression buildLoop(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, Expression paramExpression4)
  {
    if (paramExpression1 != null) {
      return new BeginExp(new Expression[] { paramExpression1, buildLoop(null, paramExpression2, paramExpression3, paramExpression4) });
    }
    throw new Error("not implemented - buildLoop");
  }
  
  public String getIdentifier()
    throws IOException, SyntaxException
  {
    Object localObject = getToken();
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    syntaxError("missing identifier");
    return "??";
  }
  
  public void getSemicolon()
    throws IOException, SyntaxException
  {
    this.token = peekToken();
    if (this.token == Lexer.semicolonToken) {
      skipToken();
    }
    while ((this.token == Lexer.rbraceToken) || (this.token == Lexer.eofToken) || (this.previous_token == Lexer.eolToken)) {
      return;
    }
    syntaxError("missing ';' after expression");
  }
  
  public Object getToken()
    throws IOException, SyntaxException
  {
    Object localObject = peekToken();
    skipToken();
    return localObject;
  }
  
  public Expression makeCallExpression(Expression paramExpression, Expression[] paramArrayOfExpression)
  {
    return new ApplyExp(paramExpression, paramArrayOfExpression);
  }
  
  public Expression makeNewExpression(Expression paramExpression, Expression[] paramArrayOfExpression)
  {
    paramExpression = paramArrayOfExpression;
    if (paramArrayOfExpression == null) {
      paramExpression = emptyArgs;
    }
    return new ApplyExp(null, paramExpression);
  }
  
  public Expression makePropertyAccessor(Expression paramExpression1, Expression paramExpression2)
  {
    return null;
  }
  
  public Expression[] parseArguments()
    throws IOException, SyntaxException
  {
    skipToken();
    if (peekToken() == Lexer.rparenToken)
    {
      skipToken();
      return emptyArgs;
    }
    Vector localVector = new Vector(10);
    for (;;)
    {
      localVector.addElement(parseAssignmentExpression());
      Object localObject = getToken();
      if (localObject == Lexer.rparenToken)
      {
        localObject = new Expression[localVector.size()];
        localVector.copyInto((Object[])localObject);
        return (Expression[])localObject;
      }
      if (localObject != Lexer.commaToken) {
        syntaxError("invalid token '" + localObject + "' in argument list");
      }
    }
  }
  
  public Expression parseAssignmentExpression()
    throws IOException, SyntaxException
  {
    Expression localExpression = parseConditionalExpression();
    Object localObject2 = peekToken();
    if (localObject2 == Lexer.equalToken)
    {
      skipToken();
      localObject1 = parseAssignmentExpression();
      if ((localExpression instanceof ReferenceExp))
      {
        localObject1 = new SetExp(((ReferenceExp)localExpression).getName(), (Expression)localObject1);
        ((SetExp)localObject1).setDefining(true);
      }
    }
    do
    {
      do
      {
        return (Expression)localObject1;
        return syntaxError("unmplemented non-symbol ihs in assignment");
        localObject1 = localExpression;
      } while (!(localObject2 instanceof Reserved));
      localObject2 = (Reserved)localObject2;
      localObject1 = localExpression;
    } while (!((Reserved)localObject2).isAssignmentOp());
    skipToken();
    Object localObject1 = parseAssignmentExpression();
    return new ApplyExp(new QuoteExp(((Reserved)localObject2).proc), new Expression[] { localExpression, localObject1 });
  }
  
  public Expression parseBinaryExpression(int paramInt)
    throws IOException, SyntaxException
  {
    Reserved localReserved;
    Expression localExpression;
    for (Object localObject = parseUnaryExpression();; localObject = new ApplyExp(new QuoteExp(localReserved.proc), new Expression[] { localObject, localExpression }))
    {
      this.token = peekToken();
      if (!(this.token instanceof Reserved)) {}
      do
      {
        return (Expression)localObject;
        localReserved = (Reserved)this.token;
      } while (localReserved.prio < paramInt);
      getToken();
      localExpression = parseBinaryExpression(localReserved.prio + 1);
    }
  }
  
  public Expression parseBlock()
    throws IOException, SyntaxException
  {
    Object localObject2 = null;
    if (getToken() != Lexer.lbraceToken) {
      return syntaxError("extened '{'");
    }
    int i = 0;
    for (;;)
    {
      this.token = peekToken();
      int j;
      Object localObject1;
      if (this.token == Lexer.rbraceToken)
      {
        skipToken();
        if (localObject2 == null) {
          return emptyStatement;
        }
        j = 1;
        if (localObject2 != null) {
          break label84;
        }
        localObject1 = new Expression[2];
      }
      for (;;)
      {
        if (j != 0)
        {
          return new BeginExp((Expression[])localObject1);
          j = 0;
          break;
          label84:
          if (j != 0)
          {
            localObject1 = localObject2;
            if (localObject2.length == i) {
              continue;
            }
            label97:
            if (j == 0) {
              break label133;
            }
          }
          label133:
          for (int k = i;; k = localObject2.length * 2)
          {
            localObject1 = new Expression[k];
            System.arraycopy(localObject2, 0, localObject1, 0, i);
            break;
            localObject1 = localObject2;
            if (localObject2.length > i) {
              break;
            }
            break label97;
          }
        }
      }
      localObject1[i] = parseStatement();
      i += 1;
      localObject2 = localObject1;
    }
  }
  
  public Expression parseConditionalExpression()
    throws IOException, SyntaxException
  {
    Expression localExpression1 = parseBinaryExpression(1);
    if (peekToken() != Lexer.condToken) {
      return localExpression1;
    }
    skipToken();
    Expression localExpression2 = parseAssignmentExpression();
    if (getToken() != Lexer.colonToken) {
      return syntaxError("expected ':' in conditional expression");
    }
    return new IfExp(localExpression1, localExpression2, parseAssignmentExpression());
  }
  
  public Expression parseExpression()
    throws IOException, SyntaxException
  {
    Object localObject2 = null;
    int i = 0;
    for (;;)
    {
      Expression localExpression = parseAssignmentExpression();
      int j;
      if (peekToken() != Lexer.commaToken) {
        j = 1;
      }
      Object localObject1;
      while (localObject2 == null) {
        if (j != 0)
        {
          return localExpression;
          j = 0;
        }
        else
        {
          localObject1 = new Expression[2];
        }
      }
      do
      {
        localObject1[i] = localExpression;
        if (j == 0) {
          break label130;
        }
        return new BeginExp((Expression[])localObject1);
        if (j == 0) {
          break;
        }
        localObject1 = localObject2;
      } while (localObject2.length == i + 1);
      label80:
      if (j != 0) {}
      for (int k = i + 1;; k = localObject2.length * 2)
      {
        localObject1 = new Expression[k];
        System.arraycopy(localObject2, 0, localObject1, 0, i);
        break;
        localObject1 = localObject2;
        if (localObject2.length > i) {
          break;
        }
        break label80;
      }
      label130:
      skipToken();
      i += 1;
      localObject2 = localObject1;
    }
  }
  
  public Expression parseFunctionDefinition()
    throws IOException, SyntaxException
  {
    skipToken();
    Object localObject1 = getIdentifier();
    Object localObject2 = getToken();
    if (localObject2 != Lexer.lparenToken) {
      return syntaxError("expected '(' - got:" + localObject2);
    }
    localObject2 = new Vector(10);
    if (peekToken() == Lexer.rparenToken) {
      skipToken();
    }
    for (;;)
    {
      localObject2 = new LambdaExp(parseBlock());
      ((LambdaExp)localObject2).setName((String)localObject1);
      localObject1 = new SetExp(localObject1, (Expression)localObject2);
      ((SetExp)localObject1).setDefining(true);
      return (Expression)localObject1;
      Object localObject3;
      do
      {
        if (localObject3 != Lexer.commaToken) {
          syntaxError("invalid token '" + localObject3 + "' in argument list");
        }
        ((Vector)localObject2).addElement(getIdentifier());
        localObject3 = getToken();
      } while (localObject3 != Lexer.rparenToken);
    }
  }
  
  public Expression parseIfStatement()
    throws IOException, SyntaxException
  {
    skipToken();
    Object localObject = getToken();
    if (localObject != Lexer.lparenToken) {
      return syntaxError("expected '(' - got:" + localObject);
    }
    Expression localExpression1 = parseExpression();
    localObject = getToken();
    if (localObject != Lexer.rparenToken) {
      return syntaxError("expected ')' - got:" + localObject);
    }
    Expression localExpression2 = parseStatement();
    if (peekToken() == Lexer.elseToken) {
      skipToken();
    }
    for (localObject = parseStatement();; localObject = null) {
      return new IfExp(localExpression1, localExpression2, (Expression)localObject);
    }
  }
  
  public Expression parseLeftHandSideExpression()
    throws IOException, SyntaxException
  {
    int i = 0;
    while (peekToken() == Lexer.newToken)
    {
      i += 1;
      skipToken();
    }
    Expression localExpression = parsePrimaryExpression();
    Object localObject1;
    int j;
    for (;;)
    {
      Object localObject2 = peekToken();
      if (localObject2 == Lexer.dotToken)
      {
        skipToken();
        localExpression = makePropertyAccessor(localExpression, new QuoteExp(getIdentifier()));
      }
      else if (localObject2 == Lexer.lbracketToken)
      {
        skipToken();
        localObject1 = parseExpression();
        localObject2 = getToken();
        if (localObject2 != Lexer.rbracketToken) {
          return syntaxError("expected ']' - got:" + localObject2);
        }
        localExpression = makePropertyAccessor(localExpression, (Expression)localObject1);
      }
      else
      {
        localObject1 = localExpression;
        j = i;
        if (localObject2 != Lexer.lparenToken) {
          break;
        }
        localObject1 = parseArguments();
        System.err.println("after parseArgs:" + peekToken());
        if (i > 0)
        {
          localExpression = makeNewExpression(localExpression, (Expression[])localObject1);
          i -= 1;
        }
        else
        {
          localExpression = makeCallExpression(localExpression, (Expression[])localObject1);
        }
      }
    }
    while (j > 0)
    {
      localObject1 = makeNewExpression((Expression)localObject1, null);
      j -= 1;
    }
    return (Expression)localObject1;
  }
  
  public Expression parsePostfixExpression()
    throws IOException, SyntaxException
  {
    Expression localExpression = parseLeftHandSideExpression();
    Object localObject = peekTokenOrLine();
    if ((localObject != Reserved.opPlusPlus) && (localObject != Reserved.opMinusMinus)) {
      return localExpression;
    }
    skipToken();
    return new ApplyExp(new QuoteExp(((Reserved)localObject).proc), new Expression[] { localExpression });
  }
  
  public Expression parsePrimaryExpression()
    throws IOException, SyntaxException
  {
    Object localObject1 = getToken();
    if ((localObject1 instanceof QuoteExp)) {
      return (QuoteExp)localObject1;
    }
    if ((localObject1 instanceof String)) {
      return new ReferenceExp((String)localObject1);
    }
    if (localObject1 == Lexer.lparenToken)
    {
      localObject1 = parseExpression();
      Object localObject2 = getToken();
      if (localObject2 != Lexer.rparenToken) {
        return syntaxError("expected ')' - got:" + localObject2);
      }
      return (Expression)localObject1;
    }
    return syntaxError("unexpected token: " + localObject1);
  }
  
  public Expression parseStatement()
    throws IOException, SyntaxException
  {
    Object localObject = peekToken();
    if ((localObject instanceof Reserved)) {}
    switch (((Reserved)localObject).prio)
    {
    default: 
      if (localObject == Lexer.eofToken) {
        return eofExpr;
      }
      break;
    case 31: 
      return parseIfStatement();
    case 32: 
      return parseWhileStatement();
    case 41: 
      return parseFunctionDefinition();
    }
    if (localObject == Lexer.semicolonToken)
    {
      skipToken();
      return emptyStatement;
    }
    if (localObject == Lexer.lbraceToken) {
      return parseBlock();
    }
    localObject = parseExpression();
    getSemicolon();
    return (Expression)localObject;
  }
  
  public Expression parseUnaryExpression()
    throws IOException, SyntaxException
  {
    return parsePostfixExpression();
  }
  
  public Expression parseWhileStatement()
    throws IOException, SyntaxException
  {
    skipToken();
    Object localObject1 = getToken();
    if (localObject1 != Lexer.lparenToken) {
      return syntaxError("expected '(' - got:" + localObject1);
    }
    localObject1 = parseExpression();
    Object localObject2 = getToken();
    if (localObject2 != Lexer.rparenToken) {
      return syntaxError("expected ')' - got:" + localObject2);
    }
    return buildLoop(null, (Expression)localObject1, null, parseStatement());
  }
  
  public Object peekToken()
    throws IOException, SyntaxException
  {
    if (this.token == null) {}
    for (this.token = this.lexer.getToken(); this.token == Lexer.eolToken; this.token = this.lexer.getToken()) {
      skipToken();
    }
    return this.token;
  }
  
  public Object peekTokenOrLine()
    throws IOException, SyntaxException
  {
    if (this.token == null) {
      this.token = this.lexer.getToken();
    }
    return this.token;
  }
  
  public final void skipToken()
  {
    if (this.token != Lexer.eofToken)
    {
      this.previous_token = this.token;
      this.token = null;
    }
  }
  
  public Expression syntaxError(String paramString)
  {
    this.errors += 1;
    OutPort localOutPort = OutPort.errDefault();
    String str = this.port.getName();
    int i = this.port.getLineNumber() + 1;
    int j = this.port.getColumnNumber() + 1;
    if (i > 0)
    {
      if (str != null) {
        localOutPort.print(str);
      }
      localOutPort.print(':');
      localOutPort.print(i);
      if (j > 1)
      {
        localOutPort.print(':');
        localOutPort.print(j);
      }
      localOutPort.print(": ");
    }
    localOutPort.println(paramString);
    return new ErrorExp(paramString);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\ecmascript\Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */