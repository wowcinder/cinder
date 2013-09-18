
package xdata.etl.entity.terminal.order.end.srv;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_order_play_end_req_srv_v3")
public class OrderPlayEndReqSrvV3
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = -973921175629115916L;
    private Long srvIP;
    private Integer srvPort;
    private Integer srvType;
    private Long connTimes;
    private Long shutTimes;

    public OrderPlayEndReqSrvV3() {
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

    public Long getConnTimes() {
        return this.connTimes;
    }

    public void setConnTimes(Long connTimes) {
        this.connTimes = connTimes;
    }

    public Long getShutTimes() {
        return this.shutTimes;
    }

    public void setShutTimes(Long shutTimes) {
        this.shutTimes = shutTimes;
    }

}
