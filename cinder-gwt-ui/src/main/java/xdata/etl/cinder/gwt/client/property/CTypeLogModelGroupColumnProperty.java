
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import java.util.List;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;

public interface CTypeLogModelGroupColumnProperty
    extends PropertyAccess<CTypeLogModelGroupColumn>
{


    public ValueProvider<CTypeLogModelGroupColumn, HbaseTableVersion> hbaseTableVersion();

    public ValueProvider<CTypeLogModelGroupColumn, List<CTypeLogModelColumn>> columns();

    public ValueProvider<CTypeLogModelGroupColumn, CTypeLogModelGroupColumn> groupColumn();

    public ValueProvider<CTypeLogModelGroupColumn, String> name();

    public ValueProvider<CTypeLogModelGroupColumn, Integer> pos();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CTypeLogModelGroupColumn> key();

    public ValueProvider<CTypeLogModelGroupColumn, Integer> id();

    public ValueProvider<CTypeLogModelGroupColumn, Date> lastUpdateTimeStamp();

    public ValueProvider<CTypeLogModelGroupColumn, Date> createTime();

    public ValueProvider<CTypeLogModelGroupColumn, String> createTimePropertyName();

}
