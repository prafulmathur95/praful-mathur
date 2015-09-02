package gnu.xml;

import gnu.mapping.Symbol;

final class MappingInfo
{
  int index = -1;
  String local;
  NamespaceBinding namespaces;
  MappingInfo nextInBucket;
  String prefix;
  Symbol qname;
  int tagHash;
  XName type;
  String uri;
  
  static boolean equals(String paramString, StringBuffer paramStringBuffer)
  {
    int j = paramStringBuffer.length();
    if (paramString.length() != j) {
      return false;
    }
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label42;
      }
      if (paramStringBuffer.charAt(i) != paramString.charAt(i)) {
        break;
      }
      i += 1;
    }
    label42:
    return true;
  }
  
  static boolean equals(String paramString, char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (paramString.length() != paramInt2) {
      return false;
    }
    int i = 0;
    for (;;)
    {
      if (i >= paramInt2) {
        break label43;
      }
      if (paramArrayOfChar[(paramInt1 + i)] != paramString.charAt(i)) {
        break;
      }
      i += 1;
    }
    label43:
    return true;
  }
  
  static int hash(String paramString1, String paramString2)
  {
    int j = paramString2.hashCode();
    int i = j;
    if (paramString1 != null) {
      i = j ^ paramString1.hashCode();
    }
    return i;
  }
  
  static int hash(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int j = 0;
    int k = 0;
    int m = -1;
    int i = 0;
    if (i < paramInt2)
    {
      int n = paramArrayOfChar[(paramInt1 + i)];
      if ((n == 58) && (m < 0))
      {
        m = i;
        n = 0;
        k = j;
      }
      for (j = n;; j = j * 31 + n)
      {
        i += 1;
        break;
      }
    }
    return k ^ j;
  }
  
  boolean match(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (this.prefix != null)
    {
      int i = this.local.length();
      int j = this.prefix.length();
      return (paramInt2 == j + 1 + i) && (paramArrayOfChar[j] == ':') && (equals(this.prefix, paramArrayOfChar, paramInt1, j)) && (equals(this.local, paramArrayOfChar, paramInt1 + j + 1, i));
    }
    return equals(this.local, paramArrayOfChar, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\xml\MappingInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */