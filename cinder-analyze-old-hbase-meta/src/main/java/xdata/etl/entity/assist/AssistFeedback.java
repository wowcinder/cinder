
package xdata.etl.entity.assist;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.assist.AssistEntity;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_assist_feedback")
public class AssistFeedback
    extends AssistEntity
    implements Serializable
{

    private final static long serialVersionUID = -957194810283768458L;
    @SerializedName("feedBackInfo")
    @HbaseEmbedded
    private FeedBackInfo feedBackInfo = new FeedBackInfo();
    @SerializedName("oemid")
    private String oemid;
    @SerializedName("uid")
    private String uid;
    @SerializedName("hid")
    private String hid;
    @SerializedName("optionCode")
    private String optionCode;
    @SerializedName("packagename")
    private String packagename;
    @SerializedName("appId")
    private String appId;
    @SerializedName("resultCode")
    private String resultCode;

    public AssistFeedback() {
    }

    public FeedBackInfo getFeedBackInfo() {
        return this.feedBackInfo;
    }

    public void setFeedBackInfo(FeedBackInfo feedBackInfo) {
        this.feedBackInfo = feedBackInfo;
    }

    public String getOemid() {
        return this.oemid;
    }

    public void setOemid(String oemid) {
        this.oemid = oemid;
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

    public String getOptionCode() {
        return this.optionCode;
    }

    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }

    public String getPackagename() {
        return this.packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
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
