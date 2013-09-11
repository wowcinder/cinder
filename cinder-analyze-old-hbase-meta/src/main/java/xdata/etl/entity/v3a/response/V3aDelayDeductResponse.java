
package xdata.etl.entity.v3a.response;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseEmbeddable;

@HbaseEmbeddable
public class V3aDelayDeductResponse
    implements Serializable
{

    private final static long serialVersionUID = -4121086455302963105L;
    @SerializedName("status")
    @HbaseColumn(name = "response_status")
    private String status;
    @SerializedName("balance")
    @HbaseColumn(name = "response_balance")
    private Integer balance;

    public V3aDelayDeductResponse() {
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBalance() {
        return this.balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

}
