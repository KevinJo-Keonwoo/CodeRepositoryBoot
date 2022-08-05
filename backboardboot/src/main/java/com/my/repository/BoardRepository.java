package com.my.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.my.dto.Board;

public interface BoardRepository extends CrudRepository<Board, Long> { //Board자리는 사용할 엔터티 자료형은 Wrapper Class로 사용해야함 
	List<Board> findAll(org.springframework.data.domain.Pageable paging);

	@Query(value = "SELECT *\r\n"
			+ "FROM (\r\n"
			+ "  SELECT rownum r, a.*\r\n"
			+ "  FROM (SELECT level, \r\n"
			+ "               board_no, board_parent_no, board_title, board_content, board_id, board_viewcount, board_dt\r\n"
			+ "          FROM board_jpa \r\n"
			+ "          START WITH board_parent_no = 0\r\n"
			+ "          CONNECT BY PRIOR board_no = board_parent_no\r\n"
			+ "          ORDER SIBLINGS BY board_no DESC\r\n"
			+ "  ) a\r\n"
			+ ")\r\n"
			+ "WHERE r BETWEEN ?1 AND ?2"
			,nativeQuery = true)
	List<Board> findByPage(int startRow, int endRow);
	
	/**
	 * 검색어를 포함한 게시글제목 또는 검색어
	 * @param word
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	@Query(value = "SELECT *\r\n"
			+ "FROM (\r\n"
			+ "  SELECT rownum r, a.*\r\n"
			+ "  FROM (SELECT level, \r\n"
			+ "  				board_no, board_parent_no, board_title, board_content, board_dt, board_id, board_viewcount\r\n"
			+ "          FROM board_jpa \r\n"
			+ "          WHERE board_title LIKE %?1% OR board_id LIKE %?1% \r\n"
			+ "          START WITH board_parent_no = 0\r\n"
			+ "          CONNECT BY PRIOR board_no = board_parent_no\r\n"
			+ "          ORDER SIBLINGS BY board_no DESC\r\n"
			+ "  ) a\r\n"
			+ ")\r\n"
			+ "WHERE r BETWEEN ?2 AND ?3"
			,nativeQuery = true)
	List<Board> findByWord(String word, int startRow, int endRow);
	
//	@Query(value = "SELECT COUNT(*) FROM board")
//	int	findCount();
	
	@Query(value = "DELETE FROM board_jpa"
			+ "		WHERE board_no IN ( SELECT board_no"
			+ "							FROM board_jpa"
			+ "							START WITH board_parent_no = ?1"
			+ "							CONNECT BY PRIOR board_no = board_parent_no)"
			, nativeQuery = true)
	void deleteReply(Long boardNo);
	
}



