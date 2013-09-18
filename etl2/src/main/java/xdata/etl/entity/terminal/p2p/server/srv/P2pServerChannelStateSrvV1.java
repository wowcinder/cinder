
package xdata.etl.entity.terminal.p2p.server.srv;

import java.io.Serializable;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachment;

@HbaseTable(name = "msg_p2p_server_channel_state_srv_v1")
public class P2pServerChannelStateSrvV1
    extends HbaseAttachment
    implements Serializable
{

    private final static long serialVersionUID = 5219840744753680030L;
    private Long chl;
    private Long time;
    private Long concurClient;

    public P2pServerChannelStateSrvV1() {
    }

    public Long getChl() {
        return this.chl;
    }

    public void setChl(Long chl) {
        this.chl = chl;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getConcurClient() {
        return this.concurClient;
    }

    public void setConcurClient(Long concurClient) {
        this.concurClient = concurClient;
    }

}
