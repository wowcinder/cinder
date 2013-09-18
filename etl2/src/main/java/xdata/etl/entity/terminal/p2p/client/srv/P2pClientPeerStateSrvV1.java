
package xdata.etl.entity.terminal.p2p.client.srv;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_p2p_client_peer_state_srv_v1")
public class P2pClientPeerStateSrvV1
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = 1773309457900587607L;
    private Long peerIp;
    private Long peerUid;
    private Long peerDownloadCount;
    private Long peerReqCount;
    private Long peerRecvReqCount;
    private Long peerUploadCount;
    private Long peerDownSpeed;
    private Long peerUploadSpeed;

    public P2pClientPeerStateSrvV1() {
    }

    public Long getPeerIp() {
        return this.peerIp;
    }

    public void setPeerIp(Long peerIp) {
        this.peerIp = peerIp;
    }

    public Long getPeerUid() {
        return this.peerUid;
    }

    public void setPeerUid(Long peerUid) {
        this.peerUid = peerUid;
    }

    public Long getPeerDownloadCount() {
        return this.peerDownloadCount;
    }

    public void setPeerDownloadCount(Long peerDownloadCount) {
        this.peerDownloadCount = peerDownloadCount;
    }

    public Long getPeerReqCount() {
        return this.peerReqCount;
    }

    public void setPeerReqCount(Long peerReqCount) {
        this.peerReqCount = peerReqCount;
    }

    public Long getPeerRecvReqCount() {
        return this.peerRecvReqCount;
    }

    public void setPeerRecvReqCount(Long peerRecvReqCount) {
        this.peerRecvReqCount = peerRecvReqCount;
    }

    public Long getPeerUploadCount() {
        return this.peerUploadCount;
    }

    public void setPeerUploadCount(Long peerUploadCount) {
        this.peerUploadCount = peerUploadCount;
    }

    public Long getPeerDownSpeed() {
        return this.peerDownSpeed;
    }

    public void setPeerDownSpeed(Long peerDownSpeed) {
        this.peerDownSpeed = peerDownSpeed;
    }

    public Long getPeerUploadSpeed() {
        return this.peerUploadSpeed;
    }

    public void setPeerUploadSpeed(Long peerUploadSpeed) {
        this.peerUploadSpeed = peerUploadSpeed;
    }

}
