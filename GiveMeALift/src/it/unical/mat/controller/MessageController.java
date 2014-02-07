package it.unical.mat.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

import it.unical.mat.datamapper.MessageMapper;
import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Conversation;
import it.unical.mat.domain.Message;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.service.ParseDate;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

	@RequestMapping("/ShowConversations")
	public String retriveConversations(Model model,HttpSession session){
		RegisteredUser user=(RegisteredUser)session.getAttribute("user");
		if(user!=null){
			MessageMapper mm=new MessageMapper();
//			java.util.Date today=new java.util.Date();
//			Date d=new Date(today.getTime());
//			Time t=new Time(today.getTime());
//			List<Message> mess=new ArrayList<Message>();
//			Message m=new Message(user,user,"ciaooooo", d, t, false, null);
//			mess.add(m);
//			Conversation c=new Conversation("viaggio", false, mess);
//			mm.insert(c);
			List<Conversation> conversations=mm.findUserConversations(user.getId());
//			ParseDate.getItalianFormat(conversations.get(0).computeLastMessageDate().toString());
			model.addAttribute("conversations", conversations);
			return "showMessageHome";
		}
		return "error";
	}
	
	@RequestMapping("/ShowConversationsArchiviated")
	public String retriveConversationsArchiviated(Model model,HttpSession session){
		RegisteredUser user=(RegisteredUser)session.getAttribute("user");
		if(user!=null){
			MessageMapper mm=new MessageMapper();
			List<Conversation> conversations=mm.findUserConversationsArchiviated(user.getId());
			model.addAttribute("conversations", conversations);
			return "showMessageHome";
		}
		return "error";
	}
	
	@RequestMapping(value="/ShowConversationMessages", method=RequestMethod.POST)
	public String retriveConversationMessages(@RequestParam String c, Model model,HttpSession session){
		RegisteredUser user=(RegisteredUser)session.getAttribute("user");
		if(user!=null && c!=null && !c.equals("")){
			MessageMapper mm=new MessageMapper();
			Conversation co=mm.findConversationById(Long.parseLong(c));
			model.addAttribute("messages", co.getMessages());
			model.addAttribute("conversation",co.getId());
			return "showConversationMessages";
		}
		return "error";
	}
	
	
	@RequestMapping(value="/SendMessage", method=RequestMethod.POST)
	public String registerSendMessage(@RequestParam String text, @RequestParam(required=false) String c, @RequestParam(required=false) String offer, Model model,HttpSession session){
		RegisteredUser user=(RegisteredUser)session.getAttribute("user");
		if(user!=null){
			MessageMapper mm=new MessageMapper();
			java.util.Date today=new java.util.Date();
			Date d=new Date(today.getTime());
			Time t=new Time(today.getTime());
			if(c==null || c.equals("")){
				if(offer!=null){
					RegisteredUserMapper rm=new RegisteredUserMapper();
					RegisteredUser uOff=rm.findRegisteredUserById(Long.parseLong(offer));
					System.out.println(uOff.getId());
					if(uOff!=null){				
						Message m=new Message(user,uOff,text,d,t,false,null);
						List<Message> lm=new LinkedList<>();
						lm.add(m);
						Conversation coCopy=new Conversation("viaggio", false, lm);
						mm.insert(coCopy);
						System.out.println(coCopy.getCaption());
						List<Conversation> conversations=mm.findUserConversations(user.getId());
//						ParseDate.getItalianFormat(conversations.get(0).computeLastMessageDate().toString());
						model.addAttribute("conversations", conversations);
						model.addAttribute("messages", coCopy.getMessages());
						model.addAttribute("conversation",coCopy.getId());
					}
				}
			}
			else{		
				long originalId=Long.parseLong(c);
				Conversation co=mm.findConversationById(originalId);
				Message m=new Message(user,co.computeOtherUser(user.getId()),text,d,t,false,null);
				Conversation coCopy=new Conversation();
				List<Message> lm=co.getMessages();
				lm.add(m);
				coCopy.setMessages(lm);
				mm.update(coCopy, originalId);
				model.addAttribute("messages", co.getMessages());
				model.addAttribute("conversation",co.getId());
			}
			return "showConversationMessages";
		}
		return "error";
	}
	
	
	@RequestMapping(value="/ArchiveConversation", method=RequestMethod.POST)
	public String registerArchiviationConversation(@RequestParam String c, Model model,HttpSession session){
		RegisteredUser user=(RegisteredUser)session.getAttribute("user");
		if(user!=null && c!=null && !c.equals("")){
			MessageMapper mm=new MessageMapper();
			Conversation cCopy=new Conversation();
			cCopy.setIsArchiviated(true);
			mm.update(cCopy, Long.parseLong(c));
			List<Conversation> conversations=mm.findUserConversationsArchiviated(user.getId());
			model.addAttribute("conversations", conversations);
			return "showMessageHome";
		}
		return "error";
	}
}
