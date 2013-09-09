/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.server.init;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotations;
import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import xdata.etl.cinder.common.util.ClassScaner;
import xdata.etl.cinder.dao.authorize.AuthorizeDao;
import xdata.etl.cinder.server.rpc.open.OpenRpcService;
import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.entity.authorize.AuthorizeGroup;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@Component
public class AuthorizeInit implements InitializingBean {
	private ClassScaner scanner;
	@Autowired
	private AuthorizeDao authorizeDao;

	public AuthorizeInit() {
		try {
			scanner = new ClassScaner("xdata.etl.cinder.server.rpc");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Set<ScanedAccessAuthority> list = new HashSet<AuthorizeInit.ScanedAccessAuthority>();
		for (Class<?> clazz : scanner.getClazzes()) {
			if (!RemoteService.class.isAssignableFrom(clazz)
					|| OpenRpcService.class.isAssignableFrom(clazz)) {
				continue;
			}
			AuthorizeGroupAnnotation authorizeGroupAnnotation = clazz
					.getAnnotation(AuthorizeGroupAnnotation.class);
			String agName = null;
			if (authorizeGroupAnnotation != null
					&& authorizeGroupAnnotation.value().length() != 0) {
				agName = authorizeGroupAnnotation.value();
			}
			for (Method method : clazz.getMethods()) {
				AuthorizeAnnotation authorizeAnnotation = method
						.getAnnotation(AuthorizeAnnotation.class);
				AuthorizeAnnotations authorizeAnnotations = method
						.getAnnotation(AuthorizeAnnotations.class);
				if (authorizeAnnotation == null && authorizeAnnotations == null) {
					continue;
				}
				if (authorizeAnnotation != null) {
					ScanedAccessAuthority s = ScanedAccessAuthority.get(
							authorizeAnnotation, agName);
					if (s != null) {
						list.add(s);
					}
				}
				if (authorizeAnnotations != null) {
					for (AuthorizeAnnotation authorizeAnnotation2 : authorizeAnnotations
							.value()) {
						ScanedAccessAuthority s = ScanedAccessAuthority.get(
								authorizeAnnotation2, agName);
						if (s != null) {
							list.add(s);
						}
					}
				}
			}

		}
		deal(list);
	}

	/**
	 * @param list
	 */
	@Transactional
	private void deal(Set<ScanedAccessAuthority> list) {
		HashMap<String, List<ScanedAccessAuthority>> map = new HashMap<String, List<ScanedAccessAuthority>>();
		for (ScanedAccessAuthority scanedAccessAuthority : list) {
			String group = scanedAccessAuthority.getGroup();
			if (!map.containsKey(group)) {
				map.put(group, new ArrayList<ScanedAccessAuthority>());
			}
			map.get(group).add(scanedAccessAuthority);
		}
		for (String group : map.keySet()) {
			List<ScanedAccessAuthority> items = map.get(group);
			Integer gid = authorizeDao.queryAuthorizeGroupIdByName(group);
			AuthorizeGroup ag = new AuthorizeGroup();
			ag.setName(group);
			if (gid != null) {
				ag.setId(gid);
			} else {
				authorizeDao.saveAuthorizeGroup(ag);
			}

			for (ScanedAccessAuthority scanedAccessAuthority : items) {
				Authorize old = authorizeDao.queryAuthorizeIdByName(ag.getId(),
						scanedAccessAuthority.getValue());
				if (old == null) {
					Authorize authority = new Authorize();
					authority.setGroup(ag);
					authority.setName(scanedAccessAuthority.value);
					authorizeDao.saveAuthorize(authority);
				}
			}
		}
	}

	public static class ScanedAccessAuthority {
		private String group;
		private String value;

		public ScanedAccessAuthority() {
		}

		public ScanedAccessAuthority(String group, String value) {
			this.group = group;
			this.value = value;
		}

		public String getGroup() {
			return group;
		}

		public void setGroup(String group) {
			this.group = group;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public static ScanedAccessAuthority get(
				AuthorizeAnnotation authorizeAnnotation, String agName) {
			String realAgName = authorizeAnnotation.group();
			if (realAgName == null || realAgName.length() == 0) {
				realAgName = agName;
			}
			if (realAgName == null || realAgName.length() == 0
					|| authorizeAnnotation.value() == null
					|| authorizeAnnotation.value().length() == 0) {
				return null;
			}
			return new ScanedAccessAuthority(realAgName,
					authorizeAnnotation.value());
		}

		@Override
		public int hashCode() {
			return getGroup().hashCode() + 3 * getValue().hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof ScanedAccessAuthority)) {
				return false;
			}
			ScanedAccessAuthority that = (ScanedAccessAuthority) obj;
			return that.getGroup().equals(this.getGroup())
					&& that.getValue().equals(this.getValue());
		}

	}

}
