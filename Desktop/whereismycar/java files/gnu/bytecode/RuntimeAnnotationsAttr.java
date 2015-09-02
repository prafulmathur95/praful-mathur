package gnu.bytecode;

public class RuntimeAnnotationsAttr
  extends MiscAttr
{
  int numEntries;
  
  public RuntimeAnnotationsAttr(String paramString, byte[] paramArrayOfByte, AttrContainer paramAttrContainer)
  {
    super(paramString, paramArrayOfByte, 0, paramArrayOfByte.length);
    addToFrontOf(paramAttrContainer);
    this.numEntries = u2(0);
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter)
  {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", number of entries: ");
    paramClassTypeWriter.println(this.numEntries);
    int j = this.offset;
    this.offset = (j + 2);
    int i = 0;
    while (i < this.numEntries)
    {
      printAnnotation(2, paramClassTypeWriter);
      i += 1;
    }
    this.offset = j;
  }
  
  public void printAnnotation(int paramInt, ClassTypeWriter paramClassTypeWriter)
  {
    int i = u2();
    paramClassTypeWriter.printSpaces(paramInt);
    paramClassTypeWriter.printOptionalIndex(i);
    paramClassTypeWriter.print('@');
    paramClassTypeWriter.printContantUtf8AsClass(i);
    i = u2();
    paramClassTypeWriter.println();
    int j = paramInt + 2;
    paramInt = 0;
    while (paramInt < i)
    {
      int k = u2();
      paramClassTypeWriter.printSpaces(j);
      paramClassTypeWriter.printOptionalIndex(k);
      paramClassTypeWriter.printConstantTersely(k, 1);
      paramClassTypeWriter.print(" => ");
      printAnnotationElementValue(j, paramClassTypeWriter);
      paramClassTypeWriter.println();
      paramInt += 1;
    }
  }
  
  public void printAnnotationElementValue(int paramInt, ClassTypeWriter paramClassTypeWriter)
  {
    int i1 = u1();
    int i;
    int j;
    int k;
    int n;
    int m;
    if ((paramClassTypeWriter.flags & 0x8) != 0)
    {
      paramClassTypeWriter.print("[kind:");
      if ((i1 >= 65) && (i1 <= 122))
      {
        paramClassTypeWriter.print((char)i1);
        paramClassTypeWriter.print("] ");
      }
    }
    else
    {
      i = 0;
      j = 0;
      k = 0;
      n = 0;
      m = n;
      switch (i1)
      {
      }
    }
    for (;;)
    {
      return;
      paramClassTypeWriter.print(i1);
      break;
      m = n;
      if (0 == 0) {
        m = 3;
      }
      i = m;
      if (m == 0) {
        i = 5;
      }
      j = i;
      if (i == 0) {
        j = 6;
      }
      k = j;
      if (j == 0) {
        k = 4;
      }
      paramInt = k;
      if (k == 0) {
        paramInt = 1;
      }
      i = u2();
      Object localObject = paramClassTypeWriter.getCpoolEntry(i);
      paramClassTypeWriter.printOptionalIndex((CpoolEntry)localObject);
      if ((i1 == 90) && (localObject != null) && (((CpoolEntry)localObject).getTag() == 3))
      {
        localObject = (CpoolValue1)localObject;
        if ((((CpoolValue1)localObject).value == 0) || (((CpoolValue1)localObject).value == 1))
        {
          if (((CpoolValue1)localObject).value == 0) {}
          for (localObject = "false";; localObject = "true")
          {
            paramClassTypeWriter.print((String)localObject);
            return;
          }
        }
      }
      paramClassTypeWriter.printConstantTersely(i, paramInt);
      return;
      paramInt = u2();
      i = u2();
      paramClassTypeWriter.print("enum[");
      if ((paramClassTypeWriter.flags & 0x8) != 0) {
        paramClassTypeWriter.print("type:");
      }
      paramClassTypeWriter.printOptionalIndex(paramInt);
      paramClassTypeWriter.printContantUtf8AsClass(paramInt);
      if ((paramClassTypeWriter.flags & 0x8) != 0) {
        paramClassTypeWriter.print(" value:");
      }
      for (;;)
      {
        paramClassTypeWriter.printOptionalIndex(i);
        paramClassTypeWriter.printConstantTersely(i, 1);
        paramClassTypeWriter.print("]");
        return;
        paramClassTypeWriter.print(' ');
      }
      paramInt = u2();
      paramClassTypeWriter.printOptionalIndex(paramInt);
      paramClassTypeWriter.printContantUtf8AsClass(paramInt);
      return;
      paramClassTypeWriter.println();
      paramClassTypeWriter.printSpaces(paramInt + 2);
      printAnnotation(paramInt + 2, paramClassTypeWriter);
      return;
      j = u2();
      paramClassTypeWriter.print("array length:");
      paramClassTypeWriter.print(j);
      i = 0;
      while (i < j)
      {
        paramClassTypeWriter.println();
        paramClassTypeWriter.printSpaces(paramInt + 2);
        paramClassTypeWriter.print(i);
        paramClassTypeWriter.print(": ");
        printAnnotationElementValue(paramInt + 2, paramClassTypeWriter);
        i += 1;
      }
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\RuntimeAnnotationsAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */