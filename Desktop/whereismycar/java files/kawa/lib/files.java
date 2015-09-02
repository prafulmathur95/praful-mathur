package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.FileUtils;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.IsEqual;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.FilePath;
import gnu.text.Path;
import gnu.text.URIPath;
import java.io.File;
import java.io.IOException;
import kawa.standard.readchar;

public class files
  extends ModuleBody
{
  public static final ModuleMethod $Mn$Grpathname;
  public static final ModuleMethod $Pcfile$Mnseparator;
  public static final files $instance;
  static final SimpleSymbol Lit0;
  static final SimpleSymbol Lit1;
  static final SimpleSymbol Lit10;
  static final SimpleSymbol Lit11;
  static final SimpleSymbol Lit12;
  static final SimpleSymbol Lit13;
  static final SimpleSymbol Lit14;
  static final SimpleSymbol Lit15;
  static final SimpleSymbol Lit16;
  static final SimpleSymbol Lit17;
  static final SimpleSymbol Lit18;
  static final SimpleSymbol Lit19;
  static final SimpleSymbol Lit2;
  static final SimpleSymbol Lit20;
  static final SimpleSymbol Lit21;
  static final SimpleSymbol Lit22;
  static final SimpleSymbol Lit23;
  static final SimpleSymbol Lit24;
  static final SimpleSymbol Lit25;
  static final SimpleSymbol Lit26;
  static final SimpleSymbol Lit27;
  static final SimpleSymbol Lit28;
  static final SimpleSymbol Lit29 = (SimpleSymbol)new SimpleSymbol("make-temporary-file").readResolve();
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  public static final ModuleMethod URI$Qu;
  public static final ModuleMethod absolute$Mnpath$Qu;
  public static final ModuleMethod copy$Mnfile;
  public static final ModuleMethod create$Mndirectory;
  public static final ModuleMethod delete$Mnfile;
  public static final ModuleMethod directory$Mnfiles;
  public static final ModuleMethod file$Mndirectory$Qu;
  public static final ModuleMethod file$Mnexists$Qu;
  public static final ModuleMethod file$Mnreadable$Qu;
  public static final ModuleMethod file$Mnwritable$Qu;
  public static final ModuleMethod filepath$Qu;
  public static final ModuleMethod make$Mntemporary$Mnfile;
  public static final ModuleMethod path$Mnauthority;
  public static final ModuleMethod path$Mndirectory;
  public static final ModuleMethod path$Mnextension;
  public static final ModuleMethod path$Mnfile;
  public static final ModuleMethod path$Mnfragment;
  public static final ModuleMethod path$Mnhost;
  public static final ModuleMethod path$Mnlast;
  public static final ModuleMethod path$Mnparent;
  public static final ModuleMethod path$Mnport;
  public static final ModuleMethod path$Mnquery;
  public static final ModuleMethod path$Mnscheme;
  public static final ModuleMethod path$Mnuser$Mninfo;
  public static final ModuleMethod path$Qu;
  public static final ModuleMethod rename$Mnfile;
  public static final ModuleMethod resolve$Mnuri;
  public static final ModuleMethod system$Mntmpdir;
  
  public static String $PcFileSeparator()
  {
    return System.getProperty("file.separator");
  }
  
  public static Path $To$Pathname(Object paramObject)
  {
    return Path.valueOf(paramObject);
  }
  
  static
  {
    Lit28 = (SimpleSymbol)new SimpleSymbol("resolve-uri").readResolve();
    Lit27 = (SimpleSymbol)new SimpleSymbol("system-tmpdir").readResolve();
    Lit26 = (SimpleSymbol)new SimpleSymbol("%file-separator").readResolve();
    Lit25 = (SimpleSymbol)new SimpleSymbol("->pathname").readResolve();
    Lit24 = (SimpleSymbol)new SimpleSymbol("directory-files").readResolve();
    Lit23 = (SimpleSymbol)new SimpleSymbol("create-directory").readResolve();
    Lit22 = (SimpleSymbol)new SimpleSymbol("copy-file").readResolve();
    Lit21 = (SimpleSymbol)new SimpleSymbol("rename-file").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("delete-file").readResolve();
    Lit19 = (SimpleSymbol)new SimpleSymbol("file-writable?").readResolve();
    Lit18 = (SimpleSymbol)new SimpleSymbol("file-readable?").readResolve();
    Lit17 = (SimpleSymbol)new SimpleSymbol("file-directory?").readResolve();
    Lit16 = (SimpleSymbol)new SimpleSymbol("file-exists?").readResolve();
    Lit15 = (SimpleSymbol)new SimpleSymbol("path-fragment").readResolve();
    Lit14 = (SimpleSymbol)new SimpleSymbol("path-query").readResolve();
    Lit13 = (SimpleSymbol)new SimpleSymbol("path-port").readResolve();
    Lit12 = (SimpleSymbol)new SimpleSymbol("path-extension").readResolve();
    Lit11 = (SimpleSymbol)new SimpleSymbol("path-last").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("path-parent").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("path-directory").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("path-file").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("path-host").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("path-user-info").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("path-authority").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("path-scheme").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("absolute-path?").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("URI?").readResolve();
    Lit1 = (SimpleSymbol)new SimpleSymbol("filepath?").readResolve();
    Lit0 = (SimpleSymbol)new SimpleSymbol("path?").readResolve();
    $instance = new files();
    files localfiles = $instance;
    path$Qu = new ModuleMethod(localfiles, 1, Lit0, 4097);
    filepath$Qu = new ModuleMethod(localfiles, 2, Lit1, 4097);
    URI$Qu = new ModuleMethod(localfiles, 3, Lit2, 4097);
    absolute$Mnpath$Qu = new ModuleMethod(localfiles, 4, Lit3, 4097);
    path$Mnscheme = new ModuleMethod(localfiles, 5, Lit4, 4097);
    path$Mnauthority = new ModuleMethod(localfiles, 6, Lit5, 4097);
    path$Mnuser$Mninfo = new ModuleMethod(localfiles, 7, Lit6, 4097);
    path$Mnhost = new ModuleMethod(localfiles, 8, Lit7, 4097);
    path$Mnfile = new ModuleMethod(localfiles, 9, Lit8, 4097);
    path$Mndirectory = new ModuleMethod(localfiles, 10, Lit9, 4097);
    path$Mnparent = new ModuleMethod(localfiles, 11, Lit10, 4097);
    path$Mnlast = new ModuleMethod(localfiles, 12, Lit11, 4097);
    path$Mnextension = new ModuleMethod(localfiles, 13, Lit12, 4097);
    path$Mnport = new ModuleMethod(localfiles, 14, Lit13, 4097);
    path$Mnquery = new ModuleMethod(localfiles, 15, Lit14, 4097);
    path$Mnfragment = new ModuleMethod(localfiles, 16, Lit15, 4097);
    file$Mnexists$Qu = new ModuleMethod(localfiles, 17, Lit16, 4097);
    file$Mndirectory$Qu = new ModuleMethod(localfiles, 18, Lit17, 4097);
    file$Mnreadable$Qu = new ModuleMethod(localfiles, 19, Lit18, 4097);
    file$Mnwritable$Qu = new ModuleMethod(localfiles, 20, Lit19, 4097);
    delete$Mnfile = new ModuleMethod(localfiles, 21, Lit20, 4097);
    rename$Mnfile = new ModuleMethod(localfiles, 22, Lit21, 8194);
    copy$Mnfile = new ModuleMethod(localfiles, 23, Lit22, 8194);
    create$Mndirectory = new ModuleMethod(localfiles, 24, Lit23, 4097);
    directory$Mnfiles = new ModuleMethod(localfiles, 25, Lit24, 4097);
    $Mn$Grpathname = new ModuleMethod(localfiles, 26, Lit25, 4097);
    $Pcfile$Mnseparator = new ModuleMethod(localfiles, 27, Lit26, 0);
    system$Mntmpdir = new ModuleMethod(localfiles, 28, Lit27, 0);
    resolve$Mnuri = new ModuleMethod(localfiles, 29, Lit28, 8194);
    make$Mntemporary$Mnfile = new ModuleMethod(localfiles, 30, Lit29, 4096);
    $instance.run();
  }
  
  public files()
  {
    ModuleInfo.register(this);
  }
  
  public static boolean URI$Qu(Object paramObject)
  {
    return paramObject instanceof URIPath;
  }
  
  public static void copyFile(Path paramPath1, Path paramPath2)
  {
    InPort localInPort = ports.openInputFile(paramPath1);
    paramPath2 = ports.openOutputFile(paramPath2);
    for (paramPath1 = readchar.readChar.apply1(localInPort); !ports.isEofObject(paramPath1); paramPath1 = readchar.readChar.apply1(localInPort)) {
      ports.writeChar(paramPath1, paramPath2);
    }
    ports.closeOutputPort(paramPath2);
    ports.closeInputPort(localInPort);
  }
  
  public static boolean createDirectory(FilePath paramFilePath)
  {
    return paramFilePath.toFile().mkdir();
  }
  
  public static void deleteFile(FilePath paramFilePath)
  {
    if (!paramFilePath.delete()) {
      throw ((Throwable)new IOException(Format.formatToString(0, new Object[] { "cannot delete ~a", paramFilePath }).toString()));
    }
  }
  
  public static Object directoryFiles(FilePath paramFilePath)
  {
    paramFilePath = paramFilePath.toFile();
    if (paramFilePath == null) {}
    for (paramFilePath = null;; paramFilePath = paramFilePath.toString())
    {
      paramFilePath = new File(paramFilePath).list();
      if (paramFilePath != null) {
        break;
      }
      return Boolean.FALSE;
    }
    return LList.makeList(paramFilePath, 0);
  }
  
  public static boolean isAbsolutePath(Path paramPath)
  {
    return paramPath.isAbsolute();
  }
  
  public static boolean isFileDirectory(Path paramPath)
  {
    return paramPath.isDirectory();
  }
  
  public static boolean isFileExists(Path paramPath)
  {
    return paramPath.exists();
  }
  
  public static boolean isFileReadable(FilePath paramFilePath)
  {
    return paramFilePath.toFile().canRead();
  }
  
  public static boolean isFileWritable(FilePath paramFilePath)
  {
    return paramFilePath.toFile().canWrite();
  }
  
  public static boolean isFilepath(Object paramObject)
  {
    return paramObject instanceof FilePath;
  }
  
  public static boolean isPath(Object paramObject)
  {
    return paramObject instanceof Path;
  }
  
  public static FilePath makeTemporaryFile()
  {
    return makeTemporaryFile("kawa~d.tmp");
  }
  
  public static FilePath makeTemporaryFile(CharSequence paramCharSequence)
  {
    return FilePath.makeFilePath(FileUtils.createTempFile(paramCharSequence.toString()));
  }
  
  public static Object pathAuthority(Path paramPath)
  {
    String str = paramPath.getAuthority();
    paramPath = str;
    if (str == null) {
      paramPath = Boolean.FALSE;
    }
    return paramPath;
  }
  
  public static Object pathDirectory(Path paramPath)
  {
    paramPath = paramPath.getDirectory();
    if (paramPath == null) {
      return Boolean.FALSE;
    }
    return paramPath.toString();
  }
  
  public static Object pathExtension(Path paramPath)
  {
    String str = paramPath.getExtension();
    paramPath = str;
    if (str == null) {
      paramPath = Boolean.FALSE;
    }
    return paramPath;
  }
  
  public static Object pathFile(Path paramPath)
  {
    String str = paramPath.getPath();
    paramPath = str;
    if (str == null) {
      paramPath = Boolean.FALSE;
    }
    return paramPath;
  }
  
  public static Object pathFragment(Path paramPath)
  {
    String str = paramPath.getFragment();
    paramPath = str;
    if (str == null) {
      paramPath = Boolean.FALSE;
    }
    return paramPath;
  }
  
  public static String pathHost(Path paramPath)
  {
    return paramPath.getHost();
  }
  
  public static Object pathLast(Path paramPath)
  {
    String str = paramPath.getLast();
    paramPath = str;
    if (str == null) {
      paramPath = Boolean.FALSE;
    }
    return paramPath;
  }
  
  public static Object pathParent(Path paramPath)
  {
    paramPath = paramPath.getParent();
    if (paramPath == null) {
      return Boolean.FALSE;
    }
    return paramPath.toString();
  }
  
  public static int pathPort(Path paramPath)
  {
    return paramPath.getPort();
  }
  
  public static Object pathQuery(Path paramPath)
  {
    String str = paramPath.getQuery();
    paramPath = str;
    if (str == null) {
      paramPath = Boolean.FALSE;
    }
    return paramPath;
  }
  
  public static Object pathScheme(Path paramPath)
  {
    String str = paramPath.getScheme();
    paramPath = str;
    if (str == null) {
      paramPath = Boolean.FALSE;
    }
    return paramPath;
  }
  
  public static Object pathUserInfo(Path paramPath)
  {
    String str = paramPath.getUserInfo();
    paramPath = str;
    if (str == null) {
      paramPath = Boolean.FALSE;
    }
    return paramPath;
  }
  
  public static boolean renameFile(FilePath paramFilePath1, FilePath paramFilePath2)
  {
    return paramFilePath1.toFile().renameTo(paramFilePath2.toFile());
  }
  
  public static Path resolveUri(Path paramPath1, Path paramPath2)
  {
    return paramPath2.resolve(paramPath1);
  }
  
  public static String systemTmpdir()
  {
    String str = System.getProperty("java.io.tmpdir");
    if (str != null) {
      return str;
    }
    if (IsEqual.apply($PcFileSeparator(), "\\")) {
      return "C:\\temp";
    }
    return "/tmp";
  }
  
  public Object apply0(ModuleMethod paramModuleMethod)
  {
    switch (paramModuleMethod.selector)
    {
    case 29: 
    default: 
      return super.apply0(paramModuleMethod);
    case 27: 
      return $PcFileSeparator();
    case 28: 
      return systemTmpdir();
    }
    return makeTemporaryFile();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    switch (paramModuleMethod.selector)
    {
    case 22: 
    case 23: 
    case 27: 
    case 28: 
    case 29: 
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 1: 
      if (isPath(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    case 2: 
      if (isFilepath(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    case 3: 
      if (URI$Qu(paramObject)) {
        return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    for (;;)
    {
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        if (isAbsolutePath(paramModuleMethod)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramModuleMethod)
      {
        try
        {
          paramModuleMethod = (CharSequence)paramObject;
          return makeTemporaryFile(paramModuleMethod);
        }
        catch (ClassCastException paramModuleMethod)
        {
          throw new WrongType(paramModuleMethod, "make-temporary-file", 1, paramObject);
        }
        paramModuleMethod = paramModuleMethod;
        throw new WrongType(paramModuleMethod, "absolute-path?", 1, paramObject);
      }
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        return pathScheme(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "path-scheme", 1, paramObject);
      }
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        return pathAuthority(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "path-authority", 1, paramObject);
      }
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        return pathUserInfo(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "path-user-info", 1, paramObject);
      }
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        return pathHost(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "path-host", 1, paramObject);
      }
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        return pathFile(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "path-file", 1, paramObject);
      }
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        return pathDirectory(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "path-directory", 1, paramObject);
      }
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        return pathParent(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "path-parent", 1, paramObject);
      }
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        return pathLast(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "path-last", 1, paramObject);
      }
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        return pathExtension(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "path-extension", 1, paramObject);
      }
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        return Integer.valueOf(pathPort(paramModuleMethod));
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "path-port", 1, paramObject);
      }
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        return pathQuery(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "path-query", 1, paramObject);
      }
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        return pathFragment(paramModuleMethod);
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "path-fragment", 1, paramObject);
      }
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        if (isFileExists(paramModuleMethod)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "file-exists?", 1, paramObject);
      }
      try
      {
        paramModuleMethod = Path.valueOf(paramObject);
        if (isFileDirectory(paramModuleMethod)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "file-directory?", 1, paramObject);
      }
      try
      {
        paramModuleMethod = FilePath.makeFilePath(paramObject);
        if (isFileReadable(paramModuleMethod)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "file-readable?", 1, paramObject);
      }
      try
      {
        paramModuleMethod = FilePath.makeFilePath(paramObject);
        if (isFileWritable(paramModuleMethod)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "file-writable?", 1, paramObject);
      }
      try
      {
        paramModuleMethod = FilePath.makeFilePath(paramObject);
        deleteFile(paramModuleMethod);
        return Values.empty;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "delete-file", 1, paramObject);
      }
      try
      {
        paramModuleMethod = FilePath.makeFilePath(paramObject);
        if (createDirectory(paramModuleMethod)) {
          return Boolean.TRUE;
        }
        return Boolean.FALSE;
      }
      catch (ClassCastException paramModuleMethod)
      {
        throw new WrongType(paramModuleMethod, "create-directory", 1, paramObject);
      }
    }
    try
    {
      paramModuleMethod = FilePath.makeFilePath(paramObject);
      return directoryFiles(paramModuleMethod);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "directory-files", 1, paramObject);
    }
    return $To$Pathname(paramObject);
  }
  
  /* Error */
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 530	gnu/expr/ModuleMethod:selector	I
    //   4: lookupswitch	default:+36->40, 22:+44->48, 23:+70->74, 29:+89->93
    //   40: aload_0
    //   41: aload_1
    //   42: aload_2
    //   43: aload_3
    //   44: invokespecial 612	gnu/expr/ModuleBody:apply2	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   47: areturn
    //   48: aload_2
    //   49: invokestatic 441	gnu/text/FilePath:makeFilePath	(Ljava/lang/Object;)Lgnu/text/FilePath;
    //   52: astore_1
    //   53: aload_3
    //   54: invokestatic 441	gnu/text/FilePath:makeFilePath	(Ljava/lang/Object;)Lgnu/text/FilePath;
    //   57: astore_2
    //   58: aload_1
    //   59: aload_2
    //   60: invokestatic 614	kawa/lib/files:renameFile	(Lgnu/text/FilePath;Lgnu/text/FilePath;)Z
    //   63: ifeq +7 -> 70
    //   66: getstatic 546	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   69: areturn
    //   70: getstatic 391	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   73: areturn
    //   74: aload_2
    //   75: invokestatic 86	gnu/text/Path:valueOf	(Ljava/lang/Object;)Lgnu/text/Path;
    //   78: astore_1
    //   79: aload_3
    //   80: invokestatic 86	gnu/text/Path:valueOf	(Ljava/lang/Object;)Lgnu/text/Path;
    //   83: astore_2
    //   84: aload_1
    //   85: aload_2
    //   86: invokestatic 616	kawa/lib/files:copyFile	(Lgnu/text/Path;Lgnu/text/Path;)V
    //   89: getstatic 597	gnu/mapping/Values:empty	Lgnu/mapping/Values;
    //   92: areturn
    //   93: aload_2
    //   94: invokestatic 86	gnu/text/Path:valueOf	(Ljava/lang/Object;)Lgnu/text/Path;
    //   97: astore_1
    //   98: aload_3
    //   99: invokestatic 86	gnu/text/Path:valueOf	(Ljava/lang/Object;)Lgnu/text/Path;
    //   102: astore_2
    //   103: aload_1
    //   104: aload_2
    //   105: invokestatic 618	kawa/lib/files:resolveUri	(Lgnu/text/Path;Lgnu/text/Path;)Lgnu/text/Path;
    //   108: areturn
    //   109: astore_1
    //   110: new 605	gnu/mapping/WrongType
    //   113: dup
    //   114: aload_1
    //   115: ldc -124
    //   117: iconst_1
    //   118: aload_2
    //   119: invokespecial 608	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   122: athrow
    //   123: astore_1
    //   124: new 605	gnu/mapping/WrongType
    //   127: dup
    //   128: aload_1
    //   129: ldc -124
    //   131: iconst_2
    //   132: aload_3
    //   133: invokespecial 608	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   136: athrow
    //   137: astore_1
    //   138: new 605	gnu/mapping/WrongType
    //   141: dup
    //   142: aload_1
    //   143: ldc -128
    //   145: iconst_1
    //   146: aload_2
    //   147: invokespecial 608	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   150: athrow
    //   151: astore_1
    //   152: new 605	gnu/mapping/WrongType
    //   155: dup
    //   156: aload_1
    //   157: ldc -128
    //   159: iconst_2
    //   160: aload_3
    //   161: invokespecial 608	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   164: athrow
    //   165: astore_1
    //   166: new 605	gnu/mapping/WrongType
    //   169: dup
    //   170: aload_1
    //   171: ldc 104
    //   173: iconst_1
    //   174: aload_2
    //   175: invokespecial 608	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   178: athrow
    //   179: astore_1
    //   180: new 605	gnu/mapping/WrongType
    //   183: dup
    //   184: aload_1
    //   185: ldc 104
    //   187: iconst_2
    //   188: aload_3
    //   189: invokespecial 608	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   192: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	193	0	this	files
    //   0	193	1	paramModuleMethod	ModuleMethod
    //   0	193	2	paramObject1	Object
    //   0	193	3	paramObject2	Object
    // Exception table:
    //   from	to	target	type
    //   48	53	109	java/lang/ClassCastException
    //   53	58	123	java/lang/ClassCastException
    //   74	79	137	java/lang/ClassCastException
    //   79	84	151	java/lang/ClassCastException
    //   93	98	165	java/lang/ClassCastException
    //   98	103	179	java/lang/ClassCastException
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    case 29: 
    default: 
      return super.match0(paramModuleMethod, paramCallContext);
    case 30: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 28: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    }
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 0;
    return 0;
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    case 22: 
    case 23: 
    case 27: 
    case 28: 
    case 29: 
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 30: 
      if ((paramObject instanceof CharSequence))
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 26: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 25: 
      if (FilePath.coerceToFilePathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 24: 
      if (FilePath.coerceToFilePathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 21: 
      if (FilePath.coerceToFilePathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 20: 
      if (FilePath.coerceToFilePathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 19: 
      if (FilePath.coerceToFilePathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 18: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 17: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 16: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 15: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 14: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 13: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 12: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 11: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 10: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 9: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 8: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 7: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 6: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 5: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 4: 
      if (Path.coerceToPathOrNull(paramObject) != null)
      {
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      }
      return -786431;
    case 3: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 2: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    }
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
    case 29: 
      if (Path.coerceToPathOrNull(paramObject1) != null)
      {
        paramCallContext.value1 = paramObject1;
        if (Path.coerceToPathOrNull(paramObject2) != null)
        {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        }
      }
      else
      {
        return -786431;
      }
      return -786430;
    case 23: 
      if (Path.coerceToPathOrNull(paramObject1) != null)
      {
        paramCallContext.value1 = paramObject1;
        if (Path.coerceToPathOrNull(paramObject2) != null)
        {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        }
      }
      else
      {
        return -786431;
      }
      return -786430;
    }
    if (FilePath.coerceToFilePathOrNull(paramObject1) != null)
    {
      paramCallContext.value1 = paramObject1;
      if (FilePath.coerceToFilePathOrNull(paramObject2) != null)
      {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      }
    }
    else
    {
      return -786431;
    }
    return -786430;
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lib\files.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */