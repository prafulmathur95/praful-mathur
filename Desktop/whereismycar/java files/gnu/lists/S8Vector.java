package gnu.lists;

public class S8Vector
  extends ByteVector
{
  public S8Vector()
  {
    this.data = ByteVector.empty;
  }
  
  public S8Vector(int paramInt)
  {
    this.data = new byte[paramInt];
    this.size = paramInt;
  }
  
  public S8Vector(int paramInt, byte paramByte)
  {
    byte[] arrayOfByte = new byte[paramInt];
    this.data = arrayOfByte;
    this.size = paramInt;
    for (;;)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      arrayOfByte[paramInt] = paramByte;
    }
  }
  
  public S8Vector(Sequence paramSequence)
  {
    this.data = new byte[paramSequence.size()];
    addAll(paramSequence);
  }
  
  public S8Vector(byte[] paramArrayOfByte)
  {
    this.data = paramArrayOfByte;
    this.size = paramArrayOfByte.length;
  }
  
  public int compareTo(Object paramObject)
  {
    return compareToInt(this, (S8Vector)paramObject);
  }
  
  public final Object get(int paramInt)
  {
    if (paramInt > this.size) {
      throw new IndexOutOfBoundsException();
    }
    return Convert.toObject(this.data[paramInt]);
  }
  
  public final Object getBuffer(int paramInt)
  {
    return Convert.toObject(this.data[paramInt]);
  }
  
  public int getElementKind()
  {
    return 18;
  }
  
  public String getTag()
  {
    return "s8";
  }
  
  public final int intAtBuffer(int paramInt)
  {
    return this.data[paramInt];
  }
  
  public Object setBuffer(int paramInt, Object paramObject)
  {
    byte b = this.data[paramInt];
    this.data[paramInt] = Convert.toByte(paramObject);
    return Convert.toObject(b);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\S8Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */