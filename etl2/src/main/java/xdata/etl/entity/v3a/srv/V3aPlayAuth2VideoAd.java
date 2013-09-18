
package xdata.etl.entity.v3a.srv;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_v3a_play_auth2_video_ad")
public class V3aPlayAuth2VideoAd
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = -3250833832303113699L;
    @SerializedName("video")
    private String video;
    @SerializedName("txt")
    private String txt;
    @SerializedName("inserttime")
    private Long inserttime;
    @SerializedName("length")
    private Long length;

    public V3aPlayAuth2VideoAd() {
    }

    public String getVideo() {
        return this.video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTxt() {
        return this.txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Long getInserttime() {
        return this.inserttime;
    }

    public void setInserttime(Long inserttime) {
        this.inserttime = inserttime;
    }

    public Long getLength() {
        return this.length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

}
