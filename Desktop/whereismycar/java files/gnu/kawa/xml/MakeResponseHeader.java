package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;

public class MakeResponseHeader
  extends MethodProc
{
  public static MakeResponseHeader makeResponseHeader = new MakeResponseHeader();
  
  public void apply(CallContext paramCallContext)
  {
    String str = paramCallContext.getNextArg().toString();
    Object localObject = paramCallContext.getNextArg();
    paramCallContext.lastArg();
    paramCallContext = paramCallContext.consumer;
    paramCallContext.startAttribute(str);
    paramCallContext.write(localObject.toString());
    paramCallContext.endAttribute();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\MakeResponseHeader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */