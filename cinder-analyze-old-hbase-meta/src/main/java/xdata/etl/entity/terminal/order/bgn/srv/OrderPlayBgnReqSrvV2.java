
package xdata.etl.entity.terminal.order.bgn.srv;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_order_play_bgn_req_srv_v2")
public class OrderPlayBgnReqSrvV2
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = 2332794769242605881L;
    private Long srvIP;
    private Integer srvPort;
    private Integer srvType;

    public OrderPlayBgnReqSrvV2() {
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

}
