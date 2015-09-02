package gnu.q2.lang;

import gnu.mapping.InPort;
import gnu.mapping.Procedure1;

class Prompter
  extends Procedure1
{
  public Object apply1(Object paramObject)
  {
    paramObject = (InPort)paramObject;
    int i = ((InPort)paramObject).getLineNumber() + 1;
    char c2 = ((InPort)paramObject).readState;
    if (c2 == ']') {
      return "<!--Q2:" + i + "-->";
    }
    char c1 = c2;
    if (c2 == '\n') {
      c1 = '-';
    }
    return "#|--Q2:" + i + "|#" + c1;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\q2\lang\Prompter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */