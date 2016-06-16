package com.conways.demo.home.firstmenu.bloodpressure;

/**
 * Created by user on 2016/5/17.
 */
public class BPContants {


    public static final String DEVICE_NAME = "RBP";
//    public static final String DEVICE_NAME = "KBB";


    /**
     * 与血压计交互时的Service；
     */
    public static final String BP_SERVICE_UUID = "0000fff0-0000-1000-8000-00805f9b34fb";
    /**
     * 发送命令的通道；
     */
    public static final String BP_SERVICE_CHARACTERISTIC_WRITE = "0000fff2-0000-1000-8000-00805f9b34fb";
    /**
     * 接受数据的通道；
     */
    public static final String BP_SERVICE_CHARACTERISTIC_READ = "0000fff1-0000-1000-8000-00805f9b34fb";


    private static byte[] Contect = {
            (byte) 0xCC, (byte) 0x80,//前导码
            (byte) 0x02,//版本号
            (byte) 0x03,//数据长度
            (byte) 0x01,//类型标识
            (byte) 0x01,//类型子码
            (byte) 0x00,//数据/参数
            (byte) 0x00,//校验码（此处用0占位，并不是真正的校验码）
    };

    private static byte[] Start_measure = {
            (byte) 0xCC, (byte) 0x80,//前导码
            (byte) 0x02,//版本号
            (byte) 0x03,//数据长度
            (byte) 0x01,//类型标识
            (byte) 0x02,//类型子码
            (byte) 0x00,//数据/参数
            (byte) 0x00,//校验码（此处用0占位，并不是真正的校验码）
    };


    private static byte[] Stop_measure = {
            (byte) 0xCC, (byte) 0x80,//前导码
            (byte) 0x02,//版本号
            (byte) 0x03,//数据长度
            (byte) 0x01,//类型标识
            (byte) 0x03,//类型子码
            (byte) 0x00,//数据/参数
            (byte) 0x00,//校验码（此处用0占位，并不是真正的校验码）
    };
    private static byte[] Close = {
            (byte) 0xCC, (byte) 0x80,//前导码
            (byte) 0x02,//版本号
            (byte) 0x03,//数据长度
            (byte) 0x01,//类型标识
            (byte) 0x04,//类型子码
            (byte) 0x00,//数据/参数
            (byte) 0x00,//校验码（此处用0占位，并不是真正的校验码）
    };

    /**
     * 开始连接命令
     *
     * @return
     */

    public static final byte[] CONTECT() {
        Contect[Contect.length - 1] = getCheckOrder(Contect);
        return Contect;
    }

    /**
     * 开始测量命令
     *
     * @return
     */

    public static final byte[] START_MEASURE() {
        Start_measure[Start_measure.length - 1] = getCheckOrder(Start_measure);
        return Start_measure;
    }

    /**
     * 停止测量命令
     *
     * @return
     */

    public static final byte[] STOP_MEASURE() {
        Stop_measure[Stop_measure.length - 1] = getCheckOrder(Stop_measure);
        return Stop_measure;
    }

    /**
     * 关机命令
     *
     * @return
     */

    public static final byte[] CLOSE() {
        Close[Close.length - 1] = getCheckOrder(Close);
        return Close;
    }


    private static byte getCheckOrder(byte[] data) {
        byte temp = (byte) 0x00;

        for (int i = 2; i < data.length - 1; i++) {
            temp = (byte) (temp ^ data[i]);
        }
        return temp;
    }


    public static final String ACTION_STATE_CONNECT = "com.conways.bpservice.connect";
    public static final String ACTION_STATE_DISONNECT = "com.conways.bpservice.disconnect";
    public static final String ACTION_STATE_INIT_CHARACTERISTIC_SECCUSS = "com.conways.bpservice" +
            ".init.characteristic.seccuss";

    public static final String ACTION_CONNECT_SECCUSS = "com.conways.bpservice.connect.seccuss";
    public static final String ACTION_START_MEASURE_SECCUSS = "com.conways" +
            ".bpservice.start.measure.seccuss";
    public static final String ACTION_EXTRA_DATA = "com.conways.bpservice.extra.data";


}
