package blog.service;

import java.sql.Connection;
import java.util.List;

import blog.commons.DBUtil;
import blog.dao.SubjectDao;
import blog.vo.Subject;

public class SubjectService {
	  private SubjectDao subjectDao;
	
	  
	  public List<Subject> getSubjectListAll() {
		  Connection conn = null;
		  List<Subject> subjectList = null;
		  try {
			  conn = DBUtil.getConnection();
			  subjectDao = new SubjectDao();
			  subjectList = subjectDao.selectSubjectListAll(conn);
			  System.out.println(subjectList.size()+"<--List<Subject> subjectList.size() = subjectDao.selectSubjectListAll(conn)");
			  } catch (Exception e) {
				  e.printStackTrace();
			  } finally {
				  DBUtil.close(null, null, conn);
			  }
			  return subjectList;
	  }
}
