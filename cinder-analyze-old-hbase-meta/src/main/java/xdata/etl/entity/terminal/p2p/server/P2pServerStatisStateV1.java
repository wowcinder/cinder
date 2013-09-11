
package xdata.etl.entity.terminal.p2p.server;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_p2p_server_statis_state_v1")
public class P2pServerStatisStateV1
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = 1415591310541867920L;
    private String uuid;
    private String uid;
    private Long hid;
    private Long oemid;
    private Long peerCount;
    private Long peerPartnercount;
    private Long downloadCount;
    private Long uploadCount;
    private Long rebootCount;
    private Long staitsCount;

    public P2pServerStatisStateV1() {
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

    public Long getPeerPartnercount() {
        return this.peerPartnercount;
    }

    public void setPeerPartnercount(Long peerPartnercount) {
        this.peerPartnercount = peerPartnercount;
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

    public Long getRebootCount() {
        return this.rebootCount;
    }

    public void setRebootCount(Long rebootCount) {
        this.rebootCount = rebootCount;
    }

    public Long getStaitsCount() {
        return this.staitsCount;
    }

    public void setStaitsCount(Long staitsCount) {
        this.staitsCount = staitsCount;
    }

}
