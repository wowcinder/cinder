
package xdata.etl.entity.v3a.srv;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_v3a_play_auth2_txt_ad")
public class V3aPlayAuth2TxtAd
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = -8338716344391548902L;
    @SerializedName("txtid")
    private Integer txtid;
    @SerializedName("txt")
    private String txt;
    @SerializedName("inserttime")
    private Long inserttime;

    public V3aPlayAuth2TxtAd() {
    }

    public Integer getTxtid() {
        return this.txtid;
    }

    public void setTxtid(Integer txtid) {
        this.txtid = txtid;
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

}
