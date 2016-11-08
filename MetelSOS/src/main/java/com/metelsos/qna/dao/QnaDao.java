package com.metelsos.qna.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.qna.vo.QnaVo;

@Repository("qnaDao")
public class QnaDao extends AbstractDAO{

	public List<QnaVo> getQnaBest5() throws Exception{
		return (List<QnaVo>)selectList("qna.getQnaBest5");
	}
	
}
