package com.inventec.iMobile.baseOP;

import android.content.Context;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class DealFile
{
  public static void deleteFile(Context paramContext, String paramString)
  {
    paramContext = paramContext.getFileStreamPath(paramString);
    if (paramContext.exists()) {
      paramContext.delete();
    }
  }
  
  public static boolean loaddata(Context paramContext, String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      paramContext = paramContext.openFileInput(paramString);
      paramString = new java/io/DataInputStream;
      paramString.<init>(paramContext);
      paramString.read(paramArrayOfByte);
      paramString.close();
      paramContext.close();
      bool = true;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
    return bool;
  }
  
  public static boolean savedata(Context paramContext, String paramString, byte[] paramArrayOfByte)
  {
    boolean bool = false;
    try
    {
      paramContext = paramContext.openFileOutput(paramString, 0);
      paramString = new java/io/DataOutputStream;
      paramString.<init>(paramContext);
      paramString.write(paramArrayOfByte);
      paramString.close();
      paramContext.close();
      bool = true;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return bool;
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseOP\DealFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */