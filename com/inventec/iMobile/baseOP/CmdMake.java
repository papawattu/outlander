package com.inventec.iMobile.baseOP;

import com.inventec.iMobile.baseStruct.DefMsg;

public class CmdMake
{
  public static byte[] KO_COMMAND_CONFIRM_EX_SP(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    byte[] arrayOfByte = new byte[6];
    arrayOfByte[0] = ((byte)(paramByte1 << 4 | paramByte1 >> 4));
    arrayOfByte[1] = 4;
    arrayOfByte[2] = 1;
    arrayOfByte[3] = paramByte2;
    if (paramByte3 == -1) {
      arrayOfByte[4] = 0;
    }
    for (;;)
    {
      arrayOfByte[5] = DefMsg.calcCheckSum(arrayOfByte);
      return arrayOfByte;
      arrayOfByte[4] = paramByte3;
    }
  }
  
  public static byte[] KO_COMMAND_CONFIRM_SP(byte paramByte1, byte paramByte2)
  {
    byte[] arrayOfByte = new byte[6];
    arrayOfByte[0] = -10;
    arrayOfByte[1] = 4;
    arrayOfByte[2] = 1;
    arrayOfByte[3] = paramByte1;
    if (paramByte2 == -1) {
      arrayOfByte[4] = 0;
    }
    for (;;)
    {
      arrayOfByte[5] = DefMsg.calcCheckSum(arrayOfByte);
      return arrayOfByte;
      arrayOfByte[4] = paramByte2;
    }
  }
  
  public static byte[] KO_LONG_FRAME_COMMAND_CONFIRM_SP()
  {
    return null;
  }
  
  public static byte[] KO_WF_12BATT_SCH_SP(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    byte[] arrayOfByte = new byte[8];
    arrayOfByte[0] = -10;
    arrayOfByte[1] = 6;
    arrayOfByte[2] = 0;
    arrayOfByte[3] = 22;
    arrayOfByte[4] = paramByte1;
    arrayOfByte[5] = paramByte2;
    arrayOfByte[6] = paramByte3;
    arrayOfByte[7] = DefMsg.calcCheckSum(arrayOfByte);
    return arrayOfByte;
  }
  
  public static byte[] KO_WF_AC_ERROR_DISP_SP()
  {
    return makeOneCmd(, (byte)1);
  }
  
  public static byte[] KO_WF_AC_SCH_SP(byte paramByte)
  {
    byte[] arrayOfByte = new byte[20];
    arrayOfByte[0] = -10;
    arrayOfByte[1] = 18;
    arrayOfByte[2] = 0;
    arrayOfByte[3] = 2;
    DefMsg.memcpy(arrayOfByte, 4, DefMsg.g_recdatas, 119, 6);
    arrayOfByte[10] = paramByte;
    DefMsg.memcpy(arrayOfByte, 11, DefMsg.g_recdatas, 86, 8);
    arrayOfByte[19] = DefMsg.calcCheckSum(arrayOfByte);
    return arrayOfByte;
  }
  
  public static byte[] KO_WF_ANY_CONNECT_SP(byte paramByte)
  {
    return makeOneCmd(, paramByte);
  }
  
  public static byte[] KO_WF_BUP_ERROR_DISP_SP()
  {
    return makeOneCmd(, (byte)1);
  }
  
  public static byte[] KO_WF_CHG_ERROR_DISP_SP()
  {
    return makeOneCmd(, (byte)1);
  }
  
  public static byte[] KO_WF_CONNECT_INFO_SP(byte[] paramArrayOfByte, byte paramByte)
  {
    byte[] arrayOfByte = new byte[12];
    arrayOfByte[0] = -14;
    arrayOfByte[1] = 10;
    arrayOfByte[2] = 0;
    arrayOfByte[3] = 1;
    for (int i = 0;; i++)
    {
      if (i >= paramArrayOfByte.length)
      {
        arrayOfByte[10] = paramByte;
        arrayOfByte[11] = DefMsg.calcCheckSum(arrayOfByte);
        return arrayOfByte;
      }
      arrayOfByte[(i + 4)] = paramArrayOfByte[i];
    }
  }
  
  public static byte[] KO_WF_DUMMY_SP(byte paramByte)
  {
    byte[] arrayOfByte = new byte[6];
    arrayOfByte[0] = -7;
    arrayOfByte[1] = 4;
    arrayOfByte[2] = 0;
    arrayOfByte[3] = paramByte;
    arrayOfByte[4] = 0;
    arrayOfByte[5] = DefMsg.calcCheckSum(arrayOfByte);
    return arrayOfByte;
  }
  
  public static byte[] KO_WF_D_LOCK_RQ_SP(byte paramByte)
  {
    return makeOneCmd(, paramByte);
  }
  
  public static byte[] KO_WF_EV_UPDATE_SP()
  {
    AppDealWifi.LogMsg("Send Update!");
    return makeOneCmd((byte)6, (byte)3);
  }
  
  public static byte[] KO_WF_FN_SEL_ADJ_VAL_R_SP(byte paramByte1, byte paramByte2)
  {
    byte[] arrayOfByte = new byte[7];
    arrayOfByte[0] = -10;
    arrayOfByte[1] = 5;
    arrayOfByte[2] = 0;
    arrayOfByte[3] = 15;
    arrayOfByte[4] = paramByte1;
    arrayOfByte[5] = paramByte2;
    arrayOfByte[6] = DefMsg.calcCheckSum(arrayOfByte);
    return arrayOfByte;
  }
  
  public static byte[] KO_WF_H_LAMP_CONT_SP(byte paramByte)
  {
    return makeOneCmd(, paramByte);
  }
  
  public static byte[] KO_WF_INIT_RQ_SP()
  {
    return makeOneCmd(, (byte)1);
  }
  
  public static byte[] KO_WF_MANUAL_AC_ON_RQ_SP(byte paramByte)
  {
    return makeOneCmd(, paramByte);
  }
  
  public static byte[] KO_WF_P_ALARM_RQ_SP(byte paramByte)
  {
    return makeOneCmd(, paramByte);
  }
  
  public static byte[] KO_WF_P_LAMP_CONT_SP(byte paramByte)
  {
    return makeOneCmd(, paramByte);
  }
  
  public static byte[] KO_WF_REG_DISP_SP(byte paramByte)
  {
    return makeOneCmd(, paramByte);
  }
  
  public static byte[] KO_WF_R_CUSTOM_SP()
  {
    return makeOneCmd(, (byte)0);
  }
  
  public static byte[] KO_WF_R_HORN_CONT_SP(byte paramByte)
  {
    return makeOneCmd(, paramByte);
  }
  
  public static byte[] KO_WF_SSID_CHANGE_SP(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[37];
    arrayOfByte[0] = -10;
    arrayOfByte[1] = 35;
    arrayOfByte[2] = 0;
    arrayOfByte[3] = 20;
    for (int i = 0;; i++)
    {
      if (i >= 32)
      {
        arrayOfByte[36] = DefMsg.calcCheckSum(arrayOfByte);
        return arrayOfByte;
      }
      arrayOfByte[(i + 4)] = paramArrayOfByte[i];
    }
  }
  
  public static byte[] KO_WF_THEFT_DISP_SP()
  {
    return makeOneCmd(, (byte)1);
  }
  
  public static byte[] KO_WF_TM_AC_ON_RQ_SP(byte paramByte)
  {
    return makeOneCmd(, paramByte);
  }
  
  public static byte[] KO_WF_TM_CHG_ON_RQ_SP(byte paramByte)
  {
    return makeOneCmd(, paramByte);
  }
  
  public static byte[] makeOneCmd(byte paramByte1, byte paramByte2)
  {
    byte[] arrayOfByte = new byte[6];
    arrayOfByte[0] = -10;
    arrayOfByte[1] = 4;
    arrayOfByte[2] = 0;
    arrayOfByte[3] = paramByte1;
    arrayOfByte[4] = paramByte2;
    arrayOfByte[5] = DefMsg.calcCheckSum(arrayOfByte);
    return arrayOfByte;
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseOP\CmdMake.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */