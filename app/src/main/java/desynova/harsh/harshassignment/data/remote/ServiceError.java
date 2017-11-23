package desynova.harsh.harshassignment.data.remote;

class ServiceError {
    public static final int ERROR_CODE = 400;
    static final String NETWORK_ERROR = "Unknown ServiceError";
    static final int SUCCESS_CODE = 200;
    private static final int GROUP_200 = 2;
    private static final int GROUP_400 = 4;
    private static final int GROUP_500 = 5;
    private static final int VALUE_100 = 100;
    private String description;
    private int code;

    ServiceError() {
    }

    ServiceError(String description, int code) {
        this.description = description;
        this.code = code;
    }

    public static boolean isSuccess(int responseCode) {
        return responseCode / VALUE_100 == GROUP_200;
    }

    public static boolean isClientError(int errorCode) {
        return errorCode / VALUE_100 == GROUP_400;
    }

    public static boolean isServerError(int errorCode) {
        return errorCode / VALUE_100 == GROUP_500;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}
