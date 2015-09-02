package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.Symbol;
import gnu.text.SyntaxException;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import kawa.lang.AutoloadProcedure;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_autoload
  extends Syntax
{
  public static final define_autoload define_autoload = new define_autoload("define-autoload", false);
  public static final define_autoload define_autoloads_from_file = new define_autoload("define-autoloads-from-file", true);
  boolean fromFile;
  
  public define_autoload(String paramString, boolean paramBoolean)
  {
    super(paramString);
    this.fromFile = paramBoolean;
  }
  
  public static void findAutoloadComments(LispReader paramLispReader, String paramString, ScopeExp paramScopeExp, Translator paramTranslator)
    throws IOException, SyntaxException
  {
    int i = 1;
    int n = ";;;###autoload".length();
    label21:
    int m;
    label74:
    label139:
    label315:
    label342:
    label378:
    do
    {
      int j;
      do
      {
        for (;;)
        {
          int k = paramLispReader.peek();
          if (k < 0) {
            return;
          }
          if ((k == 10) || (k == 13))
          {
            paramLispReader.read();
            i = 1;
          }
          else
          {
            m = k;
            if (i != 0)
            {
              m = k;
              if (k == 59)
              {
                j = 0;
                i = k;
                Pair localPair;
                Object localObject3;
                Object localObject2;
                if (j == n)
                {
                  m = i;
                  if (j <= 0) {
                    break label378;
                  }
                  localObject1 = paramLispReader.readObject();
                  if ((localObject1 instanceof Pair))
                  {
                    localPair = (Pair)localObject1;
                    localObject3 = null;
                    localObject2 = null;
                    localObject1 = localPair.getCar();
                    if (!(localObject1 instanceof String)) {
                      break label315;
                    }
                    localObject1 = localObject1.toString();
                    if (localObject1 != "defun") {
                      break label342;
                    }
                    localObject2 = ((Pair)localPair.getCdr()).getCar().toString();
                  }
                }
                for (Object localObject1 = new AutoloadProcedure((String)localObject2, paramString, paramTranslator.getLanguage());; localObject1 = localObject3)
                {
                  if (localObject1 != null)
                  {
                    localObject2 = paramScopeExp.getDefine(localObject2, 'w', paramTranslator);
                    localObject1 = new QuoteExp(localObject1);
                    ((Declaration)localObject2).setFlag(16384L);
                    ((Declaration)localObject2).noteValue((Expression)localObject1);
                    ((Declaration)localObject2).setProcedureDecl(true);
                    ((Declaration)localObject2).setType(Compilation.typeProcedure);
                  }
                  i = 0;
                  break;
                  k = paramLispReader.read();
                  if (k < 0) {
                    break label21;
                  }
                  if ((k == 10) || (k == 13))
                  {
                    i = 1;
                    break;
                  }
                  i = k;
                  if (j < 0) {
                    break label74;
                  }
                  if (k == ";;;###autoload".charAt(j))
                  {
                    j += 1;
                    i = k;
                    break label74;
                  }
                  j = -1;
                  i = k;
                  break label74;
                  if ((localObject1 instanceof Symbol))
                  {
                    localObject1 = ((Symbol)localObject1).getName();
                    break label139;
                  }
                  localObject1 = null;
                  break label139;
                  paramTranslator.error('w', "unsupported ;;;###autoload followed by: " + localPair.getCar());
                }
              }
            }
            j = 0;
            paramLispReader.skip();
            if ((m != 35) || (paramLispReader.peek() != 124)) {
              break;
            }
            paramLispReader.skip();
            paramLispReader.readNestedComment('#', '|');
            i = j;
          }
        }
        i = j;
      } while (Character.isWhitespace((char)m));
      i = j;
    } while (paramLispReader.readObject(m) != Sequence.eofValue);
  }
  
  public static boolean process(Object paramObject1, Object paramObject2, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    if ((paramObject1 instanceof Pair))
    {
      paramObject1 = (Pair)paramObject1;
      if ((!process(((Pair)paramObject1).getCar(), paramObject2, paramVector, paramScopeExp, paramTranslator)) || (!process(((Pair)paramObject1).getCdr(), paramObject2, paramVector, paramScopeExp, paramTranslator))) {}
    }
    while (paramObject1 == LList.Empty)
    {
      return true;
      return false;
    }
    if (((paramObject1 instanceof String)) || ((paramObject1 instanceof Symbol)))
    {
      paramVector = paramObject1.toString();
      paramScopeExp = paramScopeExp.getDefine(paramVector, 'w', paramTranslator);
      paramObject1 = paramObject2;
      if ((paramObject2 instanceof String))
      {
        String str = (String)paramObject2;
        int i = str.length();
        paramObject1 = paramObject2;
        if (i > 2)
        {
          paramObject1 = paramObject2;
          if (str.charAt(0) == '<')
          {
            paramObject1 = paramObject2;
            if (str.charAt(i - 1) == '>') {
              paramObject1 = str.substring(1, i - 1);
            }
          }
        }
      }
      paramObject1 = new QuoteExp(new AutoloadProcedure(paramVector, paramObject1.toString(), paramTranslator.getLanguage()));
      paramScopeExp.setFlag(16384L);
      paramScopeExp.noteValue((Expression)paramObject1);
      return true;
    }
    return false;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    return null;
  }
  
  public boolean scanFile(String paramString, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    if (paramString.endsWith(".el")) {}
    Object localObject2 = new File(paramString);
    Object localObject1 = localObject2;
    if (!((File)localObject2).isAbsolute()) {
      localObject1 = new File(new File(paramTranslator.getFileName()).getParent(), paramString);
    }
    String str = ((File)localObject1).getPath();
    int i = str.lastIndexOf('.');
    Language localLanguage;
    if (i >= 0)
    {
      localObject1 = str.substring(i);
      localLanguage = Language.getInstance((String)localObject1);
      if (localLanguage == null)
      {
        paramTranslator.syntaxError("unknown extension for " + str);
        return true;
      }
      localObject2 = paramTranslator.classPrefix;
      i = ((String)localObject1).length();
      for (localObject1 = paramString.substring(0, paramString.length() - i); ((String)localObject1).startsWith("../"); localObject1 = ((String)localObject1).substring(3))
      {
        i = ((String)localObject2).lastIndexOf('.', ((String)localObject2).length() - 2);
        if (i < 0)
        {
          paramTranslator.syntaxError("cannot use relative filename \"" + paramString + "\" with simple prefix \"" + (String)localObject2 + "\"");
          return false;
        }
        localObject2 = ((String)localObject2).substring(0, i + 1);
      }
      paramString = ((String)localObject2 + (String)localObject1).replace('/', '.');
    }
    try
    {
      findAutoloadComments((LispReader)localLanguage.getLexer(InPort.openFile(str), paramTranslator.getMessages()), paramString, paramScopeExp, paramTranslator);
      return true;
    }
    catch (Exception paramString)
    {
      paramTranslator.syntaxError("error reading " + str + ": " + paramString);
    }
    return true;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    boolean bool2 = false;
    boolean bool1;
    if (!(paramPair.getCdr() instanceof Pair))
    {
      bool1 = super.scanForDefinitions(paramPair, paramVector, paramScopeExp, paramTranslator);
      return bool1;
    }
    paramPair = (Pair)paramPair.getCdr();
    if (this.fromFile) {
      for (;;)
      {
        if (!(paramPair.getCar() instanceof CharSequence)) {}
        do
        {
          paramTranslator.syntaxError("invalid syntax for define-autoloads-from-file");
          return false;
          bool1 = bool2;
          if (!scanFile(paramPair.getCar().toString(), paramScopeExp, paramTranslator)) {
            break;
          }
          paramVector = paramPair.getCdr();
          if (paramVector == LList.Empty) {
            return true;
          }
        } while (!(paramVector instanceof Pair));
        paramPair = (Pair)paramPair.getCdr();
      }
    }
    Object localObject = paramPair.getCar();
    if ((paramPair.getCdr() instanceof Pair)) {
      return process(localObject, ((Pair)paramPair.getCdr()).getCar(), paramVector, paramScopeExp, paramTranslator);
    }
    paramTranslator.syntaxError("invalid syntax for define-autoload");
    return false;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\define_autoload.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */