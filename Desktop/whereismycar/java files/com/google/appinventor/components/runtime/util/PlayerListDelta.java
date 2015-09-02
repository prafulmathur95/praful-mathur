package com.google.appinventor.components.runtime.util;

import java.util.ArrayList;
import java.util.List;

public class PlayerListDelta
{
  public static PlayerListDelta NO_CHANGE = new PlayerListDelta(new ArrayList(), new ArrayList());
  private List<String> playersAdded;
  private List<String> playersRemoved;
  
  public PlayerListDelta(List<String> paramList1, List<String> paramList2)
  {
    this.playersRemoved = paramList1;
    this.playersAdded = paramList2;
  }
  
  public List<String> getPlayersAdded()
  {
    return this.playersAdded;
  }
  
  public List<String> getPlayersRemoved()
  {
    return this.playersRemoved;
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\util\PlayerListDelta.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */