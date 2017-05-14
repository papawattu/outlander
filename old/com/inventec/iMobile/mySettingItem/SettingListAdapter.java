package com.inventec.iMobile.mySettingItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class SettingListAdapter
  extends BaseAdapter
{
  private Context mContext;
  private LayoutInflater mInflater;
  private ArrayList<SettingItem> mList;
  
  public SettingListAdapter(Context paramContext, ArrayList<SettingItem> paramArrayList)
  {
    this.mContext = paramContext;
    this.mInflater = LayoutInflater.from(this.mContext);
    this.mList = paramArrayList;
  }
  
  public int getCount()
  {
    if (this.mList != null) {}
    for (int i = this.mList.size();; i = 0) {
      return i;
    }
  }
  
  public Object getItem(int paramInt)
  {
    if (this.mList != null) {}
    for (Object localObject = this.mList.get(paramInt);; localObject = null) {
      return localObject;
    }
  }
  
  public long getItemId(int paramInt)
  {
    if (this.mList != null) {}
    for (long l = paramInt;; l = 0L) {
      return l;
    }
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return ((SettingItem)this.mList.get(paramInt)).GetConvertView(this.mInflater, paramView, this.mList, paramInt);
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\mySettingItem\SettingListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */