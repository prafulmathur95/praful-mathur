package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;

public class CodeAttr
  extends Attribute
  implements AttrContainer
{
  public static final int DONT_USE_JSR = 2;
  static final int FIXUP_CASE = 3;
  static final int FIXUP_DEFINE = 1;
  static final int FIXUP_DELETE3 = 8;
  static final int FIXUP_GOTO = 4;
  static final int FIXUP_JSR = 5;
  static final int FIXUP_LINE_NUMBER = 15;
  static final int FIXUP_LINE_PC = 14;
  static final int FIXUP_MOVE = 9;
  static final int FIXUP_MOVE_TO_END = 10;
  static final int FIXUP_NONE = 0;
  static final int FIXUP_SWITCH = 2;
  static final int FIXUP_TRANSFER = 6;
  static final int FIXUP_TRANSFER2 = 7;
  static final int FIXUP_TRY = 11;
  static final int FIXUP_TRY_END = 12;
  static final int FIXUP_TRY_HANDLER = 13;
  public static final int GENERATE_STACK_MAP_TABLE = 1;
  public static boolean instructionLineMode = false;
  int PC;
  int SP;
  Attribute attributes;
  byte[] code;
  ExitableBlock currentExitableBlock;
  short[] exception_table;
  int exception_table_length;
  int exitableBlockLevel;
  int fixup_count;
  Label[] fixup_labels;
  int[] fixup_offsets;
  int flags;
  IfState if_stack;
  LineNumbersAttr lines;
  Type[] local_types;
  public LocalVarsAttr locals;
  private int max_locals;
  private int max_stack;
  Label previousLabel;
  SourceDebugExtAttr sourceDbgExt;
  public StackMapTableAttr stackMap;
  public Type[] stack_types;
  TryState try_stack;
  private boolean unreachable_here;
  boolean[] varsSetInCurrentBlock;
  
  public CodeAttr(Method paramMethod)
  {
    super("Code");
    addToFrontOf(paramMethod);
    paramMethod.code = this;
    if (paramMethod.getDeclaringClass().getClassfileMajorVersion() >= 50) {
      this.flags |= 0x3;
    }
  }
  
  private int adjustTypedOp(char paramChar)
  {
    switch (paramChar)
    {
    default: 
      return 4;
    case 'I': 
      return 0;
    case 'J': 
      return 1;
    case 'F': 
      return 2;
    case 'D': 
      return 3;
    case 'B': 
    case 'Z': 
      return 5;
    case 'C': 
      return 6;
    }
    return 7;
  }
  
  private int adjustTypedOp(Type paramType)
  {
    return adjustTypedOp(paramType.getSignature().charAt(0));
  }
  
  public static final String calculateSplit(String paramString)
  {
    int i2 = paramString.length();
    StringBuffer localStringBuffer = new StringBuffer(20);
    int m = 0;
    int j = 0;
    int i = 0;
    if (i < i2)
    {
      int k = paramString.charAt(i);
      if (k >= 2048) {
        k = 3;
      }
      for (;;)
      {
        int i1 = j;
        int n = m;
        if (j + k > 65535)
        {
          localStringBuffer.append((char)(i - m));
          n = i;
          i1 = 0;
        }
        j = i1 + k;
        i += 1;
        m = n;
        break;
        if ((k >= 128) || (k == 0)) {
          k = 2;
        } else {
          k = 1;
        }
      }
    }
    localStringBuffer.append((char)(i2 - m));
    return localStringBuffer.toString();
  }
  
  public static boolean castNeeded(Type paramType1, Type paramType2)
  {
    Type localType1 = paramType1;
    Type localType2 = paramType2;
    if ((paramType1 instanceof UninitializedType))
    {
      localType1 = ((UninitializedType)paramType1).getImplementationType();
      localType2 = paramType2;
    }
    for (;;)
    {
      if (localType1 == localType2) {
        return false;
      }
      if (((localType2 instanceof ClassType)) && ((localType1 instanceof ClassType)) && (((ClassType)localType1).isSubclass((ClassType)localType2))) {
        return false;
      }
      if ((!(localType2 instanceof ArrayType)) || (!(localType1 instanceof ArrayType))) {
        break;
      }
      localType2 = ((ArrayType)localType2).getComponentType();
      localType1 = ((ArrayType)localType1).getComponentType();
    }
    return true;
  }
  
  private void emitBinop(int paramInt)
  {
    Type localType1 = popType().promote();
    Type localType2 = popType();
    Type localType3 = localType2.promote();
    if ((localType3 != localType1) || (!(localType3 instanceof PrimType))) {
      throw new Error("non-matching or bad types in binary operation");
    }
    emitTypedOp(paramInt, localType3);
    pushType(localType2);
  }
  
  private void emitBinop(int paramInt, char paramChar)
  {
    popType();
    popType();
    emitTypedOp(paramInt, paramChar);
    pushType(Type.signatureToPrimitive(paramChar));
  }
  
  private void emitCheckcast(Type paramType, int paramInt)
  {
    reserve(3);
    popType();
    put1(paramInt);
    if ((paramType instanceof ObjectType))
    {
      putIndex2(getConstants().addClass((ObjectType)paramType));
      return;
    }
    throw new Error("unimplemented type " + paramType + " in emitCheckcast/emitInstanceof");
  }
  
  private final void emitFieldop(Field paramField, int paramInt)
  {
    reserve(3);
    put1(paramInt);
    putIndex2(getConstants().addFieldRef(paramField));
  }
  
  private void emitShift(int paramInt)
  {
    Type localType1 = popType().promote();
    Type localType2 = popType();
    Type localType3 = localType2.promote();
    if ((localType3 != Type.intType) && (localType3 != Type.longType)) {
      throw new Error("the value shifted must be an int or a long");
    }
    if (localType1 != Type.intType) {
      throw new Error("the amount of shift must be an int");
    }
    emitTypedOp(paramInt, localType3);
    pushType(localType2);
  }
  
  private void emitTryEnd(boolean paramBoolean)
  {
    if (this.try_stack.tryClauseDone) {
      return;
    }
    this.try_stack.tryClauseDone = true;
    if (this.try_stack.finally_subr != null) {
      this.try_stack.exception = addLocal(Type.javalangThrowableType);
    }
    gotoFinallyOrEnd(paramBoolean);
    this.try_stack.end_try = getLabel();
  }
  
  private void emitTypedOp(int paramInt, char paramChar)
  {
    reserve(1);
    put1(adjustTypedOp(paramChar) + paramInt);
  }
  
  private void emitTypedOp(int paramInt, Type paramType)
  {
    reserve(1);
    put1(adjustTypedOp(paramType) + paramInt);
  }
  
  private final int fixupKind(int paramInt)
  {
    return this.fixup_offsets[paramInt] & 0xF;
  }
  
  private final int fixupOffset(int paramInt)
  {
    return this.fixup_offsets[paramInt] >> 4;
  }
  
  private void gotoFinallyOrEnd(boolean paramBoolean)
  {
    if (reachableHere())
    {
      if (this.try_stack.saved_result != null) {
        emitStore(this.try_stack.saved_result);
      }
      if (this.try_stack.end_label == null) {
        this.try_stack.end_label = new Label();
      }
      if ((this.try_stack.finally_subr != null) && (!useJsr())) {
        break label102;
      }
      if (this.try_stack.finally_subr != null) {
        emitJsr(this.try_stack.finally_subr);
      }
      emitGoto(this.try_stack.end_label);
    }
    label102:
    do
    {
      return;
      if (this.try_stack.exitCases != null) {
        emitPushInt(0);
      }
      emitPushNull();
    } while (paramBoolean);
    emitGoto(this.try_stack.finally_subr);
  }
  
  private Label mergeLabels(Label paramLabel1, Label paramLabel2)
  {
    if (paramLabel1 != null) {
      paramLabel2.setTypes(paramLabel1);
    }
    return paramLabel2;
  }
  
  private void print(String paramString, int paramInt, PrintWriter paramPrintWriter)
  {
    int i = 0;
    int j = -1;
    while (paramInt >= 0)
    {
      i = j + 1;
      j = paramString.indexOf(';', i);
      paramInt -= 1;
    }
    paramPrintWriter.write(paramString, i, j - i);
  }
  
  private int readInt(int paramInt)
  {
    return readUnsignedShort(paramInt) << 16 | readUnsignedShort(paramInt + 2);
  }
  
  private int readUnsignedShort(int paramInt)
  {
    return (this.code[paramInt] & 0xFF) << 8 | this.code[(paramInt + 1)] & 0xFF;
  }
  
  private int words(Type[] paramArrayOfType)
  {
    int i = 0;
    int j = paramArrayOfType.length;
    for (;;)
    {
      j -= 1;
      if (j < 0) {
        break;
      }
      if (paramArrayOfType[j].size > 4) {
        i += 2;
      } else {
        i += 1;
      }
    }
    return i;
  }
  
  public void addHandler(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = this.exception_table_length * 4;
    if (this.exception_table == null) {
      this.exception_table = new short[20];
    }
    for (;;)
    {
      short[] arrayOfShort = this.exception_table;
      int i = j + 1;
      arrayOfShort[j] = ((short)paramInt1);
      arrayOfShort = this.exception_table;
      paramInt1 = i + 1;
      arrayOfShort[i] = ((short)paramInt2);
      arrayOfShort = this.exception_table;
      paramInt2 = paramInt1 + 1;
      arrayOfShort[paramInt1] = ((short)paramInt3);
      this.exception_table[paramInt2] = ((short)paramInt4);
      this.exception_table_length += 1;
      return;
      if (this.exception_table.length <= j)
      {
        arrayOfShort = new short[this.exception_table.length * 2];
        System.arraycopy(this.exception_table, 0, arrayOfShort, 0, j);
        this.exception_table = arrayOfShort;
      }
    }
  }
  
  public void addHandler(Label paramLabel1, Label paramLabel2, ClassType paramClassType)
  {
    ConstantPool localConstantPool = getConstants();
    int i;
    if (paramClassType == null)
    {
      i = 0;
      fixupAdd(11, paramLabel1);
      fixupAdd(12, i, paramLabel2);
      paramLabel2 = new Label();
      paramLabel2.localTypes = paramLabel1.localTypes;
      paramLabel2.stackTypes = new Type[1];
      if (paramClassType != null) {
        break label96;
      }
    }
    label96:
    for (paramLabel1 = Type.javalangThrowableType;; paramLabel1 = paramClassType)
    {
      paramLabel2.stackTypes[0] = paramLabel1;
      setTypes(paramLabel2);
      fixupAdd(13, 0, paramLabel2);
      return;
      i = localConstantPool.addClass(paramClassType).index;
      break;
    }
  }
  
  public Variable addLocal(Type paramType)
  {
    return this.locals.current_scope.addVariable(this, paramType, null);
  }
  
  public Variable addLocal(Type paramType, String paramString)
  {
    return this.locals.current_scope.addVariable(this, paramType, paramString);
  }
  
  public void addParamLocals()
  {
    Method localMethod = getMethod();
    if ((localMethod.access_flags & 0x8) == 0) {
      addLocal(localMethod.classfile).setParameter(true);
    }
    int j = localMethod.arg_types.length;
    int i = 0;
    while (i < j)
    {
      addLocal(localMethod.arg_types[i]).setParameter(true);
      i += 1;
    }
  }
  
  public void assignConstants(ClassType paramClassType)
  {
    if ((this.locals != null) && (this.locals.container == null) && (!this.locals.isEmpty())) {
      this.locals.addToFrontOf(this);
    }
    processFixups();
    if ((this.stackMap != null) && (this.stackMap.numEntries > 0)) {
      this.stackMap.addToFrontOf(this);
    }
    if (instructionLineMode)
    {
      if (this.lines == null) {
        this.lines = new LineNumbersAttr(this);
      }
      this.lines.linenumber_count = 0;
      int j = getCodeLength();
      int i = 0;
      while (i < j)
      {
        this.lines.put(i, i);
        i += 1;
      }
    }
    super.assignConstants(paramClassType);
    Attribute.assignConstants(this, paramClassType);
  }
  
  public int beginFragment(Label paramLabel)
  {
    return beginFragment(new Label(), paramLabel);
  }
  
  public int beginFragment(Label paramLabel1, Label paramLabel2)
  {
    int i = this.fixup_count;
    fixupAdd(10, paramLabel2);
    paramLabel1.define(this);
    return i;
  }
  
  public void disAssemble(ClassTypeWriter paramClassTypeWriter, int paramInt1, int paramInt2)
  {
    int i = 0;
    int k = paramInt1;
    int m;
    Object localObject;
    int n;
    int j;
    if (k < paramInt2)
    {
      paramInt1 = k + 1;
      m = this.code[k] & 0xFF;
      localObject = Integer.toString(k);
      n = 0;
      j = ((String)localObject).length();
      for (;;)
      {
        j += 1;
        if (j > 3) {
          break;
        }
        paramClassTypeWriter.print(' ');
      }
      paramClassTypeWriter.print((String)localObject);
      paramClassTypeWriter.print(": ");
      if (m < 120) {
        if (m < 87) {
          if (m < 3)
          {
            print("nop;aconst_null;iconst_m1;", m, paramClassTypeWriter);
            j = n;
            label115:
            if (j <= 0) {
              break label2030;
            }
            if (j != 1) {
              break label2015;
            }
            localObject = this.code;
            k = paramInt1 + 1;
            j = localObject[paramInt1] & 0xFF;
            paramInt1 = k;
            label150:
            paramClassTypeWriter.printConstantOperand(j);
          }
        }
      }
    }
    label399:
    label416:
    label500:
    label543:
    label2008:
    label2015:
    label2030:
    for (;;)
    {
      paramClassTypeWriter.println();
      k = paramInt1;
      break;
      if (m < 9)
      {
        paramClassTypeWriter.print("iconst_");
        paramClassTypeWriter.print(m - 3);
        j = n;
        break label115;
      }
      char c;
      if (m < 16)
      {
        if (m < 11)
        {
          c = 'l';
          j = m - 9;
        }
        for (;;)
        {
          paramClassTypeWriter.print(c);
          paramClassTypeWriter.print("const_");
          paramClassTypeWriter.print(j);
          j = n;
          break;
          if (m < 14)
          {
            c = 'f';
            j = m - 11;
          }
          else
          {
            c = 'd';
            j = m - 14;
          }
        }
      }
      if (m < 21)
      {
        if (m < 18)
        {
          print("bipush ;sipush ;", m - 16, paramClassTypeWriter);
          if (m == 16)
          {
            j = this.code[paramInt1];
            paramInt1 += 1;
          }
          for (;;)
          {
            paramClassTypeWriter.print(j);
            j = n;
            break;
            j = (short)readUnsignedShort(paramInt1);
            paramInt1 += 2;
          }
        }
        if (m == 18) {}
        for (j = 1;; j = 2)
        {
          print("ldc;ldc_w;ldc2_w;", m - 18, paramClassTypeWriter);
          break;
        }
      }
      if (m < 54)
      {
        localObject = "load";
        if (m >= 26) {
          break label500;
        }
        j = -1;
        k = m - 21;
        paramClassTypeWriter.print("ilfdabcs".charAt(k));
        if (j == -2) {
          paramClassTypeWriter.write(97);
        }
        paramClassTypeWriter.print((String)localObject);
        if (j < 0) {
          break label543;
        }
        paramClassTypeWriter.write(95);
        paramClassTypeWriter.print(j);
        k = i;
        m = paramInt1;
      }
      do
      {
        paramInt1 = m;
        j = n;
        i = k;
        break;
        localObject = "store";
        m -= 33;
        break label399;
        if (m < 46)
        {
          k = m - 26;
          j = k % 4;
          k >>= 2;
          break label416;
        }
        j = -2;
        k = m - 46;
        break label416;
        m = paramInt1;
        k = i;
      } while (j != -1);
      if (i != 0)
      {
        i = readUnsignedShort(paramInt1);
        m = paramInt1 + 2;
      }
      for (paramInt1 = i;; paramInt1 = i)
      {
        k = 0;
        paramClassTypeWriter.print(' ');
        paramClassTypeWriter.print(paramInt1);
        break;
        i = this.code[paramInt1] & 0xFF;
        m = paramInt1 + 1;
      }
      if (m < 96)
      {
        print("pop;pop2;dup;dup_x1;dup_x2;dup2;dup2_x1;dup2_x2;swap;", m - 87, paramClassTypeWriter);
        j = n;
        break label115;
      }
      paramClassTypeWriter.print("ilfda".charAt((m - 96) % 4));
      print("add;sub;mul;div;rem;neg;", m - 96 >> 2, paramClassTypeWriter);
      j = n;
      break label115;
      if (m < 170)
      {
        if (m < 132)
        {
          if ((m & 0x1) == 0) {}
          for (c = 'i';; c = 'l')
          {
            paramClassTypeWriter.print(c);
            print("shl;shr;ushr;and;or;xor;", m - 120 >> 1, paramClassTypeWriter);
            j = n;
            break;
          }
        }
        if (m == 132)
        {
          paramClassTypeWriter.print("iinc");
          if (i == 0)
          {
            localObject = this.code;
            j = paramInt1 + 1;
            k = localObject[paramInt1] & 0xFF;
            localObject = this.code;
            paramInt1 = j + 1;
            j = localObject[j];
          }
          for (;;)
          {
            paramClassTypeWriter.print(' ');
            paramClassTypeWriter.print(k);
            paramClassTypeWriter.print(' ');
            paramClassTypeWriter.print(j);
            j = n;
            break;
            k = readUnsignedShort(paramInt1);
            paramInt1 += 2;
            j = (short)readUnsignedShort(paramInt1);
            paramInt1 += 2;
            i = 0;
          }
        }
        if (m < 148)
        {
          paramClassTypeWriter.print("ilfdi".charAt((m - 133) / 3));
          paramClassTypeWriter.print('2');
          paramClassTypeWriter.print("lfdifdildilfbcs".charAt(m - 133));
          j = n;
          break label115;
        }
        if (m < 153)
        {
          print("lcmp;fcmpl;fcmpg;dcmpl;dcmpg;", m - 148, paramClassTypeWriter);
          j = n;
          break label115;
        }
        if (m < 169)
        {
          if (m < 159)
          {
            paramClassTypeWriter.print("if");
            print("eq;ne;lt;ge;gt;le;", m - 153, paramClassTypeWriter);
          }
          for (;;)
          {
            j = (short)readUnsignedShort(paramInt1);
            paramClassTypeWriter.print(' ');
            paramClassTypeWriter.print(k + j);
            paramInt1 += 2;
            j = n;
            break;
            if (m < 167)
            {
              if (m < 165) {
                paramClassTypeWriter.print("if_icmp");
              }
              for (;;)
              {
                print("eq;ne;lt;ge;gt;le;", m - 159, paramClassTypeWriter);
                break;
                paramClassTypeWriter.print("if_acmp");
                m -= 6;
              }
            }
            print("goto;jsr;", m - 167, paramClassTypeWriter);
          }
        }
        paramClassTypeWriter.print("ret ");
        if (i != 0) {
          i = readUnsignedShort(paramInt1) + 2;
        }
        for (;;)
        {
          k = 0;
          paramClassTypeWriter.print(i);
          j = n;
          i = k;
          break;
          i = this.code[paramInt1] & 0xFF;
          paramInt1 += 1;
        }
      }
      if (m < 172)
      {
        j = paramInt1;
        if (this.fixup_count <= 0) {
          j = paramInt1 + 3 & 0xFFFFFFFC;
        }
        int i2 = readInt(j);
        paramInt1 = j + 4;
        int i1;
        if (m == 170)
        {
          paramClassTypeWriter.print("tableswitch");
          m = readInt(paramInt1);
          paramInt1 += 4;
          i1 = readInt(paramInt1);
          paramInt1 += 4;
          paramClassTypeWriter.print(" low: ");
          paramClassTypeWriter.print(m);
          paramClassTypeWriter.print(" high: ");
          paramClassTypeWriter.print(i1);
          paramClassTypeWriter.print(" default: ");
          paramClassTypeWriter.print(k + i2);
          for (;;)
          {
            j = paramInt1;
            if (m > i1) {
              break;
            }
            j = readInt(paramInt1);
            paramInt1 += 4;
            paramClassTypeWriter.println();
            paramClassTypeWriter.print("  ");
            paramClassTypeWriter.print(m);
            paramClassTypeWriter.print(": ");
            paramClassTypeWriter.print(k + j);
            m += 1;
          }
        }
        paramClassTypeWriter.print("lookupswitch");
        j = readInt(paramInt1);
        paramInt1 += 4;
        paramClassTypeWriter.print(" npairs: ");
        paramClassTypeWriter.print(j);
        paramClassTypeWriter.print(" default: ");
        paramClassTypeWriter.print(k + i2);
        for (;;)
        {
          m = j - 1;
          j = paramInt1;
          if (m < 0) {
            break;
          }
          j = readInt(paramInt1);
          paramInt1 += 4;
          i1 = readInt(paramInt1);
          paramInt1 += 4;
          paramClassTypeWriter.println();
          paramClassTypeWriter.print("  ");
          paramClassTypeWriter.print(j);
          paramClassTypeWriter.print(": ");
          paramClassTypeWriter.print(k + i1);
          j = m;
        }
        paramInt1 = j;
        j = n;
        break label115;
      }
      if (m < 178)
      {
        if (m < 177) {
          paramClassTypeWriter.print("ilfda".charAt(m - 172));
        }
        paramClassTypeWriter.print("return");
        j = n;
        break label115;
      }
      if (m < 182)
      {
        print("getstatic;putstatic;getfield;putfield;", m - 178, paramClassTypeWriter);
        j = 2;
        break label115;
      }
      if (m < 185)
      {
        paramClassTypeWriter.print("invoke");
        print("virtual;special;static;", m - 182, paramClassTypeWriter);
        j = 2;
        break label115;
      }
      if (m == 185)
      {
        paramClassTypeWriter.print("invokeinterface (");
        j = readUnsignedShort(paramInt1);
        paramInt1 += 2;
        k = this.code[paramInt1];
        paramClassTypeWriter.print((k & 0xFF) + " args)");
        paramClassTypeWriter.printConstantOperand(j);
        paramInt1 += 2;
        j = n;
        break label115;
      }
      if (m < 196)
      {
        print("186;new;newarray;anewarray;arraylength;athrow;checkcast;instanceof;monitorenter;monitorexit;", m - 186, paramClassTypeWriter);
        if ((m == 187) || (m == 189) || (m == 192) || (m == 193))
        {
          j = 2;
          break label115;
        }
        if (m != 188) {
          break label2008;
        }
        localObject = this.code;
        j = paramInt1 + 1;
        paramInt1 = localObject[paramInt1];
        paramClassTypeWriter.print(' ');
        if ((paramInt1 >= 4) && (paramInt1 <= 11))
        {
          print("boolean;char;float;double;byte;short;int;long;", paramInt1 - 4, paramClassTypeWriter);
          paramInt1 = j;
          j = n;
          break label115;
        }
        paramClassTypeWriter.print(paramInt1);
        paramInt1 = j;
        j = n;
        break label115;
      }
      if (m == 196)
      {
        paramClassTypeWriter.print("wide");
        i = 1;
        j = n;
        break label115;
      }
      if (m == 197)
      {
        paramClassTypeWriter.print("multianewarray");
        j = readUnsignedShort(paramInt1);
        k = paramInt1 + 2;
        paramClassTypeWriter.printConstantOperand(j);
        localObject = this.code;
        paramInt1 = k + 1;
        j = localObject[k];
        paramClassTypeWriter.print(' ');
        paramClassTypeWriter.print(j & 0xFF);
        j = n;
        break label115;
      }
      if (m < 200)
      {
        print("ifnull;ifnonnull;", m - 198, paramClassTypeWriter);
        j = (short)readUnsignedShort(paramInt1);
        paramClassTypeWriter.print(' ');
        paramClassTypeWriter.print(k + j);
        paramInt1 += 2;
        j = n;
        break label115;
      }
      if (m < 202)
      {
        print("goto_w;jsr_w;", m - 200, paramClassTypeWriter);
        j = readInt(paramInt1);
        paramClassTypeWriter.print(' ');
        paramClassTypeWriter.print(k + j);
        paramInt1 += 4;
        j = n;
        break label115;
      }
      paramClassTypeWriter.print(m);
      j = n;
      break label115;
      j = readUnsignedShort(paramInt1);
      paramInt1 += 2;
      break label150;
      return;
    }
  }
  
  public final void emitAdd()
  {
    emitBinop(96);
  }
  
  public final void emitAdd(char paramChar)
  {
    emitBinop(96, paramChar);
  }
  
  public final void emitAdd(PrimType paramPrimType)
  {
    emitBinop(96, paramPrimType);
  }
  
  public final void emitAnd()
  {
    emitBinop(126);
  }
  
  public final void emitArrayLength()
  {
    if (!(popType() instanceof ArrayType)) {
      throw new Error("non-array type in emitArrayLength");
    }
    reserve(1);
    put1(190);
    pushType(Type.intType);
  }
  
  public void emitArrayLoad()
  {
    popType();
    Type localType = ((ArrayType)popType().getImplementationType()).getComponentType();
    emitTypedOp(46, localType);
    pushType(localType);
  }
  
  public void emitArrayLoad(Type paramType)
  {
    popType();
    popType();
    emitTypedOp(46, paramType);
    pushType(paramType);
  }
  
  public void emitArrayStore()
  {
    popType();
    popType();
    emitTypedOp(79, ((ArrayType)popType().getImplementationType()).getComponentType());
  }
  
  public void emitArrayStore(Type paramType)
  {
    popType();
    popType();
    popType();
    emitTypedOp(79, paramType);
  }
  
  public void emitBinop(int paramInt, Type paramType)
  {
    popType();
    popType();
    emitTypedOp(paramInt, paramType);
    pushType(paramType);
  }
  
  public void emitCatchEnd()
  {
    gotoFinallyOrEnd(false);
    this.try_stack.try_type = null;
  }
  
  public void emitCatchStart(Variable paramVariable)
  {
    emitTryEnd(false);
    setTypes(this.try_stack.start_try.localTypes, Type.typeArray0);
    if (this.try_stack.try_type != null) {
      emitCatchEnd();
    }
    if (paramVariable == null) {}
    for (ClassType localClassType = null;; localClassType = (ClassType)paramVariable.getType())
    {
      this.try_stack.try_type = localClassType;
      addHandler(this.try_stack.start_try, this.try_stack.end_try, localClassType);
      if (paramVariable != null) {
        emitStore(paramVariable);
      }
      return;
    }
  }
  
  public void emitCheckcast(Type paramType)
  {
    if (castNeeded(topType(), paramType))
    {
      emitCheckcast(paramType, 192);
      pushType(paramType);
    }
  }
  
  public final void emitConvert(Type paramType1, Type paramType2)
  {
    String str1 = paramType2.getSignature();
    String str2 = paramType1.getSignature();
    int k = -1;
    int i;
    int m;
    if (str1.length() != 1)
    {
      i = k;
      if (str2.length() != 1) {}
    }
    else
    {
      m = str1.charAt(0);
      i = str2.charAt(0);
      if (i == m) {}
      int j;
      do
      {
        return;
        if (paramType1.size < 4) {
          i = 73;
        }
        j = i;
        if (paramType2.size < 4)
        {
          emitConvert(paramType1, Type.intType);
          j = 73;
        }
      } while (j == m);
      i = k;
      switch (j)
      {
      default: 
        i = k;
      }
    }
    while (i < 0)
    {
      throw new Error("unsupported CodeAttr.emitConvert");
      switch (m)
      {
      default: 
        i = k;
        break;
      case 66: 
        i = 145;
        break;
      case 67: 
        i = 146;
        break;
      case 83: 
        i = 147;
        break;
      case 74: 
        i = 133;
        break;
      case 70: 
        i = 134;
        break;
      case 68: 
        i = 135;
        continue;
        switch (m)
        {
        case 69: 
        case 71: 
        case 72: 
        default: 
          i = k;
          break;
        case 68: 
          i = 138;
          break;
        case 73: 
          i = 136;
          break;
        case 70: 
          i = 137;
          continue;
          switch (m)
          {
          default: 
            i = k;
            break;
          case 68: 
            i = 141;
            break;
          case 73: 
            i = 139;
            break;
          case 74: 
            i = 140;
            continue;
            switch (m)
            {
            case 71: 
            case 72: 
            default: 
              i = k;
              break;
            case 70: 
              i = 144;
              break;
            case 73: 
              i = 142;
              break;
            case 74: 
              i = 143;
            }
            break;
          }
          break;
        }
        break;
      }
    }
    reserve(1);
    popType();
    put1(i);
    pushType(paramType2);
  }
  
  public final void emitDiv()
  {
    emitBinop(108);
  }
  
  public void emitDup()
  {
    reserve(1);
    Type localType = topType();
    if (localType.size <= 4) {}
    for (int i = 89;; i = 92)
    {
      put1(i);
      pushType(localType);
      return;
    }
  }
  
  public void emitDup(int paramInt)
  {
    emitDup(paramInt, 0);
  }
  
  public void emitDup(int paramInt1, int paramInt2)
  {
    if (paramInt1 == 0) {
      return;
    }
    reserve(1);
    Type localType3 = popType();
    Object localObject2 = null;
    if (paramInt1 == 1)
    {
      if (localType3.size > 4) {
        throw new Error("using dup for 2-word type");
      }
    }
    else
    {
      if (paramInt1 != 2) {
        throw new Error("invalid size to emitDup");
      }
      if (localType3.size <= 4)
      {
        localObject1 = popType();
        localObject2 = localObject1;
        if (((Type)localObject1).size > 4) {
          throw new Error("dup will cause invalid types on stack");
        }
      }
    }
    Object localObject1 = null;
    Object localObject3 = null;
    if (paramInt2 == 0)
    {
      if (paramInt1 == 1) {}
      for (paramInt1 = 89;; paramInt1 = 92)
      {
        put1(paramInt1);
        if (localObject2 != null) {
          pushType((Type)localObject2);
        }
        pushType(localType3);
        if (localObject3 != null) {
          pushType((Type)localObject3);
        }
        if (localObject1 != null) {
          pushType((Type)localObject1);
        }
        if (localObject2 != null) {
          pushType((Type)localObject2);
        }
        pushType(localType3);
        return;
      }
    }
    Type localType1;
    if (paramInt2 == 1)
    {
      if (paramInt1 == 1) {}
      for (paramInt1 = 90;; paramInt1 = 93)
      {
        localType1 = popType();
        localObject1 = localType1;
        if (localType1.size <= 4) {
          break;
        }
        throw new Error("dup will cause invalid types on stack");
      }
    }
    if (paramInt2 == 2)
    {
      if (paramInt1 == 1) {}
      for (paramInt2 = 91;; paramInt2 = 94)
      {
        localType1 = popType();
        paramInt1 = paramInt2;
        localObject1 = localType1;
        if (localType1.size > 4) {
          break;
        }
        Type localType2 = popType();
        paramInt1 = paramInt2;
        localObject1 = localType1;
        localObject3 = localType2;
        if (localType2.size <= 4) {
          break;
        }
        throw new Error("dup will cause invalid types on stack");
      }
    }
    throw new Error("emitDup:  invalid offset");
  }
  
  public void emitDup(Type paramType)
  {
    if (paramType.size > 4) {}
    for (int i = 2;; i = 1)
    {
      emitDup(i, 0);
      return;
    }
  }
  
  public void emitDupX()
  {
    reserve(1);
    Type localType1 = popType();
    Type localType2 = popType();
    if (localType2.size <= 4)
    {
      if (localType1.size <= 4) {}
      for (i = 90;; i = 93)
      {
        put1(i);
        pushType(localType1);
        pushType(localType2);
        pushType(localType1);
        return;
      }
    }
    if (localType1.size <= 4) {}
    for (int i = 91;; i = 94)
    {
      put1(i);
      break;
    }
  }
  
  public final void emitElse()
  {
    Label localLabel1 = this.if_stack.end_label;
    if (reachableHere())
    {
      Label localLabel2 = new Label(this);
      this.if_stack.end_label = localLabel2;
      int i = this.SP - this.if_stack.start_stack_size;
      this.if_stack.stack_growth = i;
      if (i > 0)
      {
        this.if_stack.then_stacked_types = new Type[i];
        System.arraycopy(this.stack_types, this.if_stack.start_stack_size, this.if_stack.then_stacked_types, 0, i);
        emitGoto(localLabel2);
      }
    }
    for (;;)
    {
      if (this.SP <= this.if_stack.start_stack_size) {
        break label143;
      }
      popType();
      continue;
      this.if_stack.then_stacked_types = new Type[0];
      break;
      this.if_stack.end_label = null;
    }
    label143:
    this.SP = this.if_stack.start_stack_size;
    if (localLabel1 != null) {
      localLabel1.define(this);
    }
    this.if_stack.doing_else = true;
  }
  
  public final void emitFi()
  {
    int j = 0;
    int i;
    int k;
    if (!this.if_stack.doing_else)
    {
      i = j;
      if (reachableHere())
      {
        i = j;
        if (this.SP != this.if_stack.start_stack_size) {
          throw new Error("at PC " + this.PC + " then clause grows stack with no else clause");
        }
      }
    }
    else
    {
      if (this.if_stack.then_stacked_types == null) {
        break label258;
      }
      k = this.if_stack.start_stack_size + this.if_stack.stack_growth;
      if (reachableHere()) {
        break label194;
      }
      if (this.if_stack.stack_growth > 0) {
        System.arraycopy(this.if_stack.then_stacked_types, 0, this.stack_types, this.if_stack.start_stack_size, this.if_stack.stack_growth);
      }
      this.SP = k;
      i = j;
    }
    for (;;)
    {
      if (this.if_stack.end_label != null) {
        this.if_stack.end_label.define(this);
      }
      if (i != 0) {
        setUnreachable();
      }
      this.if_stack = this.if_stack.previous;
      return;
      label194:
      i = j;
      if (this.SP != k)
      {
        throw new Error("at PC " + this.PC + ": SP at end of 'then' was " + k + " while SP at end of 'else' was " + this.SP);
        label258:
        i = j;
        if (this.unreachable_here) {
          i = 1;
        }
      }
    }
  }
  
  public void emitFinallyEnd()
  {
    if (useJsr()) {
      emitRet(this.try_stack.finally_ret_addr);
    }
    for (;;)
    {
      popScope();
      this.try_stack.finally_subr = null;
      return;
      if ((this.try_stack.end_label == null) && (this.try_stack.exitCases == null))
      {
        emitThrow();
      }
      else
      {
        emitStore(this.try_stack.exception);
        emitLoad(this.try_stack.exception);
        emitIfNotNull();
        emitLoad(this.try_stack.exception);
        emitThrow();
        emitElse();
        Object localObject = this.try_stack.exitCases;
        if (localObject != null)
        {
          SwitchState localSwitchState = startSwitch();
          if (localObject != null)
          {
            ExitableBlock localExitableBlock = ((ExitableBlock)localObject).nextCase;
            ((ExitableBlock)localObject).nextCase = null;
            ((ExitableBlock)localObject).currentTryState = null;
            TryState localTryState = TryState.outerHandler(this.try_stack.previous, ((ExitableBlock)localObject).initialTryState);
            if (localTryState == ((ExitableBlock)localObject).initialTryState) {
              localSwitchState.addCaseGoto(((ExitableBlock)localObject).switchCase, this, ((ExitableBlock)localObject).endLabel);
            }
            for (;;)
            {
              localObject = localExitableBlock;
              break;
              localSwitchState.addCase(((ExitableBlock)localObject).switchCase, this);
              ((ExitableBlock)localObject).exit(localTryState);
            }
          }
          this.try_stack.exitCases = null;
          localSwitchState.addDefault(this);
          localSwitchState.finish(this);
        }
        emitFi();
        setUnreachable();
      }
    }
  }
  
  public void emitFinallyStart()
  {
    emitTryEnd(true);
    if (this.try_stack.try_type != null) {
      emitCatchEnd();
    }
    this.try_stack.end_try = getLabel();
    pushScope();
    if (useJsr())
    {
      this.SP = 0;
      emitCatchStart(null);
      emitStore(this.try_stack.exception);
      emitJsr(this.try_stack.finally_subr);
      emitLoad(this.try_stack.exception);
      emitThrow();
    }
    for (;;)
    {
      this.try_stack.finally_subr.define(this);
      if (useJsr())
      {
        ClassType localClassType = Type.objectType;
        this.try_stack.finally_ret_addr = addLocal(localClassType);
        pushType(localClassType);
        emitStore(this.try_stack.finally_ret_addr);
      }
      return;
      setUnreachable();
      int i = beginFragment(new Label(this));
      addHandler(this.try_stack.start_try, this.try_stack.end_try, Type.javalangThrowableType);
      if (this.try_stack.saved_result != null) {
        emitStoreDefaultValue(this.try_stack.saved_result);
      }
      if (this.try_stack.exitCases != null)
      {
        emitPushInt(-1);
        emitSwap();
      }
      emitGoto(this.try_stack.finally_subr);
      endFragment(i);
    }
  }
  
  public final void emitGetField(Field paramField)
  {
    popType();
    pushType(paramField.type);
    emitFieldop(paramField, 180);
  }
  
  public final void emitGetStatic(Field paramField)
  {
    pushType(paramField.type);
    emitFieldop(paramField, 178);
  }
  
  public final void emitGoto(Label paramLabel)
  {
    paramLabel.setTypes(this);
    fixupAdd(4, paramLabel);
    reserve(3);
    put1(167);
    this.PC += 2;
    setUnreachable();
  }
  
  public final void emitGotoIfCompare1(Label paramLabel, int paramInt)
  {
    popType();
    reserve(3);
    emitTransfer(paramLabel, paramInt);
  }
  
  public final void emitGotoIfCompare2(Label paramLabel, int paramInt)
  {
    int i = 0;
    if ((paramInt < 153) || (paramInt > 158)) {
      throw new Error("emitGotoIfCompare2: logop must be one of ifeq...ifle");
    }
    Type localType1 = popType().promote();
    Type localType2 = popType().promote();
    reserve(4);
    int j = localType2.getSignature().charAt(0);
    int k = localType1.getSignature().charAt(0);
    if ((paramInt == 155) || (paramInt == 158)) {
      i = 1;
    }
    if ((j == 73) && (k == 73)) {
      paramInt += 6;
    }
    for (;;)
    {
      emitTransfer(paramLabel, paramInt);
      return;
      if ((j == 74) && (k == 74))
      {
        put1(148);
      }
      else
      {
        if ((j == 70) && (k == 70))
        {
          if (i != 0) {}
          for (i = 149;; i = 150)
          {
            put1(i);
            break;
          }
        }
        if ((j == 68) && (k == 68))
        {
          if (i != 0) {}
          for (i = 151;; i = 152)
          {
            put1(i);
            break;
          }
        }
        if (((j != 76) && (j != 91)) || ((k != 76) && (k != 91)) || (paramInt > 154)) {
          break;
        }
        paramInt += 12;
      }
    }
    throw new Error("invalid types to emitGotoIfCompare2");
  }
  
  public final void emitGotoIfEq(Label paramLabel)
  {
    emitGotoIfCompare2(paramLabel, 153);
  }
  
  public final void emitGotoIfEq(Label paramLabel, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 154;; i = 153)
    {
      emitGotoIfCompare2(paramLabel, i);
      return;
    }
  }
  
  public final void emitGotoIfGe(Label paramLabel)
  {
    emitGotoIfCompare2(paramLabel, 156);
  }
  
  public final void emitGotoIfGt(Label paramLabel)
  {
    emitGotoIfCompare2(paramLabel, 157);
  }
  
  public final void emitGotoIfIntEqZero(Label paramLabel)
  {
    emitGotoIfCompare1(paramLabel, 153);
  }
  
  public final void emitGotoIfIntGeZero(Label paramLabel)
  {
    emitGotoIfCompare1(paramLabel, 156);
  }
  
  public final void emitGotoIfIntGtZero(Label paramLabel)
  {
    emitGotoIfCompare1(paramLabel, 157);
  }
  
  public final void emitGotoIfIntLeZero(Label paramLabel)
  {
    emitGotoIfCompare1(paramLabel, 158);
  }
  
  public final void emitGotoIfIntLtZero(Label paramLabel)
  {
    emitGotoIfCompare1(paramLabel, 155);
  }
  
  public final void emitGotoIfIntNeZero(Label paramLabel)
  {
    emitGotoIfCompare1(paramLabel, 154);
  }
  
  public final void emitGotoIfLe(Label paramLabel)
  {
    emitGotoIfCompare2(paramLabel, 158);
  }
  
  public final void emitGotoIfLt(Label paramLabel)
  {
    emitGotoIfCompare2(paramLabel, 155);
  }
  
  public final void emitGotoIfNE(Label paramLabel)
  {
    emitGotoIfCompare2(paramLabel, 154);
  }
  
  public final void emitIOr()
  {
    emitBinop(128);
  }
  
  public final void emitIfCompare1(int paramInt)
  {
    IfState localIfState = new IfState(this);
    if (popType().promote() != Type.intType) {
      throw new Error("non-int type to emitIfCompare1");
    }
    reserve(3);
    emitTransfer(localIfState.end_label, paramInt);
    localIfState.start_stack_size = this.SP;
  }
  
  public final void emitIfEq()
  {
    IfState localIfState = new IfState(this);
    emitGotoIfNE(localIfState.end_label);
    localIfState.start_stack_size = this.SP;
  }
  
  public final void emitIfGe()
  {
    IfState localIfState = new IfState(this);
    emitGotoIfLt(localIfState.end_label);
    localIfState.start_stack_size = this.SP;
  }
  
  public final void emitIfGt()
  {
    IfState localIfState = new IfState(this);
    emitGotoIfLe(localIfState.end_label);
    localIfState.start_stack_size = this.SP;
  }
  
  public final void emitIfIntCompare(int paramInt)
  {
    IfState localIfState = new IfState(this);
    popType();
    popType();
    reserve(3);
    emitTransfer(localIfState.end_label, paramInt);
    localIfState.start_stack_size = this.SP;
  }
  
  public final void emitIfIntEqZero()
  {
    emitIfCompare1(154);
  }
  
  public final void emitIfIntLEqZero()
  {
    emitIfCompare1(157);
  }
  
  public final void emitIfIntLt()
  {
    emitIfIntCompare(162);
  }
  
  public final void emitIfIntNotZero()
  {
    emitIfCompare1(153);
  }
  
  public final void emitIfLe()
  {
    IfState localIfState = new IfState(this);
    emitGotoIfGt(localIfState.end_label);
    localIfState.start_stack_size = this.SP;
  }
  
  public final void emitIfLt()
  {
    IfState localIfState = new IfState(this);
    emitGotoIfGe(localIfState.end_label);
    localIfState.start_stack_size = this.SP;
  }
  
  public final void emitIfNEq()
  {
    IfState localIfState = new IfState(this);
    emitGotoIfEq(localIfState.end_label);
    localIfState.start_stack_size = this.SP;
  }
  
  public final void emitIfNotNull()
  {
    emitIfRefCompare1(198);
  }
  
  public final void emitIfNull()
  {
    emitIfRefCompare1(199);
  }
  
  public final void emitIfRefCompare1(int paramInt)
  {
    IfState localIfState = new IfState(this);
    if (!(popType() instanceof ObjectType)) {
      throw new Error("non-ref type to emitIfRefCompare1");
    }
    reserve(3);
    emitTransfer(localIfState.end_label, paramInt);
    localIfState.start_stack_size = this.SP;
  }
  
  public final void emitIfThen()
  {
    new IfState(this, null);
  }
  
  public void emitInc(Variable paramVariable, short paramShort)
  {
    if (paramVariable.dead()) {
      throw new Error("attempting to increment dead variable");
    }
    int j = paramVariable.offset;
    if ((j < 0) || (!paramVariable.isSimple())) {
      throw new Error("attempting to increment unassigned variable" + paramVariable.getName() + " simple:" + paramVariable.isSimple() + ", offset: " + j);
    }
    reserve(6);
    if (paramVariable.getType().getImplementationType().promote() != Type.intType) {
      throw new Error("attempting to increment non-int variable");
    }
    if ((j > 255) || (paramShort > 255) || (paramShort < 65280)) {}
    for (int i = 1; i != 0; i = 0)
    {
      put1(196);
      put1(132);
      put2(j);
      put2(paramShort);
      return;
    }
    put1(132);
    put1(j);
    put1(paramShort);
  }
  
  public void emitInstanceof(Type paramType)
  {
    emitCheckcast(paramType, 193);
    pushType(Type.booleanType);
  }
  
  public void emitInvoke(Method paramMethod)
  {
    int i;
    if ((paramMethod.access_flags & 0x8) != 0) {
      i = 184;
    }
    for (;;)
    {
      emitInvokeMethod(paramMethod, i);
      return;
      if (paramMethod.classfile.isInterface()) {
        i = 185;
      } else if ("<init>".equals(paramMethod.getName())) {
        i = 183;
      } else {
        i = 182;
      }
    }
  }
  
  public void emitInvokeInterface(Method paramMethod)
  {
    emitInvokeMethod(paramMethod, 185);
  }
  
  public void emitInvokeMethod(Method paramMethod, int paramInt)
  {
    int m = 1;
    int n;
    label36:
    int j;
    if (paramInt == 185)
    {
      i = 5;
      reserve(i);
      n = paramMethod.arg_types.length;
      if (paramInt != 184) {
        break label117;
      }
      k = 1;
      if ((paramInt != 183) || (!"<init>".equals(paramMethod.getName()))) {
        break label123;
      }
      j = 1;
      label59:
      if ((paramMethod.access_flags & 0x8) == 0) {
        break label129;
      }
    }
    label117:
    label123:
    label129:
    for (int i = m;; i = 0)
    {
      if (k == i) {
        break label135;
      }
      throw new Error("emitInvokeXxx static flag mis-match method.flags=" + paramMethod.access_flags);
      i = 3;
      break;
      k = 0;
      break label36;
      j = 0;
      break label59;
    }
    label135:
    i = n;
    if (k == 0)
    {
      i = n;
      if (j == 0) {
        i = n + 1;
      }
    }
    put1(paramInt);
    putIndex2(getConstants().addMethodRef(paramMethod));
    int k = i;
    if (paramInt == 185)
    {
      put1(words(paramMethod.arg_types) + 1);
      put1(0);
      k = i;
    }
    Type localType;
    do
    {
      k -= 1;
      if (k < 0) {
        break;
      }
      localType = popType();
    } while (!(localType instanceof UninitializedType));
    throw new Error("passing " + localType + " as parameter");
    if (j != 0)
    {
      localType = popType();
      if (!(localType instanceof UninitializedType)) {
        throw new Error("calling <init> on already-initialized object");
      }
      ClassType localClassType = ((UninitializedType)localType).ctype;
      paramInt = 0;
      while (paramInt < this.SP)
      {
        if (this.stack_types[paramInt] == localType) {
          this.stack_types[paramInt] = localClassType;
        }
        paramInt += 1;
      }
      Variable[] arrayOfVariable = this.locals.used;
      if (arrayOfVariable == null) {
        paramInt = 0;
      }
      for (;;)
      {
        i = paramInt - 1;
        if (i < 0) {
          break;
        }
        Variable localVariable = arrayOfVariable[i];
        paramInt = i;
        if (localVariable != null)
        {
          paramInt = i;
          if (localVariable.type == localType)
          {
            localVariable.type = localClassType;
            paramInt = i;
            continue;
            paramInt = arrayOfVariable.length;
          }
        }
      }
      if (this.local_types == null) {
        paramInt = 0;
      }
      for (;;)
      {
        i = paramInt - 1;
        if (i < 0) {
          break;
        }
        paramInt = i;
        if (this.local_types[i] == localType)
        {
          this.local_types[i] = localClassType;
          paramInt = i;
          continue;
          paramInt = this.local_types.length;
        }
      }
    }
    if (paramMethod.return_type.size != 0) {
      pushType(paramMethod.return_type);
    }
  }
  
  public void emitInvokeSpecial(Method paramMethod)
  {
    emitInvokeMethod(paramMethod, 183);
  }
  
  public void emitInvokeStatic(Method paramMethod)
  {
    emitInvokeMethod(paramMethod, 184);
  }
  
  public void emitInvokeVirtual(Method paramMethod)
  {
    emitInvokeMethod(paramMethod, 182);
  }
  
  public final void emitJsr(Label paramLabel)
  {
    fixupAdd(5, paramLabel);
    reserve(3);
    put1(168);
    this.PC += 2;
  }
  
  public final void emitLoad(Variable paramVariable)
  {
    if (paramVariable.dead()) {
      throw new Error("attempting to push dead variable");
    }
    int i = paramVariable.offset;
    if ((i < 0) || (!paramVariable.isSimple())) {
      throw new Error("attempting to load from unassigned variable " + paramVariable + " simple:" + paramVariable.isSimple() + ", offset: " + i);
    }
    Type localType = paramVariable.getType().promote();
    reserve(4);
    int j = adjustTypedOp(localType);
    if (i <= 3) {
      put1(j * 4 + 26 + i);
    }
    for (;;)
    {
      pushType(paramVariable.getType());
      return;
      emitMaybeWide(j + 21, i);
    }
  }
  
  void emitMaybeWide(int paramInt1, int paramInt2)
  {
    if (paramInt2 >= 256)
    {
      put1(196);
      put1(paramInt1);
      put2(paramInt2);
      return;
    }
    put1(paramInt1);
    put1(paramInt2);
  }
  
  public final void emitMonitorEnter()
  {
    popType();
    reserve(1);
    put1(194);
  }
  
  public final void emitMonitorExit()
  {
    popType();
    reserve(1);
    put1(195);
  }
  
  public final void emitMul()
  {
    emitBinop(104);
  }
  
  public void emitNew(ClassType paramClassType)
  {
    reserve(3);
    Label localLabel = new Label(this);
    localLabel.defineRaw(this);
    put1(187);
    putIndex2(getConstants().addClass(paramClassType));
    pushType(new UninitializedType(paramClassType, localLabel));
  }
  
  void emitNewArray(int paramInt)
  {
    reserve(2);
    put1(188);
    put1(paramInt);
  }
  
  public void emitNewArray(Type paramType)
  {
    emitNewArray(paramType, 1);
  }
  
  public void emitNewArray(Type paramType, int paramInt)
  {
    if (popType().promote() != Type.intType) {
      throw new Error("non-int dim. spec. in emitNewArray");
    }
    if ((paramType instanceof PrimType)) {
      switch (paramType.getSignature().charAt(0))
      {
      default: 
        throw new Error("bad PrimType in emitNewArray");
      case 'B': 
        paramInt = 8;
        emitNewArray(paramInt);
      }
    }
    for (;;)
    {
      pushType(new ArrayType(paramType));
      return;
      paramInt = 9;
      break;
      paramInt = 10;
      break;
      paramInt = 11;
      break;
      paramInt = 6;
      break;
      paramInt = 7;
      break;
      paramInt = 4;
      break;
      paramInt = 5;
      break;
      if (!(paramType instanceof ObjectType)) {
        break label221;
      }
      reserve(3);
      put1(189);
      putIndex2(getConstants().addClass((ObjectType)paramType));
    }
    label221:
    if ((paramType instanceof ArrayType))
    {
      reserve(4);
      put1(197);
      putIndex2(getConstants().addClass(new ArrayType(paramType)));
      if ((paramInt < 1) || (paramInt > 255)) {
        throw new Error("dims out of range in emitNewArray");
      }
      put1(paramInt);
      do
      {
        paramInt -= 1;
        if (paramInt <= 0) {
          break;
        }
      } while (popType().promote() == Type.intType);
      throw new Error("non-int dim. spec. in emitNewArray");
    }
    throw new Error("unimplemented type in emitNewArray");
  }
  
  public final void emitNot(Type paramType)
  {
    emitPushConstant(1, paramType);
    emitAdd();
    emitPushConstant(1, paramType);
    emitAnd();
  }
  
  public void emitPop(int paramInt)
  {
    if (paramInt > 0)
    {
      reserve(1);
      if (popType().size > 4) {
        put1(88);
      }
      for (;;)
      {
        paramInt -= 1;
        break;
        if (paramInt > 1)
        {
          if (popType().size > 4)
          {
            put1(87);
            reserve(1);
          }
          put1(88);
          paramInt -= 1;
        }
        else
        {
          put1(87);
        }
      }
    }
  }
  
  public void emitPrimop(int paramInt1, int paramInt2, Type paramType)
  {
    reserve(1);
    for (;;)
    {
      paramInt2 -= 1;
      if (paramInt2 < 0) {
        break;
      }
      popType();
    }
    put1(paramInt1);
    pushType(paramType);
  }
  
  public final void emitPushClass(ObjectType paramObjectType)
  {
    emitPushConstant(getConstants().addClass(paramObjectType));
    pushType(Type.javalangClassType);
  }
  
  public final void emitPushConstant(int paramInt, Type paramType)
  {
    switch (paramType.getSignature().charAt(0))
    {
    default: 
      throw new Error("bad type to emitPushConstant");
    case 'B': 
    case 'C': 
    case 'I': 
    case 'S': 
    case 'Z': 
      emitPushInt(paramInt);
      return;
    case 'J': 
      emitPushLong(paramInt);
      return;
    case 'F': 
      emitPushFloat(paramInt);
      return;
    }
    emitPushDouble(paramInt);
  }
  
  public final void emitPushConstant(CpoolEntry paramCpoolEntry)
  {
    reserve(3);
    int i = paramCpoolEntry.index;
    if ((paramCpoolEntry instanceof CpoolValue2))
    {
      put1(20);
      put2(i);
      return;
    }
    if (i < 256)
    {
      put1(18);
      put1(i);
      return;
    }
    put1(19);
    put2(i);
  }
  
  public void emitPushDefaultValue(Type paramType)
  {
    paramType = paramType.getImplementationType();
    if ((paramType instanceof PrimType))
    {
      emitPushConstant(0, paramType);
      return;
    }
    emitPushNull();
  }
  
  public void emitPushDouble(double paramDouble)
  {
    int i = (int)paramDouble;
    if ((i == paramDouble) && (i >= -128) && (i < 128)) {
      if ((i == 0) || (i == 1))
      {
        reserve(1);
        put1(i + 14);
        if ((i == 0) && (Double.doubleToLongBits(paramDouble) != 0L))
        {
          reserve(1);
          put1(119);
        }
      }
    }
    for (;;)
    {
      pushType(Type.doubleType);
      return;
      emitPushInt(i);
      reserve(1);
      popType();
      put1(135);
      continue;
      emitPushConstant(getConstants().addDouble(paramDouble));
    }
  }
  
  public void emitPushFloat(float paramFloat)
  {
    int i = (int)paramFloat;
    if ((i == paramFloat) && (i >= -128) && (i < 128)) {
      if ((i >= 0) && (i <= 2))
      {
        reserve(1);
        put1(i + 11);
        if ((i == 0) && (Float.floatToIntBits(paramFloat) != 0))
        {
          reserve(1);
          put1(118);
        }
      }
    }
    for (;;)
    {
      pushType(Type.floatType);
      return;
      emitPushInt(i);
      reserve(1);
      popType();
      put1(134);
      continue;
      emitPushConstant(getConstants().addFloat(paramFloat));
    }
  }
  
  public final void emitPushInt(int paramInt)
  {
    reserve(3);
    if ((paramInt >= -1) && (paramInt <= 5)) {
      put1(paramInt + 3);
    }
    for (;;)
    {
      pushType(Type.intType);
      return;
      if ((paramInt >= -128) && (paramInt < 128))
      {
        put1(16);
        put1(paramInt);
      }
      else if ((paramInt >= 32768) && (paramInt < 32768))
      {
        put1(17);
        put2(paramInt);
      }
      else
      {
        emitPushConstant(getConstants().addInt(paramInt));
      }
    }
  }
  
  public void emitPushLong(long paramLong)
  {
    if ((paramLong == 0L) || (paramLong == 1L))
    {
      reserve(1);
      put1((int)paramLong + 9);
    }
    for (;;)
    {
      pushType(Type.longType);
      return;
      if ((int)paramLong == paramLong)
      {
        emitPushInt((int)paramLong);
        reserve(1);
        popType();
        put1(133);
      }
      else
      {
        emitPushConstant(getConstants().addLong(paramLong));
      }
    }
  }
  
  public void emitPushNull()
  {
    reserve(1);
    put1(1);
    pushType(Type.nullType);
  }
  
  public final void emitPushPrimArray(Object paramObject, ArrayType paramArrayType)
  {
    Type localType = paramArrayType.getComponentType();
    int j = Array.getLength(paramObject);
    emitPushInt(j);
    emitNewArray(localType);
    int k = localType.getSignature().charAt(0);
    int i = 0;
    if (i < j)
    {
      long l1 = 0L;
      float f1 = 0.0F;
      double d1 = 0.0D;
      switch (k)
      {
      default: 
        label128:
        emitDup(paramArrayType);
        emitPushInt(i);
        switch (k)
        {
        }
        break;
      }
      for (;;)
      {
        emitArrayStore(localType);
        for (;;)
        {
          label222:
          i += 1;
          break;
          long l2 = ((long[])(long[])paramObject)[i];
          l1 = l2;
          if (l2 != 0L) {
            break label128;
          }
          continue;
          l2 = ((int[])(int[])paramObject)[i];
          l1 = l2;
          if (l2 != 0L) {
            break label128;
          }
          continue;
          l2 = ((short[])(short[])paramObject)[i];
          l1 = l2;
          if (l2 != 0L) {
            break label128;
          }
          continue;
          l2 = ((char[])(char[])paramObject)[i];
          l1 = l2;
          if (l2 != 0L) {
            break label128;
          }
          continue;
          l2 = ((byte[])(byte[])paramObject)[i];
          l1 = l2;
          if (l2 != 0L) {
            break label128;
          }
          continue;
          if (((boolean[])(boolean[])paramObject)[i] != 0) {}
          for (l2 = 1L;; l2 = 0L)
          {
            l1 = l2;
            if (l2 != 0L) {
              break;
            }
            break label222;
          }
          float f2 = ((float[])(float[])paramObject)[i];
          f1 = f2;
          if (f2 != 0.0D) {
            break label128;
          }
          continue;
          double d2 = ((double[])(double[])paramObject)[i];
          d1 = d2;
          if (d2 != 0.0D) {
            break label128;
          }
        }
        emitPushInt((int)l1);
        continue;
        emitPushLong(l1);
        continue;
        emitPushFloat(f1);
        continue;
        emitPushDouble(d1);
      }
    }
  }
  
  public final void emitPushString(String paramString)
  {
    if (paramString == null) {
      emitPushNull();
    }
    for (;;)
    {
      return;
      int i = paramString.length();
      String str = calculateSplit(paramString);
      int m = str.length();
      if (m <= 1)
      {
        emitPushConstant(getConstants().addString(paramString));
        pushType(Type.javalangStringType);
        return;
      }
      if (m == 2)
      {
        i = str.charAt(0);
        emitPushString(paramString.substring(0, i));
        emitPushString(paramString.substring(i));
        emitInvokeVirtual(Type.javalangStringType.getDeclaredMethod("concat", 1));
      }
      while (paramString == paramString.intern())
      {
        emitInvokeVirtual(Type.javalangStringType.getDeclaredMethod("intern", 0));
        return;
        ClassType localClassType = ClassType.make("java.lang.StringBuffer");
        emitNew(localClassType);
        emitDup(localClassType);
        emitPushInt(i);
        emitInvokeSpecial(localClassType.getDeclaredMethod("<init>", new Type[] { Type.intType }));
        Method localMethod = localClassType.getDeclaredMethod("append", new Type[] { Type.javalangStringType });
        int j = 0;
        i = 0;
        while (i < m)
        {
          emitDup(localClassType);
          int k = j + str.charAt(i);
          emitPushString(paramString.substring(j, k));
          emitInvokeVirtual(localMethod);
          j = k;
          i += 1;
        }
        emitInvokeVirtual(Type.toString_method);
      }
    }
  }
  
  public final void emitPushThis()
  {
    emitLoad(this.locals.used[0]);
  }
  
  public final void emitPutField(Field paramField)
  {
    popType();
    popType();
    emitFieldop(paramField, 181);
  }
  
  public final void emitPutStatic(Field paramField)
  {
    popType();
    emitFieldop(paramField, 179);
  }
  
  final void emitRawReturn()
  {
    if (getMethod().getReturnType().size == 0)
    {
      reserve(1);
      put1(177);
    }
    for (;;)
    {
      setUnreachable();
      return;
      emitTypedOp(172, popType().promote());
    }
  }
  
  public final void emitRem()
  {
    emitBinop(112);
  }
  
  public void emitRet(Variable paramVariable)
  {
    int i = paramVariable.offset;
    if (i < 256)
    {
      reserve(2);
      put1(169);
      put1(i);
      return;
    }
    reserve(4);
    put1(196);
    put1(169);
    put2(i);
  }
  
  public final void emitReturn()
  {
    if (this.try_stack != null) {
      new Error();
    }
    emitRawReturn();
  }
  
  public final void emitShl()
  {
    emitShift(120);
  }
  
  public final void emitShr()
  {
    emitShift(122);
  }
  
  public void emitStore(Variable paramVariable)
  {
    int i = paramVariable.offset;
    if ((i < 0) || (!paramVariable.isSimple())) {
      throw new Error("attempting to store in unassigned " + paramVariable + " simple:" + paramVariable.isSimple() + ", offset: " + i);
    }
    paramVariable = paramVariable.getType().promote();
    noteVarType(i, paramVariable);
    reserve(4);
    popType();
    int j = adjustTypedOp(paramVariable);
    if (i <= 3)
    {
      put1(j * 4 + 59 + i);
      return;
    }
    emitMaybeWide(j + 54, i);
  }
  
  public void emitStoreDefaultValue(Variable paramVariable)
  {
    emitPushDefaultValue(paramVariable.getType());
    emitStore(paramVariable);
  }
  
  public final void emitSub()
  {
    emitBinop(100);
  }
  
  public final void emitSub(char paramChar)
  {
    emitBinop(100, paramChar);
  }
  
  public final void emitSub(PrimType paramPrimType)
  {
    emitBinop(100, paramPrimType);
  }
  
  public void emitSwap()
  {
    reserve(1);
    Type localType1 = popType();
    Type localType2 = popType();
    if ((localType1.size > 4) || (localType2.size > 4))
    {
      pushType(localType2);
      pushType(localType1);
      emitDupX();
      emitPop(1);
      return;
    }
    pushType(localType1);
    put1(95);
    pushType(localType2);
  }
  
  public void emitTailCall(boolean paramBoolean, Scope paramScope)
  {
    if (paramBoolean)
    {
      Method localMethod = getMethod();
      int i;
      if ((localMethod.access_flags & 0x8) != 0)
      {
        i = 0;
        j = localMethod.arg_types.length;
        label29:
        k = j - 1;
        if (k < 0) {
          break label83;
        }
        if (localMethod.arg_types[k].size <= 4) {
          break label77;
        }
      }
      label77:
      for (int j = 2;; j = 1)
      {
        i += j;
        j = k;
        break label29;
        i = 1;
        break;
      }
      label83:
      j = localMethod.arg_types.length;
      int k = j - 1;
      if (k >= 0)
      {
        if (localMethod.arg_types[k].size > 4) {}
        for (j = 2;; j = 1)
        {
          i -= j;
          emitStore(this.locals.used[i]);
          j = k;
          break;
        }
      }
    }
    emitGoto(paramScope.start);
  }
  
  public final void emitThen()
  {
    this.if_stack.start_stack_size = this.SP;
  }
  
  public final void emitThrow()
  {
    popType();
    reserve(1);
    put1(191);
    setUnreachable();
  }
  
  final void emitTransfer(Label paramLabel, int paramInt)
  {
    paramLabel.setTypes(this);
    fixupAdd(6, paramLabel);
    put1(paramInt);
    this.PC += 2;
  }
  
  public void emitTryCatchEnd()
  {
    if (this.try_stack.finally_subr != null) {
      emitFinallyEnd();
    }
    Variable[] arrayOfVariable = this.try_stack.savedStack;
    if (this.try_stack.end_label == null) {
      setUnreachable();
    }
    for (;;)
    {
      if ((this.try_stack.saved_result != null) || (arrayOfVariable != null)) {
        popScope();
      }
      this.try_stack = this.try_stack.previous;
      return;
      setTypes(this.try_stack.start_try.localTypes, Type.typeArray0);
      this.try_stack.end_label.define(this);
      if (arrayOfVariable != null)
      {
        int i = arrayOfVariable.length;
        for (;;)
        {
          int j = i - 1;
          if (j < 0) {
            break;
          }
          Variable localVariable = arrayOfVariable[j];
          i = j;
          if (localVariable != null)
          {
            emitLoad(localVariable);
            i = j;
          }
        }
      }
      if (this.try_stack.saved_result != null) {
        emitLoad(this.try_stack.saved_result);
      }
    }
  }
  
  public void emitTryEnd()
  {
    emitTryEnd(false);
  }
  
  public void emitTryStart(boolean paramBoolean, Type paramType)
  {
    Type localType = paramType;
    if (paramType != null)
    {
      localType = paramType;
      if (paramType.isVoid()) {
        localType = null;
      }
    }
    paramType = null;
    if ((localType != null) || (this.SP > 0)) {
      pushScope();
    }
    int i;
    if (this.SP > 0)
    {
      localObject = new Variable[this.SP];
      i = 0;
      for (;;)
      {
        paramType = (Type)localObject;
        if (this.SP <= 0) {
          break;
        }
        paramType = addLocal(topType());
        emitStore(paramType);
        localObject[i] = paramType;
        i += 1;
      }
    }
    Object localObject = new TryState(this);
    ((TryState)localObject).savedStack = paramType;
    if (this.local_types == null)
    {
      i = 0;
      if ((i > 0) && (this.local_types[(i - 1)] == null)) {
        break label195;
      }
      if (i != 0) {
        break label204;
      }
      paramType = Type.typeArray0;
    }
    for (;;)
    {
      ((TryState)localObject).start_try.localTypes = paramType;
      if (localType != null) {
        ((TryState)localObject).saved_result = addLocal(localType);
      }
      if (paramBoolean) {
        ((TryState)localObject).finally_subr = new Label();
      }
      return;
      i = this.local_types.length;
      break;
      label195:
      i -= 1;
      break;
      label204:
      paramType = new Type[i];
      System.arraycopy(this.local_types, 0, paramType, 0, i);
    }
  }
  
  public final void emitUshr()
  {
    emitShift(124);
  }
  
  public void emitWithCleanupCatch(Variable paramVariable)
  {
    emitTryEnd();
    Type[] arrayOfType;
    if (this.SP > 0)
    {
      arrayOfType = new Type[this.SP];
      System.arraycopy(this.stack_types, 0, arrayOfType, 0, this.SP);
      this.SP = 0;
    }
    for (;;)
    {
      this.try_stack.savedTypes = arrayOfType;
      this.try_stack.saved_result = paramVariable;
      int i = this.SP;
      emitCatchStart(paramVariable);
      return;
      arrayOfType = null;
    }
  }
  
  public void emitWithCleanupDone()
  {
    Object localObject = this.try_stack.saved_result;
    this.try_stack.saved_result = null;
    if (localObject != null) {
      emitLoad((Variable)localObject);
    }
    emitThrow();
    emitCatchEnd();
    localObject = this.try_stack.savedTypes;
    emitTryCatchEnd();
    if (localObject != null)
    {
      this.SP = localObject.length;
      if (this.SP >= this.stack_types.length)
      {
        this.stack_types = ((Type[])localObject);
        return;
      }
      System.arraycopy(localObject, 0, this.stack_types, 0, this.SP);
      return;
    }
    this.SP = 0;
  }
  
  public void emitWithCleanupStart()
  {
    int i = this.SP;
    this.SP = 0;
    emitTryStart(false, null);
    this.SP = i;
  }
  
  public final void emitXOr()
  {
    emitBinop(130);
  }
  
  public void endExitableBlock()
  {
    ExitableBlock localExitableBlock = this.currentExitableBlock;
    localExitableBlock.finish();
    this.currentExitableBlock = localExitableBlock.outer;
  }
  
  public void endFragment(int paramInt)
  {
    this.fixup_offsets[paramInt] = (this.fixup_count << 4 | 0xA);
    Label localLabel = this.fixup_labels[paramInt];
    fixupAdd(9, 0, null);
    localLabel.define(this);
  }
  
  public void enterScope(Scope paramScope)
  {
    paramScope.setStartPC(this);
    this.locals.enterScope(paramScope);
  }
  
  final void fixupAdd(int paramInt1, int paramInt2, Label paramLabel)
  {
    if ((paramLabel != null) && (paramInt1 != 1) && (paramInt1 != 0) && (paramInt1 != 2) && (paramInt1 != 11)) {
      paramLabel.needsStackMapEntry = true;
    }
    int i = this.fixup_count;
    if (i == 0)
    {
      this.fixup_offsets = new int[30];
      this.fixup_labels = new Label[30];
    }
    for (;;)
    {
      this.fixup_offsets[i] = (paramInt2 << 4 | paramInt1);
      this.fixup_labels[i] = paramLabel;
      this.fixup_count = (i + 1);
      return;
      if (this.fixup_count == this.fixup_offsets.length)
      {
        int j = i * 2;
        Object localObject = new Label[j];
        System.arraycopy(this.fixup_labels, 0, localObject, 0, i);
        this.fixup_labels = ((Label[])localObject);
        localObject = new int[j];
        System.arraycopy(this.fixup_offsets, 0, localObject, 0, i);
        this.fixup_offsets = ((int[])localObject);
      }
    }
  }
  
  public final void fixupAdd(int paramInt, Label paramLabel)
  {
    fixupAdd(paramInt, this.PC, paramLabel);
  }
  
  public final void fixupChain(Label paramLabel1, Label paramLabel2)
  {
    fixupAdd(9, 0, paramLabel2);
    paramLabel1.defineRaw(this);
  }
  
  public Variable getArg(int paramInt)
  {
    return this.locals.parameter_scope.getVariable(paramInt);
  }
  
  public final Attribute getAttributes()
  {
    return this.attributes;
  }
  
  public byte[] getCode()
  {
    return this.code;
  }
  
  public int getCodeLength()
  {
    return this.PC;
  }
  
  public final ConstantPool getConstants()
  {
    return getMethod().classfile.constants;
  }
  
  public Scope getCurrentScope()
  {
    return this.locals.current_scope;
  }
  
  public final TryState getCurrentTry()
  {
    return this.try_stack;
  }
  
  public Label getLabel()
  {
    Label localLabel = new Label();
    localLabel.defineRaw(this);
    return localLabel;
  }
  
  public final int getLength()
  {
    return getCodeLength() + 12 + this.exception_table_length * 8 + Attribute.getLengthAll(this);
  }
  
  public int getMaxLocals()
  {
    return this.max_locals;
  }
  
  public int getMaxStack()
  {
    return this.max_stack;
  }
  
  public final Method getMethod()
  {
    return (Method)getContainer();
  }
  
  public final int getPC()
  {
    return this.PC;
  }
  
  public final int getSP()
  {
    return this.SP;
  }
  
  byte invert_opcode(byte paramByte)
  {
    paramByte &= 0xFF;
    if (((paramByte >= 153) && (paramByte <= 166)) || ((paramByte >= 198) && (paramByte <= 199))) {
      return (byte)(paramByte ^ 0x1);
    }
    throw new Error("unknown opcode to invert_opcode");
  }
  
  public final boolean isInTry()
  {
    return this.try_stack != null;
  }
  
  public Variable lookup(String paramString)
  {
    for (Scope localScope = this.locals.current_scope; localScope != null; localScope = localScope.parent)
    {
      Variable localVariable = localScope.lookup(paramString);
      if (localVariable != null) {
        return localVariable;
      }
    }
    return null;
  }
  
  void noteParamTypes()
  {
    Method localMethod = getMethod();
    int i = 0;
    Object localObject;
    if ((localMethod.access_flags & 0x8) == 0)
    {
      ClassType localClassType = localMethod.classfile;
      localObject = localClassType;
      if ("<init>".equals(localMethod.getName()))
      {
        localObject = localClassType;
        if (!"java.lang.Object".equals(localClassType.getName())) {
          localObject = UninitializedType.uninitializedThis((ClassType)localClassType);
        }
      }
      noteVarType(0, (Type)localObject);
      i = 0 + 1;
    }
    int m = localMethod.arg_types.length;
    int k = 0;
    for (int j = i; k < m; j = i)
    {
      localObject = localMethod.arg_types[k];
      i = j + 1;
      noteVarType(j, (Type)localObject);
      j = ((Type)localObject).getSizeInWords();
      for (;;)
      {
        j -= 1;
        if (j <= 0) {
          break;
        }
        i += 1;
      }
      k += 1;
    }
    if ((this.flags & 0x1) != 0)
    {
      this.stackMap = new StackMapTableAttr();
      localObject = new int[j + 20];
      i = 0;
      k = 0;
      while (i < j)
      {
        m = this.stackMap.encodeVerificationType(this.local_types[i], this);
        localObject[k] = m;
        int n = m & 0xFF;
        if (n != 3)
        {
          m = i;
          if (n != 4) {}
        }
        else
        {
          m = i + 1;
        }
        i = m + 1;
        k += 1;
      }
      this.stackMap.encodedLocals = ((int[])localObject);
      this.stackMap.countLocals = k;
      this.stackMap.encodedStack = new int[10];
      this.stackMap.countStack = 0;
    }
  }
  
  public void noteVarType(int paramInt, Type paramType)
  {
    int k = paramType.getSizeInWords();
    if (this.local_types == null)
    {
      this.local_types = new Type[paramInt + k + 20];
      this.local_types[paramInt] = paramType;
      if (this.varsSetInCurrentBlock != null) {
        break label196;
      }
      this.varsSetInCurrentBlock = new boolean[this.local_types.length];
    }
    for (;;)
    {
      this.varsSetInCurrentBlock[paramInt] = true;
      int i = k;
      int j = paramInt;
      if (paramInt > 0)
      {
        paramType = this.local_types[(paramInt - 1)];
        i = k;
        j = paramInt;
        if (paramType != null)
        {
          i = k;
          j = paramInt;
          if (paramType.getSizeInWords() == 2)
          {
            this.local_types[(paramInt - 1)] = null;
            j = paramInt;
            i = k;
          }
        }
      }
      for (;;)
      {
        i -= 1;
        if (i <= 0) {
          break;
        }
        paramType = this.local_types;
        j += 1;
        paramType[j] = null;
      }
      if (paramInt + k <= this.local_types.length) {
        break;
      }
      Type[] arrayOfType = new Type[(paramInt + k) * 2];
      System.arraycopy(this.local_types, 0, arrayOfType, 0, this.local_types.length);
      this.local_types = arrayOfType;
      break;
      label196:
      if (this.varsSetInCurrentBlock.length <= paramInt)
      {
        paramType = new boolean[this.local_types.length];
        System.arraycopy(this.varsSetInCurrentBlock, 0, paramType, 0, this.varsSetInCurrentBlock.length);
        this.varsSetInCurrentBlock = paramType;
      }
    }
  }
  
  public Scope popScope()
  {
    Scope localScope = this.locals.current_scope;
    this.locals.current_scope = localScope.parent;
    localScope.freeLocals(this);
    localScope.end = getLabel();
    return localScope;
  }
  
  public final Type popType()
  {
    if (this.SP <= 0) {
      throw new Error("popType called with empty stack " + getMethod());
    }
    Object localObject = this.stack_types;
    int i = this.SP - 1;
    this.SP = i;
    localObject = localObject[i];
    if ((((Type)localObject).size == 8) && (!popType().isVoid())) {
      throw new Error("missing void type on stack");
    }
    return (Type)localObject;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter)
  {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", max_stack:");
    paramClassTypeWriter.print(this.max_stack);
    paramClassTypeWriter.print(", max_locals:");
    paramClassTypeWriter.print(this.max_locals);
    paramClassTypeWriter.print(", code_length:");
    int i = getCodeLength();
    paramClassTypeWriter.println(i);
    disAssemble(paramClassTypeWriter, 0, i);
    if (this.exception_table_length > 0)
    {
      paramClassTypeWriter.print("Exceptions (count: ");
      paramClassTypeWriter.print(this.exception_table_length);
      paramClassTypeWriter.println("):");
      int j = this.exception_table_length;
      i = 0;
      j -= 1;
      if (j >= 0)
      {
        paramClassTypeWriter.print("  start: ");
        paramClassTypeWriter.print(this.exception_table[i] & 0xFFFF);
        paramClassTypeWriter.print(", end: ");
        paramClassTypeWriter.print(this.exception_table[(i + 1)] & 0xFFFF);
        paramClassTypeWriter.print(", handler: ");
        paramClassTypeWriter.print(this.exception_table[(i + 2)] & 0xFFFF);
        paramClassTypeWriter.print(", type: ");
        int k = this.exception_table[(i + 3)] & 0xFFFF;
        if (k == 0) {
          paramClassTypeWriter.print("0 /* finally */");
        }
        for (;;)
        {
          paramClassTypeWriter.println();
          i += 4;
          break;
          paramClassTypeWriter.printOptionalIndex(k);
          paramClassTypeWriter.printConstantTersely(k, 7);
        }
      }
    }
    paramClassTypeWriter.printAttributes(this);
  }
  
  public void processFixups()
  {
    if (this.fixup_count <= 0) {
      return;
    }
    int k = 0;
    int n = this.fixup_count;
    fixupAdd(9, 0, null);
    int j = 0;
    int i3 = this.fixup_offsets[j];
    int i2 = i3 >> 4;
    Object localObject1 = this.fixup_labels[j];
    int i = k;
    int m = j;
    int i1 = n;
    switch (i3 & 0xF)
    {
    case 7: 
    case 12: 
    case 13: 
    default: 
      throw new Error("unexpected fixup");
    case 11: 
      m = j + 2;
      i = k;
    case 0: 
    case 3: 
    case 8: 
    case 14: 
    case 1: 
    case 2: 
    case 4: 
    case 5: 
    case 6: 
      for (;;)
      {
        j = m + 1;
        k = i;
        break;
        m = j + 1;
        i = k;
        continue;
        ((Label)localObject1).position += k;
        i = k;
        m = j;
        continue;
        i = k + 3;
        m = j;
        continue;
        if ((((Label)localObject1).first_fixup == j + 1) && (fixupOffset(j + 1) == i2 + 3))
        {
          this.fixup_offsets[j] = (i2 << 4 | 0x8);
          this.fixup_labels[j] = null;
          i = k - 3;
          m = j;
        }
        else
        {
          i = k;
          m = j;
          if (this.PC >= 32768)
          {
            i = k + 2;
            m = j;
            continue;
            i = k;
            m = j;
            if (this.PC >= 32768)
            {
              i = k + 5;
              m = j;
            }
          }
        }
      }
    case 10: 
      this.fixup_labels[n] = this.fixup_labels[(j + 1)];
      i1 = i2;
    }
    if (j + 1 >= this.fixup_count)
    {
      i = this.PC;
      label386:
      this.fixup_offsets[j] = (i << 4 | 0x9);
      if (localObject1 != null) {
        break label513;
      }
      i3 = this.PC;
      m = 0;
      i = 0;
    }
    Object localObject2;
    label513:
    label914:
    label924:
    label969:
    byte[] arrayOfByte;
    int i4;
    for (;;)
    {
      if (i < this.fixup_count)
      {
        j = this.fixup_offsets[i];
        i1 = j & 0xF;
        localObject2 = this.fixup_labels[i];
        localObject1 = localObject2;
        if (localObject2 != null)
        {
          localObject1 = localObject2;
          if (((Label)localObject2).position < 0)
          {
            throw new Error("undefined label " + localObject2);
            i = fixupOffset(this.fixup_labels[(j + 1)].first_fixup);
            break label386;
            j = ((Label)localObject1).first_fixup;
            k = i + k - fixupOffset(j);
            n = i1;
            break;
          }
        }
        while ((localObject1 != null) && (i1 >= 4) && (i1 <= 7) && (((Label)localObject1).first_fixup + 1 < this.fixup_count) && (this.fixup_offsets[(localObject1.first_fixup + 1)] == (this.fixup_offsets[localObject1.first_fixup] & 0xF | 0x4)))
        {
          localObject1 = this.fixup_labels[(localObject1.first_fixup + 1)];
          this.fixup_labels[i] = localObject1;
        }
        i2 = j >> 4;
        j = m;
        n = i;
        k = i3;
        switch (i1)
        {
        case 7: 
        case 10: 
        case 12: 
        case 13: 
        default: 
          throw new Error("unexpected fixup");
        case 11: 
          n = i + 2;
          this.fixup_labels[n].position = (i2 + m);
          k = i3;
          j = m;
        case 0: 
        case 3: 
        case 14: 
        case 8: 
        case 1: 
        case 2: 
        case 4: 
        case 5: 
        case 6: 
          for (;;)
          {
            i = n + 1;
            m = j;
            i3 = k;
            break;
            n = i + 1;
            j = m;
            k = i3;
            continue;
            j = m - 3;
            k = i3 - 3;
            n = i;
            continue;
            ((Label)localObject1).position = (i2 + m);
            j = m;
            n = i;
            k = i3;
            continue;
            k = 3 - (i2 + m) & 0x3;
            j = m + k;
            k = i3 + k;
            n = i;
            continue;
            j = ((Label)localObject1).position - (i2 + m);
            if ((short)j != j) {
              break label914;
            }
            this.fixup_offsets[i] = (i2 << 4 | 0x7);
            j = m;
            n = i;
            k = i3;
          }
          if (i1 == 6)
          {
            j = 5;
            k = m + j;
            if (i1 != 6) {
              break label969;
            }
          }
          for (j = 5;; j = 2)
          {
            m = i3 + j;
            j = k;
            n = i;
            k = m;
            break;
            j = 2;
            break label924;
          }
        }
        if (localObject1 != null) {
          break label1040;
        }
      }
      arrayOfByte = new byte[i3];
      i4 = -1;
      m = 0;
      k = fixupOffset(0);
      localObject2 = null;
      j = 0;
      i = 0;
      while (j < k)
      {
        arrayOfByte[i] = this.code[j];
        j += 1;
        i += 1;
      }
      label1040:
      i = ((Label)localObject1).first_fixup;
      m = i2 + m - fixupOffset(i);
    }
    n = this.fixup_offsets[m] & 0xF;
    Label localLabel = this.fixup_labels[m];
    localObject1 = localObject2;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (((Label)localObject2).position < i)
      {
        this.stackMap.emitStackMapEntry((Label)localObject2, this);
        localObject1 = null;
      }
    }
    if ((localObject1 != null) && (((Label)localObject1).position > i)) {
      throw new Error("labels out of order");
    }
    int i5;
    switch (n)
    {
    case 3: 
    case 10: 
    case 12: 
    case 13: 
    default: 
      throw new Error("unexpected fixup");
    case 0: 
      i5 = i4;
      localObject2 = localObject1;
      k = j;
      j = i;
    }
    for (;;)
    {
      label1237:
      m += 1;
      i1 = fixupOffset(m);
      n = k;
      i = j;
      k = i1;
      j = n;
      i4 = i5;
      break;
      if ((this.stackMap != null) && (localLabel != null) && (localLabel.stackTypes != null) && (localLabel.needsStackMapEntry))
      {
        localObject2 = mergeLabels((Label)localObject1, localLabel);
        k = j;
        j = i;
        i5 = i4;
        continue;
        k = j + 3;
        j = i;
        localObject2 = localObject1;
        i5 = i4;
        continue;
        k = localLabel.position - i;
        n = i + 1;
        arrayOfByte[i] = this.code[j];
        i1 = n + 1;
        arrayOfByte[n] = ((byte)(k >> 8));
        i = i1 + 1;
        arrayOfByte[i1] = ((byte)(k & 0xFF));
        k = j + 3;
        j = i;
        localObject2 = localObject1;
        i5 = i4;
        continue;
        k = localLabel.position - i;
        byte b = this.code[j];
        if (n == 6)
        {
          b = invert_opcode(b);
          n = i + 1;
          arrayOfByte[i] = b;
          i1 = n + 1;
          arrayOfByte[n] = 0;
          i = i1 + 1;
          arrayOfByte[i1] = 8;
        }
        for (b = -56;; b = (byte)(b + 33))
        {
          n = i + 1;
          arrayOfByte[i] = b;
          i = n + 1;
          arrayOfByte[n] = ((byte)(k >> 24));
          n = i + 1;
          arrayOfByte[i] = ((byte)(k >> 16));
          i = n + 1;
          arrayOfByte[n] = ((byte)(k >> 8));
          arrayOfByte[i] = ((byte)(k & 0xFF));
          k = j + 3;
          j = i + 1;
          localObject2 = localObject1;
          i5 = i4;
          break;
        }
        k = 3 - i & 0x3;
        localObject2 = this.code;
        i1 = j + 1;
        arrayOfByte[i] = localObject2[j];
        j = i + 1;
        for (;;)
        {
          k -= 1;
          if (k < 0) {
            break;
          }
          arrayOfByte[j] = 0;
          j += 1;
        }
        label1662:
        m = this.fixup_labels[i2].position - i;
        n = k + 1;
        arrayOfByte[k] = ((byte)(m >> 24));
        k = n + 1;
        arrayOfByte[n] = ((byte)(m >> 16));
        n = k + 1;
        arrayOfByte[k] = ((byte)(m >> 8));
        arrayOfByte[n] = ((byte)(m & 0xFF));
        i1 = j + 4;
        n += 1;
        for (;;)
        {
          j = n;
          m = i2;
          k = i1;
          localObject2 = localObject1;
          i5 = i4;
          if (i2 >= this.fixup_count) {
            break label1237;
          }
          j = n;
          m = i2;
          k = i1;
          localObject2 = localObject1;
          i5 = i4;
          if (fixupKind(i2 + 1) != 3) {
            break label1237;
          }
          i2 += 1;
          m = fixupOffset(i2);
          j = i1;
          k = n;
          while (j < m)
          {
            arrayOfByte[k] = this.code[j];
            j += 1;
            k += 1;
          }
          break label1662;
          localLabel = this.fixup_labels[(m + 2)];
          k = fixupOffset(m + 1);
          localObject2 = localObject1;
          if (this.stackMap != null) {
            localObject2 = mergeLabels((Label)localObject1, localLabel);
          }
          addHandler(this.fixup_labels[m].position, this.fixup_labels[(m + 1)].position, i, k);
          m += 2;
          k = j;
          j = i;
          i5 = i4;
          break label1237;
          if (this.lines == null) {
            this.lines = new LineNumbersAttr(this);
          }
          m += 1;
          i5 = fixupOffset(m);
          if (i5 != i4) {
            this.lines.put(i5, i);
          }
          k = j;
          j = i;
          localObject2 = localObject1;
          break label1237;
          if (localLabel == null)
          {
            if (i3 != i) {
              throw new Error("PC confusion new_pc:" + i + " new_size:" + i3);
            }
          }
          else
          {
            m = localLabel.first_fixup;
            j = fixupOffset(m);
            k = j;
            if (localLabel.position == i) {
              break label2138;
            }
            throw new Error("bad pc");
          }
          this.PC = i3;
          this.code = arrayOfByte;
          this.fixup_count = 0;
          this.fixup_labels = null;
          this.fixup_offsets = null;
          return;
          label2138:
          localObject2 = localObject1;
          break;
          n = j;
          i2 = m;
        }
      }
      else
      {
        k = j;
        j = i;
        localObject2 = localObject1;
        i5 = i4;
      }
    }
  }
  
  public Scope pushScope()
  {
    Scope localScope = new Scope();
    if (this.locals == null) {
      this.locals = new LocalVarsAttr(getMethod());
    }
    enterScope(localScope);
    if (this.locals.parameter_scope == null) {
      this.locals.parameter_scope = localScope;
    }
    return localScope;
  }
  
  public final void pushType(Type paramType)
  {
    if (paramType.size == 0) {
      throw new Error("pushing void type onto stack");
    }
    if ((this.stack_types == null) || (this.stack_types.length == 0)) {
      this.stack_types = new Type[20];
    }
    for (;;)
    {
      if (paramType.size == 8)
      {
        arrayOfType = this.stack_types;
        i = this.SP;
        this.SP = (i + 1);
        arrayOfType[i] = Type.voidType;
      }
      Type[] arrayOfType = this.stack_types;
      int i = this.SP;
      this.SP = (i + 1);
      arrayOfType[i] = paramType;
      if (this.SP > this.max_stack) {
        this.max_stack = this.SP;
      }
      return;
      if (this.SP + 1 >= this.stack_types.length)
      {
        arrayOfType = new Type[this.stack_types.length * 2];
        System.arraycopy(this.stack_types, 0, arrayOfType, 0, this.SP);
        this.stack_types = arrayOfType;
      }
    }
  }
  
  public final void put1(int paramInt)
  {
    byte[] arrayOfByte = this.code;
    int i = this.PC;
    this.PC = (i + 1);
    arrayOfByte[i] = ((byte)paramInt);
    this.unreachable_here = false;
  }
  
  public final void put2(int paramInt)
  {
    byte[] arrayOfByte = this.code;
    int i = this.PC;
    this.PC = (i + 1);
    arrayOfByte[i] = ((byte)(paramInt >> 8));
    arrayOfByte = this.code;
    i = this.PC;
    this.PC = (i + 1);
    arrayOfByte[i] = ((byte)paramInt);
    this.unreachable_here = false;
  }
  
  public final void put4(int paramInt)
  {
    byte[] arrayOfByte = this.code;
    int i = this.PC;
    this.PC = (i + 1);
    arrayOfByte[i] = ((byte)(paramInt >> 24));
    arrayOfByte = this.code;
    i = this.PC;
    this.PC = (i + 1);
    arrayOfByte[i] = ((byte)(paramInt >> 16));
    arrayOfByte = this.code;
    i = this.PC;
    this.PC = (i + 1);
    arrayOfByte[i] = ((byte)(paramInt >> 8));
    arrayOfByte = this.code;
    i = this.PC;
    this.PC = (i + 1);
    arrayOfByte[i] = ((byte)paramInt);
    this.unreachable_here = false;
  }
  
  public final void putIndex2(CpoolEntry paramCpoolEntry)
  {
    put2(paramCpoolEntry.index);
  }
  
  public final void putLineNumber(int paramInt)
  {
    int i = paramInt;
    if (this.sourceDbgExt != null) {
      i = this.sourceDbgExt.fixLine(paramInt);
    }
    fixupAdd(14, null);
    fixupAdd(15, i, null);
  }
  
  public final void putLineNumber(String paramString, int paramInt)
  {
    if (paramString != null) {
      getMethod().classfile.setSourceFile(paramString);
    }
    putLineNumber(paramInt);
  }
  
  public final boolean reachableHere()
  {
    return !this.unreachable_here;
  }
  
  public final void reserve(int paramInt)
  {
    if (this.code == null) {
      this.code = new byte[paramInt + 100];
    }
    while (this.PC + paramInt <= this.code.length) {
      return;
    }
    byte[] arrayOfByte = new byte[this.code.length * 2 + paramInt];
    System.arraycopy(this.code, 0, arrayOfByte, 0, this.PC);
    this.code = arrayOfByte;
  }
  
  public final void setAttributes(Attribute paramAttribute)
  {
    this.attributes = paramAttribute;
  }
  
  public void setCode(byte[] paramArrayOfByte)
  {
    this.code = paramArrayOfByte;
    this.PC = paramArrayOfByte.length;
  }
  
  public void setCodeLength(int paramInt)
  {
    this.PC = paramInt;
  }
  
  public void setMaxLocals(int paramInt)
  {
    this.max_locals = paramInt;
  }
  
  public void setMaxStack(int paramInt)
  {
    this.max_stack = paramInt;
  }
  
  public final void setReachable(boolean paramBoolean)
  {
    if (!paramBoolean) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      this.unreachable_here = paramBoolean;
      return;
    }
  }
  
  public final void setTypes(Label paramLabel)
  {
    setTypes(paramLabel.localTypes, paramLabel.stackTypes);
  }
  
  public final void setTypes(Type[] paramArrayOfType1, Type[] paramArrayOfType2)
  {
    int j = paramArrayOfType2.length;
    int i = paramArrayOfType1.length;
    if (this.local_types != null)
    {
      if (i > 0) {
        System.arraycopy(paramArrayOfType1, 0, this.local_types, 0, i);
      }
      while (i < this.local_types.length)
      {
        this.local_types[i] = null;
        i += 1;
      }
    }
    if ((this.stack_types == null) || (j > this.stack_types.length)) {
      this.stack_types = new Type[j];
    }
    for (;;)
    {
      System.arraycopy(paramArrayOfType2, 0, this.stack_types, 0, j);
      this.SP = j;
      return;
      i = j;
      while (i < this.stack_types.length)
      {
        this.stack_types[i] = null;
        i += 1;
      }
    }
  }
  
  public final void setUnreachable()
  {
    this.unreachable_here = true;
  }
  
  public ExitableBlock startExitableBlock(Type paramType, boolean paramBoolean)
  {
    paramType = new ExitableBlock(paramType, this, paramBoolean);
    paramType.outer = this.currentExitableBlock;
    this.currentExitableBlock = paramType;
    return paramType;
  }
  
  public SwitchState startSwitch()
  {
    SwitchState localSwitchState = new SwitchState(this);
    localSwitchState.switchValuePushed(this);
    return localSwitchState;
  }
  
  public final Type topType()
  {
    return this.stack_types[(this.SP - 1)];
  }
  
  boolean useJsr()
  {
    return (this.flags & 0x2) == 0;
  }
  
  public void write(DataOutputStream paramDataOutputStream)
    throws IOException
  {
    paramDataOutputStream.writeShort(this.max_stack);
    paramDataOutputStream.writeShort(this.max_locals);
    paramDataOutputStream.writeInt(this.PC);
    paramDataOutputStream.write(this.code, 0, this.PC);
    paramDataOutputStream.writeShort(this.exception_table_length);
    int j = this.exception_table_length;
    int i = 0;
    for (;;)
    {
      j -= 1;
      if (j < 0) {
        break;
      }
      paramDataOutputStream.writeShort(this.exception_table[i]);
      paramDataOutputStream.writeShort(this.exception_table[(i + 1)]);
      paramDataOutputStream.writeShort(this.exception_table[(i + 2)]);
      paramDataOutputStream.writeShort(this.exception_table[(i + 3)]);
      i += 4;
    }
    Attribute.writeAll(this, paramDataOutputStream);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\CodeAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */