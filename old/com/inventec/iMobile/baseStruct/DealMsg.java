package com.inventec.iMobile.baseStruct;

import android.content.Context;
import com.inventec.iMobile.baseOP.AppDealWifi;
import com.inventec.iMobile.baseOP.DealFile;
import java.lang.reflect.Array;

public class DealMsg
{
  public static final int FULL_PASSWORD_SIZE = 6;
  public static final int FUNC_LIST_SIZE = 9;
  public static final int GROUP_LIST_SIZE = 10;
  public static final int MAX_EUC_NUMBER = 61;
  public static final int MAX_PASSWORD_SIZE = 5;
  public static final int MAX_SSIDPwd_SIZE = 129;
  public static final int MAX_SSID_SIZE = 33;
  public static final int MAX_THEFT_HISTORY = 20;
  public static final int PASSWORD_SIZE = 4;
  public static final int SSIDPwd_SIZE = 128;
  public static final int SSID_SIZE = 32;
  public static final int THEFT_HISTORY_LEN = 7;
  public static final int VALUE_LIST_SIZE = 7;
  public static final int VIN_NUMBER_SIZE = 20;
  public static final int VIN_SIZE = 17;
  public static String amStr;
  static final int[] bitsDBeginPos;
  static final int[] bitsPBeginPos;
  static final int[] bitsPmHBeginPos;
  static final int[] bitsPmMBeginPos;
  static final int[] bitsTBeginPos;
  static final int[] byteDBeginPos;
  static final int[] bytePBeginPos;
  static final int[] bytePmHBeginPos;
  static final int[] bytePmMBeginPos;
  static final int[] byteTBeginPos;
  public static byte[][] errorOn;
  public static byte[][] g_FUNC;
  static final byte[][] g_GroupFUNCList;
  public static byte[] g_PASSWORD;
  public static byte[] g_SSID;
  public static byte[] g_SSIDPwd;
  public static byte[][] g_TheftHistory;
  public static byte[] g_VINNum = new byte[20];
  public static boolean g_isDemo;
  static final byte[] g_maxFUNC;
  public static byte[][] g_recvFUNC;
  public static String pmStr;
  public static byte[][] theftAlertOn;
  public static String timeSep;
  public static boolean useAMPM;
  static final short[] valMasks;
  
