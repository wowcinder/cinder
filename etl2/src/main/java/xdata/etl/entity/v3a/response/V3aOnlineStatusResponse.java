
package xdata.etl.entity.v3a.response;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;

@HbaseEmbeddable
public class V3aOnlineStatusResponse
    implements Serializable
{

    private final static long serialVersionUID = -2972055192546360498L;
    @SerializedName("status")
    @HbaseColumn(name = "response_status")
    private String status;

    public V3aOnlineStatusResponse() {
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
