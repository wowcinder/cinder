package xdata.etl.cinder.dao.authorize;

import java.util.Set;

public interface AuthorizeDao {
	Set<String> getAuthorizeTokens(Integer uid);
}
