package com.inventec.controls;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import java.util.Locale;

public class MyTextView
  extends TextView
{
  public static final float MIN_TEXT_SIZE = 8.0F;
  public int colorShadow = -16777216;
  public int colorText = -1;
  byte drawPos = 1;
  boolean mNeedsResize = false;
  float mOldTextSize;
  float mTextSize;
  int rw = 0;
  
  public MyTextView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public MyTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public MyTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    float f = getTextSize();
    this.mTextSize = f;
    this.mOldTextSize = f;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    TextPaint localTextPaint = getPaint();
    localTextPaint.setFakeBoldText(true);
    localTextPaint.setAntiAlias(true);
    if (this.mNeedsResize) {
      resizeText(getWidth(), getHeight());
    }
    paramCanvas.save();
    Object localObject = Layout.Alignment.ALIGN_NORMAL;
    int j = getWidth();
    int i = this.rw;
    localObject = new StaticLayout(getText(), localTextPaint, j - i, (Layout.Alignment)localObject, 1.0F, 0.0F, false);
    paramCanvas.translate(this.rw + getPaddingLeft(), 0.0F + (getPaddingTop() + (getHeight() - getPaddingTop() - getPaddingBottom() - ((StaticLayout)localObject).getHeight()) / 2));
    if (this.colorShadow != 0)
    {
      paramCanvas.translate(1.0F, 1.0F);
      localTextPaint.setColor(this.colorShadow);
      ((StaticLayout)localObject).draw(paramCanvas);
      setTextColor(this.colorText);
      paramCanvas.translate(-1.0F, -1.0F);
    }
    localTextPaint.setColor(this.colorText);
    ((StaticLayout)localObject).draw(paramCanvas);
    paramCanvas.restore();
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (((paramInt1 != paramInt3) || (paramInt2 != paramInt4)) && (Locale.getDefault().getLanguage().equals("ja"))) {
      this.mNeedsResize = true;
    }
  }
  
  protected void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (Locale.getDefault().getLanguage().equals("ja")) {
      this.mNeedsResize = true;
    }
    resetTextSize();
  }
  
  public void resetTextSize()
  {
    super.setTextSize(0, this.mOldTextSize);
  }
  
  int resizeFont(int paramInt, String paramString)
  {
    TextPaint localTextPaint = getPaint();
    float f = this.mTextSize;
    localTextPaint.setTextSize(f);
    for (int i = (int)localTextPaint.measureText(paramString);; i = (int)localTextPaint.measureText(paramString))
    {
      if ((i < paramInt) || (f <= 8.0F))
      {
        this.mTextSize = f;
        return (int)localTextPaint.measureText(paramString);
      }
      f = Math.max(f - 1.0F, 8.0F);
      localTextPaint.setTextSize(f);
    }
  }
  
  public void resizeText()
  {
    int j = getHeight();
    int i = getPaddingBottom();
    int k = getPaddingTop();
    resizeText(getWidth() - getPaddingLeft() - getPaddingRight(), j - i - k);
  }
  
  void resizeText(int paramInt1, int paramInt2)
  {
    Object localObject = getText();
    if ((localObject == null) || (((CharSequence)localObject).length() == 0) || (paramInt2 <= 0) || (paramInt1 <= 0)) {
      return;
    }
    String[] arrayOfString = ((CharSequence)localObject).toString().split("\n");
    paramInt2 = 0;
    label42:
    int j;
    int i;
    if (paramInt2 >= arrayOfString.length)
    {
      localObject = getPaint();
      j = 0;
      i = 0;
      label60:
      if (i < arrayOfString.length) {
        break label110;
      }
      if (this.drawPos != 1) {
        break label144;
      }
      this.rw = ((paramInt1 - j) / 2);
    }
    for (;;)
    {
      this.mNeedsResize = false;
      break;
      resizeFont(paramInt1, arrayOfString[paramInt2]);
      paramInt2++;
      break label42;
      label110:
      int k = (int)((TextPaint)localObject).measureText(arrayOfString[i]);
      paramInt2 = j;
      if (k > j) {
        paramInt2 = k;
      }
      i++;
      j = paramInt2;
      break label60;
      label144:
      if (this.drawPos == 2) {
        this.rw = (paramInt1 - j);
      } else {
        this.rw = 0;
      }
    }
  }
  
  public void setCenter()
  {
    this.drawPos = 1;
  }
  
  public void setColors(int paramInt1, int paramInt2)
  {
    this.colorText = paramInt1;
    this.colorShadow = paramInt2;
    setTextColor(paramInt1);
  }
  
  public void setLeft()
  {
    this.drawPos = 0;
  }
  
  public void setRight()
  {
    this.drawPos = 2;
  }
  
  public void setTextSize(int paramInt, float paramFloat)
  {
    super.setTextSize(paramInt, paramFloat);
    paramFloat = getTextSize();
    this.mTextSize = paramFloat;
    this.mOldTextSize = paramFloat;
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\controls\MyTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */