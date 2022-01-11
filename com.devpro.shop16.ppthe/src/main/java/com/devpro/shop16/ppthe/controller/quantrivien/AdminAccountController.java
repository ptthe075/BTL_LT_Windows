package com.devpro.shop16.ppthe.controller.quantrivien;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.shop16.ppthe.controller.BaseController;
import com.devpro.shop16.ppthe.entities.Category;
import com.devpro.shop16.ppthe.entities.User;
import com.devpro.shop16.ppthe.services.UserService;

@Controller
@RequestMapping("/admin/accounts")
public class AdminAccountController extends BaseController {

	@Autowired
	UserService userService;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String index(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("employees", userService.findByRole("ADMIN"));
		model.addAttribute("users", userService.findByRole("GUEST"));

		return "quantrivien/accounts/list";
	}
	

	@RequestMapping(value = { "/management", }, method = RequestMethod.GET)
	public String adminUserAdd(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("user", new User());

		return "quantrivien/accounts/account_management";
	}

	@RequestMapping(value = { "/management/{accountId}", }, method = RequestMethod.GET)
	public String adminUserEdit(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("accountId") int accountId) throws Exception {

		User user = userService.getById(accountId);

		model.addAttribute("user", user);

		return "quantrivien/accounts/account_management";
	}

	@RequestMapping(value = { "/management", }, method = RequestMethod.POST)
	public String adminUserAddOrUpdate(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @ModelAttribute("user") User user) throws Exception {

		if (user.getId() == null || user.getId() <= 0) {
		} else {
		}

		return "redirect:/admin/accounts";
	}
	
	@RequestMapping(value = { "/management_ajax" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteOrRestoreUser(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody User user) {
		
		User userOld = userService.getById(user.getId());
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		
		if(userOld.getStatus()) {
			userOld.setStatus(false);
			jsonResult.put("status", false);
		}else {
			userOld.setStatus(true);
			jsonResult.put("status", true);
		}
		
		userService.saveOrUpdate(userOld);

		return ResponseEntity.ok(jsonResult);
	}
}
