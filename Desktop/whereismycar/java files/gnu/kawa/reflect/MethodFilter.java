package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Filter;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;

class MethodFilter
  implements Filter
{
  ClassType caller;
  int modifiers;
  int modmask;
  String name;
  int nlen;
  ObjectType receiver;
  
  public MethodFilter(String paramString, int paramInt1, int paramInt2, ClassType paramClassType, ObjectType paramObjectType)
  {
    this.name = paramString;
    this.nlen = paramString.length();
    this.modifiers = paramInt1;
    this.modmask = paramInt2;
    this.caller = paramClassType;
    this.receiver = paramObjectType;
  }
  
  public boolean select(Object paramObject)
  {
    Method localMethod = (Method)paramObject;
    paramObject = localMethod.getName();
    int i = localMethod.getModifiers();
    if (((this.modmask & i) != this.modifiers) || ((i & 0x1000) != 0) || (!((String)paramObject).startsWith(this.name))) {}
    do
    {
      return false;
      i = ((String)paramObject).length();
      if (i == this.nlen) {
        break;
      }
      if ((i == this.nlen + 2) && (((String)paramObject).charAt(this.nlen) == '$'))
      {
        int j = ((String)paramObject).charAt(this.nlen + 1);
        if ((j == 86) || (j == 88)) {
          break;
        }
      }
    } while ((i != this.nlen + 4) || (!((String)paramObject).endsWith("$V$X")));
    if ((this.receiver instanceof ClassType))
    {
      paramObject = (ClassType)this.receiver;
      if ((this.caller != null) && (!this.caller.isAccessible((ClassType)paramObject, this.receiver, localMethod.getModifiers()))) {
        break label188;
      }
    }
    label188:
    for (boolean bool = true;; bool = false)
    {
      return bool;
      paramObject = localMethod.getDeclaringClass();
      break;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\MethodFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */