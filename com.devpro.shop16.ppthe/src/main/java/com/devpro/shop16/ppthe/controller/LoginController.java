package com.devpro.shop16.ppthe.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shop16.ppthe.entities.Role;
import com.devpro.shop16.ppthe.entities.Subscribe;
import com.devpro.shop16.ppthe.entities.User;
import com.devpro.shop16.ppthe.services.RoleService;
import com.devpro.shop16.ppthe.services.UserService;

@Controller
public class LoginController{
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
    private JavaMailSender emailSender;
	
	@RequestMapping(value = { "/login"}, method = RequestMethod.GET)
	public String home(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
		return "login";
	}
	
	@RequestMapping(value = { "/ajax/signup"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_SignUp(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody User user) {
		String sql = "SELECT * FROM tbl_role WHERE name = 'GUEST'";
		Role role = roleService.getOneByNativeSQL(sql);
		
		String password = user.getPassword();
		user.setPassword(new BCryptPasswordEncoder(4).encode(password));
		
		user.addRoles(role);
		userService.saveOrUpdate(user);

		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);

		return ResponseEntity.ok(jsonResult);
	}
	
	@RequestMapping(value = { "/ajax/forgot-password"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_ForgotPassword(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody User user) {
		String sql ="SELECT * FROM javaweb16.tbl_user WHERE username = '" + user.getUsername() +"' AND email = '" + user.getEmail() + "'";
		
		User u = null;
		try {
			u = userService.executeByNativeSQL(sql, 0).getData().get(0);
		} catch (Exception e) {
			u = null;
		}
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		
		if(u != null) {
			String newPass= ((int) (new Random().nextDouble() * 1000000))+"";
			u.setPassword(new BCryptPasswordEncoder(4).encode(newPass));
			userService.saveOrUpdate(u);
			sentEmail(u.getEmail(), "Lấy mật khẩu", "Mật khẩu lấy lại của bạn là: " + newPass);
			jsonResult.put("status", true);
		}else {
			jsonResult.put("status", false);
		}

		return ResponseEntity.ok(jsonResult);
	}
	
	public void sentEmail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        this.emailSender.send(message);
	}
}