  static
  {
    g_PASSWORD = new byte[6];
    g_SSID = new byte[33];
    g_SSIDPwd = new byte[''];
    g_isDemo = true;
    useAMPM = false;
    g_TheftHistory = (byte[][])Array.newInstance(Byte.TYPE, new int[] { 20, 7 });
    errorOn = (byte[][])Array.newInstance(Byte.TYPE, new int[] { 7, 2 });
    theftAlertOn = (byte[][])Array.newInstance(Byte.TYPE, new int[] { 7, 7 });
    g_FUNC = (byte[][])Array.newInstance(Byte.TYPE, new int[] { 61, 3 });
    g_recvFUNC = (byte[][])Array.newInstance(Byte.TYPE, new int[] { 61, 3 });
    Object localObject = new byte[61];
    localObject[1] = 2;
    localObject[2] = 2;
    localObject[3] = 7;
    localObject[4] = 4;
    localObject[5] = 3;
    localObject[6] = 4;
    localObject[7] = 5;
    localObject[8] = 4;
    localObject[9] = 5;
    localObject[10] = 2;
    localObject[11] = 7;
    localObject[12] = 6;
    localObject[13] = 4;
    localObject[14] = 2;
    localObject[15] = 2;
    localObject[16] = 5;
    localObject[17] = 2;
    localObject[19] = 2;
    localObject[20] = 5;
    localObject[21] = 4;
    localObject[22] = 4;
    localObject[23] = 2;
    localObject[24] = 4;
    localObject[25] = 3;
    localObject[26] = 6;
    localObject[27] = 2;
    localObject[28] = 2;
    localObject[29] = 2;
    localObject[32] = 3;
    localObject[33] = 2;
    localObject[34] = 2;
    localObject[35] = 2;
    localObject[36] = 3;
    localObject[41] = 4;
    localObject[42] = 5;
    localObject[43] = 3;
    localObject[50] = 2;
    localObject[51] = 2;
    localObject[53] = 2;
    localObject[54] = 3;
    localObject[55] = 3;
    localObject[56] = 2;
    localObject[57] = 2;
    localObject[58] = 2;
    localObject[60] = 2;
    g_maxFUNC = (byte[])localObject;
    byte[] arrayOfByte7 = new byte[10];
    arrayOfByte7[0] = 3;
    arrayOfByte7[1] = 20;
    arrayOfByte7[2] = 28;
    arrayOfByte7[3] = 26;
    arrayOfByte7[4] = 19;
    byte[] arrayOfByte3 = new byte[10];
    arrayOfByte3[0] = 3;
    arrayOfByte3[1] = 20;
    arrayOfByte3[2] = 28;
    arrayOfByte3[3] = 26;
    arrayOfByte3[4] = 21;
    arrayOfByte3[5] = 32;
    arrayOfByte3[6] = 19;
    byte[] arrayOfByte2 = new byte[10];
    arrayOfByte2[0] = 4;
    arrayOfByte2[1] = 5;
    arrayOfByte2[2] = 15;
    arrayOfByte2[3] = 6;
    arrayOfByte2[4] = 29;
    byte[] arrayOfByte5 = new byte[10];
    arrayOfByte5[0] = 12;
    arrayOfByte5[1] = 9;
    arrayOfByte5[2] = 10;
    arrayOfByte5[3] = 42;
    arrayOfByte5[4] = 43;
    arrayOfByte5[5] = 13;
    arrayOfByte5[6] = 11;
    arrayOfByte5[7] = 7;
    byte[] arrayOfByte8 = new byte[10];
    arrayOfByte8[0] = 23;
    arrayOfByte8[1] = 41;
    arrayOfByte8[2] = 25;
    arrayOfByte8[3] = 27;
    byte[] arrayOfByte9 = new byte[10];
    arrayOfByte9[0] = 24;
    arrayOfByte9[1] = 34;
    arrayOfByte9[2] = 35;
    byte[] arrayOfByte6 = new byte[10];
    arrayOfByte6[0] = 1;
    arrayOfByte6[1] = 2;
    arrayOfByte6[2] = 14;
    byte[] arrayOfByte1 = new byte[10];
    arrayOfByte1[0] = 22;
    arrayOfByte1[1] = 17;
    arrayOfByte1[2] = 16;
    byte[] arrayOfByte4 = new byte[10];
    arrayOfByte4[0] = 50;
    arrayOfByte4[1] = 51;
    arrayOfByte4[2] = 53;
    arrayOfByte4[3] = 54;
    arrayOfByte4[4] = 55;
    arrayOfByte4[5] = 56;
    arrayOfByte4[6] = 57;
    arrayOfByte4[7] = 58;
    arrayOfByte4[8] = 60;
    localObject = new byte[10];
    localObject[0] = 8;
    localObject[1] = 33;
    localObject[2] = 36;
    g_GroupFUNCList = new byte[][] { arrayOfByte7, arrayOfByte3, arrayOfByte2, arrayOfByte5, arrayOfByte8, arrayOfByte9, arrayOfByte6, arrayOfByte1, arrayOfByte4, localObject };
    localObject = new short[9];
    localObject[1] = 1;
    localObject[2] = 3;
    localObject[3] = 7;
    localObject[4] = 15;
    localObject[5] = 31;
    localObject[6] = 63;
    localObject[7] = 127;
    localObject[8] = 255;
    valMasks = (short[])localObject;
    localObject = new int[7];
    localObject[1] = 5;
    localObject[2] = 2;
    localObject[3] = 7;
    localObject[4] = 4;
    localObject[5] = 1;
    localObject[6] = 6;
    bitsDBeginPos = (int[])localObject;
    localObject = new int[7];
    localObject[2] = 1;
    localObject[3] = 1;
    localObject[4] = 2;
    localObject[5] = 3;
    localObject[6] = 3;
    byteDBeginPos = (int[])localObject;
    localObject = new int[7];
    localObject[1] = 3;
    localObject[2] = 6;
    localObject[3] = 1;
    localObject[4] = 4;
    localObject[5] = 7;
    localObject[6] = 2;
    bitsTBeginPos = (int[])localObject;
    localObject = new int[7];
    localObject[3] = 1;
    localObject[4] = 1;
    localObject[5] = 1;
    localObject[6] = 2;
    byteTBeginPos = (int[])localObject;
    localObject = new int[4];
    localObject[2] = 5;
    localObject[3] = 2;
    bitsPmHBeginPos = (int[])localObject;
    localObject = new int[4];
    localObject[3] = 1;
    bytePmHBeginPos = (int[])localObject;
    localObject = new int[4];
    localObject[2] = 3;
    localObject[3] = 6;
    bitsPmMBeginPos = (int[])localObject;
    bytePmMBeginPos = new int[4];
    localObject = new int[7];
    localObject[1] = 2;
    localObject[2] = 4;
    localObject[3] = 6;
    localObject[5] = 2;
    localObject[6] = 4;
    bitsPBeginPos = (int[])localObject;
    localObject = new int[7];
    localObject[4] = 1;
    localObject[5] = 1;
    localObject[6] = 1;
    bytePBeginPos = (int[])localObject;
  }
  
  public static void AToB(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1, int paramInt1, int paramInt2)
  {
    int j = 0;
    int i = 0;
    if (i >= paramInt1) {
      return;
    }
    for (int k = 0;; k++)
    {
      if (k >= paramInt2)
      {
        i++;
        break;
      }
      paramArrayOfByte[j] = paramArrayOfByte1[i][k];
      j++;
    }
  }
  
  public static void BToA(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1, int paramInt1, int paramInt2)
  {
    int j = 0;
    int i = 0;
    if (i >= paramInt1) {
      return;
    }
    for (int k = 0;; k++)
    {
      if (k >= paramInt2)
      {
        i++;
        break;
      }
      paramArrayOfByte1[i][k] = paramArrayOfByte[j];
      j++;
    }
  }
  
  public static void BToIA(byte[] paramArrayOfByte, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    for (int i = 0;; i++)
    {
      if (i >= paramInt2) {
        return;
      }
      paramArrayOfInt[i] = ((int)BytesToData(paramArrayOfByte, paramInt1, 4));
      paramInt1 += 4;
    }
  }
  
  public static void BToIA2(byte[] paramArrayOfByte, int paramInt1, int[][] paramArrayOfInt, int paramInt2, int paramInt3)
  {
    int j = 0;
    int i = paramInt1;
    paramInt1 = j;
    if (paramInt1 >= paramInt2) {
      return;
    }
    for (j = 0;; j++)
    {
      if (j >= paramInt3)
      {
        paramInt1++;
        break;
      }
      paramArrayOfInt[paramInt1][j] = ((int)BytesToData(paramArrayOfByte, i, 4));
      i += 4;
    }
  }
  
  public static long BytesToData(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    long l = paramArrayOfByte[paramInt1];
    for (int i = 1;; i++)
    {
      if (i >= paramInt2) {
        return l;
      }
      l = (l << 8) + paramArrayOfByte[(paramInt1 + i)];
    }
  }
  
  public static void DataToBytes(long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    
    for (;;)
    {
      if (paramInt2 < 0) {
        return;
      }
      paramArrayOfByte[(paramInt1 + paramInt2)] = ((byte)(int)(0xFF & paramLong));
      paramLong >>= 8;
      paramInt2--;
    }
  }
  
  public static byte GetBitVal(int paramInt1, int paramInt2, byte paramByte)
  {
    paramInt1 = (byte)(paramByte >> paramInt1);
    return (byte)(valMasks[paramInt2] & paramInt1);
  }
  
  public static short GetBitVal(int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3)
  {
    int j = (short)((short)paramArrayOfByte[(paramInt3 + 0)] & 0xFF);
    int i = j;
    if (paramInt1 + paramInt2 >= 8) {
      i = (short)((short)((short)paramArrayOfByte[(paramInt3 + 1)] << 8) | j);
    }
    paramInt1 = (short)(i >> paramInt1);
    return (short)(valMasks[paramInt2] & paramInt1);
  }
  
  public static boolean GetFValue(int paramInt, byte paramByte)
  {
    if ((new byte[] { 1, 2, 4, 8, 16, 32, 64 }[paramInt] & paramByte) > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static byte[] GetGroupFuncList(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= 10)) {}
    for (byte[] arrayOfByte = null;; arrayOfByte = g_GroupFUNCList[paramInt]) {
      return arrayOfByte;
    }
  }
  
  public static short GetHValue(int paramInt, byte[] paramArrayOfByte)
  {
    return GetBitVal(bitsDBeginPos[paramInt], 5, paramArrayOfByte, byteDBeginPos[paramInt]);
  }
  
  public static short GetMValue(int paramInt, byte[] paramArrayOfByte)
  {
    return GetBitVal(bitsTBeginPos[paramInt], 3, paramArrayOfByte, byteTBeginPos[paramInt]);
  }
  
  public static short GetPValue(int paramInt, byte[] paramArrayOfByte)
  {
    return GetBitVal(bitsPBeginPos[paramInt], 2, paramArrayOfByte, bytePBeginPos[paramInt]);
  }
  
  public static short GetPmHValue(int paramInt, byte[] paramArrayOfByte)
  {
    return GetBitVal(bitsPmHBeginPos[paramInt], 5, paramArrayOfByte, bytePmHBeginPos[paramInt]);
  }
  
  public static short GetPmMValue(int paramInt, byte[] paramArrayOfByte)
  {
    return GetBitVal(bitsPmMBeginPos[paramInt], 3, paramArrayOfByte, bytePmMBeginPos[paramInt]);
  }
  
  public static void IA2ToB(byte[] paramArrayOfByte, int paramInt1, int[][] paramArrayOfInt, int paramInt2, int paramInt3)
  {
    int j = 0;
    int i = paramInt1;
    paramInt1 = j;
    if (paramInt1 >= paramInt2) {
      return;
    }
    for (j = 0;; j++)
    {
      if (j >= paramInt3)
      {
        paramInt1++;
        break;
      }
      DataToBytes(paramArrayOfInt[paramInt1][j], paramArrayOfByte, i, 4);
      i += 4;
    }
  }
  
  public static void IAToB(byte[] paramArrayOfByte, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    for (int i = 0;; i++)
    {
      if (i >= paramInt2) {
        return;
      }
      DataToBytes(paramArrayOfInt[i], paramArrayOfByte, paramInt1, 4);
      paramInt1 += 4;
    }
  }
  
  public static void LoadAPPSet(Context paramContext)
  {
    byte[] arrayOfByte = new byte[3];
    useAMPM = paramContext.getString(2131165196).contains("P");
    amStr = paramContext.getString(2131165197);
    pmStr = paramContext.getString(2131165198);
    timeSep = paramContext.getString(2131165199);
    DealFile.loaddata(paramContext, "g_recdatas.sav", DefMsg.g_recdatas);
    AppDealWifi.LogMsg("load all message in LoadAPPSet");
    initDemoValue();
    DealFile.loaddata(paramContext, "g_VINNum.sav", g_VINNum);
    if ((g_VINNum[0] != 0) && (DealFile.loaddata(paramContext, "solidData.sav", arrayOfByte)))
    {
      int m = arrayOfByte[0];
      int k = arrayOfByte[1];
      int j = arrayOfByte[2];
      int i = j;
      if (j < 2) {
        i = 2;
      }
      DefMsg.g_recdatas[6] = m;
      DefMsg.g_recdatas[9] = k;
      DefMsg.g_recdatas[97] = i;
    }
    DealFile.loaddata(paramContext, "g_PASSWORD.sav", g_PASSWORD);
    DealFile.loaddata(paramContext, "g_SSID.sav", g_SSID);
    DealFile.loaddata(paramContext, "g_SSIDPwd.sav", g_SSIDPwd);
    loadArrays(paramContext, "g_TheftHistory.sav", g_TheftHistory, 20, 7);
  }
  
  public static void SaveAPPSet(Context paramContext)
  {
    if (g_isDemo) {}
    for (;;)
    {
      return;
      AppDealWifi.LogMsg("save all message in saveAPPSet");
      DealFile.savedata(paramContext, "g_recdatas.sav", DefMsg.g_recdatas);
      DealFile.savedata(paramContext, "solidData.sav", new byte[] { DefMsg.g_recdatas[6], DefMsg.g_recdatas[9], DefMsg.g_recdatas[97] });
    }
  }
  
  public static void SetBitVal(int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3, short paramShort)
  {
    paramShort = (short)(paramShort & 0xFF);
    short s2 = valMasks[paramInt2];
    int i = (short)((short)(paramShort & s2) << paramInt1);
    short s1 = (short)((short)paramArrayOfByte[paramInt3] & 0xFF);
    paramShort = s1;
    if (paramInt1 + paramInt2 >= 8) {
      paramShort = (short)((short)((short)paramArrayOfByte[(paramInt3 + 1)] << 8 & 0xFF00) | s1);
    }
    paramShort = (short)((short)(((short)(s2 << paramInt1) ^ 0xFFFFFFFF) & paramShort) | i);
    paramArrayOfByte[(paramInt3 + 0)] = ((byte)(paramShort & 0xFF));
    if (paramInt1 + paramInt2 >= 8) {
      paramArrayOfByte[(paramInt3 + 1)] = ((byte)(paramShort >> 8 & 0xFF));
    }
  }
  
