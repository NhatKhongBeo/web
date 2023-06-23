package com.example.btlweb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.btlweb.entity.Book;
import com.example.btlweb.entity.Order;
import com.example.btlweb.entity.Review;
import com.example.btlweb.entity.User;
import com.example.btlweb.repository.BookRepository;
import com.example.btlweb.repository.OrderRepository;
import com.example.btlweb.repository.ReviewRepository;
import com.example.btlweb.repository.UserRepository;
import com.example.btlweb.service.OrderSevice;
import com.example.btlweb.service.ReviewService;
import com.example.btlweb.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserService userService;
	@Autowired
	private OrderSevice orderSevice;
	@Autowired
	private ReviewService reviewService;
//@Autowired
//@Qualifier("USER_BEAN")
//private User userBean;

	@GetMapping("/user")
	public String userView(Model model) {
		return "user/login";

	}
	@GetMapping("/userrg")
	public String userRgView(Model model) {
		return "user/register";
		
	}
	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		session.setAttribute("USERNAME", null);
		session.setAttribute("PASSWORD", null);
		session.setAttribute("IdKH", null);
		session.setAttribute("name", null);
		session.setAttribute("registered", false);
		return "redirect:/user";
	}
//	@GetMapping("/user/logined")
//	public String logined(Model model) {
//		List<Book> books = bookRepository.findAll();
//		model.addAttribute("books", books);
//		return "user/books";
//	}

	@GetMapping("/user/logined/{id}")
	String displayBook(@PathVariable Integer id, Model model) {
		model.addAttribute("id", id);

		Book book = bookRepository.findById(id).get();
		List<Review> reviews =reviewService.findByBookId(id);
		model.addAttribute("book", book);
		model.addAttribute("reviews",reviews);
		return "user/bookdetail";

	}

	@RequestMapping(value = "/user/checklogin", method = { RequestMethod.GET, RequestMethod.POST })
	public String userLogin(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password, Model model, HttpSession session) {
		session.setAttribute("success", false);
		session.setAttribute("cancel", false);
		if (session.getAttribute("IdKH") != null) {
			List<Book> books = bookRepository.findAll();
			model.addAttribute("books", books);
			return "user/books";
		}

		User user = userService.findByUserName(username);

		if (userService.checkLogin(username, password)) {
			// Đăng nhập thành công
			session.setAttribute("USERNAME", username);
			session.setAttribute("PASSWORD", password);
			session.setAttribute("name", user.getName());
			session.setAttribute("IdKH", user.getId());
			List<Book> books = bookRepository.findAll();
			model.addAttribute("books", books);
			return "user/books";
		} else {
			model.addAttribute("ERROR", "Username or password not exits");
			return "user/login";
		}

	}

	@PostMapping("user/order")
	public String userOrder(@RequestParam("bookId") Integer bookId, @RequestParam("so_luong") int so_luong,
			HttpSession session) {
		session.setAttribute("cancel", false);
		Integer idkh = (Integer) session.getAttribute("IdKH");
		Order order = new Order();
		Book book = bookRepository.findById(bookId).get();
		User user = userService.findById(idkh);
		order.setBook(book);
		order.setUser(user);
		order.setSo_luong(so_luong);
		orderSevice.taodon(order);
		session.setAttribute("success", true);
		return "redirect:/user/cart";
	}
	
	@PostMapping("/user/register")
	public String userRegister(@RequestParam("username") String username,@RequestParam("password") String password,
			@RequestParam("confirm_password") String cfpassword,@RequestParam("name" )String name,
			Model model,HttpSession session) {
		if(!userService.checkExist(username)) {
			model.addAttribute("ERROR","Tài khoản đã tồn tại");
			return"user/register";
		}
		if(!password.equals(cfpassword)) {
			model.addAttribute("ERROR","Mật khẩu nhập lại không khớp");
			return"user/register";	
		}
		
		User newUser= new User();
		newUser.setUsername(username);
		newUser.setPassword(cfpassword);
		newUser.setName(name);
		userService.save(newUser);
		session.setAttribute("registered", true);
		return"user/login";
		
	}
	
	@GetMapping("/user/cart")
	public String userCart(HttpSession session,Model model) {

		Integer idkh = (Integer) session.getAttribute("IdKH");
		List<Order> orders = orderSevice.findOrderByUserId(idkh);
		model.addAttribute("orders",orders);
		return "user/userorder";
	}
	
	@GetMapping("/user/cart/delete/{id}")
	public String deleteOrder(@PathVariable("id") Integer id,HttpSession session) {
		session.setAttribute("success", false);
		orderSevice.deleteOrderById(id);
		session.setAttribute("cancel", true);
		return "redirect:/user/cart";
		
	}
	
	@PostMapping("/user/review")
	public String addReview(@ModelAttribute("review") Review review,
			@RequestParam("bookId") Integer bookId,
			HttpSession session) {
		Integer idkh = (Integer) session.getAttribute("IdKH");
		Book book = bookRepository.findById(bookId).get();
		User user = userService.findById(idkh);
		review.setBook(book);
		review.setUser(user);
		reviewService.saveReview(review);
		return "redirect:/user/logined/" + bookId;
	}
}
