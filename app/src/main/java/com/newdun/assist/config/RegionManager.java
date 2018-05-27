package com.newdun.assist.config;

import com.newdun.assist.model.City;
import com.newdun.assist.cache.DataCache;
import com.newdun.frame.model.Content;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class RegionManager {

	private static RegionManager instance;

	private List<Content> mHotCitys = new ArrayList<Content>();
	private List<Content> mOpenCitys = new ArrayList<Content>();

	private RegionManager() {
	}

	public void setCitys(List<Content> hotCitys, List<Content> openCitys) {
		mHotCitys = hotCitys;
		mOpenCitys = openCitys;
//		Comparator<Content> comp = new BaiduIdComparator();
//		Collections.sort(mOpenCitys, comp);
		DataCache.save(hotCitys, "hotCity");
		DataCache.save(openCitys, "openCity");
	}

	public void loadCitys() {
		mHotCitys = DataCache.loadList(City.class, "hotCity");
		mOpenCitys = DataCache.loadList(City.class, "openCity");
//		Comparator<Content> comp = new BaiduIdComparator();
//		Collections.sort(mOpenCitys, comp);
	}

	public static RegionManager getInstance() {
		if (instance == null) {
			instance = new RegionManager();
		}
		return instance;
	}

	public City getCityByBaiduCode(String baiduCode) {
		try {
//			Comparator<Content> comp = new BaiduIdComparator();
			int baiduId = Integer.valueOf(baiduCode);
			Iterator<Content> iterator = mOpenCitys.iterator();
			while (iterator.hasNext()) {
				City city = (City) iterator.next();
				if (city.baiduId == baiduId) {
					return city;
				}
			}
//			int pos = Collections.binarySearch(mOpenCitys, content, comp);
//			if (pos >= 0) {
//				return (City) mOpenCitys.get(pos);
//			}
		} catch (Exception e) {
			
		}
		return null;
	}

	public class BaiduIdComparator implements Comparator {
		@Override
		public int compare(Object lhs, Object rhs) {
			City a = (City) lhs;
			City b = (City) rhs;

			return (a.baiduId - b.baiduId);
		}
	}

	public List<Content> getHotCitys() {
		return mHotCitys;
	}
	
	public List<Content> getOpenCitys() {
		return mOpenCitys;
	}
}
