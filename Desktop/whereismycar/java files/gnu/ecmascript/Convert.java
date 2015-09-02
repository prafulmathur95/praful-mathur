package gnu.ecmascript;

public class Convert
{
  public static double toInteger(double paramDouble)
  {
    if (Double.isNaN(paramDouble)) {
      return 0.0D;
    }
    if (paramDouble < 0.0D) {
      return Math.ceil(paramDouble);
    }
    return Math.floor(paramDouble);
  }
  
  public static double toInteger(Object paramObject)
  {
    return toInteger(toNumber(paramObject));
  }
  
  public static double toNumber(Object paramObject)
  {
    double d = NaN.0D;
    if ((paramObject instanceof Number)) {
      d = ((Number)paramObject).doubleValue();
    }
    do
    {
      return d;
      if ((paramObject instanceof Boolean))
      {
        if (((Boolean)paramObject).booleanValue()) {
          return 1.0D;
        }
        return 0.0D;
      }
    } while (!(paramObject instanceof String));
    try
    {
      d = Double.valueOf((String)paramObject).doubleValue();
      return d;
    }
    catch (NumberFormatException paramObject) {}
    return NaN.0D;
  }
  
  public int toInt32(double paramDouble)
  {
    if ((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble))) {
      return 0;
    }
    return (int)paramDouble;
  }
  
  public int toInt32(Object paramObject)
  {
    return toInt32(toNumber(paramObject));
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\ecmascript\Convert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */