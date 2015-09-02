package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class StackMapTableAttr
  extends MiscAttr
{
  public static boolean compressStackMapTable = true;
  int countLocals;
  int countStack;
  int[] encodedLocals;
  int[] encodedStack;
  int numEntries;
  int prevPosition = -1;
  
  public StackMapTableAttr()
  {
    super("StackMapTable", null, 0, 0);
    put2(0);
  }
  
  public StackMapTableAttr(byte[] paramArrayOfByte, CodeAttr paramCodeAttr)
  {
    super("StackMapTable", paramArrayOfByte, 0, paramArrayOfByte.length);
    addToFrontOf(paramCodeAttr);
    this.numEntries = u2(0);
  }
  
  static int[] reallocBuffer(int[] paramArrayOfInt, int paramInt)
  {
    if (paramArrayOfInt == null) {
      arrayOfInt = new int[paramInt + 10];
    }
    do
    {
      return arrayOfInt;
      arrayOfInt = paramArrayOfInt;
    } while (paramInt <= paramArrayOfInt.length);
    int[] arrayOfInt = new int[paramInt + 10];
    System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, paramArrayOfInt.length);
    return arrayOfInt;
  }
  
  public void emitStackMapEntry(Label paramLabel, CodeAttr paramCodeAttr)
  {
    int i1 = paramLabel.position - this.prevPosition - 1;
    int n = paramLabel.localTypes.length;
    Object localObject;
    if (n > this.encodedLocals.length)
    {
      localObject = new int[this.encodedLocals.length + n];
      System.arraycopy(this.encodedLocals, 0, localObject, 0, this.countLocals);
      this.encodedLocals = ((int[])localObject);
    }
    int i2 = paramLabel.stackTypes.length;
    if (i2 > this.encodedStack.length)
    {
      localObject = new int[this.encodedStack.length + i2];
      System.arraycopy(this.encodedStack, 0, localObject, 0, this.countStack);
      this.encodedStack = ((int[])localObject);
    }
    int k = 0;
    int j = 0;
    int i = 0;
    int m;
    while (j < n)
    {
      int i4 = this.encodedLocals[i];
      int i3 = encodeVerificationType(paramLabel.localTypes[j], paramCodeAttr);
      m = k;
      if (i4 == i3)
      {
        m = k;
        if (k == i) {
          m = i + 1;
        }
      }
      this.encodedLocals[i] = i3;
      if (i3 != 3)
      {
        k = j;
        if (i3 != 4) {}
      }
      else
      {
        k = j + 1;
      }
      j = k + 1;
      i += 1;
      k = m;
    }
    for (;;)
    {
      if ((j > 0) && (this.encodedLocals[(j - 1)] == 0))
      {
        j -= 1;
      }
      else
      {
        i = 0;
        m = 0;
        while (i < i2)
        {
          n = this.encodedStack[m];
          Type localType = paramLabel.stackTypes[i];
          n = i;
          localObject = localType;
          if (localType == Type.voidType)
          {
            localObject = paramLabel.stackTypes;
            n = i + 1;
            localObject = localObject[n];
          }
          i = encodeVerificationType((Type)localObject, paramCodeAttr);
          this.encodedStack[m] = i;
          i = n + 1;
          m += 1;
        }
        n = j - this.countLocals;
        if ((compressStackMapTable) && (n == 0) && (j == k) && (m <= 1)) {
          if (m == 0) {
            if (i1 <= 63) {
              put1(i1);
            }
          }
        }
        for (;;)
        {
          this.countLocals = j;
          this.countStack = m;
          this.prevPosition = paramLabel.position;
          this.numEntries += 1;
          return;
          put1(251);
          put2(i1);
          continue;
          if (i1 <= 63) {
            put1(i1 + 64);
          }
          for (;;)
          {
            emitVerificationType(this.encodedStack[0]);
            break;
            put1(247);
            put2(i1);
          }
          if ((compressStackMapTable) && (m == 0) && (j < this.countLocals) && (k == j) && (n >= -3))
          {
            put1(n + 251);
            put2(i1);
          }
          else if ((compressStackMapTable) && (m == 0) && (this.countLocals == k) && (n <= 3))
          {
            put1(n + 251);
            put2(i1);
            i = 0;
            while (i < n)
            {
              emitVerificationType(this.encodedLocals[(k + i)]);
              i += 1;
            }
          }
          else
          {
            put1(255);
            put2(i1);
            put2(j);
            i = 0;
            while (i < j)
            {
              emitVerificationType(this.encodedLocals[i]);
              i += 1;
            }
            put2(m);
            i = 0;
            while (i < m)
            {
              emitVerificationType(this.encodedStack[i]);
              i += 1;
            }
          }
        }
        j = i;
      }
    }
  }
  
  void emitVerificationType(int paramInt)
  {
    int i = paramInt & 0xFF;
    put1(i);
    if (i >= 7) {
      put2(paramInt >> 8);
    }
  }
  
  int encodeVerificationType(Type paramType, CodeAttr paramCodeAttr)
  {
    if (paramType == null) {
      return 0;
    }
    if ((paramType instanceof UninitializedType))
    {
      paramType = ((UninitializedType)paramType).label;
      if (paramType == null) {
        return 6;
      }
      return paramType.position << 8 | 0x8;
    }
    paramType = paramType.getImplementationType();
    if ((paramType instanceof PrimType))
    {
      switch (paramType.signature.charAt(0))
      {
      default: 
        return 0;
      case 'B': 
      case 'C': 
      case 'I': 
      case 'S': 
      case 'Z': 
        return 1;
      case 'J': 
        return 4;
      case 'F': 
        return 2;
      }
      return 3;
    }
    if (paramType == Type.nullType) {
      return 5;
    }
    return paramCodeAttr.getConstants().addClass((ObjectType)paramType).index << 8 | 0x7;
  }
  
  int extractVerificationType(int paramInt1, int paramInt2)
  {
    int i;
    if (paramInt2 != 7)
    {
      i = paramInt2;
      if (paramInt2 != 8) {}
    }
    else
    {
      i = paramInt2 | u2(paramInt1 + 1) << 8;
    }
    return i;
  }
  
  int extractVerificationTypes(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt)
  {
    int i = paramInt1;
    paramInt1 = paramInt3;
    paramInt3 = paramInt2;
    paramInt2 = i;
    for (;;)
    {
      i = paramInt3 - 1;
      if (i < 0) {
        return paramInt2;
      }
      if (paramInt2 < this.dataLength) {
        break;
      }
      paramInt3 = -1;
      paramArrayOfInt[paramInt1] = paramInt3;
      paramInt1 += 1;
      paramInt3 = i;
    }
    paramInt3 = this.data[paramInt2];
    int j = extractVerificationType(paramInt2, paramInt3);
    if ((paramInt3 == 7) || (paramInt3 == 8)) {}
    for (paramInt3 = 3;; paramInt3 = 1)
    {
      paramInt2 += paramInt3;
      paramInt3 = j;
      break;
    }
    return paramInt2;
  }
  
  public Method getMethod()
  {
    return ((CodeAttr)this.container).getMethod();
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter)
  {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", number of entries: ");
    paramClassTypeWriter.println(this.numEntries);
    int j = 2;
    int k = -1;
    Object localObject = getMethod();
    int[] arrayOfInt = null;
    int i;
    int n;
    int m;
    if (((Method)localObject).getStaticFlag())
    {
      i = 0;
      n = i + ((Method)localObject).arg_types.length;
      m = 0;
      i = j;
      j = n;
    }
    for (;;)
    {
      if ((m >= this.numEntries) || (i >= this.dataLength))
      {
        return;
        i = 1;
        break;
      }
      n = i + 1;
      int i1 = u1(i);
      i = k + 1;
      if (i1 <= 127)
      {
        k = i + (i1 & 0x3F);
        i = n;
        paramClassTypeWriter.print("  offset: ");
        paramClassTypeWriter.print(k);
        if (i1 > 63) {
          break label221;
        }
        paramClassTypeWriter.println(" - same_frame");
      }
      for (;;)
      {
        if (i >= 0) {
          break label607;
        }
        paramClassTypeWriter.println("<ERROR - missing data>");
        return;
        if (n + 1 >= this.dataLength) {
          return;
        }
        k = i + u2(n);
        i = n + 2;
        break;
        label221:
        if ((i1 <= 127) || (i1 == 247))
        {
          if (i1 <= 127) {}
          for (localObject = " - same_locals_1_stack_item_frame";; localObject = " - same_locals_1_stack_item_frame_extended")
          {
            paramClassTypeWriter.println((String)localObject);
            arrayOfInt = reallocBuffer(arrayOfInt, 1);
            i = extractVerificationTypes(i, 1, 0, arrayOfInt);
            printVerificationTypes(arrayOfInt, 0, 1, paramClassTypeWriter);
            break;
          }
        }
        if (i1 <= 246)
        {
          paramClassTypeWriter.print(" - tag reserved for future use - ");
          paramClassTypeWriter.println(i1);
          return;
        }
        if (i1 <= 250)
        {
          n = 251 - i1;
          paramClassTypeWriter.print(" - chop_frame - undefine ");
          paramClassTypeWriter.print(n);
          paramClassTypeWriter.println(" locals");
          j -= n;
        }
        else if (i1 == 251)
        {
          paramClassTypeWriter.println(" - same_frame_extended");
        }
        else if (i1 <= 254)
        {
          n = i1 - 251;
          paramClassTypeWriter.print(" - append_frame - define ");
          paramClassTypeWriter.print(n);
          paramClassTypeWriter.println(" more locals");
          arrayOfInt = reallocBuffer(arrayOfInt, j + n);
          i = extractVerificationTypes(i, n, j, arrayOfInt);
          printVerificationTypes(arrayOfInt, j, n, paramClassTypeWriter);
          j += n;
        }
        else
        {
          if (i + 1 >= this.dataLength) {
            return;
          }
          j = u2(i);
          paramClassTypeWriter.print(" - full_frame.  Locals count: ");
          paramClassTypeWriter.println(j);
          arrayOfInt = reallocBuffer(arrayOfInt, j);
          n = extractVerificationTypes(i + 2, j, 0, arrayOfInt);
          printVerificationTypes(arrayOfInt, 0, j, paramClassTypeWriter);
          if (n + 1 >= this.dataLength) {
            return;
          }
          i1 = u2(n);
          paramClassTypeWriter.print("    (end of locals)");
          i = Integer.toString(k).length();
          for (;;)
          {
            i -= 1;
            if (i < 0) {
              break;
            }
            paramClassTypeWriter.print(' ');
          }
          paramClassTypeWriter.print("       Stack count: ");
          paramClassTypeWriter.println(i1);
          arrayOfInt = reallocBuffer(arrayOfInt, i1);
          i = extractVerificationTypes(n + 2, i1, 0, arrayOfInt);
          printVerificationTypes(arrayOfInt, 0, i1, paramClassTypeWriter);
        }
      }
      label607:
      m += 1;
    }
  }
  
  void printVerificationType(int paramInt, ClassTypeWriter paramClassTypeWriter)
  {
    int i = paramInt & 0xFF;
    switch (i)
    {
    default: 
      paramClassTypeWriter.print("<bad verification type tag " + i + '>');
      return;
    case 0: 
      paramClassTypeWriter.print("top/unavailable");
      return;
    case 1: 
      paramClassTypeWriter.print("integer");
      return;
    case 2: 
      paramClassTypeWriter.print("float");
      return;
    case 3: 
      paramClassTypeWriter.print("double");
      return;
    case 4: 
      paramClassTypeWriter.print("long");
      return;
    case 5: 
      paramClassTypeWriter.print("null");
      return;
    case 6: 
      paramClassTypeWriter.print("uninitialized this");
      return;
    case 7: 
      paramInt >>= 8;
      paramClassTypeWriter.printOptionalIndex(paramInt);
      paramClassTypeWriter.printConstantTersely(paramInt, 7);
      return;
    }
    paramClassTypeWriter.print("uninitialized object created at ");
    paramClassTypeWriter.print(paramInt >> 8);
  }
  
  void printVerificationTypes(int[] paramArrayOfInt, int paramInt1, int paramInt2, ClassTypeWriter paramClassTypeWriter)
  {
    int i = 0;
    int j = 0;
    if (j < paramInt1 + paramInt2)
    {
      int k = paramArrayOfInt[j];
      int m = k & 0xFF;
      if (j >= paramInt1)
      {
        paramClassTypeWriter.print("  ");
        if (i < 100) {
          break label114;
        }
      }
      for (;;)
      {
        paramClassTypeWriter.print(i);
        paramClassTypeWriter.print(": ");
        printVerificationType(k, paramClassTypeWriter);
        paramClassTypeWriter.println();
        k = i + 1;
        if (m != 3)
        {
          i = k;
          if (m != 4) {}
        }
        else
        {
          i = k + 1;
        }
        j += 1;
        break;
        label114:
        if (i < 10) {
          paramClassTypeWriter.print(' ');
        }
        paramClassTypeWriter.print(' ');
      }
    }
  }
  
  public void write(DataOutputStream paramDataOutputStream)
    throws IOException
  {
    put2(0, this.numEntries);
    super.write(paramDataOutputStream);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\StackMapTableAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */