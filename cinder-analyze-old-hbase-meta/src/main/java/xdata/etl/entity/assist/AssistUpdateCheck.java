
package xdata.etl.entity.assist;

import java.io.Serializable;
import java.util.Date;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.assist.AssistEntity;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_assist_update_check")
public class AssistUpdateCheck
    extends AssistEntity
    implements Serializable
{

    private final static long serialVersionUID = 6714327943935119149L;
    @SerializedName("uid")
    private String uid;
    @SerializedName("fid")
    private String fid;
    @SerializedName("dependPackegeVersion")
    private String dependPackegeVersion;
    @SerializedName("detailUrl")
    private String detailUrl;
    @SerializedName("appSize")
    private String appSize;
    @SerializedName("appId")
    private String appId;
    @SerializedName("oemid")
    private String oemid;
    @SerializedName("hid")
    private String hid;
    @SerializedName("updateTime")
    private Date updateTime;
    @SerializedName("packagename")
    private String packagename;
    @SerializedName("downloadUrl")
    private String downloadUrl;
    @SerializedName("version")
    private String version;
    @SerializedName("requiredSdk")
    private String requiredSdk;
    @SerializedName("updateStyle")
    private String updateStyle;
    @SerializedName("currentVersion")
    private String currentVersion;
    @SerializedName("resultCode")
    private String resultCode;
    @SerializedName("updateAvailabe")
    private String updateAvailabe;
    @SerializedName("resultText")
    private String resultText;
    @SerializedName("dependPackageName")
    private String dependPackageName;
    @SerializedName("introduction")
    private String introduction;

    public AssistUpdateCheck() {
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getDependPackegeVersion() {
        return this.dependPackegeVersion;
    }

    public void setDependPackegeVersion(String dependPackegeVersion) {
        this.dependPackegeVersion = dependPackegeVersion;
    }

    public String getDetailUrl() {
        return this.detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getAppSize() {
        return this.appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
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

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPackagename() {
        return this.packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRequiredSdk() {
        return this.requiredSdk;
    }

    public void setRequiredSdk(String requiredSdk) {
        this.requiredSdk = requiredSdk;
    }

    public String getUpdateStyle() {
        return this.updateStyle;
    }

    public void setUpdateStyle(String updateStyle) {
        this.updateStyle = updateStyle;
    }

    public String getCurrentVersion() {
        return this.currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getUpdateAvailabe() {
        return this.updateAvailabe;
    }

    public void setUpdateAvailabe(String updateAvailabe) {
        this.updateAvailabe = updateAvailabe;
    }

    public String getResultText() {
        return this.resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    public String getDependPackageName() {
        return this.dependPackageName;
    }

    public void setDependPackageName(String dependPackageName) {
        this.dependPackageName = dependPackageName;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
