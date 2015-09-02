package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.lists.XConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import gnu.xml.TextUtils;
import gnu.xml.XMLFilter;

public class CommentConstructor
  extends MethodProc
{
  public static final CommentConstructor commentConstructor = new CommentConstructor();
  
  public void apply(CallContext paramCallContext)
  {
    Consumer localConsumer = paramCallContext.consumer;
    XMLFilter localXMLFilter = NodeConstructor.pushNodeContext(paramCallContext);
    for (;;)
    {
      StringBuffer localStringBuffer;
      int k;
      Object localObject3;
      try
      {
        localStringBuffer = new StringBuffer();
        Object localObject2 = Location.UNBOUND;
        i = 1;
        k = 0;
        localObject3 = paramCallContext.getNextArg(localObject2);
        if (localObject3 == localObject2)
        {
          i = localStringBuffer.length();
          localObject2 = new char[i];
          localStringBuffer.getChars(0, i, (char[])localObject2, 0);
          localXMLFilter.writeComment((char[])localObject2, 0, i);
          return;
        }
        if ((localObject3 instanceof Values))
        {
          localObject3 = (Values)localObject3;
          j = 0;
          int m = ((Values)localObject3).nextPos(j);
          j = i;
          if (m == 0) {
            break label188;
          }
          if (i == 0) {
            localStringBuffer.append(' ');
          }
          i = 0;
          TextUtils.stringValue(((Values)localObject3).getPosPrevious(m), localStringBuffer);
          j = m;
          continue;
        }
        if (i != 0) {
          break label178;
        }
      }
      finally
      {
        NodeConstructor.popNodeContext(localConsumer, paramCallContext);
      }
      localStringBuffer.append(' ');
      label178:
      int j = 0;
      TextUtils.stringValue(localObject3, localStringBuffer);
      label188:
      k += 1;
      int i = j;
    }
  }
  
  public int numArgs()
  {
    return 4097;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\CommentConstructor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */