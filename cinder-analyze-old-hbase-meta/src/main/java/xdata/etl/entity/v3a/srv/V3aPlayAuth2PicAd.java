
package xdata.etl.entity.v3a.srv;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_v3a_play_auth2_pic_ad")
public class V3aPlayAuth2PicAd
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = -2980316414367416017L;
    @SerializedName("pic")
    private String pic;
    @SerializedName("length")
    private Long length;

    public V3aPlayAuth2PicAd() {
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Long getLength() {
        return this.length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

}
