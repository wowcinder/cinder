
package xdata.etl.entity.v3a.response;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;

@HbaseEmbeddable
public class V3aOrderResponse
    implements Serializable
{

    private final static long serialVersionUID = 3387929019954318255L;
    @SerializedName("status")
    @HbaseColumn(name = "response_status")
    private String status;

    public V3aOrderResponse() {
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
