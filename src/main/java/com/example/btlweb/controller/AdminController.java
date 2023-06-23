package com.example.btlweb.controller;

import com.example.btlweb.entity.Admin;
import com.example.btlweb.entity.Book;
import com.example.btlweb.repository.AdminRepository;
import com.example.btlweb.repository.BookRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class AdminController {
	@Autowired
	private BookRepository repo;
	@Autowired
	private AdminRepository adminRepository;
	
	@GetMapping("/")
	String index() {
		return "index";	
		}
	@GetMapping("/admin")
	String displayBooks(Model model) {
		List<Book> books = repo.findAll();
		model.addAttribute("books", books);
		return "admin/books";
	}

	@GetMapping("/adminlg")
	String displayBookslg(Model model) {
		List<Book> books = repo.findAll();
		model.addAttribute("books", books);
		return "admin/booklg";
	}

	@GetMapping("/logout")
	String logout(HttpSession session) {
		session.setAttribute("ADMIN", null);
		session.setAttribute("PASSWORDADMIN", null);
		session.setAttribute("name", null);
		return "redirect:/admin";

	}

	@GetMapping("admin/book/{id}")
	String displayBook(@PathVariable Integer id, Model model, HttpSession session) {

		if (session.getAttribute("ADMIN") == null)
			return "redirect:/books";
		session.setAttribute("success1", false);
		session.setAttribute("success2", false);
		session.setAttribute("success3", false);
		model.addAttribute("id", id);
		if (id == -1) {
			model.addAttribute("book", new Book());
			return "admin/bookadd";
		} else {
			Book book = repo.findById(id).get();
			model.addAttribute("book", book);
			return "admin/book";
		}

	}

	@PostMapping("admin/book/save")
	String saveBook(Book book, @RequestParam("image") MultipartFile file, Model model, HttpSession session) {
		session.setAttribute("error1", false);
		Integer tmp = 0;
		try {
			String fileName = saveImageAndGetName(file);
			if (book.getId() == null) {
				if (repo.findByTieu_de(book.getTieu_de().toLowerCase(), book.getTac_gia().toLowerCase()) != null) {
					session.setAttribute("ERROR", "Sách đã tồn tại");
					return "redirect:/admin/book/-1";
				}
				book.setDa_ban(0);
				session.setAttribute("success2", true);
				tmp = 1;
			}

			if (fileName != null)
				book.setBia_sach(fileName);
			repo.save(book);
			if (tmp != 1)
				session.setAttribute("success1", true);
			return "redirect:/admin/login";
		} catch (Exception e) {
			model.addAttribute("e", e);
			return "error";
		}
	}

	@RequestMapping(value = "/admin/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminLogin(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password, Model model, HttpSession session) {
		session.setAttribute("success", false);
		if (session.getAttribute("ADMIN") != null) {
			List<Book> books = repo.findAll();
			model.addAttribute("books", books);
			return "admin/booklg";
		}

		Admin user = adminRepository.findByUsername(username);

		if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
			session.setAttribute("ADMIN", user.getUsername());
			session.setAttribute("PASSWORDADMIN", user.getPassword());
			session.setAttribute("name", user.getName());
			List<Book> books = repo.findAll();
			model.addAttribute("books", books);
			return "admin/booklg";
		} else {
			if (username == null && password == null)
				model.addAttribute("ERROR", "Xin hãy đăng nhập");
			else
				model.addAttribute("ERROR", "Tài khoản hoặc mật khẩu không tồn tại");
			List<Book> books = repo.findAll();
			model.addAttribute("books", books);
			return "admin/books";
		}

	}

	@PostMapping("/admin/delete/{id}")
	String deleteBook(@PathVariable("id") Integer id, HttpSession session) {

		session.setAttribute("success1", false);
		session.setAttribute("success2", false);
		session.setAttribute("success3", false);
		try {
			repo.deleteById(id);
			session.setAttribute("success3", true);
		} catch (Exception e) {
			session.setAttribute("error1", true);
		}
		return "redirect:/admin/login";
	}

	private String saveImageAndGetName(MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			StringBuilder fileNames = new StringBuilder();
			Path fileNameAndPath = Paths.get("D:\\eclipse\\workspace\\btlweb\\src\\main\\resources\\bia",
					file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename());
			Files.write(fileNameAndPath, file.getBytes());
			return fileNames.toString();
		}
		return null;
	}

}
