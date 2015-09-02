package gnu.bytecode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class dump
  extends ClassFileInput
{
  ClassTypeWriter writer;
  
  dump(InputStream paramInputStream, ClassTypeWriter paramClassTypeWriter)
    throws IOException, ClassFormatError
  {
    super(paramInputStream);
    this.ctype = new ClassType();
    readFormatVersion();
    readConstants();
    readClassInfo();
    readFields();
    readMethods();
    readAttributes(this.ctype);
    paramClassTypeWriter.print(this.ctype);
    paramClassTypeWriter.flush();
  }
  
  /* Error */
  public static void main(String[] paramArrayOfString)
  {
    // Byte code:
    //   0: aload_0
    //   1: arraylength
    //   2: istore 7
    //   4: new 46	gnu/bytecode/ClassTypeWriter
    //   7: dup
    //   8: aconst_null
    //   9: getstatic 71	java/lang/System:out	Ljava/io/PrintStream;
    //   12: iconst_0
    //   13: invokespecial 74	gnu/bytecode/ClassTypeWriter:<init>	(Lgnu/bytecode/ClassType;Ljava/io/OutputStream;I)V
    //   16: astore 4
    //   18: iload 7
    //   20: ifne +9 -> 29
    //   23: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   26: invokestatic 81	gnu/bytecode/dump:usage	(Ljava/io/PrintStream;)V
    //   29: iconst_0
    //   30: istore 6
    //   32: iload 6
    //   34: iload 7
    //   36: if_icmpge +650 -> 686
    //   39: aload_0
    //   40: iload 6
    //   42: aaload
    //   43: astore_1
    //   44: aload_1
    //   45: ldc 83
    //   47: invokevirtual 89	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   50: ifne +12 -> 62
    //   53: aload_1
    //   54: ldc 91
    //   56: invokevirtual 89	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   59: ifeq +19 -> 78
    //   62: aload 4
    //   64: bipush 15
    //   66: invokevirtual 95	gnu/bytecode/ClassTypeWriter:setFlags	(I)V
    //   69: iload 6
    //   71: iconst_1
    //   72: iadd
    //   73: istore 6
    //   75: goto -43 -> 32
    //   78: aload_1
    //   79: invokestatic 99	gnu/bytecode/dump:uriSchemeSpecified	(Ljava/lang/String;)Z
    //   82: ifeq +443 -> 525
    //   85: aload_1
    //   86: ldc 101
    //   88: invokevirtual 104	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   91: istore 10
    //   93: aload_1
    //   94: astore_2
    //   95: iload 10
    //   97: istore 9
    //   99: iload 10
    //   101: ifeq +125 -> 226
    //   104: aload_1
    //   105: iconst_4
    //   106: invokevirtual 108	java/lang/String:substring	(I)Ljava/lang/String;
    //   109: astore 5
    //   111: aload_1
    //   112: astore_3
    //   113: aload 5
    //   115: invokestatic 99	gnu/bytecode/dump:uriSchemeSpecified	(Ljava/lang/String;)Z
    //   118: ifne +74 -> 192
    //   121: aload 5
    //   123: bipush 33
    //   125: invokevirtual 112	java/lang/String:indexOf	(I)I
    //   128: istore 8
    //   130: aload_1
    //   131: astore_3
    //   132: iload 8
    //   134: iflt +58 -> 192
    //   137: new 114	java/io/File
    //   140: dup
    //   141: aload 5
    //   143: iconst_0
    //   144: iload 8
    //   146: invokevirtual 117	java/lang/String:substring	(II)Ljava/lang/String;
    //   149: invokespecial 120	java/io/File:<init>	(Ljava/lang/String;)V
    //   152: invokevirtual 124	java/io/File:toURI	()Ljava/net/URI;
    //   155: invokevirtual 130	java/net/URI:toURL	()Ljava/net/URL;
    //   158: invokevirtual 136	java/net/URL:toString	()Ljava/lang/String;
    //   161: astore_1
    //   162: new 138	java/lang/StringBuilder
    //   165: dup
    //   166: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   169: ldc 101
    //   171: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: aload_1
    //   175: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: aload 5
    //   180: iload 8
    //   182: invokevirtual 108	java/lang/String:substring	(I)Ljava/lang/String;
    //   185: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: invokevirtual 144	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   191: astore_3
    //   192: aload_3
    //   193: astore_2
    //   194: iload 10
    //   196: istore 9
    //   198: aload 5
    //   200: ldc -110
    //   202: invokevirtual 149	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   205: ifge +21 -> 226
    //   208: aload_3
    //   209: bipush 33
    //   211: invokevirtual 152	java/lang/String:lastIndexOf	(I)I
    //   214: istore 8
    //   216: iload 8
    //   218: ifgt +62 -> 280
    //   221: iconst_0
    //   222: istore 9
    //   224: aload_3
    //   225: astore_2
    //   226: new 132	java/net/URL
    //   229: dup
    //   230: aload_2
    //   231: invokespecial 153	java/net/URL:<init>	(Ljava/lang/String;)V
    //   234: astore_3
    //   235: aload_3
    //   236: invokevirtual 157	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   239: invokevirtual 163	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   242: astore_1
    //   243: aload_1
    //   244: aload_2
    //   245: aload 4
    //   247: invokestatic 167	gnu/bytecode/dump:process	(Ljava/io/InputStream;Ljava/lang/String;Lgnu/bytecode/ClassTypeWriter;)V
    //   250: goto -181 -> 69
    //   253: astore_1
    //   254: aload_1
    //   255: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   258: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   261: ldc -84
    //   263: invokevirtual 177	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   266: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   269: aload_1
    //   270: invokevirtual 180	java/io/PrintStream:print	(Ljava/lang/Object;)V
    //   273: iconst_m1
    //   274: invokestatic 183	java/lang/System:exit	(I)V
    //   277: goto -208 -> 69
    //   280: aload_3
    //   281: astore_2
    //   282: iload 10
    //   284: istore 9
    //   286: aload_3
    //   287: bipush 47
    //   289: iload 8
    //   291: invokevirtual 186	java/lang/String:indexOf	(II)I
    //   294: ifge -68 -> 226
    //   297: aload_3
    //   298: iload 8
    //   300: iconst_1
    //   301: iadd
    //   302: invokevirtual 108	java/lang/String:substring	(I)Ljava/lang/String;
    //   305: bipush 46
    //   307: bipush 47
    //   309: invokevirtual 190	java/lang/String:replace	(CC)Ljava/lang/String;
    //   312: astore_1
    //   313: new 138	java/lang/StringBuilder
    //   316: dup
    //   317: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   320: aload_3
    //   321: iconst_0
    //   322: iload 8
    //   324: iconst_1
    //   325: iadd
    //   326: invokevirtual 117	java/lang/String:substring	(II)Ljava/lang/String;
    //   329: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   332: bipush 47
    //   334: invokevirtual 193	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   337: aload_1
    //   338: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: ldc -61
    //   343: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: invokevirtual 144	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   349: astore_2
    //   350: iload 10
    //   352: istore 9
    //   354: goto -128 -> 226
    //   357: astore 5
    //   359: iload 9
    //   361: ifeq +46 -> 407
    //   364: aload_3
    //   365: invokevirtual 198	java/net/URL:getFile	()Ljava/lang/String;
    //   368: astore_3
    //   369: aload_3
    //   370: bipush 33
    //   372: invokevirtual 152	java/lang/String:lastIndexOf	(I)I
    //   375: istore 8
    //   377: aload_3
    //   378: astore_1
    //   379: iload 8
    //   381: ifle +11 -> 392
    //   384: aload_3
    //   385: iconst_0
    //   386: iload 8
    //   388: invokevirtual 117	java/lang/String:substring	(II)Ljava/lang/String;
    //   391: astore_1
    //   392: new 132	java/net/URL
    //   395: dup
    //   396: aload_1
    //   397: invokespecial 153	java/net/URL:<init>	(Ljava/lang/String;)V
    //   400: invokevirtual 157	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   403: invokevirtual 163	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   406: pop
    //   407: aload 5
    //   409: athrow
    //   410: astore_1
    //   411: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   414: ldc -56
    //   416: invokevirtual 202	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   419: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   422: aload_2
    //   423: invokevirtual 202	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   426: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   429: ldc -52
    //   431: invokevirtual 177	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   434: iconst_m1
    //   435: invokestatic 183	java/lang/System:exit	(I)V
    //   438: aconst_null
    //   439: astore_1
    //   440: goto -197 -> 243
    //   443: astore_3
    //   444: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   447: ldc -50
    //   449: invokevirtual 202	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   452: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   455: aload_1
    //   456: invokevirtual 202	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   459: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   462: ldc -52
    //   464: invokevirtual 177	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   467: iconst_m1
    //   468: invokestatic 183	java/lang/System:exit	(I)V
    //   471: goto -64 -> 407
    //   474: astore_1
    //   475: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   478: ldc -48
    //   480: invokevirtual 202	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   483: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   486: aload_2
    //   487: invokevirtual 202	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   490: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   493: ldc -52
    //   495: invokevirtual 177	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   498: aload_1
    //   499: invokevirtual 209	java/util/zip/ZipException:printStackTrace	()V
    //   502: aload_1
    //   503: invokevirtual 213	java/util/zip/ZipException:getCause	()Ljava/lang/Throwable;
    //   506: ifnull +10 -> 516
    //   509: aload_1
    //   510: invokevirtual 213	java/util/zip/ZipException:getCause	()Ljava/lang/Throwable;
    //   513: invokevirtual 214	java/lang/Throwable:printStackTrace	()V
    //   516: iconst_m1
    //   517: invokestatic 183	java/lang/System:exit	(I)V
    //   520: aconst_null
    //   521: astore_1
    //   522: goto -279 -> 243
    //   525: new 216	java/io/FileInputStream
    //   528: dup
    //   529: aload_1
    //   530: invokespecial 217	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   533: astore_3
    //   534: aload_1
    //   535: astore_2
    //   536: aload_3
    //   537: astore_1
    //   538: goto -295 -> 243
    //   541: astore_2
    //   542: aload_1
    //   543: invokestatic 223	gnu/bytecode/ObjectType:getContextClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   546: invokevirtual 229	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
    //   549: astore_2
    //   550: new 138	java/lang/StringBuilder
    //   553: dup
    //   554: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   557: aload_1
    //   558: bipush 46
    //   560: bipush 47
    //   562: invokevirtual 190	java/lang/String:replace	(CC)Ljava/lang/String;
    //   565: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   568: ldc -61
    //   570: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: invokevirtual 144	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   576: astore_3
    //   577: aload_2
    //   578: aload_3
    //   579: invokevirtual 235	java/lang/ClassLoader:getResource	(Ljava/lang/String;)Ljava/net/URL;
    //   582: astore_2
    //   583: aload_2
    //   584: invokevirtual 157	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   587: invokevirtual 163	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   590: astore_3
    //   591: aload_2
    //   592: invokevirtual 136	java/net/URL:toString	()Ljava/lang/String;
    //   595: astore_2
    //   596: aload_3
    //   597: astore_1
    //   598: goto -355 -> 243
    //   601: astore_2
    //   602: invokestatic 238	gnu/bytecode/ObjectType:getContextClassLoader	()Ljava/lang/ClassLoader;
    //   605: astore_2
    //   606: goto -56 -> 550
    //   609: astore_2
    //   610: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   613: ldc -16
    //   615: invokevirtual 202	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   618: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   621: aload_1
    //   622: invokevirtual 202	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   625: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   628: ldc -52
    //   630: invokevirtual 177	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   633: iconst_m1
    //   634: invokestatic 183	java/lang/System:exit	(I)V
    //   637: aconst_null
    //   638: astore_2
    //   639: goto -89 -> 550
    //   642: astore_2
    //   643: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   646: ldc -14
    //   648: invokevirtual 202	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   651: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   654: aload_1
    //   655: invokevirtual 202	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   658: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   661: ldc -12
    //   663: invokevirtual 202	java/io/PrintStream:print	(Ljava/lang/String;)V
    //   666: getstatic 77	java/lang/System:err	Ljava/io/PrintStream;
    //   669: aload_2
    //   670: invokevirtual 246	java/io/PrintStream:println	(Ljava/lang/Object;)V
    //   673: iconst_m1
    //   674: invokestatic 183	java/lang/System:exit	(I)V
    //   677: aconst_null
    //   678: astore_3
    //   679: aload_1
    //   680: astore_2
    //   681: aload_3
    //   682: astore_1
    //   683: goto -440 -> 243
    //   686: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	687	0	paramArrayOfString	String[]
    //   43	201	1	localObject1	Object
    //   253	17	1	localIOException	IOException
    //   312	85	1	localObject2	Object
    //   410	1	1	localFileNotFoundException1	java.io.FileNotFoundException
    //   439	17	1	str1	String
    //   474	36	1	localZipException1	java.util.zip.ZipException
    //   521	162	1	localObject3	Object
    //   94	442	2	localObject4	Object
    //   541	1	2	localFileNotFoundException2	java.io.FileNotFoundException
    //   549	47	2	localObject5	Object
    //   601	1	2	localNoClassDefFoundError	NoClassDefFoundError
    //   605	1	2	localClassLoader	ClassLoader
    //   609	1	2	localThrowable1	Throwable
    //   638	1	2	localObject6	Object
    //   642	28	2	localThrowable2	Throwable
    //   680	1	2	localObject7	Object
    //   112	273	3	localObject8	Object
    //   443	1	3	localFileNotFoundException3	java.io.FileNotFoundException
    //   533	149	3	localObject9	Object
    //   16	230	4	localClassTypeWriter	ClassTypeWriter
    //   109	90	5	str2	String
    //   357	51	5	localZipException2	java.util.zip.ZipException
    //   30	44	6	i	int
    //   2	35	7	j	int
    //   128	259	8	k	int
    //   97	263	9	bool1	boolean
    //   91	260	10	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   85	93	253	java/io/IOException
    //   104	111	253	java/io/IOException
    //   113	130	253	java/io/IOException
    //   137	192	253	java/io/IOException
    //   198	216	253	java/io/IOException
    //   226	235	253	java/io/IOException
    //   235	243	253	java/io/IOException
    //   243	250	253	java/io/IOException
    //   286	350	253	java/io/IOException
    //   364	377	253	java/io/IOException
    //   384	392	253	java/io/IOException
    //   392	407	253	java/io/IOException
    //   407	410	253	java/io/IOException
    //   411	438	253	java/io/IOException
    //   444	471	253	java/io/IOException
    //   475	516	253	java/io/IOException
    //   516	520	253	java/io/IOException
    //   525	534	253	java/io/IOException
    //   542	550	253	java/io/IOException
    //   550	577	253	java/io/IOException
    //   577	596	253	java/io/IOException
    //   602	606	253	java/io/IOException
    //   610	637	253	java/io/IOException
    //   643	677	253	java/io/IOException
    //   235	243	357	java/util/zip/ZipException
    //   226	235	410	java/io/FileNotFoundException
    //   235	243	410	java/io/FileNotFoundException
    //   364	377	410	java/io/FileNotFoundException
    //   384	392	410	java/io/FileNotFoundException
    //   407	410	410	java/io/FileNotFoundException
    //   444	471	410	java/io/FileNotFoundException
    //   392	407	443	java/io/FileNotFoundException
    //   226	235	474	java/util/zip/ZipException
    //   364	377	474	java/util/zip/ZipException
    //   384	392	474	java/util/zip/ZipException
    //   392	407	474	java/util/zip/ZipException
    //   407	410	474	java/util/zip/ZipException
    //   444	471	474	java/util/zip/ZipException
    //   525	534	541	java/io/FileNotFoundException
    //   542	550	601	java/lang/NoClassDefFoundError
    //   542	550	609	java/lang/Throwable
    //   577	596	642	java/lang/Throwable
  }
  
  public static void process(InputStream paramInputStream, String paramString, ClassTypeWriter paramClassTypeWriter)
    throws IOException
  {
    paramInputStream = new BufferedInputStream(paramInputStream);
    paramInputStream.mark(5);
    int i = readMagic(paramInputStream);
    if (i == -889275714)
    {
      paramClassTypeWriter.print("Reading .class from ");
      paramClassTypeWriter.print(paramString);
      paramClassTypeWriter.println('.');
      new dump(paramInputStream, paramClassTypeWriter);
      return;
    }
    if (i == 1347093252)
    {
      paramInputStream.reset();
      paramClassTypeWriter.print("Reading classes from archive ");
      paramClassTypeWriter.print(paramString);
      paramClassTypeWriter.println('.');
      paramInputStream = new ZipInputStream(paramInputStream);
      for (;;)
      {
        paramString = paramInputStream.getNextEntry();
        if (paramString == null) {
          break;
        }
        String str = paramString.getName();
        if (paramString.isDirectory())
        {
          paramClassTypeWriter.print("Archive directory: ");
          paramClassTypeWriter.print(str);
          paramClassTypeWriter.println('.');
        }
        else
        {
          paramClassTypeWriter.println();
          if (readMagic(paramInputStream) == -889275714)
          {
            paramClassTypeWriter.print("Reading class member: ");
            paramClassTypeWriter.print(str);
            paramClassTypeWriter.println('.');
            new dump(paramInputStream, paramClassTypeWriter);
          }
          else
          {
            paramClassTypeWriter.print("Skipping non-class member: ");
            paramClassTypeWriter.print(str);
            paramClassTypeWriter.println('.');
          }
        }
      }
      System.exit(-1);
      return;
    }
    System.err.println("File " + paramString + " is not a valid .class file");
    System.exit(-1);
  }
  
  public static void process(InputStream paramInputStream, String paramString, OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    process(paramInputStream, paramString, new ClassTypeWriter(null, paramOutputStream, paramInt));
  }
  
  public static void process(InputStream paramInputStream, String paramString, Writer paramWriter, int paramInt)
    throws IOException
  {
    process(paramInputStream, paramString, new ClassTypeWriter(null, paramWriter, paramInt));
  }
  
  static int readMagic(InputStream paramInputStream)
    throws IOException
  {
    int j = 0;
    int i = 0;
    for (;;)
    {
      int k;
      if (i < 4)
      {
        k = paramInputStream.read();
        if (k >= 0) {}
      }
      else
      {
        return j;
      }
      j = j << 8 | k & 0xFF;
      i += 1;
    }
  }
  
  static int uriSchemeLength(String paramString)
  {
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      char c = paramString.charAt(i);
      if (c == ':') {
        return i;
      }
      if (i == 0)
      {
        if (Character.isLetter(c)) {}
      }
      else {
        while ((!Character.isLetterOrDigit(c)) && (c != '+') && (c != '-') && (c != '.')) {
          return -1;
        }
      }
      i += 1;
    }
    return -1;
  }
  
  static boolean uriSchemeSpecified(String paramString)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    int i = uriSchemeLength(paramString);
    if ((i == 1) && (File.separatorChar == '\\'))
    {
      i = paramString.charAt(0);
      if (i >= 97)
      {
        bool1 = bool2;
        if (i <= 122) {}
      }
      else if (i >= 65)
      {
        bool1 = bool2;
        if (i <= 90) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    if (i > 0) {}
    for (;;)
    {
      return bool1;
      bool1 = false;
    }
  }
  
  public static void usage(PrintStream paramPrintStream)
  {
    paramPrintStream.println("Prints and dis-assembles the contents of JVM .class files.");
    paramPrintStream.println("Usage: [--verbose] class-or-jar ...");
    paramPrintStream.println("where a class-or-jar can be one of:");
    paramPrintStream.println("- a fully-qualified class name; or");
    paramPrintStream.println("- the name of a .class file, or a URL reference to one; or");
    paramPrintStream.println("- the name of a .jar or .zip archive file, or a URL reference to one.");
    paramPrintStream.println("If a .jar/.zip archive is named, all its.class file members are printed.");
    paramPrintStream.println();
    paramPrintStream.println("You can name a single .class member of an archive with a jar: URL,");
    paramPrintStream.println("which looks like: jar:jar-spec!/p1/p2/cl.class");
    paramPrintStream.println("The jar-spec can be a URL or the name of the .jar file.");
    paramPrintStream.println("You can also use the shorthand syntax: jar:jar-spec!p1.p2.cl");
    System.exit(-1);
  }
  
  public Attribute readAttribute(String paramString, int paramInt, AttrContainer paramAttrContainer)
    throws IOException
  {
    return super.readAttribute(paramString, paramInt, paramAttrContainer);
  }
  
  public ConstantPool readConstants()
    throws IOException
  {
    this.ctype.constants = super.readConstants();
    return this.ctype.constants;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\bytecode\dump.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */