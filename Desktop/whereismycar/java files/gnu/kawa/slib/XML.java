package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.xml.Document;
import gnu.kawa.xml.KAttr;
import gnu.kawa.xml.KComment;
import gnu.kawa.xml.KDocument;
import gnu.kawa.xml.KElement;
import gnu.kawa.xml.KProcessingInstruction;
import gnu.kawa.xml.OutputAsXML;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;

public class XML
  extends ModuleBody
{
  public static final XML $instance;
  static final SimpleSymbol Lit0;
  static final SimpleSymbol Lit1;
  static final SimpleSymbol Lit2 = (SimpleSymbol)new SimpleSymbol("attribute-name").readResolve();
  public static OutputAsXML as$Mnxml;
  public static final ModuleMethod attribute$Mnname;
  public static final Class comment;
  public static final ModuleMethod element$Mnname;
  public static final ModuleMethod parse$Mnxml$Mnfrom$Mnurl;
  public static final Class processing$Mninstruction;
  
  static
  {
    Lit1 = (SimpleSymbol)new SimpleSymbol("element-name").readResolve();
    Lit0 = (SimpleSymbol)new SimpleSymbol("parse-xml-from-url").readResolve();
    processing$Mninstruction = KProcessingInstruction.class;
    comment = KComment.class;
    $instance = new XML();
    XML localXML = $instance;
    parse$Mnxml$Mnfrom$Mnurl = new ModuleMethod(localXML, 1, Lit0, 4097);
    element$Mnname = new ModuleMethod(localXML, 2, Lit1, 4097);
    attribute$Mnname = new ModuleMethod(localXML, 3, Lit2, 4097);
    $instance.run();
  }
  
  public XML()
  {
    ModuleInfo.register(this);
  }
  
  public static Symbol attributeName(KAttr paramKAttr)
  {
    return paramKAttr.getNodeSymbol();
  }
  
  public static Symbol elementName(KElement paramKElement)
  {
    return paramKElement.getNodeSymbol();
  }
  
  public static KDocument parseXmlFromUrl(Object paramObject)
  {
    return Document.parse(paramObject);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 1: 
      return parseXmlFromUrl(paramObject);
    }
    try
    {
      paramModuleMethod = (KElement)paramObject;
      return elementName(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      try
      {
        paramModuleMethod = (KAttr)paramObject;
        return attributeName(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "attribute-name", 1, paramObject);
      }
      paramModuleMethod = paramModuleMethod;
      throw new WrongType(paramModuleMethod, "element-name", 1, paramObject);
    }
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    int i = -786431;
    switch (paramModuleMethod.selector)
    {
    default: 
      i = super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 3: 
    case 2: 
      do
      {
        do
        {
          return i;
        } while (!(paramObject instanceof KAttr));
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      } while (!(paramObject instanceof KElement));
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    }
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
    as$Mnxml = new OutputAsXML();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\slib\XML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */