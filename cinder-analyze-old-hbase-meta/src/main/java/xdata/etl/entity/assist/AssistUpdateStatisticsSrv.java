
package xdata.etl.entity.assist;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_assist_update_statistics_srv")
public class AssistUpdateStatisticsSrv
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = 8022435516550696327L;
    @SerializedName("appName")
    private String appName;
    @SerializedName("uid")
    private String uid;
    @SerializedName("errorText")
    private String errorText;
    @SerializedName("appId")
    private String appId;
    @SerializedName("oemid")
    private String oemid;
    @SerializedName("hid")
    private String hid;
    @SerializedName("appVersion")
    private String appVersion;
    @SerializedName("errorCode")
    private String errorCode;
    @SerializedName("value")
    private String value;
    @SerializedName("type")
    private String type;

    public AssistUpdateStatisticsSrv() {
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getErrorText() {
        return this.errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOemid() {
        return this.oemid;
    }

    public void setOemid(String oemid) {
        this.oemid = oemid;
    }

    public String getHid() {
        return this.hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
