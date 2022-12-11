package kr.inha.technical.college.press.board.repository;


import static kr.inha.technical.college.press.manager.entity.QBoard.board;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.inha.technical.college.press.board.dto.BoardSearchDto;
import kr.inha.technical.college.press.manager.entity.Board;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
	
	private JPAQueryFactory queryFactory;
	
	public BoardRepositoryCustomImpl(EntityManager em) {
		queryFactory = new JPAQueryFactory(em);
	}
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("title", searchQuery)) {
			return board.title.like("%" + searchQuery + "%");
		} else if (StringUtils.equals("member", searchQuery)) {
			return board.member.like("%" + searchQuery + "%");
		}
		
		return null;
		
	}
	
	@Override
	public Page<Board> getAdminItemPage(BoardSearchDto boardSearchDto, Pageable pageable) {

		List<Board> itemList = queryFactory
			.selectFrom(board)
			.where(searchByLike(boardSearchDto.getSearchBy(),
					boardSearchDto.getSearchQuery()))
			.orderBy(board.news.desc())
			.offset(pageable.getOffset())		// 어디부터 시작할거냐? 페이지 시작을 어디부터 할거냐?
			.limit(pageable.getPageSize())
			.fetch();
		
        long total = queryFactory.select(Wildcard.count).from(board)
                .where(searchByLike(boardSearchDto.getSearchBy(), boardSearchDto.getSearchQuery()))
                .fetchOne()
                ;
			
		return new PageImpl<>(itemList, pageable, total);
	}

}
