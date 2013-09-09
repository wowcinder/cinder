package xdata.etl.cinder.dao.authorize;

import java.util.Set;

import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.entity.authorize.AuthorizeGroup;

public interface AuthorizeDao {
	Set<String> getAuthorizeTokens(Integer uid);

	/**
	 * @param group
	 * @return
	 */
	Integer queryAuthorizeGroupIdByName(String group);

	/**
	 * @param ag
	 * @return
	 */
	AuthorizeGroup saveAuthorizeGroup(AuthorizeGroup ag);

	/**
	 * @param id
	 * @param value
	 * @return
	 */
	Authorize queryAuthorizeIdByName(Integer id, String value);

	/**
	 * @param authority
	 */
	Authorize saveAuthorize(Authorize authority);
}
