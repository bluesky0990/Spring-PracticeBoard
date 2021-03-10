package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVO;
import com.board.domain.Page;
import com.board.domain.ReplyVO;
import com.board.service.BoardService;
import com.board.service.ReplyService;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Inject
	private BoardService service;
	@Inject
	private ReplyService replyService;

	// 게시글 목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getList(Model model) throws Exception {
		List<BoardVO> list = null;
		list = service.list();

		model.addAttribute("list", list);
	}

	// 게시물 작성 페이지
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWrite() throws Exception {
	}

	// 게시물 작성
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWirte(BoardVO vo) throws Exception {
		service.write(vo);

		return "redirect:/";
	}

	// 게시물 조회
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("bno") int bno, Model model) throws Exception {
		BoardVO vo = service.view(bno);
		model.addAttribute("view", vo);
		
		
		//댓글조회
		List<ReplyVO> reply = replyService.list(bno);
		model.addAttribute("reply", reply);
	}

	// 게시물 수정 페이지
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify(@RequestParam("bno") int bno, Model model) throws Exception {
		BoardVO vo = service.view(bno);

		model.addAttribute("view", vo);
	}

	// 게시물 수정
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(BoardVO vo) throws Exception {
		service.modify(vo);

		return "redirect:/board/view?bno=" + vo.getBno();
	}

	// 게시물 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(@RequestParam("bno") int bno) throws Exception {
		service.delete(bno);

		return "redirect:/board/list";
	}

	// 게시물 목록 + 페이징
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void getListPage(Model model, @RequestParam("num") int num) throws Exception {

		Page page = new Page();
		page.setNum(num);
		page.setCount(service.count());

		List<BoardVO> list = null;
		list = service.listPage(page.getDisplayPost(), page.getPostNum());

		model.addAttribute("list", list);
		/*
		 * model.addAttribute("pageNum", page.getPageNum());
		 * 
		 * model.addAttribute("startPageNum", page.getStartPageNum());
		 * model.addAttribute("endPageNum", page.getEndPageNum());
		 * 
		 * model.addAttribute("prev", page.isPrev()); model.addAttribute("next",
		 * page.isNext());
		 */
		model.addAttribute("page", page);

		model.addAttribute("select", num);
	}

	// 게시물 목록 + 페이징 + 검색
	@RequestMapping(value = "/listPageSearch", method = RequestMethod.GET)
	public void getListPageSearch(
			Model model, 
			@RequestParam(value="num", defaultValue = "1") int num, 
			@RequestParam(value="searchType", required = false, defaultValue = "title") String searchType, 
			@RequestParam(value="keyword", required = false, defaultValue = "") String keyword) throws Exception {

		Page page = new Page();
		page.setNum(num);
		page.setCount(service.searchCount(searchType, keyword));
		//page.setSearchTypeKeyword(searchType, keyword);
		page.setSearchType(searchType);
		page.setKeyword(keyword);

		List<BoardVO> list = null;
		list = service.listPageSearch(page.getDisplayPost(), page.getPostNum(), searchType, keyword);

		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("select", num);
		
//		model.addAttribute("searchType", searchType);
//		model.addAttribute("keyword", keyword);
	}
}