package com.inventec.iMobile.popup;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class ActionItem
{
  private Drawable icon;
  private boolean selected;
  private Bitmap thumb;
  private String title;
  
  public ActionItem() {}
  
  public ActionItem(Drawable paramDrawable)
  {
    this.icon = paramDrawable;
  }
  
  public Drawable getIcon()
  {
    return this.icon;
  }
  
  public Bitmap getThumb()
  {
    return this.thumb;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public boolean isSelected()
  {
    return this.selected;
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    this.icon = paramDrawable;
  }
  
  public void setSelected(boolean paramBoolean)
  {
    this.selected = paramBoolean;
  }
  
  public void setThumb(Bitmap paramBitmap)
  {
    this.thumb = paramBitmap;
  }
  
  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\popup\ActionItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */