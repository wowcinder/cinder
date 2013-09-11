
package xdata.etl.entity.terminal.p2p.client;

import java.io.Serializable;

import xdata.etl.entity.terminal.p2p.client.srv.P2pClientStatisStateSrvV1;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_p2p_client_statis_state_v1")
public class P2pClientStatisStateV1
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = 9005417965245449524L;
    private String uuid;
    private String uid;
    private Long hid;
    private Long oemid;
    private Long downloadCount;
    private Long uploadCount;
    private Long chCount;
    private HbaseAttachmentList<P2pClientStatisStateSrvV1> attachments = new HbaseAttachmentList<P2pClientStatisStateSrvV1>(this);

    public P2pClientStatisStateV1() {
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getHid() {
        return this.hid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }

    public Long getOemid() {
        return this.oemid;
    }

    public void setOemid(Long oemid) {
        this.oemid = oemid;
    }

    public Long getDownloadCount() {
        return this.downloadCount;
    }

    public void setDownloadCount(Long downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Long getUploadCount() {
        return this.uploadCount;
    }

    public void setUploadCount(Long uploadCount) {
        this.uploadCount = uploadCount;
    }

    public Long getChCount() {
        return this.chCount;
    }

    public void setChCount(Long chCount) {
        this.chCount = chCount;
    }

    public HbaseAttachmentList<P2pClientStatisStateSrvV1> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(HbaseAttachmentList<P2pClientStatisStateSrvV1> attachments) {
        this.attachments = attachments;
    }

}
