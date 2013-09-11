
package xdata.etl.entity.cdn;

import java.io.Serializable;
import java.util.Date;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_cdn_cache_update")
public class CdnCacheUpdate
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = -5341980555866257758L;
    private Date time;
    private String serverIP;
    private String nodeID;
    private String ISP;
    private String FID;
    private String segNo;
    private String operationType;
    private String operationCode;

    public CdnCacheUpdate() {
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getServerIP() {
        return this.serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getNodeID() {
        return this.nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getISP() {
        return this.ISP;
    }

    public void setISP(String ISP) {
        this.ISP = ISP;
    }

    public String getFID() {
        return this.FID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public String getSegNo() {
        return this.segNo;
    }

    public void setSegNo(String segNo) {
        this.segNo = segNo;
    }

    public String getOperationType() {
        return this.operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationCode() {
        return this.operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

}
