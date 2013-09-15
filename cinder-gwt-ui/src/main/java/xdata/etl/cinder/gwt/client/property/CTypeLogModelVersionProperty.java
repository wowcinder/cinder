
package xdata.etl.cinder.gwt.client.property;

import java.util.Date;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;

public interface CTypeLogModelVersionProperty
    extends PropertyAccess<CTypeLogModelVersion>
{


    public ValueProvider<CTypeLogModelVersion, CTypeLogModelGroupColumn> rootNode();

    public ValueProvider<CTypeLogModelVersion, String> desc();

    public ValueProvider<CTypeLogModelVersion, String> version();

    public ValueProvider<CTypeLogModelVersion, CTypeLogModel> model();

    @com.google.gwt.editor.client.Editor.Path("id")
    public ModelKeyProvider<CTypeLogModelVersion> key();

    public ValueProvider<CTypeLogModelVersion, Integer> id();

    public ValueProvider<CTypeLogModelVersion, Date> lastUpdateTimeStamp();

    public ValueProvider<CTypeLogModelVersion, Date> createTime();

    public ValueProvider<CTypeLogModelVersion, String> createTimePropertyName();

}
