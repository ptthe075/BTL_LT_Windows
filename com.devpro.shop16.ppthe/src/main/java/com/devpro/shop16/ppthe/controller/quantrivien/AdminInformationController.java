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
import com.devpro.shop16.ppthe.entities.Information;
import com.devpro.shop16.ppthe.services.InformationService;
import com.github.slugify.Slugify;

@Controller
@RequestMapping("/admin/informations")
public class AdminInformationController extends BaseController {

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String index(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		model.addAttribute("informations", informationService.findAll());

		return "quantrivien/informations/list";
	}

	@RequestMapping(value = { "/management", }, method = RequestMethod.GET)
	public String adminInformationAdd(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("information", new Information());

		return "quantrivien/informations/information_management";
	}

	@RequestMapping(value = { "/management/{informationId}", }, method = RequestMethod.GET)
	public String adminInformationEdit(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("informationId") int informationId) throws Exception {

		Information information = informationService.getById(informationId);

		model.addAttribute("information", information);

		return "quantrivien/informations/information_management";
	}

	@RequestMapping(value = { "/management", }, method = RequestMethod.POST)
	public String adminInformationAddOrUpdate(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @ModelAttribute("information") Information information) throws Exception {

		information.setSeo(new Slugify().slugify(information.getName()));
		informationService.saveOrUpdate(information);

		return "redirect:/admin/informations";
	}
	
	@RequestMapping(value = { "/management_ajax" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteOrRestoreInformation(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody Information information) {
		
		Information informationOld = informationService.getById(information.getId());
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		
		if(informationOld.getStatus()) {
			informationOld.setStatus(false);
			jsonResult.put("status", false);
		}else {
			informationOld.setStatus(true);
			jsonResult.put("status", true);
		}
		
		informationService.saveOrUpdate(informationOld);

		return ResponseEntity.ok(jsonResult);
	}
}
