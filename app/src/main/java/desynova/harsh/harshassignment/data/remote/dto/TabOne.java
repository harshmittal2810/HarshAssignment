package desynova.harsh.harshassignment.data.remote.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TabOne implements Parcelable {


    public static final Parcelable.Creator<TabOne> CREATOR = new Parcelable.Creator<TabOne>() {
        @Override
        public TabOne createFromParcel(Parcel source) {
            return new TabOne(source);
        }

        @Override
        public TabOne[] newArray(int size) {
            return new TabOne[size];
        }
    };
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
    private List<Datum> data = null;

    public TabOne() {
    }

    protected TabOne(Parcel in) {
        this.errorCode = in.readString();
        this.errorMessage = in.readString();
        this.success = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.data = new ArrayList<Datum>();
        in.readList(this.data, Datum.class.getClassLoader());
    }

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.errorCode);
        dest.writeString(this.errorMessage);
        dest.writeValue(this.success);
        dest.writeList(this.data);
    }

    public static class Datum implements Parcelable {

        public static final Creator<Datum> CREATOR = new Creator<Datum>() {
            @Override
            public Datum createFromParcel(Parcel source) {
                return new Datum(source);
            }

            @Override
            public Datum[] newArray(int size) {
                return new Datum[size];
            }
        };
        @SerializedName("name")
        @Expose
        private Integer name;
        @SerializedName("url")
        @Expose
        private String url;

        public Datum() {
        }

        protected Datum(Parcel in) {
            this.name = (Integer) in.readValue(Integer.class.getClassLoader());
            this.url = in.readString();
        }

        public Integer getName() {
            return name;
        }

        public void setName(Integer name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.name);
            dest.writeString(this.url);
        }
    }
}


