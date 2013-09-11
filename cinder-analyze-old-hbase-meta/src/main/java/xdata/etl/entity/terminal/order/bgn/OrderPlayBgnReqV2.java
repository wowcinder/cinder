
package xdata.etl.entity.terminal.order.bgn;

import java.io.Serializable;

import xdata.etl.entity.terminal.order.bgn.srv.OrderPlayBgnReqSrvV2;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_order_play_bgn_req_v2")
public class OrderPlayBgnReqV2
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = 4912301042935877051L;
    private Long OEMID;
    private Long vendorID;
    private Long curVer;
    private Long buildTime;
    private String HID;
    private String UID;
    private Long localIP;
    private String sessID;
    private Integer sessType;
    private Integer sessStatus;
    private String FID;
    private Long mSize;
    private Integer mmime;
    private Long idxLen;
    private Integer urlLen;
    private String URL;
    private Long playTick;
    private Integer srvNum;
    private HbaseAttachmentList<OrderPlayBgnReqSrvV2> attachments = new HbaseAttachmentList<OrderPlayBgnReqSrvV2>(this);

    public OrderPlayBgnReqV2() {
    }

    public Long getOEMID() {
        return this.OEMID;
    }

    public void setOEMID(Long OEMID) {
        this.OEMID = OEMID;
    }

    public Long getVendorID() {
        return this.vendorID;
    }

    public void setVendorID(Long vendorID) {
        this.vendorID = vendorID;
    }

    public Long getCurVer() {
        return this.curVer;
    }

    public void setCurVer(Long curVer) {
        this.curVer = curVer;
    }

    public Long getBuildTime() {
        return this.buildTime;
    }

    public void setBuildTime(Long buildTime) {
        this.buildTime = buildTime;
    }

    public String getHID() {
        return this.HID;
    }

    public void setHID(String HID) {
        this.HID = HID;
    }

    public String getUID() {
        return this.UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public Long getLocalIP() {
        return this.localIP;
    }

    public void setLocalIP(Long localIP) {
        this.localIP = localIP;
    }

    public String getSessID() {
        return this.sessID;
    }

    public void setSessID(String sessID) {
        this.sessID = sessID;
    }

    public Integer getSessType() {
        return this.sessType;
    }

    public void setSessType(Integer sessType) {
        this.sessType = sessType;
    }

    public Integer getSessStatus() {
        return this.sessStatus;
    }

    public void setSessStatus(Integer sessStatus) {
        this.sessStatus = sessStatus;
    }

    public String getFID() {
        return this.FID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public Long getMSize() {
        return this.mSize;
    }

    public void setMSize(Long mSize) {
        this.mSize = mSize;
    }

    public Integer getMmime() {
        return this.mmime;
    }

    public void setMmime(Integer mmime) {
        this.mmime = mmime;
    }

    public Long getIdxLen() {
        return this.idxLen;
    }

    public void setIdxLen(Long idxLen) {
        this.idxLen = idxLen;
    }

    public Integer getUrlLen() {
        return this.urlLen;
    }

    public void setUrlLen(Integer urlLen) {
        this.urlLen = urlLen;
    }

    public String getURL() {
        return this.URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Long getPlayTick() {
        return this.playTick;
    }

    public void setPlayTick(Long playTick) {
        this.playTick = playTick;
    }

    public Integer getSrvNum() {
        return this.srvNum;
    }

    public void setSrvNum(Integer srvNum) {
        this.srvNum = srvNum;
    }

    public HbaseAttachmentList<OrderPlayBgnReqSrvV2> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(HbaseAttachmentList<OrderPlayBgnReqSrvV2> attachments) {
        this.attachments = attachments;
    }

}
