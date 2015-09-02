package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.ModuleManager;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import java.io.PrintStream;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class ImportFromLibrary
  extends Syntax
{
  private static final String BUILTIN = "";
  private static final String MISSING;
  static final String[][] SRFI97Map;
  public static final ImportFromLibrary instance = new ImportFromLibrary();
  public String[] classPrefixPath = { "", "kawa.lib." };
  
  static
  {
    MISSING = null;
    String[] arrayOfString1 = { "2", "and-let*", "gnu.kawa.slib.srfi2" };
    String[] arrayOfString2 = { "5", "let", MISSING };
    String[] arrayOfString3 = { "6", "basic-string-ports", "" };
    String[] arrayOfString4 = { "8", "receive", "" };
    String[] arrayOfString5 = { "9", "records", "" };
    String[] arrayOfString6 = { "11", "let-values", "" };
    String[] arrayOfString7 = { "13", "strings", "gnu.kawa.slib.srfi13" };
    String[] arrayOfString8 = { "14", "char-sets", MISSING };
    String[] arrayOfString9 = { "16", "case-lambda", "" };
    String[] arrayOfString10 = { "17", "generalized-set!", "" };
    String[] arrayOfString11 = { "18", "multithreading", MISSING };
    String[] arrayOfString12 = { "19", "time", MISSING };
    String[] arrayOfString13 = { "21", "real-time-multithreading", MISSING };
    String[] arrayOfString14 = { "23", "error", "" };
    String[] arrayOfString15 = { "25", "multi-dimensional-arrays", "" };
    String[] arrayOfString16 = { "26", "cut", "" };
    String[] arrayOfString17 = { "27", "random-bits", MISSING };
    String[] arrayOfString18 = { "28", "basic-format-strings", "" };
    String[] arrayOfString19 = { "29", "localization", MISSING };
    String str1 = MISSING;
    String[] arrayOfString20 = { "38", "with-shared-structure", MISSING };
    String[] arrayOfString21 = { "39", "parameters", "" };
    String[] arrayOfString22 = { "41", "streams", MISSING };
    String[] arrayOfString23 = { "42", "eager-comprehensions", MISSING };
    String[] arrayOfString24 = { "43", "vectors", MISSING };
    String[] arrayOfString25 = { "44", "collections", MISSING };
    String[] arrayOfString26 = { "45", "lazy", MISSING };
    String[] arrayOfString27 = { "46", "syntax-rules", MISSING };
    String[] arrayOfString28 = { "47", "arrays", MISSING };
    String[] arrayOfString29 = { "48", "intermediate-format-strings", MISSING };
    String[] arrayOfString30 = { "51", "rest-values", MISSING };
    String[] arrayOfString31 = { "54", "cat", MISSING };
    String[] arrayOfString32 = { "57", "records", MISSING };
    String[] arrayOfString33 = { "59", "vicinities", MISSING };
    String[] arrayOfString34 = { "60", "integer-bits", MISSING };
    String[] arrayOfString35 = { "61", "cond", MISSING };
    String[] arrayOfString36 = { "63", "arrays", MISSING };
    String[] arrayOfString37 = { "64", "testing", "gnu.kawa.slib.testing" };
    String[] arrayOfString38 = { "66", "octet-vectors", MISSING };
    String[] arrayOfString39 = { "67", "compare-procedures", MISSING };
    String[] arrayOfString40 = { "69", "basic-hash-tables", "gnu.kawa.slib.srfi69" };
    String str2 = MISSING;
    String[] arrayOfString41 = { "74", "blobs", MISSING };
    String[] arrayOfString42 = { "78", "lightweight-testing", MISSING };
    String[] arrayOfString43 = { "86", "mu-and-nu", MISSING };
    String[] arrayOfString44 = { "87", "case", MISSING };
    String[] arrayOfString45 = { "95", "sorting-and-merging", "kawa.lib.srfi95" };
    SRFI97Map = new String[][] { { "1", "lists", "gnu.kawa.slib.srfi1" }, arrayOfString1, arrayOfString2, arrayOfString3, arrayOfString4, arrayOfString5, arrayOfString6, arrayOfString7, arrayOfString8, arrayOfString9, arrayOfString10, arrayOfString11, arrayOfString12, arrayOfString13, arrayOfString14, arrayOfString15, arrayOfString16, arrayOfString17, arrayOfString18, arrayOfString19, { "31", "rec", str1 }, arrayOfString20, arrayOfString21, arrayOfString22, arrayOfString23, arrayOfString24, arrayOfString25, arrayOfString26, arrayOfString27, arrayOfString28, arrayOfString29, arrayOfString30, arrayOfString31, arrayOfString32, arrayOfString33, arrayOfString34, arrayOfString35, arrayOfString36, arrayOfString37, arrayOfString38, arrayOfString39, arrayOfString40, { "71", "let", str2 }, arrayOfString41, arrayOfString42, arrayOfString43, arrayOfString44, arrayOfString45 };
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    return null;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    Object localObject2 = null;
    paramPair = paramPair.getCdr();
    if (!(paramPair instanceof Pair)) {
      return false;
    }
    paramPair = (Pair)paramPair;
    Object localObject3 = paramPair.getCar();
    if (LList.listLength(localObject3, false) <= 0)
    {
      paramTranslator.error('e', "expected <library reference> which must be a list");
      return false;
    }
    paramPair = paramPair.getCdr();
    Object localObject1 = localObject2;
    if ((paramPair instanceof Pair))
    {
      localObject1 = localObject2;
      if ((((Pair)paramPair).getCar() instanceof Procedure)) {
        localObject1 = (Procedure)((Pair)paramPair).getCar();
      }
    }
    paramPair = null;
    localObject2 = null;
    Object localObject5 = new StringBuffer();
    Object localObject4;
    if ((localObject3 instanceof Pair))
    {
      localObject3 = (Pair)localObject3;
      localObject4 = ((Pair)localObject3).getCar();
      localObject3 = ((Pair)localObject3).getCdr();
      if ((localObject4 instanceof Pair))
      {
        if (paramPair != null) {
          paramTranslator.error('e', "duplicate version reference - was " + paramPair);
        }
        paramPair = (Pair)localObject4;
        System.err.println("import version " + localObject4);
      }
      for (;;)
      {
        break;
        if ((localObject4 instanceof String))
        {
          if ((localObject3 instanceof Pair)) {
            paramTranslator.error('e', "source specifier must be last elemnt in library reference");
          }
          localObject2 = (String)localObject4;
        }
        else
        {
          if (((StringBuffer)localObject5).length() > 0) {
            ((StringBuffer)localObject5).append('.');
          }
          ((StringBuffer)localObject5).append(Compilation.mangleNameIfNeeded(localObject4.toString()));
        }
      }
    }
    paramPair = null;
    if (localObject2 != null)
    {
      localObject3 = require.lookupModuleFromSourcePath((String)localObject2, paramScopeExp);
      paramPair = (Pair)localObject3;
      if (localObject3 == null)
      {
        paramTranslator.error('e', "malformed URL: " + (String)localObject2);
        return false;
      }
    }
    localObject3 = ((StringBuffer)localObject5).toString();
    localObject2 = localObject3;
    if (((String)localObject3).startsWith("srfi."))
    {
      localObject5 = Compilation.demangleName(((String)localObject3).substring(5));
      i = ((String)localObject5).indexOf('.');
      if (i < 0)
      {
        localObject2 = null;
        i = ((String)localObject5).length();
        localObject4 = null;
        if (i < 2)
        {
          localObject3 = localObject4;
          if (((String)localObject5).charAt(0) != ':') {}
        }
        else
        {
          j = 1;
        }
      }
      for (;;)
      {
        if (j == i) {
          localObject3 = ((String)localObject5).substring(1, i);
        }
        do
        {
          if (localObject3 != null) {
            break label491;
          }
          paramTranslator.error('e', "SRFI library reference must have the form: (srfi :NNN [name])");
          return false;
          localObject2 = ((String)localObject5).substring(i + 1);
          break;
          localObject3 = localObject4;
        } while (Character.digit(((String)localObject5).charAt(j), 10) < 0);
        j += 1;
      }
      label491:
      i = SRFI97Map.length;
      do
      {
        j = i - 1;
        if (j < 0)
        {
          paramTranslator.error('e', "unknown SRFI number '" + (String)localObject3 + "' in SRFI library reference");
          return false;
        }
        i = j;
      } while (!SRFI97Map[j][0].equals(localObject3));
      localObject5 = SRFI97Map[j][1];
      localObject4 = SRFI97Map[j][2];
      if ((localObject2 != null) && (!((String)localObject2).equals(localObject5))) {
        paramTranslator.error('w', "the name of SRFI " + (String)localObject3 + " should be '" + (String)localObject5 + '\'');
      }
      if (localObject4 == "") {
        return true;
      }
      if (localObject4 == MISSING)
      {
        paramTranslator.error('e', "sorry - Kawa does not support SRFI " + (String)localObject3 + " (" + (String)localObject5 + ')');
        return false;
      }
      localObject2 = localObject4;
    }
    int j = this.classPrefixPath.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label781;
      }
      localObject3 = this.classPrefixPath[i] + (String)localObject2;
      try
      {
        localObject3 = ModuleManager.getInstance().findWithClassName((String)localObject3);
        paramPair = (Pair)localObject3;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      i += 1;
    }
    label781:
    if (paramPair == null)
    {
      paramTranslator.error('e', "unknown class " + (String)localObject2);
      return false;
    }
    require.importDefinitions(null, paramPair, (Procedure)localObject1, paramVector, paramScopeExp, paramTranslator);
    return true;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\ImportFromLibrary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */