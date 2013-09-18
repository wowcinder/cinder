package xdata.etl.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseEntity;
import xdata.etl.kafka.annotatins.Kafka;

public class ClassScaner {
	protected static final String RESOURCE_PATTERN = "/**/*.class";
	protected ResourcePatternResolver resourcePatternResolver;
	protected String[] scanPackages;
	protected List<Class<?>> clazzes;

	public ClassScaner(String... scanPackages) throws Exception {
		resourcePatternResolver = new PathMatchingResourcePatternResolver();
		this.scanPackages = scanPackages;
		this.clazzes = new ArrayList<Class<?>>();
		scanPackages();
	}

	protected void scanPackages() throws Exception {
		for (String pkg : scanPackages) {
			String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
					+ ClassUtils.convertClassNameToResourcePath(pkg)
					+ RESOURCE_PATTERN;
			Resource[] resources = this.resourcePatternResolver
					.getResources(pattern);
			MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(
					this.resourcePatternResolver);
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					MetadataReader reader = readerFactory
							.getMetadataReader(resource);
					String className = reader.getClassMetadata().getClassName();
					Class<?> clazz = this.resourcePatternResolver
							.getClassLoader().loadClass(className);
					clazzes.add(clazz);
				}
			}
		}
	}

	public List<Class<?>> getClazzes() {
		return clazzes;
	}

	public void setClazzes(List<Class<?>> clazzes) {
		this.clazzes = clazzes;
	}

	public static interface ClassFilter<T> {
		public List<T> filte(ClassScaner scaner);
	}

	public static class HbaseEntityClassFilter implements
			ClassFilter<Class<? extends HbaseEntity>> {
		@SuppressWarnings("unchecked")
		@Override
		public List<Class<? extends HbaseEntity>> filte(ClassScaner scaner) {
			List<Class<? extends HbaseEntity>> list = new ArrayList<Class<? extends HbaseEntity>>();
			for (Class<?> clazz : scaner.getClazzes()) {
				if (HbaseEntity.class.isAssignableFrom(clazz)
						&& clazz.getAnnotation(HbaseTable.class) != null) {
					list.add((Class<? extends HbaseEntity>) clazz);
				}
			}
			return list;
		}
	}

	public static class KafkaTopicClassFilter implements
			ClassFilter<Class<? extends HbaseEntity>> {
		@SuppressWarnings("unchecked")
		@Override
		public List<Class<? extends HbaseEntity>> filte(ClassScaner scaner) {
			List<Class<? extends HbaseEntity>> list = new ArrayList<Class<? extends HbaseEntity>>();
			for (Class<?> clazz : scaner.getClazzes()) {
				Kafka kafka = clazz.getAnnotation(Kafka.class);
				if (kafka != null) {
					list.add((Class<? extends HbaseEntity>) clazz);
				}
			}
			return list;
		}
	}
}
