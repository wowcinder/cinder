
package xdata.etl.entity.terminal.p2p.exception;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_p2p_play_exception_req_v1")
public class P2pPlayExceptionReqV1
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = -6114633652303281253L;
    private String uuid;
    private String uid;
    private Long hid;
    private Long oemid;
    private Long errorcode;

    public P2pPlayExceptionReqV1() {
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

    public Long getErrorcode() {
        return this.errorcode;
    }

    public void setErrorcode(Long errorcode) {
        this.errorcode = errorcode;
    }

}
