package gnu.kawa.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class PreProcess
{
  static final String JAVA4_FEATURES = "+JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio";
  static final String JAVA5_FEATURES = "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName";
  static final String NO_JAVA4_FEATURES = "-JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android";
  static final String NO_JAVA6_FEATURES = "-JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer";
  static String[] version_features = { "java1", "-JAVA2 -JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java2", "+JAVA2 -JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java4", "-JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java4x", "-JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +JAXP-1.3 +use:javax.xml.transform -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java5", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java6compat5", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName -JAVA6 -JAVA7 +JAVA6COMPAT5 +use:java.text.Normalizer -use:java.dyn -Android", "java6", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName +JAVA6 -JAVA7 -JAVA6COMPAT5 +use:java.text.Normalizer -use:java.dyn -Android", "java7", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName +JAVA6 +JAVA7 -JAVA6COMPAT5 +use:java.text.Normalizer +use:java.dyn -Android", "android", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +JAXP-1.3 -JAXP-QName -use:javax.xml.transform -JAVA6 -JAVA6COMPAT5 +Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer" };
  String filename;
  Hashtable keywords = new Hashtable();
  int lineno;
  byte[] resultBuffer;
  int resultLength;
  
  public static void main(String[] paramArrayOfString)
  {
    PreProcess localPreProcess = new PreProcess();
    localPreProcess.keywords.put("true", Boolean.TRUE);
    localPreProcess.keywords.put("false", Boolean.FALSE);
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      localPreProcess.handleArg(paramArrayOfString[i]);
      i += 1;
    }
  }
  
  void error(String paramString)
  {
    System.err.println(this.filename + ':' + this.lineno + ": " + paramString);
    System.exit(-1);
  }
  
  public void filter(String paramString)
    throws Throwable
  {
    if (filter(paramString, new BufferedInputStream(new FileInputStream(paramString))))
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
      localFileOutputStream.write(this.resultBuffer, 0, this.resultLength);
      localFileOutputStream.close();
      System.err.println("Pre-processed " + paramString);
    }
  }
  
  public boolean filter(String paramString, BufferedInputStream paramBufferedInputStream)
    throws Throwable
  {
    this.filename = paramString;
    Object localObject1 = new byte['ߐ'];
    int i = 0;
    int i5 = 0;
    int m = -1;
    this.lineno = 1;
    int i4 = -1;
    int k = 0;
    int i3 = 0;
    int i1 = 0;
    Object localObject2 = null;
    boolean bool1 = false;
    int n = 0;
    int i2 = 0;
    int i6 = paramBufferedInputStream.read();
    boolean bool2;
    if (i6 < 0)
    {
      bool2 = bool1;
      localObject3 = localObject1;
      m = i;
    }
    label270:
    label274:
    label321:
    do
    {
      if (i3 != 0)
      {
        this.lineno = i2;
        error("unterminated " + (String)localObject2);
      }
      this.resultBuffer = ((byte[])localObject3);
      this.resultLength = m;
      return bool2;
      if (i + 10 < localObject1.length) {
        break label1589;
      }
      localObject3 = new byte[i * 2];
      System.arraycopy(localObject1, 0, localObject3, 0, i);
      localObject1 = localObject3;
      if ((i6 == 10) && (i > 0) && (localObject1[(i - 1)] == 13))
      {
        localObject1[i] = ((byte)i6);
        i += 1;
        break;
      }
      if ((i4 < 0) || (m >= 0) || (n > 0) || (i6 == 13) || (i6 == 10) || ((i4 != k) && ((i6 == 32) || (i6 == 9)))) {
        break label1582;
      }
      if (i6 != 47) {
        break label578;
      }
      paramBufferedInputStream.mark(100);
      j = paramBufferedInputStream.read();
      if (j != 47) {
        break label526;
      }
      j = 0;
      paramBufferedInputStream.reset();
      if (j == 0) {
        break label1582;
      }
      j = i + 1;
      localObject1[i] = 47;
      i = j + 1;
      localObject1[j] = 47;
      localObject1[i] = 32;
      bool1 = true;
      i += 1;
      j = 1;
      if ((i6 == 32) || (i6 == 9) || (m >= 0)) {
        break label1559;
      }
      if ((i3 <= 0) || (i4 == k) || (i6 != 47)) {
        break label1536;
      }
      n = paramBufferedInputStream.read();
      m = i;
      localObject3 = localObject1;
      bool2 = bool1;
    } while (n < 0);
    if (n != 47)
    {
      localObject1[i] = 47;
      i6 = j;
      m = i + 1;
      j = i;
    }
    label412:
    int i7;
    for (i = i6;; i = i6)
    {
      localObject1[m] = ((byte)n);
      i6 = m + 1;
      if ((n != 13) && (n != 10)) {
        break label1455;
      }
      j = 0;
      i7 = -1;
      i = i5;
      i5 = j;
      while (i < i6 - 1)
      {
        j = i5;
        m = i7;
        if (localObject1[i] != 32)
        {
          j = i5;
          m = i7;
          if (localObject1[i] != 9)
          {
            if (i7 >= 0) {
              break label1500;
            }
            j = i;
            m = i;
          }
        }
        label509:
        i += 1;
        i5 = j;
        i7 = m;
      }
      label526:
      if (j == 42)
      {
        do
        {
          j = paramBufferedInputStream.read();
        } while ((j == 32) || (j == 9));
        if (j != 35)
        {
          j = 1;
          break label270;
        }
        j = 0;
        break label270;
      }
      j = 1;
      break label270;
      label578:
      j = 1;
      break label274;
      n = paramBufferedInputStream.read();
      m = i;
      localObject3 = localObject1;
      bool2 = bool1;
      if (n < 0) {
        break;
      }
      j = n;
      if (n != 32) {
        break label1511;
      }
      n = paramBufferedInputStream.read();
      if (n != 32)
      {
        j = n;
        if (n != 9) {
          break label1511;
        }
      }
      i6 = -1;
      j = -1;
      m = i;
      bool1 = true;
    }
    i = i2;
    Object localObject3 = localObject2;
    m = i1;
    int j = i4;
    n = i3;
    String str2;
    String str1;
    if (i5 - i7 >= 4)
    {
      i = i2;
      localObject3 = localObject2;
      m = i1;
      j = i4;
      n = i3;
      if (localObject1[i7] == 47)
      {
        i = i2;
        localObject3 = localObject2;
        m = i1;
        j = i4;
        n = i3;
        if (localObject1[(i7 + 1)] == 42)
        {
          i = i2;
          localObject3 = localObject2;
          m = i1;
          j = i4;
          n = i3;
          if (localObject1[(i5 - 1)] == 42)
          {
            i = i2;
            localObject3 = localObject2;
            m = i1;
            j = i4;
            n = i3;
            if (localObject1[i5] == 47)
            {
              i7 += 2;
              while ((i7 < i5) && (localObject1[i7] == 32)) {
                i7 += 1;
              }
              i5 -= 2;
              while ((i5 > i7) && (localObject1[i5] == 32)) {
                i5 -= 1;
              }
              i = i2;
              localObject3 = localObject2;
              m = i1;
              j = i4;
              n = i3;
              if (localObject1[i7] == 35)
              {
                str2 = new String((byte[])localObject1, i7, i5 - i7 + 1, "ISO-8859-1");
                j = str2.indexOf(' ');
                i = this.lineno;
                if (j <= 0) {
                  break label1144;
                }
                localObject2 = str2.substring(0, j);
                str1 = str2.substring(j).trim();
                localObject3 = this.keywords.get(str1);
                label975:
                if ((!"#ifdef".equals(localObject2)) && (!"#ifndef".equals(localObject2))) {
                  break label1221;
                }
                if (localObject3 != null) {
                  break label1641;
                }
                System.err.println(paramString + ":" + this.lineno + ": warning - undefined keyword: " + str1);
                localObject3 = Boolean.FALSE;
              }
            }
          }
        }
      }
    }
    label1074:
    label1125:
    label1144:
    label1172:
    label1215:
    label1221:
    label1455:
    label1500:
    label1511:
    label1536:
    label1559:
    label1582:
    label1589:
    label1603:
    label1641:
    for (;;)
    {
      j = i3 + 1;
      if (i1 > 0)
      {
        n = j;
        j = k;
        m = i1;
        localObject3 = localObject2;
      }
      for (;;)
      {
        i7 = -1;
        k = 0;
        this.lineno += 1;
        i5 = i6;
        i2 = 0;
        i3 = n;
        i4 = j;
        i1 = m;
        j = i7;
        localObject2 = localObject3;
        m = i;
        i = i2;
        i2 = m;
        n = i;
        m = j;
        i = i6;
        break;
        str1 = "";
        localObject3 = null;
        localObject2 = str2;
        break label975;
        if (((String)localObject2).charAt(3) == 'n')
        {
          m = 1;
          if (localObject3 != Boolean.FALSE) {
            break label1215;
          }
          n = 1;
        }
        for (;;)
        {
          if (m != n)
          {
            m = j;
            n = j;
            j = k;
            localObject3 = localObject2;
            break;
            m = 0;
            break label1172;
            n = 0;
            continue;
            if ("#else".equals(localObject2))
            {
              if (i3 == 0)
              {
                error("unexpected " + (String)localObject2);
                localObject3 = localObject2;
                m = i1;
                j = i4;
                n = i3;
                break;
              }
              if (i3 == i1)
              {
                j = -1;
                m = 0;
                localObject3 = localObject2;
                n = i3;
                break;
              }
              if (i1 != 0) {
                break label1603;
              }
              m = i3;
              j = k;
              localObject3 = localObject2;
              n = i3;
              break;
            }
            if ("#endif".equals(localObject2))
            {
              if (i3 == 0) {
                error("unexpected " + (String)localObject2);
              }
              if (i3 == i1)
              {
                j = 0;
                k = -1;
              }
            }
            for (;;)
            {
              n = i3 - 1;
              localObject3 = localObject2;
              m = j;
              j = k;
              break label1074;
              if (i1 > 0)
              {
                j = i1;
                continue;
                error("unknown command: " + str2);
                localObject3 = localObject2;
                m = i1;
                j = i4;
                n = i3;
                break label1074;
                if (j < 0)
                {
                  if (n == 9) {
                    k = k + 8 & 0xFFFFFFF8;
                  }
                  for (;;)
                  {
                    m = i2;
                    break;
                    k += 1;
                  }
                }
                m = i2;
                break label1125;
                j = i;
                m = i7;
                break label509;
                m = i;
                i6 = i;
                bool1 = true;
                n = j;
                i = -1;
                j = i6;
                break label412;
                i7 = j;
                j = i;
                m = i;
                n = i6;
                i = i7;
                break label412;
                n = i;
                i = j;
                j = m;
                m = n;
                n = i6;
                break label412;
                j = n;
                break label321;
                break;
              }
              j = i1;
              k = i4;
            }
            j = k;
            localObject3 = localObject2;
            m = i1;
            n = i3;
            break;
          }
        }
        n = j;
        localObject3 = localObject2;
        m = i1;
        j = i4;
      }
    }
  }
  
  void handleArg(String paramString)
  {
    int i = 0;
    Object localObject;
    if (paramString.charAt(0) == '%')
    {
      paramString = paramString.substring(1);
      for (;;)
      {
        if (i >= version_features.length)
        {
          System.err.println("Unknown version: " + paramString);
          System.exit(-1);
        }
        if (paramString.equals(version_features[i]))
        {
          localObject = version_features[(i + 1)];
          System.err.println("(variant " + paramString + " maps to: " + (String)localObject + ")");
          paramString = new StringTokenizer((String)localObject);
          while (paramString.hasMoreTokens()) {
            handleArg(paramString.nextToken());
          }
        }
        i += 2;
      }
    }
    if (paramString.charAt(0) == '+')
    {
      this.keywords.put(paramString.substring(1), Boolean.TRUE);
      return;
    }
    if (paramString.charAt(0) == '-')
    {
      int j = paramString.indexOf('=');
      if (j > 1)
      {
        String str1;
        String str2;
        if (paramString.charAt(1) == '-')
        {
          i = 2;
          str1 = paramString.substring(i, j);
          str2 = paramString.substring(j + 1);
          localObject = Boolean.FALSE;
          if (!str2.equalsIgnoreCase("true")) {
            break label272;
          }
          paramString = Boolean.TRUE;
        }
        for (;;)
        {
          this.keywords.put(str1, paramString);
          return;
          i = 1;
          break;
          label272:
          paramString = (String)localObject;
          if (!str2.equalsIgnoreCase("false"))
          {
            System.err.println("invalid value " + str2 + " for " + str1);
            System.exit(-1);
            paramString = (String)localObject;
          }
        }
      }
      this.keywords.put(paramString.substring(1), Boolean.FALSE);
      return;
    }
    try
    {
      filter(paramString);
      return;
    }
    catch (Throwable paramString)
    {
      System.err.println("caught " + paramString);
      paramString.printStackTrace();
      System.exit(-1);
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\util\PreProcess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */