package com.my.test;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//import com.my.logger.api.Log;

public class RootStatisticsInfoxx implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -1268658471985098823L;
	private static final String TAG = "RootStatisticsInfo";
	private Map<String, PrimaryStatisticsInfo> mItemMap;
	private String mItemName;
	// 跑的次数
	private int mRunCounter = 0;
	// 运行总时间
	private long mRunTime = 0;

	public RootStatisticsInfoxx() {
		mItemMap = new ConcurrentHashMap<String, PrimaryStatisticsInfo>();		
	}

	public Map<String, PrimaryStatisticsInfo> getItemMap() {
		return mItemMap;
	}

	public String getItemName() {
		return mItemName;
	}

	public void setItemName(String mItemName) {
		this.mItemName = mItemName;
	}

	public int getRunCounter() {
		return mRunCounter;
	}

	public void setRunCounter(int mRunCounter) {
		this.mRunCounter = mRunCounter;
	}

	public long getRunTime() {
		return mRunTime;
	}

	public void setRunTime(long mRunTime) {
		this.mRunTime = mRunTime;
	}

	public void addPrimaryStatisInfoStart(String primaryKey,
			String secondKey, Object objectMark, long timeStart) {
		PrimaryStatisticsInfo primaryStatisticsInfo = null;
		synchronized (this) {
			primaryStatisticsInfo = mItemMap.get(primaryKey);
			if (primaryStatisticsInfo == null) {
				primaryStatisticsInfo = new PrimaryStatisticsInfo(primaryKey);
				mItemMap.put(primaryKey, primaryStatisticsInfo);
			}	
		}		
		primaryStatisticsInfo.addSecondStatisInfoStart(secondKey, objectMark,
				timeStart);	
	}

	public void addPrimaryStatisInfoEnd(String primaryKey, String secondKey,
			Object objectMark, long timeEnd) {
		PrimaryStatisticsInfo primaryStatisticsInfo = null;
		primaryStatisticsInfo = mItemMap.get(primaryKey);
		if (primaryStatisticsInfo == null) {
//			Log.e(TAG, "addPrimaryStatisInfoEnd调用失败,未插入Key值:" + primaryKey);
		} else {
			primaryStatisticsInfo.addSecondStatisInfoEnd(secondKey, objectMark,
					timeEnd);
		}
	}


	public void runStatist() {
		mRunCounter = 0;
		mRunTime = 0;
		for (Map.Entry<String, PrimaryStatisticsInfo> entry : mItemMap
				.entrySet()) {
			entry.getValue().runStatist();
			mRunCounter += entry.getValue().getRunCounter();
			mRunTime += entry.getValue().getRunTime();
		}
	}
}
