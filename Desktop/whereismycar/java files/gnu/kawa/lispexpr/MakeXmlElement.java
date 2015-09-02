package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.kawa.xml.MakeElement;
import gnu.kawa.xml.MakeText;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.xml.NamespaceBinding;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class MakeXmlElement
  extends Syntax
{
  public static final MakeXmlElement makeXml = new MakeXmlElement();
  static final ClassType typeNamespace = ClassType.make("gnu.mapping.Namespace");
  
  static
  {
    makeXml.setName("$make-xml$");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    Object localObject2 = (Pair)paramPair.getCdr();
    Object localObject1 = ((Pair)localObject2).getCar();
    Object localObject5 = ((Pair)localObject2).getCdr();
    int i = 0;
    NamespaceBinding localNamespaceBinding = paramTranslator.xmlElementNamespaces;
    localObject2 = localNamespaceBinding;
    if ((localObject1 instanceof Pair))
    {
      int j = i;
      if (i == 0)
      {
        paramTranslator.letStart();
        j = 1;
      }
      Pair localPair1 = (Pair)localObject1;
      Object localObject3 = (Pair)localPair1.getCar();
      localObject1 = (String)((Pair)localObject3).getCar();
      label108:
      Pair localPair2;
      if (((String)localObject1).length() == 0)
      {
        localObject1 = null;
        localObject3 = ((Pair)localObject3).getCdr();
        localObject4 = new StringBuilder();
        if (!(localObject3 instanceof Pair)) {
          break label249;
        }
        localPair2 = (Pair)localObject3;
        localObject3 = localPair2.getCar();
        if ((LList.listLength(localObject3, false) != 2) || (!(localObject3 instanceof Pair)) || (((Pair)localObject3).getCar() != MakeText.makeText)) {
          break label223;
        }
        localObject3 = ((Pair)((Pair)localObject3).getCdr()).getCar();
        label178:
        if (localObject3 != null) {
          break label238;
        }
        localObject3 = paramTranslator.pushPositionOf(localPair2);
        paramTranslator.error('e', "namespace URI must be literal");
        paramTranslator.popPositionOf(localObject3);
      }
      for (;;)
      {
        localObject3 = localPair2.getCdr();
        break label108;
        localObject1 = ((String)localObject1).intern();
        break;
        label223:
        localObject3 = paramTranslator.rewrite_car(localPair2, false).valueIfConstant();
        break label178;
        label238:
        ((StringBuilder)localObject4).append(localObject3);
      }
      label249:
      Object localObject4 = ((StringBuilder)localObject4).toString().intern();
      if (localObject4 == "")
      {
        localObject3 = null;
        label269:
        localObject3 = new NamespaceBinding((String)localObject1, (String)localObject3, (NamespaceBinding)localObject2);
        if (localObject1 != null) {
          break label350;
        }
        localObject2 = Namespace.valueOf((String)localObject4);
        localObject1 = "[default-element-namespace]";
      }
      for (;;)
      {
        paramTranslator.letVariable(Namespace.EmptyNamespace.getSymbol((String)localObject1), typeNamespace, new QuoteExp(localObject2)).setFlag(2121728L);
        localObject1 = localPair1.getCdr();
        localObject2 = localObject3;
        i = j;
        break;
        localObject3 = localObject4;
        break label269;
        label350:
        localObject2 = XmlNamespace.getInstance((String)localObject1, (String)localObject4);
      }
    }
    localObject1 = new MakeElement();
    ((MakeElement)localObject1).setNamespaceNodes((NamespaceBinding)localObject2);
    paramTranslator.xmlElementNamespaces = ((NamespaceBinding)localObject2);
    if (i != 0) {}
    try
    {
      paramTranslator.letEnter();
      localObject1 = paramTranslator.rewrite(Translator.makePair(paramPair, localObject1, localObject5));
      paramPair = (Pair)localObject1;
      if (i != 0) {
        paramPair = paramTranslator.letDone((Expression)localObject1);
      }
      return paramPair;
    }
    finally
    {
      paramTranslator.xmlElementNamespaces = localNamespaceBinding;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\MakeXmlElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */