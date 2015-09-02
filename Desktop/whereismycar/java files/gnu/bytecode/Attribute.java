package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Attribute
{
  AttrContainer container;
  String name;
  int name_index;
  Attribute next;
  
  public Attribute(String paramString)
  {
    this.name = paramString;
  }
  
  public static void assignConstants(AttrContainer paramAttrContainer, ClassType paramClassType)
  {
    for (paramAttrContainer = paramAttrContainer.getAttributes(); paramAttrContainer != null; paramAttrContainer = paramAttrContainer.next) {
      if (!paramAttrContainer.isSkipped()) {
        paramAttrContainer.assignConstants(paramClassType);
      }
    }
  }
  
  public static int count(AttrContainer paramAttrContainer)
  {
    int i = 0;
    paramAttrContainer = paramAttrContainer.getAttributes();
    while (paramAttrContainer != null)
    {
      int j = i;
      if (!paramAttrContainer.isSkipped()) {
        j = i + 1;
      }
      paramAttrContainer = paramAttrContainer.next;
      i = j;
    }
    return i;
  }
  
  public static Attribute get(AttrContainer paramAttrContainer, String paramString)
  {
    for (paramAttrContainer = paramAttrContainer.getAttributes(); paramAttrContainer != null; paramAttrContainer = paramAttrContainer.next) {
      if (paramAttrContainer.getName() == paramString) {
        return paramAttrContainer;
      }
    }
    return null;
  }
  
  public static int getLengthAll(AttrContainer paramAttrContainer)
  {
    int i = 0;
    paramAttrContainer = paramAttrContainer.getAttributes();
    while (paramAttrContainer != null)
    {
      int j = i;
      if (!paramAttrContainer.isSkipped()) {
        j = i + (paramAttrContainer.getLength() + 6);
      }
      paramAttrContainer = paramAttrContainer.next;
      i = j;
    }
    return i;
  }
  
  public static void writeAll(AttrContainer paramAttrContainer, DataOutputStream paramDataOutputStream)
    throws IOException
  {
    paramDataOutputStream.writeShort(count(paramAttrContainer));
    paramAttrContainer = paramAttrContainer.getAttributes();
    if (paramAttrContainer != null)
    {
      if (paramAttrContainer.isSkipped()) {}
      for (;;)
      {
        paramAttrContainer = paramAttrContainer.next;
        break;
        if (paramAttrContainer.name_index == 0) {
          throw new Error("Attribute.writeAll called without assignConstants");
        }
        paramDataOutputStream.writeShort(paramAttrContainer.name_index);
        paramDataOutputStream.writeInt(paramAttrContainer.getLength());
        paramAttrContainer.write(paramDataOutputStream);
      }
    }
  }
  
  public void addToFrontOf(AttrContainer paramAttrContainer)
  {
    setContainer(paramAttrContainer);
    setNext(paramAttrContainer.getAttributes());
    paramAttrContainer.setAttributes(this);
  }
  
  public void assignConstants(ClassType paramClassType)
  {
    if (this.name_index == 0) {
      this.name_index = paramClassType.getConstants().addUtf8(this.name).getIndex();
    }
  }
  
  public final AttrContainer getContainer()
  {
    return this.container;
  }
  
  public abstract int getLength();
  
  public final String getName()
  {
    return this.name;
  }
  
  public final int getNameIndex()
  {
    return this.name_index;
  }
  
  public final Attribute getNext()
  {
    return this.next;
  }
  
  public final boolean isSkipped()
  {
    return this.name_index < 0;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter)
  {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.println(getLength());
  }
  
  public final void setContainer(AttrContainer paramAttrContainer)
  {
    this.container = paramAttrContainer;
  }
  
  public final void setName(String paramString)
  {
    this.name = paramString.intern();
  }
  
  public final void setNameIndex(int paramInt)
  {
    this.name_index = paramInt;
  }
  
  public final void setNext(Attribute paramAttribute)
  {
    this.next = paramAttribute;
  }
  
  public final void setSkipped()
  {
    this.name_index = -1;
  }
  
  public final void setSkipped(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = -1;; i = 0)
    {
      this.name_index = i;
      return;
    }
  }
  
  public abstract void write(DataOutputStream paramDataOutputStream)
    throws IOException;
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\Attribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */