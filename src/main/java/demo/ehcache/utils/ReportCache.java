package demo.ehcache.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
/**
 * ReportCache reportCache = ReportCache.getInstance();
 * 
 * */
public class ReportCache {
    private static ReportCache reportCache = null;
    private static Cache cache = null;
    
    //实现单例模式
    public static ReportCache getInstance() {
        if(reportCache == null) {
            reportCache = new ReportCache();
        } 
        return reportCache;
    }
    
    //private Cache cache;

    public ReportCache() {
        String path = this.getClass().getResource("/ehcache.xml").getFile();

        CacheManager manager = CacheManager.create(path);
        cache = manager.getCache("reportCache");
    }

    /**
     * 设置缓存
     * @param key
     * @param o
     */
    public void setReportCache(String key, Object o) {

        Element element = new Element(key, o);
        cache.put(element);

    }

    /**
     * 从缓存中获得结果
     * @param key
     * @return
     */
    public Object getReportCache(String key) {
        Element aa = cache.get(key);
        Object r = null;
        if (aa != null) {
            r = aa.getObjectValue();
        }
        return r;

    }
    
    /**
     * 清除某个缓存
     * @param key
     */
    public boolean removeReportCache(String key) {
        return cache.remove(key);
    }

    /**
     * 清空全部缓存
     */
    public void removeAllReportCache() {
        cache.removeAll();
    }
    
    /**
     * @return the cache
     */
    public Cache getCache() {
        return cache;
    }



}
