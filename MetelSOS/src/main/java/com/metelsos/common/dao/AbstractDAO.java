package com.metelsos.common.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
/**
 * 
* <pre>
* com.metelsos.common.dao
*   |_ AbstractDAO.java
* </pre>
* 
* Desc : 모든 Dao 클래스에서 공통으로 상속받는 DAO 클래스
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. 오후 2:49:29
* @Version : 
 */
public class AbstractDAO {
	protected Log log = LogFactory.getLog(AbstractDAO.class);
    
    @Autowired
    private SqlSessionTemplate sqlSession;
     
    protected void printQueryId(String queryId) {
        if(log.isDebugEnabled()){
            log.debug("\t QueryId  \t:  " + queryId);
        }
    }
     
    public Object insert(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.insert(queryId, params);
    }
     
    public Object update(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.update(queryId, params);
    }
     
    public Object delete(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.delete(queryId, params);
    }
     
    public Object selectOne(String queryId){
        printQueryId(queryId);
        return sqlSession.selectOne(queryId);
    }
     
    public Object selectOne(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.selectOne(queryId, params);
    }
     
    @SuppressWarnings("rawtypes")
    public List selectList(String queryId){
        printQueryId(queryId);
        return sqlSession.selectList(queryId);
    }
     
    @SuppressWarnings("rawtypes")
    public List selectList(String queryId, Object params){
        printQueryId(queryId);
        return sqlSession.selectList(queryId,params);
    }
    
    /**
     * 
     * Desc : 페이징 처리를 위한 selectList, START부터 END까지의 ROWNUM에 해당하는 데이터를 가져옴
     * @Method Name : selectPagingList
     * @param queryId
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    public Object selectPagingList(String queryId, Object params){
        printQueryId(queryId);
        HashMap<String,Object> map = (HashMap<String,Object>)params;
         
        String strPageIndex = (String)map.get("PAGE_INDEX");
        String strPageRow = (String)map.get("PAGE_ROW");
        int nPageIndex = 0;
        int nPageRow = 5;
         
        if(StringUtils.isEmpty(strPageIndex) == false){
            nPageIndex = Integer.parseInt(strPageIndex)-1;
        }
        if(StringUtils.isEmpty(strPageRow) == false){
            nPageRow = Integer.parseInt(strPageRow);
        }
        map.put("START", (nPageIndex * nPageRow) + 1);
        map.put("END", (nPageIndex * nPageRow) + nPageRow);
         
        return sqlSession.selectList(queryId, map);
    }
}
