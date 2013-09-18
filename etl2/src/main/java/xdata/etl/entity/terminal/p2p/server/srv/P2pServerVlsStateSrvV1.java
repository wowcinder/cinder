
package xdata.etl.entity.terminal.p2p.server.srv;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_p2p_server_vls_state_srv_v1")
public class P2pServerVlsStateSrvV1
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = -8731421025397356937L;
    private Long chl;
    private Long speed;

    public P2pServerVlsStateSrvV1() {
    }

    public Long getChl() {
        return this.chl;
    }

    public void setChl(Long chl) {
        this.chl = chl;
    }

    public Long getSpeed() {
        return this.speed;
    }

    public void setSpeed(Long speed) {
        this.speed = speed;
    }

}
