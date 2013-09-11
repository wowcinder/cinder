
package xdata.etl.entity.v3a;

import java.io.Serializable;

import xdata.etl.entity.v3a.request.V3aRepreatPlayQueryRequest;
import xdata.etl.entity.v3a.response.V3aRepreatPlayQueryResponse;
import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.v3a.V3aEntity;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_v3a_repreat_play_query")
public class V3aRepreatPlayQuery
    extends V3aEntity
    implements Serializable
{

    private final static long serialVersionUID = 1779926181262084572L;
    @HbaseEmbedded
    @SerializedName("request")
    private V3aRepreatPlayQueryRequest v3aRepreatPlayQueryRequest = new V3aRepreatPlayQueryRequest();
    @HbaseEmbedded
    @SerializedName("response")
    private V3aRepreatPlayQueryResponse v3aRepreatPlayQueryResponse = new V3aRepreatPlayQueryResponse();

    public V3aRepreatPlayQuery() {
    }

    public V3aRepreatPlayQueryRequest getV3aRepreatPlayQueryRequest() {
        return this.v3aRepreatPlayQueryRequest;
    }

    public void setV3aRepreatPlayQueryRequest(V3aRepreatPlayQueryRequest v3aRepreatPlayQueryRequest) {
        this.v3aRepreatPlayQueryRequest = v3aRepreatPlayQueryRequest;
    }

    public V3aRepreatPlayQueryResponse getV3aRepreatPlayQueryResponse() {
        return this.v3aRepreatPlayQueryResponse;
    }

    public void setV3aRepreatPlayQueryResponse(V3aRepreatPlayQueryResponse v3aRepreatPlayQueryResponse) {
        this.v3aRepreatPlayQueryResponse = v3aRepreatPlayQueryResponse;
    }

}
