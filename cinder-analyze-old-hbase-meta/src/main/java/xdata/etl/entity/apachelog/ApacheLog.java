
package xdata.etl.entity.apachelog;

import java.io.Serializable;
import java.util.Date;

import xdata.etl.hbase.annotatins.HbaseColumn;
import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;

@HbaseTable(name = "msg_apache_log")
public class ApacheLog
    extends HbaseEntity
    implements Serializable
{

    private final static long serialVersionUID = 5246850687691538947L;
    @HbaseColumn(name = "server_ip")
    private String serverIp;
    @HbaseColumn(name = "user_ip")
    private String userIp;
    @HbaseColumn(name = "request_time")
    private Date requestTime;
    @HbaseColumn(name = "request_method")
    private String requestMethod;
    @HbaseColumn(name = "request_uri")
    private String requestUri;
    @HbaseColumn(name = "url_query_string")
    private String urlQueryString;
    @HbaseColumn(name = "http_code")
    private Integer httpCode;
    @HbaseColumn(name = "response_len")
    private Integer responseLen;
    @HbaseColumn(name = "referer")
    private String referer;
    @HbaseColumn(name = "user_agent")
    private String userAgent;
    @HbaseColumn(name = "request_host")
    private String requestHost;
    @HbaseColumn(name = "used_time")
    private Long usedTime;

    public ApacheLog() {
    }

    public String getServerIp() {
        return this.serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getUserIp() {
        return this.userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Date getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestUri() {
        return this.requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getUrlQueryString() {
        return this.urlQueryString;
    }

    public void setUrlQueryString(String urlQueryString) {
        this.urlQueryString = urlQueryString;
    }

    public Integer getHttpCode() {
        return this.httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public Integer getResponseLen() {
        return this.responseLen;
    }

    public void setResponseLen(Integer responseLen) {
        this.responseLen = responseLen;
    }

    public String getReferer() {
        return this.referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getRequestHost() {
        return this.requestHost;
    }

    public void setRequestHost(String requestHost) {
        this.requestHost = requestHost;
    }

    public Long getUsedTime() {
        return this.usedTime;
    }

    public void setUsedTime(Long usedTime) {
        this.usedTime = usedTime;
    }

}