  public static void SetFValue(int paramInt, boolean paramBoolean, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[7];
    byte[] tmp6_5 = arrayOfByte;
    tmp6_5[0] = 1;
    byte[] tmp11_6 = tmp6_5;
    tmp11_6[1] = 2;
    byte[] tmp16_11 = tmp11_6;
    tmp16_11[2] = 4;
    byte[] tmp21_16 = tmp16_11;
    tmp21_16[3] = 8;
    byte[] tmp26_21 = tmp21_16;
    tmp26_21[4] = 16;
    byte[] tmp31_26 = tmp26_21;
    tmp31_26[5] = 32;
    byte[] tmp36_31 = tmp31_26;
    tmp36_31[6] = 64;
    tmp36_31;
    if (paramBoolean) {
      paramArrayOfByte[0] = ((byte)(paramArrayOfByte[0] | arrayOfByte[paramInt]));
    }
    for (;;)
    {
      return;
      paramArrayOfByte[0] = ((byte)(paramArrayOfByte[0] & (arrayOfByte[paramInt] ^ 0xFFFFFFFF)));
    }
  }
  
  public static void SetHValue(int paramInt, short paramShort, byte[] paramArrayOfByte)
  {
    SetBitVal(bitsDBeginPos[paramInt], 5, paramArrayOfByte, byteDBeginPos[paramInt], paramShort);
  }
  
  public static void SetMValue(int paramInt, short paramShort, byte[] paramArrayOfByte)
  {
    SetBitVal(bitsTBeginPos[paramInt], 3, paramArrayOfByte, byteTBeginPos[paramInt], paramShort);
  }
  
  public static void SetPValue(int paramInt, short paramShort, byte[] paramArrayOfByte)
  {
    SetBitVal(bitsPBeginPos[paramInt], 2, paramArrayOfByte, bytePBeginPos[paramInt], paramShort);
  }
  
  public static void SetPmHValue(int paramInt, short paramShort, byte[] paramArrayOfByte)
  {
    SetBitVal(bitsPmHBeginPos[paramInt], 5, paramArrayOfByte, bytePmHBeginPos[paramInt], paramShort);
  }
  
  public static void SetPmMValue(int paramInt, short paramShort, byte[] paramArrayOfByte)
  {
    SetBitVal(bitsPmMBeginPos[paramInt], 3, paramArrayOfByte, bytePmMBeginPos[paramInt], paramShort);
  }
  
  public static boolean VINhasValue()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (DefMsg.strlen(g_VINNum, 0, 17) == 17)
    {
      bool1 = bool2;
      if (DefMsg.g_recdatas[39] < 3) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static int addAlertMsg(int paramInt)
  {
    int i = 1;
    int j = 0;
    boolean bool = VINhasValue();
    int k;
    if (paramInt == 1)
    {
      int m = DefMsg.g_recdatas[38];
      if ((bool) && (m > 0) && (m < 7))
      {
        j = 1;
        i = j;
        k = m;
        if (j != 0)
        {
          if ((DefMsg.g_recdatas[32] > 0) && (!DefMsg.memcmp(g_TheftHistory[0], 0, DefMsg.g_recdatas, 32, 7)))
          {
            i = 19;
            label81:
            if (i > 0) {
              break label172;
            }
            DefMsg.memcpy(g_TheftHistory[0], 0, DefMsg.g_recdatas, 32, 7);
          }
          DefMsg.memcpy(theftAlertOn[m], 0, DefMsg.g_recdatas, 32, 7);
          theftAlertOn[m][6] = 1;
          k = m;
          i = j;
        }
        label133:
        if (i == 0) {
          break label382;
        }
        errorOn[paramInt][0] = 1;
        errorOn[paramInt][1] = 0;
        i = paramInt;
        if (paramInt != 1) {}
      }
    }
    label172:
    label382:
    for (i = k + 10;; i = 0)
    {
      return i;
      j = 0;
      break;
      DefMsg.memcpy(g_TheftHistory[i], 0, g_TheftHistory[(i - 1)], 0, 7);
      i--;
      break label81;
      if (paramInt == 2)
      {
        if ((bool) && (DefMsg.g_recdatas[22] == 3)) {}
        for (i = 1;; i = 0)
        {
          k = j;
          break;
        }
      }
      if (paramInt == 3)
      {
        if ((bool) && (DefMsg.g_recdatas[104] == 3)) {}
        for (i = 1;; i = 0)
        {
          k = j;
          break;
        }
      }
      if (paramInt == 4)
      {
        if ((bool) && (DefMsg.g_recdatas[31] == 1)) {}
        for (i = 1;; i = 0)
        {
          k = j;
          break;
        }
      }
      if (paramInt == 5)
      {
        if ((DefMsg.g_recdatas[39] != 3) && ((!bool) || (DefMsg.g_recdatas[40] == 0) || (bytesCmp(g_VINNum, 0, DefMsg.g_recdatas, 40, 17)))) {}
        for (i = 0;; i = 1)
        {
          k = j;
          break;
        }
      }
      k = j;
      if (paramInt != 6) {
        break label133;
      }
      if (DefMsg.g_recdatas['´'] == 1) {}
      for (i = 1;; i = 0)
      {
        k = j;
        break;
      }
    }
  }
  
  private static boolean bytesCmp(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3)
  {
    boolean bool = true;
    for (int i = 0;; i++)
    {
      if (i >= paramInt3) {}
      for (;;)
      {
        return bool;
        if (paramArrayOfByte1[(paramInt1 + i)] == paramArrayOfByte2[(paramInt2 + i)]) {
          break;
        }
        bool = false;
      }
    }
  }
  
  public static String getPassword()
  {
    String str = "";
    for (int i = 0;; i++)
    {
      if (i >= 4) {}
      int j;
      do
      {
        return str;
        j = g_PASSWORD[i];
      } while (j == 0);
      str = str + (char)j;
    }
  }
  
  public static byte getPasswordType()
  {
    return g_PASSWORD[5];
  }
  
  public static String getSSID()
  {
    String str = "";
    for (int i = 0;; i++)
    {
      if (i >= 33) {}
      int j;
      do
      {
        return str;
        j = g_SSID[i];
      } while (j == 0);
      str = str + (char)j;
    }
  }
  
  public static String getSSIDPwd()
  {
    String str = "";
    for (int i = 0;; i++)
    {
      if (i >= 129) {}
      int j;
      do
      {
        return str;
        j = g_SSIDPwd[i];
      } while (j == 0);
      str = str + (char)j;
    }
  }
  
  public static String getTheftDate(Context paramContext, byte[] paramArrayOfByte)
  {
    String str2 = "";
    int i = paramArrayOfByte[5];
    String str1 = str2;
    if (paramArrayOfByte[0] > 0)
    {
      str1 = str2;
      if (i < 7)
      {
        str1 = paramContext.getString(2131165488).replaceFirst("%t", getTimeString(paramArrayOfByte[3], paramArrayOfByte[4]));
        paramContext = getWeekString(paramContext, i);
        str1 = str1.replace("%y", paramArrayOfByte[0] + 2000).replace("%m", paramArrayOfByte[1]).replace("%d", paramArrayOfByte[2]).replace("%w", paramContext);
      }
    }
    return str1;
  }
  
  public static String getTimeString(int paramInt1, int paramInt2)
  {
    String str;
    if ((paramInt1 > 24) || (paramInt2 > 59)) {
      str = String.format("--%s--", new Object[] { timeSep });
    }
    for (;;)
    {
      return str;
      if (paramInt1 > 12)
      {
        int i = paramInt1;
        if (useAMPM) {
          i = paramInt1 - 12;
        }
        str = String.format("%d%s%02d", new Object[] { Integer.valueOf(i), timeSep, Integer.valueOf(paramInt2) }) + pmStr;
      }
      else
      {
        str = String.format("%d%s%02d", new Object[] { Integer.valueOf(paramInt1), timeSep, Integer.valueOf(paramInt2) }) + amStr;
      }
    }
  }
  
  public static String getVIN()
  {
    String str = "";
    for (int i = 0;; i++)
    {
      if (i >= 20) {}
      while (g_VINNum[i] == 0) {
        return str;
      }
      str = str + (char)g_VINNum[i];
    }
  }
  
  public static String getWeekString(Context paramContext, int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < 7)) {}
    for (paramContext = paramContext.getString(new int[] { 2131165344, 2131165338, 2131165339, 2131165340, 2131165341, 2131165342, 2131165343 }[paramInt]);; paramContext = "") {
      return paramContext;
    }
  }
  
  public static void initDemoValue()
  {
    DefMsg.memcpy(new byte[7], 0, DefMsg.g_recdatas, 195, 7);
    resetVector(DefMsg.g_recdatas, 202);
    DefMsg.g_recdatas[6] = 1;
    DefMsg.g_recdatas[9] = 1;
    DefMsg.g_recdatas[16] = 1;
    DefMsg.g_recdatas[68] = 1;
    DefMsg.g_recdatas[17] = 1;
    DefMsg.g_recdatas[0] = 1;
    DefMsg.g_recdatas[14] = 1;
    DefMsg.g_recdatas[13] = 1;
    DefMsg.g_recdatas[''] = 1;
    resetRecDatasTimer((short)69, 5);
    resetRecDatasTimer((short)74, 3);
    resetRecDatasTimer((short)77, 5);
    resetRecDatasTimer((short)82, 3);
    resetRecDatasTimer((short)110, 2);
    resetRecDatasTimer((short)112, 2);
    resetRecDatasTimer((short)114, 2);
    resetRecDatasTimer((short)116, 2);
    resetRecDatasTimer((short)86, 5);
    resetRecDatasTimer((short)91, 3);
    resetRecDatasTimer((short)121, 2);
    resetRecDatasTimer((short)123, 2);
    DefMsg.g_recdatas[106] = 112;
    DefMsg.g_recdatas[107] = 23;
    DefMsg.g_recdatas[118] = 1;
    DefMsg.g_recdatas[97] = 2;
    resetVector_D2(g_recvFUNC, 61, 3);
    for (int i = 0;; i++)
    {
      if (i >= 202)
      {
        i = 0;
        if (i < 61) {
          break;
        }
        return;
      }
      DefMsg.g_datas[i] = DefMsg.g_recdatas[i];
    }
    for (int j = 0;; j++)
    {
      if (j >= 3)
      {
        i++;
        break;
      }
      g_FUNC[i][j] = g_recvFUNC[i][j];
    }
  }
  
  public static boolean isFuncCanShow(int paramInt)
  {
    boolean bool = true;
    if ((paramInt < 0) || (paramInt >= 61)) {}
    for (bool = false;; bool = false)
    {
      int i;
      byte[] arrayOfByte;
      do
      {
        return bool;
        i = g_maxFUNC[paramInt];
        arrayOfByte = new byte[8];
        arrayOfByte[1] = 1;
        arrayOfByte[2] = 3;
        arrayOfByte[3] = 7;
        arrayOfByte[4] = 15;
        arrayOfByte[5] = 31;
        arrayOfByte[6] = 63;
        arrayOfByte[7] = Byte.MAX_VALUE;
      } while ((g_FUNC[paramInt][1] < i) && (g_FUNC[paramInt][0] != 0) && (g_FUNC[paramInt][0] <= arrayOfByte[i]));
    }
  }
  
  public static boolean isGroupCanShow(int paramInt)
  {
    int i = 1;
    int m = 0;
    byte[] arrayOfByte = GetGroupFuncList(paramInt);
    if (arrayOfByte == null) {
      return m;
    }
    m = 0;
    boolean bool1 = false;
    label22:
    label28:
    int k;
    if (bool1 > true)
    {
      k = m;
      if (paramInt == 0)
      {
        if (DefMsg.g_datas['²'] != 0) {
          break label110;
        }
        bool1 = true;
        label48:
        k = m & bool1;
      }
      m = k;
      if (paramInt == 1) {
        if (DefMsg.g_datas['²'] == 0) {
          break label115;
        }
      }
    }
    label110:
    label115:
    for (paramInt = i;; paramInt = 0)
    {
      int n = k & paramInt;
      break;
      int j = arrayOfByte[bool1];
      if (j == 0) {
        break label28;
      }
      boolean bool2;
      n |= isFuncCanShow(j);
      bool1++;
      break label22;
      bool1 = false;
      break label48;
    }
  }
  
  public static void loadAll(Context paramContext)
  {
    useAMPM = paramContext.getString(2131165196).contains("P");
    amStr = paramContext.getString(2131165197);
    pmStr = paramContext.getString(2131165198);
    timeSep = paramContext.getString(2131165199);
    DealFile.loaddata(paramContext, "g_VINNum.sav", g_VINNum);
    DealFile.loaddata(paramContext, "g_PASSWORD.sav", g_PASSWORD);
    DealFile.loaddata(paramContext, "g_SSID.sav", g_SSID);
    DealFile.loaddata(paramContext, "g_SSIDPwd.sav", g_SSIDPwd);
    loadArrays(paramContext, "g_TheftHistory.sav", g_TheftHistory, 20, 7);
    loadArrays(paramContext, "g_FUNC.sav", g_FUNC, 61, 2);
    loadArrays(paramContext, "g_recvFUNC.sav", g_recvFUNC, 61, 2);
    if (g_VINNum[0] != 0) {
      g_isDemo = false;
    }
  }
  
  static void loadArrays(Context paramContext, String paramString, byte[][] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[paramInt1 * paramInt2];
    if (DealFile.loaddata(paramContext, paramString, arrayOfByte)) {
      BToA(arrayOfByte, paramArrayOfByte, paramInt1, paramInt2);
    }
  }
  
  private static void resetRecDatasTimer(short paramShort, int paramInt)
  {
    for (int i = paramShort;; i++)
    {
      if (i >= paramShort + paramInt) {
        return;
      }
      DefMsg.g_recdatas[i] = -1;
    }
  }
  
  public static void resetVector(byte[] paramArrayOfByte, int paramInt)
  {
    for (int i = 0;; i++)
    {
      if (i >= paramInt) {
        return;
      }
      paramArrayOfByte[i] = 0;
    }
  }
  
  public static void resetVector_D2(byte[][] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    for (int i = 0;; i++)
    {
      if (i >= paramInt1) {
        return;
      }
      resetVector(paramArrayOfByte[i], paramInt2);
    }
  }
  
  public static void saveAll(Context paramContext)
  {
    DealFile.savedata(paramContext, "g_VINNum.sav", g_VINNum);
    DealFile.savedata(paramContext, "g_PASSWORD.sav", g_PASSWORD);
    DealFile.savedata(paramContext, "g_SSID.sav", g_SSID);
    DealFile.savedata(paramContext, "g_SSIDPwd.sav", g_SSIDPwd);
    saveArrays(paramContext, "g_TheftHistory.sav", g_TheftHistory, 20, 7);
    saveArrays(paramContext, "g_FUNC.sav", g_FUNC, 61, 2);
    saveArrays(paramContext, "g_recvFUNC.sav", g_recvFUNC, 61, 2);
  }
  
  static void saveArrays(Context paramContext, String paramString, byte[][] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[paramInt1 * paramInt2];
    AToB(arrayOfByte, paramArrayOfByte, paramInt1, paramInt2);
    DealFile.savedata(paramContext, paramString, arrayOfByte);
  }
  
  public static void saveHistory(Context paramContext)
  {
    saveArrays(paramContext, "g_TheftHistory.sav", g_TheftHistory, 20, 7);
  }
  
  public static void savePASSWORD(Context paramContext)
  {
    DealFile.savedata(paramContext, "g_PASSWORD.sav", g_PASSWORD);
  }
  
  public static void saveSSID(Context paramContext)
  {
    DealFile.savedata(paramContext, "g_SSID.sav", g_SSID);
  }
  
  public static void saveSSIDPwd(Context paramContext)
  {
    DealFile.savedata(paramContext, "g_SSIDPwd.sav", g_SSIDPwd);
  }
  
  public static void saveVIN(Context paramContext)
  {
    DealFile.savedata(paramContext, "g_VINNum.sav", g_VINNum);
  }
  
  public static void setPassword(String paramString)
  {
    int i = 0;
    int j = i;
    if (i < paramString.length()) {
      if (i < 4) {
        break label25;
      }
    }
    for (j = i;; j++)
    {
      if (j >= 5)
      {
        return;
        label25:
        g_PASSWORD[i] = ((byte)paramString.charAt(i));
        i++;
        break;
      }
      g_PASSWORD[j] = 0;
    }
  }
  
  public static void setPasswordType(byte paramByte)
  {
    g_PASSWORD[5] = paramByte;
  }
  
  public static void setSSID(String paramString)
  {
    int i = 0;
    int j = i;
    if (i < paramString.length()) {
      if (i < 32) {
        break label27;
      }
    }
    for (j = i;; j++)
    {
      if (j >= 33)
      {
        return;
        label27:
        g_SSID[i] = ((byte)paramString.charAt(i));
        i++;
        break;
      }
      g_SSID[j] = 0;
    }
  }
  
  public static void setSSIDPwd(String paramString)
  {
    int i = 0;
    int j = i;
    if (i < paramString.length()) {
      if (i < 128) {
        break label29;
      }
    }
    for (j = i;; j++)
    {
      if (j >= 129)
      {
        return;
        label29:
        g_SSIDPwd[i] = ((byte)paramString.charAt(i));
        i++;
        break;
      }
      g_SSIDPwd[j] = 0;
    }
  }
  
  public static void setVIN(String paramString)
  {
    int i = 0;
    int j = i;
    if (i < paramString.length()) {
      if (i < 17) {
        break label27;
      }
    }
    for (j = i;; j++)
    {
      if (j >= 20)
      {
        return;
        label27:
        g_VINNum[i] = ((byte)paramString.charAt(i));
        i++;
        break;
      }
      g_VINNum[j] = 0;
    }
  }
  
  public static int unsignedByteValue(byte paramByte)
  {
    if (paramByte >= 0) {}
    for (;;)
    {
      return paramByte;
      paramByte += 256;
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseStruct\DealMsg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */