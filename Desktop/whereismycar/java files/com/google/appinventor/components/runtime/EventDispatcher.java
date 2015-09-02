package com.google.appinventor.components.runtime;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class EventDispatcher
{
  private static final boolean DEBUG = false;
  private static final Map<HandlesEventDispatching, EventRegistry> mapDispatchDelegateToEventRegistry = new HashMap();
  
  private static boolean delegateDispatchEvent(HandlesEventDispatching paramHandlesEventDispatching, Set<EventClosure> paramSet, Component paramComponent, Object... paramVarArgs)
  {
    boolean bool = false;
    paramSet = paramSet.iterator();
    while (paramSet.hasNext())
    {
      EventClosure localEventClosure = (EventClosure)paramSet.next();
      if (paramHandlesEventDispatching.dispatchEvent(paramComponent, localEventClosure.componentId, localEventClosure.eventName, paramVarArgs)) {
        bool = true;
      }
    }
    return bool;
  }
  
  public static boolean dispatchEvent(Component paramComponent, String paramString, Object... paramVarArgs)
  {
    boolean bool2 = false;
    HandlesEventDispatching localHandlesEventDispatching = paramComponent.getDispatchDelegate();
    boolean bool1 = bool2;
    if (localHandlesEventDispatching.canDispatchEvent(paramComponent, paramString))
    {
      paramString = (Set)getEventRegistry(localHandlesEventDispatching).eventClosuresMap.get(paramString);
      bool1 = bool2;
      if (paramString != null)
      {
        bool1 = bool2;
        if (paramString.size() > 0) {
          bool1 = delegateDispatchEvent(localHandlesEventDispatching, paramString, paramComponent, paramVarArgs);
        }
      }
    }
    return bool1;
  }
  
  private static EventRegistry getEventRegistry(HandlesEventDispatching paramHandlesEventDispatching)
  {
    EventRegistry localEventRegistry2 = (EventRegistry)mapDispatchDelegateToEventRegistry.get(paramHandlesEventDispatching);
    EventRegistry localEventRegistry1 = localEventRegistry2;
    if (localEventRegistry2 == null)
    {
      localEventRegistry1 = new EventRegistry(paramHandlesEventDispatching);
      mapDispatchDelegateToEventRegistry.put(paramHandlesEventDispatching, localEventRegistry1);
    }
    return localEventRegistry1;
  }
  
  public static String makeFullEventName(String paramString1, String paramString2)
  {
    return paramString1 + '$' + paramString2;
  }
  
  public static void registerEventForDelegation(HandlesEventDispatching paramHandlesEventDispatching, String paramString1, String paramString2)
  {
    EventRegistry localEventRegistry = getEventRegistry(paramHandlesEventDispatching);
    Set localSet = (Set)localEventRegistry.eventClosuresMap.get(paramString2);
    paramHandlesEventDispatching = localSet;
    if (localSet == null)
    {
      paramHandlesEventDispatching = new HashSet();
      localEventRegistry.eventClosuresMap.put(paramString2, paramHandlesEventDispatching);
    }
    paramHandlesEventDispatching.add(new EventClosure(paramString1, paramString2, null));
  }
  
  public static void removeDispatchDelegate(HandlesEventDispatching paramHandlesEventDispatching)
  {
    paramHandlesEventDispatching = removeEventRegistry(paramHandlesEventDispatching);
    if (paramHandlesEventDispatching != null) {
      paramHandlesEventDispatching.eventClosuresMap.clear();
    }
  }
  
  private static EventRegistry removeEventRegistry(HandlesEventDispatching paramHandlesEventDispatching)
  {
    return (EventRegistry)mapDispatchDelegateToEventRegistry.remove(paramHandlesEventDispatching);
  }
  
  public static void unregisterAllEventsForDelegation()
  {
    Iterator localIterator = mapDispatchDelegateToEventRegistry.values().iterator();
    while (localIterator.hasNext()) {
      ((EventRegistry)localIterator.next()).eventClosuresMap.clear();
    }
  }
  
  public static void unregisterEventForDelegation(HandlesEventDispatching paramHandlesEventDispatching, String paramString1, String paramString2)
  {
    paramHandlesEventDispatching = (Set)getEventRegistry(paramHandlesEventDispatching).eventClosuresMap.get(paramString2);
    if ((paramHandlesEventDispatching == null) || (paramHandlesEventDispatching.isEmpty())) {}
    for (;;)
    {
      return;
      paramString2 = new HashSet();
      Iterator localIterator = paramHandlesEventDispatching.iterator();
      while (localIterator.hasNext())
      {
        EventClosure localEventClosure = (EventClosure)localIterator.next();
        if (localEventClosure.componentId.equals(paramString1)) {
          paramString2.add(localEventClosure);
        }
      }
      paramString1 = paramString2.iterator();
      while (paramString1.hasNext()) {
        paramHandlesEventDispatching.remove((EventClosure)paramString1.next());
      }
    }
  }
  
  private static final class EventClosure
  {
    private final String componentId;
    private final String eventName;
    
    private EventClosure(String paramString1, String paramString2)
    {
      this.componentId = paramString1;
      this.eventName = paramString2;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if ((paramObject == null) || (getClass() != paramObject.getClass())) {
          return false;
        }
        paramObject = (EventClosure)paramObject;
        if (!this.componentId.equals(((EventClosure)paramObject).componentId)) {
          return false;
        }
      } while (this.eventName.equals(((EventClosure)paramObject).eventName));
      return false;
    }
    
    public int hashCode()
    {
      return this.eventName.hashCode() * 31 + this.componentId.hashCode();
    }
  }
  
  private static final class EventRegistry
  {
    private final HandlesEventDispatching dispatchDelegate;
    private final HashMap<String, Set<EventDispatcher.EventClosure>> eventClosuresMap = new HashMap();
    
    EventRegistry(HandlesEventDispatching paramHandlesEventDispatching)
    {
      this.dispatchDelegate = paramHandlesEventDispatching;
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\EventDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */