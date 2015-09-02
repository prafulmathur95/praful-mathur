package gnu.kawa.servlet;

import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleContext;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleManager;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;

public class KawaAutoHandler
{
  static final String MODULE_MAP_ATTRIBUTE = "gnu.kawa.module-map";
  
  public static Object getModule(HttpRequestContext paramHttpRequestContext, CallContext paramCallContext, boolean paramBoolean)
    throws Exception
  {
    Object localObject5 = paramHttpRequestContext.getRequestPath().substring(paramHttpRequestContext.getContextPath().length() - 1);
    Object localObject1 = (Hashtable)paramHttpRequestContext.getAttribute("gnu.kawa.module-map");
    Object localObject7 = localObject1;
    if (localObject1 == null)
    {
      localObject7 = new Hashtable();
      paramHttpRequestContext.setAttribute("gnu.kawa.module-map", localObject7);
    }
    localObject1 = (ModuleContext)paramHttpRequestContext.getAttribute("gnu.kawa.module-context");
    Object localObject4 = localObject1;
    if (localObject1 == null) {
      localObject4 = ModuleContext.getContext();
    }
    ((ModuleContext)localObject4).addFlags(ModuleContext.IN_HTTP_SERVER);
    if (paramHttpRequestContext.getClass().getName().endsWith("KawaServlet$Context")) {
      ((ModuleContext)localObject4).addFlags(ModuleContext.IN_SERVLET);
    }
    ModuleInfo localModuleInfo = (ModuleInfo)((Hashtable)localObject7).get(localObject5);
    long l = System.currentTimeMillis();
    ModuleManager localModuleManager = ((ModuleContext)localObject4).getManager();
    if ((localModuleInfo != null) && (l - localModuleInfo.lastCheckedTime < localModuleManager.lastModifiedCacheTime)) {
      return ((ModuleContext)localObject4).findInstance(localModuleInfo);
    }
    int i = ((String)localObject5).length();
    Object localObject2;
    Object localObject6;
    if ((i == 0) || (((String)localObject5).charAt(i - 1) == '/'))
    {
      localObject1 = null;
      localObject2 = localObject5;
      if (localObject1 != null) {
        break label383;
      }
      localObject6 = localObject5;
      label199:
      i = ((String)localObject6).lastIndexOf('/');
      if (i >= 0) {
        break label298;
      }
    }
    for (;;)
    {
      if (localObject1 != null) {
        break label404;
      }
      paramCallContext = ("The requested URL " + (String)localObject5 + " was not found on this server." + " res/:" + paramHttpRequestContext.getResourceURL("/") + "\r\n").getBytes();
      paramHttpRequestContext.sendResponseHeaders(404, null, paramCallContext.length);
      paramHttpRequestContext = paramHttpRequestContext.getResponseStream();
      try
      {
        paramHttpRequestContext.write(paramCallContext);
        return null;
      }
      catch (IOException paramHttpRequestContext)
      {
        throw new RuntimeException(paramHttpRequestContext);
      }
      localObject1 = paramHttpRequestContext.getResourceURL((String)localObject5);
      break;
      label298:
      localObject6 = ((String)localObject6).substring(0, i);
      localObject9 = (String)localObject6 + "/+default+";
      localObject8 = paramHttpRequestContext.getResourceURL((String)localObject9);
      localObject2 = localObject9;
      localObject1 = localObject8;
      if (localObject8 == null) {
        break label199;
      }
      paramHttpRequestContext.setScriptAndLocalPath(((String)localObject5).substring(1, i + 1), ((String)localObject5).substring(i + 1));
      localObject2 = localObject9;
      localObject1 = localObject8;
      continue;
      label383:
      paramHttpRequestContext.setScriptAndLocalPath((String)localObject5, "");
    }
    label404:
    Object localObject8 = ((URL)localObject1).toExternalForm();
    if (localModuleInfo != null)
    {
      localObject6 = localModuleInfo;
      if (((String)localObject8).equals(localModuleInfo.getSourceAbsPathname())) {}
    }
    else
    {
      localObject6 = localModuleManager.findWithURL((URL)localObject1);
    }
    if (((ModuleInfo)localObject6).checkCurrent(localModuleManager, l)) {
      return ((ModuleContext)localObject4).findInstance((ModuleInfo)localObject6);
    }
    ((Hashtable)localObject7).put(localObject5, localObject6);
    Object localObject9 = ((ModuleInfo)localObject6).getSourceAbsPath();
    localObject8 = ((Path)localObject9).openInputStream();
    localObject7 = localObject8;
    if (!(localObject8 instanceof BufferedInputStream)) {
      localObject7 = new BufferedInputStream((InputStream)localObject8);
    }
    localObject8 = Language.getInstanceFromFilenameExtension((String)localObject5);
    if (localObject8 != null)
    {
      paramHttpRequestContext.log("Compile " + (String)localObject5 + " - a " + ((Language)localObject8).getName() + " source file (based on extension)");
      localObject2 = localObject8;
    }
    for (;;)
    {
      localObject5 = new InPort((InputStream)localObject7, (Path)localObject9);
      Language.setCurrentLanguage((Language)localObject2);
      localObject7 = new SourceMessages();
      try
      {
        localObject2 = ((Language)localObject2).parse((InPort)localObject5, (SourceMessages)localObject7, 9, (ModuleInfo)localObject6);
        localObject5 = null;
        if (!((SourceMessages)localObject7).seenErrors())
        {
          ((Compilation)localObject2).getModule();
          localObject5 = (Class)ModuleExp.evalModule1(Environment.getCurrent(), (Compilation)localObject2, (URL)localObject1, null);
        }
        if (((SourceMessages)localObject7).seenErrors())
        {
          localObject1 = "script syntax error:\n" + ((SourceMessages)localObject7).toString(20);
          ((ServletPrinter)paramCallContext.consumer).addHeader("Content-type", "text/plain");
          paramHttpRequestContext.sendResponseHeaders(500, "Syntax errors", -1L);
          paramCallContext.consumer.write((String)localObject1);
          ((ModuleInfo)localObject6).cleanupAfterCompilation();
          return null;
          localObject8 = Language.detect((InputStream)localObject7);
          if (localObject8 != null)
          {
            paramHttpRequestContext.log("Compile " + (String)localObject5 + " - a " + ((Language)localObject8).getName() + " source file (detected from content)");
            localObject2 = localObject8;
            continue;
          }
          if (localObject5 != localObject2)
          {
            paramCallContext = ("The requested URL " + (String)localObject5 + " was not found on this server." + " upath=" + (String)localObject2 + ".\r\n").getBytes();
            paramHttpRequestContext.sendResponseHeaders(404, null, paramCallContext.length);
            paramHttpRequestContext = paramHttpRequestContext.getResponseStream();
            try
            {
              paramHttpRequestContext.write(paramCallContext);
              return null;
            }
            catch (IOException paramHttpRequestContext)
            {
              throw new RuntimeException(paramHttpRequestContext);
            }
          }
          paramHttpRequestContext.sendResponseHeaders(200, null, ((Path)localObject9).getContentLength());
          paramHttpRequestContext = paramHttpRequestContext.getResponseStream();
          paramCallContext = new byte['က'];
          for (;;)
          {
            i = ((InputStream)localObject7).read(paramCallContext);
            if (i < 0)
            {
              ((InputStream)localObject7).close();
              paramHttpRequestContext.close();
              return null;
            }
            paramHttpRequestContext.write(paramCallContext, 0, i);
          }
        }
      }
      catch (SyntaxException localSyntaxException)
      {
        for (;;)
        {
          if (localSyntaxException.getMessages() != localObject7) {
            throw localSyntaxException;
          }
          Object localObject3 = null;
        }
        ((ModuleInfo)localObject6).setModuleClass((Class)localObject5);
      }
    }
    return ((ModuleContext)localObject4).findInstance((ModuleInfo)localObject6);
  }
  
  public static void run(HttpRequestContext paramHttpRequestContext, CallContext paramCallContext)
    throws Throwable
  {
    if (paramHttpRequestContext.getRequestParameter("qexo-save-class") != null) {}
    for (boolean bool = true;; bool = false)
    {
      paramHttpRequestContext = getModule(paramHttpRequestContext, paramCallContext, bool);
      if ((paramHttpRequestContext instanceof ModuleBody)) {
        ((ModuleBody)paramHttpRequestContext).run(paramCallContext);
      }
      return;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\servlet\KawaAutoHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */