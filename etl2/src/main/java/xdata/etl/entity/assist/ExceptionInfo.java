
package xdata.etl.entity.assist;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;

@HbaseEmbeddable
public class ExceptionInfo
    implements Serializable
{

    private final static long serialVersionUID = 8163229843755779212L;
    @SerializedName("logcategory")
    @HbaseColumn(name = "exceptionInfo_logcategory")
    private String logcategory;
    @SerializedName("priority")
    @HbaseColumn(name = "exceptionInfo_priority")
    private String priority;
    @SerializedName("packagename")
    @HbaseColumn(name = "exceptionInfo_packagename")
    private String packagename;
    @SerializedName("appname")
    @HbaseColumn(name = "exceptionInfo_appname")
    private String appname;
    @SerializedName("excepinfo")
    @HbaseColumn(name = "exceptionInfo_excepinfo")
    private String excepinfo;
    @SerializedName("version")
    @HbaseColumn(name = "exceptionInfo_version")
    private String version;
    @SerializedName("errcode")
    @HbaseColumn(name = "exceptionInfo_errcode")
    private String errcode;

    public ExceptionInfo() {
    }

    public String getLogcategory() {
        return this.logcategory;
    }

    public void setLogcategory(String logcategory) {
        this.logcategory = logcategory;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPackagename() {
        return this.packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getAppname() {
        return this.appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getExcepinfo() {
        return this.excepinfo;
    }

    public void setExcepinfo(String excepinfo) {
        this.excepinfo = excepinfo;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getErrcode() {
        return this.errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

}
