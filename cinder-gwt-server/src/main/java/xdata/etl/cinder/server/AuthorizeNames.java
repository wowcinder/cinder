package xdata.etl.cinder.server;

public class AuthorizeNames {
	public static class AuthorizeAnnotationNamesForMenu {
		public static final String GROUP = "菜单";
		public static final String ALL = "菜单管理";
	}

	public static class AuthorizeAnnotationNamesForUser {
		public static final String GROUP = "用户管理";

		public static final String ADD_USER = "添加用户";
		public static final String UPDATE_USER = "修改用户信息";
		public static final String DELETE_USER = "删除用户";
		public static final String QUERY_USER = "查询用户";

		public static final String ADD_USER_GROUP = "添加用户组";
		public static final String UPDATE_USER_GROUP = "修改用户信息组";
		public static final String DELETE_USER_GROUP = "删除用户组";
		public static final String QUERY_USER_GROUP = "查询用户组";
	}

	public static class AuthorizeAnnotationNamesForLogModelMeta {
		public static final String GROUP = "CType日志模型";

		public static final String ADD_LOG_MODEL = "添加模型";
		public static final String UPDATE_LOG_MODEL = "修改模型";
		public static final String DELETE_LOG_MODEL = "删除模型";
		public static final String QUERY_LOG_MODEL = "查询模型";

		public static final String ADD_LOG_MODEL_VERSION = "添加模型版本";
		public static final String UPDATE_LOG_MODEL_VERSION = "修改模型版本";
		public static final String DELETE_LOG_MODEL_VERSION = "删除模型版本";
		public static final String QUERY_LOG_MODEL_VERSION = "查询模型版本";

	}

	public static class AuthorizeAnnotationNamesForHbaseMeta {
		public static final String GROUP = "HbaseMeta";

		public static final String ADD_TABLE = "添加表";
		public static final String UPDATE_TABLE = "修改表";
		public static final String DELETE_TABLE = "删除表";
		public static final String QUERY_TABLE = "查询表";

		public static final String ADD_TABLE_VERSION = "添加表版本";
		public static final String UPDATE_TABLE_VERSION = "修改表版本";
		public static final String DELETE_TABLE_VERSION = "删除表版本";
		public static final String QUERY_TABLE_VERSION = "查询表版本";

		public static final String ADD_TABLE_COLUMN = "添加表字段";
		public static final String UPDATE_TABLE_COLUMN = "修改表字段";
		public static final String DELETE_TABLE_COLUMN = "删除表字段";
		public static final String QUERY_TABLE_COLUMN = "查询表字段";
	}

	public static class AuthorizeAnnotationNamesForHbaseQuery {
		public static final String GROUP = "HbaseQuery";

		public static final String QUERY = "查询";

	}

}