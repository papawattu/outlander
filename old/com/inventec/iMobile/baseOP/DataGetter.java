package com.inventec.iMobile.baseOP;

import com.inventec.iMobile.baseStruct.DealMsg;

public class DataGetter
{
  public static boolean canDealTheftAlerm()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (g_FUNC(23, 0) == 3)
    {
      bool1 = bool2;
      if (g_FUNC(23, 1) == 1) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static byte g_FUNC(int paramInt1, int paramInt2)
  {
    return DealMsg.g_FUNC[paramInt1][paramInt2];
  }
  
  public static boolean g_FUNC_CanShow(int paramInt)
  {
    return DealMsg.isFuncCanShow(paramInt);
  }
  
  public static int g_FUNC_CurSel(int paramInt)
  {
    return DealMsg.g_FUNC[paramInt][1];
  }
  
  public static boolean g_FUNC_Sel_CanShow(int paramInt1, int paramInt2)
  {
    boolean bool = true;
    if (DealMsg.GetBitVal(paramInt2, 1, DealMsg.g_FUNC[paramInt1][0]) != 0) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public static byte g_datas(short paramShort)
  {
    return com.inventec.iMobile.baseStruct.DefMsg.g_datas[paramShort];
  }
  
  public static byte[] g_datas(short paramShort, int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    for (int i = 0;; i++)
    {
      if (i >= paramInt) {
        return arrayOfByte;
      }
      arrayOfByte[i] = com.inventec.iMobile.baseStruct.DefMsg.g_datas[(paramShort + i)];
    }
  }
  
  public static int g_datas_Day_PRESET_Hour(short paramShort, int paramInt)
  {
    return DealMsg.GetHValue(paramInt, g_datas(paramShort, 5));
  }
  
  public static int g_datas_Day_PRESET_Minute(short paramShort, int paramInt)
  {
    return DealMsg.GetMValue(paramInt, g_datas(paramShort, 3));
  }
  
  public static byte[] g_recdatas(short paramShort, int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    for (int i = 0;; i++)
    {
      if (i >= paramInt) {
        return arrayOfByte;
      }
      arrayOfByte[i] = com.inventec.iMobile.baseStruct.DefMsg.g_recdatas[(paramShort + i)];
    }
  }
  
  public static int g_recdatas_Day_PRESET_Hour(short paramShort, int paramInt)
  {
    return DealMsg.GetHValue(paramInt, g_recdatas(paramShort, 5));
  }
  
  public static int g_recdatas_Day_PRESET_Minute(short paramShort, int paramInt)
  {
    return DealMsg.GetMValue(paramInt, g_recdatas(paramShort, 3));
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseOP\DataGetter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */