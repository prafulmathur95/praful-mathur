package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.lists.XConsumer;
import gnu.mapping.Location;
import gnu.mapping.NamedLocation;
import gnu.mapping.ThreadLocation;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.xml.NodeTree;
import gnu.xml.XMLParser;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Hashtable;

public class Document
{
  private static HashMap cache = new HashMap();
  private static ThreadLocation docMapLocation;
  public static final Document document = new Document();
  
  static
  {
    docMapLocation = new ThreadLocation("document-map");
  }
  
  public static void clearLocalCache()
  {
    docMapLocation.getLocation().set(null);
  }
  
  public static void clearSoftCache()
  {
    cache = new HashMap();
  }
  
  public static KDocument parse(Object paramObject)
    throws Throwable
  {
    NodeTree localNodeTree = new NodeTree();
    parse(paramObject, localNodeTree);
    return new KDocument(localNodeTree, 10);
  }
  
  public static void parse(Object paramObject, Consumer paramConsumer)
    throws Throwable
  {
    SourceMessages localSourceMessages = new SourceMessages();
    if ((paramConsumer instanceof XConsumer)) {
      ((XConsumer)paramConsumer).beginEntity(paramObject);
    }
    XMLParser.parse(paramObject, localSourceMessages, paramConsumer);
    if (localSourceMessages.seenErrors()) {
      throw new SyntaxException("document function read invalid XML", localSourceMessages);
    }
    if ((paramConsumer instanceof XConsumer)) {
      ((XConsumer)paramConsumer).endEntity();
    }
  }
  
  public static KDocument parseCached(Path paramPath)
    throws Throwable
  {
    for (;;)
    {
      Object localObject1;
      Object localObject2;
      try
      {
        localObject1 = (DocReference)DocReference.queue.poll();
        if (localObject1 == null)
        {
          NamedLocation localNamedLocation = docMapLocation.getLocation();
          localObject2 = (Hashtable)localNamedLocation.get(null);
          localObject1 = localObject2;
          if (localObject2 == null)
          {
            localObject1 = new Hashtable();
            localNamedLocation.set(localObject1);
          }
          localObject2 = (KDocument)((Hashtable)localObject1).get(paramPath);
          if (localObject2 != null)
          {
            paramPath = (Path)localObject2;
            return paramPath;
          }
        }
        else
        {
          cache.remove(((DocReference)localObject1).key);
          continue;
        }
        localObject2 = (DocReference)cache.get(paramPath);
      }
      finally {}
      if (localObject2 != null)
      {
        localObject2 = (KDocument)((DocReference)localObject2).get();
        if (localObject2 == null) {
          cache.remove(paramPath);
        }
      }
      else
      {
        localObject2 = parse(paramPath);
        ((Hashtable)localObject1).put(paramPath, localObject2);
        cache.put(paramPath, new DocReference(paramPath, (KDocument)localObject2));
        paramPath = (Path)localObject2;
        continue;
      }
      ((Hashtable)localObject1).put(paramPath, localObject2);
      paramPath = (Path)localObject2;
    }
  }
  
  public static KDocument parseCached(Object paramObject)
    throws Throwable
  {
    return parseCached(Path.valueOf(paramObject));
  }
  
  private static class DocReference
    extends SoftReference
  {
    static ReferenceQueue queue = new ReferenceQueue();
    Path key;
    
    public DocReference(Path paramPath, KDocument paramKDocument)
    {
      super(queue);
      this.key = paramPath;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\xml\Document.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */