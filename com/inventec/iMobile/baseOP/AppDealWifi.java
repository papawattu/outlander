package com.inventec.iMobile.baseOP;

import android.content.Context;
import com.inventec.iMobile.baseClass.myActivityBase;
import com.inventec.iMobile.iMobile_AppGlobalVar;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Enumeration;

public final class AppDealWifi
{
  public static final int CYCLIC_INTERVAL = 100;
  static FileOutputStream fos = null;
  public static final boolean g_DoLog = false;
  public static final boolean g_DoLog_Buf = false;
  public static final boolean g_DoLog_Msg = true;
  public static String logMsg;
  public static final String mHostIP = "192.168.8.46";
  private static final int mHostPort = 8080;
  static StringBuilder mStrMSG;
  static boolean socketConnected = false;
  boolean bRunTimerThread = true;
  final int connSocketTimeOut = 1000;
  private Thread mThread = null;
  Thread mTimerThread = null;
  final int reconnTimeOut = 30;
  private int reconnectTimes = 30;
  Socket socket = null;
  
  static
  {
    mStrMSG = new StringBuilder();
  }
  
  public static void ClearLog()
  {
    mStrMSG.setLength(0);
  }
  
  public static String GetLog()
  {
    return mStrMSG.toString();
  }
  
  static void LogBuffMsg(byte[] paramArrayOfByte, int paramInt, boolean paramBoolean) {}
  
  public static void LogExceptionMsg(Exception paramException) {}
  
  public static void LogMsg(String paramString) {}
  
  static int byte2unsigned(byte paramByte)
  {
    byte b = paramByte;
    paramByte = b;
    if (b < 0) {
      paramByte = b + 256;
    }
    return paramByte;
  }
  
  public static void closeFile()
  {
    if (fos != null) {}
    try
    {
      fos.close();
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        LogExceptionMsg(localIOException);
      }
    }
  }
  
  public static boolean createFile(String paramString)
  {
    try
    {
      FileOutputStream localFileOutputStream = new java/io/FileOutputStream;
      localFileOutputStream.<init>(paramString);
      fos = localFileOutputStream;
      bool = true;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        boolean bool = false;
      }
    }
    return bool;
  }
  
  public static int myWithSendAction(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte[1] & 0xFF;
    if (paramArrayOfByte[0] == -9) {
      i = i * 256 + (paramArrayOfByte[2] & 0xFF) + 3;
    }
    for (;;)
    {
      int j = i;
      try
      {
        if (i > paramArrayOfByte.length) {
          j = paramArrayOfByte.length;
        }
        return j;
        i += 2;
      }
      catch (Exception paramArrayOfByte)
      {
        for (;;)
        {
          LogExceptionMsg(paramArrayOfByte);
          j = 0;
        }
      }
    }
  }
  
  public void cancelTimer()
  {
    this.bRunTimerThread = false;
    MsgReceiver.BroadcastMsgAction(60, 0);
    if (this.mTimerThread != null) {}
    try
    {
      this.mTimerThread.join(1000L);
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        LogExceptionMsg(localInterruptedException);
        this.mTimerThread = null;
      }
    }
    finally
    {
      this.mTimerThread = null;
    }
    LogMsg("-----------AppDealWifi cancelTimer----------");
  }
  
  void dealTimerFunc()
  {
    
    if (iMobile_AppGlobalVar.isConn())
    {
      if (!socketConnected)
      {
        socketConnected = true;
        sendMacAddr();
        if (MsgReceiver.upgradeProcess == null) {
          MsgSender.resendKMsg(CmdMake.makeOneCmd((byte)-86, (byte)0));
        }
      }
      MsgReceiver.dealSyncTimer();
    }
    for (;;)
    {
      return;
      socketConnected = false;
      MsgReceiver.BroadcastMsgAction(60, 0);
      if (this.reconnectTimes <= 0)
      {
        LogMsg("disconnected detected");
        iMobile_AppGlobalVar.BackgroundReConn();
        this.reconnectTimes = 30;
      }
      this.reconnectTimes -= 1;
    }
  }
  
  final String getLocalIpAddress()
  {
    try
    {
      localEnumeration = NetworkInterface.getNetworkInterfaces();
      if (localEnumeration.hasMoreElements()) {}
    }
    catch (SocketException localSocketException)
    {
      for (;;)
      {
        Enumeration localEnumeration;
        Object localObject;
        InetAddress localInetAddress;
        LogExceptionMsg(localSocketException);
      }
    }
    for (localObject = null;; localObject = localInetAddress.getHostAddress().toString())
    {
      return (String)localObject;
      localObject = ((NetworkInterface)localEnumeration.nextElement()).getInetAddresses();
      do
      {
        if (!((Enumeration)localObject).hasMoreElements()) {
          break;
        }
        localInetAddress = (InetAddress)((Enumeration)localObject).nextElement();
      } while (localInetAddress.isLoopbackAddress());
    }
  }
  
  public void initTimer()
  {
    LogMsg("-----------initTimer----------");
    if (this.mTimerThread == null)
    {
      this.bRunTimerThread = true;
      this.mTimerThread = new Thread(new Runnable()
      {
        public void run()
        {
          AppDealWifi.LogMsg("sending Task Started");
          try
          {
            Thread.sleep(200L);
            if (!AppDealWifi.this.bRunTimerThread)
            {
              AppDealWifi.LogMsg("sending Task terminated");
              return;
            }
          }
          catch (InterruptedException localInterruptedException1)
          {
            for (;;)
            {
              AppDealWifi.LogExceptionMsg(localInterruptedException1);
              AppDealWifi.LogMsg("sleep failed");
              continue;
              AppDealWifi.this.dealTimerFunc();
              try
              {
                Thread.sleep(100L);
              }
              catch (InterruptedException localInterruptedException2)
              {
                AppDealWifi.LogExceptionMsg(localInterruptedException2);
                AppDealWifi.LogMsg("sleep failed");
              }
            }
          }
        }
      });
    }
    if (this.mTimerThread.isAlive()) {}
    for (;;)
    {
      return;
      this.mTimerThread.start();
    }
  }
  
  /* Error */
  public void myCloseConn()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 55	com/inventec/iMobile/baseOP/AppDealWifi:socket	Ljava/net/Socket;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull +170 -> 178
    //   11: aload_0
    //   12: getfield 55	com/inventec/iMobile/baseOP/AppDealWifi:socket	Ljava/net/Socket;
    //   15: invokevirtual 217	java/net/Socket:shutdownInput	()V
    //   18: aload_0
    //   19: getfield 55	com/inventec/iMobile/baseOP/AppDealWifi:socket	Ljava/net/Socket;
    //   22: invokevirtual 220	java/net/Socket:shutdownOutput	()V
    //   25: aload_0
    //   26: getfield 55	com/inventec/iMobile/baseOP/AppDealWifi:socket	Ljava/net/Socket;
    //   29: invokevirtual 221	java/net/Socket:close	()V
    //   32: new 44	java/lang/StringBuilder
    //   35: astore_1
    //   36: aload_1
    //   37: ldc -33
    //   39: invokespecial 224	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   42: aload_1
    //   43: aload_0
    //   44: getfield 55	com/inventec/iMobile/baseOP/AppDealWifi:socket	Ljava/net/Socket;
    //   47: invokevirtual 227	java/net/Socket:isConnected	()Z
    //   50: invokevirtual 231	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   53: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 127	com/inventec/iMobile/baseOP/AppDealWifi:LogMsg	(Ljava/lang/String;)V
    //   59: new 44	java/lang/StringBuilder
    //   62: astore_1
    //   63: aload_1
    //   64: ldc -23
    //   66: invokespecial 224	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   69: aload_1
    //   70: aload_0
    //   71: getfield 55	com/inventec/iMobile/baseOP/AppDealWifi:socket	Ljava/net/Socket;
    //   74: invokevirtual 236	java/net/Socket:isClosed	()Z
    //   77: invokevirtual 231	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   80: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: invokestatic 127	com/inventec/iMobile/baseOP/AppDealWifi:LogMsg	(Ljava/lang/String;)V
    //   86: new 44	java/lang/StringBuilder
    //   89: astore_1
    //   90: aload_1
    //   91: ldc -18
    //   93: invokespecial 224	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   96: aload_1
    //   97: aload_0
    //   98: getfield 55	com/inventec/iMobile/baseOP/AppDealWifi:socket	Ljava/net/Socket;
    //   101: invokevirtual 241	java/net/Socket:isBound	()Z
    //   104: invokevirtual 231	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   107: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: invokestatic 127	com/inventec/iMobile/baseOP/AppDealWifi:LogMsg	(Ljava/lang/String;)V
    //   113: new 44	java/lang/StringBuilder
    //   116: astore_1
    //   117: aload_1
    //   118: ldc -13
    //   120: invokespecial 224	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   123: aload_1
    //   124: aload_0
    //   125: getfield 55	com/inventec/iMobile/baseOP/AppDealWifi:socket	Ljava/net/Socket;
    //   128: invokevirtual 246	java/net/Socket:isInputShutdown	()Z
    //   131: invokevirtual 231	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   134: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   137: invokestatic 127	com/inventec/iMobile/baseOP/AppDealWifi:LogMsg	(Ljava/lang/String;)V
    //   140: new 44	java/lang/StringBuilder
    //   143: astore_1
    //   144: aload_1
    //   145: ldc -8
    //   147: invokespecial 224	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   150: aload_1
    //   151: aload_0
    //   152: getfield 55	com/inventec/iMobile/baseOP/AppDealWifi:socket	Ljava/net/Socket;
    //   155: invokevirtual 251	java/net/Socket:isOutputShutdown	()Z
    //   158: invokevirtual 231	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   161: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   164: invokestatic 127	com/inventec/iMobile/baseOP/AppDealWifi:LogMsg	(Ljava/lang/String;)V
    //   167: ldc -3
    //   169: invokestatic 127	com/inventec/iMobile/baseOP/AppDealWifi:LogMsg	(Ljava/lang/String;)V
    //   172: ldc2_w 254
    //   175: invokestatic 258	java/lang/Thread:sleep	(J)V
    //   178: invokestatic 261	com/inventec/iMobile/baseOP/MsgReceiver:emptySendArray	()V
    //   181: aload_0
    //   182: aconst_null
    //   183: putfield 55	com/inventec/iMobile/baseOP/AppDealWifi:socket	Ljava/net/Socket;
    //   186: aload_0
    //   187: bipush 30
    //   189: putfield 61	com/inventec/iMobile/baseOP/AppDealWifi:reconnectTimes	I
    //   192: aload_0
    //   193: getfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   196: astore_1
    //   197: aload_1
    //   198: ifnull +18 -> 216
    //   201: aload_0
    //   202: getfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   205: ldc2_w 116
    //   208: invokevirtual 123	java/lang/Thread:join	(J)V
    //   211: aload_0
    //   212: aconst_null
    //   213: putfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   216: aload_0
    //   217: monitorexit
    //   218: return
    //   219: astore_1
    //   220: aload_1
    //   221: invokestatic 95	com/inventec/iMobile/baseOP/AppDealWifi:LogExceptionMsg	(Ljava/lang/Exception;)V
    //   224: goto -199 -> 25
    //   227: astore_1
    //   228: aload_1
    //   229: invokestatic 95	com/inventec/iMobile/baseOP/AppDealWifi:LogExceptionMsg	(Ljava/lang/Exception;)V
    //   232: invokestatic 261	com/inventec/iMobile/baseOP/MsgReceiver:emptySendArray	()V
    //   235: aload_0
    //   236: aconst_null
    //   237: putfield 55	com/inventec/iMobile/baseOP/AppDealWifi:socket	Ljava/net/Socket;
    //   240: aload_0
    //   241: bipush 30
    //   243: putfield 61	com/inventec/iMobile/baseOP/AppDealWifi:reconnectTimes	I
    //   246: aload_0
    //   247: getfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   250: astore_1
    //   251: aload_1
    //   252: ifnull -36 -> 216
    //   255: aload_0
    //   256: getfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   259: ldc2_w 116
    //   262: invokevirtual 123	java/lang/Thread:join	(J)V
    //   265: aload_0
    //   266: aconst_null
    //   267: putfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   270: goto -54 -> 216
    //   273: astore_1
    //   274: aload_0
    //   275: monitorexit
    //   276: aload_1
    //   277: athrow
    //   278: astore_1
    //   279: aload_1
    //   280: invokestatic 95	com/inventec/iMobile/baseOP/AppDealWifi:LogExceptionMsg	(Ljava/lang/Exception;)V
    //   283: aload_0
    //   284: aconst_null
    //   285: putfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   288: goto -72 -> 216
    //   291: astore_1
    //   292: aload_0
    //   293: aconst_null
    //   294: putfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   297: aload_1
    //   298: athrow
    //   299: astore_1
    //   300: invokestatic 261	com/inventec/iMobile/baseOP/MsgReceiver:emptySendArray	()V
    //   303: aload_0
    //   304: aconst_null
    //   305: putfield 55	com/inventec/iMobile/baseOP/AppDealWifi:socket	Ljava/net/Socket;
    //   308: aload_0
    //   309: bipush 30
    //   311: putfield 61	com/inventec/iMobile/baseOP/AppDealWifi:reconnectTimes	I
    //   314: aload_0
    //   315: getfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   318: astore_2
    //   319: aload_2
    //   320: ifnull +18 -> 338
    //   323: aload_0
    //   324: getfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   327: ldc2_w 116
    //   330: invokevirtual 123	java/lang/Thread:join	(J)V
    //   333: aload_0
    //   334: aconst_null
    //   335: putfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   338: aload_1
    //   339: athrow
    //   340: astore_2
    //   341: aload_2
    //   342: invokestatic 95	com/inventec/iMobile/baseOP/AppDealWifi:LogExceptionMsg	(Ljava/lang/Exception;)V
    //   345: aload_0
    //   346: aconst_null
    //   347: putfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   350: goto -12 -> 338
    //   353: astore_1
    //   354: aload_0
    //   355: aconst_null
    //   356: putfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   359: aload_1
    //   360: athrow
    //   361: astore_1
    //   362: aload_1
    //   363: invokestatic 95	com/inventec/iMobile/baseOP/AppDealWifi:LogExceptionMsg	(Ljava/lang/Exception;)V
    //   366: aload_0
    //   367: aconst_null
    //   368: putfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   371: goto -155 -> 216
    //   374: astore_1
    //   375: aload_0
    //   376: aconst_null
    //   377: putfield 67	com/inventec/iMobile/baseOP/AppDealWifi:mThread	Ljava/lang/Thread;
    //   380: aload_1
    //   381: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	382	0	this	AppDealWifi
    //   6	192	1	localObject1	Object
    //   219	2	1	localException1	Exception
    //   227	2	1	localException2	Exception
    //   250	2	1	localThread1	Thread
    //   273	4	1	localObject2	Object
    //   278	2	1	localInterruptedException1	InterruptedException
    //   291	7	1	localObject3	Object
    //   299	40	1	localObject4	Object
    //   353	7	1	localObject5	Object
    //   361	2	1	localInterruptedException2	InterruptedException
    //   374	7	1	localObject6	Object
    //   318	2	2	localThread2	Thread
    //   340	2	2	localInterruptedException3	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   11	25	219	java/lang/Exception
    //   2	7	227	java/lang/Exception
    //   25	178	227	java/lang/Exception
    //   220	224	227	java/lang/Exception
    //   178	197	273	finally
    //   211	216	273	finally
    //   232	251	273	finally
    //   265	270	273	finally
    //   283	288	273	finally
    //   292	299	273	finally
    //   300	319	273	finally
    //   333	338	273	finally
    //   338	340	273	finally
    //   345	350	273	finally
    //   354	361	273	finally
    //   366	371	273	finally
    //   375	382	273	finally
    //   255	265	278	java/lang/InterruptedException
    //   255	265	291	finally
    //   279	283	291	finally
    //   2	7	299	finally
    //   11	25	299	finally
    //   25	178	299	finally
    //   220	224	299	finally
    //   228	232	299	finally
    //   323	333	340	java/lang/InterruptedException
    //   323	333	353	finally
    //   341	345	353	finally
    //   201	211	361	java/lang/InterruptedException
    //   201	211	374	finally
    //   362	366	374	finally
  }
  
  public boolean myOpenConn()
  {
    bool2 = false;
    for (;;)
    {
      try
      {
        if (getLocalIpAddress() != null) {
          continue;
        }
        bool1 = bool2;
      }
      catch (Exception localException2)
      {
        Object localObject;
        int i;
        LogExceptionMsg(localException2);
        myCloseConn();
        boolean bool1 = bool2;
        continue;
      }
      return bool1;
      ConnSSID.scanAccessPoint(myActivityBase.GetCurForegroundActivity());
      LogMsg("myOpenConn");
      localObject = new java/net/Socket;
      ((Socket)localObject).<init>();
      this.socket = ((Socket)localObject);
      bool1 = bool2;
      if (this.socket != null)
      {
        localObject = new java/net/InetSocketAddress;
        ((InetSocketAddress)localObject).<init>("192.168.8.46", 8080);
        LogMsg("try connet to:192.168.8.46 port:8080");
        this.socket.setReuseAddress(true);
        i = 0;
      }
      try
      {
        this.socket.connect((SocketAddress)localObject, 1000);
        i = 1;
      }
      catch (Exception localException1)
      {
        LogExceptionMsg(localException1);
        continue;
      }
      bool1 = bool2;
      if (i != 0)
      {
        localObject = new java/lang/StringBuilder;
        ((StringBuilder)localObject).<init>("Local Port is ");
        LogMsg(this.socket.getLocalPort());
        bool1 = bool2;
        if (this.socket.isConnected())
        {
          this.socket.setSoLinger(true, 0);
          this.socket.setKeepAlive(false);
          this.socket.setSoTimeout(0);
          MsgReceiver.g_sendtime = 200;
          localObject = new java/lang/StringBuilder;
          ((StringBuilder)localObject).<init>("socket is connected :");
          LogMsg(this.socket.getInetAddress() + "\n");
          iMobile_AppGlobalVar.needShowSSIDErr = true;
          bool1 = true;
        }
      }
    }
  }
  
  public boolean mySend(byte[] paramArrayOfByte)
  {
    boolean bool = false;
    if (this.socket == null) {}
    for (;;)
    {
      return bool;
      try
      {
        int i = myWithSendAction(paramArrayOfByte);
        if (i > 0)
        {
          OutputStream localOutputStream = this.socket.getOutputStream();
          if (localOutputStream != null)
          {
            LogBuffMsg(paramArrayOfByte, i, true);
            localOutputStream.write(paramArrayOfByte, 0, i);
            localOutputStream.flush();
          }
          bool = true;
        }
        else
        {
          LogMsg("send msg lenght == 0");
        }
      }
      catch (IOException paramArrayOfByte)
      {
        LogExceptionMsg(paramArrayOfByte);
        LogMsg("disconnect by outputStream");
        iMobile_AppGlobalVar.CloseConn();
      }
    }
  }
  
  public boolean myStartRecv()
  {
    if (this.socket == null) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      if (this.mThread == null)
      {
        this.mThread = new Thread(new Runnable()
        {
          public void run()
          {
            AppDealWifi.LogMsg("Recieve Task started");
            for (;;)
            {
              int i;
              try
              {
                Thread.sleep(200L);
                if (AppDealWifi.this.socket != null)
                {
                  boolean bool = AppDealWifi.this.socket.isClosed();
                  if (!bool) {}
                }
                else
                {
                  if (!iMobile_AppGlobalVar.isConnecting())
                  {
                    AppDealWifi.LogMsg("disconnect by inputStream");
                    iMobile_AppGlobalVar.CloseConn();
                  }
                  AppDealWifi.LogMsg("Recieve Task terminated");
                  return;
                }
              }
              catch (InterruptedException localInterruptedException)
              {
                AppDealWifi.LogExceptionMsg(localInterruptedException);
                AppDealWifi.LogMsg("sleep failed");
                continue;
              }
              catch (IOException localIOException)
              {
                AppDealWifi.LogExceptionMsg(localIOException);
                if (iMobile_AppGlobalVar.isConnecting()) {
                  continue;
                }
                AppDealWifi.LogMsg("disconnect by inputStream");
                iMobile_AppGlobalVar.CloseConn();
                continue;
                InputStream localInputStream = AppDealWifi.this.socket.getInputStream();
                byte[] arrayOfByte1 = new byte['Ѐ'];
                i = localInputStream.read(arrayOfByte1);
                if (i <= 0)
                {
                  if (AppDealWifi.this.socket.isConnected()) {
                    continue;
                  }
                  AppDealWifi.LogMsg("is.read() return -1");
                  continue;
                }
              }
              finally
              {
                if (!iMobile_AppGlobalVar.isConnecting())
                {
                  AppDealWifi.LogMsg("disconnect by inputStream");
                  iMobile_AppGlobalVar.CloseConn();
                }
              }
              AppDealWifi.LogBuffMsg(arrayOfByte2, i, false);
              StackTraceElement[] arrayOfStackTraceElement;
              try
              {
                if (myActivityBase.GetCurForegroundActivity() == null) {
                  continue;
                }
                MsgReceiver.receiveData(arrayOfByte2, i);
              }
              catch (Exception localException)
              {
                AppDealWifi.LogMsg("MsgReceiver.receiveData(buffer_max, rl);");
                AppDealWifi.LogExceptionMsg(localException);
                arrayOfStackTraceElement = localException.getStackTrace();
                i = 0;
              }
              while (i < arrayOfStackTraceElement.length)
              {
                AppDealWifi.LogMsg(arrayOfStackTraceElement[i].toString());
                i++;
              }
            }
          }
        });
        this.mThread.start();
      }
    }
  }
  
  void sendMacAddr()
  {
    Object localObject = myActivityBase.GetCurForegroundActivity();
    if (localObject == null) {
      LogMsg("Error!!no foreground Activity,cannot get Mac Address");
    }
    for (;;)
    {
      return;
      localObject = CmdMake.KO_WF_CONNECT_INFO_SP(ConnSSID.getWifiMacAddr((Context)localObject), (byte)0);
      LogMsg("send Mac Addr");
      MsgSender.resendKMsg((byte[])localObject);
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseOP\AppDealWifi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */