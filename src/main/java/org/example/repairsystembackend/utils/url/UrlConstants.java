package org.example.repairsystembackend.utils.url;

public final class UrlConstants {

    public static final String URL_V1_VERSION_PREFIX        = "/api?v1";

    public static final String ANONYMOUS_URL_PREFIX         = "anonymous";

    public static final String ANY                          = "/**";

    public static final String ANONYMOUS_V1_URL             = URL_V1_VERSION_PREFIX+ANONYMOUS_URL_PREFIX;

    public static final String USER_LOGIN_URL               = ANONYMOUS_V1_URL+"/user/login";

    public static final String USER_LOGOUT_URL              = URL_V1_VERSION_PREFIX+"/user/logout";

    public static final String USER_MODIFY_URL              = URL_V1_VERSION_PREFIX+"/user/modify";

    public static final String USER_REPAIR_APPLY_URL        = URL_V1_VERSION_PREFIX+"/user/repairApply";

    public static final String USER_REPAIR_TRACK_URL        = URL_V1_VERSION_PREFIX+"/user/repairTrack";

    public static final String USER_REPAIR_FEEDBACK_URL     = URL_V1_VERSION_PREFIX+"/user/repairFeedback";

    public static final String REPAIRMAN_LOGIN_URL          = ANONYMOUS_V1_URL+"/repairman/login";

    public static final String REPAIRMAN_LOGOUT_URL         = URL_V1_VERSION_PREFIX+"/repairman/logout";

    public static final String REPAIRMAN_MODIFY_URL         = URL_V1_VERSION_PREFIX+"/repairman/modify";

    public static final String REPAIRMAN_REPAIR_RECEIVE_URL = URL_V1_VERSION_PREFIX+"/repairman/repairReceive";

    public static final String REPAIRMAN_REPAIR_COMPLEX_URL = URL_V1_VERSION_PREFIX+"/repairman/repairComplex";

    public static final String REPAIR_INFO_URL              = URL_V1_VERSION_PREFIX+"/repair/info";


}
