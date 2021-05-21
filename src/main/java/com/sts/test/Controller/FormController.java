package com.sts.test.Controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.io.IOException;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sts.test.model.TT0001Model;

@Controller
public class FormController {	
	@RequestMapping(value = "/add")
	public String index(Model model) {
		MongoClient mc = new MongoClient("localhost", 27017);
		MongoDatabase db = mc.getDatabase("WayneDB");
		MongoCollection<Document> col = db.getCollection("TT0001v2_seq");
		Document document = col.find().first();
		TT0001Model vo = new TT0001Model();
		vo.setId(document.getInteger("seq_id") + 1);
		
		mc.close();
		model.addAttribute("dataObj", vo);
		return "add";
	}

	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String indexFormSubmit(@ModelAttribute TT0001Model dataObj, Model model) {

		model.addAttribute("dataObj", dataObj);
		
		MongoClient mc = new MongoClient("localhost", 27017);
		MongoDatabase db = mc.getDatabase("WayneDB");
		MongoCollection<Document> col = db.getCollection("TT0001v2");
		
		Document document = new Document("item", dataObj.getItem())
				.append("_id", dataObj.getId())
				.append("qty", dataObj.getQty());
		col.insertOne(document);
		
		col = db.getCollection("TT0001v2_seq");
		document = col.find().first();
		col.updateOne(
				eq("seq_id", document.getInteger("seq_id")),
				set("seq_id", document.getInteger("seq_id") + 1)
				);
		
		model.addAttribute("mongodb", "Insert Data to MongoDB !");
		mc.close();

		return "redirect:/hello";
	}
	
	//=========================================================
	
//	@RequestMapping(value = "/editData" , method = RequestMethod.POST)
//	public String showEditTargetMethod(@RequestParam(name="id", required=false) Double postId, Model model) {
	@PostMapping(value = "/edit_request")
	public String edit_obj_get(@ModelAttribute("edit_target") TT0001Model edit_target , Model model) {
		int postId = edit_target.getId();

		MongoClient mc = new MongoClient("localhost", 27017);
		MongoDatabase db = mc.getDatabase("WayneDB");
		MongoCollection<Document> col = db.getCollection("TT0001v2");
		
		Document document = col.find(eq("_id", postId)).first();
		if (document == null) {
		    //Document does not exist
			mc.close();
			return "redirect:/hello";
		} else {
		    //We found the document
			String str = document.toJson();
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				JsonNode jsonNode = mapper.readTree(str);
				TT0001Model editVO = new TT0001Model();

				editVO.setId(jsonNode.get("_id").asInt());
				editVO.setItem(jsonNode.get("item").asText());
				editVO.setQty(jsonNode.get("qty").asInt());

				mc.close();

				System.out.println("id :" + editVO.getId());
				System.out.println("item :" + editVO.getItem());
				System.out.println("qty :" + editVO.getQty());
				
				model.addAttribute("editObj", editVO);
				
				return "edit";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		mc.close();
		return "hello";
	}
	
	@PostMapping(value = "/editDone")
	public String editFormSubmit(@ModelAttribute TT0001Model dataObj) {
		
		System.out.println(" ■■■■■■■  id : " + dataObj.getId() + " ◆ item : " + dataObj.getItem() + " ◆ qty :  " + dataObj.getQty());
		
		MongoClient mc = new MongoClient("localhost", 27017);
		MongoDatabase db = mc.getDatabase("WayneDB");
		MongoCollection<Document> col = db.getCollection("TT0001v2");
		col.updateOne(
                eq("_id", dataObj.getId()),
                combine(set("item", dataObj.getItem()), set("qty", dataObj.getQty())));
		
		mc.close();
		System.out.println("Edit Done ■■■■■■■■■■■■■■■■■■■■■■■■");
		return "redirect:/hello";
	}
	
	@PostMapping(value = "/delete_request")
	public String deleteRecordSubmit(@ModelAttribute TT0001Model deleteObj) {
		
		System.out.println("DELETE :::: ■■■■■■■  id : " + deleteObj.getId());
		
		MongoClient mc = new MongoClient("localhost", 27017);
		MongoDatabase db = mc.getDatabase("WayneDB");
		MongoCollection<Document> col = db.getCollection("TT0001v2");
		
		col.deleteOne(eq("_id", deleteObj.getId()));
		
		mc.close();
		
		System.out.println("DELETE Done ■■■■■■■■■■■■■■■■■■■■■■■■");
		return "redirect:/hello";
	}

}	