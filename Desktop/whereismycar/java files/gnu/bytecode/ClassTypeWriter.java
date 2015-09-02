package gnu.bytecode;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

public class ClassTypeWriter
  extends PrintWriter
{
  public static final int PRINT_CONSTANT_POOL = 1;
  public static final int PRINT_CONSTANT_POOL_INDEXES = 2;
  public static final int PRINT_EXTRAS = 8;
  public static final int PRINT_VERBOSE = 15;
  public static final int PRINT_VERSION = 4;
  ClassType ctype;
  int flags;
  
  public ClassTypeWriter(ClassType paramClassType, OutputStream paramOutputStream, int paramInt)
  {
    super(paramOutputStream);
    this.ctype = paramClassType;
    this.flags = paramInt;
  }
  
  public ClassTypeWriter(ClassType paramClassType, Writer paramWriter, int paramInt)
  {
    super(paramWriter);
    this.ctype = paramClassType;
    this.flags = paramInt;
  }
  
  public static void print(ClassType paramClassType, PrintStream paramPrintStream, int paramInt)
  {
    paramClassType = new ClassTypeWriter(paramClassType, paramPrintStream, paramInt);
    paramClassType.print();
    paramClassType.flush();
  }
  
  public static void print(ClassType paramClassType, PrintWriter paramPrintWriter, int paramInt)
  {
    paramClassType = new ClassTypeWriter(paramClassType, paramPrintWriter, paramInt);
    paramClassType.print();
    paramClassType.flush();
  }
  
  CpoolEntry getCpoolEntry(int paramInt)
  {
    CpoolEntry[] arrayOfCpoolEntry = this.ctype.constants.pool;
    if ((arrayOfCpoolEntry == null) || (paramInt < 0) || (paramInt >= arrayOfCpoolEntry.length)) {
      return null;
    }
    return arrayOfCpoolEntry[paramInt];
  }
  
  public void print()
  {
    if ((this.flags & 0x4) != 0)
    {
      print("Classfile format major version: ");
      print(this.ctype.getClassfileMajorVersion());
      print(", minor version: ");
      print(this.ctype.getClassfileMinorVersion());
      println('.');
    }
    if ((this.flags & 0x1) != 0) {
      printConstantPool();
    }
    printClassInfo();
    printFields();
    printMethods();
    printAttributes();
  }
  
  public void print(ClassType paramClassType)
  {
    this.ctype = paramClassType;
    print();
  }
  
  public void printAttributes()
  {
    ClassType localClassType = this.ctype;
    println();
    print("Attributes (count: ");
    print(Attribute.count(localClassType));
    println("):");
    printAttributes(localClassType);
  }
  
  public void printAttributes(AttrContainer paramAttrContainer)
  {
    for (paramAttrContainer = paramAttrContainer.getAttributes(); paramAttrContainer != null; paramAttrContainer = paramAttrContainer.next) {
      paramAttrContainer.print(this);
    }
  }
  
  public void printClassInfo()
  {
    println();
    print("Access flags:");
    print(Access.toString(this.ctype.getModifiers(), 'C'));
    println();
    print("This class: ");
    printOptionalIndex(this.ctype.thisClassIndex);
    printConstantTersely(this.ctype.thisClassIndex, 7);
    print(" super: ");
    int[] arrayOfInt;
    if (this.ctype.superClassIndex == -1)
    {
      print("<unknown>");
      println();
      print("Interfaces (count: ");
      arrayOfInt = this.ctype.interfaceIndexes;
      if (arrayOfInt != null) {
        break label211;
      }
    }
    label211:
    for (int i = 0;; i = arrayOfInt.length)
    {
      print(i);
      print("):");
      println();
      int j = 0;
      while (j < i)
      {
        print("- Implements: ");
        int k = arrayOfInt[j];
        printOptionalIndex(k);
        printConstantTersely(k, 7);
        println();
        j += 1;
      }
      if (this.ctype.superClassIndex == 0)
      {
        print("0");
        break;
      }
      printOptionalIndex(this.ctype.superClassIndex);
      printConstantTersely(this.ctype.superClassIndex, 7);
      break;
    }
  }
  
  final void printConstantOperand(int paramInt)
  {
    print(' ');
    printOptionalIndex(paramInt);
    Object localObject = this.ctype.constants.pool;
    if ((localObject != null) && (paramInt >= 0) && (paramInt < localObject.length))
    {
      localObject = localObject[paramInt];
      if (localObject != null) {}
    }
    else
    {
      print("<invalid constant index>");
      return;
    }
    print('<');
    ((CpoolEntry)localObject).print(this, 1);
    print('>');
  }
  
  public void printConstantPool()
  {
    CpoolEntry[] arrayOfCpoolEntry = this.ctype.constants.pool;
    int j = this.ctype.constants.count;
    int i = 1;
    if (i <= j)
    {
      CpoolEntry localCpoolEntry = arrayOfCpoolEntry[i];
      if (localCpoolEntry == null) {}
      for (;;)
      {
        i += 1;
        break;
        print('#');
        print(localCpoolEntry.index);
        print(": ");
        localCpoolEntry.print(this, 2);
        println();
      }
    }
  }
  
  final void printConstantTersely(int paramInt1, int paramInt2)
  {
    printConstantTersely(getCpoolEntry(paramInt1), paramInt2);
  }
  
  final void printConstantTersely(CpoolEntry paramCpoolEntry, int paramInt)
  {
    if (paramCpoolEntry == null)
    {
      print("<invalid constant index>");
      return;
    }
    if (paramCpoolEntry.getTag() != paramInt)
    {
      print("<unexpected constant type ");
      paramCpoolEntry.print(this, 1);
      print('>');
      return;
    }
    paramCpoolEntry.print(this, 0);
  }
  
  final void printContantUtf8AsClass(int paramInt)
  {
    Object localObject = getCpoolEntry(paramInt);
    if ((localObject != null) && (((CpoolEntry)localObject).getTag() == 1))
    {
      localObject = ((CpoolUtf8)localObject).string;
      Type.printSignature((String)localObject, 0, ((String)localObject).length(), this);
      return;
    }
    printConstantTersely(paramInt, 1);
  }
  
  public void printFields()
  {
    println();
    print("Fields (count: ");
    print(this.ctype.fields_count);
    print("):");
    println();
    int i = 0;
    for (Field localField = this.ctype.fields; localField != null; localField = localField.next)
    {
      print("Field name: ");
      if (localField.name_index != 0) {
        printOptionalIndex(localField.name_index);
      }
      print(localField.getName());
      print(Access.toString(localField.flags, 'F'));
      print(" Signature: ");
      if (localField.signature_index != 0) {
        printOptionalIndex(localField.signature_index);
      }
      printSignature(localField.type);
      println();
      printAttributes(localField);
      i += 1;
    }
  }
  
  public void printMethod(Method paramMethod)
  {
    println();
    print("Method name:");
    if (paramMethod.name_index != 0) {
      printOptionalIndex(paramMethod.name_index);
    }
    print('"');
    print(paramMethod.getName());
    print('"');
    print(Access.toString(paramMethod.access_flags, 'M'));
    print(" Signature: ");
    if (paramMethod.signature_index != 0) {
      printOptionalIndex(paramMethod.signature_index);
    }
    print('(');
    int i = 0;
    while (i < paramMethod.arg_types.length)
    {
      if (i > 0) {
        print(',');
      }
      printSignature(paramMethod.arg_types[i]);
      i += 1;
    }
    print(')');
    printSignature(paramMethod.return_type);
    println();
    printAttributes(paramMethod);
  }
  
  public void printMethods()
  {
    println();
    print("Methods (count: ");
    print(this.ctype.methods_count);
    print("):");
    println();
    for (Method localMethod = this.ctype.methods; localMethod != null; localMethod = localMethod.next) {
      printMethod(localMethod);
    }
  }
  
  void printName(String paramString)
  {
    print(paramString);
  }
  
  public final void printOptionalIndex(int paramInt)
  {
    if ((this.flags & 0x2) != 0)
    {
      print('#');
      print(paramInt);
      print('=');
    }
  }
  
  public final void printOptionalIndex(CpoolEntry paramCpoolEntry)
  {
    printOptionalIndex(paramCpoolEntry.index);
  }
  
  public final void printQuotedString(String paramString)
  {
    print('"');
    int m = paramString.length();
    int j = 0;
    if (j < m)
    {
      int i = paramString.charAt(j);
      if (i == 34) {
        print("\\\"");
      }
      for (;;)
      {
        j += 1;
        break;
        if ((i >= 32) && (i < 127))
        {
          print(i);
        }
        else
        {
          if (i != 10) {
            break label82;
          }
          print("\\n");
        }
      }
      label82:
      print("\\u");
      int k = 4;
      for (;;)
      {
        k -= 1;
        if (k < 0) {
          break;
        }
        print(Character.forDigit(i >> k * 4 & 0xF, 16));
      }
    }
    print('"');
  }
  
  public final int printSignature(String paramString, int paramInt)
  {
    int j = paramString.length();
    if (paramInt >= j)
    {
      print("<empty signature>");
      return paramInt;
    }
    int i = Type.signatureLength(paramString, paramInt);
    if (i > 0)
    {
      String str = Type.signatureToName(paramString.substring(paramInt, paramInt + i));
      if (str != null)
      {
        print(str);
        return paramInt + i;
      }
    }
    char c = paramString.charAt(paramInt);
    if (c != '(')
    {
      print(c);
      return paramInt + 1;
    }
    i = paramInt + 1;
    print(c);
    paramInt = 0;
    for (;;)
    {
      if (i >= j)
      {
        print("<truncated method signature>");
        return i;
      }
      c = paramString.charAt(i);
      if (c == ')')
      {
        print(c);
        return printSignature(paramString, i + 1);
      }
      if (paramInt > 0) {
        print(',');
      }
      i = printSignature(paramString, i);
      paramInt += 1;
    }
  }
  
  public final void printSignature(Type paramType)
  {
    if (paramType == null)
    {
      print("<unknown type>");
      return;
    }
    printSignature(paramType.getSignature());
  }
  
  public final void printSignature(String paramString)
  {
    int i = printSignature(paramString, 0);
    if (i < paramString.length())
    {
      print("<trailing junk:");
      print(paramString.substring(i));
      print('>');
    }
  }
  
  public void printSpaces(int paramInt)
  {
    for (;;)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      print(' ');
    }
  }
  
  public void setClass(ClassType paramClassType)
  {
    this.ctype = paramClassType;
  }
  
  public void setFlags(int paramInt)
  {
    this.flags = paramInt;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\ClassTypeWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */