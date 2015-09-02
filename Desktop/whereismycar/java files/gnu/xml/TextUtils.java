package gnu.xml;

import gnu.kawa.xml.KNode;
import gnu.lists.Consumer;
import gnu.lists.TreeList;
import gnu.mapping.Values;
import gnu.math.DFloNum;
import java.math.BigDecimal;

public class TextUtils
{
  public static String asString(Object paramObject)
  {
    if ((paramObject == Values.empty) || (paramObject == null)) {
      return "";
    }
    if ((paramObject instanceof Values)) {
      throw new ClassCastException();
    }
    StringBuffer localStringBuffer = new StringBuffer(100);
    stringValue(paramObject, localStringBuffer);
    return localStringBuffer.toString();
  }
  
  public static String replaceWhitespace(String paramString, boolean paramBoolean)
  {
    Object localObject1 = null;
    int i1 = paramString.length();
    int i;
    int j;
    if (paramBoolean)
    {
      i = 1;
      j = 0;
    }
    for (;;)
    {
      if (j >= i1) {
        break label343;
      }
      int k = j + 1;
      char c2 = paramString.charAt(j);
      label48:
      Object localObject2;
      if (c2 == ' ')
      {
        j = 1;
        c1 = c2;
        localObject2 = localObject1;
        if (localObject1 != null) {
          break label214;
        }
        if ((j != 2) && ((j != 1) || (i <= 0) || (!paramBoolean)))
        {
          c1 = c2;
          localObject2 = localObject1;
          if (j != 1) {
            break label214;
          }
          c1 = c2;
          localObject2 = localObject1;
          if (k != i1) {
            break label214;
          }
          c1 = c2;
          localObject2 = localObject1;
          if (!paramBoolean) {
            break label214;
          }
        }
        localObject2 = new StringBuilder();
        if (i <= 0) {
          break label202;
        }
      }
      label202:
      for (int m = k - 2;; m = k - 1)
      {
        int n = 0;
        while (n < m)
        {
          ((StringBuilder)localObject2).append(paramString.charAt(n));
          n += 1;
        }
        i = 0;
        break;
        if ((c2 == '\t') || (c2 == '\r') || (c2 == '\n'))
        {
          j = 2;
          break label48;
        }
        j = 0;
        break label48;
      }
      char c1 = ' ';
      label214:
      m = i;
      if (paramBoolean)
      {
        if ((i > 0) && (j == 0))
        {
          if ((localObject2 != null) && (((StringBuilder)localObject2).length() > 0)) {
            ((StringBuilder)localObject2).append(' ');
          }
          i = 0;
        }
        for (;;)
        {
          m = i;
          if (i <= 0) {
            break label316;
          }
          j = k;
          localObject1 = localObject2;
          break;
          if ((j == 2) || ((j == 1) && (i > 0))) {
            i = 2;
          } else if (j > 0) {
            i = 1;
          } else {
            i = 0;
          }
        }
      }
      label316:
      if (localObject2 != null) {
        ((StringBuilder)localObject2).append(c1);
      }
      j = k;
      i = m;
      localObject1 = localObject2;
    }
    label343:
    if (localObject1 != null) {
      paramString = ((StringBuilder)localObject1).toString();
    }
    return paramString;
  }
  
  public static String stringValue(Object paramObject)
  {
    StringBuffer localStringBuffer = new StringBuffer(100);
    int i;
    int j;
    if ((paramObject instanceof Values))
    {
      paramObject = (TreeList)paramObject;
      i = 0;
      j = ((TreeList)paramObject).getNextKind(i);
      if (j != 0) {}
    }
    for (;;)
    {
      return localStringBuffer.toString();
      if (j == 32) {
        stringValue(((TreeList)paramObject).getPosNext(i), localStringBuffer);
      }
      for (;;)
      {
        i = ((TreeList)paramObject).nextPos(i);
        break;
        ((TreeList)paramObject).stringValue(((TreeList)paramObject).posToDataIndex(i), localStringBuffer);
      }
      stringValue(paramObject, localStringBuffer);
    }
  }
  
  public static void stringValue(Object paramObject, StringBuffer paramStringBuffer)
  {
    Object localObject;
    if ((paramObject instanceof KNode))
    {
      paramObject = (KNode)paramObject;
      localObject = (NodeTree)((KNode)paramObject).sequence;
      ((NodeTree)localObject).stringValue(((NodeTree)localObject).posToDataIndex(((KNode)paramObject).ipos), paramStringBuffer);
    }
    for (;;)
    {
      return;
      if ((paramObject instanceof BigDecimal)) {
        localObject = XMLPrinter.formatDecimal((BigDecimal)paramObject);
      }
      while ((localObject != null) && (localObject != Values.empty))
      {
        paramStringBuffer.append(localObject);
        return;
        if (((paramObject instanceof Double)) || ((paramObject instanceof DFloNum)))
        {
          localObject = XMLPrinter.formatDouble(((Number)paramObject).doubleValue());
        }
        else
        {
          localObject = paramObject;
          if ((paramObject instanceof Float)) {
            localObject = XMLPrinter.formatFloat(((Number)paramObject).floatValue());
          }
        }
      }
    }
  }
  
  public static void textValue(Object paramObject, Consumer paramConsumer)
  {
    if ((paramObject == null) || (((paramObject instanceof Values)) && (((Values)paramObject).isEmpty()))) {
      return;
    }
    if ((paramObject instanceof String)) {}
    StringBuffer localStringBuffer;
    for (paramObject = (String)paramObject;; paramObject = localStringBuffer.toString())
    {
      paramConsumer.write((String)paramObject);
      return;
      localStringBuffer = new StringBuffer();
      if ((paramObject instanceof Values))
      {
        paramObject = ((Values)paramObject).getValues();
        int i = 0;
        while (i < paramObject.length)
        {
          if (i > 0) {
            localStringBuffer.append(' ');
          }
          stringValue(paramObject[i], localStringBuffer);
          i += 1;
        }
      }
      stringValue(paramObject, localStringBuffer);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\xml\TextUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */