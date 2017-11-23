package desynova.harsh.harshassignment.data.remote.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TabTwo {

    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("error_message")
    @Expose
    private String errorMessage;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data {

        @SerializedName("bgurl")
        @Expose
        private String bgurl;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("rv1")
        @Expose
        private List<TabOne.Datum> rv1 = null;
        @SerializedName("rv2")
        @Expose
        private List<TabOne.Datum> rv2 = null;
        @SerializedName("rv3")
        @Expose
        private List<TabOne.Datum> rv3 = null;
        @SerializedName("rv4")
        @Expose
        private List<TabOne.Datum> rv4 = null;

        public String getBgurl() {
            return bgurl;
        }

        public void setBgurl(String bgurl) {
            this.bgurl = bgurl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<TabOne.Datum> getRv1() {
            return rv1;
        }

        public void setRv1(List<TabOne.Datum> rv1) {
            this.rv1 = rv1;
        }

        public List<TabOne.Datum> getRv2() {
            return rv2;
        }

        public void setRv2(List<TabOne.Datum> rv2) {
            this.rv2 = rv2;
        }

        public List<TabOne.Datum> getRv3() {
            return rv3;
        }

        public void setRv3(List<TabOne.Datum> rv3) {
            this.rv3 = rv3;
        }

        public List<TabOne.Datum> getRv4() {
            return rv4;
        }

        public void setRv4(List<TabOne.Datum> rv4) {
            this.rv4 = rv4;
        }

    }
}
