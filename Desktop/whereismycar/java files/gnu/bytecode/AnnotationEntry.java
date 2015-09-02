package gnu.bytecode;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class AnnotationEntry
  implements Annotation
{
  ClassType annotationType;
  LinkedHashMap<String, Object> elementsValue = new LinkedHashMap(10);
  
  public void addMember(String paramString, Object paramObject)
  {
    this.elementsValue.put(paramString, paramObject);
  }
  
  public Class<? extends Annotation> annotationType()
  {
    return this.annotationType.getReflectClass();
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof AnnotationEntry)) {}
    do
    {
      return false;
      paramObject = (AnnotationEntry)paramObject;
    } while (!getAnnotationType().getName().equals(((AnnotationEntry)paramObject).getAnnotationType().getName()));
    Object localObject1 = this.elementsValue.entrySet().iterator();
    Object localObject2;
    for (;;)
    {
      if (((Iterator)localObject1).hasNext())
      {
        Object localObject3 = (Map.Entry)((Iterator)localObject1).next();
        localObject2 = (String)((Map.Entry)localObject3).getKey();
        localObject3 = ((Map.Entry)localObject3).getValue();
        localObject2 = ((AnnotationEntry)paramObject).elementsValue.get(localObject2);
        if (localObject3 != localObject2)
        {
          if ((localObject3 == null) || (localObject2 == null)) {
            break;
          }
          if (!localObject3.equals(localObject2)) {
            return false;
          }
        }
      }
    }
    paramObject = ((AnnotationEntry)paramObject).elementsValue.entrySet().iterator();
    for (;;)
    {
      if (((Iterator)paramObject).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)paramObject).next();
        localObject1 = (String)((Map.Entry)localObject2).getKey();
        localObject2 = ((Map.Entry)localObject2).getValue();
        localObject1 = this.elementsValue.get(localObject1);
        if (localObject1 != localObject2)
        {
          if ((localObject1 == null) || (localObject2 == null)) {
            break;
          }
          if (!localObject1.equals(localObject2)) {
            return false;
          }
        }
      }
    }
    return true;
  }
  
  public ClassType getAnnotationType()
  {
    return this.annotationType;
  }
  
  public int hashCode()
  {
    int i = 0;
    Iterator localIterator = this.elementsValue.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      i += (((String)localEntry.getKey()).hashCode() * 127 ^ localEntry.getValue().hashCode());
    }
    return i;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('@');
    localStringBuilder.append(getAnnotationType().getName());
    localStringBuilder.append('(');
    int i = 0;
    Iterator localIterator = this.elementsValue.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (i > 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append('=');
      localStringBuilder.append(localEntry.getValue());
      i += 1;
    }
    localStringBuilder.append(')');
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\AnnotationEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */