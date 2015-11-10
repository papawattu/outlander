package com.inventec.iMobile.baseOP;

import com.inventec.iMobile.baseStruct.DealMsg;

public class DataSetter
{
  public static void g_datas(short paramShort, byte paramByte)
  {
    com.inventec.iMobile.baseStruct.DefMsg.g_datas[paramShort] = paramByte;
  }
  
  public static void g_datas(short paramShort, byte[] paramArrayOfByte, int paramInt)
  {
    int i = 0;
    for (;;)
    {
      if (i >= paramInt) {
        return;
      }
      com.inventec.iMobile.baseStruct.DefMsg.g_datas[paramShort] = paramArrayOfByte[i];
      i++;
      paramShort++;
    }
  }
  
  public static void g_datas_Day_PRESET_Hour(short paramShort, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = DataGetter.g_datas(paramShort, 5);
    DealMsg.SetHValue(paramInt1, (short)paramInt2, arrayOfByte);
    g_datas(paramShort, arrayOfByte, 5);
  }
  
  public static void g_datas_Day_PRESET_Minute(short paramShort, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = DataGetter.g_datas(paramShort, 3);
    DealMsg.SetMValue(paramInt1, (short)paramInt2, arrayOfByte);
    g_datas(paramShort, arrayOfByte, 3);
  }
  
  public static void g_datas_Day_PRESET_Mode(short paramShort, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = DataGetter.g_datas(paramShort, 2);
    DealMsg.SetPValue(paramInt1, (short)paramInt2, arrayOfByte);
    g_datas(paramShort, arrayOfByte, 2);
  }
  
  public static void g_datas_Day_PRESET_OnOff(short paramShort, int paramInt, boolean paramBoolean)
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = DataGetter.g_datas(paramShort);
    DealMsg.SetFValue(paramInt, paramBoolean, arrayOfByte);
    g_datas(paramShort, arrayOfByte[0]);
  }
  
  public static void g_recdatas(short paramShort, byte paramByte)
  {
    com.inventec.iMobile.baseStruct.DefMsg.g_recdatas[paramShort] = paramByte;
  }
  
  public static void g_recdatas(short paramShort, byte[] paramArrayOfByte, int paramInt)
  {
    int i = 0;
    for (;;)
    {
      if (i >= paramInt) {
        return;
      }
      com.inventec.iMobile.baseStruct.DefMsg.g_recdatas[paramShort] = paramArrayOfByte[i];
      i++;
      paramShort++;
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseOP\DataSetter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */