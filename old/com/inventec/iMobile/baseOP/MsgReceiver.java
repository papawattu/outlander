package com.inventec.iMobile.baseOP;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myActivityBase;
import com.inventec.iMobile.baseClass.myWaitDlg;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.baseStruct.DefMsg;
import com.inventec.iMobile.baseStruct.recvDataStruct;
import com.inventec.iMobile.iMobile_AppGlobalVar;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MsgReceiver
{
  public static int COMM_TIMEOUT = 0;
  public static int MSG_SEND_TIMEOUT = 35;
  private static final String MsgDoAction = "com.inventec.iMobile.message.action";
  private static int TIMER_SEND_SYNC;
  private static boolean bNeedEmpty = true;
  static boolean bShowUpgrade;
  private static byte bTestMsg;
  static boolean bTestSync;
  private static boolean comSucceed;
  private static byte dummyCnt;
  public static byte g_doorLook;
  public static int g_sendtime;
  static long g_times;
  private static boolean isRegistrationEnd;
  public static boolean noVINbeforREG;
  private static byte recievedDummyCnt;
  private static byte refreshCount;
  public static int resendTimeOut;
  private static ArrayList<byte[]> sendMsgArray;
  private static ArrayList<byte[]> sendMsgTimesArray;
  public static boolean showErrBar;
  static boolean showErrBarStatusChanged;
  private static boolean updateRealtime;
  public static MsgUpdateTran upgradeProcess;
  private static byte waitECUFunc;
  private static byte waitECUValue;
  
  static
  {
    COMM_TIMEOUT = 7000;
    dummyCnt = 0;
    recievedDummyCnt = 0;
    refreshCount = 0;
    updateRealtime = true;
    comSucceed = true;
    TIMER_SEND_SYNC = 30000;
    isRegistrationEnd = false;
    g_doorLook = -1;
    showErrBar = false;
    showErrBarStatusChanged = false;
    noVINbeforREG = false;
    upgradeProcess = null;
    resendTimeOut = 0;
    g_sendtime = 300;
    bTestSync = false;
    g_times = Calendar.getInstance().getTime().getTime() / 1000L;
    bShowUpgrade = false;
    bTestMsg = 0;
    sendMsgArray = new ArrayList();
    sendMsgTimesArray = new ArrayList();
  }
  
  public static void BroadcastMsgAction(int paramInt1, int paramInt2)
  {
    myActivityBase localmyActivityBase = myActivity.GetCurForegroundActivity();
    Bundle localBundle = new Bundle();
    localBundle.putInt("actID", paramInt1);
    localBundle.putInt("actData", paramInt2);
    myBroadcast.BroadcastMsg(localmyActivityBase, "com.inventec.iMobile.message.action", localBundle);
  }
  
  public static CmdFindStruct FindFirstValidRecvEVRMsg(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramInt1 >= paramInt2)
    {
      paramArrayOfByte = null;
      return paramArrayOfByte;
    }
    int i;
    int j;
    if (paramArrayOfByte[paramInt1] != 111)
    {
      i = paramInt1;
      if (paramArrayOfByte[paramInt1] != -97) {}
    }
    else
    {
      i = paramInt1;
      if (paramInt1 + 1 < paramInt2)
      {
        j = paramArrayOfByte[(paramInt1 + 1)];
        i = paramInt1;
        if (paramInt1 + 2 + j <= paramInt2)
        {
          int k = generalCheckRecvEVRMsg(paramArrayOfByte, paramInt1, j + 2);
          if (k == 0)
          {
            CmdFindStruct localCmdFindStruct = new CmdFindStruct();
            byte[] arrayOfByte = new byte[j + 2];
            for (paramInt2 = 0;; paramInt2++)
            {
              if (paramInt2 >= j + 2)
              {
                localCmdFindStruct.cmd = arrayOfByte;
                localCmdFindStruct.findPos = paramInt1;
                localCmdFindStruct.len = (j + 2);
                paramArrayOfByte = localCmdFindStruct;
                break;
              }
              arrayOfByte[paramInt2] = paramArrayOfByte[(paramInt1 + paramInt2)];
            }
          }
          i = paramInt1;
          if (k == 5)
          {
            byte b = paramArrayOfByte[(paramInt1 + 3)];
            if ((b != -54) && (b != -63) && (b != -61) && (b != -55)) {
              break label208;
            }
            MsgSender.resendKMsg(CmdMake.KO_COMMAND_CONFIRM_SP(b, (byte)0));
          }
        }
      }
    }
    for (;;)
    {
      i = paramInt1 + (j + 1);
      paramInt1 = i + 1;
      break;
      label208:
      showErrBar = true;
    }
  }
  
  static final byte[] KO_WF_DATE_INFO_SYNC_SP__Now()
  {
    Calendar localCalendar = Calendar.getInstance();
    byte[] arrayOfByte = new byte[12];
    arrayOfByte[0] = -10;
    arrayOfByte[1] = 10;
    arrayOfByte[2] = 0;
    arrayOfByte[3] = 5;
    arrayOfByte[4] = ((byte)(localCalendar.get(1) - 2000));
    arrayOfByte[5] = ((byte)(localCalendar.get(2) + 1));
    arrayOfByte[6] = ((byte)localCalendar.get(5));
    arrayOfByte[7] = ((byte)localCalendar.get(11));
    arrayOfByte[8] = ((byte)localCalendar.get(12));
    arrayOfByte[9] = ((byte)localCalendar.get(13));
    arrayOfByte[10] = ((byte)(localCalendar.get(7) - 1));
    arrayOfByte[11] = DefMsg.calcCheckSum(arrayOfByte);
    return arrayOfByte;
  }
  
  public static void RegisterMsgAction(Activity paramActivity, BroadcastReceiver paramBroadcastReceiver)
  {
    myBroadcast.RegisterMsg(paramActivity, "com.inventec.iMobile.message.action", paramBroadcastReceiver);
  }
  
  static void addToArray(byte[] paramArrayOfByte)
  {
    sendMsgArray.add(paramArrayOfByte);
    sendMsgTimesArray.add(new byte[] { 0 });
  }
  
  static boolean cannotDoRemote()
  {
    boolean bool = true;
    if ((DealMsg.g_VINNum[0] != 0) && (DefMsg.g_recdatas[6] != 1)) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public static void closeRegistration()
  {
    isRegistrationEnd = true;
  }
  
  public static void dealECUVer()
  {
    if ((!bShowUpgrade) && (iMobile_AppGlobalVar.needReprogramming(DefMsg.g_recdatas, 181, false, false)))
    {
      bShowUpgrade = true;
      iMobile_AppGlobalVar.needUpgrade = true;
      if (iMobile_AppGlobalVar.canShowUpgrade) {
        BroadcastMsgAction(32, 0);
      }
    }
  }
  
  public static void dealPeriodicMsg()
  {
    MsgSender.resendKMsg(CmdMake.KO_WF_DUMMY_SP(dummyCnt));
    dummyCnt = (byte)(dummyCnt + 1);
    if (dummyCnt >= 100) {
      dummyCnt = 0;
    }
    if (refreshCount >= 40)
    {
      BroadcastMsgAction(60, recievedDummyCnt * 10 / 4);
      recievedDummyCnt = 0;
      refreshCount = 0;
    }
    refreshCount = (byte)(refreshCount + 1);
  }
  
  public static void dealResendTimer()
  {
    if (upgradeProcess != null) {}
    label97:
    label182:
    for (;;)
    {
      return;
      byte[] arrayOfByte1;
      byte[] arrayOfByte2;
      if ((sendMsgArray.size() > 0) && (resendTimeOut <= 0))
      {
        resendTimeOut = MSG_SEND_TIMEOUT;
        arrayOfByte1 = (byte[])sendMsgArray.get(0);
        if (arrayOfByte1 != null)
        {
          arrayOfByte2 = (byte[])sendMsgTimesArray.get(0);
          arrayOfByte2[0] = ((byte)(arrayOfByte2[0] + 1));
          if (arrayOfByte2[0] != 1) {
            break label97;
          }
          AppDealWifi.LogMsg("timeout once, try resend");
          MsgSender.resendKMsg(arrayOfByte1);
        }
      }
      for (;;)
      {
        if (resendTimeOut <= 0) {
          break label182;
        }
        resendTimeOut -= 1;
        break;
        if (arrayOfByte2[0] == 2)
        {
          AppDealWifi.LogMsg("timeout twice, give up");
          myWaitDlg.setWaitResult(2);
          int i = arrayOfByte1[3];
          comSucceed = false;
          if ((i == 7) || (i == 8) || (i == 18) || (i == 19))
          {
            MsgSender.resendKMsg(arrayOfByte1);
          }
          else
          {
            sendMsgArray.remove(0);
            sendMsgTimesArray.remove(0);
            sendNext();
          }
        }
        else
        {
          MsgSender.resendKMsg(arrayOfByte1);
        }
      }
    }
  }
  
  public static void dealSyncTimer()
  {
    if (upgradeProcess != null) {}
    label146:
    for (;;)
    {
      return;
      g_sendtime += 1;
      if ((bTestSync) && (g_sendtime > 50))
      {
        AppDealWifi.LogMsg("wait Timer Sync confirm time out!");
        bTestSync = false;
      }
      long l = Calendar.getInstance().getTime().getTime() / 1000L;
      if ((g_sendtime >= TIMER_SEND_SYNC / 100) || (l < g_times) || (l - g_times >= TIMER_SEND_SYNC / 1000))
      {
        g_sendtime = 0;
        g_times = l;
        bTestSync = true;
        byte[] arrayOfByte = KO_WF_DATE_INFO_SYNC_SP__Now();
        AppDealWifi.LogMsg("send sync time-------------------------------");
        MsgSender.resendKMsg(arrayOfByte);
        if (!DealMsg.g_isDemo) {
          updateRealtime = false;
        }
      }
      for (;;)
      {
        if (!showErrBarStatusChanged) {
          break label146;
        }
        BroadcastMsgAction(40, 0);
        break;
        dealPeriodicMsg();
      }
    }
  }
  
  public static void emptySendArray()
  {
    if (bNeedEmpty)
    {
      AppDealWifi.LogMsg("empty SendArray!!");
      i = sendMsgArray.size() - 1;
      if (i >= 0) {
        break label39;
      }
    }
    for (int i = sendMsgTimesArray.size() - 1;; i--)
    {
      if (i < 0)
      {
        return;
        label39:
        sendMsgArray.remove(i);
        i--;
        break;
      }
      sendMsgTimesArray.remove(i);
    }
  }
  
  private static int generalCheckRecvEVRMsg(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = 0;
    if ((paramArrayOfByte[paramInt1] == -97) && (paramArrayOfByte[(paramInt1 + 3)] < 100)) {
      paramInt1 = i;
    }
    for (;;)
    {
      return paramInt1;
      if (paramArrayOfByte[paramInt1] != 111)
      {
        paramInt1 = 6;
      }
      else
      {
        int j = paramArrayOfByte[(paramInt1 + 1)];
        if (j != paramInt2 - 2)
        {
          paramInt1 = 6;
        }
        else
        {
          int k = paramArrayOfByte[(paramInt1 + 2)];
          if ((k != 0) && (k != 1))
          {
            paramInt1 = 6;
          }
          else
          {
            byte b = paramArrayOfByte[(paramInt1 + 3)];
            if (k == 0)
            {
              if ((b >= 45) || ((b < 0) && (b != -64))) {
                paramInt1 = 5;
              }
            }
            else if (b >= 24)
            {
              paramInt1 = 4;
              continue;
            }
            if (DefMsg.calcCheckSum_Complex(paramArrayOfByte, paramInt1) != paramArrayOfByte[(paramInt1 + paramInt2 - 1)])
            {
              paramInt1 = 2;
            }
            else
            {
              paramInt1 = i;
              if (k == 0)
              {
                paramArrayOfByte = DefMsg.getDataLenPosBymsgID(b);
                if (paramArrayOfByte == null)
                {
                  paramInt1 = 5;
                }
                else
                {
                  paramInt1 = i;
                  if (paramArrayOfByte.datalen != j - 3) {
                    paramInt1 = 1;
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  public static boolean isCommSucceed()
  {
    return comSucceed;
  }
  
  private static boolean isEUCValuRight(byte paramByte1, byte paramByte2)
  {
    boolean bool2 = true;
    for (byte b = 0;; b++)
    {
      boolean bool1;
      if (b >= 7) {
        bool1 = false;
      }
      do
      {
        return bool1;
        if ((paramByte1 & 0x1) != 1) {
          break;
        }
        bool1 = bool2;
      } while (b == paramByte2);
      paramByte1 = (byte)(paramByte1 >> 1);
    }
  }
  
  public static void loadAll(Context paramContext)
  {
    boolean bool2 = true;
    byte[] arrayOfByte = new byte[4];
    DealFile.loaddata(paramContext, "MsgReciver.sav", arrayOfByte);
    g_doorLook = arrayOfByte[0];
    if (arrayOfByte[1] == 1)
    {
      bool1 = true;
      showErrBar = bool1;
      if (arrayOfByte[2] != 1) {
        break label53;
      }
    }
    label53:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      noVINbeforREG = bool1;
      return;
      bool1 = false;
      break;
    }
  }
  
  public static void openRegistration()
  {
    isRegistrationEnd = false;
  }
  
  public static void receiveData(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramInt < 5) {}
    int i;
    int k;
    label105:
    ArrayList localArrayList;
    for (;;)
    {
      return;
      i = paramInt;
      j = 0;
      for (;;)
      {
        if (i <= 0)
        {
          if (upgradeProcess == null) {
            break label105;
          }
          upgradeProcess.dealMsg(paramArrayOfByte, paramInt);
          break;
        }
        k = paramArrayOfByte[(j + 1)] + 2;
        if ((paramArrayOfByte[j] == 159) && (paramArrayOfByte[(j + 3)] == 203))
        {
          MsgSender.resendKMsg(CmdMake.KO_COMMAND_CONFIRM_EX_SP(paramArrayOfByte[j], paramArrayOfByte[(j + 3)], (byte)0));
          break;
        }
        j += k;
        i -= k;
      }
      localArrayList = splitCmds(paramArrayOfByte, paramInt);
      if ((localArrayList != null) && (localArrayList.size() > 0))
      {
        j = -1;
        i = 0;
        if (i < localArrayList.size()) {
          break;
        }
        if (j != -1) {
          myWaitDlg.setWaitResult(j);
        }
      }
    }
    paramArrayOfByte = ((CmdFindStruct)localArrayList.get(i)).cmd;
    byte b1;
    byte b2;
    if (paramArrayOfByte[2] != 1)
    {
      b1 = paramArrayOfByte[3];
      b2 = saveRightData(paramArrayOfByte);
      if ((DealMsg.g_VINNum[0] == 0) && (b1 != 21) && (b1 != 3) && (b1 != 42) && (b1 != -64))
      {
        MsgSender.resendKMsg(CmdMake.KO_COMMAND_CONFIRM_SP(b1, (byte)0));
        paramInt = j;
      }
    }
    label290:
    label684:
    do
    {
      do
      {
        for (;;)
        {
          i++;
          j = paramInt;
          break;
          MsgSender.resendKMsg(CmdMake.KO_COMMAND_CONFIRM_SP(b1, b2));
          boolean bool;
          if (b2 == 0) {
            if (b1 == 38) {
              if ((DefMsg.g_recdatas[''] == 1) && (DefMsg.g_recdatas[94] > 0) && (DefMsg.g_recdatas[94] < 7))
              {
                bool = true;
                showNAVIWnd(bool);
              }
            }
          }
          for (;;)
          {
            paramInt = j;
            if (!updateRealtime) {
              break;
            }
            BroadcastMsgAction(80, 0);
            paramInt = j;
            break;
            bool = false;
            break label290;
            if (showAlarm(b1) == -1) {
              if (b1 == 22)
              {
                setECUData();
              }
              else if (b1 == 27)
              {
                DefMsg.doTestCHGMode();
                DealMsg.SaveAPPSet(myActivity.GetCurForegroundActivity());
                sendAddToArray(CmdMake.KO_WF_TM_CHG_ON_RQ_SP(paramArrayOfByte[4]));
              }
              else if (b1 == 24)
              {
                DefMsg.doTestCHGChange();
              }
              else if (b1 == 25)
              {
                DefMsg.doTestACChange();
              }
              else if (b1 == 36)
              {
                paramInt = DefMsg.g_recdatas[''];
                b1 = DefMsg.g_recdatas[''];
                if ((paramInt == 1) && ((b1 == 1) || (b1 == 2))) {
                  g_doorLook = b1;
                } else if ((paramInt == 2) && ((b1 == 4) || (b1 == 5))) {
                  g_doorLook = b1;
                } else if (g_doorLook == -1) {
                  g_doorLook = 0;
                }
              }
              else if (b1 == -64)
              {
                if (!DealMsg.g_isDemo) {
                  dealECUVer();
                }
              }
              else if (b1 == 44)
              {
                testEcuCommEvr();
              }
              else if ((b1 == 1) || (b1 == 2) || (b1 == 29))
              {
                if ((b1 == 1) && (DefMsg.g_recdatas[0] == 0))
                {
                  DefMsg.g_recdatas[98] = ((byte)(DefMsg.g_recdatas[98] & 0xF0));
                  DefMsg.g_recdatas[98] = ((byte)(DefMsg.g_recdatas[98] | 0x1));
                  DefMsg.g_datas[98] = ((byte)(DefMsg.g_datas[98] & 0xF0));
                  DefMsg.g_datas[98] = ((byte)(DefMsg.g_datas[98] | 0x1));
                }
                myActivity.GetCurForegroundActivity();
                BroadcastMsgAction(90, 0);
                continue;
                if (b2 == -1) {
                  showAlarm(b1);
                }
              }
            }
          }
          paramInt = paramArrayOfByte[4];
          if ((paramArrayOfByte[0] == -97) && (paramArrayOfByte[3] < 100))
          {
            recievedDummyCnt = (byte)(recievedDummyCnt + 1);
            paramInt = j;
          }
          else if (paramInt != 0)
          {
            paramInt = 0;
          }
          else
          {
            if (paramArrayOfByte[3] != 5) {
              break label684;
            }
            bTestSync = false;
            paramInt = j;
          }
        }
        paramInt = j;
      } while (sendMsgArray.size() <= 0);
      k = ((byte[])sendMsgArray.get(0))[3];
      paramInt = j;
    } while (k != paramArrayOfByte[3]);
    int j = 1;
    AppDealWifi.LogMsg("received right ACK, set result to true");
    comSucceed = true;
    sendMsgArray.remove(0);
    sendMsgTimesArray.remove(0);
    bNeedEmpty = true;
    label769:
    label776:
    Object localObject;
    if (k == 20)
    {
      paramArrayOfByte = "";
      paramInt = 145;
      if (paramInt >= 177)
      {
        localObject = myActivity.GetCurForegroundActivity();
        DealMsg.setSSID(paramArrayOfByte);
        DealMsg.saveSSID((Context)localObject);
      }
    }
    for (;;)
    {
      sendNext();
      paramInt = j;
      break;
      localObject = DefMsg.g_recdatas;
      b1 = DefMsg.g_datas[paramInt];
      localObject[paramInt] = b1;
      if ((b1 <= 32) || (b1 >= Byte.MAX_VALUE)) {
        break label776;
      }
      paramArrayOfByte = paramArrayOfByte + (char)b1;
      paramInt++;
      break label769;
      if (k == 16)
      {
        DealMsg.g_isDemo = false;
        DefMsg.memcpy(DealMsg.g_VINNum, 0, DefMsg.g_recdatas, 40, 17);
        DefMsg.memsetZero(DefMsg.g_recdatas, 40, 17);
        DefMsg.g_recdatas[57] = 0;
        DefMsg.g_recdatas['´'] = -1;
        paramArrayOfByte = myActivity.GetCurForegroundActivity();
        DealMsg.saveVIN(paramArrayOfByte);
        DealMsg.SaveAPPSet(paramArrayOfByte);
        ConnSSID.saveCurWifi(paramArrayOfByte);
        DefMsg.updateAll();
        Log.i("shudingcai", "reg------------------------------------------------");
        iMobile_AppGlobalVar.showRegOK = true;
        MsgUpdateTran.g_curA0Status = 2;
      }
    }
  }
  
  private static void reportECUDone()
  {
    waitECUFunc = 0;
    waitECUValue = 0;
    sendAddToArray(CmdMake.KO_WF_R_CUSTOM_SP());
  }
  
  public static void saveAll(Context paramContext)
  {
    int j = 1;
    byte[] arrayOfByte = new byte[4];
    arrayOfByte[0] = g_doorLook;
    if (showErrBar)
    {
      i = 1;
      arrayOfByte[1] = ((byte)i);
      if (!noVINbeforREG) {
        break label53;
      }
    }
    label53:
    for (int i = j;; i = 0)
    {
      arrayOfByte[2] = ((byte)i);
      DealFile.savedata(paramContext, "MsgReciver.sav", arrayOfByte);
      return;
      i = 0;
      break;
    }
  }
  
  private static byte saveRightData(byte[] paramArrayOfByte)
  {
    recvDataStruct localrecvDataStruct = DefMsg.getDataLenPosBymsgID(paramArrayOfByte[3]);
    byte b;
    if (localrecvDataStruct == null)
    {
      b = 5;
      return b;
    }
    int k = localrecvDataStruct.datalen;
    int m = localrecvDataStruct.gdataPos;
    int j = 1;
    label71:
    for (int i = 0;; i++)
    {
      if (i >= k) {}
      for (i = j;; i = 0)
      {
        if (i == 0) {
          break label77;
        }
        b = -1;
        break;
        if (paramArrayOfByte[(i + 4)] == DefMsg.g_recdatas[(m + i)]) {
          break label71;
        }
      }
    }
    label77:
    for (i = 0;; i++)
    {
      if (i >= k)
      {
        b = 0;
        break;
      }
      DefMsg.g_recdatas[(m + i)] = paramArrayOfByte[(i + 4)];
    }
  }
  
  public static boolean sendAddToArray(byte[] paramArrayOfByte)
  {
    int i = sendMsgArray.size();
    if (i > 0)
    {
      i--;
      if (i < 0) {
        addToArray(paramArrayOfByte);
      }
    }
    for (;;)
    {
      return true;
      byte[] arrayOfByte = (byte[])sendMsgArray.get(i);
      if ((arrayOfByte.length == paramArrayOfByte.length) && (DefMsg.memcmp(paramArrayOfByte, 0, arrayOfByte, 0, paramArrayOfByte.length)))
      {
        if ((DefMsg.g_recdatas[57] == 1) && (paramArrayOfByte[3] == 16))
        {
          sendMsgArray.remove(i);
          sendMsgArray.add(0, arrayOfByte);
          paramArrayOfByte = (byte[])sendMsgTimesArray.get(i);
          sendMsgTimesArray.remove(i);
          sendMsgTimesArray.add(0, paramArrayOfByte);
          sendNext();
        }
      }
      else
      {
        i--;
        break;
        addToArray(paramArrayOfByte);
        sendNext();
      }
    }
  }
  
  static boolean sendNext()
  {
    if (sendMsgArray.size() > 0)
    {
      byte[] arrayOfByte = (byte[])sendMsgArray.get(0);
      resendTimeOut = MSG_SEND_TIMEOUT;
      MsgSender.resendKMsg(arrayOfByte);
      AppDealWifi.LogMsg("send first message from array, set result to false");
      comSucceed = false;
    }
    return true;
  }
  
  private static void setECUData()
  {
    int i = 61;
    for (;;)
    {
      if (i >= 67) {
        return;
      }
      byte[] arrayOfByte = DefMsg.g_recdatas;
      int k = i + 1;
      i = arrayOfByte[i];
      arrayOfByte = DefMsg.g_recdatas;
      int j = k + 1;
      int m = arrayOfByte[k];
      k = (byte)(i & 0x3F);
      byte b1 = (byte)(m >> 1 & 0x7F);
      byte b2 = (byte)(i >> 6 & 0x3 | m << 2 & 0x4);
      i = j;
      if (k < 61)
      {
        i = j;
        if (k > 0) {
          if ((b1 != 0) && (b2 != 7))
          {
            i = j;
            if (!isEUCValuRight(b1, b2)) {}
          }
          else
          {
            DealMsg.g_recvFUNC[k][0] = b1;
            DealMsg.g_recvFUNC[k][1] = b2;
            DealMsg.g_recvFUNC[k][2] = 1;
            i = j;
            if (waitECUFunc == k)
            {
              i = j;
              if (waitECUValue == b2)
              {
                reportECUDone();
                i = j;
              }
            }
          }
        }
      }
    }
  }
  
  private static byte showAlarm(byte paramByte)
  {
    byte b1 = 1;
    byte b2 = 0;
    if (paramByte == 20)
    {
      paramByte = DealMsg.addAlertMsg(1);
      AppDealWifi.LogMsg("bTestMsg = " + bTestMsg);
      if (paramByte > 0) {
        BroadcastMsgAction(0, paramByte);
      }
      if ((DealMsg.g_VINNum[0] != 0) && (DefMsg.g_recdatas[57] != 1)) {
        break label708;
      }
      if (bTestMsg == 7)
      {
        bTestMsg = 0;
        testMsg(true);
      }
    }
    for (;;)
    {
      return b1;
      if (paramByte == 16)
      {
        paramByte = DealMsg.addAlertMsg(2);
        if (paramByte == 2) {}
        for (b1 = 1;; b1 = 0) {
          break;
        }
      }
      if (paramByte == 30)
      {
        paramByte = DealMsg.addAlertMsg(3);
        if (paramByte == 3) {}
        for (b1 = 1;; b1 = 0) {
          break;
        }
      }
      if (paramByte == 19)
      {
        paramByte = DealMsg.addAlertMsg(4);
        if (paramByte == 4) {}
        for (b1 = 1;; b1 = 0) {
          break;
        }
      }
      if (paramByte == 21)
      {
        bTestMsg = (byte)(bTestMsg | 0x1);
        paramByte = b2;
        break;
      }
      if (paramByte == 3)
      {
        bTestMsg = (byte)(bTestMsg | 0x2);
        int j = DefMsg.g_recdatas[7];
        int i = DefMsg.g_recdatas[8];
        DefMsg.g_recdatas[9] = ((byte)(j & 0x3));
        byte[] arrayOfByte = DefMsg.g_recdatas;
        if ((j & 0x10) == 16)
        {
          paramByte = 1;
          label237:
          arrayOfByte[10] = ((byte)paramByte);
          arrayOfByte = DefMsg.g_recdatas;
          if ((j & 0x20) != 32) {
            break label591;
          }
          paramByte = 1;
          label259:
          arrayOfByte[11] = ((byte)paramByte);
          arrayOfByte = DefMsg.g_recdatas;
          if ((j & 0x40) != 64) {
            break label596;
          }
          paramByte = 1;
          label281:
          arrayOfByte[12] = ((byte)paramByte);
          arrayOfByte = DefMsg.g_recdatas;
          if ((i & 0x1) != 1) {
            break label601;
          }
          paramByte = 1;
          label301:
          arrayOfByte[13] = ((byte)paramByte);
          arrayOfByte = DefMsg.g_recdatas;
          if ((i & 0x2) != 2) {
            break label606;
          }
          paramByte = 1;
          label321:
          arrayOfByte[14] = ((byte)paramByte);
          arrayOfByte = DefMsg.g_recdatas;
          if ((i & 0x4) != 4) {
            break label611;
          }
          paramByte = 1;
          label341:
          arrayOfByte[15] = ((byte)paramByte);
          arrayOfByte = DefMsg.g_recdatas;
          if ((i & 0x20) != 32) {
            break label616;
          }
          paramByte = 1;
          label363:
          arrayOfByte[16] = ((byte)paramByte);
          arrayOfByte = DefMsg.g_datas;
          if ((i & 0x40) != 64) {
            break label621;
          }
          paramByte = 1;
          label385:
          arrayOfByte[17] = ((byte)paramByte);
          DefMsg.g_datas[9] = ((byte)(j & 0x3));
          arrayOfByte = DefMsg.g_datas;
          if ((j & 0x10) != 16) {
            break label626;
          }
          paramByte = 1;
          label418:
          arrayOfByte[10] = ((byte)paramByte);
          arrayOfByte = DefMsg.g_datas;
          if ((j & 0x20) != 32) {
            break label631;
          }
          paramByte = 1;
          label440:
          arrayOfByte[11] = ((byte)paramByte);
          arrayOfByte = DefMsg.g_datas;
          if ((j & 0x40) != 64) {
            break label636;
          }
          paramByte = 1;
          label462:
          arrayOfByte[12] = ((byte)paramByte);
          arrayOfByte = DefMsg.g_datas;
          if ((i & 0x1) != 1) {
            break label641;
          }
          paramByte = 1;
          label482:
          arrayOfByte[13] = ((byte)paramByte);
          arrayOfByte = DefMsg.g_datas;
          if ((i & 0x2) != 2) {
            break label646;
          }
          paramByte = 1;
          label502:
          arrayOfByte[14] = ((byte)paramByte);
          arrayOfByte = DefMsg.g_datas;
          if ((i & 0x4) != 4) {
            break label651;
          }
          paramByte = 1;
          label522:
          arrayOfByte[15] = ((byte)paramByte);
          arrayOfByte = DefMsg.g_datas;
          if ((i & 0x20) != 32) {
            break label656;
          }
          paramByte = 1;
          label544:
          arrayOfByte[16] = ((byte)paramByte);
          arrayOfByte = DefMsg.g_datas;
          if ((i & 0x40) != 64) {
            break label661;
          }
        }
        label591:
        label596:
        label601:
        label606:
        label611:
        label616:
        label621:
        label626:
        label631:
        label636:
        label641:
        label646:
        label651:
        label656:
        label661:
        for (paramByte = 1;; paramByte = 0)
        {
          arrayOfByte[17] = ((byte)paramByte);
          AppDealWifi.LogMsg("get KO_WF_REMOTE_SECURTY_PRSNT_INFO_EVR");
          testRemoteContrl();
          paramByte = b2;
          break;
          paramByte = 0;
          break label237;
          paramByte = 0;
          break label259;
          paramByte = 0;
          break label281;
          paramByte = 0;
          break label301;
          paramByte = 0;
          break label321;
          paramByte = 0;
          break label341;
          paramByte = 0;
          break label363;
          paramByte = 0;
          break label385;
          paramByte = 0;
          break label418;
          paramByte = 0;
          break label440;
          paramByte = 0;
          break label462;
          paramByte = 0;
          break label482;
          paramByte = 0;
          break label502;
          paramByte = 0;
          break label522;
          paramByte = 0;
          break label544;
        }
      }
      if (paramByte == 42)
      {
        bTestMsg = (byte)(bTestMsg | 0x4);
        paramByte = DealMsg.addAlertMsg(6);
        if (paramByte == 6) {}
        for (b1 = 1;; b1 = 0) {
          break;
        }
      }
      b1 = -1;
      continue;
      label708:
      if (bTestMsg == 1) {
        testMsg(false);
      }
      bTestMsg = 0;
    }
  }
  
  private static void showNAVIWnd(boolean paramBoolean)
  {
    if (paramBoolean) {
      BroadcastMsgAction(10, 0);
    }
  }
  
  public static ArrayList<CmdFindStruct> splitCmds(byte[] paramArrayOfByte, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    boolean bool = showErrBar;
    showErrBar = false;
    for (CmdFindStruct localCmdFindStruct = FindFirstValidRecvEVRMsg(paramArrayOfByte, 0, paramInt);; localCmdFindStruct = FindFirstValidRecvEVRMsg(paramArrayOfByte, localCmdFindStruct.findPos + localCmdFindStruct.len, paramInt))
    {
      if (localCmdFindStruct == null)
      {
        if ((!showErrBar) && (localArrayList.size() == 0)) {
          showErrBar = bool;
        }
        showErrBarStatusChanged = showErrBar ^ bool;
        return localArrayList;
      }
      localArrayList.add(localCmdFindStruct);
    }
  }
  
  public static void testECUDone()
  {
    if (waitECUFunc > 0) {
      reportECUDone();
    }
  }
  
  static void testEcuCommEvr()
  {
    if (DefMsg.g_recdatas['Â'] == 1)
    {
      AppDealWifi.LogMsg("ECU_COMM_EVR Error!!!");
      BroadcastMsgAction(1, 0);
    }
  }
  
  static void testMsg(boolean paramBoolean)
  {
    boolean bool;
    if (!noVINbeforREG)
    {
      if (DealMsg.g_VINNum[0] == 0)
      {
        bool = true;
        noVINbeforREG = bool;
      }
    }
    else
    {
      if ((DealMsg.g_VINNum[0] == 0) || (DealMsg.addAlertMsg(5) != 5)) {
        break label57;
      }
      BroadcastMsgAction(0, 5);
      DefMsg.memsetZero(DefMsg.g_recdatas, 40, 17);
    }
    for (;;)
    {
      return;
      bool = false;
      break;
      label57:
      if ((DefMsg.strlen(DealMsg.g_VINNum, 0, 17) == 0) || (paramBoolean))
      {
        int i = DefMsg.strlen(DefMsg.g_recdatas, 40, 17);
        if ((DefMsg.g_recdatas[39] < 3) && (i >= 17))
        {
          if ((DefMsg.g_recdatas[57] == 1) && (DefMsg.g_recdatas['´'] == 0))
          {
            sendAddToArray(CmdMake.KO_WF_REG_DISP_SP((byte)1));
            isRegistrationEnd = true;
          }
        }
        else
        {
          if (DefMsg.g_recdatas[39] == 3) {
            BroadcastMsgAction(0, 5);
          }
          DefMsg.memsetZero(DefMsg.g_recdatas, 40, 17);
        }
      }
      else
      {
        DealMsg.g_isDemo = false;
        DefMsg.memsetZero(DefMsg.g_recdatas, 40, 17);
        DealMsg.SaveAPPSet(myActivity.GetCurForegroundActivity());
      }
    }
  }
  
  static void testRemoteContrl()
  {
    AppDealWifi.LogMsg("testRemoteContrl");
    if (cannotDoRemote())
    {
      AppDealWifi.LogMsg("show error");
      BroadcastMsgAction(1, 0);
    }
  }
  
  public static void waitECUMessage(byte paramByte1, byte paramByte2)
  {
    waitECUFunc = paramByte1;
    waitECUValue = paramByte2;
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseOP\MsgReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */