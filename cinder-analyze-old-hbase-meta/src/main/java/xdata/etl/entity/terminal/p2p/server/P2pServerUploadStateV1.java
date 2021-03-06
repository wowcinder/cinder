
package xdata.etl.entity.terminal.p2p.server;

import java.io.Serializable;

import xdata.etl.entity.terminal.p2p.server.srv.P2pServerUploadStateSrvV1;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_p2p_server_upload_state_v1")
public class P2pServerUploadStateV1
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = -3059306540678298692L;
    private String uuid;
    private String uid;
    private Long hid;
    private Long oemid;
    private Integer chInstanteNum;
    private HbaseAttachmentList<P2pServerUploadStateSrvV1> attachments = new HbaseAttachmentList<P2pServerUploadStateSrvV1>(this);

    public P2pServerUploadStateV1() {
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

    public Integer getChInstanteNum() {
        return this.chInstanteNum;
    }

    public void setChInstanteNum(Integer chInstanteNum) {
        this.chInstanteNum = chInstanteNum;
    }

    public HbaseAttachmentList<P2pServerUploadStateSrvV1> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(HbaseAttachmentList<P2pServerUploadStateSrvV1> attachments) {
        this.attachments = attachments;
    }

}
