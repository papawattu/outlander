package com.inventec.iMobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;
import com.inventec.controls.makeBitmap;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myAlertDlg;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseClass.myWaitDlg;
import com.inventec.iMobile.baseOP.CmdMake;
import com.inventec.iMobile.baseOP.ConnSSID;
import com.inventec.iMobile.baseOP.DataGetter;
import com.inventec.iMobile.baseOP.MsgSender;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.baseStruct.DefMsg;
import java.util.Calendar;
import java.util.Timer;

public class MvHome
  extends myMainView
{
  final int HOUR_INVALID = 25;
  final int MIN_INVALID = 61;
  makeBitmap[] anis = new makeBitmap[4];
  boolean bShowAni = false;
  boolean bShowErrWhenEnd = true;
  ImageView battWarmInd;
  MyButton btnACDoing;
  MyButton btnACTimer;
  MyButton btnCHGTimer;
  boolean flag = true;
  boolean flag_anim = true;
  boolean flag_battery = true;
  boolean flag_flow = true;
  boolean flag_layout = true;
  boolean flag_viable = true;
  ImageView image1;
  ImageView image2;
  ImageView image3;
  ImageView image4;
  LinearLayout modeac;
  LinearLayout modechg;
  RelativeLayout modimg;
  BattWarmIndicator myBattWarmInd;
  MyHandler myhd;
  Paint paint;
  int timeMargin = 0;
  Timer timer;
  AutoResizeTextView txtac;
  AutoResizeTextView txtacTimer;
  AutoResizeTextView txtchg;
  AutoResizeTextView txtchgTimer;
  
  public MvHome(myActivity parammyActivity)
  {
    super(parammyActivity, 2130903074);
    for (int i = 0;; i++)
    {
      if (i >= 4)
      {
        RelativeLayout localRelativeLayout = (RelativeLayout)this.mView.findViewById(2131296465);
        this.mAct.enlargeViewToFitScreen(localRelativeLayout, false);
        this.image1 = ((ImageView)this.mView.findViewById(2131296290));
        this.image2 = ((ImageView)this.mView.findViewById(2131296466));
        this.image3 = ((ImageView)this.mView.findViewById(2131296467));
        this.image4 = ((ImageView)this.mView.findViewById(2131296468));
        this.battWarmInd = ((ImageView)this.mView.findViewById(2131296469));
        parammyActivity.enlargeViewToFitScreen(this.battWarmInd, false);
        Bitmap localBitmap3 = BitmapFactory.decodeResource(this.mAct.getResources(), 2130837560);
        Bitmap localBitmap4 = BitmapFactory.decodeResource(this.mAct.getResources(), 2130837559);
        Bitmap localBitmap1 = BitmapFactory.decodeResource(this.mAct.getResources(), 2130837540);
        Bitmap localBitmap2 = BitmapFactory.decodeResource(this.mAct.getResources(), 2130837539);
        this.myBattWarmInd = new BattWarmIndicator(this.battWarmInd, localBitmap4, localBitmap3, localBitmap2, localBitmap1);
        this.myBattWarmInd.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            MvHome.BattWarmIndicator localBattWarmIndicator = MvHome.this.myBattWarmInd;
            paramAnonymousView = new myAlertDlg(MvHome.this.mAct, 2130903093);
            int i;
            if (localBattWarmIndicator.getType() == 0)
            {
              i = 2131165327;
              paramAnonymousView.hideContents(2131296621);
            }
            for (;;)
            {
              paramAnonymousView.setDlgContent(2131165326, i, true);
              paramAnonymousView.setImage(2131296619);
              paramAnonymousView.setImage(2131296621);
              paramAnonymousView.setImage(2131296620);
              paramAnonymousView.show();
              return;
              switch (DefMsg.g_recdatas[4])
              {
              default: 
                i = 2131165327;
                paramAnonymousView.hideContents(2131296621);
                break;
              case 1: 
                i = 2131165331;
                break;
              case 2: 
                i = 2131165333;
                paramAnonymousView.hideContents(2131296621);
                break;
              case 3: 
                i = 2131165328;
                paramAnonymousView.hideContents(2131296619);
                break;
              case 4: 
                i = 2131165330;
                paramAnonymousView.hideContents(2131296619);
                break;
              case 5: 
                i = 2131165332;
                paramAnonymousView.hideContents(2131296619);
                break;
              case 6: 
                i = 2131165329;
                paramAnonymousView.hideContents(2131296621);
              }
            }
          }
        });
        this.myBattWarmInd.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            int i = paramAnonymousMotionEvent.getAction();
            if ((i == 0) || (i == 2)) {
              MvHome.this.myBattWarmInd.setStatus(1);
            }
            for (;;)
            {
              return false;
              MvHome.this.myBattWarmInd.setStatus(0);
            }
          }
        });
        this.paint = new Paint();
        this.modechg = ((LinearLayout)this.mView.findViewById(2131296470));
        parammyActivity.enlargeViewToFitScreen(this.modechg, false);
        this.txtchg = ((AutoResizeTextView)this.mView.findViewById(2131296471));
        parammyActivity.enlargeViewToFitScreen(this.txtchg, true);
        this.txtchgTimer = ((AutoResizeTextView)this.mView.findViewById(2131296472));
        parammyActivity.enlargeViewToFitScreen(this.txtchgTimer, true);
        this.txtchgTimer.setSingle();
        this.btnCHGTimer = ((MyButton)this.mView.findViewById(2131296474));
        this.btnCHGTimer.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            MvHome.this.onclickTimerOnOff();
          }
        });
        parammyActivity.enlargeViewToFitScreen(this.btnCHGTimer, true);
        parammyActivity.enlargeViewToFitScreen(this.mView.findViewById(2131296473), false);
        this.modimg = ((RelativeLayout)this.mView.findViewById(2131296265));
        this.modeac = ((LinearLayout)this.mView.findViewById(2131296475));
        parammyActivity.enlargeViewToFitScreen(this.modeac, false);
        this.txtac = ((AutoResizeTextView)this.mView.findViewById(2131296476));
        parammyActivity.enlargeViewToFitScreen(this.txtac, true);
        this.txtacTimer = ((AutoResizeTextView)this.mView.findViewById(2131296477));
        parammyActivity.enlargeViewToFitScreen(this.txtacTimer, true);
        this.btnACDoing = ((MyButton)this.mView.findViewById(2131296478));
        this.btnACDoing.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramAnonymousView = (MyButton)paramAnonymousView;
            if ((paramAnonymousView != null) && (!paramAnonymousView.IsMyButtonEnable()))
            {
              paramAnonymousView = new myAlertDlg(MvHome.this.mAct);
              paramAnonymousView.setDlgContent(0, 2131165325, true);
              paramAnonymousView.show();
            }
            for (;;)
            {
              return;
              MvHome.this.onclickACDoingOnOff();
            }
          }
        });
        parammyActivity.enlargeViewToFitScreen(this.btnACDoing, true);
        this.btnACTimer = ((MyButton)this.mView.findViewById(2131296479));
        this.btnACTimer.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            MvHome.this.onclickACTimerOnOff();
          }
        });
        parammyActivity.enlargeViewToFitScreen(this.btnACTimer, true);
        i = this.mAct.getValidScreenHeight() - getViewSpaceVertical(localRelativeLayout) - getViewSpaceVertical(this.modechg) - getViewSpaceVertical(this.modeac);
        if (i > 3)
        {
          enlargeViewHeight(localRelativeLayout, i / 3 + GetViewParaHeight(localRelativeLayout));
          localRelativeLayout.setPadding(0, i / 3, 0, 0);
          enlargeViewHeight(this.modechg, i / 3 + GetViewParaHeight(this.modechg));
          enlargeViewHeight(this.modeac, i / 3 + GetViewParaHeight(this.modeac));
        }
        this.myhd = new MyHandler();
        Update();
        return;
      }
      this.anis[i] = null;
    }
  }
  
  private void setStatus()
  {
    StopAnim();
    int i1 = calcBatterySegment();
    if (DealMsg.g_isDemo) {
      i1 = 12;
    }
    int j = DefMsg.g_recdatas[100];
    int m = DefMsg.g_recdatas[1];
    int i = DefMsg.g_recdatas[4];
    boolean bool1;
    label122:
    int n;
    label152:
    int i7;
    label181:
    int i2;
    boolean bool2;
    label250:
    Object localObject2;
    label258:
    label317:
    Object localObject1;
    int i3;
    label383:
    label400:
    label418:
    label428:
    String str3;
    String str1;
    String str2;
    label685:
    String str4;
    if ((m == 0) && (j == 3))
    {
      this.myBattWarmInd.setType(0);
      this.myBattWarmInd.show();
      j = DataGetter.g_datas((short)105);
      bool1 = quickCharge();
      i = j;
      if (DealMsg.g_isDemo)
      {
        i = j;
        if (DefMsg.g_datas[96] > 1) {
          i = 1;
        }
      }
      if ((i == 1) || (bool1)) {
        break label1591;
      }
      bool1 = false;
      j = getChgTimeMin();
      n = DataGetter.g_datas((short)18);
      if ((n != 4) && (n != 5)) {
        break label1597;
      }
      n = 65535;
      i = DataGetter.g_datas((short)103);
      i7 = DataGetter.g_datas((short)2);
      j = DataGetter.g_datas((short)3);
      if (!quickCharge()) {
        break label1629;
      }
      i = 4;
      j = 0;
      int i6 = DataGetter.g_datas((short)85);
      int i4 = DataGetter.g_datas((short)125);
      int i5 = DataGetter.g_datas((short)95);
      if (i6 != 2)
      {
        i2 = j;
        if (i4 == 1)
        {
          i2 = j;
          if (i5 != 1) {}
        }
      }
      else
      {
        i2 = DataGetter.g_datas((short)98) & 0xF;
      }
      if (i4 != 1) {
        break label1697;
      }
      bool2 = true;
      localObject2 = new makeBitmap[4];
      j = 0;
      if (j < 4) {
        break label1703;
      }
      this.anis[0] = new makeBitmap();
      this.anis[0].InitBgBmp(this.mAct.getResources(), 2130837541);
      if (i > 0)
      {
        j = 2131165219;
        if (i != 4) {
          break label1720;
        }
        j = 2131165222;
        localObject1 = this.mAct.getString(j);
        i3 = ((String)localObject1).length();
        m = i3;
        if (i3 < ((String)localObject1).getBytes().length) {
          m = i3 * 2;
        }
        if (m <= 10) {
          break label1855;
        }
        this.anis[0].setFont(36, false);
        if (i7 != 2) {
          break label1762;
        }
        m = 2130837647;
        if (i != 4) {
          break label1778;
        }
        if (i7 != 2) {
          break label1770;
        }
        m = 2130837649;
        localObject1 = this.anis[0];
        if (i7 != 2) {
          break label1840;
        }
        i = 310;
        if (i7 != 2) {
          break label1848;
        }
        i3 = 12;
        ((makeBitmap)localObject1).AddImgData(m, i, i3);
        this.anis[0].AddTxtDataCenter(j, 320, 67, 270);
      }
      if ((n > 0) && (n < 6000))
      {
        j = n / 60;
        i3 = n % 60;
        n = this.anis[0].getImage().getWidth();
        str3 = this.mAct.getString(2131165306);
        this.paint.setTextSize(32.0F);
        this.paint.setFakeBoldText(true);
        i = (int)this.paint.measureText(str3, 0, str3.length());
        this.paint.setTextSize(48.0F);
        this.paint.setFakeBoldText(true);
        str1 = j;
        m = (int)this.paint.measureText(str1, 0, str1.length());
        this.paint.setTextSize(32.0F);
        this.paint.setFakeBoldText(true);
        str2 = this.mAct.getString(2131165307);
        j = (int)this.paint.measureText(str2, 0, str2.length());
        this.paint.setTextSize(48.0F);
        this.paint.setFakeBoldText(true);
        if (i3 <= 9) {
          break label2048;
        }
        localObject1 = i3;
        i3 = (int)this.paint.measureText((String)localObject1, 0, ((String)localObject1).length());
        this.paint.setTextSize(32.0F);
        this.paint.setFakeBoldText(true);
        str4 = this.mAct.getString(2131165308);
        i7 = (int)this.paint.measureText(str4, 0, str4.length());
        int i8 = (n - i - m - j - i3 - i7 - 15) / 2;
        if (i8 < 0) {
          break label2070;
        }
        n = i8 + 5;
        this.anis[0].setFont(32, true);
        this.anis[0].AddTxtData(str3, n, 390);
        this.anis[0].setFont(48, true);
        this.anis[0].AddTxtData(str1, n + i + 5, 390);
        this.anis[0].setFont(32, true);
        this.anis[0].AddTxtData(str2, n + i + 5 + m, 390);
        this.anis[0].setFont(48, true);
        this.anis[0].AddTxtData((String)localObject1, n + i + 5 + m + j, 390);
        this.anis[0].setFont(32, true);
        this.anis[0].AddTxtData(str4, n + i + 5 + m + j + i3, 390);
      }
      label965:
      if (i2 > 0)
      {
        this.anis[0].AddImgData(2130837815, 9, 10);
        this.anis[0].setFont(36, false);
        this.anis[0].AddTxtDataCenter(2131165319, 42, 28, 140);
        this.anis[0].setFont(24, false);
        localObject1 = this.anis[0];
        if (!bool2) {
          break label2275;
        }
        i = 2131165320;
        label1046:
        ((makeBitmap)localObject1).AddTxtDataCenter(i, 20, 142, 176);
      }
      if ((!bool2) && (!bool1) && (0 == 0)) {
        break label2283;
      }
      this.anis[1] = new makeBitmap();
      this.anis[1].InitBgBmp(this.mAct.getResources(), this.anis[0].getImage());
      this.anis[2] = new makeBitmap();
      this.anis[2].InitBgBmp(this.mAct.getResources(), this.anis[0].getImage());
      this.anis[3] = new makeBitmap();
      this.anis[3].InitBgBmp(this.mAct.getResources(), this.anis[0].getImage());
      drawAniImgs(0, 0, i1, bool1, bool2, i2);
      this.image1.setImageBitmap(this.anis[0].getImage());
      drawAniImgs(1, 0, i1, bool1, bool2, i2);
      this.image2.setImageBitmap(this.anis[1].getImage());
      drawAniImgs(2, 0, i1, bool1, bool2, i2);
      this.image3.setImageBitmap(this.anis[2].getImage());
      drawAniImgs(3, 0, i1, bool1, bool2, i2);
      this.image4.setImageBitmap(this.anis[3].getImage());
      StartAnim();
      label1312:
      m = 0;
      i = 0;
      label1318:
      if (i < 4) {
        break label2390;
      }
      if (m != 0) {
        System.gc();
      }
      localObject1 = " ";
      i = DataGetter.g_datas((short)96);
      if ((i == 2) || (i == 3)) {
        break label2428;
      }
      bool1 = false;
      label1358:
      this.flag_battery = bool1;
      this.btnCHGTimer.setToggle(this.flag_battery);
      if (this.flag_battery) {
        localObject1 = getCurCHGTimerString();
      }
      this.txtchgTimer.setText((CharSequence)localObject1);
      i = DataGetter.g_datas((short)17);
      localObject1 = " ";
      localObject2 = localObject1;
      if (i != 0)
      {
        localObject2 = this.btnACTimer;
        if (i6 != 2) {
          break label2434;
        }
        bool1 = true;
        label1427:
        ((MyButton)localObject2).setToggle(bool1);
        if (i6 == 2) {
          localObject1 = getCurACTimerString();
        }
        i = DataGetter.g_datas((short)94);
        this.flag_flow = false;
        if ((quickCharge()) || (i != 0) || ((i4 == 1) && (i5 == 0))) {
          break label2440;
        }
        if (i5 == 1) {
          this.flag_flow = true;
        }
        this.btnACDoing.SetMyButtonEnable(true);
      }
    }
    for (;;)
    {
      this.btnACDoing.setToggle(this.flag_flow);
      localObject2 = localObject1;
      this.txtacTimer.setText((CharSequence)localObject2);
      return;
      if ((m == 1) && ((i == 1) || (i == 2) || (i == 3) || (i == 4) || (i == 5) || (i == 6)))
      {
        this.myBattWarmInd.setType(1);
        this.myBattWarmInd.show();
        break;
      }
      this.myBattWarmInd.hide();
      break;
      label1591:
      bool1 = true;
      break label122;
      label1597:
      if (i == 0)
      {
        n = 65535;
        break label152;
      }
      n = j;
      if (j != 65534) {
        break label152;
      }
      n = 60;
      break label152;
      label1629:
      if ((j == 1) && ((m == 0) || (m == 1)))
      {
        i = 5;
        break label181;
      }
      if (i == 2)
      {
        i = 3;
        break label181;
      }
      if (i == 1)
      {
        i = 2;
        break label181;
      }
      if (DataGetter.g_datas((short)104) == 1)
      {
        i = 1;
        break label181;
      }
      i = 0;
      break label181;
      label1697:
      bool2 = false;
      break label250;
      label1703:
      localObject2[j] = this.anis[j];
      j++;
      break label258;
      label1720:
      if (i == 3)
      {
        j = 2131165224;
        break label317;
      }
      if (i == 2)
      {
        j = 2131165224;
        break label317;
      }
      if (i != 5) {
        break label317;
      }
      j = 2131165227;
      break label317;
      label1762:
      m = 2130837662;
      break label383;
      label1770:
      m = 2130837664;
      break label400;
      label1778:
      if ((i == 3) || (i == 5))
      {
        if (i7 == 2) {}
        for (m = 2130837645;; m = 2130837660) {
          break;
        }
      }
      if (i != 2) {
        break label400;
      }
      if (i7 == 2) {}
      for (m = 2130837651;; m = 2130837666) {
        break;
      }
      label1840:
      i = 311;
      break label418;
      label1848:
      i3 = 14;
      break label428;
      label1855:
      this.anis[0].setFont(32, false);
      if (i7 == 2)
      {
        m = 2130837646;
        label1878:
        if (i != 4) {
          break label1971;
        }
        if (i7 != 2) {
          break label1963;
        }
        m = 2130837648;
        label1895:
        localObject1 = this.anis[0];
        if (i7 != 2) {
          break label2033;
        }
        i = 386;
        label1913:
        if (i7 != 2) {
          break label2041;
        }
      }
      label1963:
      label1971:
      label2033:
      label2041:
      for (i3 = 12;; i3 = 14)
      {
        ((makeBitmap)localObject1).AddImgData(m, i, i3);
        this.anis[0].AddTxtDataCenter(j, 396, 67, 180);
        break;
        m = 2130837661;
        break label1878;
        m = 2130837663;
        break label1895;
        if ((i == 3) || (i == 5))
        {
          if (i7 == 2) {}
          for (m = 2130837644;; m = 2130837659) {
            break;
          }
        }
        if (i != 2) {
          break label1895;
        }
        if (i7 == 2) {}
        for (m = 2130837650;; m = 2130837665) {
          break;
        }
        i = 391;
        break label1913;
      }
      label2048:
      localObject1 = "0" + i3;
      break label685;
      label2070:
      this.anis[0].setFont(32, true);
      this.anis[0].AddTxtDataRight(str4, n - i7 - 5, 390, i7);
      this.anis[0].setFont(48, true);
      this.anis[0].AddTxtDataRight((String)localObject1, n - i7 - i3 - 5, 390, i3);
      this.anis[0].setFont(32, true);
      this.anis[0].AddTxtDataRight(str2, n - i7 - i3 - j - 5, 390, j);
      this.anis[0].setFont(48, true);
      this.anis[0].AddTxtData(str1, n - i7 - i3 - j - m - 5, 390, m);
      this.anis[0].setFont(32, true);
      this.anis[0].AddTxtData(str3, 5, 390, n - i7 - i3 - j - m - 10);
      break label965;
      label2275:
      i = 2131165321;
      break label1046;
      label2283:
      this.timeMargin = 0;
      this.anis[1] = null;
      this.anis[2] = null;
      this.anis[3] = null;
      drawAniImgs(0, 0, i1, bool1, bool2, i2);
      this.image1.setImageBitmap(this.anis[0].getImage());
      this.image2.setImageBitmap(this.anis[0].getImage());
      this.image3.setImageBitmap(this.anis[0].getImage());
      this.image4.setImageBitmap(this.anis[0].getImage());
      break label1312;
      label2390:
      j = m;
      int k;
      if (localObject2[i] != null)
      {
        k = m | localObject2[i].freeBmp();
        localObject2[i] = null;
      }
      i++;
      m = k;
      break label1318;
      label2428:
      bool1 = true;
      break label1358;
      label2434:
      bool1 = false;
      break label1427;
      label2440:
      this.btnACDoing.SetMyButtonEnable(false);
    }
  }
  
  boolean ACTimerRight(int paramInt, boolean paramBoolean)
  {
    boolean bool2 = false;
    int i = DataGetter.g_datas_Day_PRESET_Hour((short)86, paramInt);
    int k = DataGetter.g_datas_Day_PRESET_Minute((short)91, paramInt);
    boolean bool1 = bool2;
    if (i < 24)
    {
      bool1 = bool2;
      if (k < 6)
      {
        if ((DefMsg.g_recdatas[125] == 1) || (!paramBoolean)) {
          break label111;
        }
        Calendar localCalendar = Calendar.getInstance();
        int j = localCalendar.get(11);
        paramInt = localCalendar.get(12);
        if (j >= i)
        {
          bool1 = bool2;
          if (j != i) {
            break label108;
          }
          bool1 = bool2;
          if (paramInt >= k * 10) {
            break label108;
          }
        }
      }
    }
    label108:
    label111:
    for (bool1 = true;; bool1 = true) {
      return bool1;
    }
  }
  
  boolean CHGTimerRight(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool2 = true;
    int n = DataGetter.g_datas_Day_PRESET_Hour((short)69, paramInt);
    int i1 = DataGetter.g_datas_Day_PRESET_Minute((short)74, paramInt);
    int i = DataGetter.g_datas_Day_PRESET_Hour((short)77, paramInt);
    int m = DataGetter.g_datas_Day_PRESET_Minute((short)82, paramInt);
    if (((n != i) || (i1 != m)) && (n < 24) && (i1 < 6) && (i < 24) && (m < 6))
    {
      bool1 = bool2;
      if (paramBoolean1)
      {
        Calendar localCalendar = Calendar.getInstance();
        int k = localCalendar.get(11);
        int j = localCalendar.get(12);
        if (i >= n)
        {
          paramInt = i;
          paramBoolean1 = paramBoolean2;
          if (i == n)
          {
            paramInt = i;
            paramBoolean1 = paramBoolean2;
            if (m >= i1) {}
          }
        }
        else
        {
          paramInt = i;
          if (paramBoolean2) {
            paramInt = i + 24;
          }
          paramBoolean1 = true;
        }
        if (!paramBoolean1) {
          break label191;
        }
        bool1 = bool2;
        if (k >= paramInt) {
          if ((k != paramInt) || (j >= m * 10)) {
            break label191;
          }
        }
      }
    }
    label191:
    for (boolean bool1 = bool2;; bool1 = false) {
      return bool1;
    }
  }
  
  public void PopupHelpInfo()
  {
    Intent localIntent = new Intent(this.mAct, BxShowHelp.class);
    Bundle localBundle = new Bundle();
    localBundle.putInt("CardNo", 11);
    localIntent.putExtras(localBundle);
    this.mAct.startActivityForResult(localIntent, 101);
  }
  
  public void Refresh()
  {
    FrmMain localFrmMain = (FrmMain)this.mAct;
    localFrmMain.SetTitleUpdateTimeText(updateTime());
    localFrmMain.showDemoBar();
    change_layout();
    setStatus();
  }
  
  public void StartAnim()
  {
    if (this.myhd != null)
    {
      this.myhd.removeMessages(1);
      this.myhd.sendEmptyMessageDelayed(1, 1000L);
    }
  }
  
  public void StopAnim()
  {
    if (this.myhd != null) {
      this.myhd.removeMessages(1);
    }
  }
  
  int calcBatterySegment()
  {
    int i = 16;
    int k = DataGetter.g_datas((short)99);
    if ((k > 100) || (k < 0)) {
      i = 0;
    }
    while (k >= 94) {
      return i;
    }
    for (int j = 0;; j++)
    {
      i = j;
      if (k < new int[] { 6, 12, 18, 24, 29, 35, 41, 47, 53, 59, 65, 71, 76, 82, 88, 94 }[j]) {
        break;
      }
    }
  }
  
  public void change_layout()
  {
    int j = 8;
    LinearLayout localLinearLayout = this.modechg;
    if (1 == 0)
    {
      i = 8;
      localLinearLayout.setVisibility(i);
      localLinearLayout = this.modeac;
      if (1 != 0) {
        break label42;
      }
    }
    label42:
    for (int i = j;; i = 0)
    {
      localLinearLayout.setVisibility(i);
      return;
      i = 0;
      break;
    }
  }
  
  void drawAniImgs(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, int paramInt4)
  {
    int i = paramInt3;
    if (paramInt2 > 0)
    {
      if (paramInt2 != 2) {
        break label293;
      }
      i = paramInt3;
      if (paramInt1 % 2 != 0) {
        i = 0;
      }
    }
    this.anis[paramInt1].AddImgData(new int[] { 2130837542, 2130837543, 2130837551, 2130837552, 2130837553, 2130837554, 2130837555, 2130837556, 2130837557, 2130837558, 2130837544, 2130837545, 2130837546, 2130837547, 2130837548, 2130837549, 2130837550 }[i], 176, 254);
    if (paramBoolean1)
    {
      localObject = new Rect(290, 256, 344, 340);
      this.anis[paramInt1].AddImgData(new int[] { 2130837612, 2130837613, 2130837614, 2130837615 }[paramInt1], (Rect)localObject);
    }
    Object localObject = null;
    if (paramInt4 == 1)
    {
      localObject = new int[] { 2130837505, 2130837506, 2130837507, 2130837508 };
      label263:
      if (localObject != null) {
        if (!paramBoolean2) {
          break label384;
        }
      }
    }
    label293:
    label384:
    for (paramInt2 = paramInt1;; paramInt2 = 0)
    {
      this.anis[paramInt1].AddImgData(localObject[paramInt2], 52, 36);
      return;
      i = paramInt3;
      if (paramInt1 / 2 == 0) {
        break;
      }
      i = 0;
      break;
      if (paramInt4 == 2)
      {
        localObject = new int[] { 2130837513, 2130837514, 2130837515, 2130837516 };
        break label263;
      }
      if (paramInt4 != 3) {
        break label263;
      }
      localObject = new int[] { 2130837509, 2130837510, 2130837511, 2130837512 };
      break label263;
    }
  }
  
  boolean findRightACTimer()
  {
    boolean bool = false;
    for (int i = 0;; i++)
    {
      if (i >= 7) {}
      for (;;)
      {
        return bool;
        if (!ACTimerRight(i, false)) {
          break;
        }
        bool = true;
      }
    }
  }
  
  boolean findRightCHGTimer()
  {
    boolean bool2 = true;
    for (int i = 0;; i++)
    {
      boolean bool1;
      if (i >= 7) {
        bool1 = false;
      }
      do
      {
        return bool1;
        bool1 = bool2;
      } while (CHGTimerRight(i, false, true));
    }
  }
  
  String getACTimerString(int paramInt)
  {
    int j = DataGetter.g_datas_Day_PRESET_Hour(, paramInt);
    int i = DataGetter.g_datas_Day_PRESET_Minute((short)91, paramInt);
    if ((j < 24) && (i < 6)) {
      str = " " + this.mAct.getString(2131165202);
    }
    for (String str = DealMsg.getWeekString(this.mAct, paramInt) + " " + DealMsg.getTimeString(j, i * 10) + str;; str = "") {
      return str;
    }
  }
  
  String getCHGTimerString(int paramInt)
  {
    int i = DataGetter.g_datas_Day_PRESET_Hour(, paramInt);
    int m = DataGetter.g_datas_Day_PRESET_Minute((short)74, paramInt);
    int k = DataGetter.g_datas_Day_PRESET_Hour((short)77, paramInt);
    int j = DataGetter.g_datas_Day_PRESET_Minute((short)82, paramInt);
    if (((i != k) || (m != j)) && (i < 24) && (m < 6) && (k < 24) && (j < 6)) {
      str = " " + this.mAct.getString(2131165202) + " ";
    }
    for (String str = DealMsg.getWeekString(this.mAct, paramInt) + " " + DealMsg.getTimeString(i, m * 10) + str + DealMsg.getTimeString(k, j * 10);; str = "") {
      return str;
    }
  }
  
  int getChgTimeMin()
  {
    byte[] arrayOfByte = DataGetter.g_datas(, 2);
    int i;
    if (arrayOfByte[1] < 0)
    {
      i = arrayOfByte[1] + 256;
      if (arrayOfByte[0] >= 0) {
        break label50;
      }
    }
    label50:
    for (int j = arrayOfByte[0] + 256;; j = arrayOfByte[0])
    {
      return i * 256 + j;
      i = arrayOfByte[1];
      break;
    }
  }
  
  String getCurACTimerString()
  {
    int j = Calendar.getInstance().get(7) - 1;
    int i = j;
    if (i >= 7) {}
    label67:
    label91:
    for (i = 0;; i++)
    {
      String str;
      if (i >= j) {
        str = getACTimerString(j);
      }
      for (;;)
      {
        return str;
        if (i == j) {}
        for (boolean bool = true;; bool = false)
        {
          if (!ACTimerRight(i, bool)) {
            break label67;
          }
          str = getACTimerString(i);
          break;
        }
        i++;
        break;
        if (!ACTimerRight(i, false)) {
          break label91;
        }
        str = getACTimerString(i);
      }
    }
  }
  
  String getCurCHGTimerString()
  {
    int j = Calendar.getInstance().get(7) - 1;
    int k = j - 1;
    int i = k;
    if (k < 0) {
      i = 6;
    }
    String str;
    if (CHGTimerRight(i, true, false))
    {
      str = getCHGTimerString(i);
      return str;
    }
    i = j;
    label47:
    if (i >= 7) {}
    for (i = 0;; i++)
    {
      if (i >= j)
      {
        str = getCHGTimerString(j);
        break;
        if (i == j) {}
        for (boolean bool = true;; bool = false)
        {
          if (!CHGTimerRight(i, bool, true)) {
            break label103;
          }
          str = getCHGTimerString(i);
          break;
        }
        label103:
        i++;
        break label47;
      }
      if (CHGTimerRight(i, false, true))
      {
        str = getCHGTimerString(i);
        break;
      }
    }
  }
  
  public void onclickACDoingOnOff()
  {
    int k = 1;
    boolean bool = true;
    Object localObject = DealMsg.getSSID();
    if ((ConnSSID.isCurSSIDError(this.mAct, (String)localObject)) && (!DealMsg.g_isDemo)) {
      this.mAct.ShowSSIDError();
    }
    label121:
    int j;
    label127:
    do
    {
      return;
      if ((DefMsg.g_datas[0] == 1) && ((DefMsg.g_datas[98] & 0xF) == 0))
      {
        DefMsg.updateAll();
        if ((DefMsg.g_datas[0] == 1) && ((DefMsg.g_datas[98] & 0xF) == 0)) {}
        for (;;)
        {
          this.bShowErrWhenEnd = bool;
          showUpdating();
          this.myhd.sendEmptyMessageDelayed(12, 5000L);
          break;
          bool = false;
        }
      }
      if (!this.flag_flow) {
        break;
      }
      i = 0;
      if (i == 0) {
        break label243;
      }
      j = 2;
      localObject = CmdMake.KO_WF_MANUAL_AC_ON_RQ_SP((byte)j);
      if (DefMsg.g_datas[0] == 0) {
        MsgSender.Send(CmdMake.KO_WF_AC_SCH_SP((byte)((byte)(DefMsg.g_datas[98] & 0xF0) | 0x1)));
      }
      MsgSender.Send((byte[])localObject);
    } while (!DealMsg.g_isDemo);
    if ((DefMsg.g_recdatas[98] & 0xF) == 0) {
      DefMsg.g_recdatas[98] = ((byte)(DefMsg.g_recdatas[98] & 0xF1));
    }
    localObject = DefMsg.g_recdatas;
    if (i != 0)
    {
      j = 1;
      label212:
      localObject[95] = ((byte)j);
      localObject = DefMsg.g_recdatas;
      if (i == 0) {
        break label253;
      }
    }
    label243:
    label253:
    for (int i = k;; i = 0)
    {
      localObject[125] = ((byte)i);
      break;
      i = 1;
      break label121;
      j = 1;
      break label127;
      j = 0;
      break label212;
    }
  }
  
  public void onclickACTimerOnOff()
  {
    String str = DealMsg.getSSID();
    if ((ConnSSID.isCurSSIDError(this.mAct, str)) && (!DealMsg.g_isDemo))
    {
      this.mAct.ShowSSIDError();
      return;
    }
    if (!findRightACTimer())
    {
      DefMsg.updateAll();
      if (findRightACTimer()) {}
      for (boolean bool = false;; bool = true)
      {
        this.bShowErrWhenEnd = bool;
        showUpdating();
        this.myhd.sendEmptyMessageDelayed(11, 5000L);
        break;
      }
    }
    DefMsg.copyACSend();
    if (DataGetter.g_datas((short)85) == 2) {}
    for (byte b = 1;; b = 2)
    {
      MsgSender.Send(CmdMake.KO_WF_TM_AC_ON_RQ_SP(b));
      if (!DealMsg.g_isDemo) {
        break;
      }
      DefMsg.g_recdatas[85] = b;
      if ((DefMsg.g_recdatas[98] & 0xF) != 0) {
        break;
      }
      DefMsg.g_recdatas[98] = ((byte)(DefMsg.g_recdatas[98] & 0xF1));
      break;
    }
  }
  
  void onclickTimerOnOff()
  {
    int j = 0;
    boolean bool = false;
    Object localObject = DealMsg.getSSID();
    if ((ConnSSID.isCurSSIDError(this.mAct, (String)localObject)) && (!DealMsg.g_isDemo)) {
      this.mAct.ShowSSIDError();
    }
    label97:
    byte b;
    label117:
    do
    {
      return;
      if (!findRightCHGTimer())
      {
        DefMsg.updateAll();
        if (findRightCHGTimer()) {}
        for (;;)
        {
          this.bShowErrWhenEnd = bool;
          showUpdating();
          this.myhd.sendEmptyMessageDelayed(11, 5000L);
          break;
          bool = true;
        }
      }
      DefMsg.copyCHGSend();
      if (!this.flag_battery) {
        break;
      }
      bool = false;
      this.flag_battery = bool;
      if (!this.flag_battery) {
        break label225;
      }
      b = DefMsg.g_recdatas[97];
      MsgSender.Send(CmdMake.KO_WF_TM_CHG_ON_RQ_SP(b));
    } while (!DealMsg.g_isDemo);
    DefMsg.g_recdatas[96] = b;
    localObject = DefMsg.g_recdatas;
    if (this.flag_battery)
    {
      i = 2;
      label151:
      localObject[103] = ((byte)i);
      localObject = DefMsg.g_recdatas;
      if (!this.flag_battery) {
        break label235;
      }
      i = 2;
      label170:
      localObject[2] = ((byte)i);
      localObject = DefMsg.g_recdatas;
      if (!this.flag_battery) {
        break label240;
      }
      i = 150;
      label190:
      localObject[106] = ((byte)i);
      localObject = DefMsg.g_recdatas;
      if (!this.flag_battery) {
        break label246;
      }
    }
    label225:
    label235:
    label240:
    label246:
    for (int i = j;; i = 23)
    {
      localObject[107] = ((byte)i);
      break;
      bool = true;
      break label97;
      b = 1;
      break label117;
      i = 0;
      break label151;
      i = 0;
      break label170;
      i = 112;
      break label190;
    }
  }
  
  boolean quickCharge()
  {
    boolean bool2 = true;
    int i = DataGetter.g_datas((short)19);
    int m = DataGetter.g_datas((short)18);
    int k = DataGetter.g_datas((short)105);
    int j = getChgTimeMin();
    boolean bool1;
    if (i == 3) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      if ((m == 4) && (k == 0))
      {
        bool1 = bool2;
        if (j == 65534) {}
      }
      else
      {
        bool1 = bool2;
        if (m != 5) {
          bool1 = false;
        }
      }
    }
  }
  
  void showACErr()
  {
    showMsg(2131165324);
  }
  
  void showCHGErr()
  {
    showMsg(2131165323);
  }
  
  void showMsg(int paramInt)
  {
    
    if (this.bShowErrWhenEnd)
    {
      myAlertDlg localmyAlertDlg = new myAlertDlg(this.mAct);
      localmyAlertDlg.setDlgContent(0, paramInt, true);
      localmyAlertDlg.show();
    }
  }
  
  public void showUpdating()
  {
    myWaitDlg.MyShowWaitDlg(this.mAct, 5200);
    myWaitDlg.setWaitResult(3);
  }
  
  public String updateTime()
  {
    int i = DefMsg.g_recdatas['Ã'] + 2000;
    int j = DefMsg.g_recdatas['Ä'];
    int k = DefMsg.g_recdatas['Å'];
    int m = DefMsg.g_recdatas['Æ'];
    int n = DefMsg.g_recdatas['Ç'];
    if ((i == 0) || (j == 0) || (k == 0)) {
      str = DealMsg.getTimeString(25, 61);
    }
    for (String str = this.mAct.getString(2131165305).replace("%y", "----").replace("%m", "--").replace("%d", "--").replace("%t", str);; str = this.mAct.getString(2131165305).replace("%y", i).replace("%m", j).replace("%d", k).replace("%t", str))
    {
      return str;
      str = DealMsg.getTimeString(m, n);
    }
  }
  
  class BattWarmIndicator
  {
    static final int IND_STAT_OFF = 0;
    static final int IND_STAT_ON = 1;
    static final int IND_TYPE_A = 0;
    static final int IND_TYPE_B = 1;
    Bitmap batHeatIndOff;
    Bitmap batHeatIndOn;
    Bitmap batWarmIndOff;
    Bitmap batWarmIndOn;
    Bitmap curIndOff;
    Bitmap curIndOn;
    int currentType;
    ImageView myImage;
    
    public BattWarmIndicator(ImageView paramImageView, Bitmap paramBitmap1, Bitmap paramBitmap2, Bitmap paramBitmap3, Bitmap paramBitmap4)
    {
      this.myImage = paramImageView;
      this.batWarmIndOff = paramBitmap1;
      this.batWarmIndOn = paramBitmap2;
      this.batHeatIndOff = paramBitmap3;
      this.batHeatIndOn = paramBitmap4;
      setType(0);
    }
    
    public int getType()
    {
      return this.currentType;
    }
    
    public void hide()
    {
      this.myImage.setVisibility(8);
    }
    
    public void setOnClickListener(View.OnClickListener paramOnClickListener)
    {
      this.myImage.setOnClickListener(paramOnClickListener);
    }
    
    public void setOnTouchListener(View.OnTouchListener paramOnTouchListener)
    {
      this.myImage.setOnTouchListener(paramOnTouchListener);
    }
    
    public void setStatus(int paramInt)
    {
      if (paramInt == 0) {
        this.myImage.setImageBitmap(this.curIndOff);
      }
      for (;;)
      {
        return;
        this.myImage.setImageBitmap(this.curIndOn);
      }
    }
    
    public void setType(int paramInt)
    {
      this.currentType = paramInt;
      if (paramInt == 1) {
        this.curIndOff = this.batHeatIndOff;
      }
      for (this.curIndOn = this.batHeatIndOn;; this.curIndOn = this.batWarmIndOn)
      {
        this.myImage.setImageBitmap(this.curIndOff);
        return;
        this.curIndOff = this.batWarmIndOff;
      }
    }
    
    public void show()
    {
      this.myImage.setVisibility(0);
    }
  }
  
  class MyHandler
    extends Handler
  {
    MyHandler() {}
    
    public void handleMessage(Message paramMessage)
    {
      int j = 0;
      int i;
      if (paramMessage.what == 1)
      {
        paramMessage = MvHome.this;
        paramMessage.timeMargin += 1;
        paramMessage = MvHome.this;
        paramMessage.timeMargin %= 4;
        paramMessage = MvHome.this.image1;
        if (MvHome.this.timeMargin == 0)
        {
          i = 0;
          paramMessage.setVisibility(i);
          paramMessage = MvHome.this.image2;
          if (MvHome.this.timeMargin != 1) {
            break label158;
          }
          i = 0;
          label86:
          paramMessage.setVisibility(i);
          paramMessage = MvHome.this.image3;
          if (MvHome.this.timeMargin != 2) {
            break label163;
          }
          i = 0;
          label112:
          paramMessage.setVisibility(i);
          paramMessage = MvHome.this.image4;
          if (MvHome.this.timeMargin != 3) {
            break label168;
          }
          i = j;
          label138:
          paramMessage.setVisibility(i);
          sendEmptyMessageDelayed(1, 500L);
        }
      }
      for (;;)
      {
        return;
        i = 4;
        break;
        label158:
        i = 4;
        break label86;
        label163:
        i = 4;
        break label112;
        label168:
        i = 4;
        break label138;
        if (paramMessage.what == 11) {
          MvHome.this.showCHGErr();
        } else if (paramMessage.what == 12) {
          MvHome.this.showACErr();
        } else {
          i = paramMessage.what;
        }
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\MvHome.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */