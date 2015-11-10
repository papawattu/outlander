package android.support.v4.widget;

import android.database.Cursor;
import android.widget.Filter;
import android.widget.Filter.FilterResults;

class CursorFilter
  extends Filter
{
  CursorFilterClient mClient;
  
  CursorFilter(CursorFilterClient paramCursorFilterClient)
  {
    this.mClient = paramCursorFilterClient;
  }
  
  public CharSequence convertResultToString(Object paramObject)
  {
    return this.mClient.convertToString((Cursor)paramObject);
  }
  
  protected Filter.FilterResults performFiltering(CharSequence paramCharSequence)
  {
    Cursor localCursor = this.mClient.runQueryOnBackgroundThread(paramCharSequence);
    paramCharSequence = new Filter.FilterResults();
    if (localCursor != null) {
      paramCharSequence.count = localCursor.getCount();
    }
    for (paramCharSequence.values = localCursor;; paramCharSequence.values = null)
    {
      return paramCharSequence;
      paramCharSequence.count = 0;
    }
  }
  
  protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults)
  {
    paramCharSequence = this.mClient.getCursor();
    if ((paramFilterResults.values != null) && (paramFilterResults.values != paramCharSequence)) {
      this.mClient.changeCursor((Cursor)paramFilterResults.values);
    }
  }
  
  static abstract interface CursorFilterClient
  {
    public abstract void changeCursor(Cursor paramCursor);
    
    public abstract CharSequence convertToString(Cursor paramCursor);
    
    public abstract Cursor getCursor();
    
    public abstract Cursor runQueryOnBackgroundThread(CharSequence paramCharSequence);
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\android\support\v4\widget\CursorFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */