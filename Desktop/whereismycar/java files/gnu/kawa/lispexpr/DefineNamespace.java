package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class DefineNamespace
  extends Syntax
{
  public static final String XML_NAMESPACE_MAGIC = "&xml&";
  public static final DefineNamespace define_namespace = new DefineNamespace();
  public static final DefineNamespace define_private_namespace = new DefineNamespace();
  public static final DefineNamespace define_xml_namespace = new DefineNamespace();
  private boolean makePrivate;
  private boolean makeXML;
  
  static
  {
    define_namespace.setName("define-namespace");
    define_private_namespace.setName("define-private-namespace");
    define_private_namespace.makePrivate = true;
    define_xml_namespace.setName("define-xml-namespace");
    define_xml_namespace.makeXML = true;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    return paramTranslator.syntaxError("define-namespace is only allowed in a <body>");
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    Pair localPair1;
    Pair localPair2;
    if ((paramPair.getCdr() instanceof Pair))
    {
      localPair1 = (Pair)paramPair.getCdr();
      if (((localPair1.getCar() instanceof Symbol)) && ((localPair1.getCdr() instanceof Pair)))
      {
        localPair2 = (Pair)localPair1.getCdr();
        if (localPair2.getCdr() == LList.Empty) {
          break label73;
        }
      }
    }
    paramTranslator.error('e', "invalid syntax for define-namespace");
    return false;
    label73:
    paramPair = (Symbol)localPair1.getCar();
    Declaration localDeclaration = paramScopeExp.getDefine(paramPair, 'w', paramTranslator);
    paramTranslator.push(localDeclaration);
    localDeclaration.setFlag(2375680L);
    if (this.makePrivate)
    {
      localDeclaration.setFlag(16777216L);
      localDeclaration.setPrivate(true);
      Translator.setLine(localDeclaration, localPair1);
      if (!(localPair2.getCar() instanceof CharSequence)) {
        break label287;
      }
      paramScopeExp = localPair2.getCar().toString();
      if (!paramScopeExp.startsWith("class:")) {
        break label240;
      }
      paramPair = ClassNamespace.getInstance(paramScopeExp, ClassType.make(paramScopeExp.substring(6)));
      localDeclaration.setType(ClassType.make("gnu.kawa.lispexpr.ClassNamespace"));
      label189:
      paramPair = new QuoteExp(paramPair);
      localDeclaration.setFlag(8192L);
    }
    for (;;)
    {
      localDeclaration.noteValue(paramPair);
      paramVector.addElement(SetExp.makeDefinition(localDeclaration, paramPair));
      return true;
      if (!(paramScopeExp instanceof ModuleExp)) {
        break;
      }
      localDeclaration.setCanRead(true);
      break;
      label240:
      if (this.makeXML)
      {
        paramPair = XmlNamespace.getInstance(paramPair.getName(), paramScopeExp);
        localDeclaration.setType(ClassType.make("gnu.kawa.xml.XmlNamespace"));
        break label189;
      }
      paramPair = Namespace.valueOf(paramScopeExp);
      localDeclaration.setType(ClassType.make("gnu.mapping.Namespace"));
      break label189;
      label287:
      paramPair = paramTranslator.rewrite_car(localPair2, false);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\DefineNamespace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */