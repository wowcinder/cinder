
package xdata.etl.entity.v3a;

import java.io.Serializable;

import xdata.etl.entity.v3a.request.V3aRechargeRequest;
import xdata.etl.entity.v3a.response.V3aRechargeResponse;
import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.v3a.V3aEntity;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_v3a_recharge")
public class V3aRecharge
    extends V3aEntity
    implements Serializable
{

    private final static long serialVersionUID = 5452274700200397386L;
    @HbaseEmbedded
    @SerializedName("request")
    private V3aRechargeRequest v3aRechargeRequest = new V3aRechargeRequest();
    @HbaseEmbedded
    @SerializedName("response")
    private V3aRechargeResponse v3aRechargeResponse = new V3aRechargeResponse();

    public V3aRecharge() {
    }

    public V3aRechargeRequest getV3aRechargeRequest() {
        return this.v3aRechargeRequest;
    }

    public void setV3aRechargeRequest(V3aRechargeRequest v3aRechargeRequest) {
        this.v3aRechargeRequest = v3aRechargeRequest;
    }

    public V3aRechargeResponse getV3aRechargeResponse() {
        return this.v3aRechargeResponse;
    }

    public void setV3aRechargeResponse(V3aRechargeResponse v3aRechargeResponse) {
        this.v3aRechargeResponse = v3aRechargeResponse;
    }

}
