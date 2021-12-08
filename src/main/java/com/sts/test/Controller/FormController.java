package com.sts.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sts.test.Service.TT0001Service;
import com.sts.test.model.TT0001Model;

@Controller
public class FormController {
	
	@Autowired
	TT0001Service serv;
	
	@RequestMapping(value = "/add")
	public String index(Model model) {
		TT0001Model vo = new TT0001Model();
		model.addAttribute("dataObj", vo);
		return "add";
	}

	@PostMapping(value = "/addSubmit")
	public String indexFormSubmit(@ModelAttribute TT0001Model dataObj) {
		TT0001Model req = new TT0001Model();
		req.setItem(dataObj.getItem());
		req.setQty(dataObj.getQty());
		serv.add_(req);
		return "redirect:/hello";
	}
	
	//=========================================================
	
	@RequestMapping(value = "/edit_request")
	public String edit_obj_get(@ModelAttribute("edit_target") TT0001Model editTarget , Model model) {
		int postId = editTarget.getId();
		TT0001Model vo = serv.get_(postId);
		model.addAttribute("editObj", vo);
		return "edit";
	}
	
	@PostMapping(value = "/editDone")
	public String editFormSubmit(@ModelAttribute TT0001Model editObj) {
		serv.update_(editObj);
		return "redirect:/hello";
	}

	@PostMapping(value = "/delete_request")
	public String deleteRecordSubmit(@ModelAttribute TT0001Model deleteObj) {
		serv.delete_(deleteObj.getId());
		return "redirect:/hello";
	}

}	