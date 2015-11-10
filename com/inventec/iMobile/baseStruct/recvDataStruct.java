package com.inventec.iMobile.baseStruct;

public class recvDataStruct
{
  public static final recvDataStruct[] recvDataDef = { new recvDataStruct(0, 0, 0), new recvDataStruct(1, 2, 0), new recvDataStruct(2, 4, 2), new recvDataStruct(3, 3, 6), new recvDataStruct(4, 1, 10), new recvDataStruct(5, 1, 11), new recvDataStruct(6, 1, 12), new recvDataStruct(7, 1, 13), new recvDataStruct(8, 1, 14), new recvDataStruct(9, 1, 15), new recvDataStruct(10, 1, 16), new recvDataStruct(11, 1, 17), new recvDataStruct(12, 1, 18), new recvDataStruct(13, 1, 19), new recvDataStruct(14, 1, 20), new recvDataStruct(15, 1, 21), new recvDataStruct(16, 1, 22), new recvDataStruct(17, 1, 23), new recvDataStruct(18, 7, 24), new recvDataStruct(19, 1, 31), new recvDataStruct(20, 7, 32), new recvDataStruct(21, 20, 39), new recvDataStruct(22, 8, 60), new recvDataStruct(23, 1, 68), new recvDataStruct(24, 16, 69), new recvDataStruct(25, 9, 85), new recvDataStruct(26, 2, 94), new recvDataStruct(27, 1, 96), new recvDataStruct(28, 1, 98), new recvDataStruct(29, 4, 99), new recvDataStruct(30, 2, 103), new recvDataStruct(31, 3, 105), new recvDataStruct(32, 10, 108), new recvDataStruct(33, 1, 118), new recvDataStruct(34, 6, 119), new recvDataStruct(35, 5, 125), new recvDataStruct(36, 10, 130), new recvDataStruct(37, 3, 140), new recvDataStruct(38, 1, 143), new recvDataStruct(39, 1, 144), new recvDataStruct(40, 32, 145), new recvDataStruct(41, 2, 178), new recvDataStruct(42, 1, 180), new recvDataStruct(43, 10, 181), new recvDataStruct(44, 1, 194), new recvDataStruct(-64, 13, 181) };
  public byte datalen;
  public short gdataPos;
  public byte msgID;
  
  public recvDataStruct(byte paramByte, int paramInt, short paramShort)
  {
    this.msgID = paramByte;
    this.datalen = ((byte)paramInt);
    this.gdataPos = paramShort;
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseStruct\recvDataStruct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */