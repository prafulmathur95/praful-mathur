package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ModuleContext;
import gnu.mapping.SimpleSymbol;
import kawa.lang.SyntaxForm;

public class IfFeature
{
  public static boolean hasFeature(String paramString)
  {
    if (paramString == "kawa") {}
    label143:
    do
    {
      int i;
      do
      {
        do
        {
          do
          {
            return true;
          } while ((paramString == "srfi-0") || (paramString == "srfi-4") || (paramString == "srfi-6") || (paramString == "srfi-8") || (paramString == "srfi-9") || (paramString == "srfi-11") || (paramString == "srfi-16") || (paramString == "srfi-17") || (paramString == "srfi-23") || (paramString == "srfi-25") || (paramString == "srfi-26") || (paramString == "srfi-28") || (paramString == "srfi-30") || (paramString == "srfi-39"));
          if ((paramString != "in-http-server") && (paramString != "in-servlet")) {
            break label143;
          }
          i = ModuleContext.getContext().getFlags();
          if (paramString != "in-http-server") {
            break;
          }
        } while ((ModuleContext.IN_HTTP_SERVER & i) != 0);
        return false;
        if (paramString != "in-servlet") {
          break;
        }
      } while ((ModuleContext.IN_SERVLET & i) != 0);
      return false;
      paramString = ("%provide%" + paramString).intern();
      paramString = Compilation.getCurrent().lookup(paramString, -1);
    } while ((paramString != null) && (!paramString.getFlag(65536L)));
    return false;
  }
  
  public static boolean testFeature(Object paramObject)
  {
    Object localObject = paramObject;
    if ((paramObject instanceof SyntaxForm)) {
      localObject = ((SyntaxForm)paramObject).getDatum();
    }
    if (((localObject instanceof String)) || ((localObject instanceof SimpleSymbol))) {
      return hasFeature(localObject.toString());
    }
    return false;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\IfFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */