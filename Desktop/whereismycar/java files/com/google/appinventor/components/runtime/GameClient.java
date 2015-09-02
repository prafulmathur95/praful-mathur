package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.AsyncCallbackPair;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.GameInstance;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.PlayerListDelta;
import com.google.appinventor.components.runtime.util.WebServiceUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@DesignerComponent(category=ComponentCategory.INTERNAL, description="Provides a way for applications to communicate with online game servers", iconName="images/gameClient.png", nonVisible=true, version=1)
@SimpleObject
@UsesPermissions(permissionNames="android.permission.INTERNET, com.google.android.googleapps.permission.GOOGLE_AUTH")
public class GameClient
  extends AndroidNonvisibleComponent
  implements Component, OnResumeListener, OnStopListener
{
  private static final String COMMAND_ARGUMENTS_KEY = "args";
  private static final String COMMAND_TYPE_KEY = "command";
  private static final String COUNT_KEY = "count";
  private static final String ERROR_RESPONSE_KEY = "e";
  private static final String GAME_ID_KEY = "gid";
  private static final String GET_INSTANCE_LISTS_COMMAND = "getinstancelists";
  private static final String GET_MESSAGES_COMMAND = "messages";
  private static final String INSTANCE_ID_KEY = "iid";
  private static final String INSTANCE_PUBLIC_KEY = "makepublic";
  private static final String INVITED_LIST_KEY = "invited";
  private static final String INVITEE_KEY = "inv";
  private static final String INVITE_COMMAND = "invite";
  private static final String JOINED_LIST_KEY = "joined";
  private static final String JOIN_INSTANCE_COMMAND = "joininstance";
  private static final String LEADER_KEY = "leader";
  private static final String LEAVE_INSTANCE_COMMAND = "leaveinstance";
  private static final String LOG_TAG = "GameClient";
  private static final String MESSAGES_LIST_KEY = "messages";
  private static final String MESSAGE_CONTENT_KEY = "contents";
  private static final String MESSAGE_RECIPIENTS_KEY = "mrec";
  private static final String MESSAGE_SENDER_KEY = "msender";
  private static final String MESSAGE_TIME_KEY = "mtime";
  private static final String NEW_INSTANCE_COMMAND = "newinstance";
  private static final String NEW_MESSAGE_COMMAND = "newmessage";
  private static final String PLAYERS_LIST_KEY = "players";
  private static final String PLAYER_ID_KEY = "pid";
  private static final String PUBLIC_LIST_KEY = "public";
  private static final String SERVER_COMMAND = "servercommand";
  private static final String SERVER_RETURN_VALUE_KEY = "response";
  private static final String SET_LEADER_COMMAND = "setleader";
  private static final String TYPE_KEY = "type";
  private Activity activityContext;
  private Handler androidUIHandler = new Handler();
  private String gameId;
  private GameInstance instance;
  private List<String> invitedInstances;
  private List<String> joinedInstances;
  private List<String> publicInstances;
  private String serviceUrl;
  private String userEmailAddress = "";
  
  public GameClient(ComponentContainer paramComponentContainer)
  {
    super(paramComponentContainer.$form());
    this.activityContext = paramComponentContainer.$context();
    this.form.registerForOnResume(this);
    this.form.registerForOnStop(this);
    this.gameId = "";
    this.instance = new GameInstance("");
    this.joinedInstances = Lists.newArrayList();
    this.invitedInstances = Lists.newArrayList();
    this.publicInstances = Lists.newArrayList();
    this.serviceUrl = "http://appinvgameserver.appspot.com";
  }
  
  private void postCommandToGameServer(String paramString, List<NameValuePair> paramList, AsyncCallbackPair<JSONObject> paramAsyncCallbackPair)
  {
    postCommandToGameServer(paramString, paramList, paramAsyncCallbackPair, false);
  }
  
  private void postCommandToGameServer(final String paramString, final List<NameValuePair> paramList, final AsyncCallbackPair<JSONObject> paramAsyncCallbackPair, final boolean paramBoolean)
  {
    paramAsyncCallbackPair = new AsyncCallbackPair()
    {
      public void onFailure(String paramAnonymousString)
      {
        Log.d("GameClient", "Posting to server failed for " + paramString + " with arguments " + paramList + "\n Failure message: " + paramAnonymousString);
        paramAsyncCallbackPair.onFailure(paramAnonymousString);
      }
      
      public void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        Log.d("GameClient", "Received response for " + paramString + ": " + paramAnonymousJSONObject.toString());
        try
        {
          if (paramAnonymousJSONObject.getBoolean("e"))
          {
            paramAsyncCallbackPair.onFailure(paramAnonymousJSONObject.getString("response"));
            return;
          }
          str = paramAnonymousJSONObject.getString("gid");
          if (!str.equals(GameClient.this.GameId()))
          {
            GameClient.this.Info("Incorrect game id in response: + " + str + ".");
            return;
          }
        }
        catch (JSONException paramAnonymousJSONObject)
        {
          Log.w("GameClient", paramAnonymousJSONObject);
          paramAsyncCallbackPair.onFailure("Failed to parse JSON response to command " + paramString);
          return;
        }
        String str = paramAnonymousJSONObject.getString("iid");
        if (str.equals(""))
        {
          paramAsyncCallbackPair.onSuccess(paramAnonymousJSONObject.getJSONObject("response"));
          return;
        }
        if (str.equals(GameClient.this.InstanceId())) {
          GameClient.this.updateInstanceInfo(paramAnonymousJSONObject);
        }
        for (;;)
        {
          paramAsyncCallbackPair.onSuccess(paramAnonymousJSONObject.getJSONObject("response"));
          return;
          if ((!paramBoolean) && (!GameClient.this.InstanceId().equals(""))) {
            break;
          }
          GameClient.access$302(GameClient.this, new GameInstance(str));
          GameClient.this.updateInstanceInfo(paramAnonymousJSONObject);
          GameClient.this.InstanceIdChanged(str);
        }
        GameClient.this.Info("Ignored server response to " + paramString + " for incorrect instance " + str + ".");
      }
    };
    WebServiceUtil.getInstance().postCommandReturningObject(ServiceUrl(), paramString, paramList, paramAsyncCallbackPair);
  }
  
  private void postGetInstanceLists()
  {
    AsyncCallbackPair local15 = new AsyncCallbackPair()
    {
      public void onFailure(String paramAnonymousString)
      {
        GameClient.this.WebServiceError("GetInstanceLists", "Failed to get up to date instance lists.");
      }
      
      public void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        GameClient.this.processInstanceLists(paramAnonymousJSONObject);
        GameClient.this.FunctionCompleted("GetInstanceLists");
      }
    };
    postCommandToGameServer("getinstancelists", Lists.newArrayList(new NameValuePair[] { new BasicNameValuePair("gid", GameId()), new BasicNameValuePair("iid", InstanceId()), new BasicNameValuePair("pid", UserEmailAddress()) }), local15);
  }
  
  private void postGetMessages(final String paramString, int paramInt)
  {
    AsyncCallbackPair local17 = new AsyncCallbackPair()
    {
      public void onFailure(String paramAnonymousString)
      {
        GameClient.this.WebServiceError("GetMessages", paramAnonymousString);
      }
      
      public void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        try
        {
          int j = paramAnonymousJSONObject.getInt("count");
          paramAnonymousJSONObject = paramAnonymousJSONObject.getJSONArray("messages");
          int i = 0;
          while (i < j)
          {
            Object localObject = paramAnonymousJSONObject.getJSONObject(i);
            String str1 = ((JSONObject)localObject).getString("type");
            String str2 = ((JSONObject)localObject).getString("msender");
            String str3 = ((JSONObject)localObject).getString("mtime");
            localObject = JsonUtil.getListFromJsonArray(((JSONObject)localObject).getJSONArray("contents"));
            if (paramString.equals("")) {
              GameClient.this.instance.putMessageTime(paramString, str3);
            }
            GameClient.this.instance.putMessageTime(str1, str3);
            GameClient.this.GotMessage(str1, str2, (List)localObject);
            i += 1;
          }
          return;
        }
        catch (JSONException paramAnonymousJSONObject)
        {
          Log.w("GameClient", paramAnonymousJSONObject);
          GameClient.this.Info("Failed to parse messages response.");
          GameClient.this.FunctionCompleted("GetMessages");
        }
      }
    };
    if (InstanceId().equals(""))
    {
      Info("You must join an instance before attempting to fetch messages.");
      return;
    }
    postCommandToGameServer("messages", Lists.newArrayList(new NameValuePair[] { new BasicNameValuePair("gid", GameId()), new BasicNameValuePair("iid", InstanceId()), new BasicNameValuePair("pid", UserEmailAddress()), new BasicNameValuePair("count", Integer.toString(paramInt)), new BasicNameValuePair("mtime", this.instance.getMessageTime(paramString)), new BasicNameValuePair("type", paramString) }), local17);
  }
  
  private void postInvite(String paramString)
  {
    AsyncCallbackPair local19 = new AsyncCallbackPair()
    {
      public void onFailure(String paramAnonymousString)
      {
        GameClient.this.WebServiceError("Invite", paramAnonymousString);
      }
      
      public void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        try
        {
          paramAnonymousJSONObject = paramAnonymousJSONObject.getString("inv");
          if (paramAnonymousJSONObject.equals("")) {
            GameClient.this.Info(paramAnonymousJSONObject + " was already invited.");
          }
          for (;;)
          {
            GameClient.this.FunctionCompleted("Invite");
            return;
            GameClient.this.Info("Successfully invited " + paramAnonymousJSONObject + ".");
          }
        }
        catch (JSONException paramAnonymousJSONObject)
        {
          for (;;)
          {
            Log.w("GameClient", paramAnonymousJSONObject);
            GameClient.this.Info("Failed to parse invite player response.");
          }
        }
      }
    };
    if (InstanceId().equals(""))
    {
      Info("You must have joined an instance before you can invite new players.");
      return;
    }
    postCommandToGameServer("invite", Lists.newArrayList(new NameValuePair[] { new BasicNameValuePair("gid", GameId()), new BasicNameValuePair("iid", InstanceId()), new BasicNameValuePair("pid", UserEmailAddress()), new BasicNameValuePair("inv", paramString) }), local19);
  }
  
  private void postLeaveInstance()
  {
    AsyncCallbackPair local21 = new AsyncCallbackPair()
    {
      public void onFailure(String paramAnonymousString)
      {
        GameClient.this.WebServiceError("LeaveInstance", paramAnonymousString);
      }
      
      public void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        GameClient.this.SetInstance("");
        GameClient.this.processInstanceLists(paramAnonymousJSONObject);
        GameClient.this.FunctionCompleted("LeaveInstance");
      }
    };
    postCommandToGameServer("leaveinstance", Lists.newArrayList(new NameValuePair[] { new BasicNameValuePair("gid", GameId()), new BasicNameValuePair("iid", InstanceId()), new BasicNameValuePair("pid", UserEmailAddress()) }), local21);
  }
  
  private void postMakeNewInstance(String paramString, Boolean paramBoolean)
  {
    AsyncCallbackPair local23 = new AsyncCallbackPair()
    {
      public void onFailure(String paramAnonymousString)
      {
        GameClient.this.WebServiceError("MakeNewInstance", paramAnonymousString);
      }
      
      public void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        GameClient.this.processInstanceLists(paramAnonymousJSONObject);
        GameClient.this.NewInstanceMade(GameClient.this.InstanceId());
        GameClient.this.FunctionCompleted("MakeNewInstance");
      }
    };
    postCommandToGameServer("newinstance", Lists.newArrayList(new NameValuePair[] { new BasicNameValuePair("pid", UserEmailAddress()), new BasicNameValuePair("gid", GameId()), new BasicNameValuePair("iid", paramString), new BasicNameValuePair("makepublic", paramBoolean.toString()) }), local23, true);
  }
  
  private void postNewMessage(String paramString, YailList paramYailList1, YailList paramYailList2)
  {
    AsyncCallbackPair local25 = new AsyncCallbackPair()
    {
      public void onFailure(String paramAnonymousString)
      {
        GameClient.this.WebServiceError("SendMessage", paramAnonymousString);
      }
      
      public void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        GameClient.this.FunctionCompleted("SendMessage");
      }
    };
    if (InstanceId().equals(""))
    {
      Info("You must have joined an instance before you can send messages.");
      return;
    }
    postCommandToGameServer("newmessage", Lists.newArrayList(new NameValuePair[] { new BasicNameValuePair("gid", GameId()), new BasicNameValuePair("iid", InstanceId()), new BasicNameValuePair("pid", UserEmailAddress()), new BasicNameValuePair("type", paramString), new BasicNameValuePair("mrec", paramYailList1.toJSONString()), new BasicNameValuePair("contents", paramYailList2.toJSONString()), new BasicNameValuePair("mtime", this.instance.getMessageTime(paramString)) }), local25);
  }
  
  private void postServerCommand(final String paramString, final YailList paramYailList)
  {
    AsyncCallbackPair local27 = new AsyncCallbackPair()
    {
      public void onFailure(String paramAnonymousString)
      {
        GameClient.this.ServerCommandFailure(paramString, paramYailList);
        GameClient.this.WebServiceError("ServerCommand", paramAnonymousString);
      }
      
      public void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        try
        {
          GameClient.this.ServerCommandSuccess(paramString, JsonUtil.getListFromJsonArray(paramAnonymousJSONObject.getJSONArray("contents")));
          GameClient.this.FunctionCompleted("ServerCommand");
          return;
        }
        catch (JSONException paramAnonymousJSONObject)
        {
          for (;;)
          {
            Log.w("GameClient", paramAnonymousJSONObject);
            GameClient.this.Info("Server command response failed to parse.");
          }
        }
      }
    };
    Log.d("GameClient", "Going to post " + paramString + " with args " + paramYailList);
    postCommandToGameServer("servercommand", Lists.newArrayList(new NameValuePair[] { new BasicNameValuePair("gid", GameId()), new BasicNameValuePair("iid", InstanceId()), new BasicNameValuePair("pid", UserEmailAddress()), new BasicNameValuePair("command", paramString), new BasicNameValuePair("args", paramYailList.toJSONString()) }), local27);
  }
  
  private void postSetInstance(String paramString)
  {
    AsyncCallbackPair local29 = new AsyncCallbackPair()
    {
      public void onFailure(String paramAnonymousString)
      {
        GameClient.this.WebServiceError("SetInstance", paramAnonymousString);
      }
      
      public void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        GameClient.this.processInstanceLists(paramAnonymousJSONObject);
        GameClient.this.FunctionCompleted("SetInstance");
      }
    };
    postCommandToGameServer("joininstance", Lists.newArrayList(new NameValuePair[] { new BasicNameValuePair("gid", GameId()), new BasicNameValuePair("iid", paramString), new BasicNameValuePair("pid", UserEmailAddress()) }), local29, true);
  }
  
  private void postSetLeader(String paramString)
  {
    AsyncCallbackPair local31 = new AsyncCallbackPair()
    {
      public void onFailure(String paramAnonymousString)
      {
        GameClient.this.WebServiceError("SetLeader", paramAnonymousString);
      }
      
      public void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        GameClient.this.FunctionCompleted("SetLeader");
      }
    };
    if (InstanceId().equals(""))
    {
      Info("You must join an instance before attempting to set a leader.");
      return;
    }
    postCommandToGameServer("setleader", Lists.newArrayList(new NameValuePair[] { new BasicNameValuePair("gid", GameId()), new BasicNameValuePair("iid", InstanceId()), new BasicNameValuePair("pid", UserEmailAddress()), new BasicNameValuePair("leader", paramString) }), local31);
  }
  
  private void processInstanceLists(JSONObject paramJSONObject)
  {
    try
    {
      this.joinedInstances = JsonUtil.getStringListFromJsonArray(paramJSONObject.getJSONArray("joined"));
      this.publicInstances = JsonUtil.getStringListFromJsonArray(paramJSONObject.getJSONArray("public"));
      Object localObject = JsonUtil.getStringListFromJsonArray(paramJSONObject.getJSONArray("invited"));
      if (!((List)localObject).equals(InvitedInstances()))
      {
        paramJSONObject = this.invitedInstances;
        this.invitedInstances = ((List)localObject);
        localObject = new ArrayList((Collection)localObject);
        ((List)localObject).removeAll(paramJSONObject);
        paramJSONObject = ((List)localObject).iterator();
        while (paramJSONObject.hasNext()) {
          Invited((String)paramJSONObject.next());
        }
      }
      return;
    }
    catch (JSONException paramJSONObject)
    {
      Log.w("GameClient", paramJSONObject);
      Info("Instance lists failed to parse.");
    }
  }
  
  private void updateInstanceInfo(JSONObject paramJSONObject)
    throws JSONException
  {
    int i = 0;
    Object localObject = paramJSONObject.getString("leader");
    paramJSONObject = JsonUtil.getStringListFromJsonArray(paramJSONObject.getJSONArray("players"));
    if (!Leader().equals(localObject))
    {
      this.instance.setLeader((String)localObject);
      i = 1;
    }
    paramJSONObject = this.instance.setPlayers(paramJSONObject);
    if (paramJSONObject != PlayerListDelta.NO_CHANGE)
    {
      localObject = paramJSONObject.getPlayersRemoved().iterator();
      while (((Iterator)localObject).hasNext()) {
        PlayerLeft((String)((Iterator)localObject).next());
      }
      paramJSONObject = paramJSONObject.getPlayersAdded().iterator();
      while (paramJSONObject.hasNext()) {
        PlayerJoined((String)paramJSONObject.next());
      }
    }
    if (i != 0) {
      NewLeader(Leader());
    }
  }
  
  @SimpleEvent(description="Indicates that a function call completed.")
  public void FunctionCompleted(final String paramString)
  {
    this.androidUIHandler.post(new Runnable()
    {
      public void run()
      {
        Log.d("GameClient", "Request completed: " + paramString);
        EventDispatcher.dispatchEvent(GameClient.this, "FunctionCompleted", new Object[] { paramString });
      }
    });
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The game name for this application. The same game ID can have one or more game instances.")
  public String GameId()
  {
    return this.gameId;
  }
  
  @DesignerProperty(defaultValue="", editorType="string")
  public void GameId(String paramString)
  {
    this.gameId = paramString;
  }
  
  @SimpleFunction(description="Updates the InstancesJoined and InstancesInvited lists. This procedure can be called before setting the InstanceId.")
  public void GetInstanceLists()
  {
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        GameClient.this.postGetInstanceLists();
      }
    });
  }
  
  @SimpleFunction(description="Retrieves messages of the specified type.")
  public void GetMessages(final String paramString, final int paramInt)
  {
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        GameClient.this.postGetMessages(paramString, paramInt);
      }
    });
  }
  
  @SimpleEvent(description="Indicates that a new message has been received.")
  public void GotMessage(final String paramString1, final String paramString2, final List<Object> paramList)
  {
    Log.d("GameClient", "Got message of type " + paramString1);
    this.androidUIHandler.post(new Runnable()
    {
      public void run()
      {
        EventDispatcher.dispatchEvent(GameClient.this, "GotMessage", new Object[] { paramString1, paramString2, paramList });
      }
    });
  }
  
  @SimpleEvent(description="Indicates that something has occurred which the player should know about.")
  public void Info(final String paramString)
  {
    Log.d("GameClient", "Info: " + paramString);
    this.androidUIHandler.post(new Runnable()
    {
      public void run()
      {
        EventDispatcher.dispatchEvent(GameClient.this, "Info", new Object[] { paramString });
      }
    });
  }
  
  public void Initialize()
  {
    Log.d("GameClient", "Initialize");
    if (this.gameId.equals("")) {
      throw new YailRuntimeError("Game Id must not be empty.", "GameClient Configuration Error.");
    }
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The game instance id.  Taken together,the game ID and the instance ID uniquely identify the game.")
  public String InstanceId()
  {
    return this.instance.getInstanceId();
  }
  
  @SimpleEvent(description="Indicates that the InstanceId property has changed as a result of calling MakeNewInstance or SetInstance.")
  public void InstanceIdChanged(final String paramString)
  {
    Log.d("GameClient", "Instance id changed to " + paramString);
    this.androidUIHandler.post(new Runnable()
    {
      public void run()
      {
        EventDispatcher.dispatchEvent(GameClient.this, "InstanceIdChanged", new Object[] { paramString });
      }
    });
  }
  
  @SimpleFunction(description="Invites a player to this game instance.")
  public void Invite(final String paramString)
  {
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        GameClient.this.postInvite(paramString);
      }
    });
  }
  
  @SimpleEvent(description="Indicates that a user has been invited to this game instance.")
  public void Invited(final String paramString)
  {
    Log.d("GameClient", "Player invited to " + paramString);
    this.androidUIHandler.post(new Runnable()
    {
      public void run()
      {
        EventDispatcher.dispatchEvent(GameClient.this, "Invited", new Object[] { paramString });
      }
    });
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The set of game instances to which this player has been invited but has not yet joined.  To ensure current values are returned, first invoke GetInstanceLists.")
  public List<String> InvitedInstances()
  {
    return this.invitedInstances;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The set of game instances in which this player is participating.  To ensure current values are returned, first invoke GetInstanceLists.")
  public List<String> JoinedInstances()
  {
    return this.joinedInstances;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The game's leader. At any time, each game instance has only one leader, but the leader may change with time.  Initially, the leader is the game instance creator. Application writers determine special properties of the leader. The leader value is updated each time a successful communication is made with the server.")
  public String Leader()
  {
    return this.instance.getLeader();
  }
  
  @SimpleFunction(description="Leaves the current instance.")
  public void LeaveInstance()
  {
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        GameClient.this.postLeaveInstance();
      }
    });
  }
  
  @SimpleFunction(description="Asks the server to create a new instance of this game.")
  public void MakeNewInstance(final String paramString, final boolean paramBoolean)
  {
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        GameClient.this.postMakeNewInstance(paramString, Boolean.valueOf(paramBoolean));
      }
    });
  }
  
  @SimpleEvent(description="Indicates that a new instance was successfully created after calling MakeNewInstance.")
  public void NewInstanceMade(final String paramString)
  {
    this.androidUIHandler.post(new Runnable()
    {
      public void run()
      {
        Log.d("GameClient", "New instance made: " + paramString);
        EventDispatcher.dispatchEvent(GameClient.this, "NewInstanceMade", new Object[] { paramString });
      }
    });
  }
  
  @SimpleEvent(description="Indicates that this game has a new leader as specified through SetLeader")
  public void NewLeader(final String paramString)
  {
    this.androidUIHandler.post(new Runnable()
    {
      public void run()
      {
        Log.d("GameClient", "Leader change to " + paramString);
        EventDispatcher.dispatchEvent(GameClient.this, "NewLeader", new Object[] { paramString });
      }
    });
  }
  
  @SimpleEvent(description="Indicates that a new player has joined this game instance.")
  public void PlayerJoined(final String paramString)
  {
    this.androidUIHandler.post(new Runnable()
    {
      public void run()
      {
        if (!paramString.equals(GameClient.this.UserEmailAddress()))
        {
          Log.d("GameClient", "Player joined: " + paramString);
          EventDispatcher.dispatchEvent(GameClient.this, "PlayerJoined", new Object[] { paramString });
        }
      }
    });
  }
  
  @SimpleEvent(description="Indicates that a player has left this game instance.")
  public void PlayerLeft(final String paramString)
  {
    this.androidUIHandler.post(new Runnable()
    {
      public void run()
      {
        Log.d("GameClient", "Player left: " + paramString);
        EventDispatcher.dispatchEvent(GameClient.this, "PlayerLeft", new Object[] { paramString });
      }
    });
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The current set of players for this game instance. Each player is designated by an email address, which is a string. The list of players is updated each time a successful communication is made with the game server.")
  public List<String> Players()
  {
    return this.instance.getPlayers();
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The set of game instances that have been marked public. To ensure current values are returned, first invoke {@link #GetInstanceLists}. ")
  public List<String> PublicInstances()
  {
    return this.publicInstances;
  }
  
  @SimpleFunction(description="Sends a keyed message to all recipients in the recipients list. The message will consist of the contents list.")
  public void SendMessage(final String paramString, final YailList paramYailList1, final YailList paramYailList2)
  {
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        GameClient.this.postNewMessage(paramString, paramYailList1, paramYailList2);
      }
    });
  }
  
  @SimpleFunction(description="Sends the specified command to the game server.")
  public void ServerCommand(final String paramString, final YailList paramYailList)
  {
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        GameClient.this.postServerCommand(paramString, paramYailList);
      }
    });
  }
  
  @SimpleEvent(description="Indicates that a server command failed.")
  public void ServerCommandFailure(final String paramString, final YailList paramYailList)
  {
    this.androidUIHandler.post(new Runnable()
    {
      public void run()
      {
        Log.d("GameClient", "Server command failed: " + paramString);
        EventDispatcher.dispatchEvent(GameClient.this, "ServerCommandFailure", new Object[] { paramString, paramYailList });
      }
    });
  }
  
  @SimpleEvent(description="Indicates that a server command returned successfully.")
  public void ServerCommandSuccess(final String paramString, final List<Object> paramList)
  {
    Log.d("GameClient", paramString + " server command returned.");
    this.androidUIHandler.post(new Runnable()
    {
      public void run()
      {
        EventDispatcher.dispatchEvent(GameClient.this, "ServerCommandSuccess", new Object[] { paramString, paramList });
      }
    });
  }
  
  @DesignerProperty(defaultValue="http://appinvgameserver.appspot.com", editorType="string")
  public void ServiceURL(String paramString)
  {
    if (paramString.endsWith("/"))
    {
      this.serviceUrl = paramString.substring(0, paramString.length() - 1);
      return;
    }
    this.serviceUrl = paramString;
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The URL of the game server.")
  public String ServiceUrl()
  {
    return this.serviceUrl;
  }
  
  @SimpleFunction(description="Sets InstanceId and joins the specified instance.")
  public void SetInstance(final String paramString)
  {
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        if (paramString.equals(""))
        {
          Log.d("GameClient", "Instance id set to empty string.");
          if (!GameClient.this.InstanceId().equals(""))
          {
            GameClient.access$302(GameClient.this, new GameInstance(""));
            GameClient.this.InstanceIdChanged("");
            GameClient.this.FunctionCompleted("SetInstance");
          }
          return;
        }
        GameClient.this.postSetInstance(paramString);
      }
    });
  }
  
  @SimpleFunction(description="Tells the server to set the leader to playerId. Only the current leader may successfully set a new leader.")
  public void SetLeader(final String paramString)
  {
    AsynchUtil.runAsynchronously(new Runnable()
    {
      public void run()
      {
        GameClient.this.postSetLeader(paramString);
      }
    });
  }
  
  @SimpleProperty(category=PropertyCategory.BEHAVIOR, description="The email address that is being used as the player id for this game client.   At present, users must set this manually in oder to join a game.  But this property will change in the future so that is set automatically, and users will not be able to change it.")
  public String UserEmailAddress()
  {
    if (this.userEmailAddress.equals("")) {
      Info("User email address is empty.");
    }
    return this.userEmailAddress;
  }
  
  @SimpleProperty
  public void UserEmailAddress(String paramString)
  {
    this.userEmailAddress = paramString;
    UserEmailAddressSet(paramString);
  }
  
  @SimpleEvent(description="Indicates that the user email address has been set.")
  public void UserEmailAddressSet(final String paramString)
  {
    Log.d("GameClient", "Email address set.");
    this.androidUIHandler.post(new Runnable()
    {
      public void run()
      {
        EventDispatcher.dispatchEvent(GameClient.this, "UserEmailAddressSet", new Object[] { paramString });
      }
    });
  }
  
  @SimpleEvent(description="Indicates that an error occurred while communicating with the web server.")
  public void WebServiceError(final String paramString1, final String paramString2)
  {
    Log.e("GameClient", "WebServiceError: " + paramString2);
    this.androidUIHandler.post(new Runnable()
    {
      public void run()
      {
        EventDispatcher.dispatchEvent(GameClient.this, "WebServiceError", new Object[] { paramString1, paramString2 });
      }
    });
  }
  
  public void onResume()
  {
    Log.d("GameClient", "Activity Resumed.");
  }
  
  public void onStop()
  {
    Log.d("GameClient", "Activity Stopped.");
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\com\google\appinventor\components\runtime\GameClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */