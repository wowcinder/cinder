
package xdata.etl.entity.terminal.p2p.server;

import java.io.Serializable;

import xdata.etl.entity.terminal.p2p.server.srv.P2pServerPeerStateSrvV1;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_p2p_server_peer_state_v1")
public class P2pServerPeerStateV1
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = 9015698422364654796L;
    private String uuid;
    private String uid;
    private Long hid;
    private Long oemid;
    private Long peerCount;
    private HbaseAttachmentList<P2pServerPeerStateSrvV1> attachments = new HbaseAttachmentList<P2pServerPeerStateSrvV1>(this);

    public P2pServerPeerStateV1() {
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

    public Long getPeerCount() {
        return this.peerCount;
    }

    public void setPeerCount(Long peerCount) {
        this.peerCount = peerCount;
    }

    public HbaseAttachmentList<P2pServerPeerStateSrvV1> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(HbaseAttachmentList<P2pServerPeerStateSrvV1> attachments) {
        this.attachments = attachments;
    }

}
