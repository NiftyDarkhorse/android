package eu.ttbox.geoping.core;

import java.util.Calendar;

public class AppConstants {

    public final static double E6 = 1000000d;

    // Constant
    public static final long UNSET_TIME = -1l;
    public static final long UNSET_ID = -1l;

    public static final long DATE_ZERO =  getZeroDate();
    
    public static final String MARKET_LINK = "market://details?id=eu.ttbox.geoping"; //"https://play.google.com/store/apps/details?id=eu.ttbox.geoping";
//    "market://search?q=pname:eu.ttbox.geoping"
    // "market://search?q=pub:eu.ttbox.geoping"
    
    //
    public static final String LOCAL_DB_KEY = "local";
    public static final int SMS_MAX_SIZE = 160;

    public static final char PHONE_SEP = ';';  

    public static final int PER_PERSON_ID_MULTIPLICATOR = 10000;
    public static final String KEY_DB_LOCAL = "local";
    // Request Notification
    public static final String PREFS_SMS_DELETE_ON_MESSAGE = "smsDeleteOnMessage";
    public static final String PREFS_SHOW_GEOPING_NOTIFICATION = "showGeopingNotification";
    public static final String PREFS_LOCAL_SAVE = "localSave";
    
    // Map
    public static final String PREFS_KEY_MYLOCATION_DISPLAY_GEOLOC = "myLocationDisplayLatlng";
    public static final String PREFS_GEOPOINT_GEOCODING_AUTO = "geoPointGeocodingAuto";
    
    
    // TODO in prefs

    public static final String PREFS_KEY_TILE_SOURCE = "KEY_TILE_SOURCE";

    public static final String PREFS_APP_COUNT_LAUGHT = "APP_COUNT_LAUGHT";
    public static final String PREFS_APP_FIRSTDATE_LAUGHT = "APP_FIRSTDATE_LAUGHT";

 
    private static long getZeroDate() {
        Calendar cal = Calendar.getInstance(); 
        cal.set(Calendar.YEAR, 2012);
        cal.set(Calendar.MONTH,Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 22);
        // Midnight
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long timeAtMidnight = cal.getTimeInMillis();
        return timeAtMidnight;
    }
}
