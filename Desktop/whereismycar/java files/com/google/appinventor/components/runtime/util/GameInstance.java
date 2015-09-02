package com.google.appinventor.components.runtime.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameInstance
{
  private String instanceId;
  private String leader;
  private Map<String, String> messageTimes = new HashMap();
  private List<String> players = new ArrayList(0);
  
  public GameInstance(String paramString)
  {
    this.instanceId = paramString;
    this.leader = "";
  }
  
  public String getInstanceId()
  {
    return this.instanceId;
  }
  
  public String getLeader()
  {
    return this.leader;
  }
  
  public String getMessageTime(String paramString)
  {
    if (this.messageTimes.containsKey(paramString)) {
      return (String)this.messageTimes.get(paramString);
    }
    return "";
  }
  
  public List<String> getPlayers()
  {
    return this.players;
  }
  
  public void putMessageTime(String paramString1, String paramString2)
  {
    this.messageTimes.put(paramString1, paramString2);
  }
  
  public void setLeader(String paramString)
  {
    this.leader = paramString;
  }
  
  public PlayerListDelta setPlayers(List<String> paramList)
  {
    if (paramList.equals(this.players)) {
      return PlayerListDelta.NO_CHANGE;
    }
    List localList = this.players;
    ArrayList localArrayList = new ArrayList(paramList);
    this.players = new ArrayList(paramList);
    localArrayList.removeAll(localList);
    localList.removeAll(paramList);
    if ((localArrayList.size() == 0) && (localList.size() == 0)) {
      return PlayerListDelta.NO_CHANGE;
    }
    return new PlayerListDelta(localList, localArrayList);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\GameInstance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */