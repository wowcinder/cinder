package xdata.etl.zookeeper.lock;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.ACL;

public class WriteBlockLock {
	private final WriteLock writeLock;
	private final int interval;

	private final static int DEFAULT_INTERVAL = 10;

	public WriteBlockLock(ZooKeeper zookeeper, String dir, List<ACL> acl,
			LockListener callback, int interval) {
		writeLock = new WriteLock(zookeeper, dir, acl, callback);
		this.interval = interval;
		createDir();
	}

	public WriteBlockLock(ZooKeeper zookeeper, String dir, List<ACL> acl) {
		this(zookeeper, dir, acl, null, DEFAULT_INTERVAL);
	}

	public WriteBlockLock(ZooKeeper zookeeper, String dir, List<ACL> acl,
			int interval) {
		this(zookeeper, dir, acl, null, interval);
	}

	public void lock() throws KeeperException, InterruptedException {
		while (!writeLock.lock()) {
			TimeUnit.SECONDS.sleep(interval);
		}
	}

	public void unlock() {
		writeLock.unlock();
	}

	protected void createDir() {
		String dir = writeLock.getDir();
		ZooKeeper zk = writeLock.getZookeeper();
		String[] paths = dir.split("/");
		String path = "";
		for (String subPath : paths) {
			if (subPath.equals("")) {
				continue;
			}
			path += "/" + subPath;
			try {
				if (zk.exists(path, false) != null) {
					continue;
				}
			} catch (KeeperException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				zk.create(path, new byte[] {}, Ids.OPEN_ACL_UNSAFE,
						CreateMode.PERSISTENT);
			} catch (KeeperException e) {
				continue;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
