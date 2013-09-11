
package xdata.etl.entity.assist;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.assist.AssistEntity;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_assist_exception")
public class AssistException
    extends AssistEntity
    implements Serializable
{

    private final static long serialVersionUID = -709748821438541856L;
    @SerializedName("exceptionInfo")
    @HbaseEmbedded
    private ExceptionInfo exceptionInfo = new ExceptionInfo();
    @SerializedName("uid")
    private String uid;
    @SerializedName("appId")
    private String appId;
    @SerializedName("oemid")
    private String oemid;
    @SerializedName("hid")
    private String hid;
    @SerializedName("packagename")
    private String packagename;
    @SerializedName("resultCode")
    private String resultCode;
    @SerializedName("optionCode")
    private String optionCode;

    public AssistException() {
    }

    public ExceptionInfo getExceptionInfo() {
        return this.exceptionInfo;
    }

    public void setExceptionInfo(ExceptionInfo exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getPackagename() {
        return this.packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getOptionCode() {
        return this.optionCode;
    }

    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }

}
