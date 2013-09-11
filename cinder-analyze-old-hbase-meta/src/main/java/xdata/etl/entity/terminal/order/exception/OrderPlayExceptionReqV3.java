
package xdata.etl.entity.terminal.order.exception;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_order_play_exception_req_v3")
public class OrderPlayExceptionReqV3
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = 4828958791271072762L;
    private String sessID;
    private Long accID;
    private Integer exception;
    private Integer satus;
    private Long srvIP;
    private Integer srvPort;
    private Integer srvType;
    private Long tick;
    private Long nodeID;
    private String HID;
    private Long OEMID;
    private String UID;
    private Long clientVersion;
    private String FID;
    private Long fIDOffset;
    private Integer errMsgLen;
    private String errMsg;

    public OrderPlayExceptionReqV3() {
    }

    public String getSessID() {
        return this.sessID;
    }

    public void setSessID(String sessID) {
        this.sessID = sessID;
    }

    public Long getAccID() {
        return this.accID;
    }

    public void setAccID(Long accID) {
        this.accID = accID;
    }

    public Integer getException() {
        return this.exception;
    }

    public void setException(Integer exception) {
        this.exception = exception;
    }

    public Integer getSatus() {
        return this.satus;
    }

    public void setSatus(Integer satus) {
        this.satus = satus;
    }

    public Long getSrvIP() {
        return this.srvIP;
    }

    public void setSrvIP(Long srvIP) {
        this.srvIP = srvIP;
    }

    public Integer getSrvPort() {
        return this.srvPort;
    }

    public void setSrvPort(Integer srvPort) {
        this.srvPort = srvPort;
    }

    public Integer getSrvType() {
        return this.srvType;
    }

    public void setSrvType(Integer srvType) {
        this.srvType = srvType;
    }

    public Long getTick() {
        return this.tick;
    }

    public void setTick(Long tick) {
        this.tick = tick;
    }

    public Long getNodeID() {
        return this.nodeID;
    }

    public void setNodeID(Long nodeID) {
        this.nodeID = nodeID;
    }

    public String getHID() {
        return this.HID;
    }

    public void setHID(String HID) {
        this.HID = HID;
    }

    public Long getOEMID() {
        return this.OEMID;
    }

    public void setOEMID(Long OEMID) {
        this.OEMID = OEMID;
    }

    public String getUID() {
        return this.UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public Long getClientVersion() {
        return this.clientVersion;
    }

    public void setClientVersion(Long clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getFID() {
        return this.FID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public Long getFIDOffset() {
        return this.fIDOffset;
    }

    public void setFIDOffset(Long fIDOffset) {
        this.fIDOffset = fIDOffset;
    }

    public Integer getErrMsgLen() {
        return this.errMsgLen;
    }

    public void setErrMsgLen(Integer errMsgLen) {
        this.errMsgLen = errMsgLen;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
