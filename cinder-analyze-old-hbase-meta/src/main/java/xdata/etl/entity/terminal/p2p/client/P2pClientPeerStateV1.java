
package xdata.etl.entity.terminal.p2p.client;

import java.io.Serializable;

import xdata.etl.entity.terminal.p2p.client.srv.P2pClientPeerStateSrvV1;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_p2p_client_peer_state_v1")
public class P2pClientPeerStateV1
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = 6105479070105357394L;
    private String uuid;
    private String uid;
    private Long hid;
    private Long oemid;
    private Long chl;
    private Long peerCount;
    private HbaseAttachmentList<P2pClientPeerStateSrvV1> attachments = new HbaseAttachmentList<P2pClientPeerStateSrvV1>(this);

    public P2pClientPeerStateV1() {
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

    public Long getChl() {
        return this.chl;
    }

    public void setChl(Long chl) {
        this.chl = chl;
    }

    public Long getPeerCount() {
        return this.peerCount;
    }

    public void setPeerCount(Long peerCount) {
        this.peerCount = peerCount;
    }

    public HbaseAttachmentList<P2pClientPeerStateSrvV1> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(HbaseAttachmentList<P2pClientPeerStateSrvV1> attachments) {
        this.attachments = attachments;
    }

}
