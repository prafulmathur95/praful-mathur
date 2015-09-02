package kawa.standard;

import gnu.math.NamedUnit;
import gnu.math.Quantity;
import gnu.math.Unit;
import kawa.lang.GenericError;

public class sleep
{
  public static void sleep(Quantity paramQuantity)
  {
    Unit localUnit = paramQuantity.unit();
    long l;
    int i;
    if ((localUnit == Unit.Empty) || (localUnit.dimensions() == Unit.second.dimensions()))
    {
      double d = paramQuantity.doubleValue();
      l = (1000.0D * d);
      i = (int)(1.0E9D * d - l * 1000000.0D);
    }
    try
    {
      Thread.sleep(l, i);
      return;
    }
    catch (InterruptedException paramQuantity)
    {
      throw new GenericError("sleep was interrupted");
    }
    throw new GenericError("bad unit for sleep");
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\standard\sleep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */