
package xdata.etl.entity.terminal.live.bgn.srv;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_live_play_bgn_req_srv_v1")
public class LivePlayBgnReqSrvV1
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = 2019052894523900890L;
    private Long srvIP;
    private Integer srvPort;
    private Integer srvType;

    public LivePlayBgnReqSrvV1() {
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
