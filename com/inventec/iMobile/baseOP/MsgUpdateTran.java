package com.inventec.iMobile.baseOP;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.inventec.iMobile.UpgradeDlg;
import com.inventec.iMobile.baseClass.myActivityBase;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.baseStruct.DefMsg;
import com.inventec.iMobile.iMobile_AppGlobalVar;
import java.io.IOException;
import java.io.InputStream;

public class MsgUpdateTran
{
  public static int g_curA0Status = 0;
  private final int CHECKSUM_SIZE = 4;
  private final int FRAME_SIZE = 516;
  private final int ROM_FRAME_SIZE = 527;
  final int[] auchCRCHi;
  final int[] auchCRCLo;
  boolean canReconnent;
  byte[] checkSum = new byte[4];
  int curROM;
  int curROMLength;
  int curROMReadLength;
  private InputStream curROMfis;
  int curWaitTime;
  byte[] curmsg = new byte[16];
  boolean doDownload;
  boolean doSendEVRData = false;
  byte[] frameBuf = new byte['ȏ'];
  private boolean inSpecialMode = true;
  private Handler mHandler;
  public UpgradeDlg m_dlg;
  final int nextTimeOut = 30000;
  boolean noDealMsg = false;
  final int rebootTestSSID = 15;
  final int rebootTimeOut = 360;
  private boolean reconnectThreadOn;
  byte rerequestCMDID;
  boolean resendIsMsg;
  int resendTimes;
  final int sendTimeOut = 15000;
  boolean testFailure;
  byte[] time_stamp = new byte[10];
  int time_stamp_cks;
  
  public MsgUpdateTran()
  {
    int[] arrayOfInt = new int['Ā'];
    arrayOfInt[1] = 193;
    arrayOfInt[2] = 129;
    arrayOfInt[3] = 64;
    arrayOfInt[4] = 1;
    arrayOfInt[5] = 192;
    arrayOfInt[6] = 128;
    arrayOfInt[7] = 65;
    arrayOfInt[8] = 1;
    arrayOfInt[9] = 192;
    arrayOfInt[10] = 128;
    arrayOfInt[11] = 65;
    arrayOfInt[13] = 193;
    arrayOfInt[14] = 129;
    arrayOfInt[15] = 64;
    arrayOfInt[16] = 1;
    arrayOfInt[17] = 192;
    arrayOfInt[18] = 128;
    arrayOfInt[19] = 65;
    arrayOfInt[21] = 193;
    arrayOfInt[22] = 129;
    arrayOfInt[23] = 64;
    arrayOfInt[25] = 193;
    arrayOfInt[26] = 129;
    arrayOfInt[27] = 64;
    arrayOfInt[28] = 1;
    arrayOfInt[29] = 192;
    arrayOfInt[30] = 128;
    arrayOfInt[31] = 65;
    arrayOfInt[32] = 1;
    arrayOfInt[33] = 192;
    arrayOfInt[34] = 128;
    arrayOfInt[35] = 65;
    arrayOfInt[37] = 193;
    arrayOfInt[38] = 129;
    arrayOfInt[39] = 64;
    arrayOfInt[41] = 193;
    arrayOfInt[42] = 129;
    arrayOfInt[43] = 64;
    arrayOfInt[44] = 1;
    arrayOfInt[45] = 192;
    arrayOfInt[46] = 128;
    arrayOfInt[47] = 65;
    arrayOfInt[49] = 193;
    arrayOfInt[50] = 129;
    arrayOfInt[51] = 64;
    arrayOfInt[52] = 1;
    arrayOfInt[53] = 192;
    arrayOfInt[54] = 128;
    arrayOfInt[55] = 65;
    arrayOfInt[56] = 1;
    arrayOfInt[57] = 192;
    arrayOfInt[58] = 128;
    arrayOfInt[59] = 65;
    arrayOfInt[61] = 193;
    arrayOfInt[62] = 129;
    arrayOfInt[63] = 64;
    arrayOfInt[64] = 1;
    arrayOfInt[65] = 192;
    arrayOfInt[66] = 128;
    arrayOfInt[67] = 65;
    arrayOfInt[69] = 193;
    arrayOfInt[70] = 129;
    arrayOfInt[71] = 64;
    arrayOfInt[73] = 193;
    arrayOfInt[74] = 129;
    arrayOfInt[75] = 64;
    arrayOfInt[76] = 1;
    arrayOfInt[77] = 192;
    arrayOfInt[78] = 128;
    arrayOfInt[79] = 65;
    arrayOfInt[81] = 193;
    arrayOfInt[82] = 129;
    arrayOfInt[83] = 64;
    arrayOfInt[84] = 1;
    arrayOfInt[85] = 192;
    arrayOfInt[86] = 128;
    arrayOfInt[87] = 65;
    arrayOfInt[88] = 1;
    arrayOfInt[89] = 192;
    arrayOfInt[90] = 128;
    arrayOfInt[91] = 65;
    arrayOfInt[93] = 193;
    arrayOfInt[94] = 129;
    arrayOfInt[95] = 64;
    arrayOfInt[97] = 193;
    arrayOfInt[98] = 129;
    arrayOfInt[99] = 64;
    arrayOfInt[100] = 1;
    arrayOfInt[101] = 192;
    arrayOfInt[102] = 128;
    arrayOfInt[103] = 65;
    arrayOfInt[104] = 1;
    arrayOfInt[105] = 192;
    arrayOfInt[106] = 128;
    arrayOfInt[107] = 65;
    arrayOfInt[109] = 193;
    arrayOfInt[110] = 129;
    arrayOfInt[111] = 64;
    arrayOfInt[112] = 1;
    arrayOfInt[113] = 192;
    arrayOfInt[114] = 128;
    arrayOfInt[115] = 65;
    arrayOfInt[117] = 193;
    arrayOfInt[118] = 129;
    arrayOfInt[119] = 64;
    arrayOfInt[121] = 193;
    arrayOfInt[122] = 129;
    arrayOfInt[123] = 64;
    arrayOfInt[124] = 1;
    arrayOfInt[125] = 192;
    arrayOfInt[126] = 128;
    arrayOfInt[127] = 65;
    arrayOfInt[''] = 1;
    arrayOfInt[''] = 192;
    arrayOfInt[''] = 128;
    arrayOfInt[''] = 65;
    arrayOfInt[''] = 193;
    arrayOfInt[''] = 129;
    arrayOfInt[''] = 64;
    arrayOfInt[''] = 193;
    arrayOfInt[''] = 129;
    arrayOfInt[''] = 64;
    arrayOfInt[''] = 1;
    arrayOfInt[''] = 192;
    arrayOfInt[''] = 128;
    arrayOfInt[''] = 65;
    arrayOfInt[''] = 193;
    arrayOfInt[''] = 129;
    arrayOfInt[''] = 64;
    arrayOfInt[''] = 1;
    arrayOfInt[''] = 192;
    arrayOfInt[''] = 128;
    arrayOfInt[''] = 65;
    arrayOfInt[''] = 1;
    arrayOfInt[''] = 192;
    arrayOfInt[''] = 128;
    arrayOfInt[''] = 65;
    arrayOfInt[''] = 193;
    arrayOfInt[''] = 129;
    arrayOfInt[''] = 64;
    arrayOfInt['¡'] = 193;
    arrayOfInt['¢'] = 129;
    arrayOfInt['£'] = 64;
    arrayOfInt['¤'] = 1;
    arrayOfInt['¥'] = 192;
    arrayOfInt['¦'] = 128;
    arrayOfInt['§'] = 65;
    arrayOfInt['¨'] = 1;
    arrayOfInt['©'] = 192;
    arrayOfInt['ª'] = 128;
    arrayOfInt['«'] = 65;
    arrayOfInt['­'] = 193;
    arrayOfInt['®'] = 129;
    arrayOfInt['¯'] = 64;
    arrayOfInt['°'] = 1;
    arrayOfInt['±'] = 192;
    arrayOfInt['²'] = 128;
    arrayOfInt['³'] = 65;
    arrayOfInt['µ'] = 193;
    arrayOfInt['¶'] = 129;
    arrayOfInt['·'] = 64;
    arrayOfInt['¹'] = 193;
    arrayOfInt['º'] = 129;
    arrayOfInt['»'] = 64;
    arrayOfInt['¼'] = 1;
    arrayOfInt['½'] = 192;
    arrayOfInt['¾'] = 128;
    arrayOfInt['¿'] = 65;
    arrayOfInt['Á'] = 193;
    arrayOfInt['Â'] = 129;
    arrayOfInt['Ã'] = 64;
    arrayOfInt['Ä'] = 1;
    arrayOfInt['Å'] = 192;
    arrayOfInt['Æ'] = 128;
    arrayOfInt['Ç'] = 65;
    arrayOfInt['È'] = 1;
    arrayOfInt['É'] = 192;
    arrayOfInt['Ê'] = 128;
    arrayOfInt['Ë'] = 65;
    arrayOfInt['Í'] = 193;
    arrayOfInt['Î'] = 129;
    arrayOfInt['Ï'] = 64;
    arrayOfInt['Ð'] = 1;
    arrayOfInt['Ñ'] = 192;
    arrayOfInt['Ò'] = 128;
    arrayOfInt['Ó'] = 65;
    arrayOfInt['Õ'] = 193;
    arrayOfInt['Ö'] = 129;
    arrayOfInt['×'] = 64;
    arrayOfInt['Ù'] = 193;
    arrayOfInt['Ú'] = 129;
    arrayOfInt['Û'] = 64;
    arrayOfInt['Ü'] = 1;
    arrayOfInt['Ý'] = 192;
    arrayOfInt['Þ'] = 128;
    arrayOfInt['ß'] = 65;
    arrayOfInt['à'] = 1;
    arrayOfInt['á'] = 192;
    arrayOfInt['â'] = 128;
    arrayOfInt['ã'] = 65;
    arrayOfInt['å'] = 193;
    arrayOfInt['æ'] = 129;
    arrayOfInt['ç'] = 64;
    arrayOfInt['é'] = 193;
    arrayOfInt['ê'] = 129;
    arrayOfInt['ë'] = 64;
    arrayOfInt['ì'] = 1;
    arrayOfInt['í'] = 192;
    arrayOfInt['î'] = 128;
    arrayOfInt['ï'] = 65;
    arrayOfInt['ñ'] = 193;
    arrayOfInt['ò'] = 129;
    arrayOfInt['ó'] = 64;
    arrayOfInt['ô'] = 1;
    arrayOfInt['õ'] = 192;
    arrayOfInt['ö'] = 128;
    arrayOfInt['÷'] = 65;
    arrayOfInt['ø'] = 1;
    arrayOfInt['ù'] = 192;
    arrayOfInt['ú'] = 128;
    arrayOfInt['û'] = 65;
    arrayOfInt['ý'] = 193;
    arrayOfInt['þ'] = 129;
    arrayOfInt['ÿ'] = 64;
    this.auchCRCHi = arrayOfInt;
    arrayOfInt = new int['Ā'];
    arrayOfInt[1] = 192;
    arrayOfInt[2] = 193;
    arrayOfInt[3] = 1;
    arrayOfInt[4] = 195;
    arrayOfInt[5] = 3;
    arrayOfInt[6] = 2;
    arrayOfInt[7] = 194;
    arrayOfInt[8] = 198;
    arrayOfInt[9] = 6;
    arrayOfInt[10] = 7;
    arrayOfInt[11] = 199;
    arrayOfInt[12] = 5;
    arrayOfInt[13] = 197;
    arrayOfInt[14] = 196;
    arrayOfInt[15] = 4;
    arrayOfInt[16] = 204;
    arrayOfInt[17] = 12;
    arrayOfInt[18] = 13;
    arrayOfInt[19] = 205;
    arrayOfInt[20] = 15;
    arrayOfInt[21] = 207;
    arrayOfInt[22] = 206;
    arrayOfInt[23] = 14;
    arrayOfInt[24] = 10;
    arrayOfInt[25] = 202;
    arrayOfInt[26] = 203;
    arrayOfInt[27] = 11;
    arrayOfInt[28] = 201;
    arrayOfInt[29] = 9;
    arrayOfInt[30] = 8;
    arrayOfInt[31] = 200;
    arrayOfInt[32] = 216;
    arrayOfInt[33] = 24;
    arrayOfInt[34] = 25;
    arrayOfInt[35] = 217;
    arrayOfInt[36] = 27;
    arrayOfInt[37] = 219;
    arrayOfInt[38] = 218;
    arrayOfInt[39] = 26;
    arrayOfInt[40] = 30;
    arrayOfInt[41] = 222;
    arrayOfInt[42] = 223;
    arrayOfInt[43] = 31;
    arrayOfInt[44] = 221;
    arrayOfInt[45] = 29;
    arrayOfInt[46] = 28;
    arrayOfInt[47] = 220;
    arrayOfInt[48] = 20;
    arrayOfInt[49] = 212;
    arrayOfInt[50] = 213;
    arrayOfInt[51] = 21;
    arrayOfInt[52] = 215;
    arrayOfInt[53] = 23;
    arrayOfInt[54] = 22;
    arrayOfInt[55] = 214;
    arrayOfInt[56] = 210;
    arrayOfInt[57] = 18;
    arrayOfInt[58] = 19;
    arrayOfInt[59] = 211;
    arrayOfInt[60] = 17;
    arrayOfInt[61] = 209;
    arrayOfInt[62] = 208;
    arrayOfInt[63] = 16;
    arrayOfInt[64] = 240;
    arrayOfInt[65] = 48;
    arrayOfInt[66] = 49;
    arrayOfInt[67] = 241;
    arrayOfInt[68] = 51;
    arrayOfInt[69] = 243;
    arrayOfInt[70] = 242;
    arrayOfInt[71] = 50;
    arrayOfInt[72] = 54;
    arrayOfInt[73] = 246;
    arrayOfInt[74] = 247;
    arrayOfInt[75] = 55;
    arrayOfInt[76] = 245;
    arrayOfInt[77] = 53;
    arrayOfInt[78] = 52;
    arrayOfInt[79] = 244;
    arrayOfInt[80] = 60;
    arrayOfInt[81] = 252;
    arrayOfInt[82] = 253;
    arrayOfInt[83] = 61;
    arrayOfInt[84] = 255;
    arrayOfInt[85] = 63;
    arrayOfInt[86] = 62;
    arrayOfInt[87] = 254;
    arrayOfInt[88] = 250;
    arrayOfInt[89] = 58;
    arrayOfInt[90] = 59;
    arrayOfInt[91] = 251;
    arrayOfInt[92] = 57;
    arrayOfInt[93] = 249;
    arrayOfInt[94] = 248;
    arrayOfInt[95] = 56;
    arrayOfInt[96] = 40;
    arrayOfInt[97] = 232;
    arrayOfInt[98] = 233;
    arrayOfInt[99] = 41;
    arrayOfInt[100] = 235;
    arrayOfInt[101] = 43;
    arrayOfInt[102] = 42;
    arrayOfInt[103] = 234;
    arrayOfInt[104] = 238;
    arrayOfInt[105] = 46;
    arrayOfInt[106] = 47;
    arrayOfInt[107] = 239;
    arrayOfInt[108] = 45;
    arrayOfInt[109] = 237;
    arrayOfInt[110] = 236;
    arrayOfInt[111] = 44;
    arrayOfInt[112] = 228;
    arrayOfInt[113] = 36;
    arrayOfInt[114] = 37;
    arrayOfInt[115] = 229;
    arrayOfInt[116] = 39;
    arrayOfInt[117] = 231;
    arrayOfInt[118] = 230;
    arrayOfInt[119] = 38;
    arrayOfInt[120] = 34;
    arrayOfInt[121] = 226;
    arrayOfInt[122] = 227;
    arrayOfInt[123] = 35;
    arrayOfInt[124] = 225;
    arrayOfInt[125] = 33;
    arrayOfInt[126] = 32;
    arrayOfInt[127] = 224;
    arrayOfInt[''] = 160;
    arrayOfInt[''] = 96;
    arrayOfInt[''] = 97;
    arrayOfInt[''] = 161;
    arrayOfInt[''] = 99;
    arrayOfInt[''] = 163;
    arrayOfInt[''] = 162;
    arrayOfInt[''] = 98;
    arrayOfInt[''] = 102;
    arrayOfInt[''] = 166;
    arrayOfInt[''] = 167;
    arrayOfInt[''] = 103;
    arrayOfInt[''] = 165;
    arrayOfInt[''] = 101;
    arrayOfInt[''] = 100;
    arrayOfInt[''] = 164;
    arrayOfInt[''] = 108;
    arrayOfInt[''] = 172;
    arrayOfInt[''] = 173;
    arrayOfInt[''] = 109;
    arrayOfInt[''] = 175;
    arrayOfInt[''] = 111;
    arrayOfInt[''] = 110;
    arrayOfInt[''] = 174;
    arrayOfInt[''] = 170;
    arrayOfInt[''] = 106;
    arrayOfInt[''] = 107;
    arrayOfInt[''] = 171;
    arrayOfInt[''] = 105;
    arrayOfInt[''] = 169;
    arrayOfInt[''] = 168;
    arrayOfInt[''] = 104;
    arrayOfInt[' '] = 120;
    arrayOfInt['¡'] = 184;
    arrayOfInt['¢'] = 185;
    arrayOfInt['£'] = 121;
    arrayOfInt['¤'] = 187;
    arrayOfInt['¥'] = 123;
    arrayOfInt['¦'] = 122;
    arrayOfInt['§'] = 186;
    arrayOfInt['¨'] = 190;
    arrayOfInt['©'] = 126;
    arrayOfInt['ª'] = 127;
    arrayOfInt['«'] = 191;
    arrayOfInt['¬'] = 125;
    arrayOfInt['­'] = 189;
    arrayOfInt['®'] = 188;
    arrayOfInt['¯'] = 124;
    arrayOfInt['°'] = 180;
    arrayOfInt['±'] = 116;
    arrayOfInt['²'] = 117;
    arrayOfInt['³'] = 181;
    arrayOfInt['´'] = 119;
    arrayOfInt['µ'] = 183;
    arrayOfInt['¶'] = 182;
    arrayOfInt['·'] = 118;
    arrayOfInt['¸'] = 114;
    arrayOfInt['¹'] = 178;
    arrayOfInt['º'] = 179;
    arrayOfInt['»'] = 115;
    arrayOfInt['¼'] = 177;
    arrayOfInt['½'] = 113;
    arrayOfInt['¾'] = 112;
    arrayOfInt['¿'] = 176;
    arrayOfInt['À'] = 80;
    arrayOfInt['Á'] = 144;
    arrayOfInt['Â'] = 145;
    arrayOfInt['Ã'] = 81;
    arrayOfInt['Ä'] = 147;
    arrayOfInt['Å'] = 83;
    arrayOfInt['Æ'] = 82;
    arrayOfInt['Ç'] = 146;
    arrayOfInt['È'] = 150;
    arrayOfInt['É'] = 86;
    arrayOfInt['Ê'] = 87;
    arrayOfInt['Ë'] = 151;
    arrayOfInt['Ì'] = 85;
    arrayOfInt['Í'] = 149;
    arrayOfInt['Î'] = 148;
    arrayOfInt['Ï'] = 84;
    arrayOfInt['Ð'] = 156;
    arrayOfInt['Ñ'] = 92;
    arrayOfInt['Ò'] = 93;
    arrayOfInt['Ó'] = 157;
    arrayOfInt['Ô'] = 95;
    arrayOfInt['Õ'] = 159;
    arrayOfInt['Ö'] = 158;
    arrayOfInt['×'] = 94;
    arrayOfInt['Ø'] = 90;
    arrayOfInt['Ù'] = 154;
    arrayOfInt['Ú'] = 155;
    arrayOfInt['Û'] = 91;
    arrayOfInt['Ü'] = 153;
    arrayOfInt['Ý'] = 89;
    arrayOfInt['Þ'] = 88;
    arrayOfInt['ß'] = 152;
    arrayOfInt['à'] = 136;
    arrayOfInt['á'] = 72;
    arrayOfInt['â'] = 73;
    arrayOfInt['ã'] = 137;
    arrayOfInt['ä'] = 75;
    arrayOfInt['å'] = 139;
    arrayOfInt['æ'] = 138;
    arrayOfInt['ç'] = 74;
    arrayOfInt['è'] = 78;
    arrayOfInt['é'] = 142;
    arrayOfInt['ê'] = 143;
    arrayOfInt['ë'] = 79;
    arrayOfInt['ì'] = 141;
    arrayOfInt['í'] = 77;
    arrayOfInt['î'] = 76;
    arrayOfInt['ï'] = 140;
    arrayOfInt['ð'] = 68;
    arrayOfInt['ñ'] = 132;
    arrayOfInt['ò'] = 133;
    arrayOfInt['ó'] = 69;
    arrayOfInt['ô'] = 135;
    arrayOfInt['õ'] = 71;
    arrayOfInt['ö'] = 70;
    arrayOfInt['÷'] = 134;
    arrayOfInt['ø'] = 130;
    arrayOfInt['ù'] = 66;
    arrayOfInt['ú'] = 67;
    arrayOfInt['û'] = 131;
    arrayOfInt['ü'] = 65;
    arrayOfInt['ý'] = 129;
    arrayOfInt['þ'] = 128;
    arrayOfInt['ÿ'] = 64;
    this.auchCRCLo = arrayOfInt;
    this.resendTimes = 0;
    this.doDownload = false;
    this.testFailure = true;
    this.curWaitTime = 0;
    this.reconnectThreadOn = false;
    this.canReconnent = false;
    this.mHandler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        super.handleMessage(paramAnonymousMessage);
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          return;
          paramAnonymousMessage = MsgUpdateTran.this;
          paramAnonymousMessage.curWaitTime += 1;
          if (MsgUpdateTran.this.curWaitTime < 360)
          {
            if (MsgUpdateTran.this.curWaitTime <= 196)
            {
              MsgUpdateTran.this.m_dlg.setProgress(MsgUpdateTran.this.curWaitTime / 4 + 50);
              if (MsgUpdateTran.this.curWaitTime == 15)
              {
                iMobile_AppGlobalVar.CloseConn();
                MsgUpdateTran.this.startCheckingThread();
              }
            }
            sendEmptyMessageDelayed(10, 1000L);
          }
          else
          {
            MsgUpdateTran.this.showReprgramm();
            continue;
            MsgUpdateTran.this.dealTimeout();
            continue;
            MsgUpdateTran.this.resend();
            continue;
            int i = MsgUpdateTran.this.curWaitTime / 4 + 50;
            if (i < 100)
            {
              MsgUpdateTran localMsgUpdateTran = MsgUpdateTran.this;
              localMsgUpdateTran.curWaitTime += 4;
              MsgUpdateTran.this.m_dlg.setProgress(i + 1);
              sendEmptyMessageDelayed(paramAnonymousMessage.what, 50L);
            }
            else
            {
              MsgUpdateTran.this.m_dlg.setProgress(100.0F);
              sendEmptyMessageDelayed(paramAnonymousMessage.what + 10, 1000L);
              continue;
              MsgUpdateTran.this.m_dlg.showReupgrade();
              continue;
              MsgUpdateTran.this.m_dlg.showUpgradeFinish();
              continue;
              AppDealWifi.LogMsg("setting colour");
              MsgUpdateTran.this.m_dlg.setProgressColor(-65536);
            }
          }
        }
      }
    };
    this.resendIsMsg = false;
    this.m_dlg = null;
  }
  
  private void beginTransfer(int paramInt)
  {
    this.frameBuf[0] = -9;
    this.frameBuf[1] = 0;
    this.frameBuf[2] = 0;
    this.frameBuf[3] = 0;
    this.frameBuf[4] = -89;
    this.curROM = paramInt;
    for (;;)
    {
      try
      {
        if (this.curROM >= 5) {
          continue;
        }
        this.curROMfis = GetUpdateRomFileInputStream(this.curROM);
        if ((this.curROMfis == null) || (this.curROMfis.available() <= 0)) {
          continue;
        }
        this.m_dlg.ShowProgress();
        this.curROMLength = this.curROMfis.available();
        if (this.curROM == 1)
        {
          this.curROMLength -= 4;
          this.curROMfis.read(this.checkSum, 0, 4);
        }
        this.curROMReadLength = 0;
      }
      catch (Exception localException)
      {
        AppDealWifi.LogExceptionMsg(localException);
        continue;
        this.curROMLength = 0;
        continue;
      }
      transferROMData();
      return;
      this.curROMfis = null;
    }
  }
  
  private int makeTransferData()
  {
    try
    {
      if (this.curROMfis != null)
      {
        i = this.curROMfis.available();
        if (i >= 0) {
          break label38;
        }
      }
      i = 0;
    }
    catch (IOException localIOException1)
    {
      for (;;)
      {
        AppDealWifi.LogExceptionMsg(localIOException1);
        int i = 0;
        continue;
        try
        {
          label38:
          i = this.curROMfis.read(this.frameBuf, 5, 516);
          if (i != -1) {}
        }
        catch (Exception localException)
        {
          try
          {
            this.curROMfis.close();
            this.curROMfis = null;
            i = 0;
            continue;
            localException = localException;
            AppDealWifi.LogExceptionMsg(localException);
            i = 0;
          }
          catch (IOException localIOException2)
          {
            for (;;)
            {
              AppDealWifi.LogExceptionMsg(localIOException2);
            }
          }
          if (i <= 0)
          {
            i = -1;
          }
          else
          {
            if ((this.frameBuf[6] == 1) && (this.frameBuf[5] == 0) && (this.frameBuf[7] == 0) && (this.frameBuf[8] == 0)) {
              DefMsg.memcpy(this.frameBuf, 265, this.time_stamp, 0, 10);
            }
            this.curROMReadLength += i;
            float f1 = this.curROMReadLength * 100.0F / this.curROMLength / 4.0F;
            float f2 = this.curROM;
            this.m_dlg.setProgress((f1 + (f2 - 1.0F) * 25.0F) / 2.0F);
            DealMsg.DataToBytes(i + 4, this.frameBuf, 1, 2);
            DealMsg.DataToBytes(CRC16(this.frameBuf, 9, i - 4), this.frameBuf, i + 5, 2);
          }
        }
      }
    }
    return i;
  }
  
  private void startCheckingThread()
  {
    new Thread()
    {
      public void run()
      {
        for (;;)
        {
          try
          {
            AppDealWifi.LogMsg("Begin SSID test thread");
            localContext = MsgUpdateTran.this.m_dlg.getContext();
            str1 = DealMsg.getSSID();
            str2 = DealMsg.getSSIDPwd();
            iMobile_AppGlobalVar.CloseConn();
            ConnSSID.stopCurSSID(localContext);
            sleep(3000L);
            i = ConnSSID.connectToMyWifi(localContext, str1, str2);
            if ((MsgUpdateTran.this.curWaitTime >= 360) || (i == 0))
            {
              if (i != 0) {
                AppDealWifi.LogMsg("Time out. Stop SSID test thread");
              }
              return;
            }
          }
          catch (Exception localException)
          {
            Context localContext;
            String str1;
            String str2;
            int i;
            AppDealWifi.LogExceptionMsg(localException);
            continue;
          }
          if (myActivityBase.GetCurForegroundActivity() != null) {
            i = ConnSSID.connectToMyWifi(localContext, str1, str2);
          } else {
            sleep(3000L);
          }
        }
      }
    }.start();
  }
  
  private void startReconnectThread()
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          AppDealWifi.LogMsg("Send Timeout. Begin Reconnect thread!!!!!!!!!!!!!!!!!!!!!!!!!");
          Context localContext = MsgUpdateTran.this.m_dlg.getContext();
          String str2 = DealMsg.getSSID();
          String str1 = DealMsg.getSSIDPwd();
          ConnSSID.stopCurSSID(localContext);
          sleep(3000L);
          i = ConnSSID.connectToMyWifi(localContext, str2, str1);
          if (!MsgUpdateTran.this.reconnectThreadOn) {
            AppDealWifi.LogMsg("wifi repro is canceled, do not check socket!!");
          }
          for (;;)
          {
            return;
            if (i != 0) {
              break label212;
            }
            i = 0;
            if (i < 30) {
              break;
            }
            if (i >= 30)
            {
              AppDealWifi.LogMsg("Time out. Stop SSID test thread");
              MsgUpdateTran.this.mHandler.sendEmptyMessageDelayed(11, 50L);
            }
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            int i;
            AppDealWifi.LogExceptionMsg(localException);
            continue;
            sleep(1000L);
            if ((iMobile_AppGlobalVar.isConn()) && (MsgUpdateTran.this.reconnectThreadOn))
            {
              AppDealWifi.LogMsg("In wifi repro thread:socket connected,start transmit data");
              if (MsgUpdateTran.this.doSendEVRData)
              {
                AppDealWifi.LogMsg("startReconnectThread to send 0xAA----- ");
                MsgUpdateTran.this.setError((byte)1);
              }
              else
              {
                MsgUpdateTran.this.resend();
                MsgUpdateTran.this.testResend(true);
              }
            }
            else if (!MsgUpdateTran.this.reconnectThreadOn)
            {
              AppDealWifi.LogMsg("wifi repro is canceled!!stop check socket");
            }
            else
            {
              AppDealWifi.LogMsg("In wifi repro thread:check socket connection every 1s:not connected");
              i++;
              continue;
              label212:
              AppDealWifi.LogMsg("In wifi repro thread:wifi reconnecting failed");
              i = 30;
            }
          }
        }
      }
    }.start();
  }
  
  private void transferROMData()
  {
    removeAllMsg();
    if (!this.doSendEVRData) {}
    for (;;)
    {
      return;
      int i = makeTransferData();
      if (i > 0)
      {
        this.resendIsMsg = false;
        MsgSender.resendKMsg(this.frameBuf);
        testResend(true);
      }
      else if (i == 0)
      {
        if (this.curROM == 5)
        {
          this.rerequestCMDID = -85;
          sendCMD(this.rerequestCMDID, (byte)0);
          this.canReconnent = false;
        }
        else
        {
          beginTransfer(this.curROM + 1);
        }
      }
    }
  }
  
  private void waitUpgradeFinish()
  {
    Log.e("iMobile", "waitUpgradeFinish");
    removeAllMsg();
    this.curWaitTime = 0;
    this.m_dlg.hideCancelButton();
    this.mHandler.sendEmptyMessageDelayed(10, 1000L);
  }
  
  final int CRC16(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int j = 255;
    int k = 255;
    for (int i = paramInt1;; i++)
    {
      if (i >= paramInt2 + paramInt1) {
        return (j << 8 | k) & 0xFFFF;
      }
      int n = paramArrayOfByte[i];
      int m = n;
      if (n < 0) {
        m = n + 256;
      }
      m = k ^ m;
      k = j ^ this.auchCRCHi[m];
      j = this.auchCRCLo[m];
    }
  }
  
  public InputStream GetUpdateRomFileInputStream(int paramInt)
  {
    Object localObject1 = iMobile_AppGlobalVar.myGetResources();
    if (localObject1 == null) {
      localObject1 = null;
    }
    for (;;)
    {
      return (InputStream)localObject1;
      try
      {
        localObject1 = ((Resources)localObject1).openRawResource(new int[] { 2131034112, 2131034113, 2131034114, 2131034115 }[(paramInt - 1)]);
        StringBuilder localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>("Open: rom");
        AppDealWifi.LogMsg(paramInt);
      }
      catch (Exception localException)
      {
        AppDealWifi.LogExceptionMsg(localException);
        Object localObject2 = null;
      }
    }
  }
  
  public void StartUpgrade(UpgradeDlg paramUpgradeDlg)
  {
    this.m_dlg = paramUpgradeDlg;
    iMobile_AppGlobalVar.setReprogrammingTimeStamp(this.time_stamp);
    MsgReceiver.upgradeProcess = this;
    sendCMD((byte)-96, (byte)g_curA0Status);
  }
  
  public void StopUpgrade()
  {
    this.reconnectThreadOn = false;
    this.canReconnent = false;
    MsgReceiver.upgradeProcess = null;
  }
  
  public boolean dealMsg(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte = new byte[50];
    int i = 0;
    int k = paramArrayOfByte[(i + 1)] + 2;
    for (int j = 0;; j++)
    {
      if (j >= k)
      {
        k = paramInt - k;
        i += j;
        boolean bool = dealSingleMsg(arrayOfByte);
        paramInt = k;
        if (k > 0) {
          break;
        }
        return bool;
      }
      arrayOfByte[j] = paramArrayOfByte[(i + j)];
    }
  }
  
  boolean dealSingleMsg(byte[] paramArrayOfByte)
  {
    boolean bool4 = false;
    boolean bool3 = false;
    boolean bool2 = true;
    boolean bool1;
    if (paramArrayOfByte.length < 5) {
      bool1 = false;
    }
    for (;;)
    {
      return bool1;
      int k = paramArrayOfByte[0];
      byte b = paramArrayOfByte[3];
      int i = paramArrayOfByte[4];
      if ((paramArrayOfByte[2] == 1) && (k != 127))
      {
        bool1 = bool2;
        if (k == -97)
        {
          bool1 = bool2;
          if (b != -85) {}
        }
      }
      else
      {
        if (k == 111)
        {
          int j = paramArrayOfByte[1];
          if (DefMsg.calcCheckSum(paramArrayOfByte) != paramArrayOfByte[(j + 1)])
          {
            MsgSender.resendKMsg(CmdMake.KO_COMMAND_CONFIRM_SP(b, (byte)2));
            bool1 = false;
            continue;
          }
        }
        if ((k == 111) && (b == -54))
        {
          MsgSender.resendKMsg(CmdMake.KO_COMMAND_CONFIRM_SP(b, (byte)0));
          showReprgramm();
          StopUpgrade();
          this.doSendEVRData = false;
          bool1 = bool2;
        }
        else if ((k == -97) && (b == -61))
        {
          paramArrayOfByte = CmdMake.KO_COMMAND_CONFIRM_SP(b, (byte)0);
          paramArrayOfByte[0] = -7;
          paramArrayOfByte[5] = DefMsg.calcCheckSum(paramArrayOfByte);
          MsgSender.resendKMsg(paramArrayOfByte);
          if (this.doSendEVRData)
          {
            this.doSendEVRData = false;
            showReprgramm();
            StopUpgrade();
            AppDealWifi.LogMsg("download process intertup by  other msg(0xc3)--------------------------");
          }
          this.doSendEVRData = true;
          if (i == 0)
          {
            this.canReconnent = true;
            beginTransfer(this.curROM);
            bool1 = bool2;
          }
          else if (i == 1)
          {
            testResend(true);
            bool1 = bool2;
          }
          else
          {
            bool1 = bool2;
            if (i == 2)
            {
              showReprgramm();
              bool1 = bool2;
            }
          }
        }
        else if ((k == -97) && (b == -85))
        {
          this.rerequestCMDID = -87;
          sendCMD((byte)-87, (byte)0);
          bool1 = bool2;
        }
        else if ((k == 127) && (!this.noDealMsg))
        {
          if (i == 0)
          {
            transferROMData();
            bool1 = bool2;
          }
          else
          {
            sendCMD((byte)-93, (byte)this.curROM);
            this.doSendEVRData = false;
            AppDealWifi.LogMsg("one  frmame is send error---------------");
            bool1 = bool2;
          }
        }
        else if (k == 111)
        {
          if (((b != -64) && (b != -63) && (b != -55) && (b != 21) && (b != 42) && (b != 3)) || (this.noDealMsg))
          {
            MsgSender.resendKMsg(CmdMake.KO_COMMAND_CONFIRM_SP(b, (byte)0));
            bool1 = false;
          }
          else
          {
            MsgSender.resendKMsg(CmdMake.KO_COMMAND_CONFIRM_SP(b, (byte)0));
            if ((this.doSendEVRData) && ((b == -64) || (b == -63) || (b == 21)))
            {
              this.doSendEVRData = false;
              showReprgramm();
              StopUpgrade();
              AppDealWifi.LogMsg("download process intertup by  other msg(c0,c1,15)--------------------------");
              bool1 = bool2;
            }
            else if (b == -64)
            {
              removeAllMsg();
              bool1 = bool3;
              if (this.rerequestCMDID == -1) {
                bool1 = true;
              }
              DefMsg.memcpy(DefMsg.g_recdatas, 181, paramArrayOfByte, 4, 10);
              DefMsg.updateAll();
              bool3 = iMobile_AppGlobalVar.needReprogramming(paramArrayOfByte, 4, bool1, true);
              if (bool1)
              {
                this.noDealMsg = true;
                if (bool3)
                {
                  this.mHandler.sendEmptyMessageDelayed(20, 50L);
                  bool1 = bool2;
                }
                else
                {
                  this.mHandler.sendEmptyMessageDelayed(21, 50L);
                  bool1 = bool2;
                }
              }
              else if (!bool3)
              {
                if (g_curA0Status == 1)
                {
                  this.noDealMsg = true;
                  this.m_dlg.showNoNeedUpgrade();
                  bool1 = bool2;
                }
                else
                {
                  this.m_dlg.dismiss();
                  bool1 = bool2;
                }
              }
              else if (paramArrayOfByte[14] == 0)
              {
                this.noDealMsg = true;
                removeAllMsg();
                this.m_dlg.showHowToSPModel();
                Log.e("iMobile", "upgradeSpecialModeErr");
                bool1 = bool2;
              }
              else if (paramArrayOfByte[15] == 1)
              {
                showReprgramm();
                Log.e("iMobile", "upgradeIgnitionErr");
                bool1 = bool2;
              }
              else if (paramArrayOfByte[16] == 1)
              {
                showReprgramm();
                Log.e("iMobile", "upgradeDiagnosticSessionErr");
                bool1 = bool2;
              }
              else if (g_curA0Status == 1)
              {
                this.m_dlg.showSelect();
                bool1 = bool2;
              }
              else
              {
                showStep();
                bool1 = bool2;
              }
            }
            else if (b == -63)
            {
              if (paramArrayOfByte[4] == 0)
              {
                bool1 = bool2;
                if (!this.doDownload)
                {
                  this.doDownload = true;
                  this.curROM = 1;
                  AppDealWifi.LogMsg("curROM is reset to " + this.curROM);
                  sendCMD((byte)-93, (byte)0);
                  bool1 = bool2;
                }
              }
              else if (paramArrayOfByte[4] == 2)
              {
                showReprgramm();
                Log.e("iMobile", "Reprogramming Fail: General Reject");
                bool1 = bool2;
              }
              else
              {
                bool1 = bool2;
                if (paramArrayOfByte[4] == 5)
                {
                  this.noDealMsg = true;
                  removeAllMsg();
                  this.m_dlg.showHowToSPModel();
                  Log.e("iMobile", "upgradeSpecialModeErr");
                  bool1 = bool2;
                }
              }
            }
            else if (b == -55)
            {
              this.doSendEVRData = false;
              if (paramArrayOfByte[4] == 0)
              {
                this.rerequestCMDID = -1;
                waitUpgradeFinish();
                bool1 = bool2;
              }
              else if (paramArrayOfByte[4] == 1)
              {
                testResend(true);
                bool1 = bool2;
              }
              else if (paramArrayOfByte[4] == 2)
              {
                showReprgramm();
                Log.e("iMobile", "Reprogramming Fail: Flash Error");
                bool1 = bool2;
              }
              else
              {
                showReprgramm();
                Log.e("iMobile", "Reprogramming Fail: Non-Complete Data");
                bool1 = bool2;
              }
            }
            else
            {
              bool1 = bool2;
              if (b == 21)
              {
                bool1 = bool4;
                if (paramArrayOfByte[22] == 1) {
                  bool1 = true;
                }
                this.inSpecialMode = bool1;
                bool1 = bool2;
              }
            }
          }
        }
        else
        {
          bool1 = false;
        }
      }
    }
  }
  
  void dealTimeout()
  {
    AppDealWifi.LogMsg("timeout happened twice");
    if (this.canReconnent)
    {
      AppDealWifi.LogMsg("30s timeout ,startReconnectThread----------");
      this.canReconnent = false;
      this.reconnectThreadOn = true;
      startReconnectThread();
    }
    for (;;)
    {
      return;
      showSendError();
      AppDealWifi.LogMsg("error dialog----------");
    }
  }
  
  public void dismissDlg()
  {
    removeAllMsg();
    if (this.m_dlg != null)
    {
      this.m_dlg.dismiss();
      this.m_dlg = null;
    }
  }
  
  void removeAllMsg()
  {
    this.mHandler.removeMessages(10);
    this.mHandler.removeMessages(11);
    this.mHandler.removeMessages(12);
  }
  
  void resend()
  {
    AppDealWifi.LogMsg("timeout happened, resend");
    removeAllMsg();
    if (this.resendIsMsg) {
      resendMsg();
    }
    for (;;)
    {
      this.mHandler.sendEmptyMessageDelayed(11, 15000L);
      return;
      if ((this.frameBuf[0] != 0) && (this.frameBuf[1] == 0)) {
        int i = this.frameBuf[2];
      }
    }
  }
  
  void resendMsg()
  {
    this.resendIsMsg = true;
    MsgSender.resendKMsg(this.curmsg);
  }
  
  void sendCMD(byte paramByte1, byte paramByte2)
  {
    if ((paramByte1 == -96) || (paramByte1 == -95)) {
      this.noDealMsg = false;
    }
    if (paramByte1 == 170)
    {
      this.doSendEVRData = false;
      AppDealWifi.LogMsg("wifi repo by sp--------------------------");
    }
    this.rerequestCMDID = paramByte1;
    if ((paramByte1 == -93) || (paramByte1 == -85))
    {
      this.curmsg[0] = -7;
      this.curmsg[2] = 0;
      this.curmsg[3] = paramByte1;
      this.curmsg[4] = paramByte2;
      if (paramByte1 != -95) {
        break label145;
      }
      this.curmsg[1] = 13;
      DefMsg.memcpy(this.curmsg, 4, this.time_stamp, 0, 10);
      this.curmsg[14] = DefMsg.calcCheckSum(this.curmsg);
    }
    for (;;)
    {
      resendMsg();
      testResend(true);
      return;
      this.curmsg[0] = -10;
      break;
      label145:
      if (paramByte1 == -85)
      {
        this.curmsg[1] = 7;
        DefMsg.memcpy(this.curmsg, 4, this.checkSum, 0, 4);
        this.curmsg[8] = DefMsg.calcCheckSum(this.curmsg);
      }
      else
      {
        this.curmsg[1] = 4;
        this.curmsg[5] = DefMsg.calcCheckSum(this.curmsg);
      }
    }
  }
  
  void setError(byte paramByte)
  {
    sendCMD((byte)-86, paramByte);
    removeAllMsg();
  }
  
  void showReprgramm()
  {
    this.doDownload = false;
    setError((byte)1);
    this.m_dlg.showReupgrade();
    this.doSendEVRData = false;
  }
  
  void showSendError()
  {
    if (this.noDealMsg) {}
    for (;;)
    {
      return;
      this.noDealMsg = true;
      setError((byte)2);
      if (this.m_dlg != null) {
        this.m_dlg.showSendError();
      }
    }
  }
  
  public void showStep()
  {
    if (!this.inSpecialMode)
    {
      this.noDealMsg = true;
      removeAllMsg();
      this.m_dlg.showHowToSPModel();
      Log.e("iMobile", "upgradeSpecialModeErr");
    }
    for (;;)
    {
      return;
      this.m_dlg.showUpgradeProcess();
      this.doDownload = false;
      this.rerequestCMDID = -95;
      sendCMD((byte)-95, (byte)0);
    }
  }
  
  void testNextMsg()
  {
    Log.e("iMobile", "testNextMsg");
    removeAllMsg();
    this.mHandler.sendEmptyMessageDelayed(11, 30000L);
  }
  
  void testResend(boolean paramBoolean)
  {
    if (paramBoolean) {
      this.resendTimes = 0;
    }
    removeAllMsg();
    this.mHandler.sendEmptyMessageDelayed(12, 15000L);
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseOP\MsgUpdateTran.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */