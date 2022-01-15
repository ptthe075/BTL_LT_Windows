package com.devpro.shop16.ppthe.controller.quantrivien;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shop16.ppthe.controller.BaseController;
import com.devpro.shop16.ppthe.entities.SaleOrder;
import com.devpro.shop16.ppthe.entities.SaleOrderStatus;
import com.devpro.shop16.ppthe.services.SaleOrderService;
import com.devpro.shop16.ppthe.services.SaleOrderStatusService;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController extends BaseController {
	@Autowired
	SaleOrderService saleOrderService;
	@Autowired
	SaleOrderStatusService saleOrderStatusService;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String index(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
		model.addAttribute("orders", saleOrderService.findAll());
		
		return "quantrivien/orders/list";
	}
	
	@RequestMapping(value = { "/ajax/updateOrderStatus"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_AdminUpdateOrder(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody SaleOrder saleOrder) {
		int statusId = saleOrder.getUserId();
		SaleOrderStatus saleOrderStatus = saleOrderStatusService.getById(statusId);
		SaleOrder so = saleOrderService.getById(saleOrder.getId());
		
		so.setSaleOrderStatus(saleOrderStatus);
		so.setNote(saleOrder.getNote());
		
		saleOrderService.saveOrUpdate(so);
		
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		
		String str = "";
		switch (statusId) {
		case 2:
			jsonResult.put("message", "Đơn hàng này đang được giao cho khách");
			str = "Đơn hàng có mã: "+so.getCode() + " đang được chúng tôi giao. \n Vui lòng xác nhận khi bạn nhận được hàng. \n Xin cảm ơn!";
			sentEmail(so.getCustomerEmail(), "Đơn hàng đang giao", str);
			break;
		case 4:
			jsonResult.put("message", "Bạn đã hủy thành công đơn hàng này");
			str = "Đơn hàng có mã: "+so.getCode() + " của bạn đã bị chúng tôi hủy với lý do: " + so.getNote() + "\n Xin cảm ơn!";
			sentEmail(so.getCustomerEmail(), "Đơn hàng bị hủy", str);
			break;
		}
		
		jsonResult.put("status", saleOrderStatus.getName());

		return ResponseEntity.ok(jsonResult);
	}
}
