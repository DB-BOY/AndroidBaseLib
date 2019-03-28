package cn.dbboy.generallib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class NetworkUtils {

    public static final int NETWORK_WIFI = 1;
    public static final int NETWORK_UNAVAILABLE = -1;
    public static final int NETWORK_UNKNOWN = 0;
    private static final int NETWORK_2_G = 2;
    private static final int NETWORK_3_G = 3;
    private static final int NETWORK_4_G = 4;

    private static final String China_Mobile = "cm";
    private static final String China_Unicom = "cu";
    private static final String China_Telecom = "ct";
    private static final String China_Unkown = "wz";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = cm.getActiveNetworkInfo();
        return network != null && network.isAvailable();
    }

    public static int getCurrentNetworkType(Context context) {
        int networkType = NETWORK_UNKNOWN;

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null)
            return networkType;
        NetworkInfo network = manager.getActiveNetworkInfo();
        if (network != null && network.isAvailable() && network.isConnected()) {
            int type = manager.getActiveNetworkInfo().getType();
            if (type == ConnectivityManager.TYPE_WIFI) {
                networkType = NETWORK_WIFI;
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                networkType = getNetworkClassByType(telephonyManager.getNetworkType());
            }
        } else {
            networkType = NETWORK_UNAVAILABLE;
        }
        return networkType;
    }

    private static int getNetworkClassByType(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS: //联通2g
            case TelephonyManager.NETWORK_TYPE_EDGE: //移动2g
            case TelephonyManager.NETWORK_TYPE_CDMA: //电信2g
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NETWORK_2_G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A: //电信3g
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NETWORK_3_G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NETWORK_4_G;
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return NETWORK_UNKNOWN;
            default:
                return NETWORK_UNKNOWN;
        }
    }

    /**
     * 判断网络为哪个运营商
     */
    public static String getTelecomOperators(Context context) {
        String imsi = "";
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isAvailable()) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            imsi = telephonyManager.getSubscriberId();
        }
        if (!TextUtils.isEmpty(imsi)) {
            if (imsi.startsWith("46000") || imsi.startsWith("46002")) {
                return China_Mobile;
            } else if (imsi.startsWith("46001")) {
                return China_Unicom;
            } else if (imsi.startsWith("46003")) {
                return China_Telecom;
            }
        }
        return China_Unkown;
    }
}