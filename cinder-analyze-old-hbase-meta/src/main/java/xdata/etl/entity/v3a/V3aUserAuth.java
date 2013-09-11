
package xdata.etl.entity.v3a;

import java.io.Serializable;

import xdata.etl.entity.v3a.request.V3aUserAuthRequest;
import xdata.etl.entity.v3a.response.V3aUserAuthResponse;
import xdata.etl.hbase.annotatins.HbaseEmbedded;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.v3a.V3aEntity;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_v3a_user_auth")
public class V3aUserAuth
    extends V3aEntity
    implements Serializable
{

    private final static long serialVersionUID = 9134547028891288510L;
    @HbaseEmbedded
    @SerializedName("request")
    private V3aUserAuthRequest v3aUserAuthRequest = new V3aUserAuthRequest();
    @HbaseEmbedded
    @SerializedName("response")
    private V3aUserAuthResponse v3aUserAuthResponse = new V3aUserAuthResponse();

    public V3aUserAuth() {
    }

    public V3aUserAuthRequest getV3aUserAuthRequest() {
        return this.v3aUserAuthRequest;
    }

    public void setV3aUserAuthRequest(V3aUserAuthRequest v3aUserAuthRequest) {
        this.v3aUserAuthRequest = v3aUserAuthRequest;
    }

    public V3aUserAuthResponse getV3aUserAuthResponse() {
        return this.v3aUserAuthResponse;
    }

    public void setV3aUserAuthResponse(V3aUserAuthResponse v3aUserAuthResponse) {
        this.v3aUserAuthResponse = v3aUserAuthResponse;
    }

}
