
package xdata.etl.entity.assist;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.assist.AssistEntity;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_assist_get_help")
public class AssistGetHelp
    extends AssistEntity
    implements Serializable
{

    private final static long serialVersionUID = -1119021051578137556L;
    @SerializedName("oemid")
    private String oemid;
    @SerializedName("version")
    private String version;
    @SerializedName("packagename")
    private String packagename;
    @SerializedName("uid")
    private String uid;
    @SerializedName("hid")
    private String hid;
    @SerializedName("type")
    private String type;
    @SerializedName("appId")
    private String appId;
    @SerializedName("resultCode")
    private String resultCode;

    public AssistGetHelp() {
    }

    public String getOemid() {
        return this.oemid;
    }

    public void setOemid(String oemid) {
        this.oemid = oemid;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPackagename() {
        return this.packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHid() {
        return this.hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

}
