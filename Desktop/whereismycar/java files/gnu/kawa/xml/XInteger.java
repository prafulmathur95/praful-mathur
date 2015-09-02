package gnu.kawa.xml;

import gnu.math.IntNum;

public class XInteger
  extends IntNum
{
  private XIntegerType type;
  
  XInteger(IntNum paramIntNum, XIntegerType paramXIntegerType)
  {
    this.words = paramIntNum.words;
    this.ival = paramIntNum.ival;
    this.type = paramXIntegerType;
  }
  
  public XIntegerType getIntegerType()
  {
    return this.type;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\XInteger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */