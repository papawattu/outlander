package com.inventec.controls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.FontMetrics;
import android.os.Build.VERSION;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

public class AutoResizeTextView
  extends TextView
{
  public static final float MIN_TEXT_SIZE = 8.0F;
  private static final int[] hG = { 3, 1, 5 };
  private static final String mEllipsis = "...";
  static final Canvas sTextResizeCanvas = new Canvas();
  private static final int[] vG = { 48, 16, 80 };
  int colorShadow = -16777216;
  int colorText = -1;
  StaticLayout drawlayout = null;
  int htype = 0;
  boolean mNeedsResize = false;
  float mSpacingMult = 1.0F;
  float mTextSize = getTextSize();
  boolean single = false;
  int timeNo = 0;
  int vtype = 0;
  
  public AutoResizeTextView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AutoResizeTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public AutoResizeTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private int getFontHeight(float paramFloat)
  {
    Object localObject = getPaint();
    ((TextPaint)localObject).setTextSize(paramFloat);
    localObject = ((TextPaint)localObject).getFontMetrics();
    return (int)(((Paint.FontMetrics)localObject).descent - ((Paint.FontMetrics)localObject).ascent);
  }
  
  void drawTimer(Canvas paramCanvas, float paramFloat1, float paramFloat2)
  {
    TextPaint localTextPaint = getPaint();
    float f1 = localTextPaint.getTextSize();
    paramFloat2 += f1;
    String str = getText().toString();
    str = str.substring(0, str.length() - 1);
    paramCanvas.drawText(str, paramFloat1, paramFloat2, localTextPaint);
    float f2 = localTextPaint.measureText(str);
    str = this.timeNo;
    localTextPaint.setTextSize(1.3F * f1);
    paramCanvas.drawText(str, paramFloat1 + f2, paramFloat2, localTextPaint);
    localTextPaint.setTextSize(f1);
  }
  
  public float getFittingSizeOfWidth(int paramInt, float paramFloat)
  {
    CharSequence localCharSequence = getText();
    TextPaint localTextPaint = getPaint();
    localTextPaint.setTextSize(paramFloat);
    for (float f = localTextPaint.measureText(localCharSequence.toString());; f = localTextPaint.measureText(localCharSequence.toString()))
    {
      if ((paramInt >= f) || (paramFloat <= 8.0F)) {
        return paramFloat;
      }
      paramFloat -= 1.0F;
      localTextPaint.setTextSize(paramFloat);
    }
  }
  
  int getFontHeightwithSpace()
  {
    Paint.FontMetrics localFontMetrics = getPaint().getFontMetrics();
    return (int)(localFontMetrics.bottom - localFontMetrics.top);
  }
  
  int getTextHeight(CharSequence paramCharSequence, TextPaint paramTextPaint, int paramInt, float paramFloat)
  {
    paramTextPaint.setTextSize(paramFloat);
    paramCharSequence = new StaticLayout(paramCharSequence, paramTextPaint, paramInt, Layout.Alignment.ALIGN_NORMAL, this.mSpacingMult, 0.0F, false);
    paramCharSequence.draw(sTextResizeCanvas);
    return paramCharSequence.getHeight();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    TextPaint localTextPaint = getPaint();
    localTextPaint.setFakeBoldText(true);
    localTextPaint.setAntiAlias(true);
    int m = getPaddingRight();
    int j = getPaddingLeft();
    int k = getPaddingTop();
    int i = getPaddingBottom();
    m = getWidth() - m - j;
    int n = getHeight() - k - i;
    if (Build.VERSION.SDK_INT > 15) {
      this.mSpacingMult = getLineSpacingMultiplier();
    }
    if ((this.mNeedsResize) || ((this.drawlayout == null) && (this.timeNo == 0))) {
      resizeText(m, n);
    }
    label193:
    Layout.Alignment localAlignment;
    label211:
    float f3;
    float f1;
    label268:
    float f2;
    label288:
    float f4;
    switch (getGravity() & 0xF0)
    {
    default: 
      this.vtype = 2;
      switch (getGravity() & 0xF)
      {
      case 2: 
      default: 
        this.htype = 2;
        localAlignment = Layout.Alignment.ALIGN_NORMAL;
        if (this.htype == 1)
        {
          localAlignment = Layout.Alignment.ALIGN_CENTER;
          if (this.timeNo == 0) {
            this.drawlayout = new StaticLayout(getText(), localTextPaint, m, localAlignment, this.mSpacingMult, 0.0F, true);
          }
          paramCanvas.save();
          f3 = 0.0F;
          f1 = 0.0F;
          if (!this.single) {
            break label473;
          }
          i = getFontHeightwithSpace();
          f2 = i;
          if (this.vtype != 1) {
            break label485;
          }
          f1 = (n - f2) / 2.0F;
          f4 = f1 + k;
          if (this.timeNo != 0) {
            break label502;
          }
          f1 = this.drawlayout.getWidth();
          label311:
          f2 = f1;
          if (this.timeNo != 0) {
            f2 = f1 + 6.0F;
          }
          if (this.htype != 2) {
            break label520;
          }
          f1 = m - f2;
          label339:
          f1 += j;
          if (this.timeNo != 0) {
            break label552;
          }
          if (this.colorShadow == 0) {
            break label542;
          }
          paramCanvas.translate(1.0F + f1, 1.0F + f4);
          localTextPaint.setColor(this.colorShadow);
          this.drawlayout.draw(paramCanvas);
          setTextColor(this.colorText);
          paramCanvas.translate(-1.0F, -1.0F);
          label403:
          localTextPaint.setColor(this.colorText);
          this.drawlayout.draw(paramCanvas);
        }
        break;
      }
      break;
    }
    for (;;)
    {
      paramCanvas.restore();
      return;
      this.vtype = 0;
      break;
      this.vtype = 1;
      break;
      this.htype = 0;
      break label193;
      this.htype = 1;
      break label193;
      if (this.htype != 2) {
        break label211;
      }
      localAlignment = Layout.Alignment.ALIGN_OPPOSITE;
      break label211;
      label473:
      i = this.drawlayout.getHeight();
      break label268;
      label485:
      if (this.vtype != 2) {
        break label288;
      }
      f1 = n - f2;
      break label288;
      label502:
      f1 = localTextPaint.measureText(getText().toString());
      break label311;
      label520:
      f1 = f3;
      if (this.htype != 1) {
        break label339;
      }
      f1 = (m - f2) / 2.0F;
      break label339;
      label542:
      paramCanvas.translate(f1, f4);
      break label403;
      label552:
      if (this.colorShadow != 0)
      {
        localTextPaint.setColor(this.colorShadow);
        drawTimer(paramCanvas, 1.0F + f1, 1.0F + f4);
      }
      localTextPaint.setColor(this.colorText);
      drawTimer(paramCanvas, f1, f4);
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt1 != paramInt3) || (paramInt2 != paramInt4)) {
      this.mNeedsResize = true;
    }
  }
  
  protected void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mNeedsResize = true;
    this.mTextSize = getTextSize();
    resetTextSize();
  }
  
  public void resetTextSize()
  {
    super.setTextSize(0, this.mTextSize);
  }
  
  int resizeText(int paramInt1, int paramInt2)
  {
    CharSequence localCharSequence = getText();
    if ((localCharSequence == null) || (localCharSequence.length() == 0) || (paramInt2 <= 0) || (paramInt1 <= 0))
    {
      paramInt1 = 0;
      return paramInt1;
    }
    TextPaint localTextPaint = getPaint();
    float f1 = this.mTextSize;
    if (this.single)
    {
      localTextPaint.setTextSize(f1);
      f2 = localTextPaint.measureText(localCharSequence.toString());
      label71:
      f3 = f1;
      if (paramInt1 < f2)
      {
        if (f1 <= 8.0F) {
          f3 = f1;
        }
      }
      else
      {
        label92:
        f2 = f3;
        if (paramInt2 < getFontHeightwithSpace())
        {
          if (f3 > 8.0F) {
            break label198;
          }
          f2 = f3;
        }
      }
    }
    label198:
    label236:
    StaticLayout localStaticLayout;
    do
    {
      do
      {
        do
        {
          do
          {
            localTextPaint.setTextSize(f2);
            this.mNeedsResize = false;
            paramInt1 = (int)localTextPaint.measureText(localCharSequence.toString());
            break;
            f3 = f1 - 1.0F;
            localTextPaint.setTextSize(f3);
            float f4 = localTextPaint.measureText(localCharSequence.toString());
            f2 = f4;
            f1 = f3;
            if (this.timeNo == 0) {
              break label71;
            }
            f2 = f4 + 6.0F;
            f1 = f3;
            break label71;
            f3 -= 1.0F;
            localTextPaint.setTextSize(f3);
            break label92;
            f2 = f1;
          } while (isInEditMode());
          i = getTextHeight(localCharSequence, localTextPaint, paramInt1, f1);
          if ((i > paramInt2) && (f1 > 8.0F)) {
            break label419;
          }
          f2 = f1;
        } while (f1 != 8.0F);
        f2 = f1;
      } while (i <= paramInt2);
      localStaticLayout = new StaticLayout(localCharSequence, localTextPaint, paramInt1, Layout.Alignment.ALIGN_NORMAL, this.mSpacingMult, 0.0F, false);
      localStaticLayout.draw(sTextResizeCanvas);
      paramInt2 = localStaticLayout.getLineForVertical(paramInt2) - 1;
      f2 = f1;
    } while (paramInt2 < 0);
    int i = localStaticLayout.getLineEnd(paramInt2);
    paramInt2 = localStaticLayout.getLineStart(paramInt2) - 1;
    float f2 = localTextPaint.measureText(localCharSequence.subSequence(i, paramInt2 + 1).toString());
    float f3 = localTextPaint.measureText("...");
    for (;;)
    {
      if ((paramInt1 >= f2 + f3) || (paramInt2 <= i))
      {
        setText(localCharSequence.subSequence(0, paramInt2) + "...");
        f2 = f1;
        break;
        label419:
        f1 -= 1.0F;
        i = getTextHeight(localCharSequence, localTextPaint, paramInt1, f1);
        break label236;
      }
      paramInt2--;
      f2 = localTextPaint.measureText(localCharSequence.subSequence(i, paramInt2 + 1).toString());
    }
  }
  
  public void setColors(int paramInt1, int paramInt2)
  {
    this.colorText = paramInt1;
    this.colorShadow = paramInt2;
    setTextColor(paramInt1);
  }
  
  public void setHVType(int paramInt1, int paramInt2)
  {
    this.htype = paramInt1;
    this.vtype = paramInt2;
    setGravity(hG[this.htype] | vG[this.vtype]);
  }
  
  public void setNumber(int paramInt)
  {
    this.timeNo = paramInt;
    this.mNeedsResize = true;
  }
  
  public void setSingle()
  {
    this.single = true;
    this.mNeedsResize = true;
  }
  
  public void setTextSize(float paramFloat)
  {
    super.setTextSize(paramFloat);
    this.mTextSize = getTextSize();
    requestLayout();
    invalidate();
  }
  
  public void setTextSize(int paramInt, float paramFloat)
  {
    super.setTextSize(paramInt, paramFloat);
    this.mTextSize = getTextSize();
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\controls\AutoResizeTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */