package com.inventec.iMobile.baseStruct;

import android.content.Context;
import com.inventec.iMobile.baseOP.AppDealWifi;
import com.inventec.iMobile.baseOP.CmdMake;
import com.inventec.iMobile.baseOP.DataGetter;
import com.inventec.iMobile.baseOP.DealFile;
import com.inventec.iMobile.baseOP.MsgReceiver;
import java.util.Calendar;

public class DefMsg
{
  public static final byte ACK_TYPE = 1;
  public static final short AC_ERROR_STAT = 23;
  public static final short AC_MODE_HOT_STAT = 98;
  public static final short AC_PRE_MD = 119;
  public static final short AC_PRE_SH = 121;
  public static final short AC_PRE_SM = 123;
  public static final short AC_SCH_SH = 86;
  public static final short AC_SCH_SM = 91;
  public static final short AC_STAT = 85;
  public static final short ANY_CONNECT = 144;
  public static final short BATTERY_CHRG = 16;
  public static final short BATT_CHG = 142;
  public static final short BATT_HEAT_TYPE = 1;
  public static final byte BATT_HEAT_TYPE_A = 0;
  public static final byte BATT_HEAT_TYPE_B = 1;
  public static final byte BATT_HEAT_TYPE_NOT_PRESENT = 2;
  public static final byte BATT_HEAT_TYPE_SNA = 3;
  public static final short BATT_LEVEL = 99;
  public static final short BATT_LVL_WRN_ON = 100;
  public static final short BATT_SH = 140;
  public static final short BATT_SM = 141;
  public static final short BAT_TEMP_CONT_MODE = 3;
  public static final short BCM_DRV_AJAR = 133;
  public static final short BCM_PSG_AJAR = 134;
  public static final short BUP_ERROR_STAT = 31;
  public static final short CHG_ERROR_STAT = 21;
  public static final short CHG_GUN_STATUS = 2;
  public static final short CHG_PRE_FH = 114;
  public static final short CHG_PRE_FM = 116;
  public static final short CHG_PRE_MODE_STAT = 108;
  public static final short CHG_PRE_SH = 110;
  public static final short CHG_PRE_SM = 112;
  public static final short CHG_SCH_FH = 77;
  public static final short CHG_SCH_FM = 82;
  public static final short CHG_SCH_SH = 69;
  public static final short CHG_SCH_SM = 74;
  public static final short CHG_STAT = 104;
  public static final short CHG_TIME_MIN = 106;
  public static final byte COLD_INF_BATT_WARM_1 = 3;
  public static final byte COLD_INF_BATT_WARM_2 = 4;
  public static final byte COLD_INF_BATT_WARM_STP_1 = 5;
  public static final byte COLD_INF_BATT_WARM_STP_2 = 6;
  public static final byte COLD_INF_PLUGIN_1 = 1;
  public static final byte COLD_INF_PLUGIN_2 = 2;
  public static final short DATA_SIZE = 202;
  public static final short DATE_INFO_SYNC = 24;
  public static final short DOORLK_STAT = 131;
  public static final short DOOR_CONTROL = 11;
  public static final short D_DR_LOCK = 130;
  public static final short ECU_COMM = 194;
  public static final short ECU_CUSTOM = 60;
  public static final short ECU_NUM = 58;
  public static final short ECU_OPER_MODE = 5;
  public static final short ECU_STAT = 57;
  public static final short ECU_VERSION = 181;
  public static final short END_CHAR = 59;
  public static final short EVR_COLD_INFO = 4;
  public static final short EVR_PRE_OK_ON = 19;
  public static final short EVR_TIM_OK_ON = 18;
  public static final short FULL_CHG_STAT = 20;
  public static final byte Friday = 5;
  public static final short HAZ_LAMP_STAT = 128;
  public static final byte HEAD_EVR2SP = 111;
  public static final byte HEAD_EVR2SP_DUMMY = -97;
  public static final byte HEAD_GS2SP = 47;
  public static final byte HEAD_GS2SP_REP = 127;
  public static final byte HEAD_GS2SP_REP_C = -97;
  public static final short HEAD_LMP_CONT = 14;
  public static final byte HEAD_SP2EVR = -10;
  public static final byte HEAD_SP2EVR_DUMMY = -7;
  public static final byte HEAD_SP2GS = -14;
  public static final byte HEAD_SP2GS_REP = -9;
  public static final byte HEAD_SP2GS_REP_C = -7;
  public static final short HL_SW_MODE = 102;
  public static final short HOOD_AJAR = 138;
  public static final short HORN_CONT = 15;
  public static final short IGN_STAT = 94;
  public static final short KEY_IN_IGN = 132;
  public static final byte KO_AC_MANUAL_SW_EVR = 26;
  public static final byte KO_AC_TIMER_SW_EVR = 25;
  public static final byte KO_TIMER_CHG_SW_EVR = 24;
  public static final byte KO_TM_AC_PRESET_SCH_INFO_REP_EVR = 34;
  public static final byte KO_TM_CHG_PRESET_SCH_UPDATE_EVR = 32;
  public static final byte KO_WF_12BATT_SCH_SP = 22;
  public static final byte KO_WF_12BATT_SCH_STAT_EVR = 37;
  public static final byte KO_WF_AC_ERROR_DISP_SP = 19;
  public static final byte KO_WF_AC_ERROR_STAT_EVR = 17;
  public static final byte KO_WF_AC_SCH_SP = 2;
  public static final byte KO_WF_ANY_CONNECT_EVR = 39;
  public static final byte KO_WF_ANY_CONNECT_SP = 17;
  public static final byte KO_WF_BATTERY_CHRG_EVR = 10;
  public static final byte KO_WF_BATT_LEVEL_INFO_REP_EVR = 29;
  public static final byte KO_WF_BUP_ERROR_DISP_SP = 7;
  public static final byte KO_WF_BUP_ERROR_STAT_EVR = 19;
  public static final byte KO_WF_CHG_ERROR_DISP_SP = 18;
  public static final byte KO_WF_CHG_ERROR_STAT_EVR = 15;
  public static final byte KO_WF_CHG_GUN_STATUS_EVR = 2;
  public static final byte KO_WF_CHG_TYPE_INFO_REP_EVR = 30;
  public static final byte KO_WF_CONNECT_INFO_GS_SP = 1;
  public static final byte KO_WF_DATE_INFO_SYNC_EVR = 18;
  public static final byte KO_WF_DATE_INFO_SYNC_SP = 5;
  public static final byte KO_WF_DOOR_CONTROL_EVR = 5;
  public static final byte KO_WF_DOOR_STATUS_INFO_REP_EVR = 36;
  public static final byte KO_WF_D_LOCK_RQ_SP = 13;
  public static final byte KO_WF_ECU_COMM_EVR = 44;
  public static final byte KO_WF_ECU_CUSTOM_INFO_REP_EVR = 22;
  public static final byte KO_WF_ECU_VERSION_EVR = 43;
  public static final byte KO_WF_EVR_PRE_OK_ON_EVR = 13;
  public static final byte KO_WF_EVR_TIM_OK_ON_EVR = 12;
  public static final byte KO_WF_EV_UPDATE_SP = 6;
  public static final byte KO_WF_FN_SEL_ADJ_VAL_R_SP = 15;
  public static final byte KO_WF_FULL_CHG_STAT_EVR = 14;
  public static final byte KO_WF_HEAD_LMP_CONT_EVR = 8;
  public static final byte KO_WF_HORN_CONT_EVR = 9;
  public static final byte KO_WF_H_LAMP_CONT_SP = 10;
  public static final byte KO_WF_INIT_RQ_SP = 21;
  public static final byte KO_WF_LAMP_STATUS_INFO_REP_EVR = 35;
  public static final byte KO_WF_MANUAL_AC_ON_RQ_SP = 4;
  public static final byte KO_WF_MAX_CMD_EVR = 45;
  public static final byte KO_WF_MAX_CMD_SP = 24;
  public static final byte KO_WF_NAV_PRESENT_EVR = 23;
  public static final byte KO_WF_NOP_CMD_EVR = 0;
  public static final byte KO_WF_NOP_CMD_GS_SP = 0;
  public static final byte KO_WF_NOP_CMD_SP = 0;
  public static final byte KO_WF_OBCHG_OK_ON_INFO_REP_EVR = 31;
  public static final byte KO_WF_OPTION_HTR_PRSNT_EVR = 1;
  public static final byte KO_WF_PANIC_ALARM_EVR = 6;
  public static final byte KO_WF_POSITION_LMP_CONT_EVR = 7;
  public static final byte KO_WF_PRE_AC_CONT_EVR = 11;
  public static final byte KO_WF_PRE_AC_STAT_EVR = 16;
  public static final byte KO_WF_P_ALARM_RQ_SP = 9;
  public static final byte KO_WF_P_LAMP_CONT_SP = 11;
  public static final byte KO_WF_REGISTRATION_EVR = 42;
  public static final byte KO_WF_REG_DISP_SP = 16;
  public static final byte KO_WF_REMOTE_SECURTY_PRSNT_INFO_EVR = 3;
  public static final byte KO_WF_R_CUSTOM_SP = 14;
  public static final byte KO_WF_R_HORN_CONT_SP = 12;
  public static final byte KO_WF_SSID_CHANGE_SP = 20;
  public static final byte KO_WF_SSID_EVR = 40;
  public static final byte KO_WF_THEFT_ALARM_EVR = 4;
  public static final byte KO_WF_THEFT_DISP_SP = 8;
  public static final byte KO_WF_THEFT_HISTORY_INFO_SYNC_EVR = 20;
  public static final byte KO_WF_TM_AC_ON_RQ_SP = 3;
  public static final byte KO_WF_TM_AC_STAT_INFO_REP_EVR = 28;
  public static final byte KO_WF_TM_CHG_MODE_STAT_INFO_REP_EVR = 27;
  public static final byte KO_WF_TM_CHG_ON_RQ_SP = 23;
  public static final byte KO_WF_TM_CHG_SP = 1;
  public static final byte KO_WF_TM_FUNC_DISP_EVR = 38;
  public static final byte KO_WF_TM_REG_POINT_STAT_INFO_REP_EVR = 33;
  public static final byte KO_WF_VIN_INFO_EVR = 21;
  public static final byte KO_WF_WCM_S_PRSNT_EVR = 41;
  public static final short LAST_UPDATE_DATE = 195;
  public static final short LHD_RHD = 179;
  public static final short LOBEAM_ON = 139;
  public static final short L_R_AJAR = 136;
  public static final short MANUAL_AC_ON = 95;
  public static final byte Monday = 1;
  public static final byte NOR_TYPE = 0;
  public static final short OBCHG_OK_ON = 105;
  public static final short OBC_IN_V_TYPE = 103;
  public static final short OPTION_HTR_PRSNT = 0;
  public static final short PANIC_ALARM = 12;
  public static final short POSITION_LMP_CONT = 13;
  public static final short PRE_AC_ACT = 125;
  public static final short PRE_AC_CONT = 17;
  public static final short PRE_AC_STAT = 22;
  public static final short P_ALARM_STAT = 127;
  public static final short REGISTRATION = 180;
  public static final short REMOTE = 6;
  public static final short REMOTE_SECURTY_PRSNT = 9;
  public static final short R_LAMP_STAT = 129;
  public static final short R_R_AJAR = 135;
  public static final short SLAMP_ACTV = 101;
  public static final short SSID_DATA = 145;
  public static final byte Saturday = 6;
  public static final byte Sunday = 0;
  public static final short THEFT_ALARM = 10;
  public static final short THEFT_ARM_STAT = 126;
  public static final short THEFT_DATE_INFO_SYNC = 32;
  public static final short THEFT_HISTORY = 38;
  public static final short TLG_AJAR = 137;
  public static final short TM_CHG_MODE_STAT = 96;
  public static final short TM_CHG_MODE_STAT_BAK = 97;
  public static final short TM_FUNC_DISP = 143;
  public static final short TM_REG_POINT_STAT = 118;
  public static final byte Thursday = 4;
  public static final byte Tuesday = 2;
  public static final short VEH_CODE0 = 7;
  public static final short VEH_CODE1 = 8;
  public static final short VIN_NUMBER = 40;
  public static final short WCM_S_PRSNT = 178;
  public static final short WF_NAV_PRESENT = 68;
  public static final short WF_VIN_INFO = 39;
  public static final byte WIFI_DATA_RESULT_ABNORMAL = 7;
  public static final byte WIFI_DATA_RESULT_CHECKSUM_ERROR = 2;
  public static final byte WIFI_DATA_RESULT_DATA_SAME = -1;
  public static final byte WIFI_DATA_RESULT_FRAME_ERROR = 6;
  public static final byte WIFI_DATA_RESULT_ID_ERROR = 5;
  public static final byte WIFI_DATA_RESULT_INVALID_PARAM = 1;
  public static final byte WIFI_DATA_RESULT_OPERATION_ERROR = 4;
  public static final byte WIFI_DATA_RESULT_STATUS_ERROR = 3;
  public static final byte WIFI_DATA_RESULT_SUCCESS = 0;
  public static final byte Wednesday = 3;
  public static byte[] g_ACSendBuf;
  public static byte[] g_CHGSendBuf;
  public static byte[] g_datas = new byte['Ò'];
  public static byte[] g_recdatas = new byte['Ò'];
  static boolean isFirstTestAC = true;
  static boolean isFirstTestCHG;
  
  static
  {
    g_CHGSendBuf = new byte[16];
    g_ACSendBuf = new byte[8];
    isFirstTestCHG = true;
  }
  
  static boolean ACSCHValueRight()
  {
    for (int i = 0;; i++)
    {
      if (i >= 7) {}
      for (boolean bool = false;; bool = true)
      {
        return bool;
        int j = DataGetter.g_recdatas_Day_PRESET_Hour((short)86, i);
        int k = DataGetter.g_recdatas_Day_PRESET_Minute((short)91, i);
        if ((j > 23) || (k > 5)) {
          break;
        }
      }
    }
  }
  
  static boolean CHGSCHValueRight()
  {
    for (int i = 0;; i++)
    {
      if (i >= 7) {}
      for (boolean bool = false;; bool = true)
      {
        return bool;
        int m = DataGetter.g_recdatas_Day_PRESET_Hour((short)69, i);
        int k = DataGetter.g_recdatas_Day_PRESET_Minute((short)74, i);
        int j = DataGetter.g_recdatas_Day_PRESET_Hour((short)77, i);
        int n = DataGetter.g_recdatas_Day_PRESET_Minute((short)82, i);
        if ((m > 23) || (k > 5) || (j > 23) || (n > 5)) {
          break;
        }
      }
    }
  }
  
  public static byte calcCheckSum(byte[] paramArrayOfByte)
  {
    byte b = 0;
    int j = getDataLength(paramArrayOfByte);
    for (int i = 0;; i++)
    {
      if (i >= j - 1) {
        return b;
      }
      b = (byte)(paramArrayOfByte[i] + b);
    }
  }
  
  public static byte calcCheckSum_Complex(byte[] paramArrayOfByte, int paramInt)
  {
    byte b = 0;
    int j = getDataLength_Complex(paramArrayOfByte, paramInt);
    for (int i = 0;; i++)
    {
      if (i >= j - 1) {
        return b;
      }
      b = (byte)(paramArrayOfByte[(paramInt + i)] + b);
    }
  }
  
  public static void copyACSend()
  {
    memcpy(g_ACSendBuf, 0, g_recdatas, 86, 8);
  }
  
  public static void copyCHGSend()
  {
    memcpy(g_CHGSendBuf, 0, g_recdatas, 69, 16);
  }
  
  public static void doTestACChange()
  {
    if ((g_recdatas[98] & 0xF) == 0) {
      g_recdatas[98] = g_datas[98];
    }
    if (isFirstTestAC)
    {
      isFirstTestAC = false;
      copyACSend();
    }
    byte b;
    if ((!memcmp(g_datas, 86, g_recdatas, 86, 8)) || (!memcmp(g_ACSendBuf, 0, g_recdatas, 86, 8)))
    {
      copyACSend();
      b = 0;
      if (!ACSCHValueRight()) {
        break label128;
      }
      if (g_recdatas[85] != 2) {
        b = 2;
      }
    }
    for (;;)
    {
      if (b != 0)
      {
        MsgReceiver.sendAddToArray(CmdMake.KO_WF_TM_AC_ON_RQ_SP(b));
        if (DealMsg.g_isDemo)
        {
          g_datas[85] = b;
          g_recdatas[85] = b;
        }
      }
      return;
      label128:
      if (g_recdatas[85] != 1) {
        b = 1;
      }
    }
  }
  
  public static void doTestCHGChange()
  {
    if (isFirstTestCHG)
    {
      isFirstTestCHG = false;
      copyCHGSend();
    }
    byte b1;
    if (!memcmp(g_CHGSendBuf, 0, g_recdatas, 69, 16))
    {
      copyCHGSend();
      b1 = 0;
      if (!CHGSCHValueRight()) {
        break label100;
      }
      if (g_recdatas[96] < 2)
      {
        byte b2 = g_recdatas[97];
        b1 = b2;
        if (b2 < 2) {
          b1 = 2;
        }
      }
    }
    for (;;)
    {
      if (b1 != 0)
      {
        MsgReceiver.sendAddToArray(CmdMake.KO_WF_TM_CHG_ON_RQ_SP(b1));
        if (DealMsg.g_isDemo)
        {
          g_datas[96] = b1;
          g_recdatas[96] = b1;
        }
      }
      return;
      label100:
      if (g_recdatas[96] != 1) {
        b1 = 1;
      }
    }
  }
  
  public static void doTestCHGMode()
  {
    if (g_recdatas[96] > 1) {
      g_recdatas[97] = g_recdatas[96];
    }
  }
  
  public static recvDataStruct getDataLenPosBymsgID(byte paramByte)
  {
    byte b = recvDataStruct.recvDataDef.length;
    Object localObject;
    if ((paramByte >= b) || ((paramByte < 0) && (paramByte != -64)))
    {
      localObject = null;
      return (recvDataStruct)localObject;
    }
    if (paramByte == -64) {}
    for (recvDataStruct localrecvDataStruct = recvDataStruct.recvDataDef[(b - 1)];; localrecvDataStruct = recvDataStruct.recvDataDef[paramByte])
    {
      localObject = localrecvDataStruct;
      if (localrecvDataStruct.msgID == paramByte) {
        break;
      }
      localObject = null;
      break;
    }
  }
  
  private static byte getDataLength(byte[] paramArrayOfByte)
  {
    return (byte)(paramArrayOfByte[1] + 2);
  }
  
  private static byte getDataLength_Complex(byte[] paramArrayOfByte, int paramInt)
  {
    return (byte)(paramArrayOfByte[(paramInt + 1)] + 2);
  }
  
  public static void loadAll(Context paramContext)
  {
    boolean bool2 = true;
    byte[] arrayOfByte = new byte[26];
    DealFile.loaddata(paramContext, "defmsg.sav", arrayOfByte);
    memcpy(g_CHGSendBuf, 0, arrayOfByte, 0, 16);
    memcpy(g_ACSendBuf, 0, arrayOfByte, 16, 8);
    if (arrayOfByte[24] == 1)
    {
      bool1 = true;
      isFirstTestCHG = bool1;
      if (arrayOfByte[25] != 1) {
        break label101;
      }
    }
    label101:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      isFirstTestAC = bool1;
      DealFile.loaddata(paramContext, "g_datas.sav", g_datas);
      DealFile.loaddata(paramContext, "g_recdatas.sav", g_recdatas);
      AppDealWifi.LogMsg("load all message");
      return;
      bool1 = false;
      break;
    }
  }
  
  public static boolean memcmp(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3)
  {
    for (int i = 0;; i++)
    {
      if (i >= paramInt3) {}
      for (boolean bool = true;; bool = false)
      {
        return bool;
        if (paramArrayOfByte1[(paramInt1 + i)] == paramArrayOfByte2[(paramInt2 + i)]) {
          break;
        }
      }
    }
  }
  
  public static void memcpy(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3)
  {
    for (int i = 0;; i++)
    {
      if (i >= paramInt3) {
        return;
      }
      paramArrayOfByte1[(paramInt1 + i)] = paramArrayOfByte2[(paramInt2 + i)];
    }
  }
  
  public static void memsetZero(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    for (int i = 0;; i++)
    {
      if (i >= paramInt2) {
        return;
      }
      paramArrayOfByte[(paramInt1 + i)] = 0;
    }
  }
  
  public static void saveAll(Context paramContext)
  {
    byte[] arrayOfByte = new byte[26];
    memcpy(arrayOfByte, 0, g_CHGSendBuf, 0, 16);
    memcpy(arrayOfByte, 16, g_ACSendBuf, 0, 8);
    if (isFirstTestCHG)
    {
      i = 1;
      arrayOfByte[24] = ((byte)i);
      if (!isFirstTestAC) {
        break label137;
      }
    }
    label137:
    for (int i = 1;; i = 0)
    {
      arrayOfByte[25] = ((byte)i);
      DealFile.savedata(paramContext, "defmsg.sav", arrayOfByte);
      DealFile.savedata(paramContext, "g_datas.sav", g_datas);
      AppDealWifi.LogMsg("save all message");
      DealFile.savedata(paramContext, "g_recdatas.sav", g_recdatas);
      DealFile.savedata(paramContext, "solidData.sav", new byte[] { g_recdatas[6], g_recdatas[9], g_recdatas[97] });
      return;
      i = 0;
      break;
    }
  }
  
  public static void setUpdateTime()
  {
    Calendar localCalendar = Calendar.getInstance();
    g_recdatas['Ã'] = ((byte)(localCalendar.get(1) - 2000));
    g_recdatas['Ä'] = ((byte)(localCalendar.get(2) + 1));
    g_recdatas['Å'] = ((byte)localCalendar.get(5));
    g_recdatas['Æ'] = ((byte)localCalendar.get(11));
    g_recdatas['Ç'] = ((byte)localCalendar.get(12));
    AppDealWifi.LogMsg("record update time:" + g_recdatas['Ã'] + " " + g_recdatas['Ä'] + " " + g_recdatas['Å'] + " " + g_recdatas['Æ'] + " " + g_recdatas['Ç']);
  }
  
  public static int strlen(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    for (int i = 0;; i++)
    {
      if (i >= paramInt2) {}
      while (paramArrayOfByte[(paramInt1 + i)] == 0) {
        return i;
      }
    }
  }
  
  public static void updateAll()
  {
    setUpdateTime();
    memcpy(g_datas, 0, g_recdatas, 0, 202);
    AppDealWifi.LogMsg("updateAll!!!!!");
    int i = 0;
    if (i >= 61) {
      return;
    }
    for (int j = 0;; j++)
    {
      if (j >= 2)
      {
        i++;
        break;
      }
      DealMsg.g_FUNC[i][j] = DealMsg.g_recvFUNC[i][j];
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseStruct\DefMsg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */