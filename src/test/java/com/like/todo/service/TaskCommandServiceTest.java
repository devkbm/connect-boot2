package com.like.todo.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.todo.domain.model.TaskGroup;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TaskCommandServiceTest {

	private static final Logger log = LoggerFactory.getLogger(TaskCommandServiceTest.class);
	
	@Autowired
	TaskCommandService tcs;
	
	@Autowired
	TaskQueryService tqs;
	
	@Test
	@Ignore
	public void 업무그룹조회() {
		List<?> list = null;// tqs.getTaskGroupList();
		
		log.info(list.toString());
	}
	
	@Test
	@Ignore
	public void 업무그룹등록() {
		TaskGroup taskGroup = new TaskGroup("제목없는 목록");
		
		//tcs.saveTaskGroup(taskGroup);
		
		log.info(taskGroup.toString());
	}
	
	@Test
	@Ignore
	public void 업무등록() {
		
	}
	
	
	/*@Test
	public void getBoardHierarchy() throws Exception {		
		List list = bs.getBoardHierarchy(2L);		
		log.info(list.toString());
	}
	
	@Test
	public void insertBoard() throws Exception {
		Board board = new Board("Test");
		List<Article> articles = new ArrayList<Article>();
		Article article = new Article();
		
		FileInfo file= new FileInfo();
		file.setFileNm("test");
		article.addAttachedFile(file);
		
		articles.add(article);
		board.setArticles(articles);
		bs.saveBoard(board);		
		
		bs.getBoardList();
	}*/
			
}
