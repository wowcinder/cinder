package xdata.etl.cinder.server.rpc;

import org.springframework.stereotype.Service;

import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import xdata.etl.cinder.gwt.client.service.MenuRpcService;

@Service
@AuthorizeGroupAnnotation("菜单")
public class MenuRpcServiceImpl implements MenuRpcService {

}
