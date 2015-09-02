package gnu.bytecode;

public class Access
{
  public static final short ABSTRACT = 1024;
  public static final short ANNOTATION = 8192;
  public static final short BRIDGE = 64;
  public static final char CLASS_CONTEXT = 'C';
  public static final short CLASS_MODIFIERS = 30257;
  public static final short ENUM = 16384;
  public static final char FIELD_CONTEXT = 'F';
  public static final short FIELD_MODIFIERS = 20703;
  public static final short FINAL = 16;
  public static final char INNERCLASS_CONTEXT = 'I';
  public static final short INNERCLASS_MODIFIERS = 30239;
  public static final short INTERFACE = 512;
  public static final char METHOD_CONTEXT = 'M';
  public static final short METHOD_MODIFIERS = 7679;
  public static final short NATIVE = 256;
  public static final short PRIVATE = 2;
  public static final short PROTECTED = 4;
  public static final short PUBLIC = 1;
  public static final short STATIC = 8;
  public static final short STRICT = 2048;
  public static final short SUPER = 32;
  public static final short SYNCHRONIZED = 32;
  public static final short SYNTHETIC = 4096;
  public static final short TRANSIENT = 128;
  public static final short VARARGS = 128;
  public static final short VOLATILE = 64;
  
  public static String toString(int paramInt)
  {
    return toString(paramInt, '\000');
  }
  
  public static String toString(int paramInt, char paramChar)
  {
    int i;
    int j;
    StringBuffer localStringBuffer;
    if (paramChar == 'C')
    {
      i = 30257;
      j = (short)((i ^ 0xFFFFFFFF) & paramInt);
      paramInt &= i;
      localStringBuffer = new StringBuffer();
      if ((paramInt & 0x1) != 0) {
        localStringBuffer.append(" public");
      }
      if ((paramInt & 0x2) != 0) {
        localStringBuffer.append(" private");
      }
      if ((paramInt & 0x4) != 0) {
        localStringBuffer.append(" protected");
      }
      if ((paramInt & 0x8) != 0) {
        localStringBuffer.append(" static");
      }
      if ((paramInt & 0x10) != 0) {
        localStringBuffer.append(" final");
      }
      if ((paramInt & 0x20) != 0)
      {
        if (paramChar != 'C') {
          break label349;
        }
        str = " super";
        label116:
        localStringBuffer.append(str);
      }
      if ((paramInt & 0x40) != 0)
      {
        if (paramChar != 'M') {
          break label355;
        }
        str = " bridge";
        label138:
        localStringBuffer.append(str);
      }
      if ((paramInt & 0x80) != 0) {
        if (paramChar != 'M') {
          break label361;
        }
      }
    }
    label349:
    label355:
    label361:
    for (String str = " varargs";; str = " transient")
    {
      localStringBuffer.append(str);
      if ((paramInt & 0x100) != 0) {
        localStringBuffer.append(" native");
      }
      if ((paramInt & 0x200) != 0) {
        localStringBuffer.append(" interface");
      }
      if ((paramInt & 0x400) != 0) {
        localStringBuffer.append(" abstract");
      }
      if ((paramInt & 0x800) != 0) {
        localStringBuffer.append(" strict");
      }
      if ((paramInt & 0x4000) != 0) {
        localStringBuffer.append(" enum");
      }
      if ((paramInt & 0x1000) != 0) {
        localStringBuffer.append(" synthetic");
      }
      if ((paramInt & 0x2000) != 0) {
        localStringBuffer.append(" annotation");
      }
      if (j != 0)
      {
        localStringBuffer.append(" unknown-flags:0x");
        localStringBuffer.append(Integer.toHexString(j));
      }
      return localStringBuffer.toString();
      if (paramChar == 'I')
      {
        i = 30239;
        break;
      }
      if (paramChar == 'F')
      {
        i = 20703;
        break;
      }
      if (paramChar == 'M')
      {
        i = 7679;
        break;
      }
      i = 32767;
      break;
      str = " synchronized";
      break label116;
      str = " volatile";
      break label138;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\Access.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */