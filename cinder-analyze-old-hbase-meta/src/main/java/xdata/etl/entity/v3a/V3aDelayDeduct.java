
package xdata.etl.entity.v3a;

import java.io.Serializable;

import xdata.etl.entity.v3a.request.V3aDelayDeductRequest;
import xdata.etl.entity.v3a.response.V3aDelayDeductResponse;
import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.v3a.V3aEntity;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_v3a_delay_deduct")
public class V3aDelayDeduct
    extends V3aEntity
    implements Serializable
{

    private final static long serialVersionUID = -3177011217188699664L;
    @HbaseEmbedded
    @SerializedName("request")
    private V3aDelayDeductRequest v3aDelayDeductRequest = new V3aDelayDeductRequest();
    @HbaseEmbedded
    @SerializedName("response")
    private V3aDelayDeductResponse v3aDelayDeductResponse = new V3aDelayDeductResponse();

    public V3aDelayDeduct() {
    }

    public V3aDelayDeductRequest getV3aDelayDeductRequest() {
        return this.v3aDelayDeductRequest;
    }

    public void setV3aDelayDeductRequest(V3aDelayDeductRequest v3aDelayDeductRequest) {
        this.v3aDelayDeductRequest = v3aDelayDeductRequest;
    }

    public V3aDelayDeductResponse getV3aDelayDeductResponse() {
        return this.v3aDelayDeductResponse;
    }

    public void setV3aDelayDeductResponse(V3aDelayDeductResponse v3aDelayDeductResponse) {
        this.v3aDelayDeductResponse = v3aDelayDeductResponse;
    }

}
