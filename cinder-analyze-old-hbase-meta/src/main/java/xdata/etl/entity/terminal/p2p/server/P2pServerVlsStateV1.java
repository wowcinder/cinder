
package xdata.etl.entity.terminal.p2p.server;

import java.io.Serializable;

import xdata.etl.entity.terminal.p2p.server.srv.P2pServerVlsStateSrvV1;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_p2p_server_vls_state_v1")
public class P2pServerVlsStateV1
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = 1309445689304760398L;
    private String uuid;
    private String uid;
    private Long hid;
    private Long oemid;
    private Long totalCh;
    private HbaseAttachmentList<P2pServerVlsStateSrvV1> attachments = new HbaseAttachmentList<P2pServerVlsStateSrvV1>(this);

    public P2pServerVlsStateV1() {
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

    public Long getTotalCh() {
        return this.totalCh;
    }

    public void setTotalCh(Long totalCh) {
        this.totalCh = totalCh;
    }

    public HbaseAttachmentList<P2pServerVlsStateSrvV1> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(HbaseAttachmentList<P2pServerVlsStateSrvV1> attachments) {
        this.attachments = attachments;
    }

}
