
package xdata.etl.entity.terminal.p2p.bgn.srv;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_p2p_play_bgn_req_srv_v3")
public class P2pPlayBgnReqSrvV3
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = -2836469794327019093L;
    private Long srvIP;
    private Integer srvPort;
    private Integer srvType;

    public P2pPlayBgnReqSrvV3() {
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
