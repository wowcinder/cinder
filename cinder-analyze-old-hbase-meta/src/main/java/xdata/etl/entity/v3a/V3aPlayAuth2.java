
package xdata.etl.entity.v3a;

import java.io.Serializable;

import xdata.etl.entity.v3a.request.V3aPlayAuth2Request;
import xdata.etl.entity.v3a.response.V3aPlayAuth2Response;
import xdata.etl.entity.v3a.srv.V3aPlayAuth2PicAd;
import xdata.etl.entity.v3a.srv.V3aPlayAuth2TxtAd;
import xdata.etl.entity.v3a.srv.V3aPlayAuth2VideoAd;
import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.v3a.V3aEntity;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_v3a_play_auth2")
public class V3aPlayAuth2
    extends V3aEntity
    implements Serializable
{

    private final static long serialVersionUID = 7535110380552470855L;
    @HbaseEmbedded
    @SerializedName("request")
    private V3aPlayAuth2Request v3aPlayAuth2Request = new V3aPlayAuth2Request();
    @HbaseEmbedded
    @SerializedName("response")
    private V3aPlayAuth2Response v3aPlayAuth2Response = new V3aPlayAuth2Response();
    private HbaseAttachmentList<V3aPlayAuth2PicAd> v3aPlayAuth2PicAd = new HbaseAttachmentList<V3aPlayAuth2PicAd>(this);
    private HbaseAttachmentList<V3aPlayAuth2TxtAd> v3aPlayAuth2TxtAd = new HbaseAttachmentList<V3aPlayAuth2TxtAd>(this);
    private HbaseAttachmentList<V3aPlayAuth2VideoAd> v3aPlayAuth2VideoAd = new HbaseAttachmentList<V3aPlayAuth2VideoAd>(this);

    public V3aPlayAuth2() {
    }

    public V3aPlayAuth2Request getV3aPlayAuth2Request() {
        return this.v3aPlayAuth2Request;
    }

    public void setV3aPlayAuth2Request(V3aPlayAuth2Request v3aPlayAuth2Request) {
        this.v3aPlayAuth2Request = v3aPlayAuth2Request;
    }

    public V3aPlayAuth2Response getV3aPlayAuth2Response() {
        return this.v3aPlayAuth2Response;
    }

    public void setV3aPlayAuth2Response(V3aPlayAuth2Response v3aPlayAuth2Response) {
        this.v3aPlayAuth2Response = v3aPlayAuth2Response;
    }

    public HbaseAttachmentList<V3aPlayAuth2PicAd> getV3aPlayAuth2PicAd() {
        return this.v3aPlayAuth2PicAd;
    }

    public void setV3aPlayAuth2PicAd(HbaseAttachmentList<V3aPlayAuth2PicAd> v3aPlayAuth2PicAd) {
        this.v3aPlayAuth2PicAd = v3aPlayAuth2PicAd;
    }

    public HbaseAttachmentList<V3aPlayAuth2TxtAd> getV3aPlayAuth2TxtAd() {
        return this.v3aPlayAuth2TxtAd;
    }

    public void setV3aPlayAuth2TxtAd(HbaseAttachmentList<V3aPlayAuth2TxtAd> v3aPlayAuth2TxtAd) {
        this.v3aPlayAuth2TxtAd = v3aPlayAuth2TxtAd;
    }

    public HbaseAttachmentList<V3aPlayAuth2VideoAd> getV3aPlayAuth2VideoAd() {
        return this.v3aPlayAuth2VideoAd;
    }

    public void setV3aPlayAuth2VideoAd(HbaseAttachmentList<V3aPlayAuth2VideoAd> v3aPlayAuth2VideoAd) {
        this.v3aPlayAuth2VideoAd = v3aPlayAuth2VideoAd;
    }

}
