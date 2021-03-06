package com.andzj.library.action;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.andzj.library.bean.BorrowInformation;
import com.andzj.library.bean.CommentInformation;
import com.andzj.library.service.AdministratorService;
import com.andzj.library.util.MyTimeUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AddCommentAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private AdministratorService administratorService;

	public void setAdministratorService(AdministratorService administratorService) {
		this.administratorService = administratorService;
	}
	
	public String execute() {
		try {
			ActionContext context = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
			String bookIsbn= request.getParameter("book_isbn");
			String commentAccountName= request.getParameter("comment_account_name");
			String commentContent = request.getParameter("comment_content");

			String commentTimeStr = MyTimeUtils.getMyTimeStr(new Date());
			
			CommentInformation commentInformation = new CommentInformation();
			commentInformation.setBookIsbn(bookIsbn);
			commentInformation.setCommentAccountName(commentAccountName);
			commentInformation.setCommentContent(commentContent);
			commentInformation.setCommentTime(commentTimeStr);
			
			if (administratorService.addComment(commentInformation))
			{
				return null;
			}
			return ERROR;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ERROR;
	}
}
