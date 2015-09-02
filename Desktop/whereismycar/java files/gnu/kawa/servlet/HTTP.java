package gnu.kawa.servlet;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Format;
import gnu.kawa.xml.MakeResponseHeader;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.URIPath;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

public class HTTP
  extends ModuleBody
{
  public static final HTTP $instance;
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
  static final SimpleSymbol Lit29;
  static final SimpleSymbol Lit3;
  static final SimpleSymbol Lit30;
  static final SimpleSymbol Lit31;
  static final SimpleSymbol Lit32 = (SimpleSymbol)new SimpleSymbol("request-input-port").readResolve();
  static final SimpleSymbol Lit4;
  static final SimpleSymbol Lit5;
  static final SimpleSymbol Lit6;
  static final SimpleSymbol Lit7;
  static final SimpleSymbol Lit8;
  static final SimpleSymbol Lit9;
  public static final ModuleMethod error$Mnresponse;
  public static final ModuleMethod request$MnURI;
  public static final ModuleMethod request$Mnbody$Mnstring;
  public static final ModuleMethod request$Mncontext$Mnpath;
  public static final ModuleMethod request$Mnheader;
  public static final ModuleMethod request$Mnheader$Mnmap;
  public static final ModuleMethod request$Mninput$Mnport;
  public static final ModuleMethod request$Mninput$Mnstream;
  public static final ModuleMethod request$Mnlocal$MnIP$Mnaddress;
  public static final ModuleMethod request$Mnlocal$Mnhost;
  public static final ModuleMethod request$Mnlocal$Mnpath;
  public static final ModuleMethod request$Mnlocal$Mnport;
  public static final ModuleMethod request$Mnlocal$Mnsocket$Mnaddress;
  public static final ModuleMethod request$Mnmethod;
  public static final ModuleMethod request$Mnparameter;
  public static final ModuleMethod request$Mnparameter$Mnmap;
  public static final ModuleMethod request$Mnparameters;
  public static final ModuleMethod request$Mnpath;
  public static final ModuleMethod request$Mnpath$Mntranslated;
  public static final ModuleMethod request$Mnquery$Mnstring;
  public static final ModuleMethod request$Mnremote$MnIP$Mnaddress;
  public static final ModuleMethod request$Mnremote$Mnhost;
  public static final ModuleMethod request$Mnremote$Mnport;
  public static final ModuleMethod request$Mnremote$Mnsocket$Mnaddress;
  public static final ModuleMethod request$Mnscheme;
  public static final ModuleMethod request$Mnscript$Mnpath;
  public static final ModuleMethod request$Mnuri;
  public static final ModuleMethod request$Mnurl;
  public static final ModuleMethod response$Mncontent$Mntype;
  public static final ModuleMethod response$Mnheader;
  public static final ModuleMethod response$Mnstatus;
  
  static
  {
    Lit31 = (SimpleSymbol)new SimpleSymbol("request-input-stream").readResolve();
    Lit30 = (SimpleSymbol)new SimpleSymbol("request-body-string").readResolve();
    Lit29 = (SimpleSymbol)new SimpleSymbol("request-parameter-map").readResolve();
    Lit28 = (SimpleSymbol)new SimpleSymbol("request-parameters").readResolve();
    Lit27 = (SimpleSymbol)new SimpleSymbol("request-parameter").readResolve();
    Lit26 = (SimpleSymbol)new SimpleSymbol("request-query-string").readResolve();
    Lit25 = (SimpleSymbol)new SimpleSymbol("request-path-translated").readResolve();
    Lit24 = (SimpleSymbol)new SimpleSymbol("request-url").readResolve();
    Lit23 = (SimpleSymbol)new SimpleSymbol("request-uri").readResolve();
    Lit22 = (SimpleSymbol)new SimpleSymbol("request-path").readResolve();
    Lit21 = (SimpleSymbol)new SimpleSymbol("request-local-path").readResolve();
    Lit20 = (SimpleSymbol)new SimpleSymbol("request-script-path").readResolve();
    Lit19 = (SimpleSymbol)new SimpleSymbol("request-context-path").readResolve();
    Lit18 = (SimpleSymbol)new SimpleSymbol("request-URI").readResolve();
    Lit17 = (SimpleSymbol)new SimpleSymbol("request-header-map").readResolve();
    Lit16 = (SimpleSymbol)new SimpleSymbol("request-header").readResolve();
    Lit15 = (SimpleSymbol)new SimpleSymbol("request-remote-host").readResolve();
    Lit14 = (SimpleSymbol)new SimpleSymbol("request-remote-port").readResolve();
    Lit13 = (SimpleSymbol)new SimpleSymbol("request-remote-IP-address").readResolve();
    Lit12 = (SimpleSymbol)new SimpleSymbol("request-remote-socket-address").readResolve();
    Lit11 = (SimpleSymbol)new SimpleSymbol("request-local-host").readResolve();
    Lit10 = (SimpleSymbol)new SimpleSymbol("request-local-port").readResolve();
    Lit9 = (SimpleSymbol)new SimpleSymbol("request-local-IP-address").readResolve();
    Lit8 = (SimpleSymbol)new SimpleSymbol("request-local-socket-address").readResolve();
    Lit7 = (SimpleSymbol)new SimpleSymbol("request-scheme").readResolve();
    Lit6 = (SimpleSymbol)new SimpleSymbol("request-method").readResolve();
    Lit5 = (SimpleSymbol)new SimpleSymbol("error-response").readResolve();
    Lit4 = (SimpleSymbol)new SimpleSymbol("response-status").readResolve();
    Lit3 = (SimpleSymbol)new SimpleSymbol("response-content-type").readResolve();
    Lit2 = (SimpleSymbol)new SimpleSymbol("response-header").readResolve();
    Lit1 = (SimpleSymbol)new SimpleSymbol("Status").readResolve();
    Lit0 = (SimpleSymbol)new SimpleSymbol("Content-Type").readResolve();
    $instance = new HTTP();
    HTTP localHTTP = $instance;
    response$Mnheader = new ModuleMethod(localHTTP, 1, Lit2, 8194);
    response$Mncontent$Mntype = new ModuleMethod(localHTTP, 2, Lit3, 4097);
    response$Mnstatus = new ModuleMethod(localHTTP, 3, Lit4, 8193);
    error$Mnresponse = new ModuleMethod(localHTTP, 5, Lit5, 8193);
    request$Mnmethod = new ModuleMethod(localHTTP, 7, Lit6, 0);
    request$Mnscheme = new ModuleMethod(localHTTP, 8, Lit7, 0);
    request$Mnlocal$Mnsocket$Mnaddress = new ModuleMethod(localHTTP, 9, Lit8, 0);
    request$Mnlocal$MnIP$Mnaddress = new ModuleMethod(localHTTP, 10, Lit9, 0);
    request$Mnlocal$Mnport = new ModuleMethod(localHTTP, 11, Lit10, 0);
    request$Mnlocal$Mnhost = new ModuleMethod(localHTTP, 12, Lit11, 0);
    request$Mnremote$Mnsocket$Mnaddress = new ModuleMethod(localHTTP, 13, Lit12, 0);
    request$Mnremote$MnIP$Mnaddress = new ModuleMethod(localHTTP, 14, Lit13, 0);
    request$Mnremote$Mnport = new ModuleMethod(localHTTP, 15, Lit14, 0);
    request$Mnremote$Mnhost = new ModuleMethod(localHTTP, 16, Lit15, 0);
    request$Mnheader = new ModuleMethod(localHTTP, 17, Lit16, 4097);
    request$Mnheader$Mnmap = new ModuleMethod(localHTTP, 18, Lit17, 0);
    request$MnURI = new ModuleMethod(localHTTP, 19, Lit18, 0);
    request$Mncontext$Mnpath = new ModuleMethod(localHTTP, 20, Lit19, 0);
    request$Mnscript$Mnpath = new ModuleMethod(localHTTP, 21, Lit20, 0);
    request$Mnlocal$Mnpath = new ModuleMethod(localHTTP, 22, Lit21, 0);
    request$Mnpath = new ModuleMethod(localHTTP, 23, Lit22, 0);
    request$Mnuri = new ModuleMethod(localHTTP, 24, Lit23, 0);
    request$Mnurl = new ModuleMethod(localHTTP, 25, Lit24, 0);
    request$Mnpath$Mntranslated = new ModuleMethod(localHTTP, 26, Lit25, 0);
    request$Mnquery$Mnstring = new ModuleMethod(localHTTP, 27, Lit26, 0);
    request$Mnparameter = new ModuleMethod(localHTTP, 28, Lit27, 8193);
    request$Mnparameters = new ModuleMethod(localHTTP, 30, Lit28, 4097);
    request$Mnparameter$Mnmap = new ModuleMethod(localHTTP, 31, Lit29, 0);
    request$Mnbody$Mnstring = new ModuleMethod(localHTTP, 32, Lit30, 0);
    request$Mninput$Mnstream = new ModuleMethod(localHTTP, 33, Lit31, 0);
    request$Mninput$Mnport = new ModuleMethod(localHTTP, 34, Lit32, 0);
    $instance.run();
  }
  
  public HTTP()
  {
    ModuleInfo.register(this);
  }
  
  public static Object errorResponse(int paramInt)
  {
    return errorResponse(paramInt, "Error");
  }
  
  public static Object errorResponse(int paramInt, String paramString)
  {
    return responseHeader(Lit1, Format.formatToString(0, new Object[] { "~d ~a", Integer.valueOf(paramInt), paramString }));
  }
  
  public static URIPath request$MnURI()
  {
    return URIPath.makeURI(HttpRequestContext.getInstance("request-URI").getRequestURI());
  }
  
  public static CharSequence requestBodyString()
  {
    return HttpRequestContext.getInstance("request-body-string").getRequestBodyChars();
  }
  
  public static URIPath requestContextPath()
  {
    return URIPath.makeURI(HttpRequestContext.getInstance("request-context-path").getContextPath());
  }
  
  public static String requestHeader(Object paramObject)
  {
    HttpRequestContext localHttpRequestContext = HttpRequestContext.getInstance("request-header");
    if (paramObject == null) {}
    for (paramObject = null;; paramObject = paramObject.toString()) {
      return localHttpRequestContext.getRequestHeader((String)paramObject);
    }
  }
  
  public static Map requestHeaderMap()
  {
    return HttpRequestContext.getInstance("request-header-map").getRequestHeaders();
  }
  
  public static InPort requestInputPort()
  {
    return HttpRequestContext.getInstance("request-input-port").getRequestPort();
  }
  
  public static InputStream requestInputStream()
  {
    return HttpRequestContext.getInstance("request-input-stream").getRequestStream();
  }
  
  public static String requestLocal$MnIPAddress()
  {
    return HttpRequestContext.getInstance("request-local-IP-address").getLocalIPAddress();
  }
  
  public static InetAddress requestLocalHost()
  {
    return HttpRequestContext.getInstance("request-local-host").getLocalHost();
  }
  
  public static URIPath requestLocalPath()
  {
    return URIPath.makeURI(HttpRequestContext.getInstance("request-local-path").getLocalPath());
  }
  
  public static int requestLocalPort()
  {
    return HttpRequestContext.getInstance("request-local-port").getLocalPort();
  }
  
  public static InetSocketAddress requestLocalSocketAddress()
  {
    return HttpRequestContext.getInstance("request-local-socket-address").getLocalSocketAddress();
  }
  
  public static String requestMethod()
  {
    return HttpRequestContext.getInstance("request-method").getRequestMethod();
  }
  
  public static String requestParameter(String paramString)
  {
    return requestParameter(paramString, null);
  }
  
  public static String requestParameter(String paramString, Object paramObject)
  {
    paramString = HttpRequestContext.getInstance("request-parameter").getRequestParameter(paramString);
    if (paramString == null)
    {
      if (paramObject == null) {
        return null;
      }
      return paramObject.toString();
    }
    return paramString;
  }
  
  public static Map requestParameterMap()
  {
    return HttpRequestContext.getInstance("request-parameter-map").getRequestParameters();
  }
  
  public static Object requestParameters(String paramString)
  {
    paramString = HttpRequestContext.getInstance("request-parameters").getRequestParameters().get(paramString);
    try
    {
      List localList = (List)paramString;
      return Values.make(localList);
    }
    catch (ClassCastException localClassCastException)
    {
      throw new WrongType(localClassCastException, "plist", -2, paramString);
    }
  }
  
  public static String requestPath()
  {
    URIPath localURIPath = URIPath.makeURI(HttpRequestContext.getInstance("request-path").getRequestPath());
    if (localURIPath == null) {
      return null;
    }
    return localURIPath.toString();
  }
  
  public static String requestPathTranslated()
  {
    return HttpRequestContext.getInstance("request-path-translated").getPathTranslated();
  }
  
  public static Object requestQueryString()
  {
    String str = HttpRequestContext.getInstance("request-query-string").getQueryString();
    Object localObject = str;
    if (str == null) {
      localObject = Boolean.FALSE;
    }
    return localObject;
  }
  
  public static String requestRemote$MnIPAddress()
  {
    return HttpRequestContext.getInstance("request-remote-IP-address").getRemoteIPAddress();
  }
  
  public static InetAddress requestRemoteHost()
  {
    return HttpRequestContext.getInstance("request-remote-host").getRemoteHost();
  }
  
  public static int requestRemotePort()
  {
    return HttpRequestContext.getInstance("request-remote-port").getRemotePort();
  }
  
  public static InetSocketAddress requestRemoteSocketAddress()
  {
    return HttpRequestContext.getInstance("request-remote-socket-address").getRemoteSocketAddress();
  }
  
  public static String requestScheme()
  {
    return HttpRequestContext.getInstance("request-scheme").getRequestScheme();
  }
  
  public static URIPath requestScriptPath()
  {
    return URIPath.makeURI(HttpRequestContext.getInstance("request-script-path").getScriptPath());
  }
  
  public static String requestUri()
  {
    return requestPath();
  }
  
  public static StringBuffer requestUrl()
  {
    return HttpRequestContext.getInstance("request-path").getRequestURLBuffer();
  }
  
  public static Object responseContentType(Object paramObject)
  {
    return responseHeader(Lit0, paramObject);
  }
  
  public static Object responseHeader(Object paramObject1, Object paramObject2)
  {
    return MakeResponseHeader.makeResponseHeader.apply2(paramObject1, paramObject2);
  }
  
  public static Object responseStatus(int paramInt)
  {
    return responseStatus(paramInt, null);
  }
  
  public static Object responseStatus(int paramInt, String paramString)
  {
    SimpleSymbol localSimpleSymbol = Lit1;
    if (paramString == null) {}
    for (String str = "~d ";; str = "~d ~a") {
      return responseHeader(localSimpleSymbol, Format.formatToString(0, new Object[] { str, Integer.valueOf(paramInt), paramString }));
    }
  }
  
  public Object apply0(ModuleMethod paramModuleMethod)
  {
    switch (paramModuleMethod.selector)
    {
    case 17: 
    case 28: 
    case 29: 
    case 30: 
    default: 
      return super.apply0(paramModuleMethod);
    case 7: 
      return requestMethod();
    case 8: 
      return requestScheme();
    case 9: 
      return requestLocalSocketAddress();
    case 10: 
      return requestLocal$MnIPAddress();
    case 11: 
      return Integer.valueOf(requestLocalPort());
    case 12: 
      return requestLocalHost();
    case 13: 
      return requestRemoteSocketAddress();
    case 14: 
      return requestRemote$MnIPAddress();
    case 15: 
      return Integer.valueOf(requestRemotePort());
    case 16: 
      return requestRemoteHost();
    case 18: 
      return requestHeaderMap();
    case 19: 
      return request$MnURI();
    case 20: 
      return requestContextPath();
    case 21: 
      return requestScriptPath();
    case 22: 
      return requestLocalPath();
    case 23: 
      return requestPath();
    case 24: 
      return requestUri();
    case 25: 
      return requestUrl();
    case 26: 
      return requestPathTranslated();
    case 27: 
      return requestQueryString();
    case 31: 
      return requestParameterMap();
    case 32: 
      return requestBodyString();
    case 33: 
      return requestInputStream();
    }
    return requestInputPort();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    switch (paramModuleMethod.selector)
    {
    default: 
      return super.apply1(paramModuleMethod, paramObject);
    case 2: 
      return responseContentType(paramObject);
    }
    try
    {
      i = ((Number)paramObject).intValue();
      return responseStatus(i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      int i;
      throw new WrongType(paramModuleMethod, "response-status", 1, paramObject);
    }
    try
    {
      i = ((Number)paramObject).intValue();
      return errorResponse(i);
    }
    catch (ClassCastException paramModuleMethod)
    {
      throw new WrongType(paramModuleMethod, "error-response", 1, paramObject);
    }
    return requestHeader(paramObject);
    if (paramObject == null) {}
    for (paramModuleMethod = (ModuleMethod)localObject1;; paramModuleMethod = paramObject.toString()) {
      return requestParameter(paramModuleMethod);
    }
    if (paramObject == null) {}
    for (paramModuleMethod = (ModuleMethod)localObject2;; paramModuleMethod = paramObject.toString()) {
      return requestParameters(paramModuleMethod);
    }
  }
  
  /* Error */
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 4
    //   6: aload_1
    //   7: getfield 512	gnu/expr/ModuleMethod:selector	I
    //   10: lookupswitch	default:+42->52, 1:+50->60, 3:+56->66, 5:+86->96, 28:+117->127
    //   52: aload_0
    //   53: aload_1
    //   54: aload_2
    //   55: aload_3
    //   56: invokespecial 584	gnu/expr/ModuleBody:apply2	(Lgnu/expr/ModuleMethod;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   59: areturn
    //   60: aload_2
    //   61: aload_3
    //   62: invokestatic 325	gnu/kawa/servlet/HTTP:responseHeader	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   65: areturn
    //   66: aload_2
    //   67: checkcast 568	java/lang/Number
    //   70: invokevirtual 571	java/lang/Number:intValue	()I
    //   73: istore 6
    //   75: aload_3
    //   76: ifnonnull +12 -> 88
    //   79: aconst_null
    //   80: astore_1
    //   81: iload 6
    //   83: aload_1
    //   84: invokestatic 504	gnu/kawa/servlet/HTTP:responseStatus	(ILjava/lang/String;)Ljava/lang/Object;
    //   87: areturn
    //   88: aload_3
    //   89: invokevirtual 361	java/lang/Object:toString	()Ljava/lang/String;
    //   92: astore_1
    //   93: goto -12 -> 81
    //   96: aload_2
    //   97: checkcast 568	java/lang/Number
    //   100: invokevirtual 571	java/lang/Number:intValue	()I
    //   103: istore 6
    //   105: aload_3
    //   106: ifnonnull +13 -> 119
    //   109: aload 4
    //   111: astore_1
    //   112: iload 6
    //   114: aload_1
    //   115: invokestatic 305	gnu/kawa/servlet/HTTP:errorResponse	(ILjava/lang/String;)Ljava/lang/Object;
    //   118: areturn
    //   119: aload_3
    //   120: invokevirtual 361	java/lang/Object:toString	()Ljava/lang/String;
    //   123: astore_1
    //   124: goto -12 -> 112
    //   127: aload_2
    //   128: ifnonnull +12 -> 140
    //   131: aload 5
    //   133: astore_1
    //   134: aload_1
    //   135: aload_3
    //   136: invokestatic 407	gnu/kawa/servlet/HTTP:requestParameter	(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;
    //   139: areturn
    //   140: aload_2
    //   141: invokevirtual 361	java/lang/Object:toString	()Ljava/lang/String;
    //   144: astore_1
    //   145: goto -11 -> 134
    //   148: astore_1
    //   149: new 434	gnu/mapping/WrongType
    //   152: dup
    //   153: aload_1
    //   154: ldc -58
    //   156: iconst_1
    //   157: aload_2
    //   158: invokespecial 439	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   161: athrow
    //   162: astore_1
    //   163: new 434	gnu/mapping/WrongType
    //   166: dup
    //   167: aload_1
    //   168: ldc -62
    //   170: iconst_1
    //   171: aload_2
    //   172: invokespecial 439	gnu/mapping/WrongType:<init>	(Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   175: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	176	0	this	HTTP
    //   0	176	1	paramModuleMethod	ModuleMethod
    //   0	176	2	paramObject1	Object
    //   0	176	3	paramObject2	Object
    //   4	106	4	localObject1	Object
    //   1	131	5	localObject2	Object
    //   73	40	6	i	int
    // Exception table:
    //   from	to	target	type
    //   66	75	148	java/lang/ClassCastException
    //   96	105	162	java/lang/ClassCastException
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext)
  {
    switch (paramModuleMethod.selector)
    {
    case 17: 
    case 28: 
    case 29: 
    case 30: 
    default: 
      return super.match0(paramModuleMethod, paramCallContext);
    case 34: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 33: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 32: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 31: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 27: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 26: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 25: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 24: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 23: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 22: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 21: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 20: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 19: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 18: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 16: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 15: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 14: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 13: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 12: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 11: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 10: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 9: 
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    case 8: 
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
    default: 
      return super.match1(paramModuleMethod, paramObject, paramCallContext);
    case 30: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 28: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 17: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 5: 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    case 3: 
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
    case 28: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 5: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    case 3: 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    }
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public final void run(CallContext paramCallContext)
  {
    paramCallContext = paramCallContext.consumer;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\servlet\HTTP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */