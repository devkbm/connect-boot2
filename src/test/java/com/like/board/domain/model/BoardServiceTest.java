package com.like.board.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.boundary.BoardDTO;
import com.like.board.domain.model.Article;
import com.like.board.domain.model.Board;
import com.like.board.domain.service.AttachedFileConverter;
import com.like.board.service.BoardCommandService;
import com.like.board.service.BoardQueryService;
import com.like.file.domain.model.FileInfo;
import com.like.file.service.FileService;
import com.like.user.domain.model.User;
import com.like.user.service.UserService;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {

	private static final Logger log = LoggerFactory.getLogger(BoardServiceTest.class);
		 
	@MockBean
	private AuditorAware<String> auditorAware;
		
	@Autowired
	UserService userService;
	
	@Autowired
	BoardCommandService boardCommandService;
	
	@Autowired
	BoardQueryService boardQueryService;	
	
	@Autowired
	FileService fs;
	
	Long key;
	
	@Before 
    public void setUp() { 
		User user = userService.getUser("1");
		Optional<String> username = Optional.of(user.getUsername());
		Mockito.when(auditorAware.getCurrentAuditor()).thenReturn(username);
		
		//Board board = new Board("테스트"); 		
		//bcs.saveBoard(board);
    } 
	
	@Test	
	public void test01_게시판등록및조회() {
		//Given
		Board board = Board.builder()
						  	.boardName("테스트 게시판")
						  	.build();
		
		//When
		boardCommandService.saveBoard(board);
		
		//Then
		Board test = boardQueryService.getBoard(board.getPkBoard());
		
		assertThat(test.getBoardName()).isEqualTo("테스트 게시판");
		assertThat(test.getFromDate()).isEqualTo(LocalDate.now());
		assertThat(test.getToDate()).isEqualTo(LocalDate.of(9999, 12, 31));
		assertThat(test.getUseYn()).isEqualTo(true);			
	}
	
		
	/**
	 * 삭제 테스트 확인해봐야함
	 */
	@Test
	public void test02_게시판삭제() {		

		//Given 
		Board board = Board.builder()
						  	.boardName("테스트 게시판")
						  	.build();
		
		boardCommandService.saveBoard(board);
				
		//when 
		boardCommandService.deleteBoard(board);
		
		
		//Then
		Board test = boardQueryService.getBoard(board.pkBoard);
		
		assertThat(test).isNull();
	}
	
	@Test
	public void test03_첨부파일없이게시글등록() {
		
		//Given
		Board board = createBoard();
		
		Article article = Article.builder()
								.board(board)
								.title("제목")
								.contents("내용")
								.clearArticleChecks()
								.clearFiles()
								.build();
		
		//When 
		boardCommandService.saveArticle(article);
		
		//Then 
		Article test = boardQueryService.getArticle(article.pkArticle);
		
		assertThat(test.title).isEqualTo("제목");
		assertThat(test.contents).isEqualTo("내용");
		
	}
	
	@Test
	public void test04_게시글파일저장() throws Exception {
		
		//Given 		
		Board board = createBoard();
		
		
		//When					
		MockMultipartFile file = new MockMultipartFile("user-file", "test.txt",
                                  "multipart/form-data", "test data".getBytes());
		
		FileInfo fileInfo = fs.uploadFile(file, "test","test");		
		
		Article article = Article.builder()
				.board(board)
				.title("제목")
				.contents("내용")				
				.clearArticleChecks()
				.clearFiles()				
				.build();
				
		List<FileInfo> fileInfoList = new ArrayList<>();
		fileInfoList.add(fileInfo);
		List<AttachedFile> attachedFileList = null;
		
		attachedFileList = AttachedFileConverter.convert(article, fileInfoList);			
		article.setFiles(attachedFileList);			
	
		boardCommandService.saveArticle(article);
		
		//Then
		
	}
	
	private Board createBoard() {
		Board board = Board.builder()
						  	.boardName("테스트 게시판")
						  	.build();

		boardCommandService.saveBoard(board);
		
		return board;
	}
			
	
	
}
