
package xdata.etl.entity.assist;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;

@HbaseEmbeddable
public class FeedBackInfo
    implements Serializable
{

    private final static long serialVersionUID = 3365889627012418716L;
    @SerializedName("content")
    @HbaseColumn(name = "feedBackInfo_content")
    private String content;
    @SerializedName("phone")
    @HbaseColumn(name = "feedBackInfo_phone")
    private String phone;
    @SerializedName("email")
    @HbaseColumn(name = "feedBackInfo_email")
    private String email;
    @SerializedName("optioncode")
    @HbaseColumn(name = "feedBackInfo_optioncode")
    private String optioncode;
    @SerializedName("version")
    @HbaseColumn(name = "feedBackInfo_version")
    private String version;

    public FeedBackInfo() {
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOptioncode() {
        return this.optioncode;
    }

    public void setOptioncode(String optioncode) {
        this.optioncode = optioncode;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
