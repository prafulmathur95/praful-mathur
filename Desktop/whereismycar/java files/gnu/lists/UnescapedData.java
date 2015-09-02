package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class UnescapedData
  implements CharSequence, Externalizable
{
  String data;
  
  public UnescapedData() {}
  
  public UnescapedData(String paramString)
  {
    this.data = paramString;
  }
  
  public char charAt(int paramInt)
  {
    return this.data.charAt(paramInt);
  }
  
  public final boolean equals(Object paramObject)
  {
    return ((paramObject instanceof UnescapedData)) && (this.data.equals(paramObject.toString()));
  }
  
  public final String getData()
  {
    return this.data;
  }
  
  public final int hashCode()
  {
    if (this.data == null) {
      return 0;
    }
    return this.data.hashCode();
  }
  
  public int length()
  {
    return this.data.length();
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    this.data = ((String)paramObjectInput.readObject());
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2)
  {
    return new UnescapedData(this.data.substring(paramInt1, paramInt2));
  }
  
  public final String toString()
  {
    return this.data;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(this.data);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\UnescapedData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */