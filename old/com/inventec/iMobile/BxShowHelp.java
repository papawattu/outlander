package com.inventec.iMobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyViewPager;
import com.inventec.controls.makeBitmap;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseOP.DataGetter;
import com.inventec.iMobile.mySettingItem.ItemOnClick;
import com.inventec.iMobile.mySettingItem.SettingItem;
import com.inventec.iMobile.mySettingItem.SettingItem_Normal;
import com.inventec.iMobile.mySettingItem.SettingListAdapter;
import java.util.ArrayList;
import java.util.List;

public class BxShowHelp
  extends myActivity
{
  final int PAGE_NUMBER_ALL = 2000;
  RadioButton card1;
  RadioButton card2;
  RadioButton card3;
  RadioButton card4;
  RadioButton card5;
  int cardno = 0;
  private int homeHelpPageNum = 5;
  ImageView imgHead;
  ImageView imgPos;
  ImageView imgaccool;
  ImageView imgacdef;
  ImageView imgachot;
  ImageView imgchg;
  private List<View> mListViews;
  private MyViewPager myViewPager;
  MyHandler myhd = null;
  boolean timeBlink = true;
  int timeMargin = 0;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getExtras();
    if (paramBundle != null) {
      this.cardno = paramBundle.getInt("CardNo");
    }
    if ((com.inventec.iMobile.baseStruct.DefMsg.g_datas[1] == 0) || (com.inventec.iMobile.baseStruct.DefMsg.g_datas[1] == 1)) {}
    for (this.homeHelpPageNum = 5;; this.homeHelpPageNum = 4)
    {
      paramBundle = new MvShowHelp(this);
      SetMainView(paramBundle);
      paramBundle.setScreen();
      hideTabBar();
      hideUpdateBtn();
      return;
    }
  }
  
  class MvShowHelp
    extends myMainView
  {
    makeBitmap ibmp = null;
    ImageView img;
    ArrayList<SettingItem> mListSetting;
    
    public MvShowHelp(myActivity parammyActivity)
    {
      super();
      switch (BxShowHelp.this.cardno)
      {
      }
      for (;;)
      {
        return;
        initView(2130903060);
        BxShowHelp.this.myViewPager = ((MyViewPager)this.mView.findViewById(2131296328));
        BxShowHelp.this.myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
          public void onPageScrollStateChanged(int paramAnonymousInt) {}
          
          public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2) {}
          
          public void onPageSelected(int paramAnonymousInt)
          {
            RadioButton localRadioButton1 = BxShowHelp.this.card1;
            RadioButton localRadioButton4 = BxShowHelp.this.card2;
            RadioButton localRadioButton2 = BxShowHelp.this.card3;
            RadioButton localRadioButton3 = BxShowHelp.this.card4;
            RadioButton localRadioButton5 = BxShowHelp.this.card5;
            int i = BxShowHelp.this.homeHelpPageNum;
            new RadioButton[] { localRadioButton1, localRadioButton4, localRadioButton2, localRadioButton3, localRadioButton5 }[(paramAnonymousInt % i)].setChecked(true);
          }
        });
        BxShowHelp.this.card1 = ((RadioButton)this.mView.findViewById(2131296400));
        BxShowHelp.this.card2 = ((RadioButton)this.mView.findViewById(2131296401));
        BxShowHelp.this.card3 = ((RadioButton)this.mView.findViewById(2131296402));
        BxShowHelp.this.card4 = ((RadioButton)this.mView.findViewById(2131296403));
        BxShowHelp.this.card5 = ((RadioButton)this.mView.findViewById(2131296404));
        View localView1 = this.mView.findViewById(2131296265);
        View localView4 = this.mView.findViewById(2131296291);
        View localView2 = this.mView.findViewById(2131296367);
        View localView3 = this.mView.findViewById(2131296386);
        parammyActivity = this.mView.findViewById(2131296396);
        BxShowHelp.this.mListViews = new ArrayList();
        BxShowHelp.this.mListViews.add(localView1);
        BxShowHelp.this.mListViews.add(localView4);
        BxShowHelp.this.mListViews.add(localView2);
        BxShowHelp.this.mListViews.add(localView3);
        if (BxShowHelp.this.homeHelpPageNum >= 5)
        {
          BxShowHelp.this.mListViews.add(parammyActivity);
          label404:
          BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165314));
          this.ibmp = new makeBitmap();
          this.ibmp.InitBgBmp(this.mAct.getResources(), 2130837663);
          this.ibmp.setFont(32, false);
          this.ibmp.AddTxtDataCenter(2131165222, 6, 48, 180);
          this.img = ((ImageView)this.mView.findViewById(2131296332));
          this.img.setImageBitmap(this.ibmp.getImage());
          this.ibmp = new makeBitmap();
          this.ibmp.InitBgBmp(this.mAct.getResources(), 2130837659);
          this.ibmp.setFont(32, false);
          this.ibmp.AddTxtDataCenter(2131165224, 6, 48, 180);
          this.img = ((ImageView)this.mView.findViewById(2131296335));
          this.img.setImageBitmap(this.ibmp.getImage());
          this.ibmp = new makeBitmap();
          this.ibmp.InitBgBmp(this.mAct.getResources(), 2130837665);
          this.ibmp.setFont(32, false);
          this.ibmp.AddTxtDataCenter(2131165224, 6, 48, 180);
          this.img = ((ImageView)this.mView.findViewById(2131296338));
          this.img.setImageBitmap(this.ibmp.getImage());
          this.ibmp = new makeBitmap();
          this.ibmp.InitBgBmp(this.mAct.getResources(), 2130837661);
          this.ibmp.setFont(32, false);
          this.ibmp.AddTxtDataCenter(2131165219, 6, 48, 180);
          this.img = ((ImageView)this.mView.findViewById(2131296341));
          this.img.setImageBitmap(this.ibmp.getImage());
          BxShowHelp.this.imgchg = ((ImageView)this.mView.findViewById(2131296329));
          BxShowHelp.this.imgaccool = ((ImageView)this.mView.findViewById(2131296354));
          BxShowHelp.this.imgachot = ((ImageView)this.mView.findViewById(2131296359));
          BxShowHelp.this.imgacdef = ((ImageView)this.mView.findViewById(2131296364));
          BxShowHelp.this.myhd = new BxShowHelp.MyHandler(BxShowHelp.this);
          BxShowHelp.this.myhd.sendEmptyMessageDelayed(1, 1000L);
          parammyActivity = new MyPagerAdapter(null);
          BxShowHelp.this.myViewPager.setAdapter(parammyActivity);
          BxShowHelp.this.myViewPager.setCurrentItem(1000 / BxShowHelp.this.homeHelpPageNum * BxShowHelp.this.homeHelpPageNum);
          enlargeText(new int[] { 2131296331, 2131296334, 2131296337, 2131296340, 2131296343, 2131296345, 2131296347, 2131296349, 2131296351, 2131296353, 2131296356, 2131296358, 2131296361, 2131296363, 2131296366, 2131296370, 2131296373, 2131296376, 2131296379, 2131296382, 2131296385, 2131296389, 2131296392, 2131296395, 2131296398, 2131296330, 2131296333, 2131296336, 2131296339, 2131296342, 2131296344, 2131296346, 2131296348, 2131296350, 2131296352, 2131296355, 2131296357, 2131296360, 2131296362, 2131296365, 2131296369, 2131296372, 2131296375, 2131296378, 2131296381, 2131296384, 2131296388, 2131296391, 2131296394, 2131296397 });
          this$1 = new int[6];
          BxShowHelp tmp1201_1200 = BxShowHelp.this;
          tmp1201_1200[0] = 2131296368;
          BxShowHelp tmp1207_1201 = tmp1201_1200;
          tmp1207_1201[1] = 2131296371;
          BxShowHelp tmp1213_1207 = tmp1207_1201;
          tmp1213_1207[2] = 2131296374;
          BxShowHelp tmp1219_1213 = tmp1213_1207;
          tmp1219_1213[3] = 2131296377;
          BxShowHelp tmp1225_1219 = tmp1219_1213;
          tmp1225_1219[4] = 2131296380;
          BxShowHelp tmp1231_1225 = tmp1225_1219;
          tmp1231_1225[5] = 2131296383;
          tmp1231_1225;
          enlargeText(BxShowHelp.this);
        }
        for (int i = 0;; i++)
        {
          if (i >= BxShowHelp.this.length)
          {
            enlargeNoneText(new int[] { 2131296387, 2131296390, 2131296393 });
            break;
            BxShowHelp.this.card5.setVisibility(8);
            break label404;
          }
          ((AutoResizeTextView)this.mView.findViewById(BxShowHelp.this[i])).setHVType(1, 1);
        }
        initView(2130903051);
        initListSetting(2131165248, 2131165249);
        BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165247));
        continue;
        initView(2130903052);
        setWebView(2131296292, 2131165252);
        BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165247));
        this.ibmp = new makeBitmap();
        this.ibmp.InitBgBmp(this.mAct.getResources(), 2130837636);
        this.ibmp.setFont(32, false);
        this.ibmp.AddTxtDataCenter(2131165250, 40, 40, 180);
        this.ibmp.AddTxtDataCenter(2131165251, 390, 40, 180);
        this.img = ((ImageView)this.mView.findViewById(2131296290));
        this.img.setImageBitmap(this.ibmp.getImage());
        continue;
        initView(2130903053);
        setWebView(2131296292, 2131165259);
        BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165247));
        this.ibmp = new makeBitmap();
        this.ibmp.InitBgBmp(this.mAct.getResources(), 2130837637);
        this.ibmp.setFont(28, false);
        this.ibmp.AddTxtData(2131165253, 18, 30);
        this.ibmp.AddTxtData(2131165254, 130, 76, 370);
        this.ibmp.AddTxtData(2131165254, 240, 180, 370);
        this.ibmp.AddTxtDataRight(2131165255, 270, 270, 360);
        this.ibmp.AddTxtData(2131165256, 80, 320);
        this.ibmp.AddTxtData(2131165257, 400, 320);
        this.ibmp.AddTxtDataCenter(2131165258, 4, 360, 630);
        this.img = ((ImageView)this.mView.findViewById(2131296290));
        this.img.setImageBitmap(this.ibmp.getImage());
        continue;
        initView(2130903051);
        initListSetting(2131165248, 2131165260);
        BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165247));
        continue;
        initView(2130903054);
        setWebView(2131296292, 2131165252);
        BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165247));
        this.ibmp = new makeBitmap();
        this.ibmp.InitBgBmp(this.mAct.getResources(), 2130837636);
        this.ibmp.setFont(32, false);
        this.ibmp.AddTxtDataCenter(2131165250, 40, 40, 180);
        this.ibmp.AddTxtDataCenter(2131165251, 390, 40, 180);
        this.img = ((ImageView)this.mView.findViewById(2131296290));
        this.img.setImageBitmap(this.ibmp.getImage());
        continue;
        initView(2130903055);
        setWebView(2131296292, 2131165264);
        BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165247));
        this.ibmp = new makeBitmap();
        this.ibmp.InitBgBmp(this.mAct.getResources(), 2130837638);
        this.ibmp.setFont(28, false);
        this.ibmp.AddTxtDataCenter(2131165261, 18, 30, 300);
        this.ibmp.AddTxtDataCenter(2131165262, 130, 106, 450);
        this.ibmp.AddTxtDataCenter(2131165263, 200, 370, 460);
        this.img = ((ImageView)this.mView.findViewById(2131296290));
        this.img.setImageBitmap(this.ibmp.getImage());
        continue;
        initView(2130903051);
        initListSetting(2131165266, 2131165267);
        BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165265));
        continue;
        initView(2130903049);
        setWebView(2131296292, 2131165268);
        BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165265));
        this.ibmp = new makeBitmap();
        this.ibmp.InitBgBmp(this.mAct.getResources(), 2130837634);
        this.ibmp.setFont(32, false);
        this.ibmp.AddTxtDataCenter(2131165250, 40, 40, 180);
        this.ibmp.AddTxtDataCenter(2131165251, 390, 40, 180);
        this.img = ((ImageView)this.mView.findViewById(2131296290));
        this.img.setImageBitmap(this.ibmp.getImage());
        continue;
        initView(2130903050);
        setWebView(2131296292, 2131165269);
        BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165265));
        continue;
        initView(2130903062);
        BxShowHelp.this.myViewPager = ((MyViewPager)this.mView.findViewById(2131296328));
        BxShowHelp.this.myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
          public void onPageScrollStateChanged(int paramAnonymousInt) {}
          
          public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2) {}
          
          public void onPageSelected(int paramAnonymousInt)
          {
            paramAnonymousInt %= 2;
            if (paramAnonymousInt == 0) {
              BxShowHelp.this.card1.setChecked(true);
            }
            for (;;)
            {
              return;
              if (paramAnonymousInt == 1) {
                BxShowHelp.this.card2.setChecked(true);
              }
            }
          }
        });
        BxShowHelp.this.card1 = ((RadioButton)this.mView.findViewById(2131296400));
        BxShowHelp.this.card2 = ((RadioButton)this.mView.findViewById(2131296401));
        localView1 = this.mView.findViewById(2131296265);
        parammyActivity = this.mView.findViewById(2131296291);
        enlargeText(new int[] { 2131296407, 2131296409, 2131296412, 2131296415, 2131296418, 2131296406, 2131296408, 2131296411, 2131296414, 2131296417 });
        if (!DataGetter.canDealTheftAlerm())
        {
          this.mView.findViewById(2131296416).setVisibility(8);
          this.mView.findViewById(2131296413).setVisibility(8);
          this.mView.findViewById(2131296410).setVisibility(8);
        }
        BxShowHelp.this.mListViews = new ArrayList();
        BxShowHelp.this.mListViews.add(localView1);
        BxShowHelp.this.mListViews.add(parammyActivity);
        setWebView(2131296292, 2131165277);
        BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165270));
        parammyActivity = new MyPagerAdapter(null);
        BxShowHelp.this.myViewPager.setAdapter(parammyActivity);
        BxShowHelp.this.myViewPager.setTwoPage(true);
        BxShowHelp.this.myViewPager.setCurrentItem(1000);
        continue;
        initView(2130903056);
        enlargeText(new int[] { 2131296295, 2131296297, 2131296294, 2131296296 });
        BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165278));
        continue;
        initView(2130903057);
        enlargeText(new int[] { 2131296267, 2131296273, 2131296269, 2131296308, 2131296312, 2131296316, 2131296320, 2131296324, 2131296300, 2131296302, 2131296304, 2131296307, 2131296311, 2131296315, 2131296319, 2131296323 });
        enlargeNoneText(new int[] { 2131296299, 2131296301, 2131296303, 2131296306, 2131296310, 2131296314, 2131296318, 2131296322 });
        BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165281));
        BxShowHelp.this.imgHead = ((ImageView)this.mView.findViewById(2131296301));
        BxShowHelp.this.imgPos = ((ImageView)this.mView.findViewById(2131296310));
        BxShowHelp.this.myhd = new BxShowHelp.MyHandler(BxShowHelp.this);
        BxShowHelp.this.myhd.sendEmptyMessageDelayed(2, 1000L);
        if (com.inventec.iMobile.baseStruct.DefMsg.g_datas[15] != 1)
        {
          this.mView.findViewById(2131296317).setVisibility(8);
          this.mView.findViewById(2131296321).setVisibility(8);
          continue;
          initView(2130903058);
          enlargeText(new int[] { 2131296267, 2131296273, 2131296269, 2131296308, 2131296312, 2131296316, 2131296320, 2131296300, 2131296302, 2131296304, 2131296307, 2131296311, 2131296315, 2131296319 });
          enlargeNoneText(new int[] { 2131296299, 2131296301, 2131296303, 2131296306, 2131296310, 2131296314, 2131296318 });
          BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165290));
          continue;
          initView(2130903061);
          BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165298));
          setWebView(2131296405, 2131165299);
          continue;
          initView(2130903059);
          BxShowHelp.this.SetTitleText(BxShowHelp.this.getString(2131165300));
          setWebView(2131296327, 2131165302);
          ((WebView)this.mView.findViewById(2131296327)).setOnTouchListener(new View.OnTouchListener()
          {
            public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
            {
              return true;
            }
          });
          enlargeText(new int[] { 2131296326 });
          enlargeNoneText(new int[] { 2131296327 });
        }
      }
    }
    
    private void initListSetting(int paramInt1, int paramInt2)
    {
      this.mListSetting = new ArrayList();
      Object localObject = new SettingItem_Normal(paramInt1, 0);
      ((SettingItem)localObject).SetOnTrigger(new ItemOnClick()
      {
        public void OnClick(SettingItem paramAnonymousSettingItem)
        {
          BxShowHelp.MvShowHelp.this.doListItemClicked(1);
        }
      });
      ((SettingItem)localObject).setTextSizeScale(this.mAct.getScrScale());
      this.mListSetting.add(localObject);
      localObject = new SettingItem_Normal(paramInt2, 1);
      ((SettingItem)localObject).SetOnTrigger(new ItemOnClick()
      {
        public void OnClick(SettingItem paramAnonymousSettingItem)
        {
          BxShowHelp.MvShowHelp.this.doListItemClicked(2);
        }
      });
      ((SettingItem)localObject).setTextSizeScale(this.mAct.getScrScale());
      this.mListSetting.add(localObject);
      SettingListAdapter localSettingListAdapter = new SettingListAdapter(this.mAct, this.mListSetting);
      localObject = (ListView)this.mView.findViewById(2131296280);
      ((ListView)localObject).setAdapter(localSettingListAdapter);
      ((ListView)localObject).setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          ((SettingItem)BxShowHelp.MvShowHelp.this.mListSetting.get(paramAnonymousInt)).Trigger();
        }
      });
    }
    
    void doListItemClicked(int paramInt)
    {
      Intent localIntent = new Intent(this.mAct, BxShowHelp.class);
      int i = 0;
      if (BxShowHelp.this.cardno == 21) {
        if (paramInt == 1) {
          i = 22;
        }
      }
      do
      {
        for (;;)
        {
          Bundle localBundle = new Bundle();
          localBundle.putInt("CardNo", i);
          localIntent.putExtras(localBundle);
          this.mAct.startActivityForResult(localIntent, 101);
          return;
          i = 23;
        }
        if (BxShowHelp.this.cardno == 24)
        {
          if (paramInt == 1) {}
          for (i = 25;; i = 26) {
            break;
          }
        }
      } while (BxShowHelp.this.cardno != 27);
      if (paramInt == 1) {}
      for (i = 28;; i = 29) {
        break;
      }
    }
    
    public void enlargeNoneText(int[] paramArrayOfInt)
    {
      for (int i = 0;; i++)
      {
        if (i >= paramArrayOfInt.length) {
          return;
        }
        View localView = this.mView.findViewById(paramArrayOfInt[i]);
        this.mAct.enlargeViewToFitScreen(localView, false);
      }
    }
    
    public void enlargeText(int[] paramArrayOfInt)
    {
      for (int i = 0;; i++)
      {
        if (i >= paramArrayOfInt.length) {
          return;
        }
        TextView localTextView = (TextView)this.mView.findViewById(paramArrayOfInt[i]);
        this.mAct.enlargeViewToFitScreen(localTextView, true);
      }
    }
    
    public void setScreen()
    {
      switch (BxShowHelp.this.cardno)
      {
      }
      for (;;)
      {
        return;
        ((LinearLayout)this.mView.findViewById(2131296325)).getLayoutParams().height = -1;
      }
    }
    
    void setWebView(int paramInt1, int paramInt2)
    {
      WebView localWebView = (WebView)this.mView.findViewById(paramInt1);
      if (localWebView == null) {}
      for (;;)
      {
        return;
        iMobile_AppGlobalVar.setWebContent(localWebView, BxShowHelp.this.getString(paramInt2), 1);
      }
    }
    
    private class MyPagerAdapter
      extends PagerAdapter
    {
      private MyPagerAdapter() {}
      
      public void destroyItem(View paramView, int paramInt, Object paramObject)
      {
        if (BxShowHelp.this.mListViews.size() > 3) {
          ((ViewPager)paramView).removeView((View)paramObject);
        }
      }
      
      public void finishUpdate(View paramView) {}
      
      public int getCount()
      {
        return 2000;
      }
      
      public Object instantiateItem(View paramView, int paramInt)
      {
        View localView = (View)BxShowHelp.this.mListViews.get(paramInt % BxShowHelp.this.mListViews.size());
        if (localView.getParent() != paramView) {
          ((ViewPager)paramView).addView(localView, 0);
        }
        return BxShowHelp.this.mListViews.get(paramInt % BxShowHelp.this.mListViews.size());
      }
      
      public boolean isViewFromObject(View paramView, Object paramObject)
      {
        if (paramView == paramObject) {}
        for (boolean bool = true;; bool = false) {
          return bool;
        }
      }
      
      public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader) {}
      
      public Parcelable saveState()
      {
        return null;
      }
      
      public void startUpdate(View paramView) {}
    }
  }
  
  class MyHandler
    extends Handler
  {
    MyHandler() {}
    
    public void handleMessage(Message paramMessage)
    {
      int i;
      if (paramMessage.what == 1)
      {
        paramMessage = BxShowHelp.this;
        paramMessage.timeMargin += 1;
        paramMessage = BxShowHelp.this;
        paramMessage.timeMargin %= 4;
        paramMessage = BxShowHelp.this.imgchg;
        i = BxShowHelp.this.timeMargin;
        paramMessage.setImageResource(new int[] { 2130837612, 2130837613, 2130837614, 2130837615 }[i]);
        paramMessage = BxShowHelp.this.imgaccool;
        i = BxShowHelp.this.timeMargin;
        paramMessage.setImageResource(new int[] { 2130837505, 2130837506, 2130837507, 2130837508 }[i]);
        paramMessage = BxShowHelp.this.imgachot;
        i = BxShowHelp.this.timeMargin;
        paramMessage.setImageResource(new int[] { 2130837513, 2130837514, 2130837515, 2130837516 }[i]);
        paramMessage = BxShowHelp.this.imgacdef;
        i = BxShowHelp.this.timeMargin;
        paramMessage.setImageResource(new int[] { 2130837509, 2130837510, 2130837511, 2130837512 }[i]);
        sendEmptyMessageDelayed(1, 500L);
        return;
      }
      if (paramMessage.what == 2)
      {
        paramMessage = BxShowHelp.this.imgHead;
        if (!BxShowHelp.this.timeBlink) {
          break label322;
        }
        i = 2130837762;
        label257:
        paramMessage.setImageResource(i);
        paramMessage = BxShowHelp.this.imgPos;
        if (!BxShowHelp.this.timeBlink) {
          break label328;
        }
        i = 2130837765;
        label283:
        paramMessage.setImageResource(i);
        sendEmptyMessageDelayed(2, 500L);
        paramMessage = BxShowHelp.this;
        if (!BxShowHelp.this.timeBlink) {
          break label334;
        }
      }
      label322:
      label328:
      label334:
      for (boolean bool = false;; bool = true)
      {
        paramMessage.timeBlink = bool;
        break;
        break;
        i = 2130837763;
        break label257;
        i = 2130837766;
        break label283;
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\BxShowHelp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */