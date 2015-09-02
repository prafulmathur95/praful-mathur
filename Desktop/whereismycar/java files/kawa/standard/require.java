package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleManager;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class require
  extends Syntax
{
  private static final String SLIB_PREFIX = "gnu.kawa.slib.";
  static Hashtable featureMap;
  public static final require require = new require();
  
  static
  {
    require.setName("require");
    featureMap = new Hashtable();
    map("generic-write", "gnu.kawa.slib.genwrite");
    map("pretty-print", "gnu.kawa.slib.pp");
    map("pprint-file", "gnu.kawa.slib.ppfile");
    map("printf", "gnu.kawa.slib.printf");
    map("xml", "gnu.kawa.slib.XML");
    map("readtable", "gnu.kawa.slib.readtable");
    map("srfi-10", "gnu.kawa.slib.readtable");
    map("http", "gnu.kawa.servlet.HTTP");
    map("servlets", "gnu.kawa.servlet.servlets");
    map("srfi-1", "gnu.kawa.slib.srfi1");
    map("list-lib", "gnu.kawa.slib.srfi1");
    map("srfi-2", "gnu.kawa.slib.srfi2");
    map("and-let*", "gnu.kawa.slib.srfi2");
    map("srfi-13", "gnu.kawa.slib.srfi13");
    map("string-lib", "gnu.kawa.slib.srfi13");
    map("srfi-34", "gnu.kawa.slib.srfi34");
    map("srfi-35", "gnu.kawa.slib.conditions");
    map("condition", "gnu.kawa.slib.conditions");
    map("conditions", "gnu.kawa.slib.conditions");
    map("srfi-37", "gnu.kawa.slib.srfi37");
    map("args-fold", "gnu.kawa.slib.srfi37");
    map("srfi-64", "gnu.kawa.slib.testing");
    map("testing", "gnu.kawa.slib.testing");
    map("srfi-69", "gnu.kawa.slib.srfi69");
    map("hash-table", "gnu.kawa.slib.srfi69");
    map("basic-hash-tables", "gnu.kawa.slib.srfi69");
    map("srfi-95", "kawa.lib.srfi95");
    map("sorting-and-merging", "kawa.lib.srfi95");
    map("regex", "kawa.lib.kawa.regex");
    map("pregexp", "gnu.kawa.slib.pregexp");
    map("gui", "gnu.kawa.slib.gui");
    map("swing-gui", "gnu.kawa.slib.swing");
    map("android-defs", "gnu.kawa.android.defs");
    map("syntax-utils", "gnu.kawa.slib.syntaxutils");
  }
  
  public static Object find(String paramString)
  {
    return ModuleManager.getInstance().findWithClassName(paramString).getInstance();
  }
  
  public static boolean importDefinitions(String paramString, ModuleInfo paramModuleInfo, Procedure paramProcedure, Vector paramVector, ScopeExp paramScopeExp, Compilation paramCompilation)
  {
    Object localObject1 = ModuleManager.getInstance();
    Object localObject2;
    if (((paramModuleInfo.getState() & 0x1) == 0) && (paramModuleInfo.getCompilation() == null) && (!paramModuleInfo.checkCurrent((ModuleManager)localObject1, System.currentTimeMillis())))
    {
      localObject1 = paramCompilation.getMessages();
      localObject2 = Language.getDefaultLanguage();
    }
    try
    {
      localObject3 = InPort.openFile(paramModuleInfo.getSourceAbsPath());
      paramModuleInfo.clearClass();
      paramModuleInfo.setClassName(paramString);
      i = 8;
      if (paramCompilation.immediate) {
        i = 0x8 | 0x1;
      }
      paramString = ((Language)localObject2).parse((InPort)localObject3, (SourceMessages)localObject1, i, paramModuleInfo);
      paramModuleInfo.setClassName(paramString.getModule().classFor(paramString).getName());
      if ((paramCompilation.minfo != null) && (paramCompilation.getState() < 4))
      {
        paramCompilation.minfo.addDependency(paramModuleInfo);
        if ((!paramModuleInfo.loadEager(12)) && (paramModuleInfo.getState() < 6))
        {
          paramCompilation.pushPendingImport(paramModuleInfo, paramScopeExp, paramVector.size());
          return true;
        }
      }
    }
    catch (FileNotFoundException paramString)
    {
      paramCompilation.error('e', "not found: " + paramString.getMessage());
      return false;
    }
    catch (IOException paramString)
    {
      paramCompilation.error('e', "caught " + paramString);
      return false;
    }
    catch (SyntaxException paramString)
    {
      if (paramString.getMessages() != localObject1) {
        throw new RuntimeException("confussing syntax error: " + paramString);
      }
      return false;
    }
    ClassType localClassType = paramModuleInfo.getClassType();
    String str = localClassType.getName();
    boolean bool2 = paramCompilation.sharedModuleDefs();
    boolean bool1;
    ApplyExp localApplyExp;
    Language localLanguage;
    int k;
    Vector localVector;
    label383:
    Object localObject5;
    if (paramModuleInfo.getState() < 6)
    {
      bool1 = paramModuleInfo.getCompilation().makeRunnable();
      paramString = null;
      localApplyExp = Invoke.makeInvokeStatic(ClassType.make("kawa.standard.require"), "find", new Expression[] { new QuoteExp(str) });
      localObject1 = null;
      localLanguage = paramCompilation.getLanguage();
      localApplyExp.setLine(paramCompilation);
      k = paramVector.size();
      paramModuleInfo = paramModuleInfo.setupModuleExp();
      localVector = new Vector();
      localObject2 = paramModuleInfo.firstDecl();
      if (localObject2 == null) {
        break label1149;
      }
      if (!((Declaration)localObject2).isPrivate()) {
        break label441;
      }
      localObject5 = localObject1;
      j = k;
      localObject3 = paramString;
    }
    label441:
    boolean bool3;
    label737:
    label765:
    Declaration localDeclaration;
    do
    {
      for (;;)
      {
        localObject2 = ((Declaration)localObject2).nextDecl();
        paramString = (String)localObject3;
        k = j;
        localObject1 = localObject5;
        break label383;
        bool1 = localClassType.isSubtype(Compilation.typeRunnable);
        break;
        paramModuleInfo = (Symbol)((Declaration)localObject2).getSymbol();
        localObject4 = paramModuleInfo;
        if (paramProcedure != null)
        {
          try
          {
            paramModuleInfo = paramProcedure.apply1(paramModuleInfo);
            localObject3 = paramString;
            j = k;
            localObject5 = localObject1;
            if (paramModuleInfo == null) {
              continue;
            }
            if (!(paramModuleInfo instanceof Symbol))
            {
              paramCompilation.error('e', "internal error - import name mapper returned non-symbol: " + paramModuleInfo.getClass().getName());
              localObject3 = paramString;
              j = k;
              localObject5 = localObject1;
            }
          }
          catch (Throwable paramModuleInfo)
          {
            for (;;) {}
            localObject4 = (Symbol)paramModuleInfo;
          }
        }
        else
        {
          bool3 = ((Declaration)localObject2).getFlag(2048L);
          paramModuleInfo = paramString;
          i = k;
          if (!bool3)
          {
            paramModuleInfo = paramString;
            i = k;
            if (paramString == null)
            {
              paramModuleInfo = new Declaration(SimpleSymbol.valueOf(str.replace('.', '$') + "$instance"), localClassType);
              paramModuleInfo.setPrivate(true);
              paramModuleInfo.setFlag(1073758208L);
              paramScopeExp.addDeclaration(paramModuleInfo);
              paramModuleInfo.noteValue(localApplyExp);
              paramString = new SetExp(paramModuleInfo, localApplyExp);
              paramString.setLine(paramCompilation);
              paramString.setDefining(true);
              paramVector.addElement(paramString);
              i = paramVector.size();
              paramModuleInfo.setFlag(536870912L);
              if (bool1) {
                paramModuleInfo.setSimple(false);
              }
              paramModuleInfo.setFlag(8192L);
            }
          }
          if ((((Declaration)localObject2).field == null) || (!((Declaration)localObject2).field.getName().equals("$instance"))) {
            break label737;
          }
          localObject5 = ((Declaration)localObject2).field;
          localObject3 = paramModuleInfo;
          j = i;
        }
      }
      if ((((Declaration)localObject2).field == null) || (!((Declaration)localObject2).field.getName().endsWith("$instance"))) {
        break label1021;
      }
      k = 1;
      localDeclaration = paramScopeExp.lookup(localObject4, localLanguage, localLanguage.getNamespaceOf((Declaration)localObject2));
      if (k == 0) {
        break label1027;
      }
      localObject3 = paramModuleInfo;
      j = i;
      localObject5 = localObject1;
    } while (localDeclaration != null);
    paramString = paramScopeExp.addDeclaration(localObject4);
    paramString.setFlag(1073758208L);
    paramString.setType(((Declaration)localObject2).getType());
    paramString.setFlag(8192L);
    paramString.setLocation(paramCompilation);
    Object localObject3 = new ReferenceExp((Declaration)localObject2);
    ((ReferenceExp)localObject3).setContextDecl(paramModuleInfo);
    if (k == 0)
    {
      ((ReferenceExp)localObject3).setDontDereference(true);
      if (!bool2) {
        paramString.setPrivate(true);
      }
    }
    paramString.setFlag(16384L);
    if (((Declaration)localObject2).getFlag(32768L)) {
      paramString.setFlag(32768L);
    }
    if (((Declaration)localObject2).isProcedureDecl()) {
      paramString.setProcedureDecl(true);
    }
    if (bool3) {
      paramString.setFlag(2048L);
    }
    Object localObject4 = new SetExp(paramString, (Expression)localObject3);
    paramString.setFlag(536870912L);
    ((SetExp)localObject4).setDefining(true);
    if (k != 0)
    {
      paramVector.insertElementAt(localObject4, i);
      i += 1;
    }
    for (;;)
    {
      localVector.add(paramString);
      localVector.add(localObject2);
      paramString.noteValue((Expression)localObject3);
      paramString.setFlag(131072L);
      paramCompilation.push(paramString);
      localObject3 = paramModuleInfo;
      j = i;
      localObject5 = localObject1;
      break;
      label1021:
      k = 0;
      break label765;
      label1027:
      if ((localDeclaration != null) && (!localDeclaration.getFlag(512L)))
      {
        localObject3 = paramModuleInfo;
        j = i;
        localObject5 = localObject1;
        if (Declaration.followAliases(localDeclaration) == Declaration.followAliases((Declaration)localObject2)) {
          break;
        }
      }
      if ((localDeclaration != null) && (localDeclaration.getFlag(66048L)))
      {
        localDeclaration.setFlag(false, 66048L);
        paramString = localDeclaration;
      }
      for (;;)
      {
        paramString.setAlias(true);
        paramString.setIndirectBinding(true);
        break;
        localObject3 = paramScopeExp.addDeclaration(localObject4);
        paramString = (String)localObject3;
        if (localDeclaration != null)
        {
          ScopeExp.duplicateDeclarationError(localDeclaration, (Declaration)localObject3, paramCompilation);
          paramString = (String)localObject3;
        }
      }
      paramVector.addElement(localObject4);
    }
    label1149:
    int j = localVector.size();
    int i = 0;
    while (i < j)
    {
      paramProcedure = (Declaration)localVector.elementAt(i);
      localObject2 = (Declaration)localVector.elementAt(i + 1);
      paramModuleInfo = ((Declaration)localObject2).getValue();
      if ((((Declaration)localObject2).isIndirectBinding()) && ((paramModuleInfo instanceof ReferenceExp)))
      {
        paramProcedure = (ReferenceExp)paramProcedure.getValue();
        paramModuleInfo = ((ReferenceExp)paramModuleInfo).getBinding();
        paramProcedure.setBinding(paramModuleInfo);
        if (paramModuleInfo.needsContext())
        {
          paramModuleInfo = paramScopeExp.lookup(SimpleSymbol.valueOf(paramModuleInfo.field.getDeclaringClass().getName().replace('.', '$') + "$instance"));
          paramModuleInfo.setFlag(1024L);
          paramProcedure.setContextDecl(paramModuleInfo);
        }
      }
      i += 2;
    }
    if (bool1)
    {
      paramModuleInfo = Compilation.typeRunnable.getDeclaredMethod("run", 0);
      if (paramString == null) {
        break label1365;
      }
      paramString = new ReferenceExp(paramString);
    }
    for (;;)
    {
      paramString = new ApplyExp(paramModuleInfo, new Expression[] { paramString });
      paramString.setLine(paramCompilation);
      paramVector.addElement(paramString);
      return true;
      label1365:
      paramString = localApplyExp;
      if (localObject1 != null)
      {
        paramString = new QuoteExp(localClassType);
        paramProcedure = new QuoteExp("$instance");
        paramString = new ApplyExp(SlotGet.staticField, new Expression[] { paramString, paramProcedure });
      }
    }
  }
  
  public static ModuleInfo lookupModuleFromSourcePath(String paramString, ScopeExp paramScopeExp)
  {
    ModuleManager localModuleManager = ModuleManager.getInstance();
    String str = paramScopeExp.getFileName();
    paramScopeExp = paramString;
    if (str != null) {
      paramScopeExp = Path.valueOf(str).resolve(paramString).toString();
    }
    return localModuleManager.findWithSourcePath(paramScopeExp);
  }
  
  static void map(String paramString1, String paramString2)
  {
    featureMap.put(paramString1, paramString2);
  }
  
  public static String mapFeature(String paramString)
  {
    return (String)featureMap.get(paramString);
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    return null;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    if (paramTranslator.getState() == 1)
    {
      paramTranslator.setState(2);
      paramTranslator.pendingForm = paramPair;
      return true;
    }
    Object localObject2 = (Pair)paramPair.getCdr();
    Object localObject3 = ((Pair)localObject2).getCar();
    Object localObject1 = null;
    if ((localObject3 instanceof Pair))
    {
      paramPair = (Pair)localObject3;
      if (paramTranslator.matches(paramPair.getCar(), "quote"))
      {
        paramPair = paramPair.getCdr();
        if ((paramPair instanceof Pair))
        {
          paramPair = (Pair)paramPair;
          if ((paramPair.getCdr() == LList.Empty) && ((paramPair.getCar() instanceof Symbol))) {}
        }
        else
        {
          paramTranslator.error('e', "invalid quoted symbol for 'require'");
          return false;
        }
        localObject1 = mapFeature(paramPair.getCar().toString());
        if (localObject1 == null)
        {
          paramTranslator.error('e', "unknown feature name '" + paramPair.getCar() + "' for 'require'");
          return false;
        }
        paramPair = ClassType.make((String)localObject1);
      }
    }
    while (!(paramPair instanceof ClassType))
    {
      paramTranslator.error('e', "invalid specifier for 'require'");
      return false;
      if ((localObject3 instanceof CharSequence))
      {
        paramPair = localObject3.toString();
        localObject1 = lookupModuleFromSourcePath(paramPair, paramScopeExp);
        if (localObject1 == null)
        {
          paramTranslator.error('e', "malformed URL: " + paramPair);
          return false;
        }
        return importDefinitions(null, (ModuleInfo)localObject1, null, paramVector, paramScopeExp, paramTranslator);
      }
      paramPair = (Pair)localObject1;
      if ((localObject3 instanceof Symbol))
      {
        paramPair = (Pair)localObject1;
        if (!paramTranslator.selfEvaluatingSymbol(localObject3))
        {
          localObject1 = paramTranslator.getLanguage().getTypeFor(paramTranslator.rewrite(localObject3, false));
          paramPair = (Pair)localObject1;
          if ((localObject1 instanceof ClassType))
          {
            paramPair = (Pair)localObject1;
            if ((((Pair)localObject2).getCdr() instanceof Pair))
            {
              localObject2 = ((Pair)((Pair)localObject2).getCdr()).getCar();
              paramPair = (Pair)localObject1;
              if ((localObject2 instanceof CharSequence))
              {
                paramPair = localObject2.toString();
                localObject2 = lookupModuleFromSourcePath(paramPair, paramScopeExp);
                if (localObject2 == null)
                {
                  paramTranslator.error('e', "malformed URL: " + paramPair);
                  return false;
                }
                return importDefinitions(((Type)localObject1).getName(), (ModuleInfo)localObject2, null, paramVector, paramScopeExp, paramTranslator);
              }
            }
          }
        }
      }
    }
    try
    {
      localObject1 = ModuleInfo.find((ClassType)paramPair);
      importDefinitions(null, (ModuleInfo)localObject1, null, paramVector, paramScopeExp, paramTranslator);
      return true;
    }
    catch (Exception paramVector)
    {
      paramTranslator.error('e', "unknown class " + paramPair.getName());
    }
    return false;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\require.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */