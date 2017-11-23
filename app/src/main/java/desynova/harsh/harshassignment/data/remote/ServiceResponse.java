package desynova.harsh.harshassignment.data.remote;

class ServiceResponse {
    private int code;
    private Object data;
    private ServiceError ServiceError;

    ServiceResponse(int code, Object response) {
        this.code = code;
        this.data = response;
    }

    ServiceResponse(ServiceError ServiceError) {
        this.ServiceError = ServiceError;
    }

    public ServiceResponse(Object response) {
        this.data = response;
    }

    public int getCode() {
        return code;
    }

    public ServiceError getServiceError() {
        return ServiceError;
    }

    public Object getData() {

        return data;
    }
}
